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
package prismConvenienceClass;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import prismRoot.PrismMain;

public class PrismIconPanel extends JPanel {

	public PrismIconPanel(Component nestedCOmponent) {
		setLayout(new GridBagLayout());
//		Border tempBorder = BorderFactory.createMatteBorder(3, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 255));
//		setBorder(tempBorder);
		setBorder(null);
		
	
	    	    

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		JButton btnIconify = new JButton();
		btnIconify.setText("OFF");
		btnIconify.setToolTipText("Strata Filter");
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon_refresh.png"));
		Image scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnIconify.setIcon(new ImageIcon(scaleImage));
		btnIconify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
		
				if (btnIconify.getText().equals("OFF")) {
					btnIconify.setText("ON");
					scrollPane.setViewportView(nestedCOmponent);
					// Get everything show up nicely
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());	//this can replace the below 2 lines
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().revalidate();
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().repaint();
				} else {
					btnIconify.setText("OFF");
					scrollPane.setViewportView(null);
					// Get everything show up nicely
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().setSize(PrismMain.get_Prism_DesktopPane().getSelectedFrame().getSize());	//this can replace the below 2 lines
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().revalidate();
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().repaint();
				}
			}
		});
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
	    c.weighty = 1;
	    
	    // Add the 1st grid - importPanel to the main Grid
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
	    c.weighty = 0;
		add(btnIconify, c);
		
		// Add Empty Label to make button not resize
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		add(new JLabel(), c);
		
		// Add the 1st grid - importPanel to the main Grid
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
	    c.weighty = 1;
	    c.gridheight = 2;
		add(scrollPane, c);
		
		
		
		
		
		
		
//		//button 3
//		JButton btnIconify = new JButton();
//		btnIconify.setText("OFF");
//		btnIconify.setToolTipText("Strata Filter");
//		ImageIcon icon = new ImageIcon(getClass().getResource("/icon_refresh.png"));
//		Image scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
//		btnIconify.setIcon(new ImageIcon(scaleImage));
//		btnIconify.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionEvent) {
//		
//				if (btnIconify.getText().equals("OFF")) {
//					btnIconify.setText("ON");
//					checkPanel.setVisible(true);
//					// Get everything show up nicely
//					GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
//				} else {
//					btnIconify.setText("OFF");
//					checkPanel.setVisible(false);
//					// Get everything show up nicely
//					GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
//				}
//			}
//		});
//		checkPanel.setVisible(false);

	}
}
