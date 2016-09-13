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
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import spectrumGUI.Spectrum_Main;

@SuppressWarnings("serial")
public class Panel_YieldProject extends JLayeredPane {
	private JSplitPane splitPanel;
	private Panel_EditRun editPanel;		// This panel only visible when "Start Editing"
	private Panel_SolveRun solvePanel;		// This panel only visible when "Start Solving"
	private JButton btnNewRun, btnDeleteRun, btnEditRun, btnSolveRun;
	
	private String workingLocation;
	private static File[] listOfEditRuns;
	private File projectsFolder, currentProjectFolder, currentRunFolder;
	private String seperator = "/";
	private JTree projectTree;
	private DefaultMutableTreeNode root, processingNode;
	private JTextField displayTextField;
	private JTextArea rightPanelTextArea;

	private String currentInputFile, currentRun;
	private int currentLevel;
	private TreePath[] selectionPaths;
	private TreePath editingPath;
	private Boolean runName_Edit_HasChanged = false;
	private Boolean renaming = false;
	private Boolean isProjectNewlyCreatedOrOpened = true;

	private JToolBar projectToolBar;

	public Panel_YieldProject() {
		super.setLayout(new BorderLayout(0, 0));

		splitPanel = new JSplitPane();
		// splitPane.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(200);
		// splitPane.setDividerSize(5);
		// splitPane.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Left split panel--------------------------------------------------------------------------------
		JScrollPane scrollPane_Left = new JScrollPane();
		splitPanel.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Runs");
		projectTree = new JTree(root); // Add the root of DatabaseTree
		// projectTree.setEditable(true);
		projectTree.setInvokesStopCellEditing(true); // Even when we leave the node by clicking mouse, the name editing will be kept

	
		
		projectTree.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
			public void mousePressed(MouseEvent e) {
				doMousePressed(e);
			}
		});
		
//		projectTree.addTreeSelectionListener(new TreeSelectionListener() {
//			public void valueChanged(TreeSelectionEvent evt) {
//				doWhenSelectionChange(evt);
//			}
//		});	
	
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
		splitPanel.setRightComponent(scrollPane_Right);
		
		rightPanelTextArea = new JTextArea();
		rightPanelTextArea.setEditable(false);
		scrollPane_Right.setViewportView(rightPanelTextArea);
		
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

		//------------------------------------------------------------------------------------------------
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(projectToolBar, BorderLayout.NORTH);
		super.add(displayTextField, BorderLayout.SOUTH);
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_Project()

	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMousePressed(MouseEvent e) {
		
		TreePath path = projectTree.getPathForLocation(e.getX(), e.getY());
		if (path == null) {
			projectTree.clearSelection();		//clear selection whenever mouse click is performed not on Jtree nodes	
			showNothing();	// show nothing if no node selected
			return;
		}
		if (path != null) displayTextField.setText(path.toString()); 	// display Full path
//		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
//		dataDisplayTextField.setText(selectedNode.toString());		//display Only last node name

		
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
				// Show node information of the last selected node
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				currentLevel = path.getPathCount();
				
				// ------------Only show currentInputFile when the node level is 3
				if (currentLevel == 3) {	//selected node is an InputFile
					// Get the URL of the current selected node			
					currentInputFile = selectedNode.getUserObject().toString();
					// Get the parent node which is the Run that contains the selected InputFile
					currentRun = selectedNode.getParent().toString();          
					// Show the GUI for the currentInputFile here....	
					try {
						FileReader reader;
						reader = new FileReader(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
						rightPanelTextArea.read(reader,currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);	
						reader.close();			//must close it otherwise, file is in use and cannot be deleted
					} catch (IOException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
					
				} else if (currentLevel != 3) {		
					rightPanelTextArea.setText("");
					if (currentLevel == 2) currentRun = selectedNode.getUserObject().toString();	// the selected node is a Run
					if (currentLevel == 1) currentRun = selectedNode.getUserObject().toString();	// the selected node is Root
				}
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
				
				//Deselect all level3 nodes if this is a multiple Nodes selection
				selectionPaths = projectTree.getSelectionPaths();
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					if (NodeCount>1 && selectionPath.getPathCount() == 3) {
						projectTree.getSelectionModel().removeSelectionPath(selectionPath);
						NodeCount = NodeCount - 1;
					} else {
						currentLevel = selectionPath.getPathCount();
					}
				} 
							
				//Reselect all nodes left
				selectionPaths = projectTree.getSelectionPaths();
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes	
					currentLevel = selectionPath.getPathCount();
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
					if (currentLevel == 2 && rootSelected ==false) {					
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
					if (currentLevel == 2 && rootSelected ==false) {					
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
					if (currentLevel == 2 && rootSelected ==false) {					
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
//	public void doWhenSelectionChange (TreeSelectionEvent evt) {
//	}
	
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
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
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
		String fileName;
		File[] listOfFiles = currentProjectFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("");
			}
		});

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				fileName = listOfFiles[i].getName();
				DefaultMutableTreeNode level2node = new DefaultMutableTreeNode(fileName);
				root.add(level2node);
	
				//Inside run folder, add all files as child nodes
				String filename2;
				File[] listOfFiles2 = listOfFiles[i].listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".txt") || name.endsWith(".lp");
					}
				});
				
				for (int j = 0; j < listOfFiles2.length; j++) {
					filename2 = listOfFiles2[j].getName();
					DefaultMutableTreeNode level3node = new DefaultMutableTreeNode(filename2);
					level2node.add(level3node);
				}
	
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
		//Some set up ---------------------------------------------------------------	
		if (selectionPaths != null) {
			int node_Level;
			for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
				node_Level = selectionPath.getPathCount();		
				if (node_Level == 1 || node_Level == 3) {
					projectTree.getSelectionModel().removeSelectionPath(selectionPath);		//Deselect all level 1 and level 3 nodes
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
						File[] contents = file.listFiles();		//Delete all input files inside a Run
					    if (contents != null) {
					        for (File f : contents) {
					        	f.delete();
					        }
					    }
						file.delete();		//Here, the Run folder is deleted
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
			//Some set up ---------------------------------------------------------------	
			if (selectionPaths != null) {
				int node_Level;
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					node_Level = selectionPath.getPathCount();		
					if (node_Level == 1 || node_Level == 3) {
						projectTree.getSelectionModel().removeSelectionPath(selectionPath);		//Deselect all level 1 and level 3 nodes
					}				
				}
				selectionPaths = projectTree.getSelectionPaths(); //This is very important to get the most recent selected paths
			}
			//End of set up---------------------------------------------------------------
			
					
			if (selectionPaths != null) { //at least 1 run has to be selected 
				// Create a files list that contains selected runs
				listOfEditRuns = new File[selectionPaths.length];
				int fileCount=0;
				
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				for (TreePath selectionPath : selectionPaths) { //Loop through and delete all level 2 nodes (Runs)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();
					if (currentLevel == 2) { //Add to the list
						currentRun = processingNode.getUserObject().toString();
						File file = new File(currentProjectFolder + seperator + currentRun);
						listOfEditRuns[fileCount] = file;
						fileCount++;
					}
				}
				

				//Disable all other buttons, change name to "Stop Editing",  remove splitPanel and add editPanel
				btnNewRun.setVisible(false);
				btnDeleteRun.setVisible(false);
				btnSolveRun.setVisible(false);
				displayTextField.setVisible(false);
				btnEditRun.setText("Stop Editing");
				btnEditRun.setForeground(Color.RED);
				super.remove(splitPanel);
				editPanel = new Panel_EditRun();		// This panel only visible when "Start Editing"	
				super.add(editPanel);
			} 	
		} //End of start editing
		
	
		
		// For Stop Editing
		else if (btnEditRun.getText() == "Stop Editing") {
			int response = JOptionPane.showConfirmDialog(this, 
			"Save all changes you made ?", "Stop Editing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION || response == JOptionPane.YES_OPTION) {
				//Enable all other buttons, change name to "Start Editing",  remove editPanel and add splitPanel 
				displayTextField.setVisible(true);
				btnNewRun.setVisible(true);
				btnDeleteRun.setVisible(true);
				btnSolveRun.setVisible(true);
				btnEditRun.setText("Start Editing");
				btnEditRun.setForeground(null);
				super.remove(editPanel);
				super.add(splitPanel);
				
				if (response == JOptionPane.YES_OPTION) {

					//Delete all files of the edited Runs
					for (int i = 0; i < listOfEditRuns.length; i++) {
						File[] contents = listOfEditRuns[i].listFiles();
						if (contents != null) {
							for (File f : contents) {
								f.delete();
							}
						}
					}
					//Get all input files
					File[] generalInputFile = editPanel.getGeneralInputFile();
					File[] managementOptionsFile = editPanel.getManagementOptionsFile();
					File[] CoverTypeConversionsFile = editPanel.getCoverTypeConversionsFile();
					File[] userConstraintsFile = editPanel.getUserConstraintsFile();
					//Create new input files		
					for (int i = 0; i < listOfEditRuns.length; i++) {			
							try {
								generalInputFile[i].createNewFile();
								managementOptionsFile[i].createNewFile();
								CoverTypeConversionsFile[i].createNewFile();
								userConstraintsFile[i].createNewFile();
							} catch (IOException e) {
								System.err.println(e.getClass().getName() + ": " + e.getMessage());
							}
					}
					refreshProjectTree(); //Refresh the tree
				}
			} else if (response == JOptionPane.CLOSED_OPTION) {			
			}

		}  //End of stop editing
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void solve_Runs() {
		
		// For Start Solving
		if (btnSolveRun.getText()=="Start Solving") {
			//Some set up ---------------------------------------------------------------	
			if (selectionPaths != null) {
				int node_Level;
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					node_Level = selectionPath.getPathCount();		
					if (node_Level == 1 || node_Level == 3) {
						projectTree.getSelectionModel().removeSelectionPath(selectionPath);		//Deselect all level 1 and level 3 nodes
					}				
				}
				selectionPaths = projectTree.getSelectionPaths(); //This is very important to get the most recent selected paths
			}
			//End of set up---------------------------------------------------------------
			
					
			if (selectionPaths != null) { //at least 1 run has to be selected 
				// Create a files list that contains selected runs
				listOfEditRuns = new File[selectionPaths.length];
				int fileCount=0;
				
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				for (TreePath selectionPath : selectionPaths) { //Loop through and delete all level 2 nodes (Runs)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();
					if (currentLevel == 2) { //Add to the list
						currentRun = processingNode.getUserObject().toString();
						File file = new File(currentProjectFolder + seperator + currentRun);
						listOfEditRuns[fileCount] = file;
						fileCount++;
					}
				}
				
				// Disable all other buttons, change name to "Stop Solving", remove splitPanel and add editPanel
				btnNewRun.setVisible(false);
				btnDeleteRun.setVisible(false);
				btnEditRun.setVisible(false);
				displayTextField.setVisible(false);
				btnSolveRun.setText("Stop Solving");
				btnSolveRun.setForeground(Color.RED);
				super.remove(splitPanel);
				solvePanel = new Panel_SolveRun(); // This panel only visible when "Start Solving"
				super.add(solvePanel);
			}
		} // End of start solving
		
		
		
		// For Stop Solving
		else if (btnSolveRun.getText() == "Stop Solving") {
			//Enable all other buttons and splitPanel and change name to "Start Solving"
			btnNewRun.setVisible(true);
			btnDeleteRun.setVisible(true);
			btnEditRun.setVisible(true);
			displayTextField.setVisible(true);
			btnSolveRun.setText("Start Solving");
			btnSolveRun.setForeground(null);
			super.remove(solvePanel);
			super.add(splitPanel);
			refreshProjectTree(); //Refresh the tree
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void showNothing() {
		displayTextField.setText(null); // Show nothing on the TextField
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	public static File[] getSelectedRuns() {
		return listOfEditRuns;
	}

}
