package spectrumYieldProject;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import spectrumGUI.Spectrum_Main;

public class FilesChooser_Database {
	

	public static File chosenDatabase() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select database file");
		chooser.setMultiSelectionEnabled(false);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import database for the imported management units");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Database file '.db'", "db");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		chooser.showOpenDialog(Spectrum_Main.mainFrameReturn());
		File file;
		file = chooser.getSelectedFile();
		return file;
	}
}