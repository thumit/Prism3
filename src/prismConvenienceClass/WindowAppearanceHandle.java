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
package prismConvenienceClass;

import java.awt.Component;
import java.util.Enumeration;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class WindowAppearanceHandle {	
	// All child components will be transparent
	public static void setOpaqueForAll(JComponent aComponent, boolean isOpaque) {
		aComponent.setOpaque(isOpaque);
		Component[] comps = aComponent.getComponents();
		for (Component c : comps) {
			if (c instanceof JComponent) {
				setOpaqueForAll((JComponent) c, isOpaque);
			}
		}
	}
	
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getLookAndFeelDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.getLookAndFeelDefaults().get(key);
			if (value instanceof FontUIResource) {
				UIManager.getLookAndFeelDefaults().put(key, f);
			}
		}
	}
}
