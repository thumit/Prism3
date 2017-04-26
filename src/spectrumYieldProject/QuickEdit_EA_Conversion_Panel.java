package spectrumYieldProject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import spectrumConvenienceClasses.IconHandle;

public class QuickEdit_EA_Conversion_Panel extends JPanel {
	public QuickEdit_EA_Conversion_Panel(JTable table, Object[][] data) {

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
		add(new JLabel("min_age_cut_existing"), c);

		
		// Add comboBox
		class Combo_e_minage extends JComboBox {
			public Combo_e_minage() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 20);
			}
		}
		JComboBox combo_e_min = new Combo_e_minage();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(combo_e_min, c);
		
		
		// Add button apply
		JButton btn_apply_e_min = new JButton();
		btn_apply_e_min.setToolTipText("make changes for all highlighted rows");
		btn_apply_e_min.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btn_apply_e_min.addActionListener(new ActionListener() {
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
					data[i][2] = combo_e_min.getSelectedItem();
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
		add(btn_apply_e_min, c);

		
	
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("max_age_cut_existing"), c);
		
		
		// Add comboBox
		class Combo_e_maxage extends JComboBox {
			public Combo_e_maxage() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 24);
			}
		}
		JComboBox combo_e_max = new Combo_e_maxage();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(combo_e_max, c);
		

		// Add button apply
		JButton btn_apply_e_max = new JButton();
		btn_apply_e_max.setToolTipText("make changes for all highlighted rows");
		btn_apply_e_max.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btn_apply_e_max.addActionListener(new ActionListener() {
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
					data[i][3] = combo_e_max.getSelectedItem();
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
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
		add(btn_apply_e_max, c);
		
	
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("min_age_cut_regeneration"), c);

		
		// Add comboBox
		class Combo_r_minage extends JComboBox {
			public Combo_r_minage() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 10);
			}
		}
		JComboBox combo_r_min = new Combo_r_minage();
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(combo_r_min, c);
		
		
		// Add button apply
		JButton btn_apply_r_min = new JButton();
		btn_apply_r_min.setToolTipText("make changes for all highlighted rows");
		btn_apply_r_min.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btn_apply_r_min.addActionListener(new ActionListener() {
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
					data[i][4] = combo_r_min.getSelectedItem();
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
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
		add(btn_apply_r_min, c);

		
	
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("max_age_cut_regeneration"), c);
		
		
		// Add comboBox
		class Combo_r_maxage extends JComboBox {
			public Combo_r_maxage() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 15);
			}
		}
		JComboBox combo_r_max = new Combo_r_maxage();
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(combo_r_max, c);
		

		// Add button apply
		JButton btn_apply_r_max = new JButton();
		btn_apply_r_max.setToolTipText("make changes for all highlighted rows");
		btn_apply_r_max.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btn_apply_r_max.addActionListener(new ActionListener() {
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
					data[i][5] = combo_r_max.getSelectedItem();
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				}
			}
		});
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btn_apply_r_max, c);
		
		
		
		
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("implementation"), c);

	
		// Add checkBox
		JCheckBox inplement_Check = new JCheckBox();
		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(inplement_Check, c);

		
		// Add button apply
		JButton btnApplyImplement = new JButton();
		btnApplyImplement.setToolTipText("make changes for all highlighted rows");
		btnApplyImplement.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyImplement.addActionListener(new ActionListener() {
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
					if (inplement_Check.isSelected()) {
						data[i][6] = true;
					} else {
						data[i][6] = false;
					}	
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				}
			}
		});
		c.gridx = 0;
		c.gridy = 9;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApplyImplement, c);
	}
}
