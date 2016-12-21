package spectrumYieldProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Read_Strata {
	private int totalRows, totalColumns;
	private String[][] value;
	
	public void readValues (File file) {
		String delimited = ","; // 		","		comma delimited			"\\s+"		space delimited		"\t"	tab delimited
				
		if (delimited != null) {
			try {		
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
							
				//Read the first row 
				String[] columnName = a[0].split(delimited);
				totalRows = a.length;
				totalColumns = columnName.length;				
				value = new String[totalRows][totalColumns];
			
				// read all values from all rows and columns
				for (int i = 0; i < totalRows; i++) {		//From 1st row			
					String[] rowValue = a[i].split(delimited);		
					for (int j = 0; j < totalColumns; j++) {
						value[i][j] = rowValue[j].replaceAll("\\s+","");
					}
				}

			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public String[][] getValues () {
		return value;
	}
	
	public int get_TotalRows () {
		return totalRows;
	}
	
	public int get_TotalColumns () {
		return totalColumns;
	}
}
