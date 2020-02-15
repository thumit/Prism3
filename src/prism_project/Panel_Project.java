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
package prism_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import prism_convenience.ColorUtil;
import prism_convenience.FilesHandle;
import prism_convenience.IconHandle;
import prism_convenience.MarqueePanel;
import prism_convenience.PrismTableModel;
import prism_convenience.PrismTextAreaReadMe;
import prism_convenience.ToolBarWithBgImage;
import prism_project.data_process.LinkedList_Databases_Item;
import prism_project.data_process.Read_Database;
import prism_project.edit.Panel_Edit;
import prism_project.output.Output_Panel_Basic_Constraints;
import prism_project.output.Output_Panel_Flow_Constraints;
import prism_project.output.Output_Panel_Management_Details_NOSQL;
import prism_project.output.Output_Panel_Management_Details_SQL;
import prism_project.output.Output_Panel_Management_Overview;
import prism_project.solve.Panel_Solve;
import prism_root.OptionPane_Startup;
import prism_root.PrismMain;
@SuppressWarnings("serial")
public class Panel_Project extends JLayeredPane {
	private JSplitPane splitPanel;
	private Panel_Edit editPanel;		// This panel only visible when "Start Editing"
	private Panel_Solve solvePanel;		// This panel only visible when "Start Solving"
	private JButton btnNewRun, btnDeleteRun, btnEditRun, btnRefresh, btn_compact, btnSolveRun, btnCollectMemory, btnSave, btnHint;
	private List<JButton> buttons_list;
	private Boolean is_compact_view = false;
	private MarqueePanel maequee_panel = new MarqueePanel();;
	
	private File[] listOfEditRuns;
	private File currentProjectFolder, currentRunFolder;
	private String seperator = "/";
	private JTree projectTree;
	private DefaultMutableTreeNode root, processingNode;
	private JTextField display_text_field;

	private String currentInputFile, currentProject, currentRun;
	private int currentLevel;
	private TreePath[] selectionPaths;
	private TreePath editingPath;
	private File oldfile;
	private Boolean runName_Edit_HasChanged = false;
	private Boolean renamingRun = false;

	private ToolBarWithBgImage projectToolBar;
	private JScrollPane scrollPane_Left;
	private JScrollPane scrollPane_Right;
	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private PrismTableModel model;
	private Object[][] data;	
	private TableFilterHeader filterHeader = new TableFilterHeader();
	
	private PrismTextAreaReadMe readme;
	private Output_Panel_Management_Details_NOSQL management_details_NOSQL_panel;
	private Output_Panel_Management_Details_SQL management_details_SQL_panel;
	private boolean is_output_05_processing = false;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	
	public Panel_Project(String currentProject) {
		
		this.currentProject = currentProject;
		this.currentProjectFolder = new File(FilesHandle.get_projectsFolder().getAbsolutePath() + "/" + this.currentProject);
		
		this.setLayout(new BorderLayout(0, 0));
		ToolTipManager.sharedInstance().setInitialDelay(0);		//Show toolTip immediately

		splitPanel = new JSplitPane();
//		splitPane.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(250);
//		splitPanel.setDividerSize(5);
//		splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Left split panel--------------------------------------------------------------------------------
		scrollPane_Left = new JScrollPane();
		scrollPane_Left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
//		scrollPane_Left.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
//		scrollPane_Left.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 12));
		splitPanel.setLeftComponent(scrollPane_Left);
		root = new DefaultMutableTreeNode("Runs");
		projectTree = new JTree(root); // Add the root of projectTree
		
		class CustomIconRenderer extends DefaultTreeCellRenderer {	// set icon for tree node
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				Object nodeObj = ((DefaultMutableTreeNode) value).getUserObject();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

				// check whatever you need to on the node user object
				if (nodeObj.toString().startsWith("input") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_new.png"));
				} 
				if (nodeObj.toString().startsWith("output") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_new1.png"));
				} 
				if (nodeObj.toString().startsWith("readme") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_new1.png"));
				}
				if (nodeObj.toString().startsWith("summarize") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_new1.png"));
				}
				if (nodeObj.toString().contains("output_04") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_pie.png"));
				}
				if (nodeObj.toString().startsWith("summarize_output_06") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_flow2.png"));
				}
				if (nodeObj.toString().contains("output_07") && node.getLevel() == 2) {
					setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_flow.png"));
				}
				return this;
			}
		}
		projectTree.setCellRenderer(new CustomIconRenderer());
		
		// projectTree.setEditable(true);
		projectTree.setInvokesStopCellEditing(true); // Even when we leave the node by clicking mouse, the name editing will be kept

	
		
		projectTree.addMouseListener(new MouseAdapter() { // Add listener to projectTree
			public void mousePressed(MouseEvent e) {
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
				
				
				
				try {
					if (!is_output_05_processing) executor.shutdownNow();
				} catch (Exception e1) {
					System.err.println("Thread shut down fails - " + e1.getClass().getName() + ": " + e1.getMessage());
				}
				executor = Executors.newSingleThreadExecutor();
				executor.submit(() -> {
					doMousePressed(e);
				});
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
	     });		
		refreshProjectTree();	// Refresh the tree
		scrollPane_Left.setViewportView(projectTree);

		// Right split panel-------------------------------------------------------------------------------
		scrollPane_Right = new JScrollPane();
		scrollPane_Right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
//		scrollPane_Right.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
//		scrollPane_Right.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 12));
		splitPanel.setRightComponent(scrollPane_Right);
		
		// TextField at South----------------------------------------------
		display_text_field = new JTextField("", 0);
		display_text_field.setEditable(false);

		// projectToolBar at North-------------------------------------------------------------------------
//		projectToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, IconHandle.get_scaledImageIcon(250, 25, "spectrumlite.png"));
		projectToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
		projectToolBar.setFloatable(false);	//to make a tool bar immovable
		projectToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor


	
		btnNewRun = new JButton();
		btnNewRun.setToolTipText("New Run");
		btnNewRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_new.png"));
		btnNewRun.setFocusable(false);
		btnNewRun.addActionListener(e -> {
			refreshProjectTree();
			processingNode=root;
			new_Run();
		});
		projectToolBar.add(btnNewRun);
		
		btnDeleteRun = new JButton();
		btnDeleteRun.setToolTipText("Delete Runs");
		btnDeleteRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_delete.png"));
		btnDeleteRun.setFocusable(false);
		btnDeleteRun.addActionListener(e -> {
			delete_Runs();
		});
		projectToolBar.add(btnDeleteRun);

		
		btnRefresh = new JButton();
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_refresh.png"));
		btnRefresh.setFocusable(false);
		btnRefresh.addActionListener(e -> {
//			is_compact_view = (is_compact_view) ? false : true;
//			if (is_compact_view) { 
//				btnRefresh.setToolTipText("Refresh");
//				btnRefresh.setIcon(IconHandle.get_rotated_scaledImageIcon(25, 25, "icon_refresh.png"));
//			} else {
//				btnRefresh.setToolTipText("Refresh and display only summary outputs");
//				btnRefresh.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_refresh.png"));
//			}
			refreshProjectTree();
		});
		projectToolBar.add(btnRefresh);
		
		
		btn_compact = new JButton();
		btn_compact.setToolTipText("Show fewer outputs");
		btn_compact.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_script_gray.png"));
		btn_compact.setFocusable(false);
		btn_compact.addActionListener(e -> {
			is_compact_view = (is_compact_view) ? false : true;
			if (is_compact_view) {
				btn_compact.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_script.png"));
				btn_compact.setToolTipText("Show all outputs");
			} else {
				btn_compact.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_script_gray.png"));
				btn_compact.setToolTipText("Show fewer outputs (only outputs summary)");
			}
			refreshProjectTree();
		});
		projectToolBar.add(btn_compact);
		
		
		btnEditRun = new JButton();
		btnEditRun.setToolTipText("Start Editing");
		btnEditRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_edit.png"));
		btnEditRun.setFocusable(false);
		btnEditRun.addActionListener(e -> {
			edit_Runs();
		});
		projectToolBar.add(btnEditRun);
		
		
		btnSolveRun = new JButton();
		btnSolveRun.setToolTipText("Start Solving");
		btnSolveRun.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_solve.png"));
		btnSolveRun.setFocusable(false);
		btnSolveRun.addActionListener(e -> {
			solve_Runs();
		});
		projectToolBar.add(btnSolveRun);

		
		btnCollectMemory = new JButton();
		btnCollectMemory.setToolTipText("Collect Memory (memory in use: " + OptionPane_Startup.memory_in_use() + ", memory left: " + OptionPane_Startup.memory_left());
		btnCollectMemory.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_ram.png"));
		btnCollectMemory.setFocusable(false);
		btnCollectMemory.addActionListener(e -> {
			OptionPane_Startup.Restart_Project(currentProject);
		});
		btnCollectMemory.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent e) {
		    	btnCollectMemory.setToolTipText("Collect Memory (memory in use: " + OptionPane_Startup.memory_in_use() + ", memory left: " + OptionPane_Startup.memory_left());
		    }
		});
		projectToolBar.add(btnCollectMemory);
		
		
		btnSave = new JButton();
		btnSave.setToolTipText("Save");
		btnSave.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_save.png"));	
		btnSave.setVisible(false);
		projectToolBar.add(btnSave);
		
		
		btnHint = new JButton();
		btnHint.setToolTipText("Hints & Facts");
		btnHint.setFocusable(false);
		btnHint.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_light_on.png"));	
		btnHint.addActionListener(e -> {
			if (maequee_panel.is_text_running() == true) {
				maequee_panel.stop();
				btnHint.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_light_off.png"));
			} else {
				maequee_panel.start();
				btnHint.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_light_on.png"));
			}
		});
		projectToolBar.add(btnHint);
		
		
		projectToolBar.add(Box.createGlue());	//Add glue for alignment
		projectToolBar.add(Box.createGlue());	//Add glue for alignment
		projectToolBar.add(Box.createGlue());	//Add glue for alignment
		projectToolBar.add(maequee_panel);
		projectToolBar.add(Box.createGlue());	//Add glue for alignment
		
		
		// Make this list to make all buttons in this windows not focus on the ugly blue border after click
		buttons_list = new ArrayList<JButton>();
		buttons_list.add(btnNewRun);
		buttons_list.add(btnDeleteRun);
		buttons_list.add(btnEditRun);
		buttons_list.add(btnRefresh);
		buttons_list.add(btn_compact);
		buttons_list.add(btnSolveRun);
		buttons_list.add(btnCollectMemory);
		buttons_list.add(btnSave);
		buttons_list.add(btnHint);
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
		
		
		//------------------------------------------------------------------------------------------------
		// Add all components to JInternalFrame------------------------------------------------------------
		this.add(projectToolBar, BorderLayout.NORTH);
		this.add(display_text_field, BorderLayout.SOUTH);
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
		if (path != null) display_text_field.setText(path.toString()); 	// display Full path
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
					File file = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);	
					
					try {
						List<String> lines_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);			
						try {
							// Setup the table--------------------------------------------------------------------------------
							String delimited = "\t";		// tab delimited
							columnNames = lines_list.get(0).split(delimited);		// read the first row	
							lines_list.remove(0); 	// remove the  first line which is the column name
							rowCount = lines_list.size();
							colCount = columnNames.length;
							data = new Object[rowCount][colCount];
							
							// populate the data matrix
							for (int row = 0; row < rowCount; row++) {
								data[row] = lines_list.get(row).split(delimited);	// tab delimited	
								int total_row_elements = data[row].length;
								for (int col = total_row_elements; col < colCount; col++) {
									data[row][col] = "";		// if lacking data --> fill the data with empty string
								}	
							}
							
							// create a table
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
								
								@Override	// Implement table cell tool tips, just for the output_07_flow_constraints          
								public String getToolTipText(MouseEvent e) {
									java.awt.Point p = e.getPoint();
									int row = rowAtPoint(p);
									int col = columnAtPoint(p);
									String tip = (table.getColumnName(col).equals("flow_description") && row >= 0 && getValueAt(row, col) != null) ? getValueAt(row, col).toString() : null;
									return tip;
								}
							};
													
							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
							renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
							table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				     		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						} catch (Exception e2) {
							System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
							System.out.println("Fail to create table data. Often this is only when Readme.txt has nothing");
							table = new JTable();
						} finally {
							lines_list = null;	// clear memory after reading file	
						}
			     		
			     					     		
						// Show table on the scroll panel
						if (currentInputFile.startsWith("output_04_management_overview") || currentInputFile.startsWith("summarize_output_04_management_overview")) {
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							Output_Panel_Management_Overview chart_panel = new Output_Panel_Management_Overview(table, data);
							scrollPane_Right.setViewportView(chart_panel);
						} 
						
						else if (currentInputFile.equals("summarize_output_05_management_details.txt")) {	
						    Thread thread_management_details = new Thread() {			// Make a thread for output5
								public void run() {
									is_output_05_processing = true;
									
									// stop running text to optimize speed
									maequee_panel.stop();
									btnHint.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_light_off.png"));
									
									// 2 links buttons (to clear bug 20). This is to remove all static definitions (static would make display fails when multiple projects are open)
									JButton SQL_link_button = new JButton();
									SQL_link_button.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent actionEvent) {	
											scrollPane_Right.setViewportView(management_details_NOSQL_panel);
										}
									});	
									JButton NoSQL_link_button = new JButton();
									NoSQL_link_button.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent actionEvent) {	
											scrollPane_Right.setViewportView(management_details_SQL_panel);
										}
									});	
									
									management_details_SQL_panel = null;
									management_details_NOSQL_panel = null;
									management_details_SQL_panel = new Output_Panel_Management_Details_SQL(file, columnNames, data, SQL_link_button);
									management_details_SQL_panel.get_btnSwitch().setEnabled(false);
									scrollPane_Right.setViewportView(management_details_SQL_panel);
									
									File file_database = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/database.db");
									Read_Database read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
									if (read_database == null) {
										read_database = new Read_Database(file_database);	// Read the database
										PrismMain.get_databases_linkedlist().update(file_database, read_database);			
										System.out.println(PrismMain.get_databases_linkedlist().size());
										for (LinkedList_Databases_Item rr: PrismMain.get_databases_linkedlist()) {
											System.out.println(rr.file_database.getAbsolutePath() + rr.last_modify);
										}
									}
									management_details_NOSQL_panel = new Output_Panel_Management_Details_NOSQL(executor, currentProjectFolder, currentRun, table, data, model, NoSQL_link_button);
									management_details_SQL_panel.get_btnSwitch().setEnabled(true);
									
									is_output_05_processing = false;
									this.interrupt();
								}
							};
							thread_management_details.start();
						} 
						
						else if (currentInputFile.startsWith("summarize_output_06_basic_constraints")) {		//show a panel with bar and line charts
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							Output_Panel_Basic_Constraints chart_panel = new Output_Panel_Basic_Constraints(table, data);
							scrollPane_Right.setViewportView(chart_panel);
						} 
						
						else if (currentInputFile.startsWith("output_07_flow_constraints") || currentInputFile.startsWith("summarize_output_07_flow_constraints")) {		//show a panel with bar and line charts
							table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							Output_Panel_Flow_Constraints chart_panel = new Output_Panel_Flow_Constraints(currentProjectFolder, currentRun, table, data);
							scrollPane_Right.setViewportView(chart_panel);
						} 
						
						else if (currentInputFile.equals("readme.txt")) {		// show the file as text area
				 			readme = new PrismTextAreaReadMe("icon_tree.png", 70, 70);
				 			readme.activate_clicktosave_feature(file);
				 			scrollPane_Right.setViewportView(readme);
							try {
								FileReader reader = new FileReader(currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
								readme.read(reader, currentProjectFolder.getAbsolutePath() + "/" + currentRun + "/" + currentInputFile);
								reader.close();
							} catch (IOException e1) {
								System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
							}			
//							scrollPane_Right.setViewportView(new Panel_Readme(file, readme));
							scrollPane_Right.setViewportView(readme);
						} 
						
						else {		// Show the file as table
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
				if (currentLevel == 3 && !currentInputFile.equals("output_05_management_details.txt") && !currentInputFile.equals("output_07_flow_constraints.txt")) {
					// show the filter only when double left click
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
					
					
					// Only nodes level 2 (Run) can be Deleted--------------------------
					if (currentLevel == 2 && rootSelected == false) {					
						final JMenuItem updateMenuItem = new JMenuItem("Update Runs");
						updateMenuItem.setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_light_on_yellow.png"));
						updateMenuItem.setMnemonic(KeyEvent.VK_U);
						updateMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent actionEvent) {								
								update_runs_to_new_prism_version();
							}
						});
						popup.add(updateMenuItem);
						updateMenuItem.setEnabled(false);
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
		DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
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
							if (!is_compact_view) 
								return (name.endsWith(".txt") && !name.startsWith("output_05_fly_constraints")) || name.endsWith(".lp") || name.endsWith(".sol");
							else
								return (name.endsWith(".txt") && !name.startsWith("output_05_fly_constraints") && !name.startsWith("output_")) || name.endsWith(".lp") || name.endsWith(".sol");
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
		
		projectTree.expandPath(new TreePath(root.getPath())); // Expand the root
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
			display_text_field.setText("Type your new Run name");
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
				
		    	display_text_field.setText("Type your new Run name");
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
		Enumeration<TreeNode> e1 = root.depthFirstEnumeration();
		while (e1.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e1.nextElement();
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

		display_text_field.setText(temptext);
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
		Enumeration<TreeNode> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) { // Search for the name that match
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			if (node.toString().equalsIgnoreCase(RunName) && root.isNodeChild(node)) {		// Name match, and node is child of root
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				TreeNode[] nodes = model.getPathToRoot(node);
				TreePath path = new TreePath(nodes);
				if (path != null) display_text_field.setText(path.toString()); 	// display Full path
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
			String ExitOption[] = {"Delete", "Cancel"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), "Selected Runs will be deleted?", "Confirm Delete",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
			if (response == 0) {
				DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
				for (TreePath selectionPath : selectionPaths) { //Loop through and delete all level 2 nodes (Runs)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
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
				int fileCount = 0;

				for (TreePath selectionPath : selectionPaths) { //Loop through all level 2 nodes (Runs)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					if (currentLevel == 2) { // Add to the list
						currentRun = processingNode.getUserObject().toString();
						File file = new File(currentProjectFolder + seperator + currentRun);
						listOfEditRuns[fileCount] = file;
						fileCount++;
					}
				}
				
				this.setVisible(false); //----------------------------------------------
				//Disable all other buttons, change name to "Stop Editing",  remove splitPanel and add editPanel
				for (Component c : buttons_list) if (c != btnHint && c != btnEditRun) c.setVisible(false);
				display_text_field.setVisible(false);				
				btnSave.setVisible(true);
				btnEditRun.setToolTipText("Stop Editing");
				btnEditRun.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_back.png"));
				btnEditRun.setForeground(Color.RED);
				this.remove(splitPanel);
				editPanel = new Panel_Edit(listOfEditRuns, btnSave);		// This panel only visible when "Start Editing"	
				this.add(editPanel);
				this.setVisible(true); //----------------------------------------------
			} 	
		} //End of start editing
		
	
		
		// For Stop Editing
		else if (btnEditRun.getToolTipText() == "Stop Editing") {
			String[] ExitOption = { "Stop & save", "Stop & don't save", "Cancel"};
			int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Outputs from all runs listed on the left screen will be deleted when click 'Stop & save'\n Your option?", "Stop Editing",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
			
			if (response == 0 || response == 1) { // Yes or No			
				// Enable all other buttons, change name to "Start Editing",  remove editPanel and add splitPanel 
				for (Component c : buttons_list) c.setVisible(true);
				display_text_field.setVisible(true);
				btnSave.setVisible(false);
				btnEditRun.setToolTipText("Start Editing");
				btnEditRun.setRolloverIcon(null);
				btnEditRun.setForeground(null);
				this.remove(editPanel);
				this.add(splitPanel);
				
				if (response == 0) { // Yes option				
					editPanel.save_inputs();	// Save Input Files. Delete all output files, problem file, and solution file, but keep the fly_constraints file
				}
				
				// Refresh the tree regardless of Yes or No			
				refreshProjectTree();
				
				// Make the runs appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
				for (File file : listOfEditRuns) {
					String RunName = file.getName();
					@SuppressWarnings("unchecked")
					Enumeration<TreeNode> e = root.depthFirstEnumeration();
					while (e.hasMoreElements()) { // Search for the name that match
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
						if (node.toString().equalsIgnoreCase(RunName) && root.isNodeChild(node)) {		// Name match, and node is child of root
							DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
							TreeNode[] nodes = model.getPathToRoot(node);
							TreePath path = new TreePath(nodes);
							if (path != null) display_text_field.setText(path.toString()); 	// display Full path
							projectTree.scrollPathToVisible(path);
							projectTree.addSelectionPath(path);
							editingPath = path;
							selectionPaths = projectTree.getSelectionPaths();
						}
					}
				}
	        }
		}  // End of stop editing
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	public void solve_Runs() {
//		// Summarize output
//		File runFolder = new File(currentProjectFolder.getAbsolutePath() + "/" + currentRun);
//		Summarize_Outputs sumamrize_output = new Summarize_Outputs(runFolder, 3);
//		sumamrize_output = null;
		
		
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
			
			// stop running text to optimize speed
			maequee_panel.stop();
			btnHint.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_light_off.png"));
			//End of set up---------------------------------------------------------------
			
					
			if (selectionPaths != null) { //at least 1 run has to be selected 
				// Create a files list that contains selected runs
				listOfEditRuns = new File[selectionPaths.length];
				int fileCount = 0;
				
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
				for (Component c : buttons_list) if (c != btnHint) c.setVisible(false);
				display_text_field.setVisible(false);
				
				btnSolveRun.setVisible(true);
				btnSolveRun.setToolTipText("Stop Solving");
				btnSolveRun.setRolloverIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_back.png"));
				btnSolveRun.setForeground(Color.RED);
				this.remove(splitPanel);
				
				try {
					solvePanel = new Panel_Solve(listOfEditRuns); // This panel only visible when "Start Solving"
				} catch (Exception e) {
				} finally {
					if (solvePanel != null) {
						this.add(solvePanel);
					} else {
						File jar_file = new File(PrismMain.get_main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
						String directory ="";
						try {
							directory = URLDecoder.decode(jar_file.getParentFile().getAbsolutePath(), "utf-8");
						} catch (UnsupportedEncodingException e) {
						}
						String msg = "To make this window visible, you need: \n"
								+ "Step 1. Obtain 'cplex.jar' and 'cplex1261.dll' from IBM - ILOG CPLEX \n"
								+ "Step 2. Put 'cplex.jar' into the folder '" + directory.replaceAll("\\\\", "/") + "/" + jar_file.getName().replace(".jar", "") + "_lib' \n"
								+ "Step 3. Put 'cplex1261.dll' into the folder '" + directory.replaceAll("\\\\", "/") + "/Temporary \n\n"
								+ "Note: \n"
								+ "1. CPLEX should be free for academic use: www.ibm.com/products/ilog-cplex-optimization-studio \n"
								+ "2. Use the correct version (32bit or 64bit) of the .jar and .dll files for all the above 3 steps \n"
								+ "3. If your CPLEX version is higher than 12.61, just rename the .dll file to 'cplex1261.dll' before doing step 3 \n";
						String[] ExitOption = { "OK"};
						int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), msg, "Solver Requirement",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_Warning.png"), ExitOption, ExitOption[0]);
					}
				}
			}
		} // End of start solving
		
		
		
		// For Stop Solving
		else if (btnSolveRun.getToolTipText() == "Stop Solving") {
			//Enable all other buttons and splitPanel and change name to "Start Solving"
			for (Component c : projectToolBar.getComponents()) c.setVisible(true);
			display_text_field.setVisible(true);
			btnSave.setVisible(false);
			btnSolveRun.setToolTipText("Start Solving");
			btnSolveRun.setRolloverIcon(null);
			btnSolveRun.setForeground(null);
			if (solvePanel != null) this.remove(solvePanel);
			this.add(splitPanel);
			refreshProjectTree(); //Refresh the tree
			
			// Make the runs appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
			for (File file : listOfEditRuns) {
				String RunName = file.getName();
				@SuppressWarnings("unchecked")
				Enumeration<TreeNode> e = root.depthFirstEnumeration();
				while (e.hasMoreElements()) { // Search for the name that match
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (node.toString().equalsIgnoreCase(RunName) && root.isNodeChild(node)) {		// Name match, and node is child of root
						DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
						TreeNode[] nodes = model.getPathToRoot(node);
						TreePath path = new TreePath(nodes);
						if (path != null) display_text_field.setText(path.toString()); 	// display Full path
						projectTree.scrollPathToVisible(path);
						projectTree.addSelectionPath(path);
						editingPath = path;
						selectionPaths = projectTree.getSelectionPaths();
					}
				}
			}
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public void update_runs_to_new_prism_version() {
		// The below is used to rename all input files in version 1.2.01 to the new names as required by version 1.2.02
		
		String ask_ExitOption[] = { "Update", "Cancel"};
		int ask_response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), "Update will be instant.\nYou are recommended to make copies of the runs before updating, just in case", "Runs update",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(32, 32, "icon_light_on_yellow.png"), ask_ExitOption, ask_ExitOption[0]);
		if (ask_response == 0) {
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
				int fileCount = 0;

				for (TreePath selectionPath : selectionPaths) { //Loop through all level 2 nodes (Runs)
					currentLevel = selectionPath.getPathCount();
					DefaultMutableTreeNode processingNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
					if (currentLevel == 2) { // Add to the list
						currentRun = processingNode.getUserObject().toString();
						File file = new File(currentProjectFolder + seperator + currentRun);
						listOfEditRuns[fileCount] = file;
						fileCount++;
					}
				}
					
				int file_changed_count = 0;
				int file_deleted_count = 0;
				for (int i = 0; i < listOfEditRuns.length; i++) {
					File[] contents = listOfEditRuns[i].listFiles();
					if (contents != null) {
						for (File f : contents) {
							switch (f.getName()) {

							case "input_05_non_sr_disturbances.txt":
								try {
									// Read sample file into data
									File[] files = new File[] { FilesHandle.get_file_input_05(), FilesHandle.get_file_input_05_alt() };	
									
									for (File sample_file: files) {		// there are 2 sample files for HLC and CGNF to try
										String delimited = "\t";		// tab delimited
										List<String> list = Files.readAllLines(Paths.get(sample_file.getAbsolutePath()), StandardCharsets.UTF_8);			
										String[] a = list.toArray(new String[list.size()]);		
										
										
										columnNames = a[0].split(delimited);		//tab delimited		//Read the first row	
										rowCount = a.length - 1;  // - 1st row which is the column name
										colCount = columnNames.length;
										data = new Object[rowCount][colCount];
								
										// populate the data matrix
										for (int row = 0; row < rowCount; row++) {
											data[row] = a[row + 1].split(delimited);	//tab delimited	
										}
										
										
										// Read old file into old_data
										f = new File(f.getParentFile().getAbsolutePath() + "/" + "input_05_non_sr_disturbances.txt");		
										delimited = "\t";		// tab delimited
										list = Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.UTF_8);		
										a = list.toArray(new String[list.size()]);		
										
										String[] old_columnNames = a[0].split(delimited);		//tab delimited		//Read the first row	
										rowCount = a.length - 1;  // - 1st row which is the column name
										colCount = old_columnNames.length;
										Object[][] old_data = new Object[rowCount][colCount];
								
										// populate the data matrix
										for (int row = 0; row < rowCount; row++) {
											old_data[row] = a[row + 1].split(delimited);	//tab delimited	
										}
										
										
										if (old_columnNames.length == 4 && data.length == old_data.length) {	// This is the only case we would modify the old file, this file only has 4 columns and number of row in both file match
											// paste from old_data to data
											for (int row = 0; row < data.length; row++) {
												for (int col = 0; col < data[row].length; col++) {
													if (col == 3 || col == 4) data[row][col] = old_data[row][col - 1];
												}
											}

											if (f.exists()) {
												f.delete();		// Delete the old file before writing new contents to that old file
											}
											
											if (data != null && data.length > 0) {
												try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(f))) {
													for (int j = 0; j < columnNames.length; j++) {
														fileOut.write(columnNames[j] + "\t");
													}

													for (int row = 0; row < data.length; row++) {
														fileOut.newLine();
														for (int col = 0; col < data[row].length; col++) {
															fileOut.write(data[row][col] + "\t");
														}
													}
													fileOut.close();
												} catch (IOException e) {
													System.err.println(e.getClass().getName() + ": " + e.getMessage());
												} 
											}
											file_changed_count++;
										}
									}
									
									
									// Now the old file might be modified or not. If not modified yet --> delete
									f = new File(f.getParentFile().getAbsolutePath() + "/" + "input_05_non_sr_disturbances.txt");		
									String delimited = "\t";		// tab delimited
									List<String> list = Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.UTF_8);		
									String[] a = list.toArray(new String[list.size()]);		
									String[] columnNames = a[0].split(delimited);		//tab delimited		//Read the first row	
									
									if (columnNames.length == 4) { // This is the case we would delete the old file
										f.delete();
										file_deleted_count++;
									}
									
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								break;
							}
						}
					}
				}
					
				// Refresh the tree		
				refreshProjectTree();
				
				// Make the runs appear on the TREE----------->YEAHHHHHHHHHHHHHHH	
				for (File file : listOfEditRuns) {
					String RunName = file.getName();
					@SuppressWarnings("unchecked")
					Enumeration<TreeNode> e = root.depthFirstEnumeration();
					while (e.hasMoreElements()) { // Search for the name that match
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
						if (node.toString().equalsIgnoreCase(RunName) && root.isNodeChild(node)) {		// Name match, and node is child of root
							DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
							TreeNode[] nodes = model.getPathToRoot(node);
							TreePath path = new TreePath(nodes);
							if (path != null) display_text_field.setText(path.toString()); 	// display Full path
							projectTree.scrollPathToVisible(path);
							projectTree.addSelectionPath(path);
							editingPath = path;
							selectionPaths = projectTree.getSelectionPaths();
						}
					}
				}
				
				String warningText = "";
				if (file_changed_count > 0) warningText = file_changed_count + " files have been modified.\n";
				if (file_deleted_count > 0) warningText = file_deleted_count + " files have been deleted.\n";
				if (file_deleted_count == 0 && file_changed_count == 0) warningText = "Prism found no need to modify anything.\n";
				warningText = warningText + "\nHighlighted runs are updated to the lastest version 1.2.02.\n"; 
				warningText = warningText + "\nNote 1: input_05 is modified when Prism found a solution to update your run.\n"; 
				warningText = warningText + "Note 2: input_05 is deleted when Prism could not find a solution to update your run.\n";
				warningText = warningText + "Note 3: Update result might not be correct in several cases. You are recommended to review input_05 after updating.";
				String ExitOption[] = { "OK"};
				int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), warningText, "Runs update",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(32, 32, "icon_light_on_yellow.png"), ExitOption, ExitOption[0]);
			}
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------		
	public void showNothing() {
		display_text_field.setText(null); // Show nothing on the TextField
		scrollPane_Right.setViewportView(null);
	}
}
