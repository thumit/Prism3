package spectrumROOT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import spectrumConvenienceClasses.ColorUtil;
import spectrumConvenienceClasses.IconHandle;

public class MenuBar_Customize extends JMenuBar implements MouseListener, MouseMotionListener {
	private JButton buttonMinimize, buttonMaximize, buttonExit;
	private int pX,pY;
	
	
	public MenuBar_Customize() {
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
	}	
	
	public void addFrameFeatures() {	

		//Shift all below components to the right by some glue boxes
		add(Box.createGlue());
		JLabel title = new JLabel("SpectrumLite pre-alpha version 1.20");
//		title.setIcon(IconHandle.get_scaledImageIcon(100, 20, "spectrumlite1.png"));
		add(title);
		add(Box.createGlue());
		
		
		
		// this button will be shifted right on the menubar
		Action actionMinimize = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().minimize();
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
		    }
		});
		add(buttonMinimize);
		
		
		
		// this button will be shifted right on the menubar
		Action actionMaximize = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().restore();
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
		    }
		});
		add(buttonMaximize);
		
		
		
		// this button will be shifted right on the menubar
		Action actionExit = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().exitSpectrumLite();
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
		    }
		});
		add(buttonExit);
	
		//Add mouseListener to JMenuBar
		addMouseListener(this);
		addMouseMotionListener(this);
	}				
	
	
	//For mouse listener
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ColorUtil.makeTransparent(Color.WHITE, 125));
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (e.getClickCount() == 2) {
				Spectrum_Main.mainReturn().restore();
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
		if (Spectrum_Main.mainReturn().getExtendedState() != JFrame.NORMAL) {
			Spectrum_Main.mainReturn().setExtendedState(JFrame.NORMAL);		//Set normal Jframe size when start dragging
			//make the JFrame have the top center move to the cursor location)
			if (pX > Spectrum_Main.mainReturn().getWidth()/2) {
				pX =  Spectrum_Main.mainReturn().getWidth()/2;
			} 	
		} else {		
		Spectrum_Main.mainReturn().setLocation(Spectrum_Main.mainReturn().getLocation().x + event.getX() - pX, 
				Spectrum_Main.mainReturn().getLocation().y + event.getY() - pY);		//when dragged, move the frame
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		
	}
	

	
}
