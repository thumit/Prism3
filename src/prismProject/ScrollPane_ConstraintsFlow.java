
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package prismProject;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.TitledBorder;

import prismConvenienceClass.ArrayListTransferHandler;
import prismConvenienceClass.ColorUtil;
import prismConvenienceClass.IconHandle;
import prismConvenienceClass.MenuScroller;
import prismConvenienceClass.PrismGridBagLayoutHandle;



public class ScrollPane_ConstraintsFlow extends JScrollPane {
	private ArrayListTransferHandler lh;
	private JList[] flow_list;
	private JList id_list;
	private DefaultListModel[] list_model = null;
	private JPanel flow_panel;
	private JScrollPane list_scroll;
	
	
	public ScrollPane_ConstraintsFlow (JList outside_id_list) {
		id_list = outside_id_list;
		
		lh = new ArrayListTransferHandler(); 
		flow_panel = new JPanel(new GridLayout(0, 3, 20, 0));
		list_scroll = new JScrollPane(flow_panel);
		list_scroll.setPreferredSize(new Dimension(300, 0));
		TitledBorder border = new TitledBorder("Flow Arrangement");
		border.setTitleJustification(TitledBorder.CENTER);
		list_scroll.setBorder(border);	
//		list_scroll.setBorder(BorderFactory.createTitledBorder("Flow arrangement"));
		create_flow_arrangement_UI(list_model);
		
				
		
		
		// IDs container
        id_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        id_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);	// For horizontal element order
        id_list.setVisibleRowCount(-1);							// For horizontal element order
        id_list.setCellRenderer(new SelectedListCellRenderer());	//Change selected items color
        
        id_list.setDragEnabled(true);
        id_list.setTransferHandler(lh);
        id_list.setDropMode(DropMode.INSERT);
        setMappings(id_list);
        
        JScrollPane id_scroll = new JScrollPane(id_list);
		id_scroll.setPreferredSize(new Dimension(150, 0));
        border = new TitledBorder("IDs Container");
		border.setTitleJustification(TitledBorder.CENTER);
        id_scroll.setBorder(border);

        // Make transparent inside id_scroll
        id_list.setOpaque(false);
		id_scroll.setOpaque(false);
		id_scroll.getViewport().setOpaque(false);
		id_scroll.setViewportBorder(null);
        
       
		
		
		// Add mouse listeners
		list_scroll.addMouseListener(mouse_listener);
		id_scroll.addMouseListener(mouse_listener);
		id_list.addMouseListener(mouse_listener);
		
		
		
     			
        // Add id and list scroll panels to a JPanel
		JPanel panel_all = new JPanel();
		panel_all.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		panel_all.add(list_scroll, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
					0, 0, 1, 1, 0.9, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
					0, 0, 0, 0));		// insets top, left, bottom, right
		panel_all.add(id_scroll, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				1, 0, 1, 1, 0.1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right

        
        // Add the JPanel to the super scroll pane
		setViewportView(panel_all);
	}
	
	 private void setMappings(JList list) {
		ActionMap map = list.getActionMap();
		map.put(TransferHandler.getCutAction().getValue(Action.NAME), TransferHandler.getCutAction());
		map.put(TransferHandler.getCopyAction().getValue(Action.NAME), TransferHandler.getCopyAction());
		map.put(TransferHandler.getPasteAction().getValue(Action.NAME), TransferHandler.getPasteAction());
	}
	 
	 private class SelectedListCellRenderer extends DefaultListCellRenderer {
	     @Override
	     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	         Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	         if (isSelected) {
	             c.setBackground(Color.ORANGE);
	         }
	         return c;
	     }
	}
	 
	public String get_flow_info_from_GUI() {
		String flow_info = "";

		for (JList list : flow_list) {
			for (int i = 0; i < list.getModel().getSize(); i++) {
				Object obj = list.getModel().getElementAt(i);
				flow_info = flow_info + obj.toString() + " ";
			}
			if (!flow_info.equals("")) {
				flow_info = flow_info.substring(0, flow_info.length() - 1);		// remove the last space
				flow_info = flow_info + ";";
			}						
		}
		
		if (!flow_info.equals("")) {
			flow_info = flow_info.substring(0, flow_info.length() - 1);		// remove the last ;
		}
		
		return flow_info;
	} 
	
	public void create_flow_arrangement_UI(DefaultListModel[] new_list_model) {	
		flow_panel.removeAll();
		int total_Flow = (new_list_model != null) ? new_list_model.length : 5;			// new or reload		
				
		// List of flows container
		flow_list = new JList[total_Flow];
		list_model = new DefaultListModel[total_Flow];
		JScrollPane[] list_scrollpane = new JScrollPane[total_Flow];

		for (int i = 0; i < total_Flow; i++) {
			list_model[i] = (new_list_model != null) ? new_list_model[i] : new DefaultListModel();			// new or reload
			flow_list[i] = new JList(list_model[i]);

			flow_list[i].setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);     
			flow_list[i].setLayoutOrientation(JList.HORIZONTAL_WRAP);	// For horizontal element order
			flow_list[i].setVisibleRowCount(-1);							// For horizontal element order
			flow_list[i].setCellRenderer(new SelectedListCellRenderer());	//Change selected items color

			flow_list[i].setDragEnabled(true);
			flow_list[i].setTransferHandler(lh);
			flow_list[i].setDropMode(DropMode.INSERT);
			setMappings(flow_list[i]);

			list_scrollpane[i] = new JScrollPane(flow_list[i]);
			list_scrollpane[i].setBorder(BorderFactory.createTitledBorder("Sigma " + (int) (i + 1)));
			list_scrollpane[i].setPreferredSize(new Dimension(120, 100));			
			flow_panel.add(list_scrollpane[i]);
			
			// Add mouse listeners
			flow_list[i].addMouseListener(mouse_listener);	
			list_scrollpane[i].addMouseListener(mouse_listener);
			
			// Make transparent inside each list_scrollpane[i]
			flow_list[i].setOpaque(false);
			list_scrollpane[i].setOpaque(false);
			list_scrollpane[i].getViewport().setOpaque(false);
			list_scrollpane[i].setViewportBorder(null);
		}
		list_scroll.setViewportView(flow_panel);	
		update_spin_sigma();
	} 
	
	
	public MouseAdapter mouse_listener = new MouseAdapter()
	{
		@Override
		public void mousePressed(MouseEvent e) {
			doMousePressed(e);
		}
	};		
	
	
	
	public void doMousePressed(MouseEvent e) {	

		if (SwingUtilities.isLeftMouseButton(e)) {		

		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getClickCount() == 1) {				
				// A popup that holds all JmenuItems
				JPopupMenu popup = new JPopupMenu();

				
				// Clear Menu--------------------------------- ------------------------------------------------------------
				final JMenu clear_menu = new JMenu("Clear");
				MenuScroller.setScrollerFor(clear_menu, 5, 125, 4, 1);
				clear_menu.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_sweep.png"));
				popup.add(clear_menu);		
				
				// Clear all
				final JMenuItem clear_all_sigma = new JMenuItem("Clear all Sigma");
				clear_all_sigma.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						for (int i = 0; i < flow_list.length; i++) {
							flow_list[i].setModel(new DefaultListModel<>());
							list_model[i] = new DefaultListModel<>();		// Update the list model so when change the spinner value we have the most recent list model to keep the Sigma boxes
						}
					}
				});
				clear_menu.add(clear_all_sigma);		
				
				// Clear one
				if (flow_list != null) {
					for (int i = 0; i < flow_list.length; i++) {
						final JMenuItem clear_a_sigma = new JMenuItem("Clear Sigma " + (int) (i + 1));
						clear_menu.add(clear_a_sigma);
						int current_sigma = i;
						clear_a_sigma.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {
								flow_list[current_sigma].setModel(new DefaultListModel<>());
								list_model[current_sigma] = new DefaultListModel<>();		// Update the list model so when change the spinner value we have the most recent list model to keep the Sigma boxes
							}
						});
					}
				}
				// Auto Menu--------------------------------- ------------------------------------------------------------
				final JMenu auto_menu = new JMenu("Auto");
				auto_menu.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_diagram.png"));
				popup.add(auto_menu);
				
				// IDs to Sigma - Forward
				final JMenuItem id_to_sigma_forward = new JMenuItem("Create Sigma from IDs - Forward");
				id_to_sigma_forward.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						if (id_list != null) {
							list_model = new DefaultListModel[id_list.getModel().getSize()];
							for (int i = 0; i < id_list.getModel().getSize(); i++) {
								list_model[i] = new DefaultListModel<>();
								
								// Add example only: 1 ID per Sigma
								list_model[i].addElement(id_list.getModel().getElementAt(i));
							}
							create_flow_arrangement_UI(list_model);
						}					
					}
				});
				auto_menu.add(id_to_sigma_forward);
				
				// IDs to Sigma - Backward
				final JMenuItem id_to_sigma_backward = new JMenuItem("Create Sigma from IDs - Backward");
				id_to_sigma_backward.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						if (id_list != null) {
							list_model = new DefaultListModel[id_list.getModel().getSize()];
							for (int i = 0; i < id_list.getModel().getSize(); i++) {
								list_model[i] = new DefaultListModel<>();
								
								// Add example only: 1 ID per Sigma
								list_model[i].addElement(id_list.getModel().getElementAt(id_list.getModel().getSize() - 1 - i));
							}
							create_flow_arrangement_UI(list_model);
						}					
					}
				});
				auto_menu.add(id_to_sigma_backward);
				// Auto Menu---------------------------------------------------------------------------------------------
				
				
				
				
				
				
				
				
				
				// Show the JmenuItems on selected node when it is right clicked
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	public JScrollPane get_list_scroll() {
		return list_scroll;
	}
	
	public DefaultListModel[] get_list_model() {
		return list_model;
	}
	
	public void update_spin_sigma() {
		// nothing here. we will have an override in Flow_Constraints_GUI to update the spin_sigma 
		// spin_sigma is always updated after   "create_flow_arrangement_UI"
	}
		
	public void reload_flow_arrangement_for_one_flow(JTable table10, Object[][] data10, JSpinner spin_sigma) {	
		int[] selectedRow = table10.getSelectedRows();
		if (selectedRow.length == 1) {	
			// Reload GUI				
			int currentRow = selectedRow[0];
			currentRow = table10.convertRowIndexToModel(currentRow);		// Convert row index because "Sort" causes problems				
			String[] flow_arrangement = data10[currentRow][2].toString().split(";");
			DefaultListModel[] list_model = new DefaultListModel[flow_arrangement.length];					
			for (int i = 0; i < flow_arrangement.length; i++) {		
				list_model[i] = new DefaultListModel();
				String[] arrangement = flow_arrangement[i].split(" ");							
				for (String a: arrangement) {
					list_model[i].addElement(a);
				}		
			}
			create_flow_arrangement_UI(list_model);	
		}
	}
	
	public void highlight() {			
		flow_panel.setBackground(ColorUtil.makeTransparent(new Color(240, 255, 255), 255));
		revalidate();
		repaint();
	}
	
	public void unhighlight() {			
		flow_panel.setBackground(null);
		revalidate();
		repaint();
	}
}
