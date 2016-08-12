package spectrumYieldProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import spectrumDatabaseUtil.Panel_DatabaseManagement;

public class ReadUnit {

private static String delimited;
private static int totalRows, totalColumns;
private static String value[][];
	
	public static void readValues (File file) {
		delimited = ",";		// comma delimited
	//	delimited = "\\s+";		// space delimited
	//	delimited = "\t";		// tab delimited
	//	delimited = Panel_DatabaseManagement.getDelimited();
				
		if (delimited != null) {
			try {		
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
							
				//Read the first row into array. This will be Column names
				String[] columnName = a[0].split(delimited);
				totalRows = a.length;
				totalColumns = columnName.length;				
				value = new String[totalRows][totalColumns];
			
				// read all values from all rows and columns
				for (int i = 0; i < totalRows; i++) {		//From 2nd row			
					String[] rowValue = a[i].split(delimited);		
					for (int j = 0; j < totalColumns; j++) {
						value[i][j] = rowValue[j].replaceAll("\\s+","");
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String[][] getValues () {
		return value;
	}
	
	public static int get_TotalRows () {
		return totalRows;
	}
	
	public static int get_TotalColumns () {
		return totalColumns;
	}
}
