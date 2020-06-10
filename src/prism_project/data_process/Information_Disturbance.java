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
import java.util.Collections;
import java.util.List;
	
// This class is created only when there is at least 1 condition --> no need to check null condition
public class Information_Disturbance {
	private Identifiers_Processing identifiers_processing;
	private List<List<String>>[] all_priority_condition_static_identifiers;
	private List<List<String>>[] all_priority_condition_dynamic_identifiers;
	private List<String>[] all_priority_condition_dynamic_dentifiers_column_indexes;
	private String[][] all_priority_condition_info;	
	private String[][][] yield_tables_values;
	
	private Object[] all_condition_loss_rate_mean, all_condition_loss_rate_std;	// contains 2D array for all conditions
	private Object[] all_condition_conversion_rate_mean, all_condition_conversion_rate_std;	// contains 3D array for all conditions
	
	public Information_Disturbance(Read_Database read_database, List<String> disturbance_condition_list) {
		identifiers_processing = new Identifiers_Processing(read_database);
		yield_tables_values = read_database.get_yield_tables_values();
		
		
		all_priority_condition_static_identifiers = new ArrayList[disturbance_condition_list.size()];
		all_priority_condition_dynamic_identifiers = new ArrayList[disturbance_condition_list.size()];
		all_priority_condition_dynamic_dentifiers_column_indexes = new ArrayList[disturbance_condition_list.size()];
		all_priority_condition_info = new String[disturbance_condition_list.size()][];
		all_condition_loss_rate_mean = new Object[disturbance_condition_list.size()];
		all_condition_loss_rate_std = new Object[disturbance_condition_list.size()];
		all_condition_conversion_rate_mean = new Object[disturbance_condition_list.size()];
		all_condition_conversion_rate_std = new Object[disturbance_condition_list.size()];
		
		// Just do this once when an object of this class is created, not every time we encounter a variable
		for (int priority = 0; priority < disturbance_condition_list.size(); priority++) {		// Looping from the highest priority condition to the lowest, each priority is a row in the table GUI
			all_priority_condition_info[priority] = disturbance_condition_list.get(priority).split("\t");
			all_condition_loss_rate_mean[priority] = get_2D_array_from_loss_rate_mean_or_std(all_priority_condition_info[priority][2]);		// column 2 is loss_rate_mean
			all_condition_loss_rate_std[priority] = get_2D_array_from_loss_rate_mean_or_std(all_priority_condition_info[priority][3]);		// column 3 is loss_rate_std
			all_condition_conversion_rate_mean[priority] = get_3D_array_from_conversion_rate_mean_or_std(all_priority_condition_info[priority][4]);		// column 4 is conversion_rate_mean
			all_condition_conversion_rate_std[priority] = get_3D_array_from_conversion_rate_mean_or_std(all_priority_condition_info[priority][5]);		// column 5 is conversion_rate_std
			all_priority_condition_static_identifiers[priority] = identifiers_processing.get_static_identifiers(all_priority_condition_info[priority][6]);	// column 6 is static identifiers
			all_priority_condition_dynamic_identifiers[priority] = identifiers_processing.get_dynamic_identifiers(all_priority_condition_info[priority][7]);	// column 7 is dynamic identifiers
			all_priority_condition_dynamic_dentifiers_column_indexes[priority] = identifiers_processing.get_dynamic_dentifiers_column_indexes(all_priority_condition_info[priority][7]);	// column 7 is dynamic identifiers
			
			// sort for Binary search used in:     are_all_static_identifiers_matched()
			for (List<String> this_static_identifier: all_priority_condition_static_identifiers[priority]) {
				Collections.sort(this_static_identifier);
			}	
			
			// sort for Binary search used in:     are_all_dynamic_identifiers_matched()
			for (List<String> this_dynamic_identifier: all_priority_condition_dynamic_identifiers[priority]) {
				Collections.sort(this_dynamic_identifier);
			}			
		}
	}
			
	
	public int get_rd_condition_id_for_this_var(Information_Variable var_info, int table_id_to_find, int row_id_to_find) {
		int t = var_info.get_period();
		int tR = var_info.get_rotation_period();
		if (t == tR) {
			return -9999;	// always return -9999 if there is clear cut activity
		}
		
		int id = -9999;
		if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
			if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
				int priority = 0;
				while (id == -9999 && priority < all_priority_condition_info.length) {		// loop all condition until found the one matched
					if (identifiers_processing.are_all_static_identifiers_matched(var_info, all_priority_condition_static_identifiers[priority])
								&& identifiers_processing.are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
						id = priority;
					}
					priority++;
				}
			}
		}
		return id;	
	}
	
	public double[][] get_loss_rate_mean_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][]) all_condition_loss_rate_mean[condition_id];
		}
		return null;	
	}
	
	public double[][] get_loss_rate_std_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][]) all_condition_loss_rate_std[condition_id];
		}
		return null;	
	}
	
	public double[][][] get_conversion_rate_mean_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][][]) all_condition_conversion_rate_mean[condition_id];
		}
		return null;	
	}
	
	public double[][][] get_conversion_rate_std_from_rd_condition_id(int condition_id) {
		if (condition_id != -9999) {					
			return (double[][][]) all_condition_conversion_rate_std[condition_id];
		}
		return null;	
	}
	
	
//	public double[][][] get_conversion_rate_mean(Information_Variable var_info, int table_id_to_find, int row_id_to_find) {
//		if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
//			if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
//				if (all_priority_condition_info != null) {		// If there is at least one condition	
//					int priority = 0;
//					while (priority < all_priority_condition_info.length) {		// loop all condition until found the one matched
//						if (are_all_static_identifiers_matched(var_info, all_priority_condition_static_identifiers[priority])
//									&& are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, all_priority_condition_dynamic_dentifiers_column_indexes[priority], all_priority_condition_dynamic_identifiers[priority])) {
//							return (double[][][]) all_condition_conversion_rate_mean[priority];
//						}
//						priority++;
//					}
//				}
//			}
//		}
//		return null;	
//	}

	
	private double[][] get_2D_array_from_loss_rate_mean_or_std(String loss_rate_mean_or_std) {	//[disturbance_type][covertype_before]
		//Read the whole cell into array
		String[] array = loss_rate_mean_or_std.split(";");		// example 2 disturbance types:       B All 0.0 0.0;C All 0.0 0.0
		int total_replacing_disturbances = array[0].split("\\s+").length - 2;
		int total_covertype = array.length;
		double[][] lr_mean_or_std = new double[total_replacing_disturbances][total_covertype];
		for (int i = 0; i < total_covertype; i++) {
			String[] info = array[i].split("\\s+");    // example:       B All 0.0 0.0
			for (int k = 2; k < info.length; k++) {
				lr_mean_or_std[k - 2][i] = Double.parseDouble(info[k]);
			}
		}
		return lr_mean_or_std;
	}
	
	
	private double[][][] get_3D_array_from_conversion_rate_mean_or_std(String conversion_rate_mean_or_std) {	//[disturbance_type][covertype_before][covertype_after]
		//Read the whole cell into array
		String[] array = conversion_rate_mean_or_std.split(";");		// example 2 disturbance types:       B B 0.0 0.0;B C 0.0 0.0
		int total_replacing_disturbances = array[0].split("\\s+").length - 2;
		int total_covertype = (int) Math.sqrt(array.length);
		
		double[][][] cr_mean_or_std = new double[total_replacing_disturbances][total_covertype][total_covertype];
		for (int i = 0; i < total_covertype; i++) {
			for (int j = 0; j < total_covertype; j++) {
				int current_array_position = total_covertype * i + j;
				String[] info = array[current_array_position].split("\\s+");    // example:       B B 0.0 0.0
				for (int k = 2; k < info.length; k++) {
					cr_mean_or_std[k - 2][i][j] = Double.parseDouble(info[k]);
				}
			}
		}
		return cr_mean_or_std;
	}
	
}
