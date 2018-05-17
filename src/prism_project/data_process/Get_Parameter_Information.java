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

public class Get_Parameter_Information {
	private Object[][][] yield_tables_values;
	
	public Get_Parameter_Information(Read_Database read_database) {
		// Some set up
		this.yield_tables_values = read_database.get_yield_tables_values();
	}
	
	public double get_total_value(String var_name, int table_id_to_find, int row_id_to_find,
			List<String> parameters_indexes, List<String> dynamic_dentifiers_column_indexes, List<List<String>> dynamic_identifiers,
			double cost_value) {
		
		
		double value_to_return = 0;						
		
		if (dynamic_dentifiers_column_indexes.contains("NoIdentifier") && parameters_indexes.contains("NoParameter")) {	// This is the only case when we don't need to check yield table
			value_to_return = 1;
		} else {	// Check the prescription (a.k.a. yield table) 				
			
			if (table_id_to_find != -9999) {	// If prescription exists (not exist when table_id_to_find = -9999)						
				if (row_id_to_find < yield_tables_values[table_id_to_find].length && row_id_to_find != -9999) { 	// If row in this prescription exists (not exists when row_id_to_find = -9999 or >= total rows in that prescription)
					
					boolean constraint_dynamicIdentifiers_matched = are_all_dynamic_identifiers_matched(yield_tables_values, table_id_to_find, row_id_to_find, dynamic_dentifiers_column_indexes, dynamic_identifiers);								
					if (constraint_dynamicIdentifiers_matched) {					
						if (parameters_indexes.contains("NoParameter")) {			// Return 1 if NoParameter & all dynamic identifiers match
							value_to_return = 1;							
						} else if (parameters_indexes.contains("CostParameter")) {			// If this is a cost constrain
							value_to_return = cost_value;			
						} else {	// If this is a constraint with Parameters		
							for (int j = 0; j < parameters_indexes.size(); j++) {		// Loop all parameters_indexes_list 	
								int col = Integer.parseInt(parameters_indexes.get(j));						
								value_to_return = value_to_return + Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][col].toString());		// then add to the total of all parameters found
							}
						}						
					}			
					
				} else {	// If prescription does not have the row to find
//					System.out.println("Not found row id = " + row_index_to_find + " of the yield table "+ yield_table_name_to_find);
				}	
			} else { // If prescription does not exist
//				System.out.println("Not found table " + yield_table_name_to_find);
			}
			
		}
		
		return value_to_return;
	}
		

	private Boolean are_all_dynamic_identifiers_matched(Object[][][] yield_tables_values, int table_id_to_find, int row_id_to_find,
			List<String> dynamic_identifiers_column_indexes, List<List<String>> dynamic_identifiers) {
		
		if (!dynamic_identifiers_column_indexes.contains("NoIdentifier")) {	//If there are dynamic identifiers
			//Check if in the same row of this yield table we have all the dynamic identifiers match				
			for (int dynamic_count = 0; dynamic_count < dynamic_identifiers_column_indexes.size(); dynamic_count++) {
				int current_dynamic_column = Integer.parseInt(dynamic_identifiers_column_indexes.get(dynamic_count));		//This is the yield table column of the dynamic identifier
				List<String> this_dynamic_identifier = dynamic_identifiers.get(dynamic_count);
								
				if (this_dynamic_identifier.get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
															
					for (int element = 0; element < this_dynamic_identifier.size(); element++) {	//Loop all elements (all ranges) of this range identifier
						String[] min_and_max = this_dynamic_identifier.get(element).split(",");									
						double min_value = Double.parseDouble(min_and_max[0].replace("[", ""));
						double max_value = Double.parseDouble(min_and_max[1].replace(")", ""));																	
						if (!(min_value <= yt_value && yt_value < max_value)) {
							return false;
						}
					}										
				} else { // if this is a discrete identifier
					int index = Collections.binarySearch(this_dynamic_identifier, yield_tables_values[table_id_to_find][row_id_to_find][current_dynamic_column].toString());
					if (index < 0) 	{	// If all selected items in this list do not contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
						return false;			
					}
				}
			}
		}	
		return true;
	}

}
