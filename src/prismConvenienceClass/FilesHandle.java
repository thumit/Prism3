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
package prismConvenienceClass;

import java.awt.Dimension;
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

import prismProject.Panel_EditRun_Details;
import prismRoot.PrismMain;

public class FilesHandle {
	
	
	public FilesHandle() {

	}
	

//    static public String ExportResource(String resourceName) throws Exception {
//    	/**
//         * Export a resource embedded into a Jar file to the local file path.
//         *
//         * @param resourceName ie.: "/SmartLibrary.dll"
//         * @return The path to the exported resource
//         * @throws Exception
//         */
//    	
//        InputStream stream = null;
//        OutputStream resStreamOut = null;
//        String jarFolder;
//        try {
//            stream = Spectrum_Main.class.getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
//			if (stream == null) {
//				throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
//			}
//
//			int readBytes;
//			byte[] buffer = new byte[4096];
//			jarFolder = new File(
//					Spectrum_Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
//							.getParentFile().getPath().replace('\\', '/');
//			resStreamOut = new FileOutputStream(jarFolder + "/Temporary" + resourceName);
//			while ((readBytes = stream.read(buffer)) > 0) {
//				resStreamOut.write(buffer, 0, readBytes);
//			}
//		} catch (Exception ex) {
//			throw ex;
//		} finally {
//			stream.close();
//			resStreamOut.close();
//		}
//		return jarFolder + "/Temporary" + resourceName;
//	}
    
    
	public static File getResourceAsJarFile(String resourcePath) {
		File file_animation = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "animation.jar");
		file_animation.deleteOnExit();
		try {
			InputStream initialStream = PrismMain.get_main().getClass().getResourceAsStream("/test.jar");
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);

			OutputStream outStream = new FileOutputStream(file_animation);
			outStream.write(buffer);

			initialStream.close();
			outStream.close();
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
		}
		return file_animation;
	}
	
	
	public static String get_workingLocation() {
		// Get working location of spectrumLite
		String workingLocation;

		// Get working location of the IDE project, or runnable jar file
		final File jarFile = new File(PrismMain.get_main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
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
		chooser.setPreferredSize(new Dimension(800, 500));
		chooser.setCurrentDirectory(get_DatabasesFolder());
		chooser.setDialogTitle("Select database file");
		chooser.setMultiSelectionEnabled(false);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import database of the existing strata from the selected file");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Database file '.db'", "db");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnValue = chooser.showOpenDialog(PrismMain.get_main());
		File file = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}

		return file;
	}			
	
	
	public static File get_file_dbms_system_sql_library() {
		// Read sql_library from the system
		File file_dbms_system_sql_library = null;
		try {
			file_dbms_system_sql_library = new File(get_temporaryFolder().getAbsolutePath() + "/" + "dbms_system_sql_library.txt");
			file_dbms_system_sql_library.deleteOnExit();

			InputStream initialStream = PrismMain.get_main().getClass().getResourceAsStream("/dbms_system_sql_library.txt");
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);

			OutputStream outStream = new FileOutputStream(file_dbms_system_sql_library);
			outStream.write(buffer);

			initialStream.close();
			outStream.close();
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
		} 
		return file_dbms_system_sql_library;
	}
	
	
	public static File get_file_dbms_user_sql_library() {
		File file_dbms_user_sql_library = new File(get_temporaryFolder().getAbsolutePath() + "/" + "dbms_user_sql_library.txt");
		return file_dbms_user_sql_library;
	}
	
	
	public static File get_file_output_system_sql_library() {
		// Read sql_library from the system
		File file_output_system_sql_library = null;
		try {
			file_output_system_sql_library = new File(get_temporaryFolder().getAbsolutePath() + "/" + "output_system_sql_library.txt");
			file_output_system_sql_library.deleteOnExit();

			InputStream initialStream = PrismMain.get_main().getClass().getResourceAsStream("/output_system_sql_library.txt");
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);

			OutputStream outStream = new FileOutputStream(file_output_system_sql_library);
			outStream.write(buffer);

			initialStream.close();
			outStream.close();
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
		} 
		return file_output_system_sql_library;
	}
	
	
	public static File get_file_output_user_sql_library() {
		File file_output_user_sql_library = new File(get_temporaryFolder().getAbsolutePath() + "/" + "output_user_sql_library.txt");
		return file_output_user_sql_library;
	}
	
	
	public static File chosenDefinition() {
		File file = null;
			
		ImageIcon icon = new ImageIcon(PrismMain.get_main().getClass().getResource("/icon_question.png"));
		Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		String ExitOption[] = {"New definition","Default definition","Cancel"};
		int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Except General Inputs, everything will be reset. Your option ?", "Import Strata Definition",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[2]);
		if (response == 0)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setPreferredSize(new Dimension(800, 500));
			chooser.setCurrentDirectory(new File(get_workingLocation()));
			chooser.setDialogTitle("Select strata definition file");
			chooser.setMultiSelectionEnabled(false);
			
			chooser.setApproveButtonText("Import");
			chooser.setApproveButtonToolTipText("Import strata definition from the selected file");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Strata Definition File '.csv' '.txt'", "csv", "txt");
			chooser.setFileFilter(filter);
			chooser.setAcceptAllFileFilterUsed(false);
			
			int returnValue = chooser.showOpenDialog(PrismMain.get_main());
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
