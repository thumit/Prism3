/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prism_convenience_class;

import java.awt.Image;

import javax.swing.ImageIcon;

import prism_root.PrismMain;

public class IconHandle {
	public IconHandle() {

	}

	public static ImageIcon get_scaledImageIcon(int width, int height, String imageName) {
		try {
			ImageIcon icon = new ImageIcon(PrismMain.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
			Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
			return scaledImageIcon;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ImageIcon get_scaledImageIcon_replicate(int width, int height, String imageName) {
		try {
			ImageIcon icon = new ImageIcon(PrismMain.get_Prism_DesktopPane().getClass().getResource("/" + imageName));
			Image scaleImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon scaledImageIcon = new ImageIcon(scaleImage);				
			return scaledImageIcon;
		} catch (Exception e) {
			return null;
		}
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
