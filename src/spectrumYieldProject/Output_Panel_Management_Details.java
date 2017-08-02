package spectrumYieldProject;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import spectrumConvenienceClasses.TableModelSpectrum;
import spectrumROOT.Spectrum_Main;

public class Output_Panel_Management_Details extends JLayeredPane implements ItemListener {
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	private ScrollPane_Parameters parametersScrollPanel;
	private ScrollPane_StaticIdentifiers static_identifiersScrollPanel;
	private ScrollPane_DynamicIdentifiers dynamic_identifiersScrollPanel;
	
	private File file_Database;
	private Read_Database read_Database;
	
	private JScrollPane table_scroll_pane;
	private JTable table;
	private Object[][] data;
	private TableModelSpectrum model;
	
	private Thread thread_filter;
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	
	public Output_Panel_Management_Details(File currentProjectFolder, String currentRun, JTable table, Object[][] data, TableModelSpectrum model) {
		this.table = table;
		this.data = data;
		this.model = model;
		
		
		// Some set up ---------------------------------------------------------------------------	
		file_Database = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/database.db");
		Read_RunInputs read = new Read_RunInputs();
		read.readGeneralInputs(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_01_general_inputs.txt"));
		int total_Periods = read.get_total_periods();

		// Read the database
		read_Database = new Read_Database(file_Database);				
		// End of set up ---------------------------------------------------------------------------	

		
		
		
		
		
		
		// 1st grid ------------------------------------------------------------------------------		// Static identifiers	
		static_identifiersScrollPanel = new ScrollPane_StaticIdentifiers(read_Database);
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
    	    	
    	
		// Listeners for checkboxStaticIdentifiers
		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {
			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {
				checkboxStaticIdentifiers.get(i).get(j).addItemListener(this);
			}
		}	
		
		
//		// Some initial selection
//    	for (int i = 4; i < checkboxStaticIdentifiers.size(); i++) {				
//    		for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {		//The last element is Time period						
//				if (j > 0) {
//    				checkboxStaticIdentifiers.get(i).get(j).setSelected(false);		// only the 1st would be selected 			
//    			}
//    		} 
//		} 
    	
    	
//		// 2 lines to activate the listeners
//		checkboxStaticIdentifiers.get(0).get(0).setSelected(false);
//		checkboxStaticIdentifiers.get(0).get(0).setSelected(true);

		
		// 2nd Grid ------------------------------------------------------------------------------		// Dynamic identifiers
		dynamic_identifiersScrollPanel = new ScrollPane_DynamicIdentifiers(read_Database);
			
				
		// 3rd grid ------------------------------------------------------------------------------		// Parameters
		parametersScrollPanel = new ScrollPane_Parameters(read_Database);
		TitledBorder border = new TitledBorder("PARAMETERS");
		border.setTitleJustification(TitledBorder.CENTER);
		parametersScrollPanel.setBorder(border);
    	parametersScrollPanel.setPreferredSize(new Dimension(200, 100));			
		
    	    	
    	// 4th grid ------------------------------------------------------------------------------		// table scroll pane
        table_scroll_pane = new JScrollPane();
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
		c.weightx = 0;
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

		
//		Thread filter_thread = new Thread() {
//			public void run() {
//				try {
//					sleep(300);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//
//				// Put the whole thing here, need to fix because it is currently not working properly
//				
//				this.interrupt();
//			}
//		};
//		
//		if (!Thread.currentThread().isInterrupted()) {
//			Thread.currentThread().interrupt();
//			filter_thread.start();
//		}
		
		
		
		
//		// 1. Good FILTER: using threads	
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//		for (Thread t : threadArray) {
//			if (t.getState() == Thread.State.TIMED_WAITING) {
//				t.interrupt();
//				t.stop();
//				Spectrum_Main.get_main().revalidate();
//				Spectrum_Main.get_main().repaint();
//			}
//		}
//		
//
//		
//		executor.submit(new Runnable() {
//			public void run() {
//				try {
//				      Thread.sleep(500);
//				    } catch (InterruptedException e) {
//				      System.out.println("Interrupted, so exiting.");
//				      Spectrum_Main.get_main().revalidate();
//				      Spectrum_Main.get_main().repaint();
//				    }
//				
//				
//				table_scroll_pane.setViewportView(null);	// Hide table before filtering
//				
//				
//				RowFilter<Object, Object> equalsAFilter = new RowFilter<Object, Object>() {
//					// 1. FAST FILTER: NOT SURE IF FASTER THAN 2
//					public boolean include(Entry<? extends Object, ? extends Object> entry) {				
//						Boolean is_finally_shown = false;
//						String varible_term = entry.getStringValue(1);
//						String term;
//						
//						
//						term = Get_Variable_Information.get_layer1(entry.getStringValue(1));
//						for (JCheckBox layer1 : checkboxStaticIdentifiers.get(0)) {
//							if ((layer1.isSelected() && (layer1.isVisible()) || !layer1.isEnabled())
//									&& term.equals(layer1.getText())) {
//								is_finally_shown = true;
//							}
//						}
//						
//						
//						term = Get_Variable_Information.get_layer2(entry.getStringValue(1));
//						if (is_finally_shown) {
//							int count = 0;
//							for (JCheckBox layer2 : checkboxStaticIdentifiers.get(1)) {
//								if ((layer2.isSelected() && (layer2.isVisible()) || !layer2.isEnabled())
//										&& term.equals(layer2.getText())) {
//									count++;
//								}
//							}
//							if (count < 1)
//								is_finally_shown = false;
//						}
//						
//						
//						term = Get_Variable_Information.get_layer3(entry.getStringValue(1));
//						if (is_finally_shown) {
//							int count = 0;
//							for (JCheckBox layer3 : checkboxStaticIdentifiers.get(2)) {
//								if ((layer3.isSelected() && (layer3.isVisible()) || !layer3.isEnabled())
//										&& term.equals(layer3.getText())) {
//									count++;
//								}
//							}
//							if (count < 1)
//								is_finally_shown = false;
//						}
//						
//					
//						term = Get_Variable_Information.get_layer4(entry.getStringValue(1));
//						if (is_finally_shown) {
//							int count = 0;
//							for (JCheckBox layer4 : checkboxStaticIdentifiers.get(3)) {
//								if ((layer4.isSelected() && (layer4.isVisible()) || !layer4.isEnabled())
//										&& term.equals(layer4.getText())) {
//									count++;
//								}
//							}
//							if (count < 1)
//								is_finally_shown = false;
//						}
//						
//						
//						term = Get_Variable_Information.get_layer5(entry.getStringValue(1));
//						if (is_finally_shown) {
//							int count = 0;
//							for (JCheckBox layer5 : checkboxStaticIdentifiers.get(4)) {
//								if ((layer5.isSelected() && (layer5.isVisible()) || !layer5.isEnabled())
//										&& term.equals(layer5.getText())) {
//									count++;
//								}
//							}
//							if (count < 1)
//								is_finally_shown = false;
//						}
//						
//						
//						
//						// The following vary depending on what type of variable is
//						if (varible_term.contains("xNGe_") || varible_term.contains("xPBe_")
//								|| varible_term.contains("xGSe_") || varible_term.contains("xMSe_") 
//								|| varible_term.contains("xBSe_") || varible_term.contains("xEAe_") ) {		
//
//							
//							term = Get_Variable_Information.get_layer6(entry.getStringValue(1));
//							if (is_finally_shown) {
//								int count = 0;
//								for (JCheckBox layer6 : checkboxStaticIdentifiers.get(5)) {
//									if ((layer6.isSelected() && (layer6.isVisible()) || !layer6.isEnabled())
//											&& term.equals(layer6.getText())) {
//										count++;
//									}
//								}
//								if (count < 1)
//									is_finally_shown = false;
//							}
//							
//
//							term = Get_Variable_Information.get_method(entry.getStringValue(1)) + "e";				//NOTE NOTE NOTE Remove this + "e" later
//							if (is_finally_shown) {
//								int count = 0;
//								for (JCheckBox method : checkboxStaticIdentifiers.get(6)) {
//									if ((method.isSelected() && (method.isVisible()) || !method.isEnabled())
//											&& term.equals(method.getText())) {
//										count++;
//									}
//								}
//								if (count < 1)
//									is_finally_shown = false;
//							}	
//							
//							
//							term = String.valueOf(Get_Variable_Information.get_period(entry.getStringValue(1)));
//							if (is_finally_shown) {
//								int count = 0;
//								for (JCheckBox period : checkboxStaticIdentifiers.get(7)) {
//									if ((period.isSelected() && (period.isVisible()) || !period.isEnabled())
//											&& term.equals(period.getText())) {
//										count++;
//									}
//								}
//								if (count < 1)
//									is_finally_shown = false;
//							}	
//						}
//						
//						
//						else if (varible_term.contains("xNGr_") || varible_term.contains("xPBr_")
//								|| varible_term.contains("xGSr_") || varible_term.contains("xEAr_") ) {
//							
//							
//							term = Get_Variable_Information.get_method(entry.getStringValue(1)) + "r";				//NOTE NOTE NOTE Remove this + "r" later
//							if (is_finally_shown) {
//								int count = 0;
//								for (JCheckBox method : checkboxStaticIdentifiers.get(6)) {
//									if ((method.isSelected() && (method.isVisible()) || !method.isEnabled())
//											&& term.equals(method.getText())) {
//										count++;
//									}
//								}
//								if (count < 1)
//									is_finally_shown = false;
//							}	
//							
//							
//							term = String.valueOf(Get_Variable_Information.get_period(entry.getStringValue(1)));
//							if (is_finally_shown) {
//								int count = 0;
//								for (JCheckBox period : checkboxStaticIdentifiers.get(7)) {
//									if ((period.isSelected() && (period.isVisible()) || !period.isEnabled())
//											&& term.equals(period.getText())) {
//										count++;
//									}
//								}
//								if (count < 1)
//									is_finally_shown = false;
//							}	
//						}
//						
//						return is_finally_shown;		// return false so that this entry is not shown
//					}
//				};
//				
//				TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
//				table.setRowSorter(sorter);
//				sorter.setRowFilter(equalsAFilter);	
//				
//				
//				table_scroll_pane.setViewportView(table);	// Show table after filtering is finished
//			}
//		});
//
//		try {
//			if (executor.awaitTermination(-1, TimeUnit.SECONDS)) {	
//				System.out.println("aaaaaaaaaaa");
//			} else {				
//				System.out.println("Task completed, other waiting Filters Threads are automatically shut down");
//				Spectrum_Main.get_main().revalidate();
//				Spectrum_Main.get_main().repaint();
//			}
//		} catch (InterruptedException e1) {
//			System.out.println("Executor problem in Filter Threads in Customize Mode");
//		}
		
		

		
		
		// THESE FOLLLOWING IS INTERESTING, SAME AS ABOVE BUT I PUT THE WHOLE THING INTO A THREAD AND NO NEED TO STOP ANY MORE --> just interrupt & AVOID TROUBLE OF FREEZING
		// THESE FOLLLOWING IS INTERESTING, SAME AS ABOVE BUT I PUT THE WHOLE THING INTO A THREAD AND NO NEED TO STOP ANY MORE --> just interrupt & AVOID TROUBLE OF FREEZING
		// THESE FOLLLOWING IS INTERESTING, SAME AS ABOVE BUT I PUT THE WHOLE THING INTO A THREAD AND NO NEED TO STOP ANY MORE --> just interrupt & AVOID TROUBLE OF FREEZING
		
		Thread filter_thread = new Thread() {
			public void run() {	
				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
				Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
				for (Thread t : threadArray) {
					if (t.getState() == Thread.State.RUNNABLE && t != Thread.currentThread()) {
						t.interrupt();
//						t.stop();
						Spectrum_Main.get_main().revalidate();
						Spectrum_Main.get_main().repaint();
					}
				}

				
				executor.submit(new Runnable() {
					public void run() {										
						table_scroll_pane.setViewportView(null);	// Hide table before filtering
						
						
						RowFilter<Object, Object> equalsAFilter = new RowFilter<Object, Object>() {
							// 1. FAST FILTER: NOT SURE IF FASTER THAN 2
							public boolean include(Entry<? extends Object, ? extends Object> entry) {				
								String varible_term = entry.getStringValue(1);
								String term;
								int count;
								
								
								
								term = Get_Variable_Information.get_layer1(varible_term);
								count = 0;
								for (JCheckBox layer1 : checkboxStaticIdentifiers.get(0)) {
									if ((layer1.isSelected() && (layer1.isVisible()) || !layer1.isEnabled()) && term.equals(layer1.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown								
								
								
								
								term = Get_Variable_Information.get_layer2(varible_term);
								count = 0;
								for (JCheckBox layer2 : checkboxStaticIdentifiers.get(1)) {
									if ((layer2.isSelected() && (layer2.isVisible()) || !layer2.isEnabled()) && term.equals(layer2.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown
								
								
								
								term = Get_Variable_Information.get_layer3(varible_term);
								count = 0;
								for (JCheckBox layer3 : checkboxStaticIdentifiers.get(2)) {
									if ((layer3.isSelected() && (layer3.isVisible()) || !layer3.isEnabled()) && term.equals(layer3.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown
								
							
								
								term = Get_Variable_Information.get_layer4(varible_term);
								count = 0;
								for (JCheckBox layer4 : checkboxStaticIdentifiers.get(3)) {
									if ((layer4.isSelected() && (layer4.isVisible()) || !layer4.isEnabled()) && term.equals(layer4.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown
								
								
								
								term = Get_Variable_Information.get_layer5(varible_term);
								count = 0;
								for (JCheckBox layer5 : checkboxStaticIdentifiers.get(4)) {
									if ((layer5.isSelected() && (layer5.isVisible()) || !layer5.isEnabled()) && term.equals(layer5.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown
								
								
								
								if (Get_Variable_Information.get_layer6(varible_term) != null) {		// Only Existing variables have layer6 <> null
									term = Get_Variable_Information.get_layer6(varible_term);
									count = 0;
									for (JCheckBox layer6 : checkboxStaticIdentifiers.get(5)) {
										if ((layer6.isSelected() && (layer6.isVisible()) || !layer6.isEnabled()) && term.equals(layer6.getText())) {
											count++;
										}
									}
									if (count < 1) return false;		// return false so that this entry is not shown
								}
								
								
								
								term = Get_Variable_Information.get_method(varible_term) + "_" + Get_Variable_Information.get_forest_status(varible_term);
								count = 0;
								for (JCheckBox method : checkboxStaticIdentifiers.get(6)) {
									if ((method.isSelected() && (method.isVisible()) || !method.isEnabled()) && term.equals(method.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown	
								
								
								
								term = String.valueOf(Get_Variable_Information.get_period(varible_term));
								count = 0;
								for (JCheckBox period : checkboxStaticIdentifiers.get(7)) {
									if ((period.isSelected() && (period.isVisible()) || !period.isEnabled()) && term.equals(period.getText())) {
										count++;
									}
								}
								if (count < 1) return false;		// return false so that this entry is not shown		
								

								
								return true;	// return true to show the entry
							}
						};
						
						TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
						table.setRowSorter(sorter);
						sorter.setRowFilter(equalsAFilter);	
						
						
						table_scroll_pane.setViewportView(table);	// Show table after filtering is finished
					}
				});

				try {
					if (executor.awaitTermination(-1, TimeUnit.SECONDS)) {	
						System.out.println("aaaaaaaaaaa");
					} else {				
						System.out.println("Task completed, other waiting Filters Threads are automatically shut down");
						Spectrum_Main.get_main().revalidate();
						Spectrum_Main.get_main().repaint();
					}
				} catch (InterruptedException e1) {
					System.out.println("Executor problem in Filter Threads in Customize Mode");
				}
								
				this.interrupt();
			}
		};
		
		if (!Thread.currentThread().isInterrupted()) {
			Thread.currentThread().interrupt();
			filter_thread.start();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		// 2. FAST FILTER: not sure, this may be faster but we need many more columns in the table	
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
		
			
		
		
		
		
	
		
		
		

		
//		// 3. SLOWEST FILTER: BELOW IS THE OLD FREAKING SLOW FILTER --> THE 1st METHOD I WROTE IS MUCH MORE FASTER & SMARTER		
//		long time_start = System.currentTimeMillis();		// measure time before solving
//	
//		
//		RowFilter<Object, Object> startsWithAFilter = new RowFilter<Object, Object>() {
//			public boolean include(Entry<? extends Object, ? extends Object> entry) {				
////				for (int i = entry.getValueCount() - 1; i >= 0; i--) {	// Loop columns
//				String modified_var_name = Get_Variable_Information.get_customized_variable_term(entry.getStringValue(1));		// entry.getStringValue(1) = column 1 = var_name
//				if (checkbox_term_list.contains(modified_var_name) || checkbox_term_xEAr_list.contains(modified_var_name)) {
//					return true;
//				}
////				}
//
//				return false; // return false so that this entry is not shown
//			}
//		};
//		
//		TableRowSorter<TableModelSpectrum> sorter = new TableRowSorter<TableModelSpectrum>(model);
//		table.setRowSorter(sorter);
//		sorter.setRowFilter(startsWithAFilter);
//		
//		
//		long time_end = System.currentTimeMillis();		//measure time after solving
//		double timeElapsed = (double) (time_end - time_start) / 1000;
//		System.out.println("Total time filter = " + timeElapsed);
	}

}	
