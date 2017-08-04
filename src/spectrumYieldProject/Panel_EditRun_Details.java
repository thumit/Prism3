package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

import spectrumConvenienceClasses.FilesHandle;
import spectrumConvenienceClasses.IconHandle;
import spectrumConvenienceClasses.TableModelSpectrum;
import spectrumConvenienceClasses.ToolBarWithBgImage;
import spectrumROOT.Spectrum_Main;

public class Panel_EditRun_Details extends JLayeredPane implements ActionListener {
	private JSplitPane GUI_Text_splitPanel ;
	private JPanel radioPanel_Right; 
	private ButtonGroup radioGroup_Right; 
	private JRadioButton[] radioButton_Right; 
	
	private File currentRunFolder;
	private File file_Database;
	
	private boolean is_first_time_loaded = true;
	
	//6 panels for the selected Run
	private General_Inputs_GUI paneL_General_Inputs_GUI;
	private General_Inputs_Text panel_General_Inputs_Text;
	private Model_Identifiniton_GUI panel_Model_Identifiniton_GUI;
	private Model_Identification_Text panel_Model_Identification_Text;
	private Covertype_Conversion_GUI panel_Covertype_Conversion_GUI;
	private Covertype_Conversion_Text panel_Covertype_Conversion_Text;
	private Disturbances_GUI panel_Disturbances_GUI;
	private Disturbances_Text panel_Disturbances_Text;
	private Management_Cost_GUI panel_Management_Cost_GUI;
	private Management_Cost_Text panel_Management_Cost_Text;
	private Cost_Adjustment_GUI panel_Cost_Adjustment_GUI;
	private Cost_Adjustment_Text panel_Cost_Adjustment_Text;
	private Basic_Constraints_GUI panel_Basic_Constraints_GUI;
	private Basic_Constraints_Text panel_Basic_Constraints_Text;
	private Advanced_Constraints_GUI panel_Advanced_Constraints_GUI;
	private Advanced_Constraints_Text panel_Advanced_Constraints_Text;
	
	
	private Read_Database read_Database;
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
	private TableModelSpectrum model_overview;
	private Object[][] data_overview;
	private double modeledAcres, availableAcres;
	
	//table input_01_general_inputs.txt
	private boolean is_table1_loaded = false;
	private int rowCount1, colCount1;
	private String[] columnNames1;
	private JTable table1;
	private TableModelSpectrum model1;
	private Object[][] data1;
	
	//table input_02_modeled_strata.txt
	private boolean is_table2_loaded = false;
	private int rowCount2, colCount2;
	private String[] columnNames2;
	private JTable table2;
	private TableModelSpectrum model2;
	private Object[][] data2;
	
	//table input_03_clearcut_covertype_conversion.txt
	private boolean is_table3_loaded = false;
	private int rowCount3, colCount3;
	private String[] columnNames3;
	private JTable table3;
	private TableModelSpectrum model3;
	private Object[][] data3;
	
	//table input_04_replacingdisturbances_covertype_conversion.txt
	private boolean is_table4_loaded = false;
	private int rowCount4, colCount4;
	private String[] columnNames4;
	private JTable table4;
	private TableModelSpectrum model4;
	private Object[][] data4;

	//table input_05_non_replacing_disturbances.txt
	private boolean is_table5_loaded = false;
	private int rowCount5, colCount5;
	private String[] columnNames5;
	private JTable table5;
	private TableModelSpectrum model5;
	private Object[][] data5;
	
	//table input_06_replacing_disturbances.txt
	private boolean is_table6_loaded = false;
	private int rowCount6, colCount6;
	private String[] columnNames6;
	private JTable table6;
	private TableModelSpectrum model6;
	private Object[][] data6;

	//table input_07_base_cost.txt
	private boolean is_table7a_loaded = false;
	private int rowCount7a, colCount7a;
	private String[] columnNames7a;
	private JTable table7a;
	private TableModelSpectrum model7a;
	private Object[][] data7a;
	
	//table input_07b_conversion_cost.txt
	private boolean is_table7b_loaded = false;
	private int rowCount7b, colCount7b;
	private String[] columnNames7b;
	private JTable table7b;
	private TableModelSpectrum model7b;
	private Object[][] data7b;	
	
	//table input_08_management_cost.txt
	private boolean is_table8_loaded = false;
	private int rowCount8, colCount8;
	private String[] columnNames8;
	private JTable table8;
	private TableModelSpectrum model8;
	private Object[][] data8;
	
	//table input_09_basic_constraints.txt
	private boolean is_table9_loaded = false;
	private int rowCount9, colCount9;
	private String[] columnNames9;
	private JTable table9;
	private TableModelSpectrum model9;
	private Object[][] data9;
	
	//table input_10_advanced_constraints.txt
	private boolean is_table10_loaded = false;
	private int rowCount10, colCount10;
	private String[] columnNames10;
	private JTable table10;
	private TableModelSpectrum model10;
	private Object[][] data10;	
	
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
		radioButton_Right[1]= new JRadioButton("Model Identification");
		radioButton_Right[2]= new JRadioButton("Covertype Conversion");
		radioButton_Right[3]= new JRadioButton("Natural Disturbances");
		radioButton_Right[4]= new JRadioButton("Management Cost");
		radioButton_Right[5]= new JRadioButton("Cost Adjustment");
		radioButton_Right[5].setVisible(false);
		radioButton_Right[6]= new JRadioButton("Basic Constraints");
		radioButton_Right[7]= new JRadioButton("Advanced Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < radioButton_Right.length; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
				radioButton_Right[i].addActionListener(this);
		}	
		
		GUI_Text_splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_splitPanel.setDividerSize(0);
//		GUI_Text_splitPanel.setEnabled(false);
			
	
		// Create all new 6 panels for the selected Run--------------------------------------------------
		paneL_General_Inputs_GUI = new General_Inputs_GUI();
		panel_General_Inputs_Text = new General_Inputs_Text();
//		panel_Model_Identifiniton_GUI = new Model_Identifiniton_GUI();
//		panel_Model_Identification_Text = new Model_Identification_Text();
//		panel_Covertype_Conversion_GUI = new Covertype_Conversion_GUI();
//		panel_Covertype_Conversion_Text = new Covertype_Conversion_Text();
//		panel_Disturbances_GUI = new Disturbances_GUI();
//		panel_Disturbances_Text = new Disturbances_Text();
//		panel_Management_Cost_GUI = new Management_Cost_GUI();
//		panel_Management_Cost_Text = new Management_Cost_Text();
//		panel_Cost_Adjustment_GUI = new Cost_Adjustment_GUI();
//		panel_Cost_Adjustment_Text = new Cost_Adjustment_Text();
//		panel_Basic_Constraints_GUI = new Basic_Constraints_GUI();
//		panel_Basic_Constraints_Text = new Basic_Constraints_Text();
//		panel_Advanced_Constraints_GUI = new Advanced_Constraints_GUI();
//		panel_Advanced_Constraints_Text = new Advanced_Constraints_Text();
					
		
		// Show the 2 panelInput of the selected Run
		GUI_Text_splitPanel.setLeftComponent(paneL_General_Inputs_GUI);
		GUI_Text_splitPanel.setRightComponent(panel_General_Inputs_Text);	
		
		
		// Add all components to The Panel------------------------------------------------------------
		super.add(radioPanel_Right, BorderLayout.NORTH);
		super.add(GUI_Text_splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
		

				
		// Load database of the run if exist---------------------------------------------------------------------
		File database_to_load = new File(currentRunFolder.getAbsolutePath() + "/database.db");
		if (database_to_load.exists()) {	//Load if the file exists
			file_Database = database_to_load;
			
			// Read the tables (strata_definition, existing_strata, yield_tables) of the database-------------------
			read_Database = new Read_Database(file_Database);	
			layers_Title = read_Database.get_layers_Title();
			layers_Title_ToolTip = read_Database.get_layers_Title_ToolTip();
			allLayers =  read_Database.get_allLayers();
			allLayers_ToolTips = read_Database.get_allLayers_ToolTips();
			yieldTable_values = read_Database.get_yield_tables_values();
			yieldTable_ColumnNames = read_Database.get_yield_tables_column_names();
						
			panel_Model_Identifiniton_GUI = new Model_Identifiniton_GUI();
			panel_Model_Identification_Text = new Model_Identification_Text();
			panel_Covertype_Conversion_GUI = new Covertype_Conversion_GUI();
			panel_Covertype_Conversion_Text = new Covertype_Conversion_Text();
			panel_Disturbances_GUI = new Disturbances_GUI();
			panel_Disturbances_Text = new Disturbances_Text();
			panel_Management_Cost_GUI = new Management_Cost_GUI();
			panel_Management_Cost_Text = new Management_Cost_Text();
			panel_Cost_Adjustment_GUI = new Cost_Adjustment_GUI();
			panel_Cost_Adjustment_Text = new Cost_Adjustment_Text();
			panel_Basic_Constraints_GUI = new Basic_Constraints_GUI();
			panel_Basic_Constraints_Text = new Basic_Constraints_Text();
			panel_Advanced_Constraints_GUI = new Advanced_Constraints_GUI();
			panel_Advanced_Constraints_Text = new Advanced_Constraints_Text();			
			reload_inputs_after_creating_GUI();
			
			Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().revalidate();
			Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().repaint();
		}  else { 	// If file does not exist then use null database
			file_Database = null;
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
					GUI_Text_splitPanel.setRightComponent(panel_General_Inputs_Text);
				} else if (j == 1) {
					GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
					GUI_Text_splitPanel.setRightComponent(panel_Model_Identification_Text);
				} else if (j == 2) {
					GUI_Text_splitPanel.setLeftComponent(panel_Covertype_Conversion_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_CovertypeConversion_Text);
				} else if (j == 3) {
					GUI_Text_splitPanel.setLeftComponent(panel_Disturbances_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Disturbances_Text);
				} else if (j == 4) {
					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Management_Cost_Text);
				} else if (j == 5) {
					GUI_Text_splitPanel.setLeftComponent(panel_Cost_Adjustment_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Cost_Adjustment_Text);
				} else if (j == 6) {
					GUI_Text_splitPanel.setLeftComponent(panel_Basic_Constraints_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Basic_Constraints_Text);
				} else if (j == 7) {
					GUI_Text_splitPanel.setLeftComponent(panel_Advanced_Constraints_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Advanced_Constraints_Text);
				}
				
				// Get everything show up nicely
				Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());	//this can replace the below 2 lines
//				Spectrum_Main.get_main().revalidate();
//		    	Spectrum_Main.get_main().repaint(); 
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
			
		
//		if (file_ExistingStrata == null && is_this_the_first_load == true) {		//If there is no existing strata, still load the selected strata
//			table_file = new File(currentRunFolder.getAbsolutePath() + "/input_02_modeled_strata.txt");
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
//				System.err.println("File not exists: Input 2 - input_02_modeled_strata.txt - New interface is created");
//			}	
//		}
		
															//Need to change later (not here , below) because I didn't write the whole file, just write the yes case
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_03_clearcut_covertype_conversion.txt");
//		if (table_file.exists()) { // Load from input
//			tableLoader = new Reload_Table_Info(table_file);
//			rowCount3 = tableLoader.get_rowCount();
//			colCount3 = tableLoader.get_colCount();
//			data3 = tableLoader.get_input_data();
//			columnNames3 = tableLoader.get_columnNames();
//			is_table3_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_03_clearcut_covertype_conversion).txt - New interface is created");
//		}

		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_04_replacingdisturbances_covertype_conversion.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount4 = tableLoader.get_rowCount();
			colCount4 = tableLoader.get_colCount();
			data4 = tableLoader.get_input_data();
			columnNames4 = tableLoader.get_columnNames();
			is_table4_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_04_replacingdisturbances_covertype_conversion.txt - New interface is created");
		}
		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_05_non_replacing_disturbances.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount5 = tableLoader.get_rowCount();
			colCount5 = tableLoader.get_colCount();
			data5 = tableLoader.get_input_data();
			columnNames5 = tableLoader.get_columnNames();
			is_table5_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_05_non_replacing_disturbances.txt - New interface is created");
		}
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_06_replacing_disturbances.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount6 = tableLoader.get_rowCount();
			colCount6 = tableLoader.get_colCount();
			data6 = tableLoader.get_input_data();
			columnNames6 = tableLoader.get_columnNames();
			is_table6_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_06_replacing_disturbances.txt - New interface is created");
		}		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_07_base_cost.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount7a = tableLoader.get_rowCount();
			colCount7a = tableLoader.get_colCount();
			data7a = tableLoader.get_input_data();
			columnNames7a = tableLoader.get_columnNames();
			is_table7a_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_07_base_cost.txt - New interface is created");
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
		
		
		if (file_Database != null) {
			button_import_database.doClick(); // Trigger   button_import_database.doClick()  if  file_Database != null
		}
		
		
		// Find the data match to paste into Existing Strata		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_02_modeled_strata.txt");
		if (table_file.exists()) { // Load from input
			// Uncheck all checkboxes in "modeled_strata"
			for (int i = 0; i < data2.length; i++) {
				data2[i][9] = false;
			}
			
			
			tableLoader = new Reload_Table_Info(table_file);
			
			Object[][] temp_data = tableLoader.get_input_data();
			for (int i = 0; i < temp_data.length; i++) {
				for (int ii = 0; ii < data2.length; ii++) {
					if (   String.valueOf(data2[ii][0]).equals(String.valueOf(temp_data[i][0]))   ) {		//Just need to compare Strata ID
						// Apply temp_data row values to data row 
						for (int jj = 0; jj < data2[ii].length; jj++) {
							data2[ii][jj] = temp_data[i][jj];
						}		
					}	
				}
			}			
			model2.match_DataType();			//a smart way to retrieve the original data type :))))))
			button_select_Strata.setEnabled(true);
			button_select_Strata.doClick();
			
			is_table2_loaded = true;		
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_02_modeled_strata.txt - New interface is created");
		}	
		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_03_clearcut_covertype_conversion.txt");
		if (table_file.exists()) { // Load from input
			// Uncheck all checkboxes in "implementation" column when the input_03 exists
			for (int i = 0; i < data3.length; i++) {
				data3[i][6] = false;
			}	
			
			 // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			String twocolumnsGUI, twocolumnsInput;
			
			Object[][] temp_data = tableLoader.get_input_data();
			for (int i = 0; i < temp_data.length; i++) {
				twocolumnsInput = String.valueOf(temp_data[i][0]) + String.valueOf(temp_data[i][1]);
				for (int ii = 0; ii < data3.length; ii++) {
					twocolumnsGUI = String.valueOf(data3[ii][0]) + String.valueOf(data3[ii][1]);			
					if (twocolumnsGUI.equals(twocolumnsInput)) {		//Just need to compare 2 columns: cover type & size class
						// Apply temp_data row values to data3 row 
						for (int jj = 0; jj < data3[ii].length; jj++) {
							data3[ii][jj] = temp_data[i][jj];
						}		
					}	
				}
			}	
			
			is_table3_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: input_03_clearcut_covertype_conversion.txt - New interface is created");
		}
			
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_04_replacingdisturbances_covertype_conversion.txt");
//		if (table_file.exists()) { // Load from input
//			 // Load from input
//			tableLoader = new Reload_Table_Info(table_file);		
//			Object[][] temp_data = tableLoader.get_input_data();
//			for (int i = 0; i < temp_data.length; i++) {	
//				// Apply temp_data row values to data4 row 
//				for (int j = 0; j < data4[i].length; j++) {
//					data4[i][j] = temp_data[i][j];
//				}	
//			}	
//			is_table4_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_04_replacingdisturbances_covertype_conversion.txt - New interface is created");
//		}
		
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_05_non_replacing_disturbances.txt");
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
//			System.err.println("File not exists: input_05_non_replacing_disturbances.txt - New interface is created");
//		}
			
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_06_replacing_disturbances.txt");
//		if (table_file.exists()) { // Load from input
//			// Load from input
//			tableLoader = new Reload_Table_Info(table_file);		
//			Object[][] temp_data = tableLoader.get_input_data();
//			for (int i = 0; i < temp_data.length; i++) {	
//				// Apply temp_data row values to data7 row 
//				for (int j = 0; j < data6[i].length; j++) {
//					data6[i][j] = temp_data[i][j];
//				}	
//			}	
//			is_table6_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: input_06_replacing_disturbances.txt - New interface is created");
//		}		
		
		
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/input_07_base_cost.txt");
//		if (table_file.exists()) { // Load from input
//			 // Load from input
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
//			System.err.println("File not exists: input_07_base_cost.txt - New interface is created");
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
				
		
				
		//Matching data types after finishing reloads
		model2.match_DataType();		//a smart way to retrieve the original data type :))))))	
		model3.match_DataType();		//a smart way to retrieve the original data type :))))))
		model4.match_DataType();		//a smart way to retrieve the original data type :))))))
		model5.match_DataType();		//a smart way to retrieve the original data type :))))))
		model6.match_DataType();		//a smart way to retrieve the original data type :))))))
		model7a.match_DataType();		//a smart way to retrieve the original data type :))))))
		model7b.match_DataType();		//a smart way to retrieve the original data type :))))))
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
		rowCount_overview = 4;
		colCount_overview = 2;
		data_overview = new Object[rowCount_overview][colCount_overview];
        columnNames_overview= new String[] {"Description" , "Value"};
		
		// Populate the data matrix
		data_overview[0][0] = "Modeled existing strata vs available existing strata";
		data_overview[1][0] = "Modeled acres vs available acres";
		data_overview[2][0] = "Number of yield tables in your database";
		data_overview[3][0] = "Number of strata not connected to Natural Growth table";
		
		
		//Create a table
        model_overview = new TableModelSpectrum(rowCount_overview, colCount_overview, data_overview, columnNames_overview);
        table_overview = new JTable(model_overview);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table_overview.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//      table_overview.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_overview.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table_overview.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        table_overview.getColumnModel().getColumn(0).setPreferredWidth(250);	//Set width of 1st Column bigger
        table_overview.setTableHeader(null);
        table_overview.setPreferredScrollableViewportSize(new Dimension(400, 80));
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
        model1 = new TableModelSpectrum(rowCount1, colCount1, data1, columnNames1);
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
			colCount2 = layers_Title.size() + 4;
			columnNames2 = new String[colCount2];

			columnNames2[0] = "strata_id";		//add for the name of strata
			for (int i = 0; i < layers_Title.size(); i++) {
				columnNames2[i+1] = layers_Title.get(i);			//add 6 layers to the column header name
			}
	         
			columnNames2[colCount2 - 3] = "acres";	//add 3 more columns
			columnNames2[colCount2 - 2] = "ageclass";
			columnNames2[colCount2 - 1] = "modeled_strata";	
		}		
					
		
		//Create a table-------------------------------------------------------------
		model2 = new TableModelSpectrum(rowCount2, colCount2, data2, columnNames2) {
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col < colCount2 - 1) { // Only the last column is editable
					return false;
				} else {
					return true;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data2[row][col] = value;
				update_model_overview();
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount2; row++) {
					for (int col = 0; col < colCount2; col++) {
						if (String.valueOf(data2[row][col]).equals("null")) {
							data2[row][col] = null;
						} else {					
							if (col == colCount2 - 3) {			//column "Total Acres" accepts only Double
								try {
									data2[row][col] = Double.valueOf(String.valueOf(data2[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table2");
								}	
							} else if (col == colCount2 - 2) {			//column "Age Class" accepts only Integer
								try {
									data2[row][col] = Integer.valueOf(String.valueOf(data2[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table2");
								}	
							} else if (col == colCount2 - 1) {			//last column "modeled_strata" accepts only Boolean
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
				if (data2 != null) {
					model2.setValueAt(data2[0][0], 0, 0);	// this is just to trigger the update_model_overview
				}
			}
			
			public void update_model_overview() {  
				// de-select all modeled_strata that has not found starting age-class
				for (int row = 0; row < rowCount2; row++) {
					if (data2[row][colCount2 - 2] == null) {
						data2[row][colCount2 - 1] = false;
					}
				}
				
				// Update Model OverView table
				int modeledStrata = 0;
				for (int row = 0; row < rowCount2; row++) {
					if (data2[row][colCount2 - 1] != null && (boolean) data2[row][colCount2 - 1] == true)	modeledStrata = modeledStrata + 1;
				}
				data_overview[0][1] = modeledStrata + " vs " + rowCount2;
				model_overview.fireTableDataChanged();

				modeledAcres = 0;
				availableAcres = 0;
				for (int row = 0; row < rowCount2; row++) {
					if (data2[row][colCount2 - 1] != null && (boolean) data2[row][colCount2 - 1] == true)	modeledAcres = modeledAcres + Double.parseDouble(data2[row][colCount2 - 3].toString());
					availableAcres = availableAcres + Double.parseDouble(data2[row][colCount2 - 3].toString());
				}
				data_overview[1][1] = modeledAcres + " vs " + availableAcres;
				
		        data_overview[2][1] = yieldTable_values.length;
		        
		        int total_yieldtable =0;
		        for (int row = 0; row < rowCount2; row++) {				        	
		        	if (data2[row][colCount2 - 2] == null) {
		        		total_yieldtable = total_yieldtable + 1;
		        	}
				}
		        data_overview[3][1] = total_yieldtable;
		        model_overview.fireTableDataChanged();
		        				
				model_overview.fireTableDataChanged();
			}
		};
		
		
		

		table2 = new JTable(model2) {
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

		
		((JComponent) table2.getDefaultRenderer(Boolean.class)).setOpaque(true);	// It's a bug in the synth-installed renderer, quick hack is to force the rendering checkbox opacity to true
		
		
//		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	  
		table2.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//		table2.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
        table2.getColumnModel().getColumn(colCount2 - 1).setPreferredWidth(100);	//Set width of Column "Strata in optimization model" bigger
		
		table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table2.setFillsViewportHeight(true);
	}	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table3() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
	
		//Setup the table------------------------------------------------------------	
		if (is_table3_loaded == false) { // Create a fresh new if Load fail				
			rowCount3 = total_CoverType * total_CoverType;
			colCount3 = 7;
			data3 = new Object[rowCount3][colCount3];
	        columnNames3= new String[] {"covertype_before_cut", "covertype_after_cut", "min_age_cut_existing", "max_age_cut_existing", "min_age_cut_regeneration", "max_age_cut_regeneration", "implementation"};
			
			// Populate the data matrix
	        int table_row = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_CoverType; j++) {
					data3[table_row][0] = allLayers.get(4).get(i);
					data3[table_row][1] = allLayers.get(4).get(j);	
					data3[table_row][2] = 20;
					data3[table_row][3] = 24;
					data3[table_row][4] = 10;
					data3[table_row][5] = 15;
					if (i==j) data3[table_row][6] = true; 
					table_row++;
				}
			}
		}
			
		
		//Create a table-------------------------------------------------------------
		model3 = new TableModelSpectrum(rowCount3, colCount3, data3, columnNames3) {			
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
				data3[row][col] = value;
//				fireTableDataChanged();		// No need this because it will clear the selection, 
											// With button do task for multiple row we need fire the change outside of this class, so the change can show up in the GUI
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount3; row++) {
					for (int col = 0; col < colCount3; col++) {
						if (String.valueOf(data3[row][col]).equals("null")) {
							data3[row][col] = null;
						} else {					
							if (col >= 2 && col <= 5) {			//Column 2 to 5 are Integer
								try {
									data3[row][col] = Integer.valueOf(String.valueOf(data3[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table3");
								}	
							} else if (col == 6) {			//column "implementation" accepts only Boolean
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
			}
		};
		
		
		
		table3 = new JTable(model3) {
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
		ImageIcon[] imageIconArray = new ImageIcon[colCount3];
		for (int i = 0; i < colCount3; i++) {
			if (i >= 2) {
				imageIconArray[i] = IconHandle.get_scaledImageIcon(3, 3, "icon_main.png");
			}
		}
		
		
		// Define a set of background color for all rows
		Color[] rowColor = new Color[rowCount3];
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
        
        
        
		for (int i = 0; i < columnNames3.length - 5; i++) {		//Except the last 5 column
			table3.getColumnModel().getColumn(i).setCellRenderer(r);
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
//		for (int i = 0; i < columnNames3.length; i++) {			
//			if (i == 2 || i == 3 || i == 4) {
//				table3.getColumnModel().getColumn(i).setHeaderRenderer(r2);
//				table3.getColumnModel().getColumn(i).setHeaderValue(new JLabel(columnNames3[i], icon_scale, JLabel.CENTER));
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
//					setForeground(table3.getSelectionForeground());
//					setBackground(table3.getSelectionBackground());
//				} else {
//					setForeground(table3.getForeground());
//					setBackground(table3.getBackground());
//				}
//				setSelected((value != null && ((Boolean) value).booleanValue()));
//				this.setOpaque(true);
//				return this;
//			}
//		}
		  
		table3.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_MinAge()));
		table3.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new comboBox_MaxAge()));
		table3.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new comboBox_MinAge()));
		table3.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new comboBox_MaxAge()));
//		table3.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new checkbox_Option()));
		((JComponent) table3.getDefaultRenderer(Boolean.class)).setOpaque(true);	// It's a bug in the synth-installed renderer, quick hack is to force the rendering checkbox opacity to true		
		// End of Set up Types for each  Columns------------------------------------------------------------------------
		

		
		
//      table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table3.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//      table3.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
		
//      table3.setTableHeader(null);
        table3.setPreferredScrollableViewportSize(new Dimension(400, 100));
//      table3.setFillsViewportHeight(true);
        TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model3);	//Add sorter
		for (int i = 1; i < colCount3; i++) {
			sorter.setSortable(i, false);
			if (i == 0 || i == 1) {			//Only the first 2 columns can be sorted
				sorter.setSortable(i, true);	
			}
		}
		table3.setRowSorter(sorter);
        
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table4() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)		
		
		//Setup the table------------------------------------------------------------	
		if (is_table4_loaded == false) { // Create a fresh new if Load fail				
			rowCount4 = total_CoverType*total_CoverType;
			colCount4 = 4;
			data4 = new Object[rowCount4][colCount4];
	        columnNames4= new String[] {"covertype_before_disturbance", "covertype_after_disturbance", "regeneration_weight", "regeneration_percentage"};
			
			// Populate the data matrix
	        int table_row2 = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_CoverType; j++) {
					data4[table_row2][0] = allLayers.get(4).get(i);
					data4[table_row2][1] = allLayers.get(4).get(j);	
					if (i==j) data4[table_row2][2] = (int) 1; else data4[table_row2][2] = (int) 0;
					if (i==j) data4[table_row2][3] = (double) 100.0; else data4[table_row2][3] = (double) 0.0;
					table_row2++;
				}
			}			
		}
			
		
		//Create a table-------------------------------------------------------------		
        model4 = new TableModelSpectrum(rowCount4, colCount4, data4, columnNames4) {
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
        		data4[row][col] = value;
        		update_Percentage_column();
    		}  
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount4; row++) {
					for (int col = 0; col < colCount4; col++) {
						if (String.valueOf(data4[row][col]).equals("null")) {
							data4[row][col] = null;
						} else {					
							if (col == 2) {			//Column 2 is Integer
								try {
									data4[row][col] = Integer.valueOf(String.valueOf(data4[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table4");
								}	
							} else if (col == 3) {			//Column 3 is Double
								try {
									data4[row][col] = Double.valueOf(String.valueOf(data4[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table4");
								}	
							} else {	//All other columns are String
								data4[row][col] = String.valueOf(data4[row][col]);
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
						total_Weight[i] = total_Weight[i] + Double.parseDouble(data4[table_row][2].toString());						
						table_row++;
					}	
				}
				
				//Calculate and write percentage
				table_row=0;
				for (int i = 0; i < total_CoverType; i++) {
					for (int j = 0; j < total_CoverType; j++) {					
						data4[table_row][3]	= Double.parseDouble(data4[table_row][2].toString())/total_Weight[i]*100;			
						table_row++;
					}	
				}
				
				// Get selected rows
				int[] selectedRow = table4.getSelectedRows();
				/// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table4.convertRowIndexToModel(selectedRow[i]);
				}
				fireTableDataChanged();
				// Add selected rows back
				for (int i : selectedRow) {
					table4.addRowSelectionInterval(table4.convertRowIndexToView(i), table4.convertRowIndexToView(i));
				}
			}
        };
        
        
        
		table4 = new JTable(model4) {
             //Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				if (table4.getColumnName(colIndex).equals("covertype_before") || table4.getColumnName(colIndex).equals("covertype_after")) {
					try {
						tip = getValueAt(rowIndex, colIndex).toString();
						for (int i = 0; i < total_CoverType; i++) {
							if (tip.equals(allLayers.get(4).get(i)))	tip=allLayers_ToolTips.get(4).get(i);							
						}
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}
				
				if (table4.getColumnName(colIndex).equals("regeneration_weight")) {
					try {
						tip = "Weight of the lost area with cover type "+ getValueAt(rowIndex, 0).toString() 
								+ " to be regenerated as cover type " + getValueAt(rowIndex, 1).toString();
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}	
				
				if (table4.getColumnName(colIndex).equals("regeneration_percentage")) {
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
		ImageIcon[] imageIconArray = new ImageIcon[colCount4];
		for (int i = 0; i < colCount4; i++) {
			if (i == 2) {
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
			
		
		for (int i = 0; i < columnNames4.length; i++) {
			if (i != 2) {
        		table4.getColumnModel().getColumn(i).setCellRenderer(r);
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
 
		table4.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_Weight()));
		// End of Set up Types for each  Columns------------------------------------------------------------------------		
		
		
		
//      table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table4.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table4.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//      table4.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        
//      table4.setTableHeader(null);
        table4.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table4.setFillsViewportHeight(true);
        TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model4);	//Add sorter
		for (int i = 1; i < colCount4; i++) {
			sorter.setSortable(i, false);
			if (i == 0) {			//Only the first column can be sorted
				sorter.setSortable(i, true);	
			}
		}
		table4.setRowSorter(sorter);
	}
		
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table5() {
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		int total_SizeClass = allLayers.get(5).size();		// total number of elements - 1 in layer6 Size Class (0 to...)			
		
		
		//Setup the table------------------------------------------------------------	
		if (is_table5_loaded == false) { // Create a fresh new if Load fail				
			rowCount5 = total_CoverType*total_SizeClass;
			colCount5 = 4;
			data5 = new Object[rowCount5][colCount5];
	        columnNames5= new String[] {"period1_covertype", "period1_sizeclass", "mixedfire_percentage", "barkbeetle_percentage"};
			
			// Populate the data matrix
	        int table_row = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_SizeClass; j++) {
					data5[table_row][0] = allLayers.get(4).get(i);
					data5[table_row][1] = allLayers.get(5).get(j);	
					data5[table_row][2] = 5.0;
					data5[table_row][3] = 4.5;
					if (allLayers.get(4).get(i).equals("N")) {
						data5[table_row][2] = 0.0;	//Non-stocked --> No MS
						data5[table_row][3] = 0.0;	//Non-stocked --> No BS
					}
					table_row++;
				}
			}						
		}
					
		
		//Create a table-------------------------------------------------------------			
        model5 = new TableModelSpectrum(rowCount5, colCount5, data5, columnNames5) {
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
    				JOptionPane.showMessageDialog(Spectrum_Main.get_spectrumDesktopPane(),
    						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
    			} else {
    				data5[row][col] = value;
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount5; row++) {
					for (int col = 0; col < colCount5; col++) {
						if (String.valueOf(data5[row][col]).equals("null")) {
							data5[row][col] = null;
						} else {					
							if (col == 2 || col == 3) {			//Column 2 & 3 is Double
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
        };
        
        
		table5 = new JTable(model5){
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
				TableCellRenderer renderer2 = table5.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table5,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}		
		};
		
		
		
		// Define a set of icon for some columns
		ImageIcon[] imageIconArray = new ImageIcon[colCount5];
		for (int i = 0; i < colCount5; i++) {
			if (i == 2 || i == 3) {
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
		
		
      
        for (int i=0; i<columnNames5.length; i++) {
        	if (i < 2) {
        		table5.getColumnModel().getColumn(i).setCellRenderer(r);
        	} 
        }
       
        
//        table5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table5.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table5.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//        table5.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        
//        table5.setTableHeader(null);
        table5.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table5.setFillsViewportHeight(true);
        TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model5);	//Add sorter
		for (int i = 1; i < colCount5; i++) {
			sorter.setSortable(i, false);
			if (i == 0 || i == 1) {			//Only the first 2 columns can be sorted
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
			rowCount6 = 30;
			colCount6 = total_CoverType + 1;
			data6 = new Object[rowCount6][colCount6];
	        columnNames6= new String[colCount6];
	        columnNames6[0] = "ageclass";
	        for (int i = 1; i < colCount6; i++) {
	        	 columnNames6[i] = allLayers.get(4).get(i-1);
			}		        
			
			// Populate the data matrix
			for (int i = 0; i < rowCount6; i++) {
				data6[i][0] = i+1;			//Age class column, age starts from 1
				for (int j = 1; j < colCount6; j++) {	//all other columns
					data6[i][j] = 0.2;
					if (allLayers.get(4).get(j-1).equals("N"))	data6[i][j] = 0.0;	//Non-stocked --> No SR Fire
				}
			}								
		}
			
		
        //Header ToolTIp
        String[] headerToolTips = new String[colCount6];
        for (int i = 1; i < colCount6; i++) {
        	headerToolTips[i] = allLayers_ToolTips.get(4).get(i-1);
		}
       
        
		
		//Create a table-------------------------------------------------------------			
        model6 = new TableModelSpectrum(rowCount6, colCount6, data6, columnNames6) {
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
    				JOptionPane.showMessageDialog(Spectrum_Main.get_spectrumDesktopPane(),
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
							if (col > 0) {			//Columns except the 1st columns are Double
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
        
        
		table6 = new JTable(model6) {
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
						if (rowIndex == rowCount6 - 1) 	tip = tip + " plus";	//Add plus to the highest age class
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
		Color[] rowColor = new Color[rowCount6];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);					
		Color currentColor = color1;

		for (int i = 0; i < rowCount6; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			rowColor[i] = currentColor;
		}
		
		// Define a set of icon for some columns
 		ImageIcon[] imageIconArray = new ImageIcon[colCount6];
 		for (int i = 0; i < colCount6; i++) {
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
        
        
		for (int i = 0; i < columnNames6.length; i++) {
			if (i == 0) {
				table6.getColumnModel().getColumn(i).setCellRenderer(r);		// first column is shaded
			}
		}

        //Set toolTip for Column header
        JTableHeader header = table6.getTableHeader();
       

        
//      table6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table6.setCellSelectionEnabled(true);
        table6.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table6.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        table6.getColumnModel().getColumn(0).setPreferredWidth(150);	//Set width of 1st Column bigger
        
//      table6.setTableHeader(null);
        table6.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table6.setFillsViewportHeight(true);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table7a() {
		//Setup the table------------------------------------------------------------	
		if (is_table7a_loaded == false) { // Create a fresh new if Load fail	
			// This all_actions List contains all actions loaded from yield tables------------------------------------------------------------
			List<String> action_list = new ArrayList<String>();
			if (yieldTable_ColumnNames != null) {	//create table with column include yield tables columns
				for (String action: read_Database.get_action_type()) {
					action_list.add(action);					
				}	
				
				rowCount7a = action_list.size();			
				colCount7a = 2 + yieldTable_ColumnNames.length;
				data7a = new Object[rowCount7a][colCount7a];
				columnNames7a = new String[2 + yieldTable_ColumnNames.length];
				columnNames7a[0] = "action_list";
				columnNames7a[1] = "acres";
				for (int i = 2; i < columnNames7a.length; i++) {
					columnNames7a[i] = yieldTable_ColumnNames[i - 2];				
				}	
			} else {
				rowCount7a = action_list.size();			
				colCount7a = 2;
				data7a = new Object[rowCount7a][colCount7a];
				columnNames7a= new String[] {"action_list", "acres"};
			}			
			
	       			
			// Populate the data matrix
			for (int i = 0; i < rowCount7a; i++) {
				data7a[i][0] = action_list.get(i);
				data7a[i][1] = (action_list.get(i).equalsIgnoreCase("no-action")) ? (double) 0 : (double) 360; 
			}
		}		
		
		
        //Header ToolTIp
		String[] headerToolTips = new String[colCount7a];
		headerToolTips[0] = "all unique actions found from  yield_tables in your selected database";
        headerToolTips[1] = "currency per acre where an action is implemented";
		if (yieldTable_ColumnNames != null) {      
	        for (int i = 2; i < colCount7a; i++) {
	        	int yt_col = i - 2;
	        	headerToolTips[i] = "currency per " + read_Database.get_ParameterToolTip(yieldTable_ColumnNames[yt_col]) + " (Column index: " + yt_col + ")";	
			}
		}
	
		
		//Create a table-------------------------------------------------------------			
        model7a = new TableModelSpectrum(rowCount7a, colCount7a, data7a, columnNames7a) {
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
    				JOptionPane.showMessageDialog(Spectrum_Main.get_spectrumDesktopPane(),
    						"Your input has not been accepted. Cost cannot be negative.");
    			} else {
    				data7a[row][col] = value;
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount7a; row++) {
					for (int col = 0; col < colCount7a; col++) {
						if (String.valueOf(data7a[row][col]).equals("null")) {
							data7a[row][col] = null;
						} else {					
							if (col > 0) {			//Columns except the 1st columns are Double
								try {
									data7a[row][col] = Double.valueOf(String.valueOf(data7a[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table7");
								}	
							} else {	//All other columns are String
								data7a[row][col] = String.valueOf(data7a[row][col]);
							}
						}	
					}	
				}	
			}
        };
        
        
		table7a = new JTable(model7a) {
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
				TableCellRenderer renderer2 = table7a.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table7a,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}		
		};				
		

		// Define a set of background colors
		Color[] rowColor = new Color[rowCount7a];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);					
		Color currentColor = color1;

		for (int i = 0; i < rowCount7a; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			rowColor[i] = currentColor;
		}
		
		// Define a set of icon for some columns
 		ImageIcon[] imageIconArray = new ImageIcon[colCount6];
 		for (int i = 0; i < colCount6; i++) {
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
		
		
		for (int i = 0; i < columnNames7a.length; i++) {
			if (i == 0) {
				table7a.getColumnModel().getColumn(i).setCellRenderer(r);		// first column is shaded
			}
		}
			
		
		if (yieldTable_ColumnNames != null) table7a.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table7a.setCellSelectionEnabled(true);
        table7a.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table7a.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        
//      table7.setTableHeader(null);
        table7a.setPreferredScrollableViewportSize(new Dimension(200, 100));
//      table7a.setFillsViewportHeight(true);
        TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model7a);	//Add sorter
		for (int i = 1; i < colCount7a; i++) {
			sorter.setSortable(i, false);
			if (i == 0) {			//Only the first column can be sorted
				sorter.setSortable(i, true);	
			}
		}
//		table7a.setRowSorter(sorter);
	}	

	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table7b() {		
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)	
		
		//Setup the table------------------------------------------------------------	
		if (is_table7b_loaded == false) { // Create a fresh new if Load fail	
			if (yieldTable_ColumnNames != null) {	//create table with column include yield tables columns
				rowCount7b = total_CoverType * total_CoverType;		
				colCount7b = 4;
				data7b = new Object[rowCount7b][colCount7b];
				columnNames7b = new String[] {"covertype_before", "covertype_after", "action", "disturbance"};
			}		
			       			
			// Populate the data matrix
			 int table_row = 0;
				for (int i = 0; i < total_CoverType; i++) {
					for (int j = 0; j < total_CoverType; j++) {
						data7b[table_row][0] = allLayers.get(4).get(i);
						data7b[table_row][1] = allLayers.get(4).get(j);
						data7b[table_row][2] = (double) 240; 
						table_row++;
				}
			}
		}
		
		
        //Header ToolTIp
		String[] headerToolTips = new String[colCount7b];
		headerToolTips[0] = "cover type in period (t) before the occurence of EA clear cut or replacing disturbance in the same period (t)";
        headerToolTips[1] = "cover type in the next period (t+1) after the occurence of EA clear cut or replacing disturbance in period (t)";
        headerToolTips[2] = "currency per acre converted by management action";
        headerToolTips[3] = "currency per acre converted by replacing disturbance";
		
		//Create a table-------------------------------------------------------------			
        model7b = new TableModelSpectrum(rowCount7b, colCount7b, data7b, columnNames7b) {
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
    				JOptionPane.showMessageDialog(Spectrum_Main.get_spectrumDesktopPane(),
    						"Your input has not been accepted. Cost cannot be negative.");
    			} else {
    				data7b[row][col] = value;   				
    			}
    		}
        	
        	@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount7b; row++) {
					for (int col = 0; col < colCount7b; col++) {
						if (String.valueOf(data7b[row][col]).equals("null")) {
							data7b[row][col] = null;
						} else {					
							if (col >= 2) {			//Columns except the 1st 2 columns are Double
								try {
									data7b[row][col] = Double.valueOf(String.valueOf(data7b[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table7b");
								}	
							} else {	//All other columns are String
								data7b[row][col] = String.valueOf(data7b[row][col]);
							}
						}	
					}	
				}	
			}
        };
        
        
		table7b = new JTable(model7b) {
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
				TableCellRenderer renderer2 = table7b.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table7b,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}		
		};
		

		// Define a set of background colors
		Color[] rowColor = new Color[rowCount7b];
		Color color1 = new Color(160, 160, 160);
		Color color2 = new Color(192, 192, 192);					
		Color currentColor = color1;

		for (int i = 0; i < rowCount7b; i++) {
			if (currentColor == color2) {
				currentColor = color1;
			} else {
				currentColor = color2;
			}
			rowColor[i] = currentColor;
		}
		
		// Define a set of icon for some columns
 		ImageIcon[] imageIconArray = new ImageIcon[colCount6];
 		for (int i = 0; i < colCount6; i++) {
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
		
		
		for (int i = 0; i < columnNames7b.length; i++) {
			if (i < 2) {
				table7b.getColumnModel().getColumn(i).setCellRenderer(r);		// first 2 columns is shaded
			}
		}
			
		
//		table7b.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table7b.setCellSelectionEnabled(true);
        table7b.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table7b.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        
//      table7.setTableHeader(null);
        table7b.setPreferredScrollableViewportSize(new Dimension(200, 100));
//      table7b.setFillsViewportHeight(true);
        TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model7b);	//Add sorter
		for (int i = 0; i < colCount7b; i++) {
			sorter.setSortable(i, false);
			if (i < 2) {			//Only the first 2 columns can be sorted
				sorter.setSortable(i, true);	
			}
		}
//		table7b.setRowSorter(sorter);
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
		model8 = new TableModelSpectrum(rowCount8, colCount8, data8, columnNames8) {
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
		model9 = new TableModelSpectrum(rowCount9, colCount9, data9, columnNames9) {
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
		model10 = new TableModelSpectrum(rowCount10, colCount10, data10, columnNames10) {
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
			label1 = new JLabel("Total planning periods (1 period = 10 years)");
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
			combo2.setSelectedItem((double) 3.5);
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
			label5 = new JLabel("Export original problem file");
			check5 = new JCheckBox();
			check5.setSelected(false);
			//-----------------------------------------------------
			label6 = new JLabel("Export original solution file");
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
			btnHelp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					
				}
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
			TitledBorder border0 = new TitledBorder("Import Database (model will be forced to reset)");
			border0.setTitleJustification(TitledBorder.CENTER);
			importPanel.setBorder(border0);
			importPanel.setLayout(new GridBagLayout());
			GridBagConstraints c0 = new GridBagConstraints();
			c0.fill = GridBagConstraints.HORIZONTAL;
			c0.weightx = 1;
			c0.weighty = 1;			

			
			JTextField textField2 = new JTextField(30);
			textField2.setEditable(false);
			c0.gridx = 0;
			c0.gridy = 0;
			c0.weightx = 1;
		    c0.weighty = 0;
			importPanel.add(textField2, c0);

			
			button_import_database = new JButton();
			button_import_database.setToolTipText("Browse");
			button_import_database.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));		
			button_import_database.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					File old_database = file_Database;
					
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
						String warningText = "Importation is denied. " + file_Database.getName() + " does not meet SpectrumLite's data requirements.";
						String ExitOption[] = {"OK"};
						int response = JOptionPane.showOptionDialog(Spectrum_Main.get_spectrumDesktopPane(), warningText, "Database importation warning",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_warning.png"), ExitOption, ExitOption[0]);
						
						file_Database = null;
					} finally {
						button_import_database.setEnabled(true);
						if (file_Database == null) { // if cancel browsing
							file_Database = old_database;
						} 
						if (file_Database != null) {
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

					if (!is_first_time_loaded || file_Database == null) {	// This is because the first load may have null or not null database
						file_Database = FilesHandle.chosenDatabase();
						
						if (file_Database != null) {	// If cancel choosing file
							// read the tables (strata_definition, existing_strata, yield_tables) of the database-------------------
							read_Database = new Read_Database(file_Database);	
							layers_Title = read_Database.get_layers_Title();
							layers_Title_ToolTip = read_Database.get_layers_Title_ToolTip();
							allLayers =  read_Database.get_allLayers();
							allLayers_ToolTips = read_Database.get_allLayers_ToolTips();
							yieldTable_values = read_Database.get_yield_tables_values();
							yieldTable_ColumnNames = read_Database.get_yield_tables_column_names();
							
							// Reset all panels except General Inputs----------------------------------------------------------------		
							is_table_overview_loaded = false;
							is_table1_loaded = false;
							is_table2_loaded = false;
							is_table3_loaded = false;
							is_table4_loaded = false;
							is_table5_loaded = false;
							is_table6_loaded = false;
							is_table7a_loaded = false;
							is_table7b_loaded = false;
							is_table8_loaded = false;
							is_table9_loaded = false;
							is_table10_loaded = false;
																			
					        // create 2 new instances of this Panel
							panel_Model_Identifiniton_GUI = new Model_Identifiniton_GUI();
							panel_Model_Identification_Text = new Model_Identification_Text();
					        
					        // create 2 new instances of this Panel
							panel_Covertype_Conversion_GUI = new Covertype_Conversion_GUI();
							panel_Covertype_Conversion_Text = new Covertype_Conversion_Text();
							
							 // create 2 new instances of this Panel
							panel_Disturbances_GUI = new Disturbances_GUI();
							panel_Disturbances_Text = new Disturbances_Text();
							
					        // create 2 new instances of this Panel 
							panel_Management_Cost_GUI = new Management_Cost_GUI();
							panel_Management_Cost_Text = new Management_Cost_Text();
							
							// create 2 new instances of this Panel 
							panel_Cost_Adjustment_GUI = new Cost_Adjustment_GUI();
							panel_Cost_Adjustment_Text = new Cost_Adjustment_Text();
							
							// create 2 new instances of this Panel 
							panel_Basic_Constraints_GUI = new Basic_Constraints_GUI();
							panel_Basic_Constraints_Text = new Basic_Constraints_Text();
							
							// create 2 new instances of this Panel 
							panel_Advanced_Constraints_GUI = new Advanced_Constraints_GUI();
							panel_Advanced_Constraints_Text = new Advanced_Constraints_Text();
						}
						
					}

									
					

					if (file_Database != null) {	// If cancel choosing file
						// get the raw existing_strata from the database------------------------------------------------------
						String[][] existing_strata_values = read_Database.get_existing_strata_values();
						rowCount2 = existing_strata_values.length;	// refresh total rows based on existing strata, we don't need to refresh the total columns
						int existing_strata_colCount = existing_strata_values[0].length;
	
						data2 = new Object[rowCount2][colCount2];
						for (int row = 0; row < rowCount2; row++) {
							for (int column = 0; column < existing_strata_colCount - 1; column++) {		// loop all existing strata columns, except the last column
								data2[row][column] = existing_strata_values[row][column];
							}
							if (existing_strata_values[row][existing_strata_colCount - 1] != null) {
								data2[row][colCount2 - 3] = Double.valueOf(existing_strata_values[row][existing_strata_colCount - 1]);	// the last column of readStrata is "Total acres"	
							}								
							data2[row][colCount2 - 1] = false;
						}					
						model2.match_DataType();	// a smart way to retrieve the original data type :))))))					
						model2.updateTableModelSpectrum(rowCount2, colCount2, data2, columnNames2);		// very important to (pass table info back to table model) each time data is new Object
								         						
						// only add sorter after having the data loaded
						TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model2);
						table2.setRowSorter(sorter);
	
	
						
						
				        // update Age Class column for the existing strata------------------------------------------------------
						for (int row = 0; row < rowCount2; row++) {						
							String s5 = data2[row][5].toString();
							String s6 = data2[row][6].toString();
							if (read_Database.get_starting_ageclass(s5, s6, "NG", "0") != null) {
								data2[row][colCount2 - 2] = Integer.valueOf(read_Database.get_starting_ageclass(s5, s6, "A", "0"));	
							}												
						}
						
						
						
						
						// select all existing strata after loaded if found age class
						for (int row = 0; row < rowCount2; row++) {
							if (data2[row][colCount2 - 2] != null) {
								data2[row][colCount2 - 1] = true;
							}
						}
						
						
						
						// some final data update after import successfully
						model2.fireTableDataChanged();
						model2.update_model_overview();								        					
						textField2.setText(file_Database.getAbsolutePath());
					}
				}
			});	


			c0.gridx = 1;
			c0.gridy = 0;
			c0.weightx = 0;
		    c0.weighty = 0;
			importPanel.add(button_import_database, c0);
		 			
								
			// Add empty Label for everything above not resize
			c0.gridx = 0;
			c0.gridy = 1;
			c0.weightx = 0;
		    c0.weighty = 1;
			importPanel.add(new JLabel(), c0);
			// End of Import Database Panel -----------------------------------------------------------------------
			// End of Import Database Panel -----------------------------------------------------------------------		    
		    
		    
		    
		    
		    
		        
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
 			GridBagConstraints c = new GridBagConstraints(); 			
 			c.fill = GridBagConstraints.BOTH;
 			c.weightx = 1;
 		    c.weighty = 1;

 		    // Add helpToolBar	
 			c.gridx = 0;
 			c.gridy = 0;
 			c.weightx = 1;
 		    c.weighty = 0;
 			c.gridwidth = 3;
 			c.gridheight = 1;
 			super.add(helpToolBar, c);	 		    
 		    
 			c.insets = new Insets(0, 5, 10, 30); // padding top 0, left 5, bottom 10, right 30
 			// Add 	
 			c.gridx = 0;
 			c.gridy = 1;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(label1, c);
 			
 			// Add	
 			c.gridx = 1;
 			c.gridy = 1;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(combo1, c);			
 			
 			// Add 
 			c.gridx = 0;
 			c.gridy = 2;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(label2, c);
 			
 			// Add 	
 			c.gridx = 1;
 			c.gridy = 2;
 			c.weightx = 0;
 			c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(combo2, c);	
 			
 			// Add 
 			c.gridx = 0;
 			c.gridy = 3;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(label3, c);
 			
 			// Add 	
 			c.gridx = 1;
 			c.gridy = 3;
 			c.weightx = 0;
 			c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(combo3, c);
 			
 			// Add 
 			c.gridx = 0;
 			c.gridy = 4;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(label4, c);
 			
 			// Add 	
 			c.gridx = 1;
 			c.gridy = 4;
 			c.weightx = 0;
 			c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(spin4, c);
 			
 			// Add 
 			c.gridx = 0;
 			c.gridy = 5;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(label5, c);
 			
 			// Add 	
 			c.gridx = 1;
 			c.gridy = 5;
 			c.weightx = 0;
 			c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(check5, c);
 			
 			// Add 
 			c.gridx = 0;
 			c.gridy = 6;
 			c.weightx = 0;
 		    c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(label6, c);
 			
 			// Add 	
 			c.gridx = 1;
 			c.gridy = 6;
 			c.weightx = 0;
 			c.weighty = 0;
 			c.gridwidth = 1;
 			c.gridheight = 1;
 			super.add(check6, c);
 						
 			// Add
 			c.gridx = 0;
 			c.gridy = 7;
 			c.weightx = 0;
 			c.weighty = 0;
 			c.gridwidth = 2;
 			c.gridheight = 1;
 			super.add(importPanel, c);
		}
	}

	class General_Inputs_Text extends JScrollPane {
		public General_Inputs_Text() {	
			setBorder(null);
//	        setViewportView(table1);		// No need to show this table
		}
	}

	
	// Panel Model_Identifiniton--------------------------------------------------------------------------------------------------
	class Model_Identifiniton_GUI extends JLayeredPane implements ItemListener {
		// Define 28 check box for 6 layers
		List<List<JCheckBox>> checkboxFilter;
		
		public Model_Identifiniton_GUI() {
			setLayout(new GridBagLayout());
			
			
			
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------						
			int total_layers = allLayers.size();		
			
			//Add all layers labels and checkboxes to checkPanel
			JPanel checkPanel = new JPanel();		
			TitledBorder border = new TitledBorder("Strata Filter");
//			border.setTitleFont(new Font("Sans-Serif", Font.BOLD, 14));
			border.setTitleJustification(TitledBorder.CENTER);
			checkPanel.setBorder(border);
			checkPanel.setLayout(new GridBagLayout());
			GridBagConstraints c1 = new GridBagConstraints();
			c1.fill = GridBagConstraints.HORIZONTAL;
			c1.weightx = 1;
		    c1.weighty = 1;
			
		    
			//Add layers labels
		    List<JLabel> layers_Title_Label = new ArrayList<JLabel>();
			for (int i = 0; i < total_layers; i++) {
				layers_Title_Label.add(new JLabel(layers_Title.get(i)));
				layers_Title_Label.get(i).setToolTipText(layers_Title_ToolTip.get(i));
				
				//add listeners to select all or deselect all
				int curent_index = i;
				layers_Title_Label.get(curent_index).addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (layers_Title_Label.get(curent_index).isEnabled()) {	
							for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
								checkboxFilter.get(curent_index).get(j).setSelected(false);
							}
							layers_Title_Label.get(curent_index).setEnabled(false);
						} else {
							for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
								checkboxFilter.get(curent_index).get(j).setSelected(true);
							}
							layers_Title_Label.get(curent_index).setEnabled(true);
						}
					}
				});
				
				
				c1.gridx = i;
				c1.gridy = 0;
				checkPanel.add(layers_Title_Label.get(i), c1);
			}
			

			//Add CheckBox for all layers
			checkboxFilter = new ArrayList<List<JCheckBox>>();
			for (int i = 0; i < allLayers.size(); i++) {		//Loop all layers
				List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		//A temporary List
				checkboxFilter.add(temp_List);
				for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
					checkboxFilter.get(i).add(new JCheckBox(allLayers.get(i).get(j)));
					checkboxFilter.get(i).get(j).setToolTipText(allLayers_ToolTips.get(i).get(j));
					
					checkboxFilter.get(i).get(j).setSelected(true);
					checkboxFilter.get(i).get(j).addItemListener(this);
					
					c1.gridx = i;
					c1.gridy = j + 1;
					checkPanel.add(checkboxFilter.get(i).get(j), c1);
					
					//Make label Enable after a checkbox is selected
					int current_i = i;
					int current_j = j;
					checkboxFilter.get(i).get(j).addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if (checkboxFilter.get(current_i).get(current_j).isSelected()) {
								layers_Title_Label.get(current_i).setEnabled(true);
							}					
						}
					});
				}
			}
			
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------

			
			
			
			// 3rd grid -----------------------------------------------------------------------
			// 3rd grid -----------------------------------------------------------------------			
			JPanel inforPanel = new JPanel();		
			TitledBorder border2 = new TitledBorder("Your Model's Overview");
			border2.setTitleJustification(TitledBorder.CENTER);
			inforPanel.setBorder(border2);
			inforPanel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.weightx = 1;
		    c2.weighty = 1;

		    
			// 1st line inside inforPanel
			create_table_overview();        
	        JScrollPane overviewScrollPane = new JScrollPane();
	        overviewScrollPane.setViewportView(table_overview);
	        
		    c2.gridx = 0;
			c2.gridy = 0;
			c2.gridwidth = 3;
			c2.weightx = 1;
		    c2.weighty = 1;
			inforPanel.add(overviewScrollPane, c2);

			
			// 2nd line inside inforPanel includes 2 buttons
			//button 1
			JButton remove_Strata = new JButton();
			remove_Strata.setToolTipText("Remove highlighted strata from optimization model");
			remove_Strata.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_erase.png"));
			remove_Strata.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					
					int[] selectedRow = table2.getSelectedRows();	
					///Convert row index because "Sort" causes problems
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table2.convertRowIndexToModel(selectedRow[i]);
					}
					table2.clearSelection();	//To help trigger the row refresh: clear then add back the rows
					for (int i: selectedRow) {
						data2[i][colCount2 - 1] = false;
						model2.setValueAt(data2[i][colCount2 - 1], i, colCount2 - 1);	// this is just to trigger the update_model_overview
						table2.addRowSelectionInterval(table2.convertRowIndexToView(i),table2.convertRowIndexToView(i));
					}						
				}
			});
			c2.gridx = 0;
			c2.gridy = 1;
			c2.gridwidth = 1;	
			c2.weightx = 1;
		    c2.weighty = 0;
//		    c2.insets = new Insets(0, 10, 0, 0); // padding top 0, left 15, bottom 0, right 0
			inforPanel.add(remove_Strata, c2);		
			
			
			//button 2	
			button_select_Strata = new JButton();
			button_select_Strata.setToolTipText("Add highlighted strata to optimization model");
			button_select_Strata.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_check.png"));
			button_select_Strata.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					
					int[] selectedRow = table2.getSelectedRows();	
					///Convert row index because "Sort" causes problems
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table2.convertRowIndexToModel(selectedRow[i]);
					}
					table2.clearSelection();	//To help trigger the row refresh: clear then add back the rows
					for (int i: selectedRow) {
						data2[i][colCount2 - 1] = true;
						model2.setValueAt(data2[i][colCount2 - 1], i, colCount2 - 1);	// this is just to trigger the update_model_overview
						table2.addRowSelectionInterval(table2.convertRowIndexToView(i),table2.convertRowIndexToView(i));
					}	
				}
			});
			c2.gridx = 1;
			c2.gridy = 1;
			c2.gridwidth = 1;	
			c2.weightx = 1;
		    c2.weighty = 0;
//		    c2.insets = new Insets(0, 10, 0, 0); // padding top 0, left 15, bottom 0, right 0
			inforPanel.add(button_select_Strata, c2);
			// End of 3rd grid -----------------------------------------------------------------------
			// End of 3rd grid -----------------------------------------------------------------------
			
			
			
			
			// ToolBar Panel ----------------------------------------------------------------------------
			// ToolBar Panel ----------------------------------------------------------------------------
			ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
			helpToolBar.setFloatable(false);	//to make a tool bar immovable
			helpToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor
			helpToolBar.setBorderPainted(false);
			
			// button Strata Filter
			JToggleButton btnStrataFilter = new JToggleButton();
			checkPanel.setVisible(false);
			btnStrataFilter.setToolTipText("Show Strata Filter");
			btnStrataFilter.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
			btnStrataFilter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
			
					if (btnStrataFilter.getToolTipText().equals("Show Strata Filter")) {
						btnStrataFilter.setToolTipText("Hide Strata Filter");
						btnStrataFilter.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
						checkPanel.setVisible(true);
						// Get everything show up nicely
						GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
					} else {
						btnStrataFilter.setToolTipText("Show Strata Filter");
						btnStrataFilter.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
						checkPanel.setVisible(false);
						// Get everything show up nicely
						GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
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
			helpToolBar.add(btnStrataFilter);
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
			
//		    // Add the 1st grid - importPanel to the main Grid
//			c.gridx = 0;
//			c.gridy = 1;
//			c.weightx = 1;
//		    c.weighty = 1;
//			c.gridwidth = 1;
//			c.gridheight = 1;
//			super.add(importPanel, c);
			
			// Add the 2nd grid - checkPanel to the main Grid - only show up when btnStrataFilter is clicked
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
		    c.weighty = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(checkPanel, c);
			
			// Add the 3rd grid - inforPanel to the main Grid	
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0.5;
		    c.weighty = 1;
			c.gridwidth = 1;
			c.gridheight = 2;
			super.add(inforPanel, c);
		}
		
		
		//Listeners for checkBox Filter--------------------------------------------------------------------
		public void itemStateChanged(ItemEvent e) {

			if (data2 != null) {		//Only allow sorter if the data of existing strata is loaded
				//This help filter to get the strata as specified by the CheckBoxes
				TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model2);
				table2.setRowSorter(sorter);
				List<RowFilter<TableModelSpectrum, Object>> filters, filters2;
				filters2 = new ArrayList<RowFilter<TableModelSpectrum, Object>>();
				for (int i = 0; i < checkboxFilter.size(); i++) {
					RowFilter<TableModelSpectrum, Object> layer_filter = null;
					filters = new ArrayList<RowFilter<TableModelSpectrum, Object>>();
					for (int j = 0; j < checkboxFilter.get(i).size(); j++) {
						if (checkboxFilter.get(i).get(j).isSelected()) {
							filters.add(RowFilter.regexFilter(checkboxFilter.get(i).get(j).getText(), i + 1)); // i+1 is the table column containing the first layer	
						}
					}
					layer_filter = RowFilter.orFilter(filters);

					filters2.add(layer_filter);
				}
				RowFilter<TableModelSpectrum, Object> combine_AllFilters = null;
				combine_AllFilters = RowFilter.andFilter(filters2);
				sorter.setRowFilter(combine_AllFilters);
			}
		}
	}

		
	class Model_Identification_Text extends JLayeredPane {
		public Model_Identification_Text() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));				
			create_table2();
			JScrollPane scrollPane = new JScrollPane(table2);
			add(scrollPane);
		}
	}
	

	
	
	// Panel Covertype Conversion------------------------------------------------------------------------------------------------
	class Covertype_Conversion_GUI extends JLayeredPane {
		public Covertype_Conversion_GUI() {
			setLayout(new GridBagLayout());

			
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
	        create_table3();
	        //Put table3 into CovertypeConversion_EA_ScrollPane
	        JScrollPane CovertypeConversion_EA_ScrollPane = new JScrollPane();
	        TitledBorder border = new TitledBorder("Cover type conversion & rotation ageclass for clear cut");
			border.setTitleJustification(TitledBorder.CENTER);
			CovertypeConversion_EA_ScrollPane.setBorder(border);
	        CovertypeConversion_EA_ScrollPane.setViewportView(table3);									
		    
		    
	        
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
	        create_table4();
	        //Put table4 into CovertypeConversion_SRD_ScrollPane
			JScrollPane CovertypeConversion_SRD_ScrollPane = new JScrollPane();
			border = new TitledBorder("Cover type conversion & regeneration for replacing disturbance");
			border.setTitleJustification(TitledBorder.CENTER);
			CovertypeConversion_SRD_ScrollPane.setBorder(border);
	        CovertypeConversion_SRD_ScrollPane.setViewportView(table4);						
			
		    

			// scrollPane Quick Edit 1 & 2-----------------------------------------------------------------------
			// scrollPane Quick Edit 1 @ 2-----------------------------------------------------------------------		
			JScrollPane scrollpane_QuickEdit_1 = new JScrollPane(new QuickEdit_EA_Conversion_Panel(table3, data3));
			JScrollPane scrollpane_QuickEdit_2 = new JScrollPane(new QuickEdit_RD_Conversion_Panel(table4, data4));	
			
			border = new TitledBorder("Quick Edit ");
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
			btnQuickEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
			
					if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
						scrollpane_QuickEdit_1.setVisible(true);
						scrollpane_QuickEdit_2.setVisible(true);
						// Get everything show up nicely
						GUI_Text_splitPanel.setLeftComponent(panel_Covertype_Conversion_GUI);
						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
					} else {
						btnQuickEdit.setToolTipText("Show Quick Edit Tool");
						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
						scrollpane_QuickEdit_1.setVisible(false);
						scrollpane_QuickEdit_2.setVisible(false);
						// Get everything show up nicely
						GUI_Text_splitPanel.setLeftComponent(panel_Covertype_Conversion_GUI);
						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
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
	

	// Panel Disturbances-----------------------------------------------------------------------------------------------------------
	class Disturbances_GUI extends JLayeredPane {
		public Disturbances_GUI() {
			setLayout(new GridBagLayout());


			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
			create_table5();
	        //Put table5 into MixedFire_ScrollPane
	        JScrollPane nonStandReplacing_ScrollPane = new JScrollPane();
	    	TitledBorder border = new TitledBorder("Proportion (%) of period-one existing strata suffered from NON-REPLACING DISTURBANCES across all time periods");
			border.setTitleJustification(TitledBorder.CENTER);
			nonStandReplacing_ScrollPane.setBorder(border);
	        nonStandReplacing_ScrollPane.setViewportView(table5);			
			
		    
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
	        create_table6();
	        //Put table6 into StandReplacing_ScrollPane
	        JScrollPane StandReplacing_ScrollPane = new JScrollPane();
	        TitledBorder border3 = new TitledBorder("Proportion (%) of existing strata or regeneration strata suffered from REPLACING DISTURBANCES in each time period");
			border3.setTitleJustification(TitledBorder.CENTER);
			StandReplacing_ScrollPane.setBorder(border3);
	        StandReplacing_ScrollPane.setViewportView(table6);
			
	        
	        
	        
	        // scrollPane Quick Edit 1 & 2-----------------------------------------------------------------------
	        // scrollPane Quick Edit 1 @ 2-----------------------------------------------------------------------		
 			JScrollPane scrollpane_QuickEdit_1 = new JScrollPane(new QuickEdit_NonRD_Percentage_Panel(table5, data5));
 			JScrollPane scrollpane_QuickEdit_2 = new JScrollPane(new QuickEdit_RD_Percentage_Panel(table6, data6));	
 			
 			border = new TitledBorder("Quick Edit ");
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
 			btnQuickEdit.addActionListener(new ActionListener() {
 				@Override
 				public void actionPerformed(ActionEvent actionEvent) {
 			
 					if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
 						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
 						scrollpane_QuickEdit_1.setVisible(true);
 						scrollpane_QuickEdit_2.setVisible(true);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Disturbances_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
 					} else {
 						btnQuickEdit.setToolTipText("Show Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
 						scrollpane_QuickEdit_1.setVisible(false);
 						scrollpane_QuickEdit_2.setVisible(false);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Disturbances_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
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

		
	class Disturbances_Text extends JLayeredPane {
		public Disturbances_Text() {

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
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_Database);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();
			// End of 1st grid -----------------------------------------------------------------------

			
			// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
			dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_Database);
			// End of 2nd Grid -----------------------------------------------------------------------
				
					
			// 3rd grid ------------------------------------------------------------------------------		// Parameters
			create_table7a();
			create_table7b();
//			model7a.match_DataType();		//a smart way to retrieve the original data type :))))))
//			model7b.match_DataType();		//a smart way to retrieve the original data type :))))))
			cost_tables_ScrollPane = new ScrollPane_CostTables(table7a, data7a, columnNames7a, table7b, data7b, columnNames7b);
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
			btn_New.setToolTipText("New Condition");
			btn_New.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));	
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0;
			c.weighty = 0;
			cost_condition_panel.add(btn_New, c);		
	
			
			JButton btn_Edit = new JButton();
//			btn_Edit.setText("EDIT");
			btn_Edit.setToolTipText("Apply changes in Static Identifiers, Dynamic Identifiers, Action Cost, Conversion Cost to the highlighted condition");
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
			btn_Delete.setToolTipText("Delete Conditions");
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
			btn_EditMode.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
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
//					popup_scroll.setPreferredSize(new Dimension((int) (Spectrum_Main.get_main().getWidth() * 0.85), (int) (Spectrum_Main.get_main().getHeight() * 0.65)));	
//
//					String ExitOption[] = {"EXIT"};
//					int response = JOptionPane.showOptionDialog(Spectrum_Main.get_spectrumDesktopPane(), popup_scroll, "Flexible View Mode - You can resize this window",
//							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);					
//					if (response == 0) {																									
//					}
//					
//					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
				}
			});
			
			
			// New Condition
			btn_New.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {														
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
									
					model8.updateTableModelSpectrum(rowCount8, colCount8, data8, columnNames8);
					model8.fireTableDataChanged();		
					
					// Convert the new Row to model view and then select it 
					int newRow = table8.convertRowIndexToView(rowCount8 - 1);
					table8.setRowSelectionInterval(newRow, newRow);
					update_id();
					table8.scrollRectToVisible(new Rectangle(table8.getCellRect(newRow, 0, true)));	
				}
			});

			
			// Edit
			btn_Edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table8.isEnabled()) {
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
			btn_Delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
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
					model8.updateTableModelSpectrum(rowCount8, colCount8, data8, columnNames8);
					
					model8.fireTableDataChanged();	
				}
			});			
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------		    
		    
	
			
				
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			JScrollPane scrollpane_QuickEdit = new JScrollPane(new QuickEdit_ManagementCost_Panel(table7a, data7a, columnNames7a, table7b, data7b));			
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
 						GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
 					} else {
 						btnQuickEdit.setToolTipText("Show Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
 						scrollpane_QuickEdit.setVisible(false);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
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
			
			//when radioButton_Right[6] is selected, time period GUI will be updated
			radioButton_Right[4].addActionListener(this);			
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
	    		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_Database);	// "Get identifiers from yield table columns"
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

	
	
	// Panel Cost Adjustment--------------------------------------------------------------------------------------------------------
	class Cost_Adjustment_GUI extends JLayeredPane {
		public Cost_Adjustment_GUI() {
			setLayout(new GridBagLayout());				
		}
	}

	class Cost_Adjustment_Text  extends JTextArea {
		public Cost_Adjustment_Text() {
	
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
			static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_Database);
			checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();
			// End of 1st grid -----------------------------------------------------------------------

			
			// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
			dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_Database);
			// End of 2nd Grid -----------------------------------------------------------------------
				
					
			// 3rd grid ------------------------------------------------------------------------------		// Parameters
			parametersScrollPanel = new ScrollPane_Parameters(read_Database);
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
			btn_Edit.setToolTipText("Apply changes in Static Identifiers, Dynamic Identifiers, Parameters to the highlighted constraint");
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
					
					model9.updateTableModelSpectrum(rowCount9, colCount9, data9, columnNames9);
					update_id();
					model9.fireTableDataChanged();
					quick_edit = new QuickEdit_BasicConstraints_Panel(table9, data9);		// 2 lines to update data for Quick Edit Panel
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
					int response = JOptionPane.showOptionDialog(Spectrum_Main.get_spectrumDesktopPane(), constraint_split_ScrollPanel, "Create multiple constraints",
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
							response2 = JOptionPane.showOptionDialog(Spectrum_Main.get_spectrumDesktopPane(), warningText, "Confirm adding constraints",
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
												
		
								model9.updateTableModelSpectrum(rowCount9, colCount9, data9, columnNames9);
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
					model9.updateTableModelSpectrum(rowCount9, colCount9, data9, columnNames9);
					model9.fireTableDataChanged();	
					quick_edit = new QuickEdit_BasicConstraints_Panel(table9, data9);	// 2 lines to update data for Quick Edit Panel
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
						TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model9); // Add sorter
						table9.setRowSorter(sorter);
						btn_Sort.setText("ON");
						btn_Sort.repaint();
					}	
				}
			});
			
			
			
			
			// Validate
			btn_Validate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {						
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
				}
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
 			btnQuickEdit.addActionListener(new ActionListener() {
 				@Override
 				public void actionPerformed(ActionEvent actionEvent) {			
 					if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
 						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
 						scrollpane_QuickEdit.setVisible(true);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Basic_Constraints_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
 					} else {
 						btnQuickEdit.setToolTipText("Show Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
 						scrollpane_QuickEdit.setVisible(false);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Basic_Constraints_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
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
	    		parametersScrollPanel = new ScrollPane_Parameters(read_Database);	//"Get parameters from YT columns"
	    	}
	    	
	      	//Update Dynamic Identifier Panel
	    	if (yieldTable_ColumnNames != null && dynamic_identifiersScrollPanel.get_allDynamicIdentifiers() == null) {
	    		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_Database);	// "Get identifiers from yield table columns"
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
	class Advanced_Constraints_GUI extends JLayeredPane implements ActionListener {
		JTable basic_table;
		TableModelSpectrum model_basic;
		DefaultListModel id_list_model;
		JList id_list;
		JPanel button_table_Panel;
		
		QuickEdit_AdvancedConstraints_Panel quick_edit;
		JScrollPane scrollpane_QuickEdit;
		
		public Advanced_Constraints_GUI() {
			setLayout(new GridBagLayout());
			
			
			// 1st grid ------------------------------------------------------------------------------		// 
			id_list_model= new DefaultListModel<>();
			id_list = new JList(id_list_model);		
			ScrollPane_ConstraintsFlow flow_scrollPane = new ScrollPane_ConstraintsFlow(id_list);
			flow_scrollPane.setBorder(null);
			// End of 1st grid -----------------------------------------------------------------------

			
			// 2nd Grid ------------------------------------------------------------------------------		// Basic Constraint Table
			model_basic = new TableModelSpectrum(rowCount9, colCount9, data9, columnNames9) {
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
					TableCellRenderer renderer2 = basic_table.getTableHeader().getDefaultRenderer();	
					Component component2 = renderer2.getTableCellRendererComponent(basic_table,
				            tableColumn.getHeaderValue(), false, false, -1, column);
					maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
					
					tableColumn.setPreferredWidth(maxWidth);
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
			
			
			JScrollPane basic_table_scrollPane = new JScrollPane(basic_table);
	        TitledBorder border = new TitledBorder("Sources (Basic Constraints)");
			border.setTitleJustification(TitledBorder.CENTER);
			basic_table_scrollPane.setBorder(border);	
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
			btn_NewSingle.setToolTipText("New constraint");
			btn_NewSingle.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));					
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_NewSingle, c2);
			
			
			JButton btn_Edit = new JButton();
			btn_Edit.setToolTipText("Apply changes in Flow arrangement to the highlighted flow");
			btn_Edit.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_swap.png"));
			btn_Edit.setEnabled(false);					
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 0;
			c2.weighty = 0;
			button_table_Panel.add(btn_Edit, c2);
			
			
			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 14));
			btn_Delete.setToolTipText("Delete constraints");
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
			btn_Validate.setToolTipText("Validate constraints");
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
			btn_NewSingle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
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
//						data10[rowCount10 - 1][4] = (double) 100;
//						data10[rowCount10 - 1][5] = (double) 100;
						model10.updateTableModelSpectrum(rowCount10, colCount10, data10, columnNames10);
						update_id();
						model10.fireTableDataChanged();
						quick_edit = new QuickEdit_AdvancedConstraints_Panel(table10, data10);		// 2 lines to update data for Quick Edit Panel
			 			scrollpane_QuickEdit.setViewportView(quick_edit);
						
						// Convert the new Row to model view and then select it 
						int newRow = table10.convertRowIndexToView(rowCount10 - 1);
						table10.setRowSelectionInterval(newRow, newRow);
						table10.scrollRectToVisible(new Rectangle(table10.getCellRect(newRow, 0, true)));
					}			
				}
			});
			
			
			
			// Edit
			btn_Edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table10.isEnabled()) {	
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
			btn_Delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
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
					model10.updateTableModelSpectrum(rowCount10, colCount10, data10, columnNames10);
					model10.fireTableDataChanged();	
					quick_edit = new QuickEdit_AdvancedConstraints_Panel(table10, data10);		// 2 lines to update data for Quick Edit Panel
		 			scrollpane_QuickEdit.setViewportView(quick_edit);
				}
			});
					
			
			// Sort
			btn_Sort.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (btn_Sort.getText().equals("ON")) {
						table10.setRowSorter(null);
						btn_Sort.setText("OFF");
						btn_Sort.repaint();
					} else if (btn_Sort.getText().equals("OFF")) {
						TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model10); // Add sorter
						table10.setRowSorter(sorter);
						btn_Sort.setText("ON");
						btn_Sort.repaint();
					}	
				}
			});
			
			
			
			
			// Validate
			btn_Validate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {						
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
				}
			});			
	
			// End of Listeners for table10 & buttons -----------------------------------------------------------------------
			// End of Listeners for table10 & buttons -----------------------------------------------------------------------		    
		    
	
			
			
			
			
			
			
	        // scrollPane Quick Edit ----------------------------------------------------------------------	
			// scrollPane Quick Edit ----------------------------------------------------------------------	
			quick_edit = new QuickEdit_AdvancedConstraints_Panel(table10, data10);
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
 			btnQuickEdit.addActionListener(new ActionListener() {
 				@Override
 				public void actionPerformed(ActionEvent actionEvent) {			
 					if (btnQuickEdit.getToolTipText().equals("Show Quick Edit Tool")) {
 						btnQuickEdit.setToolTipText("Hide Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_hide.png"));
 						scrollpane_QuickEdit.setVisible(true);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Advanced_Constraints_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
 					} else {
 						btnQuickEdit.setToolTipText("Show Quick Edit Tool");
 						btnQuickEdit.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_show.png"));
 						scrollpane_QuickEdit.setVisible(false);
 						// Get everything show up nicely
 						GUI_Text_splitPanel.setLeftComponent(panel_Advanced_Constraints_GUI);
 						Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());
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
			model_basic.updateTableModelSpectrum(rowCount9, colCount9, data9, columnNames9);	// Update table9 to the Sources in Advanced constraints GUI
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

	class Advanced_Constraints_Text  extends JTextArea {
		public Advanced_Constraints_Text() {
	
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
//		create_file_input_07();
		create_file_input_08();
		create_file_input_09();		
		create_file_input_10();	
		create_file_database();		// Note for this file, we just copy overwritten
		
		
		
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
		File selectedStrataFile = new File(currentRunFolder.getAbsolutePath() + "/input_02_modeled_strata.txt");	
		if (selectedStrataFile.exists()) {
			selectedStrataFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data2 != null && modeledAcres > 0) {
			//Only print out Strata with implemented methods <> null
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(selectedStrataFile))) {
				for (int j = 0; j < columnNames2.length; j++) { //Note: colCount = columnNames.length
					fileOut.write(columnNames2[j] + "\t");
				}
				
//				String temp = String.join("\t", columnNames2);
//				fileOut.write(temp);

				for (int i = 0; i < data2.length; i++) {				//Note: String.ValueOf   is so important to get the String from Object
					if (String.valueOf(data2[i][colCount2 - 1]).equals("true")) { //IF strata is in optimization model		
						fileOut.newLine();
						for (int j = 0; j < colCount2; j++) {
							fileOut.write(data2[i][j] + "\t");
						}
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	
	private void create_file_input_03() {
		//Only print out if the last column Allowed Options <> null
		File clearcutConversionFile = new File(currentRunFolder.getAbsolutePath() + "/input_03_clearcut_covertype_conversion.txt");
		if (clearcutConversionFile.exists()) {
			clearcutConversionFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data3 != null && data3.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(clearcutConversionFile))) {
				for (int j = 0; j < columnNames3.length; j++) {
					fileOut.write(columnNames3[j] + "\t");
				}

				for (int i = 0; i < data3.length; i++) {
					if (String.valueOf(data3[i][colCount3 - 1]).equals("true")) { //IF conversion is selected
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
		File replacingDisturbanceConversionFile = new File(currentRunFolder.getAbsolutePath() + "/input_04_replacingdisturbances_covertype_conversion.txt");	
		if (replacingDisturbanceConversionFile.exists()) {
			replacingDisturbanceConversionFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data4 != null && data4.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(replacingDisturbanceConversionFile))) {
				for (int j = 0; j < columnNames4.length; j++) {
					fileOut.write(columnNames4[j] + "\t");
				}

				for (int i = 0; i < data4.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < colCount4; j++) {
						fileOut.write(data4[i][j] + "\t");
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}	
	
	
	private void create_file_input_05() {
		File nonReplacingDisturbanceFile = new File(currentRunFolder.getAbsolutePath() + "/input_05_non_replacing_disturbances.txt");	
		if (nonReplacingDisturbanceFile.exists()) {
			nonReplacingDisturbanceFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data5 != null && data5.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(nonReplacingDisturbanceFile))) {
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
		File replacingDisturbanceFile = new File(currentRunFolder.getAbsolutePath() + "/input_06_replacing_disturbances.txt");	
		if (replacingDisturbanceFile.exists()) {
			replacingDisturbanceFile.delete();		// Delete the old file before writing new contents
		}
		
		if (data6 != null && data6.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(replacingDisturbanceFile))) {
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

	
//	private void create_file_input_07() {
//		File baseCostFile = new File(currentRunFolder.getAbsolutePath() + "/input_07_base_cost.txt");	
//		if (baseCostFile.exists()) {
//			baseCostFile.delete();		// Delete the old file before writing new contents
//		}
//		
//		if (data7a != null && data7a.length > 0) {
//			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(baseCostFile))) {
//				for (int j = 0; j < columnNames7a.length; j++) {
//					fileOut.write(columnNames7a[j] + "\t");
//				}
//
//				for (int i = 0; i < data7a.length; i++) {
//					fileOut.newLine();
//					for (int j = 0; j < colCount7a; j++) {
//						fileOut.write(data7a[i][j] + "\t");
//					}
//				}
//				fileOut.close();
//			} catch (IOException e) {
//				System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			} 
//		}
//	}
	
	
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
			if (file_Database != null) Files.copy(file_Database.toPath(), databaseFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}	
	
}
