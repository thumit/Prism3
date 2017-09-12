package prismProject;

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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import lpsolve.LpSolveException;
import prismConvenienceClass.FilesHandle;
import prismConvenienceClass.LibraryHandle;
import prismConvenienceClass.PrismTableModel;
import prismRoot.PrismMain;

public class Panel_SolveRun extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel, splitPanel2;
	private JTextArea displayTextArea;
	private JButton runStatButton;
	private boolean solvingstatus;
	
	private int rowCount, colCount;
	private String[] columnNames;
	private JTable table;
	private PrismTableModel model;
	private Object[][] data;
	
	private File[] listOfEditRuns ;
	private JScrollPane scrollpane_left, scrollpane_right;
	
	private File[] 	problem_file, solution_file, output_general_outputs_file, output_variables_file, output_constraints_file,
					output_management_overview_file, output_management_details_file, output_fly_constraints_file, output_basic_constraints_file, output_flow_constraints_file;
	
	private DecimalFormat twoDForm = new DecimalFormat("#.##");	 //Only get 2 decimal will be assess
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
	
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
        model = new PrismTableModel(rowCount, colCount, data, columnNames);
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
		displayTextArea.setFocusable(false);
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
		scrollpane_left = new JScrollPane();
		scrollpane_left.setViewportView(table);			
		splitPanel2= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPanel2.setResizeWeight(1);
		splitPanel2.setDividerSize(0);
		splitPanel2.setLeftComponent(scrollpane_left);
		

		java.net.URL imgURL = getClass().getResource("/pikachuRunning.gif");		//Name is case sensitive
		java.net.URL imgURL2 = getClass().getResource("/pikachuAss.gif");			//Name is case sensitive
//		java.net.URL imgURL2 = getClass().getResource("/pikachuRoll2.gif");			//Name is case sensitive

		
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
		Image scaleImage2 = icon2.getImage().getScaledInstance(138, 150,Image.SCALE_REPLICATE);
	
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
					problem_file = new File[rowCount];
					solution_file = new File[rowCount];
					output_variables_file = new File[rowCount];
					output_constraints_file = new File[rowCount];
					output_general_outputs_file = new File[rowCount];
					output_management_overview_file = new File[rowCount];
					output_management_details_file = new File[rowCount];	
					output_fly_constraints_file = new File[rowCount];
					output_flow_constraints_file = new File[rowCount];
					output_basic_constraints_file = new File[rowCount];
					

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
								model.fireTableDataChanged();
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
							if (col != 0 && col != 4) {
								data[row][col] = "";
								model.fireTableDataChanged();
							}
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
		scrollpane_right = new JScrollPane();
		scrollpane_right.setViewportView(displayTextArea);
		splitPanel.setRightComponent(scrollpane_right);			
		
		
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
			problem_file[row] = new File(runFolder.getAbsolutePath() + "/problem.lp");
			solution_file[row] = new File(runFolder.getAbsolutePath() + "/solution.sol");
			output_general_outputs_file[row] = new File(runFolder.getAbsolutePath() + "/output_01_general_outputs.txt");	
			output_variables_file[row] = new File(runFolder.getAbsolutePath() + "/output_02_variables.txt");
			output_constraints_file[row] = new File(runFolder.getAbsolutePath() + "/output_03_constraints.txt");	
			output_management_overview_file[row] = new File(runFolder.getAbsolutePath() + "/output_04_management_overview.txt");
			output_management_details_file[row] = new File(runFolder.getAbsolutePath() + "/output_05_management_details.txt");	
			output_fly_constraints_file[row] = new File(runFolder.getAbsolutePath() + "/output_05_fly_constraints.txt");	
			output_basic_constraints_file[row] = new File(runFolder.getAbsolutePath() + "/output_06_basic_constraints.txt");
			output_flow_constraints_file[row] = new File(runFolder.getAbsolutePath() + "/output_07_flow_constraints.txt");
			
			// Database must be read first
			File file_database = new File(runFolder.getAbsolutePath() + "/database.db");
			Read_Database read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
			if (read_database == null) {
				read_database = new Read_Database(file_database);	// Read the database
				PrismMain.get_databases_linkedlist().update(file_database, read_database);			
			}
			
			//Database Info
			Object[][][] yield_tables_values = read_database.get_yield_tables_values();
			Object[] yield_tables_names = read_database.get_yield_tables_names();			
			List<String> yield_tables_names_list = new ArrayList<String>() {{ for (Object i : yield_tables_names) add(i.toString());}};		// Convert Object array to String list
				
			
			
			
			// Read input files to retrieve values later
			Read_RunInputs read = new Read_RunInputs();
			read.read_general_inputs(new File(runFolder.getAbsolutePath() + "/input_01_general_inputs.txt"));
			read.read_silviculture_method(new File(runFolder.getAbsolutePath() + "/input_02_silviculture_method.txt"));
			read.read_model_strata(new File(runFolder.getAbsolutePath() + "/input_03_model_strata.txt"));
			read.read_covertype_conversion_clearcut(new File(runFolder.getAbsolutePath() + "/input_04_covertype_conversion_clearcut.txt"));
			read.read_covertype_conversion_replacing(new File(runFolder.getAbsolutePath() + "/input_05_covertype_conversion_replacing.txt"));
			read.read_natural_disturbances_non_replacing(new File(runFolder.getAbsolutePath() + "/input_06_natural_disturbances_non_replacing.txt"));
			read.read_natural_disturbances_replacing(new File(runFolder.getAbsolutePath() + "/input_07_natural_disturbances_replacing.txt"));
			read.read_management_cost(new File(runFolder.getAbsolutePath() + "/input_08_management_cost.txt"));
			read.read_basic_constraints(new File(runFolder.getAbsolutePath() + "/input_09_basic_constraints.txt"));
			read.read_flow_constraints(new File(runFolder.getAbsolutePath() + "/input_10_flow_constraints.txt"));
			
			// Get info: input_02_silviculture_method
			List<String> sm_strata = read.get_sm_strata();
			List<String> sm_strata_without_sizeclass_and_covertype = read.get_sm_strata_without_sizeclass_and_covertype();
			List<List<String>> sm_method_choice_for_strata = read.get_sm_method_choice_for_strata();
			List<List<String>> sm_method_choice_for_strata_without_sizeclass_and_covertype = read.get_sm_method_choice_for_strata_without_sizeclass_and_covertype();
			
			// Get info: input_03_modeled_strata
			List<String> model_strata, model_strata_without_sizeclass_and_covertype = new ArrayList<String>();
			model_strata = read.get_model_strata();
			model_strata_without_sizeclass_and_covertype = read.get_model_strata_without_sizeclass_and_covertype(); 
						
			// Get Info: input_04_covertype_conversion_clearcut
			List<String> covertype_conversions, covertype_conversions_and_existing_rotation_ages, covertype_conversions_and_regeneration_rotation_ages = new ArrayList<String>();
			covertype_conversions = read.get_covertype_conversions();
			covertype_conversions_and_existing_rotation_ages = read.get_covertype_conversions_and_existing_rotation_ages();	
			covertype_conversions_and_regeneration_rotation_ages = read.get_covertype_conversions_and_regeneration_rotation_ages();

			// Get Info: input_05_covertype_conversion_replacing
			double[] rdProportion = read.getRDProportion(); 
			
			// Get info: input_06_natural_disturbances_non_replacing
			double[] msProportion = read.getMSFireProportion();
			double[] bsProportion = read.getBSFireProportion();
			
			// Get Info: input_07_natural_disturbances_replacing
			double[][] SRD_percent = read.getSRDProportion();		
			
			// Get info: input_08_management_cost
			List<String> cost_condition_list = read.get_cost_condition_list(); 
		
			// Get info: input_09_basic_constraints
			List<String> constraint_column_names_list = read.get_constraint_column_names_list();
			String[][] bc_values = read.get_bc_values();		
			int total_softConstraints = read.get_total_softConstraints();
			double[] softConstraints_LB = read.get_softConstraints_LB();
			double[] softConstraints_UB = read.get_softConstraints_UB();
			double[] softConstraints_LB_Weight = read.get_softConstraints_LB_Weight();
			double[] softConstraints_UB_Weight = read.get_softConstraints_UB_Weight();		
			int total_hardConstraints = read.get_total_hardConstraints();
			double[] hardConstraints_LB = read.get_hardConstraints_LB();
			double[] hardConstraints_UB = read.get_hardConstraints_UB();	
			int total_freeConstraints = read.get_total_freeConstraints();
			
			// Get info: input_10_flow_constraints	
			List<List<List<Integer>>> flow_set_list = read.get_flow_set_list();
			List<Integer> flow_id_list = read.get_flow_id_list();
			List<String> flow_description_list = read.get_flow_description_list();
			List<String> flow_arrangement_list = read.get_flow_arrangement_list();
			List<String> flow_type_list = read.get_flow_type_list();
			List<Double> flow_lowerbound_percentage_list = read.get_flow_lowerbound_percentage_list();
			List<Double> flow_upperbound_percentage_list = read.get_flow_upperbound_percentage_list();
			System.out.println("Reading process finished for all input files          " + dateFormat.format(new Date()));
			System.out.println();
			
			
			
			// Set up problem-------------------------------------------------			
			List<String> layers_Title = read_database.get_layers_Title();
			List<List<String>> allLayers =  read_database.get_allLayers();
		
			List<String> layer1 = allLayers.get(0);
			List<String> layer2 = allLayers.get(1);
			List<String> layer3 = allLayers.get(2);
			List<String> layer4 = allLayers.get(3);
			List<String> layer5 = allLayers.get(4);
			List<String> layer6 = allLayers.get(5);
						
			
			
			double annualDiscountRate = read.get_discount_rate() / 100;
			int total_Periods = read.get_total_periods();
			int total_AgeClasses = total_Periods - 1;		//loop from age 1 to age total_AgeClasses (set total_AgeClasses=total_Periods-1)
			int total_methods = 6;
			int total_PBe_Prescriptions = 5;
			int total_GSe_Prescriptions = 5;
			int total_EAe_Prescriptions = 5;
			int total_MS_Prescriptions = 5;
			int total_BS_Prescriptions = 5;
			int total_PBr_Prescriptions = 5;
			int total_GSr_Prescriptions = 5;
			int total_EAr_Prescriptions = 5;
			int SRDage;
			
			boolean allow_Non_Existing_Prescription = false;
			

			List<Double> objlist = new ArrayList<Double>();				//objective coefficient
			List<String> vnamelist = new ArrayList<String>();			//variable name
			List<Double> vlblist = new ArrayList<Double>();				//lower bound
			List<Double> vublist = new ArrayList<Double>();				//upper bound
			List<IloNumVarType> vtlist = new ArrayList<IloNumVarType>();//variable type
			
			int nvars = 0;
			int nV = 0;
	
			
			// Declare arrays to keep variables	
			int[] y = new int [total_softConstraints];	//y(j)
			int[] l = new int [total_softConstraints];	//l(j)
			int[] u = new int [total_softConstraints];	//u(j)
			int[] z = new int [total_hardConstraints];	//z(k)
			int[] v = new int [total_freeConstraints];	//v(n)
			
			int[][][][][][][] x = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_methods];		//x(s1,s2,s3,s4,s5,s6)(q)
			int[][][][][][][] xNGe = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_Periods + 1];						//xNGe(s1,s2,s3,s4,s5,s6)(t)
			int[][][][][][][][] xPBe = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_PBe_Prescriptions][total_Periods + 1];		//xPBe(s1,s2,s3,s4,s5,s6)(i,t)
			int[][][][][][][][] xGSe = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_GSe_Prescriptions][total_Periods + 1];			//xGSe(s1,s2,s3,s4,s5,s6)(i,t)
			int[][][][][][][][][][] xEAe = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()]
							[total_Periods + 1][layer5.size()][total_EAe_Prescriptions][total_Periods + 1];		//xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R)(i)(t)
									// total_Periods + 1 because tR starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][] xMS = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_PBe_Prescriptions][total_Periods + 1];		//xMS(s1,s2,s3,s4,s5,s6)(i,t)
			int[][][][][][][][] xBS = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()][total_PBe_Prescriptions][total_Periods + 1];		//xBS(s1,s2,s3,s4,s5,s6)(i,t)			

			
			int[][][][][][][] xNGr = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses + 1];		//xNGr(s1,s2,s3,s4,s5)(t)(a)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][] xPBr = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_PBr_Prescriptions][total_Periods + 1][total_AgeClasses + 1];		//xPBr(s1,s2,s3,s4,s5)(i)(t)(a)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][] xGSr = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_GSr_Prescriptions][total_Periods + 1][total_AgeClasses + 1];		//xGSr(s1,s2,s3,s4,s5)(i)(t)(a)
									// total_Periods + 1 because t starts from 1 to total_Periods, ignore the 0
			int[][][][][][][][][][] xEAr = new int
					[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()]
							[total_Periods + 1][total_AgeClasses + 1][layer5.size()][total_EAr_Prescriptions][total_Periods + 1];		//xEAr(s1,s2,s3,s4,s5)(tR)(a)(s5R)(i)(t)
									// total_Periods + 1 because tR starts from 1 to total_Periods, ignore the 0
			
			
			// Declare arrays to keep Fire variables	//f(s1,s2,s3,s4,s5,s6)(t)
			int[][][][][][][] fire = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][total_Periods + 1][layer5.size()];						
			

			
			//Get the 2 parameter V(s1,s2,s3,s4,s5,s6) and A(s1,s2,s3,s4,s5,s6)
			String[][] Input2_value = read.get_MO_Values();	
			double[][][][][][] StrataArea = new double[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()];
			int[][][][][][] StartingAge = new int[layer1.size()][layer2.size()][layer3.size()][layer4.size()][layer5.size()][layer6.size()];			
			
			
			
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				
				//Loop through all modeled_strata to find if the names matched and get the total area and age class
				for (int i = 0; i < read.get_MO_TotalRows(); i++) {			
					if (Input2_value[i][0].equals(strata)) {
						StrataArea[s1][s2][s3][s4][s5][s6] = Double.parseDouble(Input2_value[i][7]);		//area
						
						if (Input2_value[i][read.get_MO_TotalColumns() - 2].toString().equals("null")) {
							StartingAge[s1][s2][s3][s4][s5][s6] = 1;		//Assume age class = 1 if not found any yield table for this existing strata
						} else {
							StartingAge[s1][s2][s3][s4][s5][s6] = Integer.parseInt(Input2_value[i][read.get_MO_TotalColumns() - 2]);	//age class
						}										
					}	
				}	
			}						
			
								
			
			// Get the non-replacing disturbances %		
			double[][] msPercent = new double[layer5.size()][layer6.size()];
			double[][] bsPercent = new double[layer5.size()][layer6.size()];
			int element_Count = 0;
			for (int s5 = 0; s5 < layer5.size(); s5++) {
				for (int s6 = 0; s6 < layer6.size(); s6++) {
					msPercent[s5][s6] = msProportion[element_Count];
					bsPercent[s5][s6] = bsProportion[element_Count];
					element_Count++;	
				}
			}

			// Get the replacing disturbances %		
			double[][] rdPercent = new double[layer5.size()][layer5.size()];
			int item_Count = 0;
			for (int s5 = 0; s5 < layer5.size(); s5++) {
				for (int ss5 = 0; ss5 < layer5.size(); ss5++) {
					rdPercent[s5][ss5] = rdProportion[item_Count];
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
				vublist.add(hardConstraints_UB[k]);				// Constraints 5 is set here as UB
				vtlist.add(IloNumVarType.Float);
				z[k] = nvars;
				nvars++;				
			}
			nV = nvars;	
			
			// Create free constraint decision variables v(n)			
			for (int n = 0; n < total_freeConstraints; n++) {
				objlist.add((double) 0);
//				vnamelist.add("v(" + n + ")");
				vnamelist.add("v_" + n);
				vlblist.add((double) 0);				// 0 if not allow negative multiplier
//				vlblist.add(-Double.MAX_VALUE);			// -MAX_VALUE if allow negative multiplier, need to redesign flow logic
				vublist.add(Double.MAX_VALUE);			// MAX_VALUE is UB
				vtlist.add(IloNumVarType.Float);
				v[n] = nvars;
				nvars++;				
			}
			nV = nvars;	
						
			// Create decision variables x(s1,s2,s3,s4,s5,s6)(q)			
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int q = 0; q < total_methods; q++) {
					objlist.add((double) 0);
					vnamelist.add("x_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + q);
					if (q == 4) {	// Mixed Fire
						vlblist.add((double) msPercent[s5][s6] / 100 * StrataArea[s1][s2][s3][s4][s5][s6]);
						vublist.add((double) msPercent[s5][s6] / 100 * StrataArea[s1][s2][s3][s4][s5][s6]);
					} 
					else if (q == 5) {	// Bark Beetle
						vlblist.add((double) bsPercent[s5][s6] / 100 * StrataArea[s1][s2][s3][s4][s5][s6]);
						vublist.add((double) bsPercent[s5][s6] / 100 * StrataArea[s1][s2][s3][s4][s5][s6]);
					}
					else {
						vlblist.add((double) 0);
						vublist.add(Double.MAX_VALUE);
					}
					vtlist.add(IloNumVarType.Float);
					x[s1][s2][s3][s4][s5][s6][q] = nvars;
					nvars++;
				}
			}						
								
			nV = nvars;
			
			// Create decision variables xNGe(s1,s2,s3,s4,s5,s6)(t)	
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				if (sm_strata == null || sm_strata.contains(strata) && sm_method_choice_for_strata.get(sm_strata.indexOf(strata)).contains("NG_E" + " " + 0))	// Boost 1 (a.k.a. Silviculture Method)
					for (int t = 1; t <= total_Periods; t++) {
						String var_name = "xNG_E_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + t;										
						if (!allow_Non_Existing_Prescription) {		// Boost 2
							if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
								objlist.add((double) 0);			
								vnamelist.add(var_name);										
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								vtlist.add(IloNumVarType.Float);
								xNGe[s1][s2][s3][s4][s5][s6][t] = nvars;
								nvars++;
							}
						} else {
							objlist.add((double) 0);			
							vnamelist.add(var_name);										
							vlblist.add((double) 0);
							vublist.add(Double.MAX_VALUE);
							vtlist.add(IloNumVarType.Float);
							xNGe[s1][s2][s3][s4][s5][s6][t] = nvars;
							nvars++;
						}
					}
			}														
			nV = nvars;
			
			// Create decision variables xPBe(s1,s2,s3,s4,s5,s6)(i,t)	
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int i = 0; i < total_PBe_Prescriptions; i++) {
					if (sm_strata == null || sm_strata.contains(strata) && sm_method_choice_for_strata.get(sm_strata.indexOf(strata)).contains("PB_E" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)				
						for (int t = 1; t <= total_Periods; t++) {
							String var_name = "xPB_E_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + i + "," + t;										
							if (!allow_Non_Existing_Prescription) {		// Boost 2
								if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									vtlist.add(IloNumVarType.Float);
									xPBe[s1][s2][s3][s4][s5][s6][i][t] = nvars;
									nvars++;
								}
							} else {
								objlist.add((double) 0);
								vnamelist.add(var_name);
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								vtlist.add(IloNumVarType.Float);
								xPBe[s1][s2][s3][s4][s5][s6][i][t] = nvars;
								nvars++;
							}
						}
				}
			}														
			nV = nvars;			
			
			// Create decision variables xGSe(s1,s2,s3,s4,s5,s6)(i,t)		
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int i = 0; i < total_GSe_Prescriptions; i++) {
					if (sm_strata == null || sm_strata.contains(strata) && sm_method_choice_for_strata.get(sm_strata.indexOf(strata)).contains("GS_E" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
						for (int t = 1; t <= total_Periods; t++) {
							String var_name = "xGS_E_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + i + "," + t;										
							if (!allow_Non_Existing_Prescription) {		// Boost 2
								if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									vtlist.add(IloNumVarType.Float);
									xGSe[s1][s2][s3][s4][s5][s6][i][t] = nvars;
									nvars++;
								}
							} else {
								objlist.add((double) 0);
								vnamelist.add(var_name);
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								vtlist.add(IloNumVarType.Float);
								xGSe[s1][s2][s3][s4][s5][s6][i][t] = nvars;
								nvars++;
							}
						}
				}
			}														
			nV = nvars;																
			
			// Create decision variables xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R)(i)(t)
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (model_strata.contains(strataName)) {
										for (int tR = 1; tR <= total_Periods; tR++) {
											for (int s5R = 0; s5R < layer5.size(); s5R++) {
												int rotationAge = tR + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
												String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + rotationAge;						
												if (covertype_conversions_and_existing_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
													for (int i = 0; i < total_EAe_Prescriptions; i++) {
														if (sm_strata == null || sm_strata.contains(strataName) && sm_method_choice_for_strata.get(sm_strata.indexOf(strataName)).contains("EA_E" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
															for (int t = 1; t <= tR; t++) {
																String var_name = "xEA_E_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6) + "," + tR + "," + layer5.get(s5R) + "," + i + "," + t ;										
																String yield_table_name_to_find = Get_Variable_Information.get_yield_table_name_to_find(var_name);	
																if (yield_table_name_to_find.contains("rotation_age")) {
																	yield_table_name_to_find = yield_table_name_to_find.replace("rotation_age", String.valueOf(rotationAge));
																}
																if (!allow_Non_Existing_Prescription) {		// Boost 2
																	if (yield_tables_names_list.contains(yield_table_name_to_find)) {
																		objlist.add((double) 0);
																		vnamelist.add(var_name);
																		vlblist.add((double) 0);
																		vublist.add(Double.MAX_VALUE);
																		vtlist.add(IloNumVarType.Float);
																		xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t] = nvars;
																		nvars++;
																	}
																} else {
																	objlist.add((double) 0);
																	vnamelist.add(var_name);
																	vlblist.add((double) 0);
																	vublist.add(Double.MAX_VALUE);
																	vtlist.add(IloNumVarType.Float);
																	xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t] = nvars;
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
				}
			}
			nV = nvars;				
	
			// Create decision variables xMS(s1,s2,s3,s4,s5,s6)(i,t)		
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int i = 0; i < total_MS_Prescriptions; i++) {
					if (sm_strata == null || sm_strata.contains(strata) && sm_method_choice_for_strata.get(sm_strata.indexOf(strata)).contains("MS_E" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
						for (int t = 1; t <= total_Periods; t++) {
							String var_name = "xMS_E_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6)  + "," + i + "," + t;										
							if (!allow_Non_Existing_Prescription) {		// Boost 2
								if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									vtlist.add(IloNumVarType.Float);
									xMS[s1][s2][s3][s4][s5][s6][i][t] = nvars;
									nvars++;	
								}
							} else {
								objlist.add((double) 0);
								vnamelist.add(var_name);
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								vtlist.add(IloNumVarType.Float);
								xMS[s1][s2][s3][s4][s5][s6][i][t] = nvars;
								nvars++;
							}
						}
				}
			}														
			nV = nvars;	
			
			// Create decision variables xBS(s1,s2,s3,s4,s5,s6)(i,t)		
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int i = 0; i < total_BS_Prescriptions; i++) {
					if (sm_strata == null || sm_strata.contains(strata) && sm_method_choice_for_strata.get(sm_strata.indexOf(strata)).contains("BS_E" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
						for (int t = 1; t <= total_Periods; t++) {
							String var_name = "xBS_E_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + layer6.get(s6)  + "," + i + "," + t;										
							if (!allow_Non_Existing_Prescription) {		// Boost 2
								if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									vtlist.add(IloNumVarType.Float);
									xBS[s1][s2][s3][s4][s5][s6][i][t] = nvars;
									nvars++;	
								}
							} else {
								objlist.add((double) 0);
								vnamelist.add(var_name);
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								vtlist.add(IloNumVarType.Float);
								xBS[s1][s2][s3][s4][s5][s6][i][t] = nvars;
								nvars++;
							}									
						}
				}
			}														
			nV = nvars;	
			
			// Create decision variables xNGr(s1,s2,s3,s4,s5)(t)(a)
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				if (sm_strata_without_sizeclass_and_covertype == null || sm_strata_without_sizeclass_and_covertype.contains(strata) && 
						sm_method_choice_for_strata_without_sizeclass_and_covertype.get(sm_strata_without_sizeclass_and_covertype.indexOf(strata)).contains("NG_R" + " " + 0))	// Boost 1 (a.k.a. Silviculture Method)
					for (int s5 = 0; s5 < layer5.size(); s5++) {
						for (int t = 2; t <= total_Periods; t++) {
							for (int a = 1; a <= t-1; a++) {
								String var_name = "xNG_R_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + t + "," + a;										
								if (!allow_Non_Existing_Prescription) {		// Boost 2
									if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										vtlist.add(IloNumVarType.Float);
										xNGr[s1][s2][s3][s4][s5][t][a] = nvars;
										nvars++;
									}
								} else {
									objlist.add((double) 0);
									vnamelist.add(var_name);							
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									vtlist.add(IloNumVarType.Float);
									xNGr[s1][s2][s3][s4][s5][t][a] = nvars;
									nvars++;
								}
							}
						}
					}
			}					
			nV = nvars;
			
			// Create decision variables xPBr(s1,s2,s3,s4,s5)(i)(t)(a)
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int i = 0; i < total_PBr_Prescriptions; i++) {
						if (sm_strata_without_sizeclass_and_covertype == null || sm_strata_without_sizeclass_and_covertype.contains(strata) && 
								sm_method_choice_for_strata_without_sizeclass_and_covertype.get(sm_strata_without_sizeclass_and_covertype.indexOf(strata)).contains("PB_R" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
							for (int t = 2; t <= total_Periods; t++) {
								for (int a = 1; a <= t - 1; a++) {
									String var_name = "xPB_R_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + i + "," + t + "," + a;										
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
											objlist.add((double) 0);
											vnamelist.add(var_name);							
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xPBr[s1][s2][s3][s4][s5][i][t][a] = nvars;
											nvars++;
										}
									} else {
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										vtlist.add(IloNumVarType.Float);
										xPBr[s1][s2][s3][s4][s5][i][t][a] = nvars;
										nvars++;
									}
								}
							}
					}
				}
			}						
			nV = nvars;
			
			// Create decision variables xGSr(s1,s2,s3,s4,s5)(i)(t)(a)
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int i = 0; i < total_GSr_Prescriptions; i++) {
						if (sm_strata_without_sizeclass_and_covertype == null || sm_strata_without_sizeclass_and_covertype.contains(strata) && 
								sm_method_choice_for_strata_without_sizeclass_and_covertype.get(sm_strata_without_sizeclass_and_covertype.indexOf(strata)).contains("GS_R" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
							for (int t = 2; t <= total_Periods; t++) {
								for (int a = 1; a <= t - 1; a++) {
									String var_name = "xGS_R_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + i + "," + t + "," + a;										
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
											objlist.add((double) 0);
											vnamelist.add(var_name);							
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											vtlist.add(IloNumVarType.Float);
											xGSr[s1][s2][s3][s4][s5][i][t][a] = nvars;
											nvars++;
										}
									} else {
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										vtlist.add(IloNumVarType.Float);
										xGSr[s1][s2][s3][s4][s5][i][t][a] = nvars;
										nvars++;
									}
								}
							}
					}
				}
			}
			nV = nvars;
			
			// Create decision variables xEAr(s1,s2,s3,s4,s5)(tR)(aR)(s5R)(t)
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int tR = 2; tR <= total_Periods; tR++) {
						for (int aR = 1; aR <= tR - 1; aR++) {
							for (int s5R = 0; s5R < layer5.size(); s5R++) {
								String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + aR;						
								if (covertype_conversions_and_regeneration_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
									for (int i = 0; i < total_EAr_Prescriptions; i++) {
										if (sm_strata_without_sizeclass_and_covertype == null || sm_strata_without_sizeclass_and_covertype.contains(strata) && 
												sm_method_choice_for_strata_without_sizeclass_and_covertype.get(sm_strata_without_sizeclass_and_covertype.indexOf(strata)).contains("EA_R" + " " + i))	// Boost 1 (a.k.a. Silviculture Method)
											for (int t = tR-aR+1; t <= tR; t++) {
												String var_name = "xEA_R_" + layer1.get(s1) + "," + layer2.get(s2) + "," + layer3.get(s3) + "," + layer4.get(s4) + "," + layer5.get(s5) + "," + tR + "," + aR + "," + layer5.get(s5R) + "," + i + "," + t;										
												if (!allow_Non_Existing_Prescription) {		// Boost 2
													if (yield_tables_names_list.contains(Get_Variable_Information.get_yield_table_name_to_find(var_name))) {
														objlist.add((double) 0);
														vnamelist.add(var_name);	
														vlblist.add((double) 0);
														vublist.add(Double.MAX_VALUE);
														vtlist.add(IloNumVarType.Float);
														xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t] = nvars;
														nvars++;
													}
												} else {
													objlist.add((double) 0);
													vnamelist.add(var_name);	
													vlblist.add((double) 0);
													vublist.add(Double.MAX_VALUE);
													vtlist.add(IloNumVarType.Float);
													xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t] = nvars;
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
			
			
			//-----------------------Fire variables
			// Create decision variables fire(s1,s2,s3,s4,s5)(t,c)	
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
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
			nV = nvars;							
			
			
			
			// Convert list to 1-D arrays
			double[] objvals = Stream.of(objlist.toArray(new Double[objlist.size()])).mapToDouble(Double::doubleValue).toArray();
			String[] vname = vnamelist.toArray(new String[vnamelist.size()]);
			double[] vlb = Stream.of(vlblist.toArray(new Double[vlblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] vub = Stream.of(vublist.toArray(new Double[vublist.size()])).mapToDouble(Double::doubleValue).toArray();
			IloNumVarType[] vtype = vtlist.toArray(new IloNumVarType[vtlist.size()]);
								//Note: vname and vtype may cause problems because of wrong casting
			System.out.println("Total decision variables as in PRISM obj. function eq. (1):   " + nvars + "             " + dateFormat.format(new Date()));
					
			
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			// CREATE CONSTRAINTS-------------------------------------------------
			
			
			// Constraints 2---------------------------------------------------
			List<List<Integer>> c2_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c2_valuelist = new ArrayList<List<Double>>();
			List<Double> c2_lblist = new ArrayList<Double>();	
			List<Double> c2_ublist = new ArrayList<Double>();
			int c2_num = 0;
			
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (2):   " + c2_num + "             " + dateFormat.format(new Date()));
			
	
			// Constraints 3-------------------------------------------------
			List<List<Integer>> c3_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c3_valuelist = new ArrayList<List<Double>>();
			List<Double> c3_lblist = new ArrayList<Double>();	
			List<Double> c3_ublist = new ArrayList<Double>();
			int c3_num = 0;
			
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
				c3_lblist.add((double) 0);			// Lower bound set to 0	because y[j] >= u[j]
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (3):   " + c3_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 4ab  (hard) and 4cd (free)------------------------------
			// are set as the bounds of variables
			System.out.println("Total constraints as in PRISM model formualtion eq. (4):   0             " + dateFormat.format(new Date()));
			
			
			// Constraints 5 (flow)------------------------------------------------
			List<List<Integer>> c5_indexlist = new ArrayList<List<Integer>>();
			List<List<Double>> c5_valuelist = new ArrayList<List<Double>>();
			List<Double> c5_lblist = new ArrayList<Double>();
			List<Double> c5_ublist = new ArrayList<Double>();
			int c5_num = 0;
					
			
			List<Integer> bookkeeping_ID_list = new ArrayList<Integer>();	// This list contains all GUI - IDs of the Basic Constraints
			List<Integer> bookkeeping_Var_list = new ArrayList<Integer>();			// This list contains all SOLVER Variables - IDs of the Basic Constraints
			if (flow_set_list.size() > 0) {		// Add flow constraints if there is at least a flow set
				int current_freeConstraint = 0;
				int current_softConstraint = 0;
				int current_hardConstraint = 0;	
				int constraint_type_col = constraint_column_names_list.indexOf("bc_type");				
				int constraint_id_col = constraint_column_names_list.indexOf("bc_id");
				
				for (int i = 1; i < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; i++) {	// Loop from 1 because the first row of the Basic Constraints file is just title												
					int ID = Integer.parseInt(bc_values[i][constraint_id_col]);
					bookkeeping_ID_list.add(ID);		
									
					if (bc_values[i][constraint_type_col].equals("SOFT")) {
						bookkeeping_Var_list.add(y[current_softConstraint]);
						current_softConstraint++;
					}
					
					if (bc_values[i][constraint_type_col].equals("HARD")) {
						bookkeeping_Var_list.add(z[current_hardConstraint]);
						current_hardConstraint++;
					}
					
					if (bc_values[i][constraint_type_col].equals("FREE")) {
						bookkeeping_Var_list.add(v[current_freeConstraint]);
						current_freeConstraint++;
					}	
				}
								
				
				// Add constraints for each flow set
				for (int i = 0; i < flow_set_list.size(); i++) {		// loop each flow set (or each row of the flow_constraints_table)
					if (flow_type_list.get(i).equals("HARD")) {		// ONly add constraint if flow type is HARD
						int this_set_total_constraints = flow_set_list.get(i).size() - 1;
						for (int j = 0; j < this_set_total_constraints; j++) {
							
							if (flow_lowerbound_percentage_list.get(i) != null) {	// add when lowerbound_percentage is not null
								// Add constraint				Right term - lowerbound % * Left term >= 0
								c5_indexlist.add(new ArrayList<Integer>());
								c5_valuelist.add(new ArrayList<Double>());
								
								// Add Right term including all IDs in the (j+1) term
								for (int ID : flow_set_list.get(i).get(j + 1)) {
									if (bookkeeping_ID_list.contains(ID)) {		// Add book keeping variable
										int gui_table_id = bookkeeping_ID_list.indexOf(ID);		
										int var_id = bookkeeping_Var_list.get(gui_table_id);
										c5_indexlist.get(c5_num).add(var_id);
										c5_valuelist.get(c5_num).add((double) 1);
									}
								}
								
								// Add - % * Left term including all IDs in the (j) term
								for (int ID : flow_set_list.get(i).get(j)) {
									if (bookkeeping_ID_list.contains(ID)) {		// Add book keeping variable
										int gui_table_id = bookkeeping_ID_list.indexOf(ID);		
										int var_id = bookkeeping_Var_list.get(gui_table_id);
										c5_indexlist.get(c5_num).add(var_id);
										c5_valuelist.get(c5_num).add((double) -flow_lowerbound_percentage_list.get(i) / 100);		// -1 * lowerbound_percentage here
									}
								}
								
								// add bounds
								c5_lblist.add((double) 0);			// Lower bound set to 0	
								c5_ublist.add(Double.MAX_VALUE);		// Upper bound set to max
								c5_num++;
							}
							
							
							if (flow_upperbound_percentage_list.get(i) != null) {	// add when upperbound_percentage is not null
								// Add constraint				Right term - upperbound % * Left term <= 0
								c5_indexlist.add(new ArrayList<Integer>());
								c5_valuelist.add(new ArrayList<Double>());
								
								// Add Right term including all IDs in the (j+1) term
								for (int ID : flow_set_list.get(i).get(j + 1)) {
									if (bookkeeping_ID_list.contains(ID)) {		// Add book keeping variable
										int gui_table_id = bookkeeping_ID_list.indexOf(ID);		
										int var_id = bookkeeping_Var_list.get(gui_table_id);
										c5_indexlist.get(c5_num).add(var_id);
										c5_valuelist.get(c5_num).add((double) 1);
									}
								}
								
								// Add - % * Left term including all IDs in the (j) term
								for (int ID : flow_set_list.get(i).get(j)) {
									if (bookkeeping_ID_list.contains(ID)) {		// Add book keeping variable
										int gui_table_id = bookkeeping_ID_list.indexOf(ID);		
										int var_id = bookkeeping_Var_list.get(gui_table_id);
										c5_indexlist.get(c5_num).add(var_id);
										c5_valuelist.get(c5_num).add((double) -flow_upperbound_percentage_list.get(i) / 100);		// -1 * upperbound_percentage here
									}
								}
								
								// add bounds
								c5_lblist.add(-Double.MAX_VALUE);	// lower bound set to min	
								c5_ublist.add((double) 0);			// Upper bound set to 0
								c5_num++;
							}							
						}
					}
				}
			}
			
			double[] c5_lb = Stream.of(c5_lblist.toArray(new Double[c5_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
			double[] c5_ub = Stream.of(c5_ublist.toArray(new Double[c5_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
			int[][] c5_index = new int[c5_num][];
			double[][] c5_value = new double[c5_num][];
		
			for (int i = 0; i < c5_num; i++) {
				c5_index[i] = new int[c5_indexlist.get(i).size()];
				c5_value[i] = new double[c5_indexlist.get(i).size()];
				for (int j = 0; j < c5_indexlist.get(i).size(); j++) {
					c5_index[i][j] = c5_indexlist.get(i).get(j);
					c5_value[i][j] = c5_valuelist.get(i).get(j);			
				}
			}		
			System.out.println("Total constraints as in PRISM model formualtion eq. (5):   " + c5_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 6-------------------------------------------------
			List<List<Integer>> c6_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c6_valuelist = new ArrayList<List<Double>>();
			List<Double> c6_lblist = new ArrayList<Double>();	
			List<Double> c6_ublist = new ArrayList<Double>();
			int c6_num = 0;
			
			
			//6a
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (model_strata.contains(strataName)) {		
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
									if (model_strata.contains(strataName)) {
										// Add constraint
										c6_indexlist.add(new ArrayList<Integer>());
										c6_valuelist.add(new ArrayList<Double>());

										// Add x(s1,s2,s3,s4,s5,s6)(4)
										c6_indexlist.get(c6_num).add(x[s1][s2][s3][s4][s5][s6][4]);
										c6_valuelist.get(c6_num).add((double) 1);
										
										//Add - sigma(i) xMS(s1,s2,s3,s4,s5,s6)[i][1]
										for (int i = 0; i < total_MS_Prescriptions; i++) {
											if(xMS[s1][s2][s3][s4][s5][s6][i][1] > 0) {		// if variable is defined, this value would be > 0 
												c6_indexlist.get(c6_num).add(xMS[s1][s2][s3][s4][s5][s6][i][1]);
												c6_valuelist.get(c6_num).add((double) -1);
											}
										}

										// add bounds
										c6_lblist.add((double) 0);
										c6_ublist.add((double) 0);
										c6_num++;
										
										// Remove this constraint if total number of variables added is 1 (only x[s1][s2][s3][s4][s5][s6][4] is added)
										if (c6_indexlist.get(c6_num - 1).size() == 1) {
											c6_indexlist.remove(c6_num - 1);
											c6_valuelist.remove(c6_num - 1);
											c6_lblist.remove(c6_num - 1);
											c6_ublist.remove(c6_num - 1);
											c6_num--;
											
											// Set x[s1][s2][s3][s4][s5][s6][4] to be zero if boost 2 is implemented but associated prescriptions does not exist
											vlb[x[s1][s2][s3][s4][s5][s6][4]] = 0;
											vub[x[s1][s2][s3][s4][s5][s6][4]] = 0;
										}									
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
									if (model_strata.contains(strataName)) {
										for (int i = 0; i < total_MS_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods - 1; t++) {
												if(xMS[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 
													// Add constraint
													c6_indexlist.add(new ArrayList<Integer>());
													c6_valuelist.add(new ArrayList<Double>());
													
													//Add xMS(s1,s2,s3,s4,s5,s6)[i][t]													
													c6_indexlist.get(c6_num).add(xMS[s1][s2][s3][s4][s5][s6][i][t]);
													c6_valuelist.get(c6_num).add((double) 1);
													
													//Add -xMS(s1,s2,s3,s4,s5,s6)[i][t+1]
													c6_indexlist.get(c6_num).add(xMS[s1][s2][s3][s4][s5][s6][i][t + 1]);
													c6_valuelist.get(c6_num).add((double) -1);
													
													//add bounds
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
				}
			}	
														
					
			//6d
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (model_strata.contains(strataName)) {
										// Add constraint
										c6_indexlist.add(new ArrayList<Integer>());
										c6_valuelist.add(new ArrayList<Double>());

										// Add x(s1,s2,s3,s4,s5,s6)(5)
										c6_indexlist.get(c6_num).add(x[s1][s2][s3][s4][s5][s6][5]);
										c6_valuelist.get(c6_num).add((double) 1);
										
										//Add - sigma(i) xBS(s1,s2,s3,s4,s5,s6)[i][1]
										for (int i = 0; i < total_BS_Prescriptions; i++) {
											if(xBS[s1][s2][s3][s4][s5][s6][i][1] > 0) {		// if variable is defined, this value would be > 0 
												c6_indexlist.get(c6_num).add(xBS[s1][s2][s3][s4][s5][s6][i][1]);
												c6_valuelist.get(c6_num).add((double) -1);
											}
										}

										// add bounds
										c6_lblist.add((double) 0);
										c6_ublist.add((double) 0);
										c6_num++;
										
										// Remove this constraint if total number of variables added is 1 (only x[s1][s2][s3][s4][s5][s6][5] is added)
										if (c6_indexlist.get(c6_num - 1).size() == 1) {
											c6_indexlist.remove(c6_num - 1);
											c6_valuelist.remove(c6_num - 1);
											c6_lblist.remove(c6_num - 1);
											c6_ublist.remove(c6_num - 1);
											c6_num--;
											
											// Set x[s1][s2][s3][s4][s5][s6][5] to be zero if boost 2 is implemented but associated prescriptions does not exist
											vlb[x[s1][s2][s3][s4][s5][s6][5]] = 0;
											vub[x[s1][s2][s3][s4][s5][s6][5]] = 0;
										}									
									}
								}
							}
						}
					}
				}
			}		
			
			
			//6e
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int s5 = 0; s5 < layer5.size(); s5++) {
								for (int s6 = 0; s6 < layer6.size(); s6++) {
									String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
									if (model_strata.contains(strataName)) {
										for (int i = 0; i < total_BS_Prescriptions; i++) {
											for (int t = 1; t <= total_Periods - 1; t++) {
												if(xBS[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 
													// Add constraint
													c6_indexlist.add(new ArrayList<Integer>());
													c6_valuelist.add(new ArrayList<Double>());
													
													//Add xBS(s1,s2,s3,s4,s5,s6)[i][t]													
													c6_indexlist.get(c6_num).add(xBS[s1][s2][s3][s4][s5][s6][i][t]);
													c6_valuelist.get(c6_num).add((double) 1);
													
													//Add -xBS(s1,s2,s3,s4,s5,s6)[i][t+1]
													c6_indexlist.get(c6_num).add(xBS[s1][s2][s3][s4][s5][s6][i][t + 1]);
													c6_valuelist.get(c6_num).add((double) -1);
													
													//add bounds
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (6):   " + c6_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 7-------------------------------------------------
			List<List<Integer>> c7_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c7_valuelist = new ArrayList<List<Double>>();
			List<Double> c7_lblist = new ArrayList<Double>();	
			List<Double> c7_ublist = new ArrayList<Double>();
			int c7_num = 0;
			
			
			//7a
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				//Add constraint
				c7_indexlist.add(new ArrayList<Integer>());
				c7_valuelist.add(new ArrayList<Double>());
				
				//Add x(s1,s2,s3,s4,s5,s6)[0]
				c7_indexlist.get(c7_num).add(x[s1][s2][s3][s4][s5][s6][0]);
				c7_valuelist.get(c7_num).add((double) 1);
				
				//Add -xNGe(s1,s2,s3,s4,s5,s6)(1)
				c7_indexlist.get(c7_num).add(xNGe[s1][s2][s3][s4][s5][s6][1]);
				c7_valuelist.get(c7_num).add((double) -1);
		
				//add bounds
				c7_lblist.add((double) 0);
				c7_ublist.add((double) 0);
				c7_num++;
				
				// Remove this constraint if total number of variables added is 1 (only x[s1][s2][s3][s4][s5][s6][0] is added)
				if (c7_indexlist.get(c7_num - 1).size() == 1) {
					c7_indexlist.remove(c7_num - 1);
					c7_valuelist.remove(c7_num - 1);
					c7_lblist.remove(c7_num - 1);
					c7_ublist.remove(c7_num - 1);
					c7_num--;
					
					// Set x[s1][s2][s3][s4][s5][s6][0] to be zero if boost 2 is implemented but associated prescriptions does not exist
					vlb[x[s1][s2][s3][s4][s5][s6][0]] = 0;
					vub[x[s1][s2][s3][s4][s5][s6][0]] = 0;
				}
			}														
			
			
			//7b
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int t = 1; t <= total_Periods - 1; t++) {
					if(xNGe[s1][s2][s3][s4][s5][s6][1] > 0) {		// if variable is defined, this value would be > 0
						//Add constraint
						c7_indexlist.add(new ArrayList<Integer>());
						c7_valuelist.add(new ArrayList<Double>());
						
						//Add xNGe(s1,s2,s3,s4,s5,s6)(t)
						SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
						if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
						
						c7_indexlist.get(c7_num).add(xNGe[s1][s2][s3][s4][s5][s6][t]);
						c7_valuelist.get(c7_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);	//SR Fire loss Rate = P(s5,a)/100
				
						// Add -xNGe(s1,s2,s3,s4,s5,s6)(t+1)
						c7_indexlist.get(c7_num).add(xNGe[s1][s2][s3][s4][s5][s6][t + 1]);
						c7_valuelist.get(c7_num).add((double) -1);											
																
						//add bounds
						c7_lblist.add((double) 0);
						c7_ublist.add((double) 0);
						c7_num++;
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (7):   " + c7_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 8-------------------------------------------------
			List<List<Integer>> c8_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c8_valuelist = new ArrayList<List<Double>>();
			List<Double> c8_lblist = new ArrayList<Double>();	
			List<Double> c8_ublist = new ArrayList<Double>();
			int c8_num = 0;
			
			
			//8a
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));		
				//Add constraint
				c8_indexlist.add(new ArrayList<Integer>());
				c8_valuelist.add(new ArrayList<Double>());
				
				//Add x(s1,s2,s3,s4,s5,s6)[1]
				c8_indexlist.get(c8_num).add(x[s1][s2][s3][s4][s5][s6][1]);
				c8_valuelist.get(c8_num).add((double) 1);
				
				//Add - sigma(i) xPBe(s1,s2,s3,s4,s5,s6)[i][1]
				for (int i = 0; i < total_PBe_Prescriptions; i++) {
					if(xPBe[s1][s2][s3][s4][s5][s6][i][1] > 0) {		// if variable is defined, this value would be > 0 
						c8_indexlist.get(c8_num).add(xPBe[s1][s2][s3][s4][s5][s6][i][1]);
						c8_valuelist.get(c8_num).add((double) -1);
					}
				}
				
				//add bounds
				c8_lblist.add((double) 0);
				c8_ublist.add((double) 0);
				c8_num++;
				
				// Remove this constraint if total number of variables added is 1 (only x[s1][s2][s3][s4][s5][s6][1] is added)
				if (c8_indexlist.get(c8_num - 1).size() == 1) {
					c8_indexlist.remove(c8_num - 1);
					c8_valuelist.remove(c8_num - 1);
					c8_lblist.remove(c8_num - 1);
					c8_ublist.remove(c8_num - 1);
					c8_num--;
					
					// Set x[s1][s2][s3][s4][s5][s6][1] to be zero if boost 2 is implemented but associated prescriptions does not exist
					vlb[x[s1][s2][s3][s4][s5][s6][1]] = 0;
					vub[x[s1][s2][s3][s4][s5][s6][1]] = 0;
				}
			}													
			
			
			//8b
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));
				for (int i = 0; i < total_PBe_Prescriptions; i++) {
					for (int t = 1; t <= total_Periods-1; t++) {
						if(xPBe[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 
							//Add constraint
							c8_indexlist.add(new ArrayList<Integer>());
							c8_valuelist.add(new ArrayList<Double>());
							
							//Add xPBe(s1,s2,s3,s4,s5,s6)[i][t]
							SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
							if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
							
							c8_indexlist.get(c8_num).add(xPBe[s1][s2][s3][s4][s5][s6][i][t]);
							c8_valuelist.get(c8_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100
							
							//Add -xPBe(s1,s2,s3,s4,s5,s6)[i][t+1]
							c8_indexlist.get(c8_num).add(xPBe[s1][s2][s3][s4][s5][s6][i][t + 1]);
							c8_valuelist.get(c8_num).add((double) -1);
							
							//add bounds
							c8_lblist.add((double) 0);
							c8_ublist.add((double) 0);
							c8_num++;
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (8):   " + c8_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 9-------------------------------------------------
			List<List<Integer>> c9_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c9_valuelist = new ArrayList<List<Double>>();
			List<Double> c9_lblist = new ArrayList<Double>();	
			List<Double> c9_ublist = new ArrayList<Double>();
			int c9_num = 0;
			
			
			//9a
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));	
				//Add constraint
				c9_indexlist.add(new ArrayList<Integer>());
				c9_valuelist.add(new ArrayList<Double>());
				
				//Add x(s1,s2,s3,s4,s5,s6)[2]
				c9_indexlist.get(c9_num).add(x[s1][s2][s3][s4][s5][s6][2]);
				c9_valuelist.get(c9_num).add((double) 1);							
				
				//Add -xGSe(s1,s2,s3,s4,s5,s6)[i][1]
				for (int i = 0; i < total_GSe_Prescriptions; i++) {
					if(xGSe[s1][s2][s3][s4][s5][s6][i][1] > 0) {		// if variable is defined, this value would be > 0 
						c9_indexlist.get(c9_num).add(xGSe[s1][s2][s3][s4][s5][s6][i][1]);
						c9_valuelist.get(c9_num).add((double) -1);
					}
				}
				
				// add bounds
				c9_lblist.add((double) 0);
				c9_ublist.add((double) 0);
				c9_num++;
				
				// Remove this constraint if total number of variables added is 1 (only x[s1][s2][s3][s4][s5][s6][2] is added)
				if (c9_indexlist.get(c9_num - 1).size() == 1) {
					c9_indexlist.remove(c9_num - 1);
					c9_valuelist.remove(c9_num - 1);
					c9_lblist.remove(c9_num - 1);
					c9_ublist.remove(c9_num - 1);
					c9_num--;
					
					// Set x[s1][s2][s3][s4][s5][s6][2] to be zero if boost 2 is implemented but associated prescriptions does not exist
					vlb[x[s1][s2][s3][s4][s5][s6][2]] = 0;
					vub[x[s1][s2][s3][s4][s5][s6][2]] = 0;
				}
			}						
								

			//9b
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));	
				for (int i = 0; i < total_GSe_Prescriptions; i++) {
					for (int t = 1; t <= total_Periods-1; t++) {
						if(xGSe[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 
							//Add constraint
							c9_indexlist.add(new ArrayList<Integer>());
							c9_valuelist.add(new ArrayList<Double>());
							
							//Add xGSe(s1,s2,s3,s4,s5,s6)[i][t]
							SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
							if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
				
							c9_indexlist.get(c9_num).add(xGSe[s1][s2][s3][s4][s5][s6][i][t]);
							c9_valuelist.get(c9_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100
							
							//Add -xGSe(s1,s2,s3,s4,s5,s6)[i][t+1]
							c9_indexlist.get(c9_num).add(xGSe[s1][s2][s3][s4][s5][s6][i][t + 1]);
							c9_valuelist.get(c9_num).add((double) -1);												
							
							//add bounds
							c9_lblist.add((double) 0);
							c9_ublist.add((double) 0);
							c9_num++;
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (9):   " + c9_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 10-------------------------------------------------
			List<List<Integer>> c10_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c10_valuelist = new ArrayList<List<Double>>();
			List<Double> c10_lblist = new ArrayList<Double>();	
			List<Double> c10_ublist = new ArrayList<Double>();
			int c10_num = 0;
			
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));		
				//Add constraint
				c10_indexlist.add(new ArrayList<Integer>());
				c10_valuelist.add(new ArrayList<Double>());

				//Add x(s1,s2,s3,s4,s5,s6)[3]
				c10_indexlist.get(c10_num).add(x[s1][s2][s3][s4][s5][s6][3]);
				c10_valuelist.get(c10_num).add((double) 1);							
				
				//Add - sigma(tR,s5R)(i) xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][1]	
				for (int tR = 1; tR <= total_Periods; tR++) {
					for (int s5R = 0; s5R < layer5.size(); s5R++) {
						for (int i = 0; i < total_EAe_Prescriptions; i++) {
							int rotationAge = tR + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
							String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + rotationAge;						
							if (covertype_conversions_and_existing_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
								if(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][1] > 0) {		// if variable is defined, this value would be > 0 
									c10_indexlist.get(c10_num).add(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][1]);
									c10_valuelist.get(c10_num).add((double) -1);
								}
							}
						}
					}	
				}
				
				//add bounds
				c10_lblist.add((double) 0);
				c10_ublist.add((double) 0);
				c10_num++;
				
				// Remove this constraint if total number of variables added is 1 (only x[s1][s2][s3][s4][s5][s6][3] is added)
				if (c10_indexlist.get(c10_num - 1).size() == 1) {
					c10_indexlist.remove(c10_num - 1);
					c10_valuelist.remove(c10_num - 1);
					c10_lblist.remove(c10_num - 1);
					c10_ublist.remove(c10_num - 1);
					c10_num--;
					
					// Set x[s1][s2][s3][s4][s5][s6][3] to be zero if boost 2 is implemented but associated prescriptions does not exist
					vlb[x[s1][s2][s3][s4][s5][s6][3]] = 0;
					vub[x[s1][s2][s3][s4][s5][s6][3]] = 0;
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (10):   " + c10_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 11-------------------------------------------------
			List<List<Integer>> c11_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c11_valuelist = new ArrayList<List<Double>>();
			List<Double> c11_lblist = new ArrayList<Double>();	
			List<Double> c11_ublist = new ArrayList<Double>();
			int c11_num = 0;									
			
			// 11
			for (String strata: model_strata) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				int s5 = layer5.indexOf(strata.substring(4,5));
				int s6 = layer6.indexOf(strata.substring(5,6));			
				for (int tR = 1; tR <= total_Periods; tR++) {										
					for (int s5R = 0; s5R < layer5.size(); s5R++) {
						int rotationAge = tR + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
						String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + rotationAge;					
						if (covertype_conversions_and_existing_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
							for (int i = 0; i < total_EAe_Prescriptions; i++) {
								for (int t = 1; t <= tR-1; t++) {
									if(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
										//Add constraint
										c11_indexlist.add(new ArrayList<Integer>());
										c11_valuelist.add(new ArrayList<Double>());
										
										//Add xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][t]
										SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
										if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
								
										c11_indexlist.get(c11_num).add(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t]);
										c11_valuelist.get(c11_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100																											
										
										//Add - xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][t+1]		
										c11_indexlist.get(c11_num).add(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t + 1]);
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (11):   " + c11_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 12-------------------------------------------------
			List<List<Integer>> c12_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c12_valuelist = new ArrayList<List<Double>>();
			List<Double> c12_lblist = new ArrayList<Double>();	
			List<Double> c12_ublist = new ArrayList<Double>();
			int c12_num = 0;
			
			//12a
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
							if (model_strata_without_sizeclass_and_covertype.contains(strataName)) {
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
										
										
										//Add - sigma(s6)	xNGe[s1][s2][s3][s4][s5][s6][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
											if (model_strata.contains(strataName2)) {
												if(xNGe[s1][s2][s3][s4][s5][s6][t] > 0) {		// if variable is defined, this value would be > 0 
													SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
											
													c12_indexlist.get(c12_num).add(xNGe[s1][s2][s3][s4][s5][s6][t]);
													c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	
												}
											}
										}
										
										//Add - sigma(s6)(i)	xPBe[s1][s2][s3][s4][s5][s6][i][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											for (int i = 0; i < total_PBe_Prescriptions; i++) {
												String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
												if (model_strata.contains(strataName2)) {
													if(xPBe[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 
														SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
														if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
												
														c12_indexlist.get(c12_num).add(xPBe[s1][s2][s3][s4][s5][s6][i][t]);
														c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	
													}
												}
											}
										}
																
										//Add - sigma(s6)(i)	xGSe[s1][s2][s3][s4][s5][s6][i][t]
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											for (int i = 0; i < total_GSe_Prescriptions; i++) {
												String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
												if (model_strata.contains(strataName2)) {
													if(xGSe[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 
														SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
														if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
												
														c12_indexlist.get(c12_num).add(xGSe[s1][s2][s3][s4][s5][s6][i][t]);
														c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	
													}
												}
											}
										}
																								
							
										// Add - sigma(s6)(tR)(s5R)(i)   xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R)(i)(t)
										for (int s6 = 0; s6 < layer6.size(); s6++) {
											String strataName2 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);					
											if (model_strata.contains(strataName2)) {
												for (int tR = t + 1; tR <= total_Periods; tR++) {		// tR
													for (int s5R = 0; s5R < layer5.size(); s5R++) {		// s5R
														for (int i = 0; i < total_EAe_Prescriptions; i++) {	// i
															int rotationAge = tR + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
															String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + rotationAge;								
															if (covertype_conversions_and_existing_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
																if(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
																	SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
																	if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
															
																	c12_indexlist.get(c12_num).add(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t]);
																	c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	
																}
															}
														}
													}
												}
											}
										}					
										
										//Add - sigma(a)	xNGr[s1][s2][s3][s4][s5][t][a]
										if (t >= 2) {
											for (int a = 1; a <= t - 1; a++) {
												if(xNGr[s1][s2][s3][s4][s5][t][a] > 0) {		// if variable is defined, this value would be > 0 
													SRDage = a; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
													
													c12_indexlist.get(c12_num).add(xNGr[s1][s2][s3][s4][s5][t][a]);
													c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	\
												}
											}
										}
										
										//Add - sigma(i,a)	xPBr[s1][s2][s3][s4][s5][i][t][a]
										if (t >= 2) {
											for (int i = 0; i < total_PBr_Prescriptions; i++) {
												for (int a = 1; a <= t - 1; a++) {
													if(xPBr[s1][s2][s3][s4][s5][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
														SRDage = a; 
														if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
														
														c12_indexlist.get(c12_num).add(xPBr[s1][s2][s3][s4][s5][i][t][a]);
														c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	
													}
												}
											}
										}
										
										//Add - sigma(i,a)	xGSr[s1][s2][s3][s4][s5][i][t][a]
										if (t >= 2) {
											for (int i = 0; i < total_GSr_Prescriptions; i++) {
												for (int a = 1; a <= t - 1; a++) {
													if(xGSr[s1][s2][s3][s4][s5][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
														SRDage = a; 
														if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
		
														c12_indexlist.get(c12_num).add(xGSr[s1][s2][s3][s4][s5][i][t][a]);
														c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100	
													}
												}
											}
										}

										//Add - sigma(tR)(aR)(s5R)(i)	xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]
										if (t >= 2) {
											for (int tR = t + 1; tR <= total_Periods; tR++) { // tR
												for (int aR = 1; aR <= tR - 1; aR++) {
													for (int s5R = 0; s5R < layer5.size(); s5R++) {		// s5R
														for (int i = 0; i < total_EAr_Prescriptions; i++) {
															String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + aR;						
															if (covertype_conversions_and_regeneration_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
																if(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
																	SRDage = aR - tR + t;
																	if (SRDage > 0) {
																		if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %	
																		
																		c12_indexlist.get(c12_num).add(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]);
																		c12_valuelist.get(c12_num).add((double) - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100
																	}
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
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int t = 1; t <= total_Periods; t++) {
						for (int c = 0; c < layer5.size(); c++) {
							//Add constraint
							c12_indexlist.add(new ArrayList<Integer>());
							c12_valuelist.add(new ArrayList<Double>());
							
							//Add fire[s1][s2][s3][s4][s5][t][c]	*		1-P(s5,c')/100
							c12_indexlist.get(c12_num).add(fire[s1][s2][s3][s4][s5][t][c]);
							c12_valuelist.get(c12_num).add((double) 1 - rdPercent[s5][c] / 100);	//Group the parameters here for the case c = c'	
							
							//Add fire[s1][s2][s3][s4][s5][t][c']
							for (int cc = 0; cc < layer5.size(); cc++) {
								if (cc != c) {
									c12_indexlist.get(c12_num).add(fire[s1][s2][s3][s4][s5][t][cc]);
									c12_valuelist.get(c12_num).add((double) -rdPercent[s5][c] / 100);
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (12):   " + c12_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 13-------------------------------------------------
			List<List<Integer>> c13_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c13_valuelist = new ArrayList<List<Double>>();
			List<Double> c13_lblist = new ArrayList<Double>();	
			List<Double> c13_ublist = new ArrayList<Double>();
			int c13_num = 0;
			
			for (int s1 = 0; s1 < layer1.size(); s1++) {
				for (int s2 = 0; s2 < layer2.size(); s2++) {
					for (int s3 = 0; s3 < layer3.size(); s3++) {
						for (int s4 = 0; s4 < layer4.size(); s4++) {
							for (int t = 1; t <= total_Periods - 1; t++) {
								String strataName1 = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4);
								if (model_strata_without_sizeclass_and_covertype.contains(strataName1)) {	
									for (int c = 0; c < layer5.size(); c++) {
										
										//Add constraint
										c13_indexlist.add(new ArrayList<Integer>());
										c13_valuelist.add(new ArrayList<Double>());
										
										
										//FIRE VARIABLES
										//Add sigma(s5) fire(s1,s2,s3,s4,s5)[t][c]
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											c13_indexlist.get(c13_num).add(fire[s1][s2][s3][s4][s5][t][c]);
											c13_valuelist.get(c13_num).add((double) 1);
										}
										
			
										//NON-FIRE VARIABLES
										//Add sigma(s5)(s6)(i) xEAe(s1,s2,s3,s4,s5,s6)[t][c][i][t]
										for (int s5 = 0; s5 < layer5.size(); s5++) {
											for (int s6 = 0; s6 < layer6.size(); s6++) {
												for (int i = 0; i < total_EAe_Prescriptions; i++) {
													String strataName = layer1.get(s1) + layer2.get(s2) + layer3.get(s3) + layer4.get(s4) + layer5.get(s5) + layer6.get(s6);
													int rotationAge = t + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
													String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + rotationAge;						
													if (model_strata.contains(strataName) && covertype_conversions_and_existing_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
														if(xEAe[s1][s2][s3][s4][s5][s6][t][c][i][t] > 0) {		// if variable is defined, this value would be > 0 
															c13_indexlist.get(c13_num).add(xEAe[s1][s2][s3][s4][s5][s6][t][c][i][t]);
															c13_valuelist.get(c13_num).add((double) 1);
														}
													}
												}
											}
										}
										
										//Add sigma(s5)(a)(i) xEAr(s1,s2,s3,s4,s5)[t][a][c][i][t] only if period t>=2
										if (t >= 2) {
											for (int s5 = 0; s5 < layer5.size(); s5++) {
												for (int a = 1; a <= t - 1; a++) {
													for (int i = 0; i < total_EAr_Prescriptions; i++) {
														String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(c) + " " + a;						
														if (covertype_conversions_and_regeneration_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
															if(xEAr[s1][s2][s3][s4][s5][t][a][c][i][t] > 0) {		// if variable is defined, this value would be > 0 
																c13_indexlist.get(c13_num).add(xEAr[s1][s2][s3][s4][s5][t][a][c][i][t]);
																c13_valuelist.get(c13_num).add((double) 1);
															}
														}
													}
												}
											}
										}
								
										//Add -xNGr(s1,s2,s3,s4,s5=c)[t+1][1]
										if(xNGr[s1][s2][s3][s4][c][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 										
											c13_indexlist.get(c13_num).add(xNGr[s1][s2][s3][s4][c][t + 1][1]);
											c13_valuelist.get(c13_num).add((double) -1);
										}
										
										//Add - sigma(i) xPBr(s1,s2,s3,s4,s5=c)[i][t+1][1]
										for (int i = 0; i < total_PBr_Prescriptions; i++) {
											if(xPBr[s1][s2][s3][s4][c][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
												c13_indexlist.get(c13_num).add(xPBr[s1][s2][s3][s4][c][i][t + 1][1]);
												c13_valuelist.get(c13_num).add((double) -1);
											}
										}
										
										//Add - sigma(i) xGSr(s1,s2,s3,s4,s5=c)[i][t+1][1]
										for (int i = 0; i < total_GSr_Prescriptions; i++) {
											if(xGSr[s1][s2][s3][s4][c][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
												c13_indexlist.get(c13_num).add(xGSr[s1][s2][s3][s4][c][i][t + 1][1]);
												c13_valuelist.get(c13_num).add((double) -1);
											}
										}
																			
										//Add -sigma(t')(c')(i) xEAr(s1,s2,s3,s4,s5=c)[tR][aR=tR-t][s5R][i][t+1]
										for (int tR = t+1; tR <= total_Periods; tR++) {
											for (int s5R = 0; s5R < layer5.size(); s5R++) {
												for (int i = 0; i < total_EAr_Prescriptions; i++) {
													int rotationAge = tR - t;	
													String thisCoverTypeconversion_and_RotationAge = layer5.get(c) + " " + layer5.get(s5R) + " " + rotationAge;						
													if (covertype_conversions_and_regeneration_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
														if(xEAr[s1][s2][s3][s4][c][tR][tR - t][s5R][i][t + 1] > 0) {		// if variable is defined, this value would be > 0 
															c13_indexlist.get(c13_num).add(xEAr[s1][s2][s3][s4][c][tR][tR - t][s5R][i][t + 1]);
															c13_valuelist.get(c13_num).add((double) -1);
														}
													}
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (13):   " + c13_num + "             " + dateFormat.format(new Date()));

			
			// Constraints 14-------------------------------------------------
			List<List<Integer>> c14_indexlist = new ArrayList<List<Integer>>();	
			List<List<Double>> c14_valuelist = new ArrayList<List<Double>>();
			List<Double> c14_lblist = new ArrayList<Double>();	
			List<Double> c14_ublist = new ArrayList<Double>();
			int c14_num = 0;
			
			//14a
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));	
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int t = 2; t <= total_Periods - 1; t++) {
						for (int a = 1; a <= t-1; a++) {
							if(xNGr[s1][s2][s3][s4][s5][t][a] > 0) {		// if variable is defined, this value would be > 0 
								//Add constraint
								c14_indexlist.add(new ArrayList<Integer>());
								c14_valuelist.add(new ArrayList<Double>());
								
								//Add xNGr(s1,s2,s3,s4,s5)[t][a]
								SRDage = a; 
								if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
								
								c14_indexlist.get(c14_num).add(xNGr[s1][s2][s3][s4][s5][t][a]);
								c14_valuelist.get(c14_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100
								
								//Add - xNGr(s1,s2,s3,s4,s5)[t+1][a+1]
								c14_indexlist.get(c14_num).add(xNGr[s1][s2][s3][s4][s5][t + 1][a + 1]);
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
			
			//14b
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));			
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int i = 0; i < total_PBr_Prescriptions; i++) {
						for (int t = 2; t <= total_Periods - 1; t++) {
							for (int a = 1; a <= t-1; a++) {
								if(xPBr[s1][s2][s3][s4][s5][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
									//Add constraint
									c14_indexlist.add(new ArrayList<Integer>());
									c14_valuelist.add(new ArrayList<Double>());
									
									//Add xPBr(s1,s2,s3,s4,s5)[i][t][a]
									SRDage = a; 
									if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
									
									c14_indexlist.get(c14_num).add(xPBr[s1][s2][s3][s4][s5][i][t][a]);
									c14_valuelist.get(c14_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100
									
									//Add - xPBr(s1,s2,s3,s4,s5)[i][t+1][a+1]
									c14_indexlist.get(c14_num).add(xPBr[s1][s2][s3][s4][s5][i][t + 1][a + 1]);
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
			
			//14c
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));		
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int i = 0; i < total_GSr_Prescriptions; i++) {
						for (int t = 2; t <= total_Periods - 1; t++) {
							for (int a = 1; a <= t-1; a++) {
								if(xGSr[s1][s2][s3][s4][s5][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
									//Add constraint
									c14_indexlist.add(new ArrayList<Integer>());
									c14_valuelist.add(new ArrayList<Double>());
									
									//Add xGSr(s1,s2,s3,s4,s5)[i][t][a]
									SRDage = a; 
									if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
									
									c14_indexlist.get(c14_num).add(xGSr[s1][s2][s3][s4][s5][i][t][a]);
									c14_valuelist.get(c14_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100
									
									//Add - xGSr(s1,s2,s3,s4,s5)[i][t+1][a+1]
									c14_indexlist.get(c14_num).add(xGSr[s1][s2][s3][s4][s5][i][t + 1][a + 1]);
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
			
			//14d
			for (String strata: model_strata_without_sizeclass_and_covertype) {
				int s1 = layer1.indexOf(strata.substring(0,1));
				int s2 = layer2.indexOf(strata.substring(1,2));
				int s3 = layer3.indexOf(strata.substring(2,3));
				int s4 = layer4.indexOf(strata.substring(3,4));				
				for (int s5 = 0; s5 < layer5.size(); s5++) {
					for (int tR = 2; tR <= total_Periods; tR++) {
						for (int aR = 1; aR <= tR-1; aR++) {									
							for (int s5R = 0; s5R < layer5.size(); s5R++) {
								for (int i = 0; i < total_EAr_Prescriptions; i++) {
									String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + aR;						
									if (covertype_conversions_and_regeneration_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
										for (int t = tR - aR + 1; t <= tR - 1; t++) {
											if(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
												//Add constraint
												c14_indexlist.add(new ArrayList<Integer>());
												c14_valuelist.add(new ArrayList<Double>());
												
												//Add xEAr(s1,s2,s3,s4,s5)[tR][aR][s5R][i][t]
												SRDage = aR - tR + t; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %									
												
												c14_indexlist.get(c14_num).add(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]);
												c14_valuelist.get(c14_num).add((double) 1 - SRD_percent[s5][SRDage] / 100);		//SR Fire loss Rate = P(s5,a)/100													
										
												//Add - xEAr(s1,s2,s3,s4,s5,s6)[tR][aR][s5R][i][t+1]		
												c14_indexlist.get(c14_num).add(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t + 1]);
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (14):   " + c14_num + "             " + dateFormat.format(new Date()));
			
			
			// Constraints 15------------------------------------------------- for y(j) and z(k) and v(n)
			List<List<Integer>> c15_indexlist = new ArrayList<List<Integer>>();
			List<List<Double>> c15_valuelist = new ArrayList<List<Double>>();
			List<Double> c15_lblist = new ArrayList<Double>();
			List<Double> c15_ublist = new ArrayList<Double>();
			int c15_num = 0;

			int current_freeConstraint = 0;
			int current_softConstraint = 0;
			int current_hardConstraint = 0;	
			double currentDiscountValue;
			double para_value;
			double multiplier;
				
			// Add -y(j) + user_defined_variables = 0		or 			-z(k) + user_defined_variables = 0		or 			-v(n) + user_defined_variables = 0
			for (int id = 1; id < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; id++) {	//Loop from 1 because the first row of the userConstraint file is just title
				
				// Get the parameter indexes list
				List<String> parameters_indexes_list = read.get_parameters_indexes_list(id);
				// Get the dynamic identifiers indexes list
				List<String> dynamic_dentifiers_column_indexes = read.get_dynamic_dentifiers_column_indexes_in_row(id);
				List<List<String>> dynamic_identifiers = read.get_dynamic_identifiers_in_row(id);
				
				
						
				// Add constraint
				c15_indexlist.add(new ArrayList<Integer>());
				c15_valuelist.add(new ArrayList<Double>());

				// Add -y(j) or -z(k) or -v(n)
				int constraint_type_col = constraint_column_names_list.indexOf("bc_type");				
				
				if (bc_values[id][constraint_type_col].equals("SOFT")) {
					c15_indexlist.get(c15_num).add(y[current_softConstraint]);
					c15_valuelist.get(c15_num).add((double) -1);
					current_softConstraint++;
				}
				
				if (bc_values[id][constraint_type_col].equals("HARD")) {
					c15_indexlist.get(c15_num).add(z[current_hardConstraint]);
					c15_valuelist.get(c15_num).add((double) -1);
					current_hardConstraint++;
				}
				
				if (bc_values[id][constraint_type_col].equals("FREE")) {
					c15_indexlist.get(c15_num).add(v[current_freeConstraint]);
					c15_valuelist.get(c15_num).add((double) -1);
					current_freeConstraint++;
				}
									
				
				// Add user_defined_variables and parameters------------------------------------
				List<String> static_methods = read.get_static_methods(id);
				List<String> static_periods = read.get_static_periods(id);
				List<Integer> integer_static_periods = static_periods.stream().map(Integer::parseInt).collect(Collectors.toList());
				
				List<String> static_strata = read.get_static_strata(id);
				List<String> static_strata_without_sizeclass = read.get_static_strata_without_sizeclass(id);
				List<String> static_strata_without_sizeclass_and_covertype = read.get_static_strata_without_sizeclass_and_covertype(id);
				
				int multiplier_col = constraint_column_names_list.indexOf("bc_multiplier");
				multiplier = (!bc_values[id][multiplier_col].equals("null")) ?  Double.parseDouble(bc_values[id][multiplier_col]) : 0;	//if multiplier = null --> 0
				
							
				// Very important to keep the same elements of 1: model_strata vs static_strata
				// Very important to keep the same elements of 2: model_strata_without_sizeclass_and_covertype vs static_strata_without_sizeclass_and_covertype
				// These 4 lines help remove the IF THEN (to check static_identifiers) in all variables in equation 15 below.
				List<String> common_strata = new ArrayList<String>(model_strata);
				List<String> common_trata_without_sizeclass_and_covertype = new ArrayList<String>(model_strata_without_sizeclass_and_covertype);
				common_strata.retainAll(static_strata);
				common_trata_without_sizeclass_and_covertype.retainAll(static_strata_without_sizeclass_and_covertype);
				
				
				
				//Add xNGe
				if (static_methods.contains("NG_E")) {	
					for (String strata: common_strata) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						int s5 = layer5.indexOf(strata.substring(4,5));
						int s6 = layer6.indexOf(strata.substring(5,6));	
						if (integer_static_periods.size() > 0) {	
							for (int t : integer_static_periods) {		//Loop all periods
								if (t <= total_Periods) {
									//Find all parameter match the t and add them all to parameter
									/*	Table Name = s5 + s6 convert then + method + timingChoice
									 * Table column indexes for the parameters is identified by parameters_indexes_list
									 * dynamic identifiers are identified by "all_dynamicIdentifiers_columnIndexes" & "all_dynamicIdentifiers"
									 * Table row index = t - 1  
									 */		
									currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
											1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
									List<String> current_var_static_condition = new ArrayList<String>();
									current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
									current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
									current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
									current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
									current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
									
									int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
									
									
									
									List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
									List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
									SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
									if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
									for (int c = 0; c < layer5.size(); c++) {
										if (rdPercent[s5][c] / 100 > 0) {
											coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(c) + " " + "disturbance");
											coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][c] / 100);
										}														
									}
									
																							
																	
									para_value = Get_Parameter_Information.get_total_value(
											read_database, vname[xNGe[s1][s2][s3][s4][s5][s6][t]], rotation_age,
											yield_tables_names, yield_tables_values, parameters_indexes_list,
											dynamic_dentifiers_column_indexes, dynamic_identifiers,
											cost_condition_list,
											coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
									para_value = para_value * currentDiscountValue * multiplier;

									//Add xNGe(s1,s2,s3,s4,s5,s6)(t)
									if(xNGe[s1][s2][s3][s4][s5][s6][t] > 0) {		// if variable is defined, this value would be > 0 																
										c15_indexlist.get(c15_num).add(xNGe[s1][s2][s3][s4][s5][s6][t]);
										c15_valuelist.get(c15_num).add((double) para_value);
									}
								}
							}
						}
					}
				}	

						
				//Add xPBe[s1][s2][s3][s4][s5][s6][i][t]			
				if (static_methods.contains("PB_E")) {		
					for (String strata: common_strata) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						int s5 = layer5.indexOf(strata.substring(4,5));
						int s6 = layer6.indexOf(strata.substring(5,6));	
						for (int i = 0; i < total_PBe_Prescriptions; i++) {
							if (integer_static_periods.size() > 0) {	
								for (int t : integer_static_periods) {		//Loop all periods
									if (t <= total_Periods) {
										//Find all parameter match the t and add them all to parameter
										currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
												1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
										List<String> current_var_static_condition = new ArrayList<String>();
										current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
										current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
										current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
										current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
										current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
										
										int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
										
										
										
										List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
										List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
										SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
										if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
										for (int c = 0; c < layer5.size(); c++) {
											if (rdPercent[s5][c] / 100 > 0) {
												coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(c) + " " + "disturbance");
												coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][c] / 100);
											}														
										}
										
										
														
										para_value = Get_Parameter_Information.get_total_value(
												read_database, vname[xPBe[s1][s2][s3][s4][s5][s6][i][t]], rotation_age,
												yield_tables_names, yield_tables_values, parameters_indexes_list,
												dynamic_dentifiers_column_indexes, dynamic_identifiers,
												cost_condition_list,
												coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
										para_value = para_value * currentDiscountValue * multiplier;
										
										//Add xPBe[s1][s2][s3][s4][s5][s6][i][t]
										if(xPBe[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 																
											c15_indexlist.get(c15_num).add(xPBe[s1][s2][s3][s4][s5][s6][i][t]);
											c15_valuelist.get(c15_num).add((double) para_value);
										}
									}
								}
							}
						}
					}
				}	
							
				
				//Add xGSe[s1][s2][s3][s4][s5][s6][i][t]			
				if (static_methods.contains("GS_E")) {		
					for (String strata: common_strata) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						int s5 = layer5.indexOf(strata.substring(4,5));
						int s6 = layer6.indexOf(strata.substring(5,6));	
						for (int i = 0; i < total_GSe_Prescriptions; i++) {
							if (integer_static_periods.size() > 0) {	
								for (int t : integer_static_periods) {		//Loop all periods
									if (t <= total_Periods) {
										//Find all parameter match the t and add them all to parameter
										currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
												1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
										List<String> current_var_static_condition = new ArrayList<String>();
										current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
										current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
										current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
										current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
										current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
												
										int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
										
										
										
										List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
										List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
										SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
										if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
										for (int c = 0; c < layer5.size(); c++) {
											if (rdPercent[s5][c] / 100 > 0) {
												coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(c) + " " + "disturbance");
												coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][c] / 100);
											}														
										}
										
										
										
										para_value = Get_Parameter_Information.get_total_value(
												read_database, vname[xGSe[s1][s2][s3][s4][s5][s6][i][t]], rotation_age,
												yield_tables_names, yield_tables_values, parameters_indexes_list,
												dynamic_dentifiers_column_indexes, dynamic_identifiers,
												cost_condition_list,
												coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
										para_value = para_value * currentDiscountValue * multiplier;
										
										//Add xGSe[s1][s2][s3][s4][s5][s6][i][t]
										if(xGSe[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 																	
											c15_indexlist.get(c15_num).add(xGSe[s1][s2][s3][s4][s5][s6][i][t]);
											c15_valuelist.get(c15_num).add((double) para_value);
										}
									}
								}
							}
						}
					}
				}														
	
						
				//Add xMS[s1][s2][s3][s4][s5][s6][i][t]			
				if (static_methods.contains("MS_E")) {		
					for (String strata: common_strata) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						int s5 = layer5.indexOf(strata.substring(4,5));
						int s6 = layer6.indexOf(strata.substring(5,6));	
						for (int i = 0; i < total_MS_Prescriptions; i++) {
							if (integer_static_periods.size() > 0) {	
								for (int t : integer_static_periods) {		//Loop all periods
									if (t <= total_Periods) {
										//Find all parameter match the t and add them all to parameter
										currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
												1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
										List<String> current_var_static_condition = new ArrayList<String>();
										current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
										current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
										current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
										current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
										current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
										
										int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
										
										
										
										
										List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
										List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
										
										
										
										
										para_value = Get_Parameter_Information.get_total_value(
												read_database, vname[xMS[s1][s2][s3][s4][s5][s6][i][t]], rotation_age,
												yield_tables_names, yield_tables_values, parameters_indexes_list,
												dynamic_dentifiers_column_indexes, dynamic_identifiers,
												cost_condition_list,
												coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
										para_value = para_value * currentDiscountValue * multiplier;
										
										//Add xMS[s1][s2][s3][s4][s5][s6][i][t]
										if(xMS[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 															
											c15_indexlist.get(c15_num).add(xMS[s1][s2][s3][s4][s5][s6][i][t]);
											c15_valuelist.get(c15_num).add((double) para_value);
										}
									}
								}
							}
						}
					}
				}																
				
				
				//Add xBS[s1][s2][s3][s4][s5][s6][i][t]			
				if (static_methods.contains("BS_E")) {				
					for (String strata: common_strata) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						int s5 = layer5.indexOf(strata.substring(4,5));
						int s6 = layer6.indexOf(strata.substring(5,6));	
						for (int i = 0; i < total_BS_Prescriptions; i++) {
							if (integer_static_periods.size() > 0) {	
								for (int t : integer_static_periods) {		//Loop all periods
									if (t <= total_Periods) {
										//Find all parameter match the t and add them all to parameter
										currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
												1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
										List<String> current_var_static_condition = new ArrayList<String>();
										current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
										current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
										current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
										current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
										current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
										
										int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
										
										
										
										List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
										List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
										
										
										
										para_value = Get_Parameter_Information.get_total_value(
												read_database, vname[xBS[s1][s2][s3][s4][s5][s6][i][t]], rotation_age,
												yield_tables_names, yield_tables_values, parameters_indexes_list,
												dynamic_dentifiers_column_indexes, dynamic_identifiers,
												cost_condition_list,
												coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
										para_value = para_value * currentDiscountValue * multiplier;
										
										//Add xBS[s1][s2][s3][s4][s5][s6][i][t]
										if(xBS[s1][s2][s3][s4][s5][s6][i][t] > 0) {		// if variable is defined, this value would be > 0 															
											c15_indexlist.get(c15_num).add(xBS[s1][s2][s3][s4][s5][s6][i][t]);
											c15_valuelist.get(c15_num).add((double) para_value);	
										}
									}
								}
							}
						}
					}
				}							
								
				
				// Add xEAe[s1][s2][s3][s4][s5][s6](tR)(s5R)(i)(t)
				if (static_methods.contains("EA_E")) {									
					for (String strata: common_strata) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						int s5 = layer5.indexOf(strata.substring(4,5));
						int s6 = layer6.indexOf(strata.substring(5,6));	
						for (int tR = 1; tR <= total_Periods; tR++) {		//Loop from period tR to T
							for (int s5R = 0; s5R < layer5.size(); s5R++) {	
								int rotation_age = tR + StartingAge[s1][s2][s3][s4][s5][s6] - 1;
								String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + rotation_age;	
								if (covertype_conversions_and_existing_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {			// Check this one carefully
									if (integer_static_periods.size() > 0) {	
										for (int i = 0; i < total_EAe_Prescriptions; i++) {
											for (int t : integer_static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
												if (t <= tR) {
													//Find all parameter match the t and add them all to parameter
													currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
															1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
													List<String> current_var_static_condition = new ArrayList<String>();
													current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
													current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
													current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
													current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
													current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
														
													
													
													
													
													List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
													List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
													SRDage = StartingAge[s1][s2][s3][s4][s5][s6] + t - 1; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
													for (int s5RR = 0; s5RR < layer5.size(); s5RR++) {
														if (rdPercent[s5][s5RR] / 100 > 0) {
															coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5RR) + " " + "disturbance");
															coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5RR] / 100);
														}														
													}
													
													
													
												    para_value = Get_Parameter_Information.get_total_value(
												    		read_database, vname[xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t]], rotation_age,
															yield_tables_names, yield_tables_values, parameters_indexes_list,
															dynamic_dentifiers_column_indexes, dynamic_identifiers,
															cost_condition_list,
															coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
													para_value = para_value * currentDiscountValue * multiplier;
													
													//Add xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R)(i)(t)	final cut at tR but we need parameter at time t
													if(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 																				
														c15_indexlist.get(c15_num).add(xEAe[s1][s2][s3][s4][s5][s6][tR][s5R][i][t]);
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
				
				
				//Add xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]		  note   tR >= 2   -->   tR >= t >= tR - aR + 1
				if (static_methods.contains("EA_R")) {	
					for (String strata: common_trata_without_sizeclass_and_covertype) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));		
						for (int s5 = 0; s5 < layer5.size(); s5++) {
							for (int tR = 2; tR <= total_Periods; tR++) {
								for (int aR = 1; aR <= tR - 1; aR++) {
									for (int s5R = 0; s5R < layer5.size(); s5R++) {
										String thisCoverTypeconversion_and_RotationAge = layer5.get(s5) + " " + layer5.get(s5R) + " " + aR;						
										if (covertype_conversions_and_regeneration_rotation_ages.contains(thisCoverTypeconversion_and_RotationAge)) {
											if (integer_static_periods.size() > 0) {	
												for (int i = 0; i < total_EAr_Prescriptions; i++) {
													for (int t : integer_static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
														if (t >= tR - aR + 1 && t <= tR) {
															//Find all parameter match the t and add them all to parameter
															currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
																	1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
															List<String> current_var_static_condition = new ArrayList<String>();
															current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
															current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
															current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
															current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
															current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
															
															int rotation_age = aR;
															
															
															
															
															List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
															List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
															SRDage = aR - tR + t; 
															if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
															for (int s5RR = 0; s5RR < layer5.size(); s5RR++) {
																if (rdPercent[s5][s5RR] / 100 > 0) {
																	coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5RR) + " " + "disturbance");
																	coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5RR] / 100);
																}														
															}
															
															
															
															
														    para_value = Get_Parameter_Information.get_total_value(
														    		read_database, vname[xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]], rotation_age,
																	yield_tables_names, yield_tables_values, parameters_indexes_list,
																	dynamic_dentifiers_column_indexes, dynamic_identifiers,
																	cost_condition_list,
																	coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
															para_value = para_value * currentDiscountValue * multiplier;
															
															//Add xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]		final cut at tR but we need parameter at time t
															if(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
																c15_indexlist.get(c15_num).add(xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]);
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
				
	
				//Add xNGr
				if (static_methods.contains("NG_R")) {		
					for (String strata: common_trata_without_sizeclass_and_covertype) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						for (int s5 = 0; s5 < layer5.size(); s5++) {
							for (int tt = 2; tt <= total_Periods; tt++) {
								for (int a = 1; a <= tt - 1; a++) {
									if (integer_static_periods.size() > 0) {	
										
										//Combine all parameter together
										double parameter = 0;
										for (int t : integer_static_periods) {		//Loop all periods, t and tt are the same
											if (t == tt) {
												//Find all parameter match the t and add them all to parameter
												currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
														1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
												List<String> current_var_static_condition = new ArrayList<String>();
												current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
												current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
												current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
												current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
												current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
												
												int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
												
												
												
												List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
												List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
												SRDage = a; 
												if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
												for (int s5R = 0; s5R < layer5.size(); s5R++) {
													if (rdPercent[s5][s5R] / 100 > 0) {
														coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
														coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5R] / 100);
													}														
												}
												
												
												
											    para_value = Get_Parameter_Information.get_total_value(
											    		read_database, vname[xNGr[s1][s2][s3][s4][s5][tt][a]], rotation_age,
														yield_tables_names, yield_tables_values, parameters_indexes_list,
														dynamic_dentifiers_column_indexes, dynamic_identifiers,
														cost_condition_list,
														coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
												para_value = para_value * currentDiscountValue * multiplier;
												parameter = parameter + para_value;			
											}
										}
										
										//Add xNGr(s1,s2,s3,s4,s5)(tt)(a)
										if(xNGr[s1][s2][s3][s4][s5][tt][a] > 0) {		// if variable is defined, this value would be > 0 														
											c15_indexlist.get(c15_num).add(xNGr[s1][s2][s3][s4][s5][tt][a]);
											c15_valuelist.get(c15_num).add((double) parameter);	
										}
									}	
								}
							}
						}
					}
				}							
				

				//Add xPBr
				if (static_methods.contains("PB_R")) {		
					for (String strata: common_trata_without_sizeclass_and_covertype) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						for (int s5 = 0; s5 < layer5.size(); s5++) {
							for (int ii = 0; ii < total_PBr_Prescriptions; ii++) {
								for (int tt = 2; tt <= total_Periods; tt++) {
									for (int a = 1; a <= tt - 1; a++) {
										if (integer_static_periods.size() > 0) {	
											
											//Combine all parameter together
											double parameter = 0;
											for (int t : integer_static_periods) {		//Loop all periods, t and tt are the same
												if (t == tt) {
													//Find all parameter match the t and add them all to parameter
													currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
															1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
													List<String> current_var_static_condition = new ArrayList<String>();
													current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
													current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
													current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
													current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
													current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
													
													int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
													
													
													
													
													List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
													List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
													SRDage = a; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
													for (int s5R = 0; s5R < layer5.size(); s5R++) {
														if (rdPercent[s5][s5R] / 100 > 0) {
															coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
															coversion_cost_after_disturbance_value_list.add(SRD_percent[s5][SRDage] / 100 * rdPercent[s5][s5R] / 100);
														}														
													}
													
													
													
													
												    para_value = Get_Parameter_Information.get_total_value(
												    		read_database, vname[xPBr[s1][s2][s3][s4][s5][ii][tt][a]], rotation_age,
															yield_tables_names, yield_tables_values, parameters_indexes_list,
															dynamic_dentifiers_column_indexes, dynamic_identifiers,
															cost_condition_list,
															coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
													para_value = para_value * currentDiscountValue * multiplier;
													parameter = parameter + para_value;			
												}
											}
											
											//Add xPBr(s1,s2,s3,s4,s5)(ii)(tt)(a)
											if(xPBr[s1][s2][s3][s4][s5][ii][tt][a] > 0) {		// if variable is defined, this value would be > 0 
												c15_indexlist.get(c15_num).add(xPBr[s1][s2][s3][s4][s5][ii][tt][a]);
												c15_valuelist.get(c15_num).add((double) parameter);	
											}
										}	
									}
								}
							}
						}
					}
				}						

				
				//Add xGSr
				if (static_methods.contains("GS_R")) {		
					for (String strata: common_trata_without_sizeclass_and_covertype) {
						int s1 = layer1.indexOf(strata.substring(0,1));
						int s2 = layer2.indexOf(strata.substring(1,2));
						int s3 = layer3.indexOf(strata.substring(2,3));
						int s4 = layer4.indexOf(strata.substring(3,4));
						for (int s5 = 0; s5 < layer5.size(); s5++) {
							for (int ii = 0; ii < total_GSr_Prescriptions; ii++) {
								for (int tt = 2; tt <= total_Periods; tt++) {
									for (int a = 1; a <= tt - 1; a++) {
										if (integer_static_periods.size() > 0) {	
											
											//Combine all parameter together
											double parameter = 0;
											for (int t : integer_static_periods) {		//Loop all periods, t and tt are the same
												if (t == tt) {
													//Find all parameter match the t and add them all to parameter
													currentDiscountValue = (parameters_indexes_list.contains("CostParameter")) ? 	
															1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1)) : 1;	//total discount = 1 if not cost constraint																	
													List<String> current_var_static_condition = new ArrayList<String>();
													current_var_static_condition.add(layers_Title.get(0) + layer1.get(s1));		// layer + element
													current_var_static_condition.add(layers_Title.get(1) + layer2.get(s2));		// layer + element
													current_var_static_condition.add(layers_Title.get(2) + layer3.get(s3));		// layer + element
													current_var_static_condition.add(layers_Title.get(3) + layer4.get(s4));		// layer + element
													current_var_static_condition.add(layers_Title.get(4) + layer5.get(s5));		// layer + element
													
													int rotation_age = -9999;	// not need to use this, so put a -9999 here to note
													
													
													
													
													List<String> coversion_cost_after_disturbance_name_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
													List<Double> coversion_cost_after_disturbance_value_list = new ArrayList<Double>();	// i.e. 0.25				0.75
													SRDage = a; 
													if (SRDage >= SRD_percent[s5].length) 	SRDage = SRD_percent[s5].length - 1;		//Lump the age class if more than the max age has %
													for (int s5R = 0; s5R < layer5.size(); s5R++) {
														if (rdPercent[s5][s5R] / 100 > 0) {
															coversion_cost_after_disturbance_name_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
															coversion_cost_after_disturbance_value_list.add(rdPercent[s5][s5R] / 100);
														}														
													}
													
													
													
													
												    para_value = Get_Parameter_Information.get_total_value(
												    		read_database, vname[xGSr[s1][s2][s3][s4][s5][ii][tt][a]], rotation_age,
															yield_tables_names, yield_tables_values, parameters_indexes_list,
															dynamic_dentifiers_column_indexes, dynamic_identifiers,
															cost_condition_list,
															coversion_cost_after_disturbance_name_list, coversion_cost_after_disturbance_value_list);
													para_value = para_value * currentDiscountValue * multiplier;
													parameter = parameter + para_value;			
												}
											}
											
											//Add xGSr(s1,s2,s3,s4,s5)(ii)(tt)(a)
											if(xGSr[s1][s2][s3][s4][s5][ii][tt][a] > 0) {		// if variable is defined, this value would be > 0 
												c15_indexlist.get(c15_num).add(xGSr[s1][s2][s3][s4][s5][ii][tt][a]);
												c15_valuelist.get(c15_num).add((double) parameter);	
											}
										}	
									}
								}
							}
						}
					}
				}					
		
				
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
			System.out.println("Total constraints as in PRISM model formualtion eq. (15):   " + c15_num + "             " + dateFormat.format(new Date()));
			System.out.println();
			
			
			
			
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
			
			
			
			
			
			
			
			if (read.get_solver().equals("CPLEX")) {
				
				//Add the Cplex native library path dynamically at run time
				try {
//					LibraryHandle.addLibraryPath("C:/Program Files/IBM/ILOG/CPLEX_Studio126/cplex/bin/x64_win64");
					
					LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					System.out.println("Successfully loaded CPLEX .dll files from " + FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	
				} catch (Exception e) {
					System.err.println("Panel Solve Runs - cplexLib.addLibraryPath error - " + e.getClass().getName() + ": " + e.getMessage());
				}
				
				IloCplex cplex = new IloCplex();
				IloLPMatrix lp = cplex.addLPMatrix();
				IloNumVar[] var = cplex.numVarArray(cplex.columnArray(lp, nvars), vlb, vub, vtype, vname);
				
				
				// Add constraints
				lp.addRows(c2_lb, c2_ub, c2_index, c2_value); // Constraints 2
				lp.addRows(c3_lb, c3_ub, c3_index, c3_value); // Constraints 3
				lp.addRows(c5_lb, c5_ub, c5_index, c5_value); // Constraints 5 (flow)
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
				int solvingTimeLimit = read.get_solving_time() * 60; //Get time Limit in minute * 60 = seconds
				cplex.setParam(IloCplex.DoubleParam.TimeLimit, solvingTimeLimit); // Set Time limit
//				cplex.setParam(IloCplex.BooleanParam.PreInd, false);	// page 40: sets the Boolean parameter PreInd to false, instructing CPLEX not to apply presolve before solving the problem.
				
//				// turn off presolve to prevent it from completely solving the model before entering the actual LP optimizer (same as above ???)
//				cplex.setParam(IloCplex.Param.Preprocessing.Presolve, false);
		         
						
//				cplex.setParam(IloCplex.Param.Emphasis.Numerical, true);		// page 94: https://www.ibm.com/support/knowledgecenter/SSSA5P_12.6.3/ilog.odms.studio.help/pdf/paramcplex.pdf
//																				// true --> Exercise extreme caution in computation
//																				// false --> Do not emphasize numerical precision; default
				
				
//				numericcplex.setParam(IloCplex.Param.Read.Scale, 0);		// -1 no scaling	0 Equilibration scaling; default	1 More aggressive scaling (page 132)
				
				
//				cplex.setParam(IloCplex.DoubleParam.EpMrk, 0.99999);	// Markowitz tolerance
//																		// page 152, 154: https://www.ibm.com/support/knowledgecenter/SSSA5P_12.7.0/ilog.odms.studio.help/pdf/usrcplex.pdf
//																		// https://www.ibm.com/support/knowledgecenter/en/SS9UKU_12.5.0/com.ibm.cplex.zos.help/UsrMan/topics/cont_optim/simplex/20_num_difficulty.html
				
				// Add table info
				data[row][2] = cplex.getNcols();
				data[row][3] = cplex.getNrows();
				data[row][4] = "solving";
				model.fireTableDataChanged();
				
				
				if (read.get_export_problem()) cplex.exportModel(problem_file[row].getAbsolutePath());
				long time_start = System.currentTimeMillis();		//measure time before solving
				if (cplex.solve()) {
					// Add table info
					data[row][4] = "writing";
					model.fireTableDataChanged();
					
					
					long time_end = System.currentTimeMillis();		//measure time after solving
					if (read.get_export_solution()) cplex.writeSolution(solution_file[row].getAbsolutePath());

					//Get output info to array
					double[] value = cplex.getValues(lp);
					double[] reduceCost = cplex.getReducedCosts(lp);
					double[] dual = cplex.getDuals(lp);
					double[] slack = cplex.getSlacks(lp);

					//Write Solution files

					//General Info
					output_general_outputs_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_general_outputs_file[row]))) {
						// Write variables info
						fileOut.write("description" + "\t" + "value");

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
					output_general_outputs_file[row].createNewFile();

					
					// Variables if value <> 0
					output_variables_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_variables_file[row]))) {
						// Write variables info
						fileOut.write("var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost");
						for (int i = 0; i < value.length; i++) {
							if (value[i] != 0) {
								fileOut.newLine();
								fileOut.write(i + "\t" + vname[i] 
										+ "\t" + Double.valueOf(value[i]) /*Double.valueOf(twoDForm.format(value[i]))*/ 
										+ "\t" + Double.valueOf(reduceCost[i])) /*Double.valueOf(twoDForm.format(reduceCost[i])))*/;
							}
						}

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_variables_file[row]) error - "	+ e.getClass().getName() + ": " + e.getMessage());
					}
					output_variables_file[row].createNewFile();

					
					// Constraints if dual or slack <> 0
					output_constraints_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_constraints_file[row]))) {
						// Write constraints info
						fileOut.write("cons_id" + "\t" + "cons_slack" + "\t" + "cons_dual");
						for (int j = 0; j < dual.length; j++) {
							if (slack[j] != 0 || dual[j] != 0) {
								fileOut.newLine();
								fileOut.write(j 
										+ "\t" + Double.valueOf(slack[j]) /*Double.valueOf(twoDForm.format(slack[j]))*/ 
										+ "\t" + Double.valueOf(dual[j])) /*Double.valueOf(twoDForm.format(dual[j])))*/;
							}
						}

						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_constraints_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_constraints_file[row].createNewFile();

					
					// management_overview
					output_management_overview_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(
							new FileWriter(output_management_overview_file[row]))) {
						// Write info
						fileOut.write("strata_id" + "\t" + "layer1" + "\t" + "layer2" + "\t" + "layer3" + "\t" + "layer4" + "\t" + "layer5" + "\t" + "layer6" + "\t" 
						+ "NG_E_acres" + "\t" + "PB_E_acres" + "\t" + "GS_E_acres" + "\t"
						+ "EA_E_acres" + "\t" + "MS_E_acres"  + "\t" + "BS_E_acres");

						for (String strata: model_strata) {
							int s1 = layer1.indexOf(strata.substring(0,1));
							int s2 = layer2.indexOf(strata.substring(1,2));
							int s3 = layer3.indexOf(strata.substring(2,3));
							int s4 = layer4.indexOf(strata.substring(3,4));
							int s5 = layer5.indexOf(strata.substring(4,5));
							int s6 = layer6.indexOf(strata.substring(5,6));	

							//New line for each strata
							fileOut.newLine();
							//Write StrataID and 6 layers info
							fileOut.write(
									strata + "\t" + layer1.get(s1) + "\t" + layer2.get(s2)
											+ "\t" + layer3.get(s3) + "\t" + layer4.get(s4)
											+ "\t" + layer5.get(s5) + "\t" + layer6.get(s6));
							//Write acres from each method
							for (int q = 0; q < total_methods; q++) {
								int this_var_index = x[s1][s2][s3][s4][s5][s6][q];
								fileOut.write("\t" + Double.valueOf(value[this_var_index]) /*Double.valueOf(twoDForm.format(value[this_var_index]))*/);
							}
						}											
						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_management_overview_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_management_overview_file[row].createNewFile();
					
					
					// management_details
					output_management_details_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(
							new FileWriter(output_management_details_file[row]))) {
						// Write variables info
						fileOut.write("var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost"
								+ "\t" + "db_prescription" + "\t" + "db_row_id" + "\t" + "db_status");
						for (int i = 0; i < value.length; i++) {
							if (value[i] != 0 && (vname[i].contains("NG") || vname[i].contains("PB") || vname[i].contains("GS") 
									|| vname[i].contains("MS") || vname[i].contains("BS") || vname[i].contains("EA"))) {
								
								String yield_table_name_to_find = Get_Variable_Information.get_yield_table_name_to_find(vname[i]);
								if (yield_table_name_to_find.contains("rotation_age")) {	// replace the String "rotation_age" with the integer value of rotation_age
									if (vname[i].contains("xEA_E_")) {
										String var_name = vname[i].replace("xEA_E_", "");
										String[] term = var_name.toString().split(",");	
										int s1 = layer1.indexOf(term[0]);
										int s2 = layer2.indexOf(term[1]);
										int s3 = layer3.indexOf(term[2]);
										int s4 = layer4.indexOf(term[3]);
										int s5 = layer5.indexOf(term[4]);
										int s6 = layer6.indexOf(term[5]);									
										int rotation_period = Integer.parseInt(term[6]);
										int rotation_age = rotation_period + StartingAge[s1][s2][s3][s4][s5][s6] - 1;								
										yield_table_name_to_find = yield_table_name_to_find.replace("rotation_age", String.valueOf(rotation_age));	
									}					
								}
								int yield_table_row_index_to_find = Get_Variable_Information.get_yield_table_row_index_to_find(vname[i]);
								String db_status = "good";
								List<String> temp_list = new ArrayList<String>() {{ for (Object i : yield_tables_names) add(i.toString());}};		// Convert Object array to String list
								
								if (!temp_list.contains(yield_table_name_to_find)) {
									db_status = "missing yield table";
								} else {
									int table_id = temp_list.indexOf(yield_table_name_to_find);
									if (yield_tables_values[table_id].length <= yield_table_row_index_to_find) {
										db_status = "missing row id = " + yield_table_row_index_to_find;
									}
								}

								fileOut.newLine();
								fileOut.write(i + "\t" + vname[i] 
										+ "\t" + Double.valueOf(value[i] /*Double.valueOf(twoDForm.format(value[i])*/)
										+ "\t" + Double.valueOf(reduceCost[i]) /*Double.valueOf(twoDForm.format(reduceCost[i]))*/ 
										+ "\t" + yield_table_name_to_find + "\t" + yield_table_row_index_to_find + "\t" + db_status);
							}
						}
						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_management_details_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_management_details_file[row].createNewFile();
					
					
					// fly_constraints --> don't need to create this file, just delete the old file
					output_fly_constraints_file[row].delete();		
					
					
					// basic_constraints
					if (total_freeConstraints + total_softConstraints + total_hardConstraints > 0) {		// write basic constraints if there is at least a constraint set up
						output_basic_constraints_file[row].delete();
						try (BufferedWriter fileOut = new BufferedWriter(
								new FileWriter(output_basic_constraints_file[row]))) {
							// Write info
							fileOut.write("bc_id" + "\t" + "bc_description" + "\t" + "bc_type" + "\t" + "bc_multiplier" + "\t" + "lowerbound" + "\t" 
							+ "lowerbound_perunit_penalty" + "\t" + "upperbound" + "\t" + "upperbound_perunit_penalty" + "\t"
							+ "bookeeping_var_id" + "\t" + "bookeeping_var_name" + "\t" + "bookeeping_var_value" + "\t" + "bookeeping_var_reduced_cost" + "\t" + "total_penalty");
	
							current_freeConstraint = 0;
							current_softConstraint = 0;
							current_hardConstraint = 0;	
							int constraint_type_col = constraint_column_names_list.indexOf("bc_type");	
							int lowerbound_col = constraint_column_names_list.indexOf("lowerbound");
							int lowerbound_perunit_penalty_col = constraint_column_names_list.indexOf("lowerbound_perunit_penalty");
							int upperbound_col = constraint_column_names_list.indexOf("upperbound");
							int upperbound_perunit_penalty_col = constraint_column_names_list.indexOf("upperbound_perunit_penalty");
							
							for (int i = 1; i < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; i++) {	// Loop from 1 because the first row of the Basic Constraints file is just title												
								fileOut.newLine();
								for (int j = 0; j < 8; j++) { //just print the first 7 columns of basic constraints
									fileOut.write(bc_values[i][j] + "\t");
								}
								
								int var_id = 0;
								if (bc_values[i][constraint_type_col].equals("SOFT")) {
									var_id = y[current_softConstraint];
									current_softConstraint++;
								}
								
								if (bc_values[i][constraint_type_col].equals("HARD")) {
									var_id = z[current_hardConstraint];
									current_hardConstraint++;
								}
								
								if (bc_values[i][constraint_type_col].equals("FREE")) {
									var_id = v[current_freeConstraint];
									current_freeConstraint++;
								}		
								
								double total_penalty = 0;
								if (bc_values[i][constraint_type_col].equals("SOFT")) {
									double lowerbound = (!bc_values[i][lowerbound_col].equals("null")) ? Double.parseDouble(bc_values[i][lowerbound_col]) : 0;
									double lowerbound_perunit_penalty = (!bc_values[i][lowerbound_perunit_penalty_col].equals("null")) ? Double.parseDouble(bc_values[i][lowerbound_perunit_penalty_col]) : 0;
									double upperbound = (!bc_values[i][upperbound_col].equals("null")) ? Double.parseDouble(bc_values[i][upperbound_col]) : 0;
									double upperbound_perunit_penalty = (!bc_values[i][upperbound_perunit_penalty_col].equals("null")) ? Double.parseDouble(bc_values[i][upperbound_perunit_penalty_col]) : Double.MAX_VALUE;
									
									if (lowerbound_perunit_penalty != 0 && value[var_id] < lowerbound) {
										total_penalty = (lowerbound - value[var_id]) * lowerbound_perunit_penalty;
									}
									
									if (upperbound_perunit_penalty != 0 && value[var_id] > upperbound) {
										total_penalty = (value[var_id] - upperbound) * upperbound_perunit_penalty;
									}	
								}
	
								fileOut.write(var_id + "\t" + vname[var_id] + "\t" + value[var_id]  + "\t" + reduceCost[var_id] + "\t" + total_penalty);
							}
							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_basic_constraints_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_basic_constraints_file[row].createNewFile();					
					}							
					
					// flow_constraints 					
					if (flow_set_list.size() > 0) {		// write flow constraints if there is at least a flow set
						output_flow_constraints_file[row].delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_flow_constraints_file[row]))) {
							// Write info
							fileOut.write("flow_id" + "\t" + "flow_description" + "\t" + "flow_arrangement" + "\t"
									+ "flow_type" + "\t" + "lowerbound_percentage" + "\t" + "upperbound_percentage" + "\t" + "flow_output_original");
							// Add constraints for each flow set
							for (int i = 0; i < flow_set_list.size(); i++) {		// loop each flow set (or each row of the flow_constraints_table)								
								String temp = flow_id_list.get(i) + "\t" + flow_description_list.get(i) + "\t"
										+ flow_arrangement_list.get(i) + "\t" + flow_type_list.get(i) + "\t"
										+ flow_lowerbound_percentage_list.get(i) + "\t" + flow_upperbound_percentage_list.get(i) + "\t";
										
								// write flow_original
								for (int j = 0; j < flow_set_list.get(i).size(); j++) {		
									double aggragated_value = 0;
									for (int ID : flow_set_list.get(i).get(j)) {																			
										int gui_table_id = bookkeeping_ID_list.indexOf(ID);		
										int var_id = bookkeeping_Var_list.get(gui_table_id);
										aggragated_value = aggragated_value + value[var_id];
									}
									temp = temp + Double.valueOf(aggragated_value) /*Double.valueOf(twoDForm.format(aggragated_value))*/ + ";";	
								}	
								temp = temp.substring(0, temp.length() - 1) + "\t";		// remove the last ; and add a tab									
								temp = temp.substring(0, temp.length() - 1);		// remove the last ;								
								
								// write the whole line
								fileOut.newLine();
								fileOut.write(temp);
							}
							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output__flow_constraints_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_flow_constraints_file[row].createNewFile();
					}
					
					
					
					//Show successful or fail in the GUI
					data[row][1] = "valid";
					data[row][4] = "successful";
					model.fireTableDataChanged();
				}
				
				cplex.endModel();
				cplex.end();
			}
			
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if (read.get_solver().equals("LPSOLVE")) {				//Reference for all LPsolve classes here:		http://lpsolve.sourceforge.net/5.5/Java/docs/api/lpsolve/LpSolve.html
				
				//Add the LPsolve native library path dynamically at run time
				try {
//					LibraryHandle.addLibraryPath("C:/SpectrumLite_Documents/Setup/lp_solve_5.5_java/lib/win64");
					
					LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
					System.out.println("Successfully loaded LPSOLVE .dll files from " + FilesHandle.get_temporaryFolder().getAbsolutePath().toString());			
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
//					if (c2_lb[i] != -Double.MAX_VALUE) {
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
					if (c2_lb[i] != -Double.MAX_VALUE) {			
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
				model.fireTableDataChanged();
		        
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
					output_general_outputs_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_general_outputs_file[row]))) {
						// Write variables info
						fileOut.write("description" + "\t" + "value");

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
					output_general_outputs_file[row].createNewFile();

					
					//Variables if value <> 0
					output_variables_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_variables_file[row]))) {
						// Write variables info
						fileOut.write("var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost");
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
						fileOut.write("cons_id" + "\t" + "cons_slack" + "\t" + "cons_dual");
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
					output_management_overview_file[row].delete();
					try (BufferedWriter fileOut = new BufferedWriter(
							new FileWriter(output_management_overview_file[row]))) {
						// Write info
						fileOut.write("strata_id" + "\t" + "layer1" + "\t" + "layer2" + "\t" + "layer3" + "\t" + "layer4" + "\t" + "layer5" + "\t" + "layer6" + "\t" 
								+ "natural_growth_acres" + "\t" + "prescribed_burn_acres" + "\t" + "group_selection_acres" + "\t"
								+ "even_age_acres" + "\t" + "mixed_severity_wildfire_acres");

						for (String strata: model_strata) {
							int s1 = layer1.indexOf(strata.substring(0,1));
							int s2 = layer2.indexOf(strata.substring(1,2));
							int s3 = layer3.indexOf(strata.substring(2,3));
							int s4 = layer4.indexOf(strata.substring(3,4));
							int s5 = layer5.indexOf(strata.substring(4,5));
							int s6 = layer6.indexOf(strata.substring(5,6));	

							//New line for each strata
							fileOut.newLine();
							//Write StrataID and 6 layers info
							fileOut.write(
									strata + "\t" + layer1.get(s1) + "\t" + layer2.get(s2)
											+ "\t" + layer3.get(s3) + "\t" + layer4.get(s4)
											+ "\t" + layer5.get(s5) + "\t" + layer6.get(s6));
							//Write acres from each method
							for (int q = 0; q < total_methods; q++) {
								int this_var_index = x[s1][s2][s3][s4][s5][s6][q];
								fileOut.write("\t" + Double.valueOf(twoDForm.format(value[this_var_index])));
							}
						}										
						fileOut.close();
					} catch (IOException e) {
						System.err.println("Panel Solve Runs - FileWriter(output_managementOverview_file[row]) error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					output_management_overview_file[row].createNewFile();

					//Show successful or fail in the GUI
					data[row][1] = "valid";
					data[row][4] = "successful";
					model.fireTableDataChanged();
					
					
					
					
				}
				solver.deleteLp();
			}			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		catch (IloException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Concert exception for " + listOfEditRuns[row].getName());
			
			data[row][1] = "concert error";
			data[row][4] = "fail";
			model.fireTableDataChanged();
			
			output_variables_file[row].delete();
			output_constraints_file[row].delete();	
			output_general_outputs_file[row].delete();	
			output_management_overview_file[row].delete();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Create 4 output files exception for "+ listOfEditRuns[row].getName());			
		
			data[row][1] = "cannot create outputs";
			data[row][4] = "fail";
			model.fireTableDataChanged();
			
			output_variables_file[row].delete();
			output_constraints_file[row].delete();	
			output_general_outputs_file[row].delete();	
			output_management_overview_file[row].delete();
		} catch (LpSolveException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   LPSOLVE exception for " + listOfEditRuns[row].getName());
			
			data[row][1] = "lpsolve error";
			data[row][4] = "fail";
			model.fireTableDataChanged();
			
			output_variables_file[row].delete();
			output_constraints_file[row].delete();
			output_general_outputs_file[row].delete();
			output_management_overview_file[row].delete();
		}		
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Input files exception for " + listOfEditRuns[row].getName());

			data[row][1] = "invalid inputs";
			data[row][4] = "fail";
			model.fireTableDataChanged();
			
			output_variables_file[row].delete();
			output_constraints_file[row].delete();
			output_general_outputs_file[row].delete();
			output_management_overview_file[row].delete();
		} finally {
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
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
	
	
    /*
     * Checks whether the status corresponds to a valid solution
     * 
     * @param status    The status id returned by lpsolve
     * @return          Boolean which indicates if the solution is valid
     */
	private static boolean isSolutionValid(int status) {
		return (status == 0) || (status == 1) || (status == 11) || (status == 12);
	}
}