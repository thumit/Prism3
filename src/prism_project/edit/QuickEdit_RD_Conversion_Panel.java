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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import prism_convenience_class.IconHandle;

public class QuickEdit_RD_Conversion_Panel extends JPanel {
	public QuickEdit_RD_Conversion_Panel(JTable table, Object[][] data) {
		setPreferredSize(new Dimension(200, 230));
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
		add(new JLabel("regeneration_weight"), c);

		
		// Add comboBox
		class comboBox_MinAge extends JComboBox {
			public comboBox_MinAge() {
				for (int i = 0; i <= 100; i++) {
					addItem(i);
				}
				setSelectedItem((int) 0);
			}
		}
		JComboBox weightComBo = new comboBox_MinAge();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(weightComBo, c);
		
		
		// Add button apply
		JButton btnApplyWeight = new JButton();
		btnApplyWeight.setToolTipText("make changes for all highlighted rows");
		btnApplyWeight.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_left.png"));
		btnApplyWeight.addActionListener(new ActionListener() {
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
					data[i][2] = weightComBo.getSelectedItem();
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				}
				
				// Need this since only the view of data in selected rows will be updated, but we need to see updated view of data from all rows (including non-selected rows)
				table.setValueAt(data[table.convertRowIndexToModel(0)][table.convertColumnIndexToModel(0)], 0, 0);		//Just to activate update_Percentage_column
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		add(btnApplyWeight, c);
	}
}
