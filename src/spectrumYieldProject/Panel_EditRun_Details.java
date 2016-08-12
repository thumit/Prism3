package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

public class Panel_EditRun_Details extends JLayeredPane implements ActionListener {
	private JSplitPane GUI_Text_splitPanel ;
	private JPanel radioPanel_Right, combinePanel; 
	private ButtonGroup radioGroup_Right; 
	private JRadioButton[] radioButton_Right; 
	private File currentRun = Panel_EditRun.getTheOnlySelectedRun();
	private static File fileManagementUnit;
	
	//6 panels for the selected Run
	private PaneL_General_Inputs_GUI panelInput0_GUI;
	private PaneL_General_Inputs_Text panelInput0_TEXT;
	private PaneL_Rules_GUI panelInput1_GUI;
	private PaneL_Rules_Text panelInput1_TEXT;
	private PaneL_Constraints_GUI panelInput2_GUI;
	private PaneL_Constraints_Text panelInput2_TEXT;		

	
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
	
	public Panel_EditRun_Details() {
		super.setLayout(new BorderLayout());

		// Add 3 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
		radioPanel_Right = new JPanel();
		radioPanel_Right.setLayout(new FlowLayout());		
		radioGroup_Right = new ButtonGroup();
		
		radioButton_Right  = new JRadioButton[3];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[1]= new JRadioButton("Rules");
		radioButton_Right[2]= new JRadioButton("Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < 3; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
				radioButton_Right[i].addActionListener(this);
		}	
		
		GUI_Text_splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_splitPanel.setDividerSize(5);
		GUI_Text_splitPanel.setLeftComponent(null);
		GUI_Text_splitPanel.setRightComponent(null);
			
	
		// Create all new 6 panels for the selected Run--------------------------------------------------
		panelInput0_GUI = new PaneL_General_Inputs_GUI();
		panelInput0_TEXT = new PaneL_General_Inputs_Text();
		panelInput1_GUI = new PaneL_Rules_GUI();
		panelInput1_TEXT = new PaneL_Rules_Text();
		panelInput2_GUI = new PaneL_Constraints_GUI();
		panelInput2_TEXT = new PaneL_Constraints_Text();
					
		
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
			
			JLabel label2 = new JLabel("Budget limit (thousand dollars)");
			JSpinner spin2 = new JSpinner (new SpinnerNumberModel(1000, 0, null, 10));
			JFormattedTextField SpinnerText = ((DefaultEditor) spin2.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			super.add(label2);
			super.add(spin2);
			
			JLabel label3 = new JLabel("Discount rate (%)");
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
							+ label2.getText() + "	" + (Integer)spin2.getValue() + "\n"
							+ label3.getText() + "	" + combo3.getSelectedItem().toString() + "\n"
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
	class PaneL_Rules_GUI extends JLayeredPane implements ActionListener {
		// Define 28 check box for 6 layers
		JCheckBox[] checkboxFilter, checkboxRule;
		
		
		public PaneL_Rules_GUI() {
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
		    c.weighty = 1;

			
			// 1st grid line
			JLabel label1 = new JLabel("Management Units (.csv file)");
			c.gridx = 0;
			c.gridy = 0;
			super.add(label1, c);
			
			JTextField textField1 = new JTextField(30);
			textField1.setEditable(false);
			c.gridx = 1;
			c.gridy = 0;
			super.add(textField1, c);
			
			JButton button1 = new JButton("Import Units");
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fileManagementUnit = FilesChooser2.chosenManagementunit();				
					if (fileManagementUnit!=null) {
						textField1.setText(fileManagementUnit.getAbsolutePath());
						// Read the whole text file into table
						ReadUnit.readValues(fileManagementUnit);
						String[][] value = ReadUnit.getValues();
						rowCount = ReadUnit.get_TotalRows();
						colCount = ReadUnit.get_TotalColumns() + 1; //the +1 is the rules Column
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
						columnNames[0] = "Unit ID";
						columnNames[1] = "Layer 1";
						columnNames[2] = "Layer 2";
						columnNames[3] = "Layer 3";
						columnNames[4] = "Layer 4";
						columnNames[5] = "Layer 5";
						columnNames[6] = "Layer 6";
						columnNames[7] = "Total area (acres)";
						columnNames[colCount - 1] = "Rules";
						table.createDefaultColumnsFromModel(); // Very important code to refresh the number of Columns	shown
						table.getColumnModel().getColumn(colCount - 2).setPreferredWidth(100); //Set width of Column "Total area" bigger
						table.getColumnModel().getColumn(colCount - 1).setPreferredWidth(400); //Set width of Column "Rules" bigger
					}
				}
			});
			c.gridx = 2;
			c.gridy = 0;
			super.add(button1, c);

				
			// 2nd grid line-----------------------------------------------------------------------
			// 2nd grid line-----------------------------------------------------------------------
			checkboxFilter = new JCheckBox[29];
			for (int i = 1; i <= 28; i++) {
				checkboxFilter[i] = new JCheckBox();
				checkboxFilter[i].setSelected(true);
				checkboxFilter[i].addActionListener(this);
			}
			
			JLabel label2_1 = new JLabel("Layer 1");
			checkboxFilter[1].setText("B");
			checkboxFilter[2].setText("U");
			checkboxFilter[3].setText("S");
			checkboxFilter[4].setText("K");
			checkboxFilter[5].setText("R");
			checkboxFilter[6].setText("C");
			
			JLabel label2_2 = new JLabel("Layer 2");
			checkboxFilter[7].setText("R");
			checkboxFilter[8].setText("N");
			
			JLabel label2_3 = new JLabel("Layer 3");
			checkboxFilter[9].setText("N");
			checkboxFilter[10].setText("O");
			checkboxFilter[11].setText("P");
			checkboxFilter[12].setText("S");
			
			JLabel label2_4 = new JLabel("Layer 4");
			checkboxFilter[13].setText("L");
			checkboxFilter[14].setText("H");
			checkboxFilter[15].setText("C");
			checkboxFilter[16].setText("R");
			
			JLabel label2_5 = new JLabel("Layer 5");
			checkboxFilter[17].setText("P");
			checkboxFilter[18].setText("D");
			checkboxFilter[19].setText("W");
			checkboxFilter[20].setText("C");
			checkboxFilter[21].setText("I");
			checkboxFilter[22].setText("A");
			checkboxFilter[23].setText("L");

			JLabel label2_6 = new JLabel("Layer 6");
			checkboxFilter[24].setText("S");
			checkboxFilter[25].setText("P");
			checkboxFilter[26].setText("M");
			checkboxFilter[27].setText("L");
			checkboxFilter[28].setText("N");
			
			// Set tooltip texts
			label2_1.setToolTipText("Vegetation Desired Future Condition Areas"); 
			checkboxFilter[1].setToolTipText("Bitterroot Mtns. (M333D) Breaklands");
			checkboxFilter[2].setToolTipText("Bitterroot Mtns. (M333D) Uplands");
			checkboxFilter[3].setToolTipText("Bitterroot Mtns. (M333D) Subalpine");
			checkboxFilter[4].setToolTipText("Idaho Batholith (M332A) Breaklands");
			checkboxFilter[5].setToolTipText("Idaho Batholith (M332A) Uplands");
			checkboxFilter[6].setToolTipText("Idaho Batholith (M332A) Subalpine");
			
			label2_2.setToolTipText("Roadless Status");
			checkboxFilter[7].setToolTipText("Roadless and undeveloped");
			checkboxFilter[8].setToolTipText("Roaded and developed");
			
			label2_3.setToolTipText("Timber Suitability");
			checkboxFilter[9].setToolTipText("Not Available or Not Suited; No Timber Harvest Allowed");
			checkboxFilter[10].setToolTipText("Generally Suitable for Timber Harvest for other resource objectives, no scheduled output");
			checkboxFilter[11].setToolTipText("Generally Suitable for Timber Harvest for other resource objectives, scheduled output");
			checkboxFilter[12].setToolTipText("Suited for Timber Production");
			
			label2_4.setToolTipText("Resource Condition Zones");
			checkboxFilter[13].setToolTipText("Lynx habitat – conserve watershed");
			checkboxFilter[14].setToolTipText("Lynx habitat – restore watershed");
			checkboxFilter[15].setToolTipText("Non-Lynx habitat – conserve watershed");
			checkboxFilter[16].setToolTipText("Non-Lynx habitat – restore watershed");
			
			label2_5.setToolTipText("Vegetation Cover Types");
			checkboxFilter[17].setToolTipText("Ponderosa Pine");
			checkboxFilter[18].setToolTipText("Dry Douglas-fir/Grand Fir");
			checkboxFilter[19].setToolTipText("Mesic Douglas-fir mix");
			checkboxFilter[20].setToolTipText("Grand Fir/Western Red Cedar");
			checkboxFilter[21].setToolTipText("Cold Douglas-fir mix");
			checkboxFilter[22].setToolTipText("Subalpine Fir mix");
			checkboxFilter[23].setToolTipText("Lodgepole Pine");

			label2_6.setToolTipText("Size Class");
			checkboxFilter[24].setToolTipText("Seedling and Sapling (0” – 5”)");
			checkboxFilter[25].setToolTipText("Small (5” – 10”)");
			checkboxFilter[26].setToolTipText("Medium (10” – 15”)");
			checkboxFilter[27].setToolTipText("Large (15”+)");
			checkboxFilter[28].setToolTipText("None");
			
			
			//Add all 6 layer labels and 28 checkbox to checkPanel
			JPanel checkPanel = new JPanel();		
			TitledBorder border = new TitledBorder("Management Unit Filter");
//			border.setTitleFont(new Font("Sans-Serif", Font.BOLD, 14));
			border.setTitleJustification(TitledBorder.CENTER);
			checkPanel.setBorder(border);
			checkPanel.setLayout(new GridBagLayout());
			GridBagConstraints c1 = new GridBagConstraints();
			c1.fill = GridBagConstraints.BOTH;
			c1.weightx = 1;
		    c1.weighty = 1;
			
			// 1st grid column inside checkPanel
			c1.gridx = 0;
			c1.gridy = 0;
			checkPanel.add(label2_1, c1);

			c1.gridx = 0;
			c1.gridy = 1;
			checkPanel.add(checkboxFilter[1], c1);

			c1.gridx = 0;
			c1.gridy = 2;
			checkPanel.add(checkboxFilter[2], c1);

			c1.gridx = 0;
			c1.gridy = 3;
			checkPanel.add(checkboxFilter[3], c1);

			c1.gridx = 0;
			c1.gridy = 4;
			checkPanel.add(checkboxFilter[4], c1);

			c1.gridx = 0;
			c1.gridy = 5;
			checkPanel.add(checkboxFilter[5], c1);

			c1.gridx = 0;
			c1.gridy = 6;
			checkPanel.add(checkboxFilter[6], c1);
			
			
			// 2nd grid column inside checkPanel
			c1.gridx = 1;
			c1.gridy = 0;
			checkPanel.add(label2_2, c1);

			c1.gridx = 1;
			c1.gridy = 1;
			checkPanel.add(checkboxFilter[7], c1);

			c1.gridx = 1;
			c1.gridy = 2;
			checkPanel.add(checkboxFilter[8], c1);


			// 3rd grid column inside checkPanel
			c1.gridx = 2;
			c1.gridy = 0;
			checkPanel.add(label2_3, c1);

			c1.gridx = 2;
			c1.gridy = 1;
			checkPanel.add(checkboxFilter[9], c1);

			c1.gridx = 2;
			c1.gridy = 2;
			checkPanel.add(checkboxFilter[10], c1);
			
			c1.gridx = 2;
			c1.gridy = 3;
			checkPanel.add(checkboxFilter[11], c1);

			c1.gridx = 2;
			c1.gridy = 4;
			checkPanel.add(checkboxFilter[12], c1);
			
			
			// 4th grid column inside checkPanel
			c1.gridx = 3;
			c1.gridy = 0;
			checkPanel.add(label2_4, c1);

			c1.gridx = 3;
			c1.gridy = 1;
			checkPanel.add(checkboxFilter[13], c1);

			c1.gridx = 3;
			c1.gridy = 2;
			checkPanel.add(checkboxFilter[14], c1);

			c1.gridx = 3;
			c1.gridy = 3;
			checkPanel.add(checkboxFilter[15], c1);

			c1.gridx = 3;
			c1.gridy = 4;
			checkPanel.add(checkboxFilter[16], c1);

			// 5th grid column inside checkPanel
			c1.gridx = 4;
			c1.gridy = 0;
			checkPanel.add(label2_5, c1);

			c1.gridx = 4;
			c1.gridy = 1;
			checkPanel.add(checkboxFilter[17], c1);

			c1.gridx = 4;
			c1.gridy = 2;
			checkPanel.add(checkboxFilter[18], c1);

			c1.gridx = 4;
			c1.gridy = 3;
			checkPanel.add(checkboxFilter[19], c1);

			c1.gridx = 4;
			c1.gridy = 4;
			checkPanel.add(checkboxFilter[20], c1);

			c1.gridx = 4;
			c1.gridy = 5;
			checkPanel.add(checkboxFilter[21], c1);

			c1.gridx = 4;
			c1.gridy = 6;
			checkPanel.add(checkboxFilter[22], c1);
			
			c1.gridx = 4;
			c1.gridy = 7;
			checkPanel.add(checkboxFilter[23], c1);
			
			
			// 6th grid column inside checkPanel
			c1.gridx = 5;
			c1.gridy = 0;
			checkPanel.add(label2_6, c1);

			c1.gridx = 5;
			c1.gridy = 1;
			checkPanel.add(checkboxFilter[24], c1);

			c1.gridx = 5;
			c1.gridy = 2;
			checkPanel.add(checkboxFilter[25], c1);

			c1.gridx = 5;
			c1.gridy = 3;
			checkPanel.add(checkboxFilter[26], c1);

			c1.gridx = 5;
			c1.gridy = 4;
			checkPanel.add(checkboxFilter[27], c1);

			c1.gridx = 5;
			c1.gridy = 5;
			checkPanel.add(checkboxFilter[28], c1);
			
			
			// 2 buttons for select all and de-select all		
			JButton selectAll = new JButton("Select All");
			selectAll.addActionListener(this);
			selectAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					for (int i = 1; i <= 28; i++) {
						checkboxFilter[i].setSelected(true);
					}	
				}
			});
			c1.gridx = 0;
			c1.gridy = 9;
			c1.gridwidth = 2;
			checkPanel.add(selectAll, c1);
			
			JButton deselectAll = new JButton("De-Select All");
			deselectAll.addActionListener(this);
			deselectAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					for (int i = 1; i <= 28; i++) {
						checkboxFilter[i].setSelected(false);
					}	
				}
			});
			c1.gridx = 4;
			c1.gridy = 9;
			c1.gridwidth = 2;
			checkPanel.add(deselectAll, c1);
			// End of 2nd grid line-----------------------------------------------------------------------
			// End of 2nd grid line-----------------------------------------------------------------------

			
			
			
			// 3rd grid line-----------------------------------------------------------------------
			// 3rd grid line-----------------------------------------------------------------------
			checkboxRule = new JCheckBox[5];
			for (int i = 1; i <= 4; i++) {
				checkboxRule[i] = new JCheckBox();
				checkboxRule[i].setSelected(false);
			}
			
			
			JPanel ruleEditorPanel = new JPanel();		
			TitledBorder border2 = new TitledBorder("Rule Editor");
			border2.setTitleJustification(TitledBorder.CENTER);
			ruleEditorPanel.setBorder(border2);
			ruleEditorPanel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.fill = GridBagConstraints.BOTH;
			c2.weightx = 1;
		    c2.weighty = 1;
			
				
			// 1st line inside ruleEditorPanel
		    checkboxRule[1].setText("Even Age");
			JLabel label3_1_1 = new JLabel("Min Rotation Age");
			JLabel label3_1_2 = new JLabel("Max Rotation Age");
			JComboBox combo3_1_1 = new JComboBox();
			for (int i = 1; i <= 30; i++) {
				combo3_1_1.addItem(i);
			}
			combo3_1_1.setSelectedItem((int) 20);
			JComboBox combo3_1_2 = new JComboBox();
			for (int i = 1; i <= 30; i++) {
				combo3_1_2.addItem(i);
			}
			combo3_1_2.setSelectedItem((int) 25);
						
			c2.gridx = 0;
			c2.gridy = 0;
			ruleEditorPanel.add(checkboxRule[1], c2);
				
			c2.gridx = 1;
			c2.gridy = 1;
			ruleEditorPanel.add(label3_1_1, c2);
			
			c2.gridx = 2;
			c2.gridy = 1;
			ruleEditorPanel.add(combo3_1_1, c2);
			
			c2.gridx = 3;
			c2.gridy = 1;
			ruleEditorPanel.add(label3_1_2, c2);
			
			c2.gridx = 4;
			c2.gridy = 1;
			ruleEditorPanel.add(combo3_1_2, c2);
			
			
			// 2nd line inside ruleEditorPanel
			checkboxRule[2].setText("Group Selection");
			JLabel label3_2_1 = new JLabel("Start Age");
			JLabel label3_2_2 = new JLabel("Repeat Interval (periods)");
			JComboBox combo3_2_1 = new JComboBox();
			for (int i = 1; i <= 10; i++) {
				combo3_2_1.addItem(i);
			}
			combo3_2_1.setSelectedItem((int) 8);
			JComboBox combo3_2_2 = new JComboBox();
			for (int i = 3; i <= 5; i++) {
				combo3_2_2.addItem(i);
			}
			combo3_2_2.setSelectedItem((int) 4);
						
			c2.gridx = 0;
			c2.gridy = 2;
			ruleEditorPanel.add(checkboxRule[2], c2);
				
			c2.gridx = 1;
			c2.gridy = 3;
			ruleEditorPanel.add(label3_2_1, c2);
			
			c2.gridx = 2;
			c2.gridy = 3;
			ruleEditorPanel.add(combo3_2_1, c2);
			
			c2.gridx = 3;
			c2.gridy = 3;
			ruleEditorPanel.add(label3_2_2, c2);
			
			c2.gridx = 4;
			c2.gridy = 3;
			ruleEditorPanel.add(combo3_2_2, c2);
			
			
			// 3rd line inside ruleEditorPanel
			checkboxRule[3].setText("Prescribed Burn");
			JLabel label3_3_1 = new JLabel("Start Age");
			JLabel label3_3_2 = new JLabel("Repeat Interval (periods)");
			JComboBox combo3_3_1 = new JComboBox();
			for (int i = 1; i <= 10; i++) {
				combo3_3_1.addItem(i);
			}
			combo3_3_1.setSelectedItem((int) 8);
			JComboBox combo3_3_2 = new JComboBox();
			for (int i = 3; i <= 5; i++) {
				combo3_3_2.addItem(i);
			}
			combo3_3_2.setSelectedItem((int) 4);

			c2.gridx = 0;
			c2.gridy = 4;
			ruleEditorPanel.add(checkboxRule[3], c2);

			c2.gridx = 1;
			c2.gridy = 5;
			ruleEditorPanel.add(label3_3_1, c2);

			c2.gridx = 2;
			c2.gridy = 5;
			ruleEditorPanel.add(combo3_3_1, c2);

			c2.gridx = 3;
			c2.gridy = 5;
			ruleEditorPanel.add(label3_3_2, c2);

			c2.gridx = 4;
			c2.gridy = 5;
			ruleEditorPanel.add(combo3_3_2, c2);
			
			
			// 4th line inside ruleEditorPanel
			checkboxRule[4].setText("Natural Growth");
			c2.gridx = 0;
			c2.gridy = 6;
			ruleEditorPanel.add(checkboxRule[4], c2);

			
			// 5th line inside ruleEditorPanel: Apply Button
			JButton applyRule = new JButton("Apply selected rules to the selected management units below");
			applyRule.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					String applyText = "";
					if (checkboxRule[1].isSelected()) {
						applyText = applyText  + "EA(" + combo3_1_1.getSelectedItem() + "," + combo3_1_2.getSelectedItem() + ")   "; 
					}
					if (checkboxRule[2].isSelected()) {
						applyText = applyText  + "GS(" + combo3_2_1.getSelectedItem() + "," + combo3_2_2.getSelectedItem() + ")   "; 
					}
					if (checkboxRule[3].isSelected()) {
						applyText = applyText  + "PB(" + combo3_3_1.getSelectedItem() + "," + combo3_3_2.getSelectedItem() + ")   "; 
					}
					if (checkboxRule[4].isSelected()) {
						applyText = applyText  + "NG   "; 
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
			c2.gridy = 8;
			c2.gridwidth = 5; 	//5 columns wide
			ruleEditorPanel.add(applyRule, c2);
			
			// End of 3rd grid line-----------------------------------------------------------------------
			// End of 3rd grid line-----------------------------------------------------------------------
			
			
	
			
			// Add the checkPanel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 3;   //3 columns wide
			super.add(checkPanel, c);
			
			// Add the modifyPanel to the main Grid	
			c.gridx = 3;
			c.gridy = 1;
			c.gridwidth = 3;   //3 columns wide
			c.gridheight = 1;   //1 rows high
			super.add(ruleEditorPanel, c);
		}
		
		//Listeners for checkBox Filter--------------------------------------------------------------------
		public void actionPerformed(ActionEvent e) {
			TableRowSorter<MyTableModel> sorter = new TableRowSorter<MyTableModel>(model);
			table.setRowSorter(sorter);
			List<RowFilter<MyTableModel,Object>> filters; 			
			
			RowFilter<MyTableModel, Object> layer1_filter = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			for (int i = 1; i <= 6; i++) {
				if (checkboxFilter[i].isSelected()) {			
					filters.add(RowFilter.regexFilter(checkboxFilter[i].getText(), 1));		
				}
			}
			layer1_filter = RowFilter.orFilter(filters);
			
			
			RowFilter<MyTableModel, Object> layer2_filter = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			for (int i = 7; i <= 8; i++) {
				if (checkboxFilter[i].isSelected()) {			
					filters.add(RowFilter.regexFilter(checkboxFilter[i].getText(), 2));		
				}
			}
			layer2_filter = RowFilter.orFilter(filters);
			
			
			RowFilter<MyTableModel, Object> layer3_filter = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			for (int i = 9; i <= 12; i++) {
				if (checkboxFilter[i].isSelected()) {			
					filters.add(RowFilter.regexFilter(checkboxFilter[i].getText(), 3));		
				}
			}
			layer3_filter = RowFilter.orFilter(filters);
			
			
			RowFilter<MyTableModel, Object> layer4_filter = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			for (int i = 13; i <= 16; i++) {
				if (checkboxFilter[i].isSelected()) {			
					filters.add(RowFilter.regexFilter(checkboxFilter[i].getText(), 4));		
				}
			}
			layer4_filter = RowFilter.orFilter(filters);
			
	
			RowFilter<MyTableModel, Object> layer5_filter = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			for (int i = 17; i <= 23; i++) {
				if (checkboxFilter[i].isSelected()) {			
					filters.add(RowFilter.regexFilter(checkboxFilter[i].getText(), 5));		
				}
			}
			layer5_filter = RowFilter.orFilter(filters);

			
			RowFilter<MyTableModel, Object> layer6_filter = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			for (int i = 24; i <= 28; i++) {
				if (checkboxFilter[i].isSelected()) {
					filters.add(RowFilter.regexFilter(checkboxFilter[i].getText(), 6));		
				}
			}
			layer6_filter=RowFilter.orFilter(filters);
			
			
			RowFilter<MyTableModel, Object> combine_AllFilters = null;
			filters  = new ArrayList<RowFilter<MyTableModel,Object>>();
			filters.add(layer1_filter);
			filters.add(layer2_filter);
			filters.add(layer3_filter);
			filters.add(layer4_filter);
			filters.add(layer5_filter);
			filters.add(layer6_filter);
			combine_AllFilters = RowFilter.andFilter(filters);

			
			sorter.setRowFilter(combine_AllFilters);
		}
	}
	
	class PaneL_Rules_Text extends JPanel {
	    public PaneL_Rules_Text() {
	         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    	
	         rowCount = 30;
	         colCount = 9;
	         data = new Object[rowCount][colCount];
	         columnNames= new String[] {"Unit ID" , "Layer 1", "Layer 2", "Layer 3", "Layer 4", "Layer 5", "Layer 6", 
	 				"Total area (acres)", "Rules"};
	         
			// Populate the data matrix without any information
			for (int row = 0; row < 1; row++) {			// 1 row is ok
				for (int col = 0; col < colCount; ++col) {		//Number of Columns must match
					data[row][col] = "";
				}
			}
	    	
	    	
	         //Create a table
	         model = new MyTableModel();
	         table = new JTable(model);
	         table.getColumnModel().getColumn(colCount-2).setPreferredWidth(100);	//Set width of Column "Total area" bigger
	         table.getColumnModel().getColumn(colCount-1).setPreferredWidth(400);	//Set width of Column "Rules" bigger
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
	class PaneL_Constraints_GUI extends JLayeredPane {
		public PaneL_Constraints_GUI() {
			
			
	
		}
	}
	
	class PaneL_Constraints_Text  extends JTextArea {
		public PaneL_Constraints_Text() {
			setRows(50);		// set text areas with 10 rows when starts		
		}
	}

}
