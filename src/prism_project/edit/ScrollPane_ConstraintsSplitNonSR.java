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

package prism_project.edit;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import prism_convenience.PrismTableModel;
import prism_convenience.TableColumnsHandle;
import prism_root.PrismMain;

public class ScrollPane_ConstraintsSplitNonSR extends JScrollPane {

	private List<JCheckBox> selected_staticCheckboxes;
	private JTable table;
	private Object[][] data;
	private JCheckBox autoDescription;
	
	
	public ScrollPane_ConstraintsSplitNonSR(List<JCheckBox> staticCheckboxes) {
		// staticScrollPane	----------------------------------------------------------------------------------	
		selected_staticCheckboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : staticCheckboxes) {
			selected_staticCheckboxes.add(new JCheckBox(i.getText()));
		}
		
		JPanel staticPanel = new JPanel();
		staticPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
		c1.weighty = 1;

		// Add all checkboxes
		for (int i = 0; i < selected_staticCheckboxes.size(); i++) {
			c1.gridx = 0;
			c1.gridy = i;
			staticPanel.add(selected_staticCheckboxes.get(i), c1);
		}
		
		JScrollPane staticScrollPane = new JScrollPane(staticPanel);
		TitledBorder border = new TitledBorder("Static Identifiers");
		border.setTitleJustification(TitledBorder.CENTER);
		staticScrollPane.setBorder(border);
		staticScrollPane.setPreferredSize(new Dimension(250, 250));


		
		// tableScrollPane	------------------------------------------------------------------------------	
		int rowCount = 1;
		int colCount = 5;
		data = new Object[rowCount][colCount];
		String[] columnNames = new String[] {"condition_id", "condition_description", "static_identifiers", "MS_E_percentage", "BS_E_percentage"};	
		
		PrismTableModel model = new PrismTableModel(rowCount, colCount, data, columnNames) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;	// column 0 accepts only Integer
				else if (c > 2) return Double.class;
				else return String.class;				
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col != 0 && col != 2) {
					return true;
				} else {
					return false;
				}
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				if (value != null && col > 2 && (((Number) value).doubleValue() < 0 || ((Number) value).doubleValue() > 100)) {	// allow null to be set
    				JOptionPane.showMessageDialog(PrismMain.get_Prism_DesktopPane(), "Your input has not been accepted. Only double values in the range 0-100 (%) would be allowed.");
    			} else {
    				data[row][col] = value;
    			}
			}
			
			@Override
			public void match_DataType() {
				for (int row = 0; row < rowCount; row++) {
					for (int col = 0; col < colCount; col++) {
						if (String.valueOf(data[row][col]).equals("null")) {
							data[row][col] = null;
						} else {					
							if (col == 0) {		// column 0 is Integer
								try {
									data[row][col] = Integer.valueOf(String.valueOf(data[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Integer values in create_table");
								}	
							} else if (col > 2) {	// Double
								try {
									data[row][col] = Double.valueOf(String.valueOf(data[row][col]));
								} catch (NumberFormatException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Fail to convert String to Double values in create_table");
								}	
							} else {	//All other columns are String
								data[row][col] = String.valueOf(data[row][col]);
							}
						}	
					}	
				}	
			}
		};
		
		table = new JTable(model) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = table.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(table,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				if (column != 0) {
					tableColumn.setPreferredWidth(maxWidth);
				} else {
					tableColumn.setMinWidth(300);
				}
				return component;
			}	
			
			@Override	// Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int row = rowAtPoint(p);
				int col = columnAtPoint(p);
				String tip = (table.getColumnName(col).equals("condition_description") && row >= 0 && getValueAt(row, col) != null) ? getValueAt(row, col).toString() : null;
				return tip;
			}	
		};

		// Hide columns
		TableColumnsHandle table_handle = new TableColumnsHandle(table);
		table_handle.setColumnVisible("static_identifiers", false);
//		table.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		table.getTableHeader().setReorderingAllowed(false);		//Disable columns move
//		table.setPreferredScrollableViewportSize(new Dimension(250, 20));

		
		
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		border = new TitledBorder("Infomation below is applied for all new conditions");
		border.setTitleJustification(TitledBorder.CENTER);
		tableScrollPane.setBorder(border);
		tableScrollPane.setPreferredSize(new Dimension(550, 200));
		//Hide the id column	
		table.removeColumn(table.getColumnModel().getColumn(0));		// The data is not changed anyway
		

		
		
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
		            }
		        }
		    }
		});
		
		
		popupPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// Add Static Splitters
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		popupPanel.add(staticScrollPane, c);

		// Add tableScrollPane
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		popupPanel.add(tableScrollPane, c);
		
		// Add autoDescription checkbox
		autoDescription = new JCheckBox("Add splitting infomation to conditions description");
		autoDescription.setSelected(true);
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		popupPanel.add(autoDescription, c);

		
		
		// Add the Panel to this Big ScrollPane------------------------------------------------------------------------------
		setBorder(null);
		setViewportView(popupPanel);			
	}
	
	
	public List<Integer> get_static_split_id() {
		List<Integer> static_split_id = new ArrayList<Integer>();
		for (int i = 0; i < selected_staticCheckboxes.size(); i++) {
			if (selected_staticCheckboxes.get(i).isSelected()) {
				static_split_id.add(i);
			}
		}
		return static_split_id;
	}
	
	public Object[][] get_multiple_constraints_data() {
		return data;
	}
	
	
	public JCheckBox get_autoDescription() {
		return autoDescription;
	}
	
	public void stop_editing() {
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
	}
}
