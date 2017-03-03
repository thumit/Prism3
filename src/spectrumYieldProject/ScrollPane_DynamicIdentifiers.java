package spectrumYieldProject;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import spectrumConvenienceClasses.IconHandle;
import spectrumROOT.Spectrum_Main;

public class ScrollPane_DynamicIdentifiers extends JScrollPane {	
	private JCheckBox checkboxNoIdentifier;
	private List<JCheckBox> allDynamicIdentifiers;
	private List<JScrollPane> allDynamicIdentifiers_ScrollPane;
	private List<List<JCheckBox>> checkboxDynamicIdentifiers;
	private JScrollPane defineScrollPane;		//for Definition of dynamic identifier

	
	public ScrollPane_DynamicIdentifiers(int option, Read_DatabaseTables read_DatabaseTables, Read_Indentifiers read_Identifiers,
			String[] yieldTable_ColumnNames, Object[][][] yieldTable_values) {
		
		
		
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
		//------------------------------------------------------------------------------
		
		
	    if (yieldTable_ColumnNames != null && allDynamicIdentifiers == null) {				
			
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
			checkboxNoIdentifier.setRolloverIcon(UIManager.getIcon("CheckBox.icon"));
			checkboxNoIdentifier.setSelectedIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_check.png"));
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
//					allDynamicIdentifiers_ScrollPane.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")");		//add toolTip										
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
								popupPanel.setPreferredSize(new Dimension(800, 400));
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
											JTextField min_TF = new JTextField(10);															
											min_TF.setText(uniqueValueList.get(0));
											rangePanel.add(min_TF, c_dP);
											
											
											//Add Label and TextField asking for max value
											c_dP.gridx = 0;
											c_dP.gridy = 2;
											rangePanel.add(new JLabel("Max value"), c_dP);
											
											c_dP.gridx = 1;
											c_dP.gridy = 2;
											JTextField max_TF = new JTextField(10);															
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
													} catch (Exception ee) {
														JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(),
																"'Min value' and 'Max value' must be numbers");
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
		
		TitledBorder border = new TitledBorder("Dynamic identifiers for PARAMETERS");
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
		this.setPreferredSize(new Dimension(250, 100));	
	}
	
	public List<List<JCheckBox>> get_CheckboxDynamicIdentifiers() {
		return checkboxDynamicIdentifiers;
	}
	
	public List<JCheckBox> get_allDynamicIdentifiers() {
		return allDynamicIdentifiers;
	}
	
	public JCheckBox get_checkboxNoIdentifier() {
		return checkboxNoIdentifier;
	}
	
	public List<JScrollPane> get_allDynamicIdentifiers_ScrollPane() {
		return allDynamicIdentifiers_ScrollPane;
	}
	
	public String get_dynamic_info_from_GUI() {			
		String dynamic_info = "";
		for (int ii = 0; ii < allDynamicIdentifiers_ScrollPane.size(); ii++) {		//Loop all dynamic identifier ScrollPanes
			if (allDynamicIdentifiers_ScrollPane.get(ii).isVisible() &&
					checkboxDynamicIdentifiers.get(ii).size() > 0) {			//get the active identifiers (when identifier ScrollPane is visible and List size >0)
				dynamic_info = dynamic_info + ii + " ";
				for (int j = 0; j < checkboxDynamicIdentifiers.get(ii).size(); j++) { //Loop all checkBoxes in this active identifier
					String checkboxName = checkboxDynamicIdentifiers.get(ii).get(j).getText();									
					//Add checkBox if it is (selected & visible) or disable
					if ((checkboxDynamicIdentifiers.get(ii).get(j).isSelected() && (checkboxDynamicIdentifiers.get(ii).get(j).isVisible())
							|| !checkboxDynamicIdentifiers.get(ii).get(j).isEnabled()))
						dynamic_info = dynamic_info + checkboxName + " ";
				}
				dynamic_info = dynamic_info + ";";
			}
		}	
		
		if (dynamic_info.equals("") || checkboxNoIdentifier.isSelected()) {
			dynamic_info = "NoIdentifier";			//= checkboxNoIdentifier.getText();
		}	
		return dynamic_info;
	}
		
}
