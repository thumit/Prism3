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

package prism_project.output;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.PrismTableModel;
import prism_convenience.TableColumnsHandle;

public class ScrollPane_ConstraintsSplitFly extends JScrollPane {
	private List<JCheckBox> static_split_checkboxes, static_descriptor_checkboxes;
	private List<JCheckBox> parameters_split_checkboxes, parameters_descriptor_checkboxes;
	private List<JCheckBox> dynamic_split_checkboxes, dynamic_descriptor_checkboxes;
	private JTable table;
	private Object[][] data;
	private JCheckBox auto_description;
	
	
	public ScrollPane_ConstraintsSplitFly(List<JCheckBox> static_checkboxes, List<JCheckBox> parameters_checkboxes, List<JCheckBox> dynamic_checkboxes) {
		GridBagConstraints c = new GridBagConstraints();
		
		// static_split_scrollpane	----------------------------------------------------------------------------------	
		static_split_checkboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : static_checkboxes) {
			static_split_checkboxes.add(new JCheckBox(i.getText()));
		}
		JPanel static_split_panel = new JPanel(new GridBagLayout());
		for (int i = 0; i < static_split_checkboxes.size(); i++) {
			static_split_panel.add(static_split_checkboxes.get(i), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, i, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		}
		JScrollPane static_split_scrollpane = new JScrollPane(static_split_panel);
		TitledBorder border = new TitledBorder("Static Identifiers - Split");
		border.setTitleJustification(TitledBorder.CENTER);
		static_split_scrollpane.setBorder(border);
		static_split_scrollpane.setPreferredSize(new Dimension(300, 250));

		
		
		
		// static_descriptor_scrollpane	----------------------------------------------------------------------------------	
		static_descriptor_checkboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : static_checkboxes) {
			static_descriptor_checkboxes.add(new JCheckBox(i.getText()));
		}
		JPanel static_descriptor_panel = new JPanel(new GridBagLayout());
		for (int i = 0; i < static_descriptor_checkboxes.size(); i++) {
			static_descriptor_panel.add(static_descriptor_checkboxes.get(i), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, i, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		}
		JScrollPane static_descriptor_scrollpane = new JScrollPane(static_descriptor_panel);
		border = new TitledBorder("Static Identifiers - Descriptor");
		border.setTitleJustification(TitledBorder.CENTER);
		static_descriptor_scrollpane.setBorder(border);
		static_descriptor_scrollpane.setPreferredSize(new Dimension(300, 250));
		
		
		
		
		// parameters_split_scrollpane	------------------------------------------------------------------------------	
		parameters_split_checkboxes = new ArrayList<JCheckBox>();
		parameters_split_checkboxes.add(new JCheckBox("Parameters"));
		JPanel parameters_split_panel = new JPanel(new GridBagLayout());
		for (int i = 0; i < parameters_split_checkboxes.size(); i++) {
			parameters_split_panel.add(parameters_split_checkboxes.get(i), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, i, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		}
		JScrollPane parameters_split_scrollpane = new JScrollPane(parameters_split_panel);
		border = new TitledBorder("Parameters - Split");
		border.setTitleJustification(TitledBorder.CENTER);
		parameters_split_scrollpane.setBorder(border);
		parameters_split_scrollpane.setPreferredSize(new Dimension(300, 250));		
		
		
		
		
		// parameters_descriptor_scrollpane	------------------------------------------------------------------------------	
		parameters_descriptor_checkboxes = new ArrayList<JCheckBox>();
		parameters_descriptor_checkboxes.add(new JCheckBox("Parameters"));
		JPanel parameters_descriptor_panel = new JPanel(new GridBagLayout());
		for (int i = 0; i < parameters_descriptor_checkboxes.size(); i++) {
			parameters_descriptor_panel.add(parameters_descriptor_checkboxes.get(i), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, i, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		}
		JScrollPane parameters_descriptor_scrollpane = new JScrollPane(parameters_descriptor_panel);
		border = new TitledBorder("Parameters - Descriptor");
		border.setTitleJustification(TitledBorder.CENTER);
		parameters_descriptor_scrollpane.setBorder(border);
		parameters_descriptor_scrollpane.setPreferredSize(new Dimension(300, 250));	
				
		
		
		
		// dynamic_split_scrollpane	------------------------------------------------------------------------------	
		dynamic_split_checkboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : dynamic_checkboxes) {
			if (i.isSelected()) {
				dynamic_split_checkboxes.add(new JCheckBox(i.getText()));
			}		
		}
		JPanel dynamic_split_panel = new JPanel(new GridBagLayout());
		for (int i = 0; i < dynamic_split_checkboxes.size(); i++) {
			dynamic_split_panel.add(dynamic_split_checkboxes.get(i), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, i, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		}
		JScrollPane dynamic_split_scrollpane = new JScrollPane(dynamic_split_panel);
		border = new TitledBorder("Dynamic Identifiers - Split");
		border.setTitleJustification(TitledBorder.CENTER);
		dynamic_split_scrollpane.setBorder(border);
		dynamic_split_scrollpane.setPreferredSize(new Dimension(300, 250));					
		
		
		
		
		// dynamic_descriptor_scrollpane	------------------------------------------------------------------------------	
		dynamic_descriptor_checkboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : dynamic_checkboxes) {
			if (i.isSelected()) {
				dynamic_descriptor_checkboxes.add(new JCheckBox(i.getText()));
			}		
		}
		JPanel dynamic_descriptor_panel = new JPanel(new GridBagLayout());
		for (int i = 0; i < dynamic_descriptor_checkboxes.size(); i++) {
			dynamic_descriptor_panel.add(dynamic_descriptor_checkboxes.get(i), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
					0, i, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		}
		JScrollPane dynamic_descriptor_scrollpane = new JScrollPane(dynamic_descriptor_panel);
		border = new TitledBorder("Dynamic Identifiers - Descriptor");
		border.setTitleJustification(TitledBorder.CENTER);
		dynamic_descriptor_scrollpane.setBorder(border);
		dynamic_descriptor_scrollpane.setPreferredSize(new Dimension(300, 250));	
				
		
		
		
		// tableScrollPane	------------------------------------------------------------------------------	
		int rowCount = 1;
		int colCount = 8;
		data = new Object[rowCount][colCount];
		String[] columnNames = new String[] {"query_id", "query_description", "query_type",  "query_multiplier", "lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty"};	         				
		data[0][3] = (double) 1;
		
		PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;      //column 0 accepts only Integer
				else if (c >= 3 && c <= 7) return Double.class;      //column 3 to 7 accept only Double values   
				else return String.class;				//Just because delete all rows make JTable fail, otherwise we should use the below line
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0) { //  The first column is un-editable
					return false;
				} else {
					return true;
				}
			}
		};
		table = new JTable(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);	//Set width of Column bigger
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
		table.getTableHeader().setReorderingAllowed(false);		//Disable columns move

		JScrollPane table_scrollpane = new JScrollPane(table);
		border = new TitledBorder("Infomation below is applied for all new queries");
		border.setTitleJustification(TitledBorder.CENTER);
		table_scrollpane.setBorder(border);
		table_scrollpane.setPreferredSize(new Dimension(600, 100));		// only the 150 matters, 650 does not matter
		// Hide columns
		TableColumnsHandle table_handle = new TableColumnsHandle(table);
		table_handle.setColumnVisible("query_id", false);
		table_handle.setColumnVisible("query_type", false);
		table_handle.setColumnVisible("lowerbound", false);
		table_handle.setColumnVisible("lowerbound_perunit_penalty", false);
		table_handle.setColumnVisible("upperbound", false);
		table_handle.setColumnVisible("upperbound_perunit_penalty", false);
		table_handle.setColumnVisible("parameter_index", false);
		table_handle.setColumnVisible("static_identifiers", false);
		table_handle.setColumnVisible("dynamic_identifiers", false);
		table_handle.setColumnVisible("original_dynamic_identifiers", false);
		

		
		
				// Add all to a pop-up panel------------------------------------------------------------------------------	
		JPanel popup_panel = new JPanel(new GridBagLayout());	
		//	These codes make the popupPanel resizable --> the Big ScrollPane resizable --> JOptionPane resizable
		popup_panel.addHierarchyListener(new HierarchyListener() {
		    public void hierarchyChanged(HierarchyEvent e) {
		        Window window = SwingUtilities.getWindowAncestor(popup_panel);
		        if (window instanceof Dialog) {
		            Dialog dialog = (Dialog)window;
		            if (!dialog.isResizable()) {
		                dialog.setResizable(true);
		            }
		        }
		    }
		});
		// Add Static Split
		popup_panel.add(static_split_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 1, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add Static descriptor
		popup_panel.add(static_descriptor_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 2, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add Dynamic Split
		popup_panel.add(dynamic_split_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 1, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add Dynamic descriptor
		popup_panel.add(dynamic_descriptor_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 2, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add Parameters Split
		popup_panel.add(parameters_split_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				2, 1, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add Parameters descriptor
		popup_panel.add(parameters_descriptor_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				2, 2, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add table scrollpane
		popup_panel.add(table_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 3, 3, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add auto description checkbox
		auto_description = new JCheckBox("Add descriptors to constraints description (i.e. bc_description)");
		auto_description.setSelected(true);
		popup_panel.add(auto_description, PrismGridBagLayoutHandle.get_c(c, "CENTER", 
				0, 4, 3, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		
		
		// Add the Panel to this Big ScrollPane------------------------------------------------------------------------------
		setBorder(null);
		setViewportView(popup_panel);		
	}
	
	
	public List<Integer> get_static_split_id() {
		List<Integer> static_split_id = new ArrayList<Integer>();
		for (int i = 0; i < static_split_checkboxes.size(); i++) {
			if (static_split_checkboxes.get(i).isSelected()) {
				static_split_id.add(i);
			}
		}
		return static_split_id;
	}
	
	
	public List<Integer> get_static_descriptor_id() {
		List<Integer> static_descriptor_id = new ArrayList<Integer>();
		for (int i = 0; i < static_descriptor_checkboxes.size(); i++) {
			if (static_descriptor_checkboxes.get(i).isSelected()) {
				static_descriptor_id.add(i);
			}
		}
		return static_descriptor_id;
	}
	
	
	public List<String> get_dynamic_split_name() {
		List<String> dynamic_split_name = new ArrayList<String>();
		for (JCheckBox i: dynamic_split_checkboxes) {
			if (i.isSelected()) {
				dynamic_split_name.add(i.getText());
			}
		}
		return dynamic_split_name;
	}
	
	
	public List<String> get_dynamic_descriptor_name() {
		List<String> dynamic_descriptor_name = new ArrayList<String>();
		for (JCheckBox i: dynamic_descriptor_checkboxes) {
			if (i.isSelected()) {
				dynamic_descriptor_name.add(i.getText());
			}
		}
		return dynamic_descriptor_name;
	}
	
	
	public Boolean is_parameters_split() {
		if (parameters_split_checkboxes.get(0).isSelected()) return true;
		return false;
	}
	
	
	public Boolean is_parameters_descriptor() {
		if (parameters_descriptor_checkboxes.get(0).isSelected()) return true;
		return false;
	}
	
	
	public Object[][] get_multiple_constraints_data() {
		return data;
	}
	
	
	public JCheckBox get_autoDescription() {
		return auto_description;
	}
	
	public void stop_editing() {
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
	}
}