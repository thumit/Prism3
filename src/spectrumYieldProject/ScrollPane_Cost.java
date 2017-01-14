package spectrumYieldProject;

import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ScrollPane_Cost extends JScrollPane {	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private DefaultTableModel model;
	private Object[][] data;
	
	private ImageIcon icon;
	private Image scaleImage;
	
	public ScrollPane_Cost (Read_Indentifiers read_Identifiers, String[] yieldTable_ColumnNames, String scrollPane_name) {	

	
		//Setup the table--------------------------------------------------------------------------------------------
		rowCount = 1;
		colCount = 6;
		data = new Object[rowCount][colCount];
        columnNames= new String[] {"management_action", "cost_condition", "dollars_per_acre", "dollars_per_cubicfoot", "dollars_per_boardfoot", "dollars_per_livetree"};
		
        data[0][1] = "Base Cost";		
        data[0][2] = 360;	
        data[0][3] = 1.2;	
        data[0][4] = 0.1;	
        data[0][5] = 1.2;
        

		//Create a table----------------------------------------------------------------------------------------------------------------
        model = new DefaultTableModel(data, columnNames);
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
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width + 15);		//Here all headers + 25 empty space
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}	
			
			@Override
			public Class getColumnClass(int c) {
				if (c < 2)
					return String.class; // column 0 and 1 accepts only String
				else if (c >= 2)
					return Double.class; // column >=2 only Double values
				else
					return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col < 2) { // Columns 0 and 1 are not editable
					return false;
				} else {
					return true;
				}
			}
		};
		
		
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);		//Disable columns move
		table.getColumnModel().getColumn(0).setPreferredWidth(150);	//Set width of 1st Column bigger

//		table.setTableHeader(null);
//		table.setPreferredScrollableViewportSize(new Dimension(400, 100));
//		table.setFillsViewportHeight(true);
		

		
		
		
		
		
        
        // Add table to scroll pane
		JScrollPane costDefinition_scrollpane = new JScrollPane();
		costDefinition_scrollpane.setViewportView(table);
	
		
		
		
		//Setup the button "ADD"--------------------------------------------------------------------------------
		JButton addButton = new JButton();
		addButton.setToolTipText("Add a new cost condition (add row)");
		icon = new ImageIcon(getClass().getResource("/icon_add.png"));
		scaleImage = icon.getImage().getScaledInstance(16, 16,Image.SCALE_SMOOTH);
		addButton.setIcon(new ImageIcon(scaleImage));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				model.addRow(new Object[model.getColumnCount()]);
				
				// Refresh the data
				int nRow = model.getRowCount(), nCol = model.getColumnCount();
				data = new Object[nRow][nCol];
				for (int i = 0; i < nRow; i++) {
					for (int j = 0; j < nCol; j++) {
						data[i][j] = model.getValueAt(i, j);
					}	
				}
			}
		});
		

		//Setup the button "Delete"--------------------------------------------------------------------------------
		JButton deleteButton = new JButton();
		deleteButton.setToolTipText("Delete selected cost conditions (delete rows)");
		icon = new ImageIcon(getClass().getResource("/icon_erase.png"));
		scaleImage = icon.getImage().getScaledInstance(16, 16,Image.SCALE_SMOOTH);
		deleteButton.setIcon(new ImageIcon(scaleImage));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				table.getSelectionModel().removeSelectionInterval(0, 0);    //deselect the 1st row because we don't want to delete it
				
				//Delete the others
				int[] selectedRow = table.getSelectedRows();
				int del = 1;
				for (int j = 0; j < selectedRow.length; j++) {
					model.removeRow(selectedRow[j]);			
					if (j < selectedRow.length - 1) {
						selectedRow[j + 1] = selectedRow[j + 1] - del;
						del = del + 1;
					}
				}
								
				// Refresh the data
				int nRow = model.getRowCount(), nCol = model.getColumnCount();
				data = new Object[nRow][nCol];
				for (int i = 0; i < nRow; i++) {
					for (int j = 0; j < nCol; j++) {
						data[i][j] = model.getValueAt(i, j);
					}	
				}
			}
		});
		
		

		// Add all to this a Panel------------------------------------------------------------------------------
		JPanel cost_panel = new JPanel();
		cost_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// Add Button
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		cost_panel.add(addButton, c);

		// Add Button
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		cost_panel.add(deleteButton, c);

		// Add Empty Label to make those above 2 buttons not resize
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		cost_panel.add(new JLabel(), c);

		// Add costDefinition_scrollpane
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 3;
		cost_panel.add(costDefinition_scrollpane, c);
		
		cost_panel.setPreferredSize(new Dimension(0, 0));
		
		//Add the Panel to this Big ScrollPane------------------------------------------------------------------------------
		TitledBorder border = new TitledBorder(scrollPane_name);
		border.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
		setPreferredSize(new Dimension(250, 250));
		setViewportView(cost_panel);	
	}
	
//	public JCheckBox get_checkboxNoParameter() {
//		return checkboxNoParameter;
//	}
//	
//	public List<JCheckBox> get_checkboxParameter() {
//		return checkboxParameter;
//	}
}