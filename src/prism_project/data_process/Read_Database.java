/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prism_project.data_process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import prism_convenience_class.FilesHandle;
import prism_convenience_class.StringHandle;

public class Read_Database {
	private Object[][][] yield_tables_values;
	private Object[] yield_tables_names;
	private String[] yield_tables_column_names;
	private List<String>[] unique_values_list;
	
	private String[][] existing_strata_values;
	private String[][] strata_definition_values;	
	private List<String> layers_Title;
	private List<String> layers_Title_ToolTip;
	private List<List<String>> allLayers;
	private List<List<String>> allLayers_ToolTips;
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	
	private File file_database;
	
	private LinkedList_Layers layers;
	
	public Read_Database(File file_database) {
		this.file_database = file_database;
		
		
		Read_strata_definition();
		Read_existing_strata();
		Read_yield_tables();
		
		
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

	
	private void Read_yield_tables() {		
		try {			
			if (file_database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
				st = conn.createStatement();

				
				//-----------------------------------------------------------------------------------------------------------
				// get total yield tables
				int total_prescriptions = 0;				
				rs = st.executeQuery("SELECT COUNT(DISTINCT prescription) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique prescription
				while (rs.next()) {
					total_prescriptions = rs.getInt(1);	//column 1
				}									
				yield_tables_names = new Object[total_prescriptions];
				yield_tables_values = new Object[total_prescriptions][][];
				
				
				//get the table name and put into array "nameOftable"
				rs = st.executeQuery("SELECT DISTINCT prescription, COUNT(prescription) as total_rows FROM yield_tables GROUP BY prescription;");	// prescription is auto sorted because of the "GROUP BY"		
				int prescription_count = 0;
				while (rs.next()) {
					yield_tables_names[prescription_count] = rs.getString(1);		// column 1 = prescription
					yield_tables_values[prescription_count] = new Object[Integer.valueOf(rs.getString(2))][];		// column 2 = total_rows of that prescription					
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
						yield_tables_values[i][row] = new Object[colCount];
						for (int col = 0; col < colCount; col++) {
							String value = rs.getString(col + 1);
							yield_tables_values[i][row][col] = value;
							yield_tables_column_unique_values[col].add(value);
						}
					}
				}		
				
				// Convert sets to lists and sort the Lists  --- Important note: we always prefer SORTING DOUBLE
				Set<String> fail_comparison_attributes = new LinkedHashSet<String>();
				unique_values_list = new ArrayList[colCount];
				for (int col = 0; col < colCount; col++) {
					int processing_col = col;
					unique_values_list[col] = new ArrayList<String>(yield_tables_column_unique_values[col]);
					Collections.sort(unique_values_list[col], new Comparator<String>() {	// Sort the list
						@Override
						public int compare(String o1, String o2) {
							try {
								return Double.valueOf(o1).compareTo(Double.valueOf(o2));	// Sort Double
							} catch (Exception e1) {
								fail_comparison_attributes.add(yield_tables_column_names[processing_col]);
								return o1.compareTo(o2);	// if fail --> Sort String
							}
						}
					});
					
				}
				System.out.println("non-numeric yield attributes (fail double comparison):");
				for (String atb : fail_comparison_attributes) System.out.println("     - " + atb);
				yield_tables_column_unique_values = null;	// clear to save memory
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Read_Database   -   Database connection error");
		} finally {
			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
		    try { rs.close(); } catch (Exception e) { /* ignored */}	
		    try { st.close(); } catch (Exception e) { /* ignored */}
		    try { conn.close(); } catch (Exception e) { /* ignored */}
		}			
	}


	
	private void Read_existing_strata() {		
		try {			
			if (file_database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
				st = conn.createStatement();

				
				// get total rows (strata count)
				int rowCount2 = 0;				
				rs = st.executeQuery("SELECT COUNT(DISTINCT strata_id) FROM existing_strata;");		// This only have 1 row and 1 column, the value is total number of unique strata
				while (rs.next()) {
					rowCount2 = rs.getInt(1);	//column 1
				}				
				
				// get total columns
				rs = st.executeQuery("SELECT * FROM existing_strata ORDER BY strata_id ASC;");	// always sort by strata_id
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
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Read_Database   -   Database connection error");
		} finally {
			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
		    try { rs.close(); } catch (Exception e) { /* ignored */}	
		    try { st.close(); } catch (Exception e) { /* ignored */}
		    try { conn.close(); } catch (Exception e) { /* ignored */}
		}			
	}
	
	
	private void Read_strata_definition() {		
		try {			
			if (file_database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
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
				
//				// This is saved for another way to sort strata definition by layer_id & attribute_id
//				Arrays.sort(strata_definition_values, new Comparator<String[]>(){
//					@Override
//					public int compare(String[] first, String[] second) {
//						int comparedTo = first[0].compareTo(second[0]);	// compare the first element (layer)
//						if (comparedTo == 0)
//							return first[2].compareTo(second[2]);	// if the first element (layer) is same (result is 0), compare the third element (attribute)
//						else
//							return comparedTo;
//					}
//				});
					

				layers_Title = new ArrayList<String>();
				layers_Title_ToolTip = new ArrayList<String>();
				
				allLayers = new ArrayList<List<String>>();
				allLayers_ToolTips = new ArrayList<List<String>>();				
				
				// Loop through all rows and add all layers information
				for (int i = 0; i < rowCount3; i++) {
					if (!layers_Title.contains(strata_definition_values[i][0])) {  // If found a new layer
						// Add Layer title and toolTip    	
			        	layers_Title.add(strata_definition_values[i][0]);
			        	layers_Title_ToolTip.add(strata_definition_values[i][1]);
			        	
			        	// Add 2 temporary Lists to the allLayers & allLayers_ToolTips
			        	allLayers.add(new ArrayList<String>());
			        	allLayers_ToolTips.add(new ArrayList<String>());
					}
									
					allLayers.get(allLayers.size() - 1).add(strata_definition_values[i][2]);		// Add layer's element to the last layer
					allLayers_ToolTips.get(allLayers_ToolTips.size() - 1).add(strata_definition_values[i][3]);		// Add layer's element's ToolTip to the last layer ToolTip
				}	
				
				
				
//				// Testing Linked List
//				layers = new LinkedList_Layers();
//				for (int i = 0; i < rowCount3; i++) {				
//					if (layers.isEmpty() || ! layers.get(layers.size() - 1).layer_id.equalsIgnoreCase(strata_definition_values[i][0])) {  // If found a new layer then add the layer
//						Layer_Item new_layer = new Layer_Item(strata_definition_values[i][0], strata_definition_values[i][1], new LinkedList<Attribute_Item>());
//						layers.add(new_layer);
//					}									
//					Attribute_Item new_attribute = new Attribute_Item(strata_definition_values[i][2], strata_definition_values[i][3]);	// add the attribute to the attributes of the last added layer		
//					layers.get(layers.size() - 1).attributes.add(new_attribute);
//				}	
//															
//				for (Layer_Item i : layers) {					
//					for (Attribute_Item j : i.attributes) {
//						System.out.println(i.layer_id + " " + i.layer_description + " " + j.attribute_id + " " + j.attribute_description);
//					}
//				}		
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Read_Database   -   Database connection error");
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
	public Object[][][] get_yield_tables_values() {	
		return yield_tables_values;
	}
	
	public String[] get_yield_tables_column_names() {
		return yield_tables_column_names;
	}

	public Object[] get_yield_tables_names() {
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
//				Class.forName("org.sqlite.JDBC").newInstance();
//				conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
//				st = conn.createStatement();
//				
//				rs = st.executeQuery("SELECT DISTINCT " + yield_tables_column_names[columnIndex] + " FROM yield_tables;");
//				while (rs.next()) {
//					unique_values_list.add(rs.getString(1));	// column 1 is the only column form this query
//				}									
//			}
//		} catch (Exception e) {
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
		String tableName_toFind = cover_type + "_" + size_class + "_" + method + "_" + forest_status + "_" + timing_choice;
		tableName_toFind = tableName_toFind.toUpperCase();
		
		String valueReturn = null;
		try {
			int index = Arrays.asList(yield_tables_names).indexOf(tableName_toFind);
			
			List<String> yield_tables_column_names_list = Arrays.asList(yield_tables_column_names);	// Convert array to list
			int age_class_index = yield_tables_column_names_list.indexOf("st_age_10");																// CNPZ case
			if (yield_tables_column_names_list.contains("age_class")) age_class_index = yield_tables_column_names_list.indexOf("age_class");		// CGNF case
			
			valueReturn = yield_tables_values[index][0][age_class_index].toString();			// row 0 is the first period (1st row)
		} catch (Exception e) {
			valueReturn = null;
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Not found age class from yield table: " + tableName_toFind);
		}
		
		return valueReturn;	
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
	
	
	public List<String> get_layers_Title() {   
		return layers_Title;
	}

	
	public List<String> get_layers_Title_ToolTip() {	
		return layers_Title_ToolTip;
	}
	
	
	public List<List<String>> get_all_layers() {		
		return allLayers;
	}
	
	public List<List<String>> get_allLayers_ToolTips() {
		return allLayers_ToolTips;
	}	
	
	
	
	
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------	
	public ArrayList<String>[] get_rotation_ranges() {
		ArrayList<String>[] rotation_ranges = null;		
		try {			
			if (file_database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_database);
				st = conn.createStatement();

				
				//-----------------------------------------------------------------------------------------------------------
				// this will be used to generate combo box values in the "Cover type Conversion" windows
				rs = st.executeQuery("SELECT e_table.e_covertype, "
						+ "e_table.e_min_rotation_age, "
						+ "e_table.e_max_rotation_age, "
						+ "r_table.r_min_rotation_age, "
						+ "r_table.r_max_rotation_age FROM ( "
						
						+ "(SELECT SUBSTR(DISTINCT EA_E_prescription, 1, INSTR(DISTINCT EA_E_prescription, '_')-1) as e_covertype, "
								+ "MIN(CAST(rotation_age as decimal)) AS e_min_rotation_age, "
								+ "MAX(CAST(rotation_age as decimal)) AS e_max_rotation_age "
								+ " FROM "
								+ "(SELECT DISTINCT prescription AS EA_E_prescription, "
								+ "action_type AS final_activity, CAST(age_class as decimal) AS rotation_age FROM yield_tables WHERE prescription LIKE '%\"_EA\"_E%' ESCAPE '\"' GROUP BY prescription) "
						+ "GROUP BY e_covertype) AS e_table "
								
						+ "LEFT JOIN"
						
						+ "(SELECT SUBSTR(DISTINCT EA_R_prescription, 1, INSTR(DISTINCT EA_R_prescription, '_')-1) as r_covertype, "
							+ "MIN(CAST(rotation_age as decimal)) AS r_min_rotation_age, "
							+ "MAX(CAST(rotation_age as decimal)) AS r_max_rotation_age "
							+ "FROM "
							+ "(SELECT DISTINCT prescription AS EA_R_prescription, "
							+ "action_type AS final_activity, CAST(age_class as decimal) AS rotation_age FROM yield_tables WHERE prescription LIKE '%\"_EA\"_R%' ESCAPE '\"' GROUP BY prescription) "
						+ "GROUP BY r_covertype) AS r_table "
						
						+ "ON e_table.e_covertype = r_table.r_covertype)"
						
						
				+ "UNION ALL "
						
						
						+"SELECT r_table.r_covertype, "
						+ "e_table.e_min_rotation_age, "
						+ "e_table.e_max_rotation_age, "
						+ "r_table.r_min_rotation_age, "
						+ "r_table.r_max_rotation_age FROM ( "
						
						+ "(SELECT SUBSTR(DISTINCT EA_R_prescription, 1, INSTR(DISTINCT EA_R_prescription, '_')-1) as r_covertype, "
							+ "MIN(CAST(rotation_age as decimal)) AS r_min_rotation_age, "
							+ "MAX(CAST(rotation_age as decimal)) AS r_max_rotation_age "
							+ "FROM "
							+ "(SELECT DISTINCT prescription AS EA_R_prescription, "
							+ "action_type AS final_activity, CAST(age_class as decimal) AS rotation_age FROM yield_tables WHERE prescription LIKE '%\"_EA\"_R%' ESCAPE '\"' GROUP BY prescription) "
						+ "GROUP BY r_covertype) AS r_table "
						
						+ "LEFT JOIN"
						
						+ "(SELECT SUBSTR(DISTINCT EA_E_prescription, 1, INSTR(DISTINCT EA_E_prescription, '_')-1) as e_covertype, "
							+ "MIN(CAST(rotation_age as decimal)) AS e_min_rotation_age, "
							+ "MAX(CAST(rotation_age as decimal)) AS e_max_rotation_age "
							+ " FROM "
							+ "(SELECT DISTINCT prescription AS EA_E_prescription, "
							+ "action_type AS final_activity, CAST(age_class as decimal) AS rotation_age FROM yield_tables WHERE prescription LIKE '%\"_EA\"_E%' ESCAPE '\"' GROUP BY prescription) "
						+ "GROUP BY e_covertype) AS e_table "
						
						+ "ON e_table.e_covertype = r_table.r_covertype)"
				
				+ "WHERE  e_table.e_covertype IS NULL"
				);				
				rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				
				rotation_ranges = new ArrayList[5]; // see query 2.3. in system_sql_library
				for (int i = 0; i < rotation_ranges.length; i++) {
					rotation_ranges[i] = new ArrayList<String>();	// initialize the 5 lists
				}
				 
				while (rs.next()) {	// add to all 5 lists
					for (int i = 0; i < colCount; i++) {
						if (rs.getString(i + 1) != null) {
							rotation_ranges[i].add(rs.getString(i + 1));
						} else{
							rotation_ranges[i].add("-9999");
						}
					}
				}
				
				for (int i = 0; i < colCount; i++) {
					System.out.println("Testing 5 lists (5 columns) of rotation age ranges as in system query 2.3");
					System.out.println(rotation_ranges[i]);	// printing the 5 lists to test
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Read_Database   -   Database connection error");
		} finally {
			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
		    try { rs.close(); } catch (Exception e) { /* ignored */}	
		    try { st.close(); } catch (Exception e) { /* ignored */}
		    try { conn.close(); } catch (Exception e) { /* ignored */}
		}	
		return rotation_ranges;
	}
	
	
	public List<String> get_method_period_layers_title() {
		// Layers title
		List<String> method_period_layers_title = Arrays.asList(new String[] { "method", "period" });
		return method_period_layers_title;
	}

	
	public List<List<String>> get_method_period_layers() {
		// Layers element name
		List<String> method = Arrays.asList(new String[] { "NG_E", "PB_E", "GS_E", "EA_E", "MS_E", "BS_E", "NG_R", "PB_R", "GS_R", "EA_R" });	// method
		List<String> period = new ArrayList<String>() {		// period
			{
				for (int i = 1; i <= 99; i++) {add(Integer.toString(i));}
			}
		};	
			
		List<List<String>> method_period_layers = new ArrayList<List<String>>();
		method_period_layers.add(method);
		method_period_layers.add(period);
			
		return method_period_layers;
	}

	
	public List<String> get_method_choice_layers_title() {
		// Layers title
		List<String> method_period_layers_title = Arrays.asList(new String[] { "method", "choice" });
		return method_period_layers_title;
	}

	
	public List<List<String>> get_method_choice_layers() {
		// Layers element name
		List<String> method = Arrays.asList(new String[] { "NG_E", "PB_E", "GS_E", "EA_E", "MS_E", "BS_E", "NG_R", "PB_R", "GS_R", "EA_R" });	// method
		List<String> choice = new ArrayList<String>() {		// period
			{
				for (int i = 0; i <= 14; i++) {add(Integer.toString(i));}
			}
		};	
			
		List<List<String>> method_choice_layers = new ArrayList<List<String>>();
		method_choice_layers.add(method);
		method_choice_layers.add(choice);
			
		return method_choice_layers;
	}
	
	
	public List<String> get_method_choice_rotationperiod_rotationage_regenlayer5_layers_title() {
		// Layers title
		List<String> method_choice_layers_title = Arrays.asList(new String[] { "method", "choice", "rotation_period", "rotation_age", "regen_layer5" });
		return method_choice_layers_title;
	}

	
	public List<List<String>> get_method_choice_rotationperiod_rotationage_regenlayer5_layers() {
		// Layers element name
		List<String> method = Arrays.asList(new String[] { "NG_E", "PB_E", "GS_E", "EA_E", "MS_E", "BS_E", "NG_R", "PB_R", "GS_R", "EA_R" });	// method
		List<String> choice = new ArrayList<String>() {		// choice
			{
				for (int i = 0; i <= 14; i++) {add(Integer.toString(i));}
			}
		};
		List<String> rotation_period = new ArrayList<String>() {	// rotation_period
			{
				for (int i = 1; i <= 99; i++) {add(Integer.toString(i));}
			}
		};
		List<String> rotation_age = new ArrayList<String>() {		// rotation_age
			{
				for (int i = 1; i <= 99; i++) {add(Integer.toString(i));}
			}
		};
		List<String> regen_layer5 = new ArrayList<String>(allLayers.get(4));	// regen_layer5
			
		List<List<String>> method_choice_rotationperiod_rotationage_regenlayer5_layers = new ArrayList<List<String>>();
		method_choice_rotationperiod_rotationage_regenlayer5_layers.add(method);
		method_choice_rotationperiod_rotationage_regenlayer5_layers.add(choice);
		method_choice_rotationperiod_rotationage_regenlayer5_layers.add(rotation_period);
		method_choice_rotationperiod_rotationage_regenlayer5_layers.add(rotation_age);
		method_choice_rotationperiod_rotationage_regenlayer5_layers.add(regen_layer5);
			
		return method_choice_rotationperiod_rotationage_regenlayer5_layers;
	}

	
	public String get_ParameterToolTip(String yt_columnName) {
		String toolTip = "";

		
		// Read library from the system
		File file_PrismLibrary = null;
		
		if (file_PrismLibrary == null) {		// This is to make it read the file only once, after that no need to repeat reading this file any more
			try {
				file_PrismLibrary = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "PrismLibrary.csv");
				file_PrismLibrary.deleteOnExit();

				InputStream initialStream = getClass().getResourceAsStream("/PrismLibrary.csv"); //Default definition
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);

				OutputStream outStream = new FileOutputStream(file_PrismLibrary);
				outStream.write(buffer);

				initialStream.close();
				outStream.close();
			} catch (FileNotFoundException e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			} catch (IOException e2) {
				System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
			} 
		}
		
		
		String delimited = ","; // 		","		comma delimited			"\\s+"		space delimited		"\t"	tab delimited	
		try {
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file_PrismLibrary.getAbsolutePath()), StandardCharsets.UTF_8);
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
					toolTip = value[i][1];
				}
			}	
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
			
		return toolTip;
	}	
	
	
	
	
	
	
	
}
