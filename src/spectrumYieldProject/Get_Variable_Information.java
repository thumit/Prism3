package spectrumYieldProject;

public class Get_Variable_Information {

	private static String layer1, layer2, layer3, layer4, layer5, layer6, method;
	private static int period, timing_choice, rotation_age;
	private static String customized_variable_term;
	private static String yield_table_name_to_find, forest_status;
	private static int yield_table_row_index_to_find;
	
	public static void get_all_terms_from_name(String var_name) {
		if (var_name.startsWith("xNG_")) {
			var_name = var_name.replace("xNG_", "");
			String[] term = var_name.split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			period = Integer.parseInt(term[6]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Natural Growth" + period;
			
			
			method = "NG";
			timing_choice = 0;
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6  + "_"+ method + "_" + timing_choice + "_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		} 
		else if (var_name.startsWith("xPB_")) {
			var_name = var_name.replace("xPB_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Prescribed Burn" + period;	
			
			
			method = "PB";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6  + "_"+ method + "_" + timing_choice + "_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xGS_")) {
			var_name = var_name.replace("xGS_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Group Selection" + period;	
			
			
			method = "GS";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6  + "_"+ method + "_" + timing_choice + "_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xMS_")) {
			var_name = var_name.replace("xMS_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			timing_choice = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[7]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Mixed Severity Wildfire" + period;	
			
			
			method = "MS";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6  + "_"+ method + "_" + timing_choice + "_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xEAe'_")) {				// This before because xEAe' contains xEAe
			var_name = var_name.replace("xEAe'_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			period = Integer.parseInt(term[8]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Even Age" + period;	
			
			
			method = "EA";
			forest_status = "E";
			yield_table_name_to_find = layer5 + "_" + layer6  + "_"+ method + "_" + period + "+startage-1_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xEAe_")) {
			var_name = var_name.replace("xEAe_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			layer6 = term[5];
			period = Integer.parseInt(term[6]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Even Age" + period;	
		}	
		else if (var_name.startsWith("xEAr'_")) {
			var_name = var_name.replace("xEAr'_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			rotation_age = Integer.parseInt(term[6]);
			period = Integer.parseInt(term[8]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + "Even Age" + period;	
			
			method = "EA";
			forest_status = "R";
			yield_table_name_to_find = layer5 + "_" + method + "_" + rotation_age + "_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		}
		else if (var_name.startsWith("xEAr_")) {
			var_name = var_name.replace("xEAr_", "");
			String[] term = var_name.toString().split(",");
			layer1 = term[0];
			layer2 = term[1];
			layer3 = term[2];
			layer4 = term[3];
			layer5 = term[4];
			period = Integer.parseInt(term[5]);
			customized_variable_term = layer1 + layer2 + layer3 + layer4 + layer5 + "Even Age" + period;
			
			method = "EA";
			forest_status = "R";
			rotation_age = 0;
			yield_table_name_to_find = layer5 + "_" + method + "_" + rotation_age + "_" + forest_status;
			yield_table_row_index_to_find = period - 1;
		}	
	}
	
	
	public static String get_layer1(String var_name) {
		get_all_terms_from_name(var_name);
		return layer1;
	}

	public static String get_layer2(String var_name) {
		get_all_terms_from_name(var_name);
		return layer2;
	}	
	
	public static String get_layer3(String var_name) {
		get_all_terms_from_name(var_name);
		return layer3;
	}
	
	public static String get_layer4(String var_name) {
		get_all_terms_from_name(var_name);
		return layer4;
	}
	
	public static String get_layer5(String var_name) {
		get_all_terms_from_name(var_name);
		return layer5;
	}
	
	public static String get_layer6(String var_name) {
		get_all_terms_from_name(var_name);
		return layer6;
	}
	
	public static String get_method(String var_name) {
		get_all_terms_from_name(var_name);
		return method;
	}
	
	public static int get_period(String var_name) {
		get_all_terms_from_name(var_name);
		return period;
	}
	
	public static int get_timing_choice(String var_name) {
		get_all_terms_from_name(var_name);
		return timing_choice;
	}
	
	public static String get_customized_variable_term(String var_name) {
		get_all_terms_from_name(var_name);
		return customized_variable_term;
	}
	
	public static String get_yield_table_name_to_find(String var_name) {
		get_all_terms_from_name(var_name);
		return yield_table_name_to_find;
	}
	
	public static int get_yield_table_row_index_to_find(String var_name) {
		get_all_terms_from_name(var_name);
		return yield_table_row_index_to_find;
	}
}
