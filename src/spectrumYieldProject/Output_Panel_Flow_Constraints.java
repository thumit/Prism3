package spectrumYieldProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

// Panel_Flow_Constraints--------------------------------------------------------------------------------	
class Output_Panel_Flow_Constraints extends JLayeredPane {
	public Output_Panel_Flow_Constraints(JTable table, Object[][] data) {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
	    c.weighty = 1;
	    
	    //---------------------------------------------------------------
        JScrollPane scroll_bar_chart = new JScrollPane();
        scroll_bar_chart.setBorder(null);
        
        //---------------------------------------------------------------
        JScrollPane scroll_line_chart = new JScrollPane();
        scroll_line_chart.setBorder(null);
        
        //---------------------------------------------------------------
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				// Create a chart						
				JFreeChart chart1 = createBarChart3D(table, data);	 	        
	 	        
				// add the chart to a panel...
	         	ChartPanel chart_panel1 = new ChartPanel(chart1);
	 	        chart1.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	 	        TitledBorder border1 = new TitledBorder("");
	 			border1.setTitleJustification(TitledBorder.CENTER);
	 			chart_panel1.setBorder(border1);
	 	        chart_panel1.setPreferredSize(new Dimension(600, 350));

	 	        // Add panel to scroll panel
	 	       scroll_bar_chart.setViewportView(chart_panel1);
        	}       
        });
        
        // Trigger the value changed listener of the table
        table.setRowSelectionInterval(0, 0);
        table.clearSelection();
        //---------------------------------------------------------------
        
        
	    // Add the 1st grid - bar chart
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 0;
	    c.weighty = 0;
		super.add(scroll_bar_chart, c);
		
	    // Add the 2nd grid - line chart
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		super.add(scroll_line_chart, c);			
		
		// Add empty label
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 0;
		super.add(new JLabel(), c);							
					
		// Add the 3rd grid - table
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 0;
	    c.weighty = 1;
		table.setPreferredScrollableViewportSize(new Dimension(0, 0));			
		JScrollPane table_scroll_panel = new JScrollPane(table);
		table_scroll_panel.setBorder(BorderFactory.createEmptyBorder());	// Hide the border line surrounded scrollPane
		table_scroll_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		super.add(table_scroll_panel, c);
	}


	@SuppressWarnings("deprecation")
	private JFreeChart createBarChart3D(JTable this_table, Object[][] this_data) {			
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		String chart_name = "Highlight a flow to view chart";
		if (this_table.getSelectedRow() >= 0) {
			// Get the current selected row
        	int selectedRow = this_table.getSelectedRow();
			selectedRow = this_table.convertRowIndexToModel(selectedRow);		///Convert row index because "Sort" causes problems	
			chart_name = this_data[selectedRow][1].toString() + " - " + this_data[selectedRow][3].toString();
							
			// Read flow_arrangement
			String[] flow_arrangement_info = this_data[selectedRow][2].toString().split(";");	// Read the whole cell 'flow_arrangement'
			List<String> flow_arrangement = new ArrayList<String>();
			for (int i = 0; i < flow_arrangement_info.length; i++) {
				flow_arrangement.add(flow_arrangement_info[i]);
			}
			
			// Read flow_output_original
			String[] flow_output_original_info = this_data[selectedRow][6].toString().split(";");	// Read the whole cell 'flow_output_original'
			List<Double> flow_output_original = new ArrayList<Double>();
			for (int i = 0; i < flow_output_original_info.length; i++) {
				flow_output_original.add(Double.parseDouble(flow_output_original_info[i]));
			}				
							
			// Put all into dataset				
			for (int i = 0; i < flow_arrangement_info.length; i++) {
//				dataset.addValue(flow_output_original.get(i), "Original", flow_arrangement.get(i));
//				dataset.addValue(flow_output_relaxed.get(i), "Relaxed", flow_arrangement.get(i));
				dataset.addValue(flow_output_original.get(i), "Original", flow_arrangement.get(i).replaceAll("\\s+", "+"));
				if (!this_data[selectedRow][4].toString().equals("null")) {
					double lb_value = Double.parseDouble(this_data[selectedRow][4].toString()) * flow_output_original.get(i) / 100;	
					dataset.addValue(lb_value, "LB: " + this_data[selectedRow][4].toString() + "% of Original", flow_arrangement.get(i).replaceAll("\\s+", "+"));						
				}
				if (!this_data[selectedRow][5].toString().equals("null")) {
					double ub_value = Double.parseDouble(this_data[selectedRow][5].toString()) * flow_output_original.get(i) / 100;	
					dataset.addValue(ub_value, "UB: " + this_data[selectedRow][5].toString() + "% of Original", flow_arrangement.get(i).replaceAll("\\s+", "+"));						
				}
			}
		}
					
		// Create 3D bar chart
		JFreeChart chart = ChartFactory.createBarChart(chart_name, "Flow Arrangement: labeled by IDs of basic constraints: bc_id", "Flow Value",
				dataset, PlotOrientation.VERTICAL, true, true, false);		
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		chart.getLegend().setBackgroundPaint(null);
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
		chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
		chart.getTitle().setFont(new java.awt.Font("defaultFont", java.awt.Font.BOLD, 14));
				
		// Set color for each different bar
		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		Color color = null;
		GradientPaint gp = null;
		for (int i = 0; i < dataset.getRowCount(); i++){
		    switch (i) {
		    case 0:
		        color = new Color(255, 0, 0);
		        gp = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, new Color(64, 0, 0));  
		        break;
		    case 1:
		        color = new Color(0, 255, 0);
		        gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
		        break;
		    default:
		        color = new Color(255, 255, 51);
		        gp = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, new Color(0, 0, 64));
		        break;
		    }
		    renderer.setSeriesPaint(i, gp);		// use gradient and 2D is better than color and 3D
		    renderer.setItemMargin(0.08);			    
			renderer.setItemLabelGenerator(
					new StandardCategoryItemLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0.00 acres"), new DecimalFormat("0.0%")));
//			renderer.setBaseItemLabelsVisible(true);
			renderer.setDrawBarOutline(false);
			
		}	
		plot.setOutlineVisible(false);
								
		return chart;
	}			
}	

