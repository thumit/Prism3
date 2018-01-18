/*******************************************************************************
 * Copyright (C) 2016-2018 Dung Nguyen
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
	private String png;
	private int width;
	private int height;
	
	public TextArea_ReadMe(String png, int width, int height) {
		this.png = png;
		this.width = width;
		this.height = height;
		
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
		ImageIcon bgImage = IconHandle.get_scaledImageIcon(width, height, png);
		Dimension size = this.getSize();
		g2d.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth(), size.height - bgImage.getIconHeight() - 5, this);
		// Paint the component content, i.e. the text
		getUI().paint(g2d, this);
		g2d.dispose();
	}
}
