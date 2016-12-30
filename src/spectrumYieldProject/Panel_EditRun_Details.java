package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

import spectrumConvenienceClasses.FilesHandle;
import spectrumConvenienceClasses.TableModelSpectrum;
import spectrumROOT.Spectrum_Main;

public class Panel_EditRun_Details extends JLayeredPane implements ActionListener {
	private JSplitPane GUI_Text_splitPanel ;
	private JPanel radioPanel_Right; 
	private ButtonGroup radioGroup_Right; 
	private JRadioButton[] radioButton_Right; 
	
	private File currentRunFolder, temporary_RunFolder;
	private File file_ExistingStrata, file_Database;
	private File file_StrataDefinition;
	private String currentDefinition_location;
	
	//6 panels for the selected Run
	private General_Inputs_GUI paneL_General_Inputs_GUI;
	private General_Inputs_Text panel_General_Inputs_Text;
	private Model_Identifiniton_GUI panel_Model_Identifiniton_GUI;
	private Model_Identification_Text panel_Model_Identification_Text;
	private CovertypeConversion_GUI panel_CovertypeConversion_GUI;
	private CovertypeConversion_Text panel_CovertypeConversion_Text;
	private Disturbances_GUI panel_Disturbances_GUI;
	private Disturbances_Text panel_Disturbances_Text;
	private Management_Cost_GUI panel_Management_Cost_GUI;
	private Management_Cost_Text panel_Management_Cost_Text;
	private UserConstraints_GUI panel_UserConstraints_GUI;
	private UserConstraints_Text panel_UserConstraints_Text;	
	

	private ImageIcon icon;
	private Image scaleImage;
	
	private Read_Strata read_Strata;
	private Read_DatabaseTables read_DatabaseTables;
	private Read_Indentifiers read_Identifiers;
	
	private Object[][][] yieldTable_values;
	private String [] yieldTable_ColumnNames;
	
	
	private int totalPeriod;
	
	
	
	private boolean is_table1_loaded = false;
	private int rowCount1, colCount1;
	private String[] columnNames1;
	private JTable table1;
	private TableModelSpectrum model1;
	private Object[][] data1;
	
	
	private boolean is_table_loaded = false;
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private TableModelSpectrum model;
	private Object[][] data;


	private boolean is_table2_loaded = false;
	private int rowCount2, colCount2;
	private String[] columnNames2;
	private JTable table2;
	private TableModelSpectrum model2;
	private Object[][] data2;

	
	//Jtable Models OverView (in input 1)
	private boolean is_table3_loaded = false;
	private int rowCount3, colCount3;
	private String[] columnNames3;
	private JTable table3;
	private TableModelSpectrum model3;
	private Object[][] data3;
	private double modeledAcres, availableAcres;
	
	//Jtable EA requirements
	private boolean is_table4_loaded = false;
	private int rowCount4, colCount4;
	private String[] columnNames4;
	private JTable table4;
	private TableModelSpectrum model4;
	private Object[][] data4;
	
	//Jtable SRD requirements
	private boolean is_table7_loaded = false;
	private int rowCount7, colCount7;
	private String[] columnNames7;
	private JTable table7;
	private TableModelSpectrum model7;
	private Object[][] data7;

	//Jtable MixedFire
	private boolean is_table5_loaded = false;
	private int rowCount5, colCount5;
	private String[] columnNames5;
	private JTable table5;
	private TableModelSpectrum model5;
	private Object[][] data5;
	
	//Jtable Stand Replacing Disturbances
	private boolean is_table6_loaded = false;
	private int rowCount6, colCount6;
	private String[] columnNames6;
	private JTable table6;
	private TableModelSpectrum model6;
	private Object[][] data6;
	
	
	private boolean is_this_the_first_load = true;
	private JButton button_import_database;
	private JButton button_import_existingStrata;
	private JButton button_select_Strata;
	
	public Panel_EditRun_Details(File RunFolder) {
		super.setLayout(new BorderLayout());

		//Get information from the run and reload inputs-------------------------------------------------------------------------------------------
		currentRunFolder = RunFolder;	

		temporary_RunFolder = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + currentRunFolder.getName());
		temporary_RunFolder.deleteOnExit();
		if (!temporary_RunFolder.exists()) {
			temporary_RunFolder.mkdirs();		 // Create folder Temporary if it does not exist
		}
		
		reload_inputs_before_creating_GUI();
		
		
		
		//Create the interface ---------------------------------------------------------------------------------------------------------------------
		// Add 6 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
		radioPanel_Right = new JPanel();
		radioPanel_Right.setLayout(new FlowLayout());		
		radioGroup_Right = new ButtonGroup();
		
		radioButton_Right  = new JRadioButton[6];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[1]= new JRadioButton("Model Identification");
		radioButton_Right[2]= new JRadioButton("Covertype Conversion");
		radioButton_Right[3]= new JRadioButton("Natural Disturbances");
		radioButton_Right[4]= new JRadioButton("Management Cost");
		radioButton_Right[5]= new JRadioButton("User Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < radioButton_Right.length; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
				radioButton_Right[i].addActionListener(this);
		}	
		
		GUI_Text_splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_splitPanel.setDividerSize(5);
		GUI_Text_splitPanel.setEnabled(false);
			
	
		// Create all new 6 panels for the selected Run--------------------------------------------------
		paneL_General_Inputs_GUI = new General_Inputs_GUI();
		panel_General_Inputs_Text = new General_Inputs_Text();
		panel_Model_Identifiniton_GUI = new Model_Identifiniton_GUI();
		panel_Model_Identification_Text = new Model_Identification_Text();
		panel_CovertypeConversion_GUI = new CovertypeConversion_GUI();
		panel_CovertypeConversion_Text = new CovertypeConversion_Text();
		panel_Disturbances_GUI = new Disturbances_GUI();
		panel_Disturbances_Text = new Disturbances_Text();
		panel_Management_Cost_GUI = new Management_Cost_GUI();
		panel_Management_Cost_Text = new Management_Cost_Text();
		panel_UserConstraints_GUI = new UserConstraints_GUI();
		panel_UserConstraints_Text = new UserConstraints_Text();
					
		
		// Show the 2 panelInput of the selected Run
		GUI_Text_splitPanel.setLeftComponent(paneL_General_Inputs_GUI);
		GUI_Text_splitPanel.setRightComponent(panel_General_Inputs_Text);	
		
		
		// Add all components to The Panel------------------------------------------------------------
		super.add(radioPanel_Right, BorderLayout.NORTH);
		super.add(GUI_Text_splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
		
		
		reload_inputs_after_creating_GUI();
		is_this_the_first_load = false;	
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
					GUI_Text_splitPanel.setLeftComponent(panel_CovertypeConversion_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_CovertypeConversion_Text);
				} else if (j == 3) {
					GUI_Text_splitPanel.setLeftComponent(panel_Disturbances_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_Disturbances_Text);
				} else if (j == 4) {
					GUI_Text_splitPanel.setLeftComponent(panel_Management_Cost_GUI);
					GUI_Text_splitPanel.setRightComponent(panel_Management_Cost_Text);
				} else if (j == 5) {
					GUI_Text_splitPanel.setLeftComponent(panel_UserConstraints_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panel_UserConstraints_Text);
				}
			}
		}
	}
 
    
    // Reload inputs of the run------------------------------------------------------------------------------------------------ 
	public void reload_inputs_before_creating_GUI() {

		// Load "StrataDefinition.csv" of the run-------------------------------------------------------
		File definition_to_load = new File(currentRunFolder.getAbsolutePath() + "/StrataDefinition.csv");
		if (definition_to_load.exists()) {	//Load if the file exists
			try {
				file_StrataDefinition = new File(temporary_RunFolder.getAbsolutePath() + "/StrataDefinition.csv");
				file_StrataDefinition.deleteOnExit();
				currentDefinition_location = file_StrataDefinition.getAbsolutePath();
				
				Files.copy(definition_to_load.toPath(), file_StrataDefinition.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		} 
		else { 	// If file does not exist then load the default definition
			System.out.println("File not exists: StrataDefinition.csv - New interface is created using Default StrataDefinition.csv");
			
			try {	// Read default "StrataDefinition.csv" file from where this class is located
				file_StrataDefinition = new File(temporary_RunFolder.getAbsolutePath() + "/StrataDefinition.csv");
				file_StrataDefinition.deleteOnExit();		
				currentDefinition_location = "Default: " + file_StrataDefinition.getAbsolutePath();
				
				InputStream initialStream = getClass().getResourceAsStream("/StrataDefinition.csv");
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);
				
				OutputStream outStream = new FileOutputStream(file_StrataDefinition);		//  and write to file_StrataDefinition
				outStream.write(buffer);

				initialStream.close();
				outStream.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
		
		
		
		// Load database of the run---------------------------------------------------------------------
		File exsitingStrata_to_load = new File(currentRunFolder.getAbsolutePath() + "/existingStrata.csv");
		if (exsitingStrata_to_load.exists()) {	//Load if the file exists
			try {
				file_ExistingStrata = new File(temporary_RunFolder.getAbsolutePath() + "/existingStrata.csv");
				file_ExistingStrata.deleteOnExit();
				Files.copy(new File(currentRunFolder.getAbsolutePath() + "/existingStrata.csv").toPath(), file_ExistingStrata.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}		
		
			
		// Load database of the run---------------------------------------------------------------------
		File database_to_load = new File(currentRunFolder.getAbsolutePath() + "/database.db");
		if (database_to_load.exists()) {	//Load if the file exists
			try {
				file_Database = new File(temporary_RunFolder.getAbsolutePath() + "/database.db");
				file_Database.deleteOnExit();
				Files.copy(new File(currentRunFolder.getAbsolutePath() + "/database.db").toPath(), file_Database.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}					
											

		
		//Load tables---------------------------------------------------------------------------------
		File table_file;
		Reload_Table_Info tableLoader;
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 1 - General Inputs.txt");
		if (table_file.exists()) {		//Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount1 = tableLoader.get_rowCount();
			colCount1 = tableLoader.get_colCount();
			data1 = tableLoader.get_input_data();
			columnNames1 = tableLoader.get_columnNames();
			is_table1_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: Input 1 - General Inputs.txt - New interface is created");
		}
			
		
		if (file_ExistingStrata == null && is_this_the_first_load == true) {		//If there is no existing strata, still load the selected strata
			table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 2 - Selected Strata.txt");
			if (table_file.exists()) { // Load from input
				tableLoader = new Reload_Table_Info(table_file);
				rowCount = tableLoader.get_rowCount();
				colCount = tableLoader.get_colCount();
				data = tableLoader.get_input_data();
				columnNames = tableLoader.get_columnNames();
				is_table_loaded = true;
				
	
	//			//This is strange when only this 1 I have to register the "Yes" - in case we don't use String.ValueOf to compare data (see last lines)
	//			for (int i = 0; i < rowCount; i++) {
	//				data[i][colCount-1] = "Yes";
	//			}			
			} else { // Create a fresh new if Load fail
				System.err.println("File not exists: Input 2 - Selected Strata.txt - New interface is created");
			}	
		}
		
															//Need to change later (not here , below) because I didn't write the whole file, just write the yes case
//		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 3 - Covertype Conversion (Clear Cuts).txt");
//		if (table_file.exists()) { // Load from input
//			tableLoader = new Reload_Table_Info(table_file);
//			rowCount4 = tableLoader.get_rowCount();
//			colCount4 = tableLoader.get_colCount();
//			data4 = tableLoader.get_input_data();
//			columnNames4 = tableLoader.get_columnNames();
//			is_table4_loaded = true;
//		} else { // Create a fresh new if Load fail
//			System.err.println("File not exists: Input 3 - Covertype Conversion (Clear Cuts).txt - New interface is created");
//		}

		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 4 - Covertype Conversion (Replacing Disturbances).txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount7 = tableLoader.get_rowCount();
			colCount7 = tableLoader.get_colCount();
			data7 = tableLoader.get_input_data();
			columnNames7 = tableLoader.get_columnNames();
			is_table7_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: Input 4 - Covertype Conversion (Replacing Disturbances).txt - New interface is created");
		}
		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 5 - Mixed Severity Fire.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount5 = tableLoader.get_rowCount();
			colCount5 = tableLoader.get_colCount();
			data5 = tableLoader.get_input_data();
			columnNames5 = tableLoader.get_columnNames();
			is_table5_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: Input 5 - Mixed Severity Fire.txt - New interface is created");
		}
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 6 - Replacing Disturbances.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount6 = tableLoader.get_rowCount();
			colCount6 = tableLoader.get_colCount();
			data6 = tableLoader.get_input_data();
			columnNames6 = tableLoader.get_columnNames();
			is_table6_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: Input 6 - Replacing Disturbances.txt - New interface is created");
		}		
		
		
		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 8 - User Constraints.txt");
		if (table_file.exists()) { // Load from input
			tableLoader = new Reload_Table_Info(table_file);
			rowCount2 = tableLoader.get_rowCount();
			colCount2 = tableLoader.get_colCount();
			data2 = tableLoader.get_input_data();
			columnNames2 = tableLoader.get_columnNames();
			is_table2_loaded = true;
		} else { // Create a fresh new if Load fail
			System.err.println("File not exists: Input 8 - User Constraints.txt - New interface is created");
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
		
		if (file_ExistingStrata != null && is_this_the_first_load == true) {
			button_import_existingStrata.setEnabled(true);
			button_import_existingStrata.doClick();
			
			
			// Find the data match to paste into Existing Strata		
			table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 2 - Selected Strata.txt");
			if (table_file.exists()) { // Load from input
				tableLoader = new Reload_Table_Info(table_file);
				
				Object[][] temp_data = tableLoader.get_input_data();
				for (int i = 0; i < temp_data.length; i++) {
					for (int ii = 0; ii < data.length; ii++) {
						if (   String.valueOf(data[ii][0]).equals(String.valueOf(temp_data[i][0]))   ) {		//Just need to compare Strata ID
							// Apply temp_data row values to data row 
							for (int jj = 0; jj < data[ii].length; jj++) {
								data[ii][jj] = temp_data[i][jj];
							}		
						}	
					}
				}			
				button_select_Strata.setEnabled(true);
				button_select_Strata.doClick();
				
				is_table_loaded = true;		
			} else { // Create a fresh new if Load fail
				System.err.println("File not exists: Input 2 - Selected Strata.txt - New interface is created");
			}	
		}
		
		

		table_file = new File(currentRunFolder.getAbsolutePath() + "/Input 3 - Covertype Conversion (Clear Cuts).txt");
		if (table_file.exists()) { // Load from input
			//Reset the "Yes" to be blank
			for (int i = 0; i < data4.length; i++) {
				data4[i][4] = null;
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
			System.err.println("File not exists: Input 3 - Covertype Conversion (Clear Cuts).txt - New interface is created");
		}
				
		
		
		if (file_Database != null && is_this_the_first_load == true) {
			button_import_database.setEnabled(true);
			button_import_database.doClick();
		}
		
		
				
		//Matching data types after finishing reloads
		model.match_DataType();			//a smart way to retrieve the original data type :))))))	
		model2.match_DataType();			//a smart way to retrieve the original data type :))))))
	}					
					
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------  
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table() {
		//Setup the table------------------------------------------------------------	
		if (is_table_loaded == false) { // Create a fresh new if Load fail				
			read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
			List<String> layers_Title = read_Identifiers.get_layers_Title();
			List<String> layers_Title_ToolTip = read_Identifiers.get_layers_Title_ToolTip();
	         
			
			rowCount = 0;
			colCount = layers_Title.size() + 4;
			columnNames = new String[colCount];

			columnNames[0] = "Strata ID";		//add for the name of strata
			for (int i = 0; i < layers_Title.size(); i++) {
				columnNames[i+1] = layers_Title.get(i);			//add 6 layers to the column header name
			}
	         
			columnNames[colCount - 3] = "Total area (acres)";	//add 3 more columns
			columnNames[colCount - 2] = "Age Class";
			columnNames[colCount - 1] = "Strata in optimization model";	
		}
					
		
		//Create a table-------------------------------------------------------------
		model = new TableModelSpectrum(rowCount, colCount, data, columnNames) {
			@Override
			public Class getColumnClass(int c) {
				if (c == colCount - 3) return Double.class;      //column "Total Acres" accepts only Double
				if (c == colCount - 2) return Integer.class;     //column "Age Class" accepts only Integer
		        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount; row++) {
					for (int col = 0; col < colCount; col++) {
						if (String.valueOf(data[row][col]).equals("null")) {
							data[row][col] = null;
						} else {					
							if (col == colCount - 3) {			//column "Total Acres" accepts only Double
								try {
									data[row][col] = Double.valueOf(String.valueOf(data[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in User Constraints");
								}	
							} /*else if (col == colCount - 2) {			//column "Age Class" accepts only Integer
								try {
									data[row][col] = Integer.valueOf(String.valueOf(data[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in User Constraints");
								}	
							}*/ else {	//All other columns are String
								data[row][col] = String.valueOf(data[row][col]);
							}
						}	
					}	
				}	
			}
		};
		
		
		
		table = new JTable(model) {
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

//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	  
		table.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//		table.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
        table.getColumnModel().getColumn(colCount - 3).setPreferredWidth(120);	//Set width of Column "Total area" bigger
        table.getColumnModel().getColumn(colCount - 1).setPreferredWidth(200);	//Set width of Column "Strata in optimization model" bigger
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
	}
    
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table1() {	
		//Setup the table------------------------------------------------------------	
		if (is_table1_loaded == false) { // Create a fresh new if Load fail
			rowCount1 = 4;
			colCount1 = 2;
			data1 = new Object[rowCount1][colCount1];
			columnNames1 = new String[] { "Input Description", "Selected Option" };
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
		class comboBox_ConstraintType extends JComboBox {	
			public comboBox_ConstraintType() {
			addItem("HARD");
			addItem("SOFT");	
			setSelectedIndex(0);
			}
		}
		
			
		//Setup the table------------------------------------------------------------	
		if (is_table2_loaded == false) { // Create a fresh new if Load fail				
			rowCount2 = 10000;
			colCount2 = 9;
			data2 = new Object[rowCount2][colCount2];
			columnNames2 = new String[] { "Const. Description (optional)", "Const. Type", "LB Value", "Penalty/Unit < LB (SOFT)", "UB Value", "Penalty/Unit > UB (SOFT)", "PARAMETERS Index", "Static identifiers for VARIABLES", "Dynamic identifiers for PARAMETERS"};	         				
		}
					
		
		//Create a table-------------------------------------------------------------		
		model2 = new TableModelSpectrum(rowCount2, colCount2, data2, columnNames2) {
			@Override
			public Class getColumnClass(int c) {
				if (c==0) return String.class;      //column 0 accepts only String
				else if (c>=2 && c<=5) return Double.class;      //column 2 to 5 accept only Double values   
		        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col >= colCount2 - 3) { //  The last 3 columns are un-editable
					return false;
				} else {
					return true;
				}
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount2; row++) {
					for (int col = 0; col < colCount2; col++) {
						if (String.valueOf(data2[row][col]).equals("null")) {
							data2[row][col] = null;
						} else {					
							if (col >= 2 && col <= 5) {			//Column 2 to 5 are Double
								try {
									data2[row][col] = Double.valueOf(String.valueOf(data2[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in User Constraints");
								}	
							} else {	//All other columns are String
								data2[row][col] =String.valueOf(data2[row][col]);
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



         

        // Set up Types for each table2 Columns
		table2.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new comboBox_ConstraintType()));
			
			
         
         
         
        //table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table2.getColumnModel().getColumn(0).setPreferredWidth(250);
//		table2.getColumnModel().getColumn(colCount2-7).setPreferredWidth(150);	
//		table2.getColumnModel().getColumn(colCount2-6).setPreferredWidth(150);	
//		table2.getColumnModel().getColumn(colCount2-5).setPreferredWidth(150);	
//        table2.getColumnModel().getColumn(colCount2-4).setPreferredWidth(150);	//Set width of Column "Penalty/Unit (Soft Const.)" bigger
//        table2.getColumnModel().getColumn(colCount2-3).setPreferredWidth(150);	//Set width of Column "PARAMETERS" bigger
//        table2.getColumnModel().getColumn(colCount2-2).setPreferredWidth(300);	//Set width of Column "Static identifiers for VARIABLES" bigger
//        table2.getColumnModel().getColumn(colCount2-1).setPreferredWidth(300);	//Set width of Column "Dynamic identifiers for PARAMETERS" bigger
//		table2.setPreferredScrollableViewportSize(new Dimension(1500, 200));
		table2.setAutoResizeMode(0);
		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table2.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table2.setPreferredScrollableViewportSize(new Dimension(300, 100));
		table2.setFillsViewportHeight(true);
		TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model2);	//Add sorter
		table2.setRowSorter(sorter);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table3() {
		//Setup the table--------------------------------------------------------------------------------
		rowCount3 = 4;
		colCount3 = 2;
		data3 = new Object[rowCount3][colCount3];
        columnNames3= new String[] {"Description" , "Value"};
		
		// Populate the data matrix
		data3[0][0] = "Modeled Strata vs Available Strata";
		data3[1][0] = "Modeled Acres vs Available Acres";
		data3[2][0] = "Number of yield tables in your database";
		data3[3][0] = "Number of strata not connected to any yield table";
		
		
		//Create a table
        model3 = new TableModelSpectrum(rowCount3, colCount3, data3, columnNames3);
        table3 = new JTable(model3);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table3.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//      table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table3.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        table3.getColumnModel().getColumn(0).setPreferredWidth(250);	//Set width of 1st Column bigger
        table3.setTableHeader(null);
        table3.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table3.setFillsViewportHeight(true);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table4() {
		read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
		List<List<String>> allLayers = read_Identifiers.get_allLayers();
		List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
	

		//Setup the table------------------------------------------------------------	
//		if (is_table4_loaded == false) { // Create a fresh new if Load fail				
			rowCount4 = total_CoverType*total_CoverType;
			colCount4 = 5;
			data4 = new Object[rowCount4][colCount4];
	        columnNames4= new String[] {"Cover Type before Clear Cut", "Cover Type after Clear Cut", "Min Age-Class for Clear Cut", "Max Age-Class for Clear Cut", "Options to be applied"};
			
			// Populate the data matrix
	        int table_row = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_CoverType; j++) {
					data4[table_row][0] = allLayers.get(4).get(i);
					data4[table_row][1] = allLayers.get(4).get(j);	
					data4[table_row][2] = 20;
					data4[table_row][3] = 24;
					if (i==j) data4[table_row][4] = "Yes"; 
					table_row++;
				}
			}
//		}
			
		
		//Create a table-------------------------------------------------------------
		model4 = new TableModelSpectrum(rowCount4, colCount4, data4, columnNames4) {			
			@Override
			public Class getColumnClass(int c) {
				if (c==0) return String.class;      //column 0 accepts only String
				else if (c>=2 && c<=3) return Integer.class;      //column 2 and 3 accept only Integer values    
		        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
			}

			// Don't need to implement this method unless your table's editable.
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col < 2) { // Only the last 3 columns are editable
					return false;
				} else {
					return true;
				}
			}

			@Override
			public void setValueAt(Object value, int row, int col) {
				if ((col>=2 && col<=3) && (((Number) value).intValue() < 1 || ((Number) value).intValue() > 100)) {
					JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
							"Your input has not been accepted. Only age classes in the range 1-100 would be allowed.");
				} else {
					data4[row][col] = value;
					fireTableDataChanged();
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
		icon = new ImageIcon(getClass().getResource("/icon_main.png"));
		scaleImage = icon.getImage().getScaledInstance(3, 3, Image.SCALE_SMOOTH);
		ImageIcon icon_scale = new ImageIcon(scaleImage);

		ImageIcon[] imageIconArray = new ImageIcon[colCount4];
		for (int i = 0; i < colCount4; i++) {
			if (i == 2 || i == 3 || i == 4) {
				imageIconArray[i] = icon_scale;
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
				setIcon(imageIconArray[column]);	// Set icons for cells in some columns
				setIconTextGap(15);		// Set the distance between icon and the actual data value

                return this;
            }
        };
        

        
		for (int i = 0; i < columnNames4.length; i++) {
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
//		class comboBox_MinAge extends JComboBox {
//			public comboBox_MinAge() {
//				for (int i = 1; i <= 100; i++) {
//					addItem(i);
//				}
//				setSelectedItem((int) 20);
//			}
//		}
//		
//		class comboBox_MaxAge extends JComboBox {
//			public comboBox_MaxAge() {
//				for (int i = 1; i <= 100; i++) {
//					addItem(i);
//				}
//				setSelectedItem((int) 24);
//			}
//		}
		
		class comboBox_ConstraintType extends JComboBox {	
			public comboBox_ConstraintType() {
			addItem("Yes");
			addItem(null);	
//			setSelectedIndex(2);
			}
		}
		  
//		table4.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_MinAge()));
//		table4.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new comboBox_MaxAge()));
		table4.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new comboBox_ConstraintType()));
		// End of Set up Types for each  Columns------------------------------------------------------------------------
		
		
		
		
		
//      table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table4.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table4.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//      table4.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
		
//      table4.setTableHeader(null);
        table4.setPreferredScrollableViewportSize(new Dimension(400, 120));
        table4.setFillsViewportHeight(true);
//      TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model4);	//Add sorter
//		table4.setRowSorter(sorter);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table5() {
		read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
		List<List<String>> allLayers =  read_Identifiers.get_allLayers();
		List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		int total_SizeClass = allLayers.get(5).size();		// total number of elements - 1 in layer6 Size Class (0 to...)			
		
		
		//Setup the table------------------------------------------------------------	
		if (is_table5_loaded == false) { // Create a fresh new if Load fail				
			rowCount5 = total_CoverType*total_SizeClass;
			colCount5 = 3;
			data5 = new Object[rowCount5][colCount5];
	        columnNames5= new String[] {"1st Period - Cover Type of Existing Strata", "1st Period - Size Class of Existing Strata", "Proportion (%) sufferred from Mixed Severity Wildfire"};
			
			// Populate the data matrix
	        int table_row = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_SizeClass; j++) {
					data5[table_row][0] = allLayers.get(4).get(i);
					data5[table_row][1] = allLayers.get(5).get(j);	
					data5[table_row][2] = 5.0;
					if (allLayers.get(4).get(i).equals("N"))	data5[table_row][2] = 0.0;	//Non-stocked --> No MS Fire
					table_row++;
				}
			}						
		}
					
		
		//Create a table-------------------------------------------------------------			
        model5 = new TableModelSpectrum(rowCount5, colCount5, data5, columnNames5) {
        	@Override
        	public Class getColumnClass(int c) {
    			if (c==0 || c==1) return String.class;      //column 0 and 1 accept only String
    			else if (c==2) return Double.class;      //column 2 (last column) accept only Double values    
    	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
    		}

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
    			if (col == 2 && (((Number) value).doubleValue() < 0 || ((Number) value).doubleValue() > 100)) {
    				JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
    						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
    			} else {
    				data5[row][col] = value;
    				fireTableDataChanged();
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
		icon = new ImageIcon(getClass().getResource("/icon_main.png"));
		scaleImage = icon.getImage().getScaledInstance(3, 3, Image.SCALE_SMOOTH);
		ImageIcon icon_scale = new ImageIcon(scaleImage);

		ImageIcon[] imageIconArray = new ImageIcon[colCount5];
		for (int i = 0; i < colCount5; i++) {
			if (i == 2) {
				imageIconArray[i] = icon_scale;
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
				setIcon(imageIconArray[column]);	// Set icons for cells in some columns
				setIconTextGap(15);		// Set the distance between icon and the actual data value

//				int[] rowAlignment = new int[rowCount5];
//				int currentAlignment = SwingConstants.CENTER;
//				int rCount2 = 0;
//
//				for (int i = 0; i < total_CoverType; i++) {
//					if (currentAlignment == SwingConstants.CENTER) {
//						currentAlignment = SwingConstants.LEFT;
//					} else {
//						currentAlignment = SwingConstants.CENTER;
//					}
//					for (int j = 0; j < total_SizeClass; j++) {
//						rowAlignment[rCount2] = currentAlignment;
//						rCount2++;
//					}
//				}
//				setHorizontalAlignment(rowAlignment[row]);
				
                return this;
            }
        };						
		
		
      
        for (int i=0; i<columnNames5.length; i++) {
        	table5.getColumnModel().getColumn(i).setCellRenderer(r);
        }
       
        
//        table5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table5.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table5.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//        table5.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        
//        table5.setTableHeader(null);
        table5.setPreferredScrollableViewportSize(new Dimension(400, 120));
        table5.setFillsViewportHeight(true);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table6() {
		read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
		List<List<String>> allLayers =  read_Identifiers.get_allLayers();
		List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		int total_SizeClass = allLayers.get(5).size();		// total number of elements - 1 in layer6 Size Class (0 to...)	
		
				
		//Setup the table------------------------------------------------------------	
		if (is_table6_loaded == false) { // Create a fresh new if Load fail				
			rowCount6 = 30;
			colCount6 = total_CoverType + 1;
			data6 = new Object[rowCount6][colCount6];
	        columnNames6= new String[colCount6];
	        columnNames6[0] = "Age Class of Strata";
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
        	public Class getColumnClass(int c) {
    			if (c==0) return Integer.class;      //column 0 accepts only String
    			else if (c>=1) return Double.class;      //All other columns accept only Double values    
    	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
    		}

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
    				JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
    						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
    			} else {
    				data6[row][col] = value;
    				fireTableDataChanged();
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
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        return headerToolTips[realIndex];
                    }
                };
            }
        };
		
        
        
        
     // Define a set of icon for some columns
        icon = new ImageIcon(getClass().getResource("/icon_main.png"));
 		scaleImage = icon.getImage().getScaledInstance(3, 3, Image.SCALE_SMOOTH);
 		ImageIcon icon_scale = new ImageIcon(scaleImage);

 		ImageIcon[] imageIconArray = new ImageIcon[colCount6];
 		for (int i = 0; i < colCount6; i++) {
 			if (i >= 1) {
 				imageIconArray[i] = icon_scale;
 			}
 		}
 		
 	       
		//Set Color and Alignment for Cells
        DefaultTableCellRenderer r2 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
                value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				setHorizontalAlignment(JLabel.LEFT); 
//              setIcon(imageIconArray[column]);	// Set icons for cells in some columns
// 				setIconTextGap(15);		// Set the distance between icon and the actual data value
 				
                return this;
            }
        };
        
        
        for (int i=0; i<columnNames6.length; i++) {
        	table6.getColumnModel().getColumn(i).setCellRenderer(r2);
        }

        //Set toolTip for Column header
        JTableHeader header = table6.getTableHeader();
       

        
//        table6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table6.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table6.getTableHeader().setReorderingAllowed(false);		//Disable columns move
        table6.getColumnModel().getColumn(0).setPreferredWidth(300);	//Set width of 1st Column bigger
        
//        table6.setTableHeader(null);
        table6.setPreferredScrollableViewportSize(new Dimension(400, 120));
        table6.setFillsViewportHeight(true);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void create_table7() {
		read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
		List<List<String>> allLayers = read_Identifiers.get_allLayers();
		List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();
		int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		
		
		//Setup the table------------------------------------------------------------	
		if (is_table7_loaded == false) { // Create a fresh new if Load fail				
			rowCount7 = total_CoverType*total_CoverType;
			colCount7 = 4;
			data7 = new Object[rowCount7][colCount7];
	        columnNames7= new String[] {"Cover Type before Stand Replacing Disturbances", "Cover Type after Stand Replacing Disturbances", "Weight of Regenerated Area", "Percentage Equivalent (%)"};
			
			// Populate the data matrix
	        int table_row2 = 0;
			for (int i = 0; i < total_CoverType; i++) {
				for (int j = 0; j < total_CoverType; j++) {
					data7[table_row2][0] = allLayers.get(4).get(i);
					data7[table_row2][1] = allLayers.get(4).get(j);	
					if (i==j) data7[table_row2][2] = 1; else data7[table_row2][2] = 0;
					if (i==j) data7[table_row2][3] = 100.0; else data7[table_row2][3] = 0.0;
					table_row2++;
				}
			}			
		}
			
		
		//Create a table-------------------------------------------------------------		
        model7 = new TableModelSpectrum(rowCount7, colCount7, data7, columnNames7) {
        	@Override
        	public Class getColumnClass(int c) {
    			if (c==0 || c==1) return String.class;      //column 0 and 1 accept only String
    			else if (c==2) return Integer.class;      //column 2 accept only Integer values 
    			else if (c==3) return Double.class;      //column 3 (last column) accept only Double values    
    	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
    		}

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
    			if (col == 2 && (((Number) value).intValue() < 0 || ((Number) value).intValue() > 1000)) {
    				JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
    						"Your input has not been accepted. Only integer values in the range 0-1000 would be allowed.");
    			} else {
    				data7[row][col] = value;
    				
    				read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
    				List<List<String>> allLayers =  read_Identifiers.get_allLayers();
    				int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
    				int table_row=0;
    				double[] total_Weight = new double[total_CoverType];
    				
    				//calculate totalWeight
    				for (int i = 0; i < total_CoverType; i++) {
    					total_Weight[i] = 0;
    					for (int j = 0; j < total_CoverType; j++) {					
    						total_Weight[i] = total_Weight[i] + Double.parseDouble(data7[table_row][2].toString());						
    						table_row++;
    					}	
    				}
    				
    				//Calculate and write percentage
    				table_row=0;
    				for (int i = 0; i < total_CoverType; i++) {
    					for (int j = 0; j < total_CoverType; j++) {					
    						data7[table_row][3]	= Double.parseDouble(data7[table_row][2].toString())/total_Weight[i]*100;			
    						table_row++;
    					}	
    				}
    				
    				
    				fireTableDataChanged();
    			}
    		}       	
        };
        
        
        
        table7 = new JTable(model7){
             //Implement table cell tool tips           
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
				
				if (colIndex == 2) {
					try {
						tip = "Weight of the lost area with cover type "+ getValueAt(rowIndex, 0).toString() 
								+ " to be regenerated as cover type " + getValueAt(rowIndex, 1).toString();
					} catch (RuntimeException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
				}	
				
				return tip;
			}
		};			
        
		
		
		// Define a set of icon for some columns
		icon = new ImageIcon(getClass().getResource("/icon_main.png"));
		scaleImage = icon.getImage().getScaledInstance(3, 3, Image.SCALE_SMOOTH);
		ImageIcon icon_scale = new ImageIcon(scaleImage);

		ImageIcon[] imageIconArray = new ImageIcon[colCount7];
		for (int i = 0; i < colCount7; i++) {
			if (i == 2) {
				imageIconArray[i] = icon_scale;
			}
		}
		
		
		// Define a set of background color for all rows
		Color[] rowColor = new Color[rowCount7];
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
				setIcon(imageIconArray[column]);	// Set icons for cells in some columns
				setIconTextGap(15);		// Set the distance between icon and the actual data value

				
//				int[] rowAlignment = new int[rowCount5];
//				int currentAlignment = SwingConstants.CENTER;
//				int rCount2 = 0;
//
//				for (int i = 0; i < total_CoverType; i++) {
//					if (currentAlignment == SwingConstants.CENTER) {
//						currentAlignment = SwingConstants.LEFT;
//					} else {
//						currentAlignment = SwingConstants.CENTER;
//					}
//					for (int j = 0; j < total_SizeClass; j++) {
//						rowAlignment[rCount2] = currentAlignment;
//						rCount2++;
//					}
//				}
//				setHorizontalAlignment(rowAlignment[row]);
                return this;
            }
        };						
			
		
		for (int i = 0; i < columnNames7.length; i++) {
			table7.getColumnModel().getColumn(i).setCellRenderer(r);
		}		
		
		
//      table7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table7.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table7.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//      table7.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
        
//      table7.setTableHeader(null);
        table7.setPreferredScrollableViewportSize(new Dimension(400, 120));
        table7.setFillsViewportHeight(true);
	}
	
	
	
	
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------  
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------	
	
    
    
    
    
    
	// Panel General Inputs------------------------------------------------------------------------------------------------------	
	class General_Inputs_GUI extends JLayeredPane {
		public General_Inputs_GUI() {
			setLayout(new GridLayout(0,4,30,0));		//2 last numbers are the gaps 			
			
			JLabel label1 = new JLabel("Total planning periods (decades)");
			JComboBox combo1 = new JComboBox();		
			for (int i = 1; i <= 50; i++) {
				combo1.addItem(i);
			}
			combo1.setSelectedItem((int) 5);
			super.add(label1);
			super.add(combo1);
			
			JLabel label2 = new JLabel("Solving time limit (minutes)");
			JSpinner spin2 = new JSpinner (new SpinnerNumberModel(15, 0, 60, 1));
			JFormattedTextField SpinnerText = ((DefaultEditor) spin2.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			super.add(label2);
			super.add(spin2);
			
			JLabel label3 = new JLabel("Annual discount rate (%)");
			JComboBox combo3 = new JComboBox();		
			for (int i = 0; i <= 100; i++) {
				double value = (double) i/10;
				combo3.addItem(value);
			}
			combo3.setSelectedItem((double) 3.5);
			super.add(label3);
			super.add(combo3);
			
			JLabel label4 = new JLabel("Solver for optimization");
			JComboBox  combo4 = new JComboBox();
			combo4.addItem("CPLEX");
			combo4.addItem("LPSOLVE");
			combo4.addItem("CBC");
			combo4.addItem("CLP");
			combo4.addItem("GUROBI");
			combo4.addItem("GLPK");
			combo4.addItem("SPCIP");
			combo4.addItem("SOPLEX");
			combo4.addItem("XPRESS");	
			super.add(label4);
			super.add(combo4);
			
			
			Action apply = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					totalPeriod = Integer.parseInt(combo1.getSelectedItem().toString());
					
					// Apply any change in the GUI to the table
					data1[0][0] = "Total planning periods (decades)";
					data1[1][0] = "Solving time limit (minutes)";
					data1[2][0] = "Annual discount rate (%)";
					data1[3][0] = "Solver for optimization";
					
					data1[0][1] = combo1.getSelectedItem().toString();
					data1[1][1] = (Integer)spin2.getValue();
					data1[2][1] = combo3.getSelectedItem().toString();
					data1[3][1] = combo4.getSelectedItem().toString();
					model1.fireTableDataChanged();

				}
			};
			
			
			//Load info from input to GUI
			if (is_table1_loaded == true) {
				if (! String.valueOf(data1[0][1]).equals("null")) {
					combo1.setSelectedItem(Integer.valueOf((String) data1[0][1]));
					totalPeriod = Integer.valueOf((String) data1[0][1]);
				}
				if (! String.valueOf(data1[1][1]).equals("null")) spin2.setValue(Integer.valueOf((String) data1[1][1]));
				if (! String.valueOf(data1[2][1]).equals("null")) combo3.setSelectedItem(Double.valueOf((String) data1[2][1]));
				if (! String.valueOf(data1[3][1]).equals("null")) combo4.setSelectedItem(String.valueOf(data1[3][1]));
			}
			
			
			combo1.addActionListener(apply);
			combo3.addActionListener(apply);
			combo4.addActionListener(apply);
			
			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    spin2.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	spin2.setValue(spin2.getValue());
		        	
		        	// Apply any change in the GUI to the table
					data1[0][0] = "Total planning periods (decades)";
					data1[1][0] = "Solving time limit (minutes)";
					data1[2][0] = "Annual discount rate (%)";
					data1[3][0] = "Solver for optimization";
					
					data1[0][1] = combo1.getSelectedItem().toString();
					data1[1][1] = (Integer)spin2.getValue();
					data1[2][1] = combo3.getSelectedItem().toString();
					data1[3][1] = combo4.getSelectedItem().toString();
					model1.fireTableDataChanged();
		        }
		    });
		}
	}

	class General_Inputs_Text extends JScrollPane {
		public General_Inputs_Text() {	
			create_table1();
	        setViewportView(table1);
		}
	}

	
	// Panel Model_Identifiniton--------------------------------------------------------------------------------------------------
	class Model_Identifiniton_GUI extends JLayeredPane implements ItemListener {
		// Define 28 check box for 6 layers
		List<List<JCheckBox>> checkboxFilter;
		
		public Model_Identifiniton_GUI() {
			setLayout(new GridBagLayout());
			
		    // 1st grid -----------------------------------------------------------------------
		 	// 1st grid -----------------------------------------------------------------------
			JPanel importPanel = new JPanel();
			TitledBorder border0 = new TitledBorder("Import Files");
			border0.setTitleJustification(TitledBorder.CENTER);
			importPanel.setBorder(border0);
			importPanel.setLayout(new GridBagLayout());
			GridBagConstraints c0 = new GridBagConstraints();
			c0.fill = GridBagConstraints.HORIZONTAL;
			c0.weightx = 1;
			c0.weighty = 1;
			
		       
		 // 1st grid line 0----------------------------
			JLabel label0 = new JLabel("Strata Definition (.csv)");
			c0.gridx = 0;
			c0.gridy = 0;
			c0.weightx = 0.1;
			c0.weighty = 1;
			importPanel.add(label0, c0);

			JTextField textField0 = new JTextField(25);
			textField0.setEditable(false);
			textField0.setText(currentDefinition_location);
			c0.gridx = 1;
			c0.gridy = 0;
			c0.weightx = 1;
			c0.weighty = 1;
			importPanel.add(textField0, c0);

			JButton button0 = new JButton();
			button0.setToolTipText("Import Definition");
			icon = new ImageIcon(getClass().getResource("/icon_import.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			button0.setIcon(new ImageIcon(scaleImage));
			button0.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					File tempDefinitionFile = FilesHandle.chosenDefinition();	
					if (tempDefinitionFile != null) {	//Only change StrataDefinition if the FileChooser return a file that is not Null
						file_StrataDefinition = tempDefinitionFile;
						currentDefinition_location = file_StrataDefinition.getAbsolutePath();
						
						//create 4 new instances of the 2 Panels 
						is_table_loaded = false;
						is_table1_loaded = false;
						is_table2_loaded = false;
						is_table3_loaded = false;
						is_table4_loaded = false;
						is_table5_loaded = false;
						is_table6_loaded = false;
						is_table7_loaded = false;
						
						
						//Reset all panels except General Inputs
						panel_Model_Identifiniton_GUI = new Model_Identifiniton_GUI();
						panel_Model_Identification_Text = new Model_Identification_Text();
						panel_CovertypeConversion_GUI = new CovertypeConversion_GUI();
						panel_CovertypeConversion_Text = new CovertypeConversion_Text();
						panel_Disturbances_GUI = new Disturbances_GUI();
						panel_Disturbances_Text = new Disturbances_Text();
						panel_Management_Cost_GUI = new Management_Cost_GUI();
						panel_Management_Cost_Text = new Management_Cost_Text();
						panel_UserConstraints_GUI = new UserConstraints_GUI();
						panel_UserConstraints_Text = new UserConstraints_Text();
						
						//and show the 2 new instances of Model_Definition Panel
						GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
						GUI_Text_splitPanel.setRightComponent(panel_Model_Identification_Text);	
					}
				}
			});
			c0.gridx = 2;
			c0.gridy = 0;
			c0.weightx = 0;
			c0.weighty = 1;
			importPanel.add(button0, c0);

				
			// 1st grid line 2----------------------------
			JLabel label2 = new JLabel("Database (.db)");
			c0.gridx = 0;
			c0.gridy = 2;
			c0.weightx = 0.1;
		    c0.weighty = 1;
			importPanel.add(label2, c0);

			JTextField textField2 = new JTextField(25);
			textField2.setEditable(false);
			c0.gridx = 1;
			c0.gridy = 2;
			c0.weightx = 1;
		    c0.weighty = 1;
			importPanel.add(textField2, c0);

			
			button_import_database = new JButton();
			button_import_database.setToolTipText("Import Database");
			icon = new ImageIcon(getClass().getResource("/icon_import.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			button_import_database.setIcon(new ImageIcon(scaleImage));			
			button_import_database.setEnabled(false);
			button_import_database.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (is_this_the_first_load == false) file_Database = FilesHandle.chosenDatabase();				
					if (file_Database!=null) {
						textField2.setText(file_Database.getAbsolutePath());
							
						// Read the database tables into array
						read_DatabaseTables = new Read_DatabaseTables(file_Database);
						yieldTable_values = read_DatabaseTables.getTableArrays();
						yieldTable_ColumnNames = read_DatabaseTables.getTableColumnNames();
										        
				        //Update Age Class column of the existing strata table
						for (int row = 0; row < rowCount; row++) {						
							String s5 = data[row][5].toString();
							String s6 = data[row][6].toString();
							data[row][colCount - 2] = read_DatabaseTables.get_stratingAgeClass(s5, s6, "A", "0");							
							model.fireTableDataChanged();
						}
				      
						 //Update Models OverView table
				        data3[2][1] = yieldTable_values.length;
				        model3.fireTableDataChanged();
				        
				        int total_yieldtable =0;
				        for (int row = 0; row < rowCount; row++) {				        	
				        	if (data[row][colCount -2].toString().equals("not found"))		total_yieldtable = total_yieldtable +1;
						}
				        data3[3][1] = total_yieldtable;
				        model3.fireTableDataChanged();
				        
						
						//create 2 new instances of this Panel 
						panel_UserConstraints_GUI = new UserConstraints_GUI();
						panel_UserConstraints_Text = new UserConstraints_Text();
					}
				}
			});
			c0.gridx = 2;
			c0.gridy = 2;
			c0.weightx = 0;
		    c0.weighty = 1;
			importPanel.add(button_import_database, c0);
		 			
					
			// 1st grid line 1----------------------
			JLabel label1 = new JLabel("Existing Strata (.csv)");
			c0.gridx = 0;
			c0.gridy = 1;
			c0.weightx = 0.1;
		    c0.weighty = 1;
			importPanel.add(label1, c0);
			
			JTextField textField1 = new JTextField(25);
			textField1.setEditable(false);
			c0.gridx = 1;
			c0.gridy = 1;
			c0.weightx = 1;
		    c0.weighty = 1;
			importPanel.add(textField1, c0);
			
			
			button_import_existingStrata = new JButton();
			button_import_existingStrata.setToolTipText("Import Strata");
			icon = new ImageIcon(getClass().getResource("/icon_import.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			button_import_existingStrata.setIcon(new ImageIcon(scaleImage));
			button_import_existingStrata.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (is_this_the_first_load == false) file_ExistingStrata = FilesHandle.chosenStrata();				
					if (file_ExistingStrata!=null) {
						textField1.setText(file_ExistingStrata.getAbsolutePath());
						
						//Read the whole text file into table
						read_Strata = new Read_Strata();
						read_Strata.readValues(file_ExistingStrata);
						String[][] value = read_Strata.getValues();
						rowCount = read_Strata.get_TotalRows();	//Total rows of existing strata
//						colCount = read_Strata.get_TotalColumns() + 2; //the "Age Class" & "Strata in optimization model" Columns add to the total of existing strata columns
//						table.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns shown based on existing strata total columns
						data = new Object[rowCount][colCount];
						for (int row = 0; row < rowCount; row++) {
							for (int column = 0; column < read_Strata.get_TotalColumns() - 1; column++) {		//loop all existing strata columns, except the last column
								data[row][column] = value[row][column];
							}
							data[row][colCount - 3] = value[row][read_Strata.get_TotalColumns() - 1];	//the last column is "Total acres"
						}
						
						model.match_DataType();			//a smart way to retrieve the original data type :))))))
						model.fireTableDataChanged();
						model.updateTableModelSpectrum(rowCount, colCount, data, columnNames);		//Very important to (pass table info back to table model)
								         						
						//Only add sorter after having the data loaded
						TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
						table.setRowSorter(sorter);
				                  
				        //Update Models OverView table
				        data3[0][1] = "0 vs " + rowCount;
				        model3.fireTableDataChanged();
				        
				        availableAcres = 0;
				        for (int row = 0; row < rowCount; row++) {
				        	availableAcres = availableAcres + Double.parseDouble(data[row][colCount - 3].toString());
						}
				        data3[1][1] = "0 vs " + availableAcres;
				        model3.fireTableDataChanged();
						
				        
				        //Enable "Import Database"
				        textField2.setText(null);
				        button_import_database.setEnabled(true);
					}
				}
			});
			c0.gridx = 2;
			c0.gridy = 1;
			c0.weightx = 0;
		    c0.weighty = 1;
			importPanel.add(button_import_existingStrata, c0);
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------
			
				
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------						
			read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
			
			List<String> layers_Title = read_Identifiers.get_layers_Title();
			List<String> layers_Title_ToolTip = read_Identifiers.get_layers_Title_ToolTip();
			List<List<String>> allLayers =  read_Identifiers.get_allLayers();
			List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();

			int total_layers = allLayers.size();
			int total_layers_ToolTips = allLayers_ToolTips.size();
		
			
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
			create_table3();        
	        JScrollPane overviewScrollPane = new JScrollPane();
	        overviewScrollPane.setViewportView(table3);
	        
		    c2.gridx = 0;
			c2.gridy = 0;
			c2.gridwidth = 2;
			c2.weightx = 1;
		    c2.weighty = 1;
			inforPanel.add(overviewScrollPane, c2);

			
			// 2nd line inside inforPanel includes 2 buttons
			//button 1		
			button_select_Strata = new JButton();
			button_select_Strata.setToolTipText("Add the selected existing strata into optimization model");
			icon = new ImageIcon(getClass().getResource("/icon_select.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			button_select_Strata.setIcon(new ImageIcon(scaleImage));
			button_select_Strata.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					String applyText = "Yes";				
					
					int[] selectedRow = table.getSelectedRows();	
					///Convert row index because "Sort" causes problems
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
					}
					table.clearSelection();	//To help trigger the row refresh: clear then add back the rows
					for (int i: selectedRow) {
						data[i][colCount-1] = applyText;
						table.addRowSelectionInterval(table.convertRowIndexToView(i),table.convertRowIndexToView(i));
					}	
					
					
					 //Update Models OverView table
					int modeledStrata = 0;
					for (int row = 0; row < rowCount; row++) {
						if (data[row][colCount -1]!=null && data[row][colCount -1].toString().equals("Yes"))	modeledStrata = modeledStrata + 1;
					}
			        data3[0][1] = modeledStrata + " vs " + rowCount;
			        model3.fireTableDataChanged();
			        
			        
			        modeledAcres = 0;
			        for (int row = 0; row < rowCount; row++) {
			        	if (data[row][colCount -1]!=null && data[row][colCount -1].toString().equals("Yes"))	modeledAcres = modeledAcres + Double.parseDouble(data[row][colCount - 3].toString());
					}
			        data3[1][1] = modeledAcres + " vs " + availableAcres;
			        model3.fireTableDataChanged();
				}
			});
			c2.gridx = 0;
			c2.gridy = 1;
			c2.gridwidth = 1;	
			c2.weightx = 1;
		    c2.weighty = 0;
			inforPanel.add(button_select_Strata, c2);
			
			//button 2
			JButton remove_Strata = new JButton();
			remove_Strata.setToolTipText("Remove the selected existing strata from optimization model");
			icon = new ImageIcon(getClass().getResource("/icon_deselect.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			remove_Strata.setIcon(new ImageIcon(scaleImage));
			remove_Strata.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					String applyText = "";				
					
					int[] selectedRow = table.getSelectedRows();	
					///Convert row index because "Sort" causes problems
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
					}
					table.clearSelection();	//To help trigger the row refresh: clear then add back the rows
					for (int i: selectedRow) {
						data[i][colCount-1] = applyText;
						table.addRowSelectionInterval(table.convertRowIndexToView(i),table.convertRowIndexToView(i));
					}	
					
					
					 //Update Models OverView table
					int modeledStrata = 0;
					for (int row = 0; row < rowCount; row++) {
						if (data[row][colCount -1]!=null && data[row][colCount -1].toString().equals("Yes"))	modeledStrata = modeledStrata + 1;
					}
			        data3[0][1] = modeledStrata + " vs " + rowCount;
			        model3.fireTableDataChanged();
			        
			        
			        modeledAcres = 0;
			        for (int row = 0; row < rowCount; row++) {
			        	if (data[row][colCount -1]!=null && data[row][colCount -1].toString().equals("Yes"))	modeledAcres = modeledAcres + Double.parseDouble(data[row][7].toString());
					}
			        data3[1][1] = modeledAcres + " vs " + availableAcres;
			        model3.fireTableDataChanged();
				}
			});
			c2.gridx = 1;
			c2.gridy = 1;
			c2.gridwidth = 1;	
			c2.weightx = 1;
		    c2.weighty = 0;
			inforPanel.add(remove_Strata, c2);		
			// End of 3rd grid -----------------------------------------------------------------------
			// End of 3rd grid -----------------------------------------------------------------------
			
			
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
		    c.weighty = 1;
		    	    
		    // Add the 1st grid - importPanel to the main Grid
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1; 
			super.add(importPanel, c);
			
			// Add the 2nd grid - checkPanel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			super.add(checkPanel, c);
			
			// Add the 3rd grid - inforPanel to the main Grid	
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 2;
			super.add(inforPanel, c);
		}
		
		
		//Listeners for checkBox Filter--------------------------------------------------------------------
		public void itemStateChanged(ItemEvent e) {

			if (data != null) {		//Only allow sorter if the data of existing strata is loaded
				//This help filter to get the strata as specified by the CheckBoxes
				TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
				table.setRowSorter(sorter);
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
					
			create_table();
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane);      
	     }
	}
	

	
	
	// Panel Covertype Conversion------------------------------------------------------------------------------------------------
	class CovertypeConversion_GUI extends JLayeredPane {
		public CovertypeConversion_GUI() {
			setLayout(new GridBagLayout());


			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
	        create_table4();
	        //Put table4 into CovertypeConversion_EA_ScrollPane
	        JScrollPane CovertypeConversion_EA_ScrollPane = new JScrollPane();
	        TitledBorder border2 = new TitledBorder("Requirements of Even Age Method: Cover Type Conversion & Rotation Age-Class");
			border2.setTitleJustification(TitledBorder.CENTER);
			CovertypeConversion_EA_ScrollPane.setBorder(border2);
	        CovertypeConversion_EA_ScrollPane.setViewportView(table4);									
		    
		    
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
	        create_table7();
	        //Put table7 into CovertypeConversion_SRD_ScrollPane
			JScrollPane CovertypeConversion_SRD_ScrollPane = new JScrollPane();
			TitledBorder border3 = new TitledBorder("Requirements of Stand Replacing Disturbances: Cover Type Conversion & Proportion (%)");
			border3.setTitleJustification(TitledBorder.CENTER);
			CovertypeConversion_SRD_ScrollPane.setBorder(border3);
	        CovertypeConversion_SRD_ScrollPane.setViewportView(table7);						
			
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
		    c.weighty = 1;

			
			// Add the 1st grid - CovertypeConversion_EA_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(CovertypeConversion_EA_ScrollPane, c);
			
			// Add the 1st grid - CovertypeConversion_SRD_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(CovertypeConversion_SRD_ScrollPane, c);
		}
	}
	
	class CovertypeConversion_Text extends JLayeredPane {
	    public CovertypeConversion_Text() {

	    	
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
	        JScrollPane MixedFire_ScrollPane = new JScrollPane();
	    	TitledBorder border2 = new TitledBorder("Specify the proportion of exsiting strata area (%) sufferred from Mixed Severity Wildfire across all time periods");
			border2.setTitleJustification(TitledBorder.CENTER);
			MixedFire_ScrollPane.setBorder(border2);
	        MixedFire_ScrollPane.setViewportView(table5);			
			
		    
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
	        create_table6();
	        //Put table6 into StandReplacing_ScrollPane
	        JScrollPane StandReplacing_ScrollPane = new JScrollPane();
	        TitledBorder border3 = new TitledBorder("Specify the proportion of strata area (%) lost due to Stand Replacing Disturbances in each time period");
			border3.setTitleJustification(TitledBorder.CENTER);
			StandReplacing_ScrollPane.setBorder(border3);
	        StandReplacing_ScrollPane.setViewportView(table6);
			
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
		    c.weighty = 1;

			
			// Add the 1st grid - MixedFire_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(MixedFire_ScrollPane, c);
			
			// Add the 2nd grid - StandReplacing_ScrollPane to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(StandReplacing_ScrollPane, c);
		}

	}

		
	class Disturbances_Text extends JLayeredPane {
		public Disturbances_Text() {

		}
	}
	
	
	// Panel Management_Cost------------------------------------------------------------------------------------------------------	
	class Management_Cost_GUI extends JLayeredPane {
		public Management_Cost_GUI() {
			
			// This all_actions List contains all actions loaded from yield tables------------------------------------------------------------
			List<String> all_actions = new ArrayList<String>();
			all_actions.add("Pre-Commercial Thin Type 1");
			all_actions.add("Pre-Commercial Thin Type 2");
			all_actions.add("Pre-Commercial Thin Type 3");
			all_actions.add("Commercial Thin Type 1");
			all_actions.add("Commercial Thin Type 2");
			all_actions.add("Commercial Thin Type 3");
			all_actions.add("Clear Cut Type 1");
			all_actions.add("Clear Cut Type 2");
			all_actions.add("Clear Cut Type 3");
			all_actions.add("Understory Burn Type 1");
			all_actions.add("Understory Burn Type 2");
			all_actions.add("Understory Burn Type 3");
			all_actions.add("Broadcast Burn");
			all_actions.add("Seed Cut");
			all_actions.add("Overstory Removal");
			all_actions.add("Group Openning Type 1");
			all_actions.add("Group Openning Type 2");
			all_actions.add("Group Openning Type 3");
			all_actions.add("Single Tree Openning");
			all_actions.add("Artificial Regeneration");
			all_actions.add("Tree Planting");
			all_actions.add("Weed Treatments");
		
			JTextArea action_info = new JTextArea();
			action_info.setBackground(new Color(0,0,0,0));
			action_info.setEditable(false);
			action_info.setLineWrap(true);
			action_info.setWrapStyleWord(true);
			action_info.append("SpectrumLite is assuming that it found " + all_actions.size() + 
					" unique management actions across all" /*+ yieldTable_values.length*/ + " yield tables in your database"  + "\n");	
			action_info.append("All Base Costs are set to default values. Please re-define your true costs");	
			
			JScrollPane action_info_scroll = new JScrollPane(action_info); //place the JTextArea in a scroll pane
			action_info_scroll.setBorder(BorderFactory.createEmptyBorder());
			action_info_scroll.setPreferredSize(new Dimension(100, 50));
			// This all_actions List contains all actions loaded from yield tables------------------------------------------------------------

			
		
			
			
			
			
			
			
	
			JSplitPane splitPanel = new JSplitPane();
			// splitPanel.setResizeWeight(0.15);
//			splitPanel.setOneTouchExpandable(true);
			splitPanel.setDividerLocation(250);
			splitPanel.setDividerSize(3);
			// splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

			
			// Left split panel--------------------------------------------------------------------------------
			JScrollPane scrollPane_Left = new JScrollPane();
			TitledBorder border = new TitledBorder("Management Action List");
			border.setTitleJustification(TitledBorder.CENTER);
			scrollPane_Left.setBorder(border);
			scrollPane_Left.setPreferredSize(new Dimension(250, 250));
			splitPanel.setLeftComponent(scrollPane_Left);
			
			// Add all selected Runs to radioPanel and add that panel to scrollPane_Left
			JPanel radioPanel_Left = new JPanel();
			radioPanel_Left.setLayout(new BoxLayout(radioPanel_Left, BoxLayout.Y_AXIS));
			ButtonGroup radioGroup_Left = new ButtonGroup();
			
			JRadioButton[] radioButton_Left  = new JRadioButton[all_actions.size()];
			for (int i = 0; i < all_actions.size(); i++) {
				radioButton_Left[i] = new JRadioButton(all_actions.get(i));
					radioGroup_Left.add(radioButton_Left[i]);
					radioPanel_Left.add(radioButton_Left[i]);
//					radioButton_Left[i].addActionListener(this);
			}
			radioButton_Left[0].setSelected(true);
			scrollPane_Left.setViewportView(radioPanel_Left);					
			
			
			// Right split panel-------------------------------------------------------------------------------
			Panel_Cost[] cost_ScrollPanel = new Panel_Cost[all_actions.size()];
			for (int i = 0; i < all_actions.size(); i++) {
				cost_ScrollPanel[i] = new Panel_Cost(read_Identifiers, yieldTable_ColumnNames);
				JScrollPane scrollPane_Right = new JScrollPane();
				TitledBorder border2 = new TitledBorder("Cost Definition - " + all_actions.get(i));
				border2.setTitleJustification(TitledBorder.CENTER);
				cost_ScrollPanel[i].setBorder(border2);
				cost_ScrollPanel[i].setPreferredSize(new Dimension(400, 250));
			}
			splitPanel.setRightComponent(cost_ScrollPanel[0]);
			
			
		
			//Listeners for radios----------------------------------------------------------------------------
			for (int i = 0; i < all_actions.size(); i++) {
				int currentAction = i;
				radioButton_Left[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						if (radioButton_Left[currentAction].isSelected()) {
							splitPanel.setRightComponent(cost_ScrollPanel[currentAction]);
						}			
					}
				});
			}
		
		

			// Add all components to JInternalFrame------------------------------------------------------------
			setLayout(new BorderLayout(0, 0));
			add(action_info_scroll, BorderLayout.NORTH);
			add(splitPanel, BorderLayout.CENTER);
		}		
	}

	class Management_Cost_Text extends JTextArea {
		public Management_Cost_Text() {		
			
		}
	}	
	
	

	// Panel User Constraints--------------------------------------------------------------------------------------------------------
	class UserConstraints_GUI extends JLayeredPane implements ActionListener {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
		ScrollPane_Parameters parametersScrollPanel;
		ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
		
		public UserConstraints_GUI() {
			setLayout(new GridBagLayout());
			
			
			// 1st grid ------------------------------------------------------------------------------			
			ScrollPane_StaticIdentifiers identifiersScrollPanel = new ScrollPane_StaticIdentifiers(file_StrataDefinition);
			checkboxStaticIdentifiers = identifiersScrollPanel.get_CheckboxStaticIdentifiers();
			// End of 1st grid -----------------------------------------------------------------------

							
			// 2nd grid ------------------------------------------------------------------------------
			parametersScrollPanel = new ScrollPane_Parameters(read_Identifiers, yieldTable_ColumnNames);		// "Get parameters from YT columns"
			TitledBorder border2 = new TitledBorder("PARAMETERS (yield table columns)");
			border2.setTitleJustification(TitledBorder.CENTER);
			parametersScrollPanel.setBorder(border2);
	    	parametersScrollPanel.setPreferredSize(new Dimension(250, 100));
			// End of 2nd grid -----------------------------------------------------------------------
			
	    	
			// 4th Grid ------------------------------------------------------------------------------	
			dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(2, read_DatabaseTables, read_Identifiers,
					yieldTable_ColumnNames, yieldTable_values);	// "Get identifiers from yield table columns"
			// End of 4th Grid -----------------------------------------------------------------------
				

			// 3rd Grid -----------------------------------------------------------------------
			// 3rd Grid -----------------------------------------------------------------------						
			JPanel buttonPanel = new JPanel(new BorderLayout(0, 0));
			buttonPanel.setPreferredSize(new Dimension(250, 40));;
			JButton addBtn = new JButton();
			addBtn.setFont(new Font(null, Font.BOLD, 14));
			addBtn.setText("SET CONSTRAINTS INFO");
			addBtn.setToolTipText("Apply information of static identifiers, parameters, and dynamic idetifiers to the selected rows (or constraints)");
			
			addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {		
					if (parametersScrollPanel.get_checkboxNoParameter() != null) {		//only allow this "SET INFO" when checkBoxes are already created
						
						int[] selectedRow = table2.getSelectedRows();	
						///Convert row index because "Sort" causes problems
						for (int i = 0; i < selectedRow.length; i++) {
							selectedRow[i] = table2.convertRowIndexToModel(selectedRow[i]);
						}
						table2.clearSelection();	//To help trigger the row refresh: clear then add back the rows
						for (int i: selectedRow) {
							
							//add constraint info at column 6 "PARAMETERS"
							String parameterConstraintColumn = "";
							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {
								if (parametersScrollPanel.get_checkboxParameter().get(j).isSelected()) {			//add the index of selected Columns to this String
									parameterConstraintColumn = parameterConstraintColumn + j + " ";
								}
							}
							
							if (parameterConstraintColumn.equals("") || parametersScrollPanel.get_checkboxNoParameter().isSelected()) {
								parameterConstraintColumn = "NoParameter";		//= parametersScrollPanel.checkboxNoParameter.getText();
							}
							
							data2[i][6] = parameterConstraintColumn;
							
							
							//add constraint info at column 7 "Static Identifiers"
							String staticIdentifiersColumn = "";
							for (int ii = 0; ii < checkboxStaticIdentifiers.size(); ii++) {		//Loop all static identifiers
								staticIdentifiersColumn = staticIdentifiersColumn + ii + " ";
								for (int j = 0; j < checkboxStaticIdentifiers.get(ii).size(); j++) {		//Loop all elements in each layer
									String checkboxName = checkboxStaticIdentifiers.get(ii).get(j).getText();
									if (checkboxName.equals("Even Age")) {
										checkboxName = "EA";
									} else if (checkboxName.equals("Group Selection")) {
										checkboxName = "GS";
									} else if (checkboxName.equals("Prescribed Burn")) {
										checkboxName = "PB";
									} else if (checkboxName.equals("Natural Growth")) {
										checkboxName = "NG";
									}
									
									//Add checkBox if it is (selected & visible) or disable
									if ((checkboxStaticIdentifiers.get(ii).get(j).isSelected() && (checkboxStaticIdentifiers.get(ii).get(j).isVisible())
											|| !checkboxStaticIdentifiers.get(ii).get(j).isEnabled()))	
										staticIdentifiersColumn = staticIdentifiersColumn + checkboxName + " "	;
								}
								staticIdentifiersColumn = staticIdentifiersColumn + ";";
							}	
							data2[i][7] = staticIdentifiersColumn;
							
							
							//add constraint info at column 8 "dynamic Identifiers"
							String dynamicIdentifiersColumn = "";
							for (int ii = 0; ii < dynamic_identifiersScrollPanel.get_allDynamicIdentifiers_ScrollPane().size(); ii++) {		//Loop all dynamic identifier ScrollPanes
								if (dynamic_identifiersScrollPanel.get_allDynamicIdentifiers_ScrollPane().get(ii).isVisible() &&
										dynamic_identifiersScrollPanel.get_CheckboxDynamicIdentifiers().get(ii).size() > 0) {			//get the active identifiers (when identifier ScrollPane is visible and List size >0)
									dynamicIdentifiersColumn = dynamicIdentifiersColumn + ii + " ";
									for (int j = 0; j < dynamic_identifiersScrollPanel.get_CheckboxDynamicIdentifiers().get(ii).size(); j++) { //Loop all checkBoxes in this active identifier
										String checkboxName = dynamic_identifiersScrollPanel.get_CheckboxDynamicIdentifiers().get(ii).get(j).getText();									
										//Add checkBox if it is (selected & visible) or disable
										if ((dynamic_identifiersScrollPanel.get_CheckboxDynamicIdentifiers().get(ii).get(j).isSelected() && (dynamic_identifiersScrollPanel.get_CheckboxDynamicIdentifiers().get(ii).get(j).isVisible())
												|| !dynamic_identifiersScrollPanel.get_CheckboxDynamicIdentifiers().get(ii).get(j).isEnabled()))
											dynamicIdentifiersColumn = dynamicIdentifiersColumn + checkboxName + " ";
									}
									dynamicIdentifiersColumn = dynamicIdentifiersColumn + ";";
								}
							}	
							
							if (dynamicIdentifiersColumn.equals("") || dynamic_identifiersScrollPanel.get_checkboxNoIdentifier().isSelected()) {
								dynamicIdentifiersColumn = "NoIdentifier";			//= dynamic_identifiersScrollPanel.checkboxNoIdentifier.getText();
							}
							
							data2[i][8] = dynamicIdentifiersColumn;
							
							
								
							//To help trigger the row refresh: add back the rows, we cleared them before, now add back
							table2.addRowSelectionInterval(table2.convertRowIndexToView(i),table2.convertRowIndexToView(i));
						}	
					}
				}
			});
			
			buttonPanel.add(addBtn);		
			// End of 3rd Grid -----------------------------------------------------------------------
			// End of 3rd Grid -----------------------------------------------------------------------		    
		    
	

			// 6th Grid -----------------------------------------------------------------------
			// 6th Grid -----------------------------------------------------------------------						
			JPanel buttonClearPanel = new JPanel(new BorderLayout(0, 0));
			buttonClearPanel.setPreferredSize(new Dimension(250, 40));;
			JButton clearBtn = new JButton();
			clearBtn.setFont(new Font(null, Font.BOLD, 14));
			clearBtn.setText("CLEAR CONSTRANTS INFO");
			clearBtn.setToolTipText("Clear all information of the selected rows (or constraints)");
			
			clearBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					int[] selectedRow = table2.getSelectedRows();	
					///Convert row index because "Sort" causes problems
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table2.convertRowIndexToModel(selectedRow[i]);
					}
					table2.clearSelection();	//To help trigger the row refresh: clear then add back the rows
					for (int i: selectedRow) {
						for (int j=0; j < colCount2; j++) {
							data2[i][j] = null;
							table2.addRowSelectionInterval(table2.convertRowIndexToView(i),table2.convertRowIndexToView(i));
						}
					}	
				}
			});
			
			buttonClearPanel.add(clearBtn);					
			// End of 6th Grid -----------------------------------------------------------------------
			// End of 6th Grid -----------------------------------------------------------------------		
			
			    	
	    		
			
			// 5th Grid -----------------------------------------------------------------------
			// 5th Grid -----------------------------------------------------------------------						
			create_table2();
			// Create the scroll pane and add the constraintTablePanel to it.
			JScrollPane constraints_ScrollPane = new JScrollPane();
			TitledBorder border5 = new TitledBorder("Constraints Information");
			border5.setTitleJustification(TitledBorder.CENTER);
			constraints_ScrollPane.setBorder(border5);			
			constraints_ScrollPane.setViewportView(table2);
			// End of 5th Grid -----------------------------------------------------------------------
			// End of 5th Grid -----------------------------------------------------------------------				
			
			

			
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;

			
			// Add IdentifiersPanel to the main Grid
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 0;
			super.add(identifiersScrollPanel, c);				
		    
			// Add dynamic_identifiersPanel to the main Grid
			c.gridx = 2;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
			c.weighty = 0;
			super.add(dynamic_identifiersScrollPanel, c);	
			
			// Add the parametersScrollPanel to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 1;
			super.add(parametersScrollPanel, c);	
		    
		    // Add the buttonPanel to the main Grid	
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(buttonPanel, c);	
			
		    // Add the buttonClearPanel to the main Grid	
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(buttonClearPanel, c);	
		    	    		    
		    // Add the constraints_ScrollPane to the main Grid
			c.gridx = 1;
			c.gridy = 1;
			c.gridwidth = 2; 
			c.gridheight = 3;
			c.weightx = 1;
		    c.weighty = 1;
			super.add(constraints_ScrollPane, c);
			
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
	    	
	       	//Update Parameter Panel
	    	if (yieldTable_ColumnNames != null && parametersScrollPanel.get_checkboxParameter() == null) {
	    		parametersScrollPanel = new ScrollPane_Parameters(read_Identifiers, yieldTable_ColumnNames);	//"Get parameters from YT columns"
	    	}
	    	
	      	//Update Dynamic Identifier Panel
	    	if (yieldTable_ColumnNames != null && dynamic_identifiersScrollPanel.get_allDynamicIdentifiers() == null) {
	    		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(2, read_DatabaseTables, read_Identifiers,
						yieldTable_ColumnNames, yieldTable_values);	// "Get identifiers from yield table columns"
	    	}

	    }
	    
	}

	class UserConstraints_Text  extends JTextArea {
		public UserConstraints_Text() {
			setRows(50);		// set text areas with 10 rows when starts		
		}
	}
		
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	
	//Add all input Files to a list
	public List<File> get_List_Of_inputFiles () {
		List<File> inputFiles_list = new ArrayList<File>();	
		inputFiles_list.add(getGeneralInputFile());
		inputFiles_list.add(getSelectedStrataFile());
		inputFiles_list.add(getRequirementsFile());
		inputFiles_list.add(getMSFireFile());
		inputFiles_list.add(getSRDisturbancesFile());
		inputFiles_list.add(getUserConstraintsFile());
		inputFiles_list.add(getSRDRequirementsFile());
		
		inputFiles_list.add(getDefinitionFile());
		inputFiles_list.add(getStrataFile());
		inputFiles_list.add(getDatabaseFile());
		
		return inputFiles_list;
	}
	

	
	private File getGeneralInputFile() {
		File generalInputFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 1 - General Inputs.txt");
		
		if (data1 != null) {
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
		return generalInputFile;
	}
	
	
	private File getSelectedStrataFile() {
		File selectedStrataFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 2 - Selected Strata.txt");	
		
		if (data != null) {
			//Only print out Strata with implemented methods <> null
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(selectedStrataFile))) {
				for (int j = 0; j < columnNames.length; j++) { //Note: colCount = columnNames.length
					fileOut.write(columnNames[j] + "\t");
				}

				for (int i = 0; i < data.length; i++) {				//Note: String.ValueOf   is so important to get the String from Object
					if (String.valueOf(data[i][colCount - 1]).equals("Yes")) { //IF strata is in optimization model		
						fileOut.newLine();
						for (int j = 0; j < colCount; j++) {
							fileOut.write(data[i][j] + "\t");
						}
					}
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
		return selectedStrataFile;
	}
	
	
	private File getRequirementsFile() {
		//Only print out if the last column Allowed Options <> null
		File requirementsFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 3 - Covertype Conversion (Clear Cuts).txt");
		
		if (data4 != null) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(requirementsFile))) {
				for (int j = 0; j < columnNames4.length; j++) {
					fileOut.write(columnNames4[j] + "\t");
				}

				for (int i = 0; i < data4.length; i++) {
					if (String.valueOf(data4[i][colCount4 - 1]).equals("Yes")) { //IF conversion is selected "Yes"
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
		return requirementsFile;
	}

	
	private File getSRDRequirementsFile() {
		File SRDrequirementsFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 4 - Covertype Conversion (Replacing Disturbances).txt");	
		
		if (data7 != null) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(SRDrequirementsFile))) {
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
		return SRDrequirementsFile;
	}	
	
	
	private File getMSFireFile() {
		File MSFireFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 5 - Mixed Severity Fire.txt");	
		
		if (data5 != null) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(MSFireFile))) {
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
		return MSFireFile;
	}	
	
	
	private File getSRDisturbancesFile() {
		File SRDisturbancesFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 6 - Replacing Disturbances.txt");	
		
		if (data6 != null) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(SRDisturbancesFile))) {
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
		return SRDisturbancesFile;
	}	
	
	
	private File getUserConstraintsFile() {
		File userConstraintsFile = new File(temporary_RunFolder.getAbsolutePath() + "/Input 8 - User Constraints.txt");
		
		if (data2 != null) {
			//Only print out rows if columns  1, 2 or 4, 6, 7, 8 <> null
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userConstraintsFile))) {
				for (int j = 0; j < columnNames2.length; j++) {
					fileOut.write(columnNames2[j] + "\t");
				}
				
				for (int i = 0; i < data2.length; i++) {
					boolean checkValidity = true;
					for (int j = 0; j < colCount2; j++) {
						if (j == 1 || (j == 2 && j == 4) || j == 6 || j == 7 || j == 8) {
							if (data2[i][j] == null || String.valueOf(data2[i][j]).equals(""))
								checkValidity = false;
						}	
					}
					
					if (checkValidity == true) { // if columns  1, 2 or 4, 6, 7, 8 <> null then write to file
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
		return userConstraintsFile;	
	}

	
	private File getDefinitionFile() {	
		File definitionFile = new File(temporary_RunFolder.getAbsolutePath() + "/" + "StrataDefinition.csv");
		
		try {
			if (file_StrataDefinition != null) Files.copy(file_StrataDefinition.toPath(), definitionFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return definitionFile;	
	}	

	
	private File getStrataFile() {	
		File strataFile = new File(temporary_RunFolder.getAbsolutePath() + "/" + "existingStrata.csv");
		
		try {
			if (file_ExistingStrata != null) Files.copy(file_ExistingStrata.toPath(), strataFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return strataFile;	
	}
	
	
	private File getDatabaseFile() {	
		File databaseFile = new File(temporary_RunFolder.getAbsolutePath() + "/" + "database.db");
		
		try {
			if (file_Database != null) Files.copy(file_Database.toPath(), databaseFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return databaseFile;	
	}	
}
