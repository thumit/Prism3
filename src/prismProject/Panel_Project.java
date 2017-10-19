package prismProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import prismConvenienceClass.ColorUtil;
import prismConvenienceClass.FilesHandle;
import prismConvenienceClass.IconHandle;
import prismConvenienceClass.PrismTableModel;
import prismConvenienceClass.ToolBarWithBgImage;
import prismRoot.PrismMain;
@SuppressWarnings("serial")
public class Panel_Project extends JLayeredPane {
	private JSplitPane splitPanel;
	private Panel_EditRun editPanel;		// This panel only visible when "Start Editing"
	private Panel_SolveRun solvePanel;		// This panel only visible when "Start Solving"
	private Panel_CustomizeOutput customizeOutputPanel;		// This panel only visible when Start "Customize Output"
	private JButton btnNewRun, btnDeleteRun;
	private JButton btnEditRun;
	private JButton btnRefresh;
	private JButton btnSolveRun;
	private JButton btnCustomizeOutput;
	
	private File[] listOfEditRuns;
	private File currentProjectFolder, currentRunFolder;
	private String seperator = "/";
	private JTree projectTree;
	private DefaultMutableTreeNode root, processingNode;
	private JTextField displayTextField;

	private String currentInputFile, currentProject, currentRun;
	private int currentLevel;
	private TreePath[] selectionPaths;
	private TreePath editingPath;
	private File oldfile;
	private Boolean runName_Edit_HasChanged = false;
	private Boolean renamingRun = false;

	private ToolBarWithBgImage projectToolBar;
	
	private JScrollPane scrollPane_Left, scrollPane_Right;

	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private PrismTableModel model;
	private Object[][] data;	
	private TableFilterHeader filterHeader = new TableFilterHeader();
	
	private TextArea_ReadMe readme;
	private Thread thread_management_details;
	
	public Panel_Project(String currentProject) {
		
		this.currentProject = currentProject;
		this.currentProjectFolder = new File(FilesHandle.get_projectsFolder().getAbsolutePath() + "/" + this.currentProject);
		
		this.setLayout(new BorderLayout(0, 0));
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately

		splitPanel = new JSplitPane();
		// splitPane.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(245);
		// splitPane.setDividerSize(5);
//		splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Left split panel--------------------------------------------------------------------------------
		scrollPane_Left = new JScrollPane();
		splitPanel.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Runs");
		projectTree = new JTree(root); // Add the root of projectTree
		// projectTree.setEditable(true);
		projectTree.setInvokesStopCellEditing(true); // Even when we leave the node by clicking mouse, the name editing will be kept

	
		
		projectTree.addMouseListener(new MouseAdapter() { // Add listener to projectTree
			public void mousePressed(MouseEvent e) {
				if (thread_management_details != null && thread_management_details.isAlive()) {
					thread_management_details.interrupt();	// stop the thread which creating the panel for output5
					thread_management_details.stop();			// NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE : THIS IS DANGEROUS WAY TO STOP THREAD: DEPRICATED
				}						
//				// Interrupt all running threads				
//				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//				Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//				for (Thread t : threadArray) {
//					if (t.getState() == Thread.State.RUNNABLE && t != Thread.currentThread()) {
//						t.interrupt();
//						PrismMain.get_main().revalidate();
//						PrismMain.get_main().repaint();
//					}
//				}
//				System.gc();	// collect memory				
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
	        	if ((runName_Edit_HasChanged == true || renamingRun == true) && !projectTree.isEditing()) {
					applyNamechange();
				}
	         }
	         public void focusLost(FocusEvent e) {               
	         }
	     });//end addFocusListener		
		refreshProjectTree();	//Refresh the tree
		scrollPane_Left.setViewportView(projectTree);

		// Right split panel-------------------------------------------------------------------------------
		scrollPane_Right = new JScrollPane();
		splitPanel.setRightComponent(scrollPane_Right);
		
		// TextField at South----------------------------------------------
		displayTextField = new JTextField("", 0);
		displayTextField.setEditable(false);

		// projectToolBar at North-------------------------------------------------------------------------
//		projectToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, IconHandle.get_scaledImageIcon(250, 25, "spectrumlite.png"));
		projectToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		projectToolBar.setFloatable(false);	//to make a tool bar immovable
		projectToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor


	
		btnNewRun = new JButton();
		btnNewRun.setToolTipText("New Run");
		btnNewRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_new.png"));
		btnNewRun.addActionListener(e -> {
			refreshProjectTree();
			processingNode=root;
			new_Run();
		});
		projectToolBar.add(btnNewRun);
		
		btnDeleteRun = new JButton();
		btnDeleteRun.setToolTipText("Delete Runs");
		btnDeleteRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_delete.png"));
		btnDeleteRun.addActionListener(e -> {
			delete_Runs();
		});
		projectToolBar.add(btnDeleteRun);

		
		btnRefresh = new JButton();
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_refresh.png"));
		btnRefresh.addActionListener(e -> {
			refreshProjectTree();
		});
		projectToolBar.add(btnRefresh);
		
		
		btnEditRun = new JButton();
		btnEditRun.setToolTipText("Start Editing");
		btnEditRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_edit.png"));
		btnEditRun.addActionListener(e -> {
			Thread thread = new Thread() {			// Make a thread so JFrame will not be frozen
				public void run() {
					edit_Runs();
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().revalidate();
					PrismMain.get_Prism_DesktopPane().getSelectedFrame().repaint();
					this.interrupt();
				}
			};
			thread.start();
		});
		projectToolBar.add(btnEditRun);
		
		
		btnSolveRun = new JButton();
		btnSolveRun.setToolTipText("Start Solving");
		btnSolveRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_solve.png"));
		btnSolveRun.addActionListener(e -> {
			solve_Runs();
		});
		projectToolBar.add(btnSolveRun);
		
		
		btnCustomizeOutput = new JButton();
		btnCustomizeOutput.setToolTipText("Customize Output");
		btnCustomizeOutput.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_customize.png"));
		btnCustomizeOutput.addActionListener(e -> {
			customize_Output();
		});
		projectToolBar.add(btnCustomizeOutput);

		//------------------------------------------------------------------------------------------------
		// Add all components to JInternalFrame------------------------------------------------------------
		this.add(projectToolBar, BorderLayout.NORTH);
		this.add(displayTextField, BorderLayout.SOUTH);
		this.add(splitPanel, BorderLayout.CENTER);
		this.setOpaque(false);
	} // end Panel_Project()

	// --------------------------------------------------------------------------------------------------------------------------------
	public void doMousePressed(MouseEvent e) {
		filterHeader.setTable(null);		//set null filter after each mouse click: this is important
		
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
				if (currentLevel == 3) {	// Selected node is an Input or Output						
					currentInputFile = selectedNode.getUserObject().toString();	// Get the URL of the current selected node			
					currentRun = selectedNode.getParent().toString();    // Get the parent node which is the Run that contains the selected InputFile      
									
					try {
						String delimited = "\t";		// tab delimited
						File file = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
						List<String> list;
						list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);			
						String[] a = list.toArray(new String[list.size()]);					
														
						// Setup the table--------------------------------------------------------------------------------
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
						
						
						// Create a table
						model = new PrismTableModel(rowCount, colCount, data, columnNames);
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
				
						// Show table on the scroll panel
						if (currentInputFile.equals("output_04_management_overview.txt")) {		//show a panel with 2 pie charts
							Output_Panel_Management_Overview chart_panel = new Output_Panel_Management_Overview(table, data);
							scrollPane_Right.setViewportView(chart_panel);
						} else if (currentInputFile.equals("output_05_management_details.txt")) {							
							JPanel tempPanel = new JPanel(new GridBagLayout());
							GridBagConstraints c = new GridBagConstraints();
							c.fill = GridBagConstraints.BOTH;
																			
//							JButton runStatButton = new JButton(IconHandle.get_scaledImageIcon(200, 150, "pikachuRunning.gif"));
//							JButton runStatButton = new JButton(IconHandle.get_scaledImageIcon_replicate(160, 200, "pikachuWierd.gif"));
							JButton runStatButton = new JButton(IconHandle.get_scaledImageIcon_replicate(200, 200, "pikachuWalk.gif"));
							runStatButton.setHorizontalTextPosition(JButton.CENTER);
							runStatButton.setVerticalTextPosition(JButton.TOP);
							runStatButton.setFont(new Font(null, Font.BOLD, 15));
							runStatButton.setText("Loading Customize Mode - Click me to stop");
							runStatButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									thread_management_details.stop();			// NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE : THIS IS DANGEROUS WAY TO STOP THREAD: DEPRICATED	
									scrollPane_Right.setViewportView(table);
								}						
							});
							
							JTextArea tempArea = new JTextArea();
							tempArea.setFocusable(false);
							tempArea.setOpaque(false);
							tempArea.setEditable(false);
							DefaultCaret caret = (DefaultCaret) tempArea.getCaret();
							caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);		
							tempArea.append("\n \n");
							tempArea.append("It takes time reading database to enter Customize Mode. Example:");	
							tempArea.append("\n \n");
							tempArea.append("CNPZ: approximately 16,000 rows & 100 columns: 5 seconds");					
							tempArea.append("\n \n");	
							tempArea.append("CGNF: approximately 40,000 rows & 60 columns: 25 seconds");
							tempArea.append("\n \n");	
							tempArea.append("Plus time loading Preset Filter: 1 - 10 seconds ");
							tempArea.append("\n \n");
							tempArea.append("Please be patient...");
														
							c.gridx = 0;
							c.gridy = 0;
							c.gridheight = 2;
							c.weightx = 1;
							c.weighty = 1;	
							tempPanel.add(new JScrollPane(table), c);
									
							c.gridx = 1;
							c.gridy = 0;
							c.gridheight = 1;
							c.weightx = 0;
							c.weighty = 0;	
							tempPanel.add(runStatButton, c);
							
							c.gridx = 1;
							c.gridy = 1;
							c.gridheight = 1;
							c.weightx = 0;
							c.weighty = 1;	
							tempPanel.add(tempArea, c);
							
							
							scrollPane_Right.setViewportView(tempPanel);
							if (thread_management_details == null || !thread_management_details.isAlive()) {	// don't create new Thread if the current thread is still alive
								thread_management_details = new Thread() {			// Make a thread for output5
									public void run() {
										Output_Panel_Management_Details management_details_panel = new Output_Panel_Management_Details(currentProjectFolder, currentRun, table, data, model);
										scrollPane_Right.setViewportView(management_details_panel);
										this.interrupt();
									}
								};
								thread_management_details.start();
							}
						} else if (currentInputFile.equals("output_07_flow_constraints.txt")) {		//show a panel with bar and line charts
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							Output_Panel_Flow_Constraints chart_panel = new Output_Panel_Flow_Constraints(table, data);
							scrollPane_Right.setViewportView(chart_panel);
						} else if (currentInputFile.equals("readme.txt")) {		// show the file as text area
				 			readme = new TextArea_ReadMe("minionWrite.png", 70, 70);
				 			readme.setBackground(ColorUtil.makeTransparent(Color.WHITE, 255));
				 			readme.setEditable(false);
				 			readme.setRequestFocusEnabled(false);
							try {
								FileReader reader = new FileReader(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
								readme.read(reader, currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
								reader.close();
							} catch (IOException e1) {
								System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
							}													
							scrollPane_Right.setViewportView(readme);
						} else {		// Show the file as table
							scrollPane_Right.setViewportView(table); 
						}				
					} catch (IOException e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}				
										
				} else if (currentLevel != 3) {	
					scrollPane_Right.setViewportView(null);
					if (currentLevel == 2) currentRun = selectedNode.getUserObject().toString();	// the selected node is a Run
					if (currentLevel == 1) currentRun = selectedNode.getUserObject().toString();	// the selected node is Root
				}
			} else if (e.getClickCount() == 2) {
				// Do something here
				if (currentLevel == 3) {
					if (currentInputFile.equals("output_05_management_details.txt")) {
						thread_management_details.stop();			// NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE NOTE : THIS IS DANGEROUS WAY TO STOP THREAD: DEPRICATED
						scrollPane_Right.setViewportView(table);
					}
					
					//show the filter only when double left click
					filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
					filterHeader.setTable(table);
					filterHeader.setFilterOnUpdates(true);
				}
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getClickCount() == 1) {				
				
				// Some set up when there is a right click---------------------------------------------------------------
				Boolean rootSelected = false;		
				selectionPaths = projectTree.getSelectionPaths();			//This is very important to get the most recent selected paths
				int NodeCount = 0;		//Count the number of nodes in selectionPaths
				for (TreePath selectionPath : selectionPaths) {		//Loop through all selected nodes
					processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					if (processingNode.isRoot()) rootSelected = true;
					NodeCount++;
				}
				
				// Deselect the root if this is a multiple Nodes selection and root is selected
				TreePath rootpath = new TreePath(root);
				if (NodeCount > 1 && rootSelected == true) {
					projectTree.getSelectionModel().removeSelectionPath(rootpath);
					rootSelected = false;
					NodeCount = NodeCount - 1;
				}
				
				// Check if at least a run is selected
				boolean is_atleast_a_run_selected = false;
				selectionPaths = projectTree.getSelectionPaths();
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					if (selectionPath.getPathCount() == 2) {
						is_atleast_a_run_selected = true;
					}
				} 
									
				// Deselect all level3 nodes if this is a multiple Nodes selection & at least a run is selected
				selectionPaths = projectTree.getSelectionPaths();
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes
					if (NodeCount > 1 && selectionPath.getPathCount() == 3 && is_atleast_a_run_selected) {
						projectTree.getSelectionModel().removeSelectionPath(selectionPath);
						NodeCount = NodeCount - 1;
					} else {
						currentLevel = selectionPath.getPathCount();
					}
				} 
							
				// Reselect all nodes left
				selectionPaths = projectTree.getSelectionPaths();
				for (TreePath selectionPath : selectionPaths) { //Loop through all selected nodes	
					currentLevel = selectionPath.getPathCount();
				}
				//End of set up---------------------------------------------------------------
							

				// Right clicked MenuItems only appear on nodes level 1, 2, or 3 (single or multiple nodes selection)
				if (currentLevel == 1 || currentLevel == 2 || currentLevel == 3) {
					// A popup that holds all JmenuItems
					JPopupMenu popup = new JPopupMenu();

					// All nodes can be refreshed ------------------------------------------------------------
					final JMenuItem refreshMenuItem = new JMenuItem("Refresh");
					refreshMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_refresh.png"));
					refreshMenuItem.setMnemonic(KeyEvent.VK_R);
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
						newMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_new.png"));
						newMenuItem.setMnemonic(KeyEvent.VK_N);
						newMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {
								new_Run();
							}
						});
						popup.add(newMenuItem);
					}
					
					
					// Only nodes level 2 (Run) can be renamed--------------------------
					// and this menuItem only shows up when 1 node is selected	
					if (currentLevel == 2 && NodeCount == 1) {
						final JMenuItem renameMenuItem = new JMenuItem("Rename");
						renameMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_rename.png"));
						renameMenuItem.setMnemonic(KeyEvent.VK_N);
						renameMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								rename_Run();
							}
						});
						popup.add(renameMenuItem);
					}
					
					
					// Only nodes level 2 (Run)can have "copy"--------------------------
					// and this menuItem only shows up when 1 node is selected	
					if (currentLevel == 2 && NodeCount == 1) {
						final JMenuItem copyMenuItem = new JMenuItem("Make a Copy");
						copyMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_copy.png"));
						copyMenuItem.setMnemonic(KeyEvent.VK_C);
						copyMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								copy_Run();
							}
						});
						popup.add(copyMenuItem);
					}
				
					
					// Only nodes level 2 (Run) can be Edited--------------------------
					if (currentLevel == 2 && rootSelected == false) {					
						final JMenuItem editMenuItem = new JMenuItem("Start Editing");
						editMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_edit.png"));
						editMenuItem.setMnemonic(KeyEvent.VK_E);
						editMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								Thread thread = new Thread() {			// Make a thread so JFrame will not be frozen
									public void run() {
										edit_Runs();
										PrismMain.get_Prism_DesktopPane().getSelectedFrame().revalidate();
										PrismMain.get_Prism_DesktopPane().getSelectedFrame().repaint();
										this.interrupt();
									}
								};
								thread.start();
							}
						});
						popup.add(editMenuItem);
					}
									
					
					// Only nodes level 2 (Run) can be Solved--------------------------
					if (currentLevel == 2 && rootSelected == false) {					
						final JMenuItem solveMenuItem = new JMenuItem("Start Solving");
						solveMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_solve.png"));
						solveMenuItem.setMnemonic(KeyEvent.VK_S);
						solveMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								solve_Runs();
							}
						});
						popup.add(solveMenuItem);
					}	
					
					
					// Only nodes level 2 (Run) can be Customize output--------------------------
					if (currentLevel == 2 && rootSelected == false) {					
						final JMenuItem customizeOutput_MenuItem = new JMenuItem("Customize Output");
						customizeOutput_MenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_customize.png"));
						customizeOutput_MenuItem.setMnemonic(KeyEvent.VK_O);
						customizeOutput_MenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								customize_Output();
							}
						});
						popup.add(customizeOutput_MenuItem);
					}
					
					
					// Only nodes level 2 (Run) can be Deleted--------------------------
					if (currentLevel == 2 && rootSelected == false) {					
						final JMenuItem deleteMenuItem = new JMenuItem("Delete Runs");
						deleteMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_delete.png"));
						deleteMenuItem.setMnemonic(KeyEvent.VK_D);
						deleteMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								delete_Runs();
							}
						});
						popup.add(deleteMenuItem);
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
		filterHeader.setTable(null);		//set null filter after refresh: this is important
		
		// Remove all children nodes from the root of projectTree, and reload the tree
		DefaultTreeModel model = (DefaultTreeModel)projectTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren();
		model.reload(root);	
	
		// Find all the Runs folders in the "Projects" folder to add into projectTree	
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
							return (name.endsWith(".txt") && !name.startsWith("output_05_fly_constraints")) || name.endsWith(".lp") || name.endsWith(".sol");
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
				if (scrollPane_Right != null) {
					scrollPane_Right.setViewportView(null);
				}
	} // end of Refresh()
		
	// --------------------------------------------------------------------------------------------------------------------------------
	public void new_Run() {
		final String NodeName = "new_run";
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
	public void rename_Run() {	
		if (processingNode != null && currentLevel == 2) {		//rename Run
			DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();	
			TreeNode[] nodes = model.getPathToRoot(processingNode);
			TreePath path = new TreePath(nodes);
			projectTree.scrollPathToVisible(path);
			projectTree.setEditable(true);
			projectTree.setSelectionPath(path);
			projectTree.startEditingAtPath(path);
			editingPath = path;	
			try {
				// Get and save the old name
				DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
		    	String nameWOext = editingNode.getUserObject().toString();		//Get the user typed name
//		    	if(nameWOext.contains(".")) nameWOext= nameWOext.substring(0, nameWOext.lastIndexOf('.'));		//Remove extension if the name has it
		    	String editingName = currentProjectFolder + seperator + nameWOext;
		    	oldfile = new File(editingName);
		    	// Then perform:	applyDatabase_Namechange
				
		    	displayTextField.setText("Type your new Run name");
				renamingRun = true;
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}		
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void applyNamechange (){
 		// This is the new Run name being applied after you finished the naming edit  		
    	DefaultMutableTreeNode editingNode = (DefaultMutableTreeNode) editingPath.getLastPathComponent();  	
    	String editingName = editingNode.getUserObject().toString();		//Get the user typed name
    	File newfile = new File(currentProjectFolder + seperator + editingName);
    	String temptext = null;
    	
    	
    	if (renamingRun == true) {		// For "rename Run"
    		if (!newfile.exists()) {  			
    			File[] contents = oldfile.listFiles();		// loop all files inside the old Run
			    if (contents != null) {
					for (File f : contents) {								
						if (f.getName().endsWith(".db")) {
							PrismMain.get_databases_linkedlist().remove(f);		// If this is the database then remove it from RAM because the old run is going to be renamed
						}
					}
				}
    			oldfile.renameTo(newfile);
    			temptext = oldfile.getName() + " has been renamed to " + newfile.getName();		
    		} else {
    			temptext = "Rename fail: Run with the same name exists, or name typed contains special characters";	
    		}				
		} else {	// For "new Run"
			currentRunFolder = new File(currentProjectFolder + "/" + editingName);
			try {
				if (currentRunFolder.mkdirs()) {
					temptext = "New Run has been created";		
				} else {
					temptext = "New Run has not been created: Run with the same name exists, or name typed contains special characters";
				}
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
    			

		// Make the new Run appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
		String RunName = newfile.getName();
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
		renamingRun = false;
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void copy_Run() {
		DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) projectTree.getSelectionPath().getLastPathComponent();	// currentLevel = 2 & nodeCount =1 already
		projectTree.setSelectionPath(null);
		currentRun = processingNode.getUserObject().toString();
				
		File sourceFile = new File(currentProjectFolder + seperator + currentRun);
		File deskFile = new File(currentProjectFolder + seperator + sourceFile.getName() + "_copy");
		if (deskFile.exists()) {
			String name = deskFile.getAbsolutePath();
			int count = 2;
			while (new File(name + count).exists()) {
				count++;
			}
			deskFile = new File(name + count);
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
			System.out.println("Fail to make a copy of " + currentRun);
		}
		
		
		// Make the new Run appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
		String RunName = deskFile.getName();
		refreshProjectTree();
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = e.nextElement();
			if (node.toString().equalsIgnoreCase(RunName) && root.isNodeChild(node)) {		// Name match, and node is child of root
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				TreeNode[] nodes = model.getPathToRoot(node);
				TreePath path = new TreePath(nodes);
				projectTree.scrollPathToVisible(path);
				projectTree.setSelectionPath(path);
				editingPath = path;
				selectionPaths = projectTree.getSelectionPaths();
			}
		}
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
								if (f.getName().endsWith(".db")) {
										PrismMain.get_databases_linkedlist().remove(f);		// If this is the database then remove it from RAM before delete
								}
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
				
				this.setVisible(false); //----------------------------------------------
				//Disable all other buttons, change name to "Stop Editing",  remove splitPanel and add editPanel
				for (Component c : projectToolBar.getComponents()) c.setVisible(false);
				displayTextField.setVisible(false);
				
				btnEditRun.setVisible(true);		
				btnEditRun.setToolTipText("Stop Editing");
				btnEditRun.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_back.png"));
				btnEditRun.setForeground(Color.RED);
				this.remove(splitPanel);
				editPanel = new Panel_EditRun(listOfEditRuns);		// This panel only visible when "Start Editing"	
				this.add(editPanel);
				this.setVisible(true); //----------------------------------------------
			} 	
		} //End of start editing
		
	
		
		// For Stop Editing
		else if (btnEditRun.getToolTipText() == "Stop Editing") {
			
			
			String[] ExitOption = { "Save", "Don't Save", "Cancel"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Save all changes you made ?", "Stop Editing",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
			
			if (response == 0 || response == 1)				//Yes or No
			{
				//Enable all other buttons, change name to "Start Editing",  remove editPanel and add splitPanel 
				for (Component c : projectToolBar.getComponents()) c.setVisible(true);
				displayTextField.setVisible(true);
				
				btnEditRun.setToolTipText("Start Editing");
				btnEditRun.setRolloverIcon(null);
				btnEditRun.setForeground(null);
				this.remove(editPanel);
				this.add(splitPanel);
				
				if (response == 0)		//Yes option
				{
					//Delete all output files, problem file, and solution file of the edited Runs
					for (int i = 0; i < listOfEditRuns.length; i++) {
						File[] contents = listOfEditRuns[i].listFiles();
						if (contents != null) {
							for (File f : contents) {
								if (f.getName().contains("output") || f.getName().contains("problem") || f.getName().contains("solution")) {
									f.delete();
								}
							}
						}
					}
					
					//Create new Input Files for the edited runs & Refresh the tree		
					editPanel.createInputFiles();	
					refreshProjectTree();
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
				btnSolveRun.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_back.png"));
				btnSolveRun.setForeground(Color.RED);
				this.remove(splitPanel);
				solvePanel = new Panel_SolveRun(listOfEditRuns); // This panel only visible when "Start Solving"
				this.add(solvePanel);
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
			this.remove(solvePanel);
			this.add(splitPanel);
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
				btnCustomizeOutput.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_back.png"));
				btnCustomizeOutput.setForeground(Color.RED);
				this.remove(splitPanel);
				customizeOutputPanel = new Panel_CustomizeOutput(listOfEditRuns); // This panel only visible when Start "Customize Output"
				this.add(customizeOutputPanel);
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
			this.remove(customizeOutputPanel);
			this.add(splitPanel);
			refreshProjectTree(); //Refresh the tree
		}
	}
			
	
	// --------------------------------------------------------------------------------------------------------------------------------		
	public void showNothing() {
		displayTextField.setText(null); // Show nothing on the TextField
		scrollPane_Right.setViewportView(null);
	}
	
}
