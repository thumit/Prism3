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

import java.util.Arrays;
import java.util.List;

public class Information_Variable {
	private String var_name, method, layer1, layer2, layer3, layer4, layer5, layer6, layer5_regen, layer6_regen, forest_status;	// layer5_regen = regenerated cover type = s5R (after clear-cut or SR occurrence), while layer5 = s5 (before clear-cut or SR occurrence)
	private int iter, period, age, prescription_id, rotation_period, rotation_age;
	private String prescription;
	private int row_id;
	private int[] prescription_id_and_row_id;
	
	public Information_Variable(int iter, String var_name, Read_Database read_database) {
		String[] yield_tables_names = read_database.get_yield_tables_names();			
		List<String> prescriptions_list = Arrays.asList(yield_tables_names);
		int[] starting_age_class_for_prescription = read_database.get_starting_age_class_for_prescription();
		
		// Set up
		this.iter = iter;
		this.var_name = var_name;
		method = "";
		layer1 = "";
		layer2 = "";
		layer3 = "";
		layer4 = "";
		layer5 = "";
		layer6 = "";
		layer5_regen = "";
		layer6_regen = "";
		forest_status = "";
		rotation_period = -9999;
		rotation_age = -9999;
		period = -9999;
		age = -9999;
		prescription = "";
		prescription_id = -9999;
		row_id = -9999;
		
		
		try {
			String first_four_letters_of_var_name = var_name.substring(0, 4);
			String[] term;
			
			switch (first_four_letters_of_var_name) {
			case "x_E_":
				term = var_name.substring(4).split("_");	// remove first 4 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				layer5_regen = term[6];
				layer6_regen = term[7];
				prescription_id = Integer.parseInt(term[8]);
				period = Integer.parseInt(term[9]);	
				age = starting_age_class_for_prescription[prescription_id] + period - 1;		// calculate age for existing variable
				// rotation_age and rotation_period are set manually
				
				forest_status = "E";
				prescription = prescriptions_list.get(prescription_id);
				row_id = period - 1;
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
				
			case "x_R_":
				term = var_name.substring(4).split("_");	// remove first 4 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				layer5_regen = term[6];
				layer6_regen = term[7];
				prescription_id = Integer.parseInt(term[8]);
				period = Integer.parseInt(term[9]);
				age = Integer.parseInt(term[10]);
				// rotation_age and rotation_period are set manually
				
				forest_status = "R";
				prescription = prescriptions_list.get(prescription_id);
				row_id = age - 1;
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
				
			case "f_":
				term = var_name.substring(2).split("_");	// remove first 2 letters and then split
				layer1 = term[0];
				layer2 = term[1];
				layer3 = term[2];
				layer4 = term[3];
				layer5 = term[4];
				layer6 = term[5];
				layer5_regen = term[6];
				layer6_regen = term[7];
				period = Integer.parseInt(term[8]);
				period = period - iter;		// adjust period. Eg. period 1 + iter should be adjusted to be 1. This is to apply condition in cost, disturbance, other inputs...
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// No worry if catching error. Because variable without method jump into this --> no need warning here
		}

		
		
		// if this is the f variable or variable that is not in the 4 main types (NC_E, NC_R, EA_E, EA_R) then both prescription_id and row_id would be = -9999 (defined at the start)
		// Note prescription always exists, while row_id might not exist in the yield tables
		prescription_id_and_row_id = new int [2];	// first index is prescription, second index is row_id  
		prescription_id_and_row_id[0] = prescription_id;
		prescription_id_and_row_id[1] = row_id;		// Note prescription always exists, while row_id might not exist in the yield tables
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
	
	public String get_regenerated_sizeclass() {
		return layer6_regen;
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
	
	public String get_prescription() {
		return prescription;
	}
	
	public int get_prescription_id() {
		return prescription_id;
	}
	
	public int get_row_id() {
		return row_id;
	}
}
