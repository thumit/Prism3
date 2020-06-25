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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import prism_convenience.FilesHandle;
import prism_convenience.IconHandle;
import prism_convenience.StringHandle;
import prism_root.PrismMain;

public class Read_Database {
	private String[][][] yield_tables_values;
	private String[] yield_tables_names;
	private String[] yield_tables_column_names;
	private String[] yield_tables_column_types;
	private List<String>[] unique_values_list;
	private int[] starting_age_class_for_prescription;
	
	private String[][] existing_strata_values;
	private String[][] strata_definition_values;	
	private List<String> layers_title;
	private List<String> layers_title_tooltip;
	private List<List<String>> all_layers;
	private List<List<String>> all_layers_tooltips;
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private File file_database;
	
	private LinkedList_Layers layers;
	
	public Read_Database(File file_database) {
		this.file_database = file_database;
		
		
		if (file_database != null && file_database.exists()) {	
			Read_strata_definition();
			Read_existing_strata();
			Read_yield_tables();
			calculate_starting_age_class_for_prescription();
		}
		
		
//		Thread t = new Thread() {
//			public void run() {
//				System.out.println("Reading yield_tables");
//				Read_yield_tables();
//				this.interrupt();
//			}
//		};
//
//		
//		Thread t2 = new Thread() {
//			public void run() {
//				System.out.println("Reading existing_strata");
//				Read_existing_strata();
//				this.interrupt();
//			}
//		};
//
//
//		Thread t3 = new Thread() {
//			public void run() {
//				System.out.println("Reading strata_definition");
//				Read_strata_definition();
//				this.interrupt();
//			}
//		};
//		
//		
//		t.start();
//		t2.start();
//		t3.start();
//		
//		
//		try {
//			t.join();
//			t2.join();
//			t3.join();
//		} catch (InterruptedException e) {
//			System.out.println("Reading strata_definition failed");
//		}
//		
//		
//		System.out.println("Finish");
	}

	
	public File get_file_database() {
		return file_database;
	}
	
	
	private void Read_yield_tables() {		
		try {			
			conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
			st = conn.createStatement();

			
			//-----------------------------------------------------------------------------------------------------------
			// get total yield tables
			int total_prescriptions = 0;				
			rs = st.executeQuery("SELECT COUNT(DISTINCT prescription) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique prescription
			while (rs.next()) {
				total_prescriptions = rs.getInt(1);	//column 1
			}									
			yield_tables_names = new String[total_prescriptions];
			yield_tables_values = new String[total_prescriptions][][];
			
			
			//get the table name and put into array "nameOftable"
			rs = st.executeQuery("SELECT DISTINCT prescription, COUNT(prescription) as total_rows FROM yield_tables GROUP BY prescription;");	// prescription is auto sorted because of the "GROUP BY"		
			int prescription_count = 0;
			while (rs.next()) {
				yield_tables_names[prescription_count] = rs.getString(1);		// column 1 = prescription
				yield_tables_values[prescription_count] = new String[Integer.valueOf(rs.getString(2))][];		// column 2 = total_rows of that prescription					
				prescription_count++;
			}			
			
			
			rs = st.executeQuery("SELECT * FROM yield_tables ORDER BY prescription, CAST(row_id as decimal) ASC;");				
			// get total columns and "table_ColumnNames" for each yield table
			rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			yield_tables_column_names = new String[colCount];
			for (int i = 1; i <= colCount; i++) {		// this start from 1
				yield_tables_column_names[i-1] = rsmd.getColumnName(i);			// Note that tableColumnNames start from 0
			}		
			
			// These are arrays, each is a Set of unique values of a column in the database yield_tables
			Set<String>[] yield_tables_column_unique_values = new LinkedHashSet[colCount];
			for (int col = 0; col < colCount; col++) {
				yield_tables_column_unique_values[col] = new LinkedHashSet<>();
			}
			
			// get values for each table & unique values for each column
			for (int i = 0; i < yield_tables_values.length; i++) {
				for (int row = 0; row < yield_tables_values[i].length; row++) {
					rs.next();
					yield_tables_values[i][row] = new String[colCount];
					for (int col = 0; col < colCount; col++) {
						String value = rs.getString(col + 1);
						yield_tables_values[i][row][col] = value;
						yield_tables_column_unique_values[col].add(value);
					}
				}
			}		
			
			
			
			
			//---------------------------------------------------------------------------------------------
			//---------------------------------------------------------------------------------------------
			// Begin: can delete this below code in the future. 
			// This code is used to rename prescription to start with E_0, E_1, R_0, R_1 (E and R is the forest status, 0 and 1 indicate prescription without or with a clear cut at the end)
			// In the future, users are required to name the prescriptions with either one of the above 4 prefixes, and therefore the below code could be removed.
			for (int i = 0; i < total_prescriptions; i++) {
				System.out.print("old prescription = " + yield_tables_values[i][0][0] + "     --->     new prescription = ");
				String new_name = "";
				if (!(yield_tables_values[i][0][0].startsWith("E_0") 
						|| yield_tables_values[i][0][0].startsWith("E_1")
						|| yield_tables_values[i][0][0].startsWith("R_0")
						|| yield_tables_values[i][0][0].startsWith("R_1"))) {
					if (yield_tables_values[i][0][0].contains("_NG_E_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "E", "0", term[0], term[1], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_PB_E_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "E", "0", term[0], term[1], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_GS_E_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "E", "0", term[0], term[1], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_MS_E_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "E", "0", term[0], term[1], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_BS_E_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "E", "0", term[0], term[1], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_EA_E_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "E", "1", term[0], term[1], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_NG_R_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "R", "0", term[0], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_PB_R_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "R", "0", term[0], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_GS_R_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "R", "0", term[0], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_MS_R_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "R", "0", term[0], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_BS_R_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "R", "0", term[0], String.valueOf(i));
					} else if (yield_tables_values[i][0][0].contains("_EA_R_")) {
						String[] term = yield_tables_values[i][0][0].split("_");
						new_name = String.join("_", "R", "1", term[0], String.valueOf(i));
					}
				}
				yield_tables_names[i] = new_name;
				yield_tables_column_unique_values[0] = new LinkedHashSet<>();
				for (String name : yield_tables_names) {
					yield_tables_column_unique_values[0].add(name);
				}
						
				int total_rows = yield_tables_values[i].length;
				for (int row = 0; row < total_rows; row++) {
					yield_tables_values[i][row][0] = yield_tables_names[i];
				}
				System.out.println(yield_tables_values[i][0][0]);
			}	
			// End: delete
			//---------------------------------------------------------------------------------------------
			//---------------------------------------------------------------------------------------------
			
			
			
			
			
			
			
			
			
			
			
			// Convert sets to lists and sort the Lists  --- Important note: we always prefer SORTING DOUBLE
			unique_values_list = new ArrayList[colCount];
			for (int col = 0; col < colCount; col++) {
				unique_values_list[col] = new ArrayList<String>(yield_tables_column_unique_values[col]);
				Collections.sort(unique_values_list[col], new Comparator<String>() {	// Sort the list
					@Override
					public int compare(String o1, String o2) {
						try {
							return Double.valueOf(o1).compareTo(Double.valueOf(o2));	// Sort Double
						} catch (Exception e1) {
							return o1.compareTo(o2);	// if fail --> Sort String
						}
					}
				});
			}
			
			// Identify column type automatically based on Sorting double
			yield_tables_column_types = new String[colCount];
			for (int i = 0; i < unique_values_list.length; i++) {
				yield_tables_column_types[i] = "NUMERIC";
			}
			System.out.println("The below yield-table attributes contain at least one non-numeric cell:");
			for (int i = 0; i < unique_values_list.length; i++) {
				try {
					Double.parseDouble(unique_values_list[i].get(0));
				} catch (NumberFormatException e) {		// if the minimum unique value is not a double, then this attribute is non-numeric
					System.out.println("           - " + yield_tables_column_names[i]);
					yield_tables_column_types[i] = "TEXT";
				}
			}
			
			yield_tables_column_unique_values = null;	// clear to save memory
		} catch (Exception e) {
			e.printStackTrace();
			String warningText = "yield_tables does not meet Prism's data requirements\n";
			warningText = warningText + e.getClass().getName() + ": " + e.getMessage();
			String ExitOption[] = {"OK"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Database error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption, ExitOption[0]);
		} finally {
			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
		    try { rs.close(); } catch (Exception e) { /* ignored */}	
		    try { st.close(); } catch (Exception e) { /* ignored */}
		    try { conn.close(); } catch (Exception e) { /* ignored */}
		}			
	}


	
	private void Read_existing_strata() {		
		try {			
			conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
			st = conn.createStatement();

			
			// get total rows (strata count)
			int rowCount2 = 0;				
			rs = st.executeQuery("SELECT COUNT (DISTINCT(layer1 || layer2 || layer3 || layer4 || layer5 || layer6)) FROM existing_strata;");		// This only have 1 row and 1 column, the value is total number of unique strata
			while (rs.next()) {
				rowCount2 = rs.getInt(1);	//column 1
			}				
			
			// get total columns
			rs = st.executeQuery("SELECT * FROM existing_strata ORDER BY (layer1 || layer2 || layer3 || layer4 || layer5 || layer6) ASC;");	// always sort by strata_id
			rsmd = rs.getMetaData();
			int colCount2 = rsmd.getColumnCount();
			
			// Redefine size
			existing_strata_values = new String[rowCount2][colCount2];
			
			// get values
			for (int row = 0; row < rowCount2; row++) {
				rs.next();
				for (int col = 0; col < colCount2; col++) {
					existing_strata_values[row][col] = rs.getString(col + 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String warningText = "existing_strata does not meet Prism's data requirements\n";
			warningText = warningText + e.getClass().getName() + ": " + e.getMessage();
			String ExitOption[] = {"OK"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Database error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption, ExitOption[0]);
		} finally {
			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
		    try { rs.close(); } catch (Exception e) { /* ignored */}	
		    try { st.close(); } catch (Exception e) { /* ignored */}
		    try { conn.close(); } catch (Exception e) { /* ignored */}
		}			
	}
	
	
	private void Read_strata_definition() {		
		try {			
			conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
			st = conn.createStatement();

			
			// Get total rows
			int rowCount3 = 0;				
			rs = st.executeQuery("SELECT COUNT(layer_id) FROM strata_definition;");		// This only have 1 row and 1 column
			while (rs.next()) {
				rowCount3 = rs.getInt(1);	// column 1
			}				
			
			// get total columns
			rs = st.executeQuery("SELECT * FROM strata_definition ORDER BY layer_id, attribute_id ASC;");		// always sort by layer_id & attribute_id
			rsmd = rs.getMetaData();
			int colCount3 = rsmd.getColumnCount();
			
			// Redefine size
			strata_definition_values = new String[rowCount3][colCount3];
			
			// get values
			for (int row = 0; row < rowCount3; row++) {
				rs.next();
				for (int col = 0; col < colCount3; col++) {
					strata_definition_values[row][col] = rs.getString(col + 1);
				}
			}
			
//			// This is saved for another way to sort strata definition by layer_id & attribute_id
//			Arrays.sort(strata_definition_values, new Comparator<String[]>(){
//				@Override
//				public int compare(String[] first, String[] second) {
//					int comparedTo = first[0].compareTo(second[0]);	// compare the first element (layer)
//					if (comparedTo == 0)
//						return first[2].compareTo(second[2]);	// if the first element (layer) is same (result is 0), compare the third element (attribute)
//					else
//						return comparedTo;
//				}
//			});
				

			layers_title = new ArrayList<String>();
			layers_title_tooltip = new ArrayList<String>();
			
			all_layers = new ArrayList<List<String>>();
			all_layers_tooltips = new ArrayList<List<String>>();				
			
			// Loop through all rows and add all layers information
			for (int i = 0; i < rowCount3; i++) {
				if (!layers_title.contains(strata_definition_values[i][0])) {  // If found a new layer
					// Add Layer title and toolTip    	
		        	layers_title.add(strata_definition_values[i][0]);
		        	layers_title_tooltip.add(strata_definition_values[i][1]);
		        	
		        	// Add 2 temporary Lists to the allLayers & allLayers_ToolTips
		        	all_layers.add(new ArrayList<String>());
		        	all_layers_tooltips.add(new ArrayList<String>());
				}
								
				all_layers.get(all_layers.size() - 1).add(strata_definition_values[i][2]);		// Add layer's element to the last layer
				all_layers_tooltips.get(all_layers_tooltips.size() - 1).add(strata_definition_values[i][3]);		// Add layer's element's ToolTip to the last layer ToolTip
			}	
			
			
			
//			// Testing Linked List
//			layers = new LinkedList_Layers();
//			for (int i = 0; i < rowCount3; i++) {				
//				if (layers.isEmpty() || ! layers.get(layers.size() - 1).layer_id.equalsIgnoreCase(strata_definition_values[i][0])) {  // If found a new layer then add the layer
//					Layer_Item new_layer = new Layer_Item(strata_definition_values[i][0], strata_definition_values[i][1], new LinkedList<Attribute_Item>());
//					layers.add(new_layer);
//				}									
//				Attribute_Item new_attribute = new Attribute_Item(strata_definition_values[i][2], strata_definition_values[i][3]);	// add the attribute to the attributes of the last added layer		
//				layers.get(layers.size() - 1).attributes.add(new_attribute);
//			}	
//														
//			for (Layer_Item i : layers) {					
//				for (Attribute_Item j : i.attributes) {
//					System.out.println(i.layer_id + " " + i.layer_description + " " + j.attribute_id + " " + j.attribute_description);
//				}
//			}	
		} catch (Exception e) {
			e.printStackTrace();
			String warningText = "strata_definition does not meet Prism's data requirements\n";
			warningText = warningText + e.getClass().getName() + ": " + e.getMessage();
			String ExitOption[] = {"OK"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Database error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption, ExitOption[0]);
		} finally {
			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
		    try { rs.close(); } catch (Exception e) { /* ignored */}	
		    try { st.close(); } catch (Exception e) { /* ignored */}
		    try { conn.close(); } catch (Exception e) { /* ignored */}
		}			
	}
	
	
	
	
	// This block is For yield_tales ------------------------------------------------------------------------------------------------------
	// This block is For yield_tales ------------------------------------------------------------------------------------------------------
	// This block is For yield_tales ------------------------------------------------------------------------------------------------------
	public String[][][] get_yield_tables_values() {	
		return yield_tables_values;
	}
	
	public String[] get_yield_tables_column_names() {
		return yield_tables_column_names;
	}
	
	public String[] get_yield_tables_column_types() {
		return yield_tables_column_types;
	}

	public String[] get_yield_tables_names() {
		return yield_tables_names;
	}
	
	public String[] get_action_type() {
		List<String> actionList = null;
		
		List<String> yield_tables_column_names_list = Arrays.asList(yield_tables_column_names);	// Convert array to list		
		int index = yield_tables_column_names_list.indexOf("action_type");
		actionList = get_col_unique_values_list(index);			
		
		Collections.sort(actionList);	// Sort this list
		String[] action_type = actionList.toArray(new String[actionList.size()]);	// Convert list to array	
		return action_type;
	}
	
	public List<String> get_col_unique_values_list(int col) {
//		// Using SQL query: Same performance as as Using Set
//		List<String> unique_values_list = new ArrayList<String>();
//		try {			
//			if (file_database.exists()) {	
//				conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
//				st = conn.createStatement();
//				
//				rs = st.executeQuery("SELECT DISTINCT " + yield_tables_column_names[columnIndex] + " FROM yield_tables;");
//				while (rs.next()) {
//					unique_values_list.add(rs.getString(1));	// column 1 is the only column form this query
//				}									
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Read_Database   -   Database connection error");
//		} finally {
//			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
//		    try { rs.close(); } catch (Exception e) { /* ignored */}	
//		    try { st.close(); } catch (Exception e) { /* ignored */}
//		    try { conn.close(); } catch (Exception e) { /* ignored */}
//		}	
		
		
//		// Using Set: Same performance as as Using SQL query 
//		Set<String> all_values_in_this_column = new LinkedHashSet<>();		// Set guarantees values added are unique
//		for (int tb = 0; tb < yield_tables_values.length; tb++) {
//			for (int rowIndex = 0; rowIndex < yield_tables_values[tb].length; rowIndex++) {
//				all_values_in_this_column.add(yield_tables_values[tb][rowIndex][columnIndex].toString());
//			}
//		}
//		List<String> unique_values_list = new ArrayList<String>(all_values_in_this_column);
		
		
//		List<String> unique_values_list = new ArrayList<String>(yield_tables_column_unique_values[columnIndex]);
//		// Sort the list	
//		try {
//			Collections.sort(unique_values_list, new Comparator<String>() {
//				@Override
//				public int compare(String o1, String o2) {
//					return Double.valueOf(o1).compareTo(Double.valueOf(o2));	// Sort Double
//				}
//			});
//		} catch (Exception e1) {
//			Collections.sort(unique_values_list);	// Sort String
//		}

		return unique_values_list[col];
	}
	
	
	public String get_starting_ageclass(String cover_type, String size_class, String method, String timing_choice) {
		method = "NG";	// only use NG table to find starting age class
		timing_choice ="0";
		String forest_status = "E";
		String tableName_toFind = "E_0_" + cover_type + "_" + size_class + "_" + method + "_" + forest_status + "_" + timing_choice;
		
		String valueReturn = null;
		try {
			int index = Arrays.asList(yield_tables_names).indexOf(tableName_toFind);
			
			List<String> yield_tables_column_names_list = Arrays.asList(yield_tables_column_names);	// Convert array to list
			int age_class_index = yield_tables_column_names_list.indexOf("st_age_10");																// CNPZ case
			if (yield_tables_column_names_list.contains("age_class")) age_class_index = yield_tables_column_names_list.indexOf("age_class");		// CGNF case
			
			valueReturn = yield_tables_values[index][0][age_class_index];			// row 0 is the first period (1st row)
		} catch (Exception e) {
			valueReturn = null;
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Not found age class from yield table: " + tableName_toFind);
		}
		
		return valueReturn;	
	}

	private void calculate_starting_age_class_for_prescription() {
		List<String> yield_tables_column_names_list = Arrays.asList(yield_tables_column_names);	// convert array to list
		int age_class_col_id = yield_tables_column_names_list.indexOf("age_class");
		
		int total_prescriptions = yield_tables_values.length;
		starting_age_class_for_prescription = new int[total_prescriptions];
		for (int i = 0; i < total_prescriptions; i++) {
			starting_age_class_for_prescription[i] = Integer.valueOf(yield_tables_values[i][0][age_class_col_id]);	// row 0 is the first period (1st row)
			
		}	
	}
	
	public int[] get_starting_age_class_for_prescription() {
		return starting_age_class_for_prescription;
	}

	
	// This block is For existing_strata ------------------------------------------------------------------------------------------------------
	// This block is For existing_strata ------------------------------------------------------------------------------------------------------
	// This block is For existing_strata ------------------------------------------------------------------------------------------------------	
	public String[][] get_existing_strata_values() {
		return existing_strata_values;
	}
	
	
	
	
	// This block is For strata_definition ------------------------------------------------------------------------------------------------------
	// This block is For strata_definition ------------------------------------------------------------------------------------------------------
	// This block is For strata_definition ------------------------------------------------------------------------------------------------------	
	public String[][] get_strata_definition_values() {
		return strata_definition_values;
	}	
	
	public List<String> get_layers_title() {   
		return layers_title;
	}
	
	public List<String> get_layers_title_tooltip() {	
		return layers_title_tooltip;
	}
	
	public List<List<String>> get_all_layers() {		
		return all_layers;
	}
	
	public List<List<String>> get_all_layers_tooltips() {
		return all_layers_tooltips;
	}	
	
	
	
	
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------	
	public List<String> get_period_layers_title() {	// layers title = period
		List<String> period_layers_title = Arrays.asList(new String[] { "period" });
		return period_layers_title;
	}
	
	public List<List<String>> get_period_layers() {	// layers elements = 1, 2, ..., 99
		List<String> period = new ArrayList<String>() {
			{
				for (int i = 1; i <= 99; i++) {add(Integer.toString(i));}
			}
		};	
		List<List<String>> period_layers = new ArrayList<List<String>>();
		period_layers.add(period);
		return period_layers;
	}
	
	public String get_parameter_tooltip(String yt_columnName) {
		String tooltip = "";
		
		File file_yield_dictionary = FilesHandle.get_file_yield_dictionary();
		String delimited = ","; // 		","		comma delimited			"\\s+"		space delimited		"\t"	tab delimited	
		try {
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file_yield_dictionary.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]);
			int totalRows = a.length;
			int totalCols = 2;
			String[][] value = new String[totalRows][totalCols];

			// Read all values from all rows and columns
			for (int i = 0; i < totalRows; i++) { // Read from 1st row
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < totalCols && j < rowValue.length; j++) {
					value[i][j] = rowValue[j];		// to make toolTip text separated with space, may need the above line if there is spaces in layer and elements name in the file StrataDefinition.csv
				}				
				// Tool tip identified by comparing the name before and after normalization
				if (yt_columnName.equals(value[i][0]) || StringHandle.normalize(yt_columnName).equals(StringHandle.normalize(value[i][0]))) {
					tooltip = value[i][1];
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return tooltip;
	}	
	
}
