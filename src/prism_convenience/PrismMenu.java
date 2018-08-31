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
package prism_convenience;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

// This is the customize Menu used to remove the while and large border of the drop down area when we click on the JMenu (in Nimbus LAF, it is a large white area)

public class PrismMenu extends JMenu {
	private Border default_border, border;

	public PrismMenu(String s) {
		super(s);
		default_border = super.getPopupMenu().getBorder();
	}
	
	public void set_dark_border() {
		border = new LineBorder(ColorUtil.makeTransparent(Color.WHITE, 25), 2);
		border = BorderFactory.createMatteBorder(1, 0, 1, 0, ColorUtil.makeTransparent(Color.WHITE, 50));
	}
	
	public void set_bright_border() {
		border = default_border;
	}

	@Override
	public JPopupMenu getPopupMenu() {
		JPopupMenu menu = super.getPopupMenu();
		menu.setBorder(border);
		return menu;
	}
}
