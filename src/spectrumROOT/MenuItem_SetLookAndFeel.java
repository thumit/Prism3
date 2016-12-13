package spectrumROOT;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import spectrumConvenienceClasses.ComponentResizer;

public class MenuItem_SetLookAndFeel extends JMenuItem {
	private ImageIcon icon;
	private Image scaleImage;
	private static boolean is_Nimbus_Without_titleBar = true;		//Set this to false if in the void main(String[] args), we do the following:
																	//1: activate this line			setDefaultLookAndFeelDecorated(true);
																	//2: deactivate this line		main.setUndecorated(true);
																	//Doing those 2 things will make Nimbus has the title when JFrame main 1st start (also make: Metal LAF has title)	
																	//Reson to set true or false is to change the component resize behavior, see Dung's code
	
	public MenuItem_SetLookAndFeel (Spectrum_Main main, ComponentResizer cr) {
		setText("Change Look and Feel");
		
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

										
//										//2 more Look and Feels
//										UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//										UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

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
				icon = new ImageIcon(getClass().getResource("/icon_question.png"));
				scaleImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				String ExitOption[] = { "Ok" };
				int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(), scrollPane,
						"Select a Look and Feel", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						new ImageIcon(scaleImage), ExitOption, ExitOption[0]);

				if (response == 0) {

				}

	
			}
		});

	}
	
	public static boolean is_Nimbus_Without_titleBar() {
		return is_Nimbus_Without_titleBar;
	}
}
