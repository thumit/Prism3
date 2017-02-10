package spectrumDatabase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import spectrumConvenienceClasses.FilesHandle;
import spectrumConvenienceClasses.IconHandle;

@SuppressWarnings("serial")
public class Panel_DatabaseManagement extends JLayeredPane {
	private File databasesFolder = FilesHandle.get_DatabasesFolder();
	private String seperator = "/";
	private JTree DatabaseTree;
	private DefaultMutableTreeNode root, processingNode;
	private JTextField dataDisplayTextField, queryTextField;
	private JTable DatabaseTable;
	private Connection conn;
	private String currenTableName, currentDatabase, currentSQLstatement;
	private int currentLevel;
	private TreePath[] selectionPaths;
	private TreePath editingPath;
	private File oldfile;
	private static String fileDelimited;
	private Boolean Database_Name_Edit_HasChanged = false;
	private Boolean renamingDatabase = false;
	private Boolean renamingTable = false;
	private Boolean errorCAUGHT = false;

	private ToolBarWithBgImage databaseToolBar;
	private JScrollPane scrollPane_Left, scrollPane_Right;
	private JButton btnNewDatabase, btnDelete, btnEdit, btnRefresh;

	public Panel_DatabaseManagement() {
		super.setLayout(new BorderLayout(0, 0));

		
		//Split panel at Center of the Internal Frame----------------------------------
		JSplitPane splitPane = new JSplitPane();
		//splitPane.setResizeWeight(0.15);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(200);
//		splitPane.setDividerSize(5);
		//splitPane.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		//Left split panel-----------------------------------
		scrollPane_Left = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Databases");
		DatabaseTree = new JTree(root);	//Add the root of DatabaseTree
//		DatabaseTree.setEditable(true);
		DatabaseTree.setInvokesStopCellEditing(true);	// Even when we leave the node by clicking mouse, the name editing will be kept 
		
		DatabaseTree.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
			public void mousePressed(MouseEvent e) {
				DatabaseTree.setEnabled(true);
				queryTextField.setText("Type your queries here");
				doMousePressed(e);
			}
		});
		
//		DatabaseTree.addTreeSelectionListener(new TreeSelectionListener() {
//			public void valueChanged(TreeSelectionEvent evt) {
//				doWhenSelectionChange(evt);
//			}
//		});	
	
		DatabaseTree.addFocusListener(new FocusListener() {	//change name whenever node stopped editing
	         public void focusGained(FocusEvent e) {  
	        	if ((Database_Name_Edit_HasChanged == true || renamingDatabase == true) && !DatabaseTree.isEditing())		{ applyDatabase_Namechange(); }
	        	if ( renamingTable == true && !DatabaseTree.isEditing())											{ applyTable_Namechange(); }
	         }
	         public void focusLost(FocusEvent e) {               
	         }
		});	// end addFocusListener
		refreshDatabaseTree(); // Refresh the tree
		scrollPane_Left.setViewportView(DatabaseTree);
					
		
		//Right split panel-----------------------------------
		scrollPane_Right = new JScrollPane();
		splitPane.setRightComponent(scrollPane_Right);
		DatabaseTable = new JTable() {
			@Override			//These override is to make the width of the cell fit all contents of the cell
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// For the cells in table								
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
				
				// For the column names
				TableCellRenderer renderer2 = DatabaseTable.getTableHeader().getDefaultRenderer();	
				Component component2 = renderer2.getTableCellRendererComponent(DatabaseTable,
			            tableColumn.getHeaderValue(), false, false, -1, column);
				maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
				
				tableColumn.setPreferredWidth(maxWidth);
				return component;
			}
		};
		DatabaseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // this solve problems when resizing --> column width of the table becomes too narrow 
//		DatabaseTable.setColumnSelectionAllowed(true);
//		DatabaseTable.setRowSelectionAllowed(false);
		DatabaseTable.setDefaultEditor(Object.class, null);		//make the data un-editable
		scrollPane_Right.setViewportView(DatabaseTable);
		
		// TextField at South----------------------------------------------
		dataDisplayTextField = new JTextField("", 0);
		dataDisplayTextField.setEditable(false);
		
		// databaseToolBar & queryTextField at North----------------------------------------------------
		databaseToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		databaseToolBar.setFloatable(false);	//to make a tool bar immovable
		databaseToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor		
		
		
		btnNewDatabase = new JButton();
		btnNewDatabase.setToolTipText("New Database");
		btnNewDatabase.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_new.png"));
		btnNewDatabase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				refreshDatabaseTree();
				processingNode = root;
				currentLevel = 1;
				new_Database_or_Table();
			}
		});
		databaseToolBar.add(btnNewDatabase);
		
		btnDelete = new JButton();
		btnDelete.setToolTipText("Delete");
		btnDelete.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_delete.png"));
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				delete_Databases_or_Tables();
			}
		});
		databaseToolBar.add(btnDelete);
		
		
		btnRefresh = new JButton();
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_refresh.png"));
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				refreshDatabaseTree();
			}
		});
		databaseToolBar.add(btnRefresh);
		
		
		queryTextField = new JTextField("Type your queries here", 1000);					
		queryTextField.addMouseListener(new MouseAdapter(){			//When user click on queryTextField
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseTree.setEnabled(false);		//disable the tree
				if (queryTextField.getText().equals("Type your queries here")) {	//clear the text
					queryTextField.setText(null);					
				}
			}
	     });//end addMouseListener
		
		queryTextField.addActionListener(new AbstractAction(){		//When user press Enter on Keyboard
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSQLstatement = queryTextField.getText();	
				doQuery(currentSQLstatement);
				scrollPane_Right.setViewportView(DatabaseTable);
			}
	     });//end addActionListener
		databaseToolBar.add(queryTextField);
			
	
		// Add all components to JInternalFrame-----------------------------		
		super.add(databaseToolBar, BorderLayout.NORTH);
		super.add(dataDisplayTextField, BorderLayout.SOUTH);
		super.add(splitPane, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_DatabaseManagement()	
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMousePressed(MouseEvent e) {     	
	
		TreePath path = DatabaseTree.getPathForLocation(e.getX(), e.getY());
		if (path == null) {
			DatabaseTree.clearSelection();		//clear selection whenever mouse click is performed not on Jtree nodes	
			showNothing();	// show nothing (a table with 0 row and 0 column) if no node selected
			return;
		}
		if (path != null) dataDisplayTextField.setText(path.toString()); 	// display Full path
//		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
//		dataDisplayTextField.setText(selectedNode.toString());		//display Only last node name

		
		selectionPaths = DatabaseTree.getSelectionPaths();
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
			DatabaseTree.setSelectionPath(path);
		}
	
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (e.getClickCount() == 1) {
				// Show node information of the last selected node		
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				currentLevel = path.getPathCount();
				
				// ------------Only show DatabaseTable if the node level is 3
				if (currentLevel == 3) {	//selected node is a table
					scrollPane_Right.setViewportView(DatabaseTable);
					 // Get the URL of the current selected node			
					currenTableName = selectedNode.getUserObject().toString();
					// Get the parent node which is the database that contains the selected table
					currentDatabase = selectedNode.getParent().toString();          
					currentSQLstatement = "SELECT * FROM " + "[" + currenTableName + "];";		//this query show the whole table information
					doQuery(currentSQLstatement);
							
				} else if (currentLevel != 3) {		
					scrollPane_Right.setViewportView(null);
					if (currentLevel == 2) currentDatabase = selectedNode.getUserObject().toString();	// the selected node is a database
					if (currentLevel == 1) currentDatabase = selectedNode.getUserObject().toString();	// the selected node is Root
				}
			} else if (e.getClickCount() == 2) {
				// Do something here
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getClickCount() == 1) {				
				
				//Some set up when there is a right click---------------------------------------------------------------
				Boolean rootSelected =false;			
				selectionPaths = DatabaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
				int NodeCount=0;		//Count the number of nodes in selectionPaths
				for (TreePath selectionPath : selectionPaths) {		//Loop through all selected nodes
					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					if (processingNode.isRoot()) rootSelected = true;
					NodeCount++;		
					}
				
				//Deselect the root if this is a multiple Nodes selection and root is selected
				TreePath rootpath = new TreePath(root);
				if (NodeCount>1 && rootSelected==true) {
					DatabaseTree.getSelectionModel().removeSelectionPath(rootpath);
					rootSelected =false;
					NodeCount = NodeCount -1;
				}
				
				//Reselect all nodes left
				selectionPaths = DatabaseTree.getSelectionPaths();
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes	
					currentLevel = selectionPath.getPathCount();
				}
				//End of set up---------------------------------------------------------------
				
			
											
				//right clicked MenuItems only appear on nodes level 1, 2, or 3 (single or multiple nodes selection) 
				if /*(clickedNode.isRoot()) {*/ (currentLevel == 1 || currentLevel == 2 || currentLevel == 3) {		
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
						importDBMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_add.png"));
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
								
					
					
					// Only nodes level 2 (database) and 3 (table) can have "copy"--------------------------
					if (currentLevel == 2 || currentLevel == 3 || rootSelected ==false) {
						final JMenuItem copyMenuItem = new JMenuItem("Copy");
						copyMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_copy.png"));
						copyMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								copy_Databases_or_Tables();
							}
						});
						popup.add(copyMenuItem);
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
				
									
					//Show the JmenuItems on selected node when it is right clicked
					popup.show(DatabaseTree, e.getX(), e.getY());
				}
			}
		}
	}
		
	// --------------------------------------------------------------------------------------------------------------------------------
//	public void doWhenSelectionChange (TreeSelectionEvent evt) {
//	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doQuery(String query) {		//Note a statement not starting with SELECT is not a Query
		if (query.substring(0, 6).toUpperCase().equals("SELECT")) {		//If this is a query
			try {
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// get columns info
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// for changing column and row model
				DefaultTableModel tm = (DefaultTableModel) DatabaseTable.getModel();

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

				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
				JOptionPane.showMessageDialog(this, ex, ex.getMessage(), WIDTH, null);
				errorCAUGHT=true;
			}
		} else { // a statement that is not a query: INSERT, DELETE,...
			try {
				Class.forName("org.sqlite.JDBC").newInstance();
				conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
				JOptionPane.showMessageDialog(this, ex, ex.getMessage(), WIDTH, null);
				errorCAUGHT=true;
			}
		}
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void refreshDatabaseTree() {
		// Remove all children nodes from the root of DatabaseTree, and reload the tree
		DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
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

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				DefaultMutableTreeNode level2node = new DefaultMutableTreeNode(files);
				root.add(level2node);
				
				// Read each database file and add all of its table as child nodes--------------------------------------				  
				/*Connection*/ conn = null;
				try {
					Class.forName("org.sqlite.JDBC").newInstance();
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
		DatabaseTree.expandPath(new TreePath(root.getPath()));	//Expand the root
		if (scrollPane_Right != null) {
			scrollPane_Right.setViewportView(null);
		}
	} // end of update_DatabaseTree()
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void new_Database_or_Table() {
		String nodeName = null;
		if (currentLevel == 1) nodeName = "new_database";
		if (currentLevel == 2) nodeName = "new_table";
		
		if (nodeName.equals("new_database")) {		//New Database
			DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();	
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
			model.insertNodeInto(newNode, processingNode, processingNode.getChildCount());
			TreeNode[] nodes = model.getPathToRoot(newNode);
			TreePath path = new TreePath(nodes);
			DatabaseTree.scrollPathToVisible(path);
			DatabaseTree.setEditable(true);
			DatabaseTree.setSelectionPath(path);
			DatabaseTree.startEditingAtPath(path);
			editingPath = path;	
			try {
			dataDisplayTextField.setText("Type your new database name");
			Database_Name_Edit_HasChanged = true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}	
		} else 
			
												
		if (nodeName.equals("new_table")) {		//New Table									
			try {
			dataDisplayTextField.setText("This function is currently not supported");
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}	
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void rename_Database_or_Table() {
		
		if (processingNode != null && currentLevel == 2) {		//rename Database
			DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();	
			TreeNode[] nodes = model.getPathToRoot(processingNode);
			TreePath path = new TreePath(nodes);
			DatabaseTree.scrollPathToVisible(path);
			DatabaseTree.setEditable(true);
			DatabaseTree.setSelectionPath(path);
			DatabaseTree.startEditingAtPath(path);
			editingPath = path;	
			try {
				// Get and save the old database name
				DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
		    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name
		    	if(nameWOext.contains(".")) nameWOext= nameWOext.substring(0, nameWOext.lastIndexOf('.'));		//Remove extension if the name has it
		    	String editingName = databasesFolder + seperator + nameWOext + ".db";	//Add .db to the name
		    	oldfile = new File(editingName);
		    	// Then perform:	applyDatabase_Namechange
				
		    	dataDisplayTextField.setText("Type your new database name");
				renamingDatabase = true;
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		} else 
			
		
		if (processingNode != null && currentLevel == 3) {		//rename Table									
			DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
			TreeNode[] nodes = model.getPathToRoot(processingNode);
			TreePath path = new TreePath(nodes);
			DatabaseTree.scrollPathToVisible(path);
			DatabaseTree.setEditable(true);
			DatabaseTree.setSelectionPath(path);
			DatabaseTree.startEditingAtPath(path);
			editingPath = path;
			try {
				dataDisplayTextField.setText("Type your new Table name");
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
    	
    	
    	//For "rename database"
		if (renamingDatabase == true) {
			if (renamingDatabase == true) {
				oldfile.renameTo(newfile);
				temptext = oldfile.getName() + " has been renamed to " + newfile.getName();	
			} 
			// For "new database"
		} else {
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
		Enumeration<DefaultMutableTreeNode> e1 = root.depthFirstEnumeration();
		while (e1.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = e1.nextElement();
			if (node.toString().equalsIgnoreCase(DatabaseName) && root.isNodeChild(node)) {		//Name match, and node is child of root
				DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
				TreeNode[] nodes = model.getPathToRoot(node);
				TreePath path = new TreePath(nodes);
				DatabaseTree.scrollPathToVisible(path);
				DatabaseTree.setSelectionPath(path);
				editingPath = path;
				selectionPaths = DatabaseTree.getSelectionPaths();
			}
		}
		
		dataDisplayTextField.setText(temptext);
		DatabaseTree.setEditable(false);			// Disable editing
		Database_Name_Edit_HasChanged = false;
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
		} catch (Exception e) {
			System.err.println("Panel DatabaseManagement - applyTable_Namechange error - " + e.getClass().getName() + ": " + e.getMessage());
			temptext = "Cannot rename the table";
		}
		dataDisplayTextField.setText(temptext);
		// Disable editing
		DatabaseTree.setEditable(false);
		renamingTable = false;			//For "rename Table" option only
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void copy_Databases_or_Tables() {
		//Some set up ---------------------------------------------------------------
		int node_Level = 0;
		for (TreePath selectionPath : selectionPaths) { // Loop through all selected nodes
			processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
			node_Level = selectionPath.getPathCount();
				if (node_Level==2 && processingNode.getChildCount() >= 0) {		//If node is a database and has childs then deselect all of its childs				
				for (Enumeration e = processingNode.children(); e.hasMoreElements();) {
					TreeNode child = (TreeNode) e.nextElement();
					DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
					TreeNode[] nodes = model.getPathToRoot(child);
					TreePath path = new TreePath(nodes);
					DatabaseTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
				}
				DatabaseTree.collapsePath(new TreePath(processingNode.getPath()));	//Collapse the selected database	
			}
		}
		selectionPaths = DatabaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
		// End of set up---------------------------------------------------------------
				
	
		//Show a popup for the destination of copied tables---------------------------------------------------				
		//Loop through all databases in the working folder
		File[] listOfFiles = databasesFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".db");
			}
		});
		
		//Get databases names and make an array to store names
		String[] listNames;
		listNames = new String [listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {			
				listNames[i] = listOfFiles[i].getName();			
		}
				
		String initialSelection = listNames[0];			//First database shows up
		String selection = (String) JOptionPane.showInputDialog(this,
				"Copy highlighted tables & all tables in highlighted databases, and paste into", "Select a database as destination to paste",
				JOptionPane.QUESTION_MESSAGE, null, listNames, initialSelection);
		
		
		// Find the database user selected
		String destinationFileName = null;
		for (int i = 0; i < listNames.length; i++) {
			if (selection==listNames[i]) {
				destinationFileName = listNames[i];
			}
		}
		
		// Find the destination node
		DefaultMutableTreeNode destinationNode = new DefaultMutableTreeNode(destinationFileName);
		Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) {		//Search for the name that match
			DefaultMutableTreeNode node = e.nextElement();
			if (node.toString().equalsIgnoreCase(destinationFileName) && root.isNodeChild(node)) {		//Name match, and node is child of root
				destinationNode = node;
			}
		}
		
		//---------------------------------------------------	DO ONLY WHEN USER PRESS 'OK' 			
		//Loop through all selected tables and databases the users want to copy		
		if (selection != null) {
			//Count the number of tables (including tables inside selected databases)
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
					Enumeration<DefaultMutableTreeNode> e1 = processingNode.depthFirstEnumeration();
					while (e1.hasMoreElements()) {		//Search for the name that match
						DefaultMutableTreeNode childnode = e1.nextElement();
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
					    	currentRow = currentRow +"'" + rs.getString(jj).replace("'", "''") +"'" +", ";			//Escape the ' (i.e. xEAe') by replace
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
					DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(copiedTables_names[i]);
					model.insertNodeInto(newNode, destinationNode, destinationNode.getChildCount());
					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					DatabaseTree.scrollPathToVisible(path);
					// DatabaseTree.setEnabled(false);
					// DatabaseTree.setEditable(true);
					if (count ==0) DatabaseTree.setSelectionPath(path);			//this help deselect all original nodes
					// DatabaseTree.startEditingAtPath(path);
					editingPath = path;			
					DatabaseTree.getSelectionModel().addSelectionPath(path); // add childs into selection but not show table
					count ++;
				}
				errorCAUGHT = false;	
				
				
			}	//End of For loop
		}	//End of if (selection != null)
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
				Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) { // Search for the name that match
					DefaultMutableTreeNode node = e.nextElement();
					if (node.toString().equalsIgnoreCase(DatabaseName) && root.isNodeChild(node)) {		//Name match, and node is child of root
						DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
						TreeNode[] nodes = model.getPathToRoot(node);
						TreePath path = new TreePath(nodes);
						DatabaseTree.scrollPathToVisible(path);
						DatabaseTree.setSelectionPath(path);
						editingPath = path;
					}
				}
				dataDisplayTextField.setText(temptext);
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
				Class.forName("org.sqlite.JDBC").newInstance();
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
		
						// Find the delimited of this
						// currentFile
						for (int j = 0; j < extentionList.size(); j++) {
							if (extentionList.get(j).equals(extension.toUpperCase())) {
								fileDelimited = delimitedList.get(j); // This is the returned delimited
							}
						}
		
						
						if (fileDelimited != null) {
							// Get info from the file
							SQLite.create_importTable_Stm(currentfile);		//Read file into arrays
							String[] statement = new String [SQLite.get_importTable_TotalLines()];		//this arrays hold all the statements
							statement = SQLite.get_importTable_Stm();	

							// prepared execution
							for (int line = 0; line < SQLite.get_importTable_TotalLines(); line++) {
								pst = conn.prepareStatement(statement[line]);
								pst.executeUpdate();
							}
						}
					} // end of If
				} // end of For loop
			
		
			
				//Commit execution-------------------------------------------------
				pst.close();
				conn.commit(); // commit all prepared execution, this is important
				conn.close();

			} catch (InstantiationException e) {
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				errorCAUGHT = true;
			} catch (IllegalAccessException e) {
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				errorCAUGHT = true;
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				errorCAUGHT = true;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
				errorCAUGHT = true;
			}		
			//-------------------------------------------------
			
		
			
			//Show new impoted tables-----------------------------------------------------------------------
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
						DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
						DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(NodeName);
						model.insertNodeInto(newNode, processingNode, processingNode.getChildCount());
						TreeNode[] nodes = model.getPathToRoot(newNode);
						TreePath path = new TreePath(nodes);
						DatabaseTree.scrollPathToVisible(path);
						// DatabaseTree.setEnabled(false);
						// DatabaseTree.setEditable(true);
						if (count == 0)
							DatabaseTree.setSelectionPath(path); // this help deselect all original nodes
						// DatabaseTree.startEditingAtPath(path);
						editingPath = path;
						DatabaseTree.getSelectionModel().addSelectionPath(path); // add childs into selection but not show table
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
				DatabaseTree.getSelectionModel().removeSelectionPath(selectionPath);	
			} else if ((node_Level == 3) && !selected_databases_list.contains(processingNode.getParent().toString())) { // If node is a table then add is parent (database) to the list if not added yet
				selected_databases_list.add(processingNode.getParent().toString());
			}
		}
		int total_databases = selected_databases_list.size();
		selectionPaths = DatabaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
		// End of set up---------------------------------------------------------------		
		
		
		// Combines tables only if total_databases = 1 and there are at least 2 selected tables
		if (total_databases == 1 && selectionPaths.length >= 2) {
			currentDatabase = selected_databases_list.get(0);
			DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();
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
				Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) { // Search for the name that match
					DefaultMutableTreeNode node = e.nextElement();
					if (node.toString().equalsIgnoreCase(currentDatabase) && root.isNodeChild(node)) {		//Name match, and node is child of root
						Enumeration<DefaultMutableTreeNode> e2 = node.depthFirstEnumeration();
						
						while (e2.hasMoreElements()) { // Search for the name that match
							DefaultMutableTreeNode newNode = e2.nextElement();
							if (newNode.toString().equalsIgnoreCase("combine_table") && node.isNodeChild(newNode)) {		//Name match, and node is child of the database
								/*DefaultTreeModel */model = (DefaultTreeModel) DatabaseTree.getModel();
								TreeNode[] nodes = model.getPathToRoot(newNode);
								TreePath path = new TreePath(nodes);
								DatabaseTree.scrollPathToVisible(path);
								DatabaseTree.setSelectionPath(path);
								editingPath = path;
								
								processingNode = newNode;
								currenTableName = "combine_table";
								doQuery("SELECT * FROM combine_table");
								scrollPane_Right.setViewportView(DatabaseTable);
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
						DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();	
						TreeNode[] nodes = model.getPathToRoot(child);
						TreePath path = new TreePath(nodes);
						DatabaseTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
					}
					DatabaseTree.collapsePath(new TreePath(processingNode.getPath()));	//Collapse the selected database				
				}	
			}
			selectionPaths = DatabaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
		}
		//End of set up---------------------------------------------------------------
		
		
		
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
			conn.setAutoCommit(false);
			PreparedStatement pst = null;
			
			if (selectionPaths != null) {		//at least 1 database or table has to be selected 
				//Ask to delete 
				int response = JOptionPane.showConfirmDialog(this, "Delete highlighted tables & all tables in highlighted databases ?", "Confirm Delete",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response == JOptionPane.NO_OPTION) {
					
				} else if (response == JOptionPane.YES_OPTION) {
					DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();	
						
				    for (TreePath selectionPath : selectionPaths) {		//Loop through all and delete all level 3 nodes
						currentLevel = selectionPath.getPathCount();
						processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
						DatabaseTree.setSelectionPath(null);
							
						if (currentLevel == 3) {		//DELETE Tables						
							//Find all notes that share the same parent (same database) and currently in the selectionPath, group them into 1 delete transection						
								
								
							String nextDatabase = processingNode.getParent().toString();
							currenTableName = processingNode.getUserObject().toString();
							model.removeNodeFromParent(processingNode);
							
							if (!nextDatabase.equals(currentDatabase)) {
								pst = conn.prepareStatement("DROP TABLE IF EXISTS " + "[" + currenTableName + "]");
								pst.executeUpdate();
								
								conn.commit(); // commit all prepared execution, this is important
								conn.close();
								
								currentDatabase = nextDatabase;
								conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);		
								conn.setAutoCommit(false);						
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
						
							
					for (TreePath selectionPath : selectionPaths) { // Loop through all again and delete all level 2 nodes (databases)
						currentLevel = selectionPath.getPathCount();
						DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
						DatabaseTree.setSelectionPath(null);
						if (currentLevel == 2) { // DELETE Databases
							currentDatabase = processingNode.getUserObject().toString();
							model.removeNodeFromParent(processingNode);
							File file = new File(databasesFolder + seperator + currentDatabase);
							file.delete();
							showNothing();
						}
					}

				} else if (response == JOptionPane.CLOSED_OPTION) {
				}
			}

		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
			errorCAUGHT = true;
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
			errorCAUGHT = true;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
			errorCAUGHT = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
			errorCAUGHT = true;
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
//		dataDisplayTextField.setText(null);	//Show nothing on the TextField
		scrollPane_Right.setViewportView(null);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	public static String getDelimited() {
		return fileDelimited;
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
//				if (queryTextField.getText().equals("Type your queries here")) {	//clear the text
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
		
		
		
		
		
	}

} // Final End		