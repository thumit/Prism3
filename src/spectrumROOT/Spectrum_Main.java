package spectrumROOT;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import spectrumConvenienceClasses.ComponentResizer;
import spectrumConvenienceClasses.FilesHandle;
import spectrumConvenienceClasses.JMenuBarCustomize;
import spectrumConvenienceClasses.NameHandle;
import spectrumConvenienceClasses.RequestFocusListener;
import spectrumConvenienceClasses.WindowAppearanceHandle;
import spectrumDatabase.Panel_DatabaseManagement;
import spectrumYieldProject.Panel_YieldProject;

@SuppressWarnings("serial")
public class Spectrum_Main extends JFrame {
	// Define variables------------------------------------------------------------------------
	private ImageIcon 	icon;
	private Image 		scaleImage;
	
	private JMenuBarCustomize 	spectrum_Menubar;
	private JMenu 				menuFile, menuUtility, menuWindow, menuHelp,
								menuOpenProject;
	private JMenuItem 			newProject, exitSoftware, //Children of MenuFile
								existingProject, //For menuOpenProject
								DatabaseManagement, //For MenuUtility
								contents, update, contact, about; //For MenuMenuHelp
	
	private MenuItem_SetTransparency 	setTransparency;	//For menuWindow
	private MenuItem_SetLookAndFeel 	setLookAndFeel;		//For menuWindow
	
	private int 		pX,pY;
	
	private static Panel_BackGroundDesktop spectrumDesktopPane;
	private static String currentProjectName;
	private static Spectrum_Main main;
	private static ComponentResizer cr;
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
//		new Spectrum_Main();
			
		// For translucent windows
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		if (gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
//			setDefaultLookAndFeelDecorated(true);
			main = new Spectrum_Main();
		 	main.setUndecorated(true);
			main.setOpacity(0.95f);
			
			//Need border so cr can work
			Border tempBorder = BorderFactory.createMatteBorder(4, 0, 0, 0, Color.BLACK);
//			TitledBorder title = BorderFactory.createTitledBorder(tempBorder, "SpectrumLite Demo Version 1.10");
			main.getRootPane().setBorder(tempBorder);
			
			cr = new ComponentResizer();	//Need resize since if "setDefaultLookAndFeelDecorated(true);" then the top corners cannot be resized (java famous bug?)
			cr.registerComponent(main);
		} 		
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public Spectrum_Main() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
				}
//				setExtendedState(JFrame.MAXIMIZED_BOTH); 	//make SpectrumLite Main full screen
				
//				setTitle("SpectrumLite Demo Version 1.10");
				setIconImage(new ImageIcon(getClass().getResource("/icon_main.png")).getImage());
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e){exitSpectrumLite();}});				
				getContentPane().setLayout(new BorderLayout());	

				// Define components: Menubar, Menus, MenuItems----------------------------------
				newProject = new JMenuItem("New");
				menuOpenProject = new JMenu("Open");
				exitSoftware = new JMenuItem("Exit");
				DatabaseManagement = new JMenuItem("Database Management");
				setTransparency = new MenuItem_SetTransparency(main);
				setLookAndFeel = new MenuItem_SetLookAndFeel(main, cr);
				contents = new JMenuItem("Contents");
				update = new JMenuItem("Check for updates");
				contact = new JMenuItem("Contact us");
				about = new JMenuItem("About SpectrumLite");

				menuFile = new JMenu("File");
				menuUtility = new JMenu("Utility");
				menuWindow = new JMenu("Window");
				menuHelp = new JMenu("Help");

				
				spectrumDesktopPane = new Panel_BackGroundDesktop();
				spectrum_Menubar = new JMenuBarCustomize();
				// Add mouse listener for JMenuBar
				spectrum_Menubar.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent me) {
						// Get x,y and store them
						pX = me.getX();
						pY = me.getY();
					}
				});

				// Add MouseMotionListener for detecting drag
				spectrum_Menubar.addMouseMotionListener(new MouseAdapter() {
					public void mouseDragged(MouseEvent me) {
						setLocation(getLocation().x + me.getX() - pX, getLocation().y + me.getY() - pY);
					}
				});
							
				
				// Add components: Menubar, Menus, MenuItems----------------------------------
				menuFile.add(newProject);
				menuFile.add(menuOpenProject);
				menuFile.add(exitSoftware);
				menuUtility.add(DatabaseManagement);
				menuWindow.add(setTransparency);
				menuWindow.add(setLookAndFeel);
				menuHelp.add(contents);
				menuHelp.add(update);
				menuHelp.add(contact);
				menuHelp.add(about);

				spectrum_Menubar.add(menuFile);
				spectrum_Menubar.add(menuUtility);
				spectrum_Menubar.add(menuWindow);
				spectrum_Menubar.add(menuHelp);
				spectrum_Menubar.addFrameFeatures();

				setJMenuBar(spectrum_Menubar);	
				getContentPane().add(spectrumDesktopPane);
				WindowAppearanceHandle.setOpaqueForAll(spectrum_Menubar, false);
				WindowAppearanceHandle.setOpaqueForAll(spectrumDesktopPane, false);
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);

				
				// Add listeners for MenuItems------------------------------------------------
				
				// Add listener for "Window"------------------------------------------------
				menuWindow.addMenuListener(new MenuListener() {
					@Override
			        public void menuSelected(MenuEvent e) {						
						//Only allow to change look and feel if No Frame is opened, this is to prevent fail performance of the components after changing look and feel
						if (Spectrum_Main.mainFrameReturn().getAllFrames().length ==  0) {
							setLookAndFeel.setEnabled(true);
						} else {
							setLookAndFeel.setEnabled(false);
						}		
					}

					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				// Add listeners "New"------------------------------------------------
				newProject.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						//JtextField to type name
						JTextField projectName_JTextField = new JTextField(35);
						projectName_JTextField.getDocument().addDocumentListener(new DocumentListener() {
							@Override  
							public void changedUpdate(DocumentEvent e) {
								currentProjectName = projectName_JTextField.getText().trim();		//Trim spaces at the begin and end of the string
							}
							public void removeUpdate(DocumentEvent e) {
								currentProjectName = projectName_JTextField.getText().trim();		//Trim spaces at the begin and end of the string
							}
							public void insertUpdate(DocumentEvent e) {
								currentProjectName = projectName_JTextField.getText().trim();		//Trim spaces at the begin and end of the string
							}
						});
						projectName_JTextField.setText("New Project");		//default name
						projectName_JTextField.addAncestorListener( new RequestFocusListener());	//Set focus (mouse cursor on the JTextField)
						
						
						boolean stop_naming = false;
						String titleText = "Type your project's name";
						while (stop_naming == false) {
							icon = new ImageIcon(getClass().getResource("/icon_question.png"));
					  		scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
					  		String ExitOption[] = {"OK","Cancel"};
							int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(), projectName_JTextField, titleText,
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[0]);
							if (response == 0) 
							{
								// Find all the existing projects in the "Projects" folder		
								File[] listOfFiles = FilesHandle.get_projectsFolder().listFiles(new FilenameFilter() {
									@Override
									public boolean accept(File dir, String name) {
										return name.startsWith("");
									}
								});

								List<String> existingName_list = new ArrayList<String>();
								if (listOfFiles != null) {
									for (int i = 0; i < listOfFiles.length; i++) {
										if (listOfFiles[i].isDirectory()) {
											String fileName;
											fileName = listOfFiles[i].getName();
											existingName_list.add(fileName);								
										}
									}		
								}
												
								//if Name is valid and existing projects do not contain this Name
								if (NameHandle.nameIsValid(currentProjectName)==true && !existingName_list.contains(currentProjectName)) {
									if (new File(FilesHandle.get_projectsFolder().getAbsolutePath() + "/" + currentProjectName).mkdir()) {		//try if can create a folder with the existing project name
										createNewJInternalFrame();		//create new internal frame for this existing project
										stop_naming = true;
									}
								} else {
									titleText = "Name already exists or contains special characters. Please try a new name:";									
								}
							} 		
							
							else {
								stop_naming = true;
							}
					    }
						
					}
				});	

				
				// Add listeners "Open"------------------------------------------------
				menuOpenProject.addMenuListener(new MenuListener() {
					@Override
			        public void menuSelected(MenuEvent e) {					
						menuOpenProject.removeAll();			//REMOVE ALL existing projects
										
						// Find all the existing projects in the "Projects" folder		
						File[] listOfFiles = FilesHandle.get_projectsFolder().listFiles(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String name) {
								return name.startsWith("");
							}
						});

						if (listOfFiles != null) {
							for (int i = 0; i < listOfFiles.length; i++) {
								if (listOfFiles[i].isDirectory()) {
									String fileName;
									fileName = listOfFiles[i].getName();
									existingProject = new JMenuItem(fileName);
									menuOpenProject.add(existingProject);			//ADD ALL existing projects

									existingProject.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent event) {
											currentProjectName = fileName;
											
											JInternalFrame[] opened_InternalFrames = Spectrum_Main.mainFrameReturn().getAllFrames();	//All displayed internalFrames
											List<String> openedFrames_list = new ArrayList<String>();					//List of Frames Names					
											for (int i = 0; i < opened_InternalFrames.length; i++) {
												openedFrames_list.add(opened_InternalFrames[i].getTitle());		//Loop all displayed IFrames to get Names and add to the list
											}
											
											//Only open if it is not opened yet
											if (openedFrames_list.contains(fileName)) {
												for (int i = 0; i < opened_InternalFrames.length; i++) {
													if (opened_InternalFrames[i].getTitle().equals(fileName)) { 
														try {
															opened_InternalFrames[i].setSelected(true);
														} catch (PropertyVetoException e) {
															System.err.println(e.getClass().getName() + ": " + e.getMessage());
														}
													}
												}
											} else {
												createNewJInternalFrame(); // Open it
											}
											
											
										}
									});
								}
							}
						}

					}
					
					@Override
			        public void menuDeselected(MenuEvent e) {
			        }

			        @Override
			        public void menuCanceled(MenuEvent e) {
			        }
					
				});	
				
				
				// Add listeners "ExitSoftware"-----------------------------------------------------
				exitSoftware.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						exitSpectrumLite();
					}
				});
				
								
				// Add listeners "DatabaseManagement"------------------------------------------------
				DatabaseManagement.addActionListener(
						new ActionListener() { // anonymous inner class
							// display new internal window
							public void actionPerformed(ActionEvent event) {
								// create internal frame
								JInternalFrame DatabaseManagement_Frame = new JInternalFrame("Database Management", 
																						true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);								
								
								spectrumDesktopPane.add(DatabaseManagement_Frame, BorderLayout.CENTER); // attach internal frame
								DatabaseManagement_Frame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.25));
								DatabaseManagement_Frame.setLocation((int) ((getWidth() - DatabaseManagement_Frame.getWidth())/2),
										((int) ((getHeight() - DatabaseManagement_Frame.getHeight())/2.5)));	//Set the DatabaseManagement_Frame near the center of the Main frame
								if (Spectrum_Main.mainFrameReturn().getSelectedFrame() != null) {	//or Set the DatabaseManagement_Frame near the recently opened JInternalFrame
									DatabaseManagement_Frame.setLocation(Spectrum_Main.mainFrameReturn().getSelectedFrame().getX() + 30, Spectrum_Main.mainFrameReturn().getSelectedFrame().getY() + 30);
								}
								
								// Note: visible first for the JIframe to be selected, pack at the end would be fail for JIframe to be selected (Spectrum_Main.mainFrameReturn().getSelectedFrame = null)
								DatabaseManagement_Frame.setVisible(true); // show internal frame					
								Panel_DatabaseManagement DatabaseManagementPanel = new Panel_DatabaseManagement(); // create new panel
								DatabaseManagement_Frame.add(DatabaseManagementPanel, BorderLayout.CENTER); // add panel
//								DatabaseManagement_Frame.pack(); // set internal frame to size of contents
								

								DatabaseManagement.setEnabled(false); //Disable "DatabaseManagement" menuItem when it is already opened
								InternalFrameListener DatabaseInternalFrame_listener = new InternalFrameListener() {
								      public void internalFrameActivated(InternalFrameEvent e) {
								   
								      }

								      public void internalFrameClosed(InternalFrameEvent e) {
							
								      }

								      public void internalFrameClosing(InternalFrameEvent e) {							 
								    	  DatabaseManagement.setEnabled(true); //Enable
								      }

								      public void internalFrameDeactivated(InternalFrameEvent e) {
								      
								      }

								      public void internalFrameDeiconified(InternalFrameEvent e) {
								      
								      }

								      public void internalFrameIconified(InternalFrameEvent e) {
								      
								      }

								      public void internalFrameOpened(InternalFrameEvent e) {
								      
								      }
								    };
								    DatabaseManagement_Frame.addInternalFrameListener(DatabaseInternalFrame_listener);
							} // end method actionPerformed
						} // end anonymous inner class
				); // end call to addActionListener	
				
				
			} //end public void run()					
		}); // end EventQueue.invokeLater
	} // end public Spectrum_Main
			
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void createNewJInternalFrame() {
		// create internal frame
		JInternalFrame ProjectInternalFrame = new JInternalFrame(currentProjectName, 
																true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);	
		ProjectInternalFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
				
		spectrumDesktopPane.add(ProjectInternalFrame, BorderLayout.CENTER); // attach internal frame
		ProjectInternalFrame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.25));		
		ProjectInternalFrame.setLocation((int) ((getWidth() - ProjectInternalFrame.getWidth())/2),
				((int) ((getHeight() - ProjectInternalFrame.getHeight())/2.5)));	//Set the ProjectInternalFrame near the center of the Main frame
		if (Spectrum_Main.mainFrameReturn().getSelectedFrame() != null) {	//or Set the ProjectInternalFrame near the recently opened JInternalFrame
			ProjectInternalFrame.setLocation(Spectrum_Main.mainFrameReturn().getSelectedFrame().getX() + 30, Spectrum_Main.mainFrameReturn().getSelectedFrame().getY() + 30);
		}
			
		// Note: visible first for the JIframe to be selected, pack at the end would be fail for JIframe to be selected (Spectrum_Main.mainFrameReturn().getSelectedFrame = null)
		ProjectInternalFrame.setVisible(true); // show internal frame	
		Panel_YieldProject YieldProjectPanel = new Panel_YieldProject(); // create new panel
		ProjectInternalFrame.add(YieldProjectPanel, BorderLayout.CENTER); // add panel
//		ProjectInternalFrame.pack(); // set internal frame to size of contents
		
								
		InternalFrameListener ProjectInternalFrame_listener = new InternalFrameListener() {
		      public void internalFrameActivated(InternalFrameEvent e) {

		      }

		      public void internalFrameClosed(InternalFrameEvent e) {

		      }

		      public void internalFrameClosing(InternalFrameEvent e) {

		    	icon = new ImageIcon(getClass().getResource("/icon_question.png"));
		  		scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		  		String ExitOption[] = {"Yes","No"};
				int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(),"Your changes would not be saved if closing project while editing. Would you like to close this project ?", "Close Project",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[0]);
				if (response == 0)
				{
					 ProjectInternalFrame.dispose();
				}
				if (response == 1)
				{
		        }
		      }

		      public void internalFrameDeactivated(InternalFrameEvent e) {
		     
		      }

		      public void internalFrameDeiconified(InternalFrameEvent e) {
		   
		      }

		      public void internalFrameIconified(InternalFrameEvent e) {
		      
		      }

		      public void internalFrameOpened(InternalFrameEvent e) {
		     
		      }
		    };
			ProjectInternalFrame.addInternalFrameListener(ProjectInternalFrame_listener);	
	}
	 
	//--------------------------------------------------------------------------------------------------------------------------------
	public void exitSpectrumLite() {
		icon = new ImageIcon(getClass().getResource("/icon_question.png"));
		scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
//		String ExitOption[] = {"Yes","No","Cancel"};
		String ExitOption[] = {"Yes","No"};
		int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(),"Your changes would not be saved if exit while editing. Would you like to exit SpectrumLite ?", "Exit SpectrumLite",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[0]);
		if (response == 0)
		{
			System.exit(0);
		}
		if (response == 1)
		{
		}
	} 
	
	public void minimize() {
		main.setState(JFrame.ICONIFIED);
	}

	public void restore() {
		if (main.getState() != JFrame.MAXIMIZED_BOTH) {
			main.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} else {
			main.setExtendedState(JFrame.NORMAL);
		}		
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1600, 900);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static Panel_BackGroundDesktop mainFrameReturn() {
		return spectrumDesktopPane;
	}
	
	public static Spectrum_Main mainReturn() {
		return main;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static String getProjectName() {
		return currentProjectName;
	}

}
