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

package prism_project.solve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultCaret;

import prism_convenience.IconHandle;
import prism_convenience.PrismGridBagLayoutHandle;
import prism_convenience.PrismTableModel;

public class Panel_Solve extends JLayeredPane implements ActionListener {
	private JSplitPane splitpane;
	private JTextArea displayTextArea;
	private JButton button_solve;
	private JScrollPane scrollpane_table, scrollpane_textarea;
	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private PrismTableModel model;
	private Object[][] data;
	
	private int objective_option = 0;
	private boolean solvingstatus;
	Solve_Iterations solve_iterations;
	
	public Panel_Solve(File[] runsList) {
		super.setLayout(new BorderLayout(0, 0));
		
		// Set up a JScrollPane containing a table--------------------------------------------------------------------------------
		rowCount = runsList.length;
		colCount = 5;
		data = new Object[rowCount][colCount];
		columnNames = new String[] { "Model", "Disturbances", "From Iteration", "To Iteration", "Status" };
		
		// Populate the data matrix
		for (int row = 0; row < rowCount; row++) {
			data[row][0] = runsList[row].getName();
			data[row][1] = "mean";
			data[row][2] = "restart";
			data[row][3] = 0;
			data[row][4] = "waiting";
		}			
		
		// Create a table
        model = new PrismTableModel(rowCount, colCount, data, columnNames) {
        	@Override
			public Class getColumnClass(int col) {
				return (col == 3) ? Integer.class : String.class;
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				return (col >= 1 && col <= 4) ? true : false;
			}
			
			@Override
			public void setValueAt(Object value, int row, int col) {
				data[row][col] = value;
			}
        };
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
				
				// Apply
				if (column == 0) {
					tableColumn.setMinWidth(80);
				} else {
					tableColumn.setPreferredWidth(maxWidth);
				}
				return component;
			}
        	
        	@Override	// Implement table cell tool tips           
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int row = rowAtPoint(p);
				int column = columnAtPoint(p);
				String tip = null;
				if (row >= 0 && getValueAt(row, column) != null) {
					if (table.getColumnName(column).equals("Model")) tip = getValueAt(row, column).toString();
					if (table.getColumnName(column).equals("From Iteration") && getValueAt(row, column).toString().equals("restart")) tip = "solve from iteration 0";
					if (table.getColumnName(column).equals("From Iteration") && getValueAt(row, column).toString().equals("continue")) tip = "solve from last solved iteration";
				}
				return tip;
			}	
        };
        
        class ToolTipComboBoxRenderer extends BasicComboBoxRenderer {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (String.valueOf(value).equals("restart")) {
					list.setToolTipText("from iteration 0");
				} else if (String.valueOf(value).equals("continue")) {
					list.setToolTipText("from last solved iteration");
				}
				if (isSelected) {
					c.setBackground(UIManager.getColor("Tree.selectionBackground"));	// this works to have the desired color
					c.setForeground(UIManager.getColor("Tree.selectionForeground"));
				} else {
					c.setBackground(Color.WHITE);
					c.setForeground(UIManager.getColor("Tree.foreground"));
				}
				return c;
			}
		}
        class Combo_Iteration extends JComboBox {	
			public Combo_Iteration() {
				for (int i = 0; i <= 99; i++) {addItem(i);}
				setSelectedIndex(0);
			}
		}
        class Combo_Method extends JComboBox {	
			public Combo_Method() {
				ToolTipComboBoxRenderer r = new ToolTipComboBoxRenderer();
				setRenderer(r);
				addItem("restart");
				addItem("continue");
				setSelectedIndex(1);
				
			}
		}
        class Combo_Disturbances_Assumption extends JComboBox {	
			public Combo_Disturbances_Assumption() {
				addItem("random");
				addItem("mean");
				setSelectedIndex(1);
			}
		}
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new Combo_Disturbances_Assumption())); 
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new Combo_Method())); 
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new Combo_Iteration())); 
        
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {			
				setHorizontalAlignment(JLabel.LEFT);			
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        table.getColumnModel().getColumn(3).setCellRenderer(r);
//      DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
//      renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//      table.setPreferredScrollableViewportSize(new Dimension(100, 150));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);    
        table.getTableHeader().setReorderingAllowed(false);		// Disable columns move
		scrollpane_table = new JScrollPane(table);

       
		
        // Set up a JPanel containing radio buttons----------------------------------------------------------------------------
		JPanel panel_radio = new JPanel(new GridBagLayout());
		panel_radio.setBackground(Color.WHITE);
		TitledBorder border = new TitledBorder("Objective");
		border.setTitleJustification(TitledBorder.CENTER);
		panel_radio.setBorder(border);
		panel_radio.setPreferredSize((new Dimension(100, 100)));
		
		GridBagConstraints c = new GridBagConstraints();
		ButtonGroup radioGroup = new ButtonGroup();		
		JRadioButton[] radioButton = new JRadioButton[2];
		radioButton[0] = new JRadioButton("Minimize the sum of penalties");
		radioButton[1] = new JRadioButton("Minimize the maximum penalty");
		radioButton[1].setEnabled(false);
		radioButton[0].setSelected(true);
		
		panel_radio.add(radioButton[0], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 0, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				5, 0, 0, 0));	// insets top, left, bottom, right	
		panel_radio.add(radioButton[1], PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 1, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				5, 0, 5, 0));	// insets top, left, bottom, right	
		
		for (int i = 0; i < 2; i++) {
			radioGroup.add(radioButton[i]);
			int processing_radio = i;
			radioButton[i].addActionListener(e -> {	
				objective_option = processing_radio;		// 0 - Solve Fast     1 - Solve Large
			});
		}

				
		
        // Set up a JScrollPane containing a button----------------------------------------------------------------------------------
		button_solve = new JButton(IconHandle.get_scaledImageIcon(128, 128, "icon_main_off.png"));
		button_solve.setDisabledIcon(IconHandle.get_scaledImageIcon(128, 128, "icon_main.png"));
//		button_solve.setDisabledIcon(IconHandle.get_scaledImageIcon_replicate(128, 128, "main_animation.gif"));
		button_solve.setBackground(Color.WHITE);
		button_solve.setHorizontalTextPosition(JButton.CENTER);
		button_solve.setVerticalTextPosition(JButton.BOTTOM);
		button_solve.setFont(new Font(null, Font.BOLD, 15));
		button_solve.setText("CLICK TO SOLVE");
		button_solve.setRequestFocusEnabled(false);
		button_solve.addActionListener(e -> {
			if (solvingstatus == false) {
				// Solve runs when clicked
				// Open 2 new parallel threads: 1 for running CPLEX, 1 for redirecting console to displayTextArea
				Thread thread2 = new Thread() {
					public void run() {
						try {
							table.setEnabled(false);		// disable editing
							//redirect console to JTextArea
							PipedOutputStream pOut = new PipedOutputStream();
							System.setOut(new PrintStream(pOut));
							System.setErr(new PrintStream(pOut));
							PipedInputStream pIn = new PipedInputStream(pOut);
							BufferedReader reader = new BufferedReader(new InputStreamReader(pIn));

							while (solvingstatus == true) {
								try {
									String line = reader.readLine();
									if (line != null) {
										displayTextArea.append(line + "\n");	// Write line to displayTextArea
									}
							    } catch (IOException ex) {
							    	System.err.println("Panel Solve Runs - Thread 2 error - " + ex.getClass().getName() + ": " + ex.getMessage());
							    }
							}
							displayTextArea.append("--------------------------------------------------------------" + "\n");
							displayTextArea.append("--------------------------------------------------------------" + "\n");
							displayTextArea.append("SOLVING PROCESS IS COMPLETED" + "\n");
							displayTextArea.append("--------------------------------------------------------------" + "\n");
							displayTextArea.append("--------------------------------------------------------------" + "\n");
							displayTextArea.append("\n" + "\n" + "\n");
							
							reader.close();
							pIn.close();
							pOut.close();
							table.setEnabled(true);		// enable editing
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - Thread 2 error - " + e.getClass().getName() + ": " + e.getMessage());
						}
					}
				};
								
				Thread thread1 = new Thread() {
					public void run() {
						for (int i = 0; i < radioButton.length; i++) {
							radioButton[i].setEnabled(false);
						}
											
						for (int row = 0; row < rowCount; row++) {
							button_solve.setText("Solving: " + runsList[row].getName());
							if (objective_option == 0) {
								solve_iterations = new Solve_Iterations(runsList[row], model, data, row);
								solve_iterations = null;
							} else {
							}
						}

						try {
							sleep(1000);			//sleep 1 second to so thread 2 can still print out report
							thread2.interrupt();
						} catch (InterruptedException e) {
							System.err.println("Panel Solve Runs - Thread 1 sleep error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						
						solvingstatus = false;
						button_solve.setText("CLICK TO SOLVE");
						button_solve.setIcon(IconHandle.get_scaledImageIcon(128, 128, "icon_main_off.png"));
						for (int i = 0; i < radioButton.length; i++) {
							radioButton[i].setEnabled(true);
						}
						radioButton[1].setEnabled(false);
					}
				};
								
				// Clear table info
				for (int row = 0; row < rowCount; row++) {
					for (int col = 0; col < colCount; col++) {
						if (col > 3) {
							data[row][col] = null;
							model.fireTableDataChanged();
						}
					}
				}				
				
				solvingstatus = true;
				button_solve.setIcon(IconHandle.get_scaledImageIcon_replicate(128, 128, "main_animation.gif"));
				thread2.start();
				thread1.start();		// Note: Pipe broken due to disconnects before receiving responses. (safe Exception)		
			}
		});	
		

		
		JPanel panel_nothing = new JPanel();	// Works like a padding top
		panel_nothing.setBackground(Color.WHITE);
		panel_nothing.setPreferredSize((new Dimension(100, 10)));
		
		
		// Add all to a panel----------------------------------------------------------------------------
		JPanel combine_panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		combine_panel.add(panel_nothing, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 0, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));	// insets top, left, bottom, right	
		combine_panel.add(panel_radio, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 1, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 0, 0, 0));	// insets top, left, bottom, right	
		combine_panel.add(scrollpane_table, PrismGridBagLayoutHandle.get_c(c, "BOTH", 
				0, 2, 1, 1, 1, 1, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 1, 0, 1));	// insets top, left, bottom, right	
		combine_panel.add(button_solve, PrismGridBagLayoutHandle.get_c(c, "HORIZONTAL", 
				0, 3, 1, 1, 1, 0, 	// gridx, gridy, gridwidth, gridheight, weightx, weighty
				0, 1, 0, 1));	// insets top, left, bottom, right	
					
								
		
		// Add text area to a scroll panel -------------------------------------------------------------------------------
		displayTextArea = new JTextArea();
		displayTextArea.setBackground(Color.BLACK); 
		displayTextArea.setForeground(Color.WHITE);
		displayTextArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret) displayTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		scrollpane_textarea = new JScrollPane(displayTextArea);
		
		
		
        // Main split panel------------------------------------------------------------------------------
		splitpane = new JSplitPane();
//		splitPanel.setResizeWeight(0.5);
		splitpane.setOneTouchExpandable(true);
		splitpane.setDividerLocation(450);
//		splitPanel.setDividerSize(5);
//		splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));		
		splitpane.setLeftComponent(combine_panel);
		splitpane.setRightComponent(scrollpane_textarea);			
		
		// Add all components to Panel_SolveRun------------------------------------------------------------
		super.add(splitpane, BorderLayout.CENTER);
		super.setOpaque(false);			
	}
	
	// Listener for this class----------------------------------------------------------------------
	public void actionPerformed(ActionEvent e) {
	}
}
