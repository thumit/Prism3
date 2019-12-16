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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLayeredPane;

import org.jfree.chart.plot.PiePlot3D;

public class Chart_Rotator extends JLayeredPane {		// I am so smart to not use timer
	private ScheduledExecutorService executor;
	private Runnable task;
	private double angle = 135;

	public Chart_Rotator(final PiePlot3D plot) {
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

//// ****************************************************************************
//// * JFREECHART DEVELOPER GUIDE                                               *
//// * The JFreeChart Developer Guide, written by David Gilbert, is available   *
//// * to purchase from Object Refinery Limited:                                *
//// *                                                                          *
//// * http://www.object-refinery.com/jfreechart/guide.html                     *
//// *                                                                          *
//// * Sales are used to provide funding for the JFreeChart project - please    * 
//// * support us so that we can continue developing free software.             *
//// ****************************************************************************
//// The rotator.
//private class Rotator extends Timer implements ActionListener {
//
//    /** The plot. */
//    private PiePlot3D plot;
//
//    /** The angle. */
//    private double angle = 135;
//
//    /**
//     * Constructor.
//     *
//     * @param plot  the plot.
//     */
//    Rotator(final PiePlot3D plot) {
//        super(15, null);
//        this.plot = plot;
//        addActionListener(this);
//    }
//
//    /**
//     * Modifies the starting angle.
//     *
//     * @param event  the action event.
//     */
//    public void actionPerformed(final ActionEvent event) {
//        this.plot.setStartAngle(this.angle);
//        this.angle = this.angle + (double) 0.1;
//        if (this.angle == 360) {
//            this.angle = 0;
//        }
//    }
//
//}	