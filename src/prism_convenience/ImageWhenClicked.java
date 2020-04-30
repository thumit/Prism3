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

package prism_convenience;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import prism_root.PrismMain;

// Use the following codes to add listeners, then click on the FComponent to get Desktop Image
// abcxyz_component.addMouseListener(new ImageWhenClicked());

public class ImageWhenClicked extends MouseAdapter {
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getClickCount() == 1) {
				try {
					JComponent comp = (JComponent) e.getComponent();
					JPopupMenu popup = new JPopupMenu();

					final JMenuItem capture_all = new JMenuItem("Capture entire GUI");
					capture_all.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_camera1.png"));
					capture_all.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							create_desktop_image(PrismMain.get_main(), "PRISM");
							popup.setVisible(false);
						}
					});
					
					
					final JMenuItem capture_component = new JMenuItem("Capture component GUI");
					capture_component.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_camera2.png"));
					capture_component.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							create_desktop_image(comp, "PRISM_Component");
							popup.setVisible(false);
						}
					});
					
					final JMenuItem capture_parent_component = new JMenuItem("Capture component's parents GUI");
					capture_parent_component.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_camera3.png"));
					capture_parent_component.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							create_desktop_image(comp.getParent(), "PRISM_Parents_Component");
							popup.setVisible(false);
						}
					});
					
					final JMenuItem capture_grandparent_component = new JMenuItem("Capture component's grand-parents GUI");
					capture_grandparent_component.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_camera4.png"));
					capture_grandparent_component.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							create_desktop_image(comp.getParent().getParent(), "PRISM_GrandParents_Component");
							popup.setVisible(false);
						}
					});
					
					popup.add(capture_all);
					popup.add(capture_component);
					popup.add(capture_parent_component);
					popup.add(capture_grandparent_component);
					popup.show(comp, e.getX(), e.getY());
					
				} catch (Exception event) {
					System.err.println("Fail to create Desktop Image");
				}		
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	private void create_desktop_image(Component comp, String image_name) {
		try {
			// image to be altered
			BufferedImage imagem = getScreenShot(comp);

			// the output image
			File desktop = new File(System.getProperty("user.home"), "Desktop");
			File outPutImage = new File(desktop.getAbsolutePath() + "/" + image_name + ".jpg");

			// encapsulate the outPut image
			ImageOutputStream ios = ImageIO.createImageOutputStream(outPutImage);

			// list of ImageWritre's for jpeg format
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");

			// capture the first ImageWriter
			ImageWriter writer = iter.next();

			// define the o outPut file to the write
			writer.setOutput(ios);

			// define the changes you want to make to the image
			ImageWriteParam iwParam = writer.getDefaultWriteParam();
			iwParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			iwParam.setCompressionQuality(1.0f);

			// compression, etc... being made
			writer.write(null, new IIOImage(imagem, null, null), iwParam);

			// Write to altered image in memory to the final file
			ImageIO.write(imagem, "jpg", ios);

			ios.close();
			writer.dispose();
		} catch (IOException e) {
			System.err.println("Image capture error: " + e.getClass().getName() + ": " + e.getMessage());
		}
	}
			
	private static BufferedImage getScreenShot(Component component) {
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		// call the Component's paint method, using the Graphics object of the image.
		component.paint(image.getGraphics()); // alternately use .printAll(..)
		return image;
	}
	
	
	
	
	
	
//	private HashMap componentMap;
//
//	private void createComponentMap() {
//		componentMap = new HashMap<String, Component>();
//		List<Component> components = getAllComponents(this);
//		for (Component comp : components) {
//			componentMap.put(comp.getName(), comp);
//		}
//	}
//
//	private List<Component> getAllComponents(final Container c) {
//		Component[] comps = c.getComponents();
//		List<Component> compList = new ArrayList<Component>();
//		for (Component comp : comps) {
//			compList.add(comp);
//			if (comp instanceof Container)
//				compList.addAll(getAllComponents((Container) comp));
//		}
//		return compList;
//	}
//
//	private Component getComponentByName(String name) {
//		if (componentMap.containsKey(name)) {
//			return (Component) componentMap.get(name);
//		} else
//			return null;
//	}
	
	
	
	
	
	
	
//	if (comp.isShowing() && (comp.getClass().getName().equals("javax.swing.JInternalFrame")
////			|| (comp.getClass().getName().equals("javax.swing.JSplitPane"))
////			|| (comp.getClass().getName().equals("javax.swing.JScrollPane"))
////			|| (comp.getClass().getName().equals("javax.swing.JViewport"))
//	)) {
//		count++;
//		String comp_name = comp.getClass().getSimpleName() + count;
//		create_desktop_image_from_component(comp, comp_name);
//	}	
		
}
