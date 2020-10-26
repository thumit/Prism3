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

import java.util.List;

public class Information_Parameter {
	private Identifiers_Processing identifiers_processing;
	private String[][][] yield_tables_values;
	
	public Information_Parameter(Read_Database read_database) {
		identifiers_processing = new Identifiers_Processing(read_database);
		yield_tables_values = read_database.get_yield_tables_values();
	}
	
	public double get_total_value(int prescription_id, int row_id,
			List<String> parameters_indexes, List<Integer> dynamic_dentifiers_column_indexes, List<List<String>> dynamic_identifiers, double cost_value) {		// Note: already sort each of the dynamic_identifiers in Panel_Solve
		String first_parameter_index = parameters_indexes.get(0);
		double value_to_return = 0;						
		
		if (dynamic_dentifiers_column_indexes == null /* NoIdentifier */ && first_parameter_index.equals("NoParameter")) {	// This is the only case when we don't need to check yield table
			value_to_return = 1;
		} else {	// Check the prescription (a.k.a. yield table) 				
			if (row_id != -9999 && row_id < yield_tables_values[prescription_id].length) { 	// If row in this prescription exists (not exists when row_id = -9999 or >= total rows in that prescription)
				boolean constraint_dynamic_identifiers_matched = identifiers_processing.are_all_dynamic_identifiers_matched(prescription_id, row_id, dynamic_dentifiers_column_indexes, dynamic_identifiers);								
				if (constraint_dynamic_identifiers_matched) {					
					if (first_parameter_index.equals("NoParameter")) {			// Return 1 if NoParameter & all dynamic identifiers match
						value_to_return = 1;							
					} else if (first_parameter_index.equals("CostParameter")) {			// If this is a cost constraint
						value_to_return = cost_value;			
					} else {	// If this is a constraint with Parameters		
						for (String index : parameters_indexes) {		// Loop all parameters_indexes_list 	
							int col = Integer.parseInt(index);						
							value_to_return = value_to_return + Double.parseDouble(yield_tables_values[prescription_id][row_id][col]);		// then add to the total of all parameters found
						}
					}						
				}			
			} else {	// If this prescription does not have this row_id
//				System.out.println("Not found row id = " + row_id + " in the prescription_id = "+ prescription_id);
			}
		}
		return value_to_return;
	}
}
