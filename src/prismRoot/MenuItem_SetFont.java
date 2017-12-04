package prismRoot;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
import javax.swing.JFormattedTextField;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DefaultFormatter;

import prismConvenienceClass.IconHandle;
import prismConvenienceClass.WindowAppearanceHandle;

public class MenuItem_SetFont extends JMenuItem {
	private JSpinner spin;
	
	public MenuItem_SetFont (PrismMain main) {
		setText("Change Font");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_font.png"));
		
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if (PrismMain.get_Prism_DesktopPane().getAllFrames().length ==  0) {
					//--------------------------------------------------------------------------------------------------------------------
					GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
					Font[] allFonts = e.getAllFonts();
				
					// Create a radio buttons
					JRadioButton[] radioButton = new JRadioButton[allFonts.length];		
					for (int i = 0; i < allFonts.length; i++) {
						radioButton[i] = new JRadioButton(allFonts[i].getFontName());
						String style = allFonts[i].getFontName();
						int size = (spin != null) ? (Integer) spin.getValue() : 12;
						radioButton[i].setFont(new Font(style, Font.PLAIN, 14));
					}
	
					// Create a radio panel
					JPanel radioPanel = new JPanel();
					radioPanel.setLayout(new GridLayout(0, 2));
							
					// Create a radioGroup buttons
					ButtonGroup radioGroup = new ButtonGroup();
					for (JRadioButton i : radioButton) {
						radioGroup.add(i);
						radioPanel.add(i);
					
						if (UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName().equals(i.getText())) {		// Use MenuBar to get current Font
							i.setSelected(true);		//Select the radioButton of the current Font
						}
						
						String style = i.getText();
						i.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {													
								int size = (Integer) spin.getValue();						
								
								try {			
									UIManager.setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());		// Very important before changing Font						
									WindowAppearanceHandle.setUIFont(new FontUIResource(new Font(style, Font.PLAIN, size)));														
								} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
										| UnsupportedLookAndFeelException ex) {
									System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
								}
								SwingUtilities.updateComponentTreeUI(main);
							}
						});
					}
					
					
					
					JScrollPane scrollPane = new JScrollPane(radioPanel);
					scrollPane.setBorder(BorderFactory.createTitledBorder("Font style"));
					scrollPane.setPreferredSize(new Dimension(650, 500));
	
					
					//--------------------------------------------------------------------------------------------------------------------
					spin = new JSpinner (new SpinnerNumberModel(12, 6, 16, 1));
					spin.setBorder(BorderFactory.createTitledBorder("Font size"));
					spin.setValue(UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getSize());		
					JFormattedTextField SpinnerText = ((DefaultEditor) spin.getEditor()).getTextField();
					SpinnerText.setHorizontalAlignment(JTextField.LEFT);
					DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
				    formatter.setCommitsOnValidEdit(true);
				    spin.addChangeListener(new ChangeListener() {
				        @Override
				        public void stateChanged(ChangeEvent e) {
							spin.setValue(spin.getValue());
							int size = (Integer) spin.getValue();
	
							String style = null;
							for (JRadioButton i : radioButton) {
								if (i.isSelected()) {
									style = i.getText();
								}
							}
							
							try {					
								UIManager.setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());		// Very important before changing Font						
								WindowAppearanceHandle.setUIFont(new FontUIResource(new Font(style, Font.PLAIN, size)));									
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| UnsupportedLookAndFeelException ex) {
								System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
							}
							SwingUtilities.updateComponentTreeUI(main);
						}
				    });
				    
				    
				    //--------------------------------------------------------------------------------------------------------------------
					// Add Font size & type to a panel
					JPanel combined_panel = new JPanel(new BorderLayout());
//					These codes make the popupPanel resizable
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
					
					combined_panel.add(spin, BorderLayout.NORTH);
					combined_panel.add(scrollPane, BorderLayout.CENTER);					
										
					
					// Add the panel to a pop-up panel
					String ExitOption[] = { "Ok"};
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), combined_panel,
							"Select a Font", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
							IconHandle.get_scaledImageIcon(40, 40, "icon_font.png"), ExitOption, ExitOption[0]);
	
					if (response == 0) {
					}
				}

			}
		});
	}

}
