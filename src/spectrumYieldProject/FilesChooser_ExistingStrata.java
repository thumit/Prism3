package spectrumYieldProject;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import spectrumGUI.Spectrum_Main;

public class FilesChooser_ExistingStrata {
	

	public static File chosenStrata() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select existing strata file");
		chooser.setMultiSelectionEnabled(false);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import existing strata from the selected file");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Existing Strata File '.csv' '.txt'", "csv", "txt");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		chooser.showOpenDialog(Spectrum_Main.mainFrameReturn());
		File file;
		file = chooser.getSelectedFile();
		return file;
	}
}