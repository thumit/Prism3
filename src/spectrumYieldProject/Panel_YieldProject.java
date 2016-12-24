package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.Rotation;

import spectrumConvenienceClasses.FilesHandle;
import spectrumConvenienceClasses.TableModelSpectrum;
import spectrumROOT.Spectrum_Main;
@SuppressWarnings("serial")
public class Panel_YieldProject extends JLayeredPane {
	private JSplitPane splitPanel;
	private Panel_EditRun editPanel;		// This panel only visible when "Start Editing"
	private Panel_SolveRun solvePanel;		// This panel only visible when "Start Solving"
	private Panel_CustomizeOutput customizeOutputPanel;		// This panel only visible when Start "Customize Output"
	private JButton btnNewRun, btnDeleteRun, btnEditRun, btnRefresh, btnSolveRun, btnCustomizeOutput;
	private ImageIcon icon;
	private Image scaleImage;
	
	private File[] listOfEditRuns;
	private File currentProjectFolder, currentRunFolder;
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

	private ToolBarWithBgImage projectToolBar;
	
	private JScrollPane scrollPane_Left, scrollPane_Right;

	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private TableModelSpectrum model;
	private Object[][] data;	
	
	public Panel_YieldProject() {
		super.setLayout(new BorderLayout(0, 0));
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately

		splitPanel = new JSplitPane();
		// splitPane.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(200);
		// splitPane.setDividerSize(5);
//		splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Left split panel--------------------------------------------------------------------------------
		scrollPane_Left = new JScrollPane();
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
		scrollPane_Right = new JScrollPane();
		splitPanel.setRightComponent(scrollPane_Right);
		
		rightPanelTextArea = new JTextArea();
		rightPanelTextArea.setEditable(false);
//		scrollPane_Right.setViewportView(rightPanelTextArea); 
		
		// TextField at South----------------------------------------------
		displayTextField = new JTextField("", 0);
		displayTextField.setEditable(false);

		// projectToolBar at North-------------------------------------------------------------------------
//		icon = new ImageIcon(getClass().getResource("/spectrumlite.png"));
//		scaleImage = icon.getImage().getScaledInstance(250, 30,Image.SCALE_SMOOTH);
//		projectToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, new ImageIcon(scaleImage));
		projectToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		projectToolBar.setFloatable(false);	//to make a tool bar immovable
		projectToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor


	
		btnNewRun = new JButton();
		btnNewRun.setToolTipText("New Run");
		icon = new ImageIcon(getClass().getResource("/icon_new.png"));
		scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnNewRun.setIcon(new ImageIcon(scaleImage));
		btnNewRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				refreshProjectTree();
				processingNode=root;
				new_Run();
			}
		});
		projectToolBar.add(btnNewRun);
		
		btnDeleteRun = new JButton();
		btnDeleteRun.setToolTipText("Delete Runs");
		icon = new ImageIcon(getClass().getResource("/icon_delete.png"));
		scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnDeleteRun.setIcon(new ImageIcon(scaleImage));
		btnDeleteRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				delete_Runs();
			}
		});
		projectToolBar.add(btnDeleteRun);

		
		btnRefresh = new JButton();
		btnRefresh.setToolTipText("Refresh");
		icon = new ImageIcon(getClass().getResource("/icon_refresh.png"));
		scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnRefresh.setIcon(new ImageIcon(scaleImage));
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				refreshProjectTree();
			}
		});
		projectToolBar.add(btnRefresh);
		
		
		btnEditRun = new JButton();
		btnEditRun.setToolTipText("Start Editing");
		icon = new ImageIcon(getClass().getResource("/icon_edit.png"));
		scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnEditRun.setIcon(new ImageIcon(scaleImage));
		btnEditRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Thread thread1 = new Thread() {			// Make a thread so JFrame will not be frozen
					public void run() {
						edit_Runs();
						Spectrum_Main.mainFrameReturn().getSelectedFrame().revalidate();
						Spectrum_Main.mainFrameReturn().getSelectedFrame().repaint();
						this.interrupt();
					}
				};
				thread1.start();
			}
		});
		projectToolBar.add(btnEditRun);
		
		
		btnSolveRun = new JButton();
		btnSolveRun.setToolTipText("Start Solving");
		icon = new ImageIcon(getClass().getResource("/icon_solve.png"));
		scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnSolveRun.setIcon(new ImageIcon(scaleImage));
		btnSolveRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				solve_Runs();
			}
		});
		projectToolBar.add(btnSolveRun);
		
		
		btnCustomizeOutput = new JButton();
		btnCustomizeOutput.setToolTipText("Customize Output");
		icon = new ImageIcon(getClass().getResource("/icon_customize.png"));
		scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnCustomizeOutput.setIcon(new ImageIcon(scaleImage));
		btnCustomizeOutput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				customize_Output();
			}
		});
		projectToolBar.add(btnCustomizeOutput);

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
//					try {
//						FileReader reader;
//						reader = new FileReader(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
//						rightPanelTextArea.read(reader,currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);	
//						reader.close();			//must close it otherwise, file is in use and cannot be deleted					
//					} catch (IOException e1) {
//						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//					}
					
					
					try {
						String delimited = "\t";		// tab delimited
						File file = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
						List<String> list;
						list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);			
						String[] a = list.toArray(new String[list.size()]);					
														
						//Setup the table--------------------------------------------------------------------------------
						columnNames = a[0].split(delimited);		//tab delimited		//Read the first row	
						rowCount = a.length - 1;  // - 1st row which is the column name
						colCount = columnNames.length;
						data = new Object[rowCount][colCount];
					
						
						// Populate the data matrix
						for (int row = 0; row < rowCount; row++) {
							String[] rowValue = a[row + 1].split(delimited);	//tab delimited	
							for (int col = 0; col < colCount; col++) {
								data[row][col] = rowValue[col];
							}	
						}	
						
						
						//Create a table
						model = new TableModelSpectrum(colCount, rowCount, columnNames, data);
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
								
								tableColumn.setPreferredWidth(maxWidth);
								return component;
							}
						};
						
						
						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
						renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
						table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			     		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//						table.getColumnModel().getColumn(1).setPreferredWidth(100);	//Set width of Column 1
			     		
//						table.setPreferredScrollableViewportSize(new Dimension(400, 100));
//						table.setFillsViewportHeight(true);

						

				
//						//Make everything transparent
//						renderer.setOpaque(false);
//						table.setOpaque(false);
//						scrollPane_Right.setOpaque(false);
						
						
						if (currentInputFile.equals("Output 4 - Management Overview.txt")) {		//show a panel with chart if the selected node name is "Output 4 - Management_Overview.txt"
							scrollPane_Right.setViewportView(new PaneL_Management_Overview(table));
							
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							table.addMouseListener(new MouseAdapter() { // Add listener
								public void mouseReleased(MouseEvent e) {
									scrollPane_Right.setViewportView(new PaneL_Management_Overview(table));
								}
							});
							
						} else {		//Show the file as table
							scrollPane_Right.setViewportView(table); 
						}
						
					} catch (IOException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}				
										

						
			
				} else if (currentLevel != 3) {		
					rightPanelTextArea.setText("");
					showNothing();
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
					icon = new ImageIcon(getClass().getResource("/icon_refresh.png"));
					scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
					refreshMenuItem.setIcon(new ImageIcon(scaleImage));
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
						icon = new ImageIcon(getClass().getResource("/icon_new.png"));
						scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						newMenuItem.setIcon(new ImageIcon(scaleImage));
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
						icon = new ImageIcon(getClass().getResource("/icon_delete.png"));
						scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						deleteMenuItem.setIcon(new ImageIcon(scaleImage));
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
						icon = new ImageIcon(getClass().getResource("/icon_edit.png"));
						scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						editMenuItem.setIcon(new ImageIcon(scaleImage));
						editMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								Thread thread2 = new Thread() {			// Make a thread so JFrame will not be frozen
									public void run() {
										edit_Runs();
										Spectrum_Main.mainFrameReturn().getSelectedFrame().revalidate();
										Spectrum_Main.mainFrameReturn().getSelectedFrame().repaint();
										this.interrupt();
									}
								};
								thread2.start();
							}
						});
						popup.add(editMenuItem);
					}
					
					
					// Only nodes level 2 (Run) can be Solved--------------------------
					if (currentLevel == 2 && rootSelected ==false) {					
						final JMenuItem solveMenuItem = new JMenuItem("Start Solving");
						icon = new ImageIcon(getClass().getResource("/icon_solve.png"));
						scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						solveMenuItem.setIcon(new ImageIcon(scaleImage));
						solveMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								solve_Runs();
							}
						});
						popup.add(solveMenuItem);
					}	
					
					// Only nodes level 2 (Run) can be Customize output--------------------------
					if (currentLevel == 2 && rootSelected ==false) {					
						final JMenuItem customizeOutput_MenuItem = new JMenuItem("Customize Output");
						icon = new ImageIcon(getClass().getResource("/icon_customize.png"));
						scaleImage = icon.getImage().getScaledInstance(15, 15,Image.SCALE_SMOOTH);
						customizeOutput_MenuItem.setIcon(new ImageIcon(scaleImage));
						customizeOutput_MenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								customize_Output();
							}
						});
						popup.add(customizeOutput_MenuItem);
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

		// CurrentProjectFolder will be referred to the currently opened and selected JinternalFrame title 
		currentProjectFolder= new File(FilesHandle.get_projectsFolder().getAbsolutePath() + "/" + Spectrum_Main.mainFrameReturn().getSelectedFrame().getTitle());	
//		currentProjectFolder = new File(workingLocation + "/Projects/" + Spectrum_Main.getProjectName());	//IF pack JInternal Frame in Spectrum_main then we need this	
	
		// Find all the Runs folders in the "Projects" folder to add into DatabaseTree	
		String fileName;
		File[] listOfFiles = currentProjectFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("");
			}
		});

		if (listOfFiles != null) {
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
					
					if (listOfFiles2 != null) {
						for (int j = 0; j < listOfFiles2.length; j++) {
							filename2 = listOfFiles2[j].getName();
							DefaultMutableTreeNode level3node = new DefaultMutableTreeNode(filename2);
							level2node.add(level3node);
						}
					}
		
				} // end of If
			} // end of For loop
		}
				projectTree.expandPath(new TreePath(root.getPath()));	//Expand the root	
				if (scrollPane_Right != null) showNothing();
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
				selectionPaths = projectTree.getSelectionPaths();
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
		if (btnEditRun.getToolTipText()=="Start Editing") {	
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
				
				for (TreePath selectionPath : selectionPaths) { //Loop through all level 2 nodes (Runs)
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
				for (Component c : projectToolBar.getComponents()) c.setVisible(false);
				displayTextField.setVisible(false);
				
				btnEditRun.setVisible(true);		
				btnEditRun.setToolTipText("Stop Editing");
				icon = new ImageIcon(getClass().getResource("/icon_back.png"));
				scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
				btnEditRun.setRolloverIcon(new ImageIcon(scaleImage));
				btnEditRun.setForeground(Color.RED);
				super.remove(splitPanel);
				editPanel = new Panel_EditRun(listOfEditRuns);		// This panel only visible when "Start Editing"	
				super.add(editPanel);
			} 	
		} //End of start editing
		
	
		
		// For Stop Editing
		else if (btnEditRun.getToolTipText() == "Stop Editing") {
			
			
			icon = new ImageIcon(getClass().getResource("/icon_question.png"));
	  		scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
	  		String[] ExitOption = {"Yes","No"};
			int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(),"Save all changes you made ?", "Stop Editing",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(scaleImage), ExitOption, ExitOption[0]);
			
			if (response == 0 || response == 1)				//Yes or No
			{
				//Enable all other buttons, change name to "Start Editing",  remove editPanel and add splitPanel 
				for (Component c : projectToolBar.getComponents()) c.setVisible(true);
				displayTextField.setVisible(true);
				
				btnEditRun.setToolTipText("Start Editing");
				btnEditRun.setRolloverIcon(null);
				btnEditRun.setForeground(null);
				super.remove(editPanel);
				super.add(splitPanel);
				
				if (response == 0)		//Yes option
				{
					//Delete all files of the edited Runs
					for (int i = 0; i < listOfEditRuns.length; i++) {
						File[] contents = listOfEditRuns[i].listFiles();
						if (contents != null) {
							for (File f : contents) {
								f.delete();
							}
						}
					}
					
					//Create new Input Files for the edited runs			
					File[][] all_Inputs_from_all_runs = editPanel.getInputFiles();		//Get input files from 2D array:  [edited runs][inputs in each run]
//					for (int i = 0; i < all_Inputs_from_all_runs.length; i++) {			
//						for (int j = 0; j < all_Inputs_from_all_runs[i].length; j++) {			
//							try {
//								all_Inputs_from_all_runs[i][j].createNewFile();			//Create new for all input files
//							} catch (IOException e) {
//								System.err.println(e.getClass().getName() + ": " + e.getMessage());
//							}
//						}
//					}
					refreshProjectTree(); //Refresh the tree
				}
	        }
			

		}  //End of stop editing
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void solve_Runs() {
		
		// For Start Solving
		if (btnSolveRun.getToolTipText()=="Start Solving") {
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
				
				for (TreePath selectionPath : selectionPaths) { //Loop through all level 2 nodes (Runs)
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
				for (Component c : projectToolBar.getComponents()) c.setVisible(false);
				displayTextField.setVisible(false);
				
				btnSolveRun.setVisible(true);
				btnSolveRun.setToolTipText("Stop Solving");
				icon = new ImageIcon(getClass().getResource("/icon_back.png"));
				scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
				btnSolveRun.setRolloverIcon(new ImageIcon(scaleImage));
				btnSolveRun.setForeground(Color.RED);
				super.remove(splitPanel);
				solvePanel = new Panel_SolveRun(listOfEditRuns); // This panel only visible when "Start Solving"
				super.add(solvePanel);
			}
		} // End of start solving
		
		
		
		// For Stop Solving
		else if (btnSolveRun.getToolTipText() == "Stop Solving") {
			//Enable all other buttons and splitPanel and change name to "Start Solving"
			for (Component c : projectToolBar.getComponents()) c.setVisible(true);
			displayTextField.setVisible(true);
			
			btnSolveRun.setToolTipText("Start Solving");
			btnSolveRun.setRolloverIcon(null);
			btnSolveRun.setForeground(null);
			super.remove(solvePanel);
			super.add(splitPanel);
			refreshProjectTree(); //Refresh the tree
		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void customize_Output() {
		
		// For Start Customizing
		if (btnCustomizeOutput.getToolTipText()=="Customize Output") {
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
				

				for (TreePath selectionPath : selectionPaths) { //Loop through all level 2 nodes (Runs)
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
				
				// Disable all other buttons, change name to "Return to Main Window", remove splitPanel and add editPanel
				for (Component c : projectToolBar.getComponents()) c.setVisible(false);
				displayTextField.setVisible(false);
				
				btnCustomizeOutput.setVisible(true);
				btnCustomizeOutput.setToolTipText("Return to Main Window");
				icon = new ImageIcon(getClass().getResource("/icon_back.png"));
				scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
				btnCustomizeOutput.setRolloverIcon(new ImageIcon(scaleImage));
				btnCustomizeOutput.setForeground(Color.RED);
				super.remove(splitPanel);
				customizeOutputPanel = new Panel_CustomizeOutput(listOfEditRuns); // This panel only visible when Start "Customize Output"
				super.add(customizeOutputPanel);
			}
		} // End of start solving
		
		
		
		// For Stop Customizing
		else if (btnCustomizeOutput.getToolTipText() == "Return to Main Window") {
			//Enable all other buttons and splitPanel and change name to "Customize Output"
			for (Component c : projectToolBar.getComponents()) c.setVisible(true);
			displayTextField.setVisible(true);
			
			btnCustomizeOutput.setToolTipText("Customize Output");
			btnCustomizeOutput.setRolloverIcon(null);
			btnCustomizeOutput.setForeground(null);
			super.remove(customizeOutputPanel);
			super.add(splitPanel);
			refreshProjectTree(); //Refresh the tree
		}
	}


	// Panel Management Overview-----------------------------------------------------------------------------	
	class PaneL_Management_Overview extends JLayeredPane {
		public PaneL_Management_Overview(JTable thisTable) {
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.weightx = 1;
		    c.weighty = 1;
		    
		    //---------------------------------------------------------------
		    //Create a chart
		    PieDataset dataset = create_ManagementOverview_dataset();
	        JFreeChart chart = createChart(dataset, "Management decisions at the start of planning horizon for " + rowCount + " EXISTING STRATA");

	        // add the chart to a panel...
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chart.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	        TitledBorder border = new TitledBorder("");
			border.setTitleJustification(TitledBorder.CENTER);
			chartPanel.setBorder(border);
	        chartPanel.setPreferredSize(new Dimension(600, 350));
	        //---------------------------------------------------------------
	        
	        
	        //---------------------------------------------------------------
	        String strataName = "";
	        if (thisTable.getSelectedRow() >= 0) 	strataName = data[thisTable.getSelectedRow()][0].toString();
	        
	        //Create a chart
		    PieDataset dataset2 = create_strataOverview_dataset();
	        JFreeChart chart2 = createChart(dataset2, "Management decisions at the start of planning horizon for '" + strataName + "' ");

	        // add the chart to a panel...
        	ChartPanel chartPanel2 = new ChartPanel(chart2);
	        chart2.getLegend().setFrame(BlockBorder.NONE);	//Remove the ugly border surrounded Legend
	        TitledBorder border2 = new TitledBorder("");
			border2.setTitleJustification(TitledBorder.CENTER);
			chartPanel2.setBorder(border2);
	        chartPanel2.setPreferredSize(new Dimension(600, 350));
	        //---------------------------------------------------------------
	        
	        
		    // Add the 1st grid - chartPanel for all Strata
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 0;
		    c.weighty = 0;
			super.add(chartPanel, c);
			
		    // Add the 2nd grid - chartPanel for the selected Strata
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 0;
			c.weighty = 0;
			super.add(chartPanel2, c);			
			
			// Add the 3rd grid - table
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.weightx = 0;
		    c.weighty = 1;
			c.fill = GridBagConstraints.VERTICAL;
			thisTable.setPreferredScrollableViewportSize(new Dimension(1216, 100));			
			JScrollPane tableScrollPane = new JScrollPane(thisTable);
			tableScrollPane.setBorder(BorderFactory.createEmptyBorder());	//Hide the border line surrounded scrollPane
			super.add(tableScrollPane, c);
		}
	
				
		private PieDataset create_ManagementOverview_dataset() {
			DefaultPieDataset dataset = new DefaultPieDataset();

			double total_NG = 0;
			double total_PB = 0;
			double total_GS = 0;
			double total_EA = 0;
			double total_MS = 0;
			for (int i = 0; i < data.length; i++) { // Loop table rows
				total_NG = total_NG + Double.parseDouble(data[i][7].toString());
				total_PB = total_PB + Double.parseDouble(data[i][8].toString());
				total_GS = total_GS + Double.parseDouble(data[i][9].toString());
				total_EA = total_EA + Double.parseDouble(data[i][10].toString());
				total_MS = total_MS + Double.parseDouble(data[i][11].toString());
			}
		
			dataset.setValue("Natural Growth", total_NG);
			dataset.setValue("Prescribed Burn", total_PB);
			dataset.setValue("Group Selection", total_GS);
			dataset.setValue("Even Age", total_EA);
			dataset.setValue("Mixed Severity Wildfire", total_MS);
			
			return dataset;
		}

		private PieDataset create_strataOverview_dataset() {
			DefaultPieDataset dataset = new DefaultPieDataset();

			if (table.getSelectedRow() >= 0) {
				double total_NG = 0;
				double total_PB = 0;
				double total_GS = 0;
				double total_EA = 0;
				double total_MS = 0;
					
				total_NG = total_NG + Double.parseDouble(data[table.getSelectedRow()][7].toString());
				total_PB = total_PB + Double.parseDouble(data[table.getSelectedRow()][8].toString());
				total_GS = total_GS + Double.parseDouble(data[table.getSelectedRow()][9].toString());
				total_EA = total_EA + Double.parseDouble(data[table.getSelectedRow()][10].toString());
				total_MS = total_MS + Double.parseDouble(data[table.getSelectedRow()][11].toString());

			
				dataset.setValue("Natural Growth", total_NG);
				dataset.setValue("Prescribed Burn", total_PB);
				dataset.setValue("Group Selection", total_GS);
				dataset.setValue("Even Age", total_EA);
				dataset.setValue("Mixed Severity Wildfire", total_MS);
			}

			return dataset;
		}		
		
		
		@SuppressWarnings("deprecation")
		private JFreeChart createChart(PieDataset dataset, String chartName) {
			JFreeChart chart = ChartFactory.createPieChart3D(chartName, // chart title
					dataset, // dataset
					true, // include legend
					true, false);		
			chart.setBorderVisible(true);
			chart.setBackgroundPaint(Color.LIGHT_GRAY);
			chart.getLegend().setBackgroundPaint(null);
			chart.getLegend().setPosition(RectangleEdge.BOTTOM);
			chart.getLegend().setItemFont(new java.awt.Font("defaultFont", java.awt.Font.PLAIN, 13));
			chart.getTitle().setFont(new java.awt.Font("defaultFont", java.awt.Font.BOLD, 14));
					
			PiePlot3D  plot = (PiePlot3D ) chart.getPlot();
			plot.setOutlinePaint(null);
			plot.setStartAngle(135);
	        plot.setDirection(Rotation.CLOCKWISE);
	        plot.setForegroundAlpha(0.6f);
	        plot.setBackgroundPaint(null);
			plot.setNoDataMessage("Select an existing strata in the table below to view chart");
			plot.setExplodePercent(1, 0.1);
			
			PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
		            "{0}: {1} ({2})", new DecimalFormat("0.00 acres"), new DecimalFormat("0.0%"));			// "{0}: {1} ({2})"
		    plot.setLabelGenerator(gen);	    
		    plot.setLabelBackgroundPaint(null);
		    plot.setLabelShadowPaint(null);
		    plot.setLabelOutlinePaint(null);
		    plot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
		    
		  
		    
//		    plot.setLabelLinksVisible(false);
//			plot.setLabelGenerator(null);
//			plot.setSimpleLabels(true);
			return chart;
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
	
	// --------------------------------------------------------------------------------------------------------------------------------		
	public void showNothing() {
//		displayTextField.setText(null); // Show nothing on the TextField
		scrollPane_Right.setViewportView(null);
	}

}
