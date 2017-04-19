package spectrumYieldProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.Rotation;
	
class Output_Panel_Management_Overview extends JLayeredPane {
	private int rowCount;
	private JTable table;
	private Object[][] data;	
	
	public Output_Panel_Management_Overview(JTable table, Object[][] data) {
		this.table = table;
		this.data = data;
		this.rowCount = table.getRowCount();
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
	    c.weighty = 1;
	    
	    //---------------------------------------------------------------
	    //Create a chart
	    PieDataset dataset = create_all_strata_dataset();
        JFreeChart chart = createChart(dataset, "Management decisions at the start of planning horizon for " + rowCount + " existing strata");
        chart.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend	        

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);        
        TitledBorder border = new TitledBorder("");
		border.setTitleJustification(TitledBorder.CENTER);
		chartPanel.setBorder(border);
        chartPanel.setPreferredSize(new Dimension(600, 350));
        
    	// Rotation effect
        final Rotator rotator = new Rotator((PiePlot3D) chart.getPlot());
        rotator.start();           
        chartPanel.addMouseListener(new MouseAdapter() { // Add listener to projectTree
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
    
        // Add panel to scroll panel
        JScrollPane scroll_chart1 = new JScrollPane();
        scroll_chart1.setBorder(null);	      
		scroll_chart1.setViewportView(chartPanel);
					
        //---------------------------------------------------------------
        JScrollPane scroll_chart2 = new JScrollPane();
        scroll_chart2.setBorder(null);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	String strataName = "";
	 	        if (table.getSelectedRow() >= 0) 	strataName = data[table.getSelectedRow()][0].toString();
	 	        
	 	        //Create a chart
	 		    PieDataset dataset2 = create_selected_strata_dataset();
	 	        JFreeChart chart2 = createChart(dataset2, "Management decisions at the start of planning horizon for '" + strataName + "' ");
	 	        if (table.getSelectedRows().length > 1) {	//Change chart title if multiple strata are selected
	 				chart2.setTitle("Management decisions at the start of planning horizon for "  + table.getSelectedRows().length + " existing strata");
	 			}	

	 	        // add the chart to a panel...
	         	ChartPanel chartPanel2 = new ChartPanel(chart2);
	 	        chart2.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	 	        TitledBorder border2 = new TitledBorder("");
	 			border2.setTitleJustification(TitledBorder.CENTER);
	 			chartPanel2.setBorder(border2);
	 	        chartPanel2.setPreferredSize(new Dimension(600, 350));
	 	        
	 	        // Rotation effect 
	 	        final Rotator rotator = new Rotator((PiePlot3D) chart2.getPlot()); 	 	     
	 	        if (dataset2 != null) {
			        chartPanel2.addMouseListener(new MouseAdapter() { // Add listener to projectTree
						boolean is_rotating = false;
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

	 	        // Add panel to scroll panel
				scroll_chart2.setViewportView(chartPanel2);
	        }
        });
        
        // Trigger the value changed listener of the table
        table.setRowSelectionInterval(0, 0);
        table.clearSelection();
        //---------------------------------------------------------------
        
        
	    // Add the 1st grid - chartPanel for all Strata
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 0;
	    c.weighty = 0;
		super.add(scroll_chart1, c);
		
	    // Add the 2nd grid - chartPanel for the selected Strata
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		super.add(scroll_chart2, c);
		
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
		c.gridwidth =3;
		c.weightx = 1;
	    c.weighty = 1;			
		table.setPreferredScrollableViewportSize(new Dimension(0, 0));		// 1216	
		JScrollPane table_scroll_panel = new JScrollPane(table);
		table_scroll_panel.setBorder(BorderFactory.createEmptyBorder());	//Hide the border line surrounded scrollPane
		table_scroll_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		super.add(table_scroll_panel, c);
	}

			
	private PieDataset create_all_strata_dataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		double total_NG = 0;
		double total_PB = 0;
		double total_GS = 0;
		double total_EA = 0;
		double total_MS = 0;
		for (int i = 0; i < data.length; i++) { // Loop table rows
			total_NG = total_NG + Double.parseDouble(data[i][7].toString());
			total_PB = total_PB + Double.parseDouble(data[i][8].toString());
			total_GS = total_GS + Double.parseDouble(data[i][9].toString());
			total_EA = total_EA + Double.parseDouble(data[i][10].toString());
			total_MS = total_MS + Double.parseDouble(data[i][11].toString());
		}
	
		dataset.setValue("Natural Growth", total_NG);
		dataset.setValue("Prescribed Burn", total_PB);
		dataset.setValue("Group Selection", total_GS);
		dataset.setValue("Even Age", total_EA);
		dataset.setValue("Mixed Severity Wildfire", total_MS);
		
		return dataset;
	}

	private PieDataset create_selected_strata_dataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		if (table.getSelectedRow() >= 0) {
			double total_NG = 0;
			double total_PB = 0;
			double total_GS = 0;
			double total_EA = 0;
			double total_MS = 0;
				
			int[] selectedRow = table.getSelectedRows();	
			for (int i = 0; i < selectedRow.length; i++) {
				selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
			}
			
			for (int i: selectedRow) {
				total_NG = total_NG + Double.parseDouble(data[i][7].toString());
				total_PB = total_PB + Double.parseDouble(data[i][8].toString());
				total_GS = total_GS + Double.parseDouble(data[i][9].toString());
				total_EA = total_EA + Double.parseDouble(data[i][10].toString());
				total_MS = total_MS + Double.parseDouble(data[i][11].toString());
			}					
		
			dataset.setValue("Natural Growth", total_NG);
			dataset.setValue("Prescribed Burn", total_PB);
			dataset.setValue("Group Selection", total_GS);
			dataset.setValue("Even Age", total_EA);
			dataset.setValue("Mixed Severity Wildfire", total_MS);
		} else {
			dataset = null;
		}

		return dataset;
	}		
	
	
	@SuppressWarnings("deprecation")
	private JFreeChart createChart(PieDataset dataset, String chartName) {
		JFreeChart chart = ChartFactory.createPieChart3D(chartName, // chart title
				dataset, // dataset
				true, // include legend
				true, false);		
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
		plot.setNoDataMessage("Highlight single or multiple existing strata to view chart");
		plot.setExplodePercent(1, 0.1);
		
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	            "{0}: {1} ({2})", new DecimalFormat("0.00 acres"), new DecimalFormat("0.0%"));			// "{0}: {1} ({2})"
	    plot.setLabelGenerator(gen);	    
	    plot.setLabelBackgroundPaint(null);
	    plot.setLabelShadowPaint(null);
	    plot.setLabelOutlinePaint(null);
	    plot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
	    
	    // Customize colors
	    plot.setSectionPaint("Natural Growth", new Color(0, 255, 0));
		plot.setSectionPaint("Prescribed Burn", new Color(255, 255, 0));
		plot.setSectionPaint("Group Selection", new Color(240, 248, 255));
	    plot.setSectionPaint("Even Age", new Color(51, 255, 255));
	    plot.setSectionPaint("Mixed Severity Wildfire", new Color(255, 51, 0));
	    		    
//	    plot.setLabelLinksVisible(false);
//		plot.setLabelGenerator(null);
//		plot.setSimpleLabels(true);
		return chart;
	}	
	
	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE                                               *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available   *
	// * to purchase from Object Refinery Limited:                                *
	// *                                                                          *
	// * http://www.object-refinery.com/jfreechart/guide.html                     *
	// *                                                                          *
	// * Sales are used to provide funding for the JFreeChart project - please    * 
	// * support us so that we can continue developing free software.             *
	// ****************************************************************************
	// The rotator.

	private class Rotator extends Timer implements ActionListener {

	    /** The plot. */
	    private PiePlot3D plot;

	    /** The angle. */
	    private double angle = 135;

	    /**
	     * Constructor.
	     *
	     * @param plot  the plot.
	     */
	    Rotator(final PiePlot3D plot) {
	        super(15, null);
	        this.plot = plot;
	        addActionListener(this);
	    }

	    /**
	     * Modifies the starting angle.
	     *
	     * @param event  the action event.
	     */
	    public void actionPerformed(final ActionEvent event) {
	        this.plot.setStartAngle(this.angle);
	        this.angle = this.angle + (double) 0.1;
	        if (this.angle == 360) {
	            this.angle = 0;
	        }
	    }

	}
}	

