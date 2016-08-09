package spectrumGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import spectrumDatabaseUtil.Panel_DatabaseManagement;
import spectrumYieldProject.Panel_YieldProject;

@SuppressWarnings("serial")
public class Spectrum_Main extends JFrame {
	// Define variables------------------------------------------------------------------------
	private JMenuBar spectrum_Menubar;
	private JMenu MenuFile, MenuUtilities, MenuHelp;
	private JMenuItem NewProject, OpenProject, SaveProject, SaveProjectAs, CloseProject, ExitSoftware; // For MenuFile
	private JMenuItem DatabaseManagement; // For MenuUtilities
	private JMenuItem Contents, About; // For MenuMenuHelpFile
	
	private int OpenProjectCount = 0;
	
	private static Panel_BackGroundDesktop spectrumDesktopPane;
	private static String currentProjectName;

	
	//--------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		new Spectrum_Main();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public Spectrum_Main() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				setTitle("SpectrumLite Demo Version 1.01");
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e){exitSpectrumLite();}});				
				getContentPane().setLayout(new BorderLayout());	

				// Define components: Menubar, Menus, MenuItems----------------------------------
				NewProject = new JMenuItem("New");
				OpenProject = new JMenuItem("Open");
				SaveProject = new JMenuItem("Save");
				SaveProjectAs = new JMenuItem("Save as");
				CloseProject = new JMenuItem("Close");
				ExitSoftware = new JMenuItem("Exit");
				DatabaseManagement = new JMenuItem("Database Management");
				Contents = new JMenuItem("Contents");
				About = new JMenuItem("About");

				MenuFile = new JMenu("File");
				MenuUtilities = new JMenu("Utilities");
				MenuHelp = new JMenu("Help");

				spectrum_Menubar = new JMenuBar();
				spectrumDesktopPane = new Panel_BackGroundDesktop();
				
				// Add components: Menubar, Menus, MenuItems----------------------------------
				MenuFile.add(NewProject);
				MenuFile.add(OpenProject);
				MenuFile.add(SaveProject);
				MenuFile.add(SaveProjectAs);
				MenuFile.add(CloseProject);
				MenuFile.add(ExitSoftware);
				MenuUtilities.add(DatabaseManagement);
				MenuHelp.add(Contents);
				MenuHelp.add(About);

				spectrum_Menubar.add(MenuFile);
				spectrum_Menubar.add(MenuUtilities);
				spectrum_Menubar.add(MenuHelp);

				setJMenuBar(spectrum_Menubar);	
				getContentPane().add(spectrumDesktopPane);
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);

				
				// Add listeners for MenuItems------------------------------------------------
				// Add listeners "New"------------------------------------------------
				NewProject.addActionListener(
						new ActionListener() { // anonymous inner class
							// display new internal window
							public void actionPerformed(ActionEvent event) {
								// create internal frame
								currentProjectName="New Project " + (++OpenProjectCount);
								JInternalFrame ProjectInternalFrame = new JInternalFrame(currentProjectName, 
																						true /*resizable*/, true, /*closable*/true/*maximizable*/, true/*iconifiable*/);																						
								Panel_YieldProject YieldProjectPanel = new Panel_YieldProject(); // create new panel
								ProjectInternalFrame.add(YieldProjectPanel, BorderLayout.CENTER); // add panel
								ProjectInternalFrame.pack(); // set internal frame to size of contents
								
								spectrumDesktopPane.add(ProjectInternalFrame, BorderLayout.CENTER); // attach internal frame
								ProjectInternalFrame.setSize((int) (getWidth()/1.1),(int) (getHeight()/1.5));
								ProjectInternalFrame.setLocation(50 * (OpenProjectCount % 10), 50 * (OpenProjectCount  % 10));
								ProjectInternalFrame.setVisible(true); // show internal frame
														
//								NewProject.setEnabled(false); //Disable "New" menuItem when a new project is created
								InternalFrameListener ProjectInternalFrame_listener = new InternalFrameListener() {
								      public void internalFrameActivated(InternalFrameEvent e) {
								       // dumpInfo("Activated", e);
								      }

								      public void internalFrameClosed(InternalFrameEvent e) {
								       // dumpInfo("Closed", e);
								      }

								      public void internalFrameClosing(InternalFrameEvent e) {
								       // dumpInfo("Closing", e);
//								        NewProject.setEnabled(true); //Enable "New" menuItem
								      }

								      public void internalFrameDeactivated(InternalFrameEvent e) {
								       // dumpInfo("Deactivated", e);
								      }

								      public void internalFrameDeiconified(InternalFrameEvent e) {
								      //  dumpInfo("Deiconified", e);
								      }

								      public void internalFrameIconified(InternalFrameEvent e) {
								      //  dumpInfo("Iconified", e);
								      }

								      public void internalFrameOpened(InternalFrameEvent e) {
								      //  dumpInfo("Opened", e);
								      }

								      private void dumpInfo(String s, InternalFrameEvent e) {
								        System.out.println("Source: " + e.getInternalFrame().getName()
								            + " : " + s);
								      }
								    };
									ProjectInternalFrame.addInternalFrameListener(ProjectInternalFrame_listener);
							} // end method actionPerformed
						} // end anonymous inner class
				); // end call to addActionListener

				
				// Add listeners "ExitSoftware"-----------------------------------------------------
				ExitSoftware.addActionListener(
						new ActionListener() { // anonymous inner class
							// display new internal window
							public void actionPerformed(ActionEvent event) {
								exitSpectrumLite();				
							} // end method actionPerformed
						} // end anonymous inner class
				); // end call to addActionListener
				
								
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
								DatabaseManagement_Frame.setSize((int) (getWidth()/1.3),(int) (getHeight()/1.5));
								DatabaseManagement_Frame.setLocation((int) ((getWidth() - DatabaseManagement_Frame.getWidth())/2),
										((int) ((getHeight() - DatabaseManagement_Frame.getHeight())/3)));	// Set the DatabaseManagement_Frame near the center of the Main frame
								DatabaseManagement_Frame.setVisible(true); // show internal frame
														
								DatabaseManagement.setEnabled(false); //Disable "DatabaseManagement" menuItem when it is already opened
								InternalFrameListener DatabaseInternalFrame_listener = new InternalFrameListener() {
								      public void internalFrameActivated(InternalFrameEvent e) {
								       // dumpInfo("Activated", e);
								      }

								      public void internalFrameClosed(InternalFrameEvent e) {
								       // dumpInfo("Closed", e);
								      }

								      public void internalFrameClosing(InternalFrameEvent e) {
								       // dumpInfo("Closing", e);
								    	  DatabaseManagement.setEnabled(true); //Enable "New" menuItem
								      }

								      public void internalFrameDeactivated(InternalFrameEvent e) {
								       // dumpInfo("Deactivated", e);
								      }

								      public void internalFrameDeiconified(InternalFrameEvent e) {
								      //  dumpInfo("Deiconified", e);
								      }

								      public void internalFrameIconified(InternalFrameEvent e) {
								      //  dumpInfo("Iconified", e);
								      }

								      public void internalFrameOpened(InternalFrameEvent e) {
								      //  dumpInfo("Opened", e);
								      }

//								      private void dumpInfo(String s, InternalFrameEvent e) {
//								        System.out.println("Source: " + e.getInternalFrame().getName()
//								            + " : " + s);
//								      }
								    };
								    DatabaseManagement_Frame.addInternalFrameListener(DatabaseInternalFrame_listener);
							} // end method actionPerformed
						} // end anonymous inner class
				); // end call to addActionListener	
			} //end public void run()					
		}); // end EventQueue.invokeLater
	} // end public Spectrum_Main
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void exitSpectrumLite() {
		String ExitOption[] = {"Yes","No","Cancel"};
		int response = JOptionPane.showOptionDialog(this,"Do you want to save the changes you made to 'Projectname.prj'?", "SpectrumLite" ,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ExitOption,ExitOption[1]);
		if (response == 0) /* Save */
		{
			System.exit(0);
		}
		if (response == 1) /* Don't Save */ 
		{
			System.exit(0);
		}
	} // end public void exitSpectrumLite()

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
