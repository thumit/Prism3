package spectrumConvenienceClasses;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import spectrumROOT.Spectrum_Main;
import spectrumYieldProject.Panel_EditRun_Details;

public class FilesHandle {
	
	
	public FilesHandle() {

	}
	 
	
	public static String get_workingLocation() {
		// Get working location of spectrumLite
		String workingLocation;

		// Get working location of the IDE project, or runnable jar file
		final File jarFile = new File(Spectrum_Main.get_spectrumDesktopPane().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		workingLocation = jarFile.getParentFile().toString();

		// Make the working location with correct name
		try {
			// to handle name with space (%20)
			workingLocation = URLDecoder.decode(workingLocation, "utf-8");
			workingLocation = new File(workingLocation).getPath();
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return workingLocation;
	}
	 
	
	public static File get_projectsFolder() {		
		String workingLocation = get_workingLocation();
		File projectsFolder = new File(workingLocation + "/Projects");
		
//		final File jarFile = new File(Spectrum_Main.mainFrameReturn().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//		if (jarFile.isFile()) { // Run with JAR file
//			projectsFolder = new File(":Projects");
//		} else {
//			projectsFolder = new File(workingLocation + "/Projects");
//		}

		// Check if Projects folder exists, if not then create it
		if (!projectsFolder.exists()) {
			projectsFolder.mkdirs();
		} // Create folder Projects if it does not exist
		
		return projectsFolder;
	} 
	 

	public static File get_DatabasesFolder() {		
		String workingLocation = get_workingLocation();
		File databasesFolder = new File(workingLocation + "/Databases");
		
//		final File jarFile = new File(Spectrum_Main.mainFrameReturn().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//		if (jarFile.isFile()) { // Run with JAR file
//			databasesFolder = new File(":Databases");
//		} else {
//			databasesFolder = new File(workingLocation + "/Databases");
//		}

		// Check if Databases folder exists, if not then create it
		if (!databasesFolder.exists()) {
			databasesFolder.mkdirs();
		} // Create folder Databases if it does not exist
		
		return databasesFolder;
	}	

	public static File get_temporaryFolder() {		
		String workingLocation = get_workingLocation();
		File temporaryFolder = new File(workingLocation + "/Temporary");
		
//		final File jarFile = new File(Spectrum_Main.mainFrameReturn().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//		if (jarFile.isFile()) { // Run with JAR file
//			temporaryFolder = new File(":Temporary");
//		} else {
//			temporaryFolder = new File(workingLocation + "/Temporary");
//		}

		// Check if Temporary folder exists, if not then create it
		if (!temporaryFolder.exists()) {
			temporaryFolder.mkdirs();
		} // Create folder Temporary if it does not exist
		
		return temporaryFolder;
	}	
	
	
	public static File chosenDatabase() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(get_DatabasesFolder());
		chooser.setDialogTitle("Select database file");
		chooser.setMultiSelectionEnabled(false);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import database of the existing strata from the selected file");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Database file '.db'", "db");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnValue = chooser.showOpenDialog(Spectrum_Main.get_spectrumDesktopPane());
		File file = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}

		return file;
	}			
	
	
	public static File chosenDefinition() {
		File file = null;
			
		ImageIcon icon = new ImageIcon(Spectrum_Main.get_spectrumDesktopPane().getClass().getResource("/icon_question.png"));
		Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		String ExitOption[] = {"New definition","Default definition","Cancel"};
		int response = JOptionPane.showOptionDialog(Spectrum_Main.get_spectrumDesktopPane(),"Except General Inputs, everything will be reset. Your option ?", "Import Strata Definition",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[2]);
		if (response == 0)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(get_workingLocation()));
			chooser.setDialogTitle("Select strata definition file");
			chooser.setMultiSelectionEnabled(false);
			
			chooser.setApproveButtonText("Import");
			chooser.setApproveButtonToolTipText("Import strata definition from the selected file");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Strata Definition File '.csv' '.txt'", "csv", "txt");
			chooser.setFileFilter(filter);
			chooser.setAcceptAllFileFilterUsed(false);
			
			int returnValue = chooser.showOpenDialog(Spectrum_Main.get_spectrumDesktopPane());
			if (returnValue == JFileChooser.APPROVE_OPTION) {	//Return the new Definition as in the selected file
				file = chooser.getSelectedFile();
			}
		}
		if (response == 1)	
		{
			try {
				File file_StrataDefinition = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "strata_definition.csv");	
				file_StrataDefinition.deleteOnExit();
					
				InputStream initialStream = Panel_EditRun_Details.class.getResourceAsStream("/strata_definition.csv");		//Default definition
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);

				OutputStream outStream = new FileOutputStream(file_StrataDefinition);
				outStream.write(buffer);

				initialStream.close();
				outStream.close();

				file = file_StrataDefinition;
			} catch (FileNotFoundException e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			} catch (IOException e2) {
				System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
			}
		}	
		
		return file;
	}	
	
}
