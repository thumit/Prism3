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
package prism_project.edit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import prism_convenience.IconHandle;
import prism_convenience.PrismTableModel;
import prism_convenience.PrismTextAreaReadMe;
import prism_convenience.TableColumnsHandle;
import prism_project.data_process.Read_Database;

public class AreaMerging {
	private Read_Database read_database;
	private Object[][] data10;
	private TableColumnsHandle table_handle;
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private JScrollPane merging_result_scrollpane;
	private PrismTextAreaReadMe ranking_textarea;
	
	private JTable current_table, merging_table, merging_table_with_all_attributes;
	private TableFilterHeader filterHeader;
	
	private Object[][] data11;	// to pass back to save the state_id output
	private String[] columnNames11;	// to pass back to save the state_id output
	
	public AreaMerging(JScrollPane merging_result_scrollpane) {
		this.merging_result_scrollpane = merging_result_scrollpane;
		
		// Set up the filter--------------------------------------------------------------------
		filterHeader = new TableFilterHeader(merging_table, AutoChoices.ENABLED);
		filterHeader.setFilterOnUpdates(true);
		filterHeader.setVisible(false);
	}
	
	public void generate_merging_result(Object[][] data10, Read_Database read_database) {
		this.read_database = read_database;
		this.data10 = data10;
		/*
		 * Logic:
		 * Each attribute have an array to store unique values (values are sorted by double if possible, string otherwise)
		 * 
		 * We are going to use a LinkedHashMap where:
		 * - unique values will serve as key
		 * - the ranks will serve as value
		 * - the ranks could be generated based on user inputs from the "merging requirements" screen 
		 * 
		 * To look for the rank:
		 * - Loop through every row in the yield tables to have the value of the attribute of interest in that row
		 * - The value of the attribute in that row will serve as key in the LinkedHashMap
		 * - In the LinkedHashMap, use the key to get the value (or rank)
		 * 
		 * Finally, create a new column for the attribute with the ranks in that column
		 * Note: we must use LinkedHashMap because original order could be kept. In case using HashMap, we  would lose the original order (the order becomes random)
		 */
		create_ranking_textarea();
		

		String[] yield_tables_column_names = read_database.get_yield_tables_column_names();
		LinkedHashMap<String, String>[] attributes_map = new LinkedHashMap[data10.length];	// array to store all the LinkedHashMap, each for one attribute
		for (int row = 0; row < data10.length; row++) {		// row represents attribute
			LinkedHashMap<String, String> attribute = new LinkedHashMap<String, String>();
			if (String.valueOf(data10[row][8]).equals("true")) { // when implementation == true
				List<String> unique_values_list = read_database.get_col_unique_values_list(row);
				int unique_values_count = unique_values_list.size();
				String merging_method = (String) data10[row][5];
				double relaxed_percentage = (data10[row][6] != null) ? (double) data10[row][6] / 100 : (double) 0;	// null means zero
				double relaxed_number = (data10[row][7] != null) ? (double) data10[row][7] : (double) 0;	// null means zero
				
				if (merging_method.equals("exact")) {
					int current_rank = 0;
					for (int u = 0; u < unique_values_count; u++) {
						current_rank = u;
						attribute.put(unique_values_list.get(u), String.valueOf(current_rank));				// unique_value = key	rank = value
					}
					
					ranking_textarea.append("------------------------------------------------" + "\n");
					ranking_textarea.append(yield_tables_column_names[row] + ": exact" + "\n");
					for (String k : attribute.keySet()) {
						ranking_textarea.append(yield_tables_column_names[row] + " = " + k + "     rank = " + attribute.get(k) + "\n");
					}
					ranking_textarea.append("------------------------------------------------" + "\n");
				} 
				
				else if (merging_method.equals("RP and RN")) {
					int current_rank = 0;
					double[] unique_values = unique_values_list.stream().mapToDouble(num -> Double.parseDouble(num)).toArray();
					double current_min_value = unique_values[0];
					for (int u = 0; u < unique_values_count; u++) {
						if ((unique_values[u] - current_min_value) > relaxed_percentage * current_min_value
							|| (unique_values[u] - current_min_value) > relaxed_number) {	// difference measured by AND:  AND of <=   -->  OR >
							current_rank++;
							current_min_value = unique_values[u];
						}
						attribute.put(unique_values_list.get(u), String.valueOf(current_rank));				// unique_value = key	rank = value
					}
					
					ranking_textarea.append("------------------------------------------------" + "\n");
					ranking_textarea.append(yield_tables_column_names[row] + ": relaxed percentage = " + relaxed_percentage * 100 + "% AND number = " + relaxed_number + "\n");
					for (String k : attribute.keySet()) {
						ranking_textarea.append(yield_tables_column_names[row] + " = " + k + "     rank = " + attribute.get(k) + "\n");
					}
					ranking_textarea.append("------------------------------------------------" + "\n");
				}
				
				else if (merging_method.equals("RP or RN")) {
					int current_rank = 0;
					double[] unique_values = unique_values_list.stream().mapToDouble(num -> Double.parseDouble(num)).toArray();
					double current_min_value = unique_values[0];
					for (int u = 0; u < unique_values_count; u++) {
						if ((unique_values[u] - current_min_value) > relaxed_percentage * current_min_value
							&& (unique_values[u] - current_min_value) > relaxed_number) {	// difference measured by AND:  OR of <=   -->  AND >
							current_rank++;
							current_min_value = unique_values[u];
						}
						attribute.put(unique_values_list.get(u), String.valueOf(current_rank));				// unique_value = key	rank = value
					}
					
					ranking_textarea.append("------------------------------------------------" + "\n");
					ranking_textarea.append(yield_tables_column_names[row] + ": percentage = " + relaxed_percentage * 100 + "% OR number = " + relaxed_number + "\n");
					for (String k : attribute.keySet()) {
						ranking_textarea.append(yield_tables_column_names[row] + " = " + k + "     rank = " + attribute.get(k) + "\n");
					}
					ranking_textarea.append("------------------------------------------------" + "\n");
				}

				else if (merging_method.equals("relaxed percentage (RP)")) {
					int current_rank = 0;
					double[] unique_values = unique_values_list.stream().mapToDouble(num -> Double.parseDouble(num)).toArray();
					double current_min_value = unique_values[0];
					for (int u = 0; u < unique_values_count; u++) {
						if ((unique_values[u] - current_min_value) > relaxed_percentage * current_min_value) {	// keep the current rank and min_value if the difference is within the allowance
							current_rank++;
							current_min_value = unique_values[u];
						}
						attribute.put(unique_values_list.get(u), String.valueOf(current_rank));				// unique_value = key	rank = value
					}
					
					ranking_textarea.append("------------------------------------------------" + "\n");
					ranking_textarea.append(yield_tables_column_names[row] + ": relaxed percentage = " + relaxed_percentage * 100 + "%" + "\n");
					for (String k : attribute.keySet()) {
						ranking_textarea.append(yield_tables_column_names[row] + " = " + k + "     rank = " + attribute.get(k) + "\n");
					}
					ranking_textarea.append("------------------------------------------------" + "\n");
				}
				
				else if (merging_method.equals("relaxed number (RN)")) {
					int current_rank = 0;
					double[] unique_values = unique_values_list.stream().mapToDouble(num -> Double.parseDouble(num)).toArray();
					double current_min_value = unique_values[0];
					for (int u = 0; u < unique_values_count; u++) {
						if ((unique_values[u] - current_min_value) > relaxed_number) {	// keep the current rank and min_value if the difference is within the allowance
							current_rank++;
							current_min_value = unique_values[u];
						}
						attribute.put(unique_values_list.get(u), String.valueOf(current_rank));				// unique_value = key	rank = value
					}
					
					ranking_textarea.append("------------------------------------------------" + "\n");
					ranking_textarea.append(yield_tables_column_names[row] + ": relaxed number = " + relaxed_number + "\n");
					for (String k : attribute.keySet()) {
						ranking_textarea.append(yield_tables_column_names[row] + " = " + k + "     rank = " + attribute.get(k) + "\n");
					}
					ranking_textarea.append("------------------------------------------------" + "\n");
				}
			}
			attributes_map[row] = (LinkedHashMap<String, String>) attribute;
		}	
		
		
		
		// actual value
		String[][][] yield_tables_values = read_database.get_yield_tables_values();
		// integer ranking value
		int prescription_count = yield_tables_values.length;
		String[][][] yield_tables_individual_rank = new String[prescription_count][][];
		String[][] yield_tables_merging_rank = new String[prescription_count][];		// yield_tables_merging_rank in 2D array
		for (int i = 0; i < prescription_count; i++) {
			int prescription_row_count = yield_tables_values[i].length;
			yield_tables_individual_rank[i] = new String[prescription_row_count][]; 
			yield_tables_merging_rank[i] = new String[prescription_row_count];
			for (int row = 0; row < prescription_row_count; row++) {
				int col_count = yield_tables_values[i][row].length;
				yield_tables_individual_rank[i][row] = new String[col_count];
				// Merging all the rank for the attributes with implementation = true
				List<String> state_id = new ArrayList<String>();
				for (int col = 0; col < col_count; col++) {			// col represents attribute, this col is also the row in the data[][] of the "merging requirements" table
					if (String.valueOf(data10[col][8]).equals("true")) { // when implementation == true
						String key = yield_tables_values[i][row][col];
						String rank = attributes_map[col].get(key);
						yield_tables_individual_rank[i][row][col] = rank;
						state_id.add(rank);
					}
				}
				yield_tables_merging_rank[i][row] = String.join("_", state_id);
			}
		}
		
		
		/*
		for (int i = 0; i < prescription_count; i++) {
			int prescription_row_count = yield_tables_values[i].length;
			for (int row = 0; row < prescription_row_count; row++) {
				String prescription = String.valueOf(yield_tables_values[i][row][0]);
				String row_id = yield_tables_values[i][row][1];
				String state_id = yield_tables_merging_rank[i][row];
				System.out.println(String.join("    ", prescription, row_id, state_id));
			}
		}
		System.out.println("------------------------------------------------");
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		// Testing hashmap     must use LinkedHashMap (because original order could be kept) not HashMap (lose the original order, it becomes random)
//		try {			
//			conn = DriverManager.getConnection("jdbc:sqlite:" + read_database.get_file_database());
//			st = conn.createStatement();
//			
//			
//			rs = st.executeQuery("SELECT prescription, row_id FROM yield_tables;");
//			Map<String, Integer> original_order = new LinkedHashMap<String, Integer>();
//			int original_id = 0;	// map starts from 0
//			while (rs.next()) {
//				original_order.put(rs.getString(1) + " " + rs.getString(2), original_id);		// prescription + row_id = key, original_id = value		
//				original_id++;
//			}	
//			
//		
//			
//			
//			
//			rs = st.executeQuery("SELECT prescription, row_id, culmmai FROM yield_tables;");
//			Map<String, String> attribute = new LinkedHashMap<String, String>();
//			while (rs.next()) {
//				attribute.put(rs.getString(1)  + " " +  rs.getString(2), rs.getString(3));	
//			}	
//			
//			List<String> key = new ArrayList<>(attribute.keySet());
////			Collections.sort(key);
//			for (String k : key) {
//				System.out.println(k + " : culmmai = " + attribute.get(k));
//			}
//			
//			
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			String warningText = "Failed querying attribute\n";
//			warningText = warningText + e.getClass().getName() + ": " + e.getMessage();
//			String ExitOption[] = {"OK"};
//			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Database error",
//					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption, ExitOption[0]);
//		} finally {
//			// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
//		    try { rs.close(); } catch (Exception e) { /* ignored */}	
//		    try { st.close(); } catch (Exception e) { /* ignored */}
//		    try { conn.close(); } catch (Exception e) { /* ignored */}
//		}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		create_merging_table(yield_tables_values, yield_tables_merging_rank);
		create_merging_table_with_all_attributes(yield_tables_values, yield_tables_merging_rank);
		table_handle = new TableColumnsHandle(merging_table_with_all_attributes);
		set_function_popup();
		show_ranking_textarea();
	}
	
	
	public void create_ranking_textarea() {
		String message = 
				"For each attribute selected as implementation, its unique values will be ranked based on its merging method\n"
				+ "state_id is the combination of all the ranks from all the selected attributes, separated by underscore\n"
				+ "state_id should be defined by using attributes which represent the forest condition at the very beginning of a planning period (i.e. do not select rmcuft or action_type for implementation) \n"
				+ "state_id will be generated and saved after clicking the calculator button\n"
				+ "After generating state_id, the entire bottom area could be right clicked for more functionality\n"
				+ "For rolling horizon, forest areas would be qualified for merging if they:\n"
				+ "          1. have the same state_id\n"
				+ "          2. are at the same planning period\n"
				+ "          3. have the same forest status (Existing or Regenerated)\n"
				+ "          4. have the same 6 layers (existing strata) or have the same 5 layers (regenerated strata)\n"
				+ "If none of the attribute is selected for implementation, Prism would apply No-Merging\n"
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		ranking_textarea = new PrismTextAreaReadMe("icon_script.png", 1, 1 /*32, 32*/);
		ranking_textarea.append(message);
		ranking_textarea.setSelectionStart(0);	// scroll to top
		ranking_textarea.setSelectionEnd(0);
		ranking_textarea.setEditable(false);
	}
	
	
	private void create_merging_table(String[][][] yield_tables_values, String[][] yield_tables_merging_rank) {
		// Set up table
		int rowCount = 0;
		int prescription_count = yield_tables_merging_rank.length;
		for (int i = 0; i < prescription_count; i++) {
			int prescription_row_count = yield_tables_merging_rank[i].length;
			for (int row = 0; row < prescription_row_count; row++) {
				rowCount++;
			}
		}
		int colCount = 3;
		String[] columnNames = {"prescription", "row_id", "state_id"}; 
		
		int table_row = 0;
		Object[][] data = new Object[rowCount][colCount];
		for (int i = 0; i < prescription_count; i++) {
			int prescription_row_count = yield_tables_merging_rank[i].length;
			for (int row = 0; row < prescription_row_count; row++) {
				data[table_row][0] = yield_tables_values[i][row][0];
				data[table_row][1] = yield_tables_values[i][row][1];
				data[table_row][2] = yield_tables_merging_rank[i][row];
				table_row++;
			}
		}
							
		// create a table
		PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames);
		JTable table = new JTable(model) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(this, tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}
		};
								
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
 		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 		TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model);	// Add sorter
		table.setRowSorter(sorter);
		
		data11 = data;
		columnNames11 = columnNames;
		merging_table = table;
	}
	
	public Object[][] get_data11() {
		return data11;	// to pass back to save the state_id output
	}
	
	public String[] get_columnNames11() {
		return columnNames11;	// to pass back to save the state_id output
	}
	
	
	private void create_merging_table_with_all_attributes(String[][][] yield_tables_values, String[][] yield_tables_merging_rank) {
		// Set up table
		int rowCount = 0;
		int prescription_count = yield_tables_merging_rank.length;
		for (int i = 0; i < prescription_count; i++) {
			int prescription_row_count = yield_tables_merging_rank[i].length;
			for (int row = 0; row < prescription_row_count; row++) {
				rowCount++;
			}
		}
		int colCount = read_database.get_yield_tables_column_names().length + 1;				
		String[] columnNames = new String[colCount];
		for (int col = 0; col < colCount - 1; col++) {
			columnNames[col] = read_database.get_yield_tables_column_names()[col];
		} 
		columnNames[colCount - 1] = "state_id";
		
		int table_row = 0;
		Object[][] data = new Object[rowCount][colCount];
		for (int i = 0; i < prescription_count; i++) {
			int prescription_row_count = yield_tables_merging_rank[i].length;
			for (int row = 0; row < prescription_row_count; row++) {
				for (int col = 0; col < colCount - 1; col++) {
					data[table_row][col] = yield_tables_values[i][row][col];
				}
				data[table_row][colCount - 1] = yield_tables_merging_rank[i][row];
				table_row++;
			}
		}
							
		// create a table
		PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames);
		JTable table = new JTable(model) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(this, tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}
		};
								
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
 		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 		TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model);	// Add sorter
		table.setRowSorter(sorter);
		
		merging_table_with_all_attributes = table;
	}
	
	
	public void show_ranking_textarea() {
		current_table = null;
		filterHeader.setTable(current_table);
		
		TitledBorder border = new TitledBorder("General information and attributes ranking");
		border.setTitleJustification(TitledBorder.CENTER);
		merging_result_scrollpane.setBorder(border);
		merging_result_scrollpane.setViewportView(ranking_textarea);
	}
	
	
	public void show_merging_table() {
//		TitledBorder border = new TitledBorder("Merging Result - same state_id represents same forest condition for merging");
//		border.setTitleJustification(TitledBorder.CENTER);
//		merging_result_scrollpane.setBorder(border);
//		merging_result_scrollpane.setViewportView(merging_table);
//		
//		current_table = merging_table;
//		if (filterHeader.isVisible()) {
//			if (current_table != filterHeader.getTable()) filterHeader.setTable(current_table);
//		} else {
//			filterHeader.setTable(null);
//		}
		
		
		// The disable code above is very good but I want to have 3 tables sharing the same selections so I do not use the above. The above table is however should be used to export as virtual data to use later
		filterHeader.setTable(null);	// this make it faster. Note that filterHeader and table_handle make the process really slow
		
		TitledBorder border = new TitledBorder("Merging Result with state_id only");
		border.setTitleJustification(TitledBorder.CENTER);
		merging_result_scrollpane.setBorder(border);
		merging_result_scrollpane.setViewportView(merging_table_with_all_attributes);
		for (int row = 2; row < data10.length; row++) {		// row represents attribute. We always show prescription and row_id and state_id columns
			table_handle.setColumnVisible(String.valueOf(data10[row][0]), false);
		}
		
		current_table = merging_table_with_all_attributes;
		if (filterHeader.isVisible()) {
			if (current_table != filterHeader.getTable()) filterHeader.setTable(current_table);
		} else {
			filterHeader.setTable(null);
		}
	}
	
	
	public void show_merging_table_with_selected_attributes() {
		filterHeader.setTable(null);	// this make it faster. Note that filterHeader and table_handle make the process really slow
		
		TitledBorder border = new TitledBorder("Merging result with only attributes selected as implementation");
		border.setTitleJustification(TitledBorder.CENTER);
		merging_result_scrollpane.setBorder(border);
		merging_result_scrollpane.setViewportView(merging_table_with_all_attributes);
		for (int row = 2; row < data10.length; row++) {		// row represents attribute. We always show prescription and row_id columns
			if (String.valueOf(data10[row][8]).equals("false")) { // when implementation == false
				table_handle.setColumnVisible(String.valueOf(data10[row][0]), false);
			} else {
				table_handle.setColumnVisible(String.valueOf(data10[row][0]), true);
			}
		}
		
		current_table = merging_table_with_all_attributes;
		if (filterHeader.isVisible()) {
			if (current_table != filterHeader.getTable()) filterHeader.setTable(current_table);
		} else {
			filterHeader.setTable(null);
		}
	}
	
	
	public void show_merging_table_with_all_attributes() {
		filterHeader.setTable(null);	// this make it faster. Note that filterHeader and table_handle make the process really slow
		
		TitledBorder border = new TitledBorder("Merging result with all attributes");
		border.setTitleJustification(TitledBorder.CENTER);
		merging_result_scrollpane.setBorder(border);
		merging_result_scrollpane.setViewportView(merging_table_with_all_attributes);
		for (int row = 0; row < data10.length; row++) {		// row represents attribute. We show all columns here
			table_handle.setColumnVisible(String.valueOf(data10[row][0]), true);
		}
		
		current_table = merging_table_with_all_attributes;
		if (filterHeader.isVisible()) {
			if (current_table != filterHeader.getTable()) filterHeader.setTable(current_table);
		} else {
			filterHeader.setTable(null);
		}
	}
	
	
	public void set_function_popup() {
		JPopupMenu popup = new JPopupMenu();
		
		// show_merging_table
		final JMenuItem show_merging_table_menuitem = new JMenuItem("1. Show merging result with state_id only");
		show_merging_table_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, null));
		show_merging_table_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				show_merging_table();
			}
		});
		popup.add(show_merging_table_menuitem);
		
		// show_merging_table_with_selected_attributes
		final JMenuItem show_merging_table_with_selected_attributes_menuitem = new JMenuItem("2. Show merging result with only attributes selected as implementation");
		show_merging_table_with_selected_attributes_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, null));
		show_merging_table_with_selected_attributes_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				show_merging_table_with_selected_attributes();
			}
		});
		popup.add(show_merging_table_with_selected_attributes_menuitem);		
		
		// show_merging_table_with_all_attributes
		final JMenuItem show_merging_table_with_all_attributes_menuitem = new JMenuItem("3. Show merging result with all attributes");
		show_merging_table_with_all_attributes_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, null));
		show_merging_table_with_all_attributes_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				show_merging_table_with_all_attributes();
			}
		});
		popup.add(show_merging_table_with_all_attributes_menuitem);
		
		// show_ranking_textarea
		final JMenuItem show_ranking_textarea_menuitem = new JMenuItem("4. Show attributes ranking");
		show_ranking_textarea_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, null));
		show_ranking_textarea_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				show_ranking_textarea();
			}
		});
		popup.add(show_ranking_textarea_menuitem);
		
		// Filter
		final JMenuItem filter_menuitem = new JMenuItem("Filter ON/OFF");
		filter_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_filter.png"));
		filter_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (filterHeader.isVisible()) {
					filterHeader.setVisible(false);
				} else {
					if (current_table != filterHeader.getTable()) filterHeader.setTable(current_table);
					filterHeader.setVisible(true);
				}
			}
		});
		popup.add(filter_menuitem);
		
		// Set popup menu on right click
		merging_table.setComponentPopupMenu(popup);
		merging_table_with_all_attributes.setComponentPopupMenu(popup);
		merging_result_scrollpane.setComponentPopupMenu(popup);
		ranking_textarea.setComponentPopupMenu(popup);
	}
}
