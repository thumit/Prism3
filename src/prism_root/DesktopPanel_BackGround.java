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
package prism_root;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;


@SuppressWarnings("serial")
public class DesktopPanel_BackGround extends JDesktopPane {
	private BufferedImage img;
	
	public DesktopPanel_BackGround() {
		process_image();
	}
	
	
	public void process_image() {
		try {
//			img = ImageIO.read(new URL("https://scontent-iad3-1.xx.fbcdn.net/t31.0-8/705097_4557689934155_1784248166_o.jpg"));			   
//			img = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"));
			img = ImageIO.read(getClass().getResource("/prism_silver.png"));
			BufferedImage bg = img;
			

			// Rescale buffered image-----------------
			final float FACTOR  = 4f;
			int scaleX = (int) (bg.getWidth() * FACTOR);
			int scaleY = (int) (bg.getHeight() * FACTOR);
//			Image scaleImage = bg.getScaledInstance(3000, 60, Image.SCALE_SMOOTH);		//For spectrumlite
//			Image scaleImage = bg.getScaledInstance(1800, 100, Image.SCALE_SMOOTH);		//For spectrumlite1
			Image scaleImage = bg.getScaledInstance(150, 40, Image.SCALE_SMOOTH);
			BufferedImage bg2 = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
			bg2.getGraphics().drawImage(scaleImage, 0, 0 , null);
					
			
//			setBackgroundImage(bg);
			setBackgroundImage(bg2);
		} catch (IOException ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
		}
	}


	@Override
	public Dimension getPreferredSize() {
		BufferedImage img = getBackgroundImage();
		Dimension size = super.getPreferredSize();
		if (img != null) {
			size.width = Math.max(size.width, img.getWidth());
			size.height = Math.max(size.height, img.getHeight());
		}
		return size;
	}

	
	public BufferedImage getBackgroundImage() {
		return img;
	}

	
	public void setBackgroundImage(BufferedImage value) {
		if (img != value) {
			BufferedImage old = img;
			img = value;
			firePropertyChange("background", old, img);
			revalidate();
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage bg = getBackgroundImage();
		if (bg != null) {
			//Fore spectrumlie2 picture only
//			int x = (int) 10;
//			int y = (int) getHeight() - 7;			
//			int x = (int) getWidth() - getWidth()/2 - 205;
//			int y = (int) getHeight() - 7;		
//			int x = (int) getWidth() - 205;
//			int y = (int) 7;
			int x = (int) getWidth() - 155;
			int y = (int) getHeight() - 50;
			g.drawImage(bg, x, y, this);
		}
		
		
//		// drawing circle text
//		if (g instanceof Graphics2D) {
//			Graphics2D g2d = (Graphics2D) g;
//			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			String s = "Java Source and Support";
//			Font font = new Font("Serif", Font.PLAIN, 30);
//			FontRenderContext frc = g2d.getFontRenderContext();
//			g2d.translate(400, 200);
//
//			GlyphVector gv = font.createGlyphVector(frc, s);
//			int length = gv.getNumGlyphs();
//			for (int i = 0; i < length; i++) {
//				Point2D point = gv.getGlyphPosition(i);
//				double theta = (double) i / (double) (length - 1) * Math.PI / 4;
//				AffineTransform at = AffineTransform.getTranslateInstance(point.getX(), point.getY());
//				at.rotate(theta);
//				Shape glyph = gv.getGlyphOutline(i);
//				Shape transformedGlyph = at.createTransformedShape(glyph);
//				g2d.fill(transformedGlyph);
//			}
//		}
	}
}
