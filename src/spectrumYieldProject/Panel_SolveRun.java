package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import ilog.concert.IloException;
import ilog.concert.IloLPMatrix;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;

public class Panel_SolveRun extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel, splitPanel2;
	private JTextArea displayTextArea;
	private JButton runStatButton;
	private boolean solvingstatus;
	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private MyTableModel model;
	private Object[][] data;
	
	private File[] listOfEditRuns = null;
	private JScrollPane scrollPane_Left, scrollPane_Right;
	
	private File[] problemFile, solutionFile;
	
	public Panel_SolveRun() {
		super.setLayout(new BorderLayout(0, 0));
		// Return the selected Runs
		listOfEditRuns = Panel_YieldProject.getSelectedRuns();
		

		//Setup the table--------------------------------------------------------------------------------
		rowCount = listOfEditRuns.length;
		colCount = 5;
		data = new Object[rowCount][colCount];
        columnNames= new String[] {"Model" , "Validation", "Variables", "Constraints", "Status"};
		
		// Populate the data matrix
		for (int row = 0; row < rowCount; row++) {
			data[row][0] = listOfEditRuns[row].getName();
			data[row][4] = "waiting";
		}	
		
		//Create a table
        model = new MyTableModel();
        table = new JTable(model);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);	//Set width of Column 'Validation'
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
        
        //Setup the TextArea--------------------------------------------------------------------------------
		displayTextArea = new JTextArea();
		displayTextArea.setBackground(Color.BLACK); 
		displayTextArea.setForeground(Color.WHITE);
        
        
        //Create splitPanel------------------------------------------------------------------------------
		splitPanel = new JSplitPane();
//		splitPanel.setResizeWeight(0.5);
		splitPanel.setOneTouchExpandable(true);
//		splitPanel.setDividerLocation(200);
//		splitPanel.setDividerSize(5);
//		splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Left split panel--------------------------------------------------------------------------------
		scrollPane_Left = new JScrollPane();
		scrollPane_Left.setViewportView(table);			
		splitPanel2= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPanel2.setResizeWeight(1);
		splitPanel2.setDividerSize(0);
		splitPanel2.setLeftComponent(scrollPane_Left);
		
		// Animated test -----------------
		try {
//			https://media.giphy.com/media/TFhobYtkih62k/giphy.gif
//			http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif
//			http://orig11.deviantart.net/b288/f/2009/260/9/5/pikachu_vector_by_elfaceitoso.png
//			JLabel imageLabel = new JLabel(new ImageIcon(new URL("http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif")));
//			JScrollPane scrollPane_Left2 = new JScrollPane();
//			scrollPane_Left2.setViewportView(imageLabel);
			ImageIcon icon = new ImageIcon(new URL("http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif"));
//			ImageIcon icon2 = new ImageIcon("C:\\pikachuHat.jpg");
			ImageIcon icon2 = new ImageIcon(new URL("http://orig11.deviantart.net/b288/f/2009/260/9/5/pikachu_vector_by_elfaceitoso.png"));		
			
			Image scaleImage = icon.getImage().getScaledInstance(200, 150,Image.SCALE_DEFAULT);
			Image scaleImage2 = icon2.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
		
			runStatButton = new JButton(new ImageIcon(scaleImage2));
			runStatButton.setDisabledIcon(new ImageIcon(scaleImage));
			runStatButton.setHorizontalTextPosition(JButton.CENTER);
			runStatButton.setVerticalTextPosition(JButton.TOP);
			runStatButton.setFont(new Font(null, Font.BOLD, 15));
			runStatButton.setText("CLICK ME TO GET SOLUTIONS");
			runStatButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (solvingstatus==false) {
						//Solve runs when clicked
						problemFile = new File[rowCount];
						solutionFile = new File[rowCount];
						
						// Open 2 new parallel threads: 1 for running CPLEX, 1 for redirecting console to displayTextArea
						Thread thread2 = new Thread() {
							public void run() {
								try {
									//redirect console to JTextArea
									PipedOutputStream pOut = new PipedOutputStream();
									System.setOut(new PrintStream(pOut));
									PipedInputStream pIn = new PipedInputStream(pOut);
									BufferedReader reader = new BufferedReader(new InputStreamReader(pIn));
									
									while(solvingstatus==true) {
									    try {
									        String line = reader.readLine();
									        if(line != null) {
									            // Write line to displayTextArea
									        	displayTextArea.append(line + "\n");
									        }
									    } catch (IOException ex) {
									    	System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
									    }
									}
									reader.close();
									pIn.close();
									pOut.close();
								} catch (IOException e) {
									System.err.println(e.getClass().getName() + ": " + e.getMessage());
								}
							}
						};
						
						
						Thread thread1 = new Thread() {
							public void run() {
								for (int row = 0; row < rowCount; row++) {
									runStatButton.setText("searching for " + listOfEditRuns[row].getName() + " solution");
									SolveProblem(row, listOfEditRuns[row]);
								}
								solvingstatus=false;
								runStatButton.setText("CLICK ME TO GET SOLUTIONS");
								runStatButton.setEnabled(true);
							}
						};
						
						
						runStatButton.setEnabled(false);
						solvingstatus=true;
						thread1.start();
						thread2.start();		//Note: Pipe broken due to disconnects before receiving responses. (safe Exception)		
					}
				}
			});
			splitPanel2.setRightComponent(runStatButton);
		} catch (MalformedURLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
        // Animated test-----------------	
		splitPanel.setLeftComponent(splitPanel2);
								
		
		// Right split panel-------------------------------------------------------------------------------
		scrollPane_Right = new JScrollPane();
		scrollPane_Right.setViewportView(displayTextArea);
		splitPanel.setRightComponent(scrollPane_Right);			
		
		
		// Add all components to Panel_SolveRun------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);	
		
	} // end Panel_SolveRun()

	// Listener for----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
 
    }

	class MyTableModel extends AbstractTableModel {
   	 
		public MyTableModel() {

		  }

		public int getColumnCount() {
			return colCount;
		}

		public int getRowCount() {
			return rowCount;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public Class getColumnClass(int c) {
			return (getValueAt(0, c) == null ? Object.class : getValueAt(0, c).getClass());
		}

		// Don't need to implement this method unless your table's editable.
		public boolean isCellEditable(int row, int col) {
			return false;	// all cells are not allowed for editing
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
			fireTableDataChanged();
			repaint();
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	//Solve each run
	public void SolveProblem(int row, File runFolder) {
		
		try {
			problemFile[row] = new File(runFolder.getAbsolutePath() + "/Problem.lp");
			solutionFile[row] = new File(runFolder.getAbsolutePath() + "/Solution.lp");
		
			//Read input files to retrieve values later
			ReadRunInputs read= new ReadRunInputs();
			read.readGeneralInputs(new File(runFolder.getAbsolutePath() + "/GeneralInputs.txt"));
			read.readManagementOptions(new File(runFolder.getAbsolutePath() + "/ManagementOptions.txt"));
			read.readCoverTypeConversions(new File(runFolder.getAbsolutePath() + "/CoverTypeConversions.txt"));
			

			List<String> modeled_strata, modeled_strata_withoutSizeClass, modeled_strata_withoutSizeClassandCoverType = new ArrayList<String>();
			modeled_strata = read.get_modeled_strata();
			modeled_strata_withoutSizeClass = read.get_modeled_strata_withoutSizeClass();
			modeled_strata_withoutSizeClassandCoverType = read.get_modeled_strata_withoutSizeClassandCoverType();

			List<String> coverTypeConversions = new ArrayList<String>();
			coverTypeConversions = read.getCoverTypeConversions();
			
			
			

			// Set up problem-------------------------------------------------
			List<String> layer1 = new ArrayList<String>();
			layer1.add("B");
			layer1.add("U");
			layer1.add("S");
			layer1.add("K");
			layer1.add("R");
			layer1.add("C");
			
			List<String> layer2 = new ArrayList<String>();
			layer2.add("R");
			layer2.add("N");
		
			List<String> layer3 = new ArrayList<String>();
			layer3.add("N");
			layer3.add("O");
			layer3.add("P");
			layer3.add("S");

			List<String> layer4 = new ArrayList<String>();
			layer4.add("L");
			layer4.add("H");
			layer4.add("C");
			layer4.add("R");
			
			List<String> layer5 = new ArrayList<String>();
			layer5.add("P");
			layer5.add("D");
			layer5.add("W");
			layer5.add("C");
			layer5.add("I");
			layer5.add("A");
			layer5.add("L");
		
			List<String> layer6 = new ArrayList<String>();
			layer6.add("S");
			layer6.add("P");
			layer6.add("M");
			layer6.add("L");
			layer6.add("N");			
			

			
			int total_Periods = read.get_total_Periods();
			int total_AgeClasses = total_Periods-1;		//loop from age 1 to age total_AgeClasses (set total_AgeClasses=total_Periods-1)
			int total_methods = 4;
			int total_PB_Prescriptions = 5;
			int total_GS_Prescriptions = 5;

			List<Double> objlist = new ArrayList<Double>();				//objective coefficient
			List<String> vnamelist = new ArrayList<String>();			//variable name
			List<Double> vlblist = new ArrayList<Double>();				//lower bound
			List<Double> vublist = new ArrayList<Double>();				//upper bound
			List<IloNumVarType> vtlist = new ArrayList<IloNumVarType>();//variable type
			
			int nvars = 0;
			int nV=0;
	
			
			// Declare arrays to keep variables		 
			int[][][][][][][] x = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_methods];		//x(s1,s2,s3,s4,s5,s6)(q)
			int[][][][][][] xNG = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()];							//xNG(s1,s2,s3,s4,s5,s6)
			int[][][][][][][] xPB = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_PB_Prescriptions];		//xPB(s1,s2,s3,s4,s5,s6)(i)
			int[][][][][][][] xGS = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_GS_Prescriptions];			//xGS(s1,s2,s3,s4,s5,s6)(i)
			int[][][][][][][] xEAe = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()]
							[total_Periods + 1];		//xEAe(s1,s2,s3,s4,s5,s6)(t)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][] xEAeCut = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()]
							[total_Periods + 1][layer5.size()];		//xEAe'(s1,s2,s3,s4,s5,s6)(t)(c)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			
			int[][][][][][][] xEAeR = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()]
							[total_Periods + 1];		//xEAeR(s1,s2,s3,s4,s5,s6)(t)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			
			int[][][][][][][] xEAr = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses+1];		//xEAr(s1,s2,s3,s4,s5)(t)(a)(c)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][] xEArCut = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses+1][layer5.size()];		//xEAr'(s1,s2,s3,s4,s5)(t)(a)(c)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0

			int[][][][][][][] xEArR = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses+1];		//xEArR(s1,s2,s3,s4,s5)(t)(a)(c)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			
			
			//Get the 2 parameter V(s1,s2,s3,s4,s5,s6) and A(s1,s2,s3,s4,s5,s6)
			String Input2_value[][] = read.getIn2_Values();	
			double[][][][][][] StrataArea = new double[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()];
			int[][][][][][] StartingAge = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()];			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {						
										//Loop through all modeled_strata to find if the names matched and get the total area
										for (int i = 1; i < read.get_In2_TotalRows(); i++) {	//From 2nd row			
											if (Input2_value[i][0].equals(strataName)) {
												StrataArea[s1][s2][s3][s4][s5][s6] = Double.parseDouble(Input2_value[i][7]);		
												StartingAge[s1][s2][s3][s4][s5][s6] = 1;	//Loading the true value later	
											}	
										}
									}
								}
							}
						}
					}
				}
			}
			
			
			
			
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			
			// Create decision variables x(s1,s2,s3,s4,s5,s6)(q)			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int q = 0; q < total_methods; q++) {
											objlist.add((double) 0);
											vnamelist.add("x(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + q + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											x[s1][s2][s3][s4][s5][s6][q] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;
			
			// Create decision variables xNG(s1,s2,s3,s4,s5,s6)	
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										objlist.add((double) 0);
										vnamelist.add("xNG(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")");
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										vtlist.add(IloNumVarType.Float);
										xNG[s1][s2][s3][s4][s5][s6] = nvars;
										nvars++;
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;
			
			// Create decision variables xPB(s1,s2,s3,s4,s5,s6)(i)	
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											objlist.add((double) 0);
											vnamelist.add("xPB(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + i + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xPB[s1][s2][s3][s4][s5][s6][i] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;			
			
			// Create decision variables xGS(s1,s2,s3,s4,s5,s6)(i)		
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											objlist.add((double) 0);
											vnamelist.add("xGS(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + i + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xGS[s1][s2][s3][s4][s5][s6][i] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;	

			// Create decision variables xEAe(s1,s2,s3,s4,s5,s6)(t)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int t = 1; t <= total_Periods; t++) {
											objlist.add((double) 0);
											vnamelist.add("xEAe(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + t + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xEAe[s1][s2][s3][s4][s5][s6][t] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;					
			
			// Create decision variables xEAe'(s1,s2,s3,s4,s5,s6)(t)(c)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int t = 1; t <= total_Periods; t++) {
											for (int c = 0; c < layer5.size(); c++) {
												String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
												if (coverTypeConversions.contains(thisCoverTypeconversion)) {
													objlist.add((double) 0);
													vnamelist.add("xEAe'(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + t + ")(" + c + ")");
													vlblist.add((double) 0);
													vublist.add(Double.MAX_VALUE);
													vtlist.add(IloNumVarType.Float);
													xEAeCut[s1][s2][s3][s4][s5][s6][t][c] = nvars;
													nvars++;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;	
			
			// Create decision variables xEAeR(s1,s2,s3,s4,s5,s6)(t)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int t = 1; t <= total_Periods; t++) {
											objlist.add((double) 0);
											vnamelist.add("xEAeR(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + t + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xEAeR[s1][s2][s3][s4][s5][s6][t] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;	
			
			// Create decision variables xEAr(s1,s2,s3,s4,s5)(t)(a)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 2; t <= total_Periods; t++) {
										for (int a = 1; a <= t-1; a++) {
											objlist.add((double) 0);
											vnamelist.add("xEAr(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + ")(" + t + ")(" + a + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xEAr[s1][s2][s3][s4][s5][t][a] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;
			
			// Create decision variables xEAr'(s1,s2,s3,s4,s5)(t)(a)(c)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 2; t <= total_Periods; t++) {
										for (int a = 1; a <= t - 1; a++) {
											for (int c = 0; c < layer5.size(); c++) {
												String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
												if (coverTypeConversions.contains(thisCoverTypeconversion)) {
													objlist.add((double) 0);
													vnamelist.add("xEAr'(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + ")(" + t + ")(" + a + ")(" + c + ")");
													vlblist.add((double) 0);
													vublist.add(Double.MAX_VALUE);
													vtlist.add(IloNumVarType.Float);
													xEArCut[s1][s2][s3][s4][s5][t][a][c] = nvars;
													nvars++;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;			
			
			// Create decision variables xEArR(s1,s2,s3,s4,s5)(t)(a)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 2; t <= total_Periods; t++) {
										for (int a = 1; a <= t-1; a++) {
											objlist.add((double) 0);
											vnamelist.add("xEArR(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + ")(" + t + ")(" + a + ")");
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xEArR[s1][s2][s3][s4][s5][t][a] = nvars;
											nvars++;
										}
									}
								}
							}
						}
					}
				}
			}
			nV = nvars;
			
			
			// Convert list to 1-D arrays
//			Double[] objvals = objlist.toArray(new Double[objlist.size()]);
//			String[] vname = vnamelist.toArray(new String[vnamelist.size()]);
//			Double[] vlb = vlblist.toArray(new Double[vlblist.size()]);
//			Double[] vub = vublist.toArray(new Double[vublist.size()]);
//			IloNumVarType[] vtype = vtlist.toArray(new IloNumVarType[vtlist.size()]);

			
			double[] objvals = Stream.of(objlist.toArray(new Double[objlist.size()])).mapToDouble(Double::doubleValue).toArray();
			String[] vname = vnamelist.toArray(new String[vnamelist.size()]);
			double[] vlb = Stream.of(vlblist.toArray(new Double[vlblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] vub = Stream.of(vublist.toArray(new Double[vublist.size()])).mapToDouble(Double::doubleValue).toArray();
			IloNumVarType[] vtype = vtlist.toArray(new IloNumVarType[vtlist.size()]);
								//Note: vname and vtype may cause problems because of wrong casting
	
					
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			
			// Constraints 6-------------------------------------------------
			List<List<Integer>> c6_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c6_valuelist = new ArrayList<List<Double>>();
			List<Double> c6_lblist = new ArrayList<Double>();	
			List<Double> c6_ublist = new ArrayList<Double>();
			int C6_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										//Add constraint
										c6_indexlist.add(new ArrayList<Integer>());
										c6_valuelist.add(new ArrayList<Double>());
										
										for (int q = 0; q < total_methods; q++) {
											//Add x(s1,s2,s3,s4,s5,s6)(q)
											c6_indexlist.get(C6_num).add(x[s1][s2][s3][s4][s5][s6][q]);
											c6_valuelist.get(C6_num).add((double) 1);
										}
										
										//add bounds
										 c6_lblist.add(StrataArea[s1][s2][s3][s4][s5][s6]);
										 c6_ublist.add(StrataArea[s1][s2][s3][s4][s5][s6]);
										 C6_num++;
									}
								}
							}
						}
					}
				}
			}			
			
			double[] c6_lb = Stream.of(c6_lblist.toArray(new Double[c6_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c6_ub = Stream.of(c6_ublist.toArray(new Double[c6_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c6_index = new int[C6_num][];
			double[][] c6_value = new double[C6_num][];
		
			for (int i = 0; i < C6_num; i++) {
				c6_index[i] = new int[c6_indexlist.get(i).size()];
				c6_value[i] = new double[c6_indexlist.get(i).size()];
				for (int j = 0; j < c6_indexlist.get(i).size(); j++) {
					c6_index[i][j] = c6_indexlist.get(i).get(j);
					c6_value[i][j] = c6_valuelist.get(i).get(j);			
				}
			}
			
			
			// Constraints 7-------------------------------------------------
			List<List<Integer>> c7_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c7_valuelist = new ArrayList<List<Double>>();
			List<Double> c7_lblist = new ArrayList<Double>();	
			List<Double> c7_ublist = new ArrayList<Double>();
			int C7_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										//Add constraint
										c7_indexlist.add(new ArrayList<Integer>());
										c7_valuelist.add(new ArrayList<Double>());
										
										//Add x(s1,s2,s3,s4,s5,s6)[0]
										c7_indexlist.get(C7_num).add(x[s1][s2][s3][s4][s5][s6][0]);
										c7_valuelist.get(C7_num).add((double) 1);
										
										//Add -xNG(s1,s2,s3,s4,s5,s6)
										c7_indexlist.get(C7_num).add(xNG[s1][s2][s3][s4][s5][s6]);
										c7_valuelist.get(C7_num).add((double) -1);
								
										//add bounds
										c7_lblist.add((double) 0);
										c7_ublist.add((double) 0);
										C7_num++;
									}
								}
							}
						}
					}
				}
			}		
			
			double[] c7_lb = Stream.of(c7_lblist.toArray(new Double[c7_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c7_ub = Stream.of(c7_ublist.toArray(new Double[c7_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c7_index = new int[C7_num][];
			double[][] c7_value = new double[C7_num][];
		
			for (int i = 0; i < C7_num; i++) {
				c7_index[i] = new int[c7_indexlist.get(i).size()];
				c7_value[i] = new double[c7_indexlist.get(i).size()];
				for (int j = 0; j < c7_indexlist.get(i).size(); j++) {
					c7_index[i][j] = c7_indexlist.get(i).get(j);
					c7_value[i][j] = c7_valuelist.get(i).get(j);			
				}
			}			
			
			
			// Constraints 8-------------------------------------------------
			List<List<Integer>> c8_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c8_valuelist = new ArrayList<List<Double>>();
			List<Double> c8_lblist = new ArrayList<Double>();	
			List<Double> c8_ublist = new ArrayList<Double>();
			int C8_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										//Add constraint
										c8_indexlist.add(new ArrayList<Integer>());
										c8_valuelist.add(new ArrayList<Double>());
										
										//Add x(s1,s2,s3,s4,s5,s6)[1]
										c8_indexlist.get(C8_num).add(x[s1][s2][s3][s4][s5][s6][1]);
										c8_valuelist.get(C8_num).add((double) 1);
										
										//Add -xPB(s1,s2,s3,s4,s5,s6)[i]
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											c8_indexlist.get(C8_num).add(xPB[s1][s2][s3][s4][s5][s6][i]);
											c8_valuelist.get(C8_num).add((double) -1);
										}
										
										//add bounds
										c8_lblist.add((double) 0);
										c8_ublist.add((double) 0);
										C8_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c8_lb = Stream.of(c8_lblist.toArray(new Double[c8_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c8_ub = Stream.of(c8_ublist.toArray(new Double[c8_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c8_index = new int[C8_num][];
			double[][] c8_value = new double[C8_num][];
		
			for (int i = 0; i < C8_num; i++) {
				c8_index[i] = new int[c8_indexlist.get(i).size()];
				c8_value[i] = new double[c8_indexlist.get(i).size()];
				for (int j = 0; j < c8_indexlist.get(i).size(); j++) {
					c8_index[i][j] = c8_indexlist.get(i).get(j);
					c8_value[i][j] = c8_valuelist.get(i).get(j);			
				}
			}				
			
			
			// Constraints 9-------------------------------------------------
			List<List<Integer>> c9_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c9_valuelist = new ArrayList<List<Double>>();
			List<Double> c9_lblist = new ArrayList<Double>();	
			List<Double> c9_ublist = new ArrayList<Double>();
			int C9_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										//Add constraint
										c9_indexlist.add(new ArrayList<Integer>());
										c9_valuelist.add(new ArrayList<Double>());
										
										//Add x(s1,s2,s3,s4,s5,s6)[2]
										c9_indexlist.get(C9_num).add(x[s1][s2][s3][s4][s5][s6][2]);
										c9_valuelist.get(C9_num).add((double) 1);							
										
										//Add -xGS(s1,s2,s3,s4,s5,s6)[i]
										for (int i = 0; i < total_GS_Prescriptions; i++) {
											c9_indexlist.get(C9_num).add(xGS[s1][s2][s3][s4][s5][s6][i]);
											c9_valuelist.get(C9_num).add((double) -1);
										}
										
										// add bounds
										c9_lblist.add((double) 0);
										c9_ublist.add((double) 0);
										C9_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c9_lb = Stream.of(c9_lblist.toArray(new Double[c9_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c9_ub = Stream.of(c9_ublist.toArray(new Double[c9_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c9_index = new int[C9_num][];
			double[][] c9_value = new double[C9_num][];
		
			for (int i = 0; i < C9_num; i++) {
				c9_index[i] = new int[c9_indexlist.get(i).size()];
				c9_value[i] = new double[c9_indexlist.get(i).size()];
				for (int j = 0; j < c9_indexlist.get(i).size(); j++) {
					c9_index[i][j] = c9_indexlist.get(i).get(j);
					c9_value[i][j] = c9_valuelist.get(i).get(j);			
				}
			}				
			
			
			// Constraints 10-------------------------------------------------
			List<List<Integer>> c10_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c10_valuelist = new ArrayList<List<Double>>();
			List<Double> c10_lblist = new ArrayList<Double>();	
			List<Double> c10_ublist = new ArrayList<Double>();
			int C10_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										//Add constraint
										c10_indexlist.add(new ArrayList<Integer>());
										c10_valuelist.add(new ArrayList<Double>());

										//Add x(s1,s2,s3,s4,s5,s6)[3]
										c10_indexlist.get(C10_num).add(x[s1][s2][s3][s4][s5][s6][3]);
										c10_valuelist.get(C10_num).add((double) 1);							
									
										//Add - xEAe(s1,s2,s3,s4,s5,s6)[1]	
										c10_indexlist.get(C10_num).add(xEAe[s1][s2][s3][s4][s5][s6][1]);
										c10_valuelist.get(C10_num).add((double) -1);
										
										//Add - sigma(c) xEAe'(s1,s2,s3,s4,s5,s6)[1][c]	
										for (int c = 0; c < layer5.size(); c++) {
											String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
											if (coverTypeConversions.contains(thisCoverTypeconversion)) {
												c10_indexlist.get(C10_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][1][c]);
												c10_valuelist.get(C10_num).add((double) -1);
											}
										}									
										
										//add bounds
										c10_lblist.add((double) 0);
										c10_ublist.add((double) 0);
										C10_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c10_lb = Stream.of(c10_lblist.toArray(new Double[c10_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c10_ub = Stream.of(c10_ublist.toArray(new Double[c10_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c10_index = new int[C10_num][];
			double[][] c10_value = new double[C10_num][];
		
			for (int i = 0; i < C10_num; i++) {
				c10_index[i] = new int[c10_indexlist.get(i).size()];
				c10_value[i] = new double[c10_indexlist.get(i).size()];
				for (int j = 0; j < c10_indexlist.get(i).size(); j++) {
					c10_index[i][j] = c10_indexlist.get(i).get(j);
					c10_value[i][j] = c10_valuelist.get(i).get(j);			
				}
			}			
			
			// Constraints 11-------------------------------------------------
			List<List<Integer>> c11_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c11_valuelist = new ArrayList<List<Double>>();
			List<Double> c11_lblist = new ArrayList<Double>();	
			List<Double> c11_ublist = new ArrayList<Double>();
			int C11_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										//Add constraint
										c11_indexlist.add(new ArrayList<Integer>());
										c11_valuelist.add(new ArrayList<Double>());

										//Add xEAe(s1,s2,s3,s4,s5,s6)[T]	
										c11_indexlist.get(C11_num).add(xEAe[s1][s2][s3][s4][s5][s6][total_Periods]);
										c11_valuelist.get(C11_num).add((double) 1);
																									
										//add bounds
										c11_lblist.add((double) 0);
										c11_ublist.add((double) 0);
										C11_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c11_lb = Stream.of(c11_lblist.toArray(new Double[c11_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c11_ub = Stream.of(c11_ublist.toArray(new Double[c11_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c11_index = new int[C11_num][];
			double[][] c11_value = new double[C11_num][];
		
			for (int i = 0; i < C11_num; i++) {
				c11_index[i] = new int[c11_indexlist.get(i).size()];
				c11_value[i] = new double[c11_indexlist.get(i).size()];
				for (int j = 0; j < c11_indexlist.get(i).size(); j++) {
					c11_index[i][j] = c11_indexlist.get(i).get(j);
					c11_value[i][j] = c11_valuelist.get(i).get(j);			
				}
			}			
			
			// Constraints 12-------------------------------------------------
			List<List<Integer>> c12_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c12_valuelist = new ArrayList<List<Double>>();
			List<Double> c12_lblist = new ArrayList<Double>();	
			List<Double> c12_ublist = new ArrayList<Double>();
			int C12_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										for (int t = 1; t <= total_Periods-1; t++) {
											//Add constraint
											c12_indexlist.add(new ArrayList<Integer>());
											c12_valuelist.add(new ArrayList<Double>());
											
											//Add xEAe(s1,s2,s3,s4,s5,s6)[t]
											c12_indexlist.get(C12_num).add(xEAe[s1][s2][s3][s4][s5][s6][t]);
											c12_valuelist.get(C12_num).add((double) 1);
											
											//Add - xEAe(s1,s2,s3,s4,s5,s6)[t+1]		
											c12_indexlist.get(C12_num).add(xEAe[s1][s2][s3][s4][s5][s6][t+1]);
											c12_valuelist.get(C12_num).add((double) -1);
											
											//Add - sigma(c) xEAe'(s1,s2,s3,s4,s5,s6)[t+1][c]		
											for (int c = 0; c < layer5.size(); c++) {
												String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
												if (coverTypeConversions.contains(thisCoverTypeconversion)) {
													c12_indexlist.get(C12_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t+1][c]);
													c12_valuelist.get(C12_num).add((double) -1);
												}
											}											
											
											//add bounds
											c12_lblist.add((double) 0);
											c12_ublist.add((double) 0);
											C12_num++;
										}
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c12_lb = Stream.of(c12_lblist.toArray(new Double[c12_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c12_ub = Stream.of(c12_ublist.toArray(new Double[c12_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c12_index = new int[C12_num][];
			double[][] c12_value = new double[C12_num][];
		
			for (int i = 0; i < C12_num; i++) {
				c12_index[i] = new int[c12_indexlist.get(i).size()];
				c12_value[i] = new double[c12_indexlist.get(i).size()];
				for (int j = 0; j < c12_indexlist.get(i).size(); j++) {
					c12_index[i][j] = c12_indexlist.get(i).get(j);
					c12_value[i][j] = c12_valuelist.get(i).get(j);			
				}
			}
			
			
			// Constraints 13-------------------------------------------------
			List<List<Integer>> c13_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c13_valuelist = new ArrayList<List<Double>>();
			List<Double> c13_lblist = new ArrayList<Double>();	
			List<Double> c13_ublist = new ArrayList<Double>();
			int C13_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {		
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 2; t <= total_Periods - 1; t++) {
										for (int a = 1; a <= t-1; a++) {
								
											//Add constraint
											c13_indexlist.add(new ArrayList<Integer>());
											c13_valuelist.add(new ArrayList<Double>());
											
											//Add xEAr(s1,s2,s3,s4,s5)[t][a]
											c13_indexlist.get(C13_num).add(xEAr[s1][s2][s3][s4][s5][t][a]);
											c13_valuelist.get(C13_num).add((double) 1);
											
											//Add - xEAr(s1,s2,s3,s4,s5)[t+1][a+1]
											c13_indexlist.get(C13_num).add(xEAr[s1][s2][s3][s4][s5][t+1][a+1]);
											c13_valuelist.get(C13_num).add((double) -1);
											
											//Add - sigma(c) xEAr'(s1,s2,s3,s4,s5)[t+1][a+1][c]		
											for (int c = 0; c < layer5.size(); c++) {
												String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
												if (coverTypeConversions.contains(thisCoverTypeconversion)) {
													c13_indexlist.get(C13_num).add(xEArCut[s1][s2][s3][s4][s5][t+1][a+1][c]);
													c13_valuelist.get(C13_num).add((double) -1);
												}
											}											
											
											//add bounds
											c13_lblist.add((double) 0);
											c13_ublist.add((double) 0);
											C13_num++;
										}
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c13_lb = Stream.of(c13_lblist.toArray(new Double[c13_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c13_ub = Stream.of(c13_ublist.toArray(new Double[c13_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c13_index = new int[C13_num][];
			double[][] c13_value = new double[C13_num][];
		
			for (int i = 0; i < C13_num; i++) {
				c13_index[i] = new int[c13_indexlist.get(i).size()];
				c13_value[i] = new double[c13_indexlist.get(i).size()];
				for (int j = 0; j < c13_indexlist.get(i).size(); j++) {
					c13_index[i][j] = c13_indexlist.get(i).get(j);
					c13_value[i][j] = c13_valuelist.get(i).get(j);			
				}
			}			
			
			
			// Constraints 14-------------------------------------------------
			List<List<Integer>> c14_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c14_valuelist = new ArrayList<List<Double>>();
			List<Double> c14_lblist = new ArrayList<Double>();	
			List<Double> c14_ublist = new ArrayList<Double>();
			int C14_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int t = 1; t <= total_Periods - 1; t++) {
								String strataName1 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
								if (modeled_strata_withoutSizeClassandCoverType.contains(strataName1)) {	
									for (int c = 0; c < layer5.size(); c++) {
										
										//Add constraint
										c14_indexlist.add(new ArrayList<Integer>());
										c14_valuelist.add(new ArrayList<Double>());
										
										//Add sigma(s5)(s6) xEAe'(s1,s2,s3,s4,s5,s6)[t][c]
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int s6 = 0; s6 < layer6.size(); s6++) {
												String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
												String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
												if (modeled_strata.contains(strataName) && coverTypeConversions.contains(thisCoverTypeconversion)) {
													c14_indexlist.get(C14_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t][c]);
													c14_valuelist.get(C14_num).add((double) 1);
												}
											}
										}
										
										//Add sigma(s5)(a) xEAr'(s1,s2,s3,s4,s5)[t][a][c] only if period t>=2
										if (t >= 2) {
											for (int s5 = 0; s5 < layer5.size(); s5++) {
												for (int a = 1; a <= t - 1; a++) {
													String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
													if (coverTypeConversions.contains(thisCoverTypeconversion)) {
														c14_indexlist.get(C14_num).add(xEArCut[s1][s2][s3][s4][s5][t][a][c]);
														c14_valuelist.get(C14_num).add((double) 1);
													}
												}
											}
										}

										
										//Add -xEAr(s1,s2,s3,s4,s5=c)[t+1][1]
										c14_indexlist.get(C14_num).add(xEAr[s1][s2][s3][s4][c][t + 1][1]);
										c14_valuelist.get(C14_num).add((double) -1);
																			
										//Add -sigma(c') xEAr'(s1,s2,s3,s4,s5=c)[t+1][1][c']
										for (int cc = 0; cc < layer5.size(); cc++) {
											String thisCoverTypeconversion = layer5.get(c) + " " + layer5.get(cc);						
											if (coverTypeConversions.contains(thisCoverTypeconversion)) {
												c14_indexlist.get(C14_num).add(xEArCut[s1][s2][s3][s4][c][t + 1][1][cc]);
												c14_valuelist.get(C14_num).add((double) -1);
											}
										}
										
										// add bounds
										c14_lblist.add((double) 0);
										c14_ublist.add((double) 0);
										C14_num++;
									}
								}
							}
						}
					}
				}
			}
			
			double[] c14_lb = Stream.of(c14_lblist.toArray(new Double[c14_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c14_ub = Stream.of(c14_ublist.toArray(new Double[c14_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c14_index = new int[C14_num][];
			double[][] c14_value = new double[C14_num][];
		
			for (int i = 0; i < C14_num; i++) {
				c14_index[i] = new int[c14_indexlist.get(i).size()];
				c14_value[i] = new double[c14_indexlist.get(i).size()];
				for (int j = 0; j < c14_indexlist.get(i).size(); j++) {
					c14_index[i][j] = c14_indexlist.get(i).get(j);
					c14_value[i][j] = c14_valuelist.get(i).get(j);			
				}
			}						
			

			
			// Constraints 15-------------------------------------------------
			List<List<Integer>> c15_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c15_valuelist = new ArrayList<List<Double>>();
			List<Double> c15_lblist = new ArrayList<Double>();	
			List<Double> c15_ublist = new ArrayList<Double>();
			int C15_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										for (int t = 1; t <= total_Periods; t++) {
											//Add constraint
											c15_indexlist.add(new ArrayList<Integer>());
											c15_valuelist.add(new ArrayList<Double>());
											
											//Add xEAeR(s1,s2,s3,s4,s5,s6)[t]
											c15_indexlist.get(C15_num).add(xEAeR[s1][s2][s3][s4][s5][s6][t]);
											c15_valuelist.get(C15_num).add((double) 1);
											
											//Add - xEAe(s1,s2,s3,s4,s5,s6)[t]
											c15_indexlist.get(C15_num).add(xEAe[s1][s2][s3][s4][s5][s6][t]);
											c15_valuelist.get(C15_num).add((double) -1);
											
											//Add sigma(t')(c) xEAe'(s1,s2,s3,s4,s5,s6)[t'][c]	
											for (int tt = t + 1; tt <= total_Periods; tt++) {
												for (int c = 0; c < layer5.size(); c++) {
													String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);
													if (coverTypeConversions.contains(thisCoverTypeconversion)) {
														c15_indexlist.get(C15_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][tt][c]);
														c15_valuelist.get(C15_num).add((double) 1);
													}
												} 
											}
											
											//add bounds
											c15_lblist.add((double) 0);
											c15_ublist.add((double) 0);
											C15_num++;
										}
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c15_lb = Stream.of(c15_lblist.toArray(new Double[c15_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c15_ub = Stream.of(c15_ublist.toArray(new Double[c15_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c15_index = new int[C15_num][];
			double[][] c15_value = new double[C15_num][];
		
			for (int i = 0; i < C15_num; i++) {
				c15_index[i] = new int[c15_indexlist.get(i).size()];
				c15_value[i] = new double[c15_indexlist.get(i).size()];
				for (int j = 0; j < c15_indexlist.get(i).size(); j++) {
					c15_index[i][j] = c15_indexlist.get(i).get(j);
					c15_value[i][j] = c15_valuelist.get(i).get(j);			
				}
			}
			
			
			// Constraints 16-------------------------------------------------
			List<List<Integer>> c16_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c16_valuelist = new ArrayList<List<Double>>();
			List<Double> c16_lblist = new ArrayList<Double>();	
			List<Double> c16_ublist = new ArrayList<Double>();
			int C16_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {		
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 2; t <= total_Periods; t++) {
										for (int a = 1; a <= t-1; a++) {
								
											//Add constraint
											c16_indexlist.add(new ArrayList<Integer>());
											c16_valuelist.add(new ArrayList<Double>());
							
											//Add xEArR(s1,s2,s3,s4,s5)[t][a]
											c16_indexlist.get(C16_num).add(xEArR[s1][s2][s3][s4][s5][t][a]);
											c16_valuelist.get(C16_num).add((double) 1);
											
											//Add - xEAr(s1,s2,s3,s4,s5)[t][a]
											c16_indexlist.get(C16_num).add(xEAr[s1][s2][s3][s4][s5][t][a]);
											c16_valuelist.get(C16_num).add((double) -1);
											
											//Add sigma(c) xEAr'(s1,s2,s3,s4,s5)[t'][a=t'-t][c]
											for (int tt = t + 1; tt <= total_Periods; tt++) {
												for (int c = 0; c < layer5.size(); c++) {
													String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
													if (coverTypeConversions.contains(thisCoverTypeconversion)) {
														c16_indexlist.get(C16_num).add(xEArCut[s1][s2][s3][s4][s5][tt][tt-t][c]);
														c16_valuelist.get(C16_num).add((double) 1);
													}
												}	
											}
											
											//add bounds
											c16_lblist.add((double) 0);
											c16_ublist.add((double) 0);
											C16_num++;
										}
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c16_lb = Stream.of(c16_lblist.toArray(new Double[c16_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c16_ub = Stream.of(c16_ublist.toArray(new Double[c16_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c16_index = new int[C16_num][];
			double[][] c16_value = new double[C16_num][];
		
			for (int i = 0; i < C16_num; i++) {
				c16_index[i] = new int[c16_indexlist.get(i).size()];
				c16_value[i] = new double[c16_indexlist.get(i).size()];
				for (int j = 0; j < c16_indexlist.get(i).size(); j++) {
					c16_index[i][j] = c16_indexlist.get(i).get(j);
					c16_value[i][j] = c16_valuelist.get(i).get(j);			
				}
			}						
			
			
			
			
			
			
			// Solve problem-------------------------------------------------	
			IloCplex cplex = new IloCplex();
			IloLPMatrix lp = cplex.addLPMatrix();
			IloNumVar[] var = cplex.numVarArray(cplex.columnArray(lp, nvars), vlb, vub, vtype, vname);
			
			// Add constraints
			lp.addRows(c6_lb, c6_ub, c6_index, c6_value);	// Constraints 6
			lp.addRows(c7_lb, c7_ub, c7_index, c7_value);	// Constraints 7
			lp.addRows(c8_lb, c8_ub, c8_index, c8_value);	// Constraints 8
			lp.addRows(c9_lb, c9_ub, c9_index, c9_value);	// Constraints 9
			lp.addRows(c10_lb, c10_ub, c10_index, c10_value);	// Constraints 10
			lp.addRows(c11_lb, c11_ub, c11_index, c11_value);	// Constraints 11
			lp.addRows(c12_lb, c12_ub, c12_index, c12_value);	// Constraints 12
			lp.addRows(c13_lb, c13_ub, c13_index, c13_value);	// Constraints 13
			lp.addRows(c14_lb, c14_ub, c14_index, c14_value);	// Constraints 14
			lp.addRows(c15_lb, c15_ub, c15_index, c15_value);	// Constraints 15
			lp.addRows(c16_lb, c16_ub, c16_index, c16_value);	// Constraints 16
			
			
//			// Set constraints set name: Notice THIS WILL EXTREMELY SLOW THE SOLVING PROCESS (recommend for debugging only)
//			int indexOfC6 = C6_num;
//			int indexOfC7 = indexOfC6 + C7_num;
//			int indexOfC8 = indexOfC7 + C8_num;
//			int indexOfC9 = indexOfC8 + C9_num;
//			int indexOfC10 = indexOfC9 + C10_num;
//			int indexOfC11 = indexOfC10 + C11_num;
//			int indexOfC12 = indexOfC11 + C12_num;
//			int indexOfC13 = indexOfC12 + C13_num;
//			int indexOfC14 = indexOfC13 + C14_num;
//			int indexOfC15 = indexOfC14 + C15_num;
//			int indexOfC16 = indexOfC15 + C16_num;		//Note: 	lp.getRanges().length = indexOfC16			
//			
//			for (int i = 0; i<lp.getRanges().length; i++) {		
//				if (0<=i && i<indexOfC6) lp.getRanges() [i].setName("EQ(6)_");
//				if (indexOfC6<=i && i<indexOfC7) lp.getRanges() [i].setName("EQ(7)_");
//				if (indexOfC7<=i && i<indexOfC8) lp.getRanges() [i].setName("EQ(8)_");
//				if (indexOfC8<=i && i<indexOfC9) lp.getRanges() [i].setName("EQ(9)_");
//				if (indexOfC9<=i && i<indexOfC10) lp.getRanges() [i].setName("EQ(10)_");
//				if (indexOfC10<=i && i<indexOfC11) lp.getRanges() [i].setName("EQ(11)_");
//				if (indexOfC11<=i && i<indexOfC12) lp.getRanges() [i].setName("EQ(12)_");
//				if (indexOfC12<=i && i<indexOfC13) lp.getRanges() [i].setName("EQ(13)_");
//				if (indexOfC13<=i && i<indexOfC14) lp.getRanges() [i].setName("EQ(14)_");
//				if (indexOfC14<=i && i<indexOfC15) lp.getRanges() [i].setName("EQ(15)_");
//				if (indexOfC15<=i && i<indexOfC16) lp.getRanges() [i].setName("EQ(16)_");
//			}
			
			
			cplex.addMinimize(cplex.scalProd(var, objvals));		// Set objective function to minimize
			cplex.setParam(IloCplex.Param.RootAlgorithm,			// Auto choose optimization method
                    IloCplex.Algorithm.Auto);
//			cplex.setParam(IloCplex.DoubleParam.EpGap, 0.00);		// Gap is 0%
			cplex.setParam(IloCplex.DoubleParam.TimeLimit, 300);	// Time limit is 300 seconds
//			cplex.setParam(IloCplex.BooleanParam.PreInd, false);	// Turn off preSolve to see full variables and constraints
																	// Note: currently if not turning PreSolve off, cplex will crash
			
			data[row][2] = cplex.getNcols();
			data[row][3] = cplex.getNrows();
			data[row][4] = "running";
			table.setValueAt(data[row][2] , row, 2); //To help trigger the table refresh: fireTableDataChanged() and repaint();
			table.setValueAt(data[row][3] , row, 3);
			table.setValueAt(data[row][4] , row, 4);
			
			if (cplex.solve()) {
				cplex.exportModel(problemFile[row].getAbsolutePath());
				cplex.writeSolution(solutionFile[row].getAbsolutePath());
				
				data[row][1] = "valid";
				table.setValueAt(data[row][1], row, 1);
				data[row][4] = "successful";
				table.setValueAt(data[row][4], row, 4);
			}
			cplex.endModel();
			cplex.end();
			
			
			
	
		}
		catch (IloException e) {
			System.err.println("Concert exception '" + e + "' caught for " + listOfEditRuns[row].getName());
			displayTextArea.append("Concert exception '" + e + "' caught for " + listOfEditRuns[row].getName() + "\n");
			
			data[row][1] = "Concert error";
			table.setValueAt(data[row][1], row, 1);
			data[row][4] = "fail";
			table.setValueAt(data[row][4] , row, 4);
		}
		
		catch (Exception e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage() + " caught for '" + listOfEditRuns[row].getName() + "' please check Input Files");
			displayTextArea.append(e2.getClass().getName() + ": " + e2.getMessage() + " caught for '" + listOfEditRuns[row].getName()  + "' please check Input Files" + "\n");
			data[row][1] = "Invalid Inputs";
			table.setValueAt(data[row][1], row, 1);
			data[row][4] = "fail";
			table.setValueAt(data[row][4] , row, 4);
		}
	}
}