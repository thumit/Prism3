package spectrumYieldProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadRunInputs {
	//For readGeneralInputs
	private int In1_totalRows, In1_totalColumns;
	private String In1_value[][];
	
	//For readManagementOptions
	private int In2_totalRows, In2_totalColumns;
	private String In2_value[][];

	//For readCoverTypeConversions
	private List<String> coverTypeConversions_list;
	
	public void readGeneralInputs (File file) {
		String In1_delimited = "\t";		// tab delimited
				
		if (In1_delimited != null) {
			try {		
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
							
				//Read the first row
				String[] columnName = a[0].split(In1_delimited);
				In1_totalRows = a.length;
				In1_totalColumns = columnName.length;				
				In1_value = new String[In1_totalRows][In1_totalColumns];
			
				// read all values from all rows and columns
				for (int i = 0; i < In1_totalRows; i++) {		//From 1st row			
					String[] rowValue = a[i].split(In1_delimited);		
					for (int j = 0; j < In1_totalColumns; j++) {
						In1_value[i][j] = rowValue[j].replaceAll("\\s+","");
					}
				}

			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	public int get_total_Periods () {
		return Integer.valueOf(In1_value[0][1]);
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public void readManagementOptions (File file) {
		String In2_delimited = "\t";		// tab delimited
				
		if (In2_delimited != null) {
			try {		
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
							
				//Read the first row into array. This will be Column names
				String[] columnName = a[0].split(In2_delimited);
				In2_totalRows = a.length;
				In2_totalColumns = columnName.length;				
				In2_value = new String[In2_totalRows][In2_totalColumns];
			
				// read all values from all rows and columns
				for (int i = 1; i < In2_totalRows; i++) {		//From 2nd row			
					String[] rowValue = a[i].split(In2_delimited);		
					for (int j = 0; j < In2_totalColumns; j++) {
						In2_value[i][j] = rowValue[j].replaceAll("\\s+","");
					}
				}

			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public String[][] getIn2_Values () {
		return In2_value;
	}
	
	public List<String> get_modeled_strata () {
		List<String> modeled_strata = new ArrayList<String>();
		for (int i = 1; i < In2_totalRows; i++) {		//From 2nd row			
			modeled_strata.add(In2_value[i][0]);	//1st column contains the full name with 6 layers letters
		}
		return modeled_strata;
	}
	
	public List<String> get_modeled_strata_withoutSizeClass () {
		List<String> modeled_strata_withoutSizeClass = new ArrayList<String>();
		for (int i = 1; i < In2_totalRows; i++) {		//From 2nd row			
			modeled_strata_withoutSizeClass.add(In2_value[i][0].substring(0,In2_value[i][0].length()-1));	//remove the last character to get name with 5 layers only
		}
		return modeled_strata_withoutSizeClass;
	}	
	
	public List<String> get_modeled_strata_withoutSizeClassandCoverType () {
		List<String> get_modeled_strata_withoutSizeClassandCoverType = new ArrayList<String>();
		for (int i = 1; i < In2_totalRows; i++) {		//From 2nd row			
			get_modeled_strata_withoutSizeClassandCoverType.add(In2_value[i][0].substring(0,In2_value[i][0].length()-2));	//remove the last 2 characters to get name with 4 layers only
		}
		return get_modeled_strata_withoutSizeClassandCoverType;
	}
	
	public int get_In2_TotalRows () {
		return In2_totalRows;
	}
	
	public int get_In2_TotalColumns () {
		return In2_totalColumns;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void readCoverTypeConversions (File file) {
	//	delimited = ",";		// comma delimited
		String delimited = "\\s+";		// space delimited
	//	delimited = "\t";		// tab delimited
		
		if (delimited != null) {
			try {		
				// All lines to be in coverType_list;	
				coverTypeConversions_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	public List<String> getCoverTypeConversions () {
		return coverTypeConversions_list;
	}		

//-------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	
}
