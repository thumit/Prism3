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
