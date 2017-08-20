package prismProject;

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

import prismConvenienceClasses.IconHandle;


public class QuickEdit_BasicConstraints_Panel extends JPanel {
	public QuickEdit_BasicConstraints_Panel(JTable table, Object[][] data) {

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
		add(new JLabel("bc_type"), c);
	
		// Add comboBox
		class comboBox_constraint_type extends JComboBox {	
			public comboBox_constraint_type() {
				addItem("SOFT");
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
					data[i][2] = typeComBo.getSelectedItem();
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
		add(new JLabel("bc_multiplier"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield multiplier_Textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(multiplier_Textfield, c);
		
		// Add button apply
		Prism_ApplyButton btnApply_multiplier = new Prism_ApplyButton(table, data, 3, multiplier_Textfield);		// 3 is the column to change
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_multiplier, c);
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("lowerbound"), c);
			
		// Add JTextfield
		Prism_FormatedTextfield lowerbound_Textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(lowerbound_Textfield, c);
		
		// Add button apply
		Prism_ApplyButton btnApply_lowerbound = new Prism_ApplyButton(table, data, 4, lowerbound_Textfield);		// 4 is the column to change
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_lowerbound, c);
		
			
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("lowerbound_perunit_penalty"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield lbpenalty_Textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(lbpenalty_Textfield, c);
	
		// Add button apply
		Prism_ApplyButton btnApply_lbpenalty = new Prism_ApplyButton(table, data, 5, lbpenalty_Textfield);		// 5 is the column to change
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_lbpenalty, c);	
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("upperbound"), c);
			
		// Add JTextfield
		Prism_FormatedTextfield upperbound_Textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(upperbound_Textfield, c);
		
		// Add button apply
		Prism_ApplyButton btnApply_upperbound = new Prism_ApplyButton(table, data, 6, upperbound_Textfield);		// 6 is the column to change
		c.gridx = 0;
		c.gridy = 9;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_upperbound, c);
		
			
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 10;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("upperbound_perunit_penalty"), c);
	
		// Add JTextfield
		Prism_FormatedTextfield ubpenalty_Textfield = new Prism_FormatedTextfield();
		c.gridx = 1;
		c.gridy = 11;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(ubpenalty_Textfield, c);
	
		// Add button apply
		Prism_ApplyButton btnApply_ubpenalty = new Prism_ApplyButton(table, data, 7, ubpenalty_Textfield);		// 7 is the column to change
		c.gridx = 0;
		c.gridy = 11;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_ubpenalty, c);			
		
		
		// Add empty Label to push other component up top------------------------------------------------------------
		c.gridx = 0;
		c.gridy = 12;
		c.weightx = 0;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(new JLabel(), c);
	}
	
	
	private class Prism_FormatedTextfield extends JFormattedTextField {
		public Prism_FormatedTextfield() {
			setToolTipText("greater than 0 with maximum 2 digits after the dot");
			getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					Runnable format = new Runnable() {
						@Override
						public void run() {
							String text = getText();
							if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
								setText(text.substring(0, text.length() - 1));
							} else {
								if (!text.isEmpty() && !text.equals(".") && Double.valueOf(text) < (double) 0) {		// If the added String make value <0  then delete that String
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
						}
						table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
					}
				}
			});	
		}
	}
}
