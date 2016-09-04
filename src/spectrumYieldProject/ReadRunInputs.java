package spectrumYieldProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import spectrumDatabaseUtil.Panel_DatabaseManagement;

public class ReadRunInputs {
	//For readGeneralInputs
	private  String In1_delimited;
	private  int In1_totalRows, In1_totalColumns;
	private  String In1_value[][];
	
	//For readManagementOptions
	private  String In2_delimited;
	private  int In2_totalRows, In2_totalColumns;
	private  String In2_value[][];

	
	public  void readGeneralInputs (File file) {
	//	delimited = ",";		// comma delimited
	//	delimited = "\\s+";		// space delimited
		In1_delimited = "\t";		// tab delimited
				
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public  int get_total_Periods () {
		return Integer.valueOf(In1_value[0][1]);
	}
	
	
	public  void readManagementOptions (File file) {
	//	delimited = ",";		// comma delimited
	//	delimited = "\\s+";		// space delimited
		In2_delimited = "\t";		// tab delimited
				
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String[][] getValues () {
		return In2_value;
	}
	
	public int get_total_ManagementUnits () {
		return In2_totalRows-1;
	}
	
	public int get_TotalColumns () {
		return In2_totalColumns;
	}
}
