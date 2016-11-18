package spectrumYieldProject;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;

public class Read_DatabaseTables {
	private Object[][][] table_values;			// Note: indexes start from 0 
	private Object[] nameOftable;
	private String[] table_ColumnNames;
	
	
	public Read_DatabaseTables(File file) {
		
		// To get "table_values" and "nameOftable"
		try {
			Connection conn;
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection("jdbc:sqlite:" + file);

			//Find the total number of tables and redefine arrays
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);

			int tableCount = 0;
			while (rs.next()) {
				tableCount++;
			}
			
			nameOftable = new Object[tableCount];
			table_values = new Object[tableCount][][];
			rs.close();
	
			
			//get the table name and put into array "nameOftable"
			ResultSet rs1 = md.getTables(null, null, "%", null);			
			int tbl = 0;
			while (rs1.next()) {
				String tableName = rs1.getString(3);
				nameOftable[tbl] = tableName;
				tbl++;
			}
			rs1.close();

			
			//Loop through all tables , get value of each table and put into array table_values[][][]
			for (int tbl2 = 0; tbl2 < tableCount; tbl2++) {
				Statement st = conn.createStatement();					
				ResultSet rs2 = st.executeQuery("SELECT * FROM " + "[" + nameOftable[tbl2] + "];");
				
				// get rows & columns numbers
				int columnCount = rs2.getMetaData().getColumnCount();	
				int rowCount = 0;
				while (rs2.next()) {
					rowCount++;
				}	
				
				
				//re-define table dimensions	--->	VERY IMPORTANT CODES
				table_values[tbl2] = new Object[rowCount][];
				for (int row = 0; row < rowCount; row++) {
					table_values[tbl2][row] = new Object[columnCount];
				}
				rs2.close();
				

				//Get values for each table
				ResultSet rs3 = st.executeQuery("SELECT * FROM " + "[" + nameOftable[tbl2] + "];");
				int row = 0;
				while (rs3.next() && row < rowCount) {
					for (int col = 0; col < columnCount; col++) {
						table_values[tbl2][row][col] = rs3.getString(col + 1);
					}
					row++;
				}
				rs3.close();
			}

			conn.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}		
		//-----------------------------------------------------------------------------------------------------------------------------
		

		// To get "table_ColumnNames" --> just need to open the first yield table (table 0) and get the column names
		try {
			Connection conn;
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection("jdbc:sqlite:" + file);
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + "[" + nameOftable[0] + "];");
			
			// get columns info
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			table_ColumnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {		//this start from 1
				table_ColumnNames[i-1] = rsmd.getColumnName(i);			//Note that tableColumnNames start from 0
			}			
			
			rs.close();
			conn.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
	}
	
	
	public Object[][][] getTableArrays() {	
		return table_values;
	}
	
	
	public String[] getTableColumnNames() {
		return table_ColumnNames;
	}

	public Object[] get_nameOftable() {
		return nameOftable;
	}
	
	public List<String> getColumnUniqueValues(int columnIndex) {
		List<String> listOfUniqueValues = new ArrayList<String>();
		
		for (int tb = 0; tb < table_values.length; tb++) {
			for (int rowIndex = 0; rowIndex < table_values[tb].length; rowIndex++) {
				if (!listOfUniqueValues.contains(table_values[tb][rowIndex][columnIndex].toString())) {	// only add to list if list does not contain the value
					listOfUniqueValues.add(table_values[tb][rowIndex][columnIndex].toString());
				}
			}
		}

		return listOfUniqueValues;
	}
	
	
	public String get_stratingAgeClass(String s5, String s6, String silviculturalMethod, String timingChoice) {
		if (s5.equals("P")) s5 = "VDIP";
		if (s5.equals("D")) s5 = "VDTD";
		if (s5.equals("W")) s5 = "VMIW";
		if (s5.equals("C")) s5 = "VMTC";
		if (s5.equals("I")) s5 = "VSII";
		if (s5.equals("A")) s5 = "VSTA";
		if (s5.equals("L")) s5 = "VLPP";
		if (s5.equals("N")) s5 = "NS";
		
		if (s6.equals("N")) s6 = "50";
		if (s6.equals("S")) s6 = "30";
		if (s6.equals("P")) s6 = "20";
		if (s6.equals("M")) s6 = "13";
		if (s6.equals("L")) s6 = "12";
		
		silviculturalMethod = "A";	//only use NG table
		timingChoice ="0";
		
		String tableName_toFind = s5 + s6 + silviculturalMethod + timingChoice;
		String valueReturn = null;
		
		try {
			int index = Arrays.asList(nameOftable).indexOf(tableName_toFind);
			valueReturn = table_values[index][0][2].toString();			//row 0 is the first period (1sr row), column 2 is "St_Age/10"
		} catch (Exception e) {
			valueReturn = "not found";
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + " not found age class from yield table: " + tableName_toFind);
		}
		
		return valueReturn;	
	}
	
	
	
	
	
}
