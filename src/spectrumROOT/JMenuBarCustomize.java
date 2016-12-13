package spectrumROOT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import spectrumConvenienceClasses.ColorUtil;

public class JMenuBarCustomize extends JMenuBar implements MouseListener {
	private ImageIcon icon;
	private Image scaleImage;
	private JButton buttonMinimize, buttonMaximize, buttonExit;
	
	
	public JMenuBarCustomize() {
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately
	}	
	
	public void addFrameFeatures() {	

		//Shift all below components to the right by some glue boxes
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(new JLabel("SpectrumLite Demo Version 1.10"));
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
//		add(Box.createHorizontalGlue());
		
		// this button will be shifted right on the menubar
		Action actionMinimize = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().minimize();
			}
		};
		buttonMinimize = new JButton(actionMinimize);
		buttonMinimize.setToolTipText("Minimize");
		icon = new ImageIcon(getClass().getResource("/icon_minimize.png"));
		scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
		buttonMinimize.setIcon(new ImageIcon(scaleImage));
//		buttonMinimize.setContentAreaFilled(false);
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
		icon = new ImageIcon(getClass().getResource("/icon_maximize.png"));
		scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
		buttonMaximize.setIcon(new ImageIcon(scaleImage));
//		buttonMaximize.setContentAreaFilled(false);
		add(buttonMaximize);
		
		
		
		// this button will be shifted right on the menubar
		Action actionExit = new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().exitSpectrumLite();
			}
		};
		buttonExit = new JButton(actionExit);
		buttonExit.setToolTipText("Exit");
		icon = new ImageIcon(getClass().getResource("/icon_exit.png"));
		scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
		buttonExit.setIcon(new ImageIcon(scaleImage));
//		buttonExit.setContentAreaFilled(false);
		add(buttonExit);
	
		//Add mouseListener to JMenuBar
		addMouseListener(this);
	}				
	
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
	
	}
	
	
}
