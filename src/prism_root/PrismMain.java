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


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
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

import prism_convenience_class.ColorUtil;
import prism_convenience_class.ComponentResizer;
import prism_convenience_class.FilesHandle;
import prism_convenience_class.IconHandle;
import prism_convenience_class.MenuScroller;
import prism_convenience_class.Processing;
import prism_convenience_class.RequestFocusListener;
import prism_convenience_class.StringHandle;
import prism_convenience_class.WindowAppearanceHandle;
import prism_database.Panel_DatabaseManagement;
import prism_project.Panel_Project;
import prism_project.data_process.LinkedList_Databases;

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
	private MenuItem_CaptureGUI 		captureGUI;			// For menuWindow
		
	private static String 					prism_version = "PRISM ALPHA 1.2.01";
	private String 							currentProject;
	private static DesktopPanel_BackGround 	prism_DesktopPane;
	private Repaint_JPanel 					content_panel;
	private static PrismMain 				main;
	private static ComponentResizer 		cr;
	
	private static LinkedList_Databases databases_linkedlist = new LinkedList_Databases();

	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		if (gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
//			setDefaultLookAndFeelDecorated(true);												// 1: activate this 1 with 2  --> then we can disable 2 lines in the middle
			main = new PrismMain();
		 	main.setUndecorated(true);		// to help make translucent windows
			main.setOpacity(0.92f);
//			main.setBackground(new Color(0, 0, 0, 0.0f)); // alpha <1 = transparent;			// 2: activate this 2 with 1  --> then we can disable 2 lines in the middle
			
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
							File memory_file = new File(FilesHandle.get_temporaryFolder() + "/prism_memory.txt");	// Store the last time MAx Memory is saved by users: just an integer number
							String font_name = "Century Schoolbook";
							int font_size = 12;
							try {		
								List<String> list = Files.readAllLines(Paths.get(memory_file.getAbsolutePath()), StandardCharsets.UTF_8);			
								font_name = list.get(2).split("\t")[0];		// space delimited between Font Name and Font Size
								font_size = Integer.parseInt(list.get(2).split("\t")[1]);	// space delimited between Font Name and Font Size
							} catch (Exception e) {
								System.out.println("File prism_memory.txt does not exists");
							} finally {
								UIManager.getLookAndFeelDefaults().put("info", new Color(255, 250, 205));		// Change the ugly yellow color of ToolTip --> lemon chiffon
								UIManager.getLookAndFeelDefaults().put("defaultFont", new Font(font_name, Font.PLAIN, font_size));	// Since the update to eclipse Oxygen and update to java9, 
																																				// this line is required to make it not fail when click File --> Open after changing Look and Feel in Eclise IDE
								WindowAppearanceHandle.setUIFont(new FontUIResource(font_name, Font.PLAIN, font_size));				// Change Font for the current LAF
							}
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}
						SwingUtilities.updateComponentTreeUI(main);
					}
				}		

				
				setIconImage(new ImageIcon(getClass().getResource("/icon_main.png")).getImage());
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e){exitPRISM();}});
				prism_DesktopPane = new DesktopPanel_BackGround();
				prism_Menubar = new MenuBar_Customize();
				
								
				// Define components: Menubar, Menus, MenuItems----------------------------------
				newProject = new JMenuItem("New");
				menuOpenProject = new JMenu("Open");	MenuScroller.setScrollerFor(menuOpenProject, 6, 125, 3, 1);
				exitSoftware = new JMenuItem("Exit");
				DatabaseManagement = new JMenuItem("Database Management");
				DatabaseManagement.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_database.png"));
				setLogo = new JMenuItem("Hide Logo");
				setFont = new MenuItem_SetFont(main);
				setTransparency = new MenuItem_SetTransparency(main);
				setLookAndFeel = new MenuItem_SetLookAndFeel(main, cr);
				captureGUI = new MenuItem_CaptureGUI();
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
				
//				JPanel content_panel = new JPanel() {
//					private int angle = 200;
//		            @Override
//		            protected void paintComponent(Graphics g) {
//		                if (g instanceof Graphics2D) {
//							final int R = 0;
//							final int G = 0;
//							final int B = 0;
////							Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0.3f), 0.0f, getHeight(), new Color(0, 130, 180, 150), true);
////							Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 130, 180, 200), 0.0f, getHeight(), new Color(R, G, B, 0.9f), true);
//							Paint p = new GradientPaint(0.0f, getHeight() / 3, new Color(R, G, B, 0.05f), getHeight() / 3, getHeight() / 2, new Color(0, 130, 180, angle), true);
////							final int R = 240;
////							final int G = 240;
////							final int B = 240;
////							Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 255), 0.0f, getHeight(), new Color(0, 130, 180, 125), true);
//							Graphics2D g2d = (Graphics2D) g;
//							g2d.setPaint(p);
//							g2d.fillRect(0, 0, getWidth(), getHeight());
//		                }
//		            }
//
//		        };
				content_panel = new Repaint_JPanel();	// This line uses repainted JPanel so the above codes could be ignored
		        setContentPane(content_panel);
				getContentPane().setLayout(new BorderLayout());	
				getContentPane().add(prism_DesktopPane);
				// testing transparent, works by activating the below line, but font is in bad quality. 2 System.setProperty lines probably works on old java (before 9) for anti-alias, but not for java 9
//				setBackground(new Color(0, 0, 0, 0.0f)); // alpha <1 = transparent
			 	prism_DesktopPane.setBackground(new Color(0, 0, 0, 0)); // alpha <1 = transparent
				System.setProperty("awt.useSystemAAFontSettings", "on");
				System.setProperty("swing.aatext", "true");
			 	/*
			 	-Dawt.useSystemAAFontSettings=on -Dswing.aatext=true			// --> use for VMargument
			 	*/
				WindowAppearanceHandle.setOpaqueForAll(prism_Menubar, false);
				WindowAppearanceHandle.setOpaqueForAll(content_panel, false);
				WindowAppearanceHandle.setOpaqueForAll(prism_DesktopPane, false);
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
				
				
				// Allow users to modify max heap size on PRISM start up------------------------------------------------------
				content_panel.stop_painting();
				OptionPane_Startup.Set_Memory();
				continue_painting_content_panel_if_no_internal_frame_opened();
				
								
				// Add listener for "Window"-----------------------------------------------------------------
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
							if (response == 0) {
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
								if (StringHandle.nameIsValid(currentProject) == true && !existingName_list.contains(currentProject) && !currentProject.equals("Database Management")) {
									if (new File(FilesHandle.get_projectsFolder().getAbsolutePath() + "/" + currentProject).mkdir()) {		//try if can create a folder with the existing project name
										create_project_internal_frame(currentProject);		//create new internal frame for this existing project
										stop_naming = true;
									}
								} else {
									titleText = "Name exists or contains special characters. Try a different name:";									
								}
							} else {
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
											create_project_internal_frame(currentProject); // IF the project exists --> creates mean Open
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
				
				
				// Disable the functions that are not yet written
				contents.setEnabled(false);
				update.setEnabled(false);
				contact.setEnabled(false);
				
				
				// Add listeners "about"-----------------------------------------------------
				about.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_main.png"));
//				about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
				about.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						new OptionPane_About();					
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
				DatabaseManagement.addActionListener(new ActionListener() { // anonymous inner class
					public void actionPerformed(ActionEvent event) {
						boolean is_Database_Management_opened = false;
						
						// display Database Management if it was already created
						for (JInternalFrame i : PrismMain.get_Prism_DesktopPane().getAllFrames()) {		// Loop all displayed internalFrames
							if (i.getTitle().equals("Database Management")) {
								is_Database_Management_opened = true;
								if (!i.isSelected()) {	 // if it is not selected --> select it
									try {
										i.setSelected(true);
									} catch (PropertyVetoException e1) {
										e1.printStackTrace();
									}
								} else {	// if it is selected --> close
									i.dispose();
								}
							}
						} 
														
						// if not exist, create new internal frame
						if (!is_Database_Management_opened) {
							JInternalFrame DatabaseManagement_Frame = new JInternalFrame("Database Management", true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);								
							prism_DesktopPane.add(DatabaseManagement_Frame, BorderLayout.CENTER); // attach internal frame
							DatabaseManagement_Frame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.21));
							DatabaseManagement_Frame.setLocation((int) ((getWidth() - DatabaseManagement_Frame.getWidth())/2),
																((int) ((getHeight() - DatabaseManagement_Frame.getHeight())/2.75)));	//Set the DatabaseManagement_Frame near the center of the Main frame
							if (PrismMain.get_Prism_DesktopPane().getSelectedFrame() != null) {	//or Set the DatabaseManagement_Frame near the recently opened JInternalFrame
								DatabaseManagement_Frame.setLocation(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getX() + 25, PrismMain.get_Prism_DesktopPane().getSelectedFrame().getY() + 25);
							}
							
							// Note: visible first for the JIframe to be selected, pack at the end would be fail for JIframe to be selected (PrismMain.mainFrameReturn().getSelectedFrame = null)
							DatabaseManagement_Frame.setVisible(true); // show internal frame					
							DatabaseManagement_Frame.add(new Panel_DatabaseManagement(), BorderLayout.CENTER); // add panel
							DatabaseManagement_Frame.addInternalFrameListener(new InternalFrameListener() {
								public void internalFrameActivated(InternalFrameEvent e) {

								}

								public void internalFrameClosed(InternalFrameEvent e) {
									continue_painting_content_panel_if_no_internal_frame_opened();
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
							});
							continue_painting_content_panel_if_no_internal_frame_opened();
						}
					}
				});
							
			} // end public void run()
		}); // end EventQueue.invokeLater
	} // end public Prism_Main
			
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void create_project_internal_frame(String currentProject) {
		boolean is_currentProject_opened = false;
		
		// display project if it was already created
		for (JInternalFrame i : PrismMain.get_Prism_DesktopPane().getAllFrames()) {		// Loop all displayed internalFrames
			if (i.getTitle().equals(currentProject)) {
				try {
					i.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				is_currentProject_opened = true;
			}
		} 
										
		// if not exist, create new internal frame
		if (!is_currentProject_opened) {
			// create internal frame
			JInternalFrame ProjectInternalFrame = new JInternalFrame(currentProject, true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);	
			ProjectInternalFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
					
			prism_DesktopPane.add(ProjectInternalFrame, BorderLayout.CENTER); // attach internal frame
			ProjectInternalFrame.setSize((int) (getWidth()/1.08),(int) (getHeight()/1.21));		
			ProjectInternalFrame.setLocation((int) ((getWidth() - ProjectInternalFrame.getWidth())/2),
											((int) ((getHeight() - ProjectInternalFrame.getHeight())/2.75)));	//Set the ProjectInternalFrame near the center of the Main frame
			if (PrismMain.get_Prism_DesktopPane().getSelectedFrame() != null) {	//or Set the ProjectInternalFrame near the recently opened JInternalFrame
				ProjectInternalFrame.setLocation(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getX() + 25, PrismMain.get_Prism_DesktopPane().getSelectedFrame().getY() + 25);
			}
				
			// Note: visible first for the JIframe to be selected, pack at the end would be fail for JIframe to be selected (PrismMain.mainFrameReturn().getSelectedFrame = null)
			ProjectInternalFrame.setVisible(true); // show internal frame	
			ProjectInternalFrame.add(new Panel_Project(currentProject), BorderLayout.CENTER); // add panel
			ProjectInternalFrame.addInternalFrameListener(new InternalFrameListener() {
				public void internalFrameActivated(InternalFrameEvent e) {

				}

				public void internalFrameClosed(InternalFrameEvent e) {

				}

				public void internalFrameClosing(InternalFrameEvent e) {
					String ExitOption[] = { "Close", "Cancel" };
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), "Close now?",
							"Close Project", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
					if (response == 0) {
						ProjectInternalFrame.dispose();
						continue_painting_content_panel_if_no_internal_frame_opened();
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
			});
			continue_painting_content_panel_if_no_internal_frame_opened();
		}
	}
	 
	//--------------------------------------------------------------------------------------------------------------------------------
	public void exitPRISM() {
		String ExitOption[] = {"Exit","Cancel"};
		int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Exit now?", "Exit PRISM",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
		if (response == 0) {		
//			main.setVisible(false);
//			Thread exitThread = new Thread() { // Make a thread
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
	public void continue_painting_content_panel_if_no_internal_frame_opened() {
		if (PrismMain.get_Prism_DesktopPane().getAllFrames().length == 0) { // the case no internal frame is opened
			content_panel.start_painting();
		} else { // the case at least one internal frame is opened
			content_panel.stop_painting();
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
	public static String get_prism_version() {
		return prism_version;
	}
	
	public static DesktopPanel_BackGround get_Prism_DesktopPane() {
		return prism_DesktopPane;
	}
	
	public static PrismMain get_main() {
		return main;
	}

	public static LinkedList_Databases get_databases_linkedlist() {
		return databases_linkedlist;
	}
	
	

	
	// PRISM super panel + rotator classes. I am proud of myself!!!
	private class Rotator extends Timer implements ActionListener {
		private Repaint_JPanel panel;
		private double angle;
		private double demo_time = 0;

		Rotator(final Repaint_JPanel panel) {
			super(50, null);
			this.panel = panel;
			this.angle = panel.get_angle();
			addActionListener(this);
		}

		public void actionPerformed(final ActionEvent event) {
			if (angle <= 10) {
				angle = angle + 0.5;
			} else {
				angle = angle + 0.03;
			}
			
	        if (angle > 30) {
	            angle = 0.5;
	            panel.set_random_paint();
	        }
	        panel.set_angle(angle);
	        panel.revalidate();
	        panel.repaint();
	        
	        demo_time = demo_time + 1;
//	        if (demo_time == 600) stop();   // each 200 units of 50 millisoconds = 10 seconds			// Activate this line if we want the rotator stops after certain time
//	        System.out.println(angle + " " + demo_time);
		}
	}
	
	private class Repaint_JPanel extends JPanel {
		final Rotator rotator;
		private double angle = 0.5;
		private float horizon_of_point_one = 0.0f;
		private float horizon_of_point_two = 289.0f;
		
		{
			rotator = new Rotator(this);
	        rotator.start(); 
	        
	        addMouseListener(new MouseAdapter() { // Add listener to projectTree
				boolean is_rotating = true;
				public void mousePressed(MouseEvent e) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						if (is_rotating) {
							stop_painting();
							is_rotating = false;
						} else {
							start_painting();
							is_rotating = true;
						}
					}
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (g instanceof Graphics2D) {
				final int R = 0;
				final int G = 0;
				final int B = 0;
//				Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0.3f), 0.0f, getHeight(), new Color(0, 130, 180, 150), true);
//				Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 130, 180, 200), 0.0f, getHeight(), new Color(R, G, B, 0.9f), true);
//				Paint p = new GradientPaint(0.0f, getHeight() / 3, new Color(R, G, B, 0.05f), getHeight() / 3, (float) (getHeight() / angle), new Color(0, (int) (5 * angle + 100), 180, (int) (5 * angle + 100)), true);
				Paint p = new GradientPaint(horizon_of_point_one, getHeight() / 3, new Color(R, G, B, 0.05f), horizon_of_point_two, (float) (getHeight() / angle), new Color(0, (int) (5 * angle + 100), 180, (int) (5 * angle + 100)), true);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(p);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		}
            
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(this.getWidth(), this.getHeight());
		}

		public void set_angle(double new_angle) {
			this.angle = new_angle;
		}

		public double get_angle() {
			return this.angle;
		}
		
		public void set_random_paint() {
			horizon_of_point_one = new Random().nextInt(600) + 1;
			horizon_of_point_two = 600 - new Random().nextInt(600);
		}
		
		public void start_painting() {
			if (!rotator.isRunning()) rotator.start();
		}
		
		public void stop_painting() {
			if (rotator.isRunning()) rotator.stop();
//			angle = 13;		// activate these codes if you want a good display when Rotator stops
//			revalidate();
//	        repaint();
		}
	}
}
