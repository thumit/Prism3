package spectrumConvenienceClasses;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import spectrumROOT.Spectrum_Main;

public class JMenuBarCustomize extends JMenuBar {
	
	public JMenuBarCustomize() {

	}	
	
	public void addFrameFeatures() {
		JPanel jmenuBarPanel = new JPanel ();
		jmenuBarPanel.setLayout(new FlowLayout(0));


		//Shift all below components to the right by some glue boxes
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		add(Box.createGlue());
		jmenuBarPanel.add(new JLabel("SpectrumLite Demo Version 1.10"));
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
		Action actionMinimize = new AbstractAction("Minimize") {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().minimize();
			}
		};
		jmenuBarPanel.add(new JButton(actionMinimize));
		
		
		// this button will be shifted right on the menubar
		Action actionMaximize = new AbstractAction("Maximize") {
			public void actionPerformed(ActionEvent evt) {			
				Spectrum_Main.mainReturn().restore();
			}
		};
		jmenuBarPanel.add(new JButton(actionMaximize));
		
		
		// this button will be shifted right on the menubar
		Action actionQuit = new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent evt) {
				Spectrum_Main.mainReturn().exitSpectrumLite();
			}
		};
		jmenuBarPanel.add(new JButton(actionQuit));
	
		//Add panel to JMenuBar
		add(jmenuBarPanel, BorderLayout.EAST);
	}
}
