package spectrumYieldProject;

import java.util.List;

public class GetParameter_totalValue {

	public double getValue(String s5, String s6, String silviculturalMethod, String timingChoice, 
			Object[] yieldTable_Name, Object[][][] yieldTable_values, List<String> parameters_indexes_list,
			List<String> all_dynamicIdentifiers_columnIndexes, List<List<String>> all_dynamicIdentifiers, int row) {
		
		double value_to_return = 0;
		
		//This is the only case when we don't need to check yield table
		if (all_dynamicIdentifiers_columnIndexes.contains("NoIdentifier") && parameters_indexes_list.contains("NoParameter")) {
			value_to_return = 1;
		} 
		else {		// Check the yield table 		
		
			if (s5.equals("P")) s5 = "VDIP";
			if (s5.equals("D")) s5 = "VDTD";
			if (s5.equals("W")) s5 = "VMIW";
			if (s5.equals("C")) s5 = "VMTC";
			if (s5.equals("I")) s5 = "VSII";
			if (s5.equals("A")) s5 = "VSTA";
			if (s5.equals("L")) s5 = "VLPP";
			if (s5.equals("N")) s5 = "NS";
			
			if (s6.equals("N")) s6 = "50";
			if (s6.equals("S")) s6 = "30";
			if (s6.equals("P")) s6 = "20";
			if (s6.equals("M")) s6 = "13";
			if (s6.equals("L")) s6 = "12";
						
	
			String tableName_toFind = s5 + s6 + silviculturalMethod + timingChoice;
			tableName_toFind = tableName_toFind.toLowerCase();
	//		String[] string_yieldTable_Name = Arrays.stream(yieldTable_Name).toArray(String[]::new);		
			boolean foundtable = false;
			
			for (int i = 0; i < yieldTable_Name.length; i++) {
				if (yieldTable_Name[i].toString().equals(tableName_toFind) && row < yieldTable_values[i].length) {		// If yield table Name match && table has that row index						
					foundtable = true;
					
					boolean add_dynamicIdentifiers_match = true;	//always true if No dynamic Identifier				
					if (!all_dynamicIdentifiers_columnIndexes.contains("NoIdentifier")) {	//If there are dynamic identifiers
						//Check if in the same row of this yield table we have all the dynamic identifiers match				
						for (int dynamic_count = 0; dynamic_count < all_dynamicIdentifiers_columnIndexes.size(); dynamic_count++) {
							int current_dynamic_column = Integer.parseInt(all_dynamicIdentifiers_columnIndexes.get(dynamic_count));		//This is the yield table column of the dynamic identifier								
							add_dynamicIdentifiers_match = false;	//always false when considering the next identifier
							
							
							if (all_dynamicIdentifiers.get(dynamic_count).get(0).contains(",")) {	//if this is a range identifier (the 1st element of this identifier contains ",")							
								double yt_value = Double.parseDouble(yieldTable_values[i][row][current_dynamic_column].toString());
									
										
								for (int element = 0; element < all_dynamicIdentifiers.get(dynamic_count).size(); element++) {	//Loop all elements (all ranges) of this range identifier
									String[] min_and_max = all_dynamicIdentifiers.get(dynamic_count).get(element).split(",");
									
									double minValue = Double.parseDouble(min_and_max[0].replace("[", ""));
									double maxValue = Double.parseDouble(min_and_max[1].replace(")", ""));
																	
									if ((minValue <= yt_value) && (yt_value<maxValue))	add_dynamicIdentifiers_match = true;
								}
															
								
								
								
								
							} else {	//if this is a discrete identifier
								if (all_dynamicIdentifiers.get(dynamic_count).contains(yieldTable_values[i][row][current_dynamic_column].toString())) 	{	//If all selected items in this list contain the value in the same column (This is String comparison, we may need to change to present data manually change by users, ex. ponderosa 221 vs 221.00) 
									add_dynamicIdentifiers_match = true;			
								}
							}				
								
							
							
								
						}
					}
					
					
					
					if (parameters_indexes_list.contains("NoParameter")) {			//Return 1 if NoParameter & all dynamic identifiers match
						if (add_dynamicIdentifiers_match==true) 	value_to_return = 1;
					} else {			//IF there are parameters		
						for (int j = 0; j < parameters_indexes_list.size(); j++) {		//loop all parameters_indexes_list 	
							try {		//this try because some tables have not enough rows to match the total periods
								int col = Integer.parseInt(parameters_indexes_list.get(j));	
								if (add_dynamicIdentifiers_match==true) {		//if all dynamic identifiers match
								value_to_return = value_to_return + Double.parseDouble(yieldTable_values[i][row][col].toString());		// then add to the total of all parameters found
								}
							} catch (Exception e) {
								System.err.println("Cannot get access to table " + yieldTable_Name[i].toString() + " Row Index " + row + " Exception " + e);
							}
						}
					}
				} else { //If yield table Name does not match or table does not have that row	

				}
			}
			
			if (!foundtable) {	//If not found table
//				System.out.println("Not found table " + tableName_toFind);
			}
		}
		return value_to_return;
	}

}
