package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

import spectrumGUI.Spectrum_Main;
import spectrumYieldProject.Panel_SolveRun.MyTableModel;

public class Panel_EditRun_Details extends JLayeredPane implements ActionListener {
	private JSplitPane GUI_Text_splitPanel ;
	private JPanel radioPanel_Right; 
	private ButtonGroup radioGroup_Right; 
	private JRadioButton[] radioButton_Right; 
	private File file_ExistingStrata, file_Database;
	private File file_StrataDefinition;
	private String newDefinition = "currently set to Default with 6 Layers";
	
	//6 panels for the selected Run
	private PaneL_General_Inputs_GUI panelInput1_GUI;
	private PaneL_General_Inputs_Text panelInput1_TEXT;
	private PaneL_Model_Identifiniton_GUI panelInput2_GUI;
	private PaneL_Model_Identification_Text panelInput2_TEXT;
	private PaneL_Universal_Requiements_GUI panelInput3_GUI;
	private PaneL_Universal_Requiements_Text panelInput3_TEXT;
	private PaneL_Disturbances_GUI panelInput4_GUI;
	private PaneL_Disturbances_Text panelInput4_TEXT;
	private PaneL_UserConstraints_GUI panelInput5_GUI;
	private PaneL_UserConstraints_Text panelInput5_TEXT;		

	private ImageIcon icon;
	private Image scaleImage;
	
	private Read_Strata read_Strata;
	private Read_DatabaseTables read_DatabaseTables;
	private Read_Indentifiers read_Identifiers;
	
	private Object[][][] yieldTable_values;
	private String [] yieldTable_ColumnNames;
	
	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private MyTableModel model;
	private Object[][] data;

	
	private JCheckBox[][] ConversionCheck_To;
	
	
	private int rowCount2, colCount2;
	private String[] columnNames2;
	private JTable table2;
	private MyTableModel2 model2;
	private Object[][] data2;

	
	//Jtable Models OverView (in input 1)
	private int rowCount3, colCount3;
	private String[] columnNames3;
	private JTable table3;
	private MyTableModel3 model3;
	private Object[][] data3;
	private double modeledAcres, availableAcres;
	
	//Jtable EA requirements
	private int rowCount4, colCount4;
	private String[] columnNames4;
	private JTable table4;
	private MyTableModel4 model4;
	private Object[][] data4;
	
	//Jtable SRD requirements
	private int rowCount7, colCount7;
	private String[] columnNames7;
	private JTable table7;
	private MyTableModel7 model7;
	private Object[][] data7;

	//Jtable MixedFire
	private int rowCount5, colCount5;
	private String[] columnNames5;
	private JTable table5;
	private MyTableModel5 model5;
	private Object[][] data5;
	
	//Jtable Stand Replacing Disturbances
	private int rowCount6, colCount6;
	private String[] columnNames6;
	private JTable table6;
	private MyTableModel6 model6;
	private Object[][] data6;
	
	
	public Panel_EditRun_Details() {
		super.setLayout(new BorderLayout());

		// get the "StrataDefinition.csv" file from where this class is located
		try {
			file_StrataDefinition = new File("StrataDefinition.csv");	
			
			InputStream initialStream = getClass().getResourceAsStream("/StrataDefinition.csv");		//Default definition
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);
			
			OutputStream outStream = new FileOutputStream(file_StrataDefinition);
			outStream.write(buffer);

			initialStream.close();
			outStream.close();
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
		}

		
		// Add 3 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
		radioPanel_Right = new JPanel();
		radioPanel_Right.setLayout(new FlowLayout());		
		radioGroup_Right = new ButtonGroup();
		
		radioButton_Right  = new JRadioButton[5];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[1]= new JRadioButton("Model Identification");
		radioButton_Right[2]= new JRadioButton("Universal Requirements");
		radioButton_Right[3]= new JRadioButton("Natural Disturbances");
		radioButton_Right[4]= new JRadioButton("User Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < radioButton_Right.length; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
				radioButton_Right[i].addActionListener(this);
		}	
		
		GUI_Text_splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_splitPanel.setDividerSize(5);
		GUI_Text_splitPanel.setEnabled(false);
		GUI_Text_splitPanel.setLeftComponent(null);
		GUI_Text_splitPanel.setRightComponent(null);
			
	
		// Create all new 6 panels for the selected Run--------------------------------------------------
		panelInput1_GUI = new PaneL_General_Inputs_GUI();
		panelInput1_TEXT = new PaneL_General_Inputs_Text();
		panelInput2_GUI = new PaneL_Model_Identifiniton_GUI();
		panelInput2_TEXT = new PaneL_Model_Identification_Text();
		panelInput3_GUI = new PaneL_Universal_Requiements_GUI();
		panelInput3_TEXT = new PaneL_Universal_Requiements_Text();
		panelInput4_GUI = new PaneL_Disturbances_GUI();
		panelInput4_TEXT = new PaneL_Disturbances_Text();
		panelInput5_GUI = new PaneL_UserConstraints_GUI();
		panelInput5_TEXT = new PaneL_UserConstraints_Text();
					
		
		// Show the 2 panelInput of the selected Run
		GUI_Text_splitPanel.setLeftComponent(panelInput1_GUI);
		GUI_Text_splitPanel.setRightComponent(panelInput1_TEXT);	
		
		
		// Add all components to The Panel------------------------------------------------------------
		super.add(radioPanel_Right, BorderLayout.NORTH);
		super.add(GUI_Text_splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_EditRun_Details()

	
	// Listener for radio buttons----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
		for (int j = 0; j < radioButton_Right.length; j++) {
			if (radioButton_Right[j].isSelected()) {		
				if (j == 0) {
					GUI_Text_splitPanel.setLeftComponent(panelInput1_GUI);
					GUI_Text_splitPanel.setRightComponent(panelInput1_TEXT);
				} else if (j == 1) {
					GUI_Text_splitPanel.setLeftComponent(panelInput2_GUI);
					GUI_Text_splitPanel.setRightComponent(panelInput2_TEXT);
				} else if (j == 2) {
					GUI_Text_splitPanel.setLeftComponent(panelInput3_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panelInput3_TEXT);
				} else if (j == 3) {
					GUI_Text_splitPanel.setLeftComponent(panelInput4_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panelInput4_TEXT);
				} else if (j == 4) {
					GUI_Text_splitPanel.setLeftComponent(panelInput5_GUI);
					GUI_Text_splitPanel.setRightComponent(null);
//					GUI_Text_splitPanel.setRightComponent(panelInput5_TEXT);
				}
			}
		}
	}

	// Panel General Inputs-----------------------------------------------------------------------------	
	class PaneL_General_Inputs_GUI extends JLayeredPane {
		public PaneL_General_Inputs_GUI() {
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
					// Apply any change in the GUI to the TEXT area	
					String input0_info = label1.getText() + "	" + combo1.getSelectedItem().toString() + "\n"
							+ label2.getText() + "	" + (Integer)spin2.getValue() + "\n"
							+ label3.getText() + "	" + combo3.getSelectedItem().toString() + "\n"
							+ label4.getText() + "	" + combo4.getSelectedItem().toString();
					panelInput1_TEXT.setText(input0_info);
				}
			};
			
			
			combo1.addActionListener(apply);
			combo3.addActionListener(apply);
			combo4.addActionListener(apply);
			
			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    spin2.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	spin2.setValue(spin2.getValue());
		        	// Apply any change in the GUI to the TEXT area	
		        	String input0_info = label1.getText() + "	" + combo1.getSelectedItem().toString() + "\n"
							+ label3.getText() + "	" + combo3.getSelectedItem().toString() + "\n"
							+ label2.getText() + "	" + (Integer)spin2.getValue() + "\n"
							+ label4.getText() + "	" + combo4.getSelectedItem().toString();
					panelInput1_TEXT.setText(input0_info);
		        }
		    });
		}
	}

	class PaneL_General_Inputs_Text extends JTextArea {
		public PaneL_General_Inputs_Text() {		
			setRows(10);		// set text areas with 10 rows when starts	
			setEditable(false);
		}
	}

	
	
	// Panel Model_Definiton-----------------------------------------------------------------------------------
	class PaneL_Model_Identifiniton_GUI extends JLayeredPane implements ItemListener {
		// Define 28 check box for 6 layers
		List<List<JCheckBox>> checkboxFilter;
		
		public PaneL_Model_Identifiniton_GUI() {
			setLayout(new GridBagLayout());
			ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
			
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
			textField0.setText(newDefinition);
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
					File tempDefinitionFile = FilesChooser_StrataDefinition.chosenDefinition();	
					if (tempDefinitionFile != null) {	//Only change StrataDefinition if the FileChooser return a file that is not Null
						file_StrataDefinition = tempDefinitionFile;
						newDefinition = file_StrataDefinition.getAbsolutePath();
						
						//create 4 new instances of the 2 Panels 
						panelInput2_GUI = new PaneL_Model_Identifiniton_GUI();
						panelInput2_TEXT = new PaneL_Model_Identification_Text();
						panelInput3_GUI = new PaneL_Universal_Requiements_GUI();
						panelInput3_TEXT = new PaneL_Universal_Requiements_Text();
						panelInput4_GUI = new PaneL_Disturbances_GUI();
						panelInput4_TEXT = new PaneL_Disturbances_Text();
						panelInput5_GUI = new PaneL_UserConstraints_GUI();
						panelInput5_TEXT = new PaneL_UserConstraints_Text();
						
						//and show the 2 new instances of Model_Definition Panel
						GUI_Text_splitPanel.setLeftComponent(panelInput2_GUI);
						GUI_Text_splitPanel.setRightComponent(panelInput2_TEXT);	
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

			JButton button2 = new JButton();
			button2.setToolTipText("Import Database");
			icon = new ImageIcon(getClass().getResource("/icon_import.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			button2.setIcon(new ImageIcon(scaleImage));			
			button2.setEnabled(false);
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					file_Database = FilesChooser_Database.chosenDatabase();				
					if (file_Database!=null) {
						textField2.setText(file_Database.getAbsolutePath());
						
						//create 2 new instances of this Panel 
						panelInput5_GUI = new PaneL_UserConstraints_GUI();
						panelInput5_TEXT = new PaneL_UserConstraints_Text();
							
						// Read the database tables into array
						read_DatabaseTables = new Read_DatabaseTables(file_Database);
						yieldTable_values = read_DatabaseTables.getTableArrays();
						yieldTable_ColumnNames = read_DatabaseTables.getTableColumnNames();
										        
				        //Update Age Class column of the existing strata table
						for (int row = 0; row < rowCount; row++) {						
							String s5 = data[row][5].toString();
							String s6 = data[row][6].toString();
							data[row][colCount - 2] = read_DatabaseTables.get_stratingAgeClass(s5, s6, "A", "0");							
					        table.setValueAt(data[row][colCount - 2], row, colCount - 2);
						}
				      
						 //Update Models OverView table
				        data3[2][1] = yieldTable_values.length;
				        table3.setValueAt(data3[2][1], 2, 1);
				        
				        int total_0yieldtable =0;
				        for (int row = 0; row < rowCount; row++) {				        	
				        	if (data[row][colCount -2].toString().equals("not found"))		total_0yieldtable = total_0yieldtable +1;
						}
				        data3[3][1] = total_0yieldtable;
				        table3.setValueAt(data3[3][1], 3, 1);
					}
				}
			});
			c0.gridx = 2;
			c0.gridy = 2;
			c0.weightx = 0;
		    c0.weighty = 1;
			importPanel.add(button2, c0);
		 			
					
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
			
			JButton button1 = new JButton();
			button1.setToolTipText("Import Strata");
			icon = new ImageIcon(getClass().getResource("/icon_import.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			button1.setIcon(new ImageIcon(scaleImage));
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					file_ExistingStrata = FilesChooser_ExistingStrata.chosenStrata();				
					if (file_ExistingStrata!=null) {
						textField1.setText(file_ExistingStrata.getAbsolutePath());
						// Read the whole text file into table
						read_Strata = new Read_Strata();
						read_Strata.readValues(file_ExistingStrata);
						String[][] value = read_Strata.getValues();
						rowCount = read_Strata.get_TotalRows();
						colCount = read_Strata.get_TotalColumns() + 2; //the "Age Class" & "Strata in optimization model" Columns
						data = new Object[rowCount][colCount];
						columnNames = new String[colCount];
						for (int row = 0; row < rowCount; row++) {
							for (int column = 0; column < colCount - 2; column++) {
								data[row][column] = value[row][column];
							}
						}
						TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(model);
						table.setRowSorter(sorter);
						table.setValueAt(data[0][0], 0, 0); //To help trigger the table refresh: fireTableDataChanged() and repaint();	
						columnNames[0] = "Strata ID";
						columnNames[1] = "Layer 1";
						columnNames[2] = "Layer 2";
						columnNames[3] = "Layer 3";
						columnNames[4] = "Layer 4";
						columnNames[5] = "Layer 5";
						columnNames[6] = "Layer 6";
						columnNames[7] = "Total area (acres)";
						columnNames[colCount - 2] = "Age Class";
						columnNames[colCount - 1] = "Strata in optimization model";
						table.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
						table.getColumnModel().getColumn(7).setPreferredWidth(120);	//Set width of Column "Total area" bigger
				        table.getColumnModel().getColumn(colCount-1).setPreferredWidth(200);	//Set width of Column "Strata in optimization model" bigger
				        

				        //Update Models OverView table
				        data3[0][1] = "0 vs " + rowCount;
				        table3.setValueAt(data3[0][1], 0, 1);
				        
				        availableAcres = 0;
				        for (int row = 0; row < rowCount; row++) {
				        	availableAcres = availableAcres + Double.parseDouble(data[row][7].toString());
						}
				        data3[1][1] = "0 vs " + availableAcres;
				        table3.setValueAt(data3[1][1], 1, 1);
				        
				        //Enable "Import Database"
				        textField2.setText(null);
				        button2.setEnabled(true);
					}
				}
			});
			c0.gridx = 2;
			c0.gridy = 1;
			c0.weightx = 0;
		    c0.weighty = 1;
			importPanel.add(button1, c0);
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
			//Setup the table--------------------------------------------------------------------------------
			rowCount3 = 5;
			colCount3 = 2;
			data3 = new Object[rowCount3][colCount3];
	        columnNames3= new String[] {"Description" , "Value"};
			
			// Populate the data matrix
			data3[0][0] = "Modeled Strata vs Available Strata";
			data3[1][0] = "Modeled Acres vs Available Acres";
			data3[2][0] = "Number of yield tables in your database";
			data3[3][0] = "Number of strata not connected to any yield table";
			
			
			//Create a table
	        model3 = new MyTableModel3();
	        table3 = new JTable(model3);
	        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table3.getDefaultRenderer(Object.class);
	        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//	        table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        table3.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
	        table3.setTableHeader(null);
	        table3.setPreferredScrollableViewportSize(new Dimension(400, 100));
	        table3.setFillsViewportHeight(true);
	        table3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        
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
			JButton select_Strata = new JButton();
			select_Strata.setToolTipText("Add the selected existing strata into optimization model");
			icon = new ImageIcon(getClass().getResource("/icon_select.png"));
			scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
			select_Strata.setIcon(new ImageIcon(scaleImage));
			select_Strata.addActionListener(new ActionListener() {
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
			        table3.setValueAt(data3[0][1], 0, 1);
			        
			        
			        modeledAcres = 0;
			        for (int row = 0; row < rowCount; row++) {
			        	if (data[row][colCount -1]!=null && data[row][colCount -1].toString().equals("Yes"))	modeledAcres = modeledAcres + Double.parseDouble(data[row][7].toString());
					}
			        data3[1][1] = modeledAcres + " vs " + availableAcres;
			        table3.setValueAt(data3[1][1], 1, 1);
				}
			});
			c2.gridx = 0;
			c2.gridy = 1;
			c2.gridwidth = 1;	
			c2.weightx = 1;
		    c2.weighty = 0;
			inforPanel.add(select_Strata, c2);
			
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
			        table3.setValueAt(data3[0][1], 0, 1);
			        
			        
			        modeledAcres = 0;
			        for (int row = 0; row < rowCount; row++) {
			        	if (data[row][colCount -1]!=null && data[row][colCount -1].toString().equals("Yes"))	modeledAcres = modeledAcres + Double.parseDouble(data[row][7].toString());
					}
			        data3[1][1] = modeledAcres + " vs " + availableAcres;
			        table3.setValueAt(data3[1][1], 1, 1);
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
			
			// Add the 3rd grid - silvicultural_Methods_Panel to the main Grid	
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 2;
			super.add(inforPanel, c);
		}
		
		
		//Listeners for checkBox Filter--------------------------------------------------------------------
		public void itemStateChanged(ItemEvent e) {

			//This help filter to get the strata as specified by the CheckBoxes
			TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(model);
			table.setRowSorter(sorter);			
			List<RowFilter<MyTableModel, Object>> filters, filters2;

			filters2  = new ArrayList<RowFilter<MyTableModel,Object>>();
			
			for (int i = 0; i < checkboxFilter.size(); i++) {
				RowFilter<MyTableModel, Object> layer_filter = null;
				filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
				for (int j = 0; j < checkboxFilter.get(i).size(); j++) {
					if (checkboxFilter.get(i).get(j).isSelected()) {			
						filters.add(RowFilter.regexFilter(checkboxFilter.get(i).get(j).getText(), i + 1));	// i+1 is the table column containing the first layer	
					}
				}
				layer_filter = RowFilter.orFilter(filters);
				
				filters2.add(layer_filter);
			}
			
			RowFilter<MyTableModel, Object> combine_AllFilters = null;
			combine_AllFilters = RowFilter.andFilter(filters2);
			sorter.setRowFilter(combine_AllFilters);
		}
	}

	class PaneL_Model_Identification_Text extends JLayeredPane {
	    public PaneL_Model_Identification_Text() {
	         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    	
	         rowCount = 0;
	         colCount = 10;
	         data = new Object[rowCount][colCount];
	         columnNames= new String[] {"Strata ID" , "Layer 1", "Layer 2", "Layer 3", "Layer 4", "Layer 5", "Layer 6", 
	 				"Total area (acres)", "Age Class", "Strata in optimization model"};
	         
//			// Populate the data matrix without any information
//			for (int row = 0; row < 1; row++) {			// 1 row is ok
//				for (int col = 0; col < colCount; ++col) {		//Number of Columns must match
//					data[row][col] = "";
//				}
//			}
	    	
	    	
	         //Create a table
	         model = new MyTableModel();
	         table = new JTable(model) {
//	             //Implement table cell tool tips           
//	             public String getToolTipText(MouseEvent e) {
//	                 String tip = null;
//	                 java.awt.Point p = e.getPoint();
//	                 int rowIndex = rowAtPoint(p);
//	                 int colIndex = columnAtPoint(p);
//	                 try {
//	                       tip = getValueAt(rowIndex, colIndex).toString();
//	                 } catch (RuntimeException e1) {
//	                	 System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//	                 }
//	                 return tip;
//	             }
	         };
	         //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	         table.getColumnModel().getColumn(7).setPreferredWidth(120);	//Set width of Column "Total area" bigger
	         table.getColumnModel().getColumn(colCount-1).setPreferredWidth(200);	//Set width of Column "Strata in optimization model" bigger
	         table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	         table.setFillsViewportHeight(true);
	         table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	  

	         //Create the scroll pane and add the table to it.
	         JScrollPane scrollPane = new JScrollPane(table);
	  
	         //Add the scroll pane to this panel.
	         add(scrollPane);         
	     }
	}	
		
	
	class MyTableModel extends AbstractTableModel {
	    	 
		public MyTableModel() {

		  }

		public int getColumnCount() {
			return colCount;
		}

		public int getRowCount() {
			return rowCount;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
//			if (col < colCount - 1) { // Only the last column is editable
//				return false;
//			} else {
//				return true;
//			}
			
			return false;		// all columns are un-editable
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
			fireTableDataChanged();
			repaint();
		}
	}
	

	class MyTableModel3 extends AbstractTableModel {
	   	 
		public MyTableModel3() {

		  }

		public int getColumnCount() {
			return colCount3;
		}

		public int getRowCount() {
			return rowCount3;
		}

		public String getColumnName(int col) {
			return columnNames3[col];
		}

		public Object getValueAt(int row, int col) {
			return data3[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			if (c==0) return String.class;      //column 0 accepts only String
			else if (c>=2 && c<=5) return Double.class;      //column 2 to 5 accept only Double values    
	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setValueAt(Object value, int row, int col) {
			data3[row][col] = value;
			fireTableCellUpdated(row, col);
			fireTableDataChanged();
			repaint();
		}
	}	
	
	
	// Panel Universal_Requiremetns-----------------------------------------------------------------------------------
	class PaneL_Universal_Requiements_GUI extends JLayeredPane {
		public PaneL_Universal_Requiements_GUI() {
			setLayout(new GridBagLayout());
			ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
			
		
			read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
			List<List<String>> allLayers =  read_Identifiers.get_allLayers();
			List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();

			
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
			JPanel EArequirements_Panel = new JPanel();		
			TitledBorder border2 = new TitledBorder("Requirements of Even Age Method: Cover Type Conversion & Rotation Age-Class");
			border2.setTitleJustification(TitledBorder.CENTER);
			EArequirements_Panel.setBorder(border2);
			EArequirements_Panel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.weightx = 1;
		    c2.weighty = 1;

			int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
				

			//Setup the table--------------------------------------------------------------------------------
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
					if (i==j) data4[table_row][4] = "Yes"; else data4[table_row][4] = null;
					table_row++;
				}
			}		
			
			//Create a table
	        model4 = new MyTableModel4();
	        table4 = new JTable(model4){
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
					return tip;
				}
			};
			
			//Set Color and Alighment for Cells
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object
	                value, boolean isSelected, boolean hasFocus, int row, int column) {
	                super.getTableCellRendererComponent(
	                    table, value, isSelected, hasFocus, row, column);
//	                setForeground(Color.RED);
	                setHorizontalAlignment(JLabel.LEFT);
//	                setFont(getFont().deriveFont(Font.BOLD));
	                
	                
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
					setBackground(rowColor[row]);
	                
	                
//					int[] rowAlignment = new int[rowCount5];
//					int currentAlignment = SwingConstants.CENTER;
//					int rCount2 = 0;
//
//					for (int i = 0; i < total_CoverType; i++) {
//						if (currentAlignment == SwingConstants.CENTER) {
//							currentAlignment = SwingConstants.LEFT;
//						} else {
//							currentAlignment = SwingConstants.CENTER;
//						}
//						for (int j = 0; j < total_SizeClass; j++) {
//							rowAlignment[rCount2] = currentAlignment;
//							rCount2++;
//						}
//					}
//					setHorizontalAlignment(rowAlignment[row]);
	                
	                return this;
	            }
	        };
	        
			for (int i = 0; i < columnNames4.length; i++) {
				table4.getColumnModel().getColumn(i).setCellRenderer(r);
			}		
			
	        
	        
			class comboBox_ConstraintType extends JComboBox {	
				public comboBox_ConstraintType() {
				addItem("Yes");
				addItem(null);	
//				setSelectedIndex(2);
				}
			}
			  // Set up Types for each table2 Columns
			table4.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new comboBox_ConstraintType()));
			
//	        table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//	        table4.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
//	        table4.setTableHeader(null);
	        table4.setPreferredScrollableViewportSize(new Dimension(400, 120));
	        table4.setFillsViewportHeight(true);
	        table4.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        
	        JScrollPane Requirements_ScrollPane = new JScrollPane();
	        Requirements_ScrollPane.setViewportView(table4);
	        
		    c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 1;
		    c2.weighty = 1;
		    EArequirements_Panel.add(Requirements_ScrollPane, c2);							
			
		
//			//Labels for Cover Type From
//			 List<JLabel> CTfrom_lable = new ArrayList<JLabel>();
//			for (int i = 0; i < total_CoverType; i++) {
//				CTfrom_lable.add(new JLabel("        " + allLayers.get(4).get(i) + "      regenerated as     "));
//				CTfrom_lable.get(i).setToolTipText(allLayers_ToolTips.get(4).get(i));
//				c2.gridx = 0;
//				c2.gridy = i + 1;
//				c2.gridwidth = 1;
//				Requirements_Panel.add(CTfrom_lable.get(i), c2);
//			}
//			
//			//CheckBox for Cover Type To
//			ConversionCheck_To = new JCheckBox[total_CoverType][total_CoverType];
//		    for (int i = 0; i < total_CoverType; i++) {
//		    	   for (int j = 0; j < total_CoverType; j++) {
//				    	ConversionCheck_To[i][j] = new JCheckBox(allLayers.get(4).get(j));
//				    	ConversionCheck_To[i][j].setToolTipText(allLayers_ToolTips.get(4).get(j));
//				    	c2.gridx = j + 1;
//						c2.gridy = i+1;
//						c2.gridwidth = 1;
//						Requirements_Panel.add(ConversionCheck_To[i][j], c2);
//						if (i==j) ConversionCheck_To[i][j].setSelected(true);
//						
//						
//						//Make label Enable after a checkbox is selected
//						int current_i = i;
//						int current_j = j;
//						ConversionCheck_To[i][j].addActionListener(new ActionListener() {	
//							@Override
//							public void actionPerformed(ActionEvent actionEvent) {
//								if (ConversionCheck_To[current_i][current_j].isSelected()) {
//									CTfrom_lable.get(current_i).setEnabled(true);
//								}					
//							}
//						});
//					}
//		    	   			
//					//add listeners to select all or deselect all
//		    		int curent_index = i;
//					CTfrom_lable.get(i).addMouseListener(new MouseAdapter() {
//						@Override
//						public void mouseClicked(MouseEvent e) {
//							if (CTfrom_lable.get(curent_index).isEnabled()) {	
//								for (int j = 0; j < total_CoverType; j++) {		//Loop all elements in each layer
//									ConversionCheck_To[curent_index][j].setSelected(false);
//								}
//								CTfrom_lable.get(curent_index).setEnabled(false);
//							} else {
//								for (int j = 0; j < total_CoverType; j++) {		//Loop all elements in each layer
//									ConversionCheck_To[curent_index][j].setSelected(true);
//								}
//								CTfrom_lable.get(curent_index).setEnabled(true);
//							}
//						}
//					});
//			}

			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------
			
		    
		    
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
			JPanel SRDrequirements_Panel = new JPanel();		
			TitledBorder border3 = new TitledBorder("Requirements of Stand Replacing Disturbances: Cover Type Conversion & Proportion (%)");
			border3.setTitleJustification(TitledBorder.CENTER);
			SRDrequirements_Panel.setBorder(border3);
			SRDrequirements_Panel.setLayout(new GridBagLayout());
			GridBagConstraints c3 = new GridBagConstraints();
			c3.fill = GridBagConstraints.BOTH;
			c3.weightx = 1;
		    c3.weighty = 1;
				

			//Setup the table--------------------------------------------------------------------------------
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
			
			//Create a table
	        model7 = new MyTableModel7();
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
	        
			for (int i = 0; i < columnNames7.length; i++) {
				table7.getColumnModel().getColumn(i).setCellRenderer(r);
			}		
			
			// Set up Types for each table7 Columns
			
//	        table7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//	        table7.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
//	        table7.setTableHeader(null);
	        table7.setPreferredScrollableViewportSize(new Dimension(400, 120));
	        table7.setFillsViewportHeight(true);
	        table7.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        
	        JScrollPane SRDrequirements_ScrollPane = new JScrollPane();
	        SRDrequirements_ScrollPane.setViewportView(table7);
	        
		    c3.gridx = 0;
			c3.gridy = 0;
			c3.weightx = 1;
		    c3.weighty = 1;
		    SRDrequirements_Panel.add(SRDrequirements_ScrollPane, c3);							
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------		    
		    
		    
		      
			
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
		    c.weighty = 1;

			
			// Add the 1st grid - EArequirements_Panel to the main Grid	
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(EArequirements_Panel, c);
			
			// Add the 1st grid - SRDrequirements_Panel to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(SRDrequirements_Panel, c);
		}
	}

	class MyTableModel4 extends AbstractTableModel {
	   	 
		public MyTableModel4() {

		  }

		public int getColumnCount() {
			return colCount4;
		}

		public int getRowCount() {
			return rowCount4;
		}

		public String getColumnName(int col) {
			return columnNames4[col];
		}

		public Object getValueAt(int row, int col) {
			return data4[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			if (c==0) return String.class;      //column 0 accepts only String
			else if (c>=2 && c<=3) return Integer.class;      //column 2 and 3 accept only Integer values    
	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			if (col < 2) { // Only the last 3 columns are editable
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			if ((col>=2 && col<=3) && (((Number) value).intValue() < 1 || ((Number) value).intValue() > 100)) {
				JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
						"Your input has not been accepted. Only age classes in the range 1-100 would be allowed.");
			} else {
				data4[row][col] = value;
				fireTableCellUpdated(row, col);
				fireTableDataChanged();
				repaint();
			}
		}
	}	

	class MyTableModel7 extends AbstractTableModel {
	   	 
		public MyTableModel7() {

		  }

		public int getColumnCount() {
			return colCount7;
		}

		public int getRowCount() {
			return rowCount7;
		}

		public String getColumnName(int col) {
			return columnNames7[col];
		}

		public Object getValueAt(int row, int col) {
			return data7[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			if (c==0 || c==1) return String.class;      //column 0 and 1 accept only String
			else if (c==2) return Integer.class;      //column 2 accept only Integer values 
			else if (c==3) return Double.class;      //column 3 (last column) accept only Double values    
	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			if (col != 2) { // Only column 2 is editable
				return false;
			} else {
				return true;
			}
		}

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
				
				//Calculate and write percentge
				table_row=0;
				for (int i = 0; i < total_CoverType; i++) {
					for (int j = 0; j < total_CoverType; j++) {					
						data7[table_row][3]	= Double.parseDouble(data7[table_row][2].toString())/total_Weight[i]*100;			
						table_row++;
					}	
				}
				
				
				fireTableCellUpdated(row, col);
				fireTableDataChanged();
				repaint();
			}
		}
	}		
		
	
	class PaneL_Universal_Requiements_Text extends JLayeredPane {
	    public PaneL_Universal_Requiements_Text() {

	    	
	     }
	}	
	

	// Panel Disturbances-----------------------------------------------------------------------------------
	class PaneL_Disturbances_GUI extends JLayeredPane {
		public PaneL_Disturbances_GUI() {
			setLayout(new GridBagLayout());
			ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
			
		
			read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
			List<List<String>> allLayers =  read_Identifiers.get_allLayers();
			List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();

			
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------
			JPanel MixedFire_Panel = new JPanel();		
			TitledBorder border2 = new TitledBorder("Specify the proportion of exsiting strata area (%) sufferred from Mixed Severity Wildfire across all time periods");
			border2.setTitleJustification(TitledBorder.CENTER);
			MixedFire_Panel.setBorder(border2);
			MixedFire_Panel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.weightx = 1;
		    c2.weighty = 1;

			int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
			int total_SizeClass = allLayers.get(5).size();		// total number of elements - 1 in layer6 Size Class (0 to...)
			
			
			//Setup the table--------------------------------------------------------------------------------
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
			
			//Create a table
	        model5 = new MyTableModel5();
			table5 = new JTable(model5){
	             //Implement table cell tool tips           
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
			};
			
			//Set Color and Alighment for Cells
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object
	                value, boolean isSelected, boolean hasFocus, int row, int column) {
	                super.getTableCellRendererComponent(
	                    table, value, isSelected, hasFocus, row, column);
//	                setForeground(Color.RED);
	                setHorizontalAlignment(JLabel.LEFT);
//	                setFont(getFont().deriveFont(Font.BOLD));
	                
	                
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
					setBackground(rowColor[row]);
	                
	                
//					int[] rowAlignment = new int[rowCount5];
//					int currentAlignment = SwingConstants.CENTER;
//					int rCount2 = 0;
//
//					for (int i = 0; i < total_CoverType; i++) {
//						if (currentAlignment == SwingConstants.CENTER) {
//							currentAlignment = SwingConstants.LEFT;
//						} else {
//							currentAlignment = SwingConstants.CENTER;
//						}
//						for (int j = 0; j < total_SizeClass; j++) {
//							rowAlignment[rCount2] = currentAlignment;
//							rCount2++;
//						}
//					}
//					setHorizontalAlignment(rowAlignment[row]);
	                
	                return this;
	            }
	        };
	        
	        for (int i=0; i<columnNames5.length; i++) {
	        	table5.getColumnModel().getColumn(i).setCellRenderer(r);
	        }
	       
	        
//	        table5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//	        table5.getColumnModel().getColumn(0).setPreferredWidth(200);	//Set width of 1st Column bigger
//	        table5.setTableHeader(null);
	        table5.setPreferredScrollableViewportSize(new Dimension(400, 120));
	        table5.setFillsViewportHeight(true);
	        table5.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        
	        JScrollPane MixedFire_ScrollPane = new JScrollPane();
	        MixedFire_ScrollPane.setViewportView(table5);
	        
		    c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 1;
		    c2.weighty = 1;
		    MixedFire_Panel.add(MixedFire_ScrollPane, c2);							
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------
			
	
		    
		    
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------
			JPanel StandReplacing_Panel = new JPanel();		
			TitledBorder border3 = new TitledBorder("Specify the proportion of regenerated strata area (%) lost due to Stand Replacing Disturbances in each time period");
			border3.setTitleJustification(TitledBorder.CENTER);
			StandReplacing_Panel.setBorder(border3);
			StandReplacing_Panel.setLayout(new GridBagLayout());
			GridBagConstraints c3 = new GridBagConstraints();
			c3.fill = GridBagConstraints.BOTH;
			c3.weightx = 1;
		    c3.weighty = 1;

		    
			//Setup the table--------------------------------------------------------------------------------
			rowCount6 = 30;
			colCount6 = total_CoverType + 1;
			data6 = new Object[rowCount6][colCount6];
	        columnNames6= new String[colCount6];
	        columnNames6[0] = "Age Class of Regenerated Strata";
	        for (int i = 1; i < colCount6; i++) {
	        	 columnNames6[i] = allLayers.get(4).get(i-1);
			}	
	        
	        //Header ToolTIp
	        String[] headerToolTips = new String[colCount6];
	        for (int i = 1; i < colCount6; i++) {
	        	headerToolTips[i] = allLayers_ToolTips.get(4).get(i-1);
			}
	       
			
			// Populate the data matrix
			for (int i = 0; i < rowCount6; i++) {
				data6[i][0] = i+1;			//Age class column, age starts from 1
				for (int j = 1; j < colCount6; j++) {	//all other columns
					data6[i][j] = 0.2;
					if (allLayers.get(4).get(j-1).equals("N"))	data6[i][j] = 0.0;	//Non-stocked --> No SR Fire
				}
			}		
			
			//Create a table
	        model6 = new MyTableModel6();
			table6 = new JTable(model6) {
				  //Implement table cell tool tips           
				public String getToolTipText(MouseEvent e) {
					String tip = null;
					java.awt.Point p = e.getPoint();
					int rowIndex = rowAtPoint(p);
					int colIndex = columnAtPoint(p);
					if (colIndex > 0) {
						try {
							tip = "% loss of the regenerated strata with cover type "+ allLayers.get(4).get(colIndex - 1) 
									+ " at age class " + getValueAt(rowIndex, 0).toString();
							if (rowIndex == rowCount6 - 1) 	tip = tip + " plus";	//Add plus to the highest age class
						} catch (RuntimeException e1) {
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}
					}					
					return tip;
				}
				
			    //Implement table header tool tips. 
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
			
			//Set Color and Alighment for Cells
	        DefaultTableCellRenderer r2 = new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object
	                value, boolean isSelected, boolean hasFocus, int row, int column) {
	                super.getTableCellRendererComponent(
	                    table, value, isSelected, hasFocus, row, column);
//	                setForeground(Color.RED);
	                setHorizontalAlignment(JLabel.LEFT);
//	                setFont(getFont().deriveFont(Font.BOLD));
//					setBackground(rowColor[row]);
	                return this;
	            }
	        };
	        
	        for (int i=0; i<columnNames6.length; i++) {
	        	table6.getColumnModel().getColumn(i).setCellRenderer(r2);
	        }

	        //Set toolTip for Column header
	        JTableHeader header = table6.getTableHeader();
	       

	        
//	        table6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        table6.getColumnModel().getColumn(0).setPreferredWidth(300);	//Set width of 1st Column bigger
//	        table6.setTableHeader(null);
	        table6.setPreferredScrollableViewportSize(new Dimension(400, 120));
	        table6.setFillsViewportHeight(true);
	        table6.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        
	        JScrollPane StandReplacing_ScrollPane = new JScrollPane();
	        StandReplacing_ScrollPane.setViewportView(table6);
	        
		    c3.gridx = 0;
			c3.gridy = 0;
			c3.weightx = 1;
		    c3.weighty = 1;
		    StandReplacing_Panel.add(StandReplacing_ScrollPane, c3);							
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------		    
		    
		    
		    
    
		    
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
		    c.weighty = 1;

			
			// Add the 1st grid - MixedFire_Panel to the main Grid	
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(MixedFire_Panel, c);
			
			// Add the 2nd grid - StandReplacing_Panel to the main Grid	
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			super.add(StandReplacing_Panel, c);
		}

	}

	class MyTableModel5 extends AbstractTableModel {
	   	 
		public MyTableModel5() {

		  }

		public int getColumnCount() {
			return colCount5;
		}

		public int getRowCount() {
			return rowCount5;
		}

		public String getColumnName(int col) {
			return columnNames5[col];
		}

		public Object getValueAt(int row, int col) {
			return data5[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			if (c==0 || c==1) return String.class;      //column 0 and 1 accept only String
			else if (c==2) return Double.class;      //column 2 (last column) accept only Double values    
	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			if (col < 2) { // Only the last column are editable
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			if (col == 2 && (((Number) value).doubleValue() < 0 || ((Number) value).doubleValue() > 100)) {
				JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
			} else {
				data5[row][col] = value;
				fireTableCellUpdated(row, col);
				fireTableDataChanged();
				repaint();
			}
		}
	}	

	
	class MyTableModel6 extends AbstractTableModel {
	   	 
		public MyTableModel6() {

		  }

		public int getColumnCount() {
			return colCount6;
		}

		public int getRowCount() {
			return rowCount6;
		}

		public String getColumnName(int col) {
			return columnNames6[col];
		}

		public Object getValueAt(int row, int col) {
			return data6[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			if (c==0) return Integer.class;      //column 0 accepts only String
			else if (c>=1) return Double.class;      //All other columns accept only Double values    
	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			if (col < 1) { // Only the first column are un-editable
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			if (col > 0 && (((Number) value).doubleValue() < 0 || ((Number) value).doubleValue() > 100)) {
				JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
						"Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
			} else {
				data6[row][col] = value;
				fireTableCellUpdated(row, col);
				fireTableDataChanged();
				repaint();
			}
		}
	}	
		
	
	class PaneL_Disturbances_Text extends JLayeredPane {
		public PaneL_Disturbances_Text() {

		}
	}
	
	
	// Panel Constraints-----------------------------------------------------------------------------------
	class PaneL_UserConstraints_GUI extends JLayeredPane {
		List<List<JCheckBox>> checkboxStaticIdentifiers;
//		List<List<JCheckBox>> checkboxDynamicIdentifiers;
//		List<JCheckBox> checkbox_select_DynamicIdentifiers;
//		List<JCheckBox> checkboxParameter;
		
		public PaneL_UserConstraints_GUI() {
			setLayout(new GridBagLayout());
			ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately	
				
			// 1st grid -----------------------------------------------------------------------
			// 1st grid -----------------------------------------------------------------------						
			read_Identifiers = new Read_Indentifiers(file_StrataDefinition);
			
			List<String> layers_Title = read_Identifiers.get_layers_Title();
			List<String> layers_Title_ToolTip = read_Identifiers.get_layers_Title_ToolTip();
			List<List<String>> allLayers =  read_Identifiers.get_allLayers();
			List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();

			int total_layers = allLayers.size();				//Remove the last 1 layers				= allLayers.size() - 1
//			int total_layers_ToolTips = allLayers_ToolTips.size() -1;	//Remove the last 1 layers			= allLayers.size() - 1


			
			// Add 3 more into static identifiers
			List<String> MethodsPeriodsAges_Title = read_Identifiers.get_MethodsPeriodsAges_Title();
			List<List<String>> MethodsPeriodsAges =  read_Identifiers.get_MethodsPeriodsAges();		
			
			layers_Title.addAll(MethodsPeriodsAges_Title);
			layers_Title_ToolTip.addAll(MethodsPeriodsAges_Title);
			allLayers.addAll(MethodsPeriodsAges);
			allLayers_ToolTips.addAll(MethodsPeriodsAges);
			
			int total_staticIdentifiers = total_layers + MethodsPeriodsAges.size();
			
			
			
			//Add all layers labels and checkboxes to identifiersPanel
			JPanel identifiersPanel = new JPanel();		
			identifiersPanel.setLayout(new GridBagLayout());
			GridBagConstraints c1 = new GridBagConstraints();
			c1.fill = GridBagConstraints.HORIZONTAL;
			c1.weightx = 1;
		    c1.weighty = 1;

		    
		    
		    
	    	JScrollPane identifiersScrollPanel = new JScrollPane(identifiersPanel);
			TitledBorder border1 = new TitledBorder("Static identifiers for VARIABLES (from model definition)");
			border1.setTitleJustification(TitledBorder.CENTER);
			identifiersScrollPanel.setBorder(border1);
			identifiersScrollPanel.setPreferredSize(new Dimension(100, 250));
		    
		    
		    
			//Add all layers labels
		    List<JLabel> layers_Title_Label = new ArrayList<JLabel>();
			for (int i = 0; i < total_staticIdentifiers; i++) {
				layers_Title_Label.add(new JLabel(layers_Title.get(i)));
				layers_Title_Label.get(i).setToolTipText(layers_Title_ToolTip.get(i));
				
				//add listeners to select all or deselect all
				int curent_index = i;
				layers_Title_Label.get(curent_index).addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (layers_Title_Label.get(curent_index).isEnabled()) {	
							for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(false);
							}
							layers_Title_Label.get(curent_index).setEnabled(false);
						} else {
							for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(true);
							}
							layers_Title_Label.get(curent_index).setEnabled(true);
						}
					}
				});
		
				//Add to identifiersPanel
				c1.gridx = i;
				c1.gridy = 0;
				identifiersPanel.add(layers_Title_Label.get(i), c1);
			}
			

			//Add CheckBox for all layers
			checkboxStaticIdentifiers = new ArrayList<List<JCheckBox>>();
			for (int i = 0; i < total_staticIdentifiers; i++) {		//Loop all layers
				List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		//A temporary List
				checkboxStaticIdentifiers.add(temp_List);
				for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
					checkboxStaticIdentifiers.get(i).add(new JCheckBox(allLayers.get(i).get(j)));
					checkboxStaticIdentifiers.get(i).get(j).setToolTipText(allLayers_ToolTips.get(i).get(j));	
					checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
					
					c1.gridx = i;
					c1.gridy = j + 1;
					identifiersPanel.add(checkboxStaticIdentifiers.get(i).get(j), c1);
					
					//Make label Enable after a checkbox is selected
					int current_i = i;
					int current_j = j;
					checkboxStaticIdentifiers.get(i).get(j).addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if (checkboxStaticIdentifiers.get(current_i).get(current_j).isSelected()) {
								layers_Title_Label.get(current_i).setEnabled(true);
							}					
						}
					});
							
//					//Set layer 5 - Cover Type invisible
//					if (i==4) checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
					//Set layer 6 - Size Class invisible
					if (i==5) checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
//					//Deselect all time period check boxes (7)
//					if (i==7) checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
				}
			}
			
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------

			
			
			
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------	
			class checkboxScrollPanel extends JScrollPane {	
				private JCheckBox checkboxNoParameter;
				private List<JCheckBox> checkboxParameter;
				
				public checkboxScrollPanel(String nameTag) {
					
					JPanel parametersPanel = new JPanel();	
					parametersPanel.setLayout(new GridBagLayout());
					GridBagConstraints c2 = new GridBagConstraints();
					c2.fill = GridBagConstraints.HORIZONTAL;
					c2.weightx = 1;
				    c2.weighty = 1;
				    
					setViewportView(parametersPanel);
				    
    
				    //Add variableGroups to the comboBox
					JComboBox comboGroups = new JComboBox();			
		
					JButton tempButton = new JButton(nameTag);
					// add comboBox to the Panel
					c2.gridx = 0;
					c2.gridy = 0;
					c2.weightx = 1;
					c2.weighty = 1;
					parametersPanel.add(tempButton, c2);
					
					
					tempButton.addActionListener(new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							if (yieldTable_ColumnNames != null && checkboxParameter == null) {				
								parametersPanel.remove(tempButton);		//Remove the tempButton
								checkboxParameter = new ArrayList<JCheckBox>();
								
								for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
									String YTcolumnName = yieldTable_ColumnNames[i];

									checkboxParameter.add(new JCheckBox(YTcolumnName));		//add checkbox
									checkboxParameter.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")");		//add toolTip
									
									// add checkboxParameter to the Panel
								    c2.gridx = 0;
								    c2.gridy = 1 + i;
									c2.weightx = 1;
								    c2.weighty = 1;
									parametersPanel.add(checkboxParameter.get(i), c2);
								}
								
								
								//Add an extra checkbox for the option of not using any Column, use 1 instead as multiplier
								//This is also the checkbox for the option of not using any Column as dynamic identifier
								checkboxNoParameter = new JCheckBox();		//add checkbox			
								checkboxNoParameter.setText("NoParameter");		
								checkboxNoParameter.setToolTipText("1 is used as multiplier (parameter), no column will be used as parameter");		//set toolTip
								
								// add the checkBox to the Panel
								c2.gridx = 0;
								c2.gridy = 0;
								c2.weightx = 1;
								c2.weighty = 1;
								parametersPanel.add(checkboxNoParameter, c2);
								
								// Add listeners to de-select all other checkBoxes
								checkboxNoParameter.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent actionEvent) {
										if (checkboxNoParameter.isSelected()) {
											for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
												checkboxParameter.get(i).setSelected(false);
											} 
										}
									}
								});								
								
								
								// Add listeners to checkBox so if then name has AllSx then other checkbox would be deselected 
								for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
									String currentCheckBoxName = yieldTable_ColumnNames[i];
									int currentCheckBoxIndex = i;
									
									checkboxParameter.get(i).addActionListener(new ActionListener() {	
										@Override
										public void actionPerformed(ActionEvent actionEvent) {
											//Deselect the NoParameter checkBox
											checkboxNoParameter.setSelected(false);
											
											if (currentCheckBoxName.contains("AllSx")) {
												for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
													if (j!=currentCheckBoxIndex) 	checkboxParameter.get(j).setSelected(false);
												}
											} else {
												for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
													if (checkboxParameter.get(j).getText().contains("AllSx")) 	checkboxParameter.get(j).setSelected(false);
												}
											}					
										}
									});
								}

								//Do a resize to same size for JInteral Frame of the project to help repaint the checkboxVariables added					
								Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	
							}
						}
					});		
					
				}
			}
			
		    	
			checkboxScrollPanel parametersScrollPanel = new checkboxScrollPanel("Get parameters from YT columns");
			TitledBorder border2 = new TitledBorder("PARAMETERS (yield table columns)");
			border2.setTitleJustification(TitledBorder.CENTER);
			parametersScrollPanel.setBorder(border2);
	    	parametersScrollPanel.setPreferredSize(new Dimension(250, 100));
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------
			


	    	
			// 4th Grid -----------------------------------------------------------------------
			// 4th Grid -----------------------------------------------------------------------	
			class checkbox_dynamicScrollPanel extends JScrollPane {	
				private JCheckBox checkboxNoIdentifier;
				private List<JCheckBox> allDynamicIdentifiers;
				private List<JScrollPane> allDynamicIdentifiers_ScrollPane;
				private List<List<JCheckBox>> checkboxDynamicIdentifiers;
				private JScrollPane defineScrollPane;		//for Definition of dynamic identifier
				
				public checkbox_dynamicScrollPanel(String nameTag, int option) {

					// Define the Panel contains everything --------------------------
					JPanel dynamic_identifiersPanel = new JPanel();		
					dynamic_identifiersPanel.setLayout(new GridBagLayout());
					GridBagConstraints c3 = new GridBagConstraints();
					c3.fill = GridBagConstraints.BOTH;
					c3.weightx = 1;
				    c3.weighty = 1;
				    // Add elements to this Panel later at the end --------------------------
					
					
				
	
					//This is the Panel for select all available identifiers--------------------------
					JPanel select_Panel = new JPanel();	
					select_Panel.setLayout(new GridBagLayout());
					GridBagConstraints c2 = new GridBagConstraints();
					c2.fill = GridBagConstraints.HORIZONTAL;
					c2.weightx = 1;
				    c2.weighty = 1;
				    
				    //Add variableGroups to the comboBox
					JComboBox comboGroups = new JComboBox();			
		
					JButton tempButton = new JButton(nameTag);
					// add comboBox to the Panel
					c2.gridx = 0;
					c2.gridy = 0;
					c2.weightx = 1;
					c2.weighty = 1;
					select_Panel.add(tempButton, c2);
					//------------------------------------------------------------------------------
					
					
											
					tempButton.addActionListener(new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							if (yieldTable_ColumnNames != null && allDynamicIdentifiers == null) {				
								select_Panel.remove(tempButton);		//Remove the tempButton
								
								checkboxDynamicIdentifiers = new ArrayList<List<JCheckBox>>();	
								allDynamicIdentifiers = new ArrayList<JCheckBox>();
								
								for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
									String YTcolumnName = yieldTable_ColumnNames[i];

									checkboxDynamicIdentifiers.add(new ArrayList<JCheckBox>());		//add empty List
									allDynamicIdentifiers.add(new JCheckBox(YTcolumnName));		//add checkbox
									allDynamicIdentifiers.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")");		//add toolTip
									
									// add checkboxParameter to the Panel
								    c2.gridx = 0;
								    c2.gridy = 1 + i;
									c2.weightx = 1;
								    c2.weighty = 1;
									select_Panel.add(allDynamicIdentifiers.get(i), c2);
								}
								
								
								//Add an extra checkBox for the option of not using any Column as dynamic identifier
								checkboxNoIdentifier = new JCheckBox();		//add checkBox		
								checkboxNoIdentifier.setText("NoIdentifier");	
								checkboxNoIdentifier.setToolTipText("No column will be used as dynamic identifier");		//set toolTip
								
								// add the checkBox to the Panel
								c2.gridx = 0;
								c2.gridy = 0;
								c2.weightx = 1;
								c2.weighty = 1;
								select_Panel.add(checkboxNoIdentifier, c2);
								
								// Add listeners to de-select all other checkBoxes
								checkboxNoIdentifier.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent actionEvent) {
										if (checkboxNoIdentifier.isSelected()) {
											for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
												allDynamicIdentifiers.get(i).setSelected(false);
												allDynamicIdentifiers_ScrollPane.get(i).setVisible(false);		//Set invisible all scrollPanes of dynamic identifiers
												
												//Do a resize to same size for JInteral Frame of the project to help repaint					
												Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	
											} 
										}
									}
								});								
								
						
							
								if (option == 2) {		//For the dynamic identifiers only						
									//Add all dynamic identifiers lables
									allDynamicIdentifiers_ScrollPane = new ArrayList<JScrollPane>();
									
									for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
										String YTcolumnName = allDynamicIdentifiers.get(i).getText();		
										allDynamicIdentifiers_ScrollPane.add(new JScrollPane());			//Add ScrollPane
										allDynamicIdentifiers_ScrollPane.get(i).setBorder(new TitledBorder(YTcolumnName));	//set Title
										allDynamicIdentifiers_ScrollPane.get(i).setPreferredSize(new Dimension(150, 100));
//										allDynamicIdentifiers_ScrollPane.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")");		//add toolTip										
										allDynamicIdentifiers_ScrollPane.get(i).setVisible(false);		//Set invisible
										
										c3.gridx =1 + i;
										c3.gridy = 0;
										dynamic_identifiersPanel.add(allDynamicIdentifiers_ScrollPane.get(i), c3);
									}					
																			
									
									// Add listeners to checkBoxes
									for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
										String currentCheckBoxName = yieldTable_ColumnNames[i];
										int currentCheckBoxIndex = i;
										
										allDynamicIdentifiers.get(i).addActionListener(new ActionListener() {	
											@Override
											public void actionPerformed(ActionEvent actionEvent) {
												// A popupPanel to define identifier if the checkBox is selected
												if (allDynamicIdentifiers.get(currentCheckBoxIndex).isSelected()) {
													
													//Remove all checkBoxes previously added into the Column list (or the dynamic identifier)
													checkboxDynamicIdentifiers.get(currentCheckBoxIndex).clear();
															
													//define popupPanel
													JPanel popupPanel = new JPanel();
													popupPanel.setLayout(new GridBagLayout());
													TitledBorder border_popup = new TitledBorder("PLEASE HELP SPECTRUMLITE DEFINE THIS IDENTIFIER");
													border_popup.setTitleJustification(TitledBorder.CENTER);
													popupPanel.setBorder(border_popup);
													popupPanel.setPreferredSize(new Dimension(800, 400));;
													GridBagConstraints c_popup = new GridBagConstraints();
													c_popup.fill = GridBagConstraints.BOTH;
													c_popup.weightx = 1;
													c_popup.weighty = 1;
													
													// Just to make the OptionPanel resizable
													popupPanel.addHierarchyListener(new HierarchyListener() {
													    public void hierarchyChanged(HierarchyEvent e) {
													        Window window = SwingUtilities.getWindowAncestor(popupPanel);
													        if (window instanceof Dialog) {
													            Dialog dialog = (Dialog)window;
													            if (!dialog.isResizable()) {
													                dialog.setResizable(true);
													            }
													        }
													    }
													});												
													//---------------------------------------------------------------------------------------------------	
													
													JPanel listPanel = new JPanel();
													listPanel.setLayout(new GridBagLayout());
													GridBagConstraints c_list = new GridBagConstraints();
													c_list.fill = GridBagConstraints.HORIZONTAL;
													c_list.weightx = 1;
													c_list.weighty = 1;
													
													
													List<String> uniqueValueList = read_DatabaseTables.getColumnUniqueValues(currentCheckBoxIndex);									
													//Sort the list	
													try {	//Sort Double
														Collections.sort(uniqueValueList,new Comparator<String>() {
															@Override
														    public int compare(String o1, String o2) {
														        return Double.valueOf(o1).compareTo(Double.valueOf(o2));
														    }
														});	
													} catch (Exception e1) {
														Collections.sort(uniqueValueList);	//Sort String
													}
													
													//Add Labels of unique values to listPanel
													for (int j = 0; j < uniqueValueList.size(); j++) {
														c_list.gridx = 0;
														c_list.gridy = j;
														c_list.weightx = 1;
														c_list.weighty = 1;
														listPanel.add(new JLabel(uniqueValueList.get(j)), c_list);		
													}
													
													//ScrollPane contains the listPanel
													JScrollPane uniqueValueList_ScrollPanel = new JScrollPane(listPanel);
													TitledBorder border_List = new TitledBorder("List of unique values");
													border_List.setTitleJustification(TitledBorder.CENTER);
													uniqueValueList_ScrollPanel.setBorder(border_List);
													uniqueValueList_ScrollPanel.setPreferredSize(new Dimension(80, 300));
													//---------------------------------------------------------------------------------------------------
													
													//JTextArea contains some info of this column
													JTextArea columnInfo_TArea = new JTextArea();
													columnInfo_TArea.setEditable(false);
													columnInfo_TArea.setLineWrap(true);
													columnInfo_TArea.setWrapStyleWord(true);
													columnInfo_TArea.append("SpectrumLite found " + uniqueValueList.size() + 
															" unique values for this identifier (across " + yieldTable_values.length + " yield tables in your database)."  + "\n");
													
													if (uniqueValueList.size()<=20) {
														columnInfo_TArea.append("'DISCRETE IDENTIFIER' is recommended.");
													} else {
														columnInfo_TArea.append("'RANGE IDENTIFIER' is recommended.");
													}
													//---------------------------------------------------------------------------------------------------
	
													//defineScrollPane for Definition
													defineScrollPane = new JScrollPane();	
													defineScrollPane.setBorder(new TitledBorder(""));
													//---------------------------------------------------------------------------------------------------
												
													//2 radioButtons for DISCRETE or RANGE definition
													JRadioButton radioDISCRETE = new JRadioButton("DISCRETE IDENTIFIER"); 
													JRadioButton radioRANGE = new JRadioButton("RANGE IDENTIFIER"); 
													
													//Add 2 radio to the group
													ButtonGroup definitionGroup = new ButtonGroup();
													definitionGroup.add(radioDISCRETE);
													definitionGroup.add(radioRANGE);
													
													
													//Add listener for radioDISCRETE
													radioDISCRETE.addActionListener(new ActionListener() {
														@Override
														public void actionPerformed(ActionEvent e) {
															if (radioDISCRETE.isSelected()) {
																//Remove all checkBoxes previously added into the Column list (or the dynamic identifier)
																checkboxDynamicIdentifiers.get(currentCheckBoxIndex).clear();
																
																
																JPanel discretePanel = new JPanel();	
																discretePanel.setLayout(new GridBagLayout());
																GridBagConstraints c_dP = new GridBagConstraints();
																c_dP.fill = GridBagConstraints.HORIZONTAL;
																c_dP.weightx = 1;
																c_dP.weighty = 1;
																
																//Add 2 labels
																c_dP.gridx = 0;
																c_dP.gridy = 0;
																discretePanel.add(new JLabel("Unique Value"), c_dP);
																
																c_dP.gridx = 1;
																c_dP.gridy = 0;
																discretePanel.add(new JLabel("Define Name (Below are suggestions from SpectrumLite's library)"), c_dP);
																
																//Add all discrete values and textField for the toolTip
																for (int j = 0; j < uniqueValueList.size(); j++) {
																	String nameOfColumnAndUniqueValue = currentCheckBoxName + " " + uniqueValueList.get(j);	//The name
																								
																	checkboxDynamicIdentifiers.get(currentCheckBoxIndex).add(new JCheckBox(uniqueValueList.get(j)));
																	checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(j).setToolTipText(read_Identifiers.get_ParameterToolTip(nameOfColumnAndUniqueValue));	//ToolTip of this Name from SpectrumLite Library;
																	c_dP.gridx = 0;
																	c_dP.gridy = 1 + j;
																	discretePanel.add(checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(j), c_dP);
																	
																	c_dP.gridx = 1;
																	c_dP.gridy = 1 + j;
																	JTextField name_TF = new JTextField(20);
																	name_TF.setText(read_Identifiers.get_ParameterToolTip(nameOfColumnAndUniqueValue));	//ToolTip of this Name from SpectrumLite Library
																	discretePanel.add(name_TF, c_dP);
																	
																	//Add listener for TextField to be toolTip
																	int jj=j;
																	name_TF.getDocument().addDocumentListener(new DocumentListener() {
																		@Override  
																		public void changedUpdate(DocumentEvent e) {
																			checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(jj).setToolTipText(name_TF.getText());
																		}
																		public void removeUpdate(DocumentEvent e) {
																			checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(jj).setToolTipText(name_TF.getText());
																		}
																		public void insertUpdate(DocumentEvent e) {
																			checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(jj).setToolTipText(name_TF.getText());
																		}
																	});
																}						
																defineScrollPane.setViewportView(discretePanel);	
															}
														}
													});
													
													
													//Add listener for radioRANGE
													radioRANGE.addActionListener(new ActionListener() {
														@Override
														public void actionPerformed(ActionEvent e) {
															if (radioRANGE.isSelected()) {
																//Remove all checkBoxes previously added into the Column list (or the dynamic identifier)
																checkboxDynamicIdentifiers.get(currentCheckBoxIndex).clear();
																
																
																JPanel rangePanel = new JPanel();
																rangePanel.setLayout(new GridBagLayout());
																GridBagConstraints c_dP = new GridBagConstraints();
																c_dP.fill = GridBagConstraints.HORIZONTAL;
																c_dP.weightx = 0;
																c_dP.weighty = 0;
																
																//Add Label and Combo asking for number of ranges
																c_dP.gridx = 0;
																c_dP.gridy = 0;
																rangePanel.add(new JLabel("Number of ranges"), c_dP);
																
																c_dP.gridx = 1;
																c_dP.gridy = 0;
																JComboBox combo = new JComboBox();		
																for (int comboValue = 1; comboValue <= 100; comboValue++) {
																	combo.addItem(comboValue);
																}
																combo.setSelectedItem(null);
																rangePanel.add(combo, c_dP);


																//Add Label and TextField asking for min value
																c_dP.gridx = 0;
																c_dP.gridy = 1;
																rangePanel.add(new JLabel("Min value"), c_dP);
																
																c_dP.gridx = 1;
																c_dP.gridy = 1;
																JTextField min_TF = new JTextField(3);															
																min_TF.setText(uniqueValueList.get(0));
																rangePanel.add(min_TF, c_dP);
																
																
																//Add Label and TextField asking for max value
																c_dP.gridx = 0;
																c_dP.gridy = 2;
																rangePanel.add(new JLabel("Max value"), c_dP);
																
																c_dP.gridx = 1;
																c_dP.gridy = 2;
																JTextField max_TF = new JTextField(3);															
																max_TF.setText(uniqueValueList.get(uniqueValueList.size()-1));
																rangePanel.add(max_TF, c_dP);
																
																
																//add empty label to prevent things move
																c_dP.gridx = 0;
																c_dP.gridy = 3;
																c_dP.weightx = 0;
																c_dP.weighty = 1;
																rangePanel.add(new JLabel(""), c_dP);	
																

																
																//Listener for the combo
															    combo.addActionListener(new AbstractAction() {
															        @Override
															        public void actionPerformed(ActionEvent e) {
															        	try {																        													        	
																        	int numberofRanges = (Integer) combo.getSelectedItem();	        	
																        	double minValue = Double.parseDouble(min_TF.getText());
																        	double maxValue = Double.parseDouble(max_TF.getText());
															        
																        	if (minValue <= maxValue) {
																	        	//Remove all checkBoxes previously added into the Column list (or the dynamic identifier)
																				checkboxDynamicIdentifiers.get(currentCheckBoxIndex).clear();
																				rangePanel.removeAll();
																																									
																	        	
																	        	c_dP.weightx = 1;
																				c_dP.weighty = 0;
																	        	
																	        	//Add Label and Spinner asking for number of ranges
																				c_dP.gridx = 0;
																				c_dP.gridy = 0;
																				rangePanel.add(new JLabel("Number of ranges"), c_dP);
																				
																				c_dP.gridx = 1;
																				c_dP.gridy = 0;
																				rangePanel.add(combo, c_dP);
		
																				//Add 4 labels
																				c_dP.gridx = 0;
																				c_dP.gridy = 1;
																				rangePanel.add(new JLabel("Range"), c_dP);
																				
																				c_dP.gridx = 1;
																				c_dP.gridy = 1;
																				rangePanel.add(new JLabel("From"), c_dP);
																				
																				c_dP.gridx = 2;
																				c_dP.gridy = 1;
																				rangePanel.add(new JLabel("To"), c_dP);
																				
																				c_dP.gridx = 3;
																				c_dP.gridy = 1;
																				rangePanel.add(new JLabel("Define name of this range"), c_dP);	
																	        
																			
																	        	//Add all ranges and textField for the toolTip
																				for (int j = 0; j < numberofRanges; j++) {																									
																					String valueFrom = String.format("%.2f", minValue + (maxValue-minValue)/numberofRanges*j);
																					String valueTo = String.format("%.2f", minValue + (maxValue-minValue)/numberofRanges*(j+1));
																					
																					checkboxDynamicIdentifiers.get(currentCheckBoxIndex).add(new JCheckBox("[" + valueFrom + "," + valueTo + ")"));
																					c_dP.gridx = 0;
																					c_dP.gridy = 2 + j;
																					rangePanel.add(checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(j), c_dP);
																			
																					c_dP.gridx = 1;
																					c_dP.gridy = 2 + j;
																					JTextField from_TF = new JTextField(3);
																					
																					from_TF.setText(valueFrom);
																					rangePanel.add(from_TF, c_dP);
																					
																					c_dP.gridx = 2;
																					c_dP.gridy = 2 + j;
																					JTextField to_TF = new JTextField(3);
																					to_TF.setText(valueTo);
																					rangePanel.add(to_TF, c_dP);
																					
																					c_dP.gridx = 3;
																					c_dP.gridy = 2 + j;
																					JTextField name_TF = new JTextField(20);
																					name_TF.setText("");	//ToolTip text = ""
																					rangePanel.add(name_TF, c_dP);
																					
																					//Add listener for TextField to be toolTip
																					int jj=j;
																					name_TF.getDocument().addDocumentListener(new DocumentListener() {
																						@Override  
																						public void changedUpdate(DocumentEvent e) {
																							checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(jj).setToolTipText(name_TF.getText());
																						}
																						public void removeUpdate(DocumentEvent e) {
																							checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(jj).setToolTipText(name_TF.getText());
																						}
																						public void insertUpdate(DocumentEvent e) {
																							checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(jj).setToolTipText(name_TF.getText());
																						}
																					});
																				}
																				
																				
																				c_dP.gridx = 4;
																				c_dP.gridy = 2 + numberofRanges;
																				c_dP.weightx = 1;
																				c_dP.weighty = 1;
																				rangePanel.add(new JLabel(""), c_dP);  //add empty label to make everything not move
																	        	
																				
																	        	// Apply change to the GUI
																				defineScrollPane.setViewportView(rangePanel);	
																        	} else {
																        		JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(), "'Min value' must be less than or equal to 'Max value'");														        																							
																        	}
																		} catch (Exception ee)  {
														        		JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(), "'Min value' and 'Max value' must be numbers");														        		
																		}
																	}
															    });
		
																defineScrollPane.setViewportView(rangePanel);
															}
														}
													});
													//---------------------------------------------------------------------------------------------------
											
													//Add all to the popupPanel												
													
													//Add columnInfo_TArea to popupPanel
													c_popup.gridx = 0;
													c_popup.gridy = 0;
													c_popup.gridwidth = 3;
													c_popup.gridheight = 1;
													c_popup.weightx = 1;
													c_popup.weighty = 0;
													popupPanel.add(columnInfo_TArea, c_popup);
																						
													//Add uniqueValueList_ScrollPanel to popupPanel
													c_popup.gridx = 0;
													c_popup.gridy = 1;
													c_popup.gridwidth = 1;
													c_popup.gridheight = 2;
													c_popup.weightx = 1;
													c_popup.weighty = 1;
													popupPanel.add(uniqueValueList_ScrollPanel, c_popup);
													
													//Add 2 radios to popupPanel
													c_popup.gridx = 1;
													c_popup.gridy = 1;
													c_popup.gridwidth = 1;
													c_popup.gridheight = 1;
													c_popup.weightx = 1;
													c_popup.weighty = 0;
													popupPanel.add(radioDISCRETE, c_popup);
													
													c_popup.gridx = 2;
													c_popup.gridy = 1;
													c_popup.gridwidth = 1;
													c_popup.gridheight = 1;
													c_popup.weightx = 1;
													c_popup.weighty = 0;
													popupPanel.add(radioRANGE, c_popup);
													
													//Add defineScrollPane to popupPanel
													c_popup.gridx = 1;
													c_popup.gridy = 2;
													c_popup.gridwidth = 2;
													c_popup.gridheight = 1;
													c_popup.weightx = 1;
													c_popup.weighty = 1;
													popupPanel.add(defineScrollPane, c_popup);
													//---------------------------------------------------------------------------------------------------
													
													int response = JOptionPane.showConfirmDialog(Spectrum_Main.mainFrameReturn(), popupPanel,
															"Add   '" + currentCheckBoxName + "'   to the set of dynamic identifiers ?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
													if (response == JOptionPane.NO_OPTION) {
														allDynamicIdentifiers.get(currentCheckBoxIndex).setSelected(false);
													} else if (response == JOptionPane.YES_OPTION && checkboxDynamicIdentifiers.get(currentCheckBoxIndex).size()>0) {
														//Deselect the No Identifier checkBox
														checkboxNoIdentifier.setSelected(false);
														
														//Set the identifier ScrollPane visible
														allDynamicIdentifiers_ScrollPane.get(currentCheckBoxIndex).setVisible(true);
														
														
														//create a temporary Panel contains all checkboxes of that column 
														JPanel tempPanel = new JPanel();
														tempPanel.setLayout(new GridBagLayout());
														GridBagConstraints c_temp = new GridBagConstraints();
														c_temp.fill = GridBagConstraints.HORIZONTAL;
														c_temp.weightx = 1;
														c_temp.weighty = 1;
														
														for (int j = 0; j < checkboxDynamicIdentifiers.get(currentCheckBoxIndex).size(); j++) {
															c_temp.gridx = 1;
															c_temp.gridy = j;
															tempPanel.add(checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(j), c_temp);
														}
			
														//Set Scroll Pane view to the tempPanel
														allDynamicIdentifiers_ScrollPane.get(currentCheckBoxIndex).setViewportView(tempPanel);
														
													} else if (response == JOptionPane.CLOSED_OPTION) {
														allDynamicIdentifiers.get(currentCheckBoxIndex).setSelected(false);
													} else {
														allDynamicIdentifiers.get(currentCheckBoxIndex).setSelected(false);
													}
												
												} else {	//if checkbox is not selected then remove the identifier ScrollPane
													allDynamicIdentifiers_ScrollPane.get(currentCheckBoxIndex).setVisible(false);
												}
											
												//Do a resize to same size for JInteral Frame of the project to help repaint the identifier ScrollPane added or removed					
												Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());
											}
										});
									}		
								}
											
								//Do a resize to same size for JInteral Frame of the project to help repaint the checkboxes added					
								Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	
							}
						}
					});		
					
					
					

					
	
					//ScrollPane contains the identifiers that are able to be selected
					JScrollPane selectIdentifiersScrollPanel = new JScrollPane(select_Panel);
					TitledBorder border3_2 = new TitledBorder("Select Identifiers");
					border3_2.setTitleJustification(TitledBorder.CENTER);
					selectIdentifiersScrollPanel.setBorder(border3_2);
					selectIdentifiersScrollPanel.setPreferredSize(new Dimension(200, 100));
					
					//Add the above ScrollPane
					c3.gridx = 0;
					c3.gridy = 0;
					dynamic_identifiersPanel.add(selectIdentifiersScrollPanel, c3);
					
					
					//Add dynamic_identifiersPanel to this Class which is a mother JSCrollPanel
					setViewportView(dynamic_identifiersPanel);
				}
			}
			
			
			checkbox_dynamicScrollPanel dynamic_identifiersScrollPanel = new checkbox_dynamicScrollPanel("Get identifiers from yield table columns", 2);
			TitledBorder border3_1 = new TitledBorder("Dynamic identifiers for PARAMETERS (from yield table)");
			border3_1.setTitleJustification(TitledBorder.CENTER);
			dynamic_identifiersScrollPanel.setBorder(border3_1);
			dynamic_identifiersScrollPanel.setPreferredSize(new Dimension(250, 100));	
			// End of 4th Grid -----------------------------------------------------------------------
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
					if (parametersScrollPanel.checkboxParameter != null) {		//only allow this "SET INFO" when checkBoxes are already created
						
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
								if (parametersScrollPanel.checkboxParameter.get(j).isSelected()) {			//add the index of selected Columns to this String
									parameterConstraintColumn = parameterConstraintColumn + j + " ";
								}
							}
							
							if (parameterConstraintColumn.equals("") || parametersScrollPanel.checkboxNoParameter.isSelected()) {
								parameterConstraintColumn = "NoParameter";		//= parametersScrollPanel.checkboxNoParameter.getText();
							}
							
							data2[i][6] = parameterConstraintColumn;
							
							
							//add constraint info at column 7 "Static Identifiers"
							String staticIdentifiersColumn = "";
							for (int ii = 0; ii < total_staticIdentifiers; ii++) {		//Loop all layers
								staticIdentifiersColumn = staticIdentifiersColumn + ii + " ";
								for (int j = 0; j < allLayers.get(ii).size(); j++) {		//Loop all elements in each layer
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
									
									//Add checkBox if it is selected or disable
									if (checkboxStaticIdentifiers.get(ii).get(j).isSelected() || !checkboxStaticIdentifiers.get(ii).get(j).isEnabled())	
										staticIdentifiersColumn = staticIdentifiersColumn + checkboxName + " "	;
								}
								staticIdentifiersColumn = staticIdentifiersColumn + ";";
							}	
							data2[i][7] = staticIdentifiersColumn;
							
							
							//add constraint info at column 8 "dynamic Identifiers"
							String dynamicIdentifiersColumn = "";
							for (int ii = 0; ii < dynamic_identifiersScrollPanel.allDynamicIdentifiers_ScrollPane.size(); ii++) {		//Loop all dynamic identifier ScrollPanes
								if (dynamic_identifiersScrollPanel.allDynamicIdentifiers_ScrollPane.get(ii).isVisible() &&
										dynamic_identifiersScrollPanel.checkboxDynamicIdentifiers.get(ii).size() > 0) {			//get the active identifiers (when identifier ScrollPane is visible and List size >0)
									dynamicIdentifiersColumn = dynamicIdentifiersColumn + ii + " ";
									for (int j = 0; j < dynamic_identifiersScrollPanel.checkboxDynamicIdentifiers.get(ii).size(); j++) { //Loop all checkBoxes in this active identifier
										String checkboxName = dynamic_identifiersScrollPanel.checkboxDynamicIdentifiers.get(ii).get(j).getText();									
										//Add checkBox if it is selected or disable
										if (dynamic_identifiersScrollPanel.checkboxDynamicIdentifiers.get(ii).get(j).isSelected()
												|| !dynamic_identifiersScrollPanel.checkboxDynamicIdentifiers.get(ii).get(j).isEnabled())
											dynamicIdentifiersColumn = dynamicIdentifiersColumn + checkboxName + " ";
									}
									dynamicIdentifiersColumn = dynamicIdentifiersColumn + ";";
								}
							}	
							
							if (dynamicIdentifiersColumn.equals("") || dynamic_identifiersScrollPanel.checkboxNoIdentifier.isSelected()) {
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
			class comboBox_ConstraintType extends JComboBox {	
				public comboBox_ConstraintType() {
				addItem("HARD");
				addItem("SOFT");	
				setSelectedIndex(0);
				}
			}
			
		
			rowCount2 = 10000;
			colCount2 = 9;
			data2 = new Object[rowCount2][colCount2];
			columnNames2 = new String[] { "Const. Description (optional)", "Const. Type", "LB Value", "Penalty/Unit < LB (SOFT)", "UB Value", "Penalty/Unit > UB (SOFT)", "PARAMETERS Index", "Static identifiers for VARIABLES", "Dynamic identifiers for PARAMETERS"};
	         
			// Create a table
			model2 = new MyTableModel2();
			table2 = new JTable(model2) {
				@Override			//These override is to make the width of the cell fit all contents of the cell
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					// For the cells in table								
					Component component = super.prepareRenderer(renderer, row, column);
					int rendererWidth = component.getPreferredSize().width;
					TableColumn tableColumn = getColumnModel().getColumn(column);
					int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
					
					// For the column names
					TableCellRenderer renderer2 = table.getTableHeader().getDefaultRenderer();	
					Component component2 = renderer2.getTableCellRendererComponent(table,
				            tableColumn.getHeaderValue(), false, false, -1, column);
					maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
					
					tableColumn.setPreferredWidth(maxWidth);
					return component;
				}
				
//	             //Implement table cell tool tips           
//	             public String getToolTipText(MouseEvent e) {
//	                 String tip = null;
//	                 java.awt.Point p = e.getPoint();
//	                 int rowIndex = rowAtPoint(p);
//	                 int colIndex = columnAtPoint(p);
//	                 try {
//	                       tip = getValueAt(rowIndex, colIndex).toString();
//	                 } catch (RuntimeException e1) {
//	                	 System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//	                 }
//	                 return tip;
//	             }		
			};



	         

	        // Set up Types for each table2 Columns
			table2.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new comboBox_ConstraintType()));
				
				
	         
	         
	         
	        //table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//			table2.getColumnModel().getColumn(0).setPreferredWidth(250);
//			table2.getColumnModel().getColumn(colCount2-7).setPreferredWidth(150);	
//			table2.getColumnModel().getColumn(colCount2-6).setPreferredWidth(150);	
//			table2.getColumnModel().getColumn(colCount2-5).setPreferredWidth(150);	
//	        table2.getColumnModel().getColumn(colCount2-4).setPreferredWidth(150);	//Set width of Column "Penalty/Unit (Soft Const.)" bigger
//	        table2.getColumnModel().getColumn(colCount2-3).setPreferredWidth(150);	//Set width of Column "PARAMETERS" bigger
//	        table2.getColumnModel().getColumn(colCount2-2).setPreferredWidth(300);	//Set width of Column "Static identifiers for VARIABLES" bigger
//	        table2.getColumnModel().getColumn(colCount2-1).setPreferredWidth(300);	//Set width of Column "Dynamic identifiers for PARAMETERS" bigger
//			table2.setPreferredScrollableViewportSize(new Dimension(1500, 200));
			table2.setAutoResizeMode(0);
			table2.setFillsViewportHeight(true);
			table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
			TableRowSorter<MyTableModel2> sorter2 = new TableRowSorter<MyTableModel2>(model2);	//Add sorter
			table2.setRowSorter(sorter2);
			
			
		    MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();

		    //set TableCellRenderer into a specified JTable column class
		    table2.setDefaultRenderer(String[].class, renderer);

		    //or, set TableCellRenderer into a specified JTable column
		    table2.getColumnModel().getColumn(0).setCellRenderer(renderer);
			
   
		    
		    
			// Create the scroll pane and add the constraintTablePanel to it.
			JScrollPane constraints_ScrollPane = new JScrollPane();
			constraints_ScrollPane.setViewportView(table2);
			constraints_ScrollPane.setPreferredSize(new Dimension(800, 250));
	         
	         
	         //Create a JPanel and Add the scrollPane to this panel.
			JPanel constraintTablePanel = new JPanel(new BorderLayout(0, 0));
			TitledBorder border5 = new TitledBorder("Constraints Information");
			border5.setTitleJustification(TitledBorder.CENTER);
			constraintTablePanel.setBorder(border5);
			constraintTablePanel.add(constraints_ScrollPane);
	         		
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
		    	    		    
		    // Add the constraintTablePanel to the main Grid
			c.gridx = 1;
			c.gridy = 1;
			c.gridwidth = 2; 
			c.gridheight = 3;
			c.weightx = 1;
		    c.weighty = 1;
			super.add(constraintTablePanel, c);
		}
	}

	
	class PaneL_UserConstraints_Text  extends JTextArea {
		public PaneL_UserConstraints_Text() {
			setRows(50);		// set text areas with 10 rows when starts		
		}
	}
	

	class MyTableModel2 extends AbstractTableModel {
   	 
		public MyTableModel2() {

		  }

		public int getColumnCount() {
			return colCount2;
		}

		public int getRowCount() {
			return rowCount2;
		}

		public String getColumnName(int col) {
			return columnNames2[col];
		}

		public Object getValueAt(int row, int col) {
			return data2[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
	         
		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
			if (c==0) return String.class;      //column 0 accepts only String
			else if (c>=2 && c<=5) return Double.class;      //column 2 to 5 accept only Double values    
	        else return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col >= colCount2 - 3) { //  The last 3 columns are un-editable
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			data2[row][col] = value;
			fireTableCellUpdated(row, col);
			
//			data2[row][0] = row;
//			fireTableCellUpdated(row, 0);
			
			fireTableDataChanged();
			repaint();
		}
	}	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        //make multi line where the cell value is String[]
	        if (value instanceof String[]) {
	            setListData((String[]) value);
	        }

	        //cell backgroud color when selected
	        if (isSelected) {
	            setBackground(UIManager.getColor("Table.selectionBackground"));
	        } else {
	            setBackground(UIManager.getColor("Table.background"));
	        }

	        return this;
	    }
	}	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	public File getGeneralInputFile() {
		File generalInputFile = new File("Input 1 - GeneralInputs.txt");
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(generalInputFile))) {
			// Write info
			fileOut.write("Input Description" + "\t" + "Selected Option");
			fileOut.newLine();
			fileOut.write(panelInput1_TEXT.getText());
//			panelInput0_TEXT.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return generalInputFile;
	}
	
	public File getSelectedStrataFile() {
		File selectedStrataFile = new File("Input 2 - SelectedStrata.txt");	
		//Only print out Strata with implemented methods <> null
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(selectedStrataFile))) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				fileOut.write(table.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table.getRowCount(); i++) {
				if ((Object) table.getValueAt(i, table.getColumnCount()-1)!=null  &&  (Object) table.getValueAt(i, table.getColumnCount()-1)!="") {		//IF there is method set up for this strata
					fileOut.newLine();
					for (int j = 0; j < table.getColumnCount(); j++) {
						fileOut.write((Object) (table.getValueAt(i, j)) + "\t");
					}
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return selectedStrataFile;
	}
	
	public File getRequirementsFile() {
		//Only print out if the last column Allowed Options <> null
		File requirementsFile = new File("Input 3 - UniversalRequirements.txt");	
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(requirementsFile))) {
			for (int j = 0; j < table4.getColumnCount(); j++) {
				fileOut.write(table4.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table4.getRowCount(); i++) {
				if ((Object) table4.getValueAt(i, table4.getColumnCount()-1)!=null  &&  (Object) table4.getValueAt(i, table4.getColumnCount()-1)!="") {		//IF there is method set up for this strata
					fileOut.newLine();
					for (int j = 0; j < table4.getColumnCount(); j++) {
						fileOut.write((Object) (table4.getValueAt(i, j)) + "\t");
					}
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return requirementsFile;
	}

	public File getMSFireFile() {
		File MSFireFile = new File("Input 4 - MSFire.txt");	
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(MSFireFile))) {
			for (int j = 0; j < table5.getColumnCount(); j++) {
				fileOut.write(table5.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table5.getRowCount(); i++) {
				fileOut.newLine();
				for (int j = 0; j < table5.getColumnCount(); j++) {
					fileOut.write((Object) (table5.getValueAt(i, j)) + "\t");
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return MSFireFile;
	}	
	
	public File getSRDisturbancesFile() {
		File SRDisturbancesFile = new File("Input 5 - SRDisturbances.txt");	
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(SRDisturbancesFile))) {
			for (int j = 0; j < table6.getColumnCount(); j++) {
				fileOut.write(table6.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table6.getRowCount(); i++) {
				fileOut.newLine();
				for (int j = 0; j < table6.getColumnCount(); j++) {
					fileOut.write((Object) (table6.getValueAt(i, j)) + "\t");
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return SRDisturbancesFile;
	}	
	
	public File getUserConstraintsFile() {
		File userConstraintsFile = new File("Input 6 - UserConstraints.txt");
		
		//Only print out rows if columns  1, 2 or 4, 6, 7, 8 <> null
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userConstraintsFile))) {
			for (int j = 0; j < table2.getColumnCount(); j++) {
				fileOut.write(table2.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table2.getRowCount(); i++) {
				boolean checkValidity = true;
				for (int j = 1; j < table2.getColumnCount(); j++) {
					if (j==1 || (j==2 && j==4) || j==6 || j==7 || j==8) {
						if ((Object) (table2.getValueAt(i, j)) == null || (Object) (table2.getValueAt(i, j)) == "")		checkValidity = false;	
					}
				}
								
				if (checkValidity == true) { // if columns  1, 2 or 4, 6, 7, 8 <> null then write to file
					fileOut.newLine();
					for (int j = 0; j < table2.getColumnCount(); j++) {
						fileOut.write((Object) (table2.getValueAt(i, j)) + "\t");
					}
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return userConstraintsFile;	
	}

	public File getSRDRequirementsFile() {
		File SRDrequirementsFile = new File("Input 7 - SRDRequirements.txt");	
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(SRDrequirementsFile))) {
			for (int j = 0; j < table7.getColumnCount(); j++) {
				fileOut.write(table7.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table7.getRowCount(); i++) {
				fileOut.newLine();
				for (int j = 0; j < table7.getColumnCount(); j++) {
					fileOut.write((Object) (table7.getValueAt(i, j)) + "\t");
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return SRDrequirementsFile;
	}
	
	public File getDatabaseFile() {	
		File databaseFile = new File("database.db");
		try {
			Files.copy(file_Database.toPath(),databaseFile.toPath());
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return databaseFile;	
	}	
}
