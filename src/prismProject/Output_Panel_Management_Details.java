package prismProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import prismConvenienceClass.ColorUtil;
import prismConvenienceClass.IconHandle;
import prismConvenienceClass.PrismTableModel;
import prismConvenienceClass.ToolBarWithBgImage;
import prismRoot.PrismMain;

public class Output_Panel_Management_Details extends JLayeredPane implements ItemListener {
	//table input_09_basic_constraints.txt
	private boolean is_table9_loaded = false;
	private int rowCount9, colCount9;
	private String[] columnNames9;
	private JTable table9;
	private PrismTableModel model9;
	private Object[][] data9;
	
	
	Read_RunInputs read;
	int total_Periods;
	
	
	
	
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	private ScrollPane_Parameters parametersScrollPanel;
	private ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
	private ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
	
	private File file_database;
	private Read_Database read_database;
	
	private JScrollPane table_scroll_pane;
	private File currentProjectFolder;
	private String currentRun;
	private JTable table;
	private Object[][] data;
	private PrismTableModel model;
	
	private Thread thread_filter;
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	
	public Output_Panel_Management_Details(File currentProjectFolder, String currentRun, JTable table, Object[][] data, PrismTableModel model) {
		this.currentProjectFolder = currentProjectFolder;
		this.currentRun = currentRun;
		this.table = table;
		this.data = data;
		this.model = model;
		
		
		// Some set up ---------------------------------------------------------------------------			
		file_database = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/database.db");
		read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
		if (read_database == null) {
			read_database = new Read_Database(file_database);	// Read the database
			PrismMain.get_databases_linkedlist().update(file_database, read_database);			
			System.out.println(PrismMain.get_databases_linkedlist().size());
			for (Read_Item rr: PrismMain.get_databases_linkedlist()) {
				System.out.println(rr.file_database.getAbsolutePath() + rr.last_modify);
			}
		}
		
		read = new Read_RunInputs();
		read.read_general_inputs(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_01_general_inputs.txt"));
		total_Periods = read.get_total_periods();		
		// End of set up ---------------------------------------------------------------------------			
		
		
//		// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
//		String panel_name = "Static Identifiers  -  use strata attributes to filter variables";
//		static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_Database, 2, panel_name);
//		checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();		
//				
//		// Update GUI for time period 
//    	for (int j = 0; j < checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).size(); j++) {			//The last element is Time period			
//			if (j < total_Periods) {
//				checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(true);		//Periods to be visible 			
//			} else {
//				checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(false);		//Periods to be invisible
//				checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setSelected(false);		//Periods to be unselected
//			}
//		} 
//    	    	
//    	
//		// Listeners for checkboxStaticIdentifiers
//		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
//			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
//				checkboxStaticIdentifiers.get(i).get(j).addItemListener(this);
//			}
//		}	
//
//		
//		// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
//		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_Database);
//			
//				
//		// 3rd grid ------------------------------------------------------------------------------		// Parameters
//		parametersScrollPanel = new ScrollPane_Parameters(read_Database);
//		TitledBorder border = new TitledBorder("Parameters");
//		border.setTitleJustification(TitledBorder.CENTER);
//		parametersScrollPanel.setBorder(border);
//    	parametersScrollPanel.setPreferredSize(new Dimension(200, 100));			
//		
//    	    	
//    	// 4th grid ------------------------------------------------------------------------------		// table scroll pane
//        table_scroll_pane = new JScrollPane();
//        border = new TitledBorder("Filtered Result based on Optimal Solution");
//		border.setTitleJustification(TitledBorder.CENTER);
//		table_scroll_pane.setBorder(border);
//		table_scroll_pane.setViewportView(table);
//		table_scroll_pane.setPreferredSize(new Dimension(200, 100));
//		
//    	
//    	
//		
//    	// Add all Grids to the Main Grid-----------------------------------------------------------------------
//    	// Add all Grids to the Main Grid-----------------------------------------------------------------------
//    	setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.BOTH;
//		
//		
//		// Add static_identifiersScrollPanel to the main Grid
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridwidth = 2;
//		c.gridheight = 1;
//		c.weightx = 0;
//	    c.weighty = 0;
//		super.add(static_identifiersScrollPanel, c);				
//	    		
//		// Add dynamic_identifiersPanel to the main Grid
//		c.gridx = 2;
//		c.gridy = 1;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.weightx = 1;
//		c.weighty = 0;
//		super.add(dynamic_identifiersScrollPanel, c);	
//		    		
//		// Add the parametersScrollPanel to the main Grid	
//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.weightx = 0;
//	    c.weighty = 1;
//		super.add(parametersScrollPanel, c);						
//	    	    		    
//	    // Add the table	
//		c.gridx = 1;
//		c.gridy = 2;
//		c.gridwidth = 2; 
//		c.gridheight = 1;
//		c.weightx = 1;
//	    c.weighty = 1;
//		super.add(table_scroll_pane, c);		
		setLayout(new BorderLayout());
		reload_inputs_before_creating_GUI();
		super.add(new Fly_Constraints_GUI(), BorderLayout.CENTER);		
		model9.match_DataType();	// Matching data types after finishing reloads
	}	
	
	//Listeners for this class------------------------------------------------------------------------------------------------------------------------
	public void itemStateChanged(ItemEvent e) {
		// THESE FOLLLOWING IS INTERESTING, SAME AS ABOVE BUT I PUT THE WHOLE THING INTO A THREAD AND NO NEED TO STOP ANY MORE --> just interrupt & AVOID TROUBLE OF FREEZING
		// THESE FOLLLOWING IS INTERESTING, SAME AS ABOVE BUT I PUT THE WHOLE THING INTO A THREAD AND NO NEED TO STOP ANY MORE --> just interrupt & AVOID TROUBLE OF FREEZING
		// THESE FOLLLOWING IS INTERESTING, SAME AS ABOVE BUT I PUT THE WHOLE THING INTO A THREAD AND NO NEED TO STOP ANY MORE --> just interrupt & AVOID TROUBLE OF FREEZING
		
//		Thread filter_thread = new Thread() {
//			public void run() {	
//				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//				Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//				for (Thread t : threadArray) {
//					if (t.getState() == Thread.State.RUNNABLE && t != Thread.currentThread()) {
//						t.interrupt();
////						t.stop();
//						PrismMain.get_main().revalidate();
//						PrismMain.get_main().repaint();
//					}
//				}
//
//				
//				executor.submit(new Runnable() {
//					public void run() {										
//						table_scroll_pane.setViewportView(null);	// Hide table before filtering
//						
//						
//						RowFilter<Object, Object> equalsAFilter = new RowFilter<Object, Object>() {
//							// 1. FAST FILTER: NOT SURE IF FASTER THAN 2
//							public boolean include(Entry<? extends Object, ? extends Object> entry) {				
//								String varible_term = entry.getStringValue(1);
//								String term;
//								int count;
//								
//								
//								
//								term = Get_Variable_Information.get_layer1(varible_term);
//								count = 0;
//								for (JCheckBox layer1 : checkboxStaticIdentifiers.get(0)) {
//									if ((layer1.isSelected() && (layer1.isVisible()) || !layer1.isEnabled()) && term.equals(layer1.getText())) {
//										count++;
//									}
//								}
//								if (count < 1) return false;		// return false so that this entry is not shown								
//								
//								
//								
//								term = Get_Variable_Information.get_layer2(varible_term);
//								count = 0;
//								for (JCheckBox layer2 : checkboxStaticIdentifiers.get(1)) {
//									if ((layer2.isSelected() && (layer2.isVisible()) || !layer2.isEnabled()) && term.equals(layer2.getText())) {
//										count++;
//									}
//								}
//								if (count < 1) return false;		// return false so that this entry is not shown
//								
//								
//								
//								term = Get_Variable_Information.get_layer3(varible_term);
//								count = 0;
//								for (JCheckBox layer3 : checkboxStaticIdentifiers.get(2)) {
//									if ((layer3.isSelected() && (layer3.isVisible()) || !layer3.isEnabled()) && term.equals(layer3.getText())) {
//										count++;
//									}
//								}
//								if (count < 1) return false;		// return false so that this entry is not shown
//								
//							
//								
//								term = Get_Variable_Information.get_layer4(varible_term);
//								count = 0;
//								for (JCheckBox layer4 : checkboxStaticIdentifiers.get(3)) {
//									if ((layer4.isSelected() && (layer4.isVisible()) || !layer4.isEnabled()) && term.equals(layer4.getText())) {
//										count++;
//									}
//								}
//								if (count < 1) return false;		// return false so that this entry is not shown
//								
//								
//								
//								if (Get_Variable_Information.get_forest_status(varible_term) == "E") {		// Only applied for Existing Strata
//									term = Get_Variable_Information.get_layer5(varible_term);
//									count = 0;
//									for (JCheckBox layer5 : checkboxStaticIdentifiers.get(4)) {
//										if ((layer5.isSelected() && (layer5.isVisible()) || !layer5.isEnabled()) && term.equals(layer5.getText())) {
//											count++;
//										}
//									}
//									if (count < 1) return false;		// return false so that this entry is not shown
//									
//									
//									
//									term = Get_Variable_Information.get_layer6(varible_term);
//									count = 0;
//									for (JCheckBox layer6 : checkboxStaticIdentifiers.get(5)) {
//										if ((layer6.isSelected() && (layer6.isVisible()) || !layer6.isEnabled()) && term.equals(layer6.getText())) {
//											count++;
//										}
//									}
//									if (count < 1) return false;		// return false so that this entry is not shown
//								}
//								
//								
//								
//								term = Get_Variable_Information.get_method(varible_term) + "_" + Get_Variable_Information.get_forest_status(varible_term);
//								count = 0;
//								for (JCheckBox method : checkboxStaticIdentifiers.get(6)) {
//									if ((method.isSelected() && (method.isVisible()) || !method.isEnabled()) && term.equals(method.getText())) {
//										count++;
//									}
//								}
//								if (count < 1) return false;		// return false so that this entry is not shown	
//								
//								
//								
//								term = String.valueOf(Get_Variable_Information.get_period(varible_term));
//								count = 0;
//								for (JCheckBox period : checkboxStaticIdentifiers.get(7)) {
//									if ((period.isSelected() && (period.isVisible()) || !period.isEnabled()) && term.equals(period.getText())) {
//										count++;
//									}
//								}
//								if (count < 1) return false;		// return false so that this entry is not shown		
//								
//
//								
//								return true;	// return true to show the entry
//							}
//						};
//						
//						TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model);
//						table.setRowSorter(sorter);
//						sorter.setRowFilter(equalsAFilter);	
//						
//						
//						table_scroll_pane.setViewportView(table);	// Show table after filtering is finished
//					}
//				});
//
//				try {
//					if (executor.awaitTermination(-1, TimeUnit.SECONDS)) {	
//						System.out.println("aaaaaaaaaaa");
//					} else {				
//						System.out.println("Task completed, other waiting Filters Threads are automatically shut down");
//						PrismMain.get_main().revalidate();
//						PrismMain.get_main().repaint();
//					}
//				} catch (InterruptedException e1) {
//					System.out.println("Executor problem in Filter Threads in Customize Mode");
//				}
//								
//				this.interrupt();
//			}
//		};
//		
//		if (!Thread.currentThread().isInterrupted()) {
//			Thread.currentThread().interrupt();
//			filter_thread.start();
//		}		
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
			colCount9 = 13;
			data9 = new Object[rowCount9][colCount9];
			columnNames9 = new String[] {"fly_id", "fly_description", "fly_type",  "fly_multiplier", "lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty", "parameter_index", "static_identifiers", "dynamic_identifiers", "original_dynamic_identifiers", "fly_value"};	         				
		}
					
		
		//Create a table-------------------------------------------------------------		
		model9 = new PrismTableModel(rowCount9, colCount9, data9, columnNames9) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;      //column 0 accepts only Integer
				else if (c >= 3 && c <= 7) return Double.class;      //column 3 to 7 accept only Double values  
				else if (c == 12) return Double.class;      //column 3 to 7 accept only Double values 
				else return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col >= colCount9 - 5) { //  The first and the last 5 columns are un-editable
					return false;
				} else {
					return true;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data9[row][col] = value;
				if (col == 2) {
					fireTableDataChanged();		// When constraint type change then this would register the change and make the selection disappear
					table9.setRowSelectionInterval(table9.convertRowIndexToView(row), table9.convertRowIndexToView(row));			// select the row again
				}
				if (col == 3) {
					data9[row][12] = null;
					fireTableDataChanged();		// When constraint multiplier change then this would register the change and make the selection disappear
					table9.setRowSelectionInterval(table9.convertRowIndexToView(row), table9.convertRowIndexToView(row));			// select the row again
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
							} else if ((col >= 3 && col <= 7) || col == 12) {			//Column 3 to 7 and column 12 are Double
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
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table9.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table9,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);			
				return component;
			}	
		};

    

        // Set up Type for each column 2
		table9.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_constraint_type()));
			
		
		// Hide the some columns: this hide is better than remove column from column model, this is basically set size to be zero
		for (int i = 0; i < colCount9; i++) {
			if (i != 0 && i != 1 && i != 3 & i!= 12) {
				table9.getColumnModel().getColumn(i).setMinWidth(0);
				table9.getColumnModel().getColumn(i).setMaxWidth(0);
				table9.getColumnModel().getColumn(i).setWidth(0);
			}
		}
         
		table9.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table9.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table9.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table9.setPreferredScrollableViewportSize(new Dimension(200, 100));
//		table9.setFillsViewportHeight(true);
	}
	
	
	
	
	// Panel Fly Constraints--------------------------------------------------------------------------------------------------------
	private class Fly_Constraints_GUI extends JLayeredPane {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		ScrollPane_Parameters parametersScrollPanel;
		ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
		ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
		JPanel button_table_Panel;
		
		QuickEdit_FlyConstraints_Panel quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Fly_Constraints_GUI() {
			setLayout(new GridBagLayout());
			
			// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
			String panel_name = "Static Identifiers  -  use strata attributes to filter variables";
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_database, 2, panel_name);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();		
					
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
			dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_database);
			// End of 2nd Grid -----------------------------------------------------------------------
				
					
			// 3rd grid ------------------------------------------------------------------------------		// Parameters
			parametersScrollPanel = new ScrollPane_Parameters(read_database);
			TitledBorder border = new TitledBorder("Parameters");
			border.setTitleJustification(TitledBorder.CENTER);
			parametersScrollPanel.setBorder(border);
	    	parametersScrollPanel.setPreferredSize(new Dimension(200, 100));
			// End of 3rd grid -----------------------------------------------------------------------
			
	    	

			// 4th Grid ------------------------------------------------------------------------------		// Buttons	
			// 4th Grid -----------------------------------------------------------------------------
			// Add all buttons to a Panel----------------------------------
			button_table_Panel = new JPanel(new GridBagLayout());
			TitledBorder border3 = new TitledBorder("Fly Constraints - Queries based on the Optimal Solution");
			border3.setTitleJustification(TitledBorder.CENTER);
			button_table_Panel.setBorder(border3);
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.insets = new Insets(0, 5, 10, 10); // padding top 0, left 5, bottom 10, right 10
			
			
			JButton btn_NewSingle = new JButton();
			btn_NewSingle.setFont(new Font(null, Font.BOLD, 14));
//			btn_NewSingle.setText("NEW SINGLE");
			btn_NewSingle.setToolTipText("New constraint");
			btn_NewSingle.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));
					
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_NewSingle, c2);
			
			
			JButton btn_New_Multiple = new JButton();
			btn_New_Multiple.setFont(new Font(null, Font.BOLD, 14));
//			btn_New_Multiple.setText("NEW MULTIPLE");
			btn_New_Multiple.setToolTipText("New set of constraints");
			btn_New_Multiple.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add3.png"));
					
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_New_Multiple, c2);
			
			JButton btn_Edit = new JButton();
//			btn_Edit.setText("EDIT");
			btn_Edit.setToolTipText("Modify constraint");
			btn_Edit.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_swap.png"));
			btn_Edit.setEnabled(false);
					
			c2.gridx = 0;
			c2.gridy = 2;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Edit, c2);
			
			
			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 14));
//			btn_Delete.setText("DELETE");
			btn_Delete.setToolTipText("Delete constraints");
			btn_Delete.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_erase.png"));
			btn_Delete.setEnabled(false);
					
			c2.gridx = 0;
			c2.gridy = 3;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Delete, c2);
			
			
			JToggleButton btn_Sort = new JToggleButton();
			btn_Sort.setSelected(false);
			btn_Sort.setFocusPainted(false);
			btn_Sort.setFont(new Font(null, Font.BOLD, 12));
			btn_Sort.setText("OFF");
			btn_Sort.setToolTipText("Sorter mode: 'ON' click columns header to sort rows. 'OFF' retrieve original rows position");
			btn_Sort.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_table.png"));
					
			c2.gridx = 0;
			c2.gridy = 4;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Sort, c2);
			
			
			JButton btn_GetResult = new JButton();
			btn_GetResult.setFont(new Font(null, Font.BOLD, 14));
//			btn_GetResult.setText("Get Result");
			btn_GetResult.setToolTipText("Update fly_value & auto save");
			btn_GetResult.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_solve.png"));
			btn_GetResult.setContentAreaFilled(false);
			btn_GetResult.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			    	btn_GetResult.setContentAreaFilled(true);
			    }

			    public void mouseExited(MouseEvent e) {
			    	btn_GetResult.setContentAreaFilled(false);
			    }
			});		
			c2.gridx = 0;
			c2.gridy = 5;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_GetResult, c2);
			
			
			JButton btn_Save = new JButton();
			btn_Save.setFont(new Font(null, Font.BOLD, 14));
//			btn_Save.setText("Save");
			btn_Save.setToolTipText("Save all");
			btn_Save.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_save.png"));
			btn_Save.setContentAreaFilled(false);
			btn_Save.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			    	btn_Save.setContentAreaFilled(true);
			    }

			    public void mouseExited(MouseEvent e) {
			    	btn_Save.setContentAreaFilled(false);
			    }
			});		
			c2.gridx = 0;
			c2.gridy = 6;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Save, c2);
			
			
			c2.insets = new Insets(0, 0, 0, 0); // No padding
			// Add Empty Label to make all buttons on top not middle
			c2.gridx = 0;
			c2.gridy = 7;
			c2.weightx = 0;
			c2.weighty = 1;
			button_table_Panel.add(new JLabel(), c2);
			
			// Add table9				
			create_table9();
			JScrollPane table_ScrollPane = new JScrollPane(table9);	
			c2.gridx = 1;
			c2.gridy = 0;
			c2.weightx = 1;
			c2.weighty = 1;
			c2.gridheight = 8;
			button_table_Panel.add(table_ScrollPane, c2);
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
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data9[currentRow][9]);	// 9 is the static_identifiers which have some attributes selected				
						dynamic_identifiersScrollPanel.reload_this_constraint_dynamic_identifiers((String) data9[currentRow][10], (String) data9[currentRow][11]);	// 11 is the original_dynamic_identifiers column
						parametersScrollPanel.reload_this_constraint_parameters((String) data9[currentRow][8]);	// 8 is the selected parameters of this constraint
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table9.isEnabled()) {		// Enable Delete  when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}		
				}
			});
			
			table9.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selectedRow = table9.getSelectedRows();
					if (selectedRow.length == 1) {		// Enable Edit	when: 1 row is selected and no cell is editing
						int currentRow = selectedRow[0];
						currentRow = table9.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data9[currentRow][9]);	// 9 is the static_identifiers which have some attributes selected				
						dynamic_identifiersScrollPanel.reload_this_constraint_dynamic_identifiers((String) data9[currentRow][10], (String) data9[currentRow][11]);	// 11 is the original_dynamic_identifiers column
						parametersScrollPanel.reload_this_constraint_parameters((String) data9[currentRow][8]);	// 8 is the selected parameters of this constraint
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table9.isEnabled()) {		// Enable Delete  when: >=1 row is selected,table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}	
		        }
		    });
			
			

			// New single
			btn_NewSingle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					// Add 1 row
					rowCount9++;
					data9 = new Object[rowCount9][colCount9];
					for (int ii = 0; ii < rowCount9 - 1; ii++) {
						for (int jj = 0; jj < colCount9; jj++) {
							data9[ii][jj] = model9.getValueAt(ii, jj);
						}	
					}
									
					data9[rowCount9 - 1][3] = (double) 1;
					data9[rowCount9 - 1][8] = parametersScrollPanel.get_parameters_info_from_GUI();
					data9[rowCount9 - 1][9] = static_identifiersScrollPanel.get_static_info_from_GUI();
					data9[rowCount9 - 1][10] = dynamic_identifiersScrollPanel.get_dynamic_info_from_GUI();
					data9[rowCount9 - 1][11] = dynamic_identifiersScrollPanel.get_original_dynamic_info_from_GUI();
					
					model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
					update_id();
					model9.fireTableDataChanged();
					quick_edit = new QuickEdit_FlyConstraints_Panel(table9, data9);		// 2 lines to update data for Quick Edit Panel
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
					
					ScrollPane_ConstraintsSplit constraint_split_ScrollPanel = new ScrollPane_ConstraintsSplit(
							static_identifiersScrollPanel.get_TitleAsCheckboxes(),
							parametersScrollPanel.get_checkboxParameter(),
							dynamic_identifiersScrollPanel.get_allDynamicIdentifiers());

					
					
					String ExitOption[] = {"Add Constraints","Cancel"};
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), constraint_split_ScrollPanel, "Create multiple constraints",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[1]);
					if (response == 0)	// Add Constraints
					{		
						int total_Constraints = 1;
						List<String> splitStatic_NameList = constraint_split_ScrollPanel.get_splitStatic_NameList();	// Names of static splitters

						for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
							if (splitStatic_NameList.contains(static_identifiersScrollPanel.get_TitleAsCheckboxes().get(i).getText())) {	// IF this static must be splitted
								int total_Checked_Elements = 0;
								for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
									if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
										total_Checked_Elements ++;	// Increase number of constraints
									}
								}
								total_Constraints = total_Constraints * total_Checked_Elements;
							}
						}
						System.out.println(total_Constraints);
						
						
						
						// Ask to confirm adding if there are more than 1000 constraints
						int response2 = 0;	
						if (total_Constraints > 1000) {
							String ExitOption2[] = {"Yes","No"};
							String warningText = "You are going to add " + total_Constraints + " constraints. It would take some time. Continue to add ?";
							response2 = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Confirm adding constraints",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption2, ExitOption2[1]);
							
						}
							
						if (response2 == 0)
						{					
							// After we know the total number of Constraints, then add info for each constraint
							String[] static_info = new String[total_Constraints];
							String[] description_extra = new String[total_Constraints];
							for (int processing_constraint = 0; processing_constraint < total_Constraints; processing_constraint++) { 
								static_info[processing_constraint] = "";
								description_extra[processing_constraint] = "";
							}
								
							
							
							for (int processing_constraint = 0; processing_constraint < total_Constraints; processing_constraint++) { 
								int total_same_info_constraints = total_Constraints;
								
								
								for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
									
									static_info[processing_constraint] = static_info[processing_constraint] + i + " ";
									
									
									
									if (splitStatic_NameList.contains(static_identifiersScrollPanel.get_TitleAsCheckboxes().get(i).getText())) {	// IF this static must be splitted
										int total_Checked_Elements = 0;
										List<Integer> selected_Element_Index = new ArrayList<Integer>();
										for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
											if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible()) {	// If element of this static is checked		
												selected_Element_Index.add(j);
												total_Checked_Elements ++;	// Increase number of constraints
											}			
										}
										total_same_info_constraints = total_same_info_constraints/total_Checked_Elements;
//										System.out.println(total_same_info_constraints);
										
										

										description_extra[processing_constraint] = description_extra[processing_constraint] + " - " + static_identifiersScrollPanel.get_TitleAsCheckboxes().get(i).getText()  + " ";
											
										for (int element_to_add = 0; element_to_add < selected_Element_Index.size(); element_to_add++) {
											
											for (int j = 0; j < total_same_info_constraints; j++) {
												
												// This is my smart check: example
												/*			1	1	1
												 * 			2	2	2
												 * 			3		3
												 * 					4
												 *  Then the below If would help write out as: 1,1,1	1,1,2	1,1,3	1,1,4	1,2,1	1,2,2	1,2,3	1,2,4	......	
												 *  Please figure out the logic by yourself :))									
												*/
												if ( processing_constraint % (selected_Element_Index.size() * total_same_info_constraints) == element_to_add * total_same_info_constraints + j) {							
													String checkboxName = checkboxStaticIdentifiers.get(i).get(selected_Element_Index.get(element_to_add)).getText();												
													//Add checkBox if it is (selected & visible) or disable
													if ((checkboxStaticIdentifiers.get(i).get(selected_Element_Index.get(element_to_add)).isSelected() && (checkboxStaticIdentifiers.get(i).get(selected_Element_Index.get(element_to_add)).isVisible())
															|| !checkboxStaticIdentifiers.get(i).get(selected_Element_Index.get(element_to_add)).isEnabled())) {
														static_info[processing_constraint] = static_info[processing_constraint] + checkboxName + " ";
														description_extra[processing_constraint] = description_extra[processing_constraint] + checkboxName; 
													}		
												}	
											}
										}
										
										
										
										
									} else {		// IF this static would not be split
										for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {		//Loop all elements in each layer
											String checkboxName = checkboxStaticIdentifiers.get(i).get(j).getText();																						
											//Add checkBox if it is (selected & visible) or disable
											if ((checkboxStaticIdentifiers.get(i).get(j).isSelected() && (checkboxStaticIdentifiers.get(i).get(j).isVisible())
													|| !checkboxStaticIdentifiers.get(i).get(j).isEnabled())) {
												static_info[processing_constraint] = static_info[processing_constraint] + checkboxName + " ";										
											}		
										}
										
									}
									
									
									if (!static_info[processing_constraint].equals("")) {
										static_info[processing_constraint] = static_info[processing_constraint].substring(0, static_info[processing_constraint].length() - 1) + ";";		// remove the last space, and add ;
									}
								}	
								
								if (!static_info[processing_constraint].equals("")) {
									static_info[processing_constraint] = static_info[processing_constraint].substring(0, static_info[processing_constraint].length() - 1);		// remove the last ;
								}
							}
							
							
							
							
							for (int processing_constraint = 0; processing_constraint < total_Constraints; processing_constraint++) { 
								System.out.println(static_info[processing_constraint]);
							}
							
						
							
							
							
							
							
							
							
							
							
							// Add All Constraints ----------------------------------------------------------------
							if (total_Constraints > 0) {
								rowCount9 = rowCount9 + total_Constraints;
								data9 = new Object[rowCount9][colCount9];
								for (int ii = 0; ii < rowCount9 - total_Constraints; ii++) {
									for (int jj = 0; jj < colCount9; jj++) {
										data9[ii][jj] = model9.getValueAt(ii, jj);
									}	
								}
								
								Object[][] temp_data = constraint_split_ScrollPanel.get_multiple_constraints_data();
								JCheckBox autoDescription = constraint_split_ScrollPanel.get_autoDescription();
								
								for (int i = rowCount9 - total_Constraints; i < rowCount9; i++) {
									for (int j = 0; j < colCount9; j++) {
										if (autoDescription.isSelected()) {
											if (temp_data[0][1] == null) {
												data9[i][1] = "set constraint" + " " + (i - rowCount9 + total_Constraints + 1) + description_extra[i - rowCount9 + total_Constraints];
											} else {
												data9[i][1] = temp_data[0][1] + " " + (i - rowCount9 + total_Constraints + 1) + description_extra[i - rowCount9 + total_Constraints];
											}
										} else {
											data9[i][1] = temp_data[0][1];
										}
										data9[i][0] = temp_data[0][0];
										data9[i][2] = temp_data[0][2];
										data9[i][3] = temp_data[0][3];
										data9[i][4] = temp_data[0][4];
										data9[i][5] = temp_data[0][5];
										data9[i][6] = temp_data[0][6];
										data9[i][7] = temp_data[0][7];
										data9[i][8] = parametersScrollPanel.get_parameters_info_from_GUI();
										data9[i][9] = static_info[i - rowCount9 + total_Constraints];		// Only these splitter are currently allowed
										data9[i][10] = dynamic_identifiersScrollPanel.get_dynamic_info_from_GUI();	
										data9[i][11] = dynamic_identifiersScrollPanel.get_original_dynamic_info_from_GUI();
									}	
								}	
												
		
								model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
								update_id();
								model9.fireTableDataChanged();
								quick_edit = new QuickEdit_FlyConstraints_Panel(table9, data9);	// 2 lines to update data for Quick Edit Panel
					 			scrollpane_QuickEdit.setViewportView(quick_edit);
								
								// Convert the new Row to model view and then select it 
								for (int i = rowCount9 - total_Constraints; i < rowCount9; i++) {
									int newRow = table9.convertRowIndexToView(i);
									table9.addRowSelectionInterval(newRow, newRow);
								}	
								table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(rowCount9 - total_Constraints), 0, true)));
							}
						}
											
					}
					if (response == 1)	// Cancel: do nothing
					{
					}
				}
			});			
			
			
			// Edit
			btn_Edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table9.isEnabled()) {
						
						//A  resizable popup panel indicating changes have been made
						JPanel popup = new JPanel(new BorderLayout());
						popup.setBorder(null);
						popup.setPreferredSize(new Dimension(330, 150));	
						JLabel temp_label = new JLabel(IconHandle.get_scaledImageIcon(150, 150, "pikachuHello.png"));									
						popup.add(temp_label, BorderLayout.WEST);
						
						JTextArea temp_textarea = new JTextArea();
						temp_textarea.setBorder(null);
						temp_textarea.setBackground(ColorUtil.makeTransparent(Color.WHITE, 0)); 
						temp_textarea.setFocusable(false);
						temp_textarea.setEditable(false);
						temp_textarea.setLineWrap(true);
						temp_textarea.setWrapStyleWord(true);
						temp_textarea.append("The following infomation (in rectangles surrounded by green border) will be applied to the highlighted (blue) constraint" + "\n \n");
						temp_textarea.append("1. Static Identifiers" + "\n");
						temp_textarea.append("2. Dynamic Identifiers" + "\n");
						temp_textarea.append("3. Parameters" + "\n");
						popup.add(temp_textarea, BorderLayout.CENTER);

						popup.addHierarchyListener(new HierarchyListener() {
						    public void hierarchyChanged(HierarchyEvent e) {
						        Window window = SwingUtilities.getWindowAncestor(popup);
						        if (window instanceof Dialog) {
						            Dialog dialog = (Dialog)window;
						            if (!dialog.isResizable()) {
						                dialog.setResizable(true);
						            }
						        }
						    }
						});				

						String ExitOption[] = {"Modify", "Do not modify"};
						int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), popup, "Do you want to modify the highlighted constraint ?",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);	
												
						if (response == 0) {
							// Apply change
							int selectedRow = table9.getSelectedRow();
							selectedRow = table9.convertRowIndexToModel(selectedRow);		// Convert row index because "Sort" causes problems										
							data9[selectedRow][8] = parametersScrollPanel.get_parameters_info_from_GUI();
							data9[selectedRow][9] = static_identifiersScrollPanel.get_static_info_from_GUI();
							data9[selectedRow][10] = dynamic_identifiersScrollPanel.get_dynamic_info_from_GUI();	
							data9[selectedRow][11] = dynamic_identifiersScrollPanel.get_original_dynamic_info_from_GUI();
							data9[selectedRow][12] = null;
							model9.fireTableDataChanged();	
							
							// Convert the edited Row to model view and then select it 
							int editRow = table9.convertRowIndexToView(selectedRow);
							table9.setRowSelectionInterval(editRow, editRow);
							
							// Enable buttons and table9
							table9.setEnabled(true);
							btn_NewSingle.setEnabled(true);
							btn_New_Multiple.setEnabled(true);
							btn_Delete.setEnabled(true);
							btn_Sort.setEnabled(true);
							btn_GetResult.setEnabled(true);	
							btn_Edit.setEnabled(true);
							table_ScrollPane.setViewportView(table9);
							
							// Reset the view
							int currentRow = table9.getSelectedRow();
							currentRow = table9.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
							static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data9[currentRow][9]);	// 9 is the static_identifiers which have some attributes selected				
							dynamic_identifiersScrollPanel.reload_this_constraint_dynamic_identifiers((String) data9[currentRow][10], (String) data9[currentRow][11]);	// 11 is the original_dynamic_identifiers column
							parametersScrollPanel.reload_this_constraint_parameters((String) data9[currentRow][8]);	// 8 is the selected parameters of this constraint
						}
						
					} 
				}
			});
			
			
			btn_Edit.addMouseListener(new MouseAdapter() { // Add listener
				public void mouseEntered(java.awt.event.MouseEvent e) {
					static_identifiersScrollPanel.setBackground(new Color(0, 255, 0));
					dynamic_identifiersScrollPanel.setBackground(new Color(0, 255, 0));
					parametersScrollPanel.setBackground(new Color(0, 255, 0));
				}

				public void mouseExited(java.awt.event.MouseEvent e) {
					static_identifiersScrollPanel.setBackground(null);
					dynamic_identifiersScrollPanel.setBackground(null);
					parametersScrollPanel.setBackground(null);
				}
			});
			
				
			// Delete
			btn_Delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
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
					quick_edit = new QuickEdit_FlyConstraints_Panel(table9, data9);	// 2 lines to update data for Quick Edit Panel
		 			scrollpane_QuickEdit.setViewportView(quick_edit);
				}
			});
					
			
			// Sort
			btn_Sort.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (btn_Sort.getText().equals("ON")) {
						table9.setRowSorter(null);
						btn_Sort.setText("OFF");
						btn_Sort.repaint();
					} else if (btn_Sort.getText().equals("OFF")) {
						TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model9); // Add sorter
						table9.setRowSorter(sorter);
						btn_Sort.setText("ON");
						btn_Sort.repaint();
					}	
				}
			});
			
								
			// Get Result
			btn_GetResult.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					Thread thread_get_result = new Thread() {
						public void run() {
							btn_NewSingle.setVisible(false);
							btn_New_Multiple.setVisible(false);
							btn_Edit.setVisible(false);
							btn_Delete.setVisible(false);
							btn_Sort.setVisible(false);
							btn_GetResult.setVisible(false);
							btn_Save.setVisible(false);
							
							for (int i = 0; i < data9.length; i++) {	// Loop each row of the fly constraints table & get result for only the constraints with null value
								if (data9[i][12] == null) {	
									double multiplier = (data9[i][3] != null) ?  (double) data9[i][3] : 0;	//if multiplier = null --> 0
									String current_parameter_index = (String) data9[i][8];
									String current_static_identifiers = (String) data9[i][9];
									String current_dynamic_identifiers = (String) data9[i][10];
															
									List<List<String>> static_identifiers = new ArrayList<>(get_static_identifiers_in_row(current_static_identifiers));
									List<List<String>> dynamic_identifiers = new ArrayList<>(get_dynamic_identifiers_in_row(current_dynamic_identifiers));
									List<String> dynamic_dentifiers_column_indexes = new ArrayList<>(get_dynamic_dentifiers_column_indexes_in_row(current_dynamic_identifiers));
									List<String> parameters_indexes_list = new ArrayList<String>(get_parameters_indexes_list(current_parameter_index));
															
									// Process all the variables in output05 and use static_identifiers to trim to get the var_name_list & var_value_list
									List<String> var_name_list = new ArrayList<String>(); 
									List<Double> var_value_list = new ArrayList<Double>();						
									for (int row = 0; row < data.length; row++) {
										String var_name = String.valueOf(data[row][1]);
										double var_value = Double.valueOf(String.valueOf(data[row][2]));
										if (are_all_static_identifiers_matched(var_name, static_identifiers)) {
											var_name_list.add(var_name);
											var_value_list.add(var_value);
										}	
									}	
									
									// Convert lists to 1-D arrays
									String[] vname = var_name_list.toArray(new String[var_name_list.size()]);
									double[] vvalue = Stream.of(var_value_list.toArray(new Double[var_value_list.size()])).mapToDouble(Double::doubleValue).toArray();
											
									// Get the sum result and update the GUI table
									data9[i][12] = new Querry_Optimal_Solution().get_results(read_database, vname, vvalue, multiplier, parameters_indexes_list, dynamic_dentifiers_column_indexes, dynamic_identifiers);
									table9.scrollRectToVisible(new Rectangle(table9.getCellRect(table9.convertRowIndexToView(i), 0, true)));
									
									// Get everything show up nicely
									PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());	
								}
							}
//							model9.fireTableDataChanged();	// Fire data changes
							create_file_input_05_fly_constraints();		// Save changes after update fly_value						
							btn_NewSingle.setVisible(true);
							btn_New_Multiple.setVisible(true);
							btn_Edit.setVisible(true);
							btn_Delete.setVisible(true);
							btn_Sort.setVisible(true);
							btn_GetResult.setVisible(true);
							btn_Save.setVisible(true);
							this.interrupt();
						}
					};					
					thread_get_result.start();
				}
			});		
			
						
			// Save
			btn_Save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {						
					create_file_input_05_fly_constraints();
				}
			});	
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------		    
		    

			
			
			
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new QuickEdit_FlyConstraints_Panel(table9, data9);
				scrollpane_QuickEdit = new JScrollPane(quick_edit);
				border = new TitledBorder("Quick Edit ");
				border.setTitleJustification(TitledBorder.CENTER);
				scrollpane_QuickEdit.setBorder(border);
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
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					
				}
			});
			
			// Add all buttons to flow_panel
			helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
			helpToolBar.add(btnQuickEdit);
			helpToolBar.add(btnHelp);
			// End of ToolBar Panel -----------------------------------------------------------------------
			// End of ToolBar Panel ----------------------------------------------------------------------- 				
				
				
			
			    	
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;

			// Add helpToolBar to the main Grid
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 3;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(helpToolBar, c);				
			
			// Add static_identifiersScrollPanel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(static_identifiersScrollPanel, c);				
		    		
			// Add dynamic_identifiersPanel to the main Grid
			c.gridx = 2;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
			c.weighty = 0;
			super.add(dynamic_identifiersScrollPanel, c);	
			    		
			// Add the parametersScrollPanel to the main Grid	
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 1;
			super.add(parametersScrollPanel, c);						
		    	    		    
		    // Add the button_table_Panel & scrollpane_QuickEdit to a new Panel then add that panel to the main Grid
			JPanel button_table_qedit_panel = new JPanel();
			button_table_qedit_panel.setLayout(new BorderLayout());
			button_table_qedit_panel.add(button_table_Panel, BorderLayout.CENTER);
			button_table_qedit_panel.add(scrollpane_QuickEdit, BorderLayout.EAST);			
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 2; 
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 1;
			super.add(button_table_qedit_panel, c);
		}
		
	    
	    // Update id column. id needs to be unique in order to use in flow constraints-----------------
	    public void update_id() {  		
			List<Integer> id_list = new ArrayList<Integer>();			
			
			for (int row = 0; row < rowCount9; row++) {
				if (data9[row][0] != null) {
					id_list.add((int) data9[row][0]);
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
	
	
	
	
	
	
	
	
	
	
	
	// Get the following from each row-------------------------------------------------------------------------------------
	private Boolean are_all_static_identifiers_matched(String var_name, List<List<String>> static_identifiers) {	
		if (!static_identifiers.get(0).contains(Get_Variable_Information.get_layer1(var_name))) return false;
		if (!static_identifiers.get(1).contains(Get_Variable_Information.get_layer2(var_name))) return false;
		if (!static_identifiers.get(2).contains(Get_Variable_Information.get_layer3(var_name))) return false;
		if (!static_identifiers.get(3).contains(Get_Variable_Information.get_layer4(var_name))) return false;
		if (Get_Variable_Information.get_forest_status(var_name).equals("E") && !Get_Variable_Information.get_method(var_name).equals("MS") && !Get_Variable_Information.get_method(var_name).equals("BS")) {
			if (!static_identifiers.get(4).contains(Get_Variable_Information.get_layer5(var_name))) return false;	// layer5 cover type
			if (!static_identifiers.get(5).contains(Get_Variable_Information.get_layer6(var_name))) return false;	// layer6: size class
		}
		if (!static_identifiers.get(6).contains(Get_Variable_Information.get_method(var_name) + "_" + Get_Variable_Information.get_forest_status(var_name))) return false;
		if (!static_identifiers.get(7).contains(String.valueOf(Get_Variable_Information.get_period(var_name)))) return false;					
		return true;
	}
	
	
	private List<List<String>> get_static_identifiers_in_row (String current_static_identifiers) {
		List<List<String>> static_identifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] staticLayer_Info = current_static_identifiers.split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_staticIdentifiers = staticLayer_Info.length;
		
		//Get all static Identifiers to be in the list
		for (int i = 0; i < total_staticIdentifiers; i++) {		//6 first identifiers is strata 6 layers (layer 0 to 5)		
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = staticLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			static_identifiers.add(thisIdentifier);
		}
			
		return static_identifiers;
	}
	
	
	private List<List<String>> get_dynamic_identifiers_in_row (String current_dynamic_identifiers) {
		List<List<String>> dynamic_identifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] dynamicLayer_Info = current_dynamic_identifiers.split(";");
		int total_dynamicIdentifiers = dynamicLayer_Info.length;
	
		
		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamicIdentifiers; i++) {	
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = dynamicLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier column index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			dynamic_identifiers.add(thisIdentifier);
		}
			
		return dynamic_identifiers;
	}	
	
	
	private List<String> get_dynamic_dentifiers_column_indexes_in_row (String current_dynamic_identifiers) {
		List<String> dynamic_dentifiers_column_indexes = new ArrayList<String>();
			
		//Read the whole cell into array
		String[] dynamicLayer_Info = current_dynamic_identifiers.split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_dynamicIdentifiers = dynamicLayer_Info.length;

		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamicIdentifiers; i++) {	
			String[] identifierElements = dynamicLayer_Info[i].split("\\s+");				//space delimited
			//add the first element which is the identifier column index
			dynamic_dentifiers_column_indexes.add(identifierElements[0].replaceAll("\\s+",""));
		}
			
		return dynamic_dentifiers_column_indexes;
	}	
	
	
	private List<String> get_parameters_indexes_list (String current_parameter_index) {	
		List<String> parameters_indexes_list = new ArrayList<String>();
		
		//Read the whole cell into array
		String[] parameter_Info = current_parameter_index.split("\\s+");			
		for (int i = 0; i < parameter_Info.length; i++) {	
			parameters_indexes_list.add(parameter_Info[i].replaceAll("\\s+",""));
		}				
		return parameters_indexes_list;
	}
	// End of Get the following from each row-------------------------------------------------------------------------------------
	
	
	
	
	
	
	private static int[] get_prescription_and_row(List<String> yield_tables_names_list, String var_name, int var_rotation_age) {
    	int[] array = new int [2];	// first index is prescription, second index is row_id   	
    	int table_id_to_find = -9999, row_id_to_find = -9999;
    	
		String yield_table_name_to_find = Get_Variable_Information.get_yield_table_name_to_find(var_name);	
		if (yield_table_name_to_find.contains("rotation_age")) {
			yield_table_name_to_find = yield_table_name_to_find.replace("rotation_age", String.valueOf(var_rotation_age));
		}		
	
		int id_to_search = Collections.binarySearch(yield_tables_names_list, yield_table_name_to_find);				
		if (id_to_search >= 0) {		// If yield table name exists						
			table_id_to_find = id_to_search;
			row_id_to_find = Get_Variable_Information.get_yield_table_row_index_to_find(var_name);
		}		
		
		array[0] = table_id_to_find;
		array[1] = row_id_to_find;
		return array;
	}			
	
	
	private class Querry_Optimal_Solution {
		
		private double get_results(Read_Database read_database, String[] vname, double[] vvalue,			// This vname is the array after filtered by static_identifiers
				double multiplier, List<String> parameters_indexes_list, List<String> dynamic_dentifiers_column_indexes, List<List<String>> dynamic_identifiers) {		
			
			double sum_all = 0;
			
						
			// Database Info
			Object[][][] yield_tables_values = read_database.get_yield_tables_values();
			Object[] yield_tables_names = read_database.get_yield_tables_names();
			List<String> yield_tables_names_list = new ArrayList<String>() {{ for (Object i : yield_tables_names) add(i.toString());}};		// Convert Object array to String list
			
								
			// Read input files to retrieve values later
			Read_RunInputs read = new Read_RunInputs();
			read.read_general_inputs(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_01_general_inputs.txt"));
			read.read_model_strata(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_03_model_strata.txt"));
			read.read_covertype_conversion_clearcut(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_04_covertype_conversion_clearcut.txt"));
			read.read_covertype_conversion_replacing(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_05_covertype_conversion_replacing.txt"));
			read.read_natural_disturbances_replacing(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_07_natural_disturbances_replacing.txt"));
			read.read_management_cost(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_08_management_cost.txt"));
						
			
			// Get info: input_03_modeled_strata
			List<String> model_strata = new ArrayList<String>();
			model_strata = read.get_model_strata();
						
	
			// Get Info: input_05_covertype_conversion_replacing
			double[] rdProportion = read.getRDProportion(); 		
			
			// Get Info: input_07_natural_disturbances_replacing
			double[][] SRD_percent = read.getSRDProportion();		
			
			// Get info: input_08_management_cost
			List<String> cost_condition_list = read.get_cost_condition_list(); 
			Get_Cost_Information cost_info = null;
			if (cost_condition_list != null) cost_info = new Get_Cost_Information(read_database, cost_condition_list);
				
			
			
			// Set up problem-------------------------------------------------			
			List<List<String>> allLayers =  read_database.get_allLayers();	
			List<String> layer1 = allLayers.get(0);
			List<String> layer2 = allLayers.get(1);
			List<String> layer3 = allLayers.get(2);
			List<String> layer4 = allLayers.get(3);
			List<String> layer5 = allLayers.get(4);
			List<String> layer6 = allLayers.get(5);
						
			
			
			double annualDiscountRate = read.get_discount_rate() / 100;
			int SRDage;
			
	

			//Get the 2 parameter V(s1,s2,s3,s4,s5,s6) and A(s1,s2,s3,s4,s5,s6)
			String[][] Input2_value = read.get_MO_Values();	
			double[] strata_area = new double[model_strata.size()];
			int[] starting_age = new int[model_strata.size()];			
			
			
			
			// Loop through all modeled_strata to find if the names matched and get the total area and age class
			for (int id = 0; id < model_strata.size(); id++) {
				strata_area[id] = Double.parseDouble(Input2_value[id][7]);		// area in acres
				if (Input2_value[id][read.get_MO_TotalColumns() - 2].toString().equals("null")) {
					starting_age[id] = 1;		// assume age_class = 1 if not found any yield table for this existing strata
				} else {
					starting_age[id] = Integer.parseInt(Input2_value[id][read.get_MO_TotalColumns() - 2]);	// age_class
				}		
			}						
			
			
	
			// Get the replacing disturbances %		
			double[][] rdPercent = new double[layer5.size()][layer5.size()];
			int item_Count = 0;
			for (int s5 = 0; s5 < layer5.size(); s5++) {
				for (int ss5 = 0; ss5 < layer5.size(); ss5++) {
					rdPercent[s5][ss5] = rdProportion[item_Count];
					item_Count++;	
				}
			}
			
	
			
			
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------									
			// Define arrays containing prescription, row_id, cost_value of variables
			int[] var_prescription = new int[vname.length];
			int[] var_row_id  = new int[vname.length];
			double[] var_cost_value  = new double[vname.length];
			
			for (int i = 0; i < vname.length; i++) {
				int var_rotation_age = -9999;	// If variables is not EA variable	
				
				if (vname[i].startsWith("xEA_E_")) {
					String strata = Get_Variable_Information.get_layer1(vname[i])
							+ Get_Variable_Information.get_layer2(vname[i])
							+ Get_Variable_Information.get_layer3(vname[i])
							+ Get_Variable_Information.get_layer4(vname[i])
							+ Get_Variable_Information.get_layer5(vname[i])
							+ Get_Variable_Information.get_layer6(vname[i]);
					int strata_id = Collections.binarySearch(model_strata, strata);							
					var_rotation_age = Get_Variable_Information.get_rotation_period(vname[i]) + starting_age[strata_id] - 1;	// rotationAge = tR + starting_age[strata_id] - 1;
				} else if (vname[i].startsWith("xEA_R_")) {
					var_rotation_age = Get_Variable_Information.get_rotation_age(vname[i]); 
				} 
				
				int[] prescription_and_row = get_prescription_and_row(yield_tables_names_list, vname[i], var_rotation_age);
				var_prescription[i] = prescription_and_row[0];
				var_row_id[i] = prescription_and_row[1];
				var_cost_value[i] = -9999;	// Initialize it as -999 indicating not calculated yet
			}	
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			
					
			
			// Constraints 15-------------------------------------------------	
			// Loop all variables that were trim by the static filter already: then add to sum_all
			for (int i = 0; i < vname.length; i++) {
				String var_name	= vname[i];				
				// Add user_defined_variables and parameters------------------------------------
				String method = Get_Variable_Information.get_method(var_name) + "_" + Get_Variable_Information.get_forest_status(var_name);
				int period = Get_Variable_Information.get_period(var_name);
				int rotation_period = Get_Variable_Information.get_rotation_period(var_name);			
				String strata = 
						Get_Variable_Information.get_layer1(var_name) +
						Get_Variable_Information.get_layer2(var_name) +
						Get_Variable_Information.get_layer3(var_name) +
						Get_Variable_Information.get_layer4(var_name) +
						Get_Variable_Information.get_layer5(var_name) +
						Get_Variable_Information.get_layer6(var_name);
				
				
						
				
				//Add xNGe
				if (method.equals("NG_E")) {	
					int strata_id = Collections.binarySearch(model_strata, strata);		// Note we need index from model_strata not common_strata, the same is applied for other 6 layers strata
					int s5 = layer5.indexOf(strata.substring(4,5));					
					int t = period;
					//Find all parameter match the t and add them all to parameter
					/*	Table Name = s5 + s6 convert then + method + timingChoice
					 * Table column indexes for the parameters is identified by parameters_indexes_list
					 * dynamic identifiers are identified by "all_dynamicIdentifiers_columnIndexes" & "all_dynamicIdentifiers"
					 * Table row index = t - 1  
					 */		
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = starting_age[strata_id] + t - 1; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int c = 0; c < layer5.size(); c++) {
							if (rdPercent[s5][c] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(c) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][c] / 100);
							}														
						}
						
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i], 
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}	
						cost_value = var_cost_value[i] * currentDiscountValue;
					}
																																					
																						
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
									
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];
				}	
	
						
				//Add xPBe[s1][s2][s3][s4][s5][s6][i][t]			
				if (method.equals("PB_E")) {		
					int strata_id = Collections.binarySearch(model_strata, strata);
					int s5 = layer5.indexOf(strata.substring(4,5));
					int t = period;
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = starting_age[strata_id] + t - 1; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int c = 0; c < layer5.size(); c++) {
							if (rdPercent[s5][c] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(c) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][c] / 100);
							}														
						}
																		
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
									
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];	
				}	
							
				
				//Add xGSe[s1][s2][s3][s4][s5][s6][i][t]			
				if (method.equals("GS_E")) {		
					int strata_id = Collections.binarySearch(model_strata, strata);
					int s5 = layer5.indexOf(strata.substring(4,5));					
					int t = period;					
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = starting_age[strata_id] + t - 1; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int c = 0; c < layer5.size(); c++) {
							if (rdPercent[s5][c] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(c) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][c] / 100);
							}														
						}
																		
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
									
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];	
				}														
	
						
				//Add xMS[s1][s2][s3][s4][s5][s6][i][t]			
				if (method.equals("MS_E")) {					
					int t = period;
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						// Note: no stand replacing disturbance here --> no conversion cost due to replacing disturbance in MS area
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
										
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];	
				}																
				
				
				//Add xBS[s1][s2][s3][s4][s5][s6][i][t]			
				if (method.equals("BS_E")) {				
					int t = period;
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						// Note: no stand replacing disturbance here --> no conversion cost due to replacing disturbance in BS area
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
										
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];	
				}							
								
				
				// Add xEAe[s1][s2][s3][s4][s5][s6](tR)(s5R)(i)(t)
				if (method.equals("EA_E")) {									
					int strata_id = Collections.binarySearch(model_strata, strata);
					int s5 = layer5.indexOf(strata.substring(4,5));	
					int t = period;									
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = starting_age[strata_id] + t - 1; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int s5RR = 0; s5RR < layer5.size(); s5RR++) {
							if (rdPercent[s5][s5RR] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5RR) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5RR] / 100);
							}														
						}

						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];						
				}	
				
				
				//Add xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]
				if (method.equals("EA_R")) {		
					int s5 = layer5.indexOf(strata.substring(4,5));						
					int t = period;
					int tR = rotation_period;
					int aR = Get_Variable_Information.get_rotation_age(var_name);
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = aR - tR + t; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int s5RR = 0; s5RR < layer5.size(); s5RR++) {
							if (rdPercent[s5][s5RR] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5RR) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5RR] / 100);
							}														
						}
						
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];						
				}					
				
	
				//Add xNGr
				if (method.equals("NG_R")) {		
					int s5 = layer5.indexOf(strata.substring(4,5));						
					int t = period;
					int a = Get_Variable_Information.get_age(var_name);
					

					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = a; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int s5R = 0; s5R < layer5.size(); s5R++) {
							if (rdPercent[s5][s5R] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5R] / 100);
							}														
						}
						
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];	
				}							
				
	
				//Add xPBr
				if (method.equals("PB_R")) {		
					int s5 = layer5.indexOf(strata.substring(4,5));						
					int t = period;
					int a = Get_Variable_Information.get_age(var_name);
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = a; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int s5R = 0; s5R < layer5.size(); s5R++) {
							if (rdPercent[s5][s5R] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5R] / 100);
							}														
						}
																			
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];						
				}						
	
				
				//Add xGSr
				if (method.equals("GS_R")) {		
					int s5 = layer5.indexOf(strata.substring(4,5));						
					int t = period;
					int a = Get_Variable_Information.get_age(var_name);
					
					
					double cost_value = 0;									
					
					if (parameters_indexes_list.contains("CostParameter") && cost_info != null) {
						double currentDiscountValue = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
																	
						List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
						List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
						SRDage = a; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						for (int s5R = 0; s5R < layer5.size(); s5R++) {
							if (rdPercent[s5][s5R] / 100 > 0) {
								coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
								coversion_cost_after_disturbance_value_list.add(rdPercent[s5][s5R] / 100);
							}														
						}
																	
						if (var_cost_value[i] == -9999) {
							var_cost_value[i] = cost_info.get_cost_value(
									vname[i],  
									var_prescription[i], 
									var_row_id[i], yield_tables_values,
									cost_condition_list, coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
						}
						cost_value = var_cost_value[i] * currentDiscountValue; 
					}
					
					
					double para_value = Get_Parameter_Information.get_total_value(
							read_database, vname[i], 
							var_prescription[i], 
							var_row_id[i],
							yield_tables_values, parameters_indexes_list,
							dynamic_dentifiers_column_indexes, dynamic_identifiers,
							cost_value);
					para_value = para_value * multiplier;
				
					
					//Add to sum_all
					sum_all = sum_all + para_value * vvalue[i];						
				}		
			}		
			
			return sum_all;	
		}			
	}
	
}	


