package spectrumYieldProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
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
		
		int returnValue = chooser.showOpenDialog(Spectrum_Main.mainFrameReturn());
		File file = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {	//Return the new Definition as in the selected file
			file = chooser.getSelectedFile();
		}
		
		
		if (returnValue == JFileChooser.CANCEL_OPTION) {	//Return the Default definition if Cancel
		
			int response = JOptionPane.showConfirmDialog(Spectrum_Main.mainFrameReturn(),
					"Do you want to reload the Default Strata Definition ?", "Confirm", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				try {
					File file_StrataDefinition = new File("StrataDefinition.csv");	
						
					InputStream initialStream = Panel_EditRun_Details.class.getResourceAsStream("StrataDefinition.csv");		//Default definition
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
			} else if (response == JOptionPane.NO_OPTION) {
	
			} else if (response == JOptionPane.CLOSED_OPTION) {

			}
		}
		return file;
	}
}