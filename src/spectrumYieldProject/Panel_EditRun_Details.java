package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
import javax.swing.ToolTipManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

public class Panel_EditRun_Details extends JLayeredPane implements ActionListener {
	private JSplitPane GUI_Text_splitPanel ;
	private JPanel radioPanel_Right; 
	private ButtonGroup radioGroup_Right; 
	private JRadioButton[] radioButton_Right; 
	private File fileManagementUnit, fileDatabase;
	
	//6 panels for the selected Run
	private PaneL_General_Inputs_GUI panelInput0_GUI;
	private PaneL_General_Inputs_Text panelInput0_TEXT;
	private PaneL_ManagementOptions_GUI panelInput1_GUI;
	private PaneL_ManagementOptions_Text panelInput1_TEXT;
	private PaneL_UserConstraints_GUI panelInput2_GUI;
	private PaneL_UserConstraints_Text panelInput2_TEXT;		

	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	MyTableModel model;
	Object[][] data;
//	= {
//			{ "BNNCAL", "23", "NG", new Integer(5), new Boolean(false) },
//			{ "BNNCDM", "432", "NG", new Integer(3), new Boolean(true) },
//			{ "BNSHCL", "546", "NG", new Integer(2), new Boolean(false) },
//			{ "BNPHCL", "123", "NG", new Integer(20), new Boolean(true) },
//			{ "BNOHCM", "768", "NG", new Integer(10), new Boolean(false) } };
	private JCheckBox[][] ConversionCheck_To;
	
	public Panel_EditRun_Details() {
		super.setLayout(new BorderLayout());

		// Add 3 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
		radioPanel_Right = new JPanel();
		radioPanel_Right.setLayout(new FlowLayout());		
		radioGroup_Right = new ButtonGroup();
		
		radioButton_Right  = new JRadioButton[3];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[1]= new JRadioButton("Management Options");
		radioButton_Right[2]= new JRadioButton("User Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < 3; i++) {
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
		panelInput0_GUI = new PaneL_General_Inputs_GUI();
		panelInput0_TEXT = new PaneL_General_Inputs_Text();
		panelInput1_GUI = new PaneL_ManagementOptions_GUI();
		panelInput1_TEXT = new PaneL_ManagementOptions_Text();
		panelInput2_GUI = new PaneL_UserConstraints_GUI();
		panelInput2_TEXT = new PaneL_UserConstraints_Text();
					
		
		// Show the 2 panelInput of the selected Run
		GUI_Text_splitPanel.setLeftComponent(panelInput0_GUI);
		GUI_Text_splitPanel.setRightComponent(panelInput0_TEXT);	
		
		
		// Add all components to The Panel------------------------------------------------------------
		super.add(radioPanel_Right, BorderLayout.NORTH);
		super.add(GUI_Text_splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_EditRun_Details()

	
	// Listener for radio buttons----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < 3; j++) {
					if (radioButton_Right[j].isSelected()) {			
						if (j == 0) {
							GUI_Text_splitPanel.setLeftComponent(panelInput0_GUI);
							GUI_Text_splitPanel.setRightComponent(panelInput0_TEXT);
						} else if (j == 1) {
							GUI_Text_splitPanel.setLeftComponent(panelInput1_GUI);
							GUI_Text_splitPanel.setRightComponent(panelInput1_TEXT);
						} else if (j == 2) {
							GUI_Text_splitPanel.setLeftComponent(panelInput2_GUI);
							GUI_Text_splitPanel.setRightComponent(panelInput2_TEXT);
						}				
					}
				}
			}

	// Panel General Inputs-----------------------------------------------------------------------------	
	class PaneL_General_Inputs_GUI extends JLayeredPane {
		public PaneL_General_Inputs_GUI() {
			setLayout(new GridLayout(0,4,30,0));		//2 last numbers are the gaps 			
			
			JLabel label1 = new JLabel("Number of planning periods");
			JComboBox combo1 = new JComboBox();		
			for (int i = 1; i <= 50; i++) {
				combo1.addItem(i);
			}
			combo1.setSelectedItem((int) 5);
			super.add(label1);
			super.add(combo1);
			
			JLabel label2 = new JLabel("Solving time limit (minutes)");
			JSpinner spin2 = new JSpinner (new SpinnerNumberModel(5, 0, 60, 1));
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
					panelInput0_TEXT.setText(input0_info);
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
					panelInput0_TEXT.setText(input0_info);
		        }
		    });
		}
	}

	class PaneL_General_Inputs_Text extends JTextArea {
		public PaneL_General_Inputs_Text() {		
			setRows(10);		// set text areas with 10 rows when starts	
		}
	}

	
	
	// Panel Rules-----------------------------------------------------------------------------------
	class PaneL_ManagementOptions_GUI extends JLayeredPane implements ActionListener {
		// Define 28 check box for 6 layers
		JCheckBox[] checkboxRule;
		List<List<JCheckBox>> checkboxFilter;
		
		public PaneL_ManagementOptions_GUI() {
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
			

			// 1st grid line 1----------------------
			JLabel label1 = new JLabel("Strata (.csv file)");
			c0.gridx = 0;
			c0.gridy = 0;
			importPanel.add(label1, c0);
			
			JTextField textField1 = new JTextField(35);
			textField1.setEditable(false);
			c0.gridx = 1;
			c0.gridy = 0;
			c0.gridwidth = 4;
			importPanel.add(textField1, c0);
			
			JButton button1 = new JButton("Import Strata");
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fileManagementUnit = FilesChooser_Units.chosenManagementunit();				
					if (fileManagementUnit!=null) {
						textField1.setText(fileManagementUnit.getAbsolutePath());
						// Read the whole text file into table
						ReadUnit read = new ReadUnit();
						read.readValues(fileManagementUnit);
						String[][] value = read.getValues();
						rowCount = read.get_TotalRows();
						colCount = read.get_TotalColumns() + 2; //the 2 prescriptions Column
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
						columnNames[colCount - 2] = "Available Methods/Prescriptions";
						columnNames[colCount - 1] = "Methods for Implementation";
						table.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
						table.getColumnModel().getColumn(colCount-3).setPreferredWidth(120);	//Set width of Column "Total area" bigger
				        table.getColumnModel().getColumn(colCount-2).setPreferredWidth(350);	//Set width of Column "Available Methods/Prescriptions" bigger
				        table.getColumnModel().getColumn(colCount-1).setPreferredWidth(200);	//Set width of Column "Methods for Implementation" bigger
				        
					}
				}
			});
			c0.gridx = 5;
			c0.gridy = 0;
			importPanel.add(button1, c0);

			
			// 1st grid line 2----------------------------
			JLabel label2 = new JLabel("Database (.db file)");
			c0.gridx = 0;
			c0.gridy = 1;
			importPanel.add(label2, c0);

			JTextField textField2 = new JTextField(35);
			textField2.setEditable(false);
			c0.gridx = 1;
			c0.gridy = 1;
			c0.gridwidth = 4;
			importPanel.add(textField2, c0);

			JButton button2 = new JButton("Import Database");
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fileDatabase = FilesChooser_Database.chosenDatabase();				
					if (fileDatabase!=null) {
						textField2.setText(fileDatabase.getAbsolutePath());
						// Read the database tables into array
						Read_DatabaseTables read_DatabaseTables = new Read_DatabaseTables();
						Object[][][] table = read_DatabaseTables.getTableArrays(fileDatabase);
					}
				}
			});
			c0.gridx = 5;
			c0.gridy = 1;
			importPanel.add(button2, c0);
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------
			
				
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------						
			Read_Indentifiers read_Identifiers = new Read_Indentifiers();
			
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
					checkboxFilter.get(i).get(j).addActionListener(this);
					
					c1.gridx = i;
					c1.gridy = j + 1;
					checkPanel.add(checkboxFilter.get(i).get(j), c1);
				}
			}
			
			int lastRow = 0;
			for (int i = 0; i < allLayers.size(); i++) {		//Loop all layers
				for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
					if (j+1>lastRow) lastRow = j+1;
				}
			}
			
			
			
			//Add 2 buttons for select all and de-select all		
			JButton selectAll = new JButton("Select All");
			selectAll.addActionListener(this);
			selectAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					for (int i = 0; i < allLayers.size(); i++) {		//Loop all layers
						for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
							checkboxFilter.get(i).get(j).setSelected(true);
						}
					}
				}
			});
			c1.gridx = 0;
			c1.gridy = lastRow + 1;
			c1.gridwidth = 2;
			checkPanel.add(selectAll, c1);
			
			JButton deselectAll = new JButton("De-Select All");
			deselectAll.addActionListener(this);
			deselectAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					for (int i = 0; i < allLayers.size(); i++) {		//Loop all layers
						for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
							checkboxFilter.get(i).get(j).setSelected(false);
						}
					}	
				}
			});
			c1.gridx = allLayers.size()-2;
			c1.gridy = lastRow + 1;
			c1.gridwidth = 2;
			checkPanel.add(deselectAll, c1);
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------

			
			
			
			// 3rd grid -----------------------------------------------------------------------
			// 3rd grid -----------------------------------------------------------------------
			checkboxRule = new JCheckBox[5];
			for (int i = 1; i <= 4; i++) {
				checkboxRule[i] = new JCheckBox();
				checkboxRule[i].setSelected(false);
			}
			
			
			JPanel ruleEditorPanel = new JPanel();		
			TitledBorder border2 = new TitledBorder("Silvicultural Methods");
			border2.setTitleJustification(TitledBorder.CENTER);
			ruleEditorPanel.setBorder(border2);
			ruleEditorPanel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.HORIZONTAL;
			c2.weightx = 1;
		    c2.weighty = 1;
			
				
			// 1st line inside ruleEditorPanel
		    checkboxRule[1].setText("Even Age (EA) - Cover Type conversions below are allowed for all strata");
		    c2.gridx = 0;
			c2.gridy = 0;
			c2.gridwidth = 8;
			ruleEditorPanel.add(checkboxRule[1], c2);
			
			
			int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
		  
			//Labels for Cover Type From
			for (int i = 0; i < total_CoverType; i++) {
				JLabel labelConvert = new JLabel("        " + allLayers.get(4).get(i) + "      regenerated as     ");
				c2.gridx = 0;
				c2.gridy = i + 1;
				c2.gridwidth = 2;
				ruleEditorPanel.add(labelConvert, c2);
			}
			
			//CheckBox for Cover Type To
			int startx = 1;
			ConversionCheck_To = new JCheckBox[total_CoverType][total_CoverType];
		    for (int i = 0; i < total_CoverType; i++) {
		    	   for (int j = 0; j < total_CoverType; j++) {
				    	ConversionCheck_To[i][j] = new JCheckBox(allLayers.get(4).get(j));
				    	ConversionCheck_To[i][j].setToolTipText(allLayers_ToolTips.get(4).get(j));
				    	c2.gridx = startx+ j + 1;
						c2.gridy = i+1;
						c2.gridwidth = 1;
						ruleEditorPanel.add(ConversionCheck_To[i][j], c2);
						if (i==j) ConversionCheck_To[i][j].setSelected(true);
					}
			}
		    		
			
			// 2nd line inside ruleEditorPanel
			checkboxRule[2].setText("Group Selection (GS)");					
			c2.gridx = 0;
			c2.gridy = total_CoverType + 1;
			ruleEditorPanel.add(checkboxRule[2], c2);
			
			
			// 3rd line inside ruleEditorPanel
			checkboxRule[3].setText("Prescribed Burn (PB)");
			c2.gridx = 0;
			c2.gridy = total_CoverType + 2;
			ruleEditorPanel.add(checkboxRule[3], c2);
			
			
			// 4th line inside ruleEditorPanel
			checkboxRule[4].setText("Natural Growth (NG)");
			c2.gridx = 0;
			c2.gridy = total_CoverType + 3;
			ruleEditorPanel.add(checkboxRule[4], c2);

			
			// 5th line inside ruleEditorPanel: Apply Button
			JButton applyRule = new JButton("Set methods for implementation on the selected strata below");
			applyRule.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					String applyText = "";
					if (checkboxRule[1].isSelected()) {
						applyText = applyText  + "EA ";
					}
					if (checkboxRule[2].isSelected()) {
						applyText = applyText  + "GS ";
					}
					if (checkboxRule[3].isSelected()) {
						applyText = applyText  + "PB ";
					}
					if (checkboxRule[4].isSelected()) {
						applyText = applyText  + "NG "; 
					}
					
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
				}
			});
			c2.gridx = 0;
			c2.gridy = total_CoverType + 4;
			c2.gridwidth = total_CoverType + 1;	//GridBagConstraints.REMAINDER; 	//8 columns wide because there are 7 cover types
			ruleEditorPanel.add(applyRule, c2);
			
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
			
			// Add the 3rd grid - ruleEditorPanel to the main Grid	
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 2;
			super.add(ruleEditorPanel, c);
		}
		
		//Listeners for checkBox Filter--------------------------------------------------------------------
		public void actionPerformed(ActionEvent e) {

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

	class PaneL_ManagementOptions_Text extends JLayeredPane {
	    public PaneL_ManagementOptions_Text() {
	         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    	
	         rowCount = 30;
	         colCount = 10;
	         data = new Object[rowCount][colCount];
	         columnNames= new String[] {"Strata ID" , "Layer 1", "Layer 2", "Layer 3", "Layer 4", "Layer 5", "Layer 6", 
	 				"Total area (acres)", "Available Methods/Prescriptions", "Methods for Implementation"};
	         
			// Populate the data matrix without any information
			for (int row = 0; row < 1; row++) {			// 1 row is ok
				for (int col = 0; col < colCount; ++col) {		//Number of Columns must match
					data[row][col] = "";
				}
			}
	    	
	    	
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
	         table.getColumnModel().getColumn(colCount-3).setPreferredWidth(120);	//Set width of Column "Total area" bigger
	         table.getColumnModel().getColumn(colCount-2).setPreferredWidth(350);	//Set width of Column "Available Methods/Prescriptions" bigger
	         table.getColumnModel().getColumn(colCount-1).setPreferredWidth(200);	//Set width of Column "Methods for Implementation" bigger
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
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < colCount - 1) { // Only the last column is editable
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
			fireTableDataChanged();
			repaint();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Panel Constraints-----------------------------------------------------------------------------------
	class PaneL_UserConstraints_GUI extends JLayeredPane {
		public PaneL_UserConstraints_GUI() {
			
			
	
		}
	}
	
	class PaneL_UserConstraints_Text  extends JTextArea {
		public PaneL_UserConstraints_Text() {
			setRows(50);		// set text areas with 10 rows when starts		
		}
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	public File getGeneralInputFile() {
		File generalInputFile = new File("GeneralInputs.txt");
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(generalInputFile))) {
			panelInput0_TEXT.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return generalInputFile;
	}
	
	public File getManagementOptionsFile() {
		File managementOptionsFile = new File("ManagementOptions.txt");	
		//Only print out Strata with implemented methods <> null
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(managementOptionsFile))) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				fileOut.write(table.getColumnName(j) + "\t");
			}
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.getValueAt(i, table.getColumnCount()-1)!=null) {		//IF there is method set up for this strata
					fileOut.newLine();
					for (int j = 0; j < table.getColumnCount(); j++) {
						fileOut.write((String) (table.getValueAt(i, j)) + "\t");
					}
				}
			}
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return managementOptionsFile;
	}
	
	public File getCoverTypeConversionsFile() {
		File coverTypeConversionsFile = new File("CoverTypeConversions.txt");	
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(coverTypeConversionsFile))) {
			// Write info in the GUI Cover Type Conversions
			for (int i = 0; i < ConversionCheck_To.length; i++) {
				for (int j = 0; j < ConversionCheck_To[i].length; j++) {
					if (ConversionCheck_To[i][j].isSelected()) {
						String coverTypeConversion_info = ConversionCheck_To[i][i].getText() + " ";		//From this Cover Type
						coverTypeConversion_info = coverTypeConversion_info + ConversionCheck_To[i][j].getText();		//To this Cover Type
						fileOut.write(coverTypeConversion_info + "\n");
					}
				}
			}

			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return coverTypeConversionsFile;
	}
	
	public File getUserConstraintsFile() {
		File userConstraintsFile = new File("UserConstraints.txt");
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userConstraintsFile))) {
			panelInput2_TEXT.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return userConstraintsFile;
	}
}
