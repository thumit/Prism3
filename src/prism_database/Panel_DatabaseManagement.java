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

package prism_database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import prism_convenience.ColorUtil;
import prism_convenience.FilesHandle;
import prism_convenience.IconHandle;
import prism_convenience.MenuScroller;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_project.data_process.Read_Database;
import prism_root.Prism3Main;

@SuppressWarnings("serial")
public class Panel_DatabaseManagement extends JLayeredPane {
	private File databasesFolder = FilesHandle.get_databasesFolder();
	private String seperator = "/";
	private JTree databaseTree;
	private DefaultMutableTreeNode root, processingNode;
	private JTextField display_text_field, query_text_field;
	private JTable database_table;
	private TableFilterHeader filterHeader;
	private boolean is_filter_visible;
	private Connection conn;
	private String currenTableName, currentDatabase, currentSQLstatement;
	private int currentLevel;
	private TreePath[] selectionPaths;
	private TreePath editingPath;
	private File oldfile;
	private String fileDelimited;
	private Boolean database_name_edit_has_changed = false;
	private Boolean renamingDatabase = false;
	private Boolean renamingTable = false;
	private Boolean errorCAUGHT = false;

	private ToolBarWithBgImage databaseToolBar;
	private JScrollPane scrollPane_Left, scrollPane_Right;
	private JButton btnImportDatabase, btnNewDatabase, btnDelete, btnRefresh;
	private List<JButton> buttons_list;

	public Panel_DatabaseManagement() {
		super.setLayout(new BorderLayout(0, 0));

		
		// Set up the filter--------------------------------------------------------------------
		filterHeader = new TableFilterHeader(database_table, AutoChoices.ENABLED);
		filterHeader.setFilterOnUpdates(true);
		is_filter_visible = false;
		
		
		
		// Split panel at Center of the Internal Frame------------------------------------------
		JSplitPane splitPane = new JSplitPane();
		//splitPane.setResizeWeight(0.15);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
//		splitPane.setDividerSize(5);
//		splitPane.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		// Left split panel-----------------------------------
		scrollPane_Left = new JScrollPane();
		scrollPane_Left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
		splitPane.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Databases");
		databaseTree = new JTree(root);	//Add the root of DatabaseTree
//		DatabaseTree.setEditable(true);
		databaseTree.setInvokesStopCellEditing(true);	// Even when we leave the node by clicking mouse, the name editing will be kept 
		
		databaseTree.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
			public void mousePressed(MouseEvent e) {
				databaseTree.setEnabled(true);
				query_text_field.setText("Type your query here");
				query_text_field.setFocusable(false);
				query_text_field.setFocusable(true);
				doMousePressed(e);
			}
		});
		
//		DatabaseTree.addTreeSelectionListener(new TreeSelectionListener() {
//			public void valueChanged(TreeSelectionEvent evt) {
//				doWhenSelectionChange(evt);
//			}
//		});	
	
		databaseTree.addFocusListener(new FocusListener() {	//change name whenever node stopped editing
	         public void focusGained(FocusEvent e) {  
	        	if ((database_name_edit_has_changed == true || renamingDatabase == true) && !databaseTree.isEditing()) {
					applyDatabase_Namechange();
				}
	        	if (renamingTable == true && !databaseTree.isEditing()) {
					applyTable_Namechange();
				}
	         }
	         public void focusLost(FocusEvent e) {               
	         }
		});	// end addFocusListener
		refreshDatabaseTree(); // Refresh the tree
		scrollPane_Left.setViewportView(databaseTree);
					
		
		//Right split panel-----------------------------------
		scrollPane_Right = new JScrollPane();
		scrollPane_Right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
		splitPane.setRightComponent(scrollPane_Right);
		database_table = new JTable() {
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
		};
		database_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // this solve problems when resizing --> column width of the table becomes too narrow 
//		DatabaseTable.setColumnSelectionAllowed(true);
//		DatabaseTable.setRowSelectionAllowed(false);
		database_table.setDefaultEditor(Object.class, null);	// make the data un-editable
		scrollPane_Right.setViewportView(database_table);
		
		MouseAdapter mouse_listener = new MouseAdapter() { 	// Add listener
			public void mousePressed(MouseEvent e) {
				 if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					update_query_function_popup();
				 }
			}
		};
		// Add listeners
		database_table.addMouseListener(mouse_listener); 		// Add listener
		scrollPane_Right.addMouseListener(mouse_listener); 	// Add listener
		
		// TextField at South----------------------------------------------
		display_text_field = new JTextField("", 0);
		display_text_field.setEditable(false);
		
		// databaseToolBar & queryTextField at North----------------------------------------------------
		databaseToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		databaseToolBar.setLayout(new GridBagLayout());
		databaseToolBar.setFloatable(false);	//to make a tool bar immovable
		databaseToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor						

		
		GridBagConstraints c = new GridBagConstraints();
		btnNewDatabase = new JButton();
		btnNewDatabase.setToolTipText("New Database");
		btnNewDatabase.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_new.png"));
		btnNewDatabase.setFocusable(false);
		btnNewDatabase.addActionListener(e -> {
			refreshDatabaseTree();
			processingNode = root;
			currentLevel = 1;
			new_Database_or_Table();
		});
		databaseToolBar.add(btnNewDatabase, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		btnDelete = new JButton();
		btnDelete.setToolTipText("Delete");
		btnDelete.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_delete.png"));
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(e -> {
			delete_Databases_or_Tables();
		});
		databaseToolBar.add(btnDelete, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				1, 0, 1, 1, 0, 0,  	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		btnRefresh = new JButton();
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_refresh.png"));
		btnRefresh.setFocusable(false);
		btnRefresh.addActionListener(e -> {
			refreshDatabaseTree();
		});
		databaseToolBar.add(btnRefresh, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				2, 0, 1, 1, 0, 0,  	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
		
		btnImportDatabase = new JButton();
		btnImportDatabase.setToolTipText("Import Database");
		btnImportDatabase.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_new3.png"));
		btnImportDatabase.setFocusable(false);
		btnImportDatabase.addActionListener(e -> {
			refreshDatabaseTree();
			processingNode = root;
			currentLevel = 1;
			importDatabases();
		});
		databaseToolBar.add(btnImportDatabase, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				3, 0, 1, 1, 0, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
		
				
		query_text_field = new JTextField("Type your query here");		
		query_text_field.addMouseListener(new MouseAdapter(){			//When user click on queryTextField
			@Override
			public void mouseClicked(MouseEvent e) {
				databaseTree.setEnabled(false);		//disable the tree
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (query_text_field.getText().equals("Type your query here")) {	//clear the text
					query_text_field.setText("");					
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (query_text_field.getText().equals("") && databaseTree.isEnabled()) {
					query_text_field.setText("Type your query here");					
				}
			}
	     });//end addMouseListener
		
		query_text_field.addActionListener(new AbstractAction(){		//When user press Enter on Keyboard
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSQLstatement = query_text_field.getText();	
				doQuery(currentSQLstatement);
			}
	     });//end addActionListener
		databaseToolBar.add(query_text_field, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				4, 0, 1, 1, 1, 0,  	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));		// insets top, left, bottom, right
			
		
		// Make this list to make all buttons in this windows not focus on the ugly blue border after click
		buttons_list = new ArrayList<JButton>();
		buttons_list.add(btnNewDatabase);
		buttons_list.add(btnDelete);
		buttons_list.add(btnRefresh);
		for (JButton i : buttons_list) {
			i.setContentAreaFilled(false);
			i.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					i.setContentAreaFilled(true);
				}

				public void mouseExited(MouseEvent e) {
					i.setContentAreaFilled(false);
					if (ToolTipManager.sharedInstance().isEnabled()) {	// to avoid the case when tool-tip does not disappear immediately when gradually moving down from these buttons
			    		ToolTipManager.sharedInstance().setEnabled(false);
				    	ToolTipManager.sharedInstance().setEnabled(true);
			    	}
				}
			});	
		}	
		
	
		// Add all components to JInternalFrame-----------------------------		
		super.add(databaseToolBar, BorderLayout.NORTH);
		super.add(display_text_field, BorderLayout.SOUTH);
		super.add(splitPane, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_DatabaseManagement()	
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMousePressed(MouseEvent e) {  
    	if (!renamingTable && !renamingDatabase) {
    		filterHeader.setTable(null);		// Set null filter after each mouse click: this is important
    		is_filter_visible = false;
    		
    		TreePath path = databaseTree.getPathForLocation(e.getX(), e.getY());
    		if (path == null) {
    			databaseTree.clearSelection();		//clear selection whenever mouse click is performed not on Jtree nodes	
    			showNothing();	// show nothing (a table with 0 row and 0 column) if no node selected
    			return;
    		}
    		if (path != null) display_text_field.setText(path.toString()); 	// display Full path
//    		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
//    		dataDisplayTextField.setText(selectedNode.toString());		//display Only last node name

    		
    		selectionPaths = databaseTree.getSelectionPaths();
    		// check if node was selected
    		boolean isSelected = false;
    		if (selectionPaths != null) {
    			for (TreePath selectionPath : selectionPaths) {
    				if (selectionPath.equals(path)) {
    					isSelected = true;
    				}
    			}
    		}
    		// if clicked node was not selected, select it
    		if (!isSelected) {
    			databaseTree.setSelectionPath(path);
    		}
    	
    		
    		// Calculate current level and currentDatabaseof the last selected node		
    		currentLevel = path.getPathCount();
    		if (currentLevel == 3) {	//selected node is a table		
    			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
    			currenTableName = selectedNode.getUserObject().toString();	// Get the URL of the current selected node			
    			currentDatabase = selectedNode.getParent().toString();    	// Get the parent node which is the database that contains the selected table      					
    		} else if (currentLevel != 3) {		
    			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
    			if (currentLevel == 2) currentDatabase = selectedNode.getUserObject().toString();	// the selected node is a database
    			if (currentLevel == 1) currentDatabase = null;	// the selected node is Root
    		}
    		
    		
    		
    		
    		
    		//------------------------------------------------------------------------------------
    		//------------------------------------------------------------------------------------
    		//------------------------------------------------------------------------------------
    		update_query_function_popup();
    		//------------------------------------------------------------------------------------
    		//------------------------------------------------------------------------------------
    		//------------------------------------------------------------------------------------
    		
    		
    		
    		
    		
    		
    		if (SwingUtilities.isLeftMouseButton(e)) {
    			if (e.getClickCount() == 1) {
    				// ------------Only show DatabaseTable if the node level is 3
    				if (currentLevel == 3) {	//selected node is a table
    					scrollPane_Right.setViewportView(database_table);  
    					currentSQLstatement = "SELECT * FROM " + "[" + currenTableName + "];";		//this query show the whole table information
    					doQuery(currentSQLstatement);	
    				} else if (currentLevel != 3) {		
    					scrollPane_Right.setViewportView(null);
    				}
    			} else if (e.getClickCount() == 2) {
    				// Do something here
    				if (currentLevel == 3) {		
    					// show the filter only when double left click
    					filterHeader.setTable(database_table);
    					is_filter_visible = true;
    				}
    			}
    		} else if (SwingUtilities.isRightMouseButton(e)) {
    			if (e.getClickCount() == 1) {				
    				
    				// Some set up when there is a right click---------------------------------------------------------------
    				Boolean rootSelected =false;			
    				selectionPaths = databaseTree.getSelectionPaths();			// This is very important to get the most recent selected paths
    				int NodeCount=0;		// Count the number of nodes in selectionPaths
    				for (TreePath selectionPath : selectionPaths) {		// Loop through all selected nodes
    					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
    					if (processingNode.isRoot()) rootSelected = true;
    					NodeCount++;
    				}
    				
    				// Deselect the root if this is a multiple Nodes selection and root is selected
    				TreePath rootpath = new TreePath(root);
    				if (NodeCount>1 && rootSelected==true) {
    					databaseTree.getSelectionModel().removeSelectionPath(rootpath);
    					rootSelected =false;
    					NodeCount = NodeCount -1;
    				}
    				
    				// Reselect all nodes left
    				selectionPaths = databaseTree.getSelectionPaths();
    				for (TreePath selectionPath : selectionPaths) { // Loop through all selected nodes	
    					currentLevel = selectionPath.getPathCount();
    					
    					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
    					if (currentLevel == 3) {	// selected node is a table						
    						currenTableName = selectedNode.getUserObject().toString();
    						currentDatabase = selectedNode.getParent().toString();          			
    					} else if (currentLevel ==2) {		
    						selectedNode.getUserObject().toString();	// the selected node is a database
    					}
    				}
    				// End of set up---------------------------------------------------------------
    				
    			
    											
    				// Right clicked MenuItems only appear on nodes level 1, 2, or 3 (single or multiple nodes selection) 
    				if (currentLevel == 1 || currentLevel == 2 || currentLevel == 3) {		
    					// A popup that holds all JmenuItems
    					JPopupMenu popup = new JPopupMenu();
    					
    					
    					// All nodes can be refreshed ------------------------------------------------------------
    					final JMenuItem refreshMenuItem = new JMenuItem("Refresh");
    					refreshMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_refresh.png"));
    					refreshMenuItem.addActionListener(new ActionListener() {
    						@Override
    						public void actionPerformed(ActionEvent actionEvent) {
    							refreshDatabaseTree();
    							showNothing(); // show nothing on RightPanel and DisplayTextField
    						}
    					});
    					popup.add(refreshMenuItem);
    									
    									
    					// Only nodes level 1 (root) and 2 (databases) can have "New"--------------------------
    					// and this menuItem only shows up when 1 node is selected																	
    					if ((currentLevel == 1 || currentLevel == 2) && NodeCount==1) {
    						final JMenuItem newMenuItem = new JMenuItem();
    						if (currentLevel == 1) {
    							newMenuItem.setText("New database");
    							newMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_new.png"));
    						} else { 
    							newMenuItem.setText("New table");		
    							newMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_new1.png"));
    							newMenuItem.setEnabled(false);	// Disable until I have time to write the function
    						}
    						newMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {										
    								new_Database_or_Table();
    							}
    						});
    						popup.add(newMenuItem);
    					}
    					
    	
    					
    					// Only nodes level 1 (root) and 2 (databases) can have "Import"----------------------
    					// and this menuItem only shows up when 1 node is selected	
    					if (currentLevel == 1 && NodeCount==1) {
    						final JMenuItem importDBMenuItem = new JMenuItem("Import databases");
    						importDBMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_new3.png"));
    						importDBMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    							importDatabases();
    								} // end of actionPerformed
    							});
    						popup.add(importDBMenuItem);
    					} else
    					
    					// and this menuItem only shows up when 1 node is selected	
    					if (currentLevel == 2  && NodeCount==1) {
    						final JMenuItem importTableMenuItem = new JMenuItem("Import tables");
    						importTableMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_add4.png"));
    						importTableMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    							importTables();								
    							} //end of actionPerformed
    						});
    						popup.add(importTableMenuItem);
    					}
    					
    					
    					// Only nodes level 3 (table) can be combined--------------------------
    					// and this menuItem only shows up when >= 2 nodes are selected
    					if (currentLevel == 3 && NodeCount >= 2) {
    						final JMenuItem combineMenuItem = new JMenuItem("Combine");
    						combineMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_layer.png"));
    						combineMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								combine_Tables();
    							}
    						});
    						popup.add(combineMenuItem);
    					}
    					
    					
    					// Only nodes level 2 (database) and 3 (table) can be renamed--------------------------
    					// and this menuItem only shows up when 1 node is selected	
    					if ((currentLevel == 2 || currentLevel == 3) && NodeCount==1) {
    						final JMenuItem renameMenuItem = new JMenuItem("Rename");
    						renameMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_rename.png"));
    						renameMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								rename_Database_or_Table();
    							}
    						});
    						popup.add(renameMenuItem);
    					}
    					
    					
    					// Only nodes level 2 (Run)can have "copy"--------------------------
    					// and this menuItem only shows up when 1 node is selected	
    					if (currentLevel == 2 && NodeCount == 1) {
    						final JMenuItem copyMenuItem = new JMenuItem("Make a copy");
    						copyMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_copy.png"));
//    						copyMenuItem.setMnemonic(KeyEvent.VK_C);
    						copyMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								copy_database();
    							}
    						});
    						popup.add(copyMenuItem);
    					}
    					
    					
    					// Only nodes level 2 (database) and 3 (table) can have "copy tables"--------------------------
    					if (currentLevel == 2 || currentLevel == 3 || rootSelected ==false) {
    						final JMenuItem copyTableMenuItem = new JMenuItem("Copy tables");
    						copyTableMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_copy.png"));
    						copyTableMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								copy_tables();
    							}
    						});
    						popup.add(copyTableMenuItem);
    					}
    					
    			
    					// Only nodes level 2 (database) and 3 (table) can be deleted--------------------------
    					if (currentLevel == 2 || currentLevel == 3 || rootSelected ==false) {					
    						final JMenuItem deleteMenuItem = new JMenuItem("Delete");
    						deleteMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_delete.png"));
    						deleteMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								delete_Databases_or_Tables();
    							}
    						});
    						popup.add(deleteMenuItem);
    					}
    					
    					
    					// and this menuItem only shows up when 1 node is selected	(only use for me and will be removed)
    					if (currentLevel == 3 && currenTableName.equalsIgnoreCase("yield_tables") && NodeCount==1) {
    						final JMenuItem sampleMenuItem = new JMenuItem("Update yield tables definition");
    						sampleMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_script.png"));
    						sampleMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								update_YTdefinition();
    							}
    						});
    						popup.add(sampleMenuItem);
    					}
    					
    					
    					// and this menuItem only shows up when 1 node is selected	(only use for me and will be removed)
    					if (currentLevel == 3 && currenTableName.equalsIgnoreCase("yield_tables") && NodeCount==1) {
    						final JMenuItem sampleMenuItem = new JMenuItem("Create sample testing data - Do not use this, developers only");
    						sampleMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_warning.png"));
    						sampleMenuItem.setEnabled(false);	// Disable
    						sampleMenuItem.addActionListener(new ActionListener() {
    							@Override
    							public void actionPerformed(ActionEvent actionEvent) {								
    								create_sample();
    							}
    						});
    						popup.add(sampleMenuItem);
    					}
    				
    									
    					//Show the JmenuItems on selected node when it is right clicked
    					popup.show(databaseTree, e.getX(), e.getY());
    				}
    			}
    		}
    	}
	}
		
	// --------------------------------------------------------------------------------------------------------------------------------
//	public void doWhenSelectionChange (TreeSelectionEvent evt) {
//	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void update_query_function_popup() {
		JPopupMenu popup = new JPopupMenu();
		String conn_path = "jdbc:sqlite:" + databasesFolder + seperator + currentDatabase;
		Setting setting = new Setting(FilesHandle.get_file_dbms_system_sql_library(), FilesHandle.get_file_dbms_user_sql_library(), database_table, conn_path);
		
		
		// System Queries
		final JMenu system_queries_menu = new JMenu("System Library");
		MenuScroller.setScrollerFor(system_queries_menu, 25, 100, 0, 0);		// 1st number --> in the range, 2nd number --> milliseconds, 3rd number --> on top, 4th number --> at bottom
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
						query_text_field.setText("System Library:   " + query_name);
					}
				}
			});
			system_queries_menu.add(default_function);
		}
		
		
		// Users Queries
		final JMenu user_queries_menu = new JMenu("User Library");
		MenuScroller.setScrollerFor(user_queries_menu, 25, 100, 0, 0);		// 1st number --> in the range, 2nd number --> milliseconds, 3rd number --> on top, 4th number --> at bottom
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
						query_text_field.setText("User Library:   " + query_name);
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
//		if ((currentLevel == 2 || currentLevel == 3) && selectionPaths != null && selectionPaths.length == 1) {
		if (currentLevel > 1) {
			database_table.setComponentPopupMenu(popup);
			scrollPane_Right.setComponentPopupMenu(popup);
		} else {
			database_table.setComponentPopupMenu(null);
			scrollPane_Right.setComponentPopupMenu(null);
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doQuery(String query) {		//Note a statement not starting with SELECT is not a Query
		// Reformat the query
		if (query != null) {
			query = query.replaceAll("\\s+", " ");	// replace the spaces to be one space only
			query = query.trim();	// remove the leading and ending spaces of the query
		}
		
		if (query != null && !query.equals("")) {
			conn = null;
			Statement stmt = null;
			ResultSet rs = null;		
			
			try {
				filterHeader.setTable(null);	// Hide filter before update database table
				conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
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
				
				scrollPane_Right.setViewportView(database_table);
				if (is_filter_visible) filterHeader.setTable(database_table);		// show filter again if it is previously visible
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				errorCAUGHT=true;
			}  finally {
				// Close in case not closing properly, not need to print out because the exception only happens when there is null to close
			    try { rs.close(); } catch (Exception e) { /* ignored */ System.out.println(""); }	
			    try { stmt.close(); } catch (Exception e) { /* ignored */ System.out.println(""); }
			    try { conn.close(); } catch (Exception e) { /* ignored */ System.out.println(""); }
				errorCAUGHT = false;
			}		
		} else {
			scrollPane_Right.setViewportView(null);
		}
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void refreshDatabaseTree() {
		filterHeader.setTable(null);		//set null filter after refresh: this is important
		
		// Remove all children nodes from the root of DatabaseTree, and reload the tree
		DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		root.removeAllChildren();
		model.reload(root);

		// Find all the .db files in the predefined folder to add into to DatabaseTree	
		String files;
		File[] listOfFiles = databasesFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".db");
			}
		});

		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					DefaultMutableTreeNode level2node = new DefaultMutableTreeNode(files);
					root.add(level2node);
					
					// Read each database file and add all of its table as child nodes--------------------------------------				  
					/*Connection*/ conn = null;
					try {
						conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + files);
						DatabaseMetaData md = conn.getMetaData();
						ResultSet rs = md.getTables(null, null, "%", null);
						while (rs.next()) {
	/*						String tableCatalog = rs.getString(1);
							String tableSchema = rs.getString(2);					*/
							String tableName = rs.getString(3);
	
							DefaultMutableTreeNode level3node = new DefaultMutableTreeNode(tableName);
							level2node.add(level3node);
						}
						rs.close();
						conn.close();
					} catch (Exception e) {
						System.err.println(e.getClass().getName() + ": " + e.getMessage());
					}		
				} // end of If
			} // end of For loop		
			databaseTree.expandPath(new TreePath(root.getPath()));	//Expand the root
			if (scrollPane_Right != null) {
				scrollPane_Right.setViewportView(null);
			}
		}
		showNothing();
	} // end of update_DatabaseTree()
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void new_Database_or_Table() {
		String nodeName = null;
		if (currentLevel == 1) nodeName = "new_database";
		if (currentLevel == 2) nodeName = "new_table";
		
		if (nodeName.equals("new_database")) {		//New Database
			DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();	
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
			model.insertNodeInto(newNode, processingNode, processingNode.getChildCount());
			TreeNode[] nodes = model.getPathToRoot(newNode);
			TreePath path = new TreePath(nodes);
			databaseTree.scrollPathToVisible(path);
			databaseTree.setEditable(true);
			databaseTree.setSelectionPath(path);
			databaseTree.startEditingAtPath(path);
			editingPath = path;	
			try {
			display_text_field.setText("Type your new database name");
			database_name_edit_has_changed = true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}	
		} else 
			
												
		if (nodeName.equals("new_table")) {		//New Table									
			try {
			display_text_field.setText("This function is currently not supported");
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}	
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void rename_Database_or_Table() {
		
		if (processingNode != null && currentLevel == 2) {		//rename Database
			DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();	
			TreeNode[] nodes = model.getPathToRoot(processingNode);
			TreePath path = new TreePath(nodes);
			databaseTree.scrollPathToVisible(path);
			databaseTree.setEditable(true);
			databaseTree.setSelectionPath(path);
			databaseTree.startEditingAtPath(path);
			editingPath = path;	
			try {
				// Get and save the old database name
				DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
		    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name
		    	if(nameWOext.contains(".")) nameWOext= nameWOext.substring(0, nameWOext.lastIndexOf('.'));		//Remove extension if the name has it
		    	String editingName = databasesFolder + seperator + nameWOext + ".db";	//Add .db to the name
		    	oldfile = new File(editingName);
		    	// Then perform:	applyDatabase_Namechange
				
		    	display_text_field.setText("Type your new database name");
				renamingDatabase = true;
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		} else 
			
		
		if (processingNode != null && currentLevel == 3) {		//rename Table									
			DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
			TreeNode[] nodes = model.getPathToRoot(processingNode);
			TreePath path = new TreePath(nodes);
			databaseTree.scrollPathToVisible(path);
			databaseTree.setEditable(true);
			databaseTree.setSelectionPath(path);
			databaseTree.startEditingAtPath(path);
			editingPath = path;
			try {
				display_text_field.setText("Type your new Table name");
				renamingTable = true; // For "rename" option only
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void applyDatabase_Namechange() {
		//----------------------------------------------------
 		// This is the new Database name being applied after you finished the naming edit  	
		DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name
    	if(nameWOext.contains(".")) nameWOext= nameWOext.substring(0, nameWOext.lastIndexOf('.'));		//Remove extension if the name has it
    	String editingName = databasesFolder + seperator + nameWOext + ".db";	//Add .db to the name
    	File newfile = new File(editingName);
    	String temptext = null;
    	
    	  	
		if (renamingDatabase == true) {	//For "rename database"
			if (!newfile.exists()) {
    			oldfile.renameTo(newfile);
    			temptext = oldfile.getName() + " has been renamed to " + newfile.getName();	
    		} else {
    			temptext = "Rename fail: database with the same name exists, or name typed contains special characters";	
    		}	
		} else {	// For "new database"
			try {
				if (newfile.createNewFile()) {
					temptext = "New database has been created";		
				} else {
					temptext = "New database has not been created: database with the same name exists, or name typed contains special characters";
				}
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
    	

		// Make the new or renamed database appeared on the tree
		String DatabaseName = newfile.getName();
		refreshDatabaseTree();
		@SuppressWarnings("unchecked")
		Enumeration<TreeNode> e1 = root.depthFirstEnumeration();
		while (e1.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e1.nextElement();
			if (node.toString().equalsIgnoreCase(DatabaseName) && root.isNodeChild(node)) {		//Name match, and node is child of root
				DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
				TreeNode[] nodes = model.getPathToRoot(node);
				TreePath path = new TreePath(nodes);
				databaseTree.scrollPathToVisible(path);
				databaseTree.setSelectionPath(path);
				editingPath = path;
				selectionPaths = databaseTree.getSelectionPaths();
				processingNode = node;
				currentDatabase = DatabaseName;
				currentLevel = 2;
			}
		}
		
		display_text_field.setText(temptext);
		databaseTree.setEditable(false);			// Disable editing
		database_name_edit_has_changed = false;
		renamingDatabase = false;
	}
			
	//--------------------------------------------------------------------------------------------------------------------------------
	public void applyTable_Namechange() {	
		String temptext = null;
		DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name

		try {
			currentSQLstatement= "ALTER TABLE " + "[" + currenTableName + "]" + " RENAME TO " + "[" + nameWOext + "];";
			doQuery(currentSQLstatement);	
			temptext = "Table '" + currenTableName + "' has been renamed to " + nameWOext;
			
			// Make the table appeared on the tree
			refreshDatabaseTree();
			Enumeration<TreeNode> e = root.depthFirstEnumeration();
			while (e.hasMoreElements()) { // Search for the name that match
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
				if (node.toString().equalsIgnoreCase(currentDatabase) && root.isNodeChild(node)) {		//Name match, and node is child of root
					Enumeration<TreeNode> e2 = node.depthFirstEnumeration();
					
					while (e2.hasMoreElements()) { // Search for the name that match
						DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) e2.nextElement();
						if (newNode.toString().equalsIgnoreCase(nameWOext) && node.isNodeChild(newNode)) {		//Name match, and node is child of the database
							DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
							TreeNode[] nodes = model.getPathToRoot(newNode);
							TreePath path = new TreePath(nodes);
							databaseTree.scrollPathToVisible(path);
							databaseTree.setSelectionPath(path);
							editingPath = path;
							selectionPaths = databaseTree.getSelectionPaths();
							processingNode = newNode;
							currenTableName = nameWOext;
							currentLevel = 3;
							doQuery("SELECT * FROM " + currenTableName);
							scrollPane_Right.setViewportView(database_table);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Panel DatabaseManagement - applyTable_Namechange error - " + e.getClass().getName() + ": " + e.getMessage());
			temptext = "Cannot rename the table";
		}
		
		display_text_field.setText(temptext);
		// Disable editing
		databaseTree.setEditable(false);
		renamingTable = false;			//For "rename Table" option only
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void copy_database() {
		DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) databaseTree.getSelectionPath().getLastPathComponent();	// currentLevel = 2 & nodeCount =1 already
		databaseTree.setSelectionPath(null);
		currentDatabase = processingNode.getUserObject().toString();
				
		File sourceFile = new File(databasesFolder + seperator + currentDatabase);
		File deskFile = new File(databasesFolder + seperator + sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf(".")) + "_copy.db");
		if (deskFile.exists()) {
			String name = deskFile.getAbsolutePath().substring(0, deskFile.getAbsolutePath().lastIndexOf("."));
			int count = 2;
			while (new File(name + count + ".db").exists()) {
				count++;
			}
			deskFile = new File(name + count + ".db");
		}
		
		try {
			Files.copy(sourceFile.toPath(), deskFile.toPath());  // Make a folder copy of the Run	    
			File[] contents = sourceFile.listFiles();
		    if (contents != null) {
				for (File file_from : contents) {								
					File file_to = new File(deskFile.getAbsolutePath() + seperator + file_from.getName());
					Files.copy(file_from.toPath(), file_to.toPath());			// Copy all files inside the selected Run to the copied Run
		        }
		    }				
		} catch (IOException e) {
			System.out.println("Fail to make a copy of " + currentDatabase);
		}
		
		
		// Make the new Run appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
		String databaseName = deskFile.getName();
		refreshDatabaseTree();
		@SuppressWarnings("unchecked")
		Enumeration<TreeNode> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			if (node.toString().equalsIgnoreCase(databaseName) && root.isNodeChild(node)) {		// Name match, and node is child of root
				DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
				TreeNode[] nodes = model.getPathToRoot(node);
				TreePath path = new TreePath(nodes);
				if (path != null) display_text_field.setText(path.toString()); 	// display Full path
				databaseTree.scrollPathToVisible(path);
				databaseTree.setSelectionPath(path);
				editingPath = path;
				selectionPaths = databaseTree.getSelectionPaths();
				processingNode = node;
				currentDatabase = databaseName;
				currentLevel = 2;
			}
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void copy_tables() {
		//Some set up ---------------------------------------------------------------
		int node_Level = 0;
		for (TreePath selectionPath : selectionPaths) { // Loop through all selected nodes
			processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
			node_Level = selectionPath.getPathCount();
				if (node_Level==2 && processingNode.getChildCount() >= 0) {		//If node is a database and has childs then deselect all of its childs				
				for (Enumeration e = processingNode.children(); e.hasMoreElements();) {
					TreeNode child = (TreeNode) e.nextElement();
					DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
					TreeNode[] nodes = model.getPathToRoot(child);
					TreePath path = new TreePath(nodes);
					databaseTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
				}
				databaseTree.collapsePath(new TreePath(processingNode.getPath()));	//Collapse the selected database	
			}
		}
		selectionPaths = databaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
		// End of set up---------------------------------------------------------------
				
	
		
		//Loop through all databases in the working folder
		File[] listOfFiles = databasesFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".db");
			}
		});
		
		//Get databases names and make an array to store names
		String[] listNames;
		if (listOfFiles != null) {
			listNames = new String [listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++) {			
					listNames[i] = listOfFiles[i].getName();			
			}
					
			// Show a JOptionPane for the destination of copied tables---------------------------------------------------
			String initialSelection = listNames[0];			//First database shows up
			String selection = (String) JOptionPane.showInputDialog(this,		
					"Copy highlighted tables & all tables in highlighted databases, and paste into", "Select a database as destination to paste",
					JOptionPane.QUESTION_MESSAGE, null, listNames, initialSelection);
			
			if (selection != null) {	// DO ONLY WHEN USER PRESS 'OK' 
				// Find the database user selected
				String destinationFileName = null;
				for (int i = 0; i < listNames.length; i++) {
					if (selection.equals(listNames[i])) {
						destinationFileName = listNames[i];
					}
				}
				
				// Find the destination node
				DefaultMutableTreeNode destinationNode = new DefaultMutableTreeNode(destinationFileName);
				Enumeration<TreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) {		//Search for the name that match
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (node.toString().equalsIgnoreCase(destinationFileName) && root.isNodeChild(node)) {		//Name match, and node is child of root
						destinationNode = node;
					}
				}
				
				
				// Loop through all selected tables and databases the users want to copy
				// Count the number of tables (including tables inside selected databases)
				int numberofTables = 0;
				int nodeLevel;
				DefaultMutableTreeNode node;
				for (TreePath selectionPath : selectionPaths) {
					nodeLevel = selectionPath.getPathCount();
	
					if (nodeLevel == 3)
						numberofTables++; //Level 3 nodes: tables							
					else if (nodeLevel == 2) { //Level 2 nodes: databases				
						node = (DefaultMutableTreeNode) selectionPath.getLastPathComponent(); //This is the level 2 node			
						numberofTables = numberofTables + node.getChildCount(); //add number of tables count
					}
				}
	//			System.out.println(numberofTables);		
	//			System.out.println(selection);
			
				//Define arrays to store all tables SQL connection information: databases and tables info are needed		
				String[] copiedTables_names = new String[numberofTables];
				String[] database_of_copiedTables = new String[numberofTables];
				int ii = 0;
				for (TreePath selectionPath : selectionPaths) {
	
					currentLevel = selectionPath.getPathCount();
					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
	
					if (currentLevel == 3) {
						database_of_copiedTables[ii] = processingNode.getParent().toString();
						copiedTables_names[ii] = processingNode.getUserObject().toString();
						ii++;
					}
	
					else if (currentLevel == 2) { // Level 2 nodes: databases
						// Loop through all tables in this database to get information		
						Enumeration<TreeNode> e1 = processingNode.depthFirstEnumeration();
						while (e1.hasMoreElements()) {		//Search for the name that match
							DefaultMutableTreeNode childnode = (DefaultMutableTreeNode) e1.nextElement();
							if (processingNode.isNodeChild(childnode)) {		//If table (childnode) is child of that database (processingNode)
								database_of_copiedTables[ii] = childnode.getParent().toString();
								copiedTables_names[ii] = childnode.getUserObject().toString();
								ii++;
							}
						}
					}
				}
				//---------------------------------------------------		
	
				int count = 0;		//just help to know how many tables have been added
				
				// Now loop through all copied tables and add them to the destine database		
				for (int i = 0; i < numberofTables; i++) { // Loop through all copied tables
	
					try {
						Connection con_from = DriverManager
								.getConnection("jdbc:sqlite:" + databasesFolder + seperator + database_of_copiedTables[i]);
	
						Statement st = con_from.createStatement();					
						ResultSet rs = st.executeQuery("SELECT * FROM " + "[" + copiedTables_names[i] + "];");
						
						//Get columns name and name_modified should be:	'ColumnName' TEXT, 'ColumnName' TEXT, 'ColumnName' TEXT,.......   
						ResultSetMetaData rsmd = rs.getMetaData();
						int columnCount = rsmd.getColumnCount();
						
						String[] columnNames = new String[columnCount];
						String name_modified = new String();
						
						// The column count in rsmd starts from 1
						for (int jj = 0; jj < columnCount; jj++ ) {
							columnNames[jj] = rsmd.getColumnName(jj+1);		//jj+1 is because rsmd has colum names indexed from 1 to n
							name_modified = name_modified + "[" + columnNames[jj] + "] TEXT, ";
						}
						name_modified = name_modified.substring(0, name_modified.lastIndexOf(','));		//Remove the last ","
						//-----------------------------------------------------------
			
											
						//Now connect to destine database----------------------------		
						Connection con_to = DriverManager
								.getConnection("jdbc:sqlite:" + databasesFolder + seperator + destinationNode);											
						con_to.setAutoCommit(false);										
						PreparedStatement pst = null;
				
						//Create table 
						String statement;
						statement = "CREATE TABLE " + "[" + copiedTables_names[i] + "]" + " (" + name_modified + ");";		
						pst= con_to.prepareStatement(statement);
						pst.executeUpdate();
						
						
						//Insert value into tables
						statement = "";		//reset to make new statement													
						String currentRow;			//Make currentRow to be:		'value1', 'value2', .....
						while (rs.next()) {		//Loop through all rows of the copied table
							currentRow = "";	//This is important to make the first member of this String not "null"
						    for (int jj = 1; jj < columnCount + 1; jj++) {		
								currentRow = (rs.getString(jj) == null) ? currentRow + "null, "
						    			: currentRow + "'" + rs.getString(jj).replace("'", "''") +"'" +", "; 	//Escape the ' (i.e. xEAe') by replace
						    }
						    currentRow = currentRow.substring(0, currentRow.lastIndexOf(','));		//Remove the last "," 				      
						   
						    //Execute the insert
						    statement ="INSERT INTO " + "[" + copiedTables_names[i] + "]" + " VALUES (" + currentRow + "); ";	// [] surrounds tableName		 				
							
							pst= con_to.prepareStatement(statement);
							pst.executeUpdate();
						}
						
						
						//Close all statements, connections------------------------------------
						rs.close();
						st.close();
						con_from.close();
						
						pst.close();
						con_to.commit();			//commit all prepared execution, this is important
						con_to.close();
					}
	
					catch (SQLException e1) {
						JOptionPane.showMessageDialog(this, e1, e1.getMessage(), WIDTH, null);
						errorCAUGHT = true;
					}
					
								
					//Show tables			
					if (errorCAUGHT.equals(false)) {
						// Make the new table appear on the TREE----------->YEAHHHHHHHHHHHHHHH
						DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
						DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(copiedTables_names[i]);
						model.insertNodeInto(newNode, destinationNode, destinationNode.getChildCount());
						TreeNode[] nodes = model.getPathToRoot(newNode);
						TreePath path = new TreePath(nodes);
						databaseTree.scrollPathToVisible(path);
						// DatabaseTree.setEnabled(false);
						// DatabaseTree.setEditable(true);
						if (count ==0) databaseTree.setSelectionPath(path);			//this help deselect all original nodes
						// DatabaseTree.startEditingAtPath(path);
						editingPath = path;			
						processingNode = newNode;
						databaseTree.getSelectionModel().addSelectionPath(path); // add childs into selection but not show table
						count ++;
					}
					errorCAUGHT = false;	
					
				}	//End of For loop
			
				selectionPaths = databaseTree.getSelectionPaths();
				currentDatabase = destinationFileName;
				currentLevel = 2;
			}	//End of if (selection != null)
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void importDatabases() {
		// Open File chooser
		File[] files = FilesChooser.chosenDatabases(); 
		
		if (files!= null) {
			//Loop through all files, each is a table
			for (int i = 0; i < files.length; i++) {							
				File sourceFile = files[i];
				File deskFile = new File(databasesFolder + seperator + sourceFile.getName());
				// Copy and paste
				String temptext = null;
				try {
					if (deskFile.exists() == false) {
						Files.copy(sourceFile.toPath(), deskFile.toPath());
						temptext = "'" + deskFile.getName() + "' has been imported";
					} else if (deskFile.exists() == true) {	
						int response = JOptionPane.showConfirmDialog(this, "Do you want to overwrite the existing database " + deskFile.getName() +" ?", "Confirm",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.NO_OPTION) {
							temptext = "The existing database '" + deskFile.getName() + "' has not been overwritten";
						} else if (response == JOptionPane.YES_OPTION) {
							Files.copy(sourceFile.toPath(), deskFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							temptext = "Existing database '" + deskFile.getName() + "' has been overwritten";
						} else if (response == JOptionPane.CLOSED_OPTION) {
							temptext = "'" + deskFile.getName() + "' has not been overwritten";
						}
					}
				} catch (IOException e) {
					System.err.println("importDatabases - copy error - " + e.getClass().getName() + ": " + e.getMessage());
				}
				
				// Make the new Databases appear on the TREE----------->YEAHHHHHHHHHHHHHHH
				String DatabaseName = deskFile.getName();
				refreshDatabaseTree();
				@SuppressWarnings("unchecked")
				Enumeration<TreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) { // Search for the name that match
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (node.toString().equalsIgnoreCase(DatabaseName) && root.isNodeChild(node)) {		//Name match, and node is child of root
						DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
						TreeNode[] nodes = model.getPathToRoot(node);
						TreePath path = new TreePath(nodes);
						databaseTree.scrollPathToVisible(path);
						databaseTree.setSelectionPath(path);
						editingPath = path;
					}
				}
				display_text_field.setText(temptext);
			} // end of For loop
			
					
		}	
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void importTables () {

		// Open File chooser
		File[] files = FilesChooser.chosenTables(); 
		
		if (files!= null) {
			// Loop through all files to get extension, match extension with delimited
			List<String> extentionList = new ArrayList<String>();	//A list contain all extension that have its delimited identified							
			List<String> delimitedList = new ArrayList<String>();	//A list contain all delimited. same structure as the extentionList	
				
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					File currentfile = files[i];
					String extension = "";
					fileDelimited = "";
					int jj = currentfile.getName().lastIndexOf('.');
					if (jj > 0) {
						extension = currentfile.getName().substring(jj + 1);
					}
					if (extension.equalsIgnoreCase("csv")) {
						fileDelimited = ",";
						extentionList.add("csv");
						delimitedList.add(",");
					} else if (extension.equalsIgnoreCase("yld")) {
						fileDelimited = "\\s+";
						extentionList.add("yld");
						delimitedList.add("\\s+");
					} else if (!extentionList.contains(extension.toUpperCase())) {
						// Choose the right delimited
						// JDialog.setDefaultLookAndFeelDecorated(true);
						UIManager.put("OptionPane.cancelButtonText", "Cancel");
						UIManager.put("OptionPane.okButtonText", "Import");

						Object[] selectionValues = { "Comma", "Space", "Tab" };
						String initialSelection = "Comma";
						String selection = (String) JOptionPane.showInputDialog(this, "The delimited type for all '." + extension + "' files (i.e. " + currentfile.getName() + ") is",
								"Specify delimited type", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
							
						if (selection == "Comma") {
							fileDelimited = ",";
						} else if (selection == "Space") {
							fileDelimited = "\\s+";
						} else if (selection == "Tab") {
							fileDelimited = "\t";
						} else if (selection == null) {
							fileDelimited = null;
						}
						extentionList.add(extension.toUpperCase());
						delimitedList.add(fileDelimited);

						UIManager.put("OptionPane.cancelButtonText", "Cancel");
						UIManager.put("OptionPane.okButtonText", "Ok");
					}
				}
			}
													
			
			
			//Prepared statement multiple level
			try {
				//-------------------------------------------------
				conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
				conn.setAutoCommit(false);										
				PreparedStatement pst = null;
				//-------------------------------------------------
				// Loop through all saved extension to find the one match the current file, then return the delimited for that file 
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						String extension = null;
						File currentfile = files[i];
						int jj = currentfile.getName().lastIndexOf('.');
						if (jj > 0) {
							extension = currentfile.getName().substring(jj + 1);
						}
		
						// Find the delimited of this currentFile
						for (int j = 0; j < extentionList.size(); j++) {
							if (extentionList.get(j).equals(extension.toUpperCase())) {
								fileDelimited = delimitedList.get(j); // This is the returned delimited
							}
						}
						
						if (fileDelimited != null) {
							// Get info from the file
							SQLite.create_import_table_statement(currentfile, fileDelimited);		//Read file into arrays
							String[] statement = SQLite.get_importTable_Stm();		//this arrays hold all the statements
							// Prepared execution
							for (String st : statement) {
								pst = conn.prepareStatement(st);
								pst.executeUpdate();
							}
						}
					} // end of If
				} // end of For loop
				//Commit execution-------------------------------------------------
				pst.close();
				conn.commit(); // commit all prepared execution, this is important
				conn.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				errorCAUGHT = true;
			}		
			//-------------------------------------------------
			
		
			
			//Show new imported tables-----------------------------------------------------------------------
			int count = 0; // just help to know how many tables have been added
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					String extension = null;
					File currentfile = files[i];
					int jj = currentfile.getName().lastIndexOf('.');
					if (jj > 0) {		//to prevent empty name (before dot) where jj=0, or no dot where jj=-1 
						extension = currentfile.getName().substring(jj + 1);
					}

					// add import tables into selection path
					if (errorCAUGHT.equals(false)) {
						// Make the new table appear on the
						// TREE----------->YEAHHHHHHHHHHHHHHH
						String tableName = currentfile.getName();
						if (tableName.contains("."))
							tableName = tableName.substring(0, tableName.lastIndexOf('.'));
						final String NodeName = tableName;
						DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
						DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(NodeName);
						model.insertNodeInto(newNode, processingNode, processingNode.getChildCount());
						TreeNode[] nodes = model.getPathToRoot(newNode);
						TreePath path = new TreePath(nodes);
						databaseTree.scrollPathToVisible(path);
						// DatabaseTree.setEnabled(false);
						// DatabaseTree.setEditable(true);
						if (count == 0)
							databaseTree.setSelectionPath(path); // this help deselect all original nodes
						// DatabaseTree.startEditingAtPath(path);
						editingPath = path;
						databaseTree.getSelectionModel().addSelectionPath(path); // add childs into selection but not show table
						count++;
					}
					errorCAUGHT = false;
				}
			} // end of For loop						
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void combine_Tables() {	
		//Some set up ---------------------------------------------------------------
		int node_Level = 0;
		List<String> selected_databases_list = new ArrayList<String>();
		for (TreePath selectionPath : selectionPaths) { // Loop through all selected nodes
			processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
			node_Level = selectionPath.getPathCount();
			if (node_Level == 2) { // If node is a database then add to the list and deselect the database
				selected_databases_list.add(processingNode.toString());
				databaseTree.getSelectionModel().removeSelectionPath(selectionPath);	
			} else if ((node_Level == 3) && !selected_databases_list.contains(processingNode.getParent().toString())) { // If node is a table then add is parent (database) to the list if not added yet
				selected_databases_list.add(processingNode.getParent().toString());
			}
		}
		int total_databases = selected_databases_list.size();
		selectionPaths = databaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
		// End of set up---------------------------------------------------------------		
		
		
		// Combines tables only if total_databases = 1 and there are at least 2 selected tables
		if (total_databases == 1 && selectionPaths.length >= 2) {
			currentDatabase = selected_databases_list.get(0);
			DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
			String queryText = "CREATE TABLE combine_table AS";
			for (TreePath selectionPath : selectionPaths) {		//Loop through all nodes	
				processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
				currenTableName = processingNode.getUserObject().toString();
				queryText = queryText + " SELECT * FROM [" + currenTableName + "] UNION";			
			}
			queryText= queryText.substring(0, queryText.lastIndexOf(" "));			
			doQuery(queryText);
			
			
			if (errorCAUGHT.equals(false)) {
				// Make the combine_table appeared on the tree
				refreshDatabaseTree();
				@SuppressWarnings("unchecked")
				Enumeration<TreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) { // Search for the name that match
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (node.toString().equalsIgnoreCase(currentDatabase) && root.isNodeChild(node)) {		//Name match, and node is child of root
						Enumeration<TreeNode> e2 = node.depthFirstEnumeration();
						
						while (e2.hasMoreElements()) { // Search for the name that match
							DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) e2.nextElement();
							if (newNode.toString().equalsIgnoreCase("combine_table") && node.isNodeChild(newNode)) {		//Name match, and node is child of the database
								/*DefaultTreeModel */model = (DefaultTreeModel) databaseTree.getModel();
								TreeNode[] nodes = model.getPathToRoot(newNode);
								TreePath path = new TreePath(nodes);
								databaseTree.scrollPathToVisible(path);
								databaseTree.setSelectionPath(path);
								editingPath = path;
								
								processingNode = newNode;
								currenTableName = "combine_table";
								doQuery("SELECT * FROM combine_table");
								scrollPane_Right.setViewportView(database_table);
							}
						}
						
					}
				}
			}
			errorCAUGHT = false;
			
			
//			//Show the combine_table-----------------------------------------------------------------------
//			if (errorCAUGHT.equals(false)) {
//				/*DefaultTreeModel */model = (DefaultTreeModel) DatabaseTree.getModel();
//				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("combine_table");
//				model.insertNodeInto(newNode, (DefaultMutableTreeNode) processingNode.getParent(), processingNode.getChildCount());
//				TreeNode[] nodes = model.getPathToRoot(newNode);
//				TreePath path = new TreePath(nodes);
//				DatabaseTree.scrollPathToVisible(path);
//				DatabaseTree.setSelectionPath(path);
//				editingPath = path;
//				
//				processingNode = newNode;
//				currenTableName = "combine_table";
//			}
//			errorCAUGHT = false;
		}
	}	
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void delete_Databases_or_Tables() {	
		//Some set up ---------------------------------------------------------------
		if (selectionPaths != null) {
			int node_Level;
			for (TreePath selectionPath : selectionPaths) {		//Loop through all selected nodes
				processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
				node_Level = selectionPath.getPathCount();
				if (node_Level==2 && processingNode.getChildCount() >= 0) {		//If node is a database and has childs then deselect all of its childs				
					for (Enumeration e = processingNode.children(); e.hasMoreElements();) {
						TreeNode child = (TreeNode) e.nextElement();
						DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();	
						TreeNode[] nodes = model.getPathToRoot(child);
						TreePath path = new TreePath(nodes);
						databaseTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
					}
					databaseTree.collapsePath(new TreePath(processingNode.getPath()));	//Collapse the selected database				
				}	
			}
			selectionPaths = databaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
		}
		//End of set up---------------------------------------------------------------
		
		
		
		try {
			if (selectionPaths != null) {		//at least 1 database or table has to be selected 
				//Ask to delete 
				String ExitOption[] = {"Delete", "Cancel"};
				int response = JOptionPane.showOptionDialog(Prism3Main.get_Prism_DesktopPane(), "Delete highlighted tables & all tables in highlighted databases?", "Confirm Delete",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
				if (response == 0) {
					DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();	
						
					
					for (TreePath selectionPath : selectionPaths) { // Loop through all again and delete all level 2 nodes (databases)
						currentLevel = selectionPath.getPathCount();
						DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
						if (currentLevel == 2) { // DELETE Databases
							currentDatabase = processingNode.getUserObject().toString();
							model.removeNodeFromParent(processingNode);
							File file = new File(databasesFolder + seperator + currentDatabase);
							file.delete();							
						}
					}
					
					
					
					selectionPaths = databaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
					if (selectionPaths != null) {		//at least 1 database or table has to be selected 
						conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
						conn.setAutoCommit(false);
						PreparedStatement pst = null;
						
						
					    for (TreePath selectionPath : selectionPaths) {		//Loop through all and delete all level 3 nodes
							currentLevel = selectionPath.getPathCount();
							processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
								
							if (currentLevel == 3) {		//DELETE Tables						
								//Find all notes that share the same parent (same database) and currently in the selectionPath, group them into 1 delete transection						
									
									
								String nextDatabase = processingNode.getParent().toString();
								currenTableName = processingNode.getUserObject().toString();
								model.removeNodeFromParent(processingNode);
								
								if (!nextDatabase.equals(currentDatabase)) {
									conn.commit(); // commit all prepared execution, this is important
									conn.close();
									
									currentDatabase = nextDatabase;
									conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);		
									conn.setAutoCommit(false);	
									
									pst = conn.prepareStatement("DROP TABLE IF EXISTS " + "[" + currenTableName + "]");
									pst.executeUpdate();
								} else {
									pst = conn.prepareStatement("DROP TABLE IF EXISTS " + "[" + currenTableName + "]");
									pst.executeUpdate();
								}
											
							}
						}
							
					    //Commit execution-------------------------------------------------
						pst.close();
						conn.commit(); // commit all prepared execution, this is important
						conn.close();
						showNothing();
					}
					
					

				} else if (response == JOptionPane.CLOSED_OPTION) {
				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
			errorCAUGHT = true;
		}	
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void update_YTdefinition() {
		
		//Establish connection to the last node's database	
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
			conn.setAutoCommit(false);
			PreparedStatement pst = null;
			
			try {
				//Create table with unique 'term'
				pst = conn.prepareStatement("CREATE TABLE IF NOT EXISTS yield_tables_definition (col_index INTEGER, col_name TEXT, description TEXT, unit TEXT, data_type, UNIQUE(col_name));");
				pst.executeUpdate();
				
				//insert term = table_ColumnNames, description = from default library, data_type = TEXT: 
				//INSERT OR IGNORE works with unique 'term' so if we run the codes another time, it will not insert the current 'term'
				ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM yield_tables LIMIT 1;");	
				ResultSetMetaData rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				String[] table_ColumnNames = new String[colCount];
				for (int i = 1; i <= colCount; i++) {		//this start from 1
					table_ColumnNames[i-1] = rsmd.getColumnName(i);			//Note that tableColumnNames start from 0
				}
				rs.close();
				Read_Database read_Database = new Read_Database(null);
				for (int i = 0; i < colCount; i++) {	
					pst = conn.prepareStatement("INSERT OR IGNORE INTO yield_tables_definition (col_index, col_name, description, data_type) VALUES(" 
									+ i + ", '" + table_ColumnNames[i] + "', '" + read_Database.get_parameter_tooltip(table_ColumnNames[i]) + "', 'TEXT');");
					pst.executeUpdate();
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				e.printStackTrace();  // This will give line number
			}
			
			
			//Commit execution-------------------------------------------------
			if (pst != null) pst.close();
			conn.commit(); // commit all prepared execution, this is important
			conn.close();						
			
			
			//Show the yield_tables_definition
			if (errorCAUGHT.equals(false)) {
				// Make the combine_table appeared on the tree
				refreshDatabaseTree();
				@SuppressWarnings("unchecked")
				Enumeration<TreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) { // Search for the name that match
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (node.toString().equalsIgnoreCase(currentDatabase) && root.isNodeChild(node)) {		//Name match, and node is child of root
						Enumeration<TreeNode> e2 = node.depthFirstEnumeration();
						
						while (e2.hasMoreElements()) { // Search for the name that match
							DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) e2.nextElement();
							if (newNode.toString().equalsIgnoreCase("yield_tables_definition") && node.isNodeChild(newNode)) {		//Name match, and node is child of the database
								DefaultTreeModel model = (DefaultTreeModel) databaseTree.getModel();
								TreeNode[] nodes = model.getPathToRoot(newNode);
								TreePath path = new TreePath(nodes);
								databaseTree.scrollPathToVisible(path);
								databaseTree.setSelectionPath(path);
								editingPath = path;
								
								processingNode = newNode;
								currenTableName = "yield_tables_definition";
								doQuery("SELECT * FROM yield_tables_definition");
								scrollPane_Right.setViewportView(database_table);
							}
						}
						
					}
				}
			}
			errorCAUGHT = false;				
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}			
	}		
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void create_sample() {
		
		//Establish connection to the last node's database	
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
			conn.setAutoCommit(false);
			PreparedStatement pst = null;
			
	
//			// Change yield table (strata) name and add MS tables
//			try {
//				//get the strata name and put into a list		
//				List<String> strata_list = new ArrayList<String>();	
//				pst = conn.prepareStatement("SELECT DISTINCT strata FROM yield_tables;");
//				ResultSet rs = pst.executeQuery();			
//				while (rs.next()) {
//					strata_list.add(rs.getString(1));		//column 1
//				}
//				rs.close();
//				
//				//new strata name
//				List<String> new_strata_list = new ArrayList<String>();	
//				String covertype, sizeclass, method, timing_choice, forest_status, new_name;
//				for (String strata: strata_list) {
//					if (strata.substring(0,1).equalsIgnoreCase("v")) {
//						covertype = strata.substring(0, 4);
//						sizeclass = strata.substring(4, 6);
//						method = strata.substring(6, 7);
//						timing_choice = strata.substring(7, 8);
//						forest_status = strata.substring(8, 9);
//						
//						if (covertype.equalsIgnoreCase("VDIP")) covertype = "P";
//						if (covertype.equalsIgnoreCase("VDTD")) covertype = "D";
//						if (covertype.equalsIgnoreCase("VMIW")) covertype = "W";
//						if (covertype.equalsIgnoreCase("VMTC")) covertype = "C";
//						if (covertype.equalsIgnoreCase("VSII")) covertype = "I";
//						if (covertype.equalsIgnoreCase("VSTA")) covertype = "A";
//						if (covertype.equalsIgnoreCase("VLPP")) covertype = "L";
//						if (covertype.equalsIgnoreCase("NS")) covertype = "N";					
//						
//						if (sizeclass.equalsIgnoreCase("50")) sizeclass = "N";
//						if (sizeclass.equalsIgnoreCase("30")) sizeclass = "S";
//						if (sizeclass.equalsIgnoreCase("20")) sizeclass = "P";
//						if (sizeclass.equalsIgnoreCase("13")) sizeclass = "M";
//						if (sizeclass.equalsIgnoreCase("12")) sizeclass = "L";	
//						
//						if (method.equalsIgnoreCase("A")) method = "NG";
//						if (method.equalsIgnoreCase("B")) method = "EA";
//						if (method.equalsIgnoreCase("C")) method = "GS";
//						if (method.equalsIgnoreCase("D")) method = "PB";
//						if (method.equalsIgnoreCase("E")) method = "MS";
//									
//						//Update strata with new name					
//						new_name = covertype + "_" + sizeclass + "_" + method + "_" + forest_status + "_" + timing_choice;
//						new_name = new_name.toUpperCase();
//						new_strata_list.add(new_name);
//						pst = conn.prepareStatement("UPDATE yield_tables SET strata = '" + new_name + "' WHERE strata = '" + strata + "';");
//						pst.executeUpdate();
//					}
//				}		
//				
//				//Create MS tables based on PB tables
//				pst = conn.prepareStatement("CREATE TABLE ms_tables AS SELECT * FROM yield_tables WHERE strata LIKE '%PB%';");
//				pst.executeUpdate();	
//				for (String new_strata: new_strata_list) {
//					pst = conn.prepareStatement("UPDATE ms_tables SET strata = '" + new_strata.replace("PB", "MS") + "' WHERE strata = '" + new_strata + "';");
//					pst.executeUpdate();
//				}
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//			}
//					
//			
//			try {			
//				//Combine yield_tables & ms_tables
//				pst = conn.prepareStatement("CREATE TABLE combine_table AS SELECT * FROM yield_tables union SELECT * FROM ms_tables;");
//				pst.executeUpdate();		
//				
//				//delete yield_tables & ms_tables
//				pst = conn.prepareStatement("DROP TABLE yield_tables;");
//				pst.executeUpdate();
//				
//				pst = conn.prepareStatement("DROP TABLE ms_tables;");
//				pst.executeUpdate();
//				
//				//rename combine_table to yield_tables
//				pst = conn.prepareStatement("ALTER TABLE combine_table RENAME TO yield_tables;");
//				pst.executeUpdate();
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//			}
			
			
			
			

			
//			// Change timing choice of EAe and EAr table to be the rotation age class (the max age of this EA table) - and delete all EAr tables
//			try {		
//				// Delete all EAr tables
//				pst = conn.prepareStatement("DELETE FROM yield_tables WHERE strata LIKE '%_R_%';");		
//				pst.executeUpdate();
//				
//				
//				//get the strata name and put into a list		
//				List<String> strata_list = new ArrayList<String>();	
//				pst = conn.prepareStatement("SELECT DISTINCT strata FROM yield_tables;");
//				ResultSet rs = pst.executeQuery();			
//				while (rs.next()) {
//					strata_list.add(rs.getString(1));		//column 1
//				}
//				rs.close();
//				
//				//new strata name
//				String covertype, sizeclass, method, timing_choice, forest_status, new_name;
//				for (String strata: strata_list) {
//					covertype = strata.substring(0, 1);
//					sizeclass = strata.substring(2, 3);
//					method = strata.substring(4, 6);						
//					forest_status = strata.substring(7, 8);
//					timing_choice = strata.substring(9, 10);
//					
//					
//					// Find the min age
//					pst = conn.prepareStatement("SELECT min(CAST(st_age_10 as decimal)) FROM yield_tables WHERE strata = '" + strata + "';");
//					ResultSet rs1 = pst.executeQuery();	
//					String starting_age = rs1.getString(1);					
//					rs1.close();
//					
//					// Find the max age
//					pst = conn.prepareStatement("SELECT max(CAST(st_age_10 as decimal)) FROM yield_tables WHERE strata = '" + strata + "';");
//					ResultSet rs2 = pst.executeQuery();	
//					String rotation_age = rs2.getString(1);					
//					rs2.close();
//					
//					// Count number of row
//					pst = conn.prepareStatement("SELECT COUNT(st_age_10) FROM yield_tables WHERE strata = '" + strata + "';");
//					ResultSet rs3 = pst.executeQuery();	
//					String total_rows = rs3.getString(1);					
//					rs3.close();
//					
//															
//					// Update EAe strata with new name where timing_choice would be replaced by rotation_age					
//					new_name = covertype + "_" + sizeclass + "_" + method + "_" + forest_status + "_" + rotation_age;
//					new_name = new_name.toUpperCase();
//					
//					if (method.equalsIgnoreCase("EA")) {	
//						if (Integer.parseInt(total_rows) == Integer.parseInt(rotation_age) - Integer.parseInt(starting_age) + 1){	 // Update if this table has enough rows
//							pst = conn.prepareStatement("UPDATE yield_tables SET strata = '" + new_name + "' WHERE strata = '" + strata + "';");
//							pst.executeUpdate();
//						} else {		// Delete all EAe tables if table this table lacks rows
//							pst = conn.prepareStatement("DELETE FROM yield_tables WHERE strata = '" + strata + "';");	
//							pst.executeUpdate();
//						}					
//					}	
//					
//				}		
//				
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//			}
			
			
			
						
			
			
			
//			try {
//				// add action_id
//				pst = conn.prepareStatement("ALTER TABLE yield_tables ADD COLUMN action_id TEXT;");
//				pst.executeUpdate();
//
//				
//				// action_id = random number (max = total actions)
////				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = ABS (RANDOM() % (SELECT COUNT(action_id) FROM management_actions));");	
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = ABS (RANDOM() % 10);");	// Random first action_id from 0-9
//				pst.executeUpdate();
//				
//				
//				
//				// NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG NG-------------------
//				// no_action if method = NG 
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = '0' WHERE strata LIKE '%_NG_%';");
//				pst.executeUpdate();	
//			
//						
//				
//				// EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA EA--------------------
//				// action_id = random(15-->20) if  method = EA   and   not the row of rotation (clear cut not happens)   and   trt_acres <> 0.00
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = 15 + ABS (RANDOM() % 6) "
//						+ "WHERE (strata LIKE '%_EA_%' AND substr(strata,10,2) <> st_age_10 AND trt_acres <> '0.00')"
//						+ ";");
//				pst.executeUpdate();			
//				
//				// action_id = random(21-->24) if  method = EA and in the row of rotation (clear cut happens)
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = 21 + ABS (RANDOM() % 4) "
//						+ "WHERE strata LIKE '%_EA_%' AND substr(strata,10,2) = st_age_10"
//						+ ";");
//				pst.executeUpdate();	
//				
//				// action_id = 0 if  method = EA  and   trt_acres = 0.00
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = '0' "
//						+ "WHERE strata LIKE '%_EA_%' AND trt_acres = '0.00'"
//						+ ";");
//				pst.executeUpdate();
//				
//				
//				
//				// GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS GS----------------------		
//				// action_id = random(11-->20) if  method = GS    and   trt_acres <> 0.00
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = 11 + ABS (RANDOM() % 10) "
//						+ "WHERE (strata LIKE '%_GS_%' AND trt_acres <> '0.00')"
//						+ ";");
//				pst.executeUpdate();
//				
//				// action_id = 0 if  method = GS  and   trt_acres = 0.00
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = '0' "
//						+ "WHERE strata LIKE '%_GS_%' AND trt_acres = '0.00'"
//						+ ";");
//				pst.executeUpdate();
//				
//				
//				
//				// PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB PB----------------------
//				// action_id = 0
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = '0' "
//						+ "WHERE strata LIKE '%_PB_%'"
//						+ ";");
//				pst.executeUpdate();
//				
//				// action_id = = random(2-->9) for every random (3-6) periods if  method = PB
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = 2 + ABS (RANDOM() % 8) "
//						+ "WHERE strata LIKE '%_PB_%' AND CAST(st_age_10 as decimal) % (3 + ABS (RANDOM() % 4)) = 0"
//						+ ";");
//				pst.executeUpdate();
//				
//				
//				
//				// MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS MS----------------------
//				// action_id = 0
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = '0' "
//						+ "WHERE strata LIKE '%_MS_%'"
//						+ ";");
//				pst.executeUpdate();
//				
//				// action_id = = random(4-->9) for every random (4-8) periods if  method = PB
//				pst = conn.prepareStatement("UPDATE yield_tables SET action_id = 4 + ABS (RANDOM() % 6) "
//						+ "WHERE strata LIKE '%_MS_%' AND CAST(st_age_10 as decimal) % (4 + ABS (RANDOM() % 5)) = 0"
//						+ ";");
//				pst.executeUpdate();
//				
//				
//				
//				
//				
//				
//				//join tables to yield_tables2
//				pst = conn.prepareStatement("CREATE TABLE yield_tables2 AS SELECT * FROM yield_tables JOIN management_actions USING (action_id);");
//				pst.executeUpdate();
//				
//				//delete yield_tables
//				pst = conn.prepareStatement("DROP TABLE yield_tables;");
//				pst.executeUpdate();
//				
//				//rename yield_tables2 to yield_tables
//				pst = conn.prepareStatement("ALTER TABLE yield_tables2 RENAME TO yield_tables;");
//				pst.executeUpdate();
//			
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//			}
	
			
		
//			// add timing choices 0 for the current names of EAe and EAr tables
//			try {						
//				//get the strata name and put into a list		
//				List<String> strata_list = new ArrayList<String>();	
//				pst = conn.prepareStatement("SELECT DISTINCT strata FROM yield_tables;");
//				ResultSet rs = pst.executeQuery();			
//				while (rs.next()) {
//					strata_list.add(rs.getString(1));		//column 1
//				}
//				rs.close();
//				
//				// Update EAe and Ear strata with new name
//				for (String strata: strata_list) {														
//					if (strata.contains("_EA")) {	
//						pst = conn.prepareStatement("UPDATE yield_tables SET strata = '" + strata + "_0" + "' WHERE strata = '" + strata + "';");
//						pst.executeUpdate();				
//					}					
//				}		
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//			}			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//Commit execution-------------------------------------------------
			pst.close();
			conn.commit(); // commit all prepared execution, this is important
			conn.close();			
			
			
			//Show the yield_tables
			scrollPane_Right.setViewportView(database_table);
			doQuery("SELECT * FROM yield_tables");
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}		
	}		

	
	// Tool bar with background image--------------------------------------------------------------------------------------------------------------------------------
	class ToolBarWithBgImage extends JToolBar {	  
		private ImageIcon bgImage;

		ToolBarWithBgImage(String name, int orientation, ImageIcon ii) {
			super(name, orientation);
			this.bgImage = ii;
			setOpaque(true);
		}
	  
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (bgImage != null) {
				Dimension size = this.getSize();
	            g.drawImage(bgImage.getImage(), size.width - bgImage.getIconWidth(), (size.height - bgImage.getIconHeight())/2, bgImage.getIconWidth(), bgImage.getIconHeight(), this);
			}
		}
	}	
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void showNothing () {
		currentLevel = 0;
		if (display_text_field != null) display_text_field.setText(null); // Show nothing on the TextField
		if (scrollPane_Right != null) scrollPane_Right.setViewportView(null);
	}
	

	//--------------------------------------------------------------------------------------------------------------------------------
	// Just a class that contains different examples, not being used
	public void Examples () {

		
//		JOptionPane.showMessageDialog(Spectrum_Main.mainFrameReturn(), FilesHandle.get_workingLocation());	
		
		
		
		
		
		
//	// To help get the path to databases, use it later
//	@SuppressWarnings("unused")
//	private String GetExecutionPath(){
//	    String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//	    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
//	    absolutePath = absolutePath.replaceAll("%20"," "); // Surely need to do this here
//	    return absolutePath;
//	};
//	//------------------------------------------------------------------------------------------------------------------





//	// bind a keyboard key
//	Action doNewName = new AbstractAction() {
//		public void actionPerformed(ActionEvent e) {
//	
//		}
//	};
//	DatabaseTree.getInputMap().put(KeyStroke.getKeyStroke("F2"), "doNewName");
//	DatabaseTree.getActionMap().put("doNewName", doNewName);
//	//------------------------------------------------------------------------------------------------------------------





//	//another example
//	MouseListener ml = new MouseAdapter() {
//	@Override
//	public void mousePressed(MouseEvent e) {
//	    int row = DatabaseTree.getRowForLocation(e.getX(), e.getY());
//	    TreePath path = DatabaseTree.getPathForLocation(e.getX(), e.getY());
//	    if (row != -1) {
//	        if (e.getClickCount() == 1) {
//	        	DatabaseTree.setEditable(true);
//	        	DatabaseTree.startEditingAtPath(path);
//	        }
//	    }
//	}
//	};
//	DatabaseTree.addMouseListener(ml);
//	DatabaseTree.getInputMap().put(
//	KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "startEditing");
//		//------------------------------------------------------------------------------------------------------------------

		
		
		
		
//		// These are different ways to write listeners
//		MouseAdapter queryTextField_mouseClick = new MouseAdapter()	//When user click on queryTextField
//		{
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				DatabaseTree.setEnabled(false);		//disable the tree
//				if (queryTextField.getText().equals("Type your query here")) {	//clear the text
//					queryTextField.setText(null);					
//				}
//			}
//		};
//		queryTextField.addMouseListener(queryTextField_mouseClick); // Add listener to queryTextField

		
		
		
//		Action action = new AbstractAction()	//When user press Enter on Keyboard
//		{
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				currentSQLstatement = queryTextField.getText();	
//				doQuery(currentSQLstatement);
//				queryTextField.setText(null);
//			}
//		};
//		queryTextField.addActionListener(action);	
//		//------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		// another different way
		
//		combo1.addActionListener(new applyChanges_Listener());	
		
		
//		class applyChanges_Listener implements ActionListener {
//			public void actionPerformed(ActionEvent e) {
//				// Apply any change in the GUI to the TEXT area
//
//				
//				
//				for (int i = 0; i < listOfEditRuns.length; i++) {
//					if (radioButton_Left[i].isSelected()) {
//						for (int j = 0; j < 3; j++) {
//							if (radioButton_Right[j].isSelected()) {			
//								if (j == 0) {
////									textArea1.setText(st1);
////									textArea1.append(st1 + "\n\r");
////								} else if (j == 1) {
////									GUI_Text_splitPanel.setLeftComponent(panelInput3[i]);
////									GUI_Text_splitPanel.setRightComponent(panelInput4[i]);
////								} else if (j == 2) {
////									GUI_Text_splitPanel.setLeftComponent(panelInput5[i]);
////									GUI_Text_splitPanel.setRightComponent(panelInput6[i]);
//								}				
//							}
//						}
//					}
//				}
//				
//				
//				
//			}
//		}
		

		//------------------------------------------------------------------------------------------------------------------
		//another way to write JTable to text file
//		try (PrintWriter fileOut = new PrintWriter(managementOptionsFile)) {
//		for (int column = 0; column < table.getColumnCount(); column++) {
//			fileOut.print(table.getColumnName(column) + ",");
//		}
//		fileOut.println();
//		
//		for(int row = 0; row < table.getRowCount(); row++) {
//	        for(int column = 0; column < table.getColumnCount(); column++) {
//	        	fileOut.print(table.getValueAt(row, column) + ",");
//	        }
//			fileOut.println();
//		}
//	} catch (IOException e) {
//	}		
		
		

		
//		//Make everything transparent for a table
//		renderer.setOpaque(false);
//		table.setOpaque(false);
//		scrollPane_Right.setOpaque(false);
		
		
	}

} // Final End		
