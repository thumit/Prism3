package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

import spectrumGUI.Spectrum_Main;

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

	
	
	private Read_Strata read_Strata;
	private Read_DatabaseTables read_DatabaseTables;
	
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
							GUI_Text_splitPanel.setRightComponent(null);
//							GUI_Text_splitPanel.setRightComponent(panelInput2_TEXT);
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
	class PaneL_ManagementOptions_GUI extends JLayeredPane implements ItemListener {
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
			c0.weightx = 0.1;
		    c0.weighty = 1;
			importPanel.add(label1, c0);
			
			JTextField textField1 = new JTextField(25);
			textField1.setEditable(false);
			c0.gridx = 1;
			c0.gridy = 0;
			c0.weightx = 1;
		    c0.weighty = 1;
			importPanel.add(textField1, c0);
			
			JButton button1 = new JButton("Import Strata");
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fileManagementUnit = FilesChooser_Units.chosenManagementunit();				
					if (fileManagementUnit!=null) {
						textField1.setText(fileManagementUnit.getAbsolutePath());
						// Read the whole text file into table
						read_Strata = new Read_Strata();
						read_Strata.readValues(fileManagementUnit);
						String[][] value = read_Strata.getValues();
						rowCount = read_Strata.get_TotalRows();
						colCount = read_Strata.get_TotalColumns() + 1; //the "Methods for Implementation" Column
						data = new Object[rowCount][colCount];
						columnNames = new String[colCount];
						for (int row = 0; row < rowCount; row++) {
							for (int column = 0; column < colCount - 1; column++) {
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
						columnNames[colCount - 1] = "Methods for Implementation";
						table.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
						table.getColumnModel().getColumn(7).setPreferredWidth(120);	//Set width of Column "Total area" bigger
				        table.getColumnModel().getColumn(colCount-1).setPreferredWidth(200);	//Set width of Column "Methods for Implementation" bigger
				        
					}
				}
			});
			c0.gridx = 5;
			c0.gridy = 0;
			c0.weightx = 0;
		    c0.weighty = 1;
			importPanel.add(button1, c0);

			
			// 1st grid line 2----------------------------
			JLabel label2 = new JLabel("Database (.db file)");
			c0.gridx = 0;
			c0.gridy = 1;
			c0.weightx = 0.1;
		    c0.weighty = 1;
			importPanel.add(label2, c0);

			JTextField textField2 = new JTextField(25);
			textField2.setEditable(false);
			c0.gridx = 1;
			c0.gridy = 1;
			c0.weightx = 1;
		    c0.weighty = 1;
			importPanel.add(textField2, c0);

			JButton button2 = new JButton("Import Database");
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fileDatabase = FilesChooser_Database.chosenDatabase();				
					if (fileDatabase!=null) {
						textField2.setText(fileDatabase.getAbsolutePath());
						// Read the database tables into array
						read_DatabaseTables = new Read_DatabaseTables(fileDatabase);
						yieldTable_values = read_DatabaseTables.getTableArrays();
						yieldTable_ColumnNames = read_DatabaseTables.getTableColumnNames();
					}
				}
			});
			c0.gridx = 5;
			c0.gridy = 1;
			c0.weightx = 0;
		    c0.weighty = 1;
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
			checkboxRule = new JCheckBox[5];
			for (int i = 1; i <= 4; i++) {
				checkboxRule[i] = new JCheckBox();
				checkboxRule[i].setSelected(false);
			}
			
			
			JPanel silvicultural_Methods_Panel = new JPanel();		
			TitledBorder border2 = new TitledBorder("Silvicultural Methods");
			border2.setTitleJustification(TitledBorder.CENTER);
			silvicultural_Methods_Panel.setBorder(border2);
			silvicultural_Methods_Panel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.HORIZONTAL;
			c2.weightx = 1;
		    c2.weighty = 1;

			int total_CoverType = allLayers.get(4).size();		// total number of elements - 1 in layer5 Cover Type (0 to...)
				
			// 1st line inside silvicultural_Methods_Panel
		    checkboxRule[1].setText("Even Age (EA) - Cover Type conversions below are allowed for all strata");
		    c2.gridx = 0;
			c2.gridy = 0;
			c2.gridwidth = 1 + total_CoverType;
			silvicultural_Methods_Panel.add(checkboxRule[1], c2);
			

			//Labels for Cover Type From
			for (int i = 0; i < total_CoverType; i++) {
				JLabel labelConvert = new JLabel("        " + allLayers.get(4).get(i) + "      regenerated as     ");
				c2.gridx = 0;
				c2.gridy = i + 1;
				c2.gridwidth = 1;
				silvicultural_Methods_Panel.add(labelConvert, c2);
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
						silvicultural_Methods_Panel.add(ConversionCheck_To[i][j], c2);
						if (i==j) ConversionCheck_To[i][j].setSelected(true);
					}
			}
		    		
			
			// 2nd line inside silvicultural_Methods_Panel
			checkboxRule[2].setText("Group Selection (GS)");					
			c2.gridx = 0;
			c2.gridy = total_CoverType + 1;
			silvicultural_Methods_Panel.add(checkboxRule[2], c2);
			
			
			// 3rd line inside silvicultural_Methods_Panel
			checkboxRule[3].setText("Prescribed Burn (PB)");
			c2.gridx = 0;
			c2.gridy = total_CoverType + 2;
			silvicultural_Methods_Panel.add(checkboxRule[3], c2);
			
			
			// 4th line inside silvicultural_Methods_Panel
			checkboxRule[4].setText("Natural Growth (NG)");
			c2.gridx = 0;
			c2.gridy = total_CoverType + 3;
			silvicultural_Methods_Panel.add(checkboxRule[4], c2);

			
			// 5th line inside silvicultural_Methods_Panel: Apply Button
			JButton setMethods = new JButton("Set methods for implementation on the selected strata below");
			setMethods.addActionListener(new ActionListener() {
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
			c2.gridwidth = 1+ total_CoverType;	//GridBagConstraints.REMAINDER; 
			silvicultural_Methods_Panel.add(setMethods, c2);
			
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
			super.add(silvicultural_Methods_Panel, c);
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

	class PaneL_ManagementOptions_Text extends JLayeredPane {
	    public PaneL_ManagementOptions_Text() {
	         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    	
	         rowCount = 30;
	         colCount = 9;
	         data = new Object[rowCount][colCount];
	         columnNames= new String[] {"Strata ID" , "Layer 1", "Layer 2", "Layer 3", "Layer 4", "Layer 5", "Layer 6", 
	 				"Total area (acres)", "Methods for Implementation"};
	         
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
			Read_Indentifiers read_Identifiers = new Read_Indentifiers();
			
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
							
					//Set layer 5 - Cover Type invisible
					if (i==4) checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
					//Set layer 6 - Size Class invisible
					if (i==5) checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
					//Deselect all time period check boxes (7)
					if (i==7) checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
				}
			}
			
			// End of 1st grid -----------------------------------------------------------------------
			// End of 1st grid -----------------------------------------------------------------------

			
			
			
			// 2nd grid -----------------------------------------------------------------------
			// 2nd grid -----------------------------------------------------------------------	
			class checkboxScrollPanel extends JScrollPane {	
				private JCheckBox checkboxNotUsingColumn;
				private List<JCheckBox> checkboxParameter;
				private List<JLabel> allDynamicIdentifiers_Titles;
				private List<List<JCheckBox>> checkboxDynamicIdentifiers;
				JScrollPane defineScrollPane;		//for Definition of dynamic identifier
				
				public checkboxScrollPanel(String nameTag, int option) {
					
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
								
								checkboxDynamicIdentifiers = new ArrayList<List<JCheckBox>>();	
								checkboxParameter = new ArrayList<JCheckBox>();
								
								for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
									String YTcolumnName = yieldTable_ColumnNames[i];

									checkboxDynamicIdentifiers.add(new ArrayList<JCheckBox>());		//add empty List
									checkboxParameter.add(new JCheckBox(YTcolumnName));		//add checkbox
									checkboxParameter.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")");		//add toolTip
									
									// add checkboxVariables to the Panel
								    c2.gridx = 0;
								    c2.gridy = 1 + i;
									c2.weightx = 1;
								    c2.weighty = 1;
									parametersPanel.add(checkboxParameter.get(i), c2);
								}
								
								
								//Add an extra checkbox for the option of not using any Column, use 1 instead as multiplier
								//This is also the checkbox for the option of not using any Column as dynamic identifier
								checkboxNotUsingColumn = new JCheckBox();		//add checkbox		
								
								if (option == 1) {		//For the Parameters panel only
									checkboxNotUsingColumn.setText("NoParameter");		
									checkboxNotUsingColumn.setToolTipText("1 is used as multiplier (parameter), no column will be used as parameter");		//set toolTip
								} else if (option == 2){	//For the dynamic identifiers only	
									checkboxNotUsingColumn.setText("NoIdentifier");	
									checkboxNotUsingColumn.setToolTipText("No column will be used as dynamic identifier");		//set toolTip
								}
								
								// add the checkBox to the Panel
								c2.gridx = 0;
								c2.gridy = 0;
								c2.weightx = 1;
								c2.weighty = 1;
								parametersPanel.add(checkboxNotUsingColumn, c2);
								
								// Add listeners to de-select all other checkBoxes
								checkboxNotUsingColumn.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent actionEvent) {
										if (checkboxNotUsingColumn.isSelected()) {
											for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
												checkboxParameter.get(i).setSelected(false);
												if (option == 2) 	allDynamicIdentifiers_Titles.get(i).setVisible(false);		//Set invisible all labels of dynamic identifiers
											} 
										}
									}
								});								
								
								
								
								
								
								// Add listeners to checkBox so if then name has AllSx then other checkbox would be deselected 
								if (option == 1) {		//For the Parameters panel only
									for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
										String currentCheckBoxName = yieldTable_ColumnNames[i];
										int currentCheckBoxIndex = i;
										
										checkboxParameter.get(i).addActionListener(new ActionListener() {	
											@Override
											public void actionPerformed(ActionEvent actionEvent) {
												//Deselect the NoParameter checkBox
												checkboxNotUsingColumn.setSelected(false);
												
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
								}

								
							
								if (option == 2) {		//For the dynamic identifiers only						
									//Add all dynamic identifiers lables
									allDynamicIdentifiers_Titles = new ArrayList<JLabel>();
									
									for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
										String YTcolumnName = checkboxParameter.get(i).getText();		
										allDynamicIdentifiers_Titles.add(new JLabel(YTcolumnName));			//Add Label
										allDynamicIdentifiers_Titles.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName));		//add toolTip
										allDynamicIdentifiers_Titles.get(i).setVisible(false);		//Set invisible
										
										c2.gridx = 1 + i;
										c2.gridy = 0;
										parametersPanel.add(allDynamicIdentifiers_Titles.get(i), c2);
									}					
																			
									
									// Add listeners to checkBoxes
									for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
										String currentCheckBoxName = yieldTable_ColumnNames[i];
										int currentCheckBoxIndex = i;
										
										checkboxParameter.get(i).addActionListener(new ActionListener() {	
											@Override
											public void actionPerformed(ActionEvent actionEvent) {
												// A popupPanel to define identifier if the checkBox is selected
												if (checkboxParameter.get(currentCheckBoxIndex).isSelected()) {
										
													//define popupPanel
													JPanel popupPanel = new JPanel();
													popupPanel.setLayout(new GridBagLayout());
													TitledBorder border_popup = new TitledBorder("DEFINE THIS IDENTIFIER");
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
													
													//Add checkBoxes to listPanel
													for (int j = 0; j < uniqueValueList.size(); j++) {
														c_list.gridx = 0;
														c_list.gridy = j;
														c_list.weightx = 1;
														c_list.weighty = 1;
														listPanel.add(new JCheckBox(uniqueValueList.get(j)), c_list);		
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
														columnInfo_TArea.append("Defining this identifier as 'DISCRETE IDENTIFIER' is recommended.");
													} else {
														columnInfo_TArea.append("Defining this identifier as 'RANGE IDENTIFIER' is recommended.");
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
																discretePanel.add(new JLabel("Define Name"), c_dP);
																
																//Add all discrete values and textField for the toolTip
																for (int j = 0; j < uniqueValueList.size(); j++) {
																	checkboxDynamicIdentifiers.get(currentCheckBoxIndex).add(new JCheckBox(uniqueValueList.get(j)));
																	c_dP.gridx = 0;
																	c_dP.gridy = 1 + j;
																	discretePanel.add(checkboxDynamicIdentifiers.get(currentCheckBoxIndex).get(j), c_dP);
																	
																	c_dP.gridx = 1;
																	c_dP.gridy = 1 + j;
																	JTextField name_TF = new JTextField(20);
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
																JPanel rangePanel = new JPanel();
																
																
																
																
																
																
																
																
																
																
																
																
																
																
																
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
														checkboxParameter.get(currentCheckBoxIndex).setSelected(false);
													} else if (response == JOptionPane.YES_OPTION) {
														//Deselect the No Identifier checkBox
														checkboxNotUsingColumn.setSelected(false);
														
														//Set the title visible
														allDynamicIdentifiers_Titles.get(currentCheckBoxIndex).setVisible(true);
														
													} else if (response == JOptionPane.CLOSED_OPTION) {
														checkboxParameter.get(currentCheckBoxIndex).setSelected(false);
													}
												
												} else {	//if checkbox is not selected then remove the title
													allDynamicIdentifiers_Titles.get(currentCheckBoxIndex).setVisible(false);
												}	

											}
										});
									}		
								}
								
								
								
								//Do a resize to same size for JInteral Frame of the project to help repaint the checkboxVariables added					
								Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	
							}
						}
					});		
					
				}
			}
			
		    
		    
			
			checkboxScrollPanel parametersScrollPanel = new checkboxScrollPanel("Get parameters from YT columns", 1);
			TitledBorder border2 = new TitledBorder("PARAMETERS (yield table columns)");
			border2.setTitleJustification(TitledBorder.CENTER);
			parametersScrollPanel.setBorder(border2);
	    	parametersScrollPanel.setPreferredSize(new Dimension(250, 100));
			// End of 2nd grid -----------------------------------------------------------------------
			// End of 2nd grid -----------------------------------------------------------------------
			

	    	
	    	
	    	
	    	
			// 3rd Grid -----------------------------------------------------------------------
			// 3rd Grid -----------------------------------------------------------------------						
			JPanel buttonPanel = new JPanel(new BorderLayout(0, 0));
			buttonPanel.setPreferredSize(new Dimension(250, 40));;
			JButton addBtn = new JButton();
			addBtn.setFont(new Font(null, Font.BOLD, 14));
			addBtn.setText("SET CONSTRAINT INFO");
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
							if (parametersScrollPanel.checkboxNotUsingColumn.isSelected()) {
								parameterConstraintColumn = parametersScrollPanel.checkboxNotUsingColumn.getText();
							} else {
								for (int j = 0; j < yieldTable_ColumnNames.length; j++) {
									if (parametersScrollPanel.checkboxParameter.get(j).isSelected()) {			//add the index of selected Columns to this String
										parameterConstraintColumn = parameterConstraintColumn + j + " ";
									}
								}
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
									if (checkboxStaticIdentifiers.get(ii).get(j).isSelected())	staticIdentifiersColumn = staticIdentifiersColumn + checkboxName + " "	;
								}
								staticIdentifiersColumn = staticIdentifiersColumn + "; ";
							}	
							data2[i][7] = staticIdentifiersColumn;
							
							
							//add constraint info at column 8 "dynamic Identifiers"
							
							
							
							
							
							
							
							
							
							
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
			
			    	
	    	
	    	
			

	    	
			// 4th Grid -----------------------------------------------------------------------
			// 4th Grid -----------------------------------------------------------------------						
			JPanel dynamic_identifiersPanel = new JPanel();		
			dynamic_identifiersPanel.setLayout(new GridBagLayout());
			GridBagConstraints c3 = new GridBagConstraints();
			c3.fill = GridBagConstraints.HORIZONTAL;
			c3.weightx = 1;
		    c3.weighty = 1;
		    
		    
		    
	    	JScrollPane dynamic_identifiersScrollPanel = new JScrollPane(dynamic_identifiersPanel);
			TitledBorder border3_1 = new TitledBorder("Dynamic identifiers for PARAMETERS (from yield table)");
			border3_1.setTitleJustification(TitledBorder.CENTER);
			dynamic_identifiersScrollPanel.setBorder(border3_1);
			dynamic_identifiersScrollPanel.setPreferredSize(new Dimension(100, 250));
			
			
			
			
			
			c3.gridx = 0;
			c3.gridy = 0;	
			checkboxScrollPanel selectIdentifiersScrollPanel = new checkboxScrollPanel("Get identifiers from yield table columns", 2);
			TitledBorder border3_2 = new TitledBorder("Select Identifiers");
			border3_2.setTitleJustification(TitledBorder.CENTER);
			selectIdentifiersScrollPanel.setBorder(border3_2);
			selectIdentifiersScrollPanel.setPreferredSize(new Dimension(300, 200));
			dynamic_identifiersPanel.add(selectIdentifiersScrollPanel, c3);
			
			


			

//			//Add CheckBox for the last 2 layers only
//		    checkboxDynamicIdentifiers = new ArrayList<List<JCheckBox>>();
//			for (int i = 0; i < 2; i++) {		//Loop 2 layers
//				List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		//A temporary List
//				checkboxDynamicIdentifiers.add(temp_List);
//				for (int j = 0; j < allLayers.get(i+4).size(); j++) {		//Loop all elements in each layer
//					checkboxDynamicIdentifiers.get(i).add(new JCheckBox(allLayers.get(i+4).get(j)));
//					checkboxDynamicIdentifiers.get(i).get(j).setToolTipText(allLayers_ToolTips.get(i+4).get(j));	
//					checkboxDynamicIdentifiers.get(i).get(j).setSelected(true);
//					
//					c4.gridx = i;
//					c4.gridy = j + 1;
//					dynamic_identifiersPanel.add(checkboxDynamicIdentifiers.get(i).get(j), c4);
//				}
//			}
//			
//			int lastRow2 = 0;
//			for (int i = 0; i < 2; i++) {		//Loop 2 layers
//				for (int j = 0; j < allLayers.get(i+4).size(); j++) {		//Loop all elements in each layer
//					if (j+1>lastRow2) lastRow2 = j+1;
//				}
//			}
//			
//			
//			
//			//Add 2 buttons for select all and de-select all		
//			JButton selectAll1 = new JButton("Select All");
//			selectAll1.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent actionEvent) {
//					for (int i = 0; i < 2; i++) {		//Loop all layers
//						for (int j = 0; j < allLayers.get(i+4).size(); j++) {		//Loop all elements in each layer
//							checkboxDynamicIdentifiers.get(i).get(j).setSelected(true);
//						}
//					}
//				}
//			});
//			c4.gridx = 0;
//			c4.gridy = lastRow2 + 1;
//			c4.gridwidth = 1;
//			dynamic_identifiersPanel.add(selectAll1, c4);
//			
//			JButton deselectAll1 = new JButton("De-Select All");
//			deselectAll1.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent actionEvent) {
//					for (int i = 0; i < 2; i++) {		//Loop all layers
//						for (int j = 0; j < allLayers.get(i+4).size(); j++) {		//Loop all elements in each layer
//							checkboxDynamicIdentifiers.get(i).get(j).setSelected(false);
//						}
//					}	
//				}
//			});
//			c4.gridx = 1;
//			c4.gridy = lastRow2 + 1;
//			c4.gridwidth = 1;
//			dynamic_identifiersPanel.add(deselectAll1, c4);				
			// End of 4th Grid -----------------------------------------------------------------------
			// End of 4th Grid -----------------------------------------------------------------------	
			
		

			
	    	
	    	
	    	
	
			
			
			
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
			table2.getColumnModel().getColumn(0).setPreferredWidth(250);
			table2.getColumnModel().getColumn(colCount2-7).setPreferredWidth(150);	
			table2.getColumnModel().getColumn(colCount2-6).setPreferredWidth(150);	
			table2.getColumnModel().getColumn(colCount2-5).setPreferredWidth(150);	
	        table2.getColumnModel().getColumn(colCount2-4).setPreferredWidth(150);	//Set width of Column "Penalty/Unit (Soft Const.)" bigger
	        table2.getColumnModel().getColumn(colCount2-3).setPreferredWidth(150);	//Set width of Column "PARAMETERS" bigger
	        table2.getColumnModel().getColumn(colCount2-2).setPreferredWidth(300);	//Set width of Column "Static identifiers for VARIABLES" bigger
	        table2.getColumnModel().getColumn(colCount2-1).setPreferredWidth(300);	//Set width of Column "Dynamic identifiers for PARAMETERS" bigger
//			table2.setPreferredScrollableViewportSize(new Dimension(1500, 200));
			table2.setAutoResizeMode(0);
			table2.setFillsViewportHeight(true);
			table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
			TableRowSorter<MyTableModel2> sorter2 = new TableRowSorter<MyTableModel2>(model2);	//Add sorter
			table2.setRowSorter(sorter2);
			
   
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
	         		
			// 5th Grid -----------------------------------------------------------------------
			// 5th Grid -----------------------------------------------------------------------				
			
			
			

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		    
		    
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
		
		//Only print out rows if columns  1, 2, 4, 6, 7 <> null
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userConstraintsFile))) {
			for (int j = 0; j < table2.getColumnCount(); j++) {
				fileOut.write(table2.getColumnName(j) + "\t");
			}
			
			for (int i = 0; i < table2.getRowCount(); i++) {
				boolean checkValidity = true;
				for (int j = 1; j < table2.getColumnCount(); j++) {
					if (j==1 || j==2 || j==4 || j==6 || j==7) {
						if ((Object) (table2.getValueAt(i, j)) == null || (Object) (table2.getValueAt(i, j)) == "")		checkValidity = false;	
					}
				}
								
				if (checkValidity == true) { // if columns  1, 2, 4, 6, 7 <> null then write to file
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
	
	
	
}
