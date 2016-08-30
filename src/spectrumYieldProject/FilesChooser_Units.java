package spectrumYieldProject;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import spectrumGUI.Spectrum_Main;

public class FilesChooser_Units {
	

	public static File chosenManagementunit() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select management units file");
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