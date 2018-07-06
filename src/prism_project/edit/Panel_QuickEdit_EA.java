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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import prism_convenience_class.IconHandle;
import prism_root.PrismMain;

public class Panel_QuickEdit_EA extends JPanel {
	private JButton btn_default;
	private JButton btn_apply_e_min;
	private JButton btn_apply_e_max;
	private JButton btn_apply_r_min;
	private JButton btn_apply_r_max;
	private JButton btnApplyImplement;
	
	public Panel_QuickEdit_EA(JTable table, Object[][] data, ArrayList<String>[] rotation_ranges, Object[][] default_data) {
//		setPreferredSize(new Dimension(200, 0));
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
			{
				setPreferredSize(new Dimension(75, getPreferredSize().height));
			}

			public Combo_e_minage() {
				for (int i = min_age_for_combo_e; i <= max_age_for_combo_e; i++) {
					addItem(i);
				}
				setSelectedItem(min_age_for_combo_e);
			}
		}
		
		class Combo_e_maxage extends JComboBox {
			{
				setPreferredSize(new Dimension(75, getPreferredSize().height));
			}
			
			public Combo_e_maxage() {
				for (int i = min_age_for_combo_e; i <= max_age_for_combo_e; i++) {
					addItem(i);
				}
				setSelectedItem(max_age_for_combo_e);
			}
		}
		
		class Combo_r_minage extends JComboBox {
			{
				setPreferredSize(new Dimension(75, getPreferredSize().height));
			}
			
			public Combo_r_minage() {
				for (int i = min_age_for_combo_r; i <= max_age_for_combo_r; i++) {
					addItem(i);
				}
				setSelectedItem(min_age_for_combo_r);
			}
		}
		
		class Combo_r_maxage extends JComboBox {
			{
				setPreferredSize(new Dimension(75, getPreferredSize().height));
			}
			
			public Combo_r_maxage() {
				for (int i = min_age_for_combo_r; i <= max_age_for_combo_r; i++) {
					addItem(i);
				}
				setSelectedItem(max_age_for_combo_r);
			}
		}
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("Set to Default"), c);
		
		// Add button default
		btn_default = new JButton();
		btn_default.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_default.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_default.setToolTipText("set all cells to default values");
		btn_default.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_main.png"));
		btn_default.setRolloverIcon(IconHandle.get_scaledImageIcon(35, 35, "icon_main.png"));
		btn_default.setContentAreaFilled(false);
		btn_default.addActionListener(e -> {
			String ExitOption[] = {"Reset","Cancel"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Reset now?", "Reset all cells to default",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
			if (response == 0) {	
				for (int i = 0; i < default_data.length; i++) {
					data[i] = Arrays.copyOf(default_data[i], default_data[i].length);
				}
				table.setRowSelectionInterval(table.convertRowIndexToView(0), table.convertRowIndexToView(data.length - 1));
				table.clearSelection();
			}
			if (response == 1) {
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.CENTER;
		add(btn_default, c);
		
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("EA_E_min_ra"), c);

		
		// Add comboBox
		JComboBox combo_e_min = new Combo_e_minage();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(combo_e_min, c);
		
		
		// Add button apply
		btn_apply_e_min = new JButton();
		btn_apply_e_min.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_apply_e_min.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_apply_e_min.setToolTipText("make changes for all highlighted cells in one column");
		btn_apply_e_min.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btn_apply_e_min.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_split.png"));
		btn_apply_e_min.setContentAreaFilled(false);
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
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(btn_apply_e_min, c);

		
	
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("EA_E_max_ra"), c);
		
		
		// Add comboBox
		JComboBox combo_e_max = new Combo_e_maxage();
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(combo_e_max, c);
		

		// Add button apply
		btn_apply_e_max = new JButton();
		btn_apply_e_max.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_apply_e_max.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_apply_e_max.setToolTipText("make changes for all highlighted cells in one column");
		btn_apply_e_max.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btn_apply_e_max.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_split.png"));
		btn_apply_e_max.setContentAreaFilled(false);
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
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(btn_apply_e_max, c);
		
	
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("EA_R_min_ra"), c);

		
		// Add comboBox
		JComboBox combo_r_min = new Combo_r_minage();
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(combo_r_min, c);
		
		
		// Add button apply
		btn_apply_r_min = new JButton();
		btn_apply_r_min.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_apply_r_min.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_apply_r_min.setToolTipText("make changes for all highlighted cells in one column");
		btn_apply_r_min.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btn_apply_r_min.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_split.png"));
		btn_apply_r_min.setContentAreaFilled(false);
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
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(btn_apply_r_min, c);

		
	
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 4;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("EA_R_max_ra"), c);
		
		
		// Add comboBox
		JComboBox combo_r_max = new Combo_r_maxage();
		c.gridx = 4;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(combo_r_max, c);
		

		// Add button apply
		btn_apply_r_max = new JButton();
		btn_apply_r_max.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_apply_r_max.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_apply_r_max.setToolTipText("make changes for all highlighted cells in one column");
		btn_apply_r_max.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btn_apply_r_max.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_split.png"));
		btn_apply_r_max.setContentAreaFilled(false);
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
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(btn_apply_r_max, c);
		
		
		
		
		
		
		// Add Label-------------------------------------------------------------------------------------------------
		c.gridx = 5;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(new JLabel("implementation"), c);

	
		// Add checkBox
		JCheckBox implement_Check = new JCheckBox();
		implement_Check.setSelected(true);
		c.gridx = 5;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(implement_Check, c);

		
		// Add button apply
		btnApplyImplement = new JButton();
		btnApplyImplement.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnApplyImplement.setHorizontalTextPosition(SwingConstants.CENTER);
		btnApplyImplement.setToolTipText("make changes for all highlighted cells in one column");
		btnApplyImplement.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_split.png"));
		btnApplyImplement.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_split.png"));
		btnApplyImplement.setContentAreaFilled(false);
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
					if (implement_Check.isSelected()) {
						data[i][6] = true;
					} else {
						data[i][6] = false;
					}	
					table.addRowSelectionInterval(table.convertRowIndexToView(i), table.convertRowIndexToView(i));
				}
			}
		});
		c.gridx = 5;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		add(btnApplyImplement, c);
	}
	
	
	public void disable_all_apply_buttons() {
		btn_default.setEnabled(false);
		btn_apply_e_min.setEnabled(false);
		btn_apply_e_max.setEnabled(false);
		btn_apply_r_min.setEnabled(false);
		btn_apply_r_max.setEnabled(false);
		btnApplyImplement.setEnabled(false);
	}
	
	public void enable_all_apply_buttons() {
		btn_default.setEnabled(true);
		btn_apply_e_min.setEnabled(true);
		btn_apply_e_max.setEnabled(true);
		btn_apply_r_min.setEnabled(true);
		btn_apply_r_max.setEnabled(true);
		btnApplyImplement.setEnabled(true);
	}
}
