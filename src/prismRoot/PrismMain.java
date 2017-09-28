package prismRoot;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.FontUIResource;

import prismConvenienceClass.ColorUtil;
import prismConvenienceClass.ComponentResizer;
import prismConvenienceClass.FilesHandle;
import prismConvenienceClass.IconHandle;
import prismConvenienceClass.Processing;
import prismConvenienceClass.RequestFocusListener;
import prismConvenienceClass.StringHandle;
import prismConvenienceClass.WindowAppearanceHandle;
import prismDatabase.Panel_DatabaseManagement;
import prismProject.LinkedList_Databases;
import prismProject.Panel_Project;

@SuppressWarnings("serial")
public class PrismMain extends JFrame {
	// Define variables------------------------------------------------------------------------
	private MenuBar_Customize 	prism_Menubar;
	private JMenu 				menuFile, menuUtility, menuWindow, menuHelp,
								menuOpenProject;
	private JMenuItem 			newProject, exitSoftware, 			// For MenuFile
								existingProject, 					// For menuOpenProject
								DatabaseManagement, 				// For MenuUtility
								contents, update, contact, about; 	// For MenuMenuHelp
	
	private JMenuItem					setLogo; 			// For menuWindow
	private MenuItem_SetFont 			setFont;			// For menuWindow
	private MenuItem_SetLookAndFeel 	setLookAndFeel;		// For menuWindow
	private MenuItem_SetTransparency 	setTransparency;	// For menuWindow
	private MenuItem_CaptureGUI 		captureGUI;	// For menuWindow
		
	private static DesktopPanel_BackGround 	prism_DesktopPane;
	private static String 					currentProject;
	private static PrismMain 				main;
	private static ComponentResizer 		cr;
	
	private static LinkedList_Databases databases_linkedlist = new LinkedList_Databases();
	//--------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		if (gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
//			setDefaultLookAndFeelDecorated(true);
			main = new PrismMain();
		 	main.setUndecorated(true);		// to help make translucent windows
			main.setOpacity(0.95f);
			
			//Need border so cr can work
			Border tempBorder = BorderFactory.createMatteBorder(3, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 255));
//			TitledBorder title = BorderFactory.createTitledBorder(tempBorder, "PRISM Demo Version 1.10");
			main.getRootPane().setBorder(tempBorder);
			
			cr = new ComponentResizer();	//Need resize since if "setDefaultLookAndFeelDecorated(true);" then the top corners cannot be resized (java famous bug?)
			cr.registerComponent(main);
			

//			PApplet.main("Processing_Prism");	// Start processing animation
//			try {
//				Runtime.getRuntime().exec("C://Users//Dung Nguyen//Desktop//Planets//application.windows64//Planets.exe", null, new File("C://Users//Dung Nguyen//Desktop//Planets//application.windows64//"));
//			} catch (IOException e) {
//			}
							
		} 		
		
		
		
		// These codes add button to the right
		JLabel test_label = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {					
				Graphics2D g2d = (Graphics2D) g.create();
				// Fill the background, this is VERY important. Fail to do this and you will have major problems
				g2d.setColor(getBackground());
				g2d.fillRect(0, 0, getWidth(), getHeight());
				// Draw the background
				ImageIcon bgImage = IconHandle.get_scaledImageIcon(15, 15, "minionWrite.png");
				Dimension size = this.getSize();
				g2d.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth() - 5, size.height - bgImage.getIconHeight() - 5, this);
				// Paint the component content, i.e. the text
				getUI().paint(g2d, this);
				g2d.dispose();
			}
		};
		test_label.setSize(30, 30);
		test_label.setBackground(ColorUtil.makeTransparent(Color.WHITE, 0));
		main.setLayout(new BorderLayout());
		main.add(test_label, BorderLayout.EAST);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public PrismMain() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
								
				Thread exitThread = new Thread() { // Make a thread
					public void run() {
						try {
//							FilesHandle.ExportResource("/test.jar");
							Processing.playAnimation1();
						} catch (Exception e) {
							System.err.println(e.getClass().getName() + ": " + e.getMessage());
						}
					}
				};
				exitThread.start();
				
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {	// Set Look & Feel
					if (info.getName().equals("Nimbus")) {
						try {
							UIManager.setLookAndFeel(info.getClassName());
							UIManager.getLookAndFeelDefaults().put("info", new Color(255, 250, 205));		// Change the ugly yellow color of ToolTip --> lemon chiffon
//							UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Century Schoolbook", Font.PLAIN, 12));
							WindowAppearanceHandle.setUIFont(new FontUIResource("Century Schoolbook", Font.PLAIN, 12));		//Change Font for the current LAF
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e1) {
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}
					}
				}		

				
				setIconImage(new ImageIcon(getClass().getResource("/icon_main.png")).getImage());
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e){exitPRISM();}});				
				getContentPane().setLayout(new BorderLayout());	

				
				prism_DesktopPane = new DesktopPanel_BackGround();
				prism_Menubar = new MenuBar_Customize();
				
								
				// Define components: Menubar, Menus, MenuItems----------------------------------
				newProject = new JMenuItem("New");
				menuOpenProject = new JMenu("Open");
				exitSoftware = new JMenuItem("Exit");
				DatabaseManagement = new JMenuItem("Database Management");
				DatabaseManagement.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_database.png"));
				setLogo = new JMenuItem("Hide Logo");
				setFont = new MenuItem_SetFont(main);
				setTransparency = new MenuItem_SetTransparency(main);
				setLookAndFeel = new MenuItem_SetLookAndFeel(main, cr);
				captureGUI = new MenuItem_CaptureGUI(main);
				contents = new JMenuItem("Contents");
				update = new JMenuItem("Check for updates");
				contact = new JMenuItem("Contact us");
				about = new JMenuItem("About PRISM");

				menuFile = new JMenu("File");
				menuUtility = new JMenu("Utility");
				menuWindow = new JMenu("Window");
				menuHelp = new JMenu("Help");

				
				// Add components: Menubar, Menus, MenuItems----------------------------------
				menuFile.add(newProject);
				menuFile.add(menuOpenProject);
				menuFile.add(exitSoftware);
				menuUtility.add(DatabaseManagement);
				menuWindow.add(captureGUI);
				menuWindow.add(setLogo);	
				menuWindow.add(setTransparency);
				menuWindow.add(setFont);
				menuWindow.add(setLookAndFeel);
				
				
				menuHelp.add(contents);
				menuHelp.add(update);
				menuHelp.add(contact);
				menuHelp.add(about);

				prism_Menubar.add(menuFile);
				prism_Menubar.add(menuUtility);
				prism_Menubar.add(menuWindow);
				prism_Menubar.add(menuHelp);
				prism_Menubar.addFrameFeatures();

				setJMenuBar(prism_Menubar);	
				getContentPane().add(prism_DesktopPane);
				WindowAppearanceHandle.setOpaqueForAll(prism_Menubar, false);
				WindowAppearanceHandle.setOpaqueForAll(prism_DesktopPane, false);
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);

				
				
				// Add listener for "Window"------------------------------------------------
				menuWindow.addMenuListener(new MenuListener() {
					@Override
			        public void menuSelected(MenuEvent e) {						
						//Only allow to change look and feel if No Frame is opened, this is to prevent fail performance of the components after changing look and feel
						if (PrismMain.get_Prism_DesktopPane().getAllFrames().length ==  0) {
							setLookAndFeel.setEnabled(true);
							setFont.setEnabled(true);
						} else {
							setLookAndFeel.setEnabled(false);
							setFont.setEnabled(false);
						}		
					}

					@Override
					public void menuDeselected(MenuEvent e) {
						
					}

					@Override
					public void menuCanceled(MenuEvent e) {
						
					}
				});
				
				
				// Add listeners "New"------------------------------------------------
//				newProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));	// CTRL on Windows, *** on MAC-OS
				newProject.setMnemonic(KeyEvent.VK_N);
				newProject.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						//JtextField to type name
						JTextField projectName_JTextField = new JTextField(35);
						projectName_JTextField.getDocument().addDocumentListener(new DocumentListener() {
							@Override  
							public void changedUpdate(DocumentEvent e) {
								currentProject = projectName_JTextField.getText().trim();		//Trim spaces at the begin and end of the string
							}
							public void removeUpdate(DocumentEvent e) {
								currentProject = projectName_JTextField.getText().trim();		//Trim spaces at the begin and end of the string
							}
							public void insertUpdate(DocumentEvent e) {
								currentProject = projectName_JTextField.getText().trim();		//Trim spaces at the begin and end of the string
							}
						});
						projectName_JTextField.setText("new_project");		//default name
						projectName_JTextField.addAncestorListener( new RequestFocusListener());	//Set focus (mouse cursor on the JTextField)
						
						
						boolean stop_naming = false;
						String titleText = "Project's name";
						while (stop_naming == false) {
					  		String ExitOption[] = {"OK","Cancel"};
							int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), projectName_JTextField, titleText,
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
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
								if (StringHandle.nameIsValid(currentProject)==true && !existingName_list.contains(currentProject)) {
									if (new File(FilesHandle.get_projectsFolder().getAbsolutePath() + "/" + currentProject).mkdir()) {		//try if can create a folder with the existing project name
										createNewJInternalFrame();		//create new internal frame for this existing project
										stop_naming = true;
									}
								} else {
									titleText = "Name already exists or contains special characters. Try a different name:";									
								}
							} 		
							
							else {
								stop_naming = true;
							}
					    }
						
					}
				});	

				
				// Add listeners "Open"------------------------------------------------
				menuOpenProject.setMnemonic(KeyEvent.VK_O);
				menuOpenProject.addMenuListener(new MenuListener() {
					@Override
			        public void menuSelected(MenuEvent e) {					
						menuOpenProject.removeAll();			// Remove all existing projects
										
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
									menuOpenProject.add(existingProject);			// Add all existing projects

									existingProject.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent event) {
											currentProject = fileName;
											
											JInternalFrame[] opened_InternalFrames = PrismMain.get_Prism_DesktopPane().getAllFrames();	// All displayed internalFrames
											List<String> openedFrames_list = new ArrayList<String>();					// List of Frames Names					
											for (int i = 0; i < opened_InternalFrames.length; i++) {
												openedFrames_list.add(opened_InternalFrames[i].getTitle());		// Loop all displayed IFrames to get Names and add to the list
											}
											
											// Only open if it is not opened yet
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
				
				
				// Add listeners "setLogo"-----------------------------------------------------
				setLogo.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_main.png"));
				setLogo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
				setLogo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (prism_DesktopPane.getBackgroundImage() != null) {
							prism_DesktopPane.setBackgroundImage(null);
							setLogo.setText("Show Logo");
						} else {
							prism_DesktopPane.process_image();
							setLogo.setText("Hide Logo");
						}						
					}
				});
				
				
				// Add listeners "ExitSoftware"-----------------------------------------------------
				exitSoftware.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
				exitSoftware.setMnemonic(KeyEvent.VK_E);
				exitSoftware.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						exitPRISM();
					}
				});
				
								
				// Add listeners "DatabaseManagement"------------------------------------------------
				DatabaseManagement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK));
				DatabaseManagement.addActionListener(
						new ActionListener() { // anonymous inner class
							// display new internal window
							public void actionPerformed(ActionEvent event) {
								// create internal frame
								JInternalFrame DatabaseManagement_Frame = new JInternalFrame("Database Management", 
																						true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);								
								
								prism_DesktopPane.add(DatabaseManagement_Frame, BorderLayout.CENTER); // attach internal frame
								DatabaseManagement_Frame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.25));
								DatabaseManagement_Frame.setLocation((int) ((getWidth() - DatabaseManagement_Frame.getWidth())/2),
										((int) ((getHeight() - DatabaseManagement_Frame.getHeight())/2.5)));	//Set the DatabaseManagement_Frame near the center of the Main frame
								if (PrismMain.get_Prism_DesktopPane().getSelectedFrame() != null) {	//or Set the DatabaseManagement_Frame near the recently opened JInternalFrame
									DatabaseManagement_Frame.setLocation(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getX() + 25, PrismMain.get_Prism_DesktopPane().getSelectedFrame().getY() + 25);
								}
								
								// Note: visible first for the JIframe to be selected, pack at the end would be fail for JIframe to be selected (PrismMain.mainFrameReturn().getSelectedFrame = null)
								DatabaseManagement_Frame.setVisible(true); // show internal frame					
								Panel_DatabaseManagement DatabaseManagementPanel = new Panel_DatabaseManagement(); // create new panel
								DatabaseManagement_Frame.add(DatabaseManagementPanel, BorderLayout.CENTER); // add panel
//								DatabaseManagement_Frame.pack(); // set internal frame to size of contents
								

								DatabaseManagement.setEnabled(false); //Disable "DatabaseManagement" menuItem when it is already opened
								InternalFrameListener DatabaseInternalFrame_listener = new InternalFrameListener() {
								      public void internalFrameActivated(InternalFrameEvent e) {
								   
								      }

								      public void internalFrameClosed(InternalFrameEvent e) {
								    	  DatabaseManagement.setEnabled(true); //Enable
								      }

								      public void internalFrameClosing(InternalFrameEvent e) {							 

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
	} // end public Prism_Main
			
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void createNewJInternalFrame() {
		// create internal frame
		JInternalFrame ProjectInternalFrame = new JInternalFrame(currentProject, 
																true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);	
		ProjectInternalFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
				
		prism_DesktopPane.add(ProjectInternalFrame, BorderLayout.CENTER); // attach internal frame
		ProjectInternalFrame.setSize((int) (getWidth()/1.10),(int) (getHeight()/1.25));		
		ProjectInternalFrame.setLocation((int) ((getWidth() - ProjectInternalFrame.getWidth())/2),
				((int) ((getHeight() - ProjectInternalFrame.getHeight())/2.7)));	//Set the ProjectInternalFrame near the center of the Main frame
		if (PrismMain.get_Prism_DesktopPane().getSelectedFrame() != null) {	//or Set the ProjectInternalFrame near the recently opened JInternalFrame
			ProjectInternalFrame.setLocation(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getX() + 25, PrismMain.get_Prism_DesktopPane().getSelectedFrame().getY() + 25);
		}
			
		// Note: visible first for the JIframe to be selected, pack at the end would be fail for JIframe to be selected (PrismMain.mainFrameReturn().getSelectedFrame = null)
		ProjectInternalFrame.setVisible(true); // show internal frame	
		Panel_Project YieldProjectPanel = new Panel_Project(currentProject); // create new panel
		ProjectInternalFrame.add(YieldProjectPanel, BorderLayout.CENTER); // add panel
//		ProjectInternalFrame.pack(); // set internal frame to size of contents
		
								
		InternalFrameListener ProjectInternalFrame_listener = new InternalFrameListener() {
		      public void internalFrameActivated(InternalFrameEvent e) {

		      }

		      public void internalFrameClosed(InternalFrameEvent e) {

		      }

		      public void internalFrameClosing(InternalFrameEvent e) {
		  		String ExitOption[] = {"Close","Cancel"};
				int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Stop Editing can save changes you made. Close project ?", "Close Project",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
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
	public void exitPRISM() {
		String ExitOption[] = {"Exit","Cancel"};
		int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Stop Editing can save changes you made. Exit PRISM ?", "Exit PRISM",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
		if (response == 0) {		
//			main.setVisible(false);
//			Thread exitThread = new Thread() { // Make a thread for output5
//				public void run() {
//					try {
//						PApplet.main("Processing_Prism"); // Start processing animation						
//						sleep(10000);
//					} catch (InterruptedException e) {
//					} finally {
//						System.exit(0);
//						this.interrupt();
//					}
//				}
//			};
//			exitThread.start();
			System.exit(0);
		}
		if (response == 1) {
		}
	}
	
	public void minimize() {
		main.setExtendedState(JFrame.ICONIFIED);
	}

	public void restore() {
		if (main.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
			main.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} else {
			main.setExtendedState(JFrame.NORMAL);
		}		
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Override
	public Dimension getPreferredSize() {
		// Check multi-monitor screen resolution
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		if (width >= 1600 && height >= 900) {
			return new Dimension(1600, 900);
		} else {
			return new Dimension((int) (width * 0.85), (int) (height * 0.85));
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static DesktopPanel_BackGround get_Prism_DesktopPane() {
		return prism_DesktopPane;
	}
	
	public static PrismMain get_main() {
		return main;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static String getProjectName() {
		return currentProject;
	}

	public static LinkedList_Databases get_databases_linkedlist() {
		return databases_linkedlist;
	}
	
}
