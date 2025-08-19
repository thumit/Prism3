/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/

package prism_root;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import prism_convenience.IconHandle;
import prism_convenience.ImageWhenClicked;

public class MenuItem_CaptureGUI extends JMenuItem {

	public MenuItem_CaptureGUI() {
		setText("5s Image Capture");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_camera.png"));
		ImageWhenClicked listener = new ImageWhenClicked();
		
		List<Component> comp_list = new ArrayList<Component>();

		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK, true));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getText().equals("5s Image Capture")) {			// This is to not create new thread when the old thread is still going
					
					String warningText = "You have 5 seconds to \"right click\" anywhere inside Prism.\nPicture will be saved to Desktop.";
					String ExitOption[] = { "Start now", "Cancel" };
					int response = JOptionPane.showOptionDialog(Prism3Main.get_Prism_DesktopPane(), warningText, "Image Capture",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(32, 32, "icon_camera.png"), ExitOption, ExitOption[0]);
					if (response == 0) {
						Thread thread = new Thread() { // Make a thread to allow capture screen within 5 seconds
							public void run() {
								if (!isSelected()) {
									setSelected(true);
									setText("5s Image Capture ON");
									
									// Get all components - except JMenu
									for (Component comp : getAllComponents(Prism3Main.get_main())) {
										if (!(comp instanceof JMenu)) comp_list.add(comp);
									}
									
									Object[] comp_listener = new Object[comp_list.size()];
									for (int i = 0; i < comp_list.size(); i++) {
										comp_listener[i] = new ArrayList<MouseListener>();
										for (MouseListener ml: comp_list.get(i).getMouseListeners()) {
											((ArrayList<MouseListener>) comp_listener[i]).add(ml);
											 comp_list.get(i).removeMouseListener(ml);		// Get all listeners of each component: save to a list then remove all		--> this Fix is implemented because of java 9
										}
										comp_list.get(i).addMouseListener(listener);		// Add the listener which show option for screen capture
									}
									
									try { // sleep for 5 seconds: this is 5 seconds for screen capture
										TimeUnit.SECONDS.sleep(5);	
									} catch (InterruptedException e) {
									}
									
									for (int i = 0; i < comp_list.size(); i++) {
										for (MouseListener ml: (ArrayList<MouseListener>) comp_listener[i]) {
											 comp_list.get(i).addMouseListener(ml);			// Add all old listeners of each component		--> this Fix is implemented because of java 9
										}
										comp_list.get(i).removeMouseListener(listener);		// Remove the listener which show option for screen capture
									}
									setSelected(false);
									setText("5s Image Capture");
								}
								this.interrupt();
							}
						};
						thread.start();
					}
				}
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
