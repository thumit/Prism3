package prismConvenienceClasses;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

public class ToolBarWithBgImage extends JToolBar {	  	// Tool bar with background image
	private ImageIcon bgImage;

	public ToolBarWithBgImage(String name, int orientation, ImageIcon ii) {
		super(name, orientation);
		this.bgImage = ii;
		setOpaque(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bgImage != null) {
			Dimension size = this.getSize();
			g.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth(),
					(size.height - bgImage.getIconHeight()) / 2, bgImage.getIconWidth(), bgImage.getIconHeight(), this);
		}
    }
 }
