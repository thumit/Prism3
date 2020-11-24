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
	private int total_disturbances = 0;
	int total_layer5;
	int total_layer6;
	private LinkedHashMap<String, Integer> map_disturbance_name_to_id = new LinkedHashMap<String, Integer>();
	
	private Identifiers_Processing identifiers_processing;
	private int condition_count;
	private List<List<String>>[] all_priority_condition_static_identifiers;
	private List<List<String>>[] all_priority_condition_dynamic_identifiers;
	private List<Integer>[] all_priority_condition_dynamic_dentifiers_column_indexes;
	private String[][] all_priority_condition_info;	
	private String[][][] yield_tables_values;
	
	private String[] all_condition_category;
	private String[] all_condition_disturbance_name;
	private String[] all_condition_modelling_approach;
	private String[] all_condition_function;
	private double[] all_condition_parameter_a, all_condition_parameter_b;
	private double[] all_condition_mean, all_condition_std;
	private Object[] all_condition_conversion_rate_mean, all_condition_conversion_rate_std;	// contains 2D array for all conditions
	
	private LinkedHashMap<Integer, Double> map_global_adjustment_rd_condition_id_to_back_transformed_adjustment_value;								// apply within iteration
	private LinkedHashMap<Integer, Double> map_global_adjustment_rd_condition_id_to_back_transformed_adjustment_value_for_using_across_iteration;  	// apply (across iteration) to period 1 variable solution from previous iteration 
	
	
	public Information_Disturbance(Read_Database read_database, List<String> disturbance_condition_list) {	// Create this class once for each iteration
		List<List<String>> all_layers =  read_database.get_all_layers();
		List<String> layer5 = all_layers.get(4);		total_layer5 = layer5.size();
		List<String> layer6 = all_layers.get(5);		total_layer6 = layer6.size();
		
		identifiers_processing = new Identifiers_Processing(read_database);
		yield_tables_values = read_database.get_yield_tables_values();
		
		condition_count = disturbance_condition_list.size();
		all_priority_condition_static_identifiers = new ArrayList[condition_count];
		all_priority_condition_dynamic_identifiers = new ArrayList[condition_count];
		all_priority_condition_dynamic_dentifiers_column_indexes = new ArrayList[condition_count];
		all_priority_condition_info = new String[condition_count][];
		all_condition_category = new String[condition_count];
		all_condition_disturbance_name = new String[condition_count];
		all_condition_modelling_approach = new String[condition_count]; 
		all_condition_function = new String[condition_count];
		all_condition_parameter_a = new double[condition_count];
		all_condition_parameter_b = new double[condition_count];
		all_condition_mean = new double[condition_count];
		all_condition_std = new double[condition_count];
		all_condition_conversion_rate_mean = new Object[condition_count];
		all_condition_conversion_rate_std = new Object[condition_count];
		
		for (int priority = 0; priority < condition_count; priority++) {		// Looping from the highest priority condition to the lowest, each priority is a row in the table GUI
			all_priority_condition_info[priority] = disturbance_condition_list.get(priority).split("\t");
			all_condition_category[priority] = all_priority_condition_info[priority][2];	// column 2 is condition category
			all_condition_disturbance_name[priority] = all_priority_condition_info[priority][3];	// column 3 is disturbance name			allow reading "null"
			all_condition_modelling_approach[priority] = all_priority_condition_info[priority][4];	// column 4 is modelling approach
			all_condition_function[priority] = all_priority_condition_info[priority][6];	 		// column 6 is normalizing function		allow reading "null"
			all_condition_parameter_a[priority] = (!all_priority_condition_info[priority][7].equals("null")) ? Double.parseDouble(all_priority_condition_info[priority][7]) : 0; // column 7 is parameter_a
			all_condition_parameter_b[priority] = (!all_priority_condition_info[priority][8].equals("null")) ? Double.parseDouble(all_priority_condition_info[priority][8]) : 0; // column 8 is parameter_b
			all_condition_mean[priority] = (!all_priority_condition_info[priority][9].equals("null")) ? Double.parseDouble(all_priority_condition_info[priority][9]) : 0; // column 9 is loss_rate_mean
			all_condition_std[priority] = (!all_priority_condition_info[priority][10].equals("null")) ? Double.parseDouble(all_priority_condition_info[priority][10]) : 0; 	// column 10 is loss_rate_std
			all_condition_conversion_rate_mean[priority] = get_2D_array_from_conversion_rate_mean_or_std(all_priority_condition_info[priority][11]);		// column 11 is conversion_rate_mean
			all_condition_conversion_rate_std[priority] = get_2D_array_from_conversion_rate_mean_or_std(all_priority_condition_info[priority][12]);		// column 12 is conversion_rate_std
			all_priority_condition_static_identifiers[priority] = identifiers_processing.get_static_identifiers(all_priority_condition_info[priority][13]);	// column 13 is static identifiers
			all_priority_condition_dynamic_identifiers[priority] = identifiers_processing.get_dynamic_identifiers(all_priority_condition_info[priority][14]);	// column 14 is dynamic identifiers
			all_priority_condition_dynamic_dentifiers_column_indexes[priority] = identifiers_processing.get_dynamic_dentifiers_column_indexes(all_priority_condition_info[priority][15]);	// column 15 is dynamic identifiers
		}
		
		// mapping disturbance
		int count = -1;
		for (int priority = 0; priority < condition_count; priority++) {		// Looping from the highest priority condition to the lowest (only Local simulation), each priority is a row in the table GUI
			String disturbance_name = all_condition_disturbance_name[priority];
			String condition_category = all_condition_category[priority];
			if (condition_category.equals("Local simulation") && map_disturbance_name_to_id.get(disturbance_name) == null) {
				count++;
				map_disturbance_name_to_id.put(disturbance_name, count);
			}
		}
		total_disturbances = map_disturbance_name_to_id.size();
		
		// mapping global adjustment: map twice to use in 2 different cases
		map_global_adjustment_rd_condition_id_to_back_transformed_adjustment_value = create_a_new_instance_of_mapping_global_adjustment();								// apply within iteration
		map_global_adjustment_rd_condition_id_to_back_transformed_adjustment_value_for_using_across_iteration = create_a_new_instance_of_mapping_global_adjustment();  	// apply (across iteration) to period 1 variable solution from previous iteration 
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
					if (all_condition_category[priority].equals("Local simulation")
							&& map_disturbance_name_to_id.get(all_condition_disturbance_name[priority]) == k
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
	
	public int get_global_adjustment_rd_condition_id_for(Information_Variable var_info, int k) {	// each condition_id in this array is associated with only one variable with one disturbance k
		int prescription_id = var_info.get_prescription_id();
		int row_id = var_info.get_row_id();
		int t = var_info.get_period();
		int tR = var_info.get_rotation_period();
		
		int id = -9999;		// return -9999 if there is clear cut activity for x variable. In the case when variable is not x (f, y, z, v, l, b) then t = tR =-9999 --> always return -9999
		if (t != tR) {	// this would automatically filter the x variable
			if (row_id != -9999 && row_id < yield_tables_values[prescription_id].length) { 	// If row in this prescription exists (not exists when row_id = -9999 or >= total rows in that prescription)
				int priority = 0;
				while (id == -9999 && priority < all_priority_condition_info.length) { // loop all condition associated with  the disturbance k until found the one matched 
					if (all_condition_category[priority].equals("Global adjustment")
							&& map_disturbance_name_to_id.get(all_condition_disturbance_name[priority]) == k
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
	
	public String get_modelling_approach_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return all_condition_modelling_approach[condition_id];
		}
		return "null";
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
	
	private double[][] get_2D_array_from_conversion_rate_mean_or_std(String conversion_rate_mean_or_std) {	//	[layer5_regen][layer6_regen]
		//Read the whole cell into array
		String[] array = conversion_rate_mean_or_std.split(";");		// example:       B F 0.1;B G 1.2
		double[][] cr_mean_or_std = new double[total_layer5][];
		
		int count = 0;
		for (int i = 0; i < total_layer5; i++) {
			cr_mean_or_std[i] = new double[total_layer6];
			for (int j = 0; j < total_layer6; j++) {
				int current_array_position = count;
				String[] info = array[current_array_position].split("\\s+");    // example:       B B 0.0
				cr_mean_or_std[i][j] = Double.parseDouble(info[2]);
				count++;
			}
		}
		return cr_mean_or_std;
	}
	
	// GLOBAL ADJUSTMENT
	// GLOBAL ADJUSTMENT
	// GLOBAL ADJUSTMENT
	public double get_back_transformed_global_adjustment_for_using_within_iteration(int global_adjustment_rd_condition_id) {
		if (global_adjustment_rd_condition_id != -9999) {					
			return map_global_adjustment_rd_condition_id_to_back_transformed_adjustment_value.get(global_adjustment_rd_condition_id);
		}
		return 100;	// global multiplier = 100% = 1 for no adjustment
	}
	
	public double get_back_transformed_global_adjustment_for_using_across_iteration(int global_adjustment_rd_condition_id) {
		if (global_adjustment_rd_condition_id != -9999) {					
			return map_global_adjustment_rd_condition_id_to_back_transformed_adjustment_value_for_using_across_iteration.get(global_adjustment_rd_condition_id);
		}
		return 100;	// global multiplier = 100% = 1 for no adjustment
	}
	
	private LinkedHashMap<Integer, Double> create_a_new_instance_of_mapping_global_adjustment() {
		Statistics stat = new Statistics();
		LinkedHashMap<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for (int priority = 0; priority < condition_count; priority++) {		// Looping from the highest priority condition to the lowest (only Global adjustment), each priority is a row in the table GUI
			String condition_category = all_condition_category[priority];
			if (condition_category.equals("Global adjustment")) {
				String modelling_approach = all_condition_modelling_approach[priority];
				String normalizing_function = all_condition_function[priority];
				double parameter_a = all_condition_parameter_a[priority];
				double parameter_b = all_condition_parameter_b[priority];
				double mean = all_condition_mean[priority];
				double std = all_condition_std[priority];
				
				double transformed_number = 0;
				if (modelling_approach.equals("Deterministic")) {
					transformed_number = mean;	// no need random draw: transformed_loss_rate[] = mean[]
				} else {	// stochastic
					transformed_number = stat.get_gaussian_random_number(mean, std);	// random draw: transformed_loss_rate[] = random[] of the mean[] and std[]
				}
				map.put(priority, stat.get_back_transformed_number(transformed_number, normalizing_function, parameter_a, parameter_b));
			}
		}
		return map;
	}
}
