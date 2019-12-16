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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.PrismTableModel;
import prism_project.output.Chart;
import prism_project.output.Chart_Rotator;

public class Panel_SR_Probability extends JLayeredPane {
	
	public Panel_SR_Probability(Object[][] data6, JSplitPane sr_split_pane, JPanel sr_lower_panel) {
		// Set up table
		List<String> scenarios = new ArrayList<String>();
		List<Double> probabilities = new ArrayList<Double>();
		for (int row = 0; row < data6.length; row++) {
			String scenario_id = String.valueOf(data6[row][0]);
			if (!scenario_id.trim().equalsIgnoreCase("Main") && !scenarios.contains(scenario_id)) {
				scenarios.add(String.valueOf(data6[row][0]));
				probabilities.add(Double.valueOf(data6[row][1].toString()));
			}
		}
		
		int rowCount = scenarios.size();
		int colCount = 2;
		Object[][] data = new Object[rowCount][colCount];
		for (int row = 0; row < data.length; row++) {
			data[row][0] = scenarios.get(row);
			data[row][1] = probabilities.get(row);
		}
		String[] columnNames= new String[] {"scenario_id" , "probability"};
		
		PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 1) return Double.class;
				else return String.class;
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				return (col == 1) ? true : false;
			}
		};
        JTable table = new JTable(model) {
        	@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(this,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}
        };
		
        table.setFillsViewportHeight(true);
		JScrollPane table_scroll_pane = new JScrollPane(table);
		//---------------------------------------------------------------
		
		
		JPanel radio_panel = new JPanel();
		radio_panel.setLayout(new GridBagLayout());
		radio_panel.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints c = new GridBagConstraints();

		ButtonGroup radio_group= new ButtonGroup();
		JRadioButton[] radio_button = new JRadioButton[3];
		radio_button[0] = new JRadioButton("Bar chart");
		radio_button[1] = new JRadioButton("Pie chart");
		radio_button[2] = new JRadioButton("Save & exit");
		radio_button[0].addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if (selectedRow != -1) {
				selectedRow = table.convertRowIndexToModel(selectedRow);
				table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		radio_button[1].addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if (selectedRow != -1) {
				selectedRow = table.convertRowIndexToModel(selectedRow);
				table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		radio_button[2].addActionListener(e -> {
			for (int i = 0; i < data6.length; i++) {
				String scenario_id = String.valueOf(data6[i][0]);
				if (!scenario_id.trim().equalsIgnoreCase("Main")) {
					int row = scenarios.indexOf(String.valueOf(data6[i][0]));
					data6[i][1] = data[row][1];
				}
			}
			
			sr_split_pane.setRightComponent(sr_lower_panel);
		});
		
		radio_group.add(radio_button[0]);
		radio_group.add(radio_button[1]);
		radio_panel.add(radio_button[0], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 10, 0, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[1], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 10, 0, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[2], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				2, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 10, 0, 10));	// insets top, left, bottom, right
        //---------------------------------------------------------------
        JScrollPane scroll_bar_chart = new JScrollPane();
//      scroll_bar_chart.setPreferredSize(new Dimension(100, 100));
        scroll_bar_chart.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_bar_chart.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        TitledBorder border = new TitledBorder("Scenarios Chart");
		border.setTitleJustification(TitledBorder.CENTER);
		scroll_bar_chart.setBorder(border);
        //---------------------------------------------------------------
		JSplitPane split_pane_data = new JSplitPane();
		border = new TitledBorder("Scenarios Data (excluding the Main scenario)");
		border.setTitleJustification(TitledBorder.CENTER);
		split_pane_data.setBorder(border);
		split_pane_data.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_pane_data.setOneTouchExpandable(false);
		split_pane_data.setDividerSize(0);
		split_pane_data.setResizeWeight(0.99);
		split_pane_data.setLeftComponent(table_scroll_pane);
		split_pane_data.setRightComponent(radio_panel);
		//------------------------------------------------------------------------------------------------------------------------------
		JSplitPane split_pane = new JSplitPane();
		split_pane.setBorder(null);
		split_pane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split_pane.setOneTouchExpandable(true);
		split_pane.setDividerSize(3);
		split_pane.setResizeWeight(0.445);
		split_pane.setLeftComponent(split_pane_data);
		split_pane.setRightComponent(scroll_bar_chart);
		//------------------------------------------------------------------------------------------------------------------------------
        
			
	    // Add listener
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				// Create a chart	
				JFreeChart chart = null;
				if (radio_button[0].isSelected()) {
					chart = create_single_bar_chart(data);	 
				} else if (radio_button[1].isSelected()) {
					chart = create_single_pie_chart(data);	  
				}
	 	        
				// add the chart to a panel
				ChartPanel chart_panel = new ChartPanel(chart);
	         	if (chart != null) chart.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	 	        TitledBorder border = new TitledBorder("");
	 			border.setTitleJustification(TitledBorder.CENTER);
	 			chart_panel.setBorder(border);
	 			chart_panel.setPreferredSize(new Dimension(100, 100));
				scroll_bar_chart.setViewportView(chart_panel);	// Add panel to scroll panel
				split_pane_data.setPreferredSize(new Dimension(100, 100));
				
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
        
        radio_button[1].setSelected(true);
    	table.setRowSelectionInterval(0, 0);
        //-------------------------------------------------------------------------------------------------
        
        setLayout(new GridBagLayout());
		c = new GridBagConstraints();
        super.add(split_pane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 5, 0, 5));		// insets top, left, bottom, right
	}

	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_bar_chart(Object[][] data) {	
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Probability distribution";
		
		for (int row = 0; row < data.length; row++) {
			dataset.addValue(Double.valueOf(data[row][1].toString()), "value", String.valueOf(data[row][0]));
		}
		
		Chart charts = new Chart();
		return charts.create_single_bar_chart(chart_name, "scenario", "probability", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_pie_chart(Object[][] data) {			
		final DefaultPieDataset dataset = new DefaultPieDataset( );
		String chart_name = "Probability distribution";
		
		for (int row = 0; row < data.length; row++) {
			dataset.setValue(String.valueOf(data[row][0]), Double.valueOf(data[row][1].toString()));
		}
		if (data.length == 1) dataset.setValue("left over", 0);	// this is to prevent frozen when the pie chart only has 1 dataset (1 scenario)
		
		Chart charts = new Chart();
		return charts.create_single_pie_chart(chart_name, "list of scenarios", dataset);
	}	
}	

