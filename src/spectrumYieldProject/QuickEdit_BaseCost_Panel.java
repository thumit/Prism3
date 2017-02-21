package spectrumYieldProject;

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

import spectrumConvenienceClasses.IconHandle;

public class QuickEdit_BaseCost_Panel extends JPanel {
	public QuickEdit_BaseCost_Panel(JTable table, Object[][] data) {

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
		add(new JLabel("base_cost"), c);

		
		// Add formatedTextfield
		JFormattedTextField formatedTextfield = new JFormattedTextField();
		formatedTextfield.setToolTipText("greater than 0 with maximum 2 digits after the dot");
		formatedTextfield.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = formatedTextfield.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
							formatedTextfield.setText(text.substring(0, text.length() - 1));
						} else {
							if (!text.isEmpty() && !text.equals(".") && Double.valueOf(text) < (double) 0) {		// If the added String make value <0 then delete that String
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
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(formatedTextfield, c);
		
		
		// Add button apply
		JButton btnApplyPercentage = new JButton();
		btnApplyPercentage.setToolTipText("make changes for all highlighted cells, except cells in the first column 'action_list'");
		btnApplyPercentage.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyPercentage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Get selected rows
				int[] selectedRow = table.getSelectedRows();
				int[] selectedCol = table.getSelectedColumns();
							
				// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
				}
				// Convert col index because "Sort" causes problems
				for (int j = 0; j < selectedCol.length; j++) {
					selectedCol[j] = table.convertColumnIndexToModel(selectedCol[j]);
				}
				
				table.clearSelection(); // To help trigger the row refresh: clear then add back the rows
				for (int i : selectedRow) {
					for (int j : selectedCol) {
						if (!formatedTextfield.getText().isEmpty() && !formatedTextfield.getText().equals(".") && j != 0) {	// Only apply the changes to selected cells when the text is not empty, and column <>0 (ageclass column)
							data[i][j] = Double.valueOf(formatedTextfield.getText());
						}
						table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
						table.addColumnSelectionInterval(table.convertColumnIndexToView(j), table.convertColumnIndexToView(j));
					}
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
		add(btnApplyPercentage, c);
	}
		
}
















//public class QuickEdit_BaseCost_Panel extends JPanel {
//	public QuickEdit_BaseCost_Panel(JTable table, Object[][] data) {
//
//		setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//
//		
//		// Add Label-------------------------------------------------------------------------------------------------
//		c.gridx = 1;
//		c.gridy = 0;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.CENTER;
//		add(new JLabel("dollars_per_acre"), c);
//	
//		// Add JTextfield
//		Spectrum_FormatedTextfield per_acre_Textfield = new Spectrum_FormatedTextfield();
//		c.gridx = 1;
//		c.gridy = 1;
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.BOTH;
//		add(per_acre_Textfield, c);
//		
//		// Add button apply
//		Spectrum_ApplyButton btnApply_PerAcre = new Spectrum_ApplyButton(table, data, 2, per_acre_Textfield);		// 2 is the column to change
//		c.gridx = 0;
//		c.gridy = 1;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.BOTH;
//		add(btnApply_PerAcre, c);
//		
//		
//		// Add Label-------------------------------------------------------------------------------------------------
//		c.gridx = 1;
//		c.gridy = 2;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.CENTER;
//		add(new JLabel("dollars_per_cubicfoot"), c);
//			
//		// Add JTextfield
//		Spectrum_FormatedTextfield per_cubicfoot_Textfield = new Spectrum_FormatedTextfield();
//		c.gridx = 1;
//		c.gridy = 3;
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.BOTH;
//		add(per_cubicfoot_Textfield, c);
//		
//		// Add button apply
//		Spectrum_ApplyButton btnApply_PerCubic = new Spectrum_ApplyButton(table, data, 3, per_cubicfoot_Textfield);		// 3 is the column to change
//		c.gridx = 0;
//		c.gridy = 3;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.BOTH;
//		add(btnApply_PerCubic, c);
//		
//			
//		// Add Label-------------------------------------------------------------------------------------------------
//		c.gridx = 1;
//		c.gridy = 4;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.CENTER;
//		add(new JLabel("dollars_per_broadfoot"), c);
//	
//		// Add JTextfield
//		Spectrum_FormatedTextfield per_broadfoot_Textfield = new Spectrum_FormatedTextfield();
//		c.gridx = 1;
//		c.gridy = 5;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.BOTH;
//		add(per_broadfoot_Textfield, c);
//	
//		// Add button apply
//		Spectrum_ApplyButton btnApply_PerBroad = new Spectrum_ApplyButton(table, data, 4, per_broadfoot_Textfield);		// 4 is the column to change
//		c.gridx = 0;
//		c.gridy = 5;
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.fill = GridBagConstraints.BOTH;
//		add(btnApply_PerBroad, c);				
//	}
//	
//	
//	private class Spectrum_FormatedTextfield extends JFormattedTextField {
//		public Spectrum_FormatedTextfield() {
//			setToolTipText("greater than 0 with maximum 2 digits after the dot");
//			getDocument().addDocumentListener(new DocumentListener() {
//				@Override
//				public void insertUpdate(DocumentEvent e) {
//					Runnable format = new Runnable() {
//						@Override
//						public void run() {
//							String text = getText();
//							if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
//								setText(text.substring(0, text.length() - 1));
//							} else {
//								if (!text.isEmpty() && !text.equals(".") && Double.valueOf(text) < (double) 0) {		// If the added String make value <0  then delete that String
//									setText(text.substring(0, text.length() - 1));
//								}
//							}	
//						}
//					};
//					SwingUtilities.invokeLater(format);
//				}
//
//				@Override
//				public void removeUpdate(DocumentEvent e) {
//
//				}
//
//				@Override
//				public void changedUpdate(DocumentEvent e) {
//
//				}
//			});
//		}
//	}
//	
//	
//	private class Spectrum_ApplyButton extends JButton {
//		public Spectrum_ApplyButton(JTable table, Object[][] data, int column_to_change, Spectrum_FormatedTextfield formatedTextField) {
//		setToolTipText("make changes for all highlighted rows");
//		setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
//		addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent actionEvent) {
//					// Get selected rows
//					int[] selectedRow = table.getSelectedRows();
//					/// Convert row index because "Sort" causes problems
//					for (int i = 0; i < selectedRow.length; i++) {
//						selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);
//					}
//					table.clearSelection(); // To help trigger the row refresh: clear then add back the rows
//					for (int i : selectedRow) {
//						if (!formatedTextField.getText().isEmpty() && !formatedTextField.getText().equals(".")) {	// Only apply the changes to selected rows when the text is not empty
//							data[i][column_to_change] = Double.valueOf(formatedTextField.getText());
//						}
//						table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
//					}
//				}
//			});	
//		}
//	}
//}
