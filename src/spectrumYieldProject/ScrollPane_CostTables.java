package spectrumYieldProject;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class ScrollPane_CostTables extends JScrollPane {		
	private JTable table8a;
	private Object[][] data8a;
	private String[] columnNames8a;
	
	private JTable table8b;
	private Object[][] data8b;
	private String[] columnNames8b;
	
	JScrollPane action_base_adjust_scrollpane;
	JScrollPane conversion_base_adjust_scrollpane;
		
	public ScrollPane_CostTables(JTable table8a, Object[][] data8a, String[] columnNames8a, JTable table8b, Object[][] data8b, String[] columnNames8b) {	
		this.table8a = table8a;
		this.data8a = data8a;
		this.columnNames8a = columnNames8a;
		
		this.table8b = table8b;
		this.data8b = data8b;
		this.columnNames8b = columnNames8b;
		
	
		action_base_adjust_scrollpane = new JScrollPane(this.table8a);
		TitledBorder border = new TitledBorder("Action Cost (currency per unit of column header)");
		border.setTitleJustification(TitledBorder.CENTER);
		action_base_adjust_scrollpane.setBorder(border);
		action_base_adjust_scrollpane.setPreferredSize(new Dimension(400, 100));
		
		
		conversion_base_adjust_scrollpane = new JScrollPane(this.table8b);
		border = new TitledBorder("Conversion Cost (currency per converted acre)");
		border.setTitleJustification(TitledBorder.CENTER);
		conversion_base_adjust_scrollpane.setBorder(border);
		conversion_base_adjust_scrollpane.setPreferredSize(new Dimension(400, 100));
				
				
		JPanel combine_panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
	    c.weighty = 0.5;
	    combine_panel.add(action_base_adjust_scrollpane, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
	    c.weighty = 1;
	    combine_panel.add(conversion_base_adjust_scrollpane, c);

		
		setViewportView(combine_panel);
		setBorder(null);
	}	
			
	
	public String get_action_cost_info_from_GUI() {			
		String action_cost_info = "";		
		for (int row = 0; row < data8a.length; row++) {
			for (int col = 1; col < data8a[row].length; col++) {
				if (data8a[row][col] != null) {
					action_cost_info = action_cost_info + data8a[row][0] + " " + columnNames8a[col] + " " + data8a[row][col].toString() + ";";
				}	
			}
		}					
		if (!action_cost_info.equals("")) {
			action_cost_info = action_cost_info.substring(0, action_cost_info.length() - 1);		// remove the last ;
		}
		return action_cost_info;
	}
	
	
	public String get_conversion_cost_info_from_GUI() {			
		String conversion_to_adjust_info = "";
		for (int row = 0; row < data8b.length; row++) {
			for (int col = 2; col < data8b[row].length; col++) {
				if (data8b[row][col] != null) {
					conversion_to_adjust_info = conversion_to_adjust_info + data8b[row][0] + " " + data8b[row][1] + " " + columnNames8b[col] + " " + data8b[row][col].toString() + ";";
				}	
			}
		}			
		if (!conversion_to_adjust_info.equals("")) {
			conversion_to_adjust_info = conversion_to_adjust_info.substring(0, conversion_to_adjust_info.length() - 1);		// remove the last ;
		}
		return conversion_to_adjust_info;
	}
	
	
	public void reload_this_condition_action_cost_and_conversion_cost(String action_cost_info, String conversion_cost_info) {			
		// Reset data8a to null		
		for (int row = 0; row < data8a.length; row++) {
			for (int col = 1; col < data8a[row].length; col++) {
				data8a[row][col] = null;
			}
		}
		
		// Reload table8a
		if(action_cost_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_8a = action_cost_info.split(";");					
			for (int i = 0; i < info_8a.length; i++) {			
				String[] sub_info = info_8a[i].split(" ");
				String action = sub_info[0];
				String attribute = sub_info[1];
				double cost = Double.valueOf(sub_info[2]);
				
				for (int row = 0; row < data8a.length; row++) {
					if (data8a[row][0].toString().equals(action)) {
						for (int col = 1; col < data8a[row].length; col++) {
							if (columnNames8a[col].equals(attribute)) {
								data8a[row][col] = cost;
							}
						}
					}
				}	
			}		
		}
		
		// Reset data8b to null		
		for (int row = 0; row < data8b.length; row++) {
			for (int col = 2; col < data8b[row].length; col++) {
				data8b[row][col] = null;
			}
		}
		
		// Reload table8b
		if(conversion_cost_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_8b = conversion_cost_info.split(";");					
			for (int i = 0; i < info_8b.length; i++) {			
				String[] sub_info = info_8b[i].split(" ");
				String covertype_before = sub_info[0];
				String covertype_after = sub_info[1];
				String attribute = sub_info[2];
				double cost = Double.valueOf(sub_info[3]);
				
				for (int row = 0; row < data8b.length; row++) {
					if ((data8b[row][0].toString() + data8b[row][1].toString()).equals(covertype_before + covertype_after)) {
						for (int col = 2; col < data8b[row].length; col++) {
							if (columnNames8b[col].equals(attribute)) {
								data8b[row][col] = cost;
							}
						}
					}
				}	
			}
		}
	}


	public JScrollPane get_action_base_adjust_scrollpane() {
		return action_base_adjust_scrollpane;
	}


	public JScrollPane get_conversion_base_adjust_scrollpane() {
		return conversion_base_adjust_scrollpane;
	}
}
