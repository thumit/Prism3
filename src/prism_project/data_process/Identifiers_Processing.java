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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


//This class is created only when there is at least 1 condition --> no need to check null condition
public class Identifiers_Processing {
	private Read_Database read_database;
	private String[][][] yield_tables_values;
	
	public Identifiers_Processing(Read_Database read_database) {
		this.read_database = read_database;	
		this.yield_tables_values = read_database.get_yield_tables_values();
	}
		
	
	public Boolean are_all_static_identifiers_matched(Information_Variable var_info, List<List<String>> static_identifiers) {	
//		// The below check also implements Speed Boost RRB9
//		if (static_identifiers.get(0).size() < all_layers.get(0).size() && Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0) return false;
//		if (static_identifiers.get(1).size() < all_layers.get(1).size() && Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0) return false;
//		if (static_identifiers.get(2).size() < all_layers.get(2).size() && Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0) return false;
//		if (static_identifiers.get(3).size() < all_layers.get(3).size() && Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0) return false;
//		if (static_identifiers.get(4).size() < all_layers.get(4).size() && Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0) return false;
//		if (static_identifiers.get(5).size() < all_layers.get(5).size() && Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(6), String.valueOf(var_info.get_period())) < 0) return false;
//		return true;
		
		
		// The below check also implements Speed Boost RRB9. Same as above but it is the faster this way 
		List<List<String>> all_layers = read_database.get_all_layers();
		if (
		(static_identifiers.get(0).size() < all_layers.get(0).size() && Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0) ||
		(static_identifiers.get(1).size() < all_layers.get(1).size() && Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0) ||
		(static_identifiers.get(2).size() < all_layers.get(2).size() && Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0) ||
		(static_identifiers.get(3).size() < all_layers.get(3).size() && Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0) ||
		(static_identifiers.get(4).size() < all_layers.get(4).size() && Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0) ||
		(static_identifiers.get(5).size() < all_layers.get(5).size() && Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) ||
		Collections.binarySearch(static_identifiers.get(6), String.valueOf(var_info.get_period())) < 0) 
		{
			return false;
		}
		return true;
		
		
		
//		// Without RRB9 --> no need all_layers
//		if (
//		Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0 ||
//		Collections.binarySearch(static_identifiers.get(6), String.valueOf(var_info.get_period())) < 0) 
//		{
//			return false;
//		}
//		return true;
//		
//		
//		
//		// Without RRB9 --> no need all_layers		
//		if (Collections.binarySearch(static_identifiers.get(0), var_info.get_layer1()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(1), var_info.get_layer2()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(2), var_info.get_layer3()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(3), var_info.get_layer4()) < 0) return false;
//		if (Collections.binarySearch(static_identifiers.get(4), var_info.get_layer5()) < 0) return false;	// layer5 cover type
//		if (Collections.binarySearch(static_identifiers.get(5), var_info.get_layer6()) < 0) return false;	// layer6: size class
//		if (Collections.binarySearch(static_identifiers.get(6), String.valueOf(var_info.get_period())) < 0) return false;
//		return true;
	}	
	
	
	public Boolean are_all_dynamic_identifiers_matched(int prescription_id, int row_id, List<Integer> dynamic_identifiers_column_indexes, List<List<String>> dynamic_identifiers) {
		if (dynamic_identifiers_column_indexes != null) {	//If there are dynamic identifiers, Check if in the same row of this yield table we have all the dynamic identifiers match	
			int identifiers_count = 0;
			for (List<String> this_dynamic_identifier : dynamic_identifiers) {	// loop all dynamic identifiers
				int col_id = dynamic_identifiers_column_indexes.get(identifiers_count);		//This is the yield table column of the dynamic identifier
				if (this_dynamic_identifier.get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
					double yt_value = Double.parseDouble(yield_tables_values[prescription_id][row_id][col_id]);
					for (String range : this_dynamic_identifier) {	//Loop all ranges of this range identifier
						StringTokenizer tok = new StringTokenizer(range, ",");	// split by ,
						// will for sure have 2 items in the range --> do not need while check here
						double min_value = Double.parseDouble(tok.nextToken().replace("[", ""));
						double max_value = Double.parseDouble(tok.nextToken().replace(")", ""));	
						if (!(min_value <= yt_value && yt_value < max_value)) {
							return false;
						}
					}										
				} else { // if this is a discrete identifier
					int index = Collections.binarySearch(this_dynamic_identifier, yield_tables_values[prescription_id][row_id][col_id]);
					if (index < 0) 	{	// If all selected items in this list do not contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
						return false;			
					}
				}
				identifiers_count++;
			}
		}	
		return true;
	}
		
	
//	if (is_range_identifier(this_dynamic_identifier.get(0))) {	//if this is a range identifier (the 1st element of this identifier contains ",")
//	private boolean is_range_identifier(String s) throws SAXException {
//		char[] a = s.toCharArray();
//		boolean valid = false;
//		for (char c : a) {
//			valid = (c <= ',');
//			if (valid) {
//				return valid;
//			}
//		}
//		return valid;
//	}
	
	
	public List<List<String>> get_static_identifiers(String static_identifiers_info) {
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<List<String>> static_identifiers = new ArrayList<List<String>>();	
		StringTokenizer t1 = new StringTokenizer(static_identifiers_info, ";");
		while (t1.hasMoreTokens()) {		// loop through each element (separated by ;) --> loop each static identifier which has: 6 first identifiers are strata's 6 layers (layer 0 to 5) then period (6)
			String infor = t1.nextToken();
			
			List<String> this_identifier = new ArrayList<String>();
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			if (t2.hasMoreTokens()) t2.nextToken();					// Ignore the first element which is the identifier index
			while (t2.hasMoreTokens()) this_identifier.add(t2.nextToken().replaceAll("\\s+",""));	// Add element name, if name has spaces then remove all the spaces
			Collections.sort(this_identifier);	// sort for Binary search used in:     are_all_static_identifiers_matched()
			static_identifiers.add(this_identifier);
		}
		return static_identifiers;
	}
		
	
	public List<List<String>> get_dynamic_identifiers(String dynamic_identifiers_info) {
		// tokenizer is used instead of String.split because it is faster
		// Read the whole cell which include a string with many ; 
		List<List<String>> dynamic_identifiers = new ArrayList<List<String>>();
		StringTokenizer t1 = new StringTokenizer(dynamic_identifiers_info, ";");
		while (t1.hasMoreTokens()) {		// loop through each element (separated by ;) --> loop each dynamic identifier
			String infor = t1.nextToken();
			
			List<String> this_identifier = new ArrayList<String>();
			StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
			if (t2.hasMoreTokens()) t2.nextToken();					// Ignore the first element which is the identifier index
			while (t2.hasMoreTokens()) this_identifier.add(t2.nextToken().replaceAll("\\s+",""));	// Add element name, if name has spaces then remove all the spaces
			Collections.sort(this_identifier);	// sort for Binary search used in:     are_all_dynamic_identifiers_matched()
			dynamic_identifiers.add(this_identifier);
		}
		return dynamic_identifiers;
	}
	
	
	public List<Integer> get_dynamic_dentifiers_column_indexes(String dynamic_identifiers_info) {
		if (!dynamic_identifiers_info.equals("NoIdentifier")) {
			// tokenizer is used instead of String.split because it is faster
			// Read the whole cell which include a string with many ; 
			List<Integer> dynamic_dentifiers_column_indexes = new ArrayList<Integer>();
			StringTokenizer t1 = new StringTokenizer(dynamic_identifiers_info, ";");
			while (t1.hasMoreTokens()) {		// loop through each element (separated by ;) --> loop each dynamic identifier
				String infor = t1.nextToken();
				
				StringTokenizer t2 = new StringTokenizer(infor, " ");	// info_array = the array storing all the elements split by " "
				if (t2.hasMoreTokens()) dynamic_dentifiers_column_indexes.add(Integer.valueOf(t2.nextToken().replaceAll("\\s+","")));		// add the first element which is the identifier column index
			}
			return dynamic_dentifiers_column_indexes;
		}
		return null;	// when there is "NoIdentifier"
	}
	
	
	public List<String> get_parameters_indexes(String parameters_info) {
		List<String> parameters_indexes_list = new ArrayList<String>();
		//Read the whole cell into array
		String[] parameter_indexes_array = parameters_info.split("\\s+");			
		for (int i = 0; i < parameter_indexes_array.length; i++) {	
			parameters_indexes_list.add(parameter_indexes_array[i].replaceAll("\\s+",""));
		}				
		return parameters_indexes_list;
	}
}
