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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import prism_convenience.ColorUtil;
import prism_project.data_process.Read_Database;

public class ScrollPane_StaticIdentifiers extends JScrollPane {
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	private List<JLabel> layers_title_label;
	private JPanel identifiers_panel;
	private int option;

	public ScrollPane_StaticIdentifiers(Read_Database read_database, int option, String panel_name) {
		this.option = option;	// option = 0 --> 6 layers		1 --> 4 layers       2 --> 6 layers + period
		List<String> layers_title = new ArrayList<>(read_database.get_layers_title());
		List<String> layers_title_toolip = new ArrayList<>(read_database.get_layers_title_tooltip());
		List<List<String>> all_layers = new ArrayList<>(read_database.get_all_layers());
		List<List<String>> all_layers_toolip = new ArrayList<>(read_database.get_all_layers_tooltips());
		//-------------------------------------------------------------------------------------------
		
		if (option == 1) {
			for (int count = 0; count <= 1; count++) {	// a loop to remove the last layer 2 times
				layers_title.remove(layers_title.size() - 1);
				layers_title_toolip.remove(layers_title_toolip.size() - 1);
				all_layers.remove(all_layers.size() - 1);
				all_layers_toolip.remove(all_layers_toolip.size() - 1);
			}
		}
		
		if (option == 2) {
			// add period into static identifiers
			List<String> period_layers_title = new ArrayList<>(read_database.get_period_layers_title());
			List<List<String>> period_layers = new ArrayList<>(read_database.get_period_layers());
			layers_title.addAll(period_layers_title);
			layers_title_toolip.addAll(period_layers_title);
			all_layers.addAll(period_layers);
			all_layers_toolip.addAll(period_layers);
		}
		//-------------------------------------------------------------------------------------------
		
		// add all layers labels and CheckBoxes to identifiersPanel
		identifiers_panel = new JPanel(new GridBagLayout());		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
	    c1.weighty = 1;
    
		// add all layers labels
	    int total_static_identifiers = all_layers.size();
	    layers_title_label = new ArrayList<JLabel>();
		for (int i = 0; i < total_static_identifiers; i++) {
			JLabel title = new JLabel(layers_title.get(i));
			String title_tooltip = layers_title_toolip.get(i);
			
			layers_title_label.add(title);
			if (layers_title.get(i).equals("layer6")) {
				title.setForeground(Color.RED);
				layers_title_label.get(i).setToolTipText(title_tooltip + " (only apply to existing strata, i.e. methods with _E)");
			} else if (layers_title.get(i).equals("rotation_period") || layers_title.get(i).equals("rotation_age") || layers_title.get(i).equals("regen_layer5")) {
				title.setForeground(Color.BLUE);
				layers_title_label.get(i).setToolTipText(title_tooltip + " (only apply to even-age methods, i.e. EA_E and EA_R)");
			} else {
				layers_title_label.get(i).setToolTipText(title_tooltip);
			}					
			
			// add listeners to select all or deselect all
			int curent_index = i;
			layers_title_label.get(curent_index).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (layers_title_label.get(curent_index).isEnabled()) {	
						for (int j = 0; j < all_layers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							if (checkboxStaticIdentifiers.get(curent_index).get(j).isVisible()) {		// to prevent select or de-select invisible periods which slow the Table Filter
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(false);
							}						
						}
						layers_title_label.get(curent_index).setEnabled(false);
					} else {
						for (int j = 0; j < all_layers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							if (checkboxStaticIdentifiers.get(curent_index).get(j).isVisible()) {		// to prevent select or de-select invisible periods which slow the Table Filter
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(true);
							}							
						}
						layers_title_label.get(curent_index).setEnabled(true);
					}
				}
			});
	
			// add to identifiersPanel
			c1.gridx = i;
			c1.gridy = 0;
			identifiers_panel.add(layers_title_label.get(i), c1);
		}
		//-------------------------------------------------------------------------------------------		
		
		// add CheckBox for all layers
		checkboxStaticIdentifiers = new ArrayList<List<JCheckBox>>();
		for (int i = 0; i < total_static_identifiers; i++) {		//Loop all layers
			List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		//A temporary List
			checkboxStaticIdentifiers.add(temp_List);
			for (int j = 0; j < all_layers.get(i).size(); j++) {		//Loop all elements in each layer
				checkboxStaticIdentifiers.get(i).add(new JCheckBox(all_layers.get(i).get(j)));
				checkboxStaticIdentifiers.get(i).get(j).setToolTipText(all_layers_toolip.get(i).get(j));
				checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
				
				if (layers_title.get(i).equals("layer6")) {
					checkboxStaticIdentifiers.get(i).get(j).setForeground(Color.RED);
				} else if (layers_title.get(i).equals("rotation_period") || layers_title.get(i).equals("rotation_age") || layers_title.get(i).equals("regen_layer5")) {
					checkboxStaticIdentifiers.get(i).get(j).setForeground(Color.BLUE);
				} else {
					
				}
				
				c1.gridx = i;
				c1.gridy = j + 1;
				identifiers_panel.add(checkboxStaticIdentifiers.get(i).get(j), c1);
				
				// make label Enable after a checkbox is selected
				int current_i = i;
				int current_j = j;
				checkboxStaticIdentifiers.get(i).get(j).addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						if (checkboxStaticIdentifiers.get(current_i).get(current_j).isSelected()) {
							layers_title_label.get(current_i).setEnabled(true);
						}					
					}
				});
			}
		}
		//-------------------------------------------------------------------------------------------
		
		// add listeners to select all or de-select all
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean is_at_least_one_item_checked = false;
				for (int i = 0; i < total_static_identifiers; i++) {		//Loop all layers
					for (int j = 0; j < all_layers.get(i).size(); j++) {		//Loop all elements in each layer
						if (checkboxStaticIdentifiers.get(i).get(j).isSelected()) {
							is_at_least_one_item_checked = true;
							break;
						}
					}
				}
				
				if (is_at_least_one_item_checked) {
					for (int i = 0; i < total_static_identifiers; i++) {		//Loop all layers
						for (int j = 0; j < all_layers.get(i).size(); j++) {		//Loop all elements in each layer
							checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
							layers_title_label.get(i).setEnabled(false);
						}
					}
				} else {
					for (int i = 0; i < total_static_identifiers; i++) {		//Loop all layers
						for (int j = 0; j < all_layers.get(i).size(); j++) {		//Loop all elements in each layer
							checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
							layers_title_label.get(i).setEnabled(true);
						}
					}
				}
			}
		});		
		//-------------------------------------------------------------------------------------------
	    
		setViewportView(identifiers_panel);
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
		for (int i = 0; i < layers_title_label.size(); i++) {
			temp_List.add(new JCheckBox(layers_title_label.get(i).getText()));
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
				String static_info = layers_title_label.get(i).getText();
				
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
		identifiers_panel.setBackground(ColorUtil.makeTransparent(new Color(240, 255, 255), 255));
		revalidate();
		repaint();
	}
	
	public void unhighlight() {			
		setBackground(null);
		identifiers_panel.setBackground(null);
		revalidate();
		repaint();
	}
}
