/*
 * Copyright (C) 2016-2020 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM. If not, see <http://www.gnu.org/licenses/>.
 */

package prism_project.data_process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;
// IMPORTANT:  THE LOGIC HERE IS COMPLEX. MY APPOLOGY!


//This class is created only when there is at least 1 condition --> no need to check null condition
public class Information_Cost {
	private List<List<String>>[] all_priority_condition_static_identifiers;
	private List<List<String>>[] all_priority_condition_dynamic_identifiers;
	private List<String>[] all_priority_condition_dynamic_dentifiers_column_indexes;
	private String[][] all_priority_condition_info;	
	private List<List<String>> all_layers;
	
	private int action_type_col_id;
	private String[][][] yield_tables_values;
	private List<String> yield_tables_original_col_names_list, yield_tables_sorted_col_names_list;
	private int[] get_original_col_id_from_sorted_col_id;
	
	public Information_Cost(Read_Database read_database, List<String> cost_condition_list, List<List<String>> all_layers) {
		this.all_layers = all_layers;
		
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
			Information_Variable var_info, int table_id_to_find, int row_id_to_find,
			List<String> cost_condition_list,
			List<String> conversion_after_disturbances_classification_list,		// i.e. P P disturbance		P D disturbance			This is already sorted because we already sorted all layers, including layer5
			List<Double> conversion_after_disturbances_total_loss_rate_list) {
		

		double value_to_return = 0;
		
		
		if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
			if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
				String var_action_type = yield_tables_values[table_id_to_find][row_id_to_find][action_type_col_id];
				
				// The following includes 1 list for the action_cost and 1 list for the conversion_cost
				List<List<List<String>>> final_cost_list = get_final_action_cost_list_and_conversion_cost_list_for_this_variable(cost_condition_list, var_info, var_action_type, table_id_to_find, row_id_to_find);
				
				
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
						value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(0).get(1).get(item)) * Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][col_id]);
					}
				}								
				
				
				// convert list to 1-D array since it is faster to get item from array than get item from list?????? --> should I remove this?
				double[] conversion_after_disturbances_total_loss_rate = Stream.of(conversion_after_disturbances_total_loss_rate_list.toArray(new Double[conversion_after_disturbances_total_loss_rate_list.size()])).mapToDouble(Double::doubleValue).toArray();
				
				
				// conversion_cost: include 2 lists for column name (i.e. P D action) and value (i.e. 240)
				for (int item = 0; item < final_cost_list.get(1).get(0).size(); item++) {	// loop list:  final_cost_list.get(1).get(0) which is final_conversion_cost_column_list
					// add conversion cost for post management action (i.e clear cut) or post replacing disturbance (i.e. SRFire)
					// note only one of them is true: for example if it is clear cut --> no replacing disturbance anymore, replacing disturbance can happen in areas where no clear cut implemented
					if (var_info.get_rotation_period() == var_info.get_period()) {	// period is the rotation period (this if guarantees variable to be EA_E or EA_R)
						String conversion_cost_to_apply = var_info.get_layer5() + " " + var_info.get_regenerated_covertype() + " " + "action";
						if (final_cost_list.get(1).get(0).get(item).equals(conversion_cost_to_apply)) {
							value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(1).get(1).get(item));
						} 
					} else {	// when period is not the rotation_period (variable can be anything except EA_E or EA_R in period = rotation period when clear cut happens). Here replacing disturbances can happen
						int index = Collections.binarySearch(conversion_after_disturbances_classification_list, final_cost_list.get(1).get(0).get(item));		// i.e.   { (P P disturbance), (P D disturbance)} would contain (P P disturbance)
						if (index >= 0) {
							value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(1).get(1).get(item)) * conversion_after_disturbances_total_loss_rate[index];		// total_loss_rate is from all SRs for this conversion classification
						}
					}	
				}
				
			}
		}
		return value_to_return;	
	}

	
	private List<List<List<String>>> get_final_action_cost_list_and_conversion_cost_list_for_this_variable(
			List<String> cost_condition_list, Information_Variable var_info, String var_action_type,
			int table_id_to_find, int row_id_to_find) {	
		
		List<String> final_action_cost_column_list = new ArrayList<String>();		// example: 	"acres", "...", "hca_allsx", ... -->see table 8a in the GUI of Cost Management
		List<String> final_action_cost_value_list = new ArrayList<String>(); 		// example: 	"360", "...", "1.2", ...
		
		List<String> final_conversion_cost_column_list = new ArrayList<String>();	// example: P D action         	W L disturbance 
		List<String> final_conversion_cost_value_list = new ArrayList<String>();	// example: 240         		120 
		
									
		for (int priority = 0; priority < cost_condition_list.size(); priority++) {		// Looping from the highest priority cost condition to the lowest			
			// If this condition is satisfied
			if (are_all_static_identifiers_matched(var_info, all_priority_condition_static_identifiers[priority]) && 
					are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
				
				// For action_cost
				if (all_priority_condition_info[priority][2].length() > 0) {		// this guarantees the string is not ""
					List<String[]> action_cost_list = get_condition_action_cost(all_priority_condition_info[priority][2], var_action_type);
					for (String[] c: action_cost_list) {			// c example: clearcut acres 360		c example2: clearcut hca_allsx 0
						if (!final_action_cost_column_list.contains(c[1])) {		// the GUI already guarantees the value >=0		-->  this mean null (as seen in the GUI) will be escaped	
							final_action_cost_column_list.add(c[1]);	// i.e. acres    hca_allsx
							final_action_cost_value_list.add(c[2]);		// i.e. 360
						}
					}
				}
				
				// For conversion cost
				if (all_priority_condition_info[priority][3].length() > 0) {		// this guarantees the string is not ""
					List<String[]> conversion_cost_list = get_condition_conversion_cost(all_priority_condition_info[priority][3]);
					for (String[] c: conversion_cost_list) {			// c example:  P D action 240         	W L disturbance 120
						if (!final_conversion_cost_column_list.contains(c[0] + " " + c[1] + " " + c[2])) {		// the GUI already guarantees the value >=0		-->  this mean null (as seen in the GUI) will be escaped				
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
	
	
	private Boolean are_all_dynamic_identifiers_matched(String[][][] yield_tables_values, int table_id_to_find, int row_id_to_find,
			List<String> dynamic_identifiers_column_indexes, List<List<String>> dynamic_identifiers) {
		
		if (!dynamic_identifiers_column_indexes.get(0).equals("NoIdentifier")) {	//If there are dynamic identifiers, Check if in the same row of this yield table we have all the dynamic identifiers match	
			int identifiers_count = 0;
			for (List<String> this_dynamic_identifier : dynamic_identifiers) {	// loop all dynamic identifiers
				int current_dynamic_column = Integer.parseInt(dynamic_identifiers_column_indexes.get(identifiers_count));		//This is the yield table column of the dynamic identifier
				if (this_dynamic_identifier.get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column]);
					for (String range : this_dynamic_identifier) {	//Loop all ranges of this range identifier
						StringTokenizer tok = new StringTokenizer(range, ",");	// split by ,
						// will for sure have 2 items in the range --> do not need while check here
						double min_value = Double.parseDouble(tok.nextToken().replace("[", ""));
						double max_value = Double.parseDouble(tok.nextToken().replace(")", ""));	
						if (!(min_value <= yt_value && yt_value < max_value)) {
							return false;
						}
					}										
				} else { // if this is a discrete identifier
					int index = Collections.binarySearch(this_dynamic_identifier, yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column]);
					if (index < 0) 	{	// If all selected items in this list do not contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
						return false;			
					}
				}
				identifiers_count++;
			}
		}	
		return true;
	}
	
	
	private Boolean are_all_static_identifiers_matched(Information_Variable var_info, List<List<String>> static_identifiers) {	
//		// The below check also implements Speed Boost RRB9
//		if (static_identifiers.get(0).size() < all_layers.get(0).size() && Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0) return false;
//		if (static_identifiers.get(1).size() < all_layers.get(1).size() && Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0) return false;
//		if (static_identifiers.get(2).size() < all_layers.get(2).size() && Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0) return false;
//		if (static_identifiers.get(3).size() < all_layers.get(3).size() && Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0) return false;
//		if (static_identifiers.get(4).size() < all_layers.get(4).size() && Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0) return false;
//		if (var_info.get_forest_status().equals("E") && static_identifiers.get(5).size() < all_layers.get(5).size() && Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) return false;	// layer6: size class
//		if (Collections.binarySearch(static_identifiers.get(6), var_info.get_method() + "_" + var_info.get_forest_status()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(7), String.valueOf(var_info.get_period())) < 0) return false;
//		return true;
		
		
		// The below check also implements Speed Boost RRB9. Same as above but it is the faster this way 
		if (
		(static_identifiers.get(0).size() < all_layers.get(0).size() && Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0) ||
		(static_identifiers.get(1).size() < all_layers.get(1).size() && Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0) ||
		(static_identifiers.get(2).size() < all_layers.get(2).size() && Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0) ||
		(static_identifiers.get(3).size() < all_layers.get(3).size() && Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0) ||
		(static_identifiers.get(4).size() < all_layers.get(4).size() && Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0) ||
		(var_info.get_forest_status().equals("E") && static_identifiers.get(5).size() < all_layers.get(5).size() && Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) ||
		Collections.binarySearch(static_identifiers.get(6), var_info.get_method() + "_" + var_info.get_forest_status()) < 0 ||
		Collections.binarySearch(static_identifiers.get(7), String.valueOf(var_info.get_period())) < 0) 
		{
			return false;
		}
		return true;
		
		
		
		
//		// Without RRB9 --> no need all_layers
//		if (
//		Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0 ||
//		(var_info.get_forest_status().equals("E") && Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) ||
//		Collections.binarySearch(static_identifiers.get(6), var_info.get_method() + "_" + var_info.get_forest_status()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(7), String.valueOf(var_info.get_period())) < 0) 
//		{
//			return false;
//		}
//		return true;
//		
//		
//		// Without RRB9 --> no need all_layers		
//		if (Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0) return false;	// layer5 cover type
//		if (var_info.get_forest_status().equals("E") && Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) return false;	// layer6: size class
//		if (Collections.binarySearch(static_identifiers.get(6), var_info.get_method() + "_" + var_info.get_forest_status()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(7), String.valueOf(var_info.get_period())) < 0) return false;
//		return true;
	}			
	
	
	private List<String[]> get_condition_action_cost(String action_cost_info, String var_action_type) {	
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<String[]> action_cost = new ArrayList<String[]>();	
		StringTokenizer t1 = new StringTokenizer(action_cost_info, ";");
		while (t1.hasMoreTokens()) {			// loop through each element (separated by ;)
			String infor = t1.nextToken();		// info example: clearcut hca_allsx 360
			
			String[] info_array = new String[3];
			int count = 0;
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			while (t2.hasMoreTokens()) {
				info_array[count] = t2.nextToken();
				count++;
			}
		    
			if (info_array[0].equals(var_action_type)) {	// info_array[0] = clearcut			only get the info matched with the var_action_type
				action_cost.add(info_array);
			}
		}
		
		return action_cost;
	}
	
		
	private List<String[]> get_condition_conversion_cost(String conversion_cost_info) {	
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<String[]> conversion_cost = new ArrayList<String[]>();	
		StringTokenizer t1 = new StringTokenizer(conversion_cost_info, ";");
		while (t1.hasMoreTokens()) {		// loop through each element (separated by ;)
			String infor = t1.nextToken();	// info example: P D action 240         	W L disturbance 120
			
			String[] info_array = new String[4];
			int count = 0;
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			while (t2.hasMoreTokens()) {
				info_array[count] = t2.nextToken();
				count++;
			}
		    
			conversion_cost.add(info_array);
		}
		
		return conversion_cost;
	}
		
	
	private List<List<String>> get_condition_static_identifiers(String static_identifiers_info) {
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<List<String>> cost_condition_static_identifiers = new ArrayList<List<String>>();	
		StringTokenizer t1 = new StringTokenizer(static_identifiers_info, ";");
		while (t1.hasMoreTokens()) {		// loop through each element (separated by ;) --> loop each static identifier which has: 6 first identifiers are strata's 6 layers (layer 0 to 5)	then method (6) then period	(7)
			String infor = t1.nextToken();
			
			List<String> thisIdentifier = new ArrayList<String>();
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			if (t2.hasMoreTokens()) t2.nextToken();					// Ignore the first element which is the identifier index
			while (t2.hasMoreTokens()) thisIdentifier.add(t2.nextToken().replaceAll("\\s+",""));	// Add element name, if name has spaces then remove all the spaces
			cost_condition_static_identifiers.add(thisIdentifier);
		}
		return cost_condition_static_identifiers;
	}
	
		
	private List<List<String>> get_condition_dynamic_identifiers(String dynamic_identifiers_info) {
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<List<String>> cost_condition_dynamic_identifiers = new ArrayList<List<String>>();
		StringTokenizer t1 = new StringTokenizer(dynamic_identifiers_info, ";");
		while (t1.hasMoreTokens()) {		// loop through each element (separated by ;) --> loop each dynamic identifier
			String infor = t1.nextToken();
			
			List<String> thisIdentifier = new ArrayList<String>();
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			if (t2.hasMoreTokens()) t2.nextToken();					// Ignore the first element which is the identifier index
			while (t2.hasMoreTokens()) thisIdentifier.add(t2.nextToken().replaceAll("\\s+",""));	// Add element name, if name has spaces then remove all the spaces
			cost_condition_dynamic_identifiers.add(thisIdentifier);
		}
		return cost_condition_dynamic_identifiers;
	}
	
	
	private List<String> get_condition_dynamic_dentifiers_column_indexes(String dynamic_identifiers_info) {
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<String> cost_condition_dynamic_dentifiers_column_indexes = new ArrayList<String>();
		StringTokenizer t1 = new StringTokenizer(dynamic_identifiers_info, ";");
		while (t1.hasMoreTokens()) {		// loop through each element (separated by ;) --> loop each dynamic identifier
			String infor = t1.nextToken();
			
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			if (t2.hasMoreTokens()) cost_condition_dynamic_dentifiers_column_indexes.add(t2.nextToken().replaceAll("\\s+",""));		// add the first element which is the identifier column index
		}
		return cost_condition_dynamic_dentifiers_column_indexes;
	}
}
