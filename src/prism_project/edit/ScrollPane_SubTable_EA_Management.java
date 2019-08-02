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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import prism_convenience.PrismTableModel;

public class ScrollPane_SubTable_EA_Management extends JScrollPane {		
	private JTable table4a;
	private Object[][] data4a;
	private PrismTableModel model4a;
	
	public ScrollPane_SubTable_EA_Management(JTable table4a, Object[][] data4a, PrismTableModel model4a) {	
		this.table4a = table4a;
		this.data4a = data4a;
		this.model4a = model4a;
	
		TitledBorder border = new TitledBorder("Covertype Conversion & Rotation Age (choices limit = 0-5)");
		border.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
		setViewportView(null);
//		setPreferredSize(new Dimension(0, 0));
	}	
			
	
	public String get_conversion_and_rotation_info_from_GUI() {			
		String[] joined_string = new String[data4a.length];
		for (int row = 0; row < data4a.length; row++) {
			int final_row = row;
			List<String> string_list = new ArrayList<String>() {{ for (Object i : data4a[final_row]) add(String.valueOf(i));}};		// Convert Object array to String list
			joined_string[row] = String.join(" ", string_list);	// join with a space
		}			
		String conversion_and_rotation_info = String.join(";", joined_string);	// join with a ;
		return conversion_and_rotation_info;
	}
	
	
	public void reload_this_table(String conversion_and_rotation_info) {	
		String[] info_4a = conversion_and_rotation_info.split(";");					
		for (int i = 0; i < info_4a.length; i++) {			
			String[] row_info = info_4a[i].split(" ");
			for (int j = 0; j < row_info.length; j++) {
				data4a[i][j] = row_info[j];
			}
		}		
		model4a.match_DataType();
	}

	
	public void show_table() {			
		setViewportView(table4a);
	}
	
	public void hide_table() {			
		setViewportView(null);
	}
	
	public void update_table_data(Object[][] data4a) {			
		this.data4a = data4a;
	}
}
