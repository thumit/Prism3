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

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import prism_convenience.IconHandle;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_root.PrismMain;

// Panel_Flow_Constraints--------------------------------------------------------------------------------	
public class Output_Panel_Management_Overview extends JLayeredPane {
	
	public Output_Panel_Management_Overview(JTable overview_table, Object[][] overview_data) {
		JScrollPane table_scroll_pane = new JScrollPane(overview_table);
		table_scroll_pane.setPreferredSize(new Dimension(100, 100));
		overview_table.setFillsViewportHeight(true);
	    //---------------------------------------------------------------
        JSplitPane split_pane_data = new JSplitPane();
		TitledBorder border = new TitledBorder("Management Overview Data");
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
			int selectedRow = overview_table.getSelectedRow();
			overview_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			overview_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if (selectedRow != -1) {
				selectedRow = overview_table.convertRowIndexToModel(selectedRow);
				overview_table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		radio_button[1].addActionListener(e -> {
			int selectedRow = overview_table.getSelectedRow();
			overview_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			overview_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if (selectedRow != -1) {
				selectedRow = overview_table.convertRowIndexToModel(selectedRow);
				overview_table.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		radio_button[2].addActionListener(e -> {
			int[] selectedRows = overview_table.getSelectedRows();
			overview_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			overview_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = overview_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					overview_table.addRowSelectionInterval(i, i);
				}
			}
		});
		radio_button[3].addActionListener(e -> {
			int[] selectedRows = overview_table.getSelectedRows();
			overview_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			overview_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = overview_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					overview_table.addRowSelectionInterval(i, i);
				}
			}
		});
		radio_button[4].addActionListener(e -> {
			int[] selectedRows = overview_table.getSelectedRows();
			overview_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			overview_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = overview_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					overview_table.addRowSelectionInterval(i, i);
				}
			}
		});
		
		JScrollPane scroll_bar_chart = new JScrollPane();
		JScrollPane zoom_scrollpane = new JScrollPane();
		zoom_scrollpane.setBorder(null);
		zoom_scrollpane.addHierarchyListener(new HierarchyListener() {	// These codes make the scrollpane resizable
		    public void hierarchyChanged(HierarchyEvent e) {
		        Window window = SwingUtilities.getWindowAncestor(zoom_scrollpane);
		        if (window instanceof Dialog) {
		            Dialog dialog = (Dialog)window;
		            if (!dialog.isResizable()) {
		                dialog.setResizable(true);
		            	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		        		int width = (int) (gd.getDisplayMode().getWidth() * 0.7);
		        		int height = (int) (gd.getDisplayMode().getHeight() * 0.9);
		                dialog.setPreferredSize(new Dimension(width, height));
		            }
		        }
		    }
		});
		JButton btn_zoom = new JButton();
		btn_zoom.setText("ZOOM");
		btn_zoom.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_zoom.setHorizontalTextPosition(SwingConstants.CENTER);
//		btn_zoom.setToolTipText("explore");
		btn_zoom.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_zoom.png"));
		btn_zoom.setRolloverIcon(IconHandle.get_scaledImageIcon(35, 35, "icon_zoom.png"));
		btn_zoom.setContentAreaFilled(false);
		btn_zoom.addActionListener(e -> {
//			PrismMain.get_main().setVisible(false);
//			for (JInternalFrame i: PrismMain.get_Prism_DesktopPane().getAllFrames()) {
//				i.setVisible(false);
//			} 
			zoom_scrollpane.setViewportView(scroll_bar_chart.getViewport().getView());
			
			String ExitOption[] = { "OK" };
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), zoom_scrollpane,
					"Prism Chart", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);
			
//			PrismMain.get_main().setVisible(true);
//			for (JInternalFrame i: PrismMain.get_Prism_DesktopPane().getAllFrames()) {
//				i.setVisible(true);
//			} 
			scroll_bar_chart.setViewportView(zoom_scrollpane.getViewport().getView());
		});
		
		radio_group.add(radio_button[0]);
		radio_group.add(radio_button[1]);
		radio_group.add(radio_button[2]);
		radio_group.add(radio_button[3]);
		radio_group.add(radio_button[4]);
		radio_panel.add(radio_button[0], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[1], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 1, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[2], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 2, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[3], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 3, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(radio_button[4], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 4, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 10, 10, 10));	// insets top, left, bottom, right
		radio_panel.add(btn_zoom, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 0, 1, 5, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 20, 10, 10));	// insets top, left, bottom, right
        //---------------------------------------------------------------
        scroll_bar_chart.setPreferredSize(new Dimension(100, 100));
        scroll_bar_chart.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_bar_chart.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll_bar_chart.setBorder(null);
//      scroll_bar_chart.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, ColorUtil.makeTransparent(Color.BLACK, 0)));  // only draw the bottom border, so only bottom border can be resized 
//      ComponentResizer cr = new ComponentResizer();
//		cr.registerComponent(scroll_bar_chart);
        //---------------------------------------------------------------
		JSplitPane split_pane_chart = new JSplitPane();
		border = new TitledBorder("Management Overview Chart");
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
		overview_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
	    // Add listener
        overview_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				// Create a chart	
				JFreeChart chart = null;
				if (radio_button[0].isSelected()) {	// Single Flow
					int selectedRow = overview_table.getSelectedRow();
					selectedRow = overview_table.convertRowIndexToModel(selectedRow);	// Convert row index because "Sort" causes problems
					chart = create_single_bar_chart(overview_table, overview_data, selectedRow);	 
				} else if (radio_button[1].isSelected()) {	// Single Flow
					int selectedRow = overview_table.getSelectedRow();
					selectedRow = overview_table.convertRowIndexToModel(selectedRow);	// Convert row index because "Sort" causes problems
					chart = create_single_pie_chart(overview_table, overview_data, selectedRow);	  
				} else if (radio_button[2].isSelected()) {	// Multiple Flows
					int[] selectedRows = overview_table.getSelectedRows();
					for (int i : selectedRows) {
						i = overview_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_bar_chart(overview_table, overview_data, selectedRows);	 
				} else if (radio_button[3].isSelected()) {	// Multiple Flows - Stacked
					int[] selectedRows = overview_table.getSelectedRows();
					for (int i : selectedRows) {
						i = overview_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_stacked_bar1_chart(overview_table, overview_data, selectedRows);	 
				} else if (radio_button[4].isSelected()) {	// Multiple Flows - Stacked
					int[] selectedRows = overview_table.getSelectedRows();
					for (int i : selectedRows) {
						i = overview_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_stacked_bar2_chart(overview_table, overview_data, selectedRows);	 
				}
	 	        
				// add the chart to a panel
				ChartPanel chart_panel = new ChartPanel(chart);
	         	if (chart != null) chart.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	 	        TitledBorder border = new TitledBorder("");
	 			border.setTitleJustification(TitledBorder.CENTER);
	 			chart_panel.setBorder(border);
	 			chart_panel.setPreferredSize(new Dimension(100, 100));
				scroll_bar_chart.setViewportView(chart_panel);	// Add panel to scroll panel
				split_pane_chart.setPreferredSize(new Dimension(100, 100));
				
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
    	overview_table.setRowSelectionInterval(0, 0);
		overview_table.clearSelection();
        //-------------------------------------------------------------------------------------------------
        
        setLayout(new GridBagLayout());
		c = new GridBagConstraints();
        super.add(split_pane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 5, 0, 5));		// insets top, left, bottom, right
	}

	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_bar_chart(JTable table, Object[][] data, int selectedRow) {	
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight an iteration to view chart";
		if (selectedRow >= 0) {
			chart_name = "iteration " + data[selectedRow][0].toString() + " - " + "first period management area";
			// Put all into dataset		
			for (int i = 1; i < data[0].length; i++) {
				dataset.addValue(Double.valueOf(data[selectedRow][i].toString()), "area", table.getColumnName(i));
			}
		}
		
		Chart charts = new Chart();
		return charts.create_single_bar_chart(chart_name, "silviculture method", "area", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_pie_chart(JTable table, Object[][] data, int selectedRow) {			
		final DefaultPieDataset dataset = new DefaultPieDataset( );
		String chart_name = "Highlight an iteration to view chart";
		if (selectedRow >= 0) {
			chart_name = "iteration " + data[selectedRow][0].toString() + " - " + "first period management area";
			// Put all into dataset		
			for (int i = 1; i < data[0].length; i++) {
				dataset.setValue(table.getColumnName(i), Double.valueOf(data[selectedRow][i].toString()));
			}
		}
		
		Chart charts = new Chart();
		return charts.create_single_pie_chart(chart_name, "list of silviculture methods", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_bar_chart(JTable table, Object[][] data, int[] selectedRows) {		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight single or multiple iterations to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted iterations";
			for (int selectedRow: selectedRows) {
				// Put all into dataset		
				for (int i = 1; i < data[0].length; i++) {
					dataset.addValue(Double.valueOf(data[selectedRow][i].toString()), "iteration " + data[selectedRow][0].toString(), table.getColumnName(i));
				}
			}
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_bar_chart(chart_name, "silviculture method", "area", dataset);
	}	

	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar1_chart(JTable table, Object[][] data, int[] selectedRows) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight single or multiple iterations to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted iterations";
			for (int selectedRow: selectedRows) {
				// Put all into dataset		
				for (int i = 1; i < data[0].length; i++) {
					dataset.addValue(Double.valueOf(data[selectedRow][i].toString()), table.getColumnName(i), "iteration " + data[selectedRow][0].toString());
				}
			}
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_stacked_bar1_chart(chart_name, "iteration (stacked by silviculture methods)", "area", dataset);
	}	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar2_chart(JTable table, Object[][] data, int[] selectedRows) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String chart_name = "Highlight single or multiple iterations to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted iterations";
			for (int selectedRow: selectedRows) {
				// Put all into dataset		
				for (int i = 1; i < data[0].length; i++) {
					dataset.addValue(Double.valueOf(data[selectedRow][i].toString()), "iteration " + data[selectedRow][0].toString(), table.getColumnName(i));
				}
			}
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_stacked_bar2_chart(chart_name, "silviculture method (stacked by iterations)", "area", dataset);
	}	
}	

