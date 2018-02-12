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
package prismProject;

public class Get_Variable_Information {

	private static String layer1, layer2, layer3, layer4, layer5, layer6, method, regenerated_covertype;	// regenerated_covertype = s5R = covertype_after, while layer5 = covertype_before
	private static int period, age, timing_choice, rotation_period, rotation_age;
	private static String yield_table_name_to_find, forest_status;
	private static int yield_table_row_index_to_find;
	
	public static void get_all_terms_from_name(String var_name) {
		if (var_name.startsWith("xNG_E_")) {
			var_name = var_name.replace("xNG_E_", "");
			String[] term = var_name.split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);
			
			
			method = "NG";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
			yield_table_row_index_to_find = period - 1;
		} 
		else if (var_name.startsWith("xPB_E_")) {
			var_name = var_name.replace("xPB_E_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);
			
			
			method = "PB";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xGS_E_")) {
			var_name = var_name.replace("xGS_E_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);	
			
			
			method = "GS";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xMS_E_")) {
			var_name = var_name.replace("xMS_E_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);	
			
			
			method = "MS";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6 + "_"+ method + "_" + forest_status + "_" + timing_choice;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xBS_E_")) {
			var_name = var_name.replace("xBS_E_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);
			
			
			method = "BS";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6 + "_" + method + "_" + forest_status + "_" + timing_choice;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xEA_E_")) {				
			var_name = var_name.replace("xEA_E_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			rotation_period = Integer.parseInt(term[6]);
			regenerated_covertype = term[7];
			timing_choice = Integer.parseInt(term[8]);
			period = Integer.parseInt(term[9]);	
			
			
			method = "EA";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6 + "_" + method + "_" + forest_status + "_" + "rotation_age" + "_" + timing_choice;
			yield_table_row_index_to_find = period - 1;
		}	
		else if (var_name.startsWith("xEA_R_")) {
			var_name = var_name.replace("xEA_R_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			rotation_period = Integer.parseInt(term[5]);
			rotation_age = Integer.parseInt(term[6]);
			regenerated_covertype = term[7];
			timing_choice = Integer.parseInt(term[8]);
			period = Integer.parseInt(term[9]);

			
			method = "EA";
			forest_status = "R";
			yield_table_name_to_find = layer5 + "_" + method + "_" + forest_status + "_" + rotation_age + "_" + timing_choice;
			yield_table_row_index_to_find = rotation_age - 1 + period - rotation_period;
		}
		else if (var_name.startsWith("xNG_R_")) {
			var_name = var_name.replace("xNG_R_", "");
			String[] term = var_name.toString().split(",");
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
		}	
		else if (var_name.startsWith("xPB_R_")) {
			var_name = var_name.replace("xPB_R_", "");
			String[] term = var_name.toString().split(",");
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
		}	
		else if (var_name.startsWith("xGS_R_")) {
			var_name = var_name.replace("xGS_R_", "");
			String[] term = var_name.toString().split(",");
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
		}	
	}
	
	
	public static String get_layer1(String var_name) {
		layer1 = "";
		get_all_terms_from_name(var_name);
		return layer1;
	}

	public static String get_layer2(String var_name) {
		layer2 = "";
		get_all_terms_from_name(var_name);
		return layer2;
	}	
	
	public static String get_layer3(String var_name) {
		layer3 = "";
		get_all_terms_from_name(var_name);
		return layer3;
	}
	
	public static String get_layer4(String var_name) {
		layer4 = "";
		get_all_terms_from_name(var_name);
		return layer4;
	}
	
	public static String get_layer5(String var_name) {
		layer5 = "";
		get_all_terms_from_name(var_name);
		return layer5;
	}
	
	public static String get_layer6(String var_name) {
		layer6 = "";
		get_all_terms_from_name(var_name);
		return layer6;
	}
	
	public static String get_method(String var_name) {
		method = "";
		get_all_terms_from_name(var_name);
		return method;
	}
	
	public static String get_regenerated_covertype(String var_name) {
		regenerated_covertype = "";
		get_all_terms_from_name(var_name);
		return regenerated_covertype;
	}	
	
	public static String get_forest_status(String var_name) {
		forest_status = "";
		get_all_terms_from_name(var_name);
		return forest_status;
	}
	
	public static int get_rotation_period(String var_name) {
		rotation_period = -9999;
		get_all_terms_from_name(var_name);
		return rotation_period;
	}
	
	public static int get_rotation_age(String var_name) {
		rotation_age = -9999;
		get_all_terms_from_name(var_name);
		return rotation_age;
	}
	
	public static int get_period(String var_name) {
		period = -9999;
		get_all_terms_from_name(var_name);
		return period;
	}
	
	public static int get_age(String var_name) {
		age = -9999;
		get_all_terms_from_name(var_name);
		return age;
	}
	
	public static int get_timing_choice(String var_name) {
		timing_choice = -9999;
		get_all_terms_from_name(var_name);
		return timing_choice;
	}
	
	public static String get_yield_table_name_to_find(String var_name) {
		yield_table_name_to_find = "";
		get_all_terms_from_name(var_name);
		return yield_table_name_to_find;
	}
	
	public static int get_yield_table_row_index_to_find(String var_name) {
		yield_table_row_index_to_find = -9999;
		get_all_terms_from_name(var_name);
		return yield_table_row_index_to_find;
	}
}
