/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prism_project.data_process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
	

// IMPORTANT:  DO NOT TOUCH THESE FREAKING COMPLICATED CODES BECAUSE EVEN I TAKE A LOT OF TIME TO UNDERSTAND WHAT THE ...^..&^*&..?#$.. I WROTE
// MY APPOLOGY  ^^:


//This class is created only when there is at least 1 condition --> no need to check null condition
public class Get_Cost_Information {
	private List<List<String>>[] all_priority_condition_static_identifiers;
	private List<List<String>>[] all_priority_condition_dynamic_identifiers;
	private List<String>[] all_priority_condition_dynamic_dentifiers_column_indexes;
	private String[][] all_priority_condition_info;	
	
	private int action_type_col_id;
	private Object[][][] yield_tables_values;
	private List<String> yield_tables_original_col_names_list, yield_tables_sorted_col_names_list;
	private int[] get_original_col_id_from_sorted_col_id;
	
	public Get_Cost_Information(Read_Database read_database, List<String> cost_condition_list) {
		all_priority_condition_static_identifiers = new ArrayList[cost_condition_list.size()];
		all_priority_condition_dynamic_identifiers = new ArrayList[cost_condition_list.size()];
		all_priority_condition_dynamic_dentifiers_column_indexes = new ArrayList[cost_condition_list.size()];
		all_priority_condition_info = new String[cost_condition_list.size()][];
		
		// Just do this once when an object of this class is created, not every time we encounter a variable
		for (int priority = 0; priority < cost_condition_list.size(); priority++) {		// Looping from the highest priority cost condition to the lowest
			String[] this_condition_info = cost_condition_list.get(priority).split("\t");
			all_priority_condition_info[priority] = this_condition_info;
			all_priority_condition_static_identifiers[priority] = get_condition_static_identifiers(this_condition_info[4]);	// column 4 is static identifiers
			all_priority_condition_dynamic_identifiers[priority] = get_condition_dynamic_identifiers(this_condition_info[5]);	// column 5 is dynamic identifiers
			all_priority_condition_dynamic_dentifiers_column_indexes[priority] = get_condition_dynamic_dentifiers_column_indexes(this_condition_info[5]);	// column 5 is dynamic identifiers
		
			
			// sort for Binary search used in:     are_all_static_identifiers_matched()
			for (List<String> this_static_identifier: all_priority_condition_static_identifiers[priority]) {
				Collections.sort(this_static_identifier);
			}	
			
			// sort for Binary search used in:     are_all_dynamic_identifiers_matched()
			for (List<String> this_dynamic_identifier: all_priority_condition_dynamic_identifiers[priority]) {
				Collections.sort(this_dynamic_identifier);
			}			
		
		}
		
		// Some more set up
		this.yield_tables_values = read_database.get_yield_tables_values();
		
		// This is to prepare for Binary Search on column index  --->   get_cost_value
		// Basically, we need a sorted list, use binary search to get the sorted id, then convert the sorted id to original (unsorted) id 
		yield_tables_original_col_names_list = Arrays.asList(read_database.get_yield_tables_column_names());	// Convert array to list	
		yield_tables_sorted_col_names_list = new ArrayList<String>(yield_tables_original_col_names_list); 
		Collections.sort(yield_tables_sorted_col_names_list);
		get_original_col_id_from_sorted_col_id = new int[yield_tables_sorted_col_names_list.size()];
		for (String sorted_name: yield_tables_sorted_col_names_list) {
			for (String original_name: yield_tables_original_col_names_list) {
				int sorted_id = yield_tables_sorted_col_names_list.indexOf(sorted_name);
				int original_id = yield_tables_original_col_names_list.indexOf(original_name);
				if (sorted_name.equals(original_name)) {
					get_original_col_id_from_sorted_col_id[sorted_id] = original_id;
				}
			}
		}
		
		action_type_col_id = yield_tables_original_col_names_list.indexOf("action_type");
	}
			
	
	public double get_cost_value(				
			String var_name, int table_id_to_find, int row_id_to_find,
			List<String> cost_condition_list,
			List<String> conversion_cost_after_disturbance_name_list,		// i.e. P P disturbance		P D disturbance			This is already sorted because we already sorted all layers, including layer5
			List<Double> conversion_cost_after_disturbance_value_list) {
		

		double value_to_return = 0;
		
		
		if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
			if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
				String var_action_type = yield_tables_values[table_id_to_find][row_id_to_find][action_type_col_id].toString();
				
				// The following includes 1 list for the action_cost and 1 list for the conversion_cost
				List<List<List<String>>> final_cost_list = get_final_action_cost_list_and_conversion_cost_list_for_this_variable(
						cost_condition_list, var_name, var_action_type,
						table_id_to_find, row_id_to_find);
				
				
				// action_cost: include 2 lists for column name (i.e. hca_allsx) and value (i.e. 360)
				for (int item = 0; item < final_cost_list.get(0).get(0).size(); item++) {	// loop list:  final_cost_list.get(0).get(0) which is final_action_cost_column_list
					// Add cost per acre
					if (final_cost_list.get(0).get(0).get(item).equals("acres")) {
						value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(0).get(1).get(item));
					} 
					// Add cost per unit of the yield table column
					else {
						int sorted_id = Collections.binarySearch(yield_tables_sorted_col_names_list, final_cost_list.get(0).get(0).get(item));
						int col_id = get_original_col_id_from_sorted_col_id[sorted_id];
						value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(0).get(1).get(item)) * Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][col_id].toString());
					}
				}								
				
				
				// convert list to 1-D array since it is faster to get item from array than get item from list?????? --> should I remove this?
				double[] conversion_cost_after_disturbance_value = Stream.of(conversion_cost_after_disturbance_value_list.toArray(new Double[conversion_cost_after_disturbance_value_list.size()])).mapToDouble(Double::doubleValue).toArray();
				
				
				// conversion_cost: include 2 lists for column name (i.e. P D action) and value (i.e. 240)
				for (int item = 0; item < final_cost_list.get(1).get(0).size(); item++) {	// loop list:  final_cost_list.get(1).get(0) which is final_conversion_cost_column_list
					// add conversion cost for post management action (i.e clear cut) or post replacing disturbance (i.e. SRFire)
					// note only one of them is true: for example if it is clear cut --> no replacing disturbance anymore, replacing disturbance can happen in areas where no clear cut implemented
					if (Get_Variable_Information.get_rotation_period(var_name) == Get_Variable_Information.get_period(var_name)) {	// period is the rotation period (this if guarantees variable to be EA_E or EA_R)
						String conversion_cost_to_apply = 
								Get_Variable_Information.get_layer5(var_name) + " " +
								Get_Variable_Information.get_regenerated_covertype(var_name) + " " + "action";
						if (final_cost_list.get(1).get(0).get(item).equals(conversion_cost_to_apply)) {
							value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(1).get(1).get(item));
						} 
					} else {	// when period is not the rotation_period (variable can be anything except EA_E or EA_R in period = rotation period when clear cut happens). Here replacing disturbances can happen
						int index = Collections.binarySearch(conversion_cost_after_disturbance_name_list, final_cost_list.get(1).get(0).get(item));
						if (index >= 0) {
							value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(1).get(1).get(item)) * conversion_cost_after_disturbance_value[index];		
						}
					}	
				}
				
			}
		}
		return value_to_return;	
	}

	
	private List<List<List<String>>> get_final_action_cost_list_and_conversion_cost_list_for_this_variable(
			List<String> cost_condition_list, String var_name, String var_action_type,
			int table_id_to_find, int row_id_to_find) {	
		
		List<String> final_action_cost_column_list = new ArrayList<String>();		// example: 	"acres", "...", "hca_allsx", ... -->see table 8a in the GUI of Cost Management
		List<String> final_action_cost_value_list = new ArrayList<String>(); 		// example: 	"360", "...", "1.2", ...
		
		List<String> final_conversion_cost_column_list = new ArrayList<String>();	// example: P D action         	W L disturbance 
		List<String> final_conversion_cost_value_list = new ArrayList<String>();	// example: 240         		120 
		
									
		for (int priority = 0; priority < cost_condition_list.size(); priority++) {		// Looping from the highest priority cost condition to the lowest			
			// If this condition is satisfied
			if (are_all_static_identifiers_matched(var_name, all_priority_condition_static_identifiers[priority]) && 
					are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
				
				// For action_cost
				if (all_priority_condition_info[priority][2].length() > 0) {		// this guarantees the string is not ""
					List<String[]> action_cost_list = get_condition_action_cost(all_priority_condition_info[priority][2], var_action_type);
					for (String[] c: action_cost_list) {			// c example: clearcut acres 360		c example2: clearcut hca_allsx 0
						if (!final_action_cost_column_list.contains(c[1])) {		// only null is escape, the GUI already guarantees the value >=0			
							final_action_cost_column_list.add(c[1]);	// i.e. acres    hca_allsx
							final_action_cost_value_list.add(c[2]);		// i.e. 360
						}
					}
				}
				
				// For conversion cost
				if (all_priority_condition_info[priority][3].length() > 0) {		// this guarantees the string is not ""
					List<String[]> conversion_cost_list = get_condition_conversion_cost(all_priority_condition_info[priority][3]);
					for (String[] c: conversion_cost_list) {			// c example:  P D action 240         	W L disturbance 120
						if (!final_conversion_cost_column_list.contains(c[0] + " " + c[1] + " " + c[2])) {		// only null is escape, the GUI already guarantees the value >=0				
							final_conversion_cost_column_list.add(c[0] + " " + c[1] + " " + c[2]);		// i.e. P D action		W L disturbance
							final_conversion_cost_value_list.add(c[3]);		// i.e. 240		120
						}	
					}
				}
				
			}
		}		
				
		// Combine the above 2 action_cost lists into a single new list
		List<List<String>> final_action_cost_list = new ArrayList<List<String>>();
		final_action_cost_list.add(final_action_cost_column_list);
		final_action_cost_list.add(final_action_cost_value_list);
		
		// Combine the above 2 conversion_cost lists into a single new list
		List<List<String>> final_conversion_cost_list = new ArrayList<List<String>>();
		final_conversion_cost_list.add(final_conversion_cost_column_list);
		final_conversion_cost_list.add(final_conversion_cost_value_list);
		
		// Combine the above 2 final lists into a single big final list
		List<List<List<String>>> final_cost_list = new ArrayList<List<List<String>>>();
		final_cost_list.add(final_action_cost_list);
		final_cost_list.add(final_conversion_cost_list);
		
		return final_cost_list;
	}
	
	
	private Boolean are_all_dynamic_identifiers_matched(Object[][][] yield_tables_values, int table_id_to_find, int row_id_to_find,
			List<String> dynamic_identifiers_column_indexes, List<List<String>> dynamic_identifiers) {
		
		if (!dynamic_identifiers_column_indexes.contains("NoIdentifier")) {	//If there are dynamic identifiers
			//Check if in the same row of this yield table we have all the dynamic identifiers match				
			for (int dynamic_count = 0; dynamic_count < dynamic_identifiers_column_indexes.size(); dynamic_count++) {
				int current_dynamic_column = Integer.parseInt(dynamic_identifiers_column_indexes.get(dynamic_count));		//This is the yield table column of the dynamic identifier
				List<String> this_dynamic_identifier = dynamic_identifiers.get(dynamic_count);
								
				if (this_dynamic_identifier.get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
															
					for (int element = 0; element < this_dynamic_identifier.size(); element++) {	//Loop all elements (all ranges) of this range identifier
						String[] min_and_max = this_dynamic_identifier.get(element).split(",");									
						double min_value = Double.parseDouble(min_and_max[0].replace("[", ""));
						double max_value = Double.parseDouble(min_and_max[1].replace(")", ""));																	
						if (!(min_value <= yt_value && yt_value < max_value)) {
							return false;
						}
					}										
				} else { // if this is a discrete identifier
					int index = Collections.binarySearch(this_dynamic_identifier, yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
					if (index < 0) 	{	// If all selected items in this list do not contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
						return false;			
					}
				}
			}
		}	
		return true;
	}
	
	
	private Boolean are_all_static_identifiers_matched(String var_name, List<List<String>> static_identifier) {	
		if (Collections.binarySearch(static_identifier.get(0), Get_Variable_Information.get_layer1(var_name)) < 0) return false;
		if (Collections.binarySearch(static_identifier.get(1), Get_Variable_Information.get_layer2(var_name)) < 0) return false;
		if (Collections.binarySearch(static_identifier.get(2), Get_Variable_Information.get_layer3(var_name)) < 0) return false;
		if (Collections.binarySearch(static_identifier.get(3), Get_Variable_Information.get_layer4(var_name)) < 0) return false;
		if (Get_Variable_Information.get_forest_status(var_name).equals("E")) {
			if (Collections.binarySearch(static_identifier.get(4), Get_Variable_Information.get_layer5(var_name)) < 0) return false;	// layer5 cover type
			if (Collections.binarySearch(static_identifier.get(5), Get_Variable_Information.get_layer6(var_name)) < 0) return false;	// layer6: size class
		}
		if (Collections.binarySearch(static_identifier.get(6), Get_Variable_Information.get_method(var_name) + "_" + Get_Variable_Information.get_forest_status(var_name)) < 0) return false;
		if (Collections.binarySearch(static_identifier.get(7), String.valueOf(Get_Variable_Information.get_period(var_name))) < 0) return false;
		return true;
		
		
//		if (!static_identifier.get(0).contains(Get_Variable_Information.get_layer1(var_name))) return false;
//		if (!static_identifier.get(1).contains(Get_Variable_Information.get_layer2(var_name))) return false;
//		if (!static_identifier.get(2).contains(Get_Variable_Information.get_layer3(var_name))) return false;
//		if (!static_identifier.get(3).contains(Get_Variable_Information.get_layer4(var_name))) return false;
//		if (Get_Variable_Information.get_forest_status(var_name).equals("E") && !Get_Variable_Information.get_method(var_name).equals("MS") && !Get_Variable_Information.get_method(var_name).equals("BS")) {
//			if (!static_identifier.get(4).contains(Get_Variable_Information.get_layer5(var_name))) return false;	// layer5 cover type
//			if (!static_identifier.get(5).contains(Get_Variable_Information.get_layer6(var_name))) return false;	// layer6: size class
//		}
//		if (!static_identifier.get(6).contains(Get_Variable_Information.get_method(var_name) + "_" + Get_Variable_Information.get_forest_status(var_name))) return false;
//		if (!static_identifier.get(7).contains(String.valueOf(Get_Variable_Information.get_period(var_name)))) return false;					
//		return true;
	}			
	
	
	private List<String[]> get_condition_action_cost(String action_cost_info, String var_action_type) {	
		//Read the whole cell into array
		String[] action_cost_array = action_cost_info.split(";");
		List<String[]> action_cost = new ArrayList<String[]>();		
		for (String infor: action_cost_array) {
			String[] info_array = infor.split("\\s+");		// info example: clearcut hca_allsx 360
			if (info_array[0].equals(var_action_type)) {	// info_array[0] = clearcut			only get the info matched with the var_action_type
				action_cost.add(info_array);
			}
		}		
		return action_cost;
	}
	
		
	private List<String[]> get_condition_conversion_cost(String conversion_cost_info) {	
		//Read the whole cell into array
		String[] conversion_cost_array = conversion_cost_info.split(";");
		List<String[]> conversion_cost = new ArrayList<String[]>();		
		for (String info: conversion_cost_array) {
			String[] info_array = info.split("\\s+");		// info example: P D action 240         	W L disturbance 120
			conversion_cost.add(info_array);
		}		
		return conversion_cost;
	}
		
	
	private List<List<String>> get_condition_static_identifiers(String static_identifiers_info) {
		List<List<String>> cost_condition_static_identifiers = new ArrayList<List<String>>();		
		//Read the whole cell into array
		String[] static_layer_info = static_identifiers_info.split(";");
		int total_static_identifiers = static_layer_info.length;
		
		//Get all static Identifiers to be in the list
		for (int i = 0; i < total_static_identifiers; i++) {		// 6 first identifiers are strata's 6 layers (layer 0 to 5)	then method (6) then period	(7)
			List<String> thisIdentifier = new ArrayList<String>();			
			String[] identifierElements = static_layer_info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}		
			cost_condition_static_identifiers.add(thisIdentifier);
		}
			
		return cost_condition_static_identifiers;
	}
	
		
	private List<List<String>> get_condition_dynamic_identifiers(String dynamic_identifiers_info) {
		List<List<String>> cost_condition_dynamic_identifiers = new ArrayList<List<String>>();		
		//Read the whole cell into array
		String[] info = dynamic_identifiers_info.split(";");
		int total_dynamic_identifiers = info.length;
			
		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamic_identifiers; i++) {	
			List<String> thisIdentifier = new ArrayList<String>();			
			String[] identifierElements = info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		// Ignore the first element which is the identifier column index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}			
			cost_condition_dynamic_identifiers.add(thisIdentifier);
		}			
		return cost_condition_dynamic_identifiers;
	}
	
	
	private List<String> get_condition_dynamic_dentifiers_column_indexes(String dynamic_identifiers_info) {
		List<String> cost_condition_dynamic_dentifiers_column_indexes = new ArrayList<String>();
			
		//Read the whole cell into array
		String[] info = dynamic_identifiers_info.split(";");
		int total_dynamic_identifiers = info.length;

		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamic_identifiers; i++) {	
			String[] identifierElements = info[i].split("\\s+");				//space delimited
			//add the first element which is the identifier column index
			cost_condition_dynamic_dentifiers_column_indexes.add(identifierElements[0].replaceAll("\\s+",""));
		}			
		return cost_condition_dynamic_dentifiers_column_indexes;
	}
}
