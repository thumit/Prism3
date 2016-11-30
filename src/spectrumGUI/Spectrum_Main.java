package spectrumGUI;

import java.awt.BorderLayout;
import java.awt.Component;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import spectrumConvenienceClasses.CheckNameValid;
import spectrumConvenienceClasses.RequestFocusListener;
import spectrumDatabaseUtil.Panel_DatabaseManagement;
import spectrumYieldProject.ComponentResizer;
import spectrumYieldProject.Panel_YieldProject;

@SuppressWarnings("serial")
public class Spectrum_Main extends JFrame {
	// Define variables------------------------------------------------------------------------
	private File 		projectsFolder;
	private String 		workingLocation,
						seperator = "/";
	
	private ImageIcon 	icon;
	private Image 		scaleImage;
	
	private JMenuBar 	spectrum_Menubar;
	private JMenu 		menuFile, menuUtility, menuHelp,
						menuOpenProject;
	private JMenuItem 	newProject, saveProject, saveProjectAs, closeProject, exitSoftware, //Children of MenuFile
						existingProject, //For menuOpenProject
						DatabaseManagement, //For MenuUtility
						contents, update, contact, about; //For MenuMenuHelpFile
	
	private int 		OpenProjectCount = 0;
	private int 		pX,pY;
	
	private static Panel_BackGroundDesktop spectrumDesktopPane;
	private static String currentProjectName;

	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
//		new Spectrum_Main();
			
		//For translucent windows
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		 if (gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
			setDefaultLookAndFeelDecorated(true);	
			Spectrum_Main main = new Spectrum_Main();
//			main.setUndecorated(true);
			main.setOpacity(0.95f);		
			ComponentResizer cr = new ComponentResizer();
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
					// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
				}

				//These 2 lines make SpectrumLite Main full screen
//				setExtendedState(JFrame.MAXIMIZED_BOTH); 
//				setUndecorated(true);
				
				setTitle("SpectrumLite Demo Version 1.07");
				setIconImage(new ImageIcon(getClass().getResource("/icon_main.png")).getImage());
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e){exitSpectrumLite();}});				
				getContentPane().setLayout(new BorderLayout());	

				// Define components: Menubar, Menus, MenuItems----------------------------------
				newProject = new JMenuItem("New");
				menuOpenProject = new JMenu("Open");
//				SaveProject = new JMenuItem("Save");
//				SaveProjectAs = new JMenuItem("Save as");
//				CloseProject = new JMenuItem("Close");
				exitSoftware = new JMenuItem("Exit");
				DatabaseManagement = new JMenuItem("Database Management");
				contents = new JMenuItem("Contents");
				update = new JMenuItem("Check for updates");
				contact = new JMenuItem("Contact us");
				about = new JMenuItem("About SpectrumLite");

				menuFile = new JMenu("File");
				menuUtility = new JMenu("Utility");
				menuHelp = new JMenu("Help");

				
				spectrumDesktopPane = new Panel_BackGroundDesktop();
				spectrum_Menubar = new JMenuBar();
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
//				MenuFile.add(SaveProject);
//				MenuFile.add(SaveProjectAs);
//				MenuFile.add(CloseProject);
				menuFile.add(exitSoftware);
				menuUtility.add(DatabaseManagement);
				menuHelp.add(contents);
				menuHelp.add(update);
				menuHelp.add(contact);
				menuHelp.add(about);

				spectrum_Menubar.add(menuFile);
				spectrum_Menubar.add(menuUtility);
				spectrum_Menubar.add(menuHelp);

				setJMenuBar(spectrum_Menubar);	
				getContentPane().add(spectrumDesktopPane);
				setOpaqueForAll(spectrum_Menubar, false);
				setOpaqueForAll(spectrumDesktopPane, false);
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);

				
				// Add listeners for MenuItems------------------------------------------------
				// Add listeners "New"------------------------------------------------
				newProject.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						createProjectsFolder();			//create Projects folder if not exist
						
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
								try {	
									//Check Name if valid
									CheckNameValid checkValid = new CheckNameValid();
									
									//Check Name if already exists
									// Find all the existing projects in the "Projects" folder		
									File[] listOfFiles = projectsFolder.listFiles(new FilenameFilter() {
										@Override
										public boolean accept(File dir, String name) {
											return name.startsWith("");
										}
									});

									List<String> existingName_list = new ArrayList<String>();
									for (int i = 0; i < listOfFiles.length; i++) {
										if (listOfFiles[i].isDirectory()) {
											String fileName;
											fileName = listOfFiles[i].getName();
											existingName_list.add(fileName);								
										}
									}	
									
									
									//Only create new project if Name is valid and existing projects do not contain this Name
									if (checkValid.nameIsValid(currentProjectName)==true && !existingName_list.contains(currentProjectName)) {
										openOrnewJInternalFrame();
										stop_naming = true;
									} else {
										titleText = "Name already exists or contains special characters. Please try a new name:";									
									}
								} catch (Exception e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage());
									titleText = "Invalid (Name already exists or contains special characters). Please type a new name!";
								}
							}
							else if (response == 1) 
							{
								stop_naming = true;
							} else //This is close (x) button
							{
								stop_naming = true;
							}
					    }
						
					}
				});	

				
				// Add listeners "Open"------------------------------------------------
				menuOpenProject.addMenuListener(new MenuListener() {
					@Override
			        public void menuSelected(MenuEvent e) {					
						createProjectsFolder();			//create Projects folder if not exist
						menuOpenProject.removeAll();			//REMOVE ALL existing projects
										
						// Find all the existing projects in the "Projects" folder		
						File[] listOfFiles = projectsFolder.listFiles(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String name) {
								return name.startsWith("");
							}
						});

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
											openOrnewJInternalFrame(); // Open it
										}
										
										
									}
								});
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
								Panel_DatabaseManagement DatabaseManagementPanel = new Panel_DatabaseManagement(); // create new panel
								DatabaseManagement_Frame.add(DatabaseManagementPanel, BorderLayout.CENTER); // add panel
								DatabaseManagement_Frame.pack(); // set internal frame to size of contents
								
								spectrumDesktopPane.add(DatabaseManagement_Frame, BorderLayout.CENTER); // attach internal frame
								DatabaseManagement_Frame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.25));
								DatabaseManagement_Frame.setLocation((int) ((getWidth() - DatabaseManagement_Frame.getWidth())/2),
										((int) ((getHeight() - DatabaseManagement_Frame.getHeight())/2.5)));	//Set the DatabaseManagement_Frame near the center of the Main frame
								if (Spectrum_Main.mainFrameReturn().getSelectedFrame() != null) {	//or Set the DatabaseManagement_Frame near the recently opened JInternalFrame
									DatabaseManagement_Frame.setLocation(Spectrum_Main.mainFrameReturn().getSelectedFrame().getX() + 30, Spectrum_Main.mainFrameReturn().getSelectedFrame().getY() + 30);
								}
								DatabaseManagement_Frame.setVisible(true); // show internal frame
														
								DatabaseManagement.setEnabled(false); //Disable "DatabaseManagement" menuItem when it is already opened
								InternalFrameListener DatabaseInternalFrame_listener = new InternalFrameListener() {
								      public void internalFrameActivated(InternalFrameEvent e) {
								   
								      }

								      public void internalFrameClosed(InternalFrameEvent e) {
							
								      }

								      public void internalFrameClosing(InternalFrameEvent e) {							 
								    	  DatabaseManagement.setEnabled(true); //Enable "New" menuItem
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
	public void createProjectsFolder() {
		// Check if Projects folder exists, if not then create it--------------------------------------------
		// Get working location of the IDE project, or runnable jar file
		final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		if (jarFile.isFile()) { // Run with JAR file
			projectsFolder = new File(":Projects");
			seperator = ":";
		}

		// Both runnable jar and IDE work with condition: Projects folder and runnable jar have to be in the same location
		workingLocation = jarFile.getParentFile().toString();
		try {
			// to handle name with space (%20)
			workingLocation = URLDecoder.decode(workingLocation, "utf-8");
			workingLocation = new File(workingLocation).getPath();
		} catch (UnsupportedEncodingException e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		}
		projectsFolder = new File(workingLocation + "/Projects");
		seperator = "/";
		if (!projectsFolder.exists()) {
			projectsFolder.mkdirs();
		} // Create folder Projects if it does not exist
		// End of create projects folder-------------------------------------------------------------------		
	}		
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void openOrnewJInternalFrame() {
		// create internal frame
		JInternalFrame ProjectInternalFrame = new JInternalFrame(currentProjectName, 
																true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);	
		ProjectInternalFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		
		Panel_YieldProject YieldProjectPanel = new Panel_YieldProject(); // create new panel
		ProjectInternalFrame.add(YieldProjectPanel, BorderLayout.CENTER); // add panel
		ProjectInternalFrame.pack(); // set internal frame to size of contents
		
		spectrumDesktopPane.add(ProjectInternalFrame, BorderLayout.CENTER); // attach internal frame
		ProjectInternalFrame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.25));		
		ProjectInternalFrame.setLocation((int) ((getWidth() - ProjectInternalFrame.getWidth())/2),
				((int) ((getHeight() - ProjectInternalFrame.getHeight())/2.5)));	//Set the ProjectInternalFrame near the center of the Main frame
		if (Spectrum_Main.mainFrameReturn().getSelectedFrame() != null) {	//or Set the ProjectInternalFrame near the recently opened JInternalFrame
			ProjectInternalFrame.setLocation(Spectrum_Main.mainFrameReturn().getSelectedFrame().getX() + 30, Spectrum_Main.mainFrameReturn().getSelectedFrame().getY() + 30);
		}
		ProjectInternalFrame.setVisible(true); // show internal frame
								
//		NewProject.setEnabled(false); //Disable "New" menuItem when a new project is created
		InternalFrameListener ProjectInternalFrame_listener = new InternalFrameListener() {
		      public void internalFrameActivated(InternalFrameEvent e) {

		      }

		      public void internalFrameClosed(InternalFrameEvent e) {

		      }

		      public void internalFrameClosing(InternalFrameEvent e) {
//		        NewProject.setEnabled(true); //Enable "New" menuItem
		    	  
		    	icon = new ImageIcon(getClass().getResource("/icon_question.png"));
		  		scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		  		String ExitOption[] = {"Yes","No"};
				int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(),"We recommend 'stop editing' to save the changes you made. Would you like to close this project ?", "Close Project",
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
		int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(),"All projects will be saved automatically. Would you like to stop SpectrumLite ?", "Exit SpectrumLite",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[0]);
		if (response == 0)
		{
			System.exit(0);
		}
		if (response == 1)
		{
		}
	} 
	
	//All child components will be transparent----------------------------------------------------------------------------------------
	public void setOpaqueForAll(JComponent aComponent, boolean isOpaque) {
		  aComponent.setOpaque(isOpaque);
		  Component[] comps = aComponent.getComponents();
		  for (Component c : comps) {
		    if (c instanceof JComponent) {
		      setOpaqueForAll((JComponent) c, isOpaque);
		    }
		  }
		}	
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1600, 900);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static Panel_BackGroundDesktop mainFrameReturn(){
	   return spectrumDesktopPane;
	  } 
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static String getProjectName() {
		return currentProjectName;
	}

}
