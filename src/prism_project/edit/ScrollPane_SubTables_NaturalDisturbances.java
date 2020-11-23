/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/

package prism_project.edit;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class ScrollPane_SubTables_NaturalDisturbances extends JScrollPane {		
	private JTable table6c, table6d;
	private Object[][] data6c, data6d;
	private JScrollPane conversion_rate_mean_scrollpane, conversion_rate_std_scrollpane;
		
	public ScrollPane_SubTables_NaturalDisturbances(JTable table6c, Object[][] data6c, JTable table6d, Object[][] data6d) {
		this.table6c = table6c;
		this.table6d = table6d;
		this.data6c = data6c;
		this.data6d = data6d;
		
	
		conversion_rate_mean_scrollpane = new JScrollPane(/*this.table6c*/);
		TitledBorder border = new TitledBorder("Post disturbance transition (%)");	// mean
		border.setTitleJustification(TitledBorder.CENTER);
		conversion_rate_mean_scrollpane.setBorder(border);
		conversion_rate_mean_scrollpane.setPreferredSize(new Dimension(0, 0));
			
		
		conversion_rate_std_scrollpane = new JScrollPane(/*this.table6d*/);
		border = new TitledBorder("Post disturbance transition std");	// std
		border.setTitleJustification(TitledBorder.CENTER);
		conversion_rate_std_scrollpane.setBorder(border);
		conversion_rate_std_scrollpane.setPreferredSize(new Dimension(0, 0));
		
		
		conversion_rate_mean_scrollpane.getVerticalScrollBar().setModel(conversion_rate_std_scrollpane.getVerticalScrollBar().getModel());	 //<--------------synchronize
		conversion_rate_mean_scrollpane.getHorizontalScrollBar().setModel(conversion_rate_std_scrollpane.getHorizontalScrollBar().getModel());	 //<--------------synchronize
		table6d.setSelectionModel(table6c.getSelectionModel());	 //<--------------synchronize
		table6d.setColumnModel(table6c.getColumnModel());	 //<--------------synchronize
		
		
		JPanel combine_panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
	    c.weighty = 1;
	    combine_panel.add(conversion_rate_mean_scrollpane, c);
	    c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
	    c.weighty = 1;
	    c.gridheight = 1;	//  delete this line to allow conversion rate std (activate line 110)
	    combine_panel.add(conversion_rate_std_scrollpane, c);
	    conversion_rate_std_scrollpane.setVisible(false);
	  
		
		setViewportView(combine_panel);
		setBorder(null);
	}	
	
	
	public String get_cr_mean_from_GUI() {	
		String cr_mean = "";
		for (int row = 0; row < data6c.length; row++) {
			cr_mean = cr_mean + data6c[row][0] + " " + data6c[row][1];
			for (int col = 2; col < data6c[row].length; col++) {
				cr_mean = cr_mean + " " + data6c[row][col].toString();
			}
			cr_mean = cr_mean + ";";
		}			
		if (!cr_mean.equals("")) {
			cr_mean = cr_mean.substring(0, cr_mean.length() - 1);		// remove the last ;
		}
		return cr_mean;
	}
	
	
	public String get_cr_std_from_GUI() {	
		String cr_std = "";
		for (int row = 0; row < data6d.length; row++) {
			cr_std = cr_std + data6d[row][0] + " " + data6d[row][1];
			for (int col = 2; col < data6d[row].length; col++) {
				cr_std = cr_std + " " + data6d[row][col].toString();
			}
			cr_std = cr_std + ";";
		}			
		if (!cr_std.equals("")) {
			cr_std = cr_std.substring(0, cr_std.length() - 1);		// remove the last ;
		}
		return cr_std;
	}
	
	
	public void reload_this_condition(String cr_mean, String cr_std) {	
		// Reload table6c
		if(cr_mean.length() > 0) {		// this guarantees the string is not ""
			String[] info_6c = cr_mean.split(";");					
			for (int row = 0; row < info_6c.length; row++) {			
				String[] sub_info = info_6c[row].split(" ");
				for (int col = 2; col < data6c[row].length; col++) {
					double percentage = Double.valueOf(sub_info[col]);
					data6c[row][col] = percentage;
				}
			}
		}
		
		// Reload table6d
		if(cr_std.length() > 0) {		// this guarantees the string is not ""
			String[] info_6d = cr_std.split(";");					
			for (int row = 0; row < info_6d.length; row++) {			
				String[] sub_info = info_6d[row].split(" ");
				for (int col = 2; col < data6d[row].length; col++) {
					double percentage = Double.valueOf(sub_info[col]);
					data6d[row][col] = percentage;
				}
			}
		}
	}



	public JScrollPane get_conversion_rate_mean_scrollpane() {
		return conversion_rate_mean_scrollpane;
	}
	
	public void show_2_tables() {			
		conversion_rate_mean_scrollpane.setViewportView(table6c);
		conversion_rate_std_scrollpane.setViewportView(table6d);
	}
	
	public void hide_2_tables() {			
		conversion_rate_mean_scrollpane.setViewportView(null);
		conversion_rate_std_scrollpane.setViewportView(null);
	}
	
	public void update_2_tables_data(Object[][] data6c, Object[][] data6d) {			
		this.data6c = data6c;
		this.data6d = data6d;
	}
}
