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

package prism_project.output;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LevelRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

import prism_convenience.ColorUtil;
import prism_convenience.IconHandle;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.PrismTableModel;
import prism_convenience.TableColumnsHandle;
import prism_project.data_process.Read_Input;
import prism_root.PrismMain;

// Panel_Flow_Constraints--------------------------------------------------------------------------------	
public class Output_Panel_Flow_Constraints extends JLayeredPane {
	private int total_columns_of_the_chart;
	private JTable legend_table;
	JScrollPane legend_scroll_pane;
	String[][] bc_values;
	
	public Output_Panel_Flow_Constraints(File currentProjectFolder, String currentRun, JTable flow_table, Object[][] flow_data) {
		Read_Input read = new Read_Input(null);
		read.read_basic_constraints(new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/input_08_basic_constraints.txt"));
		bc_values = read.get_bc_data();
		//---------------------------------------------------------------
		
		JScrollPane table_scroll_pane = new JScrollPane(flow_table);
		table_scroll_pane.setPreferredSize(new Dimension(100, 100));
		flow_table.setFillsViewportHeight(true);
	    //---------------------------------------------------------------
		legend_table = new JTable();
		legend_scroll_pane = new JScrollPane(legend_table);
		legend_scroll_pane.setPreferredSize(new Dimension(100, 100));
		legend_table.setFillsViewportHeight(true);
	    //---------------------------------------------------------------
        JSplitPane split_pane_data = new JSplitPane();
		TitledBorder border = new TitledBorder("Flow Data");
		border.setTitleJustification(TitledBorder.CENTER);
		split_pane_data.setBorder(border);
		split_pane_data.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split_pane_data.setDividerSize(3);
		split_pane_data.setResizeWeight(0.55);
		split_pane_data.setLeftComponent(table_scroll_pane);
		split_pane_data.setRightComponent(legend_scroll_pane);
		//------------------------------------------------------------------------------------------------------------------------------

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
			int selectedRow = flow_table.getSelectedRow();
			flow_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			flow_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			legend_scroll_pane.setViewportView(new JTextArea());	// to make the scroll pane show a white area without any information. 
			if (selectedRow != -1) {
				selectedRow = flow_table.convertRowIndexToModel(selectedRow);
				flow_table.addRowSelectionInterval(selectedRow, selectedRow);
			}
			legend_scroll_pane.setBorder(table_scroll_pane.getBorder());
		});
		radio_button[1].addActionListener(e -> {
			int selectedRow = flow_table.getSelectedRow();
			flow_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			flow_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			legend_scroll_pane.setViewportView(new JTextArea());	// to make the scroll pane show a white area without any information. 
			if (selectedRow != -1) {
				selectedRow = flow_table.convertRowIndexToModel(selectedRow);
				flow_table.addRowSelectionInterval(selectedRow, selectedRow);
			}
			legend_scroll_pane.setBorder(table_scroll_pane.getBorder());
		});
		radio_button[2].addActionListener(e -> {
			int[] selectedRows = flow_table.getSelectedRows();
			flow_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			flow_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			legend_scroll_pane.setViewportView(new JTextArea());	// to make the scroll pane show a white area without any information. 
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = flow_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					flow_table.addRowSelectionInterval(i, i);
				}
			} else {
				legend_scroll_pane.setBorder(table_scroll_pane.getBorder());
			}
		});
		radio_button[3].addActionListener(e -> {
			int[] selectedRows = flow_table.getSelectedRows();
			flow_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			flow_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			legend_scroll_pane.setViewportView(new JTextArea());	// to make the scroll pane show a white area without any information.
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = flow_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					flow_table.addRowSelectionInterval(i, i);
				}
			} else {
				legend_scroll_pane.setBorder(table_scroll_pane.getBorder());
			}
		});
		radio_button[4].addActionListener(e -> {
			int[] selectedRows = flow_table.getSelectedRows();
			flow_table.setRowSelectionInterval(0, 0);	// no need to clear selection because the below line would auto do it. This is to show the empty graph with the default chart name
			flow_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			legend_scroll_pane.setViewportView(new JTextArea());	// to make the scroll pane show a white area without any information.
			if (selectedRows.length > 0) {
				for (int i : selectedRows) {
					i = flow_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					flow_table.addRowSelectionInterval(i, i);
				}
			} else {
				legend_scroll_pane.setBorder(table_scroll_pane.getBorder());
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
		border = new TitledBorder("Flow Chart");
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
		TableColumnsHandle table_handle = new TableColumnsHandle(flow_table);
		table_handle.setColumnVisible("flow_arrangement", false);
		table_handle.setColumnVisible("flow_output_original", false);
		
		// Set icon for column "flow_type"
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
		flow_table.getColumn("flow_type").setCellRenderer(r);
		flow_table.getColumn("iteration").setPreferredWidth(50);	
		flow_table.getColumn("flow_id").setPreferredWidth(50);					
		flow_table.getColumn("flow_description").setPreferredWidth(200);		
		flow_table.getColumn("flow_type").setPreferredWidth(50);	
		flow_table.getColumn("lowerbound_percentage").setPreferredWidth(50);
		flow_table.getColumn("upperbound_percentage").setPreferredWidth(50);
		flow_table.getColumn("lowerbound_percentage").setHeaderValue("LB%");	// change header name
		flow_table.getColumn("upperbound_percentage").setHeaderValue("UB%");	// change header name
		flow_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
	    // Add listener
        flow_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				// Create a chart	
				JFreeChart chart = null;
				if (radio_button[0].isSelected()) {	// Single Flow
					int selectedRow = flow_table.getSelectedRow();
					selectedRow = flow_table.convertRowIndexToModel(selectedRow);	// Convert row index because "Sort" causes problems
					chart = create_single_bar_chart(flow_table, flow_data, selectedRow);	 
				} else if (radio_button[1].isSelected()) {	// Single Flow
					int selectedRow = flow_table.getSelectedRow();
					selectedRow = flow_table.convertRowIndexToModel(selectedRow);	// Convert row index because "Sort" causes problems
					chart = create_single_pie_chart(flow_table, flow_data, selectedRow);	  
				} else if (radio_button[2].isSelected()) {	// Multiple Flows
					int[] selectedRows = flow_table.getSelectedRows();
					for (int i : selectedRows) {
						i = flow_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_bar_chart(flow_table, flow_data, selectedRows);	 
				} else if (radio_button[3].isSelected()) {	// Multiple Flows - Stacked
					int[] selectedRows = flow_table.getSelectedRows();
					for (int i : selectedRows) {
						i = flow_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_stacked_bar1_chart(flow_table, flow_data, selectedRows);	 
				} else if (radio_button[4].isSelected()) {	// Multiple Flows - Stacked
					int[] selectedRows = flow_table.getSelectedRows();
					for (int i : selectedRows) {
						i = flow_table.convertRowIndexToModel(i);	// Convert row index because "Sort" causes problems
					}
					chart = create_multiple_stacked_bar2_chart(flow_table, flow_data, selectedRows);	 
				}
				
	 	        
				// add the chart to a panel
				ChartPanel chart_panel = new ChartPanel(chart);
	         	if (chart != null) chart.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	 	        TitledBorder border = new TitledBorder("");
	 			border.setTitleJustification(TitledBorder.CENTER);
	 			chart_panel.setBorder(border);
	 			chart_panel.setPreferredSize(new Dimension(100, 100));
				scroll_bar_chart.setViewportView(chart_panel);	// Add panel to scroll panel
				
				if (total_columns_of_the_chart > 15) {
					split_pane_chart.setPreferredSize(new Dimension((int) 100 + 15 * (total_columns_of_the_chart - 15), 100));
				} else {
					split_pane_chart.setPreferredSize(new Dimension(100, 100));
				}
				
		    	// Rotation effect
				if (radio_button[1].isSelected()) {	// Single Flow
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
    	flow_table.setRowSelectionInterval(0, 0);
		flow_table.clearSelection();
		legend_scroll_pane.setViewportView(new JTextArea());	// to make the scroll pane show a white area without any information. 
		legend_scroll_pane.setBorder(table_scroll_pane.getBorder());
        //-------------------------------------------------------------------------------------------------
        
        setLayout(new GridBagLayout());
		c = new GridBagConstraints();
        super.add(split_pane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 5, 0, 5));		// insets top, left, bottom, right
	}
	
	
	

	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_bar_chart(JTable flow_table, Object[][] flow_data, int selectedRow) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		final DefaultCategoryDataset dataset_LB = new DefaultCategoryDataset( );
		final DefaultCategoryDataset dataset_UB = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight a flow to view chart";
		if (selectedRow >= 0) {
			chart_name = flow_data[selectedRow][0].toString()  + "," + flow_data[selectedRow][1].toString() + " - " + flow_data[selectedRow][2].toString() + " - " + flow_data[selectedRow][4].toString();
			
			// Read flow_arrangement
			String[] flow_arrangement_info = flow_data[selectedRow][3].toString().split(";");	// Read the whole cell 'flow_arrangement'
			total_columns_of_the_chart = flow_arrangement_info.length;
			List<String> flow_arrangement = new ArrayList<String>();
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				flow_arrangement.add(flow_arrangement_info[i]);
			}
			
			// Read flow_output_original
			String[] flow_output_original_info = flow_data[selectedRow][7].toString().split(";");	// Read the whole cell 'flow_output_original'
			List<Double> flow_output_original = new ArrayList<Double>();
			for (int i = 0; i < flow_output_original_info.length; i++) {
				flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
			}				
				
			// Put all into dataset		
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				dataset.addValue(flow_output_original.get(i), "FV", flow_arrangement.get(i).replaceAll("\\s+", "+"));
				if (!flow_data[selectedRow][5].toString().equals("null") && flow_data[selectedRow][4].toString().equals("HARD")) {
					if (i > 0) {
						double lb_value = Double.parseDouble(flow_data[selectedRow][5].toString()) * flow_output_original.get(i - 1) / 100;	
						dataset_LB.addValue(lb_value, "LB. " + flow_data[selectedRow][5].toString() + "% of left-column FV", flow_arrangement.get(i).replaceAll("\\s+", "+"));	
					} else if (i == 0) {
						dataset_LB.addValue(null, "LB. " + flow_data[selectedRow][5].toString() + "% of left-column FV", flow_arrangement.get(i).replaceAll("\\s+", "+"));	
					}
				} 
				if (!flow_data[selectedRow][6].toString().equals("null") && flow_data[selectedRow][4].toString().equals("HARD")) {
					if (i > 0) {
						double ub_value = Double.parseDouble(flow_data[selectedRow][6].toString()) * flow_output_original.get(i - 1) / 100;	
						dataset_UB.addValue(ub_value, "UB. " + flow_data[selectedRow][6].toString() + "% of left-column FV", flow_arrangement.get(i).replaceAll("\\s+", "+"));
					} else if (i == 0) {
						dataset_UB.addValue(null, "UB. " + flow_data[selectedRow][6].toString() + "% of left-column FV", flow_arrangement.get(i).replaceAll("\\s+", "+"));
					}
				} 
			}
			
			// Refresh legend table
			refresh_legend_table_for_single_chart(flow_data[selectedRow]);
		}

					
		
		
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createBarChart(chart_name, "bc_id", "Flow Value: FV",
				dataset, PlotOrientation.VERTICAL, true, true, false);		
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		chart.getLegend().setBackgroundPaint(null);
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
//		chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
		chart.getTitle().setFont(new java.awt.Font("defaultFont", java.awt.Font.BOLD, 14));
				
		// Set color for each different bar
		CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setItemMargin(0.5);	
	    renderer.setMaximumBarWidth(.15); // set maximum width to 15% of chart	    
	    // show value in the middle of column---------
	    renderer.setItemLabelGenerator(
				new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("###,###")));
	    try {
			renderer.setItemLabelFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 10));
			renderer.setItemLabelFont(new java.awt.Font("Sitka Small", java.awt.Font.PLAIN, 10));
		} catch (Exception e) {
		}
	    renderer.setItemLabelsVisible(true);
		renderer.setPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 2));
	    renderer.setBaseItemLabelsVisible(true);
	    //--------------------------------------------    
		renderer.setDrawBarOutline(false);	
		GradientPaint gp = null;
		for (int i = 0; i < dataset.getRowCount(); i++){
		    switch (i) {
		    case 0:
		    	if (dataset_LB.getColumnCount() > 0 || dataset_UB.getColumnCount() > 0) {
		    		gp = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(220,20,60), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,160,122), 255));
		    		break;
		    	} else {	  // if this is a FREE flow
		    		gp = new GradientPaint(0.0f, 0.0f, Color.white, 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(233,150,122), 255));
		    		break;
		    	}		        
		       
		    }
		    renderer.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D	
		}	
	
		
		if (dataset_LB.getColumnCount() > 0) {
			final CategoryItemRenderer renderer2 = new LevelRenderer();
			renderer2.setSeriesStroke(0, new BasicStroke(0.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] { 0.5f, 0.5f }, 0.0f));		// after float[] change 2 numbers for dash line
			for (int i = 0; i < dataset_LB.getRowCount(); i++){
			    switch (i) {
			    case 0:
					gp = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, new Color(0, 0, 64));
			        break;		    
			    }
			    renderer2.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D
			}
	        plot.setDataset(1, dataset_LB);
	        plot.setRenderer(1, renderer2);
	        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);	
		}

		
		
		if (dataset_UB.getColumnCount() > 0) {
	        final CategoryItemRenderer renderer3 = new LevelRenderer();
	        renderer3.setSeriesStroke(0, new BasicStroke(0.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] { 0.5f, 0.5f }, 0.0f));		// after float[] change 2 numbers for dash line
	    	for (int i = 0; i < dataset_UB.getRowCount(); i++){
			    switch (i) {
			    case 0:
			    	gp = new GradientPaint(0.0f, 0.0f, Color.black, 0.8f, 0.8f, Color.black);
			        break;	    
			    }
			    renderer3.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D
			}
	        plot.setDataset(2, dataset_UB);
	        plot.setRenderer(2, renderer3);
	        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		}
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
		
		
//		// Create 3D bar chart
//		JFreeChart chart = ChartFactory.createBarChart(chart_name, "Flow Arrangement: labeled by IDs of basic constraints: bc_id", "Flow Value",
//				dataset, PlotOrientation.VERTICAL, true, true, false);		
//		chart.setBorderVisible(true);
//		chart.setBackgroundPaint(Color.LIGHT_GRAY);
//		chart.getLegend().setBackgroundPaint(null);
//		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
//		chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
//		chart.getTitle().setFont(new java.awt.Font("defaultFont", java.awt.Font.BOLD, 14));
//				
//		// Set color for each different bar
//		CategoryPlot plot = chart.getCategoryPlot();
//		BarRenderer renderer = (BarRenderer) plot.getRenderer();
//		Color color = null;
//		GradientPaint gp = null;
//		for (int i = 0; i < dataset.getRowCount(); i++){
//		    switch (i) {
//		    case 0:
//		        color = new Color(255, 0, 0);
//		        gp = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, new Color(64, 0, 0));  
//		        break;
//		    case 1:
//		        color = new Color(0, 255, 0);
//		        gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
//		        break;
//		    default:
//		        color = new Color(255, 255, 51);
//		        gp = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, new Color(0, 0, 64));
//		        break;
//		    }
//		    renderer.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D
//		    renderer.setItemMargin(0.08);			    
//			renderer.setItemLabelGenerator(
//					new StandardCategoryItemLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0.00 acres"), new DecimalFormat("0.0%")));
////			renderer.setBaseItemLabelsVisible(true);
//			renderer.setDrawBarOutline(false);
//			
//		}	
		plot.setOutlineVisible(false);
		return chart;
	}	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_pie_chart(JTable flow_table, Object[][] flow_data, int selectedRow) {			
		final DefaultPieDataset dataset = new DefaultPieDataset( );
		
		String chart_name = "Highlight a flow to view chart";
		if (selectedRow >= 0) {
			chart_name = flow_data[selectedRow][0].toString()  + "," + flow_data[selectedRow][1].toString() + " - " + flow_data[selectedRow][2].toString() + " - " + flow_data[selectedRow][4].toString();
			
			// Read flow_arrangement
			String[] flow_arrangement_info = flow_data[selectedRow][3].toString().split(";");	// Read the whole cell 'flow_arrangement'
			total_columns_of_the_chart = flow_arrangement_info.length;
			List<String> flow_arrangement = new ArrayList<String>();
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				flow_arrangement.add(flow_arrangement_info[i]);
			}
			
			// Read flow_output_original
			String[] flow_output_original_info = flow_data[selectedRow][7].toString().split(";");	// Read the whole cell 'flow_output_original'
			List<Double> flow_output_original = new ArrayList<Double>();
			for (int i = 0; i < flow_output_original_info.length; i++) {
				flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
			}				
			
			// Put all into dataset		
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				dataset.setValue(flow_arrangement.get(i).replaceAll("\\s+", "+"), flow_output_original.get(i));
			}
			
			// Refresh legend table
			refresh_legend_table_for_single_chart(flow_data[selectedRow]);
		}
					
		Chart charts = new Chart();
		return charts.create_single_pie_chart(chart_name, "list of bc_id", dataset);
	}	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_bar_chart(JTable this_table, Object[][] flow_data, int[] selectedRows) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		final DefaultCategoryDataset dataset_LB = new DefaultCategoryDataset( );
		final DefaultCategoryDataset dataset_UB = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight single or multiple flows to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted flows of the Folow Data\n";
			for (int selectedRow: selectedRows) {
				chart_name = String.join("   ", chart_name, flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString());

				// Read flow_arrangement
				String[] flow_arrangement_info = flow_data[selectedRow][3].toString().split(";");	// Read the whole cell 'flow_arrangement'
				total_columns_of_the_chart = flow_arrangement_info.length;
				List<String> flow_arrangement = new ArrayList<String>();
				for (int i = 0; i < flow_arrangement_info.length; i++) {
					flow_arrangement.add(flow_arrangement_info[i]);
				}
				
				// Read flow_output_original
				String[] flow_output_original_info = flow_data[selectedRow][7].toString().split(";");	// Read the whole cell 'flow_output_original'
				List<Double> flow_output_original = new ArrayList<Double>();
				for (int i = 0; i < flow_output_original_info.length; i++) {
					flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
				}				
		        
				// Put all into dataset
				for (int i = 0; i < flow_arrangement_info.length; i++) {
					dataset.addValue(flow_output_original.get(i), flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString() + ". " + flow_data[selectedRow][2].toString(), String.valueOf(i));
					
					if (!flow_data[selectedRow][5].toString().equals("null")) {
						String LB_rename = /*"flow_id = " +*/ flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString()+ ". LB " + flow_data[selectedRow][5].toString() + "%";
						if (i > 0) {
							double lb_value = Double.parseDouble(flow_data[selectedRow][5].toString()) * flow_output_original.get(i - 1) / 100;	
							dataset_LB.addValue(lb_value, LB_rename, String.valueOf(i));	
						} else {	// if this is the first column in the chart
							dataset_LB.addValue(null, LB_rename , String.valueOf(i));	
						}
					} else {
						String LB_rename = flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString() + ". LB na";
						dataset_LB.addValue(null, LB_rename, String.valueOf(i));
					}
					
					if (!flow_data[selectedRow][6].toString().equals("null")) {
						String UB_rename = flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString() + ". UB " + flow_data[selectedRow][6].toString() + "%";
						if (i > 0) {
							double ub_value = Double.parseDouble(flow_data[selectedRow][6].toString()) * flow_output_original.get(i - 1) / 100;	
							dataset_UB.addValue(ub_value, UB_rename, String.valueOf(i));
						} else {	// if this is the first column in the chart
							dataset_UB.addValue(null, UB_rename, String.valueOf(i));
						}
					} else {
						String UB_rename = flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString() + ". UB na";
						dataset_UB.addValue(null, UB_rename, String.valueOf(i));
					}
				}
			}
			refresh_legend_table_for_multiple_chart(flow_data, selectedRows);
		}

					
		
		
		

		// This is for sliding category (might use later, not completed yet)
		final SlidingCategoryDataset dataset_slide = new SlidingCategoryDataset(dataset, 0, 5);
		final SlidingCategoryDataset dataset_LB_slide = new SlidingCategoryDataset(dataset_LB, 0, 5);
		final SlidingCategoryDataset dataset_UB_slide = new SlidingCategoryDataset(dataset_UB, 0, 5);

		JScrollBar scroller = new JScrollBar();
		scroller.getModel().addChangeListener(e -> {
			dataset_slide.setFirstCategoryIndex(scroller.getValue());
		});
		
		
		
		
		
		
		
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createBarChart(chart_name,  "col_id", "Flow Value: FV",
				dataset, PlotOrientation.VERTICAL, true, true, false);		
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		chart.getLegend().setBackgroundPaint(null);
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
//		chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
		chart.getTitle().setFont(new java.awt.Font("defaultFont", java.awt.Font.BOLD, 14));
		
		
		// Set color for each different bar
		CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setItemMargin(0.05);	
	    renderer.setMaximumBarWidth(.15); // set maximum width to 15% of chart	    
	    // show value in the middle of column---------
	    renderer.setItemLabelGenerator(
				new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("###,###")));
	    try {
			renderer.setItemLabelFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 10));
			renderer.setItemLabelFont(new java.awt.Font("Sitka Small", java.awt.Font.PLAIN, 10));
		} catch (Exception e) {
		}
	    renderer.setItemLabelsVisible(true);
		renderer.setPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 2));
	    renderer.setBaseItemLabelsVisible(true);
		renderer.setDrawBarOutline(false);	
		GradientPaint gp = null;
		
		
		GradientPaint[] gp_array = new GradientPaint[100];
		gp_array[0] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(220,20,60), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,160,122), 255));
		gp_array[1] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(22,30,60), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(25,200,122), 255));
		gp_array[2] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,105,0), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(210,215,30), 255));
		gp_array[3] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(50,100,50), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(0,255,0), 255));
		gp_array[4] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(199,105,60), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,255,255), 255));
		gp_array[5] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(0,128,209), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(32,206,170), 255));
		gp_array[6] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(186,85,211), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(248,255,255), 255));
		gp_array[7] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(2,85,211), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(248,255,255), 255));
		gp_array[8] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,160,153), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,255,120), 255));
		gp_array[9] = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(119,136,153), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,255,255), 255));
		for (int i = 0; i < dataset.getRowCount(); i++){
			renderer.setSeriesPaint(i, gp_array[i % 100]);		// use gradient for better color: only assign some, the rest is auto
		}	
		
		if (dataset_LB.getColumnCount() > 0) {
			final CategoryItemRenderer renderer2 = new LevelRenderer();
			renderer2.setSeriesStroke(0, new BasicStroke(0.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] { 0.5f, 0.5f }, 0.0f));		// after float[] change 2 numbers for dash line
			for (int i = 0; i < dataset_LB.getRowCount(); i++){
			    switch (i) {
			    case 0:
					gp = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, new Color(0, 0, 64));
			        break;		    
			    }
			    renderer2.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D
			}
	        plot.setDataset(1, dataset_LB);
	        plot.setRenderer(1, renderer2);
	        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);	
		}
		
		if (dataset_UB.getColumnCount() > 0) {
	        final CategoryItemRenderer renderer3 = new LevelRenderer();
	        renderer3.setSeriesStroke(0, new BasicStroke(0.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] { 0.5f, 0.5f }, 0.0f));		// after float[] change 2 numbers for dash line
	        for (int i = 0; i < dataset_UB.getRowCount(); i++){
			    switch (i) {
			    case 0:
			    	gp = new GradientPaint(0.0f, 0.0f, Color.black, 0.0f, 0.0f, Color.black);
			        break;	    
			    }
			    renderer3.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D
			}
	        plot.setDataset(2, dataset_UB);
	        plot.setRenderer(2, renderer3);
	        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		}
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		plot.setOutlineVisible(false);
		return chart;
	}	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar1_chart(JTable this_table, Object[][] flow_data, int[] selectedRows) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight single or multiple flows to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted flows of the Folow Data\n";
			for (int selectedRow: selectedRows) {
				chart_name = String.join("   ", chart_name, flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString());

				// Read flow_arrangement
				String[] flow_arrangement_info = flow_data[selectedRow][3].toString().split(";");	// Read the whole cell 'flow_arrangement'
				total_columns_of_the_chart = flow_arrangement_info.length;
				List<String> flow_arrangement = new ArrayList<String>();
				for (int i = 0; i < flow_arrangement_info.length; i++) {
					flow_arrangement.add(flow_arrangement_info[i]);
				}
				
				// Read flow_output_original
				String[] flow_output_original_info = flow_data[selectedRow][7].toString().split(";");	// Read the whole cell 'flow_output_original'
				List<Double> flow_output_original = new ArrayList<Double>();
				for (int i = 0; i < flow_output_original_info.length; i++) {
					flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
				}				
													
				// Put all into dataset
				for (int i = 0; i < flow_arrangement_info.length; i++) {
					dataset.addValue(flow_output_original.get(i), flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString()  + ". " + flow_data[selectedRow][2].toString(), String.valueOf(i));
				}
			}
			refresh_legend_table_for_multiple_chart(flow_data, selectedRows);
		}

		Chart charts = new Chart();
		return charts.create_multiple_stacked_bar1_chart(chart_name, "col_id (stacked by flows)", "Flow Value: FV", dataset);
	}	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar2_chart(JTable this_table, Object[][] flow_data, int[] selectedRows) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight single or multiple flows to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted flows of the Folow Data\n";
			for (int selectedRow: selectedRows) {
				chart_name = String.join("   ", chart_name, flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString());

				// Read flow_arrangement
				String[] flow_arrangement_info = flow_data[selectedRow][3].toString().split(";");	// Read the whole cell 'flow_arrangement'
				total_columns_of_the_chart = flow_arrangement_info.length;
				List<String> flow_arrangement = new ArrayList<String>();
				for (int i = 0; i < flow_arrangement_info.length; i++) {
					flow_arrangement.add(flow_arrangement_info[i]);
				}
				
				// Read flow_output_original
				String[] flow_output_original_info = flow_data[selectedRow][7].toString().split(";");	// Read the whole cell 'flow_output_original'
				List<Double> flow_output_original = new ArrayList<Double>();
				for (int i = 0; i < flow_output_original_info.length; i++) {
					flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
				}				
		        
				// Put all into dataset
				for (int i = 0; i < flow_arrangement_info.length; i++) {
					dataset.addValue(flow_output_original.get(i), /*"col_id = " +*/ String.valueOf(i), flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString());
				}
			}
			refresh_legend_table_for_multiple_chart(flow_data, selectedRows);
		}
		
		Chart charts = new Chart();
		return charts.create_multiple_stacked_bar2_chart(chart_name, "flow (stacked by col_id)", "Flow Value: FV", dataset);
	}	
	
	
	
	private void refresh_legend_table_for_single_chart(Object[] flow_data_in_selected_row) {
		// Read flow_arrangement
		String[] flow_arrangement_info = flow_data_in_selected_row[3].toString().split(";");	// Read the whole cell 'flow_arrangement'
		total_columns_of_the_chart = flow_arrangement_info.length;
		List<String> flow_arrangement = new ArrayList<String>();
		for (int i = 0; i < flow_arrangement_info.length; i++) {
			flow_arrangement.add(flow_arrangement_info[i]);
		}
		
		// Read flow_output_original
		String[] flow_output_original_info = flow_data_in_selected_row[7].toString().split(";");	// Read the whole cell 'flow_output_original'
		List<Double> flow_output_original = new ArrayList<Double>();
		for (int i = 0; i < flow_output_original_info.length; i++) {
			flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
		}				
											
		// Calculate FV, LB, UB	-------------------------------------------------------------------------
		List<Double> FV = new ArrayList<Double>();
		List<Double> LB = new ArrayList<Double>();
		List<Double> UB = new ArrayList<Double>();
		for (int i = 0; i < flow_arrangement_info.length; i++) {
			FV.add(flow_output_original.get(i));
			if (!flow_data_in_selected_row[5].toString().equals("null") && flow_data_in_selected_row[4].toString().equals("HARD")) {
				if (i > 0) {
					double lb_value = Double.parseDouble(flow_data_in_selected_row[5].toString()) * flow_output_original.get(i - 1) / 100;	
					LB.add(lb_value);	
				} else if (i == 0) {
					LB.add(null);	
				}
			} 
			if (!flow_data_in_selected_row[6].toString().equals("null") && flow_data_in_selected_row[4].toString().equals("HARD")) {
				if (i > 0) {
					double ub_value = Double.parseDouble(flow_data_in_selected_row[6].toString()) * flow_output_original.get(i - 1) / 100;	
					UB.add(ub_value);
				} else if (i == 0) {
					UB.add(null);
				}
			} 
		}
					
		// Refresh the legend_table------------------------------------------------------------------------
		List<String> all_bc_id_in_the_selected_row = new ArrayList<String>();
		for (int i = 0; i < flow_arrangement_info.length; i++) {
			String[] flow_item = flow_arrangement_info[i].split(" ");
			for (int id = 0; id < flow_item.length; id++) {
				if (id == 0) {
					all_bc_id_in_the_selected_row.add(flow_item[id]);
				} else {
					all_bc_id_in_the_selected_row.add("+" + flow_item[id]);
				}
			}
		}
					
		int rowCount = all_bc_id_in_the_selected_row.size();
		int colCount = 5;
		Object[][] data = new Object[rowCount][colCount];
		String[] columnNames = new String[] { "bc_id", "bc_description", "FV", "LB", "UB" };
		
		// Populate the data matrix
		DecimalFormat formatter = new DecimalFormat("###,###.###");
		formatter.setMinimumFractionDigits(0);
		formatter.setMaximumFractionDigits(0);
		for (int i = 0; i < rowCount; i++) {
			for (int row = 0; row < bc_values.length; row++) {
				if (!all_bc_id_in_the_selected_row.get(i).startsWith("+")) {
					if (all_bc_id_in_the_selected_row.get(i).equals(bc_values[row][0])) {
						data[i][0] = bc_values[row][0];
						data[i][1] = bc_values[row][1];
					}
				} else {	
					if (all_bc_id_in_the_selected_row.get(i).replace("+", "").equals(bc_values[row][0])) {
						data[i][0] = "+" + bc_values[row][0];
						data[i][1] = bc_values[row][1];
					}
				}
			}
		}					
		// This is because some sigma can have more than 1 term, we only write out for the last term (i.e. sigma includes 17 + 620 -->write the FV, UB, LB for both in the line of 620)
		int count = -1;
		for (int i = 0; i < flow_arrangement_info.length; i++) {
			String[] flow_item = flow_arrangement_info[i].split(" ");
			count = count + flow_item.length;
			data[count][2] = formatter.format((Double) FV.get(i));
			data[count][3] = (LB.size() == FV.size() && LB.get(i) != null) ? formatter.format((Double) LB.get(i)) : null;
			data[count][4] = (UB.size() == FV.size() && UB.get(i) != null) ? formatter.format((Double) UB.get(i)) : null;
		}
		
		// Create a table
		PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames);
        legend_table = new JTable(model)  {
			@Override	// Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int row = rowAtPoint(p);
				int col = columnAtPoint(p);
				String tip = (legend_table.getColumnName(col).equals("bc_description") && row >= 0 && getValueAt(row, col) != null) ? getValueAt(row, col).toString() : null;
				return tip;
			}	
		};
        legend_table.setFillsViewportHeight(true);
        legend_table.getColumnModel().getColumn(0).setPreferredWidth(60);	// Set width of 1st Column smaller
        legend_table.getColumnModel().getColumn(1).setPreferredWidth(300);	// Set width of 2nd Column bigger
        legend_table.getColumnModel().getColumn(2).setPreferredWidth(120);	// Set width of 3rd Column smaller
        legend_table.getColumnModel().getColumn(3).setPreferredWidth(120);	// Set width of 4th Column smaller
        legend_table.getColumnModel().getColumn(4).setPreferredWidth(120);	// Set width of 5th Column smaller
        legend_scroll_pane.setViewportView(legend_table);
	}
	
	private void refresh_legend_table_for_multiple_chart(Object[][] flow_data, int[] selectedRows) {
		List<JTable> table_list = new ArrayList<JTable>();
		for (int selectedRow: selectedRows) {
			// Read flow_arrangement
			String[] flow_arrangement_info = flow_data[selectedRow][3].toString().split(";");	// Read the whole cell 'flow_arrangement'
			total_columns_of_the_chart = flow_arrangement_info.length;
			List<String> flow_arrangement = new ArrayList<String>();
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				flow_arrangement.add(flow_arrangement_info[i]);
			}
			
			// Read flow_output_original
			String[] flow_output_original_info = flow_data[selectedRow][7].toString().split(";");	// Read the whole cell 'flow_output_original'
			List<Double> flow_output_original = new ArrayList<Double>();
			for (int i = 0; i < flow_output_original_info.length; i++) {
				flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
			}				
												
			// Calculate FV, LB, UB	-------------------------------------------------------------------------
			List<Double> FV = new ArrayList<Double>();
			List<Double> LB = new ArrayList<Double>();
			List<Double> UB = new ArrayList<Double>();
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				FV.add(flow_output_original.get(i));
				if (!flow_data[selectedRow][5].toString().equals("null") && flow_data[selectedRow][4].toString().equals("HARD")) {
					if (i > 0) {
						double lb_value = Double.parseDouble(flow_data[selectedRow][5].toString()) * flow_output_original.get(i - 1) / 100;	
						LB.add(lb_value);	
					} else if (i == 0) {
						LB.add(null);	
					}
				} 
				if (!flow_data[selectedRow][6].toString().equals("null") && flow_data[selectedRow][4].toString().equals("HARD")) {
					if (i > 0) {
						double ub_value = Double.parseDouble(flow_data[selectedRow][6].toString()) * flow_output_original.get(i - 1) / 100;	
						UB.add(ub_value);
					} else if (i == 0) {
						UB.add(null);
					}
				} 
			}
						
			// Refresh the legend_table------------------------------------------------------------------------
			List<String> all_bc_id_in_the_selected_row = new ArrayList<String>();
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				String[] flow_item = flow_arrangement_info[i].split(" ");
				for (int id = 0; id < flow_item.length; id++) {
					if (id == 0) {
						all_bc_id_in_the_selected_row.add(flow_item[id]);
					} else {
						all_bc_id_in_the_selected_row.add("+" + flow_item[id]);
					}
				}
			}
						
			int rowCount = all_bc_id_in_the_selected_row.size();
			int colCount = 6;
			Object[][] data = new Object[rowCount][colCount];
			String[] columnNames = new String[] { "col_id" , "bc_id", "bc_description", "FV", "LB", "UB" };
			
			// Populate the data matrix
			DecimalFormat formatter = new DecimalFormat("###,###.###");
			formatter.setMinimumFractionDigits(0);
			formatter.setMaximumFractionDigits(0);
			for (int i = 0; i < rowCount; i++) {
				for (int row = 0; row < bc_values.length; row++) {
					if (!all_bc_id_in_the_selected_row.get(i).startsWith("+")) {
						if (all_bc_id_in_the_selected_row.get(i).equals(bc_values[row][0])) {
							data[i][1] = bc_values[row][0];
							data[i][2] = bc_values[row][1];
						}
					} else {	
						if (all_bc_id_in_the_selected_row.get(i).replace("+", "").equals(bc_values[row][0])) {
							data[i][1] = "+" + bc_values[row][0];
							data[i][2] = bc_values[row][1];
						}
					}
				}
			}					
			// This is because some sigma can have more than 1 term, we only write out for the last term (i.e. sigma includes 17 + 620 -->write the FV, UB, LB for both in the line of 620)
			int count = -1;
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				String[] flow_item = flow_arrangement_info[i].split(" ");
				count = count + flow_item.length;
				data[count][0] = String.valueOf(i);
				data[count][3] = formatter.format((Double) FV.get(i));
				data[count][4] = (LB.size() == FV.size() && LB.get(i) != null) ? formatter.format((Double) LB.get(i)) : null;
				data[count][5] = (UB.size() == FV.size() && UB.get(i) != null) ? formatter.format((Double) UB.get(i)) : null;
			}
			
			// Create a table
			PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames);
	        legend_table = new JTable(model);
	        legend_table.setFillsViewportHeight(true);
	        legend_table.getColumnModel().getColumn(0).setPreferredWidth(80);	// Set width of 1st Column smaller
	        legend_table.getColumnModel().getColumn(1).setPreferredWidth(80);	// Set width of 1st Column smaller
	        legend_table.getColumnModel().getColumn(2).setPreferredWidth(300);	// Set width of 2nd Column bigger
	        legend_table.getColumnModel().getColumn(3).setPreferredWidth(120);	// Set width of 3rd Column smaller
	        legend_table.getColumnModel().getColumn(4).setPreferredWidth(120);	// Set width of 4th Column smaller
	        legend_table.getColumnModel().getColumn(5).setPreferredWidth(120);	// Set width of 5th Column smaller
	        table_list.add(legend_table);
		}
        

		
		// The combo to show data from the only one table user wants to see
		JScrollPane temporarytable_scroll = new JScrollPane();
		JTextField temporary_textfield = new JTextField();
		temporary_textfield.setBackground(Color.white);
		temporary_textfield.setEditable(false);
		temporary_textfield.setFocusable(false);
		
		JComboBox combo = new JComboBox();	
		combo.setFocusable(false);
		for (int selectedRow: selectedRows) {
			combo.addItem("iteration,flow_id = " + flow_data[selectedRow][0].toString() + "," + flow_data[selectedRow][1].toString());
		}
		combo.addActionListener(e -> {
			for (int i = 0; i < selectedRows.length; i++) {
				if (("iteration,flow_id = " + flow_data[selectedRows[i]][0].toString() + "," + flow_data[selectedRows[i]][1].toString())
					.equals(combo.getSelectedItem().toString())) {
					temporarytable_scroll.setViewportView(table_list.get(i));
					temporary_textfield.setText(flow_data[selectedRows[i]][2].toString());
				}
			}
		});
		// 3 lines for showing the first flow data
		temporary_textfield.setText(flow_data[selectedRows[0]][2].toString());
		combo.setSelectedItem(flow_data[selectedRows[0]][1].toString());
		temporarytable_scroll.setViewportView(table_list.get(0));
		
		
		JPanel all_table_panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		all_table_panel.add(combo, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				7, 0, 0, 0));	// insets top, left, bottom, right
		all_table_panel.add(temporary_textfield, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				1, 0, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				7, 0, 0, 0));	// insets top, left, bottom, right
		all_table_panel.add(temporarytable_scroll, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 1, 2, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));	// insets top, left, bottom, right
		
		all_table_panel.setPreferredSize(new Dimension(0, 0));
		legend_scroll_pane.setBorder(null);
        legend_scroll_pane.setViewportView(all_table_panel);
	}
}	

