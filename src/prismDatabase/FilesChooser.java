package prismDatabase;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import prismConvenienceClass.FilesHandle;
import prismRoot.PrismMain;

public class FilesChooser {
	
	public static File[] chosenTables() {
		JFileChooser chooser = new JFileChooser("Select files to be imported as tables");
		chooser.setCurrentDirectory(new File(FilesHandle.get_workingLocation()));
		chooser.setMultiSelectionEnabled(true);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import files as table");
		
		int returnValue = chooser.showOpenDialog(PrismMain.get_Prism_DesktopPane());
		File[] files = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {

			files = chooser.getSelectedFiles();
		}
		return files;
	}
	
	public static File[] chosenDatabases() {
		JFileChooser chooser = new JFileChooser("Select .db files to be imported as databasses");
		chooser.setCurrentDirectory(new File(FilesHandle.get_workingLocation()));
		chooser.setMultiSelectionEnabled(true);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import files as databases");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Data Base File '.db'", "db");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnValue = chooser.showOpenDialog(PrismMain.get_Prism_DesktopPane());
		File[] files = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {

			files = chooser.getSelectedFiles();
		}
		return files;
	}
}