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
		add(new JLabel("rotation_ageclass_min"), c);

		
		// Add comboBox
		class comboBox_MinAge extends JComboBox {
			public comboBox_MinAge() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 20);
			}
		}
		JComboBox minComBo = new comboBox_MinAge();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(minComBo, c);
		
		
		// Add button apply
		JButton btnApplyMin = new JButton();
		btnApplyMin.setToolTipText("make changes for all highlighted rows");
		btnApplyMin.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyMin.addActionListener(new ActionListener() {
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
					data[i][2] = minComBo.getSelectedItem();
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
		add(btnApplyMin, c);

		

		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("rotation_ageclass_max"), c);
		
		
		// Add comboBox
		class comboBox_MaxAge extends JComboBox {
			public comboBox_MaxAge() {
				for (int i = 1; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 24);
			}
		}
		JComboBox maxComBo = new comboBox_MaxAge();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(maxComBo, c);
		

		// Add button apply
		JButton btnApplyMax = new JButton();
		btnApplyMax.setToolTipText("make changes for all highlighted rows");
		btnApplyMax.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyMax.addActionListener(new ActionListener() {
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
					data[i][3] = maxComBo.getSelectedItem();
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
		add(btnApplyMax, c);
		
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("implementation"), c);

	
		// Add checkBox
		JCheckBox inplement_Check = new JCheckBox();
		c.gridx = 1;
		c.gridy = 5;
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
						data[i][4] = true;
					} else {
						data[i][4] = false;
					}	
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
		add(btnApplyImplement, c);
	}
}
