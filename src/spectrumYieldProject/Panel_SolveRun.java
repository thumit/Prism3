package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultCaret;

import ilog.concert.IloException;
import ilog.concert.IloLPMatrix;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;
import lpsolve.LpSolve;
import spectrumConvenienceClasses.FilesHandle;
import spectrumConvenienceClasses.LibraryHandle;
import spectrumConvenienceClasses.TableModelSpectrum;

public class Panel_SolveRun extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel, splitPanel2;
	private JTextArea displayTextArea;
	private JButton runStatButton;
	private boolean solvingstatus;
	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private TableModelSpectrum model;
	private Object[][] data;
	
	private File[] listOfEditRuns ;
	private JScrollPane scrollPane_Left, scrollPane_Right;
	
	private File[] problemFile, solutionFile, output_generalInfo_file, output_variables_file, output_constraints_file, output_managementOverview_file;
	
	private DecimalFormat twoDForm = new DecimalFormat("#.##");	 //Only get 2 decimal will be assess
	
	public Panel_SolveRun(File[] runsList) {
		super.setLayout(new BorderLayout(0, 0));
		listOfEditRuns = runsList;

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
        model = new TableModelSpectrum(colCount, rowCount, columnNames, data);
        table = new JTable(model);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);	//Set width of Column 'Validation'
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.LEFT);		// Set alignment of values in the table to the left side
//      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
 
        
        //Setup the TextArea--------------------------------------------------------------------------------
		displayTextArea = new JTextArea();
		displayTextArea.setBackground(Color.BLACK); 
		displayTextArea.setForeground(Color.WHITE);
		displayTextArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret) displayTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        
        
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
		

		java.net.URL imgURL = getClass().getResource("/pikachuRunning.gif");		//Name is case sensitive
		java.net.URL imgURL2 = getClass().getResource("/pikachuHello.png");			//Name is case sensitive		

		
//		try {		//Activate this if want some picture from Internet
//			//	https://media.giphy.com/media/TFhobYtkih62k/giphy.gif
//			//	http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif
//			//	http://orig11.deviantart.net/b288/f/2009/260/9/5/pikachu_vector_by_elfaceitoso.png	
//			imgURL = new java.net.URL("http://www.lovethisgif.com/uploaded_images/56753-Pikachu-Running-Animation-By-Cadetderp-On-Deviantart.gif");
//			imgURL2 = new java.net.URL("http://orig11.deviantart.net/b288/f/2009/260/9/5/pikachu_vector_by_elfaceitoso.png");
//		} catch (MalformedURLException e1) {
//			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
//		}	
		
       	
		ImageIcon icon = new ImageIcon(imgURL);		//Image is in the same location of this class
		ImageIcon icon2 = new ImageIcon(imgURL2);		//Image is in the same location of this class
				
		Image scaleImage = icon.getImage().getScaledInstance(200, 150,Image.SCALE_SMOOTH);
		Image scaleImage2 = icon2.getImage().getScaledInstance(150, 150,Image.SCALE_SMOOTH);
	
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
					output_variables_file = new File[rowCount];
					output_constraints_file = new File[rowCount];
					output_generalInfo_file = new File[rowCount];
					output_managementOverview_file = new File[rowCount];
					

					// Open 2 new parallel threads: 1 for running CPLEX, 1 for redirecting console to displayTextArea
					Thread thread2 = new Thread() {
						public void run() {
							try {
								//redirect console to JTextArea
								PipedOutputStream pOut = new PipedOutputStream();
								System.setOut(new PrintStream(pOut));
								System.setErr(new PrintStream(pOut));
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
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - Thread 2 error - " + e.getClass().getName() + ": " + e.getMessage());
							}
						}
					};
					
					
					Thread thread1 = new Thread() {
						public void run() {
							for (int row = 0; row < rowCount; row++) {
								runStatButton.setText("searching for " + listOfEditRuns[row].getName() + " solution");
								data[row][4] = "reading";
								table.setValueAt(data[row][4] , row, 4);
								SolveProblem(row, listOfEditRuns[row]);
							}

							try {
								sleep(1000);			//sleep 1 second to so thread 2 can still print out report
								thread2.interrupt();
							} catch (InterruptedException e) {
								System.err.println("Panel Solve Runs - Thread 1 sleep error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							
							solvingstatus=false;
							runStatButton.setText("CLICK ME TO GET SOLUTIONS");
							runStatButton.setEnabled(true);	
						}
					};
					
					
					// Clear table info
					for (int row = 0; row < rowCount; row++) {
						for (int col = 0; col < colCount; col++) {
							if (col != 0 && col != 4) table.setValueAt("" , row, col);
						}
					}				
					runStatButton.setEnabled(false);
					solvingstatus=true;
					thread1.start();
					thread2.start();		//Note: Pipe broken due to disconnects before receiving responses. (safe Exception)		
				}
			}
		});
		splitPanel2.setRightComponent(runStatButton);							
		splitPanel.setLeftComponent(splitPanel2);
								
		
		// Right split panel-------------------------------------------------------------------------------
		scrollPane_Right = new JScrollPane();
		scrollPane_Right.setViewportView(displayTextArea);
		splitPanel.setRightComponent(scrollPane_Right);			
		
		
		// Add all components to Panel_SolveRun------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);	
		
	} // end Panel_SolveRun()

	// Listener for this class----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
    }

	
	//--------------------------------------------------------------------------------------------------------------------------------
	//Solve each run
	public void SolveProblem(int row, File runFolder) {
	
		try {
			problemFile[row] = new File(runFolder.getAbsolutePath() + "/Problem.lp");
			solutionFile[row] = new File(runFolder.getAbsolutePath() + "/Solution.lp");
			output_generalInfo_file[row] = new File(runFolder.getAbsolutePath() + "/Output 1 - General_Info.txt");	
			output_variables_file[row] = new File(runFolder.getAbsolutePath() + "/Output 2 - Variables.txt");
			output_constraints_file[row] = new File(runFolder.getAbsolutePath() + "/Output 3 - Constraints.txt");	
			output_managementOverview_file[row] = new File(runFolder.getAbsolutePath() + "/Output 4 - Management_Overview.txt");
			
			
		
			//Read input files to retrieve values later
			ReadRunInputs read= new ReadRunInputs();
			read.readGeneralInputs(new File(runFolder.getAbsolutePath() + "/Input 1 - GeneralInputs.txt"));
			read.readManagementOptions(new File(runFolder.getAbsolutePath() + "/Input 2 - SelectedStrata.txt"));
			read.readRequirements(new File(runFolder.getAbsolutePath() + "/Input 3 - CovertypeConversion.txt"));
			read.readMSFire(new File(runFolder.getAbsolutePath() + "/Input 4 - MSFire.txt"));
			read.readSRDFile(new File(runFolder.getAbsolutePath() + "/Input 5 - SRDisturbances.txt"));
			read.readUserConstraints(new File(runFolder.getAbsolutePath() + "/Input 6 - UserConstraints.txt"));
			read.readSRDrequirementFile(new File(runFolder.getAbsolutePath() + "/Input 7 - SRDRequirements.txt"));
			Read_DatabaseTables read_DatabaseTables = new Read_DatabaseTables(new File(runFolder.getAbsolutePath() + "/database.db"));
			
			//ManagementOptions info
			List<String> modeled_strata, modeled_strata_withoutSizeClass, modeled_strata_withoutSizeClassandCoverType = new ArrayList<String>();
			modeled_strata = read.get_modeled_strata();
			modeled_strata_withoutSizeClass = read.get_modeled_strata_withoutSizeClass();
			modeled_strata_withoutSizeClassandCoverType = read.get_modeled_strata_withoutSizeClassandCoverType();

			//MSFire info
			double[] msFireProportion = read.getMSFireProportion(); 
			
			//UserConstraints info
			String[][] UC_Value = read.get_UC_Values();
			
			int total_softConstraints = read.get_total_softConstraints();
			double[] softConstraints_LB = read.get_softConstraints_LB();
			double[] softConstraints_UB = read.get_softConstraints_UB();
			double[] softConstraints_LB_Weight = read.get_softConstraints_LB_Weight();
			double[] softConstraints_UB_Weight = read.get_softConstraints_UB_Weight();
			
			int total_hardConstraints = read.get_total_hardConstraints();
			double[] hardConstraints_LB = read.get_hardConstraints_LB();
			double[] hardConstraints_UB = read.get_hardConstraints_UB();
						
			//CoverTypeConversions_and_RotationAges Info
			List<String> coverTypeConversions, coverTypeConversions_and_RotationAges = new ArrayList<String>();
			coverTypeConversions = read.getCoverTypeConversions();
			coverTypeConversions_and_RotationAges = read.getcoverTypeConversions_and_RotationAges();			
			
			//Stand Replacing Disturbances % Info
			double[][] SRD_percent = read.getSRDProportion();
			
			//SRDrequirementProportion info
			double[] SRDrequirementProportion = read.getSRDrequirementProportion(); 
			
			//Database Info
			Object[][][] yieldTable_values = read_DatabaseTables.getTableArrays();
			Object[] yieldTable_Name = read_DatabaseTables.get_nameOftable();
			GetParameter_totalValue getParameter_totalValues = new GetParameter_totalValue();
			

			
			
			// Set up problem-------------------------------------------------		//////////////////////////////////////Need to change this is important, better to read from input file //////////////////////////
			// get the "StrataDefinition.csv" file from where this class is located
		
			File file_StrataDefinition = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "StrataDefinition.csv");
			file_StrataDefinition.deleteOnExit();
			try {
				InputStream initialStream = getClass().getResourceAsStream("/StrataDefinition.csv");		//Default definition
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);
				
				 OutputStream outStream = new FileOutputStream(file_StrataDefinition);
				 outStream.write(buffer);
				 
				 initialStream.close();
				 outStream.close();
			} catch (FileNotFoundException e1) {
				System.err.println("Panel Solve Runs - file_StrataDefinition not found error - " + e1.getClass().getName() + ": " + e1.getMessage());
			} catch (IOException e2) {
				System.err.println("Panel Solve Runs - file_StrataDefinition read-write stream error - " + e2.getClass().getName() + ": " + e2.getMessage());
			}
					
			
			Read_Indentifiers read_Identifiers = new Read_Indentifiers(file_StrataDefinition);		//file_StrataDefinition
			List<List<String>> allLayers =  read_Identifiers.get_allLayers();
		
			List<String> layer1 = allLayers.get(0);
			List<String> layer2 = allLayers.get(1);
			List<String> layer3 = allLayers.get(2);
			List<String> layer4 = allLayers.get(3);
			List<String> layer5 = allLayers.get(4);
			List<String> layer6 = allLayers.get(5);
						
			
			
			
			int total_Periods = read.get_total_Periods();
			int total_AgeClasses = total_Periods-1;		//loop from age 1 to age total_AgeClasses (set total_AgeClasses=total_Periods-1)
			int total_methods = 5;
			int total_PB_Prescriptions = 5;
			int total_GS_Prescriptions = 5;
			int total_MS_Prescriptions = 5;
			int SRDage;
			

			List<Double> objlist = new ArrayList<Double>();				//objective coefficient
			List<String> vnamelist = new ArrayList<String>();			//variable name
			List<Double> vlblist = new ArrayList<Double>();				//lower bound
			List<Double> vublist = new ArrayList<Double>();				//upper bound
			List<IloNumVarType> vtlist = new ArrayList<IloNumVarType>();//variable type
			
			int nvars = 0;
			int nV=0;
	
			
			// Declare arrays to keep variables	
			int[] y = new int [total_softConstraints];	//y(j)
			int[] l = new int [total_softConstraints];	//l(j)
			int[] u = new int [total_softConstraints];	//u(j)
			int[] z = new int [total_hardConstraints];	//z(k)
			
			int[][][][][][][] x = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_methods];		//x(s1,s2,s3,s4,s5,s6)(q)
			int[][][][][][][] xNG = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_Periods + 1];						//xNG(s1,s2,s3,s4,s5,s6)(t)
			int[][][][][][][][] xPB = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_PB_Prescriptions][total_Periods + 1];		//xPB(s1,s2,s3,s4,s5,s6)(i,t)
			int[][][][][][][][] xGS = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_GS_Prescriptions][total_Periods + 1];			//xGS(s1,s2,s3,s4,s5,s6)(i,t)
			int[][][][][][][][] xMS = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_PB_Prescriptions][total_Periods + 1];		//xMS(s1,s2,s3,s4,s5,s6)(i,t)
			int[][][][][][][] xEAe = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()]
							[total_Periods + 1];		//xEAe(s1,s2,s3,s4,s5,s6)(t)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][][] xEAeCut = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()]
							[total_Periods + 1][layer5.size()][total_Periods + 1];		//xEAe'(s1,s2,s3,s4,s5,s6)(t)(c)(tR)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			
			int[][][][][][][] xEAr = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses+1];		//xEAr(s1,s2,s3,s4,s5)(t)(a)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][][] xEArCut = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses+1][layer5.size()][total_Periods+1];		//xEAr'(s1,s2,s3,s4,s5)(t)(a)(c)(tR)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			
			
			// Declare arrays to keep Fire variables	//f(s1,s2,s3,s4,s5,s6)(t)
			int[][][][][][][] fire = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][total_Periods + 1][layer5.size()];						
			

			
			//Get the 2 parameter V(s1,s2,s3,s4,s5,s6) and A(s1,s2,s3,s4,s5,s6)
			String Input2_value[][] = read.get_MO_Values();	
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
										//Loop through all modeled_strata to find if the names matched and get the total area and age class
										for (int i = 1; i < read.get_MO_TotalRows(); i++) {	//From 2nd row			
											if (Input2_value[i][0].equals(strataName)) {
												StrataArea[s1][s2][s3][s4][s5][s6] = Double.parseDouble(Input2_value[i][7]);		//area
												
												if (Input2_value[i][read.get_MO_TotalColumns() - 2].toString().equals("notfound")) {
													StartingAge[s1][s2][s3][s4][s5][s6] = 1;		//Assume age class = 1 if not found any yield table for this existing strata
												} else {
													StartingAge[s1][s2][s3][s4][s5][s6] = Integer.parseInt(Input2_value[i][read.get_MO_TotalColumns() - 2]);	//age class
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
			
			//Get the mixed severity fire %		
			double[][] msFirePercent = new double[layer5.size()][layer6.size()];
			int element_Count = 0;
			for (int s5 = 0; s5 < layer5.size(); s5++) {
				for (int s6 = 0; s6 < layer6.size(); s6++) {
					msFirePercent[s5][s6] = msFireProportion[element_Count];
					element_Count++;	
				}
			}

			//Get the SRDrequirementProportion %		
			double[][] SRDrequirementPercent = new double[layer5.size()][layer5.size()];
			int item_Count = 0;
			for (int s5 = 0; s5 < layer5.size(); s5++) {
				for (int ss5 = 0; ss5 < layer5.size(); ss5++) {
					SRDrequirementPercent[s5][ss5] = SRDrequirementProportion[item_Count];
					item_Count++;	
				}
			}
			

			
			
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
			// CREATE OBJECTIVE FUNCTION-------------------------------------------------
					
			// Create soft constraint decision variables y(j)			
			for (int j = 0; j < total_softConstraints; j++) {
				objlist.add((double) 0);
//				vnamelist.add("y(" + j + ")");
				vnamelist.add("y_" + j);
				vlblist.add((double) 0);
				vublist.add(Double.MAX_VALUE);
				vtlist.add(IloNumVarType.Float);
				y[j] = nvars;
				nvars++;				
			}
			nV = nvars;									
			
			// Create soft constraint lower bound variables l(j)			
			for (int j = 0; j < total_softConstraints; j++) {
				objlist.add(softConstraints_LB_Weight[j]);		//add LB weight W|[j]
//				vnamelist.add("l(" + j + ")");
				vnamelist.add("l_" + j);
				vlblist.add((double) 0);
				vublist.add(softConstraints_LB[j]);			//l[j] can be max = L[j]
				vtlist.add(IloNumVarType.Float);
				l[j] = nvars;
				nvars++;				
			}
			nV = nvars;					
			
			// Create soft constraint upper bound variables u(j)			
			for (int j = 0; j < total_softConstraints; j++) {
				objlist.add(softConstraints_UB_Weight[j]);		//add UB weight W||[j]
//				vnamelist.add("u(" + j + ")");
				vnamelist.add("u_" + j);
				vlblist.add((double) 0);
				vublist.add(Double.MAX_VALUE);					//u[j] can be max = any positive number
				vtlist.add(IloNumVarType.Float);
				u[j] = nvars;
				nvars++;				
			}
			nV = nvars;					
			
			// Create hard constraint decision variables z(k)			
			for (int k = 0; k < total_hardConstraints; k++) {
				objlist.add((double) 0);
//				vnamelist.add("z(" + k + ")");
				vnamelist.add("z_" + k);
				vlblist.add(hardConstraints_LB[k]);				// Constraints 4 is set here as LB
				vublist.add(hardConstraints_UB[k]);					// Constraints 5 is set here as UB
				vtlist.add(IloNumVarType.Float);
				z[k] = nvars;
				nvars++;				
			}
			nV = nvars;			
						
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
//											vnamelist.add("x(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + q + ")");
											vnamelist.add("x_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + q);
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
			
			// Create decision variables xNG(s1,s2,s3,s4,s5,s6)(t)	
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
	//										vnamelist.add("xNG(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")");			
											vnamelist.add("xNG_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + t);										
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xNG[s1][s2][s3][s4][s5][s6][t] = nvars;
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
			
			// Create decision variables xPB(s1,s2,s3,s4,s5,s6)(i,t)	
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods; t++) {
												objlist.add((double) 0);
	//											vnamelist.add("xPB(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + i + ")");
												vnamelist.add("xPB_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + i + "," + t);
												vlblist.add((double) 0);
												vublist.add(Double.MAX_VALUE);
												vtlist.add(IloNumVarType.Float);
												xPB[s1][s2][s3][s4][s5][s6][i][t] = nvars;
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
			nV = nvars;			
			
			// Create decision variables xGS(s1,s2,s3,s4,s5,s6)(i,t)		
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods; t++) {
												objlist.add((double) 0);
	//											vnamelist.add("xGS(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + i + ")");
												vnamelist.add("xGS_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6)  + "," + i + "," + t);
												vlblist.add((double) 0);
												vublist.add(Double.MAX_VALUE);
												vtlist.add(IloNumVarType.Float);
												xGS[s1][s2][s3][s4][s5][s6][i][t] = nvars;
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
			nV = nvars;	

			// Create decision variables xMS(s1,s2,s3,s4,s5,s6)(i,t)		
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods; t++) {
												objlist.add((double) 0);
	//											vnamelist.add("xMS(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + i + ")");
												vnamelist.add("xMS_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6)  + "," + i + "," + t);
												vlblist.add((double) 0);
												vublist.add(Double.MAX_VALUE);
												vtlist.add(IloNumVarType.Float);
												xMS[s1][s2][s3][s4][s5][s6][i][t] = nvars;
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
//											vnamelist.add("xEAe(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + t + ")");
											vnamelist.add("xEAe_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + t);
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
			
			// Create decision variables xEAe'(s1,s2,s3,s4,s5,s6)(t)(c)(tR)
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
												int processingAge = t + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + processingAge;						
												if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													for (int tR = 1; tR <= t; tR++) {
														objlist.add((double) 0);
	//													vnamelist.add("xEAe'(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6 + ")(" + t + ")(" + c + ")");
														vnamelist.add("xEAe'_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + t + "," + layer5.get(c) + "," + tR );
														vlblist.add((double) 0);
														vublist.add(Double.MAX_VALUE);
														vtlist.add(IloNumVarType.Float);
														xEAeCut[s1][s2][s3][s4][s5][s6][t][c][tR] = nvars;
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
//											vnamelist.add("xEAr(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + ")(" + t + ")(" + a + ")");
											vnamelist.add("xEAr_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + t + "," + a);							
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
			
			// Create decision variables xEAr'(s1,s2,s3,s4,s5)(t)(a)(c)(tR)
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
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + a;						
												if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													for (int tR = t-a+1; tR <= t; tR++) {
														objlist.add((double) 0);
	//													vnamelist.add("xEAr'(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + ")(" + t + ")(" + a + ")(" + c + ")");
														vnamelist.add("xEAr'_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + t + "," + a + "," + layer5.get(c) + "," + tR);	
														vlblist.add((double) 0);
														vublist.add(Double.MAX_VALUE);
														vtlist.add(IloNumVarType.Float);
														xEArCut[s1][s2][s3][s4][s5][t][a][c][tR] = nvars;
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
			}
			nV = nvars;					
			
			
			//-----------------------Fire variables
			// Create decision variables fire(s1,s2,s3,s4,s5)(t,c)	
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 1; t <= total_Periods; t++) {
										for (int c = 0; c < layer5.size(); c++) {
											objlist.add((double) 0);			
											vnamelist.add("fire_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + t + "," + layer5.get(c));										
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											fire[s1][s2][s3][s4][s5][t][c] = nvars;
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
			
			// Constraints 2-------------------------------------------------
			List<List<Integer>> c2_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c2_valuelist = new ArrayList<List<Double>>();
			List<Double> c2_lblist = new ArrayList<Double>();	
			List<Double> c2_ublist = new ArrayList<Double>();
			int c2_num =0;
			
			for (int j = 0; j < total_softConstraints; j++) {
				// Add constraint
				c2_indexlist.add(new ArrayList<Integer>());
				c2_valuelist.add(new ArrayList<Double>());

				// Add y(j)
				c2_indexlist.get(c2_num).add(y[j]);
				c2_valuelist.get(c2_num).add((double) 1);
				
				// Add l(j)
				c2_indexlist.get(c2_num).add(l[j]);
				c2_valuelist.get(c2_num).add((double) 1);

				// add bounds
				c2_lblist.add(softConstraints_LB[j]);	// Lower bound of the soft constraint
				c2_ublist.add(Double.MAX_VALUE);		// Upper bound set to infinite
				c2_num++;
			}			
			
			double[] c2_lb = Stream.of(c2_lblist.toArray(new Double[c2_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c2_ub = Stream.of(c2_ublist.toArray(new Double[c2_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c2_index = new int[c2_num][];
			double[][] c2_value = new double[c2_num][];
		
			for (int i = 0; i < c2_num; i++) {
				c2_index[i] = new int[c2_indexlist.get(i).size()];
				c2_value[i] = new double[c2_indexlist.get(i).size()];
				for (int j = 0; j < c2_indexlist.get(i).size(); j++) {
					c2_index[i][j] = c2_indexlist.get(i).get(j);
					c2_value[i][j] = c2_valuelist.get(i).get(j);			
				}
			}									
			
	
			// Constraints 3-------------------------------------------------
			List<List<Integer>> c3_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c3_valuelist = new ArrayList<List<Double>>();
			List<Double> c3_lblist = new ArrayList<Double>();	
			List<Double> c3_ublist = new ArrayList<Double>();
			int c3_num =0;
			
			for (int j = 0; j < total_softConstraints; j++) {
				// Add constraint
				c3_indexlist.add(new ArrayList<Integer>());
				c3_valuelist.add(new ArrayList<Double>());

				// Add y(j)
				c3_indexlist.get(c3_num).add(y[j]);
				c3_valuelist.get(c3_num).add((double) 1);
				
				// Add -u(j)
				c3_indexlist.get(c3_num).add(u[j]);
				c3_valuelist.get(c3_num).add((double) -1);

				// add bounds
				c3_lblist.add((double) 0);			// Lower bound set to 0	because y[j]>=u[j]
				c3_ublist.add(softConstraints_UB[j]);		// Upper bound of the soft constraint
				c3_num++;
			}			
			
			double[] c3_lb = Stream.of(c3_lblist.toArray(new Double[c3_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c3_ub = Stream.of(c3_ublist.toArray(new Double[c3_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c3_index = new int[c3_num][];
			double[][] c3_value = new double[c3_num][];
		
			for (int i = 0; i < c3_num; i++) {
				c3_index[i] = new int[c3_indexlist.get(i).size()];
				c3_value[i] = new double[c3_indexlist.get(i).size()];
				for (int j = 0; j < c3_indexlist.get(i).size(); j++) {
					c3_index[i][j] = c3_indexlist.get(i).get(j);
					c3_value[i][j] = c3_valuelist.get(i).get(j);			
				}
			}										
			
			
			
			// Constraints 4 and 5-------------------------------------------------
			// are set as the bounds of variables
			
			
			
			// Constraints 6-------------------------------------------------
			List<List<Integer>> c6_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c6_valuelist = new ArrayList<List<Double>>();
			List<Double> c6_lblist = new ArrayList<Double>();	
			List<Double> c6_ublist = new ArrayList<Double>();
			int c6_num =0;
			
			
			//6a
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										// Add constraint
										c6_indexlist.add(new ArrayList<Integer>());
										c6_valuelist.add(new ArrayList<Double>());

										for (int q = 0; q < total_methods; q++) {
											// Add x(s1,s2,s3,s4,s5,s6)(q)
											c6_indexlist.get(c6_num).add(x[s1][s2][s3][s4][s5][s6][q]);
											c6_valuelist.get(c6_num).add((double) 1);
										}

										// add bounds
										c6_lblist.add(StrataArea[s1][s2][s3][s4][s5][s6]);
										c6_ublist.add(StrataArea[s1][s2][s3][s4][s5][s6]);
										c6_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			
			//6b
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {		
										// Add constraint
										c6_indexlist.add(new ArrayList<Integer>());
										c6_valuelist.add(new ArrayList<Double>());

										// Add x(s1,s2,s3,s4,s5,s6)(4)
										c6_indexlist.get(c6_num).add(x[s1][s2][s3][s4][s5][s6][4]);
										c6_valuelist.get(c6_num).add((double) 1);

										// add bounds
										c6_lblist.add((double) msFirePercent[s5][s6]/100*StrataArea[s1][s2][s3][s4][s5][s6]);
										c6_ublist.add((double) msFirePercent[s5][s6]/100*StrataArea[s1][s2][s3][s4][s5][s6]);
										c6_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			//6c
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {
										for (int t = 1; t <= total_Periods; t++) {
											// Add constraint
											c6_indexlist.add(new ArrayList<Integer>());
											c6_valuelist.add(new ArrayList<Double>());
	
											// Add x(s1,s2,s3,s4,s5,s6)(4)
											c6_indexlist.get(c6_num).add(x[s1][s2][s3][s4][s5][s6][4]);
											c6_valuelist.get(c6_num).add((double) 1);
											
											//Add - sigma(i) xMS(s1,s2,s3,s4,s5,s6)[i][t]
											for (int i = 0; i < total_MS_Prescriptions; i++) {
												c6_indexlist.get(c6_num).add(xMS[s1][s2][s3][s4][s5][s6][i][t]);
												c6_valuelist.get(c6_num).add((double) -1);
											}
	
											// add bounds
											c6_lblist.add((double) 0);
											c6_ublist.add((double) 0);
											c6_num++;
										}									
									}
								}
							}
						}
					}
				}
			}				
			
			
			double[] c6_lb = Stream.of(c6_lblist.toArray(new Double[c6_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c6_ub = Stream.of(c6_ublist.toArray(new Double[c6_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c6_index = new int[c6_num][];
			double[][] c6_value = new double[c6_num][];
		
			for (int i = 0; i < c6_num; i++) {
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
			int c7_num =0;
			
			
			//7a
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
										c7_indexlist.get(c7_num).add(x[s1][s2][s3][s4][s5][s6][0]);
										c7_valuelist.get(c7_num).add((double) 1);
										
										//Add -xNG(s1,s2,s3,s4,s5,s6)(1)
										c7_indexlist.get(c7_num).add(xNG[s1][s2][s3][s4][s5][s6][1]);
										c7_valuelist.get(c7_num).add((double) -1);
								
										//add bounds
										c7_lblist.add((double) 0);
										c7_ublist.add((double) 0);
										c7_num++;
									}
								}
							}
						}
					}
				}
			}		
			
			
			//7b
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
											c7_indexlist.add(new ArrayList<Integer>());
											c7_valuelist.add(new ArrayList<Double>());
											
											//Add xNG(s1,s2,s3,s4,s5,s6)(t)
											SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
											if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
											
											c7_indexlist.get(c7_num).add(xNG[s1][s2][s3][s4][s5][s6][t]);
											c7_valuelist.get(c7_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100
									
											//Add -xNG(s1,s2,s3,s4,s5,s6)(t+1)
											c7_indexlist.get(c7_num).add(xNG[s1][s2][s3][s4][s5][s6][t+1]);
											c7_valuelist.get(c7_num).add((double) -1);											
																					
											//add bounds
											c7_lblist.add((double) 0);
											c7_ublist.add((double) 0);
											c7_num++;
										}
									}
								}
							}
						}
					}
				}
			}								
						
			
			double[] c7_lb = Stream.of(c7_lblist.toArray(new Double[c7_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c7_ub = Stream.of(c7_ublist.toArray(new Double[c7_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c7_index = new int[c7_num][];
			double[][] c7_value = new double[c7_num][];
		
			for (int i = 0; i < c7_num; i++) {
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
			int c8_num =0;
			
			
			//8a
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
										c8_indexlist.get(c8_num).add(x[s1][s2][s3][s4][s5][s6][1]);
										c8_valuelist.get(c8_num).add((double) 1);
										
										//Add - sigma(i) xPB(s1,s2,s3,s4,s5,s6)[i][1]
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											c8_indexlist.get(c8_num).add(xPB[s1][s2][s3][s4][s5][s6][i][1]);
											c8_valuelist.get(c8_num).add((double) -1);
										}
										
										//add bounds
										c8_lblist.add((double) 0);
										c8_ublist.add((double) 0);
										c8_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			
			//8b
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {	
										for (int i = 0; i < total_PB_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods-1; t++) {
												//Add constraint
												c8_indexlist.add(new ArrayList<Integer>());
												c8_valuelist.add(new ArrayList<Double>());
												
												//Add xPB(s1,s2,s3,s4,s5,s6)[i][t]
												SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
												
												c8_indexlist.get(c8_num).add(xPB[s1][s2][s3][s4][s5][s6][i][t]);
												c8_valuelist.get(c8_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100
												
												//Add -xPB(s1,s2,s3,s4,s5,s6)[i][t+1]
												c8_indexlist.get(c8_num).add(xPB[s1][s2][s3][s4][s5][s6][i][t+1]);
												c8_valuelist.get(c8_num).add((double) -1);
												
												//add bounds
												c8_lblist.add((double) 0);
												c8_ublist.add((double) 0);
												c8_num++;
											}
										}
									}
								}
							}
						}
					}
				}
			}	
			
					
			double[] c8_lb = Stream.of(c8_lblist.toArray(new Double[c8_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c8_ub = Stream.of(c8_ublist.toArray(new Double[c8_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c8_index = new int[c8_num][];
			double[][] c8_value = new double[c8_num][];
		
			for (int i = 0; i < c8_num; i++) {
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
			int c9_num =0;
			
			
			//9a
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
										c9_indexlist.get(c9_num).add(x[s1][s2][s3][s4][s5][s6][2]);
										c9_valuelist.get(c9_num).add((double) 1);							
										
										//Add -xGS(s1,s2,s3,s4,s5,s6)[i][1]
										for (int i = 0; i < total_GS_Prescriptions; i++) {
											c9_indexlist.get(c9_num).add(xGS[s1][s2][s3][s4][s5][s6][i][1]);
											c9_valuelist.get(c9_num).add((double) -1);
										}
										
										// add bounds
										c9_lblist.add((double) 0);
										c9_ublist.add((double) 0);
										c9_num++;
									}
								}
							}
						}
					}
				}
			}	

			//9b
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (modeled_strata.contains(strataName)) {	
										for (int i = 0; i < total_GS_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods-1; t++) {
												//Add constraint
												c9_indexlist.add(new ArrayList<Integer>());
												c9_valuelist.add(new ArrayList<Double>());
												
												//Add xGS(s1,s2,s3,s4,s5,s6)[i][t]
												SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
									
												c9_indexlist.get(c9_num).add(xGS[s1][s2][s3][s4][s5][s6][i][t]);
												c9_valuelist.get(c9_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100
												
												//Add -xGS(s1,s2,s3,s4,s5,s6)[i][t+1]
												c9_indexlist.get(c9_num).add(xGS[s1][s2][s3][s4][s5][s6][i][t+1]);
												c9_valuelist.get(c9_num).add((double) -1);												
												
												//add bounds
												c9_lblist.add((double) 0);
												c9_ublist.add((double) 0);
												c9_num++;
											}
										}
									}
								}
							}
						}
					}
				}
			}	
			
			
			double[] c9_lb = Stream.of(c9_lblist.toArray(new Double[c9_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c9_ub = Stream.of(c9_ublist.toArray(new Double[c9_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c9_index = new int[c9_num][];
			double[][] c9_value = new double[c9_num][];
		
			for (int i = 0; i < c9_num; i++) {
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
			int c10_num =0;
			
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
										c10_indexlist.get(c10_num).add(x[s1][s2][s3][s4][s5][s6][3]);
										c10_valuelist.get(c10_num).add((double) 1);							
									
										//Add - xEAe(s1,s2,s3,s4,s5,s6)[1]	
										c10_indexlist.get(c10_num).add(xEAe[s1][s2][s3][s4][s5][s6][1]);
										c10_valuelist.get(c10_num).add((double) -1);
										
										//Add - sigma(t,c) xEAe'(s1,s2,s3,s4,s5,s6)[t][c][1]	
										for (int t = 1; t <= total_Periods; t++) {
											for (int c = 0; c < layer5.size(); c++) {
												int processingAge = t + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + processingAge;						
												if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													c10_indexlist.get(c10_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t][c][1]);
													c10_valuelist.get(c10_num).add((double) -1);
												}
											}	
										}
										
										//add bounds
										c10_lblist.add((double) 0);
										c10_ublist.add((double) 0);
										c10_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			double[] c10_lb = Stream.of(c10_lblist.toArray(new Double[c10_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c10_ub = Stream.of(c10_ublist.toArray(new Double[c10_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c10_index = new int[c10_num][];
			double[][] c10_value = new double[c10_num][];
		
			for (int i = 0; i < c10_num; i++) {
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
			int c11_num =0;
			
			//11a
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
											c11_indexlist.add(new ArrayList<Integer>());
											c11_valuelist.add(new ArrayList<Double>());
											
											//Add xEAe(s1,s2,s3,s4,s5,s6)[t]
											SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
											if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
								
											c11_indexlist.get(c11_num).add(xEAe[s1][s2][s3][s4][s5][s6][t]);
											c11_valuelist.get(c11_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100											
											
											//Add - xEAe(s1,s2,s3,s4,s5,s6)[t+1]		
											c11_indexlist.get(c11_num).add(xEAe[s1][s2][s3][s4][s5][s6][t+1]);
											c11_valuelist.get(c11_num).add((double) -1);																					
											
											//add bounds
											c11_lblist.add((double) 0);
											c11_ublist.add((double) 0);
											c11_num++;
										}
									}
								}
							}
						}
					}
				}
			}							
			
			//11b
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
												int processingAge = t + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + processingAge;					
												if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													for (int tR = 1; tR <= t-1; tR++) {
														//Add constraint
														c11_indexlist.add(new ArrayList<Integer>());
														c11_valuelist.add(new ArrayList<Double>());
														
														//Add xEAe'(s1,s2,s3,s4,s5,s6)[t][c][tR]
														SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + tR - 1; 
														if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
												
														c11_indexlist.get(c11_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t][c][tR]);
														c11_valuelist.get(c11_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100																											
														
														//Add - xEAe'(s1,s2,s3,s4,s5,s6)[t][c][tR+1]		
														c11_indexlist.get(c11_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t][c][tR+1]);
														c11_valuelist.get(c11_num).add((double) -1);																					
														
														//add bounds
														c11_lblist.add((double) 0);
														c11_ublist.add((double) 0);
														c11_num++;
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
			}	
			
			//11c
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
										c11_indexlist.get(c11_num).add(xEAe[s1][s2][s3][s4][s5][s6][total_Periods]);
										c11_valuelist.get(c11_num).add((double) 1);
																									
										//add bounds
										c11_lblist.add((double) 0);
										c11_ublist.add((double) 0);
										c11_num++;
									}
								}
							}
						}
					}
				}
			}	
			
			
			double[] c11_lb = Stream.of(c11_lblist.toArray(new Double[c11_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c11_ub = Stream.of(c11_ublist.toArray(new Double[c11_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c11_index = new int[c11_num][];
			double[][] c11_value = new double[c11_num][];
		
			for (int i = 0; i < c11_num; i++) {
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
			int c12_num =0;
			
			//12a
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 1; t <= total_Periods; t++) {
										//Add constraint
										c12_indexlist.add(new ArrayList<Integer>());
										c12_valuelist.add(new ArrayList<Double>());
										
										//Add sigma(c)	fire[s1][s2][s3][s4][s5][t][c]
										for (int c = 0; c < layer5.size(); c++) {
											c12_indexlist.get(c12_num).add(fire[s1][s2][s3][s4][s5][t][c]);
											c12_valuelist.get(c12_num).add((double) 1);	
										}
										
										
										//Add - sigma(s6)	xNG[s1][s2][s3][s4][s5][s6][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
											if (modeled_strata.contains(strataName2)) {
												SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
										
												c12_indexlist.get(c12_num).add(xNG[s1][s2][s3][s4][s5][s6][t]);
												c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100	
											}
										}
										
										//Add - sigma(s6)(i)	xPB[s1][s2][s3][s4][s5][s6][i][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											for (int i = 0; i < total_PB_Prescriptions; i++) {
												String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
												if (modeled_strata.contains(strataName2)) {
													SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
											
													c12_indexlist.get(c12_num).add(xPB[s1][s2][s3][s4][s5][s6][i][t]);
													c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100	
												}
											}
										}
																
										//Add - sigma(s6)(i)	xGS[s1][s2][s3][s4][s5][s6][i][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											for (int i = 0; i < total_GS_Prescriptions; i++) {
												String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
												if (modeled_strata.contains(strataName2)) {
													SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
											
													c12_indexlist.get(c12_num).add(xGS[s1][s2][s3][s4][s5][s6][i][t]);
													c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100	
												}
											}
										}
																			
										//Add - sigma(s6)	xEAe[s1][s2][s3][s4][s5][s6][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
											if (modeled_strata.contains(strataName2)) {
												SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
										
												c12_indexlist.get(c12_num).add(xEAe[s1][s2][s3][s4][s5][s6][t]);
												c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100	
											}
										}						
							
										// Add - sigma(s6)(t'')(c'')   xEAe'(s1,s2,s3,s4,s5,s6)(t'')(c'')(tR)
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
											if (modeled_strata.contains(strataName2)) {
												for (int ttt = t; ttt <= total_Periods; ttt++) {		// t''
													for (int ccc = 0; ccc < layer5.size(); ccc++) {		//c''
														int processingAge = ttt + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
														String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(ccc) + " " + processingAge;								
														if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
															for (int tR = 1; tR <= ttt; tR++) {					
																if (tR == t) {
																	SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + tR - 1; 
																	if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
															
																	c12_indexlist.get(c12_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][ttt][ccc][tR]);
																	c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100	
																}						
															}
														}
													}
												}
											}
										}					
										
										//Add - sigma(a)	xEAr[s1][s2][s3][s4][s5][t][a]
										if (t >= 2) {
											for (int a = 1; a <= t - 1; a++) {
												SRDage = a; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
												
												c12_indexlist.get(c12_num).add(xEAr[s1][s2][s3][s4][s5][t][a]);
												c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100	
											}
										}

										//Add - sigma(t'')(a)(c'')	xEAr'[s1][s2][s3][s4][s5][t''][a][c''][tR=t]
										if (t >= 2) {
											for (int ttt = t; ttt <= total_Periods; ttt++) { // t''
												for (int a = 1; a <= ttt - 1; a++) {
													for (int ccc = 0; ccc < layer5.size(); ccc++) {		//c''
														String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(ccc) + " " + a;						
														if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
															for (int tR = 1; tR <= ttt; tR++) {					
																if (tR == t && tR==ttt-a+1) {
																	SRDage = a - ttt + tR; 
																	if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %																									
																	
																	c12_indexlist.get(c12_num).add(xEArCut[s1][s2][s3][s4][s5][ttt][a][ccc][tR]);
																	c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100																				
																}
															}
														}
													}
												}
											}
										}															
										
																											

										// add bounds
										c12_lblist.add((double) 0);
										c12_ublist.add((double) 0);
										c12_num++;						
									}
								}
							}
						}
					}
				}
			}					
			
			//12b
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 1; t <= total_Periods; t++) {
										for (int c = 0; c < layer5.size(); c++) {
											//Add constraint
											c12_indexlist.add(new ArrayList<Integer>());
											c12_valuelist.add(new ArrayList<Double>());
											
											//Add fire[s1][s2][s3][s4][s5][t][c]	*		1-P(s5,c')/100
											c12_indexlist.get(c12_num).add(fire[s1][s2][s3][s4][s5][t][c]);
											c12_valuelist.get(c12_num).add((double) 1-SRDrequirementPercent[s5][c]/100);	//Group the parameters here for the case c = c'	
											
											//Add fire[s1][s2][s3][s4][s5][t][c']
											for (int cc = 0; cc < layer5.size(); cc++) {
												if (cc != c) {
													c12_indexlist.get(c12_num).add(fire[s1][s2][s3][s4][s5][t][cc]);
													c12_valuelist.get(c12_num).add((double) -SRDrequirementPercent[s5][c]/100);
												}
											}
											
											// add bounds
											c12_lblist.add((double) 0);
											c12_ublist.add((double) 0);
											c12_num++;	
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
			int[][] c12_index = new int[c12_num][];
			double[][] c12_value = new double[c12_num][];
		
			for (int i = 0; i < c12_num; i++) {
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
			int c13_num =0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int t = 1; t <= total_Periods - 1; t++) {
								String strataName1 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
								if (modeled_strata_withoutSizeClassandCoverType.contains(strataName1)) {	
									for (int c = 0; c < layer5.size(); c++) {
										
										//Add constraint
										c13_indexlist.add(new ArrayList<Integer>());
										c13_valuelist.add(new ArrayList<Double>());
										
										
										//FIRE VARIABLES
										//Add sigma(s5) fire(s1,s2,s3,s4,s5)[t][c]
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											String thisCoverTypeconversion = layer5.get(s5) + " " + layer5.get(c);						
											if (coverTypeConversions.contains(thisCoverTypeconversion)) {
												c13_indexlist.get(c13_num).add(fire[s1][s2][s3][s4][s5][t][c]);
												c13_valuelist.get(c13_num).add((double) 1);
											}
										}
										
			
										//NON-FIRE VARIABLES
										//Add sigma(s5)(s6) xEAe'(s1,s2,s3,s4,s5,s6)[t][c][t]
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int s6 = 0; s6 < layer6.size(); s6++) {
												String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
												int processingAge = t + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + processingAge;						
												if (modeled_strata.contains(strataName) && coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													c13_indexlist.get(c13_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t][c][t]);
													c13_valuelist.get(c13_num).add((double) 1);
												}
											}
										}
										
										//Add sigma(s5)(a) xEAr'(s1,s2,s3,s4,s5)[t][a][c][tR=t] only if period t>=2
										if (t >= 2) {
											for (int s5 = 0; s5 < layer5.size(); s5++) {
												for (int a = 1; a <= t - 1; a++) {
													String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + a;						
													if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
														c13_indexlist.get(c13_num).add(xEArCut[s1][s2][s3][s4][s5][t][a][c][t]);
														c13_valuelist.get(c13_num).add((double) 1);
													}
												}
											}
										}
								
										//Add -xEAr(s1,s2,s3,s4,s5=c)[t+1][1]
										c13_indexlist.get(c13_num).add(xEAr[s1][s2][s3][s4][c][t + 1][1]);
										c13_valuelist.get(c13_num).add((double) -1);
																			
										//Add -sigma(t' c') xEAr'(s1,s2,s3,s4,s5=c)[t'][a'=t'-t][c'][tR=t+1]
										for (int tt = t+1; tt <= total_Periods; tt++) {
											for (int cc = 0; cc < layer5.size(); cc++) {
												int processingAge = tt - t;	
												String thisCoverTypeconversion_and_RotationAge = layer5.get(c) + " " + layer5.get(cc) + " " + processingAge;						
												if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													c13_indexlist.get(c13_num).add(xEArCut[s1][s2][s3][s4][c][tt][tt-t][cc][t+1]);
													c13_valuelist.get(c13_num).add((double) -1);
												}
											}
										}
										
										// add bounds
										c13_lblist.add((double) 0);
										c13_ublist.add((double) 0);
										c13_num++;
									}
								}
							}
						}
					}
				}
			}
			
			double[] c13_lb = Stream.of(c13_lblist.toArray(new Double[c13_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c13_ub = Stream.of(c13_ublist.toArray(new Double[c13_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c13_index = new int[c13_num][];
			double[][] c13_value = new double[c13_num][];
		
			for (int i = 0; i < c13_num; i++) {
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
			int c14_num =0;
			
			//14a
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
											c14_indexlist.add(new ArrayList<Integer>());
											c14_valuelist.add(new ArrayList<Double>());
											
											//Add xEAr(s1,s2,s3,s4,s5)[t][a]
											SRDage = a; 
											if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
											
											c14_indexlist.get(c14_num).add(xEAr[s1][s2][s3][s4][s5][t][a]);
											c14_valuelist.get(c14_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100
											
											//Add - xEAr(s1,s2,s3,s4,s5)[t+1][a+1]
											c14_indexlist.get(c14_num).add(xEAr[s1][s2][s3][s4][s5][t+1][a+1]);
											c14_valuelist.get(c14_num).add((double) -1);										
											
											//add bounds
											c14_lblist.add((double) 0);
											c14_ublist.add((double) 0);
											c14_num++;
										}
									}
								}
							}
						}
					}
				}
			}				
			
			//14b
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (modeled_strata_withoutSizeClassandCoverType.contains(strataName)) {		
								for (int s5 = 0; s5 < layer5.size(); s5++) {
									for (int t = 2; t <= total_Periods; t++) {
										for (int a = 1; a <= t-1; a++) {									
											for (int c = 0; c < layer5.size(); c++) {
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + a;						
												if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
													for (int tR = t-a+1; tR <= t-1; tR++) {
														//Add constraint
														c14_indexlist.add(new ArrayList<Integer>());
														c14_valuelist.add(new ArrayList<Double>());
														
														//Add xEAr'(s1,s2,s3,s4,s5)[t][a][c][tR]
														SRDage = a - t + tR; 
														if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
														
														c14_indexlist.get(c14_num).add(xEArCut[s1][s2][s3][s4][s5][t][a][c][tR]);
														c14_valuelist.get(c14_num).add((double) 1 - SRD_percent[s5][SRDage]/100);		//SR Fire loss Rate = P(s5,a)/100													
												
														//Add - xEAr'(s1,s2,s3,s4,s5,s6)[t][a][c][tR+1]		
														c14_indexlist.get(c14_num).add(xEArCut[s1][s2][s3][s4][s5][t][a][c][tR+1]);
														c14_valuelist.get(c14_num).add((double) -1);																					
														
														//add bounds
														c14_lblist.add((double) 0);
														c14_ublist.add((double) 0);
														c14_num++;									
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
			}															
				
			double[] c14_lb = Stream.of(c14_lblist.toArray(new Double[c14_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c14_ub = Stream.of(c14_ublist.toArray(new Double[c14_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c14_index = new int[c14_num][];
			double[][] c14_value = new double[c14_num][];
		
			for (int i = 0; i < c14_num; i++) {
				c14_index[i] = new int[c14_indexlist.get(i).size()];
				c14_value[i] = new double[c14_indexlist.get(i).size()];
				for (int j = 0; j < c14_indexlist.get(i).size(); j++) {
					c14_index[i][j] = c14_indexlist.get(i).get(j);
					c14_value[i][j] = c14_valuelist.get(i).get(j);			
				}
			}				
			
			
			// Constraints 15------------------------------------------------- for y(j) and z(k)
			List<List<Integer>> c15_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c15_valuelist = new ArrayList<List<Double>>();
			List<Double> c15_lblist = new ArrayList<Double>();	
			List<Double> c15_ublist = new ArrayList<Double>();
			int c15_num =0;
			
			int current_softConstraint =0;
			int current_hardConstraint =0;	
				
			//Add -y(j) + user constraint = 0		or 			-z(k) + user constraint = 0
			for (int i = 1; i < total_softConstraints + total_hardConstraints + 1; i++) {	//Loop from 1 because the first row of the userConstraint file is just title
				
				//Get the parameter indexes list
				List<String> parameters_indexes_list = read.get_Parameters_indexes_list(i);
				//Get the dynamic identifiers indexes list
				List<String> all_dynamicIdentifiers_columnIndexes = read.get_all_dynamicIdentifiers_columnsIndexes_in_row(i);
				List<List<String>> all_dynamicIdentifiers = read.get_all_dynamicIdentifiers_in_row(i);
				
				
						
				//Add constraint
				c15_indexlist.add(new ArrayList<Integer>());
				c15_valuelist.add(new ArrayList<Double>());

				//Add -y(j) or -z(k)
				if (UC_Value[i][1].equals("SOFT")) {
					c15_indexlist.get(c15_num).add(y[current_softConstraint]);
					c15_valuelist.get(c15_num).add((double) -1);
					current_softConstraint++;
				}
				
				if (UC_Value[i][1].equals("HARD")) {
					c15_indexlist.get(c15_num).add(z[current_hardConstraint]);
					c15_valuelist.get(c15_num).add((double) -1);
					current_hardConstraint++;
				}
									
				
				//Add user constraint - variables and parameters------------------------------------
				List<String> static_SilvivulturalMethods = read.get_static_SilvivulturalMethods(i);
				List<String> static_timePeriods = read.get_static_timePeriods(i);
				List<Integer> integer_static_timePeriods = static_timePeriods.stream().map(Integer::parseInt).collect(Collectors.toList());
				
				List<String> static_strata = read.get_static_strata(i);
				List<String> static_strata_withoutSizeClassandCoverType = read.get_static_strata_withoutSizeClassandCoverType(i);
				
								
				//Add xNG and xEAe				currently using Eq. 11 so we don't need to add xEAe, only add xNG
				if (static_SilvivulturalMethods.contains("NG")) {	
					for (int s1 = 0; s1 < layer1.size(); s1++) {
						for (int s2 = 0; s2 < layer2.size(); s2++) {
							for (int s3 = 0; s3 < layer3.size(); s3++) {
								for (int s4 = 0; s4 < layer4.size(); s4++) {
									for (int s5 = 0; s5 < layer5.size(); s5++) {
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
											if (modeled_strata.contains(strataName) && static_strata.contains(strataName)) {											
												if (integer_static_timePeriods.size() > 0) {	
													for (int t : integer_static_timePeriods) {		//Loop all periods
														if (t <= total_Periods) {
															//Find all parameter match the t and add them all to parameter
															/*	Table Name = s5 + s6 convert then + method + timingChoice
															 * Table column indexes for the parameters is identified by parameters_indexes_list
															 * dynamic identifiers are identified by "all_dynamicIdentifiers_columnIndexes" & "all_dynamicIdentifiers"
															 * Table row index = t - 1  
															 */												
															double para_value = getParameter_totalValues.getValue(layer5.get(s5), layer6.get(s6), "A", "0", 
																	yieldTable_Name, yieldTable_values, parameters_indexes_list,
																	all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers, t-1);

															//Add xNG(s1,s2,s3,s4,s5,s6)(t)
															c15_indexlist.get(c15_num).add(xNG[s1][s2][s3][s4][s5][s6][t]);
															c15_valuelist.get(c15_num).add((double) para_value);
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
				}	

				
				
				//Add xPB[s1][s2][s3][s4][s5][s6][ii]			
				if (static_SilvivulturalMethods.contains("PB")) {		
					for (int s1 = 0; s1 < layer1.size(); s1++) {
						for (int s2 = 0; s2 < layer2.size(); s2++) {
							for (int s3 = 0; s3 < layer3.size(); s3++) {
								for (int s4 = 0; s4 < layer4.size(); s4++) {
									for (int s5 = 0; s5 < layer5.size(); s5++) {
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
											if (modeled_strata.contains(strataName) && static_strata.contains(strataName)) {
												for (int ii = 0; ii < total_PB_Prescriptions; ii++) {
													if (integer_static_timePeriods.size() > 0) {	
														for (int t : integer_static_timePeriods) {		//Loop all periods
															if (t <= total_Periods) {
																//Find all parameter match the t and add them all to parameter
																double para_value = getParameter_totalValues.getValue(layer5.get(s5), layer6.get(s6), "D", Integer.toString(ii), 
																		yieldTable_Name, yieldTable_values, parameters_indexes_list,
																		all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers, t-1);
																
																//Add xPB[s1][s2][s3][s4][s5][s6][ii][t]
																c15_indexlist.get(c15_num).add(xPB[s1][s2][s3][s4][s5][s6][ii][t]);
																c15_valuelist.get(c15_num).add((double) para_value);	
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
					}							
				}	
								
			
				//Add xGS[s1][s2][s3][s4][s5][s6][ii]			
				if (static_SilvivulturalMethods.contains("GS")) {		
					for (int s1 = 0; s1 < layer1.size(); s1++) {
						for (int s2 = 0; s2 < layer2.size(); s2++) {
							for (int s3 = 0; s3 < layer3.size(); s3++) {
								for (int s4 = 0; s4 < layer4.size(); s4++) {
									for (int s5 = 0; s5 < layer5.size(); s5++) {
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
											if (modeled_strata.contains(strataName) && static_strata.contains(strataName)) {
												for (int ii = 0; ii < total_PB_Prescriptions; ii++) {
													if (integer_static_timePeriods.size() > 0) {	
														for (int t : integer_static_timePeriods) {		//Loop all periods
															if (t <= total_Periods) {
																//Find all parameter match the t and add them all to parameter
																double para_value = getParameter_totalValues.getValue(layer5.get(s5), layer6.get(s6), "C", Integer.toString(ii), 
																		yieldTable_Name, yieldTable_values, parameters_indexes_list,
																		all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers, t-1);
																
																//Add xGS[s1][s2][s3][s4][s5][s6][ii][t]
																c15_indexlist.get(c15_num).add(xGS[s1][s2][s3][s4][s5][s6][ii][t]);
																c15_valuelist.get(c15_num).add((double) para_value);
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
					}							
				}						
				
				
				//Add xEAe'  xEAr'  xEAr
				if (static_SilvivulturalMethods.contains("EA")) {		
							
					// Add xEAe'(s1,s2,s3,s4,s5,s6)(t)(c)(tR)
					for (int s1 = 0; s1 < layer1.size(); s1++) {
						for (int s2 = 0; s2 < layer2.size(); s2++) {
							for (int s3 = 0; s3 < layer3.size(); s3++) {
								for (int s4 = 0; s4 < layer4.size(); s4++) {
									for (int s5 = 0; s5 < layer5.size(); s5++) {
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
											if (modeled_strata.contains(strataName) && static_strata.contains(strataName)) {
												for (int t = 1; t <= total_Periods; t++) {		//Loop from period tR to T
													for (int c = 0; c < layer5.size(); c++) {	
														int processingAge = t + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
														String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + processingAge;	
														if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
															if (integer_static_timePeriods.size() > 0) {	
																for (int tR : integer_static_timePeriods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
																	if (tR<=t) {
																		//Find all parameter match the t and add them all to parameter
																		double para_value = getParameter_totalValues.getValue(layer5.get(s5), layer6.get(s6), "B", "AgeClassShouldBeHere", 
																				yieldTable_Name, yieldTable_values, parameters_indexes_list,
																				all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers, tR-1);
																		
																		//Add xEAe'(s1,s2,s3,s4,s5,s6)(t)(c)(tR)	final cut at t but we need parameter at time tR
																		c15_indexlist.get(c15_num).add(xEAeCut[s1][s2][s3][s4][s5][s6][t][c][tR]);
																		c15_valuelist.get(c15_num).add((double) para_value);	
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
							}
						}
					}						
				
					// Add xEAr'(s1,s2,s3,s4,s5)(t)(a)(c)(tR)  note t>=2 --> tR>=t-a+1
					for (int s1 = 0; s1 < layer1.size(); s1++) {
						for (int s2 = 0; s2 < layer2.size(); s2++) {
							for (int s3 = 0; s3 < layer3.size(); s3++) {
								for (int s4 = 0; s4 < layer4.size(); s4++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
									if (modeled_strata_withoutSizeClassandCoverType.contains(strataName) && static_strata_withoutSizeClassandCoverType.contains(strataName)) {
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int t = 2; t <= total_Periods; t++) {
												for (int a = 1; a <= t - 1; a++) {
													for (int c = 0; c < layer5.size(); c++) {
														String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + a;						
														if (coverTypeConversions_and_RotationAges.contains(thisCoverTypeconversion_and_RotationAge)) {
															if (integer_static_timePeriods.size() > 0) {	
																for (int tR : integer_static_timePeriods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
																	if (tR >= (t-a+1) && tR<=t) {
																		//Find all parameter match the t and add them all to parameter
																		double para_value = getParameter_totalValues.getValue(layer5.get(s5), "notNeeded", "B", "AgeClassShouldBeHere", 
																				yieldTable_Name, yieldTable_values, parameters_indexes_list,
																				all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers, tR-1);
																		
																		//Add xEAr'(s1,s2,s3,s4,s5)(t)(a)(c)(tR)		final cut at t but we need parameter at time tR
																		c15_indexlist.get(c15_num).add(xEArCut[s1][s2][s3][s4][s5][t][a][c][tR]);
																		c15_valuelist.get(c15_num).add((double) para_value);	
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
							}
						}
					}		
					
					
					//Add xEAr(s1,s2,s3,s4,s5)(tt)(a)
					for (int s1 = 0; s1 < layer1.size(); s1++) {
						for (int s2 = 0; s2 < layer2.size(); s2++) {
							for (int s3 = 0; s3 < layer3.size(); s3++) {
								for (int s4 = 0; s4 < layer4.size(); s4++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
									if (modeled_strata_withoutSizeClassandCoverType.contains(strataName) && static_strata_withoutSizeClassandCoverType.contains(strataName)) {
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int tt = 2; tt <= total_Periods; tt++) {
												for (int a = 1; a <= tt - 1; a++) {
													if (integer_static_timePeriods.size() > 0) {	
														
														//Combine all parameter together
														double parameter =0;
														for (int t : integer_static_timePeriods) {		//Loop all periods, t and tt are the same
															if (t == tt) {
																//Find all parameter match the t and add them all to parameter
																double para_value = getParameter_totalValues.getValue(layer5.get(s5), "notNeeded", "A", "AgeClassShouldBeHere", 
																		yieldTable_Name, yieldTable_values, parameters_indexes_list,
																		all_dynamicIdentifiers_columnIndexes, all_dynamicIdentifiers, t-1);
																parameter = parameter + para_value;			
															}
														}
														
														//Add xEAr(s1,s2,s3,s4,s5)(tt)(a)		final cut at tt but we need parameter at time t
														c15_indexlist.get(c15_num).add(xEAr[s1][s2][s3][s4][s5][tt][a]);
														c15_valuelist.get(c15_num).add((double) parameter);	
													}	
												}
											}
										}
									}
								}
							}
						}
					}	
				}	 //End of Loop if "EA" 						
				

		
				//add bounds
				c15_lblist.add((double) 0);
				c15_ublist.add((double) 0);
				c15_num++;
			}


			double[] c15_lb = Stream.of(c15_lblist.toArray(new Double[c15_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c15_ub = Stream.of(c15_ublist.toArray(new Double[c15_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c15_index = new int[c15_num][];
			double[][] c15_value = new double[c15_num][];
		
			for (int i = 0; i < c15_num; i++) {
				c15_index[i] = new int[c15_indexlist.get(i).size()];
				c15_value[i] = new double[c15_indexlist.get(i).size()];
				for (int j = 0; j < c15_indexlist.get(i).size(); j++) {
					c15_index[i][j] = c15_indexlist.get(i).get(j);
					c15_value[i][j] = c15_valuelist.get(i).get(j);			
				}
			}				
			
			
			
			// Solve problem-------------------------------------------------	
			

			
			
			
//			// Set constraints set name: Notice THIS WILL EXTREMELY SLOW THE SOLVING PROCESS (recommend for debugging only)
//			int indexOfC2 = c2_num;
//			int indexOfC3 = indexOfC2 + c3_num;
//			int indexOfC6 = indexOfC3 + c6_num;
//			int indexOfC7 = indexOfC6 + c7_num;
//			int indexOfC8 = indexOfC7 + c8_num;
//			int indexOfC9 = indexOfC8 + c9_num;
//			int indexOfC10 = indexOfC9 + c10_num;
//			int indexOfC11 = indexOfC10 + c11_num;
//			int indexOfC12 = indexOfC11 + c12_num;
//			int indexOfC13 = indexOfC12 + c13_num;
//			int indexOfC14 = indexOfC13 + c14_num;
//			int indexOfC15 = indexOfC14 + c15_num;		//Note: 	lp.getRanges().length = indexOfC15
//			
//			for (int i = 0; i<lp.getRanges().length; i++) {		
//				if (0<=i && i<indexOfC2) lp.getRanges() [i].setName("EQ(2)_");
//				if (indexOfC2<=i && i<indexOfC3) lp.getRanges() [i].setName("EQ(3)_");
//				if (indexOfC3<=i && i<indexOfC6) lp.getRanges() [i].setName("EQ(6)_");
//				if (indexOfC6<=i && i<indexOfC7) lp.getRanges() [i].setName("EQ(7)_");
//				if (indexOfC7<=i && i<indexOfC8) lp.getRanges() [i].setName("EQ(8)_");
//				if (indexOfC8<=i && i<indexOfC9) lp.getRanges() [i].setName("EQ(9)_");
//				if (indexOfC9<=i && i<indexOfC10) lp.getRanges() [i].setName("EQ(10)_");
//				if (indexOfC10<=i && i<indexOfC11) lp.getRanges() [i].setName("EQ(11)_");
//				if (indexOfC11<=i && i<indexOfC12) lp.getRanges() [i].setName("EQ(12)_");
//				if (indexOfC12<=i && i<indexOfC13) lp.getRanges() [i].setName("EQ(13)_");
//				if (indexOfC13<=i && i<indexOfC14) lp.getRanges() [i].setName("EQ(14)_");
//				if (indexOfC14<=i && i<indexOfC15) lp.getRanges() [i].setName("EQ(15)_");
//			}
			
			
			
			
			
			
			
			if (read.get_Solver().equals("CPLEX")) {
				
				//Add the Cplex native library path dynamically at run time
				try {
//					LibraryHandle.addLibraryPath("C:/Program Files/IBM/ILOG/CPLEX_Studio126/cplex/bin/x64_win64");
					
					LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					System.out.println("Loading CPLEX .dll from " + FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	
				} catch (Exception e) {
					System.err.println("Panel Solve Runs - cplexLib.addLibraryPath error - " + e.getClass().getName() + ": " + e.getMessage());
				}
				
				IloCplex cplex = new IloCplex();
				IloLPMatrix lp = cplex.addLPMatrix();
				IloNumVar[] var = cplex.numVarArray(cplex.columnArray(lp, nvars), vlb, vub, vtype, vname);
				
				
				// Add constraints
				lp.addRows(c2_lb, c2_ub, c2_index, c2_value); // Constraints 2
				lp.addRows(c3_lb, c3_ub, c3_index, c3_value); // Constraints 3
				lp.addRows(c6_lb, c6_ub, c6_index, c6_value); // Constraints 6
				lp.addRows(c7_lb, c7_ub, c7_index, c7_value); // Constraints 7
				lp.addRows(c8_lb, c8_ub, c8_index, c8_value); // Constraints 8
				lp.addRows(c9_lb, c9_ub, c9_index, c9_value); // Constraints 9
				lp.addRows(c10_lb, c10_ub, c10_index, c10_value); // Constraints 10
				lp.addRows(c11_lb, c11_ub, c11_index, c11_value); // Constraints 11
				lp.addRows(c12_lb, c12_ub, c12_index, c12_value); // Constraints 12
				lp.addRows(c13_lb, c13_ub, c13_index, c13_value); // Constraints 13
				lp.addRows(c14_lb, c14_ub, c14_index, c14_value); // Constraints 14
				lp.addRows(c15_lb, c15_ub, c15_index, c15_value); // Constraints 15
				
				
				cplex.addMinimize(cplex.scalProd(var, objvals)); // Set objective function to minimize
				cplex.setParam(IloCplex.Param.RootAlgorithm, IloCplex.Algorithm.Auto); // Auto choose optimization method
//				cplex.setParam(IloCplex.DoubleParam.EpGap, 0.00); // Gap is 0%
				int solvingTimeLimit = read.get_SolvingTimeLimit() * 60; //Get time Limit in minute * 60 = seconds
				cplex.setParam(IloCplex.DoubleParam.TimeLimit, solvingTimeLimit); // Set Time limit
//				cplex.setParam(IloCplex.BooleanParam.PreInd, false);	// Turn off preSolve to see full variables and constraints
				
				//Add table info
				data[row][2] = cplex.getNcols();
				data[row][3] = cplex.getNrows();
				data[row][4] = "solving";
				table.setValueAt(data[row][2], row, 2); //To help trigger the table refresh: fireTableDataChanged() and repaint();
				table.setValueAt(data[row][3], row, 3);
				table.setValueAt(data[row][4], row, 4);
				
				
//				cplex.exportModel(problemFile[row].getAbsolutePath());
				long time_start = System.currentTimeMillis();		//measure time before solving
				if (cplex.solve()) {
					long time_end = System.currentTimeMillis();		//measure time after solving
//					cplex.writeSolution(solutionFile[row].getAbsolutePath());

					//Get output info to array
					double[] value = cplex.getValues(lp);
					double[] reduceCost = cplex.getReducedCosts(lp);
					double[] dual = cplex.getDuals(lp);
					double[] slack = cplex.getSlacks(lp);

					//Write Solution files

					//General Info
					output_generalInfo_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_generalInfo_file[row]))) {
						// Write variables info
						fileOut.write("Output Description" + "\t" + "Output Value");

						fileOut.newLine();
						fileOut.write("Optimization solver" + "\t" + "CPLEX");

						fileOut.newLine();
						fileOut.write("Solution status" + "\t" + cplex.getStatus());

						fileOut.newLine();
						fileOut.write("Solution algorithm" + "\t" + cplex.getAlgorithm());

						fileOut.newLine();
						fileOut.write("Simplex iterations" + "\t" + cplex.getNiterations64());
				
						double timeElapsed = (double) (time_end - time_start)/1000;
						fileOut.newLine();
						fileOut.write("Solving time (seconds)" + "\t" + Double.valueOf(twoDForm.format(timeElapsed)) /*cplex.getCplexTime()*/);

						fileOut.newLine();
						fileOut.write("Total variables" + "\t" + cplex.getNcols());

						fileOut.newLine();
						fileOut.write("Total constraints" + "\t" + cplex.getNrows());

						fileOut.newLine();
						fileOut.write("Objective value" + "\t" + Double.valueOf(twoDForm.format(cplex.getObjValue())));

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_generalInfo_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_generalInfo_file[row].createNewFile();

					
					//Variables if value <> 0
					output_variables_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_variables_file[row]))) {
						// Write variables info
						fileOut.write("Index" + "\t" + "Name" + "\t" + "Value" + "\t" + "Reduced Cost");
						for (int i = 0; i < value.length; i++) {
							if (value[i] != 0) {
								fileOut.newLine();
								fileOut.write(i + "\t" + vname[i] + "\t" +  Double.valueOf(twoDForm.format(value[i])) + "\t" +  Double.valueOf(twoDForm.format(reduceCost[i])));
							}
						}

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_variables_file[row]) error - "	+ e.getClass().getName() + ": " + e.getMessage());
					}
					output_variables_file[row].createNewFile();

					
					//Constraints  if dual or slack <> 0
					output_constraints_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_constraints_file[row]))) {
						// Write constraints info
						fileOut.write("Index" + "\t" + "Slack" + "\t" + "Dual");
						for (int j = 0; j < dual.length; j++) {
							if (slack[j] != 0 || dual[j] != 0) {
								fileOut.newLine();
								fileOut.write(j + "\t" + Double.valueOf(twoDForm.format(slack[j])) + "\t" + Double.valueOf(twoDForm.format(dual[j])));
							}
						}

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_constraints_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_constraints_file[row].createNewFile();

					
					//Management Overview
					output_managementOverview_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(
							new FileWriter(output_managementOverview_file[row]))) {
						// Write info
						fileOut.write("Strata ID" + "\t" + "Layer 1" + "\t" + "Layer 2" + "\t" + "Layer 3" + "\t" + "Layer 4" + "\t" + "Layer 5" + "\t" + "Layer 6" + "\t" 
						+ "Natural Growth (acres)" + "\t" + "Prescribed Burn (acres)" + "\t" + "Group Selection (acres)" + "\t"
						+ "Even Age (acres)" + "\t" + "Mixed Severity Wildfire (acres)");

						for (int s1 = 0; s1 < layer1.size(); s1++) {
							for (int s2 = 0; s2 < layer2.size(); s2++) {
								for (int s3 = 0; s3 < layer3.size(); s3++) {
									for (int s4 = 0; s4 < layer4.size(); s4++) {
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int s6 = 0; s6 < layer6.size(); s6++) {
												String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
												if (modeled_strata.contains(strataName)) {

													//New line for each strata
													fileOut.newLine();
													//Write StrataID and 6 layers info
													fileOut.write(
															strataName + "\t" + layer1.get(s1) + "\t" + layer2.get(s2)
																	+ "\t" + layer3.get(s3) + "\t" + layer4.get(s4)
																	+ "\t" + layer5.get(s5) + "\t" + layer6.get(s6));
													//Write acres from each method
													for (int q = 0; q < total_methods; q++) {
														int this_var_index = x[s1][s2][s3][s4][s5][s6][q];
														fileOut.write("\t" + Double.valueOf(twoDForm.format(value[this_var_index])));
													}
												}
											}
										}
									}
								}
							}
						}
						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_managementOverview_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_managementOverview_file[row].createNewFile();

					//Show successful or fail in the GUI
					data[row][1] = "valid";
					table.setValueAt(data[row][1], row, 1);
					data[row][4] = "successful";
					table.setValueAt(data[row][4], row, 4);
				}
				
				cplex.endModel();
				cplex.end();
			}
			
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if (read.get_Solver().equals("LPSOLVE")) {				//Reference for all LPsolve classes here:		http://lpsolve.sourceforge.net/5.5/Java/docs/api/lpsolve/LpSolve.html
				
				//Add the LPsolve native library path dynamically at run time
				try {
//					LibraryHandle.addLibraryPath("C:/SpectrumLite_Documents/Setup/lp_solve_5.5_java/lib/win64");
					
					LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					System.out.println("Loading LPSOLVE .dll from " + FilesHandle.get_temporaryFolder().getAbsolutePath().toString());			
				} catch (Exception e) {
					System.err.println("Panel Solve Runs - LPsolve.addLibraryPath error - " + e.getClass().getName() + ": " + e.getMessage());
				}
				
				
				// Create a problem with nV variables and 0 constraints
				LpSolve solver = LpSolve.makeLp(0, nV);
				
			    solver.setVerbose(LpSolve.NEUTRAL); //set verbose level
		        solver.setMinim(); //set the problem to minimization
		        
		        
		        
		        
//		        //---------------------------------------------------------------------------
//		        //---------------------------------------------------------------------------
//		        // From other people set up, I dont understand but I think it can speed up the solving process: no speed improved
//		        solver.setScalelimit(5); 
//
//		        solver.setPivoting(LpSolve.PRICER_DEVEX + 
//		        LpSolve.PRICE_ADAPTIVE ); 
//		        solver.setMaxpivot(250); 
//
//		        solver.setBbFloorfirst(LpSolve.BRANCH_AUTOMATIC); 
//		        solver.setBbRule(LpSolve.NODE_PSEUDONONINTSELECT + 
//		        LpSolve.NODE_GREEDYMODE + LpSolve.NODE_DYNAMICMODE + 
//		        LpSolve.NODE_RCOSTFIXING); 
//		        solver.setObjBound(1E30); 
//		        solver.setBbDepthlimit(-50); 
//
//		        solver.setImprove(LpSolve.IMPROVE_DUALFEAS + 
//		        LpSolve.IMPROVE_THETAGAP); 
//
//		        solver.setSimplextype(LpSolve.SIMPLEX_DUAL_PRIMAL); 
//		        //---------------------------------------------------------------------------
//		        //---------------------------------------------------------------------------
		        
		        
		        

		        
		        //Set objective function coefficients
		        solver.setObjFn(pad1ZeroInfront(objvals));		      
				
//		        //Set scaling			//Note this make outputs change
//		        solver.setScaling(LpSolve.SCALE_NONE);				//reference here:	http://lpsolve.sourceforge.net/5.5/set_scaling.htm
		        
//		        //Set preSolve:		Eliminate linearly dependent rows = LpSolve.PRESOLVE_LINDEP  (value int = 4), option 1 can reduce iteration --> solve faster
//		        solver.setPresolve(LpSolve.PRESOLVE_LINDEP, solver.getPresolveloops());		//reference here:	http://lpsolve.sourceforge.net/5.5/set_presolve.htm
		        
		        
		        for (int i = 0; i < nV; ++i) {
					solver.setColName(i, vname[i]); // Set variable name
					solver.setBounds(i + 1, vlb[i], vub[i]); // plus one is required by the lib
				}
		      	        
//		        //lower bounds for the variables	      
//				for (int i = 0; i < nV; ++i) {
//					solver.setLowbo(i + 1, vlb[i]); // plus one is required by the lib
//				}
//		      		         
//		        //upper bounds for the variables
//				for (int i = 0; i < nV; ++i) {
//					solver.setUpbo(i + 1, vub[i]); // plus one is required by the lib
//				}
		         
		        
		        //Allocate memory forin advance to add constraints & variables faster		Reference:	http://lpsolve.sourceforge.net/5.5/resize_lp.htm
		        int totalConstraints = c2_lb.length + c3_lb.length + c6_lb.length + c7_lb.length + c8_lb.length 
		        									+ c9_lb.length + c10_lb.length + c11_lb.length + c12_lb.length + c13_lb.length + c14_lb.length + c15_lb.length;
		        solver.resizeLp(totalConstraints, solver.getNcolumns());
		        
				
		        //Add constraints:			//	The sign of the constraint: LE (1) for <=, EQ (3) for =, GE (2) for >=
											//	addConstraint(parameters, sign, RHS)
				
		        solver.setAddRowmode(true);	//perform much better addConstraintex -----------------------------------------------------------
//		        // Constraints 2   
//				for (int i = 0; i < c2_num; ++i) {
//					if (c2_lb[i] != Double.MIN_VALUE) {
//						solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c2_value[i], c2_index[i])), LpSolve.GE, c2_lb[i]);
//					}	
//				}	
//				// Constraints 3   
//				for (int i = 0; i < c3_num; ++i) {
//					if (c3_ub[i] != Double.MAX_VALUE) {
//						solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c3_value[i], c3_index[i])), LpSolve.LE, c3_ub[i]);
//					}			
//				}	
//				
//				// Constraints 6   
//				for (int i = 0; i < c6_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c6_value[i], c6_index[i])), LpSolve.EQ, c6_lb[i]);
//				}			
//				// Constraints 7
//				for (int i = 0; i < c7_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c7_value[i], c7_index[i])), LpSolve.EQ, c7_lb[i]);
//				}	
//				// Constraints 8
//				for (int i = 0; i < c8_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c8_value[i], c8_index[i])), LpSolve.EQ, c8_lb[i]);
//				}	
//				// Constraints 9
//				for (int i = 0; i < c9_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c9_value[i], c9_index[i])), LpSolve.EQ, c9_lb[i]);
//				}	
//				// Constraints 10
//				for (int i = 0; i < c10_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c10_value[i], c10_index[i])), LpSolve.EQ, c10_lb[i]);
//				}	
//				// Constraints 11
//				for (int i = 0; i < c11_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c11_value[i], c11_index[i])), LpSolve.EQ, c11_lb[i]);
//				}	
//				// Constraints 12
//				for (int i = 0; i < c12_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c12_value[i], c12_index[i])), LpSolve.EQ, c12_lb[i]);
//				}	
//				// Constraints 13
//				for (int i = 0; i < c13_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c13_value[i], c13_index[i])), LpSolve.EQ, c13_lb[i]);
//				}	
//				// Constraints 14
//				for (int i = 0; i < c14_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c14_value[i], c14_index[i])), LpSolve.EQ, c14_lb[i]);
//				}	
//				// Constraints 15
//				for (int i = 0; i < c15_num; ++i) {
//					solver.addConstraint(pad1ZeroInfront(withoutZero_To_WithZero(nV, c15_value[i], c15_index[i])), LpSolve.EQ, c15_lb[i]);
//				}	
		        
		        //Add addConstraintex much more faster than addConstraint.		Reference:		http://lpsolve.sourceforge.net/5.5/add_constraint.htm
		        // Constraints 2   
				for (int i = 0; i < c2_num; ++i) {
					if (c2_lb[i] != Double.MIN_VALUE) {			
						solver.addConstraintex(c2_value[i].length, c2_value[i], plus1toIndex(c2_index[i]), LpSolve.GE, c2_lb[i]);
					}	
				}	
				// Constraints 3   
				for (int i = 0; i < c3_num; ++i) {
					if (c3_ub[i] != Double.MAX_VALUE) {
						solver.addConstraintex(c3_value[i].length, c3_value[i], plus1toIndex(c3_index[i]), LpSolve.LE, c3_ub[i]);
					}			
				}	
				
				// Constraints 6   
				for (int i = 0; i < c6_num; ++i) {
					solver.addConstraintex(c6_value[i].length, c6_value[i], plus1toIndex(c6_index[i]), LpSolve.EQ, c6_lb[i]);
				}			
				// Constraints 7
				for (int i = 0; i < c7_num; ++i) {
					solver.addConstraintex(c7_value[i].length, c7_value[i], plus1toIndex(c7_index[i]), LpSolve.EQ, c7_lb[i]);
				}	
				// Constraints 8
				for (int i = 0; i < c8_num; ++i) {
					solver.addConstraintex(c8_value[i].length, c8_value[i], plus1toIndex(c8_index[i]), LpSolve.EQ, c8_lb[i]);
				}	
				// Constraints 9
				for (int i = 0; i < c9_num; ++i) {
					solver.addConstraintex(c9_value[i].length, c9_value[i], plus1toIndex(c9_index[i]), LpSolve.EQ, c9_lb[i]);
				}	
				// Constraints 10
				for (int i = 0; i < c10_num; ++i) {
					solver.addConstraintex(c10_value[i].length, c10_value[i], plus1toIndex(c10_index[i]), LpSolve.EQ, c10_lb[i]);
				}	
				// Constraints 11
				for (int i = 0; i < c11_num; ++i) {
					solver.addConstraintex(c11_value[i].length, c11_value[i], plus1toIndex(c11_index[i]), LpSolve.EQ, c11_lb[i]);
				}	
				// Constraints 12
				for (int i = 0; i < c12_num; ++i) {
					solver.addConstraintex(c12_value[i].length, c12_value[i], plus1toIndex(c12_index[i]), LpSolve.EQ, c12_lb[i]);
				}	
				// Constraints 13
				for (int i = 0; i < c13_num; ++i) {
					solver.addConstraintex(c13_value[i].length, c13_value[i], plus1toIndex(c13_index[i]), LpSolve.EQ, c13_lb[i]);
				}	
				// Constraints 14
				for (int i = 0; i < c14_num; ++i) {
					solver.addConstraintex(c14_value[i].length, c14_value[i], plus1toIndex(c14_index[i]), LpSolve.EQ, c14_lb[i]);
				}	
				// Constraints 15
				for (int i = 0; i < c15_num; ++i) {
					solver.addConstraintex(c15_value[i].length, c15_value[i], plus1toIndex(c15_index[i]), LpSolve.EQ, c15_lb[i]);
				}	
				solver.setAddRowmode(false);	//perform much better addConstraintex-----------------------------------------------------------
				
		        
				//Add table info
				data[row][2] = solver.getNcolumns();
				data[row][3] = solver.getNrows();
				data[row][4] = "solving";
				table.setValueAt(data[row][2], row, 2); //To help trigger the table refresh: fireTableDataChanged() and repaint();
				table.setValueAt(data[row][3], row, 3);
				table.setValueAt(data[row][4], row, 4);
		        
//		        //solve the problem
//		        int status = solver.solve();
//		        if(isSolutionValid(status)==false) {
//		            solver.setScaling(LpSolve.SCALE_NONE); //turn off automatic scaling
//		            status = solver.solve();
//		            if(isSolutionValid(status)==false) {
//		                throw new RuntimeException("LPSolver Error: "+solver.getStatustext(status));
//					}
//				}	        
		        
		        //solve the problem
//				solver.writeLp(problemFile[row].getAbsolutePath());
				if (isSolutionValid(solver.solve()) == true) {
//					solver.setOutputfile(solutionFile[row].getAbsolutePath());

					
					//Get output info to array
					double[] value = solver.getPtrVariables();
//					double[] reduceCost = cplex.getReducedCosts(lp);
					double[] dual = solver.getPtrDualSolution();
//					double[] slack = cplex.getSlacks(lp);

					//Write Solution files

					//General Info
					output_generalInfo_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_generalInfo_file[row]))) {
						// Write variables info
						fileOut.write("Output Description" + "\t" + "Output Value");

						fileOut.newLine();
						fileOut.write("Optimization solver" + "\t" + "LPSOLVE");

						fileOut.newLine();
						fileOut.write("Solution status" + "\t" + solver.getStatus());

						fileOut.newLine();
						fileOut.write("Solution algorithm" + "\t" + " "/*+ solver.getAlgorithm()*/);

						fileOut.newLine();
						fileOut.write("Simplex iterations" + "\t" + solver.getTotalIter());

						fileOut.newLine();
						fileOut.write("Solving time (seconds)" + "\t" + Double.valueOf(twoDForm.format(solver.timeElapsed())) /*+ cplex.getCplexTime()*/);

						fileOut.newLine();
						fileOut.write("Total variables" + "\t" + solver.getNcolumns());

						fileOut.newLine();
						fileOut.write("Total constraints" + "\t" + solver.getNrows());

						fileOut.newLine();
						fileOut.write("Objective value" + "\t" + Double.valueOf(twoDForm.format(solver.getObjective())));

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_generalInfo_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_generalInfo_file[row].createNewFile();

					
					//Variables if value <> 0
					output_variables_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_variables_file[row]))) {
						// Write variables info
						fileOut.write("Index" + "\t" + "Name" + "\t" + "Value" + "\t" + "Reduced Cost");
						for (int i = 0; i < value.length; i++) {
							if (value[i] != 0) {
								fileOut.newLine();
								fileOut.write(i + "\t" + vname[i] + "\t" + Double.valueOf(twoDForm.format(value[i])) + "\t" + " ");
//								fileOut.write(i + "\t" + vname[i] + "\t" + newValue + "\t" + reduceCost[i]);
							}
						}

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_variables_file[row]) error - "	+ e.getClass().getName() + ": " + e.getMessage());
					}
					output_variables_file[row].createNewFile();

					
					//Constraints  if dual or slack <> 0
					output_constraints_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_constraints_file[row]))) {
						// Write constraints info
						fileOut.write("Index" + "\t" + "Slack" + "\t" + "Dual");
//						for (int j = 0; j < dual.length; j++) {
//							if (slack[j] != 0 || dual[j] != 0) {
//								fileOut.newLine();
//								fileOut.write(j + "\t" + slack[j] + "\t" + dual[j]);
//							}
//						}

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_constraints_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_constraints_file[row].createNewFile();

					
					//Management Overview
					output_managementOverview_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(
							new FileWriter(output_managementOverview_file[row]))) {
						// Write info
						fileOut.write("Strata ID" + "\t" + "Layer 1" + "\t" + "Layer 2" + "\t" + "Layer 3" + "\t" + "Layer 4" + "\t" + "Layer 5" + "\t" + "Layer 6" + "\t" 
						+ "Natural Growth (acres)" + "\t" + "Prescribed Burn (acres)" + "\t" + "Group Selection (acres)" + "\t"
						+ "Even Age (acres)" + "\t" + "Mixed Severity Wildfire (acres)");

						for (int s1 = 0; s1 < layer1.size(); s1++) {
							for (int s2 = 0; s2 < layer2.size(); s2++) {
								for (int s3 = 0; s3 < layer3.size(); s3++) {
									for (int s4 = 0; s4 < layer4.size(); s4++) {
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int s6 = 0; s6 < layer6.size(); s6++) {
												String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
												if (modeled_strata.contains(strataName)) {

													//New line for each strata
													fileOut.newLine();
													//Write StrataID and 6 layers info
													fileOut.write(
															strataName + "\t" + layer1.get(s1) + "\t" + layer2.get(s2)
																	+ "\t" + layer3.get(s3) + "\t" + layer4.get(s4)
																	+ "\t" + layer5.get(s5) + "\t" + layer6.get(s6));
													//Write acres from each method
													for (int q = 0; q < total_methods; q++) {
														int this_var_index = x[s1][s2][s3][s4][s5][s6][q];
														fileOut.write("\t" + Double.valueOf(twoDForm.format(value[this_var_index])));
													}
												}
											}
										}
									}
								}
							}
						}
						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_managementOverview_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_managementOverview_file[row].createNewFile();

					//Show successful or fail in the GUI
					data[row][1] = "valid";
					table.setValueAt(data[row][1], row, 1);
					data[row][4] = "successful";
					table.setValueAt(data[row][4], row, 4);
					
					
					
					
				}
				solver.deleteLp();
			}			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		catch (IloException e) {
			System.err.println("Concert exception '" + e + "' caught for " + listOfEditRuns[row].getName());
			displayTextArea.append("Concert exception '" + e + "' caught for " + listOfEditRuns[row].getName() + "\n");
			
			data[row][1] = "Concert error";
			table.setValueAt(data[row][1], row, 1);
			data[row][4] = "fail";
			table.setValueAt(data[row][4] , row, 4);
			
			output_variables_file[row].delete();
			output_constraints_file[row].delete();	
			output_generalInfo_file[row].delete();	
			output_managementOverview_file[row].delete();
		}
		
		catch (Exception e2) {
			System.err.println(e2.getClass().getName() + ": " + e2.getMessage() + " caught for '" + listOfEditRuns[row].getName() + "' please check Input Files");
			displayTextArea.append(e2.getClass().getName() + ": " + e2.getMessage() + " caught for '" + listOfEditRuns[row].getName()  + "' please check Input Files" + "\n");
			data[row][1] = "Invalid Inputs";
			table.setValueAt(data[row][1], row, 1);
			data[row][4] = "fail";
			table.setValueAt(data[row][4] , row, 4);
			
			output_variables_file[row].delete();
			output_constraints_file[row].delete();
			output_generalInfo_file[row].delete();
			output_managementOverview_file[row].delete();
		}
	}
	
	
	private static int[] plus1toIndex(int[] array) {
		int[] plus1Array = new int[array.length];
		for (int i = 0; i < array.length; ++i) {
			plus1Array[i] = array[i] + 1;		 // plus one is required by the lib
		}	
		return plus1Array;
	}	
	
	
	private static double[] withoutZero_To_WithZero(int total_var, double[] value, int[] index) {
		double[] array = new double[total_var];	
		
		//all values to be 0
		for (int i = 0; i < total_var; i++) {
			array[i] = 0;
		} 
		
		//for other values, check index & value to set it
		for (int i = 0; i < index.length; i++) {
			array[index[i]] = value[i];
		} 

		return array;
	}	
	
	
	private static double[] pad1ZeroInfront(double[] array) {
		double[] paddedArray = new double[array.length + 1];
		System.arraycopy(array, 0, paddedArray, 1, array.length);
		return paddedArray;
	}
	
	
    /**
     * Checks whether the status corresponds to a valid solution
     * 
     * @param status    The status id returned by lpsolve
     * @return          Boolean which indicates if the solution is valid
     */
	private static boolean isSolutionValid(int status) {
		return (status == 0) || (status == 1) || (status == 11) || (status == 12);
	}
}