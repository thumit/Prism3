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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import prismConvenienceClass.FilesHandle;
import prismConvenienceClass.StringHandle;

public class Read_Database {
	private Object[][][] yield_tables_values;			// Note: indexes start from 0 
	private Object[] yield_tables_names;
	private String[] yield_tables_column_names;
	
	
	private String[][] existing_strata_values;
	
	
	private String[][] strata_definition_values;	
	private List<String> layers_Title;
	private List<String> layers_Title_ToolTip;
	private List<List<String>> allLayers;
	private List<List<String>> allLayers_ToolTips;
	
	
	private LinkedList_Layers layers;
	
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	
	private File file_Database;
	private boolean is_yield_tables_read = false;
	private boolean is_existing_strata_read = false;
	private boolean is_strata_definitions_read = false;
	
	public Read_Database(File file_Database) {
		this.file_Database = file_Database;
		
		
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
			if (file_Database.exists()) {	
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file_Database);
				st = conn.createStatement();

				
				//-----------------------------------------------------------------------------------------------------------
				// For yield_tables
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
				
				
				rs = st.executeQuery("SELECT * FROM yield_tables ORDER BY prescription ASC;");				
				// get total columns and "table_ColumnNames" for each yield table
				rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				yield_tables_column_names = new String[colCount];
				for (int i = 1; i <= colCount; i++) {		// this start from 1
					yield_tables_column_names[i-1] = rsmd.getColumnName(i);			// Note that tableColumnNames start from 0
				}				
				
				// get values for each table
				for (int i = 0; i < yield_tables_values.length; i++) {
					for (int row = 0; row < yield_tables_values[i].length; row++) {
						rs.next();
						yield_tables_values[i][row] = new Object[colCount];
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
				rs = st.executeQuery("SELECT COUNT(DISTINCT strata_id) FROM existing_strata;");		// This only have 1 row and 1 column, the value is total number of unique strata
				while (rs.next()) {
					rowCount2 = rs.getInt(1);	//column 1
				}				
				
				// get total columns
				rs = st.executeQuery("SELECT * FROM existing_strata ORDER BY strata_id ASC;");	
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
				// Get total rows
				int rowCount3 = 0;				
				rs = st.executeQuery("SELECT COUNT(layer_id) FROM strata_definition;");		// This only have 1 row and 1 column
				while (rs.next()) {
					rowCount3 = rs.getInt(1);	// column 1
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
				
				// Loop through all rows and add all layers information
				for (int i = 0; i < rowCount3; i++) {
					if (! layers_Title.contains(strata_definition_values[i][0])) {  // If found a new layer
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
				
				
				
				// Testing Linked List
				layers = new LinkedList_Layers();
				for (int i = 0; i < rowCount3; i++) {				
					if (layers.isEmpty() || ! layers.get(layers.size() - 1).layer_id.equalsIgnoreCase(strata_definition_values[i][0])) {  // If found a new layer then add the layer
						Layer_Item new_layer = new Layer_Item(strata_definition_values[i][0], strata_definition_values[i][1], new LinkedList<Attribute_Item>());
						layers.add(new_layer);
					}									
					Attribute_Item new_attribute = new Attribute_Item(strata_definition_values[i][2], strata_definition_values[i][3]);	// add the attribute to the attributes of the last added layer		
					layers.get(layers.size() - 1).attributes.add(new_attribute);
				}	
															
//				for (Layer_Item i : layers) {					
//					for (Attribute_Item j : i.attributes) {
//						System.out.println(i.layer_id + " " + i.layer_description + " " + j.attribute_id + " " + j.attribute_description);
//					}
//				}		
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
		List<String> actionList = null;
		
		List<String> yield_tables_column_names_list = Arrays.asList(yield_tables_column_names);	// Convert array to list		
		int index = yield_tables_column_names_list.indexOf("action_type");
		actionList = get_col_unique_values_list(index);			
		
		Collections.sort(actionList);	// Sort this list
		String[] action_type = actionList.toArray(new String[actionList.size()]);	// Convert list to array	
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


	
	
	
	
	
	
	
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------
	// This block is For 2 extra layers ------------------------------------------------------------------------------------------------------	
	public List<String> get_method_period_layers_title() {
		// Layers title
		List<String> method_period_layers_title = new ArrayList<String>();
		method_period_layers_title.add("silviculture method");
		method_period_layers_title.add("time period");
		return method_period_layers_title;
	}

	
	public List<List<String>> get_method_period_layers() {
		// Layers element name
		List<String> layer1 = new ArrayList<String>();		// methods
		layer1.add("NG_E");
		layer1.add("PB_E");
		layer1.add("GS_E");
		layer1.add("EA_E");	
		layer1.add("MS_E");
		layer1.add("BS_E");
		layer1.add("NG_R");
		layer1.add("PB_R");
		layer1.add("GS_R");
		layer1.add("EA_R");
		
		List<String> layer2 = new ArrayList<String>();		// periods
		for (int i = 1; i <= 50; i++) {
			layer2.add(Integer.toString(i));
		}		
			
		List<List<String>> method_period_layers = new ArrayList<List<String>>();
		method_period_layers.add(layer1);
		method_period_layers.add(layer2);
			
		return method_period_layers;
	}

	
	public List<String> get_method_choice_layers_title() {
		// Layers title
		List<String> method_choice_layers_title = new ArrayList<String>();
		method_choice_layers_title.add("silviculture method");
		method_choice_layers_title.add("timing choice");
		return method_choice_layers_title;
	}

	
	public List<List<String>> get_method_choice_layers() {
		// Layers element name
		List<String> layer1 = new ArrayList<String>();		// methods
		layer1.add("NG_E");
		layer1.add("PB_E");
		layer1.add("GS_E");
		layer1.add("EA_E");	
		layer1.add("MS_E");
		layer1.add("BS_E");
		layer1.add("NG_R");
		layer1.add("PB_R");
		layer1.add("GS_R");
		layer1.add("EA_R");
		
		List<String> layer2 = new ArrayList<String>();		// timing choice
		for (int i = 0; i <= 4; i++) {
			layer2.add(Integer.toString(i));
		}		
			
		List<List<String>> method_choice_layers = new ArrayList<List<String>>();
		method_choice_layers.add(layer1);
		method_choice_layers.add(layer2);
			
		return method_choice_layers;
	}

	
	public String get_ParameterToolTip(String yt_columnName) {
		String toolTip = null;

		
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
//					value[i][j] = rowValue[j].replaceAll("\\s+", "");		// Remove all the space in the String   
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
