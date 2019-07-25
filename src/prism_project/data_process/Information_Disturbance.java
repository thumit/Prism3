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
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
	
// This class is created only when there is at least 1 condition --> no need to check null condition
public class Information_Disturbance {
	private List<List<String>>[] all_priority_condition_static_identifiers;
	private List<List<String>>[] all_priority_condition_dynamic_identifiers;
	private List<String>[] all_priority_condition_dynamic_dentifiers_column_indexes;
	private String[][] all_priority_condition_info;	
	private List<List<String>> all_layers;
	private Object[][][] yield_tables_values;
	
	private Object[] all_condition_rd_percentage;	// contains 3D array rd_percentage[][][] for all conditions
	
	public Information_Disturbance(Read_Database read_database, List<String> disturbance_condition_list, List<List<String>> all_layers) {
		this.all_layers = all_layers;
		
		all_priority_condition_static_identifiers = new ArrayList[disturbance_condition_list.size()];
		all_priority_condition_dynamic_identifiers = new ArrayList[disturbance_condition_list.size()];
		all_priority_condition_dynamic_dentifiers_column_indexes = new ArrayList[disturbance_condition_list.size()];
		all_priority_condition_info = new String[disturbance_condition_list.size()][];
		all_condition_rd_percentage = new Object[disturbance_condition_list.size()];
		
		// Just do this once when an object of this class is created, not every time we encounter a variable
		for (int priority = 0; priority < disturbance_condition_list.size(); priority++) {		// Looping from the highest priority condition to the lowest, each priority is a row in the table GUI
			all_priority_condition_info[priority] = disturbance_condition_list.get(priority).split("\t");
			all_condition_rd_percentage[priority] = get_3D_array_from_regeneration_info(all_priority_condition_info[priority][3]);		// column 3 is regeneration_info
			all_priority_condition_static_identifiers[priority] = get_condition_static_identifiers(all_priority_condition_info[priority][4]);	// column 4 is static identifiers
			all_priority_condition_dynamic_identifiers[priority] = get_condition_dynamic_identifiers(all_priority_condition_info[priority][5]);	// column 5 is dynamic identifiers
			all_priority_condition_dynamic_dentifiers_column_indexes[priority] = get_condition_dynamic_dentifiers_column_indexes(all_priority_condition_info[priority][5]);	// column 5 is dynamic identifiers
			
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
	}
			
	
	public int get_rd_condition_id_for_this_var(Information_Variable var_info, int table_id_to_find, int row_id_to_find) {
		int id = -9999;
		
		if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
			if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
				int priority = 0;
				while (id == -9999 && priority < all_priority_condition_info.length) {		// loop all condition until found the one matched
					if (are_all_static_identifiers_matched(var_info, all_priority_condition_static_identifiers[priority])
								&& are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
						id = priority;
					}
					priority++;
				}
			}
		}
		return id;	
	}
	
	public double[][][] get_rd_percentage_from_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][][]) all_condition_rd_percentage[condition_id];
		}
		return null;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public double[][][] get_rd_percentage(Information_Variable var_info, int table_id_to_find, int row_id_to_find) {
		if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
			if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
				if (all_priority_condition_info != null) {		// If there is at least one condition	
					int priority = 0;
					while (priority < all_priority_condition_info.length) {		// loop all condition until found the one matched
						if (are_all_static_identifiers_matched(var_info, all_priority_condition_static_identifiers[priority])
									&& are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
							return (double[][][]) all_condition_rd_percentage[priority];
						}
						priority++;
					}
				}
			}
		}
		return null;	
	}

	
	
	
	private double[][][] get_3D_array_from_regeneration_info(String regeneration_info) {	//[disturbance_type][covertype_before][covertype_after]
		//Read the whole cell into array
		String[] array = regeneration_info.split(";");		// example 2 disturbance types:       B B 0.0 0.0;B C 0.0 0.0
		int total_replacing_disturbances = array[0].split("\\s+").length - 2;
		List<String> covertype_list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			String covertype = array[i].split("\\s+")[0];
			if (!covertype_list.contains(covertype)) {
				covertype_list.add(covertype);
			}
		}
		int total_covertype = covertype_list.size();
		
		double[][][] rd_percentage = new double[total_replacing_disturbances][total_covertype][total_covertype];
		for (int i = 0; i < total_covertype; i++) {
			for (int j = 0; j < total_covertype; j++) {
				int current_array_position = total_covertype * i + j;
				String[] info = array[current_array_position].split("\\s+");    // example:       B B 0.0 0.0
				for (int k = 2; k < info.length; k++) {
					rd_percentage[k-2][i][j] = Double.parseDouble(info[k]);
				}
			}
		}
		return rd_percentage;
	}
	
	
	private Boolean are_all_dynamic_identifiers_matched(Object[][][] yield_tables_values, int table_id_to_find, int row_id_to_find,
			List<String> dynamic_identifiers_column_indexes, List<List<String>> dynamic_identifiers) {
		
		if (!dynamic_identifiers_column_indexes.get(0).equals("NoIdentifier")) {	//If there are dynamic identifiers, Check if in the same row of this yield table we have all the dynamic identifiers match	
			int identifiers_count = 0;
			for (List<String> this_dynamic_identifier : dynamic_identifiers) {	// loop all dynamic identifiers
				int current_dynamic_column = Integer.parseInt(dynamic_identifiers_column_indexes.get(identifiers_count));		//This is the yield table column of the dynamic identifier
				if (this_dynamic_identifier.get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
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
					int index = Collections.binarySearch(this_dynamic_identifier, yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
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
		
	
	private List<List<String>> get_condition_static_identifiers(String static_identifiers_info) {
		List<List<String>> condition_static_identifiers = new ArrayList<List<String>>();		
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
			condition_static_identifiers.add(thisIdentifier);
		}
			
		return condition_static_identifiers;
	}
	
		
	private List<List<String>> get_condition_dynamic_identifiers(String dynamic_identifiers_info) {
		List<List<String>> condition_dynamic_identifiers = new ArrayList<List<String>>();		
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
			condition_dynamic_identifiers.add(thisIdentifier);
		}			
		return condition_dynamic_identifiers;
	}
	
	
	private List<String> get_condition_dynamic_dentifiers_column_indexes(String dynamic_identifiers_info) {
		List<String> condition_dynamic_dentifiers_column_indexes = new ArrayList<String>();
			
		//Read the whole cell into array
		String[] info = dynamic_identifiers_info.split(";");
		int total_dynamic_identifiers = info.length;

		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamic_identifiers; i++) {	
			String[] identifierElements = info[i].split("\\s+");				//space delimited
			//add the first element which is the identifier column index
			condition_dynamic_dentifiers_column_indexes.add(identifierElements[0].replaceAll("\\s+",""));
		}			
		return condition_dynamic_dentifiers_column_indexes;
	}
}
