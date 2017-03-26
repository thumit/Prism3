package spectrumROOT;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;


@SuppressWarnings("serial")
public class DesktopPanel_BackGround extends JDesktopPane {

	public DesktopPanel_BackGround() {
		
	}
	
	
	public void process_image() {
		try {
//			img = ImageIO.read(new URL("https://scontent-iad3-1.xx.fbcdn.net/t31.0-8/705097_4557689934155_1784248166_o.jpg"));			   
//			img = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"));
			img = ImageIO.read(getClass().getResource("/spectrumlite2.png"));
			BufferedImage bg = img;
			

			//Rescale buffered image-----------------
			final float FACTOR  = 4f;
			int scaleX = (int) (bg.getWidth() * FACTOR);
			int scaleY = (int) (bg.getHeight() * FACTOR);
//			Image scaleImage = bg.getScaledInstance(3000, 60, Image.SCALE_SMOOTH);		//For spectrumlite
//			Image scaleImage = bg.getScaledInstance(1800, 100, Image.SCALE_SMOOTH);		//For spectrumlite1
			Image scaleImage = bg.getScaledInstance(200, 40, Image.SCALE_SMOOTH);		//For spectrumlite2
			BufferedImage bg2 = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
			bg2.getGraphics().drawImage(scaleImage, 0, 0 , null);
			//End of Rescaling buffered image-----------------
					
			
//			setBackgroundImage(bg);
			setBackgroundImage(bg2);
		} catch (IOException ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
		}
	}

	private BufferedImage img;

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
//			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			
//			int x = (int) ((getWidth() - bg.getWidth())/2.5);
//			int y = (int) ((getHeight() - bg.getHeight())/1.4);
//			g.drawImage(bg, x, y, this);
			
			
//			int x = (int) 10;
//			int y = (int) 10;
//			g.drawImage(bg, x, y, getWidth(), getWidth()/2, this);
			
			
			//Fore spectrumlie2 picture only
//			int x = (int) 10;
//			int y = (int) getHeight() - 7;			
//			int x = (int) getWidth() - getWidth()/2 - 205;
//			int y = (int) getHeight() - 7;		
//			int x = (int) getWidth() - 205;
//			int y = (int) 7;
			int x = (int) getWidth() - 205;
			int y = (int) getHeight() - 47;
			g.drawImage(bg, x, y, this);

		}
	}
}