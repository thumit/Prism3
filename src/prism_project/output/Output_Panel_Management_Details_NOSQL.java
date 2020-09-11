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

package prism_project.output;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import prism_convenience.IconHandle;
import prism_convenience.MixedRangeCombinationIterable;
import prism_convenience.PrismTableModel;
import prism_convenience.TableColumnsHandle;
import prism_convenience.ToolBarWithBgImage;
import prism_project.data_process.Identifiers_Processing;
import prism_project.data_process.Information_Parameter;
import prism_project.data_process.Information_Variable;
import prism_project.data_process.LinkedList_Databases_Item;
import prism_project.data_process.Read_Database;
import prism_project.data_process.Read_Input;
import prism_project.edit.ScrollPane_DynamicIdentifiers;
import prism_project.edit.ScrollPane_Parameters;
import prism_project.edit.ScrollPane_StaticIdentifiers;
import prism_root.PrismMain;

public class Output_Panel_Management_Details_NOSQL extends JLayeredPane implements ItemListener {
	//table input_09_basic_constraints.txt
	private boolean is_table9_loaded = false;
	private int rowCount9, colCount9;
	private String[] columnNames9;
	private JTable table9;
	private PrismTableModel model9;
	private Object[][] data9;
	
	private boolean is_input_table9_loaded = false;
	private int input_rowCount9, input_colCount9;
	private String[] input_columnNames9;
	private JTable input_table9;
	private PrismTableModel input_model9;
	private Object[][] input_data9;
	
	private Identifiers_Processing identifiers_processing;
	private Read_Input read;
	private int total_Periods;
	private int total_iteration;
	
	private File file_database;
	private Read_Database read_database;
	
	private File currentProjectFolder;
	private String currentRun;
	private JButton NOSQL_link_button;
	
	private ExecutorService executor;
	
	public Output_Panel_Management_Details_NOSQL(ExecutorService executor, File currentProjectFolder, String currentRun, JButton NOSQL_link_button) {
		this.executor = executor;
		this.currentProjectFolder = currentProjectFolder;
		this.currentRun = currentRun;
		this.NOSQL_link_button = NOSQL_link_button;
		
		
		// Some set up ---------------------------------------------------------------------------			
		file_database = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/database.db");
		read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
		if (read_database == null) {
			read_database = new Read_Database(file_database);	// Read the database
			PrismMain.get_databases_linkedlist().update(file_database, read_database);			
			System.out.println(PrismMain.get_databases_linkedlist().size());
			for (LinkedList_Databases_Item rr: PrismMain.get_databases_linkedlist()) {
				System.out.println(rr.file_database.getAbsolutePath() + rr.last_modify);
			}
		}
		
		identifiers_processing = new Identifiers_Processing(read_database);
		read = new Read_Input(read_database);
		read.read_general_inputs(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_01_general_inputs.txt"));
		total_Periods = read.get_total_periods();	
		
		// Identify the total iterations solved
		File[] list_files = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("output_01_general_outputs_");
			}
		});
		total_iteration = list_files.length;
		// End of set up ---------------------------------------------------------------------------			
		
		
		setLayout(new BorderLayout());
		reload_inputs_before_creating_GUI();
		super.add(new Fly_Constraints_GUI(), BorderLayout.CENTER);		
		model9.match_DataType();	// Matching data types after finishing reloads
	}	
	
	//Listeners for this class------------------------------------------------------------------------------------------------------------------------
	public void itemStateChanged(ItemEvent e) {
	}
	
	
	
	
	private void reload_inputs_before_creating_GUI() {		
		// Load tables---------------------------------------------------------------------------------
		File table_file;
		Reload_Table_Info tableLoader;
		
		
		table_file = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/output_05_fly_constraints.txt");
		if (table_file.exists()) {		//Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount9 = tableLoader.get_rowCount();
			colCount9 = tableLoader.get_colCount();
			data9 = tableLoader.get_input_data();
			columnNames9 = tableLoader.get_columnNames();
			is_table9_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: output_05_fly_constraints.txt - New interface is created");
		}		
		
		
		table_file = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_08_basic_constraints.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			input_rowCount9 = tableLoader.get_rowCount();
			input_colCount9 = tableLoader.get_colCount();
			input_data9 = tableLoader.get_input_data();
			input_columnNames9 = tableLoader.get_columnNames();
			is_input_table9_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_08_basic_constraints.txt - New interface is created");
		} 
    }
	
	
	private void create_file_input_05_fly_constraints() {
		File flyConstraintsFile = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/output_05_fly_constraints.txt");	
		if (flyConstraintsFile.exists()) {
			flyConstraintsFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data9 != null && data9.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(flyConstraintsFile))) {
				for (int j = 0; j < columnNames9.length; j++) {
					fileOut.write(columnNames9[j] + "\t");
				}

				for (int i = 0; i < data9.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount9; j++) {
						fileOut.write(data9[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}
	
	
	// Class to reload all table------------------------------------------------------------------------------------------------ 
	private class Reload_Table_Info {
		private int input_colCount;
		private int input_rowCount;
		private Object[][] input_data;
		private String[] input_columnNames;
   	
		private Reload_Table_Info(File table_file) {
			//Load table to get its 4 attributes
			try {
				String delimited = "\t";		// tab delimited
				List<String> list;
				list = Files.readAllLines(Paths.get(table_file.getAbsolutePath()), StandardCharsets.UTF_8);			
				String[] a = list.toArray(new String[list.size()]);					
										
				//Setup the table---------------------------------
				input_columnNames = a[0].split(delimited);		//tab delimited		//Read the first row	
				input_rowCount = a.length - 1;  // - 1st row which is the column name
				input_colCount = input_columnNames.length;
				input_data = new Object[input_rowCount][input_colCount];
			
				
				// Populate the input_data matrix-----------------
				for (int row = 0; row < input_rowCount; row++) {
					String[] rowValue = a[row + 1].split(delimited);	//tab delimited	
					for (int col = 0; col < input_colCount; col++) {
						input_data[row][col] = rowValue[col];
					}	
				}	
				
			} catch (IOException e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage() + " - Cannot load. New interface is created");
			}
		}

		private int get_colCount() {
			return input_colCount;
		}
		
		private int get_rowCount() {
			return input_rowCount;
		}
		
		private Object[][] get_input_data() {
			return input_data;
		}
		
		private String[] get_columnNames() {
			return input_columnNames;
		}
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	private void create_table9() {
		class comboBox_constraint_type extends JComboBox {	
			public comboBox_constraint_type() {
				addItem("SOFT");
				addItem("HARD");
				addItem("FREE");
				setSelectedIndex(0);
			}
		}
		
			
		//Setup the table------------------------------------------------------------	
		if (is_table9_loaded == false) { // Create a fresh new if Load fail				
			rowCount9 = 0;
			colCount9 = 12 + total_iteration;
			data9 = new Object[rowCount9][colCount9];
			
			String[] name1 = new String[] {"query_id", "query_description", "query_type",  "query_multiplier", "lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty", "parameter_index", "static_identifiers", "dynamic_identifiers", "original_dynamic_identifiers"};	         				
			String[] name2 = new String[total_iteration];
			for (int i = 0; i < total_iteration; i++) {
				name2[i] = "value_iteration_" + i;
			}
			columnNames9 = Stream.concat(Arrays.stream(name1), Arrays.stream(name2)).toArray(String[]::new);
		}
					
		
		//Create a table-------------------------------------------------------------		
		model9 = new PrismTableModel(rowCount9, colCount9, data9, columnNames9) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;      //column 0 accepts only Integer
				else if (c >= 3 && c <= 7) return Double.class;      //column 3 to 7 accept only Double values  
				else if (c >= 12) return Double.class;      //column 12 accept only Double values 
				else return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col >= colCount9 - 5 - total_iteration) { //  The first and the last 5 columns are un-editable
					return false;
				} else {
					return true;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				if (value != null && (col >= 3 && col <= 7) && ((Number) value).doubleValue() < 0) {		// allow null to be set, and not allow negative numbers
    				JOptionPane.showMessageDialog(PrismMain.get_Prism_DesktopPane(),
    						"Your input has not been accepted. Only null or positive values are allowed");
    			} else {
    				data9[row][col] = value;
    				if (col == 3) {
    					for (int iter_col = 12; iter_col < colCount9; iter_col++) {
    						data9[row][iter_col] = null;
    					}
    					fireTableDataChanged();		// When constraint multiplier change then this would register the change and make the selection disappear
    					table9.setRowSelectionInterval(table9.convertRowIndexToView(row), table9.convertRowIndexToView(row));			// select the row again
    				}
    			}
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount9; row++) {
					for (int col = 0; col < colCount9; col++) {
						if (String.valueOf(data9[row][col]).equals("null")) {
							data9[row][col] = null;
						} else {					
							if (col == 0) {			//Column 0 is Integer
								try {
									data9[row][col] = Integer.valueOf(String.valueOf(data9[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table9");
								}	
							} else if ((col >= 3 && col <= 7) || col >= 12) {			//Column 3 to 7 and column 12 are Double
								try {
									data9[row][col] = Double.valueOf(String.valueOf(data9[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table9");
								}
							} else {	//All other columns are String
								data9[row][col] = String.valueOf(data9[row][col]);
							}
						}	
					}	
				}	
			}
		}; 
		
		
		
		table9 = new JTable(model9) {
			@Override		// These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cell width								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column header width
				TableCellRenderer renderer2 = table9.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table9,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				if (column != 1) {
					tableColumn.setPreferredWidth(maxWidth);
				} else {
					tableColumn.setMinWidth(140);
				}
				return component;
			}
			
			@Override	// Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int row = rowAtPoint(p);
				int col = columnAtPoint(p);
				if (table9.getColumnName(col).equals("query_description")) {
					try {
						tip = getValueAt(row, col).toString();
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}
				return tip;
			}	
		};

		// set accuracy and alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {
				// show value with max 10 digits after the dot if it is double value
				DecimalFormat formatter = new DecimalFormat("###,###,###.#");
				formatter.setMinimumFractionDigits(1);
				formatter.setMaximumFractionDigits(1);
				if (value instanceof Double) {
					value = formatter.format((Number) value);
				}
				// set alignment RIGHT
				setHorizontalAlignment(SwingConstants.RIGHT);
				
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };						
			
		for (int i = 0; i < columnNames9.length; i++) {
			if (table9.getColumnName(i).startsWith("value_iteartion_")) {	// this is query_value column
        		table9.getColumnModel().getColumn(i).setCellRenderer(r);
        	} 
		}	
    

        // Set up Type for each column 2
		table9.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_constraint_type()));
		
		// Hide columns
		TableColumnsHandle table_handle = new TableColumnsHandle(table9);
		table_handle.setColumnVisible("query_type", false);
		table_handle.setColumnVisible("lowerbound", false);
		table_handle.setColumnVisible("lowerbound_perunit_penalty", false);
		table_handle.setColumnVisible("upperbound", false);
		table_handle.setColumnVisible("upperbound_perunit_penalty", false);
		table_handle.setColumnVisible("parameter_index", false);
		table_handle.setColumnVisible("static_identifiers", false);
		table_handle.setColumnVisible("dynamic_identifiers", false);
		table_handle.setColumnVisible("original_dynamic_identifiers", false);
		
         
		table9.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table9.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table9.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table9.setPreferredScrollableViewportSize(new Dimension(200, 100));
//		table9.setFillsViewportHeight(true);
	}
	
	
	
	
	// Panel Fly Constraints--------------------------------------------------------------------------------------------------------
	private class Fly_Constraints_GUI extends JLayeredPane {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		List<List<JCheckBox>> checkboxDynamicIdentifiers;
		List<JCheckBox> allDynamicIdentifiers;
		List<JScrollPane> allDynamicIdentifiers_ScrollPane;
		List<JCheckBox> checkboxParameters;
		
		ScrollPane_Parameters parameters_scrollpane;
		ScrollPane_StaticIdentifiers static_identifiers_scrollpane;
		ScrollPane_DynamicIdentifiers dynamic_identifiers_scrollpane;
		JPanel button_table_panel;
		
		Panel_QuickEdit_FlyConstraints quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Fly_Constraints_GUI() {
			setLayout(new BorderLayout());
			
			// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
			String panel_name = "Static Identifiers  -  use model attributes to filter variables";
			static_identifiers_scrollpane = new ScrollPane_StaticIdentifiers(read_database, 2, panel_name);
			checkboxStaticIdentifiers = static_identifiers_scrollpane.get_CheckboxStaticIdentifiers();		
					
			// Update GUI for time period 
	    	for (int j = 0; j < checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).size(); j++) {			//The last element is Time period			
				if (j < total_Periods) {
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(true);		//Periods to be visible 			
				} else {
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(false);		//Periods to be invisible
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setSelected(false);		//Periods to be unselected
				}
			} 
			// End of 1st grid -----------------------------------------------------------------------

			
			// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
			dynamic_identifiers_scrollpane = new ScrollPane_DynamicIdentifiers(read_database);
			checkboxDynamicIdentifiers = dynamic_identifiers_scrollpane.get_checkboxDynamicIdentifiers();
			allDynamicIdentifiers = dynamic_identifiers_scrollpane.get_allDynamicIdentifiers();
			allDynamicIdentifiers_ScrollPane = dynamic_identifiers_scrollpane.get_allDynamicIdentifiers_ScrollPane();
			dynamic_identifiers_scrollpane.setPreferredSize(new Dimension(0, 250));
			// End of 2nd Grid -----------------------------------------------------------------------
				
					
			// 3rd grid ------------------------------------------------------------------------------		// Parameters
			parameters_scrollpane = new ScrollPane_Parameters(read_database);
			checkboxParameters = parameters_scrollpane.get_checkboxParameters();
			parameters_scrollpane.setBorder(BorderFactory.createTitledBorder(null, "Parameters", TitledBorder.CENTER, 0));
	    	parameters_scrollpane.setPreferredSize(new Dimension(200, 100));
			// End of 3rd grid -----------------------------------------------------------------------
			
	    	

			// 4th Grid ------------------------------------------------------------------------------		// Buttons	
			// 4th Grid -----------------------------------------------------------------------------
			// Add all buttons to a Panel----------------------------------
			button_table_panel = new JPanel(new GridBagLayout());
			button_table_panel.setBorder(BorderFactory.createTitledBorder(null, "Optimal Solution Queries", TitledBorder.CENTER, 0));
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.insets = new Insets(0, 5, 3, 10); // padding top 0, left 5, bottom 3, right 10
			
			
			JButton btn_NewSingle = new JButton();
			btn_NewSingle.setFont(new Font(null, Font.BOLD, 12));
//			btn_NewSingle.setText("NEW SET");
			btn_NewSingle.setToolTipText("New");
			btn_NewSingle.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_add.png"));
			btn_NewSingle.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_add.png"));
			btn_NewSingle.setContentAreaFilled(false);
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_NewSingle, c2);		
			

			JButton btn_New_Multiple = new JButton();
			btn_New_Multiple.setFont(new Font(null, Font.BOLD, 12));
//			btn_New_Multiple.setText("NEW MULTIPLE");
			btn_New_Multiple.setToolTipText("New multiple");
			btn_New_Multiple.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_add3.png"));				
			btn_New_Multiple.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_add3.png"));
			btn_New_Multiple.setContentAreaFilled(false);
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_New_Multiple, c2);
			
			
			JButton btn_Import_basic_Constraints = new JButton();
			btn_Import_basic_Constraints.setFont(new Font(null, Font.BOLD, 12));
//			btn_Import_basic_Constraints.setText("IMPORT");
			btn_Import_basic_Constraints.setToolTipText("Import Basic Constraints");
			btn_Import_basic_Constraints.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_add4.png"));	
			btn_Import_basic_Constraints.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_add4.png"));
			btn_Import_basic_Constraints.setContentAreaFilled(false);
			c2.gridx = 0;
			c2.gridy = 2;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_Import_basic_Constraints, c2);
			
			
			// Add Spinner to move priority up or down
			JSpinner spin_move_rows = new JSpinner (new SpinnerNumberModel(1, 0, 2, 1));
			spin_move_rows.setToolTipText("Move");
			JFormattedTextField SpinnerText = ((DefaultEditor) spin_move_rows.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			SpinnerText.setEditable(false);
			SpinnerText.setFocusable(false);
//			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
//		    formatter.setCommitsOnValidEdit(true);
			spin_move_rows.setEnabled(false);
		    c2.gridx = 0;
			c2.gridy = 3;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(spin_move_rows, c2);
			
			
			JButton btn_Edit = new JButton();
//			btn_Edit.setText("EDIT");
			btn_Edit.setToolTipText("Modify");
			btn_Edit.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_swap.png"));
			btn_Edit.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_swap.png"));
			btn_Edit.setContentAreaFilled(false);
			btn_Edit.setEnabled(false);	
			c2.gridx = 0;
			c2.gridy = 4;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_Edit, c2);
		    

			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 12));
//			btn_Delete.setText("DELETE");
			btn_Delete.setToolTipText("Delete");
			btn_Delete.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_erase.png"));
			btn_Delete.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_erase.png"));
			btn_Delete.setContentAreaFilled(false);
			btn_Delete.setEnabled(false);
			c2.gridx = 0;
			c2.gridy = 5;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_Delete, c2);
			
			
			JToggleButton btn_Sort = new JToggleButton();
			btn_Sort.setSelected(false);
			btn_Sort.setFocusPainted(false);
			btn_Sort.setFont(new Font(null, Font.BOLD, 12));
			btn_Sort.setText("OFF");
			btn_Sort.setToolTipText("Filter and Sorter");
			btn_Sort.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_sort.png"));	
			btn_Sort.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_sort.png"));
			btn_Sort.setContentAreaFilled(false);
			c2.gridx = 0;
			c2.gridy = 6;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_Sort, c2);
			
			
			JButton btn_GetResult = new JButton() {
				public Point getToolTipLocation(MouseEvent event) {
					return new Point(getWidth() - 10, 8);
				}
			};
			btn_GetResult.setFont(new Font(null, Font.BOLD, 12));
//			btn_GetResult.setText("Get Result");
			btn_GetResult.setToolTipText("Calculate & Save");
			btn_GetResult.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_calculator.png"));
			btn_GetResult.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_calculator.png"));
			btn_GetResult.setContentAreaFilled(false);
			btn_GetResult.setEnabled(false);
			c2.gridx = 0;
			c2.gridy = 7;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_GetResult, c2);
			
			
			JButton btn_Clear = new JButton();
			btn_Clear.setFont(new Font(null, Font.BOLD, 12));
//			btn_Clear.setText("Clear values");
			btn_Clear.setToolTipText("Filter and Sorter");
			btn_Clear.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_sweep.png"));	
			btn_Clear.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_sweep.png"));
			btn_Clear.setContentAreaFilled(false);
			btn_Clear.setEnabled(false);
			c2.gridx = 0;
			c2.gridy = 8;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_Clear, c2);
			
			
			JButton btn_Save = new JButton() {
				public Point getToolTipLocation(MouseEvent event) {
					return new Point(getWidth() - 10, 5);
				}
			};
			btn_Save.setFont(new Font(null, Font.BOLD, 14));
//			btn_Save.setText("Save");
			btn_Save.setToolTipText("Save");
			btn_Save.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_save.png"));
			btn_Save.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_save.png"));
			btn_Save.setContentAreaFilled(false);
			c2.gridx = 0;
			c2.gridy = 9;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_panel.add(btn_Save, c2);
			
			
			c2.insets = new Insets(0, 0, 0, 0); // No padding
			// Add Empty Label to make all buttons on top not middle
			c2.gridx = 0;
			c2.gridy = 10;
			c2.weightx = 0;
			c2.weighty = 1;
			button_table_panel.add(new JLabel(), c2);
			
			// Add table9				
			create_table9();
			JScrollPane table_ScrollPane = new JScrollPane(table9);	
			c2.gridx = 1;
			c2.gridy = 0;
			c2.weightx = 1;
			c2.weighty = 1;
			c2.gridheight = 11;
			button_table_panel.add(table_ScrollPane, c2);
			// End of 4th Grid -----------------------------------------------------------------------
			// End of 4th Grid -----------------------------------------------------------------------	
			
			
			
			// Add Listeners for table9 & buttons----------------------------------------------------------
			// Add Listeners for table9 & buttons----------------------------------------------------------
			
			// table9
			table9.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
				@Override
				public void mouseReleased(MouseEvent e) {
					int[] selectedRow = table9.getSelectedRows();
					if (selectedRow.length == 1) {		// Reload Constraint & Enable Edit	when: 1 row is selected and no cell is editing
						int currentRow = selectedRow[0];
						currentRow = table9.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiers_scrollpane.reload_this_constraint_static_identifiers((String) data9[currentRow][9]);	// 9 is the static_identifiers which have some attributes selected				
						dynamic_identifiers_scrollpane.reload_this_constraint_dynamic_identifiers((String) data9[currentRow][10], (String) data9[currentRow][11]);	// 11 is the original_dynamic_identifiers column
						parameters_scrollpane.reload_this_constraint_parameters((String) data9[currentRow][8]);	// 8 is the selected parameters of this constraint
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table9.isEnabled()) {		// Enable Delete & GetResult when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
						btn_GetResult.setEnabled(true);
						btn_Clear.setEnabled(true);
					} else {		// Disable Delete & GetResult
						btn_Delete.setEnabled(false);
						btn_GetResult.setEnabled(false);
						btn_Clear.setEnabled(false);
					}		
					
					if (selectedRow.length >= 1 && btn_Sort.getText().equals("OFF")) {	// Enable Spinner when: >=1 row is selected and Sorter is off
						spin_move_rows.setEnabled(true);
					} else {		// Disable Spinner
						spin_move_rows.setEnabled(false);
					}
				}
			});
			
			table9.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selectedRow = table9.getSelectedRows();
					if (selectedRow.length == 1) {		// Enable Edit	when: 1 row is selected and no cell is editing
						int currentRow = selectedRow[0];
						currentRow = table9.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiers_scrollpane.reload_this_constraint_static_identifiers((String) data9[currentRow][9]);	// 9 is the static_identifiers which have some attributes selected				
						dynamic_identifiers_scrollpane.reload_this_constraint_dynamic_identifiers((String) data9[currentRow][10], (String) data9[currentRow][11]);	// 11 is the original_dynamic_identifiers column
						parameters_scrollpane.reload_this_constraint_parameters((String) data9[currentRow][8]);	// 8 is the selected parameters of this constraint
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table9.isEnabled()) {		// Enable Delete & GetResult when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
						btn_GetResult.setEnabled(true);
						btn_Clear.setEnabled(true);
					} else {		// Disable Delete & GetResult
						btn_Delete.setEnabled(false);
						btn_GetResult.setEnabled(false);
						btn_Clear.setEnabled(false);
					}	
					
					if (selectedRow.length >= 1 && btn_Sort.getText().equals("OFF")) {	// Enable Spinner when: >=1 row is selected and Sorter is off
						spin_move_rows.setEnabled(true);
					} else {		// Disable Spinner
						spin_move_rows.setEnabled(false);
					}
		        }
		    });
			
			

			TableFilterHeader filterHeader = new TableFilterHeader(null, AutoChoices.ENABLED);
			// New single
			btn_NewSingle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					if (table9.isEditing()) {
						table9.getCellEditor().cancelCellEditing();
					}
					filterHeader.resetFilter();
					
					// Add 1 row
					rowCount9++;
					data9 = new Object[rowCount9][colCount9];
					for (int ii = 0; ii < rowCount9 - 1; ii++) {
						for (int jj = 0; jj < colCount9; jj++) {
							data9[ii][jj] = model9.getValueAt(ii, jj);
						}	
					}
						
					data9[rowCount9 - 1][1] = String.join(" ..... ",
							parameters_scrollpane.get_parameters_description_from_GUI(),
							dynamic_identifiers_scrollpane.get_dynamic_description_from_GUI(),
							static_identifiers_scrollpane.get_static_description_from_GUI());
					data9[rowCount9 - 1][3] = (double) 1;
					data9[rowCount9 - 1][8] = parameters_scrollpane.get_parameters_info_from_GUI();
					data9[rowCount9 - 1][9] = static_identifiers_scrollpane.get_static_info_from_GUI();
					data9[rowCount9 - 1][10] = dynamic_identifiers_scrollpane.get_dynamic_info_from_GUI();
					data9[rowCount9 - 1][11] = dynamic_identifiers_scrollpane.get_original_dynamic_info_from_GUI();
					
					model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
					update_id();
					model9.fireTableDataChanged();
					quick_edit = new Panel_QuickEdit_FlyConstraints(table9, data9);		// 2 lines to update data for Quick Edit Panel
		 			scrollpane_QuickEdit.setViewportView(quick_edit);
					
					// Convert the new Row to model view and then select it 
					int newRow = table9.convertRowIndexToView(rowCount9 - 1);
					table9.setRowSelectionInterval(newRow, newRow);
					table9.scrollRectToVisible(new Rectangle(table9.getCellRect(newRow, 0, true)));
				}
			});
			
			
			// New Multiple
			btn_New_Multiple.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table9.isEditing()) {
						table9.getCellEditor().cancelCellEditing();
					}
					filterHeader.resetFilter();
					
					ScrollPane_ConstraintsSplitFly constraint_split_ScrollPanel = new ScrollPane_ConstraintsSplitFly(
							static_identifiers_scrollpane.get_static_layer_title_as_checkboxes(),
							parameters_scrollpane.get_checkboxParameters(),
							dynamic_identifiers_scrollpane.get_allDynamicIdentifiers());

					
					
					String ExitOption[] = {"Add Queries","Cancel"};
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), constraint_split_ScrollPanel, "Create multiple queries - checked items will be split",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);
					if (response == 0) {	// Add Constraints
						// calculate parameter_permutation
						int total_parameter_permutation = 1;
						if (constraint_split_ScrollPanel.is_parameters_split()) { 	//if parameters would be split
							int total_checked_elements = 0;
							for (int i = 0; i < checkboxParameters.size(); i++) {
								if (checkboxParameters.get(i).isSelected() && checkboxParameters.get(i).isVisible()) {	// If this parameter is checked		
									total_checked_elements ++;	// Increase number of constraints
								}
							}
							if (total_checked_elements == 0) {	// This is the case when "NoParameter" or "CostParameter" or nothing is checked
								total_parameter_permutation = 1;
							} else {
								total_parameter_permutation = total_parameter_permutation * total_checked_elements;
							}
						}
						
						// calculate dynamic_permutation
						int total_dynamic_permutation = 1;
						List<Integer> dynamic_split_id = new ArrayList<Integer>();	// id of dynamic attribute to be split
						List<String> dynamic_split_name = constraint_split_ScrollPanel.get_dynamic_split_name();	// name of dynamic attribute to be split
						for (String name : dynamic_split_name) {
							// Find the index
							int i = 0;
							for (int cb_id = 0; cb_id < allDynamicIdentifiers.size(); cb_id++) {
								if (allDynamicIdentifiers.get(cb_id).getText().equals(name)) {
									i = cb_id;
									dynamic_split_id.add(i);
									break;
								}
							}
							int total_checked_elements = 0;
							for (int j = 0; j < checkboxDynamicIdentifiers.get(i).size(); j++) {
								if (checkboxDynamicIdentifiers.get(i).get(j).isSelected() && checkboxDynamicIdentifiers.get(i).get(j).isVisible()) {	// If element of this dynamic is checked		
									total_checked_elements ++;	// Increase number of constraints
								}
							}
							total_dynamic_permutation = total_dynamic_permutation * total_checked_elements;
						}
						
						// calculate static_permutation
						int total_static_permutation = 1;
						List<Integer> static_split_id = constraint_split_ScrollPanel.get_static_split_id();	// id of static layers to be split
						for (int i : static_split_id) {
							int total_checked_elements = 0;
							for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
								if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
									total_checked_elements ++;	// Increase number of constraints
								}
							}
							total_static_permutation = total_static_permutation * total_checked_elements;
						}										
						
						// calculate total number of constraints
						int total_constraints = total_parameter_permutation * total_dynamic_permutation * total_static_permutation;
						System.out.println(total_constraints);
						
						
						// Ask to confirm adding if there are more than 1000 constraints
						int response2 = 0;	
						if (total_constraints > 10000) {
							String ExitOption2[] = {"Yes","No"};
							String warningText = "You are going to add " + total_constraints + " constraints. It would take some time. Continue to add ?";
							response2 = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Confirm adding constraints",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption2, ExitOption2[1]);
							
						}
							
						if (response2 == 0) {	
							constraint_split_ScrollPanel.stop_editing();
							/* 
							Example: 
							List<List<String>> lists = new ArrayList<List<String>>();
							lists.add(Arrays.asList(new String[] { "lay1 a", "lay1 b", "lay1 c" }));
							lists.add(Arrays.asList(new String[] { "lay2 c d e" }));
							lists.add(Arrays.asList(new String[] { "lay3 f", "lay3 g" }));
							*/
							
							
							// for parameters------------------------------------------------------------------------------------------------------
							List<String> parameter_info_list = new ArrayList<String>();
							if (constraint_split_ScrollPanel.is_parameters_split()) { 	//if parameters would be split
								for (int i = 0; i < checkboxParameters.size(); i++) {
									if (checkboxParameters.get(i).isSelected() && checkboxParameters.get(i).isVisible()) {	// If this parameter is checked		
										parameter_info_list.add(String.valueOf(i));
									}
								}
							}
							
							
							// for dynamic identifiers------------------------------------------------------------------------------------------------------
							List<List<String>> dynamic_iterable_lists = new ArrayList<List<String>>();
							for (int i = 0; i < checkboxDynamicIdentifiers.size(); i++) {
								if (allDynamicIdentifiers_ScrollPane.get(i).isVisible()) {
									List<String> joined_string_list = new ArrayList<String>();
									if (dynamic_split_id.contains(i)) {	// if this dynamic layer must be split
										for (int j = 0; j < checkboxDynamicIdentifiers.get(i).size(); j++) {
											if (checkboxDynamicIdentifiers.get(i).get(j).isSelected() && checkboxDynamicIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
												List<String> temp = new ArrayList<String>(); 
												temp.add(String.valueOf(i));
												temp.add(checkboxDynamicIdentifiers.get(i).get(j).getText());	
												joined_string_list.add(String.join(" ", temp));
											}	
										}
										dynamic_iterable_lists.add(joined_string_list);
									} else {		// if this dynamic layer would not be split
										List<String> temp = new ArrayList<String>(); 
										temp.add(String.valueOf(i));
										for (int j = 0; j < checkboxDynamicIdentifiers.get(i).size(); j++) {
											if (checkboxDynamicIdentifiers.get(i).get(j).isSelected() && checkboxDynamicIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
												temp.add(checkboxDynamicIdentifiers.get(i).get(j).getText());	
											}	
										}
										joined_string_list.add(String.join(" ", temp));
										dynamic_iterable_lists.add(joined_string_list);
									}
								}
							}		
							// Create lists
							List<String> dynamic_info_list = new ArrayList<String>();
							// Get all permutation
							MixedRangeCombinationIterable<String> dynamic_iterable = new MixedRangeCombinationIterable<String>(dynamic_iterable_lists);
							for (List<String> element : dynamic_iterable) {
								String joined_string = String.join(";", element);
								dynamic_info_list.add(joined_string);
							}
							
							
							// for static identifiers------------------------------------------------------------------------------------------------------
							List<List<String>> static_iterable_lists = new ArrayList<List<String>>();
							for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
								List<String> joined_string_list = new ArrayList<String>();
								if (static_split_id.contains(i)) {	// if this static layer must be split
									for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
										if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
											List<String> temp = new ArrayList<String>(); 
											temp.add(String.valueOf(i));
											temp.add(checkboxStaticIdentifiers.get(i).get(j).getText());	
											joined_string_list.add(String.join(" ", temp));
										}	
									}
									
									static_iterable_lists.add(joined_string_list);
								} else {		// if this static layer would not be split
									List<String> temp = new ArrayList<String>(); 
									temp.add(String.valueOf(i));
									for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
										if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
											temp.add(checkboxStaticIdentifiers.get(i).get(j).getText());	
										}	
									}
									joined_string_list.add(String.join(" ", temp));
									
									static_iterable_lists.add(joined_string_list);
								}	
							}		
							// Create lists
							List<String> static_info_list = new ArrayList<String>();
							// Get all permutation
							MixedRangeCombinationIterable<String> static_iterable = new MixedRangeCombinationIterable<String>(static_iterable_lists);
							for (List<String> element : static_iterable) {
								String joined_string = String.join(";", element);
								static_info_list.add(joined_string);
							}
							
							
							
							
							// Pre descriptions
							List<Integer> static_descriptor_id = constraint_split_ScrollPanel.get_static_descriptor_id();	// id of static layers to be in description
							List<Integer> dynamic_descriptor_id = new ArrayList<Integer>();	// id of dynamic attribute to be descriptor (not the real yield table column id)
							List<String> dynamic_descriptor_name = constraint_split_ScrollPanel.get_dynamic_descriptor_name();	// name of dynamic attribute to be descriptor
							for (String name : dynamic_descriptor_name) {
								// Find the index
								int i = 0;
								for (int cb_id = 0; cb_id < allDynamicIdentifiers.size(); cb_id++) {
									if (allDynamicIdentifiers.get(cb_id).getText().equals(name)) {
										i = cb_id;
										dynamic_descriptor_id.add(i);
										break;
									}
								}
							}
							
							
							// for parameter description------------------------------------------------------------------------------------------------------
							List<String> parameter_description_info_list = new ArrayList<String>();
							if (parameters_scrollpane.get_checkboxNoParameter().isSelected()) {
								if (constraint_split_ScrollPanel.is_parameters_descriptor()) {
									parameter_description_info_list.add("Area");
								} else {
									parameter_description_info_list.add("");
								}
							} else if (parameters_scrollpane.get_checkboxCostParameter().isSelected()) {
								if (constraint_split_ScrollPanel.is_parameters_descriptor()) {
									parameter_description_info_list.add("Cost");
								} else {
									parameter_description_info_list.add("");
								}
							} else {
								if (constraint_split_ScrollPanel.is_parameters_split()) { 	//if parameters would be split
									for (int i = 0; i < checkboxParameters.size(); i++) {
										if (checkboxParameters.get(i).isSelected() && checkboxParameters.get(i).isVisible()) {	// If this parameter is checked	
											if (constraint_split_ScrollPanel.is_parameters_descriptor()) {
												parameter_description_info_list.add(checkboxParameters.get(i).getText());
											} else {
												parameter_description_info_list.add("");
											}
										}
									}
								} else {
									if (constraint_split_ScrollPanel.is_parameters_descriptor()) {
										List<String> temp = new ArrayList<String>(); 
										for (int i = 0; i < checkboxParameters.size(); i++) {
											if (checkboxParameters.get(i).isSelected() && checkboxParameters.get(i).isVisible()) {	// If this parameter is checked	
												temp.add(checkboxParameters.get(i).getText());
											}
										}
										parameter_description_info_list.add(String.join(" + ", temp));
									} else {
										parameter_description_info_list.add("");
									}
								}
							}
							
							
							// for dynamic description------------------------------------------------------------------------------------------------------
							List<List<String>> dynamic_description_iterable_lists = new ArrayList<List<String>>();
							for (int i = 0; i < checkboxDynamicIdentifiers.size(); i++) {
								List<String> joined_string_list = new ArrayList<String>();
								if (dynamic_split_id.contains(i)) {	// if this dynamic layer must be split
									for (int j = 0; j < checkboxDynamicIdentifiers.get(i).size(); j++) {
										if (checkboxDynamicIdentifiers.get(i).get(j).isSelected() && checkboxDynamicIdentifiers.get(i).get(j).isVisible()) {	// If element of this dynamic is checked		
											if (dynamic_descriptor_id.contains(i)) {
												List<String> temp = new ArrayList<String>(); 
												temp.add(allDynamicIdentifiers.get(i).getText());
												temp.add(checkboxDynamicIdentifiers.get(i).get(j).getText());	
												joined_string_list.add(String.join(" ", temp));
											} else {
												joined_string_list.add("");
											}
										}	
									}
									dynamic_description_iterable_lists.add(joined_string_list);
								} else {		// if this dynamic layer would not be split
									if (dynamic_descriptor_id.contains(i)) {
										List<String> temp = new ArrayList<String>(); 
										temp.add(allDynamicIdentifiers.get(i).getText());
										for (int j = 0; j < checkboxDynamicIdentifiers.get(i).size(); j++) {
											if (checkboxDynamicIdentifiers.get(i).get(j).isSelected() && checkboxDynamicIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
												temp.add(checkboxDynamicIdentifiers.get(i).get(j).getText());	
											}	
										}
										joined_string_list.add(String.join(" ", temp));
									} else {
										joined_string_list.add("");
									}
									dynamic_description_iterable_lists.add(joined_string_list);
								}
							}	
							// Create lists
							List<String> dynamic_description_info_list = new ArrayList<String>();
							// Get all permutation
							MixedRangeCombinationIterable<String> dynamic_description_iterable = new MixedRangeCombinationIterable<String>(dynamic_description_iterable_lists);
							for (List<String> element : dynamic_description_iterable) {
								List<String> temp_list = new ArrayList<String>();
								for (String st : element) {
									if (!st.equals("")) temp_list.add(st);
								}
								String joined_string = String.join(" | ", temp_list);
								dynamic_description_info_list.add(joined_string);
							}
							
							
							// for static description------------------------------------------------------------------------------------------------------
							List<List<String>> static_description_iterable_lists = new ArrayList<List<String>>();
							for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
								List<String> joined_string_list = new ArrayList<String>();
								if (static_split_id.contains(i)) {	// if this static layer must be split
									for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
										if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
											if (static_descriptor_id.contains(i)) {
												List<String> temp = new ArrayList<String>(); 
												temp.add(static_identifiers_scrollpane.get_static_layer_title_as_checkboxes().get(i).getText());
												temp.add(checkboxStaticIdentifiers.get(i).get(j).getText());	
												joined_string_list.add(String.join(" ", temp));
											} else {
												joined_string_list.add("");
											}
										}	
									}
									static_description_iterable_lists.add(joined_string_list);
								} else {		// if this static layer would not be split
									if (static_descriptor_id.contains(i)) {
										List<String> temp = new ArrayList<String>(); 
										temp.add(static_identifiers_scrollpane.get_static_layer_title_as_checkboxes().get(i).getText());
										for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
											if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked			
												temp.add(checkboxStaticIdentifiers.get(i).get(j).getText());	
											}	
										}
										joined_string_list.add(String.join(" ", temp));
									} else {
										joined_string_list.add("");
									}
									static_description_iterable_lists.add(joined_string_list);
								}
							}	
							// Create lists
							List<String> static_description_info_list = new ArrayList<String>();
							// Get all permutation
							MixedRangeCombinationIterable<String> description_iterable = new MixedRangeCombinationIterable<String>(static_description_iterable_lists);
							for (List<String> element : description_iterable) {
								List<String> temp_list = new ArrayList<String>();
								for (String st : element) {
									if (!st.equals("")) temp_list.add(st);
								}
								String joined_string = String.join(" | ", temp_list);
								static_description_info_list.add(joined_string);
							}
							
							
							
							
							// Combine everything together to create the final lists
							if (parameter_info_list.isEmpty()) parameter_info_list.add(parameters_scrollpane.get_parameters_info_from_GUI());
							if (dynamic_info_list.isEmpty()) dynamic_info_list.add(dynamic_identifiers_scrollpane.get_dynamic_info_from_GUI());
							if (static_info_list.isEmpty()) static_info_list.add(static_identifiers_scrollpane.get_static_info_from_GUI());
//							if (parameter_description_info_list.isEmpty()) parameter_description_info_list.add(parametersScrollPanel.get_parameters_description_from_GUI());
//							if (dynamic_description_info_list.isEmpty()) dynamic_description_info_list.add(dynamic_identifiersScrollPanel.get_dynamic_description_from_GUI());
//							if (static_description_info_list.isEmpty()) static_description_info_list.add(static_identifiersScrollPanel.get_static_description_from_GUI());
							if (parameter_description_info_list.isEmpty()) parameter_description_info_list.add("");
							if (dynamic_description_info_list.isEmpty()) dynamic_description_info_list.add("");
							if (static_description_info_list.isEmpty()) static_description_info_list.add("");
							
							List<String> final_parameter_info_list = new ArrayList<String>();
							List<String> final_dynamic_info_list = new ArrayList<String>();
							List<String> final_static_info_list = new ArrayList<String>();
							List<String> final_description_info_list = new ArrayList<String>();
							for (int p = 0; p < parameter_info_list.size(); p++) {
								for (int i = 0; i < dynamic_info_list.size(); i++) {
									for (int j = 0; j < static_info_list.size(); j++) {
										final_parameter_info_list.add(parameter_info_list.get(p));
										final_dynamic_info_list.add(dynamic_info_list.get(i));
										final_static_info_list.add(static_info_list.get(j));
										
										List<String> temp_list = new ArrayList<String>();
										if (!parameter_description_info_list.get(p).equals("")) temp_list.add(parameter_description_info_list.get(p));
										if (!dynamic_description_info_list.get(i).equals("")) temp_list.add(dynamic_description_info_list.get(i));
										if (!static_description_info_list.get(j).equals("")) temp_list.add(static_description_info_list.get(j));
										String joined_string = String.join(" ..... ", temp_list);
										final_description_info_list.add(joined_string);
									}
								}
							}
							
							
							
							
							// Test printing ------------------------------------------------------------------------------------------------------
							System.out.println("--------------------------------------------");
							for (String i: parameter_info_list) { 
								System.out.println(i);
							}
							System.out.println("--------------------------------------------");
							for (String i: dynamic_info_list) { 
								System.out.println(i);
							}
							System.out.println("--------------------------------------------");
							for (String i: static_info_list) { 
								System.out.println(i);
							}
							System.out.println("--------------------------------------------");
							
							
							
							
							// Add all constraints------------------------------------------------------------------------------------------------------
							if (total_constraints > 0) {
								rowCount9 = rowCount9 + total_constraints;
								data9 = new Object[rowCount9][colCount9];
								for (int i = 0; i < rowCount9 - total_constraints; i++) {
									for (int j = 0; j < colCount9; j++) {
										data9[i][j] = model9.getValueAt(i, j);
									}	
								}
								
								Object[][] temp_data = constraint_split_ScrollPanel.get_multiple_constraints_data();
								JCheckBox autoDescription = constraint_split_ScrollPanel.get_autoDescription();
								for (int i = rowCount9 - total_constraints; i < rowCount9; i++) {
									for (int j = 0; j < colCount9; j++) {
										if (autoDescription.isSelected()) {
											if (temp_data[0][1] == null) {
												data9[i][1] = /*"set constraint" + " " + (i - rowCount9 + total_constraints + 1) + " ..... " +*/ final_description_info_list.get(i - rowCount9 + total_constraints);
											} else {
												data9[i][1] = temp_data[0][1] /*+ " " + (i - rowCount9 + total_constraints + 1)*/ + " ..... " + final_description_info_list.get(i - rowCount9 + total_constraints);
											}
										} else {
											data9[i][1] = temp_data[0][1];
										}
										data9[i][0] = temp_data[0][0];
										data9[i][2] = (temp_data[0][2] == null) ? "FREE" : temp_data[0][2];
										data9[i][3] = temp_data[0][3];
										data9[i][4] = temp_data[0][4];
										data9[i][5] = temp_data[0][5];
										data9[i][6] = temp_data[0][6];
										data9[i][7] = temp_data[0][7];
										data9[i][8] = final_parameter_info_list.get(i - rowCount9 + total_constraints);		// parameter splitter is active
										data9[i][9] = final_static_info_list.get(i - rowCount9 + total_constraints);		// static splitter is active
										data9[i][10] = final_dynamic_info_list.get(i - rowCount9 + total_constraints);		// dynamic splitter is active
										data9[i][11] = dynamic_identifiers_scrollpane.get_original_dynamic_info_from_GUI();
									}	
								}	
		
								model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
								update_id();
								model9.fireTableDataChanged();
								quick_edit = new Panel_QuickEdit_FlyConstraints(table9, data9);	// 2 lines to update data for Quick Edit Panel
					 			scrollpane_QuickEdit.setViewportView(quick_edit);
								
								// Convert the new Row to model view and then select it 
								for (int i = rowCount9 - total_constraints; i < rowCount9; i++) {
									int newRow = table9.convertRowIndexToView(i);
									table9.addRowSelectionInterval(newRow, newRow);
								}	
								table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(rowCount9 - total_constraints), 0, true)));
							}
						}
					}
				}
			});			
			
			
			// Import
			btn_Import_basic_Constraints.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					if (table9.isEditing()) {
						table9.getCellEditor().cancelCellEditing();
					}
					filterHeader.resetFilter();
					
					// Add all basic constraints
					rowCount9 = rowCount9 + input_rowCount9;
					data9 = new Object[rowCount9][colCount9];
					for (int ii = 0; ii < rowCount9; ii++) {
						for (int jj = 0; jj < colCount9; jj++) {
							if (ii < rowCount9 - input_rowCount9) {
								data9[ii][jj] = model9.getValueAt(ii, jj);
							} else {
								if (jj < input_colCount9) data9[ii][jj] = input_data9[ii - rowCount9 + input_rowCount9][jj];
							}
							
						}	
					}
					model9.match_DataType();
					
					model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
					update_id();
					model9.fireTableDataChanged();
					quick_edit = new Panel_QuickEdit_FlyConstraints(table9, data9);		// 2 lines to update data for Quick Edit Panel
		 			scrollpane_QuickEdit.setViewportView(quick_edit);
					
					// Convert the new Row to model view and then select it 
					int startRow = table9.convertRowIndexToView(rowCount9 - input_rowCount9);
					int endRow = table9.convertRowIndexToView(rowCount9 - 1);
					table9.setRowSelectionInterval(startRow, endRow);
					table9.scrollRectToVisible(new Rectangle(table9.getCellRect(startRow, 0, true)));
				}
			});
			
			
			// Edit
			btn_Edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table9.isEditing()) {
						table9.getCellEditor().cancelCellEditing();
					}
					
					if (table9.isEnabled()) {
						int selectedRow = table9.getSelectedRow();
						selectedRow = table9.convertRowIndexToModel(selectedRow);		// Convert row index because "Sort" causes problems	
						
						// Apply change
						data9[selectedRow][8] = parameters_scrollpane.get_parameters_info_from_GUI();
						data9[selectedRow][9] = static_identifiers_scrollpane.get_static_info_from_GUI();
						data9[selectedRow][10] = dynamic_identifiers_scrollpane.get_dynamic_info_from_GUI();	
						data9[selectedRow][11] = dynamic_identifiers_scrollpane.get_original_dynamic_info_from_GUI();
						for (int iter_col = 12; iter_col < colCount9; iter_col++) {
    						data9[selectedRow][iter_col] = null;
    					}
						model9.fireTableDataChanged();	
						
						// Convert the edited Row to model view and then select it 
						int editRow = table9.convertRowIndexToView(selectedRow);
						table9.setRowSelectionInterval(editRow, editRow);
						
						static_identifiers_scrollpane.highlight();
						dynamic_identifiers_scrollpane.highlight();
						parameters_scrollpane.highlight();
					}
				}
			});
			
			
			btn_Edit.addMouseListener(new MouseAdapter() { // Add listener
				public void mouseEntered(java.awt.event.MouseEvent e) {
					if (table9.getSelectedRows().length == 1) {
						static_identifiers_scrollpane.highlight();
						dynamic_identifiers_scrollpane.highlight();
						parameters_scrollpane.highlight();
					}		
				}

				public void mouseExited(java.awt.event.MouseEvent e) {
					if (table9.getSelectedRows().length == 1) {
						static_identifiers_scrollpane.unhighlight();
						dynamic_identifiers_scrollpane.unhighlight();
						parameters_scrollpane.unhighlight();
					}
				}
			});
			
			
			// Spinner
		    spin_move_rows.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
					int up_or_down = (int) spin_move_rows.getValue() - 1;										
					spin_move_rows.setValue((int) 1);	// Reset spinner value to 1
										
					if (up_or_down == 1) {	// move up
						// Cancel editing before moving conditions up or down
						if (table9.isEditing()) {
							table9.getCellEditor().cancelCellEditing();
						}	
						
						// Get selected rows
						int[] selectedRow = table9.getSelectedRows();		// No need to convert row index because we never allow Sort when moving rows
						List<Integer> selectedRowList = new ArrayList<Integer>() {{ for (int i : selectedRow) add(i);}};	// Convert array to list
						
						if (selectedRowList.size() >=1 && selectedRowList.get(0) > 0) {	// If there is at least 1 row selected & the first row is not selected
							for (int i = 0; i < rowCount9; i++) {
								if (selectedRowList.contains(i)) {		
									for (int j = 0; j < colCount9; j++) {
										Object temp = data9[i - 1][j];
										data9[i - 1][j] = data9[i][j];
										data9[i][j] = temp;
									}
								}
							}							
							model9.fireTableDataChanged();	// Update the changes and select the currently selected conditions
							for (int i: selectedRow) {
								table9.addRowSelectionInterval(i - 1, i - 1);
							}
						}
						
						// Scroll to the first row of the current selected rows (- 3 to see the 3 unselected rows above when moving up)
						table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(table9.getSelectedRow()) - 3, 0, true)));	
					}
										
					if (up_or_down == -1) {	// move down						
						if (table9.isEditing()) {
							table9.getCellEditor().cancelCellEditing();	// cancel editing before moving conditions up or down
						}	
						
						// Get selected rows
						int[] selectedRow = table9.getSelectedRows();		// No need to convert row index because we never allow Sort when moving rows
						List<Integer> selectedRowList = new ArrayList<Integer>() {{ for (int i : selectedRow) add(i);}};	// Convert array to list
						
						if (selectedRowList.size() >=1 && selectedRowList.get(selectedRowList.size() - 1) < rowCount9 - 1) {	// If ...
							for (int i = rowCount9 - 1; i >= 0; i--) {
								if (selectedRowList.contains(i)) {		
									for (int j = 0; j < colCount9; j++) {
										Object temp = data9[i + 1][j];
										data9[i + 1][j] = data9[i][j];
										data9[i][j] = temp;
									}
								}
							}						
							model9.fireTableDataChanged();	// Update the changes and select the currently selected conditions
							for (int i: selectedRow) {
								table9.addRowSelectionInterval(i + 1, i + 1);
							}	
						}	
						
						// Scroll to the last row of the current selected rows (+ 3 to see the next 3 unselected rows below when moving down)
						table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(table9.getSelectedRows()[table9.getSelectedRows().length - 1]) + 3, 0, true)));	
					}
		        }
		    });
		    
				
			// Delete
			btn_Delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					String ExitOption[] = {"Delete", "Cancel"};
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), "Delete now?", "Confirm Delete",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
					if (response == 0) {
						//Cancel editing before delete
						if (table9.isEditing()) {
							table9.getCellEditor().cancelCellEditing();
						}				
						
						// Get selected rows
						int[] selectedRow = table9.getSelectedRows();	
						for (int i = 0; i < selectedRow.length; i++) {
							selectedRow[i] = table9.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
						}
						
						// Create a list of selected row indexes
						List<Integer> selected_Index = new ArrayList<Integer>();				
						for (int i: selectedRow) {
							selected_Index.add(i);
						}	
						
						// Get values to the new data9
						data9 = new Object[rowCount9 - selectedRow.length][colCount9];
						int newRow =0;
						for (int ii = 0; ii < rowCount9; ii++) {
							if (!selected_Index.contains(ii)) {			//If row not in the list then add to data9 row
								for (int jj = 0; jj < colCount9; jj++) {
									data9[newRow][jj] = model9.getValueAt(ii, jj);
								}
								newRow++;
							}
						}
						// Pass back the info to table model
						rowCount9 = rowCount9 - selectedRow.length;
						model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
						model9.fireTableDataChanged();	
						quick_edit = new Panel_QuickEdit_FlyConstraints(table9, data9);	// 2 lines to update data for Quick Edit Panel
			 			scrollpane_QuickEdit.setViewportView(quick_edit);
					}
				}
			});
					
			
			// Sort
//			TableFilterHeader filterHeader = new TableFilterHeader(null, AutoChoices.ENABLED);		Move to before the New button
			btn_Sort.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					// Identify selection
					int[] selectedRows = table9.getSelectedRows();
					for (int i = 0; i < selectedRows.length; i++) {
						selectedRows[i] = table9.convertRowIndexToModel(selectedRows[i]);		// Convert row index to model because "Sort" causes problems	
					}
					if (table9.isEditing()) {
						table9.getCellEditor().stopCellEditing();
					}
					if (btn_Sort.getText().equals("ON")) {
						filterHeader.setTable(null);
						table9.setRowSorter(null);
						btn_Sort.setText("OFF");
						btn_Sort.repaint();
					} else if (btn_Sort.getText().equals("OFF")) {
						filterHeader.setTable(table9);
						filterHeader.setFilterOnUpdates(true);
						btn_Sort.setText("ON");
						btn_Sort.repaint();
					}
					// Reselect
					for (int i = 0; i < selectedRows.length; i++) {
						selectedRows[i] = table9.convertRowIndexToView(selectedRows[i]);		// Convert row index to view because "Sort" causes problems	
						table9.addRowSelectionInterval(selectedRows[i], selectedRows[i]); 
					}
					if (selectedRows.length != 0) table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(selectedRows[0]), 0, true)));	// move to the first highlighted row
				}
			});
			
			
			
			
			
			
			
			
			
			// Process all the variables in summarize_output_05_management_details
			List<Integer> var_iter_list = new ArrayList<Integer>();	
			List<Double> var_value_list = new ArrayList<Double>();	
			List<Double> var_cost_list = new ArrayList<Double>();
			List<Information_Variable> var_info_list = new ArrayList<Information_Variable>();
			
			
			executor.submit(() -> {
				// Database Info
				String[] yield_tables_names = read_database.get_yield_tables_names();			
				List<String> yield_tables_names_list = Arrays.asList(yield_tables_names); 		// Convert array to list
							
				// Get info: input_02_modeled_strata
				read.read_model_strata(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_02_model_strata.txt"));
				List<String> model_strata = read.get_model_strata();
							
				// Get the 2 parameter V(s1,s2,s3,s4,s5,s6) and A(s1,s2,s3,s4,s5,s6)
				String[][] model_strata_data = read.get_ms_data();	
				double[] strata_area = new double[model_strata.size()];
				int[] strata_starting_age = new int[model_strata.size()];			
				for (int id = 0; id < model_strata.size(); id++) {
					strata_area[id] = Double.parseDouble(model_strata_data[id][7]);		// area (acres)
					strata_starting_age[id] = Integer.parseInt(model_strata_data[id][read.get_ms_total_columns() - 2]);	// age_class		
				}	
				
				// Read the summary file and add necessary information to the lists
				try {
					File file = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/summarize_output_05_management_details.txt");
					List<String> lines_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);	
					String delimited = "\t";		// tab delimited
					String[] columnNames = lines_list.get(0).split(delimited);		// read the first row	
					lines_list.remove(0); 	// remove the  first line which is the column name
					int rowCount = lines_list.size();
					int colCount = columnNames.length;
					
					int iteration_col = -9999; 
					int name_col = -9999; 
					int value_col = -9999; 
					int cost_col = -9999; 
					for (int col = 0; col < colCount; col++) {
						if (columnNames[col].equals("iteration")) iteration_col = col; 
						if (columnNames[col].equals("var_name")) name_col = col; 
						if (columnNames[col].equals("var_value")) value_col = col; 
						if (columnNames[col].equals("var_per_area_unit_cost")) cost_col = col; 
					}	
					
					// populate the data matrix
					for (int row = 0; row < rowCount; row++) {
						String[] row_value = lines_list.get(row).split(delimited);	// tab delimited	
						int iter = Integer.valueOf(row_value[iteration_col]); 
						String var_name = row_value[name_col];
						double var_value = Double.valueOf(row_value[value_col]);
						double var_cost = Double.valueOf(row_value[cost_col]);
						
						Information_Variable var_info = new Information_Variable(iter, var_name, read_database);
						var_iter_list.add(iter);
						var_value_list.add(var_value);
						var_cost_list.add(var_cost);
						var_info_list.add(var_info);	
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					System.err.println(e1.getClass().getName() + ": " + e1.getMessage() + " - Reading data fail in NOSQL MODE");
				}
			});
			Querry_Optimal_Solution query_os = new Querry_Optimal_Solution(); 
						
								
			// Get Result
			btn_GetResult.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					int[] selectedRow = table9.getSelectedRows();	// Get selected rows
					for (int i = 0; i < selectedRow.length; i++) {	// Convert row index because "Sort" causes problems
						selectedRow[i] = table9.convertRowIndexToModel(selectedRow[i]);
					}
					table9.clearSelection(); // clear then add back the rows later
				
					for (int ii : selectedRow) {
						int i = ii;
						
						if (data9[i][12] == null) {	
							executor.submit(() -> {
								btn_NewSingle.setVisible(false);		// Hide buttons
								btn_New_Multiple.setVisible(false);
								btn_Import_basic_Constraints.setVisible(false);
								btn_Edit.setVisible(false);
								spin_move_rows.setVisible(false);
								btn_Delete.setVisible(false);
								btn_Sort.setVisible(false);
								btn_GetResult.setVisible(false);
								btn_Clear.setVisible(false);
								btn_Save.setVisible(false);
								
								if (table9.isEditing()) {
									table9.getCellEditor().cancelCellEditing();
								}
								
								double multiplier = (data9[i][3] != null) ?  (double) data9[i][3] : 0;	//if multiplier = null --> 0
								String current_parameter_index = (String) data9[i][8];
								String current_static_identifiers = (String) data9[i][9];
								String current_dynamic_identifiers = (String) data9[i][10];
														
								List<List<String>> static_identifiers = new ArrayList<>(identifiers_processing.get_static_identifiers(current_static_identifiers));
								List<List<String>> dynamic_identifiers = new ArrayList<>(identifiers_processing.get_dynamic_identifiers(current_dynamic_identifiers));
								List<String> dynamic_dentifiers_column_indexes = new ArrayList<>(identifiers_processing.get_dynamic_dentifiers_column_indexes(current_dynamic_identifiers));
								List<String> parameters_indexes = new ArrayList<String>(identifiers_processing.get_parameters_indexes(current_parameter_index));
														
								// Process all the variables in output05 and use static_identifiers to trim to get the var_name_list & var_value_list
								List<Information_Variable> var_info = new ArrayList<Information_Variable>();
								List<Integer> var_iter = new ArrayList<Integer>();
								List<Double> var_value = new ArrayList<Double>();	
								List<Double> var_cost = new ArrayList<Double>();
								
								for (int row = 0; row < var_info_list.size(); row++) {	// row = var_index (each row is a variable)
									if (identifiers_processing.are_all_static_identifiers_matched(var_info_list.get(row), static_identifiers)) {
										var_iter.add(var_iter_list.get(row));
										var_value.add(var_value_list.get(row));
										var_cost.add(var_cost_list.get(row));
										var_info.add(var_info_list.get(row));
									}	
								}	
										
								// Get the sum result and update the GUI table
								double[] iterations_values = query_os.get_results(read_database, var_info, var_iter, var_value, var_cost, multiplier, parameters_indexes, dynamic_dentifiers_column_indexes, dynamic_identifiers);
								for (int iter_col = 12; iter_col < colCount9; iter_col++) {
									int current_iter = iter_col - 12;
		    						data9[i][iter_col] = iterations_values[current_iter];
		    					}
								table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(i), 0, true)));
								
								// Get everything show up nicely
								table9.setRowSelectionInterval(table9.convertRowIndexToView(i), table9.convertRowIndexToView(i));
								
								// To make UI refresh better
								table9.revalidate();
								table9.repaint();
								parameters_scrollpane.revalidate();
								parameters_scrollpane.repaint();
								static_identifiers_scrollpane.revalidate();
								static_identifiers_scrollpane.repaint();
								dynamic_identifiers_scrollpane.revalidate();
								dynamic_identifiers_scrollpane.repaint();
							});
						}
						
						if (i == selectedRow[selectedRow.length - 1]) {
							executor.submit(() -> {
								// Add back all the row after calculating results
								for (int highlight_row : selectedRow) {
									table9.addRowSelectionInterval(table9.convertRowIndexToView(highlight_row), table9.convertRowIndexToView(highlight_row));
								}
								
								create_file_input_05_fly_constraints();		// Save changes after update fly_value						
								btn_NewSingle.setVisible(true);
								btn_New_Multiple.setVisible(true);
								btn_Import_basic_Constraints.setVisible(true);
								btn_Edit.setVisible(true);
								spin_move_rows.setVisible(true);
								btn_Delete.setVisible(true);
								btn_Sort.setVisible(true);
								btn_GetResult.setVisible(true);
								btn_Clear.setVisible(true);
								btn_Save.setVisible(true);
								
								// To make UI refresh better
								table9.revalidate();
								table9.repaint();
								parameters_scrollpane.revalidate();
								parameters_scrollpane.repaint();
								static_identifiers_scrollpane.revalidate();
								static_identifiers_scrollpane.repaint();
								dynamic_identifiers_scrollpane.revalidate();
								dynamic_identifiers_scrollpane.repaint();
							});
						}
					}
				}
			});		
			
			
			// Clear
			btn_Clear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					String ExitOption[] = {"Clear values", "Cancel"};
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), "Clear values from all iterations now?", "Confirm Clear",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
					if (response == 0) {
						// cancel editing before clear
						if (table9.isEditing()) {
							table9.getCellEditor().cancelCellEditing();
						}				
						
						// get selected rows
						int[] selectedRow = table9.getSelectedRows();	
						for (int i = 0; i < selectedRow.length; i++) {
							selectedRow[i] = table9.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
						}
						
						// clear all values
						table9.clearSelection(); // To help trigger the row refresh: clear then add back the rows
						for (int i: selectedRow) {
							for (int iter_col = 12; iter_col < colCount9; iter_col++) {
	    						data9[i][iter_col] = null;
	    					}
							table9.addRowSelectionInterval(table9.convertRowIndexToView(i), table9.convertRowIndexToView(i));
						}	
					}
				}
			});
			
						
			// Save
			btn_Save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					if (table9.isEditing()) {
						table9.getCellEditor().cancelCellEditing();
					}
					
					create_file_input_05_fly_constraints();
				}
			});	
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------		    
		    

			
			
			
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new Panel_QuickEdit_FlyConstraints(table9, data9);
			scrollpane_QuickEdit = new JScrollPane(quick_edit);
			scrollpane_QuickEdit.setBorder(BorderFactory.createTitledBorder(null, "Quick Edit", TitledBorder.CENTER, 0));
			scrollpane_QuickEdit.setVisible(false);		
				
		

			// ToolBar Panel ----------------------------------------------------------------------------
			// ToolBar Panel ----------------------------------------------------------------------------
			ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
			helpToolBar.setFloatable(false);	//to make a tool bar immovable
			helpToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor
			helpToolBar.setBorderPainted(false);
			
			// button Quick Edit
			JToggleButton btnQuickEdit = new JToggleButton();
			btnQuickEdit.setToolTipText("Show Quick Edit Tool");
			btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
			btnQuickEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {			
					if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
						scrollpane_QuickEdit.setVisible(true);
						// Get everything show up nicely
						PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
					} else {
						btnQuickEdit.setToolTipText("Show Quick Edit Tool");
						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
						scrollpane_QuickEdit.setVisible(false);
						// Get everything show up nicely
						PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
					}
				}
			});			
			
			// button Switch
			JButton btnSwitch = new JButton();
			btnSwitch.setToolTipText("Switch Mode");
			btnSwitch.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_diagram.png"));
			btnSwitch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					NOSQL_link_button.doClick();
				}
			});	
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					
				}
			});
			
			// Label
			JLabel title = new JLabel("MANAGEMENT DETAILS   -   NOSQL MODE");
			title.setFont(new Font(UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName(), Font.BOLD, 12));	// Use MenuBar to get current Font
			
			// Add all buttons to flow_panel
			helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
			helpToolBar.add(title);
			helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
			helpToolBar.add(btnQuickEdit);
			helpToolBar.add(btnSwitch);
			helpToolBar.add(btnHelp);
			// End of ToolBar Panel -----------------------------------------------------------------------
			// End of ToolBar Panel ----------------------------------------------------------------------- 				
				
				
			
			    	
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			JSplitPane split_pane = new JSplitPane();
			split_pane.setBorder(null);
			split_pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split_pane.setDividerSize(3);
						
			JPanel upper_panel = new JPanel();
			upper_panel.setBorder(null);
			upper_panel.setLayout(new GridBagLayout());			
			
			JPanel lower_panel = new JPanel();
			lower_panel.setBorder(null);
			lower_panel.setLayout(new GridBagLayout());

			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;

			// Add helpToolBar to the main Grid
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 3;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
		    upper_panel.add(helpToolBar, c);				
			
			// Add static_identifiersScrollPanel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 0.4;
		    c.weighty = 1;
		    upper_panel.add(static_identifiers_scrollpane, c);				
		    		
			// Add dynamic_identifiersPanel to the main Grid
			c.gridx = 2;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 1;
			upper_panel.add(dynamic_identifiers_scrollpane, c);	
			    		
			// Add the parametersScrollPanel to the main Grid	
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 1;
		    lower_panel.add(parameters_scrollpane, c);						
		    	    		    
		    // Add the button_table_panel & scrollpane_QuickEdit to a new Panel then add that panel to the main Grid
			JPanel button_table_qedit_panel = new JPanel();
			button_table_qedit_panel.setLayout(new BorderLayout());
			button_table_qedit_panel.add(button_table_panel, BorderLayout.CENTER);
			button_table_qedit_panel.add(scrollpane_QuickEdit, BorderLayout.EAST);			
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 2; 
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 1;
		    lower_panel.add(button_table_qedit_panel, c);
			

			split_pane.setLeftComponent(upper_panel);
			split_pane.setRightComponent(lower_panel);
			super.add(split_pane, BorderLayout.CENTER);
		}
		
	    
	    // Update id column. id needs to be unique in order to use in flow constraints-----------------
	    public void update_id() {  		
			List<Integer> id_list = new ArrayList<Integer>();			
			
			for (int row = 0; row < rowCount9; row++) {
				if (data9[row][0] != null) {
					id_list.add(Integer.valueOf(data9[row][0].toString()));
				}
			}			
			
			for (int row = 0; row < rowCount9; row++) {
				if (data9[row][0] == null) {
					int new_id = (id_list.size() > 0) ? Collections.max(id_list) + 1 : 1;	//new id = (max id + 1) or = 1 if no row
					data9[row][0] = new_id;
					id_list.add(new_id);
				}
			}			
		}
	}
	
	
	
	
	private class Querry_Optimal_Solution {
		public Querry_Optimal_Solution() {
		}

		private double[] get_results(Read_Database read_database, List<Information_Variable> var_info,
				List<Integer> var_iter, List<Double> var_value, List<Double> var_cost, double multiplier,
				List<String> parameters_indexes_list, List<String> dynamic_dentifiers_column_indexes,
				List<List<String>> dynamic_identifiers) {			// var_info, var_iter, var_value, var_cost are results after filtered by static_identifiers
			 
			double[] sum_all = new double[total_iteration];
			for (int iter = 0; iter < total_iteration; iter++) {
				sum_all[iter] = 0;
			}

			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			Information_Parameter parameter_info = new Information_Parameter(read_database);
			// Constraints 15	
			for (int var_index = 0; var_index < var_info.size(); var_index++) {	// Loop all variables that were trim by the static filter already, then add to sum_all
				double para_value = parameter_info.get_total_value(
						var_info.get(var_index).get_prescription_id(),
						var_info.get(var_index).get_row_id(),
						parameters_indexes_list,
						dynamic_dentifiers_column_indexes, 
						dynamic_identifiers,
						var_cost.get(var_index));
				para_value = para_value * multiplier;
				
				// Add to sum_all
				int iter = var_iter.get(var_index);
				sum_all[iter] = sum_all[iter] + para_value * var_value.get(var_index);	
			}		
			return sum_all;	
		}			
	}
	
}	


