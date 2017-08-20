package prismProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import prismConvenienceClasses.IconHandle;
import prismConvenienceClasses.TableColumnsHandle;
import prismRoot.PrismMain;

public class QuickEdit_ManagementCost_Panel extends JPanel {
	
	public QuickEdit_ManagementCost_Panel(JTable table8a, Object[][] data8a, String[] columnNames8a, JTable table8b, Object[][] data8b) {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		// Add Button-------------------------------------------------------------------------------------------------
		Prism_ShowHideColumnsButtons btnApply_showhide = new Prism_ShowHideColumnsButtons(table8a, data8a, columnNames8a);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApply_showhide, c);	
		
		// Add empty Label to organize
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(new JLabel(), c);
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("Action Cost"), c);

		
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
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(formatedTextfield, c);
		
		
		// Add button apply
		JButton btnApplyActionBaseCost = new JButton();
		btnApplyActionBaseCost.setToolTipText("make changes to all highlighted cells, except cells in column action_list");
		btnApplyActionBaseCost.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyActionBaseCost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Get selected rows
				int[] selectedRow = table8a.getSelectedRows();
				int[] selectedCol = table8a.getSelectedColumns();
							
				// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table8a.convertRowIndexToModel(selectedRow[i]);
				}
				// Convert col index because "Sort" causes problems
				for (int j = 0; j < selectedCol.length; j++) {
					selectedCol[j] = table8a.convertColumnIndexToModel(selectedCol[j]);
				}
				
				table8a.clearSelection(); // To help trigger the row refresh: clear then add back the rows
				for (int i : selectedRow) {
					for (int j : selectedCol) {
						if (!formatedTextfield.getText().equals(".") && j != 0) {	// Only apply the changes to selected cells in columns > 0 (from 'acres' column)
							data8a[i][j] = (formatedTextfield.getText().isEmpty())? null : Double.valueOf(formatedTextfield.getText());
						}
						table8a.addRowSelectionInterval(table8a.convertRowIndexToView(i), table8a.convertRowIndexToView(i));
						table8a.addColumnSelectionInterval(table8a.convertColumnIndexToView(j), table8a.convertColumnIndexToView(j));
					}
				}
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApplyActionBaseCost, c);
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("Conversion Cost"), c);

		
		// Add formatedTextfield
		JFormattedTextField formatedTextfield_2 = new JFormattedTextField();
		formatedTextfield_2.setToolTipText("greater than 0 with maximum 2 digits after the dot");
		formatedTextfield_2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = formatedTextfield_2.getText();
						if (!text.matches("\\d*(\\.\\d{0,2})?")) {		//	used regex: \\d*(\\.\\d{0,2})? because two decimal places is enough
							formatedTextfield_2.setText(text.substring(0, text.length() - 1));
						} else {
							if (!text.isEmpty() && !text.equals(".") && Double.valueOf(text) < (double) 0) {		// If the added String make value <0 then delete that String
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
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(formatedTextfield_2, c);
		
		
		// Add button apply
		JButton btnApplyConversionBaseCost = new JButton();
		btnApplyConversionBaseCost.setToolTipText("make changes to all highlighted cells, except cells in columns covertype_before & covertype_after");
		btnApplyConversionBaseCost.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyConversionBaseCost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				// Get selected rows
				int[] selectedRow = table8b.getSelectedRows();
				int[] selectedCol = table8b.getSelectedColumns();
							
				// Convert row index because "Sort" causes problems
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table8b.convertRowIndexToModel(selectedRow[i]);
				}
				// Convert col index because "Sort" causes problems
				for (int j = 0; j < selectedCol.length; j++) {
					selectedCol[j] = table8b.convertColumnIndexToModel(selectedCol[j]);
				}
				
				table8b.clearSelection(); // To help trigger the row refresh: clear then add back the rows
				for (int i : selectedRow) {
					for (int j : selectedCol) {
						if (!formatedTextfield_2.getText().equals(".") && j > 1) {	// Only apply the changes to selected cells in columns > 1
							data8b[i][j] = (formatedTextfield_2.getText().isEmpty())? null : Double.valueOf(formatedTextfield_2.getText());
						}
						table8b.addRowSelectionInterval(table8b.convertRowIndexToView(i), table8b.convertRowIndexToView(i));
						table8b.addColumnSelectionInterval(table8b.convertColumnIndexToView(j), table8b.convertColumnIndexToView(j));
					}
				}
			}
		});
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApplyConversionBaseCost, c);
	}
	
	
	private class Prism_ShowHideColumnsButtons extends JButton {
		public Prism_ShowHideColumnsButtons(JTable table8a,  Object[][] data8a, String[] columnNames8a) {

			
			// Must set this show/hide column method when all columns are still visible------------------------------------------------------
			TableColumnsHandle column_handle = new TableColumnsHandle(table8a);
			Read_Database read_Database = new Read_Database(null);
			
						
			// Create a radio buttons-----------------------------------------------------------------------------
			JRadioButton[] radioButton = new JRadioButton[2];		
			radioButton[0] = new JRadioButton("Select default columns (acres and harvested volume in cubic feet per acre)");
			radioButton[1] = new JRadioButton("Select active columns (active column has at least one cell with unempty value)");
			
			
			// Create a radio group
			ButtonGroup radioGroup = new ButtonGroup();
			radioGroup.add(radioButton[0]);
			radioGroup.add(radioButton[1]);
				
			
			// Create a radio panel
			JPanel radio_panel = new JPanel();
			radio_panel.setLayout(new GridLayout(0, 1));
			radio_panel.setBorder(BorderFactory.createTitledBorder("Preset columns"));
			radio_panel.add(radioButton[0]);
			radio_panel.add(radioButton[1]);
						
						
			// Create a list of JCheckBox-------------------------------------------------------------------------
			List<JCheckBox> column_checkboxes = new ArrayList<JCheckBox>();		
			for (int i = 0; i < table8a.getColumnModel().getColumnCount(); i++) {
				if (i > 1) {	// ignore columns 0 and 1: action_list & acres
					column_checkboxes.add(new JCheckBox(table8a.getColumnName(i)));
					column_checkboxes.get(i - 2).setSelected(true);		// -2 because we ignore 2 columns
									
					String tip = read_Database.get_ParameterToolTip(column_checkboxes.get(i - 2).getText()) + " (Column index: " + (int) (i - 2) + ")";
					column_checkboxes.get(i - 2).setToolTipText(tip);				
//					if (!tip.contains("per Acre")) {	// Disable check box if unit is not per Acre
//						column_checkboxes.get(i - 2).setEnabled(false);
//					}
				}
			}
			
			
			// Add listener for JCheckBoxes
			for (JCheckBox i: column_checkboxes) {
				i.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						radioGroup.clearSelection();
					}
				});
				
				i.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent changeEvent) {
						if (i.isSelected()) {
							column_handle.setColumnVisible(i.getText(), true);	// show column
						} else {
							column_handle.setColumnVisible(i.getText(), false);	// hide column
						}
						
//						if (total_checks_count() < 10) {
//							table.setAutoResizeMode(1);		// table's auto resize is on
//						} else {
//							table.setAutoResizeMode(0);		// table's auto resize is off
//						}
					}
				
//					// Count the total check boxes that are checked
//					public int total_checks_count() {
//						int count = 0;
//						for (JCheckBox i : column_checkboxes) {
//							if (i.isSelected()) {
//								count++;
//							}
//						}
//						return count;
//					}
				});
			}
			

			// Add JCheckBoxes to check_panel
			JPanel check_panel = new JPanel();
			check_panel.setLayout(new GridLayout(0, 4));
			for (JCheckBox i: column_checkboxes) {
				check_panel.add(i);
			}
			
			
			// Add check_panel to a scroll panel
			JScrollPane scrollPane = new JScrollPane(check_panel);				
			scrollPane.setBorder(BorderFactory.createTitledBorder("Available columns (disable if unit is not per acre)"));
			scrollPane.setPreferredSize(new Dimension(600, 300));
			
			
			// Add listeners for radio buttons			
			// Listener 1		
			radioButton[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {		//3 columns at start only
					column_handle.setColumnVisible("action_list", true);	// show column acres
					column_handle.setColumnVisible("acres", true);	// show column acres
					for (JCheckBox i: column_checkboxes) {
						i.setSelected(true);		// true then false to activate the ChangeListener
						i.setSelected(false);
						if (i.getText().equalsIgnoreCase("hca_allsx") || i.getText().equalsIgnoreCase("rmcuft")) {
							i.setSelected(true);							
						}
					}						
				}
			});
			radioButton[0].doClick();		// Start with default 3 columns
			
			// Listener 2	
			radioButton[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {																	
					List<Integer> active_col_id = new ArrayList<Integer>();		// List of active columns: at least 1 cell <> null			
					for (int i = 0; i < data8a.length; i++) {
						for (int j = 0; j < data8a[i].length; j++) {
							if (data8a[i][j] != null && !active_col_id.contains(j)) {
								active_col_id.add(j);
							}		
						}	
					}
					
					// For only acres column (No check boxes so we have to set visible/invisible manually)
					if (active_col_id.contains(1)) {	// if acres is active column
						column_handle.setColumnVisible(columnNames8a[1], true);	// show column
					} else {
						column_handle.setColumnVisible(columnNames8a[1], false);	// hide column
					}
						
					// For columns > 1 (Have check boxes to we only have to check/uncheck)
					for (int i = 0; i < columnNames8a.length; i++) {						
						if (i > 1) {	// ignore columns 0 and 1: action_list & acres	
							column_checkboxes.get(i - 2).setSelected(false);		// -2 because we ignore 2 columns
							if (active_col_id.contains(i)) {
								column_checkboxes.get(i - 2).setSelected(true);		// -2 because we ignore 2 columns
							}			
						}
					}
				}
			});
			
			
			
			
			
			// Add radioPanel & scrollPane to a panel				
			JPanel combined_panel = new JPanel(new BorderLayout());
			combined_panel.add(radio_panel, BorderLayout.NORTH);
			combined_panel.add(scrollPane, BorderLayout.CENTER);
			
			
	
			
			
			// Listener for this button class------------------------------------------------------------------------------------------------------
			if (column_checkboxes.size() > 2) {
				setEnabled(true);	// Enable only when the yield columns are loaded
			} else {
				setEnabled(false);
			}
			setToolTipText("show/hide yield tables columns");
			setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_binoculars.png"));
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					radioGroup.clearSelection();
					String ExitOption[] = { "Ok" };
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), combined_panel,
							"Select yield tables columns to show", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							IconHandle.get_scaledImageIcon(50, 50, "icon_binoculars.png"), ExitOption, ExitOption[0]);

					if (response == 0) {

					}
				}
			});
		}
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
//		Prism_FormatedTextfield per_acre_Textfield = new Prism_FormatedTextfield();
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
//		Prism_ApplyButton btnApply_PerAcre = new Prism_ApplyButton(table, data, 2, per_acre_Textfield);		// 2 is the column to change
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
//		Prism_FormatedTextfield per_cubicfoot_Textfield = new Prism_FormatedTextfield();
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
//		Prism_ApplyButton btnApply_PerCubic = new Prism_ApplyButton(table, data, 3, per_cubicfoot_Textfield);		// 3 is the column to change
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
//		Prism_FormatedTextfield per_broadfoot_Textfield = new Prism_FormatedTextfield();
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
//		Prism_ApplyButton btnApply_PerBroad = new Prism_ApplyButton(table, data, 4, per_broadfoot_Textfield);		// 4 is the column to change
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
//	private class Prism_FormatedTextfield extends JFormattedTextField {
//		public Prism_FormatedTextfield() {
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
//	private class Prism_ApplyButton extends JButton {
//		public Prism_ApplyButton(JTable table, Object[][] data, int column_to_change, Prism_FormatedTextfield formatedTextField) {
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
