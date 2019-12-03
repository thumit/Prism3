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
import java.awt.GradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

import javax.swing.JLayeredPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
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

public class Chart extends JLayeredPane {

	public Chart() {
	}
	
	@SuppressWarnings("deprecation")
	public JFreeChart create_single_bar_chart(String chart_name, String horizontal_axis, String vertical_axis, DefaultCategoryDataset dataset) {	
		// Create 3D bar chart------------------------
		JFreeChart chart = ChartFactory.createBarChart(chart_name, horizontal_axis, vertical_axis,
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
	public JFreeChart create_single_pie_chart(String chart_name, String legend_text, DefaultPieDataset dataset) {			
		// Create 3D pie chart-----------------------------------
		JFreeChart chart = ChartFactory.createPieChart3D(chart_name, // chart title
				dataset, // dataset
				true, // include legend
				true, false);		
		
		// 3 lines to create another legend
		TextTitle legend_title = new TextTitle(legend_text);
		legend_title.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legend_title);
		
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
	
	
	@SuppressWarnings("deprecation")
	public JFreeChart create_multiple_bar_chart(String chart_name, String horizontal_axis, String vertical_axis, DefaultCategoryDataset dataset) {		
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createBarChart(chart_name, horizontal_axis, vertical_axis,
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
	public JFreeChart create_multiple_stacked_bar1_chart(String chart_name, String horizontal_axis, String vertical_axis, DefaultCategoryDataset dataset) {			
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createStackedBarChart(chart_name, horizontal_axis, vertical_axis,
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
	public JFreeChart create_multiple_stacked_bar2_chart(String chart_name, String horizontal_axis, String vertical_axis, DefaultCategoryDataset dataset) {			
		// Create 3D bar chart--------------------------------------------------------------------------------------------------
		JFreeChart chart = ChartFactory.createStackedBarChart(chart_name, horizontal_axis, vertical_axis,
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

