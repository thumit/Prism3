package spectrumYieldProject;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spectrumDatabase.SQLite;

public class Read_DatabaseTables {
	private Object[][][] table_values;			// Note: indexes start from 0 
	private Object[] nameOftable;
	private String[] table_ColumnNames;
	
	
	public Read_DatabaseTables(File file) {
		try {
			Connection conn;
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection("jdbc:sqlite:" + file);
			Statement st = conn.createStatement();	
			ResultSet rs;
			
			
			// get total yield tables
			int tableCount = 0;				
			rs = st.executeQuery("SELECT COUNT(DISTINCT strata) FROM yield_tables;");		//This only have 1 row and 1 column, the value is total number of unique strata
			while (rs.next()) {
				tableCount = rs.getInt(1);	//column 1
			}
			
			nameOftable = new Object[tableCount];
			table_values = new Object[tableCount][][];
			
			
			//get the table name and put into array "nameOftable"
			rs = st.executeQuery("SELECT DISTINCT strata FROM yield_tables;");			
			int tbl = 0;
			while (rs.next()) {
				nameOftable[tbl] = rs.getString(1);		//column 1
				tbl++;
			}
			
			
			// get total columns and "table_ColumnNames" for each yield table
			rs = st.executeQuery("SELECT * FROM yield_tables;");	
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			table_ColumnNames = new String[colCount];
			for (int i = 1; i <= colCount; i++) {		//this start from 1
				table_ColumnNames[i-1] = rsmd.getColumnName(i);			//Note that tableColumnNames start from 0
			}
			
			
			// get total rows for each yield table
			int[] rowCount = new int[tableCount];
			for (int i = 0; i < tableCount; i++) {				
				rowCount[i] = 0;
			}
			while (rs.next()) {
				for (int i = 0; i < tableCount; i++) {				
					if (rs.getString(1).equals(nameOftable[i])) {
						rowCount[i]++;
					}
				}		
			}	
			
			
			// loop through all yield tables , get value of each table and put into array table_values[][][]
			for (int i = 0; i < tableCount; i++) {				
				//re-define table dimensions	--->	VERY IMPORTANT CODES
				table_values[i] = new Object[rowCount[i]][];
				for (int row = 0; row < rowCount[i]; row++) {
					table_values[i][row] = new Object[colCount];
				}
			}
				
					
			// get values for each table
			rs = st.executeQuery("SELECT * FROM yield_tables;");
			for (int i = 0; i < tableCount; i++) {
				for (int row = 0; row < rowCount[i]; row++) {
					rs.next();
					for (int col = 0; col < colCount; col++) {
						table_values[i][row][col] = rs.getString(col + 1);
					}
				}
			}							
			
			
			st.close();
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
		
		String tableName_toFind = s5 + s6 + silviculturalMethod + timingChoice + "e";
		tableName_toFind = tableName_toFind.toLowerCase();
		String valueReturn = null;
		
		try {
			int index = Arrays.asList(nameOftable).indexOf(tableName_toFind);
			valueReturn = table_values[index][0][2].toString();			//row 0 is the first period (1sr row), column 2 is "st_age_10"
		} catch (Exception e) {
			valueReturn = "not found";
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + " not found age class from yield table: " + tableName_toFind);
		}
		
		return valueReturn;	
	}
	
	
	
	
	
}
