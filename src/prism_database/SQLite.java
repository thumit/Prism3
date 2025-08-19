/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/

package prism_database;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import prism_convenience.StringHandle;
import prism_root.Prism3Main;

public class SQLite {
	
private static String[] statement;
private static int lines_count;
	
	public static void create_import_table_statement(File file, String delimited) {
		// Read the whole file to array, create statement query to create table using the first line as column names
	//	delimited = ",";		// comma delimited
	//	delimited = "\\s+";		// space delimited
	//	delimited = "\t";		// tab delimited

		if (delimited != null) {
			try {		
				// All lines to be in array
				List<String> lines_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				lines_count = lines_list.size();
							
				// Read the first line into array. This will be Column names
				String[] columnName = lines_list.get(0).split(delimited);
				int colCount = columnName.length;
				
				// Find duplicated column names and add number to make them different
				int[] number_to_add = new int[colCount];
				Boolean[] is_duplicated_column = new Boolean[colCount];
				Boolean[] has_checked_duplication = new Boolean[colCount];
			
				for (int i = 0; i < colCount; i++) {
					number_to_add[i] = 0;
					is_duplicated_column[i] = false;
					has_checked_duplication[i] = false;
				}
								
				// Calculated the number to add to duplication 
				for (int i = 0; i < colCount - 1; i++) {
					int value = 0;
					for (int j = i + 1; j < colCount; j++) {					
						if (!has_checked_duplication[i] && !has_checked_duplication[j]) {
							if (columnName[i].equalsIgnoreCase(columnName[j])) {			//This is IMPORTANT FOR COMPARISON STRINGS
								value++;
								number_to_add[j] = value;
								is_duplicated_column[i] = true;
								is_duplicated_column[j] = true;
								has_checked_duplication[j] = true;
							}
						}
					}
				}											
				
				// modified to be  -->  'column name'
				for (int i = 0; i < colCount; i++) {
					if (is_duplicated_column[i]) {		// If found duplication --> Add number to the column names
						columnName[i] = columnName[i].toString() + Integer.toString(number_to_add[i]);
					}
//					columnName[i]  = "'" + columnName[i] + "'";		// add ' ' to the normalized column name --> modified to be    'column name'
					columnName[i] = "'" + StringHandle.normalize(columnName[i]) + "'";	// TO NORMALIZE COLUMNS NAMES, and add ' ' to the normalized column name --> modified to be    'column name'
				}																
				
				// This is the type of each Column
				String[] b = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					b[i] = "TEXT";
				}
				
				// Make it to be:	'ColumnName' TEXT
				String[] c = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					c[i] = columnName[i] + " " + b[i];
				}
							
				// make it to be:   'ColumnName' TEXT, 'ColumnName' TEXT, 'ColumnName' TEXT,....... 
				String col_name_and_type = String.join(",", c);
				
				//Get the table name without extension
				String tableName = file.getName();
				if(tableName.contains(".")) tableName= tableName.substring(0, tableName.lastIndexOf('.'));	
				
				//-----------------------------------------------------------------------------------------------------------------------
				
				
				// Finally, add all statements to array
				statement = new String[lines_count];
				
				// Add a statement to create a new table with Column Names only:
				statement[0] = "CREATE TABLE [" + tableName + "] (" + col_name_and_type + ");";		// [] surrounds tableName
				int statement_count = 1;
			
				// And statements to import from the second line to the end of the file
				lines_list.remove(0); 	// remove the  first line which is the column name
				
				String[] currentRow = new String[colCount];
				for (String line : lines_list) {
					String[] line_data = line.split(delimited);		//NOTE NOTE NOTE NOTE: StringTokenizer fail hard core, do not use it here to replace .split (because String.join() will fail). StringTokenizer is not compatile (search it)
					int line_data_count = line_data.length;
					
					for (int k = 0; k < colCount; k++) {
						currentRow[k] = (k < line_data_count) ? line_data[k].replace("'", "''") : "";		// Escape the ' in the variable x_EAe' or x_EAr' by using "replace" function
						currentRow[k]  = "'" + currentRow[k] + "'";			// Add ' ' to make it  --> 'rowValue'
					}
					String records_data = String.join(",", currentRow);			// make it to be: 'rowValue','rowValue','rowValue',...	
					
					statement[statement_count] = "INSERT INTO [" + tableName + "] VALUES (" + records_data + ");";	// [] surrounds tableName
					statement_count++;
				}
				
				lines_list = null; // free memory
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}

	
	public static String[] get_importTable_Stm () {
		return statement;
	}
	
	
	public static void import_file_as_table_into_database(File file_to_import, File database_file) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + database_file.getAbsolutePath());

			conn.setAutoCommit(false);										
			PreparedStatement pst = null;
			
			// Get info from the file
			create_import_table_statement(file_to_import, "\t");		// Read file into arrays
			String[] statement = get_importTable_Stm();		//this arrays hold all the statements	

			// Prepared execution
			String tableName = file_to_import.getName();
			if (tableName.contains(".")) tableName = tableName.substring(0, tableName.lastIndexOf('.'));
			pst = conn.prepareStatement("DROP TABLE IF EXISTS " + "[" + tableName + "]");
			pst.executeUpdate();
			
			for (String st : statement) {
				pst = conn.prepareStatement(st);
				pst.executeUpdate();
			}
		
			// Commit execution
			pst.close();
			conn.commit(); // commit all prepared execution, this is important
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(Prism3Main.get_Prism_DesktopPane(), e, e.getMessage(), Prism3Main.get_Prism_DesktopPane().getWidth(), null);
		}
	}
}


