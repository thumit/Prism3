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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import prism_convenience_class.IconHandle;


public class QuickEdit_FlowConstraints_Panel extends JPanel {
	public QuickEdit_FlowConstraints_Panel(JTable table, Object[][] data) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("flow_type"), c);
	
		// Add comboBox
		class comboBox_constraint_type extends JComboBox {	
			public comboBox_constraint_type() {
//				addItem("SOFT");
				addItem("HARD");
				addItem("FREE");
				setSelectedIndex(0);
			}
		}
		JComboBox typeComBo = new comboBox_constraint_type();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(typeComBo, c);
		
		// Add button apply
		JButton btnApply_type = new JButton();
		btnApply_type.setToolTipText("make changes for all highlighted rows");
		btnApply_type.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApply_type.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Get selected rows
				int[] selectedRow = table.getSelectedRows();
				/// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
				}
				table.clearSelection(); // To help trigger the row refresh: clear then add back the rows
				for (int i : selectedRow) {
					data[i][3] = typeComBo.getSelectedItem();
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				}
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_type, c);	
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("lowerbound_percentage"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield lb_percentage = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(lb_percentage, c);
		
		// Add button apply
		Prism_ApplyButton apply_lb_percentage = new Prism_ApplyButton(table, data, 4, lb_percentage);		// 4 is the column to change
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(apply_lb_percentage, c);
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("upperbound_percentage"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield ub_percentage = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(ub_percentage, c);
		
		// Add button apply
		Prism_ApplyButton apply_ub_percentage = new Prism_ApplyButton(table, data, 5, ub_percentage);		// 5 is the column to change
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(apply_ub_percentage, c);		
				
		// Add empty Label to push other component up top------------------------------------------------------------
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(new JLabel(), c);
	}
	
	
	private class Prism_FormatedTextfield extends JFormattedTextField {
		public Prism_FormatedTextfield() {
			setToolTipText("greater than or equal to zero");
			getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					Runnable format = new Runnable() {
						@Override
						public void run() {
							String text = getText();
//							if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
							if (!text.matches("\\d*(\\.\\d{0,})?")) {		//	no restriction on number of digits after the dot
								setText(text.substring(0, text.length() - 1));
							} else {
								if (!text.isEmpty() && !text.equals(".") && (Double.valueOf(text) < (double) 0)) {		// If the added String make value <0   then delete that String
									setText(text.substring(0, text.length() - 1));
								}
							}	
						}
					};
					SwingUtilities.invokeLater(format);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {

				}

				@Override
				public void changedUpdate(DocumentEvent e) {

				}
			});
		}
	}
	
	
	private class Prism_ApplyButton extends JButton {
		public Prism_ApplyButton(JTable table, Object[][] data, int column_to_change, Prism_FormatedTextfield formatedTextField) {
		setToolTipText("make changes for all highlighted rows");
		setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					// Get selected rows
					int[] selectedRow = table.getSelectedRows();
					/// Convert row index because "Sort" causes problems
					for (int i = 0; i < selectedRow.length; i++) {
						selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
					}
					table.clearSelection(); // To help trigger the row refresh: clear then add back the rows
					for (int i : selectedRow) {
						if (!formatedTextField.getText().isEmpty() && !formatedTextField.getText().equals(".")) {	// Only apply the changes to selected rows when the text is not empty
							data[i][column_to_change] = Double.valueOf(formatedTextField.getText());
						} else {
							data[i][column_to_change] = null;
						}
						table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
					}
				}
			});	
		}
	}
}
