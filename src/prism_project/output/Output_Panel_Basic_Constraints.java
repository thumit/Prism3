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
package prism_project.output;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import prism_convenience.IconHandle;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.TableColumnsHandle;

// Panel_Flow_Constraints--------------------------------------------------------------------------------	
public class Output_Panel_Basic_Constraints extends JLayeredPane {
	
	public Output_Panel_Basic_Constraints(JTable bc_table, Object[][] bc_data) {
		JScrollPane table_scroll_pane = new JScrollPane(bc_table);
		table_scroll_pane.setPreferredSize(new Dimension(100, 100));
		bc_table.setFillsViewportHeight(true);
	    //---------------------------------------------------------------
        JSplitPane split_pane_data = new JSplitPane();
		TitledBorder border = new TitledBorder("Basic Constraints Data");
		border.setTitleJustification(TitledBorder.CENTER);
		split_pane_data.setBorder(border);
		split_pane_data.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split_pane_data.setDividerSize(3);
		split_pane_data.setResizeWeight(0.55);
		split_pane_data.setLeftComponent(table_scroll_pane);
		split_pane_data.setRightComponent(null);
		//---------------------------------------------------------------
		
		JPanel radio_panel = new JPanel();
		radio_panel.setLayout(new GridBagLayout());
		radio_panel.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints c = new GridBagConstraints();

		ButtonGroup radio_group= new ButtonGroup();
		JRadioButton[] radio_button = new JRadioButton[5];
		radio_button[0] = new JRadioButton("Single - Bar");
		radio_button[1] = new JRadioButton("Single - Pie");
		radio_button[2] = new JRadioButton("Multiple - Bar");
		radio_button[3] = new JRadioButton("Multiple - Bar Stacked 1");
		radio_button[4] = new JRadioButton("Multiple - Bar Stacked 2");
		radio_button[0].addActionListener(e -> {
			int selectedRow = bc_table.getSelectedRow();
			bc_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			bc_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if (selectedRow != -1) {
				selectedRow = bc_table.convertRowIndexToModel(selectedRow);
				bc_table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		radio_button[1].addActionListener(e -> {
			int selectedRow = bc_table.getSelectedRow();
			bc_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			bc_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if (selectedRow != -1) {
				selectedRow = bc_table.convertRowIndexToModel(selectedRow);
				bc_table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		radio_button[2].addActionListener(e -> {
			int[] selectedRows = bc_table.getSelectedRows();
			bc_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			bc_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = bc_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					bc_table.addRowSelectionInterval(i, i);
				}
			}
		});
		radio_button[3].addActionListener(e -> {
			int[] selectedRows = bc_table.getSelectedRows();
			bc_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			bc_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = bc_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					bc_table.addRowSelectionInterval(i, i);
				}
			}
		});
		radio_button[4].addActionListener(e -> {
			int[] selectedRows = bc_table.getSelectedRows();
			bc_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			bc_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = bc_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					bc_table.addRowSelectionInterval(i, i);
				}
			}
		});
		
		radio_group.add(radio_button[0]);
		radio_group.add(radio_button[1]);
		radio_group.add(radio_button[2]);
		radio_group.add(radio_button[3]);
		radio_group.add(radio_button[4]);
		radio_panel.add(radio_button[0], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 10, 0, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[1], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 10, 0, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[2], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[3], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[4], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				2, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
        //---------------------------------------------------------------
        JScrollPane scroll_bar_chart = new JScrollPane();
        scroll_bar_chart.setPreferredSize(new Dimension(100, 100));
        scroll_bar_chart.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_bar_chart.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll_bar_chart.setBorder(null);
//      scroll_bar_chart.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, ColorUtil.makeTransparent(Color.BLACK, 0)));  // only draw the bottom border, so only bottom border can be resized 
//      ComponentResizer cr = new ComponentResizer();
//		cr.registerComponent(scroll_bar_chart);
        //---------------------------------------------------------------
		JSplitPane split_pane_chart = new JSplitPane();
		border = new TitledBorder("Basic Constraints Chart");
		border.setTitleJustification(TitledBorder.CENTER);
		split_pane_chart.setBorder(border);
		split_pane_chart.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split_pane_chart.setOneTouchExpandable(true);
		split_pane_chart.setDividerSize(3);
		split_pane_chart.setResizeWeight(0.55);
		split_pane_chart.setLeftComponent(scroll_bar_chart);
		split_pane_chart.setRightComponent(radio_panel);
		//------------------------------------------------------------------------------------------------------------------------------
		JSplitPane split_pane = new JSplitPane();
		split_pane.setBorder(null);
		split_pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_pane.setOneTouchExpandable(true);
		split_pane.setDividerSize(3);
		split_pane.setResizeWeight(0.66);
		split_pane.setLeftComponent(split_pane_chart);
		split_pane.setRightComponent(split_pane_data);
		//------------------------------------------------------------------------------------------------------------------------------
        
    	// Hide columns
		TableColumnsHandle table_handle = new TableColumnsHandle(bc_table);
//		table_handle.setColumnVisible("var_id", false);
//		table_handle.setColumnVisible("var_name", false);
		
		// Set icon for column "bc_type"
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				if (value.toString().equals("FREE")) {
					setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_blue.png"));
				} else if (value.toString().equals("HARD")) {
					setIcon(IconHandle.get_scaledImageIcon(10, 10, "icon_circle_red.png"));
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};	
//		bc_table.getColumn("bc_type").setCellRenderer(r);
		bc_table.getColumn("bc_description").setPreferredWidth(200);		
//		bc_table.getColumn("lowerbound_percentage").setHeaderValue("LB%");	// change header name
//		bc_table.getColumn("upperbound_percentage").setHeaderValue("UB%");	// change header name
		bc_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
	    // Add listener
        bc_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				// Create a chart	
				JFreeChart chart = null;
				if (radio_button[0].isSelected()) {	// Single Flow
					int selectedRow = bc_table.getSelectedRow();
					selectedRow = bc_table.convertRowIndexToModel(selectedRow);	// Convert row index because "Sort" causes problems
					chart = create_single_bar_chart(bc_table, bc_data, selectedRow);	 
				} else if (radio_button[1].isSelected()) {	// Single Flow
					int selectedRow = bc_table.getSelectedRow();
					selectedRow = bc_table.convertRowIndexToModel(selectedRow);	// Convert row index because "Sort" causes problems
					chart = create_single_pie_chart(bc_table, bc_data, selectedRow);	  
				} else if (radio_button[2].isSelected()) {	// Multiple Flows
					int[] selectedRows = bc_table.getSelectedRows();
					for (int i : selectedRows) {
						i = bc_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_bar_chart(bc_table, bc_data, selectedRows);	 
				} else if (radio_button[3].isSelected()) {	// Multiple Flows - Stacked
					int[] selectedRows = bc_table.getSelectedRows();
					for (int i : selectedRows) {
						i = bc_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_stacked_bar1_chart(bc_table, bc_data, selectedRows);	 
				} else if (radio_button[4].isSelected()) {	// Multiple Flows - Stacked
					int[] selectedRows = bc_table.getSelectedRows();
					for (int i : selectedRows) {
						i = bc_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_stacked_bar2_chart(bc_table, bc_data, selectedRows);	 
				}
	 	        
				// add the chart to a panel
				ChartPanel chart_panel = new ChartPanel(chart);
	         	if (chart != null) chart.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	 	        TitledBorder border = new TitledBorder("");
	 			border.setTitleJustification(TitledBorder.CENTER);
	 			chart_panel.setBorder(border);
	 			chart_panel.setPreferredSize(new Dimension(100, 100));
				scroll_bar_chart.setViewportView(chart_panel);	// Add panel to scroll panel
				
				int total_iteration = (bc_data[0].length - 4) / 2;
				int total_columns_of_the_chart = total_iteration;
				if (total_columns_of_the_chart > 15) {
					split_pane_chart.setPreferredSize(new Dimension((int) 100 + 15 * (total_columns_of_the_chart - 15), 100));
				} else {
					split_pane_chart.setPreferredSize(new Dimension(100, 100));
				}
				
		    	// Rotation effect
				if (radio_button[1].isSelected()) {	// Single Constraint
			        final Chart_Rotator rotator = new Chart_Rotator((PiePlot3D) chart.getPlot());
			        rotator.start();           
			        chart_panel.addMouseListener(new MouseAdapter() { // Add listener to projectTree
						boolean is_rotating = true;
						public void mousePressed(MouseEvent e) {
							if (SwingUtilities.isLeftMouseButton(e)) {
								if (is_rotating) {
									rotator.stop();
									is_rotating = false;
								} else {
									rotator.start();
									is_rotating = true;
								}
							}
						}
					});
				}
				revalidate();
				repaint();
        	}       
        });
        
        radio_button[0].setSelected(true);
    	bc_table.setRowSelectionInterval(0, 0);
		bc_table.clearSelection();
        //-------------------------------------------------------------------------------------------------
        
        setLayout(new GridBagLayout());
		c = new GridBagConstraints();
        super.add(split_pane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 5, 0, 5));		// insets top, left, bottom, right
	}

	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_bar_chart(JTable bc_table, Object[][] bc_data, int selectedRow) {	
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight a basic constraint to view chart";
		if (selectedRow >= 0) {
			chart_name = bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString();
			// Put all into dataset		
			for (int i = 0; i < total_iteration; i++) {
				dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), "value", String.valueOf(i));
			}
		}
		
		Chart charts = new Chart();
		return charts.create_single_bar_chart(chart_name, "iteration", "value", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_pie_chart(JTable bc_table, Object[][] bc_data, int selectedRow) {			
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultPieDataset dataset = new DefaultPieDataset( );
		
		String chart_name = "Highlight a basic constraint to view chart";
		if (selectedRow >= 0) {
			chart_name = bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString();
			// Put all into dataset		
			for (int i = 0; i < total_iteration; i++) {
				dataset.setValue("iteration " + String.valueOf(i), Double.valueOf(bc_data[selectedRow][i + 4].toString()));
			}
			if (total_iteration == 1) dataset.setValue("others", 0);	// this is to prevent frozen when the pie chart only has 1 dataset (1 iteration)	
		}
		
		Chart charts = new Chart();
		return charts.create_single_pie_chart(chart_name, "list of iterations", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_bar_chart(JTable bc_table, Object[][] bc_data, int[] selectedRows) {		
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight single or multiple basic constraints to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted basic constraints";
			for (int selectedRow: selectedRows) {
				// Put all into dataset		
				for (int i = 0; i < total_iteration; i++) {
					dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString(), String.valueOf(i));
				}
			}
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_bar_chart(chart_name, "iteration", "value", dataset);
	}	

	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar1_chart(JTable bc_table, Object[][] bc_data, int[] selectedRows) {			
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight single or multiple basic constraints to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted basic constraints";
			for (int selectedRow: selectedRows) {
				// Put all into dataset		
				for (int i = 0; i < total_iteration; i++) {
					dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString(), String.valueOf(i));
				}
			}
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_stacked_bar1_chart(chart_name, "iteration (stacked by basic constraints)", "value", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar2_chart(JTable bc_table, Object[][] bc_data, int[] selectedRows) {			
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight single or multiple basic constraints to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted basic constraints";
			for (int selectedRow: selectedRows) {
				// Put all into dataset		
				for (int i = 0; i < total_iteration; i++) { 
					dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), "iteration " + String.valueOf(i), bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString());
				}
			}
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_stacked_bar2_chart(chart_name, "basic constraint (stacked by iterations)", "value", dataset);
	}	
}	

