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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class ScrollPane_NaturalDisturbances_SubTables extends JScrollPane {		
	private JTable table7a;
	private Object[][] data7a;
	
	private JTable table7b;
	private Object[][] data7b;
	
	private JScrollPane probability_scrollpane;
	private JScrollPane regeneration_scrollpane;
	
		
	public ScrollPane_NaturalDisturbances_SubTables(JTable table7a, Object[][] data7a, String[] columnNames7a, JTable table7b, Object[][] data7b) {	
		this.table7a = table7a;
		this.data7a = data7a;
		
		this.table7b = table7b;
		this.data7b = data7b;
		
	
		probability_scrollpane = new JScrollPane(/*this.table7a*/);
		TitledBorder border = new TitledBorder("Probability of occurence (INACTIVE)");
		border.setTitleJustification(TitledBorder.CENTER);
		probability_scrollpane.setBorder(border);
		probability_scrollpane.setPreferredSize(new Dimension(360, 80));
		
		
		regeneration_scrollpane = new JScrollPane(/*this.table7b*/);
		border = new TitledBorder("Regeneration upon occurence");
		border.setTitleJustification(TitledBorder.CENTER);
		regeneration_scrollpane.setBorder(border);
		regeneration_scrollpane.setPreferredSize(new Dimension(467, 80));
				
				
		JPanel combine_panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
	    c.weighty = 1;
	    combine_panel.add(probability_scrollpane, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
	    c.weighty = 1;
	    combine_panel.add(regeneration_scrollpane, c);

		
		setViewportView(combine_panel);
		setBorder(null);
	}	
			
	
	public String get_occurence_info_from_GUI() {			
		String probability_info = "";
		for (int row = 0; row < data7a.length; row++) {
			probability_info = probability_info + data7a[row][0] + " " + data7a[row][1];
			for (int col = 2; col < data7a[row].length; col++) {
				probability_info = probability_info + " " + data7a[row][col].toString();
			}
			probability_info = probability_info + ";";
		}			
		if (!probability_info.equals("")) {
			probability_info = probability_info.substring(0, probability_info.length() - 1);		// remove the last ;
		}
		return probability_info;
	}
	
	
	public String get_regeneration_info_from_GUI() {			
		String regeneration_info = "";
		for (int row = 0; row < data7b.length; row++) {
			regeneration_info = regeneration_info + data7b[row][0] + " " + data7b[row][1];
			for (int col = 2; col < data7b[row].length; col++) {
				regeneration_info = regeneration_info + " " + data7b[row][col].toString();
			}
			regeneration_info = regeneration_info + ";";
		}			
		if (!regeneration_info.equals("")) {
			regeneration_info = regeneration_info.substring(0, regeneration_info.length() - 1);		// remove the last ;
		}
		return regeneration_info;
	}
	
	
	public void reload_this_condition_occurence_and_regeneration(String probability_info, String regeneration_info) {	
		// Reset data7a to null		
		for (int row = 0; row < data7a.length; row++) {
			for (int col = 2; col < data7a[row].length; col++) {
				data7a[row][col] = null;
			}
		}
		
		// Reload table7a
		if(probability_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_7a = probability_info.split(";");					
			for (int i = 0; i < info_7a.length; i++) {			
				String[] sub_info = info_7a[i].split(" ");
				String covertype_before = sub_info[0];
				String covertype_after = sub_info[1];
				for (int col = 2; col < sub_info.length; col++) {
					double probability = Double.valueOf(sub_info[col]);
					data7a[i][col] = probability;
				}
			}
			// Need this since only the view of data in selected rows will be updated, but we need to see updated view of data from all rows (including non-selected rows)
			table7a.setValueAt(data7a[table7a.convertRowIndexToModel(0)][table7a.convertColumnIndexToModel(0)], 0, 0);		// Just to activate update_Percentage_column
		}
		
		//---------------------------------------------------------------------------------------------------
		
		// Reset data7b to null		
		for (int row = 0; row < data7b.length; row++) {
			for (int col = 2; col < data7b[row].length; col++) {
				data7b[row][col] = null;
			}
		}
		
		// Reload table7b
		if(regeneration_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_7b = regeneration_info.split(";");					
			for (int i = 0; i < info_7b.length; i++) {			
				String[] sub_info = info_7b[i].split(" ");
				String covertype_before = sub_info[0];
				String covertype_after = sub_info[1];
				for (int col = 2; col < sub_info.length; col++) {
					double percentage = Double.valueOf(sub_info[col]);
					data7b[i][col] = percentage;
				}
			}
			// Need this since only the view of data in selected rows will be updated, but we need to see updated view of data from all rows (including non-selected rows)
			table7b.setValueAt(data7b[table7b.convertRowIndexToModel(0)][table7b.convertColumnIndexToModel(0)], 0, 0);		// Just to activate update_Percentage_column
		}
	}


	public JScrollPane get_probability_scrollpane() {
		return probability_scrollpane;
	}


	public JScrollPane get_regeneration_scrollpane() {
		return regeneration_scrollpane;
	}
	
	public void show_2_tables() {			
		probability_scrollpane.setViewportView(table7a);
		regeneration_scrollpane.setViewportView(table7b);
	}
	
	public void hide_2_tables() {			
		probability_scrollpane.setViewportView(null);
		regeneration_scrollpane.setViewportView(null);
	}
	
	public void update_2_tables_data(Object[][] data7a, Object[][] data7b) {			
		this.data7a = data7a;
		this.data7b = data7b;
	}
}
