package prismConvenienceClasses;

import java.awt.Image;

import javax.swing.ImageIcon;

import prismRoot.PrismMain;

public class IconHandle {
	public IconHandle() {

	}

	public static ImageIcon get_scaledImageIcon(int width, int height, String imageName) {
		ImageIcon icon = new ImageIcon(PrismMain.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
		Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
		return scaledImageIcon;
	}
	
	public static ImageIcon get_scaledImageIcon_replicate(int width, int height, String imageName) {
		ImageIcon icon = new ImageIcon(PrismMain.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
		Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE);
		ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
		return scaledImageIcon;
	}
	
}