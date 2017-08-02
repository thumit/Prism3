package spectrumYieldProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Get_Parameter_Information {

	public static double get_total_value(
			Read_Database read_database, String var_name, int var_rotation_age,
			Object[] yield_tables_names, Object[][][] yield_tables_values, List<String> parameters_indexes_list,
			List<String> dynamic_dentifiers_column_indexes, List<List<String>> dynamic_identifiers,
			List<String> cost_condition_list, List<String> current_var_static_condition) {		// might not need the last: current_var_static_condition --> check later
		
		String[] yield_tables_column_names = read_database.get_yield_tables_column_names();
		List<String> yield_tables_column_names_list = Arrays.asList(yield_tables_column_names);	// Convert array to list
				
				
				
		double value_to_return = 0;
						
		
		if (dynamic_dentifiers_column_indexes.contains("NoIdentifier") && parameters_indexes_list.contains("NoParameter")) {	// This is the only case when we don't need to check yield table
			value_to_return = 1;
		} else {	// Check the yield table 				
			List<String> yield_tables_name_list = new ArrayList<String>() {{ for (Object i : yield_tables_names) add(i.toString());}};		// Convert Object array to String list
			String yield_table_name_to_find = Get_Variable_Information.get_yield_table_name_to_find(var_name);	
			if (yield_table_name_to_find.contains("rotation_age")) {
				yield_table_name_to_find = yield_table_name_to_find.replace("rotation_age", String.valueOf(var_rotation_age));
			}
			int row_id_to_find = Get_Variable_Information.get_yield_table_row_index_to_find(var_name);
			
			
			if (yield_tables_name_list.contains(yield_table_name_to_find)) {		// If yield table name exists						
				int table_id_to_find = yield_tables_name_list.indexOf(yield_table_name_to_find);
				
				
				if (row_id_to_find < yield_tables_values[table_id_to_find].length) {
					
					boolean constraint_dynamicIdentifiers_matched = are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, dynamic_dentifiers_column_indexes, dynamic_identifiers);								
					if (constraint_dynamicIdentifiers_matched) {					
						if (parameters_indexes_list.contains("NoParameter")) {			//Return 1 if NoParameter & all dynamic identifiers match
							value_to_return = 1;							
						} 
						
						else if (parameters_indexes_list.contains("CostParameter")) {			//If this is a cost constraint	
							if (cost_condition_list == null) {	// no condition --> all cost is 0
								value_to_return = 0;
							} 
							else {														
								int action_type_index = yield_tables_column_names_list.indexOf("action_type");
								String var_action_type = yield_tables_values[table_id_to_find][row_id_to_find][action_type_index].toString();
								
								// The following include 1 list for the name and 1 list for the value
								List<List<String>> final_action_percentage_list = get_final_action_percentage_list_for_this_variable(
										cost_condition_list, var_name, var_rotation_age, var_action_type,
										yield_tables_values, table_id_to_find, row_id_to_find, dynamic_dentifiers_column_indexes);
									
								for (int item = 0; item < final_action_percentage_list.get(0).size(); item++) {	// loop name list
									// Add cost per acre
									if (final_action_percentage_list.get(0).get(item).equals("acres")) {
										value_to_return = value_to_return + Double.parseDouble(final_action_percentage_list.get(1).get(item));
									} 
									// Add cost per unit of the yield table column
									else {
										int col_id = yield_tables_column_names_list.indexOf(final_action_percentage_list.get(0).get(item));
										value_to_return = value_to_return + Double.parseDouble(final_action_percentage_list.get(1).get(item)) * Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][col_id].toString());
									}
								}
							}						
						} 
						
						else {			// If this is regular constraint with parameters		
							for (int j = 0; j < parameters_indexes_list.size(); j++) {		//loop all parameters_indexes_list 	
								int col = Integer.parseInt(parameters_indexes_list.get(j));						
								value_to_return = value_to_return + Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][col].toString());		// then add to the total of all parameters found
							}
						}						
					}		
					
					
					
				} else {	//If this yield table does not have the row to find
//					System.out.println("Not found row id = " + row_index_to_find + " of the yield table "+ yield_table_name_to_find);
				}	
			} else { //If this yield table name does not exist
//				System.out.println("Not found table " + yield_table_name_to_find);
			}
			
		}
		
		return value_to_return;
	}
	

		

	private static Boolean are_all_dynamic_identifiers_matched(Object[][][] yield_table_values, int table_id_to_find, int row_id_to_find,
			List<String> dynamic_identifiers_column_indexes, List<List<String>> dynamic_identifiers) {
		
		if (!dynamic_identifiers_column_indexes.contains("NoIdentifier")) {	//If there are dynamic identifiers
			//Check if in the same row of this yield table we have all the dynamic identifiers match				
			for (int dynamic_count = 0; dynamic_count < dynamic_identifiers_column_indexes.size(); dynamic_count++) {
				int current_dynamic_column = Integer.parseInt(dynamic_identifiers_column_indexes.get(dynamic_count));		//This is the yield table column of the dynamic identifier								
								
				if (dynamic_identifiers.get(dynamic_count).get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yield_table_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
															
					for (int element = 0; element < dynamic_identifiers.get(dynamic_count).size(); element++) {	//Loop all elements (all ranges) of this range identifier
						String[] min_and_max = dynamic_identifiers.get(dynamic_count).get(element).split(",");									
						double min_value = Double.parseDouble(min_and_max[0].replace("[", ""));
						double max_value = Double.parseDouble(min_and_max[1].replace(")", ""));																	
						if (!(min_value <= yt_value && yt_value < max_value)) {
							return false;
						}
					}										
				} else { // if this is a discrete identifier
					if (!dynamic_identifiers.get(dynamic_count).contains(yield_table_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString())) 	{	// If all selected items in this list do not contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
						return false;			
					}
				}
			}
		}
		
		return true;
	}
	
	
	
	
	
	// The following is for cost:	
	private static List<List<String>> get_final_action_percentage_list_for_this_variable(
			List<String> cost_condition_list, String var_name, int var_rotation_age, String var_action_type,
			Object[][][] yield_tables_values, int table_id_to_find, int row_id_to_find, List<String> dynamic_dentifiers_column_indexes) {	
		
		List<String> final_action_percentage_column_list = new ArrayList<String>();		// example: 	"acres", "...", "hca_allsx", ... -->see table 7a in the GUI of Cost Management
		List<String> final_action_percentage_value_list = new ArrayList<String>(); 		// example: 	"360", "...", "1.2", ...
								
		for (int priority = 0; priority < cost_condition_list.size(); priority++) {		// Looping from the highest priority cost condition to the lowest
			String[] this_condition_info = cost_condition_list.get(priority).split("\t");
			List<List<String>> cost_condition_static_identifiers = get_cost_condition_dynamic_identifiers(this_condition_info[4]);	// column 4 is static identifiers
			List<List<String>> cost_condition_dynamic_identifiers = get_cost_condition_dynamic_identifiers(this_condition_info[5]);	// column 5 is dynamic identifiers
									
			// If this condition is satisfied
			if (are_all_static_identifiers_matched(var_name, cost_condition_static_identifiers) && 
					are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, dynamic_dentifiers_column_indexes,cost_condition_dynamic_identifiers)) {
				
				List<String[]> cost_condition_action_percentage = get_cost_condition_action_percentage(this_condition_info[2], var_action_type);
				for (String[] c: cost_condition_action_percentage) {			// c example1: clearcut acres 360		c example2: clearcut hca_allsx 200
					if (!final_action_percentage_column_list.contains(c[1]) && Double.parseDouble(c[2]) > 0) {	// address the null case later if I really want to add the null to table7a						
						final_action_percentage_column_list.add(c[1]);	// i.e acres    hca_allsx
						final_action_percentage_value_list.add(c[2]);
					}
				}
			}
		}		
				
		// Combine the above 2 lists into a single new list
		List<List<String>> final_action_percentage_list = new ArrayList<List<String>>();
		final_action_percentage_list.add(final_action_percentage_column_list);
		final_action_percentage_list.add(final_action_percentage_value_list);		
		return final_action_percentage_list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static Boolean are_all_static_identifiers_matched(String var_name, List<List<String>> static_identifier) {	
		if (!static_identifier.get(0).contains(Get_Variable_Information.get_layer1(var_name))) return false;
		if (!static_identifier.get(1).contains(Get_Variable_Information.get_layer2(var_name))) return false;
		if (!static_identifier.get(2).contains(Get_Variable_Information.get_layer3(var_name))) return false;
		if (!static_identifier.get(3).contains(Get_Variable_Information.get_layer4(var_name))) return false;
		if (!static_identifier.get(4).contains(Get_Variable_Information.get_layer5(var_name))) return false;
		if (Get_Variable_Information.get_layer6(var_name) != null) {		// Only existing variables have layer6 <> null			
			if (!static_identifier.get(5).contains(Get_Variable_Information.get_layer6(var_name))) return false;	// layer 6: size class
		}
		if (!static_identifier.get(6).contains(Get_Variable_Information.get_method(var_name) + "_" + Get_Variable_Information.get_forest_status(var_name))) return false;
		if (!static_identifier.get(7).contains(String.valueOf(Get_Variable_Information.get_period(var_name)))) return false;					
		return true;
	}	
	

	
	private static List<String[]> get_cost_condition_action_percentage(String action_percentage_info, String var_action_type) {	
		//Read the whole cell into array
		String[] action_percentage_array = action_percentage_info.split(";");
		List<String[]> cost_condition_action_percentage = new ArrayList<String[]>();		
		for (String infor: action_percentage_array) {
			String[] info_array = infor.split("\\s+");		// infor example: clearcut hca_allsx 360
			if (info_array[0].equals(var_action_type)) {	// info_array[0] = clearcut			only get the info matched with the var_action_type
				cost_condition_action_percentage.add(info_array);
			}
		}		
		return cost_condition_action_percentage;
	}
	
	
	
	private static List<List<String>> get_cost_condition_static_identifiers (String static_identifiers_info) {
		List<List<String>> cost_condition_static_identifiers = new ArrayList<List<String>>();		
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
			cost_condition_static_identifiers.add(thisIdentifier);
		}
			
		return cost_condition_static_identifiers;
	}
	
	
	
	private static List<List<String>> get_cost_condition_dynamic_identifiers(String dynamic_identifiers_info) {
		List<List<String>> cost_condition_dynamic_identifiers = new ArrayList<List<String>>();		
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
			cost_condition_dynamic_identifiers.add(thisIdentifier);
		}			
		return cost_condition_dynamic_identifiers;
	}
	
	
	
	
	
	
	
}
