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

package prism_root;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;

import prism_convenience.ColorUtil;
import prism_convenience.IconHandle;
import prism_convenience.PrismMenu;

public class PrismMenuBar extends JMenuBar implements MouseListener, MouseMotionListener {
	private Color background_color = ColorUtil.makeTransparent(Color.WHITE, 220);
	private JButton buttonMinimize, buttonMaximize, buttonExit;
	private int pX,pY;
	
	
	public PrismMenuBar() {
		ToolTipManager.sharedInstance().setInitialDelay(0);		// Show toolTip immediately
	}	
	
	public void addFrameFeatures() {	
		add(Box.createGlue());		// shift components to the right by using glue
		JLabel title = new JLabel(Prism3Main.get_prism_version());
		add(title);
		add(Box.createGlue());
		
		
		
		// this button will be shifted right on the menubar
		Action actionMinimize = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Prism3Main.get_main().minimize();
			}
		};
		buttonMinimize = new JButton(actionMinimize);
		buttonMinimize.setToolTipText("Minimize");
		buttonMinimize.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_minimize.png"));
		buttonMinimize.setContentAreaFilled(false);
		buttonMinimize.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	buttonMinimize.setContentAreaFilled(true);
		    }

		    public void mouseExited(MouseEvent e) {
		    	buttonMinimize.setContentAreaFilled(false);
		    	if (ToolTipManager.sharedInstance().isEnabled()) {	// to avoid the case when tool-tip does not disappear immediately when gradually moving down from these buttons
		    		ToolTipManager.sharedInstance().setEnabled(false);
			    	ToolTipManager.sharedInstance().setEnabled(true);
		    	}
		    }
		});
		add(buttonMinimize);
		
		
		
		// this button will be shifted right on the menubar
		Action actionMaximize = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Prism3Main.get_main().restore();
				if (buttonMaximize.getToolTipText().equals("Maximize")) {
					buttonMaximize.setToolTipText("Restore");
				} else {
					buttonMaximize.setToolTipText("Maximize");
				}
			}
		};
		buttonMaximize = new JButton(actionMaximize);
		buttonMaximize.setToolTipText("Maximize");
		buttonMaximize.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_maximize.png"));
		buttonMaximize.setContentAreaFilled(false);
		buttonMaximize.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	buttonMaximize.setContentAreaFilled(true);
		    }

		    public void mouseExited(MouseEvent e) {
		    	buttonMaximize.setContentAreaFilled(false);
		    	if (ToolTipManager.sharedInstance().isEnabled()) {	// to avoid the case when tool-tip does not disappear immediately when gradually moving down from these buttons
		    		ToolTipManager.sharedInstance().setEnabled(false);
			    	ToolTipManager.sharedInstance().setEnabled(true);
		    	}
		    }
		});
		add(buttonMaximize);
		
		
		
		// this button will be shifted right on the menubar
		Action actionExit = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Prism3Main.get_main().exitPRISM();
			}
		};
		buttonExit = new JButton(actionExit);
		buttonExit.setToolTipText("Exit");
		buttonExit.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_erase.png"));
		buttonExit.setContentAreaFilled(false);
		buttonExit.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	buttonExit.setContentAreaFilled(true);
		    }

		    public void mouseExited(MouseEvent e) {
		    	buttonExit.setContentAreaFilled(false);
		    	if (ToolTipManager.sharedInstance().isEnabled()) {	// to avoid the case when tool-tip does not disappear immediately when gradually moving down from these buttons
		    		ToolTipManager.sharedInstance().setEnabled(false);
			    	ToolTipManager.sharedInstance().setEnabled(true);
		    	}
		    }
		});
		add(buttonExit);
	
		//Add mouseListener to JMenuBar
		addMouseListener(this);
		addMouseMotionListener(this);
	}				
	
	
	public void set_dark_background_color() {	
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, ColorUtil.makeTransparent(Color.WHITE, 125));
		setBorder(border);
		background_color = ColorUtil.makeTransparent(Color.BLACK, 220);
		
		
		Color back_color = ColorUtil.makeTransparent(Color.BLACK, 220);
		Color fore_color = ColorUtil.makeTransparent(Color.WHITE, 240);
		Component[] comps = getComponents();
		for (Component c : comps) {
			if (c instanceof JComponent) {
				c.setBackground(back_color);
				c.setForeground(fore_color);
			}
		}
		
		// For all menus and menu items
		for (int i = 0; i < Prism3Main.get_prism_Menubar().getMenuCount(); i++) {
			JMenu menu = Prism3Main.get_prism_Menubar().getMenu(i);
			if (menu != null) {
				menu.setBackground(back_color);
				menu.setForeground(fore_color);
				if (menu instanceof PrismMenu) ((PrismMenu) menu).set_dark_border();

				for (int j = 0; j < menu.getMenuComponentCount(); j++) {
					java.awt.Component comp = menu.getMenuComponent(j);
					if (comp instanceof JMenuItem) {
						JMenuItem menuItem = (JMenuItem) comp;
						if (menuItem != null) {
							menuItem.setOpaque(true);		// This is important to make the model runs menu item in dark background. There is a complex logic of opacity here
							menuItem.setBackground(back_color);
							menuItem.setForeground(fore_color);
						}
					}

					else if (comp instanceof JMenu) {
						for (Component c : ((JMenu) comp).getMenuComponents()) {
							c.setBackground(back_color);
							c.setForeground(fore_color);
						}
						if (menu instanceof PrismMenu) ((PrismMenu) menu).set_dark_border();
					}
				}
			}
		}
	}
	
	public void set_bright_background_color() {	
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, ColorUtil.makeTransparent(Color.BLACK, 125));
		setBorder(border);
		background_color = ColorUtil.makeTransparent(Color.WHITE, 220);
		
		
		Color back_color = ColorUtil.makeTransparent(Color.WHITE, 220);
		Color fore_color = ColorUtil.makeTransparent(Color.BLACK, 255);
		Component[] comps = getComponents();
		for (Component c : comps) {
			if (c instanceof JComponent) {
				c.setBackground(back_color);
				c.setForeground(fore_color);
			}
		}
		
		// For all menus and menu items
		for (int i = 0; i < Prism3Main.get_prism_Menubar().getMenuCount(); i++) {
			JMenu menu = Prism3Main.get_prism_Menubar().getMenu(i);
			if (menu != null) {
				menu.setBackground(back_color);
				menu.setForeground(fore_color);
				if (menu instanceof PrismMenu) ((PrismMenu) menu).set_bright_border();

				for (int j = 0; j < menu.getMenuComponentCount(); j++) {
					java.awt.Component comp = menu.getMenuComponent(j);
					if (comp instanceof JMenuItem) {
						JMenuItem menuItem = (JMenuItem) comp;
						if (menuItem != null) {
							menuItem.setOpaque(false);		// This is important to make the model runs not show the blur letter in nearby location. There is a complex logic of opacity here
							menuItem.setBackground(back_color);
							menuItem.setForeground(fore_color);
						}
					}

					else if (comp instanceof JMenu) {
						for (Component c : ((JMenu) comp).getMenuComponents()) {
							c.setBackground(back_color);
							c.setForeground(fore_color);
						}
						if (menu instanceof PrismMenu) ((PrismMenu) menu).set_bright_border();
					}
				}
			}
		}
	}
	
	
	//For mouse listener
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		
		// All kind of testing anti-alias fail
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(background_color);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		

		
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(ColorUtil.makeTransparent(Color.WHITE, 255));
//		g2d.fillRect(0, 0, getWidth(), getHeight());
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
//		g2d.setComposite(c);
//		super.paintComponent(g2d);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (e.getClickCount() == 2) {
				Prism3Main.get_main().restore();
				if (buttonMaximize.getToolTipText().equals("Maximize")) {
					buttonMaximize.setToolTipText("Restore");
				} else {
					buttonMaximize.setToolTipText("Maximize");
				}
			} 
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Get x,y and store them, this would help to detect mouse drag
		pX = e.getX();
		pY = e.getY();
	}
	
	
	//For mouse motion listener
	@Override
	public void mouseDragged(MouseEvent event) {
		if (Prism3Main.get_main().getExtendedState() != JFrame.NORMAL) {
			Prism3Main.get_main().setExtendedState(JFrame.NORMAL);		//Set normal Jframe size when start dragging
			//make the JFrame have the top center move to the cursor location)
			if (pX > Prism3Main.get_main().getWidth()/2) {
				pX =  Prism3Main.get_main().getWidth()/2;
			} 	
		} else {		
		Prism3Main.get_main().setLocation(Prism3Main.get_main().getLocation().x + event.getX() - pX, 
				Prism3Main.get_main().getLocation().y + event.getY() - pY);		//when dragged, move the frame
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	

	
}
