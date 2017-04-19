package spectrumConvenienceClasses;

import java.awt.Image;
import javax.swing.ImageIcon;
import spectrumROOT.Spectrum_Main;

public class IconHandle {
	public IconHandle() {

	}

	public static ImageIcon get_scaledImageIcon(int width, int height, String imageName) {
		ImageIcon icon = new ImageIcon(Spectrum_Main.get_spectrumDesktopPane().getClass().getResource("/" + imageName));
		Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
		return scaledImageIcon;
	}
	
}