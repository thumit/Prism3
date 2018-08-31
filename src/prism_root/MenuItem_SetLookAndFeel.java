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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import prism_convenience.ComponentResizer;
import prism_convenience.IconHandle;
import prism_convenience.WindowAppearanceHandle;

public class MenuItem_SetLookAndFeel extends JMenuItem {
	private static boolean is_Nimbus_Without_titleBar = true;		//Set this to false if in the void main(String[] args), we do the following:
																	//1: activate this line			setDefaultLookAndFeelDecorated(true);
																	//2: deactivate this line		main.setUndecorated(true);
																	//Doing those 2 things will make Nimbus has the title when JFrame main 1st start (also make: Metal LAF has title)	
																	//Reson to set true or false is to change the component resize behavior, see Dzung's code
	
	public MenuItem_SetLookAndFeel(PrismMain main, ComponentResizer cr) {
		setText("Change Look and Feel");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_laf.png"));
		
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.CTRL_DOWN_MASK, true));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JRadioButton[] radioButton = new JRadioButton[UIManager.getInstalledLookAndFeels().length];
				
				try {
					int i = 0;
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						radioButton[i] = new JRadioButton(info.getName());
						i++;
					}
				} catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
			
				
				// Create a radioGroup buttons
				ButtonGroup radioGroup = new ButtonGroup();
				JPanel radioPanel = new JPanel();
				radioPanel.setPreferredSize(new Dimension(400, 30));
				radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));

				for (int i = 0; i < radioButton.length; i++) {
					radioGroup.add(radioButton[i]);
					radioPanel.add(radioButton[i]);
					
					if (UIManager.getLookAndFeel().getName().equals(radioButton[i].getText())) {
						radioButton[i].setSelected(true);		//Select the radioButton of the current Look and Feel
					}
					
					int selectedLF = i;
					radioButton[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
						
								
							for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
								if (info.getName().equals(radioButton[selectedLF].getText())) {
									try {

										if (UIManager.getLookAndFeel().getName().equals("Nimbus"))  {
											is_Nimbus_Without_titleBar = true;
										}
										
										// Get Font of the old Look and Feel
										String old_font_name = UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName();
										int old_font_size = UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getSize();
										
										// Change Look and Feel
										UIManager.setLookAndFeel(info.getClassName());	
										
										// Change Font to the Font of the old Look and Feel
										UIManager.getLookAndFeelDefaults().put("info", new Color(255, 250, 205));		// Change the ugly yellow color of ToolTip --> lemon chiffon
										UIManager.getLookAndFeelDefaults().put("defaultFont", new FontUIResource(new Font(old_font_name, Font.PLAIN, old_font_size)));	// Since the update to eclipse Oxygen and update to java9, 
																																										// this line is required to make it not fail when click File --> Open after changing Look and Feel in Eclise IDE
										WindowAppearanceHandle.setUIFont(new FontUIResource(new Font(old_font_name, Font.PLAIN, old_font_size)));
										
										cr.deregisterComponent(main);
										cr.registerComponent(main);

									} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
											| UnsupportedLookAndFeelException ex) {
										System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
									}

									SwingUtilities.updateComponentTreeUI(main);
								}
							}
									
						}
					});
				}
				
				// Add lookfeelPanel to a Popup Panel
				String ExitOption[] = { "Ok" };
				int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), radioPanel,
						"Look and Feel", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						IconHandle.get_scaledImageIcon(40, 40, "icon_laf.png"), ExitOption, ExitOption[0]);

				if (response == 0) {

				}
			}
		});

	}
	
	public static boolean is_Nimbus_Without_titleBar() {
		return is_Nimbus_Without_titleBar;
	}
}
