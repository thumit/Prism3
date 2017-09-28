package prismRoot;

import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import prismConvenienceClass.ComponentResizer;
import prismConvenienceClass.IconHandle;

public class MenuItem_SetLookAndFeel extends JMenuItem {
	private static boolean is_Nimbus_Without_titleBar = true;		//Set this to false if in the void main(String[] args), we do the following:
																	//1: activate this line			setDefaultLookAndFeelDecorated(true);
																	//2: deactivate this line		main.setUndecorated(true);
																	//Doing those 2 things will make Nimbus has the title when JFrame main 1st start (also make: Metal LAF has title)	
																	//Reson to set true or false is to change the component resize behavior, see Dung's code
	
	public MenuItem_SetLookAndFeel (PrismMain main, ComponentResizer cr) {
		setText("Change Look and Feel");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_laf.png"));
		
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_DOWN_MASK));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (PrismMain.get_Prism_DesktopPane().getAllFrames().length ==  0) {
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
																														
											UIManager.setLookAndFeel(info.getClassName());									
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
					
					
					JScrollPane scrollPane = new JScrollPane(radioPanel);
					scrollPane.setPreferredSize(new Dimension(450, 30));
					scrollPane.setBorder(null);
					
					// Add lookfeelPanel to a Popup Panel
					String ExitOption[] = { "Ok" };
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), scrollPane,
							"Select a Look and Feel", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							IconHandle.get_scaledImageIcon(40, 40, "icon_laf.png"), ExitOption, ExitOption[0]);
	
					if (response == 0) {
	
					}
				}
	
			}
		});

	}
	
	public static boolean is_Nimbus_Without_titleBar() {
		return is_Nimbus_Without_titleBar;
	}
}
