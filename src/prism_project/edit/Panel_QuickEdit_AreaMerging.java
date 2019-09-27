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

import prism_convenience.IconHandle;

public class Panel_QuickEdit_AreaMerging extends JPanel {
	private JButton apply_method;
	private JButton apply_implementation;
	private Prism_ApplyButton apply_percentage;
	private Prism_ApplyButton apply_number;
	
	public Panel_QuickEdit_AreaMerging(JTable table, Object[][] data) {
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
		add(new JLabel("merging_method"), c);
	
		// Add comboBox
		class Combo_merging_method extends JComboBox {
			public Combo_merging_method() {
				addItem("exact");
				addItem("relaxed percentage (RP)");
				addItem("relaxed number (RN)");
				addItem("RP and RN");
				addItem("RP or RN");
				setSelectedIndex(0);
			}
		}
		JComboBox methodComBo = new Combo_merging_method();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(methodComBo, c);
		
		// Add button apply
		apply_method = new JButton();
		apply_method.setToolTipText("make changes for all highlighted rows");
		apply_method.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		apply_method.addActionListener(new ActionListener() {
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
					if (!data[i][1].equals("TEXT")) data[i][5] = methodComBo.getSelectedItem();		// only apply the change if the data_type is not "TEXT"
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
		add(apply_method, c);	
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("relaxed percentage (RP)"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield percentage_textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(percentage_textfield, c);
		
		// Add button apply
		apply_percentage = new Prism_ApplyButton(table, data, 6, percentage_textfield);		// 6 is the column to change
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(apply_percentage, c);
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("relaxed number (RN)"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield number_textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(number_textfield, c);
		
		// Add button apply
		apply_number = new Prism_ApplyButton(table, data, 7, number_textfield);		// 7 is the column to change
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(apply_number, c);		
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("implementation"), c);
	
		// Add checkBox
		JCheckBox implement_Check = new JCheckBox();
		implement_Check.setSelected(true);
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(implement_Check, c);
	
		// Add button apply
		// Add button apply
		apply_implementation = new JButton();
		apply_implementation.setToolTipText("make changes for all highlighted rows");
		apply_implementation.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		apply_implementation.addActionListener(new ActionListener() {
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
					if (implement_Check.isSelected()) {
						data[i][8] = true;
					} else {
						data[i][8] = false;
					}	
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				}
//				reset_view_without_changing_label();
			}
		});
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(apply_implementation, c);	
		
		
		// Add empty Label to push other component up top------------------------------------------------------------
		c.gridx = 0;
		c.gridy = 8;
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
						if (!formatedTextField.getText().isEmpty() && !formatedTextField.getText().equals(".") && !data[i][1].equals("TEXT")) {	// Only apply the changes to selected rows when the text is not empty and data_type is not "TEXT"
							data[i][column_to_change] = Double.valueOf(formatedTextField.getText());
						} else {
							data[i][column_to_change] = null;	// allow null to bet set
						}
						table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
					}
				}
			});	
		}
	}
	
	
	public void disable_all_apply_buttons() {
		apply_method.setEnabled(false);
		apply_percentage.setEnabled(false);
		apply_number.setEnabled(false);
	}
	
	
	public void enable_all_apply_buttons() {
		apply_method.setEnabled(true);
		apply_percentage.setEnabled(true);
		apply_number.setEnabled(true);
	}
}
