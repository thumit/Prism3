package prismProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import prismConvenienceClass.ColorUtil;
import prismConvenienceClass.IconHandle;

public class TextArea_ReadMe extends JTextArea{

	public TextArea_ReadMe() {
		setBackground(ColorUtil.makeTransparent(Color.BLACK, 40));
		setForeground(ColorUtil.makeTransparent(Color.BLACK, 255));
		setLineWrap(true);
		setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret) this.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);		
	}
		
	@Override
	protected void paintComponent(Graphics g) {					
		Graphics2D g2d = (Graphics2D) g.create();
		// Fill the background, this is VERY important. Fail to do this and you will have major problems
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// Draw the background
		ImageIcon bgImage = IconHandle.get_scaledImageIcon(70, 70, "minionWrite.png");
		Dimension size = this.getSize();
		g2d.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth(), size.height - bgImage.getIconHeight() - 5, this);
		// Paint the component content, i.e. the text
		getUI().paint(g2d, this);
		g2d.dispose();
	}
}
