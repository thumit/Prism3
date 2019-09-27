/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import prism_convenience.ColorUtil;
import prism_project.data_process.Read_Database;



public class ScrollPane_StaticIdentifiers extends JScrollPane {
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	private List<JLabel> layers_Title_Label;
	private JPanel identifiersPanel;
	private int option;

	public ScrollPane_StaticIdentifiers(Read_Database read_Database, int option, String panel_name) {
		this.option = option;
		
		// option = 0 --> 6 layers		1 --> 4 layers       2 --> 6 layers + method_period		3 --> method choice     4 --> 6 layers +  method choice rotation_period rotation_age regen_layer5
		List<String> layers_title = new ArrayList<>(read_Database.get_layers_title());
		List<String> layers_Title_ToolTip = new ArrayList<>(read_Database.get_layers_title_tooltip());
		List<List<String>> allLayers = new ArrayList<>(read_Database.get_all_layers());
		List<List<String>> allLayers_ToolTips = new ArrayList<>(read_Database.get_all_layers_tooltips());

		
		if (option == 1) {
			for (int count = 0; count <= 1; count++) {	// a loop to remove the last layer 2 times
				layers_title.remove(layers_title.size() - 1);
				layers_Title_ToolTip.remove(layers_Title_ToolTip.size() - 1);
				allLayers.remove(allLayers.size() - 1);
				allLayers_ToolTips.remove(allLayers_ToolTips.size() - 1);
			}
		}
		
		
		if (option == 2) {
			// Add 2 more into static identifiers
			List<String> method_period_layers_title = new ArrayList<>(read_Database.get_method_period_layers_title());
			List<List<String>> method_period_layers = new ArrayList<>(read_Database.get_method_period_layers());

			layers_title.addAll(method_period_layers_title);
			layers_Title_ToolTip.addAll(method_period_layers_title);
			allLayers.addAll(method_period_layers);
			
			// Full name of silviculture methods
			List<List<String>> allmethods_ToolTips = read_Database.get_method_period_layers();
			for (int i = 0; i < allmethods_ToolTips.get(0).size(); i++) {	// 0 is method, 1 is period
				if (allmethods_ToolTips.get(0).get(i).equals("NG_E")) 	allmethods_ToolTips.get(0).set(i, "Natural Growth existing: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("PB_E")) 	allmethods_ToolTips.get(0).set(i, "Prescribed Burn existing: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("GS_E")) 	allmethods_ToolTips.get(0).set(i, "Group Selection existing: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("EA_E")) 	allmethods_ToolTips.get(0).set(i, "Even Age existing: choices 0-5");
				if (allmethods_ToolTips.get(0).get(i).equals("MS_E")) 	allmethods_ToolTips.get(0).set(i, "Mixed Severity Wildfire: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("BS_E")) 	allmethods_ToolTips.get(0).set(i, "Severe Bark Beetle: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("NG_R")) 	allmethods_ToolTips.get(0).set(i, "Natural Growth regeneration: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("PB_R")) 	allmethods_ToolTips.get(0).set(i, "Prescribed Burn regeneration: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("GS_R")) 	allmethods_ToolTips.get(0).set(i, "Group Selection regeneration: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("EA_R")) 	allmethods_ToolTips.get(0).set(i, "Even Age regeneration: choices 0-5");		
			}	
			allLayers_ToolTips.addAll(allmethods_ToolTips);
		}
		
		
		if (option == 3) {
			layers_title.removeAll(layers_title);
			layers_Title_ToolTip.removeAll(layers_Title_ToolTip);
			allLayers.removeAll(allLayers);
			allLayers_ToolTips.removeAll(allLayers_ToolTips);			
			
			// Add 2 more into static identifiers
			// Add 2 more into static identifiers
			List<String> method_choice_layers_title = new ArrayList<>(read_Database.get_method_choice_layers_title());
			List<List<String>> method_choice_layers = new ArrayList<>(read_Database.get_method_choice_layers());

			layers_title.addAll(method_choice_layers_title);
			layers_Title_ToolTip.addAll(method_choice_layers_title);
			allLayers.addAll(method_choice_layers);
			
			// Full name of silviculture methods
			List<List<String>> allmethods_ToolTips = read_Database.get_method_choice_layers();
			for (int i = 0; i < allmethods_ToolTips.get(0).size(); i++) {	// 0 is method, 1 is choice
				if (allmethods_ToolTips.get(0).get(i).equals("NG_E")) 	allmethods_ToolTips.get(0).set(i, "Natural Growth existing: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("PB_E")) 	allmethods_ToolTips.get(0).set(i, "Prescribed Burn existing: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("GS_E")) 	allmethods_ToolTips.get(0).set(i, "Group Selection existing: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("EA_E")) 	allmethods_ToolTips.get(0).set(i, "Even Age existing: choices 0-5");
				if (allmethods_ToolTips.get(0).get(i).equals("MS_E")) 	allmethods_ToolTips.get(0).set(i, "Mixed Severity Wildfire: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("BS_E")) 	allmethods_ToolTips.get(0).set(i, "Severe Bark Beetle: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("NG_R")) 	allmethods_ToolTips.get(0).set(i, "Natural Growth regeneration: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("PB_R")) 	allmethods_ToolTips.get(0).set(i, "Prescribed Burn regeneration: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("GS_R")) 	allmethods_ToolTips.get(0).set(i, "Group Selection regeneration: choices 0-14");
				if (allmethods_ToolTips.get(0).get(i).equals("EA_R")) 	allmethods_ToolTips.get(0).set(i, "Even Age regeneration: choices 0-5");		
			}	
			allLayers_ToolTips.addAll(allmethods_ToolTips);			
		}
		
		
		if (option == 4) {		// This option is designed to use more attribute for silviculture method windows
			// Add many more into static identifiers
			List<String> method_choice_rotationperiod_rotationage_regenlayer5_layers_title = new ArrayList<>(read_Database.get_method_choice_rotationperiod_rotationage_regenlayer5_layers_title());
			List<List<String>> method_choice_rotationperiod_rotationage_regenlayer5_layers = new ArrayList<>(read_Database.get_method_choice_rotationperiod_rotationage_regenlayer5_layers());

			layers_title.addAll(method_choice_rotationperiod_rotationage_regenlayer5_layers_title);
			layers_Title_ToolTip.addAll(method_choice_rotationperiod_rotationage_regenlayer5_layers_title);
			allLayers.addAll(method_choice_rotationperiod_rotationage_regenlayer5_layers);
			
			// Tool tips
			List<List<String>> tool_tip = new ArrayList<>(read_Database.get_method_choice_rotationperiod_rotationage_regenlayer5_layers());
			allLayers_ToolTips.addAll(new ArrayList<>(tool_tip));
			
			for (int i = 0; i < allLayers_ToolTips.get(6).size(); i++) {	// 0 is method, 1 is period
				if (allLayers_ToolTips.get(6).get(i).equals("NG_E")) 	allLayers_ToolTips.get(6).set(i, "Natural Growth existing: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("PB_E")) 	allLayers_ToolTips.get(6).set(i, "Prescribed Burn existing: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("GS_E")) 	allLayers_ToolTips.get(6).set(i, "Group Selection existing: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("EA_E")) 	allLayers_ToolTips.get(6).set(i, "Even Age existing: choices 0-5");
				if (allLayers_ToolTips.get(6).get(i).equals("MS_E")) 	allLayers_ToolTips.get(6).set(i, "Mixed Severity Wildfire: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("BS_E")) 	allLayers_ToolTips.get(6).set(i, "Severe Bark Beetle: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("NG_R")) 	allLayers_ToolTips.get(6).set(i, "Natural Growth regeneration: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("PB_R")) 	allLayers_ToolTips.get(6).set(i, "Prescribed Burn regeneration: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("GS_R")) 	allLayers_ToolTips.get(6).set(i, "Group Selection regeneration: choices 0-14");
				if (allLayers_ToolTips.get(6).get(i).equals("EA_R")) 	allLayers_ToolTips.get(6).set(i, "Even Age regeneration: choices 0-5");		
			}	
			
//			// Remove the last 3 layers, might be used later in the future if we want to use more attributes
//			for (int count = 0; count <= 2; count++) {	// a loop to remove the last layer 3 times = remove 3 layers
//				layers_title.remove(layers_title.size() - 1);
//				layers_Title_ToolTip.remove(layers_Title_ToolTip.size() - 1);
//				allLayers.remove(allLayers.size() - 1);
//				allLayers_ToolTips.remove(allLayers_ToolTips.size() - 1);
//			}
		}
		
		
		int total_staticIdentifiers = allLayers.size();
		
		
		//Add all layers labels and CheckBoxes to identifiersPanel
		identifiersPanel = new JPanel(new GridBagLayout());		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
	    c1.weighty = 1;

    
		//Add all layers labels
	    layers_Title_Label = new ArrayList<JLabel>();
		for (int i = 0; i < total_staticIdentifiers; i++) {
			
			JLabel title = new JLabel(layers_title.get(i));
			String title_tooltip = layers_Title_ToolTip.get(i);
			
			layers_Title_Label.add(title);
			if (layers_title.get(i).equals("layer6")) {
				title.setForeground(Color.RED);
				layers_Title_Label.get(i).setToolTipText(title_tooltip + " (only apply to existing strata, i.e. methods with _E)");
			} else if (layers_title.get(i).equals("rotation_period") || layers_title.get(i).equals("rotation_age") || layers_title.get(i).equals("regen_layer5")) {
				title.setForeground(Color.BLUE);
				layers_Title_Label.get(i).setToolTipText(title_tooltip + " (only apply to even-age methods, i.e. EA_E and EA_R)");
			} else {
				layers_Title_Label.get(i).setToolTipText(title_tooltip);
			}					
			
			
			//add listeners to select all or deselect all
			int curent_index = i;
			layers_Title_Label.get(curent_index).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (layers_Title_Label.get(curent_index).isEnabled()) {	
						for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							if (checkboxStaticIdentifiers.get(curent_index).get(j).isVisible()) {		// to prevent select or de-select invisible periods which slow the Table Filter
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(false);
							}						
						}
						layers_Title_Label.get(curent_index).setEnabled(false);
					} else {
						for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							if (checkboxStaticIdentifiers.get(curent_index).get(j).isVisible()) {		// to prevent select or de-select invisible periods which slow the Table Filter
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(true);
							}							
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
				
				if (layers_title.get(i).equals("layer6")) {
					checkboxStaticIdentifiers.get(i).get(j).setForeground(Color.RED);
				} else if (layers_title.get(i).equals("rotation_period") || layers_title.get(i).equals("rotation_age") || layers_title.get(i).equals("regen_layer5")) {
					checkboxStaticIdentifiers.get(i).get(j).setForeground(Color.BLUE);
				} else {
					
				}
				
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
						
				// Disable method EA_E and EA_R in Non-EA Management window
				if (i == 0 && option == 3) {	 // i=0 --> method (this option only has choice and method in the GUI)
					for (JCheckBox cb : checkboxStaticIdentifiers.get(i)) {
						if (cb.getText().equals("EA_E") || cb.getText().equals("EA_R") || cb.getText().equals("MS_E") || cb.getText().equals("BS_E")) {
							cb.setEnabled(false);
						}
					}
				}
			}
		}
		
		
		
		
		// add listeners to select all or de-select all
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean is_at_least_one_item_checked = false;
				for (int i = 0; i < total_staticIdentifiers; i++) {		//Loop all layers
					for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
						if (checkboxStaticIdentifiers.get(i).get(j).isSelected()) {
							is_at_least_one_item_checked = true;
							break;
						}
					}
				}
				
				if (is_at_least_one_item_checked) {
					for (int i = 0; i < total_staticIdentifiers; i++) {		//Loop all layers
						for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
							checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
							layers_Title_Label.get(i).setEnabled(false);
						}
					}
				} else {
					for (int i = 0; i < total_staticIdentifiers; i++) {		//Loop all layers
						for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
							checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
							layers_Title_Label.get(i).setEnabled(true);
						}
					}
				}
			}
		});		
	    
	    
		setViewportView(identifiersPanel);
		setViewportBorder(null);
		TitledBorder border = new TitledBorder(panel_name);
		border.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
		setPreferredSize(new Dimension(0, 250));
	}
	
	
	public List<List<JCheckBox>> get_CheckboxStaticIdentifiers() {
		return checkboxStaticIdentifiers;
	}
	
	
	public List<JCheckBox> get_static_layer_title_as_checkboxes() {
		List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		// a temporary List
		for (int i = 0; i < layers_Title_Label.size(); i++) {
			temp_List.add(new JCheckBox(layers_Title_Label.get(i).getText()));
		}
		return temp_List;
	}
	
	
	public String get_static_description_from_GUI() {		
		List<String> temp = new ArrayList<String>();
		
		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {		//Loop all static identifiers
			// Count the total of checked items in each layer 
			int total_check_items = 0;
			int total_items = 0;
			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {		//Loop all elements in each layer
				if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible() && checkboxStaticIdentifiers.get(i).get(j).isEnabled()) {
					total_check_items++;
				}
				if (checkboxStaticIdentifiers.get(i).get(j).isVisible() && checkboxStaticIdentifiers.get(i).get(j).isEnabled()) {
					total_items++;
				}	
			}
			
			// Add to description only when the total of checked items < the total items
			if (total_check_items < total_items) {
				String static_info = layers_Title_Label.get(i).getText();
				
				for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {		//Loop all elements in each layer
					String checkboxName = checkboxStaticIdentifiers.get(i).get(j).getText();				
					//Add checkBox if it is (selected & visible & enable
					if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible() && checkboxStaticIdentifiers.get(i).get(j).isEnabled()) {	
						static_info = String.join(" ", static_info, checkboxName);	
					}
				}
				temp.add(static_info);
			}
		}	
		
		if (temp.isEmpty()) {
			if (option == 0) {
				return "all strata";
			} else if (option == 3) {
				return "All methods and choices";
			} else {
				return "No static restriction";
			}
		} else {
			String joined_string = String.join(" | ", temp);
			return joined_string;
		}
	}
	
	
	public String get_static_info_from_GUI() {			
		String static_info = "";
		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {		//Loop all static identifiers
			static_info = static_info + i + " ";
			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {		//Loop all elements in each layer
				String checkboxName = checkboxStaticIdentifiers.get(i).get(j).getText();				
				//Add checkBox if it is (selected & visible & enable
				if (checkboxStaticIdentifiers.get(i).get(j).isSelected() && checkboxStaticIdentifiers.get(i).get(j).isVisible() && checkboxStaticIdentifiers.get(i).get(j).isEnabled()) {
					static_info = static_info + checkboxName + " ";	
				}
			}
						
			if (!static_info.equals("")) {
				static_info = static_info.substring(0, static_info.length() - 1) + ";";		// remove the last space, and add ;
			}
		}	
		
		if (!static_info.equals("")) {
			static_info = static_info.substring(0, static_info.length() - 1);		// remove the last ;
		}
				
		return static_info;
	}
	
	
	public void reload_this_constraint_static_identifiers(String static_identifiers_info) {	
		// Note: static_identifiers_info: contains all the selected static identifiers	
		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {			
			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {					
				checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
			}
		}
				
		// Read the whole cell into array
		String[] info = static_identifiers_info.split(";");
		int total_static_identifiers = info.length;
		
		// Get all static Identifiers
		for (int i = 0; i < total_static_identifiers; i++) {	
			String[] identifier_elements = info[i].split("\\s+");	// Space delimited
			int current_identifier_id = Integer.valueOf(identifier_elements[0]);
						
			// Reload the selection			
			for (int j = 1; j < identifier_elements.length; j++) {		//Ignore the first element which is the identifier id
				String this_identifier_attribute = identifier_elements[j].replaceAll("\\s+","");		//Add element name, if name has spaces then remove all the spaces												
				for (JCheckBox k: checkboxStaticIdentifiers.get(current_identifier_id)) {
					if (k.getText().equals(this_identifier_attribute)) {
						k.setSelected(true);
					}
				}
			}
		}
	}
	
	public void highlight() {
		setBackground(new Color(240, 255, 255));
		identifiersPanel.setBackground(ColorUtil.makeTransparent(new Color(240, 255, 255), 255));
		revalidate();
		repaint();
	}
	
	public void unhighlight() {			
		setBackground(null);
		identifiersPanel.setBackground(null);
		revalidate();
		repaint();
	}
}
