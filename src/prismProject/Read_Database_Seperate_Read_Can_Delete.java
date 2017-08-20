package prismProject;

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
import java.util.LinkedList;
import java.util.List;

import prismConvenienceClasses.FilesHandle;
import prismConvenienceClasses.StringHandle;

public class Read_Database_Seperate_Read_Can_Delete {
	private Object[][][] yield_tables_values;			// Note: indexes start from 0 
	private Object[] yield_tables_names;
	private String[] yield_tables_column_names;
	private String[] action_type;
	
	
	private String[][] existing_strata_values;
	
	
	private String[][] strata_definition_values;	
	private List<String> layers_Title;
	private List<String> layers_Title_ToolTip;
	private List<List<String>> allLayers;
	private List<List<String>> allLayers_ToolTips;
	
	
	private LayerLinkedList layers;
	
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	
	private File file_Database;
	private boolean is_yield_tables_read = false;
	private boolean is_existing_strata_read = false;
	private boolean is_strata_definitions_read = false;
	
	public Read_Database_Seperate_Read_Can_Delete(File file_Database) {	
		this.file_Database = file_Database;
//		try {			
//			if (file_Database.exists()) {	
//				Class.forName("org.sqlite.JDBC").newInstance();
//				conn = DriverManager.getConnection("jdbc:sqlite:" + file_Database);
//				st = conn.createStatement();
//
//				
//				//-----------------------------------------------------------------------------------------------------------
//				// For yield_tables
//				//-----------------------------------------------------------------------------------------------------------
//				// get total yield tables
//				int tableCount = 0;				
//				rs = st.executeQuery("SELECT COUNT(DISTINCT prescription) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique prescription
//				while (rs.next()) {
//					tableCount = rs.getInt(1);	//column 1
//				}
//				
//				// get total action types
//				int actionCount = 0;				
//				rs = st.executeQuery("SELECT COUNT(DISTINCT action_type) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique action_type
//				while (rs.next()) {
//					actionCount = rs.getInt(1);	//column 1
//				}			
//				
//				yield_tables_names = new Object[tableCount];
//				action_type = new String[actionCount];
//				yield_tables_values = new Object[tableCount][][];
//				
//				
//				//get the table name and put into array "nameOftable"
//				rs = st.executeQuery("SELECT DISTINCT prescription FROM yield_tables;");			
//				int tbl = 0;
//				while (rs.next()) {
//					yield_tables_names[tbl] = rs.getString(1);		//column 1
//					tbl++;
//				}
//				
//				
//				//get action types and put into array "action_type"
//				rs = st.executeQuery("SELECT DISTINCT action_type FROM yield_tables ORDER BY action_type ASC;");			
//				int type_count = 0;
//				while (rs.next()) {
//					action_type[type_count] = rs.getString(1);		//column 1
//					type_count++;
//				}
//				
//				
//				// get total columns and "table_ColumnNames" for each yield table
//				rs = st.executeQuery("SELECT * FROM yield_tables;");	
//				rsmd = rs.getMetaData();
//				int colCount = rsmd.getColumnCount();
//				yield_tables_column_names = new String[colCount];
//				for (int i = 1; i <= colCount; i++) {		//this start from 1
//					yield_tables_column_names[i-1] = rsmd.getColumnName(i);			//Note that tableColumnNames start from 0
//				}
//				
//				
//				// get total rows for each yield table
//				int[] rowCount = new int[tableCount];
//				for (int i = 0; i < tableCount; i++) {				
//					rowCount[i] = 0;
//				}
//				while (rs.next()) {
//					for (int i = 0; i < tableCount; i++) {				
//						if (rs.getString(1).equals(yield_tables_names[i])) {
//							rowCount[i]++;
//						}
//					}		
//				}	
//				
//				
//				// loop through all yield tables , get value of each table and put into array table_values[][][]
//				for (int i = 0; i < tableCount; i++) {				
//					//re-define table dimensions	--->	VERY IMPORTANT CODES
//					yield_tables_values[i] = new Object[rowCount[i]][];
//					for (int row = 0; row < rowCount[i]; row++) {
//						yield_tables_values[i][row] = new Object[colCount];
//					}
//				}
//					
//						
//				// get values for each table
//				rs = st.executeQuery("SELECT * FROM yield_tables;");
//				for (int i = 0; i < tableCount; i++) {
//					for (int row = 0; row < rowCount[i]; row++) {
//						rs.next();
//						for (int col = 0; col < colCount; col++) {
//							yield_tables_values[i][row][col] = rs.getString(col + 1);
//						}
//					}
//				}			
//				
//				
//				
//				
//				//-----------------------------------------------------------------------------------------------------------
//				// For existing_strata
//				//-----------------------------------------------------------------------------------------------------------
//				// get total rows (strata count)
//				int rowCount2 = 0;				
//				rs = st.executeQuery("SELECT COUNT(DISTINCT strata_id) FROM existing_strata;");		//This only have 1 row and 1 column, the value is total number of unique strata
//				while (rs.next()) {
//					rowCount2 = rs.getInt(1);	//column 1
//				}				
//				
//				// get total columns
//				rs = st.executeQuery("SELECT * FROM existing_strata;");	
//				rsmd = rs.getMetaData();
//				int colCount2 = rsmd.getColumnCount();
//				
//				// Redefine size
//				existing_strata_values = new String[rowCount2][colCount2];
//				
//				// get values
//				for (int row = 0; row < rowCount2; row++) {
//					rs.next();
//					for (int col = 0; col < colCount2; col++) {
//						existing_strata_values[row][col] = rs.getString(col + 1);
//					}
//				}
//				
//				
//				
//				
//				//-----------------------------------------------------------------------------------------------------------
//				// For strata_definition
//				//-----------------------------------------------------------------------------------------------------------
//				// get total rows
//				int rowCount3 = 0;				
//				rs = st.executeQuery("SELECT COUNT(layer_id) FROM strata_definition;");		//This only have 1 row and 1 column
//				while (rs.next()) {
//					rowCount3 = rs.getInt(1);	//column 1
//				}				
//				
//				// get total columns
//				rs = st.executeQuery("SELECT * FROM strata_definition;");	
//				rsmd = rs.getMetaData();
//				int colCount3 = rsmd.getColumnCount();
//				
//				// Redefine size
//				strata_definition_values = new String[rowCount3][colCount3];
//				
//				// get values
//				for (int row = 0; row < rowCount3; row++) {
//					rs.next();
//					for (int col = 0; col < colCount3; col++) {
//						strata_definition_values[row][col] = rs.getString(col + 1);
//					}
//				}
//				
//				
//					
//
//				layers_Title = new ArrayList<String>();
//				layers_Title_ToolTip = new ArrayList<String>();
//				
//				allLayers = new ArrayList<List<String>>();
//				allLayers_ToolTips = new ArrayList<List<String>>();				
//				
//				//Loop through all rows and add all layers information
//				for (int i = 0; i < rowCount3; i++) {
//					if (! layers_Title.contains(strata_definition_values[i][0])) {  //If found a new layer
//						//Add Layer title and toolTip    	
//			        	layers_Title.add(strata_definition_values[i][0]);
//			        	layers_Title_ToolTip.add(strata_definition_values[i][1]);
//			        	
//			        	//Add 2 temporary Lists to the allLayers & allLayers_ToolTips
//			        	allLayers.add(new ArrayList<String>());
//			        	allLayers_ToolTips.add(new ArrayList<String>());
//					}
//									
//					allLayers.get(allLayers.size() - 1).add(strata_definition_values[i][2]);		// Add layer's element to the last layer
//					allLayers_ToolTips.get(allLayers_ToolTips.size() - 1).add(strata_definition_values[i][3]);		// Add layer's element's ToolTip to the last layer ToolTip
//				}	
//				
//				
//				
//				// Testing Linked List
//				layers = new LayerLinkedList();
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
//				
//			}
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Read_Database   -   Database connection error");
//		} finally {
//			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
//		    try { rs.close(); } catch (Exception e) { /* ignored */}	
//		    try { st.close(); } catch (Exception e) { /* ignored */}
//		    try { conn.close(); } catch (Exception e) { /* ignored */}
//		}		
//		
//		
//		
//		
//		
////		try {
////			Class.forName("org.sqlite.JDBC").newInstance();
////			conn = DriverManager.getConnection("jdbc:sqlite:" + file_Database);
////			Statement st = conn.createStatement();
////
////			// get total yield tables
////			ResultSet resultSet1 = st.executeQuery("SELECT COUNT(DISTINCT prescription) FROM yield_tables;");
////			while (resultSet1.next()) {
////				int tableCount = resultSet1.getInt(1); // column 1
////				System.out.println("Total table = " + tableCount);
////
////				// get total action types
////				ResultSet resultSet2 = st.executeQuery("SELECT COUNT(DISTINCT action_type) FROM yield_tables;");
////				while (resultSet2.next()) {
////					int actionCount = resultSet2.getInt(1); // column 1
////					System.out.println("Total action count = " + actionCount);
////					yield_tables_names = new Object[tableCount];
////					action_type = new String[actionCount];
////					yield_tables_values = new Object[tableCount][][];
////
////					// get the table name and put into array "nameOftable"
////					ResultSet resultSet3 = st.executeQuery("SELECT DISTINCT prescription FROM yield_tables;");
////					int tbl = 0;
////					while (resultSet3.next()) {
////						yield_tables_names[tbl] = resultSet3.getString(1); // column																			// 1
////						tbl++;
////						System.out.println("Table = " + yield_tables_names[tbl]);
////
////					}
////					resultSet3.close();
////				}
////				resultSet2.close();
////			}
////			resultSet1.close();
////
////		} catch (Exception e) {
////			e.printStackTrace();
////		}	
//		

	}

	
	private void Read_yield_tables() {		
		try {			
			if (file_Database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_Database);
				st = conn.createStatement();

				
				//-----------------------------------------------------------------------------------------------------------
				// For yield_tables
				//-----------------------------------------------------------------------------------------------------------
				// get total yield tables
				int tableCount = 0;				
				rs = st.executeQuery("SELECT COUNT(DISTINCT prescription) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique prescription
				while (rs.next()) {
					tableCount = rs.getInt(1);	//column 1
				}
				
				// get total action types
				int actionCount = 0;				
				rs = st.executeQuery("SELECT COUNT(DISTINCT action_type) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique action_type
				while (rs.next()) {
					actionCount = rs.getInt(1);	//column 1
				}			
				
				yield_tables_names = new Object[tableCount];
				action_type = new String[actionCount];
				yield_tables_values = new Object[tableCount][][];
				
				
				//get the table name and put into array "nameOftable"
				rs = st.executeQuery("SELECT DISTINCT prescription FROM yield_tables;");			
				int tbl = 0;
				while (rs.next()) {
					yield_tables_names[tbl] = rs.getString(1);		//column 1
					tbl++;
				}
				
				
				//get action types and put into array "action_type"
				rs = st.executeQuery("SELECT DISTINCT action_type FROM yield_tables ORDER BY action_type ASC;");			
				int type_count = 0;
				while (rs.next()) {
					action_type[type_count] = rs.getString(1);		//column 1
					type_count++;
				}
				
				
				// get total columns and "table_ColumnNames" for each yield table
				rs = st.executeQuery("SELECT * FROM yield_tables;");	
				rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				yield_tables_column_names = new String[colCount];
				for (int i = 1; i <= colCount; i++) {		//this start from 1
					yield_tables_column_names[i-1] = rsmd.getColumnName(i);			//Note that tableColumnNames start from 0
				}
				
				
				// get total rows for each yield table
				int[] rowCount = new int[tableCount];
				for (int i = 0; i < tableCount; i++) {				
					rowCount[i] = 0;
				}
				while (rs.next()) {
					for (int i = 0; i < tableCount; i++) {				
						if (rs.getString(1).equals(yield_tables_names[i])) {
							rowCount[i]++;
						}
					}		
				}	
				
				
				// loop through all yield tables , get value of each table and put into array table_values[][][]
				for (int i = 0; i < tableCount; i++) {				
					//re-define table dimensions	--->	VERY IMPORTANT CODES
					yield_tables_values[i] = new Object[rowCount[i]][];
					for (int row = 0; row < rowCount[i]; row++) {
						yield_tables_values[i][row] = new Object[colCount];
					}
				}
					
						
				// get values for each table
				rs = st.executeQuery("SELECT * FROM yield_tables;");
				for (int i = 0; i < tableCount; i++) {
					for (int row = 0; row < rowCount[i]; row++) {
						rs.next();
						for (int col = 0; col < colCount; col++) {
							yield_tables_values[i][row][col] = rs.getString(col + 1);
						}
					}
				}							
			}
			is_yield_tables_read = true;
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
			if (file_Database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_Database);
				st = conn.createStatement();

				
				//-----------------------------------------------------------------------------------------------------------
				// For existing_strata
				//-----------------------------------------------------------------------------------------------------------
				// get total rows (strata count)
				int rowCount2 = 0;				
				rs = st.executeQuery("SELECT COUNT(DISTINCT strata_id) FROM existing_strata;");		//This only have 1 row and 1 column, the value is total number of unique strata
				while (rs.next()) {
					rowCount2 = rs.getInt(1);	//column 1
				}				
				
				// get total columns
				rs = st.executeQuery("SELECT * FROM existing_strata;");	
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
			is_existing_strata_read = true;
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
			if (file_Database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_Database);
				st = conn.createStatement();

				
				
				//-----------------------------------------------------------------------------------------------------------
				// For strata_definition
				//-----------------------------------------------------------------------------------------------------------
				// get total rows
				int rowCount3 = 0;				
				rs = st.executeQuery("SELECT COUNT(layer_id) FROM strata_definition;");		//This only have 1 row and 1 column
				while (rs.next()) {
					rowCount3 = rs.getInt(1);	//column 1
				}				
				
				// get total columns
				rs = st.executeQuery("SELECT * FROM strata_definition;");	
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
				
				
					

				layers_Title = new ArrayList<String>();
				layers_Title_ToolTip = new ArrayList<String>();
				
				allLayers = new ArrayList<List<String>>();
				allLayers_ToolTips = new ArrayList<List<String>>();				
				
				//Loop through all rows and add all layers information
				for (int i = 0; i < rowCount3; i++) {
					if (! layers_Title.contains(strata_definition_values[i][0])) {  //If found a new layer
						//Add Layer title and toolTip    	
			        	layers_Title.add(strata_definition_values[i][0]);
			        	layers_Title_ToolTip.add(strata_definition_values[i][1]);
			        	
			        	//Add 2 temporary Lists to the allLayers & allLayers_ToolTips
			        	allLayers.add(new ArrayList<String>());
			        	allLayers_ToolTips.add(new ArrayList<String>());
					}
									
					allLayers.get(allLayers.size() - 1).add(strata_definition_values[i][2]);		// Add layer's element to the last layer
					allLayers_ToolTips.get(allLayers_ToolTips.size() - 1).add(strata_definition_values[i][3]);		// Add layer's element's ToolTip to the last layer ToolTip
				}	
				
				
				
				// Testing Linked List
				layers = new LayerLinkedList();
				for (int i = 0; i < rowCount3; i++) {				
					if (layers.isEmpty() || ! layers.get(layers.size() - 1).layer_id.equalsIgnoreCase(strata_definition_values[i][0])) {  // If found a new layer then add the layer
						Layer_Item new_layer = new Layer_Item(strata_definition_values[i][0], strata_definition_values[i][1], new LinkedList<Attribute_Item>());
						layers.add(new_layer);
					}									
					Attribute_Item new_attribute = new Attribute_Item(strata_definition_values[i][2], strata_definition_values[i][3]);	// add the attribute to the attributes of the last added layer		
					layers.get(layers.size() - 1).attributes.add(new_attribute);
				}	
															
				for (Layer_Item i : layers) {					
					for (Attribute_Item j : i.attributes) {
						System.out.println(i.layer_id + " " + i.layer_description + " " + j.attribute_id + " " + j.attribute_description);
					}
				}		
			}
			is_strata_definitions_read = true;
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
		if (!is_yield_tables_read) Read_yield_tables(); 
		return yield_tables_values;
	}
	
	public String[] get_yield_tables_column_names() {
		if (!is_yield_tables_read) Read_yield_tables();
		return yield_tables_column_names;
	}

	public Object[] get_yield_tables_names() {
		if (!is_yield_tables_read) Read_yield_tables();
		return yield_tables_names;
	}
	
	public String[] get_action_type() {
		if (!is_yield_tables_read) Read_yield_tables();
		return action_type;
	}
	
	public List<String> get_col_unique_values_list(int columnIndex) {
		if (!is_yield_tables_read) Read_yield_tables();
		List<String> listOfUniqueValues = new ArrayList<String>();
		
		for (int tb = 0; tb < yield_tables_values.length; tb++) {
			for (int rowIndex = 0; rowIndex < yield_tables_values[tb].length; rowIndex++) {
				if (!listOfUniqueValues.contains(yield_tables_values[tb][rowIndex][columnIndex].toString())) {	// only add to list if list does not contain the value
					listOfUniqueValues.add(yield_tables_values[tb][rowIndex][columnIndex].toString());
				}
			}
		}

		return listOfUniqueValues;
	}
	
	
	public String get_starting_ageclass(String cover_type, String size_class, String method, String timing_choice) {
		if (!is_yield_tables_read) Read_yield_tables();
		method = "NG";	//only use NG table to find starting age class
		timing_choice ="0";
		String forest_status = "E";
		String tableName_toFind = cover_type + "_" + size_class + "_" + method + "_" + forest_status + "_" + timing_choice;
		tableName_toFind = tableName_toFind.toUpperCase();
		
		String valueReturn = null;
		try {
			int index = Arrays.asList(yield_tables_names).indexOf(tableName_toFind);
			valueReturn = yield_tables_values[index][0][2].toString();			// row 0 is the first period (1st row), column 2 is "st_age_10"
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
		if (!is_existing_strata_read) Read_existing_strata();
		return existing_strata_values;
	}
	
	
	
	
	
	// This block is For strata_definition ------------------------------------------------------------------------------------------------------
	// This block is For strata_definition ------------------------------------------------------------------------------------------------------
	// This block is For strata_definition ------------------------------------------------------------------------------------------------------	
//	public LayerLinkedList get_layers() {       
//		return layers;
//	}
	
	
	
	public String[][] get_strata_definition_values() {
		if (!is_strata_definitions_read) Read_strata_definition();
		return strata_definition_values;
	}	
	
	
	public List<String> get_layers_Title() {   
		if (!is_strata_definitions_read) Read_strata_definition();
		return layers_Title;
	}

	
	public List<String> get_layers_Title_ToolTip() {	
		if (!is_strata_definitions_read) Read_strata_definition();
		return layers_Title_ToolTip;
	}
	
	
	public List<List<String>> get_allLayers() {		
		if (!is_strata_definitions_read) Read_strata_definition();
		return allLayers;
	}
	
	public List<List<String>> get_allLayers_ToolTips() {
		if (!is_strata_definitions_read) Read_strata_definition();
		return allLayers_ToolTips;
	}	


	
	
	
	
	
	
	
	
	public List<String> get_MethodsPeriodsAges_Title() {
		//Layers title
		List<String> MethodsPeriodsAges_Title = new ArrayList<String>();
		MethodsPeriodsAges_Title.add("silvicultural method");
		MethodsPeriodsAges_Title.add("time period");
		MethodsPeriodsAges_Title.add("age class");

		return MethodsPeriodsAges_Title;
	}

	
	public List<List<String>> get_MethodsPeriodsAges() {
		//Layers element name
		List<String> layer1 = new ArrayList<String>();			//Silvicultural methods
		layer1.add("NGe");
		layer1.add("PBe");
		layer1.add("GSe");
		layer1.add("EAe");	
		layer1.add("MSe");
		layer1.add("BSe");
		layer1.add("NGr");
		layer1.add("PBr");
		layer1.add("GSr");
		layer1.add("EAr");

		
		List<String> layer2 = new ArrayList<String>();		//Time Periods
		for (int i = 1; i <= 50; i++) {
			layer2.add(Integer.toString(i));
		}
		
		List<String> layer3 = new ArrayList<String>();		//Age Classes
		for (int i = 1; i <= 50; i++) {
			layer3.add(Integer.toString(i));
		}

			
		List<List<String>> MethodsPeriodsAges = new ArrayList<List<String>>();
		MethodsPeriodsAges.add(layer1);
		MethodsPeriodsAges.add(layer2);
//		MethodsPeriodsAges.add(layer3);
			
		return MethodsPeriodsAges;
	}


	
	public String get_ParameterToolTip(String yt_columnName) {
		String toolTip = null;

		
		//Read library from the system
		File file_PrismLibrary = null;
		
		if (file_PrismLibrary == null) {		//This is to make it read the file only once, after that no need to repeat reading this file any more
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

			// read all values from all rows and columns
			for (int i = 0; i < totalRows; i++) { // Read from 1st row
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < totalCols && j < rowValue.length; j++) {
//					value[i][j] = rowValue[j].replaceAll("\\s+", "");		//Remove all the space in the String   
					value[i][j] = rowValue[j];		//to make toolTp text separated with space, may need the above line if there is spaces in layer and elements name in the file StrataDefinition.csv
				
				}				
				//Tool tip identified by comparing the name before and after normalization
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
