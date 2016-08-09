package spectrumYieldProject;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import spectrumGUI.Spectrum_Main;

public class FilesChooser2 {
	

	public static File chosenManagementunit() {
		JFileChooser chooser = new JFileChooser("Select the .csv file that contains management units");
		chooser.setMultiSelectionEnabled(false);
		
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import management units from the selected file");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Management Units File '.csv' '.txt'", "csv", "txt");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		chooser.showOpenDialog(Spectrum_Main.mainFrameReturn());
		File file;
		file = chooser.getSelectedFile();
		return file;
	}
}