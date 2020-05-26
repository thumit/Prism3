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

import java.util.Collections;
import java.util.List;

public class Information_Variable {
	private String var_name, layer1, layer2, layer3, layer4, layer5, layer6, method, layer5_regen, forest_status;	// layer5_regen = regenerated cover type = s5R (after clear-cut or SR occurrence), while layer5 = s5 (before clear-cut or SR occurrence)
	private int iter, period, age, timing_choice, rotation_period, rotation_age;
	private String yield_table_name_to_find;
	private int yield_table_row_index_to_find;
	private int[] prescription_id_and_row_id;
	
	public Information_Variable(int iter, String var_name, int starting_age, List<String> yield_tables_names_list) {
		// Set up
		this.var_name = var_name;
		layer1 = "";
		layer2 = "";
		layer3 = "";
		layer4 = "";
		layer5 = "";
		layer6 = "";
		method = "";
		layer5_regen = "";
		forest_status = "";
		rotation_period = -9999;
		rotation_age = -9999;
		period = -9999;
		age = -9999;
		timing_choice = -9999;
		yield_table_name_to_find = "";
		yield_table_row_index_to_find = -9999;
		this.iter = iter;
		
		
		try {
			String first_six_letters_of_var_name = var_name.substring(0, 6);
			String[] term;
			
			switch (first_six_letters_of_var_name) {
			case "xNC_E_":
				term = var_name.substring(6).split("_");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);
				age = starting_age + period - 1;		// calculate age for existing variable
				
				method = "NC";
				forest_status = "E";
				yield_table_name_to_find = yield_tables_names_list.get(timing_choice);
				yield_table_row_index_to_find = period - 1;
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
			
			case "xEA_E_":
				term = var_name.substring(6).split("_");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				layer5_regen = term[6];
				timing_choice = Integer.parseInt(term[7]);
				period = Integer.parseInt(term[8]);	
				age = starting_age + period - 1;		// calculate age for existing variable
				// rotation_age and rotation_period are set manually
				
				method = "EA";
				forest_status = "E";
				yield_table_name_to_find = yield_tables_names_list.get(timing_choice);
				yield_table_row_index_to_find = period - 1;
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
				
			case "xNC_R_":
				term = var_name.substring(6).split("_");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				timing_choice = Integer.parseInt(term[5]);
				period = Integer.parseInt(term[6]);
				age = Integer.parseInt(term[7]);
				
				method = "NC";
				forest_status = "R";
				yield_table_name_to_find = yield_tables_names_list.get(timing_choice);
				yield_table_row_index_to_find = age - 1;
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
				
			case "xEA_R_":
				term = var_name.substring(6).split("_");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer5_regen = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);
				age = Integer.parseInt(term[8]);
				// rotation_age and rotation_period are set manually
				
				method = "EA";
				forest_status = "R";
				yield_table_name_to_find = yield_tables_names_list.get(timing_choice);
				yield_table_row_index_to_find = age - 1;
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
				
			case "f_":
				term = var_name.substring(2).split("_");	// remove first 2 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				period = Integer.parseInt(term[5]);
				layer5_regen = term[6];
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// No worry if catching error. Because variable without method jump into this --> no need warning here
		}


		
		
		// if prescription exists in the yield_tables database --> the 2 numbers will not be -9999
		prescription_id_and_row_id = new int [2];	// first index is prescription, second index is row_id   	
    	int prescription_id_to_find = -9999, row_id_to_find = -9999;
		if (!method.equals("")) {
    		int id_to_search = Collections.binarySearch(yield_tables_names_list, yield_table_name_to_find);				
    		if (id_to_search >= 0) {		// If prescription (a.k.k yield table name) exists						
    			prescription_id_to_find = id_to_search;
    			row_id_to_find = yield_table_row_index_to_find;
    		}
    	}
    	prescription_id_and_row_id[0] = prescription_id_to_find;
    	prescription_id_and_row_id[1] = row_id_to_find;
		yield_tables_names_list = null;		// clear object to save memory
	}
	
	
	public String get_var_name() {
		return var_name;
	}
	
	
	public String get_layer1() {
		return layer1;
	}

	public String get_layer2() {
		return layer2;
	}	
	
	public String get_layer3() {
		return layer3;
	}
	
	public String get_layer4() {
		return layer4;
	}
	
	public String get_layer5() {
		return layer5;
	}
	
	public String get_layer6() {
		return layer6;
	}
	
	public String get_method() {
		return method;
	}
	
	public String get_regenerated_covertype() {
		return layer5_regen;
	}	
	
	public String get_forest_status() {
		return forest_status;
	}
	
	public void set_rotation_period(int tR) {
		rotation_period = tR;
		rotation_period = rotation_period - iter; // adjusted
	}
	
	public int get_rotation_period() {
		return rotation_period;
	}
	
	public void set_rotation_age(int aR) {
		rotation_age = aR;
	}
	
	public int get_rotation_age() {
		return rotation_age;
	}
	
	public int get_period() {
		return period;
	}
	
	public int get_age() {
		return age;
	}
	
	public int get_timing_choice() {
		return timing_choice;
	}
	
	public String get_yield_table_name_to_find() {
		return yield_table_name_to_find;
	}
	
	public int get_yield_table_row_index_to_find() {
		return yield_table_row_index_to_find;
	}
	
	public int[] get_prescription_id_and_row_id() {	// Return only when prescription exists in the database yield tables. Otherwise, the 2 numbers will be -9999
		return prescription_id_and_row_id;
	}
}
