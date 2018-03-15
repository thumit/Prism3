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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import prism_convenience_class.IconHandle;

public class QuickEdit_EA_Conversion_Panel extends JPanel {
	public QuickEdit_EA_Conversion_Panel(JTable table, Object[][] data, ArrayList<String>[] rotation_ranges) {
		setPreferredSize(new Dimension(200, 230));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		// Some set up for combobox---------------------------------------------------------------------------------------------
		List<Integer> existing_min_age_list = new ArrayList<Integer>();
		List<Integer> existing_max_age_list = new ArrayList<Integer>();
		List<Integer> regeneration_min_age_list = new ArrayList<Integer>();
		List<Integer> regeneration_max_age_list = new ArrayList<Integer>();
		
		for (String s : rotation_ranges[1]) existing_min_age_list.add(Integer.valueOf(s));
		for (String s : rotation_ranges[2]) existing_max_age_list.add(Integer.valueOf(s));
		for (String s : rotation_ranges[3]) regeneration_min_age_list.add(Integer.valueOf(s));
		for (String s : rotation_ranges[4]) regeneration_max_age_list.add(Integer.valueOf(s));
		
		if (existing_min_age_list.indexOf(-9999) >= 0) existing_min_age_list.remove(existing_min_age_list.indexOf((-9999)));
		if (existing_max_age_list.indexOf(-9999) >= 0) existing_max_age_list.remove(existing_max_age_list.indexOf((-9999)));
		if (regeneration_min_age_list.indexOf(-9999) >= 0) regeneration_min_age_list.remove(regeneration_min_age_list.indexOf((-9999)));
		if (regeneration_max_age_list.indexOf(-9999) >= 0) regeneration_max_age_list.remove(regeneration_max_age_list.indexOf((-9999)));
		
		// this is the case when we remove -9999 but that is the only option we have across all cover types
		if (existing_min_age_list.isEmpty()) existing_min_age_list.add((int) -9999);
		if (existing_max_age_list.isEmpty()) existing_max_age_list.add((int) -9999);
		if (regeneration_min_age_list.isEmpty()) regeneration_min_age_list.add((int) -9999);
		if (regeneration_max_age_list.isEmpty()) regeneration_max_age_list.add((int) -9999);
		
		int min_age_for_combo_e = Collections.min(existing_min_age_list);
		int max_age_for_combo_e = Collections.max(existing_max_age_list);
		int min_age_for_combo_r = Collections.min(regeneration_min_age_list);
		int max_age_for_combo_r = Collections.max(regeneration_max_age_list);
		
		
		class Combo_e_minage extends JComboBox {
			public Combo_e_minage() {
				for (int i = min_age_for_combo_e; i <= max_age_for_combo_e; i++) {
					addItem(i);
				}
				setSelectedItem(min_age_for_combo_e);
			}
		}
		
		class Combo_e_maxage extends JComboBox {
			public Combo_e_maxage() {
				for (int i = min_age_for_combo_e; i <= max_age_for_combo_e; i++) {
					addItem(i);
				}
				setSelectedItem(max_age_for_combo_e);
			}
		}
		
		class Combo_r_minage extends JComboBox {
			public Combo_r_minage() {
				for (int i = min_age_for_combo_r; i <= max_age_for_combo_r; i++) {
					addItem(i);
				}
				setSelectedItem(min_age_for_combo_r);
			}
		}
		
		class Combo_r_maxage extends JComboBox {
			public Combo_r_maxage() {
				for (int i = min_age_for_combo_r; i <= max_age_for_combo_r; i++) {
					addItem(i);
				}
				setSelectedItem(max_age_for_combo_r);
			}
		}
		
		
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
					String covertype = (String) data[i][0];
					int min_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[1].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[2].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int min_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[3].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[4].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    
				    if ((int) combo_e_min.getSelectedItem() > max_age_cut_existing) {
				    	if ((int) data[i][2] != -9999) data[i][2] = max_age_cut_existing;
					} else if ((int) combo_e_min.getSelectedItem() < min_age_cut_existing) {
						if ((int) data[i][2] != -9999) data[i][2] = min_age_cut_existing;
					} else {
						if ((int) data[i][2] != -9999) data[i][2] = combo_e_min.getSelectedItem();
					}
				    
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
					String covertype = (String) data[i][0];
					int min_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[1].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[2].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int min_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[3].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[4].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    
				    if ((int) combo_e_max.getSelectedItem() > max_age_cut_existing) {
				    	if ((int) data[i][3] != -9999) data[i][3] = max_age_cut_existing;
					} else if ((int) combo_e_max.getSelectedItem() < min_age_cut_existing) {
						if ((int) data[i][3] != -9999) data[i][3] = min_age_cut_existing;
					} else {
						if ((int) data[i][3] != -9999) data[i][3] = combo_e_max.getSelectedItem();
					}
				    
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
					String covertype = (String) data[i][0];
					int min_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[1].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[2].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int min_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[3].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[4].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    
				    if ((int) combo_r_min.getSelectedItem() > max_age_cut_regeneration) {
				    	if ((int) data[i][4] != -9999) data[i][4] = max_age_cut_regeneration;
					} else if ((int) combo_r_min.getSelectedItem() < min_age_cut_regeneration) {
						if ((int) data[i][4] != -9999) data[i][4] = min_age_cut_regeneration;
					} else {
						if ((int) data[i][4] != -9999) data[i][4] = combo_r_min.getSelectedItem();
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
					String covertype = (String) data[i][0];
					int min_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[1].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_existing = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[2].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int min_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[3].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    int max_age_cut_regeneration = (rotation_ranges[0].indexOf(covertype) >= 0) ? Integer.valueOf(rotation_ranges[4].get(rotation_ranges[0].indexOf(covertype))) : -9999;
				    
				    if ((int) combo_r_max.getSelectedItem() > max_age_cut_regeneration) {
						if ((int) data[i][5] != -9999) data[i][5] = max_age_cut_regeneration;
					} else if ((int) combo_r_max.getSelectedItem() < min_age_cut_regeneration) {
						if ((int) data[i][5] != -9999) data[i][5] = min_age_cut_regeneration;
					} else {
						if ((int) data[i][5] != -9999) data[i][5] = combo_r_max.getSelectedItem();
					}
				    
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
		c.weightx = 1;
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
