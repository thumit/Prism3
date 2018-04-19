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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class PrismTextAreaReadMe extends JTextArea {
	private String png;
	private int width;
	private int height;
	
	public PrismTextAreaReadMe(String png, int width, int height) {
		this.png = png;
		this.width = width;
		this.height = height;
		
		setBackground(ColorUtil.makeTransparent(Color.BLACK, 40));
		setForeground(ColorUtil.makeTransparent(Color.BLACK, 255));
		
//		if (UIManager.getLookAndFeel().getName().equals("Nimbus"))  {
//			setBackground(ColorUtil.makeTransparent(Color.BLACK, 40));
//			setForeground(ColorUtil.makeTransparent(Color.BLACK, 255));
//		} else {	// This is because the transparent fails when changing look and feel which makes the text area has problem with color painted
//			setBackground(Color.LIGHT_GRAY);
//			setForeground(Color.BLACK);
//		}
		setLineWrap(true);
		setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret) this.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);		
	}
		
	@Override
	protected void paintComponent(Graphics g) {					
		Graphics2D g2d = (Graphics2D) g.create();
		
		// Paint gradient color
		final int R = 240;
		final int G = 240;
		final int B = 240;
		Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 255), 0.0f, getHeight(), new Color(130, 220, 240, 255), true);
		g2d.setPaint(p);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		// Fill the background, this is VERY important. Fail to do this and you will have major problems
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// Draw the background
		ImageIcon bgImage = IconHandle.get_scaledImageIcon(width, height, png);
		Dimension size = this.getSize();
		g2d.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth(), size.height - bgImage.getIconHeight() - 5, this);
		// Paint the component content, i.e. the text
		getUI().paint(g2d, this);
		g2d.dispose();
	}
}
