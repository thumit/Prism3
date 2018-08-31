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

public class ScrollPane_SubTables_SR_Disturbances extends JScrollPane {		
	private JTable table6a;
	private Object[][] data6a;
	
	private JTable table6b;
	private Object[][] data6b;
	
	private JScrollPane probability_scrollpane;
	private JScrollPane regeneration_scrollpane;
	
		
	public ScrollPane_SubTables_SR_Disturbances(JTable table6a, Object[][] data6a, String[] columnNames6a, JTable table6b, Object[][] data6b) {	
		this.table6a = table6a;
		this.data6a = data6a;
		
		this.table6b = table6b;
		this.data6b = data6b;
		
	
		probability_scrollpane = new JScrollPane(/*this.table6a*/);
		TitledBorder border = new TitledBorder("Probability of occurrence (INACTIVE)");
		border.setTitleJustification(TitledBorder.CENTER);
		probability_scrollpane.setBorder(border);
		probability_scrollpane.setPreferredSize(new Dimension(333, 0));
		
		
		regeneration_scrollpane = new JScrollPane(/*this.table6b*/);
		border = new TitledBorder("Regeneration upon occurrence");
		border.setTitleJustification(TitledBorder.CENTER);
		regeneration_scrollpane.setBorder(border);
		regeneration_scrollpane.setPreferredSize(new Dimension(433, 0));
				
				
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
			
	
	public String get_occurrence_info_from_GUI() {			
		String probability_info = "";
		for (int row = 0; row < data6a.length; row++) {
			probability_info = probability_info + data6a[row][0] + " " + data6a[row][1];
			for (int col = 2; col < data6a[row].length; col++) {
				probability_info = probability_info + " " + data6a[row][col].toString();
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
		for (int row = 0; row < data6b.length; row++) {
			regeneration_info = regeneration_info + data6b[row][0] + " " + data6b[row][1];
			for (int col = 2; col < data6b[row].length; col++) {
				regeneration_info = regeneration_info + " " + data6b[row][col].toString();
			}
			regeneration_info = regeneration_info + ";";
		}			
		if (!regeneration_info.equals("")) {
			regeneration_info = regeneration_info.substring(0, regeneration_info.length() - 1);		// remove the last ;
		}
		return regeneration_info;
	}
	
	
	public void reload_this_condition_occurrence_and_regeneration(String probability_info, String regeneration_info) {	
		// Reload table6a
		if(probability_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_6a = probability_info.split(";");					
			for (int i = 0; i < info_6a.length; i++) {			
				String[] sub_info = info_6a[i].split(" ");
				String covertype_before = sub_info[0];
				String covertype_after = sub_info[1];
				for (int col = 2; col < sub_info.length; col++) {
					double probability = Double.valueOf(sub_info[col]);
					data6a[i][col] = probability;
				}
			}
		}
		
		// Reload table6b
		if(regeneration_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_6b = regeneration_info.split(";");					
			for (int i = 0; i < info_6b.length; i++) {			
				String[] sub_info = info_6b[i].split(" ");
				String covertype_before = sub_info[0];
				String covertype_after = sub_info[1];
				for (int col = 2; col < sub_info.length; col++) {
					double percentage = Double.valueOf(sub_info[col]);
					data6b[i][col] = percentage;
				}
			}
		}
	}


	public JScrollPane get_probability_scrollpane() {
		return probability_scrollpane;
	}

	public JScrollPane get_regeneration_scrollpane() {
		return regeneration_scrollpane;
	}
	
	public void show_2_tables() {			
		probability_scrollpane.setViewportView(table6a);
		regeneration_scrollpane.setViewportView(table6b);
	}
	
	public void hide_2_tables() {			
		probability_scrollpane.setViewportView(null);
		regeneration_scrollpane.setViewportView(null);
	}
	
	public void update_2_tables_data(Object[][] data6a, Object[][] data6b) {			
		this.data6a = data6a;
		this.data6b = data6b;
	}
}
