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
package prism_root;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prism_convenience_class.IconHandle;


public class PrismContentPane extends JPanel {
	private Cursor c;
	private final Rotator rotator;
	private boolean is_rotating = true;
	private double angle = 0.5;
	private float horizon_of_point_one = 0.0f;
	private float horizon_of_point_two = 289.0f;
	private int delay_period = 30;	// change this number would make the spectrum light move slower or faster
	
	{
		rotator = new Rotator(this);
        rotator.start(); 
        
        addMouseListener(new MouseAdapter() { // Add listener to projectTree
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (e.getX() >= getWidth() - 150 && e.getY() >= getHeight() - 50) {		// if press mouse at the corner in the prism logo's area, then...
						if (c == null) {
							try {
								Toolkit toolkit = Toolkit.getDefaultToolkit();
								BufferedImage img = ImageIO.read(getClass().getResource("/prism_silver.png"));
								Image scaleImage = img.getScaledInstance(150, 40, Image.SCALE_SMOOTH);	// Re-scale buffered image
								c = toolkit.createCustomCursor(scaleImage, new Point(getX(), getY()), "new_cursor");
								
								
								ImageIcon icon = new ImageIcon(PrismMain.get_Prism_DesktopPane().getClass().getResource("/icon_target.png"));
								scaleImage = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);		// 32*32 is the smallest size for window cursor 
								c = toolkit.createCustomCursor(scaleImage, new Point(16, 16), "new_cursor");	// center of the 32*32 image
								PrismMain.get_main().getGlassPane().setCursor(c);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						
						if (PrismMain.get_main().getGlassPane().getCursor() != c) {
							PrismMain.get_main().getGlassPane().setCursor(c);
						} else {
							PrismMain.get_main().getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
						PrismMain.get_main().getGlassPane().setVisible(PrismMain.get_main().getGlassPane().getCursor() == c);
					}
					
					else 	// on other area --> control the painting of spectrum light
					{
						if (is_rotating) {
							stop_painting();
						} else {
							start_painting();
						}
					}
				} else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1 && is_rotating) { // Right mouse pressed, 1 click, and spectrum light is running
					JPopupMenu popup = new JPopupMenu();

					// All nodes can be refreshed ------------------------------------------------------------
					final JMenuItem speedMenuItem = new JMenuItem("change light speed");
					speedMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_main.png"));
					speedMenuItem.setMnemonic(KeyEvent.VK_R);
					speedMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							// Create a slider
							final JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, delay_period);
							slider.setSize(400, 15);
							slider.setMajorTickSpacing(10);
							slider.setMinorTickSpacing(1);
							slider.setPaintTicks(true);

							// Create a scrollBar & add listeners
							final JScrollBar scrollBar = new JScrollBar();
							
							scrollBar.addAdjustmentListener(new AdjustmentListener() {
								public void adjustmentValueChanged(AdjustmentEvent e) {
									delay_period = slider.getValue();
								}
							});

							slider.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent e) {
									delay_period = slider.getValue();
									rotator.restart();
								}
							});
							
							// Add slider to sliderPanel
							JPanel sliderPanel = new JPanel(new BorderLayout());
							sliderPanel.setPreferredSize(new Dimension(400, 30));
							sliderPanel.add(slider);

							// Add sliderPanel to a Popup Panel
							String ExitOption[] = { "OK"};
							int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), sliderPanel,
									"Spectrum light speed", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
									IconHandle.get_scaledImageIcon(40, 40, "icon_main.png"), ExitOption, ExitOption[0]);
							if (response == 0) {
								
							}
						}
					});
					popup.add(speedMenuItem);	
					// Show the JmenuItems on selected node when it is right clicked
					popup.show(PrismMain.get_prism_ContentPane(), e.getX(), e.getY());
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			final int R = 0;
			final int G = 0;
			final int B = 0;
//			Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0.3f), 0.0f, getHeight(), new Color(0, 130, 180, 150), true);
//			Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 130, 180, 200), 0.0f, getHeight(), new Color(R, G, B, 0.9f), true);
//			Paint p = new GradientPaint(0.0f, getHeight() / 3, new Color(R, G, B, 0.05f), getHeight() / 3, (float) (getHeight() / angle), new Color(0, (int) (5 * angle + 100), 180, (int) (5 * angle + 100)), true);
//			Paint p = new GradientPaint(horizon_of_point_one, getHeight() / 3, new Color(R, G, B, 0.05f), horizon_of_point_two, (float) (getHeight() / angle), new Color(0, (int) (5 * angle + 100), 180, (int) (5 * angle + 100)), true);
			Paint p = new GradientPaint(horizon_of_point_one, getHeight() / 3, new Color(R, G, B, 0.05f), horizon_of_point_two, (float) (getHeight() / angle), 
					new Color((int) (0.1 * angle + 40), (int) (4.5 * angle + 80), (int) (180), (int) (5 * angle + 80)), true);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(p);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			
//			// These 2 lines could help not constantly revalidate and repaint but cause a continuous process --> affect other process
//			// Adding these two line could help: when we move the JinternalFrame in the main screen, there would be no issue of painting
//			// However, due to the lag, those 2 lines would be moved to the component listeners of the JinternalFrame (including Project & Database Management)
//			PrismMain.get_main().revalidate();	// very important to make the background not show lagging from previous paint
//	    	PrismMain.get_main().repaint();		// very important to make the background not show lagging from previous paint
		}
	}
        
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(), this.getHeight());
	}

	public void set_angle(double new_angle) {
		this.angle = new_angle;
	}

	public double get_angle() {
		return this.angle;
	}
	
	public void set_random_paint() {
		horizon_of_point_one = new Random().nextInt(600) + 1;
		horizon_of_point_two = 600 - new Random().nextInt(600);
	}
	
	public void start_painting() {
		rotator.start();
		is_rotating = true;
	}
	
	public void stop_painting() {
		rotator.stop();
		is_rotating = false;
//		angle = 13;		// activate these codes if you want a good display when Rotator stops
//		revalidate();
//      repaint();
	}
	
	
	private class Rotator {
		private Color last_use_main_background = PrismMain.get_main().getBackground();
		private ScheduledExecutorService executor;
		private Runnable task;
		private double angle;
		private double demo_time = 0;

		Rotator(final PrismContentPane panel) {
			this.angle = panel.get_angle();
			
			task = new Runnable() {
				public void run() {
					if (angle <= 10) {
						angle = angle + 0.5;
					} else {
						angle = angle + 0.03;
					}

					if (angle > 30) {
						angle = 0.5;
						panel.set_random_paint();
					}
					panel.set_angle(angle);
					panel.revalidate();
					panel.repaint();

					demo_time = demo_time + 1;
//			        if (demo_time == 600) stop();   // each 200 units of 50 millisoconds = 10 seconds			// Activate this line if we want the rotator stops after certain time
//			        System.out.println(angle + " " + demo_time);
				}
			};
		}

	    public void stop() {
	    	PrismMain.get_main().setBackground(last_use_main_background);
	    	PrismMain.get_prism_Menubar().set_bright_background_color();
	    	
	    	executor.shutdown(); // shutdown will allow the final iteration to finish executing where shutdownNow() will kill it immediately
	    }
	    
	    public void start() {
	    	if (PrismMain.get_Prism_DesktopPane().getAllFrames().length == 0) { // show spectrum light only when all internal frames are closed
	    		float min = 0.0f;
		    	float max = 0.5f;
		    	float random = min + new Random().nextFloat() * (max - min);
		    	PrismMain.get_main().setBackground(new Color(0, 0, 0, random));
		    	PrismMain.get_prism_Menubar().set_dark_background_color();
		    	 
		    	if (executor != null) executor.shutdown(); // shutdown will allow the final iteration to finish executing where shutdownNow() will kill it immediately
		    	int initialDelay = 0;
			    executor = Executors.newScheduledThreadPool(1);
			    executor.scheduleAtFixedRate(task, initialDelay, delay_period, TimeUnit.MILLISECONDS);
			}
	    }
	    
	    public void restart() {
	    	if (executor != null) executor.shutdown(); // shutdown will allow the final iteration to finish executing where shutdownNow() will kill it immediately
	    	int initialDelay = 0;
		    executor = Executors.newScheduledThreadPool(1);
		    executor.scheduleAtFixedRate(task, initialDelay, delay_period, TimeUnit.MILLISECONDS);
	    }
	}
}