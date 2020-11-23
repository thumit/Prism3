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

public class ScrollPane_SubTable_PrescriptionAssignment extends JScrollPane {		
	private JTable table4a;
	private Object[][] data4a;
	private JScrollPane conversion_implementation_scrollpane;
		
	public ScrollPane_SubTable_PrescriptionAssignment(JTable table4a, Object[][] data4a) {
		this.table4a = table4a;
		this.data4a = data4a;
		
		conversion_implementation_scrollpane = new JScrollPane(/*this.table4a*/);
		TitledBorder border = new TitledBorder("Post management transition");
		border.setTitleJustification(TitledBorder.CENTER);
		conversion_implementation_scrollpane.setBorder(border);
		conversion_implementation_scrollpane.setPreferredSize(new Dimension(0, 0));
			
		JPanel combine_panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
	    c.weighty = 1;
	    combine_panel.add(conversion_implementation_scrollpane, c);
	  
		
		setViewportView(combine_panel);
		setBorder(null);
	}	
	
	
	public String get_conversion_implementation_from_GUI() {	
		String conversion_implementation = "";
		for (int row = 0; row < data4a.length; row++) {
			conversion_implementation = conversion_implementation + data4a[row][0] + " " + data4a[row][1];
			for (int col = 2; col < data4a[row].length; col++) {
				conversion_implementation = conversion_implementation + " " + String.valueOf(data4a[row][col]);
			}
			conversion_implementation = conversion_implementation + ";";
		}			
		if (!conversion_implementation.equals("")) {
			conversion_implementation = conversion_implementation.substring(0, conversion_implementation.length() - 1);		// remove the last ;
		}
		return conversion_implementation;
	}
	
	
	public void reload_this_condition(String cr_mean) {	
		// Reload table4a
		if(cr_mean.length() > 0) {		// this guarantees the string is not ""
			String[] info_4a = cr_mean.split(";");					
			for (int row = 0; row < info_4a.length; row++) {			
				String[] sub_info = info_4a[row].split(" ");
				for (int col = 2; col < data4a[row].length; col++) {
					boolean is_implementation = Boolean.valueOf(sub_info[col]);
					data4a[row][col] = is_implementation;
				}
			}
		}
	}



	public JScrollPane get_conversion_implementation_scrollpane() {
		return conversion_implementation_scrollpane;
	}
	
	public void show_table() {			
		conversion_implementation_scrollpane.setViewportView(table4a);
	}
	
	public void hide_table() {			
		conversion_implementation_scrollpane.setViewportView(null);
	}
	
	public void update_table_data(Object[][] data4a) {			
		this.data4a = data4a;
	}
}
