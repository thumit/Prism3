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
package prism_project.output;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import prism_convenience.FilesHandle;
import prism_convenience.IconHandle;
import prism_convenience.MenuScroller;
import prism_convenience.PrismTitleScrollPane;
import prism_convenience.ToolBarWithBgImage;
import prism_database.Setting;

public class Output_Panel_Management_Details_SQL extends JLayeredPane {
	private String conn_path;
	
	private JTable database_table;
	private DefaultTableModel model;
	
	private PrismTitleScrollPane database_table_scrollpane;
	private JButton btnSwitch;
	private TableFilterHeader filterHeader;
	private Boolean is_filter_visible;
	
	
	public Output_Panel_Management_Details_SQL(File output_05_management_details_file, String[] columnNames, Object[][] data, JButton SQL_link_button) {
		// Some set up -------------------------------------------------------------------------------------------------------
		set_up_database_table(columnNames, data);
		conn_path = "jdbc:sqlite:" + output_05_management_details_file.getParentFile().getAbsolutePath() + "/database.db";
		
		String scroll_name = (data.length == 999) ? "First 999 records of the optimal solutions" : "All records of the optimal solutions";
		database_table_scrollpane = new PrismTitleScrollPane(scroll_name, "CENTER", database_table);
		database_table_scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		database_table_scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		MouseAdapter mouse_listener = new MouseAdapter() { 	// Add listener
			public void mousePressed(MouseEvent e) {
				 if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					update_query_function_popup();
				 }
			}
		};
		// Add listeners
		database_table.addMouseListener(mouse_listener); 				// Add listener
		database_table_scrollpane.addMouseListener(mouse_listener); 	// Add listener
		
		// Set up the filter--------------------------------------------------------------------
		filterHeader = new TableFilterHeader(null, AutoChoices.ENABLED);
		filterHeader.setFilterOnUpdates(true);
		is_filter_visible = false;
		
		
		
		// ToolBar Panel -------------------------------------------------------------------------------------------------------
		ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		helpToolBar.setFloatable(false);	// to make a tool bar immovable
		helpToolBar.setRollover(true);		// to visually indicate tool bar buttons when the user passes over them with the cursor
		helpToolBar.setBorderPainted(false);
		
		// button Switch
		btnSwitch = new JButton();
		btnSwitch.setToolTipText("Switch Mode");
		btnSwitch.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_diagram.png"));
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {	
				SQL_link_button.doClick();
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
		JLabel title = new JLabel("MANAGEMENT DETAILS   -   SQL MODE");
		title.setFont(new Font(UIManager.getLookAndFeelDefaults().getFont("MenuBar.font").getFontName(), Font.BOLD, 12));	// Use MenuBar to get current Font
		
		// Add all buttons to flow_panel
		helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
		helpToolBar.add(title);
		helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
		helpToolBar.add(btnSwitch);
		helpToolBar.add(btnHelp);

		
	    
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
	    add(database_table_scrollpane, c);				
	}
	
	
	
	public void set_up_database_table(String[] columnNames, Object[][] data) {
		// Setup the table--------------------------------------------------------------------------------
		// create database_table for SQL Mode
		model = new DefaultTableModel(data, columnNames);
		database_table = new JTable(model) {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = database_table.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(database_table,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}
			
			DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
			{ // initializer block
		        renderRight.setHorizontalAlignment(SwingConstants.RIGHT);	// set alignment of values in the database_table to the right side
		    }
			@Override
		    public TableCellRenderer getCellRenderer (int row, int column) {
				renderRight.setHorizontalAlignment(SwingConstants.RIGHT);		
		        return renderRight;
		    }
		};
		
//		// set Alignment for cells --> another method, this could not capture dynamic column changes
//        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object
//			value, boolean isSelected, boolean hasFocus, int row, int column) {
//				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//				setHorizontalAlignment(JLabel.RIGHT);
//                return this;
//            }
//        };
//		for (int i = 0; i < database_table.getColumnCount(); i++) {
//			database_table.getColumnModel().getColumn(i).setCellRenderer(r);
//		}
								
		database_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		database_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		database_table.setDefaultEditor(Object.class, null);	// make the data un-editable
	}
	
	
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doQuery(String query) {		//Note a statement not starting with SELECT is not a Query
		// Reformat the query
		if (query != null) {
			query = query.replaceAll("\\s+", " ");	// replace the spaces to be one space only
			query = query.trim();	// remove the leading and ending spaces of the query
		}
		
		if (query != null && !query.equals("")) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;		
			
			try {
				filterHeader.setTable(null);	// Hide filter before update database table
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection(conn_path);
				stmt = conn.createStatement();	
				
				if (query.toUpperCase().startsWith("SELECT")) {		//If this is a query	
					rs = stmt.executeQuery(query);

					// get columns info
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();

					// for changing column and row model
					DefaultTableModel tm = (DefaultTableModel) database_table.getModel();

					// clear existing columns
					tm.setColumnCount(0);

					// add specified columns to table
					for (int i = 1; i <= columnCount; i++) {
						tm.addColumn(rsmd.getColumnName(i));
					}

					// clear existing rows
					tm.setRowCount(0);

					// add rows to table
					while (rs.next()) {
						String[] a = new String[columnCount];
						for (int i = 0; i < columnCount; i++) {
							a[i] = rs.getString(i + 1);
						}
						tm.addRow(a);
					}
					tm.fireTableDataChanged();
				} else { // a statement that is not a query: INSERT, DELETE,...
					stmt.executeUpdate(query);
				}
				
				database_table_scrollpane.setViewportView(database_table);
				if (is_filter_visible) filterHeader.setTable(database_table);		// show filter again if it is previously visible
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
			}  finally {
				// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
			    try { rs.close(); } catch (Exception e) { /* ignored */ System.out.println(""); }	
			    try { stmt.close(); } catch (Exception e) { /* ignored */ System.out.println(""); }
			    try { conn.close(); } catch (Exception e) { /* ignored */ System.out.println(""); }
			}		
		} else {
			database_table_scrollpane.setViewportView(null);
		}
	}	
	
	
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void update_query_function_popup() {
		JPopupMenu popup = new JPopupMenu();
		Setting setting = new Setting(FilesHandle.get_file_output_system_sql_library(), FilesHandle.get_file_output_user_sql_library(), database_table, conn_path);
		
		
		// System Queries
		final JMenu system_queries_menu = new JMenu("System Library");
		MenuScroller.setScrollerFor(system_queries_menu, 25, 15, 0, 0);		// 1st number --> in the range, 2nd number --> milliseconds, 3rd number --> on top, 4th number --> at bottom
		system_queries_menu.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_database.png"));
		popup.add(system_queries_menu);	
		
		List<Object>[] system_queries_list = setting.get_query_panel().get_system_queries_list();
		for (int i = 0; i < system_queries_list[0].size(); i++) {
			final JMenuItem default_function = new JMenuItem((String) system_queries_list[0].get(i));
			int current_i = i;
			default_function.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					String query_name = (String) system_queries_list[0].get(current_i);
					String query_statement = String.join(" ", (String[]) system_queries_list[1].get(current_i)).trim();
					if (!query_statement.equals("")) {
						doQuery(query_statement);
						database_table_scrollpane.set_title("System Library:   " + query_name);
					}
				}
			});
			system_queries_menu.add(default_function);
		}
		
		
		// Users Queries
		final JMenu user_queries_menu = new JMenu("User Library");
		MenuScroller.setScrollerFor(user_queries_menu, 25, 15, 0, 0);		// 1st number --> in the range, 2nd number --> milliseconds, 3rd number --> on top, 4th number --> at bottom
		user_queries_menu.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_database.png"));
		popup.add(user_queries_menu);
		
		List<Object>[] user_queries_list = setting.get_query_panel().get_user_queries_list();
		for (int i = 0; i < user_queries_list[0].size(); i++) {
			final JMenuItem user_function = new JMenuItem((String) user_queries_list[0].get(i));
			int current_i = i;
			user_function.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					String query_name = (String) user_queries_list[0].get(current_i);
					String query_statement = String.join(" ", (String[]) user_queries_list[1].get(current_i)).trim();
					if (!query_statement.equals("")) {
						doQuery(query_statement);
						database_table_scrollpane.set_title("User Library:   " + query_name);
					}
				}
			});
			user_queries_menu.add(user_function);
		}
		
		
		// Setting
		final JMenuItem setting_menuitem = new JMenuItem("Setting");
		setting_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_setting.png"));
		setting_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				filterHeader.setTable(null);	// always disable filter when go into setting
				is_filter_visible = false;
				setting.show_popup();
			}
		});
		popup.add(setting_menuitem);
		
		
		// Filter
		final JMenuItem filter_menuitem = new JMenuItem("Filter ON/OFF");
		filter_menuitem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_filter.png"));
		filter_menuitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (filterHeader.getTable() == null) {
					filterHeader.setTable(database_table);
					is_filter_visible = true;
				} else {
					filterHeader.setTable(null);
					is_filter_visible = false;
				}
			}
		});
		popup.add(filter_menuitem);
		
		
		// Set popup menu on right click
		database_table.setComponentPopupMenu(popup);
		database_table_scrollpane.setComponentPopupMenu(popup);
	}
	
	
	
	
	
	
	public JButton get_btnSwitch() {
		return btnSwitch;
		
	}
}
