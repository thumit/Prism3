package spectrumYieldProject;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class ScrollPane_CostTables extends JScrollPane {		
	private JTable table7a;
	private Object[][] data7a;
	private String[] columnNames7a;
	
	private JTable table7b;
	private Object[][] data7b;
	private String[] columnNames7b;
	
	JScrollPane action_base_adjust_scrollpane;
	JScrollPane conversion_base_adjust_scrollpane;
		
	public ScrollPane_CostTables(JTable table7a, Object[][] data7a, String[] columnNames7a, JTable table7b, Object[][] data7b, String[] columnNames7b) {	
		this.table7a = table7a;
		this.data7a = data7a;
		this.columnNames7a = columnNames7a;
		
		this.table7b = table7b;
		this.data7b = data7b;
		this.columnNames7b = columnNames7b;
		
	
		action_base_adjust_scrollpane = new JScrollPane(this.table7a);
		TitledBorder border = new TitledBorder("Action Cost (currency per unit of column header)");
		border.setTitleJustification(TitledBorder.CENTER);
		action_base_adjust_scrollpane.setBorder(border);
		action_base_adjust_scrollpane.setPreferredSize(new Dimension(400, 100));
		
		
		conversion_base_adjust_scrollpane = new JScrollPane(this.table7b);
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
		for (int row = 0; row < data7a.length; row++) {
			for (int col = 1; col < data7a[row].length; col++) {
				if (data7a[row][col] != null) {
					action_cost_info = action_cost_info + data7a[row][0] + " " + columnNames7a[col] + " " + data7a[row][col].toString() + ";";
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
		for (int row = 0; row < data7b.length; row++) {
			for (int col = 2; col < data7b[row].length; col++) {
				if (data7b[row][col] != null) {
					conversion_to_adjust_info = conversion_to_adjust_info + data7b[row][0] + " " + data7b[row][1] + " " + columnNames7b[col] + " " + data7b[row][col].toString() + ";";
				}	
			}
		}			
		if (!conversion_to_adjust_info.equals("")) {
			conversion_to_adjust_info = conversion_to_adjust_info.substring(0, conversion_to_adjust_info.length() - 1);		// remove the last ;
		}
		return conversion_to_adjust_info;
	}
	
	
	public void reload_this_condition_action_cost_and_conversion_cost(String action_cost_info, String conversion_cost_info) {			
		// Reset data7a to null		
		for (int row = 0; row < data7a.length; row++) {
			for (int col = 1; col < data7a[row].length; col++) {
				data7a[row][col] = null;
			}
		}
		
		// Reload table7a
		if(action_cost_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_7a = action_cost_info.split(";");					
			for (int i = 0; i < info_7a.length; i++) {			
				String[] sub_info = info_7a[i].split(" ");
				String action = sub_info[0];
				String attribute = sub_info[1];
				double cost = Double.valueOf(sub_info[2]);
				
				for (int row = 0; row < data7a.length; row++) {
					if (data7a[row][0].toString().equals(action)) {
						for (int col = 1; col < data7a[row].length; col++) {
							if (columnNames7a[col].equals(attribute)) {
								data7a[row][col] = cost;
							}
						}
					}
				}	
			}		
		}
		
		// Reset data7b to null		
		for (int row = 0; row < data7b.length; row++) {
			for (int col = 2; col < data7b[row].length; col++) {
				data7b[row][col] = null;
			}
		}
		
		// Reload table7b
		if(conversion_cost_info.length() > 0) {		// this guarantees the string is not ""
			String[] info_7b = conversion_cost_info.split(";");					
			for (int i = 0; i < info_7b.length; i++) {			
				String[] sub_info = info_7b[i].split(" ");
				String covertype_before = sub_info[0];
				String covertype_after = sub_info[1];
				String attribute = sub_info[2];
				double cost = Double.valueOf(sub_info[3]);
				
				for (int row = 0; row < data7b.length; row++) {
					if ((data7b[row][0].toString() + data7b[row][1].toString()).equals(covertype_before + covertype_after)) {
						for (int col = 2; col < data7b[row].length; col++) {
							if (columnNames7b[col].equals(attribute)) {
								data7b[row][col] = cost;
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
