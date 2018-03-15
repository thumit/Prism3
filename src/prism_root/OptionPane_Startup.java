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
package prism_root;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import prism_convenience_class.FilesHandle;
import prism_convenience_class.IconHandle;
import prism_convenience_class.PrismGridBagLayoutHandle;
import prism_convenience_class.PrismTitleScrollPane;
import prism_convenience_class.PrismTextAreaReadMe;

public class OptionPane_Startup extends JOptionPane {

	public static void Set_Memory() {
		File jar_file = new File(PrismMain.get_main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		File restart = new File(FilesHandle.get_temporaryFolder() + "/Restart");	// This folder works as a check. We need the first time restart to activate G1 when we running the jar out of eclipse IDE
		
					
		
		if (!jar_file.getName().endsWith(".jar")) {	// If running in Eclipse --> always create this bookkeeping folder --> no need to restart the 1st time
			if (!restart.exists()) restart.mkdirs();
			restart.deleteOnExit();
		}
		
				
		
		File memory_file = new File(FilesHandle.get_temporaryFolder() + "/prism_memory.txt");	// Store the last time MAx Memory is saved by users: just an integer number
		int previous_max_memory = 0;
		String previous_project_name = null;
		try {		
			List<String> list;
			list = Files.readAllLines(Paths.get(memory_file.getAbsolutePath()), StandardCharsets.UTF_8);			
			previous_max_memory = Integer.valueOf(list.get(0));
			previous_project_name = (list.size() == 2) ? list.get(1) : null;
		} catch (Exception e) {
			System.out.println("File prism_memory.txt does not exists");
			previous_max_memory = 1;
		}
				
		
		
		if (!restart.exists()) {
			restart.mkdirs();
			try {			
				// Always restart the 1st time running PRISM to activate G1, also set Max Heap to previously defined max memory (stored in prism_memory.txt) or 1G (in this case we are running the .jar out of eclipse)
				Memory_File.create_memory_file(memory_file, previous_max_memory, previous_project_name);
				String command_to_execute = "javaw -Xmx" + previous_max_memory + "G -XX:+UseG1GC -jar " + jar_file.getName();
				Runtime.getRuntime().exec(command_to_execute, null, new File(FilesHandle.get_workingLocation()));
			} catch (IOException e) {
			} finally {
				System.exit(0);
			}
		} else {			
			if (previous_project_name == null) {						
				PrismMain.get_main().setVisible(false);
				String ExitOption[] = {"Start","Exit"};
				int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), new ScrollPane_Popup(jar_file, memory_file), "Welcome",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);
				if (response == 0) {
					restart.delete();
				} else {
					restart.delete();
					System.exit(0);
				}
				PrismMain.get_main().setVisible(true);
			} else {	// When people press the collect memory button in the Panel_Project --> don't need to show the interface to change max memory, open the project instead
				restart.delete();
				PrismMain.get_main().createNewJInternalFrame(previous_project_name);
				Memory_File.create_memory_file(memory_file, previous_max_memory, null);
			}
		}	
	}
	
	
	
	public static void Restart_Project(String currentProject) {
		File jar_file = new File(PrismMain.get_main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		
		if (jar_file.getName().endsWith(".jar")) {	// If not running in Eclipse --> "Collect Memory" button works
			File memory_file = new File(FilesHandle.get_temporaryFolder() + "/prism_memory.txt");	// Store the last time MAx Memory is saved by users: just an integer number			
			int previous_max_memory = 0;
			try {		
				List<String> list;
				list = Files.readAllLines(Paths.get(memory_file.getAbsolutePath()), StandardCharsets.UTF_8);			
				previous_max_memory = Integer.valueOf(list.get(0));
			} catch (Exception ex) {
				System.out.println("File prism_memory.txt does not exists");
			}
			
			if (memory_file.exists()) {
				memory_file.delete();		// Delete the old file before writing new contents
			}
			
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(memory_file))) {			
				fileOut.write(String.valueOf(previous_max_memory));		
				fileOut.write( "\n" + currentProject);
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 		
			
			Set_Memory();
		}
	}
	
	public static String memory_left() {	
		return formatSize(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory());
	}
	
	public static String memory_in_use() {	
		return formatSize(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	private static String formatSize(long v) {
	    if (v < 1024) return v + " B";
	    int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
	    return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
	}
}







class ScrollPane_Popup extends JScrollPane {
	
	public ScrollPane_Popup(File jar_file, File memory_file) {			
		// Add all to a Panel------------------------------------------------------------------------------	
		JPanel popupPanel = new JPanel();	
		//	These codes make the popupPanel resizable --> the Big ScrollPane resizable --> JOptionPane resizable
		popupPanel.addHierarchyListener(new HierarchyListener() {
		    public void hierarchyChanged(HierarchyEvent e) {
		        Window window = SwingUtilities.getWindowAncestor(popupPanel);
		        if (window instanceof Dialog) {
		            Dialog dialog = (Dialog)window;
		            if (!dialog.isResizable()) {
		                dialog.setResizable(true);
		                dialog.setMinimumSize(new Dimension(600, 400));
		            }
		        }
		    }
		});
		
		
		
		
		//-----------------------------------------------------------------------------------------		
		PrismTextAreaReadMe info_TextArea = new PrismTextAreaReadMe("icon_tree.png", 75, 75);	
		info_TextArea.setEditable(false);
		// Get total computer memory in bytes
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		Object attribute = "";
		try {
			attribute = mBeanServer.getAttribute(new ObjectName("java.lang","type","OperatingSystem"), "TotalPhysicalMemorySize");
		} catch (AttributeNotFoundException | InstanceNotFoundException | MalformedObjectNameException | MBeanException
				| ReflectionException e1) {
			e1.printStackTrace();
		}
		long totalSize = Long.valueOf(attribute.toString());
		// Get current size of heap in bytes				
		long heapSize = Runtime.getRuntime().totalMemory(); 
		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
		long heapMaxSize = Runtime.getRuntime().maxMemory();
		 // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
		long heapFreeSize = Runtime.getRuntime().freeMemory(); 

		// Print the heap info	
		info_TextArea.append("\nTotal physical memory of your computer:   " + formatSize(totalSize)
		+ "\nMaximum memory Prism is allowed to use:   " + formatSize(heapMaxSize)
		+ "\nMemory Prism is using at this moment:   " + formatSize(heapSize - heapFreeSize)
		+ "\nMemory available for Prism's future use :   " + formatSize(heapMaxSize - heapSize + heapFreeSize));
		PrismTitleScrollPane readme_scrollpane = new PrismTitleScrollPane("Memory in PRISM", "CENTER", info_TextArea);
		
		
		
		
		JLabel label = new JLabel(IconHandle.get_scaledImageIcon(230, 70, "prism_ice.png"));
		JLabel label2 = new JLabel("Change maximum memory (GigaBytes) PRISM is allowed to use");
		int total_memory = Integer.valueOf(formatSize(totalSize).substring(0, formatSize(totalSize).lastIndexOf('.')));
		int max_combo_value = total_memory - (int) (total_memory * 0.1);
		JComboBox combo = new JComboBox();
		for (int i = 1; i <= max_combo_value; i++) {
			combo.addItem(i);
		}	
		combo.setSelectedItem(Integer.valueOf(formatSize(heapMaxSize).substring(0, formatSize(heapMaxSize).lastIndexOf('.'))));
	    combo.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jar_file.getName().endsWith(".jar")) {	// If not running in Eclipse --> restart
					try {
						String new_max_heap = combo.getSelectedItem().toString();
						Memory_File.create_memory_file(memory_file, Integer.valueOf(new_max_heap), null);
						String command_to_execute = "javaw -Xmx" + new_max_heap + "G -XX:+UseG1GC -jar " + jar_file.getName();
						Runtime.getRuntime().exec(command_to_execute, null, new File(FilesHandle.get_workingLocation()));
					} catch (IOException ex) {
					} finally {
						System.exit(0);
					}
				}
			}
		});
		
		
		
		
		// Add all components to popup panel
		popupPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		popupPanel.add(label, PrismGridBagLayoutHandle.get_c(c, "Center", 
				0, 0, 3, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));	// insets top, left, bottom, right	
		
		popupPanel.add(label2, PrismGridBagLayoutHandle.get_c(c, "NONE", 
				0, 1, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 0, 0, 0));	// insets top, left, bottom, right	
		
		popupPanel.add(combo, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				1, 1, 1, 1, 0.5, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 0, 0, 0));	// insets top, left, bottom, right	
		
		popupPanel.add(new JLabel(), PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				2, 1, 1, 1, 0.5, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 0, 0, 0));	// insets top, left, bottom, right	
		
		popupPanel.add(readme_scrollpane, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 2, 3, 1, 0, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				10, 0, 0, 0));	// insets top, left, bottom, right
		
		
		//Add the Panel to this Big ScrollPane------------------------------------------------------------------------------
		TitledBorder border = new TitledBorder("");
		border.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
		setViewportView(popupPanel);			
	}
	
	private static String formatSize(long v) {
	    if (v < 1024) return v + " B";
	    int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
	    return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
	}
	
}





class Memory_File {
	public static void create_memory_file(File memory_file, int max_heap, String previous_project_name) {
		if (memory_file.exists()) {
			memory_file.delete();		// Delete the old file before writing new contents
		}
		
		try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(memory_file))) {			
			fileOut.write(String.valueOf(max_heap));		
			if (previous_project_name != null) fileOut.write( "\n" + previous_project_name);	
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} 		
	}
}
