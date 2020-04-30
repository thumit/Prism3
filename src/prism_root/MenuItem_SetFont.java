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

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DefaultFormatter;

import prism_convenience.IconHandle;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.WindowAppearanceHandle;

public class MenuItem_SetFont extends JMenuItem {
	private JSpinner spin;
	
	public MenuItem_SetFont(PrismMain main) {
		setText("Change Font");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_font.png"));
		
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_DOWN_MASK, true));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String old_font_name = UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName();
				int old_font_size = UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getSize();
				JLabel testing_font = new JLabel();
				
				
				//--------------------------------------------------------------------------------------------------------------------
				GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
				Font[] allFonts = e.getAllFonts();
			
				// Create a radio buttons
				JRadioButton[] radioButton = new JRadioButton[allFonts.length];		
				for (int i = 0; i < allFonts.length; i++) {
					radioButton[i] = new JRadioButton(allFonts[i].getFontName());
					String font_name = allFonts[i].getFontName();
//					int font_size = (spin != null) ? (Integer) spin.getValue() : 12;
					radioButton[i].setFont(new Font(font_name, Font.PLAIN, 14));
				}

				// Create a radio panel
				JPanel radioPanel = new JPanel();
				radioPanel.setLayout(new GridLayout(0, 2));
						
				// Create a radioGroup buttons
				ButtonGroup radioGroup = new ButtonGroup();
				for (JRadioButton i : radioButton) {
					radioGroup.add(i);
					radioPanel.add(i);
					
					i.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {													
							String font_name = i.getText();
							int font_size = (Integer) spin.getValue();
							testing_font.setText("THIS Font is " + font_name + " - Size " + font_size);
				        	testing_font.setFont(new Font(font_name, Font.PLAIN, font_size));
						}
					});
				}
				
				
				
				JScrollPane scrollPane = new JScrollPane(radioPanel);
				scrollPane.setBorder(BorderFactory.createTitledBorder("Font name"));
				scrollPane.setPreferredSize(new Dimension(0, 0));

				
				//--------------------------------------------------------------------------------------------------------------------
				spin = new JSpinner (new SpinnerNumberModel(12, 6, 16, 1));
				spin.setPreferredSize(new Dimension(120, 70));
				spin.setBorder(BorderFactory.createTitledBorder("Font size"));
				spin.setValue(UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getSize());		
				JFormattedTextField SpinnerText = ((DefaultEditor) spin.getEditor()).getTextField();
				SpinnerText.setHorizontalAlignment(JTextField.LEFT);
				DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
			    formatter.setCommitsOnValidEdit(true);
			    spin.addChangeListener(new ChangeListener() {
			        @Override
			        public void stateChanged(ChangeEvent e) {
			        	String font_name = "";
						for (JRadioButton i : radioButton) {
							if (i.isSelected()) {
								font_name = i.getText();
							}
						}
						int font_size = (Integer) spin.getValue();
						testing_font.setText("THIS Font is " + font_name + " - Size " + font_size);
			        	testing_font.setFont(new Font(font_name, Font.PLAIN, font_size));
					}
				});
			    
			    
			    //--------------------------------------------------------------------------------------------------------------------
			    for (JRadioButton i : radioButton) {
					if (UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName().equals(i.getText())) {		// Use MenuBar to get current Font
						i.setSelected(true);	// select the radioButton of the current Font
						i.doClick();			// this is to trigger showing the testing_font right when we open the pop-up JOptionPane
					}
				}
			    
			    
			    //--------------------------------------------------------------------------------------------------------------------
			    JButton button_default = new JButton();
			    button_default.setPreferredSize(new Dimension(200, 70));
			    button_default.setBorder(BorderFactory.createTitledBorder("Set font to default"));
			    button_default.setText("Century Schoolbook - 12");
			    button_default.setVerticalTextPosition(SwingConstants.CENTER);
			    button_default.setHorizontalTextPosition(SwingConstants.RIGHT);
			    button_default.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_main.png"));	
			    button_default.setRolloverIcon(IconHandle.get_scaledImageIcon(30, 30, "icon_main.png"));
			    button_default.setContentAreaFilled(false);
			    button_default.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						spin.setValue(12);
						for (JRadioButton i : radioButton) {
							if (i.getText().equals("Century Schoolbook")) {
								i.setSelected(true);
								i.doClick();
							}
						}
					}
			    });
			    
			    
			    //--------------------------------------------------------------------------------------------------------------------
				// Add Font size & type to a panel
				JPanel combined_panel = new JPanel(new GridBagLayout());
				combined_panel.setMinimumSize(new Dimension(600, 300));
				combined_panel.setPreferredSize(new Dimension((int) (PrismMain.get_main().getWidth() * 0.6), (int) (PrismMain.get_main().getHeight() * 0.5)));
				// These codes make the popupPanel resizable
				combined_panel.addHierarchyListener(new HierarchyListener() {
				    public void hierarchyChanged(HierarchyEvent e) {
				        Window window = SwingUtilities.getWindowAncestor(combined_panel);
				        if (window instanceof Dialog) {
				            Dialog dialog = (Dialog)window;
				            if (!dialog.isResizable()) {
				                dialog.setResizable(true);
				            }
				        }
				    }
				});
				
				GridBagConstraints c = new GridBagConstraints();
				combined_panel.add(spin, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
						0, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
						0, 0, 0, 0));		// insets top, left, bottom, right	
				combined_panel.add(button_default, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
						1, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
						0, 0, 0, 0));		// insets top, left, bottom, right
				combined_panel.add(testing_font, PrismGridBagLayoutHandle.get_c(c, "CENTER", 
						2, 0, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
						0, 0, 0, 0));		// insets top, left, bottom, right
				combined_panel.add(scrollPane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
						0, 1, 3, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
						0, 0, 0, 0));		// insets top, left, bottom, right
									
				
				//--------------------------------------------------------------------------------------------------------------------
			    // Some defaults when this JmenuItem shows up
				spin.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
				((TitledBorder) spin.getBorder()).setTitleFont(new Font("Century Schoolbook", Font.BOLD, 13));
				button_default.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
				((TitledBorder) button_default.getBorder()).setTitleFont(new Font("Century Schoolbook", Font.BOLD, 13));
				((TitledBorder) scrollPane.getBorder()).setTitleFont(new Font("Century Schoolbook", Font.BOLD, 13));
				((DefaultEditor) spin.getEditor()).getTextField().setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
				
				
				//--------------------------------------------------------------------------------------------------------------------
				// Add the panel to a pop-up panel
				String ExitOption[] = { "Apply changes" };
				int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), combined_panel,
						"Select Font", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						IconHandle.get_scaledImageIcon(40, 40, "icon_font.png"), ExitOption, ExitOption[0]);

				if (response == 0) {
					// For running inside Eclipse IDE
					String font_name = "";
					for (JRadioButton i : radioButton) {
						if (i.isSelected()) {
							font_name = i.getText();
						}
					}
					int font_size = (Integer) spin.getValue();
					try {			
						UIManager.setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());		// Very important before changing Font
						UIManager.getLookAndFeelDefaults().put("defaultFont", new FontUIResource(new Font(font_name, Font.PLAIN, font_size)));	// Since the update to eclipse Oxygen and update to java9, 
																																				// this line is required to make it not fail when click File --> Open after changing Look and Feel in Eclise IDE
						WindowAppearanceHandle.setUIFont(new FontUIResource(new Font(font_name, Font.PLAIN, font_size)));														
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException ex) {
						System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
					}
					SwingUtilities.updateComponentTreeUI(main);
					
					// For running the runnable jar
					if (!font_name.equals(old_font_name) || font_size != old_font_size) {
						OptionPane_Startup.Restart_Project("after change font");
					}
				} else {
					try {			
						UIManager.setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());		// Very important before changing Font
						UIManager.getLookAndFeelDefaults().put("defaultFont", new FontUIResource(new Font(old_font_name, Font.PLAIN, old_font_size)));	// Since the update to eclipse Oxygen and update to java9, 
																																					// this line is required to make it not fail when click File --> Open after changing Look and Feel in Eclise IDE
						WindowAppearanceHandle.setUIFont(new FontUIResource(new Font(old_font_name, Font.PLAIN, old_font_size)));														
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException ex) {
						System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
					}
					SwingUtilities.updateComponentTreeUI(main);
				}
				
			}
		});
	}

}
