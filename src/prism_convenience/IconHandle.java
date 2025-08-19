/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/
package prism_convenience;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import prism_root.Prism3Main;

public class IconHandle {
	public IconHandle() {

	}

	public static ImageIcon get_scaledImageIcon(int width, int height, String imageName) {
		try {
			ImageIcon icon = new ImageIcon(Prism3Main.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
			Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
			return scaledImageIcon;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ImageIcon get_scaledImageIcon_replicate(int width, int height, String imageName) {
		try {
			ImageIcon icon = new ImageIcon(Prism3Main.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
			Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
			return scaledImageIcon;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ImageIcon get_rotated_scaledImageIcon(int width, int height, String imageName) {
		try {
			ImageIcon icon = new ImageIcon(Prism3Main.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			// paint the Icon to the BufferedImage.
			icon.paintIcon(null, g, 0, 0);
			g.dispose();
		    BufferedImage rotated = rotate(bi, 180.0d);
		    Image scaleImage = rotated.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaleImage);
			return scaledImageIcon;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static BufferedImage rotate(BufferedImage image, Double degrees) {
	    // Calculate the new size of the image based on the angle of rotaion
	    double radians = Math.toRadians(degrees);
	    double sin = Math.abs(Math.sin(radians));
	    double cos = Math.abs(Math.cos(radians));
	    int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
	    int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

	    // Create a new image
	    BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = rotate.createGraphics();
	    // Calculate the "anchor" point around which the image will be rotated
	    int x = (newWidth - image.getWidth()) / 2;
	    int y = (newHeight - image.getHeight()) / 2;
	    // Transform the origin point around the anchor point
	    AffineTransform at = new AffineTransform();
	    at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
	    at.translate(x, y);
	    g2d.setTransform(at);
	    // Paint the originl image
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();
	    return rotate;
	}
}


// ------------------------------------2 ways to set up icons for a button--------------------------------------------------------

//URL imgURL = getClass().getResource("/pikachuRunning.gif");		//Name is case sensitive
//URL imgURL2 = getClass().getResource("/pikachuAss.gif");			//Name is case sensitive
//URL imgURL2 = getClass().getResource("/pikachuRoll2.gif");			//Name is case sensitive
//
//try {		//Activate this if want some picture from Internet
//	//	https://media.giphy.com/media/TFhobYtkih62k/giphy.gif
//	//	http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif
//	//	http://orig11.deviantart.net/b288/f/2009/260/9/5/pikachu_vector_by_elfaceitoso.png	
//	imgURL = new java.net.URL("http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif");
//	imgURL2 = new java.net.URL("http://orig11.deviantart.net/b288/f/2009/260/9/5/pikachu_vector_by_elfaceitoso.png");
//} catch (MalformedURLException e1) {
//	System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//}	
//      	
//ImageIcon icon = new ImageIcon(imgURL);		//Image is in the same location of this class
//ImageIcon icon2 = new ImageIcon(imgURL2);		//Image is in the same location of this class
//		
//Image scaleImage = icon.getImage().getScaledInstance(200, 150,Image.SCALE_SMOOTH);
//Image scaleImage2 = icon2.getImage().getScaledInstance(138, 150,Image.SCALE_REPLICATE);
//
//button_solve = new JButton(new ImageIcon(scaleImage2));
//button_solve.setDisabledIcon(new ImageIcon(scaleImage));
