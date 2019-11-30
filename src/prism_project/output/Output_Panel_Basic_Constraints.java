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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

import prism_convenience.ColorUtil;
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
		split_pane_data.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split_pane_data.setDividerSize(3);
		split_pane_data.setResizeWeight(0.33);
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
//		flow_table.getColumn("flow_type").setCellRenderer(r);
		bc_table.getColumn("bc_description").setPreferredWidth(150);		
//		flow_table.getColumn("lowerbound_percentage").setHeaderValue("LB%");	// change header name
//		flow_table.getColumn("upperbound_percentage").setHeaderValue("UB%");	// change header name
		bc_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
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
				if (radio_button[1].isSelected()) {	// Single Flow
			        final Rotator rotator = new Rotator((PiePlot3D) chart.getPlot());
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
			// Calculate value
			List<Double> FV = new ArrayList<Double>();
			for (int i = 0; i < total_iteration; i++) {
				FV.add(Double.valueOf(bc_data[selectedRow][i + 4].toString()));
			}
			// Put all into dataset		
			for (int i = 0; i < total_iteration; i++) {
				dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), "Value", "" + i);
			}
		}
		
		// Create 3D bar chart------------------------
		JFreeChart chart = ChartFactory.createBarChart(chart_name, "iteration", "value",
				dataset, PlotOrientation.VERTICAL, true, true, false);		
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		chart.getLegend().setBackgroundPaint(null);
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
//		chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
		chart.getLegend().setVisible(false);	// don't show the legend
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
		    	gp = new GradientPaint(0.0f, 0.0f, ColorUtil.makeTransparent(new Color(220,20,60), 255), 0.0f, 0.0f, ColorUtil.makeTransparent(new Color(255,160,122), 255));	        
		    }
		    renderer.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D	
		}	
		plot.setOutlineVisible(false);
		return chart;
	}	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_single_pie_chart(JTable bc_table, Object[][] bc_data, int selectedRow) {			
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultPieDataset dataset = new DefaultPieDataset( );
		
		String chart_name = "Highlight a basic constraint to view chart";
		if (selectedRow >= 0) {
			chart_name = bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString();
			// Calculate value
			List<Double> FV = new ArrayList<Double>();
			for (int i = 0; i < total_iteration; i++) {
				FV.add(Double.valueOf(bc_data[selectedRow][i + 4].toString()));
			}
			// Put all into dataset		
			for (int i = 0; i < total_iteration; i++) {
				dataset.setValue("iteration " + i, Double.valueOf(bc_data[selectedRow][i + 4].toString()));
			}
		}
		
		// Create 3D pie chart-----------------------------------
		JFreeChart chart = ChartFactory.createPieChart3D(chart_name, // chart title
				dataset, // dataset
				true, // include legend
				true, false);		
		
		// 3 lines to create another legend
		TextTitle legendText = new TextTitle("list of iterations");
		legendText.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendText);
		
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		chart.getLegend().setBackgroundPaint(null);
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
		chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
		chart.getTitle().setFont(new java.awt.Font("defaultFont", java.awt.Font.BOLD, 14));

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setOutlinePaint(null);
		plot.setStartAngle(135);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.6f);
        plot.setBackgroundPaint(null);
//		plot.setNoDataMessage("Highlight single or multiple existing strata to view chart");
		plot.setExplodePercent(1, 0.1);
		
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	            "{0}: {1} ({2})", new DecimalFormat("###,###"), new DecimalFormat("##.#%"));			// "{0}: {1} ({2})"
	    plot.setLabelGenerator(gen);	    
	    plot.setLabelBackgroundPaint(null);
	    plot.setLabelShadowPaint(null);
	    plot.setLabelOutlinePaint(null);
	    plot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
	    
	    // Customize colors
//	    plot.setSectionPaint("Natural Growth", new Color(0, 255, 0));
//		plot.setSectionPaint("Prescribed Burn", new Color(255, 255, 0));
//		plot.setSectionPaint("Group Selection", new Color(240, 248, 255));
//	    plot.setSectionPaint("Even Age", new Color(51, 255, 255));
//	    plot.setSectionPaint("Mixed Severity Wildfire", new Color(255, 140, 0));
//	    plot.setSectionPaint("Severe Bark Beetle", new Color(255, 51, 0));
		
	    
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
	    for (int i = 0; i < dataset.getItemCount(); i++){
	    	if (i % 100 <= 9) {
		    	Point2D center = new Point2D.Float(0, 0);
				float radius = 500;
				float[] dist = { 0.0f, 0.4f, 0.6f, 1.0f };
				Color[] colors = { gp_array[i % 100].getColor1(), gp_array[i % 100].getColor1(), gp_array[i % 100].getColor2(), gp_array[i % 100].getColor1() };
				RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
				plot.setSectionPaint(dataset.getKey(i), p);
	    	}
	    }		
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		return chart;
	}	
	
	// I am so smart to not use timer
	private class Rotator {
		private ScheduledExecutorService executor;
		private Runnable task;
		private double angle = 135;

		Rotator(final PiePlot3D plot) {
			task = new Runnable() {
				public void run() {
					plot.setStartAngle(angle);
					angle = angle + (double) 0.05;
					if (angle == 360) {
						angle = 0;
					}

				}
			};
	    }

	    public void stop() {
	    	 executor.shutdown(); // shutdown will allow the final iteration to finish executing where shutdownNow() will kill it immediately
	    }
	    
	    public void start() {
	    	int initialDelay = 0;
		    int period = 5;	// change this number would make the text run slower or faster
		    executor = Executors.newScheduledThreadPool(1);
		    executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
	    }
	}
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_bar_chart(JTable bc_table, Object[][] bc_data, int[] selectedRows) {		
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight single or multiple basic constraints to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted basic constraints";
			for (int selectedRow: selectedRows) {
				// Calculate value
				List<Double> FV = new ArrayList<Double>();
				for (int i = 0; i < total_iteration; i++) {
					FV.add(Double.valueOf(bc_data[selectedRow][i + 4].toString()));
				}
				// Put all into dataset		
				for (int i = 0; i < total_iteration; i++) {
					dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString(), "" + i);
				}
			}
		}
		
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createBarChart(chart_name, "iteration", "value",
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
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		plot.setOutlineVisible(false);
		return chart;
	}	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar1_chart(JTable bc_table, Object[][] bc_data, int[] selectedRows) {			
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight single or multiple basic constraints to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted basic constraints (stacked by basic constraints)";
			for (int selectedRow: selectedRows) {
				// Calculate value
				List<Double> FV = new ArrayList<Double>();
				for (int i = 0; i < total_iteration; i++) {
					FV.add(Double.valueOf(bc_data[selectedRow][i + 4].toString()));
				}
				// Put all into dataset		
				for (int i = 0; i < total_iteration; i++) {
					dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString(), "" + i);
				}
			}
		}
		
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createStackedBarChart(chart_name, "iteration", "value",
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
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		plot.setOutlineVisible(false);
		return chart;
	}	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart create_multiple_stacked_bar2_chart(JTable bc_table, Object[][] bc_data, int[] selectedRows) {			
		int total_iteration = (bc_data[0].length - 4) / 2;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight single or multiple basic constraints to view chart";
		if (selectedRows.length >= 1) {
			chart_name = "Comparison for highlighted basic constraints (stacked by iterations)";
			for (int selectedRow: selectedRows) {
				// Calculate value
				List<Double> FV = new ArrayList<Double>();
				for (int i = 0; i < total_iteration; i++) {
					FV.add(Double.valueOf(bc_data[selectedRow][i + 4].toString()));
				}
				// Put all into dataset		
				for (int i = 0; i < total_iteration; i++) {
					dataset.addValue(Double.valueOf(bc_data[selectedRow][i + 4].toString()), "iteration " + i, bc_data[selectedRow][0].toString()  + ". " + bc_data[selectedRow][1].toString());
				}
			}
		}
		
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createStackedBarChart(chart_name, "basic constraint", "value",
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
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		plot.setOutlineVisible(false);
		return chart;
	}	
}	

