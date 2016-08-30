package spectrumYieldProject;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Read_DatabaseTables {
	private static Object[][][] table_values;			// Note: indexes start from 0 
	private static Object[] nameOftable;
	
	public static Object[][][] getTableArrays(File file) {
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
		return table_values;
	}
}
