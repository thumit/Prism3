package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import spectrumGUI.Spectrum_Main;

@SuppressWarnings("serial")
public class Panel_YieldProject extends JLayeredPane {
	private JSplitPane splitPane;
	private JButton btnNewRun, btnDeleteRun, btnEditRun, btnSolveRun;
	
	private String workingLocation;
	private File projectsFolder, currentProjectFolder, currentRunFolder;
	private String seperator = "/";
	private JTree projectTree;
	private DefaultMutableTreeNode root, processingNode;
	private JTextField displayTextField;

	private String currenRunTask, currentRun;
	private int currentLevel;
	private TreePath[] selectionPaths;
	private TreePath editingPath;
	private Boolean runName_Edit_HasChanged = false;
	private Boolean renaming = false;
	private Boolean isProjectNewlyCreatedOrOpened = true;

	private JToolBar projectToolBar;

	public Panel_YieldProject() {
		super.setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		// splitPane.setResizeWeight(0.15);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(200);
		// splitPane.setDividerSize(5);
		// splitPane.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Left split panel--------------------------------------------------------------------------------
		JScrollPane scrollPane_Left = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Runs");
		projectTree = new JTree(root); // Add the root of DatabaseTree
		// projectTree.setEditable(true);
		projectTree.setInvokesStopCellEditing(true); // Even when we leave the node by clicking mouse, the name editing will be kept

	
		
		projectTree.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
			public void mouseClicked(MouseEvent e) {
				doMouseClicked(e);
			}
		});
		
		projectTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {
				doWhenSelectionChange(evt);
			}
		});	
	
		projectTree.addFocusListener(new FocusListener(){		//change name whenever node stopped editing
	         public void focusGained(FocusEvent e) {  
				JTree tree = (JTree)e.getSource();
	        	if ((runName_Edit_HasChanged == true || renaming == true) && !tree.isEditing())		{ applyNamechange(); }
	         }
	         public void focusLost(FocusEvent e) {               
	         }
	     });//end addFocusListener		
		refreshProjectTree();	//Refresh the tree
		scrollPane_Left.setViewportView(projectTree);

		// Right split panel-------------------------------------------------------------------------------
		JScrollPane scrollPane_Right = new JScrollPane();
		splitPane.setRightComponent(scrollPane_Right);

		// TextField at South----------------------------------------------
		displayTextField = new JTextField("", 0);
		displayTextField.setEditable(false);

		// projectToolBar at North-------------------------------------------------------------------------
		projectToolBar = new JToolBar("Project Tools", 0);
		projectToolBar.setFloatable(false);	//to make a tool bar immovable
		projectToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor

		btnNewRun = new JButton("New Run");
		btnNewRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				refreshProjectTree();
				processingNode=root;
				new_Run();
			}
		});
		projectToolBar.add(btnNewRun);
		
		btnDeleteRun = new JButton("Delete Runs");
		btnDeleteRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				delete_Runs();
			}
		});
		projectToolBar.add(btnDeleteRun);

		btnEditRun = new JButton("Start Editing");
		btnEditRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				edit_Runs();
			}
		});
		projectToolBar.add(btnEditRun);
		
		btnSolveRun = new JButton("Start Solving");
		btnSolveRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				solve_Runs();
			}
		});
		projectToolBar.add(btnSolveRun);
		
		
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(projectToolBar, BorderLayout.NORTH);
		super.add(displayTextField, BorderLayout.SOUTH);
		super.add(splitPane, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_Project()

	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMouseClicked(MouseEvent e) {
		
		TreePath path = projectTree.getPathForLocation(e.getX(), e.getY());
		if (path == null) {
			projectTree.clearSelection();		//clear selection whenever mouse click is performed not on Jtree nodes	
			showNothing();	// show nothing if no node selected
			return;
		}
		if (path != null) displayTextField.setText(path.toString());  

		
		selectionPaths = projectTree.getSelectionPaths();
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
			projectTree.setSelectionPath(path);
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
				selectionPaths = projectTree.getSelectionPaths();			//This is very important to get the most recent selected paths
				int NodeCount=0;		//Count the number of nodes in selectionPaths
				for (TreePath selectionPath : selectionPaths) {		//Loop through all selected nodes
					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					if (processingNode.isRoot()) rootSelected = true;
					NodeCount++;		
					}
				//Deselect the root if this is a multiple Nodes selection and root is selected
				TreePath rootpath = new TreePath(root);
				if (NodeCount>1 && rootSelected==true) {
					projectTree.getSelectionModel().removeSelectionPath(rootpath);
					rootSelected =false;
					NodeCount = NodeCount -1;
				}
				//End of set up---------------------------------------------------------------
							

				// right clicked MenuItems only appear on nodes level 1, 2, or 3 (single or multiple nodes selection)
				if /* (clickedNode.isRoot()) { */ (currentLevel == 1 || currentLevel == 2 || currentLevel == 3) {
					// A popup that holds all JmenuItems
					JPopupMenu popup = new JPopupMenu();

					// All nodes can be refreshed ------------------------------------------------------------
					final JMenuItem refreshMenuItem = new JMenuItem("Refresh");
					refreshMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							refreshProjectTree();
							showNothing(); // show nothing on RightPanel and DisplayTextField
						}
					});
					popup.add(refreshMenuItem);				
					
					
					// Only nodes level 1 (root)can have "New"--------------------------
					// and this menuItem only shows up when 1 node is selected
					if ((currentLevel == 1) && NodeCount == 1) {
						final String Menuname = "New Run";
						final JMenuItem newMenuItem = new JMenuItem(Menuname);
						newMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {
								new_Run();
							}
						});
						popup.add(newMenuItem);
					}
				
					
					// Only nodes level 2 (Run) can be Deleted--------------------------
					if (currentLevel == 2 || rootSelected ==false) {					
						final JMenuItem deleteMenuItem = new JMenuItem("Delete Runs");
						deleteMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								delete_Runs();
							}
						});
						popup.add(deleteMenuItem);
					}
					
					
					// Only nodes level 2 (Run) can be Edited--------------------------
					if (currentLevel == 2 || rootSelected ==false) {					
						final JMenuItem editMenuItem = new JMenuItem("Start Editing");
						editMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								edit_Runs();
							}
						});
						popup.add(editMenuItem);
					}
					
					
					// Only nodes level 2 (Run) can be Solved--------------------------
					if (currentLevel == 2 || rootSelected ==false) {					
						final JMenuItem solveMenuItem = new JMenuItem("Start Solving");
						solveMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								solve_Runs();
							}
						});
						popup.add(solveMenuItem);
					}
					
					
					// Show the JmenuItems on selected node when it is right clicked
					popup.show(projectTree, e.getX(), e.getY());
				}
			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void doWhenSelectionChange (TreeSelectionEvent evt) {
		
		//Display full paths to the selected node to dataDisplayTextField
		TreePath path = evt.getPath();
		if (path == null) displayTextField.setText(null);
		if (path != null) displayTextField.setText(path.toString());
		
		
		// ------------Calculate the level of the current selected node
		currentLevel = path.getPathCount();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
		
		// ------------Only show currentRunTask when the node level is 3
		if (currentLevel == 3) {	//selected node is a task
			// Get the URL of the current selected node			
			currenRunTask = selectedNode.getUserObject().toString();
			// Get the parent node which is the Run that contains the selected RunTask
			currentRun = selectedNode.getParent().toString();          
			// Show the GUI for the task here.....
							
		} else if (currentLevel != 3) {		
			// show nothing if the current selected node is not a task
			showNothing();
			if (currentLevel == 2) currentRun = selectedNode.getUserObject().toString();	// the selected node is a database
			if (currentLevel == 1) currentRun = selectedNode.getUserObject().toString();	// the selected node is Root
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void refreshProjectTree() {
		// Remove all children nodes from the root of DatabaseTree, and reload the tree
		DefaultTreeModel model = (DefaultTreeModel)projectTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren();
		model.reload(root);

		// Check if Projects folder exists, if not then create it--------------------------------------------
		// Get working location of the IDE project, or runnable jar file
		final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		if (jarFile.isFile()) { // Run with JAR file
			projectsFolder = new File(":Projects");
			seperator = ":";
		}

		// Both runnable jar and IDE work with condition: Projects folder and runnable jar have to be in the same location
		workingLocation = jarFile.getParentFile().toString();
		try {
			// to handle name with space (%20)
			workingLocation = URLDecoder.decode(workingLocation, "utf-8");
			workingLocation = new File(workingLocation).getPath();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		projectsFolder = new File(workingLocation + "/Projects");
		currentProjectFolder = new File(workingLocation + "/Projects/" + Spectrum_Main.getProjectName());
		seperator = "/";
		if (!projectsFolder.exists()) {
			projectsFolder.mkdirs();
		} // Create folder Projects if it does not exist
		if (!currentProjectFolder.exists()) {
			currentProjectFolder.mkdirs();
		} // Create folder for current Project if it does not exist
		// End of create projects folder-------------------------------------------------------------------
		
		
		// These are very important codes
		//		Whenever a Project is newly created, it will be first refreshed,
		//		After the first refresh isProjectNewlyCreatedOrOpened=false
		//		Then in the next time if we do anything, the CurrentProjectFolder will be referred to the JinternalFrame title
		if (isProjectNewlyCreatedOrOpened==false) {
				currentProjectFolder= new File(workingLocation + "/Projects/" + Spectrum_Main.mainFrameReturn().getSelectedFrame().getTitle());	
		}
		isProjectNewlyCreatedOrOpened=false;
		
		
		// Find all the Runs folders in the "Projects" folder to add into DatabaseTree	
		String files;
		File[] listOfFiles = currentProjectFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("");
			}
		});

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				files = listOfFiles[i].getName();
				DefaultMutableTreeNode level2node = new DefaultMutableTreeNode(files);
				root.add(level2node);
				
//				// Read each database file and add all of its table as child nodes--------------------------------------				  
//				/*Connection*/ conn = null;
//				try {
//					Class.forName("org.sqlite.JDBC").newInstance();
//					conn = DriverManager.getConnection("jdbc:sqlite:" + databasesFolder + seperator + files);
//					DatabaseMetaData md = conn.getMetaData();
//					ResultSet rs = md.getTables(null, null, "%", null);
//					while (rs.next()) {
///*						String tableCatalog = rs.getString(1);
//						String tableSchema = rs.getString(2);					*/
//						String tableName = rs.getString(3);
//
//						DefaultMutableTreeNode level3node = new DefaultMutableTreeNode(tableName);
//						level2node.add(level3node);
//					}
//					rs.close();
//					conn.close();
//				} catch (Exception e) {
//					System.err.println(e.getClass().getName() + ": " + e.getMessage());
//				}		
			} // end of If
		} // end of For loop		
				projectTree.expandPath(new TreePath(root.getPath()));	//Expand the root		
	} // end of Refresh()
		
	// --------------------------------------------------------------------------------------------------------------------------------
	public void new_Run() {
		final String NodeName = "New Run";
		DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(NodeName);
		model.insertNodeInto(newNode, processingNode, processingNode.getChildCount());
		TreeNode[] nodes = model.getPathToRoot(newNode);
		TreePath path = new TreePath(nodes);
		projectTree.scrollPathToVisible(path);
		projectTree.setEditable(true);
		projectTree.setSelectionPath(path);
		projectTree.startEditingAtPath(path);
		editingPath = path;
		try {
			displayTextField.setText("Type your new Run name");
			runName_Edit_HasChanged = true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void applyNamechange (){
 		// This is the new Run name being applied after you finished the naming edit  		
    	DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
    	String editingName = editingNode.getUserObject().toString();		//Get the user typed name
    	
    	String temptext = null;
		currentRunFolder = new File(currentProjectFolder + "/" + editingName);
		try {
			if (currentRunFolder.mkdirs()) {
				temptext = "New Run has been Created";		
			} else {
				temptext = "New Run has not been created: Run with the same name exists, or name typed contains special characters";
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		

		// Make the new Run appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
		String RunName = currentRunFolder.getName();
		refreshProjectTree();
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> e1 = root.depthFirstEnumeration();
		while (e1.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = e1.nextElement();
			if (node.toString().equalsIgnoreCase(RunName) && root.isNodeChild(node)) {		//Name match, and node is child of root
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				TreeNode[] nodes = model.getPathToRoot(node);
				TreePath path = new TreePath(nodes);
				projectTree.scrollPathToVisible(path);
				projectTree.setSelectionPath(path);
				editingPath = path;
			}
		}

		displayTextField.setText(temptext);
		projectTree.setEditable(false);		// Disable editing
		runName_Edit_HasChanged = false;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void delete_Runs() {
		// Deselect the root if it is selected
		projectTree.getSelectionModel().removeSelectionPath(new TreePath(root));

		
		//Some set up ---------------------------------------------------------------	
		if (selectionPaths != null) {
			int node_Level;
			for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
				processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
				node_Level = selectionPath.getPathCount();
				if (node_Level == 2 && processingNode.getChildCount() >= 0) { //If node is a Run and has childs then deselect all of its childs				
					for (Enumeration e = processingNode.children(); e.hasMoreElements();) {
						TreeNode child = (TreeNode) e.nextElement();
						DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
						TreeNode[] nodes = model.getPathToRoot(child);
						TreePath path = new TreePath(nodes);
						projectTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
					}
					projectTree.collapsePath(new TreePath(processingNode.getPath())); //Collapse the selected database				
				}
			}
			selectionPaths = projectTree.getSelectionPaths(); //This is very important to get the most recent selected paths
		}
		//End of set up---------------------------------------------------------------
		
		
		if (selectionPaths != null) {		//at least 1 run has to be selected 
			//Ask to delete 
			int response = JOptionPane.showConfirmDialog(this, "Selected Runs will be deleted ?", "Confirm Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {

			} else if (response == JOptionPane.YES_OPTION) {
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				for (TreePath selectionPath : selectionPaths) { //Loop through and delete all level 2 nodes (Runs)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();
					projectTree.setSelectionPath(null);
					if (currentLevel == 2) { //DELETE selected Runs
						currentRun = processingNode.getUserObject().toString();
						model.removeNodeFromParent(processingNode);
						File file = new File(currentProjectFolder + seperator + currentRun);
						file.delete();
						showNothing();
					}
				}
			} else if (response == JOptionPane.CLOSED_OPTION) {
			}
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void edit_Runs() {
		
		// For Start Editing
		if (btnEditRun.getText()=="Start Editing") {
			//Disable all other buttons and splitPanel and change name to "Stop Editing"
			splitPane.setVisible(false);
			btnNewRun.setVisible(false);
			btnDeleteRun.setVisible(false);
			btnSolveRun.setVisible(false);
			btnEditRun.setText("Stop Editing");
			btnEditRun.setForeground(Color.RED);

			
			// Deselect the root if it is selected
			projectTree.getSelectionModel().removeSelectionPath(new TreePath(root));
			//Some set up ---------------------------------------------------------------
			if (selectionPaths != null) {
				int node_Level;
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					node_Level = selectionPath.getPathCount();
					if (node_Level == 2 && processingNode.getChildCount() >= 0) { //If node is a Run and has childs then deselect all of its childs				
						for (Enumeration e = processingNode.children(); e.hasMoreElements();) {
							TreeNode child = (TreeNode) e.nextElement();
							DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
							TreeNode[] nodes = model.getPathToRoot(child);
							TreePath path = new TreePath(nodes);
							projectTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
						}
						projectTree.collapsePath(new TreePath(processingNode.getPath())); //Collapse the selected database				
					}
				}
				selectionPaths = projectTree.getSelectionPaths(); //This is very important to get the most recent selected paths
			}
			//End of set up---------------------------------------------------------------
			if (selectionPaths != null) { //at least 1 run has to be selected 
				// Open tab panels for each selected run

			} 
		}
		
		// For Stop Editing
		else if (btnEditRun.getText() == "Stop Editing") {
			// Enable all other buttons and splitPanel and change name to "Start Editing"
			splitPane.setVisible(true);
			btnNewRun.setVisible(true);
			btnDeleteRun.setVisible(true);
			btnSolveRun.setVisible(true);
			btnEditRun.setText("Start Editing");
			btnEditRun.setForeground(null);
			
			
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void solve_Runs() {
		
		// For Start Solving
		if (btnSolveRun.getText()=="Start Solving") {
			//Disable all other buttons and splitPanel and change name to "Stop Solving"
			splitPane.setVisible(false);
			btnNewRun.setVisible(false);
			btnDeleteRun.setVisible(false);
			btnEditRun.setVisible(false);
			btnSolveRun.setText("Stop Solving");
			btnSolveRun.setForeground(Color.RED);

			
			// Deselect the root if it is selected
			projectTree.getSelectionModel().removeSelectionPath(new TreePath(root));
			//Some set up ---------------------------------------------------------------
			if (selectionPaths != null) {
				int node_Level;
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					node_Level = selectionPath.getPathCount();
					if (node_Level == 2 && processingNode.getChildCount() >= 0) { //If node is a Run and has childs then deselect all of its childs				
						for (Enumeration e = processingNode.children(); e.hasMoreElements();) {
							TreeNode child = (TreeNode) e.nextElement();
							DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
							TreeNode[] nodes = model.getPathToRoot(child);
							TreePath path = new TreePath(nodes);
							projectTree.getSelectionModel().removeSelectionPath(path); // Deselect childs
						}
						projectTree.collapsePath(new TreePath(processingNode.getPath())); //Collapse the selected database				
					}
				}
				selectionPaths = projectTree.getSelectionPaths(); //This is very important to get the most recent selected paths
			}
			//End of set up---------------------------------------------------------------
			if (selectionPaths != null) { //at least 1 run has to be selected 
				// Open tab panels for each selected run

			} 
		}
		
		// For Stop Solving
		else if (btnSolveRun.getText() == "Stop Solving") {
			//Enable all other buttons and splitPanel and change name to "Start Solving"
			splitPane.setVisible(true);
			btnNewRun.setVisible(true);
			btnDeleteRun.setVisible(true);
			btnEditRun.setVisible(true);
			btnSolveRun.setText("Start Solving");
			btnSolveRun.setForeground(null);
			
			
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void showNothing() {
		displayTextField.setText(null); // Show nothing on the TextArea
	}

}
