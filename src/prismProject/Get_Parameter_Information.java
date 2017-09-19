package prismProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Get_Parameter_Information {

	public static double get_total_value(
			Read_Database read_database, String var_name, int var_rotation_age,
			Object[] yield_tables_names, Object[][][] yield_tables_values, List<String> parameters_indexes_list,
			List<String> dynamic_dentifiers_column_indexes, List<List<String>> dynamic_identifiers,
			List<String> cost_condition_list,
			List<String> coversion_cost_after_disturbance_name_list,		// i.e. P P disturbance		P D disturbance
			List<Double> coversion_cost_after_disturbance_value_list) {		// i.e. 0.25				0.75
		
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
						
						else if (parameters_indexes_list.contains("CostParameter")) {			// If this is a cost constrain
							if (cost_condition_list != null) {					// If there is at least one cost condition										
								int action_type_index = yield_tables_column_names_list.indexOf("action_type");
								String var_action_type = yield_tables_values[table_id_to_find][row_id_to_find][action_type_index].toString();
								
								// The following includes 1 list for the action_cost and 1 list for the conversion_cost
								List<List<List<String>>> final_cost_list = get_final_action_cost_list_and_conversion_cost_list_for_this_variable(
										cost_condition_list, var_name, var_rotation_age, var_action_type,
										yield_tables_values, table_id_to_find, row_id_to_find);
								
								
								// action_cost: include 2 lists for column name (i.e. hca_allsx) and value (i.e. 360)
								for (int item = 0; item < final_cost_list.get(0).get(0).size(); item++) {	// loop list:  final_cost_list.get(0).get(0) which is final_action_cost_column_list
									// Add cost per acre
									if (final_cost_list.get(0).get(0).get(item).equals("acres")) {
										value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(0).get(1).get(item));
									} 
									// Add cost per unit of the yield table column
									else {
										int col_id = yield_tables_column_names_list.indexOf(final_cost_list.get(0).get(0).get(item));
										value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(0).get(1).get(item)) * Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][col_id].toString());
									}
								}								
								
								
								// conversion_cost: include 2 lists for column name (i.e. P D action) and value (i.e. 240)
								for (int item = 0; item < final_cost_list.get(1).get(0).size(); item++) {	// loop list:  final_cost_list.get(1).get(0) which is final_conversion_cost_column_list
									// add conversion cost for post management action (i.e clear cut) or post replacing disturbance (i.e. SRFire)
									// note only one of them is true: for example if it is clear cut --> no replacing disturbance anymore, replacing disturbance can happen in areas where no clear cut implemented
									if (Get_Variable_Information.get_rotation_period(var_name) == Get_Variable_Information.get_period(var_name)) {	// period is the rotation period (this if guarantees variable to be EA_E or EA_R)
										String conversion_cost_to_apply = 
												Get_Variable_Information.get_layer5(var_name) + " " +
												Get_Variable_Information.get_regenerated_covertype(var_name) + " " + "action";
										if (final_cost_list.get(1).get(0).get(item).equals(conversion_cost_to_apply)) {
											value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(1).get(1).get(item));
										} 
									} else {	// when period is not the rotation_period (variable can be anything except BS MS)
										if (coversion_cost_after_disturbance_name_list != null && coversion_cost_after_disturbance_name_list.contains(final_cost_list.get(1).get(0).get(item))) {
											int index = coversion_cost_after_disturbance_name_list.indexOf(final_cost_list.get(1).get(0).get(item));
											value_to_return = value_to_return + Double.parseDouble(final_cost_list.get(1).get(1).get(item)) * coversion_cost_after_disturbance_value_list.get(index);		
										}
									}
									
								}
								
							}						
						} 
						
						else {	// If this is a constraint with Parameters		
							for (int j = 0; j < parameters_indexes_list.size(); j++) {		// Loop all parameters_indexes_list 	
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
	private static List<List<List<String>>> get_final_action_cost_list_and_conversion_cost_list_for_this_variable(
			List<String> cost_condition_list, String var_name, int var_rotation_age, String var_action_type,
			Object[][][] yield_tables_values, int table_id_to_find, int row_id_to_find) {	
		
		List<String> final_action_cost_column_list = new ArrayList<String>();		// example: 	"acres", "...", "hca_allsx", ... -->see table 7a in the GUI of Cost Management
		List<String> final_action_cost_value_list = new ArrayList<String>(); 		// example: 	"360", "...", "1.2", ...
		
		List<String> final_conversion_cost_column_list = new ArrayList<String>();	// example: P D action         	W L disturbance 
		List<String> final_conversion_cost_value_list = new ArrayList<String>();	// example: 240         		120 
		
									
		for (int priority = 0; priority < cost_condition_list.size(); priority++) {		// Looping from the highest priority cost condition to the lowest
			String[] this_condition_info = cost_condition_list.get(priority).split("\t");
			List<List<String>> cost_condition_static_identifiers = get_cost_condition_dynamic_identifiers(this_condition_info[4]);	// column 4 is static identifiers
			List<List<String>> cost_condition_dynamic_identifiers = get_cost_condition_dynamic_identifiers(this_condition_info[5]);	// column 5 is dynamic identifiers
			List<String> cost_condition_dynamic_dentifiers_column_indexes = get_cost_condition_dynamic_dentifiers_column_indexes(this_condition_info[5]);	// column 5 is dynamic identifiers						
			
			// If this condition is satisfied
			if (are_all_static_identifiers_matched(var_name, cost_condition_static_identifiers) && 
					are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, cost_condition_dynamic_dentifiers_column_indexes, cost_condition_dynamic_identifiers)) {
				
				// For action_cost
				if (this_condition_info[2].length() > 0) {		// this guarantees the string is not ""
					List<String[]> action_cost_list = get_cost_condition_action_cost(this_condition_info[2], var_action_type);
					for (String[] c: action_cost_list) {			// c example: clearcut acres 360		c example2: clearcut hca_allsx 0
						if (!final_action_cost_column_list.contains(c[1])) {		// only null is escape, the GUI already guarantees the value >=0			
							final_action_cost_column_list.add(c[1]);	// i.e. acres    hca_allsx
							final_action_cost_value_list.add(c[2]);		// i.e. 360
						}
					}
				}
				
				// For conversion cost
				if (this_condition_info[3].length() > 0) {		// this guarantees the string is not ""
					List<String[]> conversion_cost_list = get_cost_condition_conversion_cost(this_condition_info[3]);
					for (String[] c: conversion_cost_list) {			// c example:  P D action 240         	W L disturbance 120
						if (!final_conversion_cost_column_list.contains(c[0] + " " + c[1] + " " + c[2])) {		// only null is escape, the GUI already guarantees the value >=0				
							final_conversion_cost_column_list.add(c[0] + " " + c[1] + " " + c[2]);		// i.e. P D action		W L disturbance
							final_conversion_cost_value_list.add(c[3]);		// i.e. 240		120
						}	
					}
				}
				
			}
		}		
				
		// Combine the above 2 action_cost lists into a single new list
		List<List<String>> final_action_cost_list = new ArrayList<List<String>>();
		final_action_cost_list.add(final_action_cost_column_list);
		final_action_cost_list.add(final_action_cost_value_list);
		
		// Combine the above 2 conversion_cost lists into a single new list
		List<List<String>> final_conversion_cost_list = new ArrayList<List<String>>();
		final_conversion_cost_list.add(final_conversion_cost_column_list);
		final_conversion_cost_list.add(final_conversion_cost_value_list);
		
		// Combine the above 2 final lists into a single big final list
		List<List<List<String>>> final_cost_list = new ArrayList<List<List<String>>>();
		final_cost_list.add(final_action_cost_list);
		final_cost_list.add(final_conversion_cost_list);
		
		return final_cost_list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static Boolean are_all_static_identifiers_matched(String var_name, List<List<String>> static_identifier) {	
		if (!static_identifier.get(0).contains(Get_Variable_Information.get_layer1(var_name))) return false;
		if (!static_identifier.get(1).contains(Get_Variable_Information.get_layer2(var_name))) return false;
		if (!static_identifier.get(2).contains(Get_Variable_Information.get_layer3(var_name))) return false;
		if (!static_identifier.get(3).contains(Get_Variable_Information.get_layer4(var_name))) return false;
		if (Get_Variable_Information.get_forest_status(var_name).equals("E") && !Get_Variable_Information.get_method(var_name).equals("MS") && !Get_Variable_Information.get_method(var_name).equals("BS")) {
			if (!static_identifier.get(4).contains(Get_Variable_Information.get_layer5(var_name))) return false;	// layer5 cover type
			if (!static_identifier.get(5).contains(Get_Variable_Information.get_layer6(var_name))) return false;	// layer6: size class
		}
		if (!static_identifier.get(6).contains(Get_Variable_Information.get_method(var_name) + "_" + Get_Variable_Information.get_forest_status(var_name))) return false;
		if (!static_identifier.get(7).contains(String.valueOf(Get_Variable_Information.get_period(var_name)))) return false;					
		return true;
	}	
	

	
	private static List<String[]> get_cost_condition_action_cost(String action_cost_info, String var_action_type) {	
		//Read the whole cell into array
		String[] action_cost_array = action_cost_info.split(";");
		List<String[]> action_cost = new ArrayList<String[]>();		
		for (String infor: action_cost_array) {
			String[] info_array = infor.split("\\s+");		// infor example: clearcut hca_allsx 360
			if (info_array[0].equals(var_action_type)) {	// info_array[0] = clearcut			only get the info matched with the var_action_type
				action_cost.add(info_array);
			}
		}		
		return action_cost;
	}
	
	
	
	private static List<String[]> get_cost_condition_conversion_cost(String conversion_cost_info) {	
		//Read the whole cell into array
		String[] conversion_cost_array = conversion_cost_info.split(";");
		List<String[]> conversion_cost = new ArrayList<String[]>();		
		for (String infor: conversion_cost_array) {
			String[] info_array = infor.split("\\s+");		// infor example: P D action 240         	W L disturbance 120
			conversion_cost.add(info_array);
		}		
		return conversion_cost;
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
	
	
	private static List<String> get_cost_condition_dynamic_dentifiers_column_indexes(String dynamic_identifiers_info) {
		List<String> cost_condition_dynamic_dentifiers_column_indexes = new ArrayList<String>();
			
		//Read the whole cell into array
		String[] info = dynamic_identifiers_info.split(";");
		int total_dynamic_identifiers = info.length;

		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamic_identifiers; i++) {	
			String[] identifierElements = info[i].split("\\s+");				//space delimited
			//add the first element which is the identifier column index
			cost_condition_dynamic_dentifiers_column_indexes.add(identifierElements[0].replaceAll("\\s+",""));
		}			
		return cost_condition_dynamic_dentifiers_column_indexes;
	}
	
	
	
	
}
