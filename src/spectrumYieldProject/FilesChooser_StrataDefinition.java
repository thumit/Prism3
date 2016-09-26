package spectrumYieldProject;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import spectrumGUI.Spectrum_Main;

public class FilesChooser_StrataDefinition {
	

	public static File chosenDefinition() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select strata definition file");
		chooser.setMultiSelectionEnabled(false);
		
		chooser.setApproveButtonText("Import");
		chooser.setApproveButtonToolTipText("Import strata definition from the selected file");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Strata Definition File '.csv' '.txt'", "csv", "txt");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		chooser.showOpenDialog(Spectrum_Main.mainFrameReturn());
		File file;
		file = chooser.getSelectedFile();
		return file;
	}
}