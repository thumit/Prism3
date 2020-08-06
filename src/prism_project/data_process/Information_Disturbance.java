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
import java.util.LinkedHashMap;
import java.util.List;
	
// This class is created only when there is at least 1 condition --> no need to check null condition
public class Information_Disturbance {
	private int total_disturbances;
	private LinkedHashMap<String, Integer> map_disturbance_name_to_id = new LinkedHashMap<String, Integer>();
	
	private Identifiers_Processing identifiers_processing;
	private List<List<String>>[] all_priority_condition_static_identifiers;
	private List<List<String>>[] all_priority_condition_dynamic_identifiers;
	private List<String>[] all_priority_condition_dynamic_dentifiers_column_indexes;
	private String[][] all_priority_condition_info;	
	private String[][][] yield_tables_values;
	
	private String[] all_condition_disturbance_name;
	private String[] all_condition_function;
	private double[] all_condition_parameter_a, all_condition_parameter_b;
	private double[] all_condition_mean, all_condition_std;
	private Object[] all_condition_conversion_rate_mean, all_condition_conversion_rate_std;	// contains 2D array for all conditions
	
	public Information_Disturbance(Read_Database read_database, List<String> disturbance_condition_list) {
		identifiers_processing = new Identifiers_Processing(read_database);
		yield_tables_values = read_database.get_yield_tables_values();
		
		int condition_count = disturbance_condition_list.size();
		all_priority_condition_static_identifiers = new ArrayList[condition_count];
		all_priority_condition_dynamic_identifiers = new ArrayList[condition_count];
		all_priority_condition_dynamic_dentifiers_column_indexes = new ArrayList[condition_count];
		all_priority_condition_info = new String[condition_count][];
		all_condition_disturbance_name = new String[condition_count];
		all_condition_function = new String[condition_count];
		all_condition_parameter_a = new double[condition_count];
		all_condition_parameter_b = new double[condition_count];
		all_condition_mean = new double[condition_count];
		all_condition_std = new double[condition_count];
		all_condition_conversion_rate_mean = new Object[condition_count];
		all_condition_conversion_rate_std = new Object[condition_count];
		
		// Just do this once when an object of this class is created, not every time we encounter a variable
		int count = -1;
		for (int priority = 0; priority < condition_count; priority++) {		// Looping from the highest priority condition to the lowest, each priority is a row in the table GUI
			all_priority_condition_info[priority] = disturbance_condition_list.get(priority).split("\t");
			String disturbance_name = all_priority_condition_info[priority][2];
			if (map_disturbance_name_to_id.get(disturbance_name) == null) {
				count++;
			}
			map_disturbance_name_to_id.put(disturbance_name, count);
			all_condition_disturbance_name[priority] = all_priority_condition_info[priority][2];	// column 2 is disturbance name
			all_condition_function[priority] = all_priority_condition_info[priority][4];	 		// column 4 is normalizing function
			all_condition_parameter_a[priority] = (all_priority_condition_info[priority][5] != null) ? Double.parseDouble(all_priority_condition_info[priority][5]) : 0; // column 5 is parameter_a
			all_condition_parameter_b[priority] = (all_priority_condition_info[priority][6] != null) ? Double.parseDouble(all_priority_condition_info[priority][6]) : 0; // column 6 is parameter_b
			all_condition_mean[priority] = (all_priority_condition_info[priority][7] != null) ? Double.parseDouble(all_priority_condition_info[priority][7]) : 0; // column 7 is loss_rate_mean
			all_condition_std[priority] = (all_priority_condition_info[priority][8] != null) ? Double.parseDouble(all_priority_condition_info[priority][8]) : 0; 	// column 8 is loss_rate_std
			all_condition_conversion_rate_mean[priority] = get_2D_array_from_conversion_rate_mean_or_std(all_priority_condition_info[priority][9]);		// column 9 is conversion_rate_mean
			all_condition_conversion_rate_std[priority] = get_2D_array_from_conversion_rate_mean_or_std(all_priority_condition_info[priority][10]);		// column 10 is conversion_rate_std
			all_priority_condition_static_identifiers[priority] = identifiers_processing.get_static_identifiers(all_priority_condition_info[priority][11]);	// column 11 is static identifiers
			all_priority_condition_dynamic_identifiers[priority] = identifiers_processing.get_dynamic_identifiers(all_priority_condition_info[priority][12]);	// column 12 is dynamic identifiers
			all_priority_condition_dynamic_dentifiers_column_indexes[priority] = identifiers_processing.get_dynamic_dentifiers_column_indexes(all_priority_condition_info[priority][13]);	// column 13 is dynamic identifiers
		}
		total_disturbances = map_disturbance_name_to_id.size();
	}
	
	public int get_total_disturbances() {
		return total_disturbances;
	}
	
	public int get_rd_condition_id_for(Information_Variable var_info, int k) {	// each condition_id in this array is associated with only one variable with one disturbance k
		int prescription_id = var_info.get_prescription_id();
		int row_id = var_info.get_row_id();
		int t = var_info.get_period();
		int tR = var_info.get_rotation_period();
		
		int id = -9999;		// return -9999 if there is clear cut activity for x variable. In the case when variable is not x (f, y, z, v, l, b) then t = tR =-9999 --> always return -9999
		if (t != tR) {	// this would automatically filter the x variable
			if (row_id != -9999 && row_id < yield_tables_values[prescription_id].length) { 	// If row in this prescription exists (not exists when row_id = -9999 or >= total rows in that prescription)
				int priority = 0;
				while (id == -9999 && priority < all_priority_condition_info.length) { // loop all condition associated with  the disturbance k until found the one matched 
					if (map_disturbance_name_to_id.get(all_condition_disturbance_name[priority]) == k
							&& identifiers_processing.are_all_static_identifiers_matched(var_info, all_priority_condition_static_identifiers[priority])
								&& identifiers_processing.are_all_dynamic_identifiers_matched(prescription_id, row_id, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
						id = priority;
					}
					priority++;
				}
			}
		}
		return id;	
	}
	
	public String get_normalizing_function_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return all_condition_function[condition_id];
		}
		return "null";
	}
	
	public double get_parameter_a_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return all_condition_parameter_a[condition_id];
		}
		return 0;	
	}
	
	public double get_parameter_b_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return all_condition_parameter_b[condition_id];
		}
		return 0;	
	}
	
	public double get_mean_from_rd_condition_id(int condition_id) {		// this is the mean of the transformed function
		if (condition_id != -9999) {					
			return all_condition_mean[condition_id];
		}
		return 0;	
	}
	
	public double get_std_from_rd_condition_id(int condition_id) {		// this is the std of the transformed function
		if (condition_id != -9999) {					
			return all_condition_std[condition_id];
		}
		return 0;	
	}
	
	public double[][] get_conversion_rate_mean_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][]) all_condition_conversion_rate_mean[condition_id];
		}
		return null;	
	}
	
	public double[][] get_conversion_rate_std_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][]) all_condition_conversion_rate_std[condition_id];
		}
		return null;	
	}
	
	private double[][] get_2D_array_from_conversion_rate_mean_or_std(String conversion_rate_mean_or_std) {	//	[covertype_before][covertype_after]
		//Read the whole cell into array
		String[] array = conversion_rate_mean_or_std.split(";");		// example 2 disturbance types:       B B 0.1;B C 1.2
		int total_covertype = (int) Math.sqrt(array.length);
		double[][] cr_mean_or_std = new double[total_covertype][total_covertype];
		for (int i = 0; i < total_covertype; i++) {
			for (int j = 0; j < total_covertype; j++) {
				int current_array_position = total_covertype * i + j;
				String[] info = array[current_array_position].split("\\s+");    // example:       B B 0.0
				cr_mean_or_std[i][j] = Double.parseDouble(info[2]);
			}
		}
		return cr_mean_or_std;
	}
}
