package prismDatabase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import prismConvenienceClasses.StringHandle;

public class SQLite {
	
private static String[] statement;
private static int totalLines;
	
	public static void create_importTable_Stm (File file, String delimited) {
		// Read the whole file to array, create statement query to create table using the first line as column names
	//	delimited = ",";		// comma delimited
	//	delimited = "\\s+";		// space delimited
	//	delimited = "\t";		// tab delimited

		if (delimited != null) {
			try {		
				// All lines to be in array
				List<String> list;
				//list = Files.readAllLines(Paths.get("C:/Testtable.csv"), StandardCharsets.UTF_8);
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
							
				//Read the first line into array. This will be Column names
				String[] columnName = a[0].split(delimited);
				int itemCount = columnName.length;
				
				//Find duplicated column names and add number to make them different
				int[] numberadded = new int[itemCount];
				Boolean[] checkDuplication = new Boolean[itemCount];
				Boolean[] nameChecked = new Boolean[itemCount];
			
				for (int i = 0; i < itemCount; i++) {
					numberadded[i] = 0;
					checkDuplication[i] = false;
					nameChecked[i] = false;
				}
								
				//Calculated the number to add to duplication 
				for (int i = 0; i < itemCount - 1; i++) {
					int value =0;
					for (int j = i + 1; j < itemCount; j++) {						
						if (nameChecked[i] == false && nameChecked[j] == false) {
							if (columnName[i].equalsIgnoreCase(columnName[j])) {			//This is IMPORTANT FOR COMPARISON STRINGS
								value++;
								numberadded[j] = value;
	
								checkDuplication[i] = true;
								checkDuplication[j] = true;
								nameChecked[j] = true;
							}
						}
					}
				}											
				
				//If found duplication --> Add number to the column names
				for (int i = 0; i < itemCount; i++) {
					if (checkDuplication[i] == true) {
						columnName[i] = columnName[i].toString() + Integer.toString(numberadded[i]);
					}
				}																	
				
				
				// this is the type of each Column
				String[] b = new String[itemCount];
				for (int i = 0; i < itemCount; i++) {
					b[i] = "TEXT";
				}
				//System.out.println(b[itemCount - 1]);		//The last member
				
				// make it to be:	'ColumnName' TEXT
				String[] c = new String[itemCount];
				for (int i = 0; i < itemCount; i++) {
//					String str = ("'" + columnName[i] + "'");
					String str = ("'" + StringHandle.normalize(columnName[i]) + "'");			// TO NORMALIZE COLUMNS NAMES
					c[i] = str + " " + b[i];
				}
							
				// make it to be:   'ColumnName' TEXT, 'ColumnName' TEXT, 'ColumnName' TEXT,....... 
				String string1 = new String();
				for (int i = 0; i < itemCount - 1; i++) {
					string1 = string1+ c[i] + ",";
				}
				string1 = string1 + c[itemCount-1];	//For the last c[i] --> do not add ,
				
				//Get the table name without extension
				String tableName = file.getName();
				if(tableName.contains(".")) tableName= tableName.substring(0, tableName.lastIndexOf('.'));	
				
				
				// add statement to array
				
				
				statement = new String[a.length];
				totalLines = a.length;
				
				// Finally, The statement to add a new table with Column Names only:
//				statement = "CREATE TABLE " + "\"" + tableName + "\"" + " (" + string1 + ");";		//Double quote surrounds tableName
				statement[0] = "CREATE TABLE " + "[" + tableName + "]" + " (" + string1 + ");";		// [] surrounds tableName	
			
				// And here are statement to import from the second record (or line) to the end of the file
				for (int i = 1; i < a.length; i++) {
					String[] currentRow = a[i].split(delimited);
					int rowCount = currentRow.length;
					String string2 = "";
					// make it to be: 'rowValue',
					for (int j = 0; j < rowCount - 1; j++) {
						currentRow[j] = currentRow[j].replace("'", "''");		//Escape the ' in the variable x_EAe' or x_EAr'
						string2 = string2 + "'" + currentRow[j] + "'" + ",";
				}
					currentRow[rowCount-1] = currentRow[rowCount-1].replace("'", "''");				//Escape the ' in the variable x_EAe' or x_EAr'
					string2 = string2 + "'" + currentRow[rowCount-1] + "'";  //For the last item of the row we don't add ,		
					
					
					
//					statement = statement + "INSERT INTO " + "\"" + tableName + "\"" + " VALUES (" + string2 + ");";	//Double quote surrounds tableName
					statement[i] = "INSERT INTO " + "[" + tableName + "]" + " VALUES (" + string2 + ");";	// [] surrounds tableName
			}

			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	public static String[] get_importTable_Stm () {
		return statement;
	}
	
	public static int get_importTable_TotalLines () {
		return totalLines;
	}
}


