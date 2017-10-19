package prismProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

import prismConvenienceClass.ColorUtil;
import prismConvenienceClass.FilesHandle;
import prismConvenienceClass.IconHandle;
import prismConvenienceClass.PrismGridBagLayoutHandle;
import prismConvenienceClass.PrismTableModel;
import prismConvenienceClass.PrismTitleScrollPane;
import prismConvenienceClass.ToolBarWithBgImage;
import prismRoot.PrismMain;

public class Panel_EditRun_Details extends JLayeredPane implements ActionListener {
	private JSplitPane GUI_Text_splitPanel ;
	private JPanel radioPanel_Right; 
	private ButtonGroup radioGroup_Right; 
	private JRadioButton[] radioButton_Right; 
	
	private File currentRunFolder;
	private File file_database;
	
	private boolean is_first_time_loaded = true;
	
	//6 panels for the selected Run
	private General_Inputs_GUI paneL_General_Inputs_GUI;
	private General_Inputs_Text panel_General_Inputs_Text;
	private Silviculture_Method_GUI panel_Silviculture_Method_GUI;
	private Silviculture_Method_Text panel_Silviculture_Method_Text;
	private Model_Strata_GUI panel_Model_Strata_GUI;
	private Model_Strata_Text panel_Model_Strata_Text;
	private Covertype_Conversion_GUI panel_Covertype_Conversion_GUI;
	private Covertype_Conversion_Text panel_Covertype_Conversion_Text;
	private Natural_Disturbances_GUI panel_Natural_Disturbances_GUI;
	private Natural_Disturbances_Text panel_Natural_Disturbances_Text;
	private Management_Cost_GUI panel_Management_Cost_GUI;
	private Management_Cost_Text panel_Management_Cost_Text;
	private Basic_Constraints_GUI panel_Basic_Constraints_GUI;
	private Basic_Constraints_Text panel_Basic_Constraints_Text;
	private Flow_Constraints_GUI panel_Flow_Constraints_GUI;
	private Flow_Constraints_Text panel_Flow_Constraints_Text;
	
	
	private Read_Database read_database;
	List<String> layers_Title;
	List<String> layers_Title_ToolTip;
	List<List<String>> allLayers;
	List<List<String>> allLayers_ToolTips;
	private Object[][][] yieldTable_values;
	private String [] yieldTable_ColumnNames;
	
	
	private int totalPeriod;
	
	
	//table Models OverView
	private boolean is_table_overview_loaded = false;
	private int rowCount_overview, colCount_overview;
	private String[] columnNames_overview;
	private JTable table_overview;
	private PrismTableModel model_overview;
	private Object[][] data_overview;
	private double modeledAcres, availableAcres;
	
	//table input_01_general_inputs.txt
	private boolean is_table1_loaded = false;
	private int rowCount1, colCount1;
	private String[] columnNames1;
	private JTable table1;
	private PrismTableModel model1;
	private Object[][] data1;
	
	//table input_02_silviculture_method.txt
	private boolean is_table2_loaded = false;
	private int rowCount2, colCount2;
	private String[] columnNames2;
	private JTable table2;
	private PrismTableModel model2;
	private Object[][] data2;
	
	//table input_03_model_strata.txt
	private boolean is_table3_loaded = false;
	private int rowCount3, colCount3;
	private String[] columnNames3;
	private JTable table3;
	private PrismTableModel model3;
	private Object[][] data3;
	
	//table input_04_covertype_conversion_clearcut.txt
	private boolean is_table4_loaded = false;
	private int rowCount4, colCount4;
	private String[] columnNames4;
	private JTable table4;
	private PrismTableModel model4;
	private Object[][] data4;
	
	//table input_05_covertype_conversion_replacing.txt
	private boolean is_table5_loaded = false;
	private int rowCount5, colCount5;
	private String[] columnNames5;
	private JTable table5;
	private PrismTableModel model5;
	private Object[][] data5;

	//table input_06_natural_disturbances_non_replacing.txt
	private boolean is_table6_loaded = false;
	private int rowCount6, colCount6;
	private String[] columnNames6;
	private JTable table6;
	private PrismTableModel model6;
	private Object[][] data6;
	
	//table input_07_natural_disturbances_replacing.txt
	private boolean is_table7_loaded = false;
	private int rowCount7, colCount7;
	private String[] columnNames7;
	private JTable table7;
	private PrismTableModel model7;
	private Object[][] data7;

	//table input_08a_action_cost.txt
	private boolean is_table8a_loaded = false;
	private int rowCount8a, colCount8a;
	private String[] columnNames8a;
	private JTable table8a;
	private PrismTableModel model8a;
	private Object[][] data8a;
	
	//table input_08b_conversion_cost.txt
	private boolean is_table8b_loaded = false;
	private int rowCount8b, colCount8b;
	private String[] columnNames8b;
	private JTable table8b;
	private PrismTableModel model8b;
	private Object[][] data8b;	
	
	//table input_08_management_cost.txt
	private boolean is_table8_loaded = false;
	private int rowCount8, colCount8;
	private String[] columnNames8;
	private JTable table8;
	private PrismTableModel model8;
	private Object[][] data8;
	
	//table input_09_basic_constraints.txt
	private boolean is_table9_loaded = false;
	private int rowCount9, colCount9;
	private String[] columnNames9;
	private JTable table9;
	private PrismTableModel model9;
	private Object[][] data9;
	
	//table input_10_advanced_constraints.txt
	private boolean is_table10_loaded = false;
	private int rowCount10, colCount10;
	private String[] columnNames10;
	private JTable table10;
	private PrismTableModel model10;
	private Object[][] data10;	
	
	//readme
	private JTextArea readme = new TextArea_ReadMe("minionWrite.png", 70, 70);
	
	
	private JButton button_import_database;
	private JButton button_select_Strata;
	
	public Panel_EditRun_Details(File RunFolder) {
		super.setLayout(new BorderLayout());	
		currentRunFolder = RunFolder;		//Get information from the run
		reload_inputs_before_creating_GUI();
		
		
		//Create the interface ---------------------------------------------------------------------------------------------------------------------
		// Add 6 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
		radioPanel_Right = new JPanel();
		radioPanel_Right.setLayout(new FlowLayout());		
		radioGroup_Right = new ButtonGroup();
		
		radioButton_Right  = new JRadioButton[8];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[2]= new JRadioButton("Silviculture Method");
		radioButton_Right[1]= new JRadioButton("Model Strata");
		radioButton_Right[3]= new JRadioButton("Covertype Conversion");
		radioButton_Right[4]= new JRadioButton("Natural Disturbances");
		radioButton_Right[5]= new JRadioButton("Management Cost");
		radioButton_Right[6]= new JRadioButton("Basic Constraints");
		radioButton_Right[7]= new JRadioButton("Flow Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < radioButton_Right.length; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
				radioButton_Right[i].addActionListener(this);
		}	
		
		GUI_Text_splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_splitPanel.setDividerSize(0);
			
	
		// Create all new 6 panels for the selected Run--------------------------------------------------
		paneL_General_Inputs_GUI = new General_Inputs_GUI();
		panel_General_Inputs_Text = new General_Inputs_Text();
//		panel_Silviculture_Method_GUI = new Silviculture_Method_GUI();
//		panel_Silviculture_Method_Text = new Silviculture_Method_Text();
//		panel_Model_Strata_GUI = new Model_Strata_GUI();
//		panel_Model_Strata_Text = new Model_Strata_Text();
//		panel_Covertype_Conversion_GUI = new Covertype_Conversion_GUI();
//		panel_Covertype_Conversion_Text = new Covertype_Conversion_Text();
//		panel_Natural_Disturbances_GUI = new Natural_Disturbances_GUI();
//		panel_Natural_Disturbances_Text = new Natural_Disturbances_Text();
//		panel_Management_Cost_GUI = new Management_Cost_GUI();
//		panel_Management_Cost_Text = new Management_Cost_Text();
//		panel_Basic_Constraints_GUI = new Basic_Constraints_GUI();
//		panel_Basic_Constraints_Text = new Basic_Constraints_Text();
//		panel_Flow_Constraints_GUI = new Flow_Constraints_GUI();
//		panel_Flow_Constraints_Text = new Flow_Constraints_Text();
					
		
		// Show the 2 panelInput of the selected Run
		GUI_Text_splitPanel.setLeftComponent(paneL_General_Inputs_GUI);
//		GUI_Text_splitPanel.setRightComponent(panel_General_Inputs_Text);	
		
		
		// Add all components to The Panel------------------------------------------------------------
		super.add(radioPanel_Right, BorderLayout.NORTH);
		super.add(GUI_Text_splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
		

				
		// Load database of the run if exist---------------------------------------------------------------------
		File database_to_load = new File(currentRunFolder.getAbsolutePath() + "/database.db");
		if (database_to_load.exists()) {	//Load if the file exists
			file_database = database_to_load;
			
			// Read the tables (strata_definition, existing_strata, yield_tables) of the database-------------------
			read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
			if (read_database == null) {
				read_database = new Read_Database(file_database);	// Read the database
				PrismMain.get_databases_linkedlist().update(file_database, read_database);			
			}
			
			layers_Title = read_database.get_layers_Title();
			layers_Title_ToolTip = read_database.get_layers_Title_ToolTip();
			allLayers =  read_database.get_allLayers();
			allLayers_ToolTips = read_database.get_allLayers_ToolTips();
			yieldTable_values = read_database.get_yield_tables_values();
			yieldTable_ColumnNames = read_database.get_yield_tables_column_names();
						
			panel_Silviculture_Method_GUI = new Silviculture_Method_GUI();
			panel_Silviculture_Method_Text = new Silviculture_Method_Text();
			panel_Model_Strata_GUI = new Model_Strata_GUI();
			panel_Model_Strata_Text = new Model_Strata_Text();
			panel_Covertype_Conversion_GUI = new Covertype_Conversion_GUI();
			panel_Covertype_Conversion_Text = new Covertype_Conversion_Text();
			panel_Natural_Disturbances_GUI = new Natural_Disturbances_GUI();
			panel_Natural_Disturbances_Text = new Natural_Disturbances_Text();
			panel_Management_Cost_GUI = new Management_Cost_GUI();
			panel_Management_Cost_Text = new Management_Cost_Text();
			panel_Basic_Constraints_GUI = new Basic_Constraints_GUI();
			panel_Basic_Constraints_Text = new Basic_Constraints_Text();
			panel_Flow_Constraints_GUI = new Flow_Constraints_GUI();
			panel_Flow_Constraints_Text = new Flow_Constraints_Text();			
			reload_inputs_after_creating_GUI();
			
			PrismMain.get_Prism_DesktopPane().getSelectedFrame().revalidate();
			PrismMain.get_Prism_DesktopPane().getSelectedFrame().repaint();
		}  else { 	// If file does not exist then use null database
			file_database = null;
			radioButton_Right[1].setEnabled(false);
			radioButton_Right[2].setEnabled(false);
			radioButton_Right[3].setEnabled(false);
			radioButton_Right[4].setEnabled(false);
			radioButton_Right[5].setEnabled(false);
			radioButton_Right[6].setEnabled(false);
			radioButton_Right[7].setEnabled(false);
			System.out.println("File not exists: database.db - New interface is created using default_database.db");					
		}
	} // End Of Panel_EditRun_Details()

		
	// Listener for radio buttons------------------------------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
		for (int j = 0; j < radioButton_Right.length; j++) {
			if (radioButton_Right[j].isSelected()) {		
				if (j == 0) {
					GUI_Text_splitPanel.setLeftComponent(paneL_General_Inputs_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_General_Inputs_Text);
				} else if (j == 2) {
					GUI_Text_splitPanel.setLeftComponent(panel_Silviculture_Method_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Silviculture_Method_Text);
				} else if (j == 1) {
					GUI_Text_splitPanel.setLeftComponent(panel_Model_Strata_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Model_Strata_Text);
				} else if (j == 3) {
					GUI_Text_splitPanel.setLeftComponent(panel_Covertype_Conversion_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_CovertypeConversion_Text);
				} else if (j == 4) {
					GUI_Text_splitPanel.setLeftComponent(panel_Natural_Disturbances_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Natural_Disturbances_Text);
				} else if (j == 5) {
					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Management_Cost_Text);
				} else if (j == 6) {
					GUI_Text_splitPanel.setLeftComponent(panel_Basic_Constraints_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Basic_Constraints_Text);
				} else if (j == 7) {
					GUI_Text_splitPanel.setLeftComponent(panel_Flow_Constraints_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Flow_Constraints_Text);
				}
				
				// Get everything show up nicely
				PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());	//this can replace the below 2 lines
//				PrismMain.get_main().revalidate();
//		    	PrismMain.get_main().repaint(); 
			}
		}
	}


    // Reload inputs of the run------------------------------------------------------------------------------------------------ 
	public void reload_inputs_before_creating_GUI() {		

		// Load tables---------------------------------------------------------------------------------
		File table_file;
		Reload_Table_Info tableLoader;
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_01_general_inputs.txt");
		if (table_file.exists()) {		//Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount1 = tableLoader.get_rowCount();
			colCount1 = tableLoader.get_colCount();
			data1 = tableLoader.get_input_data();
			columnNames1 = tableLoader.get_columnNames();
			is_table1_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_01_general_inputs.txt - New interface is created");
		}
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_02_silviculture_method.txt");
		if (table_file.exists()) {		//Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount2 = tableLoader.get_rowCount();
			colCount2 = tableLoader.get_colCount();
			data2 = tableLoader.get_input_data();
			columnNames2 = tableLoader.get_columnNames();
			is_table2_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_02_silviculture_method.txt - New interface is created");
		}
			
		
//		if (file_ExistingStrata == null && is_this_the_first_load == true) {		//If there is no existing strata, still load the selected strata
//			table_file = new File(currentRunFolder.getAbsolutePath() + "/input_03_model_strata.txt");
//			if (table_file.exists()) { // Load from input
//				tableLoader = new Reload_Table_Info(table_file);
//				rowCount = tableLoader.get_rowCount();
//				colCount = tableLoader.get_colCount();
//				data = tableLoader.get_input_data();
//				columnNames = tableLoader.get_columnNames();
//				is_table_loaded = true;
//				
//	
//	//			//This is strange when only this 1 I have to register the "Yes" - in case we don't use String.ValueOf to compare data (see last lines)
//	//			for (int i = 0; i < rowCount; i++) {
//	//				data[i][colCount-1] = "Yes";
//	//			}			
//			} else { // Create a fresh new if Load fail
//				System.err.println("File not exists: Input 2 - input_03_model_strata.txt - New interface is created");
//			}	
//		}
		
															//Need to change later (not here , below) because I didn't write the whole file, just write the yes case
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_04_covertype_conversion_clearcut.txt");
//		if (table_file.exists()) { // Load from input
//			tableLoader = new Reload_Table_Info(table_file);
//			rowCount4 = tableLoader.get_rowCount();
//			colCount4 = tableLoader.get_colCount();
//			data4 = tableLoader.get_input_data();
//			columnNames4 = tableLoader.get_columnNames();
//			is_table4_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_04_covertype_conversion_clearcut).txt - New interface is created");
//		}

		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_05_covertype_conversion_replacing.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount5 = tableLoader.get_rowCount();
			colCount5 = tableLoader.get_colCount();
			data5 = tableLoader.get_input_data();
			columnNames5 = tableLoader.get_columnNames();
			is_table5_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_05_covertype_conversion_replacing.txt - New interface is created");
		}
		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_06_natural_disturbances_non_replacing.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount6 = tableLoader.get_rowCount();
			colCount6 = tableLoader.get_colCount();
			data6 = tableLoader.get_input_data();
			columnNames6 = tableLoader.get_columnNames();
			is_table6_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_06_natural_disturbances_non_replacing.txt - New interface is created");
		}
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_07_natural_disturbances_replacing.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount7 = tableLoader.get_rowCount();
			colCount7 = tableLoader.get_colCount();
			data7 = tableLoader.get_input_data();
			columnNames7 = tableLoader.get_columnNames();
			is_table7_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_07_natural_disturbances_replacing.txt - New interface is created");
		}		

		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_08_management_cost.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount8 = tableLoader.get_rowCount();
			colCount8 = tableLoader.get_colCount();
			data8 = tableLoader.get_input_data();
			columnNames8 = tableLoader.get_columnNames();
			is_table8_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_08_management_cost.txt - New interface is created");
		}		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_09_basic_constraints.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount9 = tableLoader.get_rowCount();
			colCount9 = tableLoader.get_colCount();
			data9 = tableLoader.get_input_data();
			columnNames9 = tableLoader.get_columnNames();
			is_table9_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_09_basic_constraints.txt - New interface is created");
		}     
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_10_flow_constraints.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount10 = tableLoader.get_rowCount();
			colCount10 = tableLoader.get_colCount();
			data10 = tableLoader.get_input_data();
			columnNames10 = tableLoader.get_columnNames();
			is_table10_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_10_flow_constraints.txt - New interface is created");
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
    
	
	// Reload inputs of the run after all the GUI for all panels are already created-----------------------------------------------
	public void reload_inputs_after_creating_GUI() {
		File table_file;
		Reload_Table_Info tableLoader;
		
		
		if (file_database != null) {
			button_import_database.doClick(); // Trigger   button_import_database.doClick()  if  file_Database != null
		}
				
		
		// Find the data match to paste into Existing Strata		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_03_model_strata.txt");
		if (table_file.exists()) { // Load from input
			// Uncheck all checkboxes in "model_strata"
			for (int i = 0; i < data3.length; i++) {
				data3[i][9] = false;
			}
			
			
			tableLoader = new Reload_Table_Info(table_file);
			
			Object[][] temp_data = tableLoader.get_input_data();
			for (int i = 0; i < temp_data.length; i++) {
				for (int ii = 0; ii < data3.length; ii++) {
					if (   String.valueOf(data3[ii][0]).equals(String.valueOf(temp_data[i][0]))   ) {		//Just need to compare Strata ID
						// Apply temp_data row values to data row 
						for (int jj = 0; jj < data3[ii].length; jj++) {
							data3[ii][jj] = temp_data[i][jj];
						}		
					}	
				}
			}			
			model3.match_DataType();			//a smart way to retrieve the original data type :))))))
			button_select_Strata.setEnabled(true);
			button_select_Strata.doClick();
			
			is_table3_loaded = true;		
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_03_model_strata.txt - New interface is created");
		}	
		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_04_covertype_conversion_clearcut.txt");
		if (table_file.exists()) { // Load from input
			// Uncheck all checkboxes in "implementation" column when the input_04 exists
			for (int i = 0; i < data4.length; i++) {
				data4[i][6] = false;
			}	
			
			 // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			String twocolumnsGUI, twocolumnsInput;
			
			Object[][] temp_data = tableLoader.get_input_data();
			for (int i = 0; i < temp_data.length; i++) {
				twocolumnsInput = String.valueOf(temp_data[i][0]) + String.valueOf(temp_data[i][1]);
				for (int ii = 0; ii < data4.length; ii++) {
					twocolumnsGUI = String.valueOf(data4[ii][0]) + String.valueOf(data4[ii][1]);			
					if (twocolumnsGUI.equals(twocolumnsInput)) {		//Just need to compare 2 columns: cover type & size class
						// Apply temp_data row values to data4 row 
						for (int jj = 0; jj < data4[ii].length; jj++) {
							data4[ii][jj] = temp_data[i][jj];
						}		
					}	
				}
			}	
			
			is_table4_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_04_covertype_conversion_clearcut.txt - New interface is created");
		}
				
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_05_covertype_conversion_replacing.txt");
//		if (table_file.exists()) { // Load from input
//			 // Load from input
//			tableLoader = new Reload_Table_Info(table_file);		
//			Object[][] temp_data = tableLoader.get_input_data();
//			for (int i = 0; i < temp_data.length; i++) {	
//				// Apply temp_data row values to data5 row 
//				for (int j = 0; j < data5[i].length; j++) {
//					data5[i][j] = temp_data[i][j];
//				}	
//			}	
//			is_table5_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_05_covertype_conversion_replacing.txt - New interface is created");
//		}
		
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_06_natural_disturbances_non_replacing.txt");
//		if (table_file.exists()) { // Load from input
//			 // Load from input
//			tableLoader = new Reload_Table_Info(table_file);		
//			Object[][] temp_data = tableLoader.get_input_data();
//			for (int i = 0; i < temp_data.length; i++) {	
//				// Apply temp_data row values to data6 row 
//				for (int j = 0; j < data6[i].length; j++) {
//					data6[i][j] = temp_data[i][j];
//				}	
//			}	
//			is_table6_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_06_natural_disturbances_non_replacing.txt - New interface is created");
//		}
			
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_07_natural_disturbances_replacing.txt");
//		if (table_file.exists()) { // Load from input
//			// Load from input
//			tableLoader = new Reload_Table_Info(table_file);		
//			Object[][] temp_data = tableLoader.get_input_data();
//			for (int i = 0; i < temp_data.length; i++) {	
//				// Apply temp_data row values to data7 row 
//				for (int j = 0; j < data7[i].length; j++) {
//					data7[i][j] = temp_data[i][j];
//				}	
//			}	
//			is_table7_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_07_natural_disturbances_replacing.txt - New interface is created");
//		}		
				
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_08_management_cost.txt");
//		if (table_file.exists()) { // Load from input
//			 // Load from input
//			tableLoader = new Reload_Table_Info(table_file);		
//			Object[][] temp_data = tableLoader.get_input_data();
//			for (int i = 0; i < temp_data.length; i++) {	
//				// Apply temp_data row values to data8 row 
//				for (int j = 0; j < data8[i].length; j++) {
//					data8[i][j] = temp_data[i][j];
//				}	
//			}	
//			is_table8_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_08_management_cost.txt - New interface is created");
//		}
				
		
				
		// Matching data types after finishing reloads
		if (data2 != null) model2.match_DataType();		//a smart way to retrieve the original data type :))))))
		model3.match_DataType();		//a smart way to retrieve the original data type :))))))	
		model4.match_DataType();		//a smart way to retrieve the original data type :))))))
		model5.match_DataType();		//a smart way to retrieve the original data type :))))))
		model6.match_DataType();		//a smart way to retrieve the original data type :))))))
		model7.match_DataType();		//a smart way to retrieve the original data type :))))))
		model8a.match_DataType();		//a smart way to retrieve the original data type :))))))
		model8b.match_DataType();		//a smart way to retrieve the original data type :))))))
		model8.match_DataType();		//a smart way to retrieve the original data type :))))))
		model9.match_DataType();		//a smart way to retrieve the original data type :))))))
		model10.match_DataType();		//a smart way to retrieve the original data type :))))))
	}									
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------  
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table_overview() {
		//Setup the table--------------------------------------------------------------------------------
		rowCount_overview = 5;
		colCount_overview = 2;
		data_overview = new Object[rowCount_overview][colCount_overview];
        columnNames_overview= new String[] {"Description" , "Value"};
		
		// Populate the data matrix
		data_overview[0][0] = "existing strata   --o--   existing acres";
		data_overview[1][0] = "model strata   --o--   model acres";
		data_overview[2][0] = "highlighted strata   --o--   highlighted acres";
		data_overview[3][0] = "prescriptions in your database";
		data_overview[4][0] = "existing strata without NG_E prescriptions";
		
		
		
		//Create a table
        model_overview = new PrismTableModel(rowCount_overview, colCount_overview, data_overview, columnNames_overview);
        table_overview = new JTable(model_overview) {
        	@Override
			protected void paintComponent(Graphics g) {					
				Graphics2D g2d = (Graphics2D) g.create();
				// Fill the background, this is VERY important. Fail to do this and you will have major problems
				g2d.setColor(getBackground());
				g2d.fillRect(0, 0, getWidth(), getHeight());
				// Draw the background
				ImageIcon bgImage = IconHandle.get_scaledImageIcon(70, 70, "minionDance.png");
				Dimension size = this.getSize();
				g2d.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth(), size.height - bgImage.getIconHeight(), this);
				// Paint the component content, i.e. the text
				getUI().paint(g2d, this);
				g2d.dispose();
			}
        };
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table_overview.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//      table_overview.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_overview.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table_overview.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        table_overview.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        table_overview.setTableHeader(null);
        table_overview.setPreferredScrollableViewportSize(new Dimension(350, 120));
        table_overview.setFillsViewportHeight(true);
	}
    
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table1() {	
		//Setup the table------------------------------------------------------------	
		if (is_table1_loaded == false) { // Create a fresh new if Load fail
			rowCount1 = 6;
			colCount1 = 2;
			data1 = new Object[rowCount1][colCount1];
			columnNames1 = new String[] { "description", "selection" };
			
			// Populate the data matrix
			data1[0][0] = "Total planning periods (decades)";			
			data1[1][0] = "Annual discount rate (%)";
			data1[2][0] = "Solver for optimization";
			data1[3][0] = "Maximum solving time (minutes)";
			data1[4][0] = "Export original problem file";
			data1[5][0] = "Export original solution file";
		}
			
		//Create a table-------------------------------------------------------------
        model1 = new PrismTableModel(rowCount1, colCount1, data1, columnNames1);
        table1 = new JTable(model1);
		
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table1.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table1.getTableHeader().setReorderingAllowed(false);		//Disable columns move     
		table1.getColumnModel().getColumn(0).setPreferredWidth(250);	//Set width of 1st Column bigger
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);	//Set width of 2nd Column bigger
//		table1.setTableHeader(null);
		table1.setPreferredScrollableViewportSize(new Dimension(400, 100));
//		table1.setFillsViewportHeight(true);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table2() {		
		//Setup the table------------------------------------------------------------	
		if (is_table2_loaded == false) { // Create a fresh new if Load fail				
			rowCount2 = 0;
			colCount2 = 5;
			data2 = new Object[rowCount2][colCount2];
			columnNames2 = new String[] {"sm_id", "sm_description", "sm_static_identifiers", "sm_method_choice", "sm_implementation"};	
		}
					
		
		//Create a table-------------------------------------------------------------		
		model2 = new PrismTableModel(rowCount2, colCount2, data2, columnNames2) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;	// column 0 accepts only Integer
				else if (c == colCount2 - 1) return Boolean.class;	// last column accepts only Boolean
				else return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 1 || col == colCount2 - 1) { 	// Only the 2nd and the last column is editable
					return true;
				} else {
					return false;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data2[row][col] = value;
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount2; row++) {
					for (int col = 0; col < colCount2; col++) {
						if (String.valueOf(data2[row][col]).equals("null")) {
							data2[row][col] = null;
						} else {					
							if (col == 0) {		// column 0 is Integer
								try {
									data2[row][col] = Integer.valueOf(String.valueOf(data2[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table2");
								}	
							} else if (col == colCount2 - 1) {		// lastColumn is Boolean
								try {
									data2[row][col] = Boolean.valueOf(String.valueOf(data2[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Boolean values in create_table2");
								}
							} else {	//All other columns are String
								data2[row][col] = String.valueOf(data2[row][col]);
							}
						}	
					}	
				}	
			}
		};
		
		
		
		table2 = new JTable(model2) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table2.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table2,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}	
		};

    

        // Set up Type for the last column    
		((JComponent) table2.getDefaultRenderer(Boolean.class)).setOpaque(true);	// It's a bug in the synth-installed renderer, quick hack is to force the rendering checkbox opacity to true
		table2.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table2.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table2.setPreferredScrollableViewportSize(new Dimension(250, 20));
	}		
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table3() {
		//Setup the table------------------------------------------------------------	
		if (is_table3_loaded == false) { // Create a fresh new if Load fail				
			rowCount3 = 0;
			colCount3 = layers_Title.size() + 4;
			columnNames3 = new String[colCount3];

			columnNames3[0] = "strata_id";		//add for the name of strata
			for (int i = 0; i < layers_Title.size(); i++) {
				columnNames3[i+1] = layers_Title.get(i);			//add 6 layers to the column header name
			}
	         
			columnNames3[colCount3 - 3] = "acres";	//add 3 more columns
			columnNames3[colCount3 - 2] = "ageclass";
			columnNames3[colCount3 - 1] = "model_strata";	
		}		
					
		
		//Create a table-------------------------------------------------------------
		model3 = new PrismTableModel(rowCount3, colCount3, data3, columnNames3) {
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col < colCount3 - 1) { // Only the last column is editable
					return false;
				} else {
					return true;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data3[row][col] = value;
				update_model_overview();
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount3; row++) {
					for (int col = 0; col < colCount3; col++) {
						if (String.valueOf(data3[row][col]).equals("null")) {
							data3[row][col] = null;
						} else {					
							if (col == colCount3 - 3) {			//column "Total Acres" accepts only Double
								try {
									data3[row][col] = Double.valueOf(String.valueOf(data3[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table3");
								}	
							} else if (col == colCount3 - 2) {			//column "Age Class" accepts only Integer
								try {
									data3[row][col] = Integer.valueOf(String.valueOf(data3[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table3");
								}	
							} else if (col == colCount3 - 1) {			//last column "model_strata" accepts only Boolean
								try {
									data3[row][col] = Boolean.valueOf(String.valueOf(data3[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Boolean values in create_table3");
								}	
							} else {	//All other columns are String
								data3[row][col] = String.valueOf(data3[row][col]);
							}
						}	
					}	
				}
				if (data3 != null) {
					model3.setValueAt(data3[0][0], 0, 0);	// this is just to trigger the update_model_overview
				}
			}
			
			public void update_model_overview() {  
				// de-select all model_strata that has not found starting age-class
				for (int row = 0; row < rowCount3; row++) {
					if (data3[row][colCount3 - 2] == null) {
						data3[row][colCount3 - 1] = false;
					}
				}
				
				// Update Model OverView table
				int modeledStrata = 0;
				for (int row = 0; row < rowCount3; row++) {
					if (data3[row][colCount3 - 1] != null && (boolean) data3[row][colCount3 - 1] == true)	modeledStrata = modeledStrata + 1;
				}
				
				
				modeledAcres = 0;
				availableAcres = 0;
				for (int row = 0; row < rowCount3; row++) {
					if (data3[row][colCount3 - 1] != null && (boolean) data3[row][colCount3 - 1] == true)	modeledAcres = modeledAcres + Double.parseDouble(data3[row][colCount3 - 3].toString());
					availableAcres = availableAcres + Double.parseDouble(data3[row][colCount3 - 3].toString());
				}
				
				
		        int total_yieldtable =0;
		        for (int row = 0; row < rowCount3; row++) {				        	
		        	if (data3[row][colCount3 - 2] == null) {
		        		total_yieldtable = total_yieldtable + 1;
		        	}
				}
		        
		        
		        data_overview[0][1] = rowCount3 + "   --o--   " + availableAcres;
				data_overview[1][1] = modeledStrata + "   --o--   " + modeledAcres;
		        data_overview[3][1] = yieldTable_values.length;
		        data_overview[4][1] = total_yieldtable;
				model_overview.fireTableDataChanged();
			}
		};
		
		
		

		table3 = new JTable(model3) {
//			// Implement table cell tool tips
//			public String getToolTipText(MouseEvent e) {
//				String tip = null;
//				java.awt.Point p = e.getPoint();
//				int rowIndex = rowAtPoint(p);
//				int colIndex = columnAtPoint(p);
//				try {
//					tip = getValueAt(rowIndex, colIndex).toString();
//				} catch (RuntimeException e1) {
//					System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//				}
//				return tip;
//			}
		};

		
		((JComponent) table3.getDefaultRenderer(Boolean.class)).setOpaque(true);	// It's a bug in the synth-installed renderer, quick hack is to force the rendering checkbox opacity to true
		((AbstractButton) table3.getDefaultRenderer(Boolean.class)).setSelectedIcon(IconHandle.get_scaledImageIcon(12, 12, "icon_check.png"));
//		((AbstractButton) table3.getDefaultRenderer(Boolean.class)).setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_whitebox.png"));
		
		
//		table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	  
		table3.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//		table3.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
        table3.getColumnModel().getColumn(colCount3 - 1).setPreferredWidth(100);	//Set width of Column "Strata in optimization model" bigger
		
		table3.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table3.setFillsViewportHeight(true);
	}	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table4() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
	
		//Setup the table------------------------------------------------------------	
		if (is_table4_loaded == false) { // Create a fresh new if Load fail				
			rowCount4 = total_CoverType * total_CoverType;
			colCount4 = 7;
			data4 = new Object[rowCount4][colCount4];
	        columnNames4= new String[] {"covertype_before_cut", "covertype_after_cut", "min_age_cut_existing", "max_age_cut_existing", "min_age_cut_regeneration", "max_age_cut_regeneration", "implementation"};
			
			// Populate the data matrix
	        int table_row = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_CoverType; j++) {
					data4[table_row][0] = allLayers.get(4).get(i);
					data4[table_row][1] = allLayers.get(4).get(j);	
					data4[table_row][2] = 20;
					data4[table_row][3] = 24;
					data4[table_row][4] = 10;
					data4[table_row][5] = 15;
					if (i==j) data4[table_row][6] = true; 
					table_row++;
				}
			}
		}
			
		
		//Create a table-------------------------------------------------------------
		model4 = new PrismTableModel(rowCount4, colCount4, data4, columnNames4) {			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col < 2) { // Only the last 5 columns are editable
					return false;
				} else {
					return true;
				}
			}

			@Override
			public void setValueAt(Object value, int row, int col) {
				data4[row][col] = value;
//				fireTableDataChanged();		// No need this because it will clear the selection, 
											// With button do task for multiple row we need fire the change outside of this class, so the change can show up in the GUI
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount4; row++) {
					for (int col = 0; col < colCount4; col++) {
						if (String.valueOf(data4[row][col]).equals("null")) {
							data4[row][col] = null;
						} else {					
							if (col >= 2 && col <= 5) {			//Column 2 to 5 are Integer
								try {
									data4[row][col] = Integer.valueOf(String.valueOf(data4[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table4");
								}	
							} else if (col == 6) {			//column "implementation" accepts only Boolean
								try {
									data4[row][col] = Boolean.valueOf(String.valueOf(data4[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Boolean values in create_table4");
								}							
							} else {	//All other columns are String
								data4[row][col] = String.valueOf(data4[row][col]);
							}
						}	
					}	
				}	
			}
		};
		
		
		
		table4 = new JTable(model4) {
			// Implement table cell tool tips
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				if (colIndex < 2) {
					try {
						tip = getValueAt(rowIndex, colIndex).toString();
						for (int i = 0; i < total_CoverType; i++) {
							if (tip.equals(allLayers.get(4).get(i)))	tip=allLayers_ToolTips.get(4).get(i);							
						}
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}
				return tip;
			}
		};
		
		
		
		
		
		// Define a set of icon for some columns
		ImageIcon[] imageIconArray = new ImageIcon[colCount4];
		for (int i = 0; i < colCount4; i++) {
			if (i >= 2) {
				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
			}
		}
		
		
		// Define a set of background color for all rows
		Color[] rowColor = new Color[rowCount4];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);
		Color currentColor = color2;
		int rCount = 0;

		for (int i = 0; i < total_CoverType; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			for (int j = 0; j < total_CoverType; j++) {
				rowColor[rCount] = currentColor;
				rCount++;
			}
		}
		
	
		
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setForeground(Color.RED);
				setHorizontalAlignment(JLabel.LEFT);
				// setFont(getFont().deriveFont(Font.BOLD));
                	
				setBackground(rowColor[row]);		//Set cell background color	
				if (isSelected) {
					setBackground(table.getSelectionBackground());		//Set background color	for selected row
				}
				setIcon(imageIconArray[column]);	// Set icons for cells in some columns
				setIconTextGap(15);		// Set the distance between icon and the actual data value
                return this;
            }
        };
        
        
        
		for (int i = 0; i < columnNames4.length - 5; i++) {		//Except the last 5 column
			table4.getColumnModel().getColumn(i).setCellRenderer(r);
		}		
		
		
		
//		// Set up Icon for column headers
//		class JComponentTableCellRenderer implements TableCellRenderer {
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				return (JComponent) value;
//			}
//		}
//        
//		TableCellRenderer r2 = new JComponentTableCellRenderer();
//       
//		for (int i = 0; i < columnNames4.length; i++) {			
//			if (i == 2 || i == 3 || i == 4) {
//				table4.getColumnModel().getColumn(i).setHeaderRenderer(r2);
//				table4.getColumnModel().getColumn(i).setHeaderValue(new JLabel(columnNames4[i], icon_scale, JLabel.CENTER));
//			} 
//		}	
		
		
		
		
		
		
		
		// Set up Types for each  Columns-------------------------------------------------------------------------------
		class comboBox_MinAge extends JComboBox {
			public comboBox_MinAge() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 20);
			}
		}
		
		class comboBox_MaxAge extends JComboBox {
			public comboBox_MaxAge() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 24);
			}
		}
		
//		class checkbox_Option extends JCheckBox implements TableCellRenderer {
//			public checkbox_Option() {
//				setHorizontalAlignment(JLabel.CENTER);
//			}
//
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				if (isSelected) {
//					setForeground(table4.getSelectionForeground());
//					setBackground(table4.getSelectionBackground());
//				} else {
//					setForeground(table4.getForeground());
//					setBackground(table4.getBackground());
//				}
//				setSelected((value != null && ((Boolean) value).booleanValue()));
//				this.setOpaque(true);
//				return this;
//			}
//		}
		  
		table4.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_MinAge()));
		table4.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new comboBox_MaxAge()));
		table4.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new comboBox_MinAge()));
		table4.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new comboBox_MaxAge()));
//		table4.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new checkbox_Option()));
		((JComponent) table4.getDefaultRenderer(Boolean.class)).setOpaque(true);	// It's a bug in the synth-installed renderer, quick hack is to force the rendering checkbox opacity to true		
		// End of Set up Types for each  Columns------------------------------------------------------------------------
		

		
		
//      table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table4.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table4.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//      table4.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
		
//      table4.setTableHeader(null);
        table4.setPreferredScrollableViewportSize(new Dimension(400, 100));
//      table4.setFillsViewportHeight(true);
        TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model4);	//Add sorter
		for (int i = 1; i < colCount4; i++) {
			sorter.setSortable(i, false);
			if (i == 0 || i == 1) {			//Only the first 2 columns can be sorted
				sorter.setSortable(i, true);	
			}
		}
		table4.setRowSorter(sorter);
        
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table5() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)		
		
		//Setup the table------------------------------------------------------------	
		if (is_table5_loaded == false) { // Create a fresh new if Load fail				
			rowCount5 = total_CoverType*total_CoverType;
			colCount5 = 4;
			data5 = new Object[rowCount5][colCount5];
	        columnNames5= new String[] {"covertype_before_disturbance", "covertype_after_disturbance", "regeneration_weight", "regeneration_percentage"};
			
			// Populate the data matrix
	        int table_row2 = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_CoverType; j++) {
					data5[table_row2][0] = allLayers.get(4).get(i);
					data5[table_row2][1] = allLayers.get(4).get(j);	
					if (i==j) data5[table_row2][2] = (int) 1; else data5[table_row2][2] = (int) 0;
					if (i==j) data5[table_row2][3] = (double) 100.0; else data5[table_row2][3] = (double) 0.0;
					table_row2++;
				}
			}			
		}
			
		
		//Create a table-------------------------------------------------------------		
        model5 = new PrismTableModel(rowCount5, colCount5, data5, columnNames5) {
        	@Override
    		public boolean isCellEditable(int row, int col) {
    			if (col != 2) { // Only column 2 is editable
    				return false;
    			} else {
    				return true;
    			}
    		}

        	@Override
    		public void setValueAt(Object value, int row, int col) {
        		data5[row][col] = value;
        		update_Percentage_column();
    		}  
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount5; row++) {
					for (int col = 0; col < colCount5; col++) {
						if (String.valueOf(data5[row][col]).equals("null")) {
							data5[row][col] = null;
						} else {					
							if (col == 2) {			//Column 2 is Integer
								try {
									data5[row][col] = Integer.valueOf(String.valueOf(data5[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table5");
								}	
							} else if (col == 3) {			//Column 3 is Double
								try {
									data5[row][col] = Double.valueOf(String.valueOf(data5[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table5");
								}	
							} else {	//All other columns are String
								data5[row][col] = String.valueOf(data5[row][col]);
							}
						}	
					}	
				}	
			}
        	
        	public void update_Percentage_column() {     		
				int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
				int table_row=0;
				double[] total_Weight = new double[total_CoverType];
				
				//calculate totalWeight
				for (int i = 0; i < total_CoverType; i++) {
					total_Weight[i] = 0;
					for (int j = 0; j < total_CoverType; j++) {					
						total_Weight[i] = total_Weight[i] + Double.parseDouble(data5[table_row][2].toString());						
						table_row++;
					}	
				}
				
				//Calculate and write percentage
				table_row=0;
				for (int i = 0; i < total_CoverType; i++) {
					for (int j = 0; j < total_CoverType; j++) {					
						data5[table_row][3]	= Double.parseDouble(data5[table_row][2].toString())/total_Weight[i]*100;			
						table_row++;
					}	
				}
				
				// Get selected rows
				int[] selectedRow = table5.getSelectedRows();
				/// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table5.convertRowIndexToModel(selectedRow[i]);
				}
				fireTableDataChanged();
				// Add selected rows back
				for (int i : selectedRow) {
					table5.addRowSelectionInterval(table5.convertRowIndexToView(i), table5.convertRowIndexToView(i));
				}
			}
        };
        
        
        
		table5 = new JTable(model5) {
             //Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				if (table5.getColumnName(colIndex).equals("covertype_before") || table5.getColumnName(colIndex).equals("covertype_after")) {
					try {
						tip = getValueAt(rowIndex, colIndex).toString();
						for (int i = 0; i < total_CoverType; i++) {
							if (tip.equals(allLayers.get(4).get(i)))	tip=allLayers_ToolTips.get(4).get(i);							
						}
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}
				
				if (table5.getColumnName(colIndex).equals("regeneration_weight")) {
					try {
						tip = "Weight of the lost area with cover type "+ getValueAt(rowIndex, 0).toString() 
								+ " to be regenerated as cover type " + getValueAt(rowIndex, 1).toString();
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}	
				
				if (table5.getColumnName(colIndex).equals("regeneration_percentage")) {
					try {
						tip = "Percentage of the lost area with cover type "+ getValueAt(rowIndex, 0).toString() 
								+ " to be regenerated as cover type " + getValueAt(rowIndex, 1).toString();
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}	
				
				return tip;
			}
		};			
        
		
		// Define a set of icon for some columns
		ImageIcon[] imageIconArray = new ImageIcon[colCount5];
		for (int i = 0; i < colCount5; i++) {
			if (i == 2) {
				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
			}
		}
		
		
		// Define a set of background color for all rows
		Color[] rowColor = new Color[rowCount5];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);
		Color currentColor = color2;
		int rCount = 0;

		for (int i = 0; i < total_CoverType; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			for (int j = 0; j < total_CoverType; j++) {
				rowColor[rCount] = currentColor;
				rCount++;
			}
		}
		
	
		
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setForeground(Color.RED);
				setHorizontalAlignment(JLabel.LEFT);
				if (column == 3) {
					setHorizontalAlignment(JLabel.RIGHT);
	        	}
				// setFont(getFont().deriveFont(Font.BOLD));
                	
				setBackground(rowColor[row]);		//Set cell background color
				if (isSelected) {
					setBackground(table.getSelectionBackground());		//Set background color	for selected row
				}
				setIcon(imageIconArray[column]);	// Set icons for cells in some columns
				setIconTextGap(15);		// Set the distance between icon and the actual data value
                return this;
            }
        };						
			
		
		for (int i = 0; i < columnNames5.length; i++) {
			if (i != 2) {
        		table5.getColumnModel().getColumn(i).setCellRenderer(r);
        	} 
		}		
		
		
		// Set up Types for each  Columns-------------------------------------------------------------------------------
		class comboBox_Weight extends JComboBox {
			public comboBox_Weight() {
				for (int i = 0; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 0);
			}
		}
 
		table5.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_Weight()));
		// End of Set up Types for each  Columns------------------------------------------------------------------------		
		
		
		
//      table5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table5.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table5.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//      table5.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        
//      table5.setTableHeader(null);
        table5.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table5.setFillsViewportHeight(true);
        TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model5);	//Add sorter
		for (int i = 1; i < colCount5; i++) {
			sorter.setSortable(i, false);
			if (i == 0) {			//Only the first column can be sorted
				sorter.setSortable(i, true);	
			}
		}
		table5.setRowSorter(sorter);
	}
		
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table6() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		int total_SizeClass = allLayers.get(5).size();		// total number of elements - 1 in layer6 Size Class (0 to...)			
		
		
		//Setup the table------------------------------------------------------------	
		if (is_table6_loaded == false) { // Create a fresh new if Load fail				
			rowCount6 = total_CoverType*total_SizeClass;
			colCount6 = 4;
			data6 = new Object[rowCount6][colCount6];
	        columnNames6= new String[] {"period1_covertype", "period1_sizeclass", "mixedfire_percentage", "barkbeetle_percentage"};
			
			// Populate the data matrix
	        int table_row = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_SizeClass; j++) {
					data6[table_row][0] = allLayers.get(4).get(i);
					data6[table_row][1] = allLayers.get(5).get(j);	
					data6[table_row][2] = 5.0;
					data6[table_row][3] = 4.5;
					if (allLayers.get(4).get(i).equals("N")) {
						data6[table_row][2] = 0.0;	//Non-stocked --> No MS
						data6[table_row][3] = 0.0;	//Non-stocked --> No BS
					}
					table_row++;
				}
			}						
		}
					
		
		//Create a table-------------------------------------------------------------			
        model6 = new PrismTableModel(rowCount6, colCount6, data6, columnNames6) {
        	@Override
    		public boolean isCellEditable(int row, int col) {
    			if (col < 2) { // Only the last column are editable
    				return false;
    			} else {
    				return true;
    			}
    		}

        	@Override
    		public void setValueAt(Object value, int row, int col) {
    			if ((col == 2 || col == 3) && (((Number) value).doubleValue() < 0 || ((Number) value).doubleValue() > 100)) {
    				JOptionPane.showMessageDialog(PrismMain.get_Prism_DesktopPane(),
    						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
    			} else {
    				data6[row][col] = value;
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount6; row++) {
					for (int col = 0; col < colCount6; col++) {
						if (String.valueOf(data6[row][col]).equals("null")) {
							data6[row][col] = null;
						} else {					
							if (col == 2 || col == 3) {			//Column 2 & 3 is Double
								try {
									data6[row][col] = Double.valueOf(String.valueOf(data6[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table6");
								}	
							} else {	//All other columns are String
								data6[row][col] = String.valueOf(data6[row][col]);
							}
						}	
					}	
				}	
			}
        };
        
        
		table6 = new JTable(model6){
			@Override				//Implement table cell tool tips 			          
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				if (colIndex == 0) {
					try {
						tip = getValueAt(rowIndex, colIndex).toString();
						for (int i = 0; i < total_CoverType; i++) {
							if (tip.equals(allLayers.get(4).get(i)))	tip=allLayers_ToolTips.get(4).get(i);							
						}
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}
				else if (colIndex == 1) {
					try {
						tip = getValueAt(rowIndex, colIndex).toString();
						for (int i = 0; i < total_SizeClass; i++) {
							if (tip.equals(allLayers.get(5).get(i)))	tip=allLayers_ToolTips.get(5).get(i);							
						}
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}
				else if (colIndex == 2) {
					try {
						tip = "Percentage of the existing strata in the first period with cover type "+ getValueAt(rowIndex, 0).toString() 
								+ " at size class " + getValueAt(rowIndex, 1).toString() + " to be assigned to mixed severity wildfire";
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				} else if (colIndex == 3) {
					try {
						tip = "Percentage of the existing strata in the first period with cover type "+ getValueAt(rowIndex, 0).toString() 
								+ " at size class " + getValueAt(rowIndex, 1).toString() + " to be assigned to bark beetle";
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}	
				return tip;
			}
			
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table6.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table6,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}		
		};
		
		
		
		// Define a set of icon for some columns
		ImageIcon[] imageIconArray = new ImageIcon[colCount6];
		for (int i = 0; i < colCount6; i++) {
			if (i == 2 || i == 3) {
				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
			}
		}
		
		
		// Define a set of background color for all rows
		Color[] rowColor = new Color[rowCount6];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);
		Color currentColor = color2;
		int rCount = 0;

		for (int i = 0; i < total_CoverType; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			for (int j = 0; j < total_SizeClass; j++) {
				rowColor[rCount] = currentColor;
				rCount++;
			}
		}
		
				
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setForeground(Color.RED);
				setHorizontalAlignment(JLabel.LEFT);
				// setFont(getFont().deriveFont(Font.BOLD));
                	
				setBackground(rowColor[row]);		//Set cell background color
				if (isSelected) {
					setBackground(table.getSelectionBackground());		//Set background color	for selected row
				}
				setIcon(imageIconArray[column]);	// Set icons for cells in some columns
				setIconTextGap(15);		// Set the distance between icon and the actual data value
                return this;
            }
        };						
		
		
      
        for (int i=0; i<columnNames6.length; i++) {
        	if (i < 2) {
        		table6.getColumnModel().getColumn(i).setCellRenderer(r);
        	} 
        }
       
        
//        table6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table6.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table6.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//        table6.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        
//        table6.setTableHeader(null);
        table6.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table6.setFillsViewportHeight(true);
        TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model6);	//Add sorter
		for (int i = 1; i < colCount6; i++) {
			sorter.setSortable(i, false);
			if (i == 0 || i == 1) {			//Only the first 2 columns can be sorted
				sorter.setSortable(i, true);	
			}
		}
		table6.setRowSorter(sorter);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table7() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		int total_SizeClass = allLayers.get(5).size();		// total number of elements - 1 in layer6 Size Class (0 to...)	
		
				
		//Setup the table------------------------------------------------------------	
		if (is_table7_loaded == false) { // Create a fresh new if Load fail				
			rowCount7 = 30;
			colCount7 = total_CoverType + 1;
			data7 = new Object[rowCount7][colCount7];
	        columnNames7= new String[colCount7];
	        columnNames7[0] = "ageclass";
	        for (int i = 1; i < colCount7; i++) {
	        	 columnNames7[i] = allLayers.get(4).get(i-1);
			}		        
			
			// Populate the data matrix
			for (int i = 0; i < rowCount7; i++) {
				data7[i][0] = i+1;			//Age class column, age starts from 1
				for (int j = 1; j < colCount7; j++) {	//all other columns
					data7[i][j] = 0.2;
					if (allLayers.get(4).get(j-1).equals("N"))	data7[i][j] = 0.0;	//Non-stocked --> No SR Fire
				}
			}								
		}
			
		
        //Header ToolTIp
        String[] headerToolTips = new String[colCount7];
        for (int i = 1; i < colCount7; i++) {
        	headerToolTips[i] = allLayers_ToolTips.get(4).get(i-1);
		}
       
        
		
		//Create a table-------------------------------------------------------------			
        model7 = new PrismTableModel(rowCount7, colCount7, data7, columnNames7) {
        	@Override
    		public boolean isCellEditable(int row, int col) {
    			if (col < 1) { // Only the first column are un-editable
    				return false;
    			} else {
    				return true;
    			}
    		}

        	@Override
    		public void setValueAt(Object value, int row, int col) {
    			if (col > 0 && (((Number) value).doubleValue() < 0 || ((Number) value).doubleValue() > 100)) {
    				JOptionPane.showMessageDialog(PrismMain.get_Prism_DesktopPane(),
    						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
    			} else {
    				data7[row][col] = value;
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount7; row++) {
					for (int col = 0; col < colCount7; col++) {
						if (String.valueOf(data7[row][col]).equals("null")) {
							data7[row][col] = null;
						} else {					
							if (col > 0) {			//Columns except the 1st columns are Double
								try {
									data7[row][col] = Double.valueOf(String.valueOf(data7[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table7");
								}	
							} else {	//All other columns are String
								data7[row][col] = String.valueOf(data7[row][col]);
							}
						}	
					}	
				}	
			}
        };
        
        
		table7 = new JTable(model7) {
			@Override			//Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				if (colIndex > 0) {
					try {
						tip = "% loss of the strata with cover type "+ allLayers.get(4).get(colIndex - 1) 
								+ " at age class " + getValueAt(rowIndex, 0).toString();
						if (rowIndex == rowCount7 - 1) 	tip = tip + " plus";	//Add plus to the highest age class
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}					
				return tip;
			}
			
			@Override			//Implement table header tool tips. 
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        java.awt.Point p = e.getPoint();
                        if (columnModel.getColumnIndexAtX(p.x) > 0) {
                        	int index = columnModel.getColumnIndexAtX(p.x);
                            int realIndex = columnModel.getColumn(index).getModelIndex();
                            tip = headerToolTips[realIndex];
                        }
                        return tip;
					}
				};
			}
		};
		
		// Define a set of background colors
		Color[] rowColor = new Color[rowCount7];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);					
		Color currentColor = color1;

		for (int i = 0; i < rowCount7; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			rowColor[i] = currentColor;
		}
		
		// Define a set of icon for some columns
 		ImageIcon[] imageIconArray = new ImageIcon[colCount7];
 		for (int i = 0; i < colCount7; i++) {
 			if (i >= 1) {
 				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
 			}
 		}
 		
 	       
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
                value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setForeground(Color.RED);
				setHorizontalAlignment(JLabel.LEFT);
				// setFont(getFont().deriveFont(Font.BOLD));               	
				setBackground(rowColor[row]);		//Set cell background color
				if (isSelected) {
					setBackground(table.getSelectionBackground());		//Set background color	for selected row
				}
//				setHorizontalAlignment(JLabel.LEFT); 
//              setIcon(imageIconArray[column]);	// Set icons for cells in some columns
// 				setIconTextGap(15);		// Set the distance between icon and the actual data value				
                return this;
            }
        };
        
        
		for (int i = 0; i < columnNames7.length; i++) {
			if (i == 0) {
				table7.getColumnModel().getColumn(i).setCellRenderer(r);		// first column is shaded
			}
		}

        //Set toolTip for Column header
        JTableHeader header = table7.getTableHeader();
       

        
//      table7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table7.setCellSelectionEnabled(true);
        table7.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table7.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        table7.getColumnModel().getColumn(0).setPreferredWidth(150);	//Set width of 1st Column bigger
        
//      table7.setTableHeader(null);
        table7.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table7.setFillsViewportHeight(true);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table8a() {
		//Setup the table------------------------------------------------------------	
		if (is_table8a_loaded == false) { // Create a fresh new if Load fail	
			// This all_actions List contains all actions loaded from yield tables------------------------------------------------------------
			List<String> action_list = new ArrayList<String>();
			if (yieldTable_ColumnNames != null) {	//create table with column include yield tables columns
				for (String action: read_database.get_action_type()) {
					action_list.add(action);					
				}	
				
				rowCount8a = action_list.size();			
				colCount8a = 2 + yieldTable_ColumnNames.length;
				data8a = new Object[rowCount8a][colCount8a];
				columnNames8a = new String[2 + yieldTable_ColumnNames.length];
				columnNames8a[0] = "action_list";
				columnNames8a[1] = "acres";
				for (int i = 2; i < columnNames8a.length; i++) {
					columnNames8a[i] = yieldTable_ColumnNames[i - 2];				
				}	
			} else {
				rowCount8a = action_list.size();			
				colCount8a = 2;
				data8a = new Object[rowCount8a][colCount8a];
				columnNames8a= new String[] {"action_list", "acres"};
			}			
			
	       			
			// Populate the data matrix
			for (int i = 0; i < rowCount8a; i++) {
				data8a[i][0] = action_list.get(i);
				data8a[i][1] = (action_list.get(i).equalsIgnoreCase("no-action")) ? (double) 0 : (double) 360; 
			}
		}		
		
		
        //Header ToolTIp
		String[] headerToolTips = new String[colCount8a];
		headerToolTips[0] = "all unique actions found from  yield_tables in your selected database";
        headerToolTips[1] = "currency per acre where an action is implemented";
		if (yieldTable_ColumnNames != null) {      
	        for (int i = 2; i < colCount8a; i++) {
	        	int yt_col = i - 2;
	        	headerToolTips[i] = "currency per " + read_database.get_ParameterToolTip(yieldTable_ColumnNames[yt_col]) + " (Column index: " + yt_col + ")";	
			}
		}
	
		
		//Create a table-------------------------------------------------------------			
        model8a = new PrismTableModel(rowCount8a, colCount8a, data8a, columnNames8a) {
        	@Override
			public Class getColumnClass(int c) {
				if (c > 0) return Double.class;      // columns > 0 accept only Double  
				else return String.class;				
			}
        	
        	@Override
    		public boolean isCellEditable(int row, int col) {
				if (col == 0) { // the 1st column is not editable
    				return false;
    			} else {
    				return true;
    			}
    		}

        	@Override
    		public void setValueAt(Object value, int row, int col) {
        		if (value != null && col > 0 && ((Number) value).doubleValue() < 0) {			// allow null to be set
    				JOptionPane.showMessageDialog(PrismMain.get_Prism_DesktopPane(),
    						"Your input has not been accepted. Cost cannot be negative.");
    			} else {
    				data8a[row][col] = value;
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount8a; row++) {
					for (int col = 0; col < colCount8a; col++) {
						if (String.valueOf(data8a[row][col]).equals("null")) {
							data8a[row][col] = null;
						} else {					
							if (col > 0) {			//Columns except the 1st columns are Double
								try {
									data8a[row][col] = Double.valueOf(String.valueOf(data8a[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table7");
								}	
							} else {	//All other columns are String
								data8a[row][col] = String.valueOf(data8a[row][col]);
							}
						}	
					}	
				}	
			}
        };
        
        
		table8a = new JTable(model8a) {
			@Override			//Implement table header tool tips. 
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        java.awt.Point p = e.getPoint();
						if (columnModel.getColumnIndexAtX(p.x) > 0) {
                        	int index = columnModel.getColumnIndexAtX(p.x);
                            int realIndex = columnModel.getColumn(index).getModelIndex();
                            tip = headerToolTips[realIndex];
                        }
                        return tip;
                    }
                };
            }					
			
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table8a.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table8a,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}		
		};				
		

		// Define a set of background colors
		Color[] rowColor = new Color[rowCount8a];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);					
		Color currentColor = color1;

		for (int i = 0; i < rowCount8a; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			rowColor[i] = currentColor;
		}
		
		// Define a set of icon for some columns
 		ImageIcon[] imageIconArray = new ImageIcon[colCount7];
 		for (int i = 0; i < colCount7; i++) {
 			if (i >= 1) {
 				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
 			}
 		}	
				
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setForeground(Color.RED);
				setHorizontalAlignment(JLabel.LEFT);
				// setFont(getFont().deriveFont(Font.BOLD));               	
				setBackground(rowColor[row]);		//Set cell background color
				if (isSelected) {
					setBackground(table.getSelectionBackground());		//Set background color	for selected row
				}
//				setHorizontalAlignment(rowAlignment[row]);			
                return this;
            }
        };						
		
		
		for (int i = 0; i < columnNames8a.length; i++) {
			if (i == 0) {
				table8a.getColumnModel().getColumn(i).setCellRenderer(r);		// first column is shaded
			}
		}
			
		
		if (yieldTable_ColumnNames != null) table8a.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table8a.setCellSelectionEnabled(true);
        table8a.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table8a.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        
//      table8a.setTableHeader(null);
        table8a.setPreferredScrollableViewportSize(new Dimension(200, 100));
//      table8a.setFillsViewportHeight(true);
        TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model8a);	//Add sorter
		for (int i = 1; i < colCount8a; i++) {
			sorter.setSortable(i, false);
			if (i == 0) {			//Only the first column can be sorted
				sorter.setSortable(i, true);	
			}
		}
//		table8a.setRowSorter(sorter);
	}	

	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table8b() {		
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)	
		
		//Setup the table------------------------------------------------------------	
		if (is_table8b_loaded == false) { // Create a fresh new if Load fail	
			if (yieldTable_ColumnNames != null) {	//create table with column include yield tables columns
				rowCount8b = total_CoverType * total_CoverType;		
				colCount8b = 4;
				data8b = new Object[rowCount8b][colCount8b];
				columnNames8b = new String[] {"covertype_before", "covertype_after", "action", "disturbance"};
			}		
			       			
			// Populate the data matrix
			 int table_row = 0;
				for (int i = 0; i < total_CoverType; i++) {
					for (int j = 0; j < total_CoverType; j++) {
						data8b[table_row][0] = allLayers.get(4).get(i);
						data8b[table_row][1] = allLayers.get(4).get(j);
						data8b[table_row][2] = (double) 240; 
						table_row++;
				}
			}
		}
		
		
        //Header ToolTIp
		String[] headerToolTips = new String[colCount8b];
		headerToolTips[0] = "cover type in period (t) before the occurence of EA clear cut or replacing disturbance in the same period (t)";
        headerToolTips[1] = "cover type in the next period (t+1) after the occurence of EA clear cut or replacing disturbance in period (t)";
        headerToolTips[2] = "currency per acre converted by management action";
        headerToolTips[3] = "currency per acre converted by replacing disturbance";
		
		//Create a table-------------------------------------------------------------			
        model8b = new PrismTableModel(rowCount8b, colCount8b, data8b, columnNames8b) {
        	@Override
			public Class getColumnClass(int c) {
				if (c > 1) return Double.class;      // columns > 1 accept only Double  
				else return String.class;				
			}
        	
        	@Override
    		public boolean isCellEditable(int row, int col) {
				if (col < 2) { // the first 2 column is not editable
					return false;
    			} else {
    				return true;
    			}
    		}

        	@Override
    		public void setValueAt(Object value, int row, int col) {
        		if (value != null && col >= 2 && ((Number) value).doubleValue() < 0) {
    				JOptionPane.showMessageDialog(PrismMain.get_Prism_DesktopPane(),
    						"Your input has not been accepted. Cost cannot be negative.");
    			} else {
    				data8b[row][col] = value;   				
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount8b; row++) {
					for (int col = 0; col < colCount8b; col++) {
						if (String.valueOf(data8b[row][col]).equals("null")) {
							data8b[row][col] = null;
						} else {					
							if (col >= 2) {			//Columns except the 1st 2 columns are Double
								try {
									data8b[row][col] = Double.valueOf(String.valueOf(data8b[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table8b");
								}	
							} else {	//All other columns are String
								data8b[row][col] = String.valueOf(data8b[row][col]);
							}
						}	
					}	
				}	
			}
        };
        
        
		table8b = new JTable(model8b) {
			@Override			//Implement table header tool tips. 
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        java.awt.Point p = e.getPoint();
						if (columnModel.getColumnIndexAtX(p.x) >= 0) {
                        	int index = columnModel.getColumnIndexAtX(p.x);
                            int realIndex = columnModel.getColumn(index).getModelIndex();
                            tip = headerToolTips[realIndex];
                        }
                        return tip;
                    }
                };
            }					
			
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table8b.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table8b,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}		
		};
		

		// Define a set of background colors
		Color[] rowColor = new Color[rowCount8b];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);					
		Color currentColor = color1;

		for (int i = 0; i < rowCount8b; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			rowColor[i] = currentColor;
		}
		
		// Define a set of icon for some columns
 		ImageIcon[] imageIconArray = new ImageIcon[colCount7];
 		for (int i = 0; i < colCount7; i++) {
 			if (i >= 1) {
 				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
 			}
 		}	
				
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setForeground(Color.RED);
				setHorizontalAlignment(JLabel.LEFT);
				// setFont(getFont().deriveFont(Font.BOLD));               	
				setBackground(rowColor[row]);		//Set cell background color
				if (isSelected) {
					setBackground(table.getSelectionBackground());		//Set background color	for selected row
				}
//				setHorizontalAlignment(rowAlignment[row]);			
                return this;
            }
        };						
		
		
		for (int i = 0; i < columnNames8b.length; i++) {
			if (i < 2) {
				table8b.getColumnModel().getColumn(i).setCellRenderer(r);		// first 2 columns is shaded
			}
		}
			
		
//		table8b.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table8b.setCellSelectionEnabled(true);
        table8b.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table8b.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        
//      table8b.setTableHeader(null);
        table8b.setPreferredScrollableViewportSize(new Dimension(200, 100));
//      table8b.setFillsViewportHeight(true);
        TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model8b);	//Add sorter
		for (int i = 0; i < colCount8b; i++) {
			sorter.setSortable(i, false);
			if (i < 2) {			//Only the first 2 columns can be sorted
				sorter.setSortable(i, true);	
			}
		}
//		table8b.setRowSorter(sorter);
	}		
			
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table8() {
		//Setup the table------------------------------------------------------------	
		if (is_table8_loaded == false) { // Create a fresh new if Load fail				
			rowCount8 = 0;
			colCount8 = 7;
			data8 = new Object[rowCount8][colCount8];
			columnNames8 = new String[] {"priority", "condition_description", "action_cost", "conversion_cost", "static_identifiers", "dynamic_identifiers", "original_dynamic_identifiers"};	         				
		}
					
		
		//Create a table-------------------------------------------------------------		
		model8 = new PrismTableModel(rowCount8, colCount8, data8, columnNames8) {
			@Override
			public Class getColumnClass(int c) {
				return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col >= 1 && col <= 2) { //  Only column 1 2 "description" is editable
					return true;
				} else {
					return false;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data8[row][col] = value;
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount8; row++) {
					for (int col = 0; col < colCount8; col++) {
						if (String.valueOf(data8[row][col]).equals("null")) {
							data8[row][col] = null;
						} else {					
							data8[row][col] = String.valueOf(data8[row][col]);
						}
					}	
				}	
			}
		};
		
		
		
		table8 = new JTable(model8) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table8.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table8,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}
			
//             //Implement table cell tool tips           
//             public String getToolTipText(MouseEvent e) {
//                 String tip = null;
//                 java.awt.Point p = e.getPoint();
//                 int rowIndex = rowAtPoint(p);
//                 int colIndex = columnAtPoint(p);
//                 try {
//                       tip = getValueAt(rowIndex, colIndex).toString();
//                 } catch (RuntimeException e1) {
//                	 System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//                 }
//                 return tip;
//             }		
		};
		
		
			
		
		// Hide all except first 2 columns: this hide is better than remove column from column model, this is basically set size to be zero
		for (int i = 2; i < colCount8; i++) {
			table8.getColumnModel().getColumn(i).setMinWidth(0);
			table8.getColumnModel().getColumn(i).setMaxWidth(0);
			table8.getColumnModel().getColumn(i).setWidth(0);
		}
  
		table8.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table8.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table8.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table8.setPreferredScrollableViewportSize(new Dimension(150, 100));
//		table8.setFillsViewportHeight(true);
	}		

	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table9() {
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
			colCount9 = 12;
			data9 = new Object[rowCount9][colCount9];
			columnNames9 = new String[] {"bc_id", "bc_description", "bc_type",  "bc_multiplier", "lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty", "parameter_index", "static_identifiers", "dynamic_identifiers", "original_dynamic_identifiers"};	         				
		}
					
		
		//Create a table-------------------------------------------------------------		
		model9 = new PrismTableModel(rowCount9, colCount9, data9, columnNames9) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;      //column 0 accepts only Integer
				else if (c >= 3 && c <= 7) return Double.class;      //column 3 to 7 accept only Double values   
				else return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col >= colCount9 - 4) { //  The first and the last 4 columns are un-editable
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
							} else if (col >= 3 && col <= 7) {			//Column 3 to 7 are Double
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
								
//				// Set background color
//				if (getValueAt(row, 2) == null || getValueAt(row, 2).toString().equals("FREE")) {
//					component.setBackground(getBackground());
//				} else if (getValueAt(row, 2).toString().equals("SOFT")) {
//					component.setBackground(ColorUtil.makeTransparent(new Color(27, 158, 119), 100));
//					
//				} else if (getValueAt(row, 2).toString().equals("HARD")) {
//					component.setBackground(ColorUtil.makeTransparent(new Color(217, 95, 2), 100));
//					
//				}
//				if (isRowSelected(row)) component.setBackground(getSelectionBackground());		// for selected row
										
				// Set icon for cells
				if (column == 2) {
					if (getValueAt(row, 2) == null || getValueAt(row, 2).toString().equals("FREE")) {
						((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_gray.png"));
					} else if (getValueAt(row, 2).toString().equals("SOFT")) {
						((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_yellow.png"));
					} else if (getValueAt(row, 2).toString().equals("HARD")) {
						((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_red.png"));
					}
				}
						
				return component;
			}
			
//             //Implement table cell tool tips           
//             public String getToolTipText(MouseEvent e) {
//                 String tip = null;
//                 java.awt.Point p = e.getPoint();
//                 int rowIndex = rowAtPoint(p);
//                 int colIndex = columnAtPoint(p);
//                 try {
//                       tip = getValueAt(rowIndex, colIndex).toString();
//                 } catch (RuntimeException e1) {
//                	 System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//                 }
//                 return tip;
//             }		
		};

    

        // Set up Type for each column 2
		table9.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_constraint_type()));
			
		
		// Hide the last 4 columns: this hide is better than remove column from column model, this is basically set size to be zero
		for (int i = colCount9 - 4; i < colCount9; i++) {
			table9.getColumnModel().getColumn(i).setMinWidth(0);
			table9.getColumnModel().getColumn(i).setMaxWidth(0);
			table9.getColumnModel().getColumn(i).setWidth(0);
		}
         
		table9.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table9.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table9.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table9.setPreferredScrollableViewportSize(new Dimension(200, 100));
//		table9.setFillsViewportHeight(true);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table10() {
		class comboBox_constraint_type extends JComboBox {	
			public comboBox_constraint_type() {
//				addItem("SOFT");
				addItem("HARD");
				addItem("FREE");
				setSelectedIndex(0);
			}
		}
		
			
		//Setup the table------------------------------------------------------------	
		if (is_table10_loaded == false) { // Create a fresh new if Load fail				
			rowCount10 = 0;
			colCount10 = 6;
			data10 = new Object[rowCount10][colCount10];
			columnNames10 = new String[] {"flow_id", "flow_description", "flow_arrangement", "flow_type", "lowerbound_percentage", "upperbound_percentage"};	         				
		}
					
		
		//Create a table-------------------------------------------------------------		
		model10 = new PrismTableModel(rowCount10, colCount10, data10, columnNames10) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;      //column 0 accepts only Integer
				else if (c == 1 || c == 2) return String.class;      
				else if (c == 4 || c == 5) return Double.class;       
				else return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col == 2) { 	// Columns 0 and 2 are un-editable
					return false;
				} else {
					return true;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data10[row][col] = value;
				if (col == 3) {
					fireTableDataChanged();		// When constraint type change then this would register the change and make the selection disappear
					table10.setRowSelectionInterval(table10.convertRowIndexToView(row), table10.convertRowIndexToView(row));			// select the row again
				}
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount10; row++) {
					for (int col = 0; col < colCount10; col++) {
						if (String.valueOf(data10[row][col]).equals("null")) {
							data10[row][col] = null;
						} else {					
							if (col == 0) {			//Column 0 is Integer
								try {
									data10[row][col] = Integer.valueOf(String.valueOf(data10[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table10");
								}	
							} else if (col == 4 || col == 5) {			//Column 4 and 5 are Double
								try {
									data10[row][col] = Double.valueOf(String.valueOf(data10[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table10");
								}
							} else {	//All other columns are String
								data10[row][col] = String.valueOf(data10[row][col]);
							}
						}	
					}	
				}	
			}
		};
		
		
		
		table10 = new JTable(model10) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table10.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table10,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
							
//				// Set background color
//				if (getValueAt(row, 3) == null || getValueAt(row, 3).toString().equals("FREE")) {
//					component.setBackground(getBackground());				
//				} else if (getValueAt(row, 3).toString().equals("HARD")) {
//					component.setBackground(ColorUtil.makeTransparent(new Color(217, 95, 2), 100));
//					
//				}
//				if (isRowSelected(row)) component.setBackground(getSelectionBackground());		// for selected row
				
				// Set icon for cells
				if (column == 3) {
					if (getValueAt(row, 3) == null || getValueAt(row, 3).toString().equals("FREE")) {
						((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_gray.png"));
					} else if (getValueAt(row, 3).toString().equals("SOFT")) {
						((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_yellow.png"));
					} else if (getValueAt(row, 3).toString().equals("HARD")) {
						((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_red.png"));
					}
				}
				
				return component;
			}
			
//             //Implement table cell tool tips           
//             public String getToolTipText(MouseEvent e) {
//                 String tip = null;
//                 java.awt.Point p = e.getPoint();
//                 int rowIndex = rowAtPoint(p);
//                 int colIndex = columnAtPoint(p);
//                 try {
//                       tip = getValueAt(rowIndex, colIndex).toString();
//                 } catch (RuntimeException e1) {
//                	 System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//                 }
//                 return tip;
//             }		
		};

    

        // Set up Type for column 3
		table10.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new comboBox_constraint_type()));        
		table10.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table10.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table10.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table10.setPreferredScrollableViewportSize(new Dimension(250, 100));
	}	
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------  
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------	
	
    
    
    
    
    
	// Panel General Inputs------------------------------------------------------------------------------------------------------	
	class General_Inputs_GUI extends JLayeredPane {
		private JLabel label1, label2, label3, label4, label5, label6;
		private JComboBox combo1, combo2, combo3;
		private JSpinner spin4;
		private JCheckBox check5, check6;
		
		public General_Inputs_GUI() {
			setLayout(new GridBagLayout());
			create_table1();
			
			
			//-----------------------------------------------------
			label1 = new JLabel("Total planning periods (decades)");
			combo1 = new JComboBox();		
			for (int i = 1; i <= 50; i++) {
				combo1.addItem(i);
			}
			combo1.setSelectedItem((int) 5);
			//-----------------------------------------------------
			label2 = new JLabel("Annual discount rate (%)");
			combo2 = new JComboBox();		
			for (int i = 0; i <= 100; i++) {
				double value = (double) i/10;
				combo2.addItem(value);
			}
			combo2.setSelectedItem((double) 0);
			//-----------------------------------------------------						
			label3 = new JLabel("Solver for optimization");
			combo3 = new JComboBox();
			combo3.addItem("CPLEX");
			combo3.addItem("LPSOLVE");
			combo3.addItem("CBC");
			combo3.addItem("CLP");
			combo3.addItem("GUROBI");
			combo3.addItem("GLPK");
			combo3.addItem("SPCIP");
			combo3.addItem("SOPLEX");
			combo3.addItem("XPRESS");	
			//-----------------------------------------------------
			label4 = new JLabel("Maximum solving time (minutes)");
			spin4 = new JSpinner (new SpinnerNumberModel(20, 0, 60, 1));
			JFormattedTextField SpinnerText = ((DefaultEditor) spin4.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			//-----------------------------------------------------
			label5 = new JLabel("Export original problem file (.lp)");
			check5 = new JCheckBox();
			check5.setSelected(false);
			//-----------------------------------------------------
			label6 = new JLabel("Export original solution file (.sol)");
			check6 = new JCheckBox();
			check6.setSelected(false);
			//-----------------------------------------------------
			// ToolBar Panel
			ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
			helpToolBar.setFloatable(false);	//to make a tool bar immovable
			helpToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor
			helpToolBar.setBorderPainted(false);
									
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {
								
			});
			
			// Add all buttons to helpToolBar
			helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
			helpToolBar.add(btnHelp);
			//-----------------------------------------------------
			
			
			
			Action apply = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					totalPeriod = Integer.parseInt(combo1.getSelectedItem().toString());
					
					// Apply any change in the GUI to the table
					data1[0][1] = combo1.getSelectedItem().toString();				
					data1[1][1] = combo2.getSelectedItem().toString();
					data1[2][1] = combo3.getSelectedItem().toString();
					data1[3][1] = (Integer)spin4.getValue();
					data1[4][1] = (check5.isSelected()) ? "true" : "false";
					data1[5][1] = (check6.isSelected()) ? "true" : "false";
					model1.fireTableDataChanged();

				}
			};
			
			
			//Load info from input to GUI
			if (is_table1_loaded == true) {
				if (! String.valueOf(data1[0][1]).equals("null")) {
					combo1.setSelectedItem(Integer.valueOf((String) data1[0][1]));
					totalPeriod = Integer.valueOf((String) data1[0][1]);
				}				
				if (! String.valueOf(data1[1][1]).equals("null")) combo2.setSelectedItem(Double.valueOf((String) data1[1][1]));
				if (! String.valueOf(data1[2][1]).equals("null")) combo3.setSelectedItem(String.valueOf(data1[2][1]));
				if (! String.valueOf(data1[3][1]).equals("null")) spin4.setValue(Integer.valueOf((String) data1[3][1]));					
				if (! String.valueOf(data1[4][1]).equals("null")) check5.setSelected(Boolean.valueOf(String.valueOf(data1[4][1])));
				if (! String.valueOf(data1[5][1]).equals("null")) check6.setSelected(Boolean.valueOf(String.valueOf(data1[5][1])));
			}
			
			
			combo1.addActionListener(apply);
			combo2.addActionListener(apply);
			combo3.addActionListener(apply);
			check5.addActionListener(apply);
			check6.addActionListener(apply);
			
			
			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    spin4.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	spin4.setValue(spin4.getValue());
		        	totalPeriod = Integer.parseInt(combo1.getSelectedItem().toString());
		        	
		        	// Apply any change in the GUI to the table
		        	data1[0][1] = combo1.getSelectedItem().toString();				
					data1[1][1] = combo2.getSelectedItem().toString();
					data1[2][1] = combo3.getSelectedItem().toString();
					data1[3][1] = (Integer)spin4.getValue();
					data1[4][1] = (check5.isSelected()) ? "true" : "false";
					data1[5][1] = (check6.isSelected()) ? "true" : "false";
					model1.fireTableDataChanged();
		        }
		    });
		    if (is_table1_loaded == false) {
		    	spin4.setValue(15);		// Load GUI to table if there is no input to load	(15 <> 20 then the listener will be activate)
		    }
		    

		    
		    
		    
		    
		    // Import Database Panel -----------------------------------------------------------------------
		 	// Import Database Panel -----------------------------------------------------------------------
			JPanel importPanel = new JPanel();
			importPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			JTextField textField2 = new JTextField(40);
			textField2.setEditable(false);
			importPanel.add(textField2, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, 0, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));	// insets top, left, bottom, right	
			
			
			
			
			button_import_database = new JButton();
			button_import_database.setToolTipText("Browse");
			button_import_database.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));		
			button_import_database.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!is_first_time_loaded || file_database == null) {						
						Thread thread = new Thread() {		// Not use join thread, if use then all the code after thread start will have to wait for the thread finished to be implemented
							public void run() {
								initialize_database_change();			
								this.interrupt();
							}
						};					
						
//						try {
//							thread.join();
							thread.start();
//						} catch (InterruptedException e2) {
//							System.out.println("Thread join fail");
//						}				
//						System.out.println("This line could be written out only after all join threads are finished");
					} else {
						initialize_database_change();
					}	
				}
				
				private void initialize_database_change() {
					File old_database = file_database;
				
					try {	
						button_import_database.setEnabled(false);
						radioButton_Right[1].setEnabled(false);
						radioButton_Right[2].setEnabled(false);
						radioButton_Right[3].setEnabled(false);
						radioButton_Right[4].setEnabled(false);
						radioButton_Right[5].setEnabled(false);
						radioButton_Right[6].setEnabled(false);
						radioButton_Right[7].setEnabled(false);
						change_database();
						is_first_time_loaded = false;	
					} catch (Exception e1) {
						String warningText = "Importation is denied. " + file_database.getName() + " does not meet PRISM's data requirements.";
						String ExitOption[] = {"OK"};
						int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Database importation warning",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption, ExitOption[0]);
						
						file_database = null;
					} finally {
						button_import_database.setEnabled(true);
						if (file_database == null) { // if cancel browsing
							file_database = old_database;
						} 
						if (file_database != null) {
							radioButton_Right[1].setEnabled(true);
							radioButton_Right[2].setEnabled(true);
							radioButton_Right[3].setEnabled(true);
							radioButton_Right[4].setEnabled(true);
							radioButton_Right[5].setEnabled(true);
							radioButton_Right[6].setEnabled(true);
							radioButton_Right[7].setEnabled(true);
						}
					}

				}

				private void change_database() {

					if (!is_first_time_loaded || file_database == null) {	// This is because the first load may have null or not null database
						file_database = FilesHandle.chosenDatabase();
						
						if (file_database != null) {	// If cancel choosing file
							// read the tables (strata_definition, existing_strata, yield_tables) of the database-------------------							
							read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
							if (read_database == null) {
								read_database = new Read_Database(file_database);	// Read the database
								PrismMain.get_databases_linkedlist().update(file_database, read_database);			
							}														
							
							layers_Title = read_database.get_layers_Title();
							layers_Title_ToolTip = read_database.get_layers_Title_ToolTip();
							allLayers =  read_database.get_allLayers();
							allLayers_ToolTips = read_database.get_allLayers_ToolTips();
							yieldTable_values = read_database.get_yield_tables_values();
							yieldTable_ColumnNames = read_database.get_yield_tables_column_names();
							
							// Reset all panels except General Inputs----------------------------------------------------------------		
							is_table_overview_loaded = false;
							is_table1_loaded = false;
							is_table2_loaded = false;
							is_table3_loaded = false;
							is_table4_loaded = false;
							is_table5_loaded = false;
							is_table6_loaded = false;
							is_table7_loaded = false;
							is_table8a_loaded = false;
							is_table8b_loaded = false;
							is_table8_loaded = false;
							is_table9_loaded = false;
							is_table10_loaded = false;
													
							
							// create 2 new instances of this Panel
							panel_Silviculture_Method_GUI = new Silviculture_Method_GUI();
							panel_Silviculture_Method_Text = new Silviculture_Method_Text();
														
					        // create 2 new instances of this Panel
							panel_Model_Strata_GUI = new Model_Strata_GUI();
							panel_Model_Strata_Text = new Model_Strata_Text();
					        
					        // create 2 new instances of this Panel
							panel_Covertype_Conversion_GUI = new Covertype_Conversion_GUI();
							panel_Covertype_Conversion_Text = new Covertype_Conversion_Text();
							
							 // create 2 new instances of this Panel
							panel_Natural_Disturbances_GUI = new Natural_Disturbances_GUI();
							panel_Natural_Disturbances_Text = new Natural_Disturbances_Text();
							
					        // create 2 new instances of this Panel 
							panel_Management_Cost_GUI = new Management_Cost_GUI();
							panel_Management_Cost_Text = new Management_Cost_Text();
							
							// create 2 new instances of this Panel 
							panel_Silviculture_Method_GUI = new Silviculture_Method_GUI();
							panel_Silviculture_Method_Text = new Silviculture_Method_Text();
							
							// create 2 new instances of this Panel 
							panel_Basic_Constraints_GUI = new Basic_Constraints_GUI();
							panel_Basic_Constraints_Text = new Basic_Constraints_Text();
							
							// create 2 new instances of this Panel 
							panel_Flow_Constraints_GUI = new Flow_Constraints_GUI();
							panel_Flow_Constraints_Text = new Flow_Constraints_Text();
						}
						
					}

									
					

					if (file_database != null) {	// If cancel choosing file
						// get the raw existing_strata from the database------------------------------------------------------
						String[][] existing_strata_values = read_database.get_existing_strata_values();
						rowCount3 = existing_strata_values.length;	// refresh total rows based on existing strata, we don't need to refresh the total columns
						int existing_strata_colCount = existing_strata_values[0].length;
	
						data3 = new Object[rowCount3][colCount3];
						for (int row = 0; row < rowCount3; row++) {
							for (int column = 0; column < existing_strata_colCount - 1; column++) {		// loop all existing strata columns, except the last column
								data3[row][column] = existing_strata_values[row][column];
							}
							if (existing_strata_values[row][existing_strata_colCount - 1] != null) {
								data3[row][colCount3 - 3] = Double.valueOf(existing_strata_values[row][existing_strata_colCount - 1]);	// the last column of readStrata is "Total acres"	
							}								
							data3[row][colCount3 - 1] = false;
						}					
						model3.match_DataType();	// a smart way to retrieve the original data type :))))))					
						model3.updateTableModelPrism(rowCount3, colCount3, data3, columnNames3);		// very important to (pass table info back to table model) each time data is new Object
								         						
						// only add sorter after having the data loaded
						TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model3);
						table3.setRowSorter(sorter);
	
	
						
						
				        // update Age Class column for the existing strata------------------------------------------------------
						for (int row = 0; row < rowCount3; row++) {						
							String s5 = data3[row][5].toString();
							String s6 = data3[row][6].toString();
							if (read_database.get_starting_ageclass(s5, s6, "NG", "0") != null) {
								data3[row][colCount3 - 2] = Integer.valueOf(read_database.get_starting_ageclass(s5, s6, "A", "0"));	
							}												
						}
						
						
						
						
						// select all existing strata after loaded if found age class
						for (int row = 0; row < rowCount3; row++) {
							if (data3[row][colCount3 - 2] != null) {
								data3[row][colCount3 - 1] = true;
							}
						}
						
						
						
						// some final data update after import successfully
						model3.fireTableDataChanged();
						model3.update_model_overview();								        					
						textField2.setText(file_database.getAbsolutePath());
					}
				}
			});	

			importPanel.add(button_import_database, PrismGridBagLayoutHandle.get_c( c, "HORIZONTAL", 
					1, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));	// insets top, left, bottom, right	
						
			// Add empty Label for everything above not resize
			importPanel.add(new JLabel(), PrismGridBagLayoutHandle.get_c( c, "HORIZONTAL", 
					0, 1, 1, 1, 0, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right	
			



			// End of Import Database Panel -----------------------------------------------------------------------
			// End of Import Database Panel -----------------------------------------------------------------------		    
		    
		    
			
		    
 			// Load readme file-----------------------------------------------------------------
 			// Load readme file-----------------------------------------------------------------
			try {
				FileReader reader = new FileReader(currentRunFolder.getAbsolutePath() + "/readme.txt");
				readme.read(reader, currentRunFolder.getAbsolutePath() + "/readme.txt");
				reader.close();
			} catch (IOException e1) {
				System.err.println("File not exists: readme.txt - New interface is created");
				readme.append("Write anything to describe your model here");
			}
			
			PrismTitleScrollPane readme_scrollpane = new PrismTitleScrollPane("Model description - exported as readme.txt", "LEFT", readme);
 			readme_scrollpane.setPreferredSize(new Dimension((int) (PrismMain.get_main().getPreferredSize().width * 0.55), 100));
 			// End of Load readme file-----------------------------------------------------------------
 			// End of Load readme file-----------------------------------------------------------------
		    
		        
 			
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
 			
 		    // Add helpToolBar	
 		   super.add(helpToolBar, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
					0, 0, 6, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		    		    
 			// Add 	
 			 super.add(label1, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right
 									
 			// Add	
 			super.add(combo1, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					1, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right		
 			
 			// Add 
 			super.add(label2, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 2, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right	

 			// Add 
 			super.add(combo2, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					1, 2, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right	
	
 			// Add 
 			super.add(label3, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 3, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right	
 			
 			// Add 
 			super.add(combo3, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					1, 3, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right
 			
 			// Add 
 			super.add(label4, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 4, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right	

 			// Add 
 			super.add(spin4, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					1, 4, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right
		
 			// Add 
 			super.add(label5, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					2, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right
 			
 			// Add 
 			super.add(check5, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					3, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right
 			
 			// Add 
 			super.add(label6, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					2, 2, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right
 			
 			// Add 
 			super.add(check6, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					3, 2, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					0, 12, 10, 30));		// insets top, left, bottom, right

 			// Add 
 			super.add(new JLabel("Import database - If successful information in other windows will be reset to default"), PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 5, 4, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					5, 12, 0, 30));		// insets top, left, bottom, right
 						
 			// Add 
 			super.add(importPanel, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 6, 4, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					5, 10, 10, 30));		// insets top, left, bottom, right
 						
 			// Add 
 			super.add(readme_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
 					0, 7, 5, 1, 0, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
 					25, 5, 0, 30));		// insets top, left, bottom, right
		}
	}

	class General_Inputs_Text extends JScrollPane {
		public General_Inputs_Text() {	
			
		}
	}

	
	
	
	// Panel Silviculture_Method--------------------------------------------------------------------------------------------------------
	class Silviculture_Method_GUI extends JLayeredPane {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
		
		List<List<JCheckBox>> checkboxStaticIdentifiers_silviculture;
		ScrollPane_StaticIdentifiers static_identifiersScrollPanel_silviculture;
		
		JPanel button_table_Panel;	
		QuickEdit_SilvicultureMethod_Panel quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Silviculture_Method_GUI() {
			setLayout(new GridBagLayout());	
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------	
			String panel_name = "Strata (Existing: layers 1 to 6, Regeneration: layers 1 to 4)";
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_database, 0, panel_name);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();	
			
			for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
				for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
					checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
				}
			}
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------						
			
			
			
			
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------	
			panel_name = "Silviculture Method & Timing Choice to be implemented";
			static_identifiersScrollPanel_silviculture = new ScrollPane_StaticIdentifiers(read_database, 3, panel_name);
			static_identifiersScrollPanel_silviculture.setPreferredSize(new Dimension(250, 250));	
			checkboxStaticIdentifiers = static_identifiersScrollPanel_silviculture.get_CheckboxStaticIdentifiers();	
			
			for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
				for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
					checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
				}
			}
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------			
			
			
			
			
			// 4th Grid ------------------------------------------------------------------------------		// Buttons	
			// 4th Grid -----------------------------------------------------------------------------
			// Add all buttons to a Panel----------------------------------
			button_table_Panel = new JPanel(new GridBagLayout());
			TitledBorder border = new TitledBorder("Silviculture Method Information - Implementation based on aggregation of all the below (if not set up all methods & choices will be eligible for implementation)");
			border.setTitleJustification(TitledBorder.CENTER);
			button_table_Panel.setBorder(border);
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.insets = new Insets(0, 5, 10, 10); // padding top 0, left 5, bottom 10, right 10
			
			
			JButton btn_NewSingle = new JButton();
			btn_NewSingle.setFont(new Font(null, Font.BOLD, 14));
//			btn_NewSingle.setText("NEW SINGLE");
			btn_NewSingle.setToolTipText("New");
			btn_NewSingle.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));
					
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_NewSingle, c2);
			
			
			JButton btn_Edit = new JButton();
//			btn_Edit.setText("EDIT");
			btn_Edit.setToolTipText("Modify");
			btn_Edit.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_swap.png"));
			btn_Edit.setEnabled(false);
					
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Edit, c2);
			
			
			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 14));
//			btn_Delete.setText("DELETE");
			btn_Delete.setToolTipText("Delete");
			btn_Delete.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_erase.png"));
			btn_Delete.setEnabled(false);
					
			c2.gridx = 0;
			c2.gridy = 2;
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
			c2.gridy = 3;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Sort, c2);
						
			
			c2.insets = new Insets(0, 0, 0, 0); // No padding
			// Add Empty Label to make all buttons on top not middle
			c2.gridx = 0;
			c2.gridy = 4;
			c2.weightx = 0;
			c2.weighty = 1;
			button_table_Panel.add(new JLabel(), c2);
			
			// Add table2				
			create_table2();
			JScrollPane table_ScrollPane = new JScrollPane(table2);	
			c2.gridx = 1;
			c2.gridy = 0;
			c2.weightx = 1;
			c2.weighty = 1;
			c2.gridheight = 5;
			button_table_Panel.add(table_ScrollPane, c2);
			// End of 4th Grid -----------------------------------------------------------------------
			// End of 4th Grid -----------------------------------------------------------------------	
			
			
			
			// Add Listeners for table2 & buttons----------------------------------------------------------
			// Add Listeners for table2 & buttons----------------------------------------------------------
			
			
			// table2
			table2.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selectedRow = table2.getSelectedRows();
					if (selectedRow.length == 1) {		// Reload Constraint & Enable Edit	when: 1 row is selected and no cell is editing
						int currentRow = selectedRow[0];
						currentRow = table2.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data2[currentRow][2]);	// 2 is the static_identifiers which have some attributes selected				
						static_identifiersScrollPanel_silviculture.reload_this_constraint_static_identifiers((String) data2[currentRow][3]);	// 3 is the method & choice
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table2.isEnabled()) {		// Enable Delete  when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}	
		        }
		    });			
			table2.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF

			
			// Hide the column 2 and 3 and 4: this hide is better than remove column from column model, this is basically set size to be zero
			for (int i = 2; i < 5; i++) {
				table2.getColumnModel().getColumn(i).setMinWidth(0);
				table2.getColumnModel().getColumn(i).setMaxWidth(0);
				table2.getColumnModel().getColumn(i).setWidth(0);
			}
					

			// New single
			btn_NewSingle.addActionListener(e -> {		
				// Add 1 row
				rowCount2++;
				data2 = new Object[rowCount2][colCount2];
				for (int ii = 0; ii < rowCount2 - 1; ii++) {
					for (int jj = 0; jj < colCount2; jj++) {
						data2[ii][jj] = model2.getValueAt(ii, jj);
					}	
				}
								
				data2[rowCount2 - 1][2] = static_identifiersScrollPanel.get_static_info_from_GUI();
				data2[rowCount2 - 1][3] = static_identifiersScrollPanel_silviculture.get_static_info_from_GUI();
				data2[rowCount2 - 1][colCount2 - 1] = true;
				model2.updateTableModelPrism(rowCount2, colCount2, data2, columnNames2);
				update_id();
				model2.fireTableDataChanged();
				quick_edit = new QuickEdit_SilvicultureMethod_Panel(table2, data2);		// 2 lines to update data for Quick Edit Panel
	 			scrollpane_QuickEdit.setViewportView(quick_edit);
				
				// Convert the new Row to model view and then select it 
				int newRow = table2.convertRowIndexToView(rowCount2 - 1);
				table2.setRowSelectionInterval(newRow, newRow);
				table2.scrollRectToVisible(new Rectangle(table2.getCellRect(newRow, 0, true)));
			});
										
			
			// Edit
			btn_Edit.addActionListener(e -> {
				if (table2.isEnabled()) {			
					
					//A  resizable popup panel indicating changes have been made
					JPanel popup = new JPanel(new BorderLayout());
					popup.setBorder(null);
					popup.setPreferredSize(new Dimension(400, 150));	
					JLabel temp_label = new JLabel(IconHandle.get_scaledImageIcon(150, 150, "pikachuHello.png"));	
//					JLabel temp_label = new JLabel(IconHandle.get_scaledImageIcon_replicate(150, 150, "pikachuDance.gif"));
					popup.add(temp_label, BorderLayout.WEST);
					
					JTextArea temp_textarea = new JTextArea();
					temp_textarea.setBorder(null);
					temp_textarea.setBackground(ColorUtil.makeTransparent(Color.WHITE, 0)); 
					temp_textarea.setFocusable(false);
					temp_textarea.setEditable(false);
					temp_textarea.setLineWrap(true);
					temp_textarea.setWrapStyleWord(true);
					temp_textarea.append("The following infomation (in rectangles surrounded by green border) will be applied to the highlighted (blue) sm" + "\n \n");
					temp_textarea.append("1. Strata with selected Layers Attributes" + "\n");
					temp_textarea.append("2. Silviculture Method & Timing Choice" + "\n");
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
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), popup, "Do you want to modify the highlighted sm ?",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);	
											
					if (response == 0) {
						// Apply change
						int selectedRow = table2.getSelectedRow();
						selectedRow = table2.convertRowIndexToModel(selectedRow);		// Convert row index because "Sort" causes problems		
						data2[selectedRow][2] = static_identifiersScrollPanel.get_static_info_from_GUI();
						data2[selectedRow][3] = static_identifiersScrollPanel_silviculture.get_static_info_from_GUI();
						model2.fireTableDataChanged();	
						
						// Convert the edited Row to model view and then select it 
						int editRow = table2.convertRowIndexToView(selectedRow);
						table2.setRowSelectionInterval(editRow, editRow);
						
						// Enable buttons and table2
						table2.setEnabled(true);
						btn_NewSingle.setEnabled(true);
						btn_Delete.setEnabled(true);
						btn_Sort.setEnabled(true);
						btn_Edit.setEnabled(true);
						table_ScrollPane.setViewportView(table2);
						
						// Reset the view
						int currentRow = table2.getSelectedRow();
						currentRow = table2.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data2[currentRow][2]);	// 2 is the static_identifiers which have some attributes selected				
						static_identifiersScrollPanel_silviculture.reload_this_constraint_static_identifiers((String) data2[currentRow][3]);	// 3 is the method & choice
					}
					
				} 
			});
			
			
			btn_Edit.addMouseListener(new MouseAdapter() { // Add listener
				public void mouseEntered(java.awt.event.MouseEvent e) {
					static_identifiersScrollPanel.setBackground(new Color(0, 255, 0));
					static_identifiersScrollPanel_silviculture.setBackground(new Color(0, 255, 0));
				}

				public void mouseExited(java.awt.event.MouseEvent e) {
					static_identifiersScrollPanel.setBackground(null);
					static_identifiersScrollPanel_silviculture.setBackground(null);
				}
			});
			
				
			// Delete
			btn_Delete.addActionListener(e -> {	
				//Cancel editing before delete
				if (table2.isEditing()) {
					table2.getCellEditor().cancelCellEditing();
				}				
				
				// Get selected rows
				int[] selectedRow = table2.getSelectedRows();	
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table2.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
				}
				
				// Create a list of selected row indexes
				List<Integer> selected_Index = new ArrayList<Integer>();				
				for (int i: selectedRow) {
					selected_Index.add(i);
				}	
				
				// Get values to the new data2
				data2 = new Object[rowCount2 - selectedRow.length][colCount2];
				int newRow =0;
				for (int ii = 0; ii < rowCount2; ii++) {
					if (!selected_Index.contains(ii)) {			//If row not in the list then add to data2 row
						for (int jj = 0; jj < colCount2; jj++) {
							data2[newRow][jj] = model2.getValueAt(ii, jj);
						}
						newRow++;
					}
				}
				// Pass back the info to table model
				rowCount2 = rowCount2 - selectedRow.length;
				model2.updateTableModelPrism(rowCount2, colCount2, data2, columnNames2);
				model2.fireTableDataChanged();	
				quick_edit = new QuickEdit_SilvicultureMethod_Panel(table2, data2);	// 2 lines to update data for Quick Edit Panel
	 			scrollpane_QuickEdit.setViewportView(quick_edit);
			});
					
			
			// Sort
			btn_Sort.addActionListener(e -> {					
				if (btn_Sort.getText().equals("ON")) {
					table2.setRowSorter(null);
					btn_Sort.setText("OFF");
					btn_Sort.repaint();
				} else if (btn_Sort.getText().equals("OFF")) {
					TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model2); // Add sorter
					table2.setRowSorter(sorter);
					btn_Sort.setText("ON");
					btn_Sort.repaint();
				}	
			});
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------			
			
			
			
			
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new QuickEdit_SilvicultureMethod_Panel(table2, data2);
 			scrollpane_QuickEdit = new JScrollPane(quick_edit);
 			border = new TitledBorder("Quick Edit");
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
			btnQuickEdit.addActionListener(e -> {		
				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
						scrollpane_QuickEdit.setVisible(true);
						// Get everything show up nicely
						GUI_Text_splitPanel.setLeftComponent(panel_Silviculture_Method_GUI);
						PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Silviculture_Method_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}				
			});
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {
				
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
			c.weightx = 1;
		    c.weighty = 1;
		    	    
		    // Add helpToolBar	
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			super.add(helpToolBar, c);				
			
			// Add the 1st grid - static_identifiersScrollPanel to the main Grid 
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(static_identifiersScrollPanel, c);
			
			// Add the 2nd grid -  to the main Grid	
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 1;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(static_identifiersScrollPanel_silviculture, c);
			
			// Add the button_table_Panel & scrollpane_QuickEdit to a new Panel then add that panel to the main Grid
			JPanel button_table_qedit_panel = new JPanel();
			button_table_qedit_panel.setLayout(new BorderLayout());
			button_table_qedit_panel.add(button_table_Panel, BorderLayout.CENTER);
			button_table_qedit_panel.add(scrollpane_QuickEdit, BorderLayout.EAST);			
			c.gridx = 0;
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
			
			for (int row = 0; row < rowCount2; row++) {
				if (data2[row][0] != null) {
					id_list.add((int) data2[row][0]);
				}
			}			
			
			for (int row = 0; row < rowCount2; row++) {
				if (data2[row][0] == null) {
					int new_id = (id_list.size() > 0) ? Collections.max(id_list) + 1 : 1;	//new id = (max id + 1) or = 1 if no row
					data2[row][0] = new_id;
					id_list.add(new_id);
				}
			}			
		}
	}

	class Silviculture_Method_Text  extends JTextArea {
		public Silviculture_Method_Text() {
	
		}
	}	
	
	
	
	
	// Panel Model_Strata--------------------------------------------------------------------------------------------------
	class Model_Strata_GUI extends JLayeredPane implements ItemListener {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
		
		QuickEdit_ModelStrata_Panel quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Model_Strata_GUI() {
			setLayout(new GridBagLayout());
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------	
			String panel_name = "Strata Filter";
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_database, 0, panel_name);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();	
			
			for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
				for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
					checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
					checkboxStaticIdentifiers.get(i).get(j).addItemListener(this);
				}
			}
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------

			
			
			
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------			
			create_table_overview();      
			PrismTitleScrollPane overviewScrollPane = new PrismTitleScrollPane("Model Overview", "CENTER", table_overview);
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------
			
					
			
			// 3rd grid -----------------------------------------------------------------------
			// 3rd grid -----------------------------------------------------------------------
			create_table3();	
			table3.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event) {
					int[] selectedRow = table3.getSelectedRows();	
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table3.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
					}
					
					int highlighted_strata = 0;
					double highlighted_acres = 0;
					for (int i: selectedRow) {
						highlighted_strata++;
						highlighted_acres = highlighted_acres + Double.parseDouble(data3[i][colCount3 - 3].toString());
					}	
					data_overview[2][1] = highlighted_strata + "   --o--   " + highlighted_acres;
					model_overview.fireTableDataChanged();
				}
			});
			 
			PrismTitleScrollPane table_scrollPane = new PrismTitleScrollPane(
					"Existing strata at the start of planning horizon. Only strata checked as model_strata would be included into optimization model", "CENTER", table3);
			// End of 3rd grid -----------------------------------------------------------------------
			// End of 3rd grid -----------------------------------------------------------------------
			
					
			
			
			
			// 2 buttons------------------------------------------------------------------------------
			// 2 buttons------------------------------------------------------------------------------
			//button 1
			JButton button_remove_Strata = new JButton();
			button_remove_Strata.setToolTipText("Uncheck highlighted strata");
			button_remove_Strata.setIcon(IconHandle.get_scaledImageIcon(30, 30, "icon_uncheck.png"));
			button_remove_Strata.addActionListener(e -> {
				int[] selectedRow = table3.getSelectedRows();	
				///Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table3.convertRowIndexToModel(selectedRow[i]);
				}
				table3.clearSelection();	//To help trigger the row refresh: clear then add back the rows
				for (int i: selectedRow) {
					data3[i][colCount3 - 1] = false;
					model3.setValueAt(data3[i][colCount3 - 1], i, colCount3 - 1);	// this is just to trigger the update_model_overview
					table3.addRowSelectionInterval(table3.convertRowIndexToView(i),table3.convertRowIndexToView(i));
				}						
			});
			button_remove_Strata.setContentAreaFilled(false);
			button_remove_Strata.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			    	button_remove_Strata.setContentAreaFilled(true);
			    }

			    public void mouseExited(MouseEvent e) {
			    	button_remove_Strata.setContentAreaFilled(false);
			    }
			});
	
			
			
			//button 2	
			button_select_Strata = new JButton();
			button_select_Strata.setToolTipText("Check highlighted strata");
			button_select_Strata.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_check.png"));
			button_select_Strata.addActionListener(e -> {					
				int[] selectedRow = table3.getSelectedRows();	
				///Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table3.convertRowIndexToModel(selectedRow[i]);
				}
				table3.clearSelection();	//To help trigger the row refresh: clear then add back the rows
				for (int i: selectedRow) {
					data3[i][colCount3 - 1] = true;
					model3.setValueAt(data3[i][colCount3 - 1], i, colCount3 - 1);	// this is just to trigger the update_model_overview
					table3.addRowSelectionInterval(table3.convertRowIndexToView(i),table3.convertRowIndexToView(i));
				}	
			});
			button_select_Strata.setContentAreaFilled(false);
			button_select_Strata.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			    	button_select_Strata.setContentAreaFilled(true);
			    }

			    public void mouseExited(MouseEvent e) {
			    	button_select_Strata.setContentAreaFilled(false);
			    }
			});
			// End of 2 buttons------------------------------------------------------------------------------
			// End of 2 buttons------------------------------------------------------------------------------
						
			
			
			
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new QuickEdit_ModelStrata_Panel(table3, data3);
			quick_edit.setLayout(new FlowLayout());
			quick_edit.add(button_select_Strata);
			quick_edit.add(button_remove_Strata);		
 			scrollpane_QuickEdit = new JScrollPane(quick_edit);
 			TitledBorder border = new TitledBorder("Quick Edit");
 			border.setTitleJustification(TitledBorder.CENTER);
 			scrollpane_QuickEdit.setBorder(border);
 			scrollpane_QuickEdit.setVisible(false);	
 			
 			
			
			
			// ToolBar Panel ----------------------------------------------------------------------------
			// ToolBar Panel ----------------------------------------------------------------------------
			ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
			helpToolBar.setFloatable(false);	//to make a tool bar immovable
			helpToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor
			helpToolBar.setBorderPainted(false);
			
			// button Strata Filter
			JToggleButton btnQuickEdit = new JToggleButton();
			btnQuickEdit.setToolTipText("Show Quick Edit Tool");
			btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
			btnQuickEdit.addActionListener(e -> {
				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
						scrollpane_QuickEdit.setVisible(true);
						// Get everything show up nicely
						GUI_Text_splitPanel.setLeftComponent(panel_Model_Strata_GUI);
						PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Model_Strata_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}
			});
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

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
			c.weightx = 1;
		    c.weighty = 1;
		    	    
		    // Add helpToolBar	
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			super.add(helpToolBar, c);				
			
			// Add the 1st grid - checkPanel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(static_identifiersScrollPanel, c);
			
			// Add the 2nd grid - overviewScrollPane to the main Grid	
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 1;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(overviewScrollPane, c);
									
			// Add the table_scrollPane & scrollpane_QuickEdit to a new Panel then add that panel to the main Grid
			JPanel table_qedit_panel = new JPanel();
			table_qedit_panel.setLayout(new BorderLayout());
			table_qedit_panel.add(table_scrollPane, BorderLayout.CENTER);
			table_qedit_panel.add(scrollpane_QuickEdit, BorderLayout.EAST);			
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2; 
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 1;
			super.add(table_qedit_panel, c);
		}
		
		
		//Listeners for checkBox Filter--------------------------------------------------------------------
		public void itemStateChanged(ItemEvent e) {

			if (data3 != null) {		//Only allow sorter if the data of existing strata is loaded
				//This help filter to get the strata as specified by the CheckBoxes
				TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model3);
				table3.setRowSorter(sorter);
				List<RowFilter<PrismTableModel, Object>> filters, filters2;
				filters2 = new ArrayList<RowFilter<PrismTableModel, Object>>();
				for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
					RowFilter<PrismTableModel, Object> layer_filter = null;
					filters = new ArrayList<RowFilter<PrismTableModel, Object>>();
					for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
						if (checkboxStaticIdentifiers.get(i).get(j).isSelected()) {
							filters.add(RowFilter.regexFilter(checkboxStaticIdentifiers.get(i).get(j).getText(), i + 1)); // i+1 is the table column containing the first layer	
						}
					}
					layer_filter = RowFilter.orFilter(filters);

					filters2.add(layer_filter);
				}
				RowFilter<PrismTableModel, Object> combine_AllFilters = null;
				combine_AllFilters = RowFilter.andFilter(filters2);
				sorter.setRowFilter(combine_AllFilters);
			}
		}
	}

		
	class Model_Strata_Text extends JLayeredPane {
		public Model_Strata_Text() {
			
		}
	}
	

	
	
	// Panel Covertype Conversion------------------------------------------------------------------------------------------------
	class Covertype_Conversion_GUI extends JLayeredPane {
		public Covertype_Conversion_GUI() {
			setLayout(new GridBagLayout());

			
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
	        create_table4();
			PrismTitleScrollPane CovertypeConversion_EA_ScrollPane = new PrismTitleScrollPane("Cover type conversion & rotation ageclass for clear cut", "CENTER", table4);										    
		    
				        
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
	        create_table5();
	        PrismTitleScrollPane CovertypeConversion_SRD_ScrollPane = new PrismTitleScrollPane("Cover type conversion & regeneration for replacing disturbance", "CENTER", table5);									
		    

			// scrollPane Quick Edit 1 & 2-----------------------------------------------------------------------
			// scrollPane Quick Edit 1 @ 2-----------------------------------------------------------------------		
			JScrollPane scrollpane_QuickEdit_1 = new JScrollPane(new QuickEdit_EA_Conversion_Panel(table4, data4));
			JScrollPane scrollpane_QuickEdit_2 = new JScrollPane(new QuickEdit_RD_Conversion_Panel(table5, data5));	
			
			TitledBorder border = new TitledBorder("Quick Edit ");
			border.setTitleJustification(TitledBorder.CENTER);
			scrollpane_QuickEdit_1.setBorder(border);
			scrollpane_QuickEdit_2.setBorder(border);
			scrollpane_QuickEdit_1.setVisible(false);
			scrollpane_QuickEdit_2.setVisible(false);
			
			
			
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
			btnQuickEdit.addActionListener(e -> {	
				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
					btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
					scrollpane_QuickEdit_1.setVisible(true);
					scrollpane_QuickEdit_2.setVisible(true);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Covertype_Conversion_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit_1.setVisible(false);
					scrollpane_QuickEdit_2.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Covertype_Conversion_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}
			});			
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

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
			c.weightx = 1;
		    c.weighty = 1;

		    // Add btnQuickEdit	
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			super.add(helpToolBar, c);			
		    
			// Add the 1st grid - CovertypeConversion_EA_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
		    c.weighty = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(CovertypeConversion_EA_ScrollPane, c);
			
			// Add scrollpane_QuickEdit_1	
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(scrollpane_QuickEdit_1, c);			
			
			// Add the 2nd grid - CovertypeConversion_SRD_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
		    c.weighty = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(CovertypeConversion_SRD_ScrollPane, c);
			
			// Add scrollpane_QuickEdit_2	
			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 0;
			c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(scrollpane_QuickEdit_2, c);
		}
	}
	
	class Covertype_Conversion_Text extends JLayeredPane {
	    public Covertype_Conversion_Text() {

	    	
	     }
	}	
	

	// Panel Natural_Disturbances-----------------------------------------------------------------------------------------------------------
	class Natural_Disturbances_GUI extends JLayeredPane {
		public Natural_Disturbances_GUI() {
			setLayout(new GridBagLayout());


			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
			create_table6();
			PrismTitleScrollPane nonStandReplacing_ScrollPane = new PrismTitleScrollPane(
					"Proportion (%) of period-one existing strata suffered from NON-REPLACING DISTURBANCES across all time periods", "CENTER", table6);		
			
		    			
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
	        create_table7();
	        PrismTitleScrollPane StandReplacing_ScrollPane = new PrismTitleScrollPane(
	        		"Proportion (%) of existing strata or regeneration strata suffered from REPLACING DISTURBANCES in each time period", "CENTER", table7);	
				        
	        
	        // scrollPane Quick Edit 1 & 2-----------------------------------------------------------------------
	        // scrollPane Quick Edit 1 @ 2-----------------------------------------------------------------------		
 			JScrollPane scrollpane_QuickEdit_1 = new JScrollPane(new QuickEdit_NonRD_Percentage_Panel(table6, data6));
 			JScrollPane scrollpane_QuickEdit_2 = new JScrollPane(new QuickEdit_RD_Percentage_Panel(table7, data7));	
 			
 			TitledBorder border = new TitledBorder("Quick Edit ");
 			border.setTitleJustification(TitledBorder.CENTER);
 			scrollpane_QuickEdit_1.setBorder(border);
 			scrollpane_QuickEdit_2.setBorder(border);
 			scrollpane_QuickEdit_1.setVisible(false);
 			scrollpane_QuickEdit_2.setVisible(false);
 			
 			

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
 			btnQuickEdit.addActionListener(e -> {
 				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
					btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
					scrollpane_QuickEdit_1.setVisible(true);
					scrollpane_QuickEdit_2.setVisible(true);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Natural_Disturbances_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit_1.setVisible(false);
					scrollpane_QuickEdit_2.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Natural_Disturbances_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}
 			});			
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

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
			c.weightx = 1;
		    c.weighty = 1;

		    // Add helpToolBar	
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			super.add(helpToolBar, c);			
		    
			// Add the 1st grid - CovertypeConversion_EA_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
		    c.weighty = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(nonStandReplacing_ScrollPane, c);
			
			// Add scrollpane_QuickEdit_1	
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(scrollpane_QuickEdit_1, c);			
			
			// Add the 2nd grid - CovertypeConversion_SRD_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
		    c.weighty = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(StandReplacing_ScrollPane, c);
			
			// Add scrollpane_QuickEdit_2	
			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 0;
			c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(scrollpane_QuickEdit_2, c);					        
		}

	}

		
	class Natural_Disturbances_Text extends JLayeredPane {
		public Natural_Disturbances_Text() {

		}
	}
	
	
	// Panel Management_Cost------------------------------------------------------------------------------------------------------	
	class Management_Cost_GUI extends JLayeredPane implements ActionListener {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
		ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
		ScrollPane_CostTables cost_tables_ScrollPane;
		

		public Management_Cost_GUI() {
			setLayout(new GridBagLayout());		
			
			
			// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
			String panel_name = "Static Identifiers  -  use strata attributes to filter variables";
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_database, 2, panel_name);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();
			// End of 1st grid -----------------------------------------------------------------------

			
			// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
			dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_database);
			// End of 2nd Grid -----------------------------------------------------------------------
				
					
			// 3rd grid ------------------------------------------------------------------------------		// Parameters
			create_table8a();
			create_table8b();
//			model8a.match_DataType();		//a smart way to retrieve the original data type :))))))
//			model8b.match_DataType();		//a smart way to retrieve the original data type :))))))
			cost_tables_ScrollPane = new ScrollPane_CostTables(table8a, data8a, columnNames8a, table8b, data8b, columnNames8b);
			// End of 3rd grid -----------------------------------------------------------------------
				    			
	
			// 4th Grid ------------------------------------------------------------------------------		// Buttons	
			// 4th Grid -----------------------------------------------------------------------------
			JPanel cost_condition_panel = new JPanel(new GridBagLayout());
			TitledBorder border = new TitledBorder("Conditons to apply Costs in Priority Order");
			border.setTitleJustification(TitledBorder.CENTER);
			cost_condition_panel.setBorder(border);
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(0, 5, 10, 10); // padding top 0, left 5, bottom 10, right 10
			
			
			JButton btn_EditMode = new JButton();
			btn_EditMode.setFont(new Font(null, Font.BOLD, 14));
//			btn_EditMode.setText("EDIT MODE");
			btn_EditMode.setToolTipText("OPEN FLEXIBLE VIEW MODE");
			btn_EditMode.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_solve1.png"));
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0;
			c.weighty = 0;
			cost_condition_panel.add(btn_EditMode, c);
			
			
			JButton btn_New = new JButton();
			btn_New.setFont(new Font(null, Font.BOLD, 14));
//			btn_New.setText("NEW SET");
			btn_New.setToolTipText("New condition");
			btn_New.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));	
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0;
			c.weighty = 0;
			cost_condition_panel.add(btn_New, c);		
	
			
			JButton btn_Edit = new JButton();
//			btn_Edit.setText("EDIT");
			btn_Edit.setToolTipText("Modify condition");
			btn_Edit.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_swap.png"));
			btn_Edit.setEnabled(false);	
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 0;
			c.weighty = 0;
			cost_condition_panel.add(btn_Edit, c);
			

			// Add Spinner to move priority up or down
			JSpinner spin_priority = new JSpinner (new SpinnerNumberModel(1, 0, 2, 1));
			spin_priority.setToolTipText("Increase or decrease priority");
			JFormattedTextField SpinnerText = ((DefaultEditor) spin_priority.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
//		    formatter.setCommitsOnValidEdit(true);
		    spin_priority.setEnabled(false);
		    c.gridx = 0;
			c.gridy = 3;
			c.weightx = 0;
			c.weighty = 0;
			cost_condition_panel.add(spin_priority, c);
		    

			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 14));
//			btn_Delete.setText("DELETE");
			btn_Delete.setToolTipText("Delete conditions");
			btn_Delete.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_erase.png"));
			btn_Delete.setEnabled(false);
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 0;
			c.weighty = 0;
			cost_condition_panel.add(btn_Delete, c);
			
			
			// Add Empty Label to make all buttons on top not middle
			c.insets = new Insets(0, 0, 0, 0); // No padding			
			c.gridx = 0;
			c.gridy = 5;
			c.weightx = 0;
			c.weighty = 1;
			cost_condition_panel.add(new JLabel(), c);
			
			// Add table8				
			create_table8();
			JScrollPane table_ScrollPane = new JScrollPane(table8);
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.gridheight = 6;
			cost_condition_panel.add(table_ScrollPane, c);
						
			
			// Add Listeners for buttons----------------------------------------------------------
			// Add Listeners for buttons----------------------------------------------------------		
			// table8
			table8.addMouseListener(new MouseAdapter() { // Add listener
				@Override
				public void mouseReleased(MouseEvent e) {
					int[] selectedRow = table8.getSelectedRows();
					
					if (selectedRow.length == 1) {		// Show the set's identifiers
						int currentRow = selectedRow[0];
						currentRow = table8.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data8[currentRow][4]);	// 4 is the static_identifiers which have some attributes selected				
						dynamic_identifiersScrollPanel.reload_this_constraint_dynamic_identifiers((String) data8[currentRow][5], (String) data8[currentRow][6]);	// 6 is the original_dynamic_identifiers column
						cost_tables_ScrollPane.reload_this_condition_action_cost_and_conversion_cost((String) data8[currentRow][2], (String) data8[currentRow][3]);
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table8.isEnabled()) {		// Enable Delete & Spinner when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
						spin_priority.setEnabled(true);
					} else {		// Disable Delete & Spinner
						btn_Delete.setEnabled(false);
						spin_priority.setEnabled(false);
					}		
				}
			});
			
			table8.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selectedRow = table8.getSelectedRows();
		        	
		        	if (selectedRow.length == 1) {		// Show the set's identifiers
						int currentRow = selectedRow[0];
						currentRow = table8.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data8[currentRow][4]);	// 4 is the static_identifiers which have some attributes selected				
						dynamic_identifiersScrollPanel.reload_this_constraint_dynamic_identifiers((String) data8[currentRow][5], (String) data8[currentRow][6]);	// 6 is the original_dynamic_identifiers column
						cost_tables_ScrollPane.reload_this_condition_action_cost_and_conversion_cost((String) data8[currentRow][2], (String) data8[currentRow][3]);
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
		        	
					if (selectedRow.length >= 1 && table8.isEnabled()) {		// Enable Delete & Spinner when: >=1 row is selected,table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
						spin_priority.setEnabled(true);
					} else {		// Disable Delete & Spinner
						btn_Delete.setEnabled(false);
						spin_priority.setEnabled(false);
					}	
		        }
		    });
			
			
			// EDIT MODE
			btn_EditMode.addActionListener(e -> {
//					//	These codes make the popupPanel resizable --> the Big ScrollPane resizable --> JOptionPane resizable
//					JScrollPane popup_scroll = new JScrollPane(panel_Management_Cost_GUI);
//					popup_scroll.addHierarchyListener(new HierarchyListener() {
//					    public void hierarchyChanged(HierarchyEvent e) {
//					        Window window = SwingUtilities.getWindowAncestor(popup_scroll);
//					        if (window instanceof Dialog) {
//					            Dialog dialog = (Dialog)window;
//					            if (!dialog.isResizable()) {
//					                dialog.setResizable(true);
//					            }
//					        }
//					    }
//					});				
//					popup_scroll.setBorder(null);
//					popup_scroll.setPreferredSize(new Dimension((int) (PrismMain.get_main().getWidth() * 0.85), (int) (PrismMain.get_main().getHeight() * 0.65)));	
//
//					String ExitOption[] = {"EXIT"};
//					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), popup_scroll, "Flexible View Mode - You can resize this window",
//							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);					
//					if (response == 0) {																									
//					}
//					
//					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
			});
			
			
			// New Condition
			btn_New.addActionListener(e -> {														
				// Add 1 row
				rowCount8++;
				data8 = new Object[rowCount8][colCount8];
				for (int ii = 0; ii < rowCount8 - 1; ii++) {
					for (int jj = 0; jj < colCount8; jj++) {
						data8[ii][jj] = model8.getValueAt(ii, jj);
					}	
				}
					
				data8[rowCount8 - 1][2] = cost_tables_ScrollPane.get_action_cost_info_from_GUI();
				data8[rowCount8 - 1][3] = cost_tables_ScrollPane.get_conversion_cost_info_from_GUI();
				data8[rowCount8 - 1][4] = static_identifiersScrollPanel.get_static_info_from_GUI();
				data8[rowCount8 - 1][5] = dynamic_identifiersScrollPanel.get_dynamic_info_from_GUI();
				data8[rowCount8 - 1][6] = dynamic_identifiersScrollPanel.get_original_dynamic_info_from_GUI();
								
				model8.updateTableModelPrism(rowCount8, colCount8, data8, columnNames8);
				model8.fireTableDataChanged();		
				
				// Convert the new Row to model view and then select it 
				int newRow = table8.convertRowIndexToView(rowCount8 - 1);
				table8.setRowSelectionInterval(newRow, newRow);
				update_id();
				table8.scrollRectToVisible(new Rectangle(table8.getCellRect(newRow, 0, true)));	
			});

			
			// Edit
			btn_Edit.addActionListener(e -> {
				if (table8.isEnabled()) {
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
					temp_textarea.append("The following infomation (in rectangles surrounded by green border) will be applied to the highlighted (blue) condition" + "\n \n");
					temp_textarea.append("1. Static Identifiers" + "\n");
					temp_textarea.append("2. Dynamic Identifiers" + "\n");
					temp_textarea.append("3. Action Cost" + "\n");
					temp_textarea.append("4. Conversion Cost" + "\n");	
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
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), popup, "Do you want to modify the highlighted condition ?",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);	
											
					if (response == 0) {
						// Apply change
						int selectedRow = table8.getSelectedRow();
						selectedRow = table8.convertRowIndexToModel(selectedRow);		// Convert row index because "Sort" causes problems	
						data8[selectedRow][2] = cost_tables_ScrollPane.get_action_cost_info_from_GUI();
						data8[selectedRow][3] = cost_tables_ScrollPane.get_conversion_cost_info_from_GUI();
						data8[selectedRow][4] = static_identifiersScrollPanel.get_static_info_from_GUI();
						data8[selectedRow][5] = dynamic_identifiersScrollPanel.get_dynamic_info_from_GUI();
						data8[selectedRow][6] = dynamic_identifiersScrollPanel.get_original_dynamic_info_from_GUI();
						model8.fireTableDataChanged();	
						
						// Convert the edited Row to model view and then select it 
						int editRow = table8.convertRowIndexToView(selectedRow);
						table8.setRowSelectionInterval(editRow, editRow);
						
						// Enable buttons and table8
						table8.setEnabled(true);
						btn_New.setEnabled(true);
						btn_Delete.setEnabled(true);	
						btn_Edit.setEnabled(true);
						table_ScrollPane.setViewportView(table8);
						
						// Reset the view
						int currentRow = table8.getSelectedRow();
						currentRow = table8.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems	
						static_identifiersScrollPanel.reload_this_constraint_static_identifiers((String) data8[currentRow][4]);	// 4 is the static_identifiers which have some attributes selected				
						dynamic_identifiersScrollPanel.reload_this_constraint_dynamic_identifiers((String) data8[currentRow][5], (String) data8[currentRow][6]);	// 6 is the original_dynamic_identifiers column
						cost_tables_ScrollPane.reload_this_condition_action_cost_and_conversion_cost((String) data8[currentRow][2], (String) data8[currentRow][3]);
					}
				} 
			});			
			
			
			btn_Edit.addMouseListener(new MouseAdapter() { // Add listener
				public void mouseEntered(java.awt.event.MouseEvent e) {
					static_identifiersScrollPanel.setBackground(new Color(0, 255, 0));
					dynamic_identifiersScrollPanel.setBackground(new Color(0, 255, 0));
					cost_tables_ScrollPane.get_action_base_adjust_scrollpane().setBackground(new Color(0, 255, 0));
					cost_tables_ScrollPane.get_conversion_base_adjust_scrollpane().setBackground(new Color(0, 255, 0));
				}

				public void mouseExited(java.awt.event.MouseEvent e) {
					static_identifiersScrollPanel.setBackground(null);
					dynamic_identifiersScrollPanel.setBackground(null);
					cost_tables_ScrollPane.get_action_base_adjust_scrollpane().setBackground(null);
					cost_tables_ScrollPane.get_conversion_base_adjust_scrollpane().setBackground(null);
				}
			});
			
			
			// Spinner
		    spin_priority.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
					int up_or_down = (int) spin_priority.getValue() - 1;										
					spin_priority.setValue((int) 1);	// Reset spinner value to 1
					
					
					if (up_or_down == 1) {	// move up
						// Cancel editing before moving conditions up or down
						if (table8.isEditing()) {
							table8.getCellEditor().cancelCellEditing();
						}	
						
						// Get selected rows
						int[] selectedRow = table8.getSelectedRows();		// No need to convert row index because we never allow Sort on this table
						List<Integer> selectedRowList = new ArrayList<Integer>() {{ for (int i : selectedRow) add(i);}};	// Convert array to list
						
						if (selectedRowList.get(0) > 0) {	// If the first row (1st priority condition) is not selected
							for (int i = 0; i < rowCount8; i++) {
								if (selectedRowList.contains(i)) {		
									for (int j = 1; j < colCount8; j++) {	// not change the number in the priority column (j=0)
										Object temp = data8[i - 1][j];
										data8[i - 1][j] = data8[i][j];
										data8[i][j] = temp;
									}
								}
							}
							// Update the changes and select the currently selected conditions
							model8.fireTableDataChanged();
							for (int i: selectedRow) {
								table8.addRowSelectionInterval(i - 1, i - 1);
							}
						}
					}
					
					
					if (up_or_down == -1) {	// move down
						//Cancel editing before moving conditions up or down
						if (table8.isEditing()) {
							table8.getCellEditor().cancelCellEditing();
						}	
						
						// Get selected rows
						int[] selectedRow = table8.getSelectedRows();		// No need to convert row index because we never allow Sort on this table
						List<Integer> selectedRowList = new ArrayList<Integer>() {{ for (int i : selectedRow) add(i);}};	// Convert array to list
						
						if (selectedRowList.get(selectedRowList.size() - 1) < rowCount8 - 1) {	// If 
							for (int i = rowCount8 - 1; i >= 0; i--) {
								if (selectedRowList.contains(i)) {		
									for (int j = 1; j < colCount8; j++) {	// not change the number in the priority column (j=0)
										Object temp = data8[i + 1][j];
										data8[i + 1][j] = data8[i][j];
										data8[i][j] = temp;
									}
								}
							}						
							// Update the changes and select the currently selected conditions
							model8.fireTableDataChanged();
							for (int i: selectedRow) {
								table8.addRowSelectionInterval(i + 1, i + 1);
							}	
						}
						

					}
		        }
		    });
		    
			
			// Delete
			btn_Delete.addActionListener(e -> {
				//Cancel editing before delete
				if (table8.isEditing()) {
					table8.getCellEditor().cancelCellEditing();
				}				
				
				// Get selected rows
				int[] selectedRow = table8.getSelectedRows();	
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table8.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
				}
				
				// Create a list of selected row indexes
				List<Integer> selected_Index = new ArrayList<Integer>();				
				for (int i: selectedRow) {
					selected_Index.add(i);
				}	
				
				// Get values to the new data8
				data8 = new Object[rowCount8 - selectedRow.length][colCount8];
				int newRow =0;
				for (int ii = 0; ii < rowCount8; ii++) {
					if (!selected_Index.contains(ii)) {			//If row not in the list then add to data8 row
						for (int jj = 0; jj < colCount8; jj++) {
							data8[newRow][jj] = model8.getValueAt(ii, jj);
						}
						newRow++;
					}
				}
				// Pass back the info to table model
				rowCount8 = rowCount8 - selectedRow.length;
				model8.updateTableModelPrism(rowCount8, colCount8, data8, columnNames8);
				
				model8.fireTableDataChanged();	
			});			
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------		    
		    
	
			
				
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			JScrollPane scrollpane_QuickEdit = new JScrollPane(new QuickEdit_ManagementCost_Panel(table8a, data8a, columnNames8a, table8b, data8b));			
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
 			btnQuickEdit.addActionListener(e -> {
 				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
					btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
					scrollpane_QuickEdit.setVisible(true);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}
 			});				
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

			});
			
			// Add all buttons to flow_panel
			helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
			helpToolBar.add(btnQuickEdit);
			helpToolBar.add(btnHelp);
			// End of ToolBar Panel -----------------------------------------------------------------------
			// End of ToolBar Panel ----------------------------------------------------------------------- 				
				
				
			
			    			    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;

			// Add helpToolBar to the main Grid
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 4;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(helpToolBar, c);				
			
			// Add static_identifiersScrollPanel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(static_identifiersScrollPanel, c);				
		    		
			// Add dynamic_identifiersPanel to the main Grid
			c.gridx = 1;
			c.gridy = 1;
			c.gridwidth = 3;
			c.gridheight = 1;
			c.weightx = 1;
			c.weighty = 0;
			super.add(dynamic_identifiersScrollPanel, c);	
			
			    		
			// Add the cost_tables_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 1;
			super.add(cost_tables_ScrollPane, c);	
			
			// Add scrollpane_QuickEdit	
			c.gridx = 2;
			c.gridy = 2;
			c.weightx = 0;
		    c.weighty = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(scrollpane_QuickEdit, c);	
		    	    		    
		    // Add the cost_condition_panel	
			c.gridx = 3;
			c.gridy = 2;
			c.gridwidth = 1; 
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 1;
			super.add(cost_condition_panel, c);
			
			//when radioButton_Right[5] is selected, time period GUI will be updated
			radioButton_Right[5].addActionListener(this);			
		}
		
		// Listener for this class----------------------------------------------------------------------
		public void actionPerformed(ActionEvent e) {	    	
	    	//Update GUI for time period 
	    	for (int j = 0; j < checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).size(); j++) {			//The last element is Time period			
				if (j < totalPeriod) {
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(true);		//Periods to be visible 			
				} else {
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(false);		//Periods to be invisible
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setSelected(false);		//Periods to be unselected
				}
			}  	
	    	
	      	//Update Dynamic Identifier Panel
	    	if (yieldTable_ColumnNames != null && dynamic_identifiersScrollPanel.get_allDynamicIdentifiers() == null) {
	    		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_database);	// "Get identifiers from yield table columns"
	    	}	    	
		}	
		
	    // Update set_id column. set_id needs to be unique-----------------
	    public void update_id() {  		
			List<Integer> id_list = new ArrayList<Integer>();			
		
			for (int row = 0; row < rowCount8; row++) {
				if (data8[row][0] != null) {
					id_list.add(Integer.valueOf((String) data8[row][0].toString()/*.replace("Set ", "")*/));
				}
			}			
			
			for (int row = 0; row < rowCount8; row++) {
				if (data8[row][0] == null) {
					int new_id = (id_list.size() > 0) ? Collections.max(id_list) + 1 : 1;	//new id = (max id + 1) or = 1 if no row
					data8[row][0] = /*"Set " + */new_id;
					id_list.add(new_id);
				}
			}			
		}
	}

	class Management_Cost_Text extends JTextArea {
		public Management_Cost_Text() {		
			
		}
	}	
	
	

	// Panel Basic Constraints--------------------------------------------------------------------------------------------------------
	class Basic_Constraints_GUI extends JLayeredPane implements ActionListener {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		ScrollPane_Parameters parametersScrollPanel;
		ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
		ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
		JPanel button_table_Panel;
		
		QuickEdit_BasicConstraints_Panel quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Basic_Constraints_GUI() {
			setLayout(new GridBagLayout());
			
			
			// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
			String panel_name = "Static Identifiers  -  use strata attributes to filter variables";
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_database, 2, panel_name);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();
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
			TitledBorder border3 = new TitledBorder("Basic Constraints Information");
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
			
			
			JButton btn_Validate = new JButton();
			btn_Validate.setFont(new Font(null, Font.BOLD, 14));
//			btn_Validate.setText("VALIDATE");
			btn_Validate.setToolTipText("Validate constraints");
			btn_Validate.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_zoom.png"));
					
			c2.gridx = 0;
			c2.gridy = 5;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Validate, c2);
			
			
			c2.insets = new Insets(0, 0, 0, 0); // No padding
			// Add Empty Label to make all buttons on top not middle
			c2.gridx = 0;
			c2.gridy = 6;
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
			c2.gridheight = 7;
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
			btn_NewSingle.addActionListener(e -> {	
				// Add 1 row
				rowCount9++;
				data9 = new Object[rowCount9][colCount9];
				for (int ii = 0; ii < rowCount9 - 1; ii++) {
					for (int jj = 0; jj < colCount9; jj++) {
						data9[ii][jj] = model9.getValueAt(ii, jj);
					}	
				}
								
				data9[rowCount9 - 1][2] = "FREE";
				data9[rowCount9 - 1][3] = (double) 1;
				data9[rowCount9 - 1][8] = parametersScrollPanel.get_parameters_info_from_GUI();
				data9[rowCount9 - 1][9] = static_identifiersScrollPanel.get_static_info_from_GUI();
				data9[rowCount9 - 1][10] = dynamic_identifiersScrollPanel.get_dynamic_info_from_GUI();
				data9[rowCount9 - 1][11] = dynamic_identifiersScrollPanel.get_original_dynamic_info_from_GUI();
				
				model9.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);
				update_id();
				model9.fireTableDataChanged();
				quick_edit = new QuickEdit_BasicConstraints_Panel(table9, data9);		// 2 lines to update data for Quick Edit Panel
	 			scrollpane_QuickEdit.setViewportView(quick_edit);
				
				// Convert the new Row to model view and then select it 
				int newRow = table9.convertRowIndexToView(rowCount9 - 1);
				table9.setRowSelectionInterval(newRow, newRow);
				table9.scrollRectToVisible(new Rectangle(table9.getCellRect(newRow, 0, true)));
			});
			
			
			// New Multiple
			btn_New_Multiple.addActionListener(e -> {
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
//									System.out.println(total_same_info_constraints);
									
									

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
									data9[i][2] = (temp_data[0][2] == null) ? "FREE" : temp_data[0][2];
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
							quick_edit = new QuickEdit_BasicConstraints_Panel(table9, data9);	// 2 lines to update data for Quick Edit Panel
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
				if (response == 1) // Cancel: do nothing
				{
				}
			});			
			
			
			// Edit
			btn_Edit.addActionListener(e -> {
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
						btn_Validate.setEnabled(true);	
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
			btn_Delete.addActionListener(e -> {
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
				quick_edit = new QuickEdit_BasicConstraints_Panel(table9, data9);	// 2 lines to update data for Quick Edit Panel
	 			scrollpane_QuickEdit.setViewportView(quick_edit);
			});
					
			
			// Sort
			btn_Sort.addActionListener(e -> {
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
			});
			
			
			
			
			// Validate
			btn_Validate.addActionListener(e -> {						
//					// Apply change			
//					int[] selectedRow = table9.getSelectedRows();	
//					///Convert row index because "Sort" causes problems
//					for (int i = 0; i < selectedRow.length; i++) {
//						selectedRow[i] = table9.convertRowIndexToModel(selectedRow[i]);
//					}
//					table9.clearSelection();	//To help trigger the row refresh: clear then add back the rows
//					for (int i: selectedRow) {
//						if (String.valueOf(data9[i][2]).equalsIgnoreCase("SOFT")) {
//							data9[i][2] = "FREE";
//						} else if (String.valueOf(data9[i][2]).equalsIgnoreCase("HARD")) {
//							data9[i][2] = "FREE";
//						}
//						table9.addRowSelectionInterval(table9.convertRowIndexToView(i),table9.convertRowIndexToView(i));
//					}
			});			

			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------		    
		    
	
			
			
			
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new QuickEdit_BasicConstraints_Panel(table9, data9);
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
 			btnQuickEdit.addActionListener(e -> {		
 				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
					btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
					scrollpane_QuickEdit.setVisible(true);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Basic_Constraints_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Basic_Constraints_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}
 			});				
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

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
			
			//when radioButton_Right[6] is selected, time period GUI will be updated
			radioButton_Right[6].addActionListener(this);
		}
		
		
		// Listener for this class----------------------------------------------------------------------
	    public void actionPerformed(ActionEvent e) {
	    	
	    	//Update GUI for time period 
	    	for (int j = 0; j < checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).size(); j++) {			//The last element is Time period			
				if (j < totalPeriod) {
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(true);		//Periods to be visible 			
				} else {
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(false);		//Periods to be invisible
					checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setSelected(false);		//Periods to be unselected
				}
			}  	
	    	
	       	//Update Parameter Panel
	    	if (yieldTable_ColumnNames != null && parametersScrollPanel.get_checkboxParameter() == null) {
	    		parametersScrollPanel = new ScrollPane_Parameters(read_database);	//"Get parameters from YT columns"
	    	}
	    	
	      	//Update Dynamic Identifier Panel
	    	if (yieldTable_ColumnNames != null && dynamic_identifiersScrollPanel.get_allDynamicIdentifiers() == null) {
	    		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_database);	// "Get identifiers from yield table columns"
	    	}

	    	//Only set button_table_Panel visible when Parameter scroll Pane have checkboxes created
	    	if (parametersScrollPanel.get_checkboxParameter() == null) {
	    		button_table_Panel.setVisible(false);
	    	} else {
	    		button_table_Panel.setVisible(true);
	    	}
	    	
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

	class Basic_Constraints_Text  extends JTextArea {
		public Basic_Constraints_Text() {
	
		}
	}
		

	// Panel Advanced Constraints--------------------------------------------------------------------------------------------------------
	class Flow_Constraints_GUI extends JLayeredPane implements ActionListener {
		JTable basic_table;
		PrismTableModel model_basic;
		DefaultListModel id_list_model;
		JList id_list;
		JPanel button_table_Panel;
		
		QuickEdit_FlowConstraints_Panel quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Flow_Constraints_GUI() {
			setLayout(new GridBagLayout());
			
			
			// 1st grid ------------------------------------------------------------------------------		// 
			id_list_model= new DefaultListModel<>();
			id_list = new JList(id_list_model);		
			ScrollPane_ConstraintsFlow flow_scrollPane = new ScrollPane_ConstraintsFlow(id_list);
			flow_scrollPane.setBorder(null);
			// End of 1st grid -----------------------------------------------------------------------

			
			// 2nd Grid ------------------------------------------------------------------------------		// Basic Constraint Table
			model_basic = new PrismTableModel(rowCount9, colCount9, data9, columnNames9) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			basic_table = new JTable(model_basic) {
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
									
//					// Set background color
//					if (getValueAt(row, 2) == null || getValueAt(row, 2).toString().equals("FREE")) {
//						component.setBackground(getBackground());
//					} else if (getValueAt(row, 2).toString().equals("SOFT")) {
//						component.setBackground(ColorUtil.makeTransparent(new Color(27, 158, 119), 100));
//						
//					} else if (getValueAt(row, 2).toString().equals("HARD")) {
//						component.setBackground(ColorUtil.makeTransparent(new Color(217, 95, 2), 100));
//						
//					}
//					if (isRowSelected(row)) component.setBackground(getSelectionBackground());		// for selected row
							
					// Set icon for cells
					if (column == 2) {
						if (getValueAt(row, 2) == null || getValueAt(row, 2).toString().equals("FREE")) {
							((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_gray.png"));
						} else if (getValueAt(row, 2).toString().equals("SOFT")) {
							((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_yellow.png"));
						} else if (getValueAt(row, 2).toString().equals("HARD")) {
							((DefaultTableCellRenderer) component).setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_red.png"));
						}
					}
					
					return component;
				}
			};
			
//			basic_table.addMouseListener(new MouseAdapter() { // Add listener to get the currently selected rows and put IDs into the id_list
//				public void mouseReleased(MouseEvent e) {
//					id_list_model = new DefaultListModel<>();					
//					int[] selectedRow = basic_table.getSelectedRows();	
//					for (int i = 0; i < selectedRow.length; i++) {
//						selectedRow[i] = basic_table.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
//						id_list_model.addElement(data9[selectedRow[i]][0]);				
//					}	
//					id_list.setModel(id_list_model);
//				}
//			});
			
			
			// Add listener to get the currently selected rows and put IDs into the id_list
			basic_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	id_list_model = new DefaultListModel<>();					
					int[] selectedRow = basic_table.getSelectedRows();	
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = basic_table.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
						id_list_model.addElement(data9[selectedRow[i]][0]);				
					}	
					id_list.setModel(id_list_model);
		        }
		    });			
			basic_table.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF

			
			// Hide the last 4 columns: this hide is better than remove column from column model, this is basically set size to be zero
			for (int i = basic_table.getColumnCount() - 4; i < basic_table.getColumnCount(); i++) {
				basic_table.getColumnModel().getColumn(i).setMinWidth(0);
				basic_table.getColumnModel().getColumn(i).setMaxWidth(0);
				basic_table.getColumnModel().getColumn(i).setWidth(0);
			}
			
			PrismTitleScrollPane basic_table_scrollPane = new PrismTitleScrollPane("Sources (Basic Constraints)", "CENTER", basic_table);
			basic_table_scrollPane.setPreferredSize(new Dimension(400, 250));
			// End of 2nd Grid -----------------------------------------------------------------------							
	    	
	
			// 4th Grid ------------------------------------------------------------------------------		// Buttons	
			// 4th Grid -----------------------------------------------------------------------------
			// Add all buttons to a Panel----------------------------------
			button_table_Panel = new JPanel(new GridBagLayout());
			TitledBorder border3 = new TitledBorder("Flow Constraints Information");
			border3.setTitleJustification(TitledBorder.CENTER);
			button_table_Panel.setBorder(border3);
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.insets = new Insets(0, 5, 10, 10); // padding top 0, left 5, bottom 10, right 10
			
			
			JButton btn_NewSingle = new JButton();
			btn_NewSingle.setFont(new Font(null, Font.BOLD, 14));
			btn_NewSingle.setToolTipText("New flow");
			btn_NewSingle.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));					
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_NewSingle, c2);
			
			
			JButton btn_Edit = new JButton();
			btn_Edit.setToolTipText("Modify flow");
			btn_Edit.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_swap.png"));
			btn_Edit.setEnabled(false);					
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Edit, c2);
			
			
			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 14));
			btn_Delete.setToolTipText("Delete flows");
			btn_Delete.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_erase.png"));
			btn_Delete.setEnabled(false);				
			c2.gridx = 0;
			c2.gridy = 2;
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
			c2.gridy = 3;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Sort, c2);
			
			
			JButton btn_Validate = new JButton();
			btn_Validate.setFont(new Font(null, Font.BOLD, 14));
			btn_Validate.setToolTipText("Validate flows");
			btn_Validate.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_zoom.png"));				
			c2.gridx = 0;
			c2.gridy = 4;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Validate, c2);
			
			
			c2.insets = new Insets(0, 0, 0, 0); // No padding
			// Add Empty Label to make all buttons on top not middle
			c2.gridx = 0;
			c2.gridy = 5;
			c2.weightx = 0;
			c2.weighty = 1;
			button_table_Panel.add(new JLabel(), c2);
			
			// Add table10				
			create_table10();
			JScrollPane table_ScrollPane = new JScrollPane(table10);	
			c2.gridx = 1;
			c2.gridy = 0;
			c2.weightx = 1;
			c2.weighty = 1;
			c2.gridheight = 6;
			button_table_Panel.add(table_ScrollPane, c2);
			// End of 4th Grid -----------------------------------------------------------------------
			// End of 4th Grid -----------------------------------------------------------------------	
			
			
			
			// Add Listeners for table10 & buttons----------------------------------------------------------
			// Add Listeners for table10 & buttons----------------------------------------------------------
			
			// table10
			table10.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
				@Override
				public void mouseReleased(MouseEvent e) {
					int[] selectedRow = table10.getSelectedRows();
					if (selectedRow.length == 1) {		// Enable Edit	when: 1 row is selected and no cell is editing						
						// Reload GUI				
						int currentRow = selectedRow[0];
						currentRow = table10.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems							
						String[] flow_arrangement = data10[currentRow][2].toString().split(";");
						DefaultListModel[] list_model = new DefaultListModel[flow_arrangement.length];					
						for (int i = 0; i < flow_arrangement.length; i++) {		
							list_model[i] = new DefaultListModel();
							String[] arrangement = flow_arrangement[i].split(" ");							
							for (String a: arrangement) {
								list_model[i].addElement(a);
							}		
						}
						flow_scrollPane.create_flow_arrangement_UI(list_model);	
						
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table10.isEnabled()) {		// Enable Delete  when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}		
				}
			});
			
			table10.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selectedRow = table10.getSelectedRows();
					if (selectedRow.length == 1) {		// Enable Edit	when: 1 row is selected and no cell is editing
						// Reload GUI				
						int currentRow = selectedRow[0];
						currentRow = table10.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems				
						String[] flow_arrangement = data10[currentRow][2].toString().split(";");
						DefaultListModel[] list_model = new DefaultListModel[flow_arrangement.length];					
						for (int i = 0; i < flow_arrangement.length; i++) {		
							list_model[i] = new DefaultListModel();
							String[] arrangement = flow_arrangement[i].split(" ");							
							for (String a: arrangement) {
								list_model[i].addElement(a);
							}		
						}
						flow_scrollPane.create_flow_arrangement_UI(list_model);	
						
						btn_Edit.setEnabled(true);
					} else {		// Disable Edit
						btn_Edit.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && table10.isEnabled()) {		// Enable Delete  when: >=1 row is selected,table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}	
		        }
		    });
			
			

			// New single
			btn_NewSingle.addActionListener(e -> {	
				if (flow_scrollPane.get_flow_info_from_GUI().contains(";")) {	// Add constraint if there are at least 2 terms separated by ;
					// Add 1 row
					rowCount10++;
					data10 = new Object[rowCount10][colCount10];
					for (int ii = 0; ii < rowCount10 - 1; ii++) {
						for (int jj = 0; jj < colCount10; jj++) {
							data10[ii][jj] = model10.getValueAt(ii, jj);
						}	
					}
									
					data10[rowCount10 - 1][2] = flow_scrollPane.get_flow_info_from_GUI();	
					data10[rowCount10 - 1][3] = "FREE";
//					data10[rowCount10 - 1][4] = (double) 100;
//					data10[rowCount10 - 1][5] = (double) 100;
					model10.updateTableModelPrism(rowCount10, colCount10, data10, columnNames10);
					update_id();
					model10.fireTableDataChanged();
					quick_edit = new QuickEdit_FlowConstraints_Panel(table10, data10);		// 2 lines to update data for Quick Edit Panel
		 			scrollpane_QuickEdit.setViewportView(quick_edit);
					
					// Convert the new Row to model view and then select it 
					int newRow = table10.convertRowIndexToView(rowCount10 - 1);
					table10.setRowSelectionInterval(newRow, newRow);
					table10.scrollRectToVisible(new Rectangle(table10.getCellRect(newRow, 0, true)));
				}			
			});
			
			
			
			// Edit
			btn_Edit.addActionListener(e -> {
				if (table10.isEnabled()) {						
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
					temp_textarea.append("The following infomation (in rectangles surrounded by green border) will be applied to the highlighted (blue) flow" + "\n \n");
					temp_textarea.append("1. Flow arrangement" + "\n");	
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
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), popup, "Do you want to modify the highlighted flow ?",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);	
											
					if (response == 0) {
						int selectedRow = table10.getSelectedRow();
						selectedRow = table10.convertRowIndexToModel(selectedRow);		// Convert row index because "Sort" causes problems										
						
						if (flow_scrollPane.get_flow_info_from_GUI().contains(";")) {	// Edit is accepted if there are at least 2 terms separated by ;
							data10[selectedRow][2] = flow_scrollPane.get_flow_info_from_GUI();					
							model10.fireTableDataChanged();	
							
							// Convert the edited Row to model view and then select it 
							int editRow = table10.convertRowIndexToView(selectedRow);
							table10.setRowSelectionInterval(editRow, editRow);
						}
					}					
				}
			});
			
			
			btn_Edit.addMouseListener(new MouseAdapter() { // Add listener
				public void mouseEntered(java.awt.event.MouseEvent e) {
					flow_scrollPane.get_list_scroll().setBackground(new Color(0, 255, 0));
				}

				public void mouseExited(java.awt.event.MouseEvent e) {
					flow_scrollPane.get_list_scroll().setBackground(null);
				}
			});
			
				
			// Delete
			btn_Delete.addActionListener(e -> {	
				//Cancel editing before delete
				if (table10.isEditing()) {
					table10.getCellEditor().cancelCellEditing();
				}				
				
				// Get selected rows
				int[] selectedRow = table10.getSelectedRows();	
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table10.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
				}
				
				// Create a list of selected row indexes
				List<Integer> selected_Index = new ArrayList<Integer>();				
				for (int i: selectedRow) {
					selected_Index.add(i);
				}	
				
				// Get values to the new data10
				data10 = new Object[rowCount10 - selectedRow.length][colCount10];
				int newRow =0;
				for (int ii = 0; ii < rowCount10; ii++) {
					if (!selected_Index.contains(ii)) {			//If row not in the list then add to data10 row
						for (int jj = 0; jj < colCount10; jj++) {
							data10[newRow][jj] = model10.getValueAt(ii, jj);
						}
						newRow++;
					}
				}
				// Pass back the info to table model
				rowCount10 = rowCount10 - selectedRow.length;
				model10.updateTableModelPrism(rowCount10, colCount10, data10, columnNames10);
				model10.fireTableDataChanged();	
				quick_edit = new QuickEdit_FlowConstraints_Panel(table10, data10);		// 2 lines to update data for Quick Edit Panel
	 			scrollpane_QuickEdit.setViewportView(quick_edit);
			});
					
			
			// Sort
			btn_Sort.addActionListener(e -> {
				if (btn_Sort.getText().equals("ON")) {
					table10.setRowSorter(null);
					btn_Sort.setText("OFF");
					btn_Sort.repaint();
				} else if (btn_Sort.getText().equals("OFF")) {
					TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model10); // Add sorter
					table10.setRowSorter(sorter);
					btn_Sort.setText("ON");
					btn_Sort.repaint();
				}	
			});
			
			
			
			
			// Validate
			btn_Validate.addActionListener(e -> {					
//					// Apply change			
//					int[] selectedRow = table10.getSelectedRows();	
//					///Convert row index because "Sort" causes problems
//					for (int i = 0; i < selectedRow.length; i++) {
//						selectedRow[i] = table10.convertRowIndexToModel(selectedRow[i]);
//					}
//					table10.clearSelection();	//To help trigger the row refresh: clear then add back the rows
//					for (int i: selectedRow) {
//						if (String.valueOf(data10[i][2]).equalsIgnoreCase("SOFT")) {
//							data10[i][2] = "FREE";
//						} else if (String.valueOf(data10[i][2]).equalsIgnoreCase("HARD")) {
//							data10[i][2] = "FREE";
//						}
//						table10.addRowSelectionInterval(table10.convertRowIndexToView(i),table10.convertRowIndexToView(i));
//					}
			});			
	
			// End of Listeners for table10 & buttons -----------------------------------------------------------------------
			// End of Listeners for table10 & buttons -----------------------------------------------------------------------		    
		    
	
			
			
			
			
			
			
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new QuickEdit_FlowConstraints_Panel(table10, data10);
 			scrollpane_QuickEdit = new JScrollPane(quick_edit);
 			TitledBorder border = new TitledBorder("Quick Edit");
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
 			btnQuickEdit.addActionListener(e -> {		
 				if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
					btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
					scrollpane_QuickEdit.setVisible(true);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Flow_Constraints_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				} else {
					btnQuickEdit.setToolTipText("Show Quick Edit Tool");
					btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
					scrollpane_QuickEdit.setVisible(false);
					// Get everything show up nicely
					GUI_Text_splitPanel.setLeftComponent(panel_Flow_Constraints_GUI);
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());
				}
 			});				
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

			});
						
			// spinner
			JSpinner spin = new JSpinner (new SpinnerNumberModel(5, 0, 1000, 1));
			spin.setToolTipText("Total number of Sigma");
			JFormattedTextField SpinnerText = ((DefaultEditor) spin.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);		
			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    spin.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	spin.setValue(spin.getValue());
		        	int total_sigma = (int) spin.getValue();
		        	DefaultListModel[] list_model = new DefaultListModel[total_sigma];
					for (int i = 0; i < total_sigma; i++) {
						list_model[i] = new DefaultListModel<>();				
					}
					flow_scrollPane.create_flow_arrangement_UI(list_model);
		        	
		        }
		    });	
						
						
			// Add all buttons to flow_panel
		    helpToolBar.add(spin);
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
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(helpToolBar, c);
			
			// Add flow_scrollPane to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 0;
			super.add(flow_scrollPane, c);				
		    		
			// Add basic_table_scrollPane to the main Grid
			c.gridx = 1;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 0;
			super.add(basic_table_scrollPane, c);				    							
		    	    		  
			// Add the button_table_Panel & scrollpane_QuickEdit to a new Panel then add that panel to the main Grid
			JPanel button_table_qedit_panel = new JPanel();
			button_table_qedit_panel.setLayout(new BorderLayout());
			button_table_qedit_panel.add(button_table_Panel, BorderLayout.CENTER);
			button_table_qedit_panel.add(scrollpane_QuickEdit, BorderLayout.EAST);			
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2; 
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 1;
			super.add(button_table_qedit_panel, c);
			
			//when radioButton_Right[7] is selected, Sources (basic constraints) will be updated
			radioButton_Right[7].addActionListener(this);
		}
		
		// Listener for this class----------------------------------------------------------------------
	    public void actionPerformed(ActionEvent e) {
			model_basic.updateTableModelPrism(rowCount9, colCount9, data9, columnNames9);	// Update table9 to the Sources in Advanced constraints GUI
	    }
	    
	    // Update id column. id needs to be unique in order to use in flow constraints-----------------
	    public void update_id() {  		
			List<Integer> id_list = new ArrayList<Integer>();			
			
			for (int row = 0; row < rowCount10; row++) {
				if (data10[row][0] != null) {
					id_list.add((int) data10[row][0]);
				}
			}			
			
			for (int row = 0; row < rowCount10; row++) {
				if (data10[row][0] == null) {
					int new_id = (id_list.size() > 0) ? Collections.max(id_list) + 1 : 1;	//new id = (max id + 1) or = 1 if no row
					data10[row][0] = new_id;
					id_list.add(new_id);
				}
			}			
		}
	}

	class Flow_Constraints_Text  extends JTextArea {
		public Flow_Constraints_Text() {
	
		}
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	
	//Add all input Files to a list
	public void create_inputFiles_for_thisRun () {
		create_file_input_01();
		create_file_input_02();
		create_file_input_03();
		create_file_input_04();
		create_file_input_05();
		create_file_input_06();
		create_file_input_07();
		create_file_input_08();
		create_file_input_09();		
		create_file_input_10();	
		create_file_database();		// Note for this file, we just copy overwritten
		create_readmeFile();
		
		
//		// Just to save the rename method
//		File temp = new File(currentRunFolder.getAbsolutePath() + "/" + databaseFile.getName());			
//		databaseFile.renameTo(temp);
	}
	

	
	private void create_file_input_01() {
		File generalInputFile = new File(currentRunFolder.getAbsolutePath() + "/input_01_general_inputs.txt");
		if (generalInputFile.exists()) {
			generalInputFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data1 != null && data1.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(generalInputFile))) {
				for (int j = 0; j < columnNames1.length; j++) {
					fileOut.write(columnNames1[j] + "\t");
				}

				for (int i = 0; i < data1.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount1; j++) {
						fileOut.write(data1[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	
	private void create_file_input_02() {
		File gsilvicultureMethodFile = new File(currentRunFolder.getAbsolutePath() + "/input_02_silviculture_method.txt");
		if (gsilvicultureMethodFile.exists()) {
			gsilvicultureMethodFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data2 != null && data2.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(gsilvicultureMethodFile))) {
				for (int j = 0; j < columnNames2.length; j++) {
					fileOut.write(columnNames2[j] + "\t");
				}

				for (int i = 0; i < data2.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount2; j++) {
						fileOut.write(data2[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	
	private void create_file_input_03() {
		File selectedStrataFile = new File(currentRunFolder.getAbsolutePath() + "/input_03_model_strata.txt");	
		if (selectedStrataFile.exists()) {
			selectedStrataFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data3 != null && modeledAcres > 0) {
			//Only print out Strata with implemented methods <> null
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(selectedStrataFile))) {
				for (int j = 0; j < columnNames3.length; j++) { //Note: colCount = columnNames.length
					fileOut.write(columnNames3[j] + "\t");
				}
				
//				String temp = String.join("\t", columnNames3);
//				fileOut.write(temp);

				for (int i = 0; i < data3.length; i++) {				//Note: String.ValueOf   is so important to get the String from Object
					if (String.valueOf(data3[i][colCount3 - 1]).equals("true")) { //IF strata is in optimization model		
						fileOut.newLine();
						for (int j = 0; j < colCount3; j++) {
							fileOut.write(data3[i][j] + "\t");
						}
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	
	private void create_file_input_04() {
		//Only print out if the last column Allowed Options <> null
		File clearcutConversionFile = new File(currentRunFolder.getAbsolutePath() + "/input_04_covertype_conversion_clearcut.txt");
		if (clearcutConversionFile.exists()) {
			clearcutConversionFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data4 != null && data4.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(clearcutConversionFile))) {
				for (int j = 0; j < columnNames4.length; j++) {
					fileOut.write(columnNames4[j] + "\t");
				}

				for (int i = 0; i < data4.length; i++) {
					if (String.valueOf(data4[i][colCount4 - 1]).equals("true")) { //IF conversion is selected
						fileOut.newLine();
						for (int j = 0; j < colCount4; j++) {
							fileOut.write(data4[i][j] + "\t");
						}
					}
				}

				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}

	
	private void create_file_input_05() {
		File replacingDisturbanceConversionFile = new File(currentRunFolder.getAbsolutePath() + "/input_05_covertype_conversion_replacing.txt");	
		if (replacingDisturbanceConversionFile.exists()) {
			replacingDisturbanceConversionFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data5 != null && data5.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(replacingDisturbanceConversionFile))) {
				for (int j = 0; j < columnNames5.length; j++) {
					fileOut.write(columnNames5[j] + "\t");
				}

				for (int i = 0; i < data5.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount5; j++) {
						fileOut.write(data5[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}	
	
	
	private void create_file_input_06() {
		File nonReplacingDisturbanceFile = new File(currentRunFolder.getAbsolutePath() + "/input_06_natural_disturbances_non_replacing.txt");	
		if (nonReplacingDisturbanceFile.exists()) {
			nonReplacingDisturbanceFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data6 != null && data6.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(nonReplacingDisturbanceFile))) {
				for (int j = 0; j < columnNames6.length; j++) {
					fileOut.write(columnNames6[j] + "\t");
				}

				for (int i = 0; i < data6.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount6; j++) {
						fileOut.write(data6[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}	
	
	
	private void create_file_input_07() {
		File replacingDisturbanceFile = new File(currentRunFolder.getAbsolutePath() + "/input_07_natural_disturbances_replacing.txt");	
		if (replacingDisturbanceFile.exists()) {
			replacingDisturbanceFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data7 != null && data7.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(replacingDisturbanceFile))) {
				for (int j = 0; j < columnNames7.length; j++) {
					fileOut.write(columnNames7[j] + "\t");
				}

				for (int i = 0; i < data7.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount7; j++) {
						fileOut.write(data7[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}	
	
	
	private void create_file_input_08() {
		File costAdjustmentFile = new File(currentRunFolder.getAbsolutePath() + "/input_08_management_cost.txt");	
		if (costAdjustmentFile.exists()) {
			costAdjustmentFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data8 != null && data8.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(costAdjustmentFile))) {
				for (int j = 0; j < columnNames8.length; j++) {
					fileOut.write(columnNames8[j] + "\t");
				}

				for (int i = 0; i < data8.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount8; j++) {
						fileOut.write(data8[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}
	
	
	private void create_file_input_09() {
		File basicConstraintsFile = new File(currentRunFolder.getAbsolutePath() + "/input_09_basic_constraints.txt");
		if (basicConstraintsFile.exists()) {
			basicConstraintsFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data9 != null && data9.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(basicConstraintsFile))) {
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

	
	private void create_file_input_10() {
		File advancedConstraintsFile = new File(currentRunFolder.getAbsolutePath() + "/input_10_flow_constraints.txt");
		if (advancedConstraintsFile.exists()) {
			advancedConstraintsFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data10 != null && data10.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(advancedConstraintsFile))) {
				for (int j = 0; j < columnNames10.length; j++) {
					fileOut.write(columnNames10[j] + "\t");
				}
				
				for (int i = 0; i < data10.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount10; j++) {
						fileOut.write(data10[i][j] + "\t");
					}		
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}			
	
	
	private void create_file_database() {	
		File databaseFile = new File(currentRunFolder.getAbsolutePath() + "/" + "database.db");	
		try {
			if (file_database != null) Files.copy(file_database.toPath(), databaseFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			PrismMain.get_databases_linkedlist().update(databaseFile, read_database);	// Allow saving the databse.db into remember list after stop editing and save					
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}	
	
	private void create_readmeFile() {	
        File readmeFile = new File(currentRunFolder.getAbsolutePath() + "/" + "readme.txt");
		if (readmeFile.exists()) {
			readmeFile.delete();		// Delete the old file before writing new contents
		}
			
		FileWriter pw;
		try {
			pw = new FileWriter(currentRunFolder.getAbsolutePath() + "/" + "readme.txt");
			readme.write(pw);
			pw.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
}
