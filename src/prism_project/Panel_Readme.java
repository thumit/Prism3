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
package prism_project;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import prism_convenience_class.IconHandle;
import prism_convenience_class.PrismTextAreaReadMe;
import prism_convenience_class.PrismTitleScrollPane;
import prism_convenience_class.ToolBarWithBgImage;

public class Panel_Readme extends JLayeredPane {

	public Panel_Readme(File readme_file, PrismTextAreaReadMe readme) {
		PrismTitleScrollPane readme_scrollpane = new PrismTitleScrollPane("", "LEFT", readme);
		readme_scrollpane.setPreferredSize(new Dimension(0, 0));
		
		
		// ToolBar Panel -------------------------------------------------------------------------------------------------------
		ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		helpToolBar.setFloatable(false);	// to make a tool bar immovable
		helpToolBar.setRollover(true);		// to visually indicate tool bar buttons when the user passes over them with the cursor
		helpToolBar.setBorderPainted(false);
		
		// button Switch
		JButton btnSave = new JButton();
		btnSave.setToolTipText("Save");
		btnSave.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_save.png"));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {	
				readme_file.delete();		// Delete the old file before writing new contents
				FileWriter pw;
				try {
					pw = new FileWriter(readme_file.getAbsolutePath());
					readme.write(pw);
					pw.close();
				} catch (IOException e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}		
			}
		});				
		
		// button Help
		JButton btnHelp = new JButton();
		btnHelp.setToolTipText("Help");
		btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
			}
		});
		
		// Label
		JLabel title = new JLabel("MODEL DESCRIPTION");
		title.setFont(new Font(UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName(), Font.BOLD, 12));	// Use MenuBar to get current Font
		
		// Add all buttons to flow_panel
		helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
		helpToolBar.add(title);
		helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
//		helpToolBar.add(btnSave);
//		helpToolBar.add(btnHelp);

		
	    
		// Add all to the Main Grid---------------------------------------------------------------------------------------------
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// Add helpToolBar to the main Grid
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0;
	    c.weighty = 0;
	    add(helpToolBar, c);				
		
		// Add database_table_scrollpane to the main Grid
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
	    c.weighty = 1;
	    add(readme_scrollpane, c);	
		
	}
}