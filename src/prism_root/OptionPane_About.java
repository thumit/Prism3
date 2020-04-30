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

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import prism_convenience.FilesHandle;
import prism_convenience.PrismTextAreaReadMe;
import prism_convenience.PrismTitleScrollPane;

public class OptionPane_About extends JOptionPane {
	
	public OptionPane_About() {
		for (JInternalFrame i: PrismMain.get_Prism_DesktopPane().getAllFrames()) {
			i.setVisible(false);
		} 
		
		String ExitOption[] = { "OK" };
		int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), new ScrollPane_License_Popup(),
				"ABOUT PRISM", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);

		for (JInternalFrame i: PrismMain.get_Prism_DesktopPane().getAllFrames()) {
			i.setVisible(true);
		} 
	}
}


class ScrollPane_License_Popup extends JScrollPane {
	
	public ScrollPane_License_Popup() {		
		// Read license readme.txt file from the system--------------------------------------------------------------	
		File file_license = null;
		try {
			file_license = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "readme.txt");
			file_license.deleteOnExit();

			InputStream initialStream = getClass().getResourceAsStream("/readme.txt"); 
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);

			OutputStream outStream = new FileOutputStream(file_license);
			outStream.write(buffer);

			initialStream.close();
			outStream.close();
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
		} 
		
		
		// Print to text area-----------------------------------------------------------------------------------------		
		PrismTextAreaReadMe license_TextArea = new PrismTextAreaReadMe("icon_tree.png", 75, 75);
		license_TextArea.setEditable(false);
		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new FileReader(file_license));
			String str;
			while ((str = buff.readLine()) != null) {
				license_TextArea.append("\n" + str);
			}
			license_TextArea.setCaretPosition(0);
			license_TextArea.setHighlighter(null);
		} catch (IOException e) {
		} finally {
			try {
				buff.close();
			} catch (Exception ex) {
			}
		}
		  
		
		PrismTitleScrollPane license_scrollpane = new PrismTitleScrollPane("", "CENTER", license_TextArea);
		addHierarchyListener(new HierarchyListener() {	//	These codes make the license_scrollpane resizable --> the Big ScrollPane resizable --> JOptionPane resizable
		    public void hierarchyChanged(HierarchyEvent e) {
		        Window window = SwingUtilities.getWindowAncestor(license_scrollpane);
		        if (window instanceof Dialog) {
		            Dialog dialog = (Dialog)window;
		            if (!dialog.isResizable()) {
		                dialog.setResizable(true);
		                dialog.setPreferredSize(new Dimension((int) (PrismMain.get_main().getWidth() / 1.1), (int) (PrismMain.get_main().getHeight() / 1.21)));
		            }
		        }
		    }
		});
		
		
		// Add the Panel to this Big ScrollPane------------------------------------------------------------------------------
		setBorder(BorderFactory.createEmptyBorder());
		setViewportView(license_scrollpane);			
	}
}
