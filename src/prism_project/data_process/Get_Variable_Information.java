
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

import java.util.Collections;
import java.util.List;

public class Get_Variable_Information {

	private String layer1, layer2, layer3, layer4, layer5, layer6, method, layer5_regen, forest_status;	// layer5_regen = regenerated cover type = s5R (after clear-cut or SR occurrence), while layer5 = s5 (before clear-cut or SR occurrence)
	private int period, age, timing_choice, rotation_period, rotation_age;
	private String yield_table_name_to_find;
	private int yield_table_row_index_to_find;
	private int[] prescription_id_and_row_id;
	
	public Get_Variable_Information(String var_name, int starting_age, List<String> yield_tables_names_list) {
		// Set up
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
		
		
		try {
			String first_six_letters_of_var_name = var_name.substring(0, 6);
			String[] term;
			
			switch (first_six_letters_of_var_name) {
			case "xNG_E_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);
				age = starting_age + period - 1;		// calculate age for existing variable
				
				method = "NG";
				forest_status = "E";
				yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = period - 1;
				break;
			
			case "xPB_E_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);
				age = starting_age + period - 1;		// calculate age for existing variable
				
				method = "PB";
				forest_status = "E";
				yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = period - 1;
				break;
			
			case "xGS_E_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);	
				age = starting_age + period - 1;		// calculate age for existing variable
				
				method = "GS";
				forest_status = "E";
				yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = period - 1;
				break;
			
			case "xMS_E_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);	
				age = starting_age + period - 1;		// calculate age for existing variable
				
				method = "MS";
				forest_status = "E";
				yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = period - 1;
				break;
				
			case "xBS_E_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				timing_choice = Integer.parseInt(term[6]);
				period = Integer.parseInt(term[7]);
				age = starting_age + period - 1;		// calculate age for existing variable
				
				method = "BS";
				forest_status = "E";
				yield_table_name_to_find = layer5 + "_" + layer6 + "_" + method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = period - 1;
				break;
				
			case "xEA_E_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				rotation_period = Integer.parseInt(term[6]);
				layer5_regen = term[7];
				timing_choice = Integer.parseInt(term[8]);
				period = Integer.parseInt(term[9]);	
				age = starting_age + period - 1;		// calculate age for existing variable
				rotation_age = rotation_period + starting_age - 1;	// calculate rotation age for existing variable
				
				method = "EA";
				forest_status = "E";
				yield_table_name_to_find = layer5 + "_" + layer6 + "_" + method + "_" + forest_status + "_" + rotation_age + "_" + timing_choice;
				yield_table_row_index_to_find = period - 1;
				break;
				
			case "xEA_R_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				rotation_period = Integer.parseInt(term[5]);
				rotation_age = Integer.parseInt(term[6]);
				layer5_regen = term[7];
				timing_choice = Integer.parseInt(term[8]);
				period = Integer.parseInt(term[9]);
				age = rotation_age + period - rotation_period; // a = aR + t - tR
				
				method = "EA";
				forest_status = "R";
				yield_table_name_to_find = layer5 + "_" + method + "_" + forest_status + "_" + rotation_age + "_" + timing_choice;
				yield_table_row_index_to_find = rotation_age - 1 + period - rotation_period;
				break;
				
			case "xNG_R_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				timing_choice = Integer.parseInt(term[5]);
				period = Integer.parseInt(term[6]);
				age = Integer.parseInt(term[7]);
				
				method = "NG";
				forest_status = "R";
				yield_table_name_to_find = layer5 + "_" + method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = age - 1;
				break;
				
			case "xPB_R_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				timing_choice = Integer.parseInt(term[5]);
				period = Integer.parseInt(term[6]);
				age = Integer.parseInt(term[7]);

				method = "PB";
				forest_status = "R";
				yield_table_name_to_find = layer5 + "_" + method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = age - 1;
				break;
				
			case "xGS_R_":
				term = var_name.substring(6).split(",");	// remove first 6 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				timing_choice = Integer.parseInt(term[5]);
				period = Integer.parseInt(term[6]);
				age = Integer.parseInt(term[7]);
				
				method = "GS";
				forest_status = "R";
				yield_table_name_to_find = layer5 + "_" + method + "_" + forest_status + "_" + timing_choice;
				yield_table_row_index_to_find = age - 1;
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
	
	public int get_rotation_period() {
		return rotation_period;
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
