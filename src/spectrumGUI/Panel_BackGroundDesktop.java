package spectrumGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;


@SuppressWarnings("serial")
public class Panel_BackGroundDesktop extends JDesktopPane {

	public Panel_BackGroundDesktop() {
		try {
			img = ImageIO.read(new URL("https://scontent-ord1-1.xx.fbcdn.net/t31.0-8/281108_4557696894329_1537435731_o.jpg"));			   
//			img = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"));
			BufferedImage bg = img;
			setBackgroundImage(bg);
		} catch (IOException ex) {
			ex.printStackTrace();
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
			int x = (int) ((getWidth() - bg.getWidth())/2.5);
			int y = (int) ((getHeight() - bg.getHeight())/1.4);
			g.drawImage(bg, x, y, this);
//			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		}
	}
}