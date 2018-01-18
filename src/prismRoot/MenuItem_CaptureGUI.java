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
package prismRoot;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import prismConvenienceClass.IconHandle;
import prismConvenienceClass.ImageWhenClicked;

public class MenuItem_CaptureGUI extends JMenuItem {

	public MenuItem_CaptureGUI(PrismMain main) {
		setText("5s Image Capture OFF");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_camera.png"));
		JMenuItem this_menu_item = this;
		ImageWhenClicked listener = new ImageWhenClicked();
		
		List<Component> comp_list = new ArrayList<Component>();

		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Thread thread = new Thread() { // Make a thread to allow capture screen within 5 seconds
					public void run() {
						if (!this_menu_item.isSelected()) {
							
							this_menu_item.setSelected(true);
							setText("5s Capture Mode ON");
							for (Component comp : getAllComponents(PrismMain.get_main())) {
								comp.addMouseListener(listener);
								comp_list.add(comp);
							}

							try { // sleep for 5 seconds
								TimeUnit.SECONDS.sleep(5);	
							} catch (InterruptedException e) {
							}
							
							this_menu_item.setSelected(false);
							setText("5s Capture Mode OFF");
							for (Component comp : comp_list) {
								comp.removeMouseListener(listener);
							}
							
						}
						this.interrupt();
					}
				};
				thread.start();
			}
		});
	}
		
	
	
	private HashMap componentMap;

	private void createComponentMap() {
		componentMap = new HashMap<String, Component>();
		List<Component> components = getAllComponents(this);
		for (Component comp : components) {
			componentMap.put(comp.getName(), comp);
		}
	}

	private List<Component> getAllComponents(final Container c) {
		Component[] comps = c.getComponents();
		List<Component> compList = new ArrayList<Component>();
		for (Component comp : comps) {
			compList.add(comp);
			if (comp instanceof Container)
				compList.addAll(getAllComponents((Container) comp));
		}
		return compList;
	}

	private Component getComponentByName(String name) {
		if (componentMap.containsKey(name)) {
			return (Component) componentMap.get(name);
		} else
			return null;
	}
}
