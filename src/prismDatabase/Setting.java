package prismDatabase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import prismConvenienceClass.FilesHandle;
import prismConvenienceClass.IconHandle;
import prismConvenienceClass.PrismTableModel;
import prismConvenienceClass.ToolBarWithBgImage;
import prismRoot.PrismMain;

public class Setting {
	private Panel_Query_Libraries query_panel;
	
	public Setting(JTable database_table, String conn_path) {
		query_panel = new Panel_Query_Libraries(database_table, conn_path);
	}
	
	public void show_popup() {
		// These codes make the popupPanel resizable --> the Big ScrollPane resizable --> JOptionPane resizable
		JScrollPane popup_scroll = new JScrollPane();
		popup_scroll.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				Window window = SwingUtilities.getWindowAncestor(popup_scroll);
				if (window instanceof Dialog) {
					Dialog dialog = (Dialog) window;
					if (!dialog.isResizable()) {
						dialog.setResizable(true);
					}
				}
			}
		});
		popup_scroll.setBorder(null);
		popup_scroll.setPreferredSize(new Dimension((int) (PrismMain.get_main().getWidth() * 0.8),
				(int) (PrismMain.get_main().getHeight() * 0.8)));
		popup_scroll.setViewportView(query_panel);

		String ExitOption[] = { "Exit" };
		int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(), popup_scroll, "Customize Queries - Note that Prism always restores 'SYSTEM LIBRARY' to default",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ExitOption, ExitOption[0]);
		if (response == 0) {
		}
	}

	public Panel_Query_Libraries get_query_panel() {
		return query_panel;
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	class Panel_Query_Libraries extends JLayeredPane {
		private JPanel radio_panel; 
		private ButtonGroup radio_group; 
		private JRadioButton[] radio_button; 
		
		private JScrollPane shared_scrollpane;
		private Table_Panel system_table_panel, user_table_panel;
		private List<Object>[] system_queries_list, user_queries_list;
		
		private JScrollPane database_table_scrollpane = new JScrollPane();
		
		public Panel_Query_Libraries(JTable database_table, String conn_path) {
			setLayout(new BorderLayout());
			// Some set up
			File file_system_sql_library = get_file_system_sql_library();
			File file_user_sql_library = get_file_user_sql_library();
			system_queries_list = get_queries_list(file_system_sql_library); 
			user_queries_list = get_queries_list(file_user_sql_library); 
			system_table_panel = new Table_Panel(database_table_scrollpane, database_table, conn_path, file_system_sql_library, system_queries_list);
			user_table_panel = new Table_Panel(database_table_scrollpane, database_table, conn_path, file_user_sql_library, user_queries_list);
			
			shared_scrollpane = new JScrollPane(user_table_panel);
			TitledBorder border = new TitledBorder("Query statements are based on SQLite   -   https://sqlite.org");
			border.setTitleJustification(TitledBorder.CENTER);
			shared_scrollpane.setBorder(border);
			shared_scrollpane.setMinimumSize(new Dimension(0, 300));
			shared_scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			shared_scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			
			// Radio Panel ------------------------------------------------------------------------------
			// Add 6 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
			radio_panel = new JPanel();
			radio_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));		
			radio_group = new ButtonGroup();
			
			radio_button  = new JRadioButton[2];
			radio_button[0]= new JRadioButton("SYSTEM LIBRARY");
			radio_button[1]= new JRadioButton("USER LIBRARY");
			radio_button[1].setSelected(true);
			for (int i = 0; i < radio_button.length; i++) {
					radio_group.add(radio_button[i]);
					radio_panel.add(radio_button[i]);
			}	
			
			radio_button[0].addActionListener(e -> {
				shared_scrollpane.setViewportView(system_table_panel);
			});
			
			radio_button[1].addActionListener(e -> {
				shared_scrollpane.setViewportView(user_table_panel);
			});
			// End of Radio Panel -----------------------------------------------------------------------
			
			
			// ToolBar Panel ----------------------------------------------------------------------------
			ToolBarWithBgImage helpToolBar = new ToolBarWithBgImage("Project Tools", JToolBar.HORIZONTAL, null);
			helpToolBar.setFloatable(false);	//to make a tool bar immovable
			helpToolBar.setRollover(true);	//to visually indicate tool bar buttons when the user passes over them with the cursor
			helpToolBar.setBorderPainted(false);
			
			// button Help
			JButton btnHelp = new JButton();
			btnHelp.setToolTipText("Help");
			btnHelp.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_help.png"));
			btnHelp.addActionListener(e -> {

			});
			
			// Add all buttons to flow_panel
			helpToolBar.add(Box.createGlue());	//Add glue for Right alignment
			helpToolBar.add(btnHelp);
			// End of ToolBar Panel -----------------------------------------------------------------------
				
			
			// Add all Grids to the Main Grid-----------------------------------------------------------------------
			JSplitPane split_pane = new JSplitPane();
			split_pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			split_pane.setDividerSize(3);
			split_pane.setDividerLocation((int) (PrismMain.get_main().getHeight() * 0.4));
			split_pane.setResizeWeight(0.2);
						
			JPanel upper_panel = new JPanel();
			upper_panel.setBorder(null);
			upper_panel.setLayout(new GridBagLayout());			
			
			JPanel lower_panel = new JPanel();
			lower_panel.setBorder(null);
			lower_panel.setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;

			// Add helpToolBar to the main Grid
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 0;
		    c.weighty = 0;
		    upper_panel.add(helpToolBar, c);				
			
			// Add the button_table_Panel to the main Grid
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
		    c.weighty = 1;
		    upper_panel.add(shared_scrollpane, c);				
		    		
//		    // Add static_identifiersScrollPanel to the main Grid
//			c.gridx = 0;
//			c.gridy = 2;
//			c.gridwidth = 1; 
//			c.gridheight = 1;
//			c.weightx = 1;
//		    c.weighty = 0.5;
//		    lower_panel.add(new JPanel(), c);

		    
		    border = new TitledBorder("Query Execution Result" + "   -   " + conn_path.substring(conn_path.lastIndexOf("/") + 1));
			border.setTitleJustification(TitledBorder.CENTER);
			database_table_scrollpane.setBorder(border);
			database_table_scrollpane.setPreferredSize(new Dimension(0, 0));
			
		    
			split_pane.setLeftComponent(upper_panel);
			split_pane.setRightComponent(database_table_scrollpane);
			
			super.add(radio_panel, BorderLayout.NORTH);
			super.add(split_pane, BorderLayout.CENTER);
		}
		
		//--------------------------------------------------------------------------------------------------------------------------
		public File get_file_system_sql_library() {
			// Read sql_library from the system
			File file_system_sql_library = null;
			try {
				file_system_sql_library = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "system_sql_library.txt");
				file_system_sql_library.deleteOnExit();

				InputStream initialStream = getClass().getResourceAsStream("/system_sql_library.txt");
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);

				OutputStream outStream = new FileOutputStream(file_system_sql_library);
				outStream.write(buffer);

				initialStream.close();
				outStream.close();
			} catch (FileNotFoundException e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			} catch (IOException e2) {
				System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
			} 
			return file_system_sql_library;
		}
		
		//--------------------------------------------------------------------------------------------------------------------------
		public File get_file_user_sql_library() {
			File file_user_sql_library = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "user_sql_library.txt");
			return file_user_sql_library;
		}
		
		//--------------------------------------------------------------------------------------------------------------------------
		public List<Object>[] get_queries_list(File sql_file) {
			List<Object>[] queries_list = new ArrayList[2];
			queries_list[0] = new ArrayList<Object>();
			queries_list[1] = new ArrayList<Object>();
			try {
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(sql_file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
				int totalRows = a.length;
				
				// Add all queries names and executed strings 
				int current_row_id = 0;
				boolean is_next_query_found = true;
				List<String> query_statement_list = new ArrayList<String>();
				
				while (current_row_id < totalRows) {
					if (is_next_query_found) {
						queries_list[0].add(a[current_row_id]);	// This is the name of the query
						is_next_query_found = false;
					} else {
						if (a[current_row_id].equals("----------")) {	// this is indicator when the next query is found
							queries_list[1].add(query_statement_list.toArray(new String[query_statement_list.size()]));	// This is the query string to be executed
							is_next_query_found = true;
							query_statement_list = new ArrayList<String>();
						} else {
							query_statement_list.add(a[current_row_id]);	// add string in the next row (line)
						}
					}
					current_row_id++;		// The 1st row ID would be row 0 and the last row ID would be totalRows - 1
				}
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
			return queries_list;
		}	
		
		//--------------------------------------------------------------------------------------------------------------------------
		public List<Object>[] get_system_queries_list() {
			return system_queries_list;
		}	
		
		//--------------------------------------------------------------------------------------------------------------------------
		public List<Object>[] get_user_queries_list() {
			return user_queries_list;
		}
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	class Table_Panel extends JPanel {
		private int rowCount, colCount;
		private String[] columnNames;
		private JTable table;
		private PrismTableModel model;
		private Object[][] data;
		
		private JScrollPane table_scrollpane;	
		private JScrollPane database_table_scrollpane;
		private JTable database_table;
		private String conn_path;
		
		//--------------------------------------------------------------------------------------------------------------------------
		public void create_table(List<Object>[] queries_list) {
			// Setup the table-------------------------------
			rowCount = queries_list[0].size();
			colCount = 2;
			data = new Object[rowCount][colCount];
			for (int i = 0; i < rowCount; i++) {
				data[i][0] = queries_list[0].get(i);
				data[i][1] = queries_list[1].get(i);
			}
			columnNames = new String[] {"query_name", "query_statement"};
			
			
			// Create a table------------------------------		
			model = new PrismTableModel(rowCount, colCount, data, columnNames) {
				@Override
				public Class getColumnClass(int c) {
					if (c == 1) return String[].class;      //column 1 accepts only String[]
					else return String.class;				
				}
				
				@Override
				public boolean isCellEditable(int row, int col) {
					return true;
				}
				
				@Override
				public void setValueAt(Object value, int row, int col) {
					data[row][col] = value;
				}
			};
			
			
			
			table = new JTable(model) {
//				@Override			//These override is to make the width of the cell fit all contents of the cell
//				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//					// For the cells in table								
//					Component component = super.prepareRenderer(renderer, row, column);
//					int rendererWidth = component.getPreferredSize().width;
//					TableColumn tableColumn = getColumnModel().getColumn(column);
//					int maxWidth = Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth());
//					
//					// For the column names
//					TableCellRenderer renderer2 = table.getTableHeader().getDefaultRenderer();	
//					Component component2 = renderer2.getTableCellRendererComponent(table,
//				            tableColumn.getHeaderValue(), false, false, -1, column);
//					maxWidth = Math.max(maxWidth, component2.getPreferredSize().width);
//					
//					tableColumn.setPreferredWidth(maxWidth);
//					return component;
//				}
			};

			
			

			class TextAreaEditor extends DefaultCellEditor {
				protected JScrollPane scrollpane;
				protected JTextArea textarea;

				public TextAreaEditor(String border_name) {
					super(new JCheckBox());
					scrollpane = new JScrollPane();
					scrollpane.setRequestFocusEnabled(false);
					textarea = new JTextArea();
					textarea.setLineWrap(true);
					textarea.setWrapStyleWord(true);
					scrollpane.setBorder(new TitledBorder(border_name));
					scrollpane.getViewport().add(textarea);
				}

				public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
					if (value instanceof String[]) {
						String[] temp = (String[]) value;
						textarea.setText(String.join("\n", temp));
					} else {
						textarea.setText((String) value);
					}
					textarea.setCaretPosition(0);
					scrollpane.getVerticalScrollBar().setValue(0);
					return scrollpane;
				}

				public Object getCellEditorValue() {
					return textarea.getText();
				}
				
				public boolean isCellEditable(EventObject e) {
					if (e instanceof KeyEvent) {
						return false;
					}
					return super.isCellEditable(e);
				}
			}
			
			
			TextAreaEditor texteditor = new TextAreaEditor(""); 
			texteditor.textarea.addKeyListener(new KeyAdapter(){ // Disable enter new line if column is the 1st column
	            @Override
	            public void keyPressed(KeyEvent e) {
	            	if (e.getKeyChar() == e.VK_ENTER) e.consume();
	            }
	        });
			texteditor.setClickCountToStart(2);
		    table.getColumn("query_name").setCellEditor(texteditor);
			
		    texteditor = new TextAreaEditor(""); 
		    texteditor.setClickCountToStart(2);
		    table.getColumn("query_statement").setCellEditor(texteditor);
		    for (int i = 0; i < rowCount; i++) {		// These below 4 lines trigger the table to show all the query statements in column 1's text area-----------
				table.editCellAt(i, 1);
			}
		    table.setRowHeight(20);
		    
		    
	        // Set up Type for each column 2
//			class comboBox_constraint_type extends JComboBox {	
//				public comboBox_constraint_type() {
//					addItem("SOFT");
//					addItem("HARD");
//					addItem("FREE");
//					setSelectedIndex(0);
//				}
//			}
//			table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new comboBox_constraint_type()));
						
			// Hide columns
//			TableColumnsHandle table_handle = new TableColumnsHandle(table);
//			table_handle.setColumnVisible("parameter_index", false);
			
//			table.setAutoResizeMode(0);		// 0 = JTable.AUTO_RESIZE_OFF
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
			table.getTableHeader().setReorderingAllowed(false);		//Disable columns move
			table.setPreferredScrollableViewportSize(new Dimension(200, 100));
//			table.setTableHeader(null);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);	// Set width of 1st Column
			table.getColumnModel().getColumn(1).setPreferredWidth(400);	// Set width of 2nd Column
		}
		
		
		public Table_Panel(JScrollPane database_table_scrollpane, JTable database_table, String conn_path, File file_sql_library, List<Object>[] queries_list) {
			this.database_table_scrollpane = database_table_scrollpane;
			this.database_table = database_table;
			this.conn_path = conn_path;
			
			// 4th Grid ------------------------------------------------------------------------------		// Buttons	
			// 4th Grid -----------------------------------------------------------------------------
			// Add all buttons to a Panel----------------------------------
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(0, 5, 10, 10); // padding top 0, left 5, bottom 10, right 10
			
			
			JButton btn_NewSingle = new JButton();
			btn_NewSingle.setFont(new Font(null, Font.BOLD, 14));
//			btn_NewSingle.setText("NEW SINGLE");
			btn_NewSingle.setToolTipText("New");
			btn_NewSingle.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_add.png"));					
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0;
			c.weighty = 0;
			add(btn_NewSingle, c);

			
			JSpinner spin_move_rows = new JSpinner (new SpinnerNumberModel(1, 0, 2, 1));
			spin_move_rows.setToolTipText("Move");
			JFormattedTextField SpinnerText = ((DefaultEditor) spin_move_rows.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			SpinnerText.setEditable(false);
			SpinnerText.setFocusable(false);
		    spin_move_rows.setEnabled(false);
		    c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0;
			c.weighty = 0;
			add(spin_move_rows, c);
			
			
			JButton btn_Delete = new JButton();
			btn_Delete.setFont(new Font(null, Font.BOLD, 14));
//			btn_Delete.setText("DELETE");
			btn_Delete.setToolTipText("Delete");
			btn_Delete.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_erase.png"));
			btn_Delete.setEnabled(false);					
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 0;
			c.weighty = 0;
			add(btn_Delete, c);
			
			
			JToggleButton btn_Sort = new JToggleButton();
			btn_Sort.setSelected(false);
			btn_Sort.setFocusPainted(false);
			btn_Sort.setFont(new Font(null, Font.BOLD, 12));
			btn_Sort.setText("OFF");
			btn_Sort.setToolTipText("Sorter mode: 'ON' click columns header to sort rows. 'OFF' retrieve original rows position");
			btn_Sort.setIcon(IconHandle.get_scaledImageIcon(16, 16, "icon_sort.png"));					
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 0;
			c.weighty = 0;
			add(btn_Sort, c);
			
			
			JButton btn_Save = new JButton() {
				public Point getToolTipLocation(MouseEvent event) {
					return new Point(getWidth() - 10, 5);
				}
			};
			btn_Save.setFont(new Font(null, Font.BOLD, 14));
//			btn_Save.setText("Save");
			btn_Save.setToolTipText("Save");
			btn_Save.setIcon(IconHandle.get_scaledImageIcon(20, 20, "icon_save.png"));
			btn_Save.setRolloverIcon(IconHandle.get_scaledImageIcon(27, 27, "icon_save.png"));
			btn_Save.setContentAreaFilled(false);
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 0;
			c.weighty = 0;
			add(btn_Save, c);
			
			
			JButton btn_ExecuteQuery = new JButton() {
				public Point getToolTipLocation(MouseEvent event) {
					return new Point(getWidth() - 10, 5);
				}
			};
			btn_ExecuteQuery.setFont(new Font(null, Font.BOLD, 14));
//			btn_ExecuteQuery.setText("Execute");
			btn_ExecuteQuery.setToolTipText("Execute Query");
			btn_ExecuteQuery.setIcon(IconHandle.get_scaledImageIcon(25, 25, "icon_calculator.png"));
			btn_ExecuteQuery.setRolloverIcon(IconHandle.get_scaledImageIcon(35, 35, "icon_calculator.png"));
			btn_ExecuteQuery.setContentAreaFilled(false);
			btn_ExecuteQuery.setEnabled(false);
			c.gridx = 0;
			c.gridy = 5;
			c.weightx = 0;
			c.weighty = 0;
			add(btn_ExecuteQuery, c);
			
			
			c.insets = new Insets(0, 0, 0, 0); // No padding
			// Add Empty Label to make all buttons on top not middle
			c.gridx = 0;
			c.gridy = 7;
			c.weightx = 0;
			c.weighty = 1;
			add(new JLabel(), c);
			
			// Add table9			
			create_table(queries_list);
			table_scrollpane = new JScrollPane(table);
			// When add new until bar almost appear, then click on text area --? scroll bar appear and then we could not enter cell editor
			// Always show scroll bar would solve the problem. THis is not a perfect solution but it just works
			table_scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.gridheight = 8;
			add(table_scrollpane, c);
			// End of 4th Grid -----------------------------------------------------------------------
			// End of 4th Grid -----------------------------------------------------------------------	
			
			
			
			// Add Listeners for table & buttons----------------------------------------------------------
			// Add Listeners for table & buttons----------------------------------------------------------
			
			// table
			table.addMouseListener(new MouseAdapter() { // Add listener to DatabaseTree
				
				public void mousePressed(MouseEvent e) {
					if (table.isEditing()) {
						table.getCellEditor().stopCellEditing();
					}
					
					table.setRowHeight(table.getRowHeight());
					int[] selectedRow = table.getSelectedRows();
					if (selectedRow.length == 1 && e.getClickCount() == 2) {
						int currentRow = selectedRow[0];
//						currentRow = table.convertRowIndexToModel(currentRow);	// Convert row index because "Sort" causes problems, not sure why we do not need this
						table.setRowHeight(currentRow, 200);
						table.editCellAt(currentRow, table.getSelectedColumn());
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int[] selectedRow = table.getSelectedRows();
					
					if (selectedRow.length >= 1 && table.isEnabled()) {		// Enable Delete  when: >=1 row is selected, table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}		
					
					if (selectedRow.length == 1) {
						btn_ExecuteQuery.setEnabled(true);
					} else { // Disable Edit
						btn_ExecuteQuery.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && btn_Sort.getText().equals("OFF")) {	// Enable Spinner when: >=1 row is selected and Sorter is off
						spin_move_rows.setEnabled(true);
					} else {		// Disable Spinner
						spin_move_rows.setEnabled(false);
					}
				}
			});
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	int[] selectedRow = table.getSelectedRows();
					
					if (selectedRow.length >= 1 && table.isEnabled()) {		// Enable Delete  when: >=1 row is selected,table is enable (often after Edit button finished its task)
						btn_Delete.setEnabled(true);
					} else {		// Disable Delete
						btn_Delete.setEnabled(false);
					}	
					
					if (selectedRow.length == 1) {
						btn_ExecuteQuery.setEnabled(true);
					} else { // Disable Edit
						btn_ExecuteQuery.setEnabled(false);
					}
					
					if (selectedRow.length >= 1 && btn_Sort.getText().equals("OFF")) {	// Enable Spinner when: >=1 row is selected and Sorter is off
						spin_move_rows.setEnabled(true);
					} else {		// Disable Spinner
						spin_move_rows.setEnabled(false);
					}
		        }
		    });
			
			

			// New single
			btn_NewSingle.addActionListener(e -> {	
				if (table.isEditing()) {
					table.getCellEditor().stopCellEditing();
				}	
				
				// Add 1 row
				rowCount++;
				data = new Object[rowCount][colCount];
				for (int i = 0; i < rowCount - 1; i++) {
					for (int j = 0; j < colCount; j++) {
						data[i][j] = model.getValueAt(i, j);
					}	
				}
				
				
//				data[rowCount - 1][0] = "test";
				model.updateTableModelPrism(rowCount, colCount, data, columnNames);
				model.fireTableDataChanged();
				
				// Convert the new Row to model view and then select it 
				int newRow = table.convertRowIndexToView(rowCount - 1);
				table.setRowSelectionInterval(newRow, newRow);
				table.scrollRectToVisible(new Rectangle(table.getCellRect(newRow, 0, true)));
			});
			
			
			// Spinner
		    spin_move_rows.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
					int up_or_down = (int) spin_move_rows.getValue() - 1;										
					spin_move_rows.setValue((int) 1);	// Reset spinner value to 1
										
					if (up_or_down == 1) {	// move up
						// Cancel editing before moving conditions up or down
						if (table.isEditing()) {
							table.getCellEditor().stopCellEditing();
						}	
						
						// Get selected rows
						int[] selectedRow = table.getSelectedRows();		// No need to convert row index because we never allow Sort when moving rows
						List<Integer> selectedRowList = new ArrayList<Integer>() {{ for (int i : selectedRow) add(i);}};	// Convert array to list
						
						if (selectedRowList.size() >=1 && selectedRowList.get(0) > 0) {	// If there is at least 1 row selected & the first row is not selected
							for (int i = 0; i < rowCount; i++) {
								if (selectedRowList.contains(i)) {		
									for (int j = 0; j < colCount; j++) {
										Object temp = data[i - 1][j];
										data[i - 1][j] = data[i][j];
										data[i][j] = temp;
									}
								}
							}							
							model.fireTableDataChanged();	// Update the changes and select the currently selected conditions
							for (int i: selectedRow) {
								table.addRowSelectionInterval(i - 1, i - 1);
							}
						}
					}
										
					if (up_or_down == -1) {	// move down						
						if (table.isEditing()) {
							table.getCellEditor().stopCellEditing();	// stop editing before moving conditions up or down
						}	
						
						// Get selected rows
						int[] selectedRow = table.getSelectedRows();		// No need to convert row index because we never allow Sort when moving rows
						List<Integer> selectedRowList = new ArrayList<Integer>() {{ for (int i : selectedRow) add(i);}};	// Convert array to list
						
						if (selectedRowList.size() >=1 && selectedRowList.get(selectedRowList.size() - 1) < rowCount - 1) {	// If ...
							for (int i = rowCount - 1; i >= 0; i--) {
								if (selectedRowList.contains(i)) {		
									for (int j = 0; j < colCount; j++) {
										Object temp = data[i + 1][j];
										data[i + 1][j] = data[i][j];
										data[i][j] = temp;
									}
								}
							}						
							model.fireTableDataChanged();	// Update the changes and select the currently selected conditions
							for (int i: selectedRow) {
								table.addRowSelectionInterval(i + 1, i + 1);
							}	
						}						
					}
					
					// Scroll to the first row of the current selected rows
					table.scrollRectToVisible(new Rectangle(table.getCellRect(table.convertRowIndexToView(table.getSelectedRow()), 0, true)));	
		        }
		    });
		    
				
			// Delete
			btn_Delete.addActionListener(e -> {
				if (table.isEditing()) {
					table.getCellEditor().stopCellEditing();
				}				
				
				// Get selected rows
				int[] selectedRow = table.getSelectedRows();	
				for (int i = 0; i < selectedRow.length; i++) {
					selectedRow[i] = table.convertRowIndexToModel(selectedRow[i]);	///Convert row index because "Sort" causes problems
				}
				
				// Create a list of selected row indexes
				List<Integer> selected_Index = new ArrayList<Integer>();				
				for (int i: selectedRow) {
					selected_Index.add(i);
				}	
				
				// Get values to the new data9
				data = new Object[rowCount - selectedRow.length][colCount];
				int newRow =0;
				for (int ii = 0; ii < rowCount; ii++) {
					if (!selected_Index.contains(ii)) {			//If row not in the list then add to data9 row
						for (int jj = 0; jj < colCount; jj++) {
							data[newRow][jj] = model.getValueAt(ii, jj);
						}
						newRow++;
					}
				}
				// Pass back the info to table model
				rowCount = rowCount - selectedRow.length;
				model.updateTableModelPrism(rowCount, colCount, data, columnNames);
				model.fireTableDataChanged();	
			});
					
			
			// Sort
			btn_Sort.addActionListener(e -> {
				if (table.isEditing()) {
					table.getCellEditor().stopCellEditing();
				}
				
				if (btn_Sort.getText().equals("ON")) {
					table.setRowSorter(null);
					btn_Sort.setText("OFF");
					btn_Sort.repaint();
				} else if (btn_Sort.getText().equals("OFF")) {
					TableRowSorter<PrismTableModel> sorter = new TableRowSorter<PrismTableModel>(model); // Add sorter
					table.setRowSorter(sorter);
					btn_Sort.setText("ON");
					btn_Sort.repaint();
				}							
			});
			
			
			// Save
			btn_Save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table.isEditing()) {
						table.getCellEditor().stopCellEditing();
					}
					
					if (file_sql_library.exists()) {
						file_sql_library.delete();		// Delete the old file before writing new contents
					}
					File new_file = new File(file_sql_library.getAbsolutePath());
					
					if (data != null && data.length > 0) {
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(new_file))) {
							for (int i = 0; i < data.length; i++) {
								if (i > 0) fileOut.newLine();
								if (data[i][0] != null) fileOut.write((String) data[i][0]);
								
								if (data[i][1] != null) {
									String aaggregated_statement = (String) data[i][1];
									String[] temp = aaggregated_statement.split("\\n");
									for (int j = 0; j < temp.length; j++) {
										fileOut.newLine();
										fileOut.write(temp[j]);
									}
								}
								
								fileOut.newLine();
								fileOut.write("----------");	
							}
							fileOut.close();
						} catch (IOException e) {
							System.err.println(e.getClass().getName() + ": " + e.getMessage());
						} 
					}
				}
			});	

			
			// Execute Query
			btn_ExecuteQuery.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {	
					if (table.isEditing()) {
						table.getCellEditor().stopCellEditing();
						table.setRowHeight(table.getRowHeight());
					}
					
					if (table.isEnabled()) {				
						int selectedRow = table.getSelectedRow();
						selectedRow = table.convertRowIndexToModel(selectedRow);		// Convert row index because "Sort" causes problems	
						doQuery((String) data[selectedRow][0], (String) data[selectedRow][1]);
					}
				}
			});	
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------
			// End of Listeners for table9 & buttons -----------------------------------------------------------------------	
		}
		
		
		public void doQuery(String query_name, String query) {		//Note a statement not starting with SELECT is not a Query
			// Set title name of for the database_table_scrollpane
			((TitledBorder) database_table_scrollpane.getBorder()).setTitle(query_name + "   -   " + conn_path.substring(conn_path.lastIndexOf("/") + 1));
			database_table_scrollpane.revalidate();
			database_table_scrollpane.repaint();
			
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
	}
	
	
	
	
}
