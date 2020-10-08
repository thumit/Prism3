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
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import prism_convenience.IconHandle;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.PrismTableModel;

public class Panel_QuickEdit_PrescriptionAssignment extends JPanel {
	private JTable table4a;
	private Object[][] data4a;
	private DefaultTableCellRenderer render4a;
	private JButton btn_compact;

	public Panel_QuickEdit_PrescriptionAssignment(JTable table4a, Object[][] data4a, JButton mass_check, JButton mass_uncheck) {
		this.table4a = table4a;
		this.data4a = data4a;
		this.render4a = (DefaultTableCellRenderer) table4a.getColumnModel().getColumn(0).getCellRenderer();
		setLayout(new GridBagLayout());
		
		
		// -------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------
		JPanel combine_panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		

		// Add button check all--------------------------------------------------------------------------------------
//		combine_panel.add(new JLabel("check"), PrismGridBagLayoutHandle.get_c(c, "CENTER", 
//				0, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
//				0, 0, 0, 0));		// insets top, left, bottom, right
		combine_panel.add(mass_check, PrismGridBagLayoutHandle.get_c(c, "CENTER", 
				0, 0, 1, 2, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		// Add button uncheck all------------------------------------------------------------------------------------
//		combine_panel.add(new JLabel("uncheck"), PrismGridBagLayoutHandle.get_c(c, "CENTER", 
//				1, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
//				0, 0, 0, 0));		// insets top, left, bottom, right
		combine_panel.add(mass_uncheck, PrismGridBagLayoutHandle.get_c(c, "CENTER", 
				1, 0, 1, 2, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		// Add Label-------------------------------------------------------------------------------------------------
//		combine_panel.add(new JLabel("switch view"), PrismGridBagLayoutHandle.get_c(c, "CENTER", 
//				2, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
//				0, 0, 0, 0));		// insets top, left, bottom, right
		// Add button compact view
		btn_compact = new JButton();
		btn_compact.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_compact.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_compact.setToolTipText("switch to compact view");
		btn_compact.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_script_gray.png"));
		btn_compact.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_script_gray.png"));
		btn_compact.setContentAreaFilled(false);
		btn_compact.addActionListener(e -> {
			switch (btn_compact.getToolTipText()) {
			case "switch to compact view":
				btn_compact.setToolTipText("switch to full view");
				btn_compact.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_script.png"));
				btn_compact.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_script.png"));
				break;
			case "switch to full view":
				btn_compact.setToolTipText("switch to compact view");
				btn_compact.setIcon(IconHandle.get_scaledImageIcon(18, 18, "icon_script_gray.png"));
				btn_compact.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_script_gray.png"));
				break;
			}
			reset_view_without_changing_label();
		});
		combine_panel.add(btn_compact, PrismGridBagLayoutHandle.get_c(c, "CENTER", 
				2, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		// -------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------
		
				
		// Add to this big panel
		add(combine_panel, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 0, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
	}
	
	
	public void disable_all_apply_buttons() {
		btn_compact.setEnabled(false);
	}
	
	public void enable_all_apply_buttons() {
		btn_compact.setEnabled(true);
		reset_view_without_changing_label();
	}
	
	public void reset_view_without_changing_label() {
		if (table4a.isEditing()) table4a.getCellEditor().cancelCellEditing();
		
		switch (btn_compact.getToolTipText()) {
		case "switch to full view":
			RowFilter<Object, Object> compact_filter = new RowFilter<Object, Object>() {
				public boolean include(Entry entry) {
					for (int col = 2; col < data4a[0].length; col++) {	// except the first 2 columns
						if ((boolean) entry.getValue(col) == true) {
							return true;		// if 1 cell in this row has true value then show the row
						}
					}
					return false;	// hide the row when all cells have the value of zero
				}
			};
			TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>((PrismTableModel) table4a.getModel());
			sorter.setRowFilter(compact_filter);
			table4a.setRowSorter(sorter);
			
			// Set Color and Alignment for Cells
	        DefaultTableCellRenderer compact_r = new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object
				value, boolean isSelected, boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setHorizontalAlignment(JLabel.LEFT);
					setBackground(new Color(160, 160, 160));	// Set cell background color	
					if (isSelected)	setBackground(table.getSelectionBackground());	// Set background color	for selected row
	                return this;
	            }
	        };
			for (int i = 0; i < 2; i++) {	// first 2 columns only
				table4a.getColumnModel().getColumn(i).setCellRenderer(compact_r);
			}
			break;
		case "switch to compact view":
			table4a.setRowSorter(null);
			for (int i = 0; i < 2; i++) {	// first 2 columns only
				table4a.getColumnModel().getColumn(i).setCellRenderer(render4a);
			}
			break;
		}
	}
}
