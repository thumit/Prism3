package saveForReference;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class Panel_DatabaseManagement extends JLayeredPane {
private JTree DatabaseTree; 
private DefaultMutableTreeNode root;
private JTextField dataDisplayTextField;
private JTextField queryTextField;  
private JTable DatabaseTable;
private Connection conn;
private String currenTableName, currentDatabase, currentSQLstatement;
private int currentLevel;
private TreePath[] selectionPaths;
private File databasesFolder;
private String seperator;

	public Panel_DatabaseManagement() {
		super.setLayout(new BorderLayout(0, 0));

		
		//Split panel at Center of the Internal Frame------------------
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.15);
		
		//Left split panel-----------------------------------
		JScrollPane scrollPane_Left = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Databases");
		DatabaseTree = new JTree(root);	//Add the root of DatabaseTree
//		DatabaseTree.setEditable(true);
		DatabaseTree.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
			public void mouseClicked(MouseEvent e) {
				DatabaseTree.setEnabled(true);
				queryTextField.setText("Type your queries here");
				doMouseClickedOnTree(e);
			}
		});
		DatabaseTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {				
				doWhenSelectionChange(evt);
			}
		});
		refreshDatabaseTree();	//Refresh the tree
		scrollPane_Left.setViewportView(DatabaseTree);
					
		
		//Right split panel-----------------------------------
		JScrollPane scrollPane_Right = new JScrollPane();
		splitPane.setRightComponent(scrollPane_Right);
		DatabaseTable = new JTable();
		DatabaseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // this solve problems when resizing --> column width of the table becomes too narrow 
		scrollPane_Right.setViewportView(DatabaseTable);
		
		// TextField at South----------------------------------------------
		dataDisplayTextField = new JTextField("", 0);
		
		// DatabaseTextField at North--------------------------------------
		queryTextField = new JTextField("Type your queries here", 0);
		
		MouseAdapter queryTextField_mouseClick = new MouseAdapter()	//When user press Enter on Keyboard
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseTree.setEnabled(false);		//disable the tree
				if (queryTextField.getText().equals("Type your queries here")) {	//clear the text
					queryTextField.setText(null);					
				}
			}
		};
		queryTextField.addMouseListener(queryTextField_mouseClick); // Add listener to queryTextField

			
		Action action = new AbstractAction()	//When user press Enter on Keyboard
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSQLstatement = queryTextField.getText();	
				doQuery(currentSQLstatement);
				queryTextField.setText(null);
			}
		};
		queryTextField.addActionListener(action);	

		
		// Add all components to JInternalFrame-----------------------------		
		super.add(queryTextField, BorderLayout.NORTH);
		super.add(dataDisplayTextField, BorderLayout.SOUTH);
		super.add(splitPane, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end newProjectPanel()	
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void refreshDatabaseTree() {
		// Remove all children nodes from the root of DatabaseTree, and reload the tree
		DefaultTreeModel model = (DefaultTreeModel)DatabaseTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren();
		model.reload(root);

		// Find all the .db files in the predefined folder to add into to DatabaseTree			
		
		final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());		
		if(jarFile.isFile()) {  // Run with JAR file
//			databasesFolder = jarFile;		//return: "C:\Users\dzungcsu\Desktop\spectrumlite.jar
//			seperator = "/";
			
//			databasesFolder = new File (GetExecutionPath() + "/Databases");
//			seperator = "/";
			
			databasesFolder = new File (jarFile + "/Databases");	//return: "C:\Users\dzungcsu\Desktop\spectrumlite.jar\Databases
			seperator = "/";
		} else { // Run with IDE
			 databasesFolder = new File("C:\\SpectrumLite\\Databases\\");	//All databases are put directly in the working folder of SPECTRUMLITE
			 seperator = "\\";
			 
//			databasesFolder = new File (jarFile + "/Databases");	//return: C:\SpectrumLite\JavaModel\bin\Databases
//			seperator = "/";
		}

		
		
		databasesFolder = new File("C:\\SpectrumLite\\Databases\\");	//All databases are put directly in the working folder of SPECTRUMLITE
		seperator = "\\";
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
	
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMouseClickedOnTree(MouseEvent e) {     	
		TreePath path = DatabaseTree.getPathForLocation(e.getX(), e.getY());
		if (path == null) {
			DatabaseTree.clearSelection();		//clear selection whenever mouse click is performed not on Jtree nodes
			dataDisplayTextField.setText(null);	//Show nothing on the TextField
			// show nothing (a table with 0 row and 0 column) if no node is  currently selected
			showNothing();
			return;
		}
		if (path != null) dataDisplayTextField.setText(path.toString());

//		final DefaultMutableTreeNode clickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
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
				// System.out.println(selectedTreeNode.getText());
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getClickCount() == 1) {				
				selectionPaths = DatabaseTree.getSelectionPaths();			//This is very important to get the most recent selected paths
				
				if /*(clickedNode.isRoot()) {*/ (currentLevel == 1 || currentLevel == 2 || currentLevel == 3) {		//righclick Menu apprear only when node level 1, 2, or 3 is selected
					// A Popup window that holds all JmenuItems
					JPopupMenu popup = new JPopupMenu();
					
					// All nodes can be refreshed ------------------------------------------------------------
					final JMenuItem refreshMenuItem = new JMenuItem("Refresh");
					refreshMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							refreshDatabaseTree();
							dataDisplayTextField.setText(null);	//Show nothing on the TextField
							// show nothing (a table with 0 row and 0 column) if no node is  currently selected
							showNothing();
						}
					});
					popup.add(refreshMenuItem);
					
					// Only nodes level 2 (database) and 3 (table) can be deleted--------------------------
					if /*(clickedNode.isRoot()) {*/ (currentLevel == 2 || currentLevel == 3) {
						final JMenuItem deleteMenuItem = new JMenuItem("Delete");
						deleteMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								DefaultTreeModel model = (DefaultTreeModel) DatabaseTree.getModel();	
								
								
								for (TreePath selectionPath : selectionPaths) {		//Loop through all and delete all level 3 nodes
									currentLevel = selectionPath.getPathCount();
									DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
									DatabaseTree.setSelectionPath(null);
									
									if (currentLevel == 3) {		//DELETE Tables						
										currentDatabase = processingNode.getParent().toString(); 
										currenTableName = processingNode.getUserObject().toString();
										model.removeNodeFromParent(processingNode);
										doQuery("DROP TABLE IF EXISTS " + currenTableName);
										showNothing();
									}
					
								}	
								
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
		//Display only the selected node name
//		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
//		dataDisplayTextField.setText(selectedNode.toString());
		
		//Display full paths to the selected node
		TreePath path = evt.getPath();
		if (path == null) dataDisplayTextField.setText(null);
		if (path != null) dataDisplayTextField.setText(path.toString());
		
		
		// ------------Calculate the level of the current selected node
		//TreePath path_level = DatabaseTree.getSelectionPath();
		currentLevel = path.getPathCount();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
		
		// ------------Only show DatabaseTable if the node level is 3
		if (currentLevel == 3) {	//selected node is a table
			 // Get the URL of the current selected node			
			currenTableName = selectedNode.getUserObject().toString();
			// Get the parent node which is the database that contains the selected table
			currentDatabase = selectedNode.getParent().toString();          
			currentSQLstatement = "SELECT * FROM [" + currenTableName + "];";		//this query show the whole table information
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
			}
		}
	}
	
	public void showNothing () {
		dataDisplayTextField.setText(null);	//Show nothing on the TextField
		// show nothing (a table with 0 row and 0 column) if no node is  currently selected
		DefaultTableModel tm = (DefaultTableModel) DatabaseTable.getModel();
		tm.setColumnCount(0);
		tm.setRowCount(0);
		tm.fireTableDataChanged();
	}
	
	// To help get the path to databases, use it later
	@SuppressWarnings("unused")
	private String GetExecutionPath(){
	    String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
	    absolutePath = absolutePath.replaceAll("%20"," "); // Surely need to do this here
	    return absolutePath;
	};
	
} // Final End		
	
	
	
//	//Get variable to pass to other classes
//	  public String getConnection() {
//		     return CurrenTableName;
//		  }
