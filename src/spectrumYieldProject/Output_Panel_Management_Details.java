package spectrumYieldProject;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import spectrumConvenienceClasses.TableModelSpectrum;

public class Output_Panel_Management_Details extends JLayeredPane implements ItemListener {
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	private ScrollPane_Parameters parametersScrollPanel;
	private ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
	private ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
	
	private File file_StrataDefinition, file_Database;
	private Read_DatabaseTables read_DatabaseTables;
	private Read_Indentifiers read_Identifiers;
	private Object[][][] yieldTable_values;
	private String [] yieldTable_ColumnNames;
	
	private JTable table;
	private Object[][] data;
	private TableModelSpectrum model;
	
	public Output_Panel_Management_Details(File currentProjectFolder, String currentRun, JTable table, Object[][] data, TableModelSpectrum model) {
		this.table = table;
		this.data = data;
		this.model = model;
		
		
		// Some set up ---------------------------------------------------------------------------	
		file_StrataDefinition = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/strata_definition.csv");
		file_Database = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/database.db");
		Read_RunInputs read = new Read_RunInputs();
		read.readGeneralInputs(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_01_general_inputs.txt"));
		int total_Periods = read.get_total_periods();
		
		// Read definition
		read_Identifiers = new Read_Indentifiers(file_StrataDefinition);		
	
		// Read the database
		read_DatabaseTables = new Read_DatabaseTables(file_Database);			
		yieldTable_values = read_DatabaseTables.getTableArrays();
		yieldTable_ColumnNames = read_DatabaseTables.getTableColumnNames();		
		// End of set up ---------------------------------------------------------------------------	

		
		
		
		
		
		
		// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
		static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(file_StrataDefinition);
		checkboxStaticIdentifiers = static_identifiersScrollPanel.get_CheckboxStaticIdentifiers();		
				
		//Update GUI for time period 
    	for (int j = 0; j < checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).size(); j++) {			//The last element is Time period			
			if (j < total_Periods) {
				checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(true);		//Periods to be visible 			
			} else {
				checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setVisible(false);		//Periods to be invisible
				checkboxStaticIdentifiers.get(checkboxStaticIdentifiers.size() - 1).get(j).setSelected(false);		//Periods to be unselected
			}
		} 
    	    	
		// Some initial selection
    	for (int i = 4; i < checkboxStaticIdentifiers.size(); i++) {				
    		for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {		//The last element is Time period						
				if (j > 0) {
    				checkboxStaticIdentifiers.get(i).get(j).setSelected(false);		// only the 1st would be selected 			
    			}
    		} 
		} 
    	
		// Listeners for checkboxStaticIdentifiers
		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
				checkboxStaticIdentifiers.get(i).get(j).addItemListener(this);
			}
		}	
		// 2 lines to activate the listeners
		checkboxStaticIdentifiers.get(0).get(0).setSelected(false);
		checkboxStaticIdentifiers.get(0).get(0).setSelected(true);

		
		// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(2, 
				read_DatabaseTables, read_Identifiers, yieldTable_ColumnNames, yieldTable_values);
			
				
		// 3rd grid ------------------------------------------------------------------------------		// Parameters
		parametersScrollPanel = new ScrollPane_Parameters(yieldTable_ColumnNames);
		TitledBorder border = new TitledBorder("PARAMETERS");
		border.setTitleJustification(TitledBorder.CENTER);
		parametersScrollPanel.setBorder(border);
    	parametersScrollPanel.setPreferredSize(new Dimension(200, 100));			
		
    	    	
    	// 4th grid ------------------------------------------------------------------------------		// table scroll pane
        JScrollPane table_scroll_pane = new JScrollPane();
        border = new TitledBorder("THE OPTIMAL SOLUTION - MANAGEMENT DETAILS");
		border.setTitleJustification(TitledBorder.CENTER);
		table_scroll_pane.setBorder(border);
		table_scroll_pane.setViewportView(table);
		table_scroll_pane.setPreferredSize(new Dimension(200, 100));
		
    	
    	
		
    	// Add all Grids to the Main Grid-----------------------------------------------------------------------
    	// Add all Grids to the Main Grid-----------------------------------------------------------------------
    	setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		
		// Add static_identifiersScrollPanel to the main Grid
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.3;
	    c.weighty = 0;
		super.add(static_identifiersScrollPanel, c);				
	    		
		// Add dynamic_identifiersPanel to the main Grid
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0;
		super.add(dynamic_identifiersScrollPanel, c);	
		    		
		// Add the parametersScrollPanel to the main Grid	
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
	    c.weighty = 1;
		super.add(parametersScrollPanel, c);						
	    	    		    
	    // Add the table	
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2; 
		c.gridheight = 1;
		c.weightx = 1;
	    c.weighty = 1;
		super.add(table_scroll_pane, c);			
	}	
	
	//Listeners for this class------------------------------------------------------------------------------------------------------------------------
	public void itemStateChanged(ItemEvent e) {
//		List<String> variable_term_list = new ArrayList<String>();		
//		
//		for (int i = 0; i < data.length; i++) {
//			String var_name = data[i][1].toString();
//			
//			if (var_name.startsWith("xNG_")) {
//				var_name = var_name.replace("xNG_", "");
//				String[] term = var_name.split(",");
//				String layer1 = term[0];
//				String layer2 = term[1];
//				String layer3 = term[2];
//				String layer4 = term[3];
//				String layer5 = term[4];
//				String layer6 = term[5];
//				String period = term[6];
//				String aggregation = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Natural Growth" + period;
//				variable_term_list.add(aggregation);
//			} 
//			else if (var_name.startsWith("xPB_")) {
//				var_name = var_name.replace("xPB_", "");
//				String[] term = var_name.toString().split(",");
//				String layer1 = term[0];
//				String layer2 = term[1];
//				String layer3 = term[2];
//				String layer4 = term[3];
//				String layer5 = term[4];
//				String layer6 = term[5];
//				String timing_choice = term[6];
//				String period = term[7];
//				String aggregation = layer1 + layer2 + layer3 + layer4 + layer5 + layer6 + "Prescribed Burn" + period;
//				variable_term_list.add(aggregation);			
//			}
//		}
		//-------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------
		List<String> checkbox_term_list = new ArrayList<String>();
		List<String> checkbox_term_xEAr_list = new ArrayList<String>();
		
		for (JCheckBox layer1: checkboxStaticIdentifiers.get(0)) {
			for (JCheckBox layer2: checkboxStaticIdentifiers.get(1)) {
				for (JCheckBox layer3: checkboxStaticIdentifiers.get(2)) {
					for (JCheckBox layer4: checkboxStaticIdentifiers.get(3)) {
						for (JCheckBox layer5: checkboxStaticIdentifiers.get(4)) {
							for (JCheckBox layer6: checkboxStaticIdentifiers.get(5)) {
								for (JCheckBox method: checkboxStaticIdentifiers.get(6)) {
									for (JCheckBox period: checkboxStaticIdentifiers.get(7)) {
										if (
												(layer1.isSelected() && (layer1.isVisible()) || !layer1.isEnabled()) &&
												(layer2.isSelected() && (layer2.isVisible()) || !layer2.isEnabled()) &&
												(layer3.isSelected() && (layer3.isVisible()) || !layer3.isEnabled()) &&
												(layer4.isSelected() && (layer4.isVisible()) || !layer4.isEnabled()) &&
												(layer5.isSelected() && (layer5.isVisible()) || !layer5.isEnabled()) &&
												(layer6.isSelected() && (layer6.isVisible()) || !layer6.isEnabled()) &&
												(method.isSelected() && (method.isVisible()) || !method.isEnabled()) &&
												(period.isSelected() && (period.isVisible()) || !period.isEnabled())
												) 
										{
											String aggregation = layer1.getText() + layer2.getText() + layer3.getText() + layer4.getText() + layer5.getText() + layer6.getText() + method.getText() + period.getText();	
											checkbox_term_list.add(aggregation);	
											
											aggregation = layer1.getText() + layer2.getText() + layer3.getText() + layer4.getText() + layer5.getText() + method.getText() + period.getText();												
											checkbox_term_xEAr_list.add(aggregation);	
										}								
									}
								}
							}
						}
					}
				}
			}
		}
		//-------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------		
//		RowFilter<Object, Object> startsWithAFilter = new RowFilter<Object, Object>() {
//			public boolean include(Entry<? extends Object, ? extends Object> entry) {				
//
//				Boolean is_finally_true = false;
//				String original_term = entry.getStringValue(1);
//				String term = get_variable_term(entry.getStringValue(1));
//				
//				
//				for (JCheckBox layer1 : checkboxStaticIdentifiers.get(0)) {
//					if ((layer1.isSelected() && (layer1.isVisible()) || !layer1.isEnabled())
//							&& term.startsWith(layer1.getText())) {
//						is_finally_true = true;
//						term = term.substring(layer1.getText().length(), term.length());
//					}
//				}
//
//				
//				if (is_finally_true) {
//					int count = 0;
//					for (JCheckBox layer2 : checkboxStaticIdentifiers.get(1)) {
//						if ((layer2.isSelected() && (layer2.isVisible()) || !layer2.isEnabled())
//								&& term.startsWith(layer2.getText())) {
//							count++;
//							term = term.substring(layer2.getText().length(), term.length());
//						}
//					}
//					if (count < 1)
//						is_finally_true = false;
//				}
//				
//				
//				if (is_finally_true) {
//					int count = 0;
//					for (JCheckBox layer3 : checkboxStaticIdentifiers.get(2)) {
//						if ((layer3.isSelected() && (layer3.isVisible()) || !layer3.isEnabled())
//								&& term.startsWith(layer3.getText())) {
//							count++;
//							term = term.substring(layer3.getText().length(), term.length());
//						}
//					}
//					if (count < 1)
//						is_finally_true = false;
//				}
//				
//			
//				if (is_finally_true) {
//					int count = 0;
//					for (JCheckBox layer4 : checkboxStaticIdentifiers.get(3)) {
//						if ((layer4.isSelected() && (layer4.isVisible()) || !layer4.isEnabled())
//								&& term.startsWith(layer4.getText())) {
//							count++;
//							term = term.substring(layer4.getText().length(), term.length());
//						}
//					}
//					if (count < 1)
//						is_finally_true = false;
//				}
//				
//				
//				if (is_finally_true) {
//					int count = 0;
//					for (JCheckBox layer5 : checkboxStaticIdentifiers.get(4)) {
//						if ((layer5.isSelected() && (layer5.isVisible()) || !layer5.isEnabled())
//								&& term.startsWith(layer5.getText())) {
//							count++;
//							term = term.substring(layer5.getText().length(), term.length());
//						}
//					}
//					if (count < 1)
//						is_finally_true = false;
//				}
//				
//				if (original_term.contains("xNG_")) System.out.println("found NG " + original_term + " term = " + term);
//				if (original_term.contains("xNG_") || original_term.contains("xPB_")
//						|| original_term.contains("xGS_") || original_term.contains("xMS_") 
//						|| original_term.contains("xEAe_") || original_term.contains("xEAe'_") ) {					
//					if (is_finally_true) {
//						int count = 0;
//						for (JCheckBox layer6 : checkboxStaticIdentifiers.get(5)) {
//							if ((layer6.isSelected() && (layer6.isVisible()) || !layer6.isEnabled())
//									&& term.startsWith(layer6.getText())) {
//								count++;
//								term = term.substring(layer6.getText().length(), term.length());
//							}
//						}
//						if (count < 1)
//							is_finally_true = false;
//					}
//					
//
//					if (is_finally_true) {
//						int count = 0;
//						for (JCheckBox method : checkboxStaticIdentifiers.get(6)) {
//							if ((method.isSelected() && (method.isVisible()) || !method.isEnabled())
//									&& term.startsWith(method.getText())) {
//								count++;
//								term = term.substring(method.getText().length(), term.length());
//							}
//						}
//						if (count < 1)
//							is_finally_true = false;
//					}	
//				
//					
//					if (is_finally_true) {
//						int count = 0;
//						for (JCheckBox period : checkboxStaticIdentifiers.get(7)) {
//							if ((period.isSelected() && (period.isVisible()) || !period.isEnabled())
//									&& term.startsWith(period.getText())) {
//								count++;
//								term = term.substring(period.getText().length(), term.length());
//							}
//						}
//						if (count < 1)
//							is_finally_true = false;
//					}	
//				}
//				
//				
//				else if (original_term.contains("xEAr_") || original_term.contains("xEAr'_") ) {										
//					if (is_finally_true) {
//						int count = 0;
//						for (JCheckBox method : checkboxStaticIdentifiers.get(6)) {
//							if ((method.isSelected() && (method.isVisible()) || !method.isEnabled())
//									&& term.startsWith(method.getText())) {
//								count++;
//								term = term.substring(method.getText().length(), term.length());
//							}
//						}
//						if (count < 1)
//							is_finally_true = false;
//					}	
//				
//					
//					if (is_finally_true) {
//						int count = 0;
//						for (JCheckBox period : checkboxStaticIdentifiers.get(7)) {
//							if ((period.isSelected() && (period.isVisible()) || !period.isEnabled())
//									&& term.startsWith(period.getText())) {
//								count++;
//								term = term.substring(period.getText().length(), term.length());
//							}
//						}
//						if (count < 1)
//							is_finally_true = false;
//					}	
//				}
//
//				
//				return is_finally_true;	// return false so that this entry is not shown
//			}
//		};
//		
//		TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
//		table.setRowSorter(sorter);
//		sorter.setRowFilter(startsWithAFilter);
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
//	    TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
//		table.setRowSorter(sorter);
//		List<RowFilter<TableModelSpectrum, Object>> filters, filters2;
//		filters2 = new ArrayList<RowFilter<TableModelSpectrum, Object>>();
//		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
//			RowFilter<TableModelSpectrum, Object> layer_filter = null;
//			filters = new ArrayList<RowFilter<TableModelSpectrum, Object>>();
//			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
//				if (checkboxStaticIdentifiers.get(i).get(j).isSelected()) {
//					filters.add(RowFilter.regexFilter(checkboxStaticIdentifiers.get(i).get(j).getText(), i + 1)); // i+1 is the table column containing the first layer	
//				}
//			}
//			layer_filter = RowFilter.orFilter(filters);
//			filters2.add(layer_filter);
//		}
//		RowFilter<TableModelSpectrum, Object> combine_AllFilters = null;
//		combine_AllFilters = RowFilter.andFilter(filters2);
//		sorter.setRowFilter(combine_AllFilters);		
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		// BELOW IS THE OLD FREAKING SLOW FILTER --> THE ABOVE I WROTE IS MUCH MORE FASTER & SMARTER		
		long time_start = System.currentTimeMillis();		// measure time before solving
	
		
		RowFilter<Object, Object> startsWithAFilter = new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {				
//				for (int i = entry.getValueCount() - 1; i >= 0; i--) {	// Loop columns
				String modified_var_name = Get_Variable_Information.get_customized_variable_term(entry.getStringValue(1));		// entry.getStringValue(1) = column 1 = var_name
				if (checkbox_term_list.contains(modified_var_name) || checkbox_term_xEAr_list.contains(modified_var_name)) {
					return true;
				}
//				}

				return false; // return false so that this entry is not shown
			}
		};
		
		TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
		table.setRowSorter(sorter);
		sorter.setRowFilter(startsWithAFilter);
		
		
		long time_end = System.currentTimeMillis();		//measure time after solving
		double timeElapsed = (double) (time_end - time_start) / 1000;
		System.out.println("Total time filter = " + timeElapsed);
	}
	
	
	
	
}	
