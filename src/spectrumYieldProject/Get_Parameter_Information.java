package spectrumYieldProject;

import java.util.ArrayList;
import java.util.List;

public class Get_Parameter_Information {

	public static double get_total_value(String var_name, int rotation_age,
			Object[] yieldTable_Name, Object[][][] yieldTable_values, List<String> parameters_indexes_list,
			List<String> all_dynamicIdentifiers_columnIndexes, List<List<String>> all_dynamicIdentifiers,
			List<String> action_type_list, double[] baseCost_acres, double[][] baseCost_yieldtables,
			List<String> cost_staticCondition_list, double[] cost_adjusted_percentage,
			List<String> current_var_static_condition) {
		
		double value_to_return = 0;
						
		
		if (all_dynamicIdentifiers_columnIndexes.contains("NoIdentifier") && parameters_indexes_list.contains("NoParameter")) {	// This is the only case when we don't need to check yield table
			value_to_return = 1;
		} else {	// Check the yield table 				
			List<String> yieldTable_Name_list = new ArrayList<String>() {{ for (Object i : yieldTable_Name) add(i.toString());}};		// Convert Object array to String list
			String yield_table_name_to_find = Get_Variable_Information.get_yield_table_name_to_find(var_name);	
			if (yield_table_name_to_find.contains("rotation_age")) {
				yield_table_name_to_find = yield_table_name_to_find.replace("rotation_age", String.valueOf(rotation_age));
			}
			int row_id_to_find = Get_Variable_Information.get_yield_table_row_index_to_find(var_name);
			
			
			if (yieldTable_Name_list.contains(yield_table_name_to_find)) {		// If yield table name exists						
				int table_id_to_find = yieldTable_Name_list.indexOf(yield_table_name_to_find);
				
				
				if (row_id_to_find < yieldTable_values[table_id_to_find].length) {
					
					boolean all_dynamicIdentifiers_matched = areAllDynamicIdentifiersMatched(yieldTable_values, table_id_to_find, row_id_to_find, all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers);								
					if (all_dynamicIdentifiers_matched) {
					
						if (parameters_indexes_list.contains("NoParameter")) {			//Return 1 if NoParameter & all dynamic identifiers match
							value_to_return = 1;
							
						} else if (parameters_indexes_list.contains("CostParameter")) {			//If this is a cost constraint	
							int action_type_YTindex = yieldTable_values[table_id_to_find][row_id_to_find].length - 1;		// 'action_type' is the last column of each yield table
							String current_YTrow_action = yieldTable_values[table_id_to_find][row_id_to_find][action_type_YTindex].toString();
							
							
							if (action_type_list.contains(current_YTrow_action)) {
								int baseCost_row = action_type_list.indexOf(current_YTrow_action);
								
								//Add baseCost_acres
								if (baseCost_acres[baseCost_row] > 0) {
									value_to_return = value_to_return + baseCost_acres[baseCost_row];
								}
								
								//Add baseCost_yieldtables
								for (int j = 0; j < baseCost_yieldtables[baseCost_row].length; j++) {		//loop all yield table columns (or baseCost_yieldtables columns)
									int col = j;	
									if (baseCost_yieldtables[baseCost_row][col] > 0) {		
										value_to_return = value_to_return + baseCost_yieldtables[baseCost_row][col] * Double.parseDouble(yieldTable_values[table_id_to_find][row_id_to_find][col].toString());		// then add base_cost * parameter
									}
								}
								
								double basecost = value_to_return;
								//Add cost adjustment to Base cost
								for (String static_condition: current_var_static_condition) {
									static_condition = current_YTrow_action + static_condition;			// action_type + layer + element 
									if (cost_staticCondition_list.contains(static_condition)) {		// When cost static condition is met then adjust the cost
										int value_index = cost_staticCondition_list.indexOf(static_condition);
										value_to_return = value_to_return + basecost * cost_adjusted_percentage[value_index] / 100;
									}
								}
							}
							
							
						} else {			//If this is regular constraint with parameters		
							for (int j = 0; j < parameters_indexes_list.size(); j++) {		//loop all parameters_indexes_list 	
								int col = Integer.parseInt(parameters_indexes_list.get(j));						
								value_to_return = value_to_return + Double.parseDouble(yieldTable_values[table_id_to_find][row_id_to_find][col].toString());		// then add to the total of all parameters found
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

	private static Boolean areAllDynamicIdentifiersMatched(Object[][][] yieldTable_values, int table_id_to_find, int row_id_to_find,
			List<String> all_dynamicIdentifiers_columnIndexes, List<List<String>> all_dynamicIdentifiers) {
		
		if (!all_dynamicIdentifiers_columnIndexes.contains("NoIdentifier")) {	//If there are dynamic identifiers
			//Check if in the same row of this yield table we have all the dynamic identifiers match				
			for (int dynamic_count = 0; dynamic_count < all_dynamicIdentifiers_columnIndexes.size(); dynamic_count++) {
				int current_dynamic_column = Integer.parseInt(all_dynamicIdentifiers_columnIndexes.get(dynamic_count));		//This is the yield table column of the dynamic identifier								
				
				
				if (all_dynamicIdentifiers.get(dynamic_count).get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yieldTable_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
															
					for (int element = 0; element < all_dynamicIdentifiers.get(dynamic_count).size(); element++) {	//Loop all elements (all ranges) of this range identifier
						String[] min_and_max = all_dynamicIdentifiers.get(dynamic_count).get(element).split(",");									
						double min_value = Double.parseDouble(min_and_max[0].replace("[", ""));
						double max_value = Double.parseDouble(min_and_max[1].replace(")", ""));																	
						if (!(min_value <= yt_value && yt_value < max_value)) {
							return false;
						}
					}
						
					
				} else { // if this is a discrete identifier
					if (!all_dynamicIdentifiers.get(dynamic_count).contains(yieldTable_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString())) 	{	//If all selected items in this list do not contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
						return false;			
					}
				}
			}
		}
		
		return true;
	}
}
