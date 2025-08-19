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

package prism_project.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import prism_convenience.ColorUtil;
import prism_convenience.IconHandle;
import prism_project.data_process.Read_Database;
import prism_root.Prism3Main;

public class ScrollPane_Parameters extends JScrollPane {	
	private JCheckBox checkboxNoParameter, checkboxCostParameter;
	private List<JCheckBox> checkboxParameter;
	private JPanel parametersPanel;
	
	public ScrollPane_Parameters(Read_Database read_database) {		
		String[] yield_tables_column_names = read_database.get_yield_tables_column_names();
		
		parametersPanel = new JPanel();	
		parametersPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.weightx = 1;
	    c2.weighty = 1;
	    
		setViewportView(parametersPanel);
		setViewportBorder(null);
	    		
		if (yield_tables_column_names != null && checkboxParameter == null) {				
			checkboxParameter = new ArrayList<JCheckBox>();
			
			for (int i = 0; i < yield_tables_column_names.length; i++) {
				String YTcolumnName = yield_tables_column_names[i];

				checkboxParameter.add(new JCheckBox(YTcolumnName));		//add checkbox
				String tip = read_database.get_parameter_tooltip(YTcolumnName) + " (Column index: " + i + ")";
				checkboxParameter.get(i).setToolTipText(tip);		//add toolTip
				
//				// Disable Parameter check box if unit is not per Acre
//				if (!tip.contains("per Acre")) {
//					checkboxParameter.get(i).setEnabled(false);
//				}
//				
				// Disable Parameter check box if the minimum unique value is not a double 
				try {
					Double.parseDouble(read_database.get_col_unique_values_list(i).get(0));
				} catch (NumberFormatException e) {
					checkboxParameter.get(i).setEnabled(false);
				}
				
				// add checkboxParameter to the Panel
			    c2.gridx = 0;
			    c2.gridy = 2 + i;
				c2.weightx = 1;
			    c2.weighty = 1;
				parametersPanel.add(checkboxParameter.get(i), c2);
			}
			
			
			//Add checkboxNoParameter for the option of not using any Column, use 1 instead as multiplier
			checkboxNoParameter = new JCheckBox();			
			checkboxNoParameter.setText("NoParameter");	
			checkboxNoParameter.setRolloverIcon(UIManager.getIcon("CheckBox.icon"));
			checkboxNoParameter.setSelectedIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_check.png"));
			checkboxNoParameter.setToolTipText("Area (acres)");		//set toolTip
			// add the checkBox to the Panel
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 1;
			c2.weighty = 1;
			parametersPanel.add(checkboxNoParameter, c2);
			
			
			//Add checkboxCostParameter for the option of using cost info
			checkboxCostParameter = new JCheckBox();			
			checkboxCostParameter.setText("CostParameter");	
			checkboxCostParameter.setRolloverIcon(UIManager.getIcon("CheckBox.icon"));
			checkboxCostParameter.setSelectedIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_check.png"));
			checkboxCostParameter.setToolTipText("Cost (currency) with details based on Management Cost window");		//set toolTip			
			// add the checkBox to the Panel
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 1;
			c2.weighty = 1;
			parametersPanel.add(checkboxCostParameter, c2);			
			
			
			// Add listeners to de-select all other checkBoxes
			checkboxNoParameter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (checkboxNoParameter.isSelected()) {
						checkboxCostParameter.setSelected(false);
						for (int i = 0; i < yield_tables_column_names.length; i++) {
							checkboxParameter.get(i).setSelected(false);
						} 
					}
				}
			});		
			
			
			// Add listeners to de-select all other checkBoxes
			checkboxCostParameter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (checkboxCostParameter.isSelected()) {
						checkboxNoParameter.setSelected(false);
						for (int i = 0; i < yield_tables_column_names.length; i++) {
							checkboxParameter.get(i).setSelected(false);
						} 
					}
				}
			});				
			
			
			// Add listeners to checkBox so if then name has AllSx then other checkbox would be deselected 
			for (int i = 0; i < yield_tables_column_names.length; i++) {
				String currentCheckBoxName = yield_tables_column_names[i];
				int currentCheckBoxIndex = i;
				
				checkboxParameter.get(i).addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						//Deselect the checkboxNoParameter & checkboxCostParameter
						checkboxNoParameter.setSelected(false);
						checkboxCostParameter.setSelected(false);
						
//						if (currentCheckBoxName.contains("AllSx")) {
//							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
//								if (j!=currentCheckBoxIndex) 	checkboxParameter.get(j).setSelected(false);
//							}
//						} else {
//							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
//								if (checkboxParameter.get(j).getText().contains("AllSx")) 	checkboxParameter.get(j).setSelected(false);
//							}
//						}					
					}
				});
			}

			//Do a resize to same size for JInteral Frame of the project to help repaint the checkboxVariables added					
			Prism3Main.get_Prism_DesktopPane().getSelectedFrame().setSize(Prism3Main.get_Prism_DesktopPane().getSelectedFrame().getSize());	
		}

		
		// add listeners to select NoParameter only
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkboxNoParameter.setSelected(true);
				for (int i = 0; i < yield_tables_column_names.length; i++) {
					checkboxParameter.get(i).setSelected(false);
				} 
				checkboxCostParameter.setSelected(false);
			}
		});
				
		
		TitledBorder border = new TitledBorder("Parameters");
		border.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
    	setPreferredSize(new Dimension(200, 100));
	}
	
	public JCheckBox get_checkboxNoParameter() {
		return checkboxNoParameter;
	}
	
	public JCheckBox get_checkboxCostParameter() {
		return checkboxCostParameter;
	}
	
	public List<JCheckBox> get_checkboxParameters() {
		return checkboxParameter;
	}
	
	public String get_parameters_description_from_GUI() {	
		List<String> temp = new ArrayList<String>(); 
		
		for (JCheckBox i : checkboxParameter) {
			if (i.isSelected()) {
				temp.add(i.getText());	// add the the selected Columns names to this list
			}
		}
		
		if (temp.isEmpty()) {
			if (checkboxCostParameter.isSelected()) {
				temp.add("Cost (i.e. dollars)");
			} else {
				temp.add("Area (i.e. acres)");
			}
		}		

		String joined_string = String.join(" + ", temp);
		return joined_string;
	}
	
	public String get_parameters_info_from_GUI() {			
		String parameters_info = "";
		for (int j = 0; j < checkboxParameter.size(); j++) {
			if (checkboxParameter.get(j).isSelected()) {			//add the index of selected Columns to this String
				parameters_info = parameters_info + j + " ";
			}
		}
		
		if (parameters_info.equals("") || checkboxNoParameter.isSelected()) {
			parameters_info = "NoParameter";		//= parametersScrollPanel.checkboxNoParameter.getText();
		}		
		
		if (checkboxCostParameter.isSelected()) {
			parameters_info = "CostParameter";		//= parametersScrollPanel.checkboxCostParameter.getText();
		}	
		
		return parameters_info;
	}
	
	public void reload_this_constraint_parameters(String parameters_info) {	
		// Note: parameters_info: contains all the selected parameters	
		for (int i = 0; i < checkboxParameter.size(); i++) {			
			checkboxParameter.get(i).setSelected(false);
			checkboxNoParameter.setSelected(false);
			checkboxCostParameter.setSelected(false);
		}
				
		// Read the whole cell into array
		String[] info = parameters_info.split("\\s+");	// Space delimited
		
		// Get all parameters & Reload the check
		if (info[0].equalsIgnoreCase("NoParameter")) {
			checkboxNoParameter.setSelected(true);
		} else if (info[0].equalsIgnoreCase("CostParameter")) {
			checkboxCostParameter.setSelected(true);
		} else {		
			for (int i = 0; i < info.length; i++) {	
				int current_parameter_id = Integer.valueOf(info[i]);
				checkboxParameter.get(current_parameter_id).setSelected(true);						
			}
		}
	}
	
	public void highlight() {			
		setBackground(new Color(240, 255, 255));
		parametersPanel.setBackground(ColorUtil.makeTransparent(new Color(240, 255, 255), 255));
		revalidate();
		repaint();
	}
	
	public void unhighlight() {			
		setBackground(null);
		parametersPanel.setBackground(null);
		revalidate();
		repaint();
	}
}
