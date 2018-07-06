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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import prism_convenience_class.IconHandle;
import prism_convenience_class.PrismGridBagLayoutHandle;
import prism_convenience_class.TableColumnsHandle;
import prism_project.data_process.Read_Database;
import prism_root.PrismMain;

public class Panel_QuickEdit_SR extends JPanel {
	private JButton btnApplyProbability;
	private JButton btnApplyPercentage;
	
	public Panel_QuickEdit_SR(JTable table6a, Object[][] data6a, JTable table6b, Object[][] data6b) {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		
		JPanel qd1 = new JPanel();
		qd1.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		

		// Add Label-------------------------------------------------------------------------------------------------
		qd1.add(new JLabel("probability"), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				1, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 10));		// insets top, left, bottom, right

		
		// Add formatedTextfield
		JFormattedTextField formatedTextfield = new JFormattedTextField();
		formatedTextfield.setColumns(8);
		formatedTextfield.setToolTipText("0-100 percent");
		formatedTextfield.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = formatedTextfield.getText();
//						if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
						if (!text.matches("\\d*(\\.\\d{0,})?")) {		//	no restriction on number of digits after the dot
							formatedTextfield.setText(text.substring(0, text.length() - 1));
						} else {
							if (!text.isEmpty() && !text.equals(".") && (Double.valueOf(text) < (double) 0 || Double.valueOf(text) > (double) 100)) {		// If the added String make value <0 or >100 then delete that String
								formatedTextfield.setText(text.substring(0, text.length() - 1));
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
		qd1.add(formatedTextfield, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				2, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
				
		// Add button apply
		btnApplyProbability = new JButton();
		btnApplyProbability.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnApplyProbability.setHorizontalTextPosition(SwingConstants.CENTER);
		btnApplyProbability.setToolTipText("make changes for all highlighted cells, except cells in the first column");
		btnApplyProbability.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btnApplyProbability.setRolloverIcon(IconHandle.get_scaledImageIcon(30, 30, "icon_split.png"));
		btnApplyProbability.setContentAreaFilled(false);
		btnApplyProbability.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Get selected rows
				int[] selectedRow = table6a.getSelectedRows();
				int[] selectedCol = table6a.getSelectedColumns();
							
				// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table6a.convertRowIndexToModel(selectedRow[i]);
				}
				// Convert col index because "Sort" causes problems
				for (int j = 0; j < selectedCol.length; j++) {
					selectedCol[j] = table6a.convertColumnIndexToModel(selectedCol[j]);
				}
				
				for (int i : selectedRow) {
					for (int j : selectedCol) {
						if (!formatedTextfield.getText().equals(".") && j >= 2) {	// Only apply the changes to selected cells in columns >= 2 (fall the percentage columns)
							if (!formatedTextfield.getText().isEmpty()) data6a[i][j] = Double.valueOf(formatedTextfield.getText());	// Only apply the changes to selected cells in column 2 "weight", do not allow null
						}
					}
				}
				
				// just need to add 1 currently selected row (no need to add all because it would trigger a lot of "fireTableDataChanged" in "setValueAt" because of the ListSelectionListener of table6a)
				// also need re-validate and repaint so all the new data would show up after the change is triggered by the "addRowSelectionInterval"
				table6a.removeRowSelectionInterval(table6a.convertRowIndexToView(selectedRow[0]), table6a.convertRowIndexToView(selectedRow[0]));	// only trigger the data change once by remove then add 1 time
				table6a.addRowSelectionInterval(table6a.convertRowIndexToView(selectedRow[0]), table6a.convertRowIndexToView(selectedRow[0]));
				table6a.revalidate();
				table6a.repaint();
			}
		});		
		qd1.add(btnApplyProbability, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				3, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		
		
		
		JPanel qd2 = new JPanel();
		qd2.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		

		// Add Label-------------------------------------------------------------------------------------------------
		qd2.add(new JLabel("percentage"), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				1, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 10));		// insets top, left, bottom, right

		
		// Add formatedTextfield
		JFormattedTextField formatedTextfield_2 = new JFormattedTextField();
		formatedTextfield_2.setColumns(8);
		formatedTextfield_2.setToolTipText("0-100 percent");
		formatedTextfield_2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = formatedTextfield_2.getText();
//						if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
						if (!text.matches("\\d*(\\.\\d{0,})?")) {		//	no restriction on number of digits after the dot
							formatedTextfield_2.setText(text.substring(0, text.length() - 1));
						} else {
							if (!text.isEmpty() && !text.equals(".") && (Double.valueOf(text) < (double) 0 || Double.valueOf(text) > (double) 100)) {		// If the added String make value <0 or >100 then delete that String
								formatedTextfield_2.setText(text.substring(0, text.length() - 1));
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
		qd2.add(formatedTextfield_2, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				2, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
				
		// Add button apply
		btnApplyPercentage = new JButton();
		btnApplyPercentage.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnApplyPercentage.setHorizontalTextPosition(SwingConstants.CENTER);
		btnApplyPercentage.setToolTipText("make changes for all highlighted cells, except cells in the first two columns");
		btnApplyPercentage.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btnApplyPercentage.setRolloverIcon(IconHandle.get_scaledImageIcon(30, 30, "icon_split.png"));
		btnApplyPercentage.setContentAreaFilled(false);
		btnApplyPercentage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Get selected rows
				int[] selectedRow = table6b.getSelectedRows();
				int[] selectedCol = table6b.getSelectedColumns();
							
				// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table6b.convertRowIndexToModel(selectedRow[i]);
				}
				// Convert col index because "Sort" causes problems
				for (int j = 0; j < selectedCol.length; j++) {
					selectedCol[j] = table6b.convertColumnIndexToModel(selectedCol[j]);
				}
				
				for (int i : selectedRow) {
					for (int j : selectedCol) {
						if (!formatedTextfield_2.getText().equals(".") && j >= 2) {	// Only apply the changes to selected cells in columns >= 2 (all the percentage columns)
							if (!formatedTextfield_2.getText().isEmpty()) data6b[i][j] = Double.valueOf(formatedTextfield_2.getText());	// Only apply the changes to selected cells in column 2 "weight", do not allow null
						}
					}
				}
				
				// just need to add 1 currently selected row (no need to add all because it would trigger a lot of "fireTableDataChanged" in "setValueAt" because of the ListSelectionListener of table6a)
				// also need re-validate and repaint so all the new data would show up after the change is triggered by the "addRowSelectionInterval"
				table6b.removeRowSelectionInterval(table6b.convertRowIndexToView(selectedRow[0]), table6b.convertRowIndexToView(selectedRow[0]));	// only trigger the data change once by remove then add 1 time
				table6b.addRowSelectionInterval(table6b.convertRowIndexToView(selectedRow[0]), table6b.convertRowIndexToView(selectedRow[0]));
				table6b.revalidate();
				table6b.repaint();
			}
		});		
		qd2.add(btnApplyPercentage, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				3, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
				
		
		
		// Add 2 panels to this big Panel
		add(qd1, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 0, 1, 0, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		add(qd2, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				1, 0, 1, 0, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
	}
	
	
	public void disable_all_apply_buttons() {
		btnApplyProbability.setEnabled(false);
		btnApplyPercentage.setEnabled(false);
	}
	
	public void enable_all_apply_buttons() {
		btnApplyProbability.setEnabled(true);
		btnApplyPercentage.setEnabled(true);
	}
}
