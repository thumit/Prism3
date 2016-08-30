package spectrumDatabaseUtil;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import javax.swing.Action;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class Panel_DatabaseManagement extends JLayeredPane {
	private String workingLocation;
	private File databasesFolder;
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
	private File newfile, oldfile;
	private static String fileDelimited;
	private Boolean Database_Name_Edit_HasChanged = false;
	private Boolean renaming = false;
	private Boolean renamingTable = false;
	private Boolean errorCAUGHT = false;


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
		JScrollPane scrollPane_Left = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Databases");
		DatabaseTree = new JTree(root);	//Add the root of DatabaseTree
//		DatabaseTree.setEditable(true);
		DatabaseTree.setInvokesStopCellEditing(true);	// Even when we leave the node by clicking mouse, the name editing will be kept 
		
		DatabaseTree.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
			public void mouseClicked(MouseEvent e) {
				DatabaseTree.setEnabled(true);
				queryTextField.setText("Type your queries here");
				doMouseClicked(e);
			}
		});
		
		DatabaseTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {
				doWhenSelectionChange(evt);
			}
		});	
	
		DatabaseTree.addFocusListener(new FocusListener(){		//change name whenever node stopped editing
	         public void focusGained(FocusEvent e) {  
				JTree tree = (JTree)e.getSource();
	        	if ((Database_Name_Edit_HasChanged == true || renaming == true) && !tree.isEditing())		{ applyDatabase_Namechange(); }
	        	if ( renamingTable == true && !tree.isEditing())											{ applyTable_Namechange(); }
	         }
	         public void focusLost(FocusEvent e) {               
	         }
	     });//end addFocusListener		
		refreshDatabaseTree();	//Refresh the tree
		scrollPane_Left.setViewportView(DatabaseTree);
					
		
		//Right split panel-----------------------------------
		JScrollPane scrollPane_Right = new JScrollPane();
		splitPane.setRightComponent(scrollPane_Right);
		DatabaseTable = new JTable();
		DatabaseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // this solve problems when resizing --> column width of the table becomes too narrow 
//		DatabaseTable.setColumnSelectionAllowed(true);
//		DatabaseTable.setRowSelectionAllowed(false);
		scrollPane_Right.setViewportView(DatabaseTable);
		
		// TextField at South----------------------------------------------
		dataDisplayTextField = new JTextField("", 0);
		dataDisplayTextField.setEditable(false);
		
		// DatabaseTextField at North--------------------------------------
		queryTextField = new JTextField("Type your queries here", 0);				
	
		queryTextField.addFocusListener(new FocusListener(){		//reset text when textfield is focused
	         public void focusGained(FocusEvent e) {  
	        	 queryTextField.setText(null);	
	         }
	         public void focusLost(FocusEvent e) {
	             
	         }
	     });//end addFocusListener
		
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
				queryTextField.setText(null);
			}
	     });//end addActionListener
		
			
	
		// Add all components to JInternalFrame-----------------------------		
		super.add(queryTextField, BorderLayout.NORTH);
		super.add(dataDisplayTextField, BorderLayout.SOUTH);
		super.add(splitPane, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_DatabaseManagement()	
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMouseClicked(MouseEvent e) {     	
	
		TreePath path = DatabaseTree.getPathForLocation(e.getX(), e.getY());
		if (path == null) {
			DatabaseTree.clearSelection();		//clear selection whenever mouse click is performed not on Jtree nodes	
			showNothing();	// show nothing (a table with 0 row and 0 column) if no node selected
			return;
		}
		if (path != null) dataDisplayTextField.setText(path.toString());

		
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
				// Do something here
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
				//End of set up---------------------------------------------------------------
				
			
											
				//right clicked MenuItems only appear on nodes level 1, 2, or 3 (single or multiple nodes selection) 
				if /*(clickedNode.isRoot()) {*/ (currentLevel == 1 || currentLevel == 2 || currentLevel == 3) {		
					// A popup that holds all JmenuItems
					JPopupMenu popup = new JPopupMenu();
					
					
					// All nodes can be refreshed ------------------------------------------------------------
					final JMenuItem refreshMenuItem = new JMenuItem("Refresh");
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
						final String Menuname;	
						if (currentLevel == 1) Menuname = "New database"; else Menuname = "New table";				
						final JMenuItem newMenuItem = new JMenuItem(Menuname);
						newMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {										
								new_Database_or_Table ();
							}
						});
						popup.add(newMenuItem);
					}
					
	
					
					// Only nodes level 1 (root) and 2 (databases) can have "Import"----------------------
					// and this menuItem only shows up when 1 node is selected	
					if (currentLevel == 1 && NodeCount==1) {
						final JMenuItem importDBMenuItem = new JMenuItem("Import databases");
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
						importTableMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
							importTables();								
							} //end of actionPerformed
						});
						popup.add(importTableMenuItem);
					}
					
					
					
					// Only nodes level 2 (database) and 3 (table) can be renamed--------------------------
					// and this menuItem only shows up when 1 node is selected	
					if ((currentLevel == 2 || currentLevel == 3) && NodeCount==1) {
						final JMenuItem renameMenuItem = new JMenuItem("Rename");
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
	public void doWhenSelectionChange (TreeSelectionEvent evt) {
		
		//----------------------------------------------------
		//Display only the selected node name to dataDisplayTextField
//		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
//		dataDisplayTextField.setText(selectedNode.toString());
		
		//Display full paths to the selected node to dataDisplayTextField
		TreePath path = evt.getPath();
		if (path == null) dataDisplayTextField.setText(null);
		if (path != null) dataDisplayTextField.setText(path.toString());
		
		
		// ------------Calculate the level of the current selected node
		currentLevel = path.getPathCount();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
		
		// ------------Only show DatabaseTable if the node level is 3
		if (currentLevel == 3) {	//selected node is a table
			 // Get the URL of the current selected node			
			currenTableName = selectedNode.getUserObject().toString();
			// Get the parent node which is the database that contains the selected table
			currentDatabase = selectedNode.getParent().toString();          
			currentSQLstatement = "SELECT * FROM " + "[" + currenTableName + "];";		//this query show the whole table information
			doQuery(currentSQLstatement);
					
		} else if (currentLevel != 3) {		
			// show nothing (a table with 0 row and 0 column) if the current selected node is not a table
			showNothing();
			if (currentLevel == 2) currentDatabase = selectedNode.getUserObject().toString();	// the selected node is a database
			if (currentLevel == 1) currentDatabase = selectedNode.getUserObject().toString();	// the selected node is Root
		}
	}
	
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
				JOptionPane.showMessageDialog(this, ex, ex.getMessage(), WIDTH, null);
				errorCAUGHT=true;
			}
		}
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void refreshDatabaseTree() {
		// Remove all children nodes from the root of DatabaseTree, and reload the tree
		DefaultTreeModel model = (DefaultTreeModel)DatabaseTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren();
		model.reload(root);


		// Get working location of the IDE project, or runnable jar file
		final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());		
		if(jarFile.isFile()) {  // Run with JAR file
			databasesFolder = new File (":Databases");	//Note: we have to define databaseFolder as String to make it work
			seperator = ":";		
			
//			//runnable jar path
//			String url = "jdbc:sqlite::resource:mydb.db";
		} else { // Run with IDE
//			databasesFolder = new File (jarFile + "/Databases");	//return: C:\SpectrumLite\JavaModel\bin\Databases
//			seperator = "/";			
			
//			//Eclipse test path
//			String url = "jdbc:sqlite:resource/mydb.db";
		}

		
		// Both runnable jar and IDE work with condition: Databases folder and runnable jar have to be in the same location
		workingLocation = jarFile.getParentFile().toString();	
		try {
			//to handle name with space (%20)
			workingLocation = URLDecoder.decode(workingLocation, "utf-8");
			workingLocation = new File(workingLocation).getPath();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		databasesFolder = new File (workingLocation + "/Databases");		//parent is the folder contain jar file
		seperator = "/";		
		if (!databasesFolder.exists()) {databasesFolder.mkdirs();}	//Create folder Databases if it does not exist		
//		JOptionPane.showMessageDialog(this, databasesFolder);
		

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
	} // end of update_DatabaseTree()
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void new_Database_or_Table() {
		final String NodeName;
		if (currentLevel == 1) NodeName = "New database"; else NodeName = "New table";
		
		if (processingNode != null && currentLevel == 1) {		//New Database
			DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();	
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(NodeName);
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
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + "empty.db");
			conn.close();
			Database_Name_Edit_HasChanged = true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}	
		} else 
			
												
		if (processingNode != null && currentLevel == 2) {		//New Table									
			try {
			dataDisplayTextField.setText("Sorry, this function is currently NOT supported");
			
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
			dataDisplayTextField.setText("Type your new database name");

			// Rename the current database to be "empty.db"
			DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
	    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name
	    	if(nameWOext.contains(".")) nameWOext= nameWOext.substring(0, nameWOext.lastIndexOf('.'));		//Remove extension if the name has it
	    	String editingName = databasesFolder + seperator + nameWOext + ".db";	//Add .db to the name
	    	oldfile = new File(editingName);
	    	newfile = new File(editingName);
	    	File file = new File(databasesFolder + seperator + "empty.db");	
	    	newfile.renameTo(file);
	    	// Then make perform:	applyDatabase_Namechange
			
			Database_Name_Edit_HasChanged = true;
			renaming = true;
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
				renamingTable = true;			//For "rename" option only
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
		
	//--------------------------------------------------------------------------------------------------------------------------------
	public void applyDatabase_Namechange (){
		//----------------------------------------------------
 		// This is the new Database name being applied after you finished the naming edit  
			//Simulate 1 enter
//			try {
//				Robot r = new Robot();
//				r.keyPress(KeyEvent.VK_ENTER);
//				r.keyRelease(KeyEvent.VK_ENTER);
//				refreshDatabaseTree();
//			} catch (AWTException e) {
//				// TODO Auto-generated catch block
//			}	
					
    	DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name
    	if(nameWOext.contains(".")) nameWOext= nameWOext.substring(0, nameWOext.lastIndexOf('.'));		//Remove extension if the name has it
    	String editingName = databasesFolder + seperator + nameWOext + ".db";	//Add .db to the name
    	newfile = new File(editingName);
 			
		File file = new File(databasesFolder + seperator + "empty.db");		//temporary empty database for New option					
		File file2 = new File(databasesFolder + seperator + "empty.db");	//For "rename" option only
		
		
		File deskFile = newfile;
		// Copy and paste
		String temptext = null;

		if (deskFile.exists() == false) {
			file.renameTo(newfile); // Then replace the file name
			temptext = "'" + deskFile.getName() + "' has been created";
		} else if (deskFile.exists() == true) {
			int response = JOptionPane.showConfirmDialog(this,
					"Do you want to overwrite the existing database " + deskFile.getName() + " ?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {
				temptext = "The existing database '" + deskFile.getName() + "' has not been overwritten";
			} else if (response == JOptionPane.YES_OPTION) {
				deskFile.delete();
				file.renameTo(newfile); // Then replace the file name
				temptext = "Existing database '" + deskFile.getName() + "' has been overwritten";
			} else if (response == JOptionPane.CLOSED_OPTION) {
				temptext = "'" + deskFile.getName() + "' has not been overwritten";
			}
		}

		if (renaming == true) file2.renameTo(oldfile);				//For "rename" option only
		if (Database_Name_Edit_HasChanged == true) file2.delete(); // delete the temporary empty.db		//For "new" option
		
		
		// Make the new Databases appear on the TREE----------->YEAHHHHHHHHHHHHHHH
		String DatabaseName = deskFile.getName();
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
			}
		}
		
		dataDisplayTextField.setText(temptext);
		DatabaseTree.setEditable(false);			// Disable editing
		Database_Name_Edit_HasChanged = false;
		renaming = false;			//For "rename" option only
	}
			
	//--------------------------------------------------------------------------------------------------------------------------------
	public void applyTable_Namechange (){	
		String temptext = null;
		DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name

		try {
			currentSQLstatement= "ALTER TABLE " + "[" + currenTableName + "]" + " RENAME TO " + "[" + nameWOext + "];";
			doQuery(currentSQLstatement);	
			temptext = "Table '" + currenTableName + "' has been renamed to " + nameWOext;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				"Selected tables and selected databases (including all tables in selected databases) will be copied and pasted into", "Please select a database as destination to paste",
				JOptionPane.QUESTION_MESSAGE, null, listNames, initialSelection);
		
		
		// Find the database user selected
		String destinationFileName = null;
		File destinationFile = null;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (selection==listNames[i]) {
				destinationFile = listOfFiles[i];
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
						name_modified = name_modified + "'" + columnNames[jj] + "' TEXT, ";
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
					String[] currentRow = new String[columnCount];			//Make currentrow[row] to be:		'value1', 'value2', .....
					int row = 0;
					while (rs.next()) {		//Loop through all rows of the copied table
						currentRow[row] = "";	//This is important to make the first member of this String not "null"
					    for (int jj = 1; jj < columnCount + 1; jj++) {
					    	currentRow[row] = currentRow[row] +"'" + rs.getString(jj) +"'" +", ";
					    }
					    currentRow[row] = currentRow[row].substring(0, currentRow[row].lastIndexOf(','));		//Remove the last "," 				      
					   
					    //Execute the insert
					    statement ="INSERT INTO " + "[" + copiedTables_names[i] + "]" + " VALUES (" + currentRow[row] + "); ";	// [] surrounds tableName		 				
						row++;
						
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Make the new Databases appear on the
			// TREE----------->YEAHHHHHHHHHHHHHHH
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
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void importTables () {

		// Open File chooser
		File[] files = FilesChooser.chosenTables(); 
		
		
		// Loop through all files to get extension, match extension with delimited
		List<String> extentionList = new ArrayList<String>();	//A list contain all extension that have its delimited identified							
		List<String> delimitedList = new ArrayList<String>();	//A list contain all delimited. same structure as the extentionList	
			
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				File currentfile = files[i];
				String extension = null;
				fileDelimited = null;
				int jj = currentfile.getName().lastIndexOf('.');
				if (jj > 0) {
					extension = currentfile.getName().substring(jj + 1);
				}
				if (extension.toUpperCase().equals("CSV")) {
					fileDelimited = ",";
					extentionList.add("CSV");
					delimitedList.add(",");
				} else if (extension.toUpperCase().equals("YLD")) {
					fileDelimited = "\\s+";
					extentionList.add("YLD");
					delimitedList.add("\\s+");
				} else if (!extentionList.contains(extension.toUpperCase())) {
					// Choose the right delimited
					// JDialog.setDefaultLookAndFeelDecorated(true);
					UIManager.put("OptionPane.cancelButtonText", "Cancel");
					UIManager.put("OptionPane.okButtonText", "Import");

					Object[] selectionValues = { "Comma", "Space", "Tab" };
					String initialSelection = "Comma";
					String selection = (String) JOptionPane.showInputDialog(this, "The delimited type for all of your '." + extension + "' files (i.e. " + currentfile.getName() + ") would be",
							"Please help SpectrumLite identify delimited type", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
						
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

	//--------------------------------------------------------------------------------------------------------------------------------
	public void delete_Databases_or_Tables() {
		
		//Some set up ---------------------------------------------------------------
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
		//End of set up---------------------------------------------------------------
		
		
		
		//Ask to delete 
		int response = JOptionPane.showConfirmDialog(this, "Selected tables and selected databases (including all tables in selected databases) will be deleted ?", "Confirm Delete",
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
						
						
						currentDatabase = processingNode.getParent().toString(); 
						currenTableName = processingNode.getUserObject().toString();
						model.removeNodeFromParent(processingNode);
						doQuery("DROP TABLE IF EXISTS " + "[" + currenTableName + "]");
						showNothing();
					}
				}	
				
				
				
				
		    	
		 
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
//				try {
//					Class.forName("org.sqlite.JDBC").newInstance();
//					int connCount =0;		//Total number of databases as parents of selected tables, also total number of connections we need for DELETE 
//					String[] conDeleteString = new String[1000];
//					Connection[] conDelete = new Connection[1000];
//					PreparedStatement[] pst = new PreparedStatement[1000];
//					conDelete[connCount] = null;
//					conDeleteString[connCount] = null;
//					pst[connCount] = null;
//					
//					
//					// prepared execution
//					
//					for (TreePath selectionPath : selectionPaths) {		//Loop through all and delete all level 3 nodes
//						currentLevel = selectionPath.getPathCount();
//						processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
//						DatabaseTree.setSelectionPath(null);
//						
//						if (currentLevel == 3) {		//DELETE Tables						
//							currentDatabase = processingNode.getParent().toString(); 
//							currenTableName = processingNode.getUserObject().toString();
//							model.removeNodeFromParent(processingNode);
//							
//							
//							if (conDeleteString[connCount] != ("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase)) {		//check if connection has changed
//								connCount++;		//connection will start from connCount=1
//								
//								conDeleteString[connCount] = ("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
//								conDelete[connCount] = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + currentDatabase);
//								conDelete[connCount].setAutoCommit(false);	
//							}
//															
//							pst[connCount] = conDelete[connCount].prepareStatement("DROP TABLE IF EXISTS " + "[" + currenTableName + "]");
//							pst[connCount].executeUpdate();					
//						} 
//					}		
//					
//					
//					if (connCount>=1) {
//						//Now loop through all the connections to commit DELETE
//						for (int i = 1; i <= connCount; i++) { //Loop starts from 1 not 0, because connection started from 1 (see above)
//							pst[connCount].close();
//							conDelete[i].commit(); //commit all prepared execution, this is important
//							conDelete[i].close();
//							showNothing();
//						}			
//					}	
//					
//					
//	
//					
//					
//					
//					
//				} catch (InstantiationException e) {
//					JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//					errorCAUGHT=true;
//				} catch (IllegalAccessException e) {
//					JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//					errorCAUGHT=true;
//				} catch (ClassNotFoundException e) {
//					JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//					errorCAUGHT=true;
//				} catch (SQLException e) {
//					JOptionPane.showMessageDialog(this, e, e.getMessage(), WIDTH, null);
//					errorCAUGHT=true;
//				}
					
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
				
				
				
				for (TreePath selectionPath : selectionPaths) {		//Loop through all again and delete all level 2 nodes (databases)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					DatabaseTree.setSelectionPath(null);	
					if (currentLevel == 2) {		//DELETE Databases
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

	//--------------------------------------------------------------------------------------------------------------------------------
	public void showNothing () {
		dataDisplayTextField.setText(null);	//Show nothing on the TextField
		// show nothing (a table with 0 row and 0 column) if no node is  currently selected
		DefaultTableModel tm = (DefaultTableModel) DatabaseTable.getModel();
		tm.setColumnCount(0);
		tm.setRowCount(0);
		tm.fireTableDataChanged();
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	public static String getDelimited() {
		return fileDelimited;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// Just a class that contains different examples, not being used
	public void Examples () {

		
//		JOptionPane.showMessageDialog(this, databasesFolder);		
		
		
		
		
		
		
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