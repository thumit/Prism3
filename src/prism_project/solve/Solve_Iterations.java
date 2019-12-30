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
package prism_project.solve;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import prism_convenience.FilesHandle;
import prism_convenience.LibraryHandle;
import prism_convenience.PrismTableModel;
import prism_database.SQLite;
import prism_project.data_process.Information_Cost;
import prism_project.data_process.Information_Disturbance;
import prism_project.data_process.Information_Parameter;
import prism_project.data_process.Information_Variable;
import prism_project.data_process.Read_Database;
import prism_project.data_process.Read_Input;
import prism_root.PrismMain;

public class Solve_Iterations {
	private File problem_file, solution_file, output_general_outputs_file, output_variables_file,
			output_constraints_file, output_management_overview_file, output_management_details_file,
			output_fly_constraints_file, output_basic_constraints_file, output_flow_constraints_file, file_database;
	private DecimalFormat twoDForm = new DecimalFormat("#.##");	 // Only get 2 decimal
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
	private long time_start, time_end;
	private double time_reading, time_solving, time_writing;
	
	public Solve_Iterations(File runFolder, PrismTableModel model, Object[][] data, int row) {
		try {
			//--------------------------------------------------------------------------------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------  
		    //--------------------------------------------------DELETE SOME OUTPUTS-----------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------
			// Identify the last solved iteration
			File[] list_files = runFolder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("output_01_general_outputs_");
				}
			});
			
			int last_solved_iter = -1;	// the case of "restart"
			if (data[row][2].equals("continue")) last_solved_iter = list_files.length - 1;
			// Delete some outputs
			File[] contents = runFolder.listFiles();
			if (contents != null) {
				for (File f : contents) {
					// Delete all output files, problem file, and solution file, but keep the fly_constraints file
					if ((f.getName().startsWith("output") || f.getName().startsWith("problem") || f.getName().startsWith("solution")) && !f.getName().contains("fly_constraints")) {
						String iter_name = f.getName().substring(f.getName().lastIndexOf("_") + 1, f.getName().lastIndexOf("."));
						try {
							if (Integer.parseInt(iter_name) > last_solved_iter) f.delete();
						} catch (Exception e) {
							e.printStackTrace();
							f.delete();		// i.e. output_01_general_outputs.txt (1.xx.xx version output) would need to be deleted so users could run old model even without set up state_id input_11
						}
					}
				}
			}
			
			
			
			
			
			//--------------------------------------------------------------------------------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------  
		    //--------------------------------------------------READING ALL CORE INPUTS-------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------
			System.out.println(runFolder.getName() + "          " + dateFormat.format(new Date()) + "\n");
			time_start = System.currentTimeMillis();		// measure time before reading
			
			// Database must be read first
			file_database = new File(runFolder.getAbsolutePath() + "/database.db");
			Read_Database read_database = PrismMain.get_databases_linkedlist().return_read_database_if_exist(file_database);
			if (read_database == null) {
				read_database = new Read_Database(file_database);	// Read the database
				PrismMain.get_databases_linkedlist().update(file_database, read_database);			
			}
			
			// Database Info
			String[][][] yield_tables_values = read_database.get_yield_tables_values();
			String[] yield_tables_column_names = read_database.get_yield_tables_column_names();
			String[] yield_tables_names = read_database.get_yield_tables_names();			
			List<String> yield_tables_names_list = Arrays.asList(yield_tables_names); 
			
			// Get info: layers from database (strata_definition)
			List<List<String>> all_layers =  read_database.get_all_layers();		
			List<String> layer1 = all_layers.get(0);
			List<String> layer2 = all_layers.get(1);
			List<String> layer3 = all_layers.get(2);
			List<String> layer4 = all_layers.get(3);
			List<String> layer5 = all_layers.get(4);		int total_layer5 = layer5.size();
			List<String> layer6 = all_layers.get(5);		int total_layer6 = layer6.size();
				
			// Read input files to retrieve values later
			File input_01_file = new File(runFolder.getAbsolutePath() + "/input_01_general_inputs.txt");
			File input_02_file = new File(runFolder.getAbsolutePath() + "/input_02_model_strata.txt");
			File input_03_file = new File(runFolder.getAbsolutePath() + "/input_03_non_ea_management.txt");
			File input_04_file = new File(runFolder.getAbsolutePath() + "/input_04_ea_management.txt");
			File input_05_file = new File(runFolder.getAbsolutePath() + "/input_05_non_sr_disturbances.txt");
			File input_06_file = new File(runFolder.getAbsolutePath() + "/input_06_sr_disturbances.txt");
			File input_07_file = new File(runFolder.getAbsolutePath() + "/input_07_management_cost.txt");
			File input_08_file = new File(runFolder.getAbsolutePath() + "/input_08_basic_constraints.txt");
			File input_09_file = new File(runFolder.getAbsolutePath() + "/input_09_flow_constraints.txt");
			File input_11_file = new File(runFolder.getAbsolutePath() + "/input_11_state_id.txt");
			Read_Input read = new Read_Input();
			read.read_general_inputs(input_01_file);
			read.read_model_strata(input_02_file);
			read.read_non_ea_management(input_03_file);
			read.read_ea_management(input_04_file);
			read.read_non_sr_disturbaces(input_05_file);
			read.read_replacing_disturbances(input_06_file);
			read.read_management_cost(input_07_file);
			read.read_basic_constraints(input_08_file);
			read.read_flow_constraints(input_09_file);
			read.read_state_id(input_11_file);
			
			// Get info: input_01_general_inputs
			int total_periods = read.get_total_periods();
			int total_replacing_disturbances = read.get_total_replacing_disturbances();
			double annualDiscountRate = read.get_discount_rate() / 100;
			String solver_for_optimization = read.get_solver();
			int solvingTimeLimit = read.get_solving_time() * 60;	//convert to seconds
			boolean is_problem_exported = read.get_export_problem();
			boolean is_solution_exported = read.get_export_solution();
						
			// Get info: input_02_model_strata
			List<String> model_strata = read.get_model_strata();
			List<String> model_strata_without_sizeclass_and_covertype = read.get_model_strata_without_sizeclass_and_covertype(); 
			List<String> model_strata_without_sizeclass = new ArrayList<String>();
			for (String l1234: model_strata_without_sizeclass_and_covertype) {	// This is a special case, we need all covers not just the covers in model_strata, because any cover could be the regenerated cover
				for (String l5: layer5) {
					model_strata_without_sizeclass.add(l1234 + "_" + l5);
				}
			}
			int	total_model_strata = model_strata.size();		
			int	total_model_strata_without_sizeclass = model_strata_without_sizeclass.size();	
			
			// Get info: input_03_non_ea_management
			read.populate_nonea_lists(model_strata, model_strata_without_sizeclass, all_layers);
			List<List<String>> nonea_method_choice_for_strata = read.get_nonea_method_choice_for_strata();
			List<List<String>> nonea_method_choice_for_strata_without_sizeclass = read.get_nonea_method_choice_for_strata_without_sizeclass();
			boolean is_nonea_defined_with_some_rows = (input_03_file.exists()) ? true : false;
			
			// Get Info: input_04_ea_management
			read.populate_ea_lists(model_strata, model_strata_without_sizeclass, all_layers);
			List<List<String>> ea_conversion_and_rotation_for_strata = read.get_ea_conversion_and_rotation_for_strata();
			List<List<String>> ea_conversion_and_rotation_for_strata_without_sizeclass = read.get_ea_conversion_and_rotation_for_strata_without_sizeclass();
			boolean is_ea_defined_with_some_rows = (input_04_file.exists()) ? true : false;
			
			// Get info: input_05_non_sr_disturbances
			read.populate_non_sr_lists(model_strata, model_strata_without_sizeclass, all_layers);
			double[] percentage_MS_E = read.get_percentage_MS_E_for_strata();
			double[] percentage_BS_E = read.get_percentage_BS_E_for_strata();
			boolean is_nonsr_defined_with_some_rows = (input_05_file.exists()) ? true : false;
			
			// Get Info: input_06_sr_disturbances
			List<String> disturbance_condition_list = read.get_disturbance_condition_list(); 
			
			// Get info: input_07_management_cost
			List<String> cost_condition_list = read.get_cost_condition_list(); 
			
			// Get info: input_08_basic_constraints
			List<String> constraint_column_names_list = read.get_constraint_column_names_list();
			String[][] bc_values = read.get_bc_data();		
			int total_softConstraints = read.get_total_softConstraints();
			double[] softConstraints_LB = read.get_softConstraints_LB();
			double[] softConstraints_UB = read.get_softConstraints_UB();
			double[] softConstraints_LB_Weight = read.get_softConstraints_LB_Weight();
			double[] softConstraints_UB_Weight = read.get_softConstraints_UB_Weight();		
			int total_hardConstraints = read.get_total_hardConstraints();
			double[] hardConstraints_LB = read.get_hardConstraints_LB();
			double[] hardConstraints_UB = read.get_hardConstraints_UB();	
			int total_freeConstraints = read.get_total_freeConstraints();
			
			// Get info: input_09_flow_constraints	
			List<List<List<Integer>>> flow_set_list = read.get_flow_set_list();
			List<Integer> flow_id_list = read.get_flow_id_list();
			List<String> flow_description_list = read.get_flow_description_list();
			List<String> flow_arrangement_list = read.get_flow_arrangement_list();
			List<String> flow_type_list = read.get_flow_type_list();
			List<Double> flow_lowerbound_percentage_list = read.get_flow_lowerbound_percentage_list();
			List<Double> flow_upperbound_percentage_list = read.get_flow_upperbound_percentage_list();			
			System.out.println("Reading process finished for all core inputs          " + dateFormat.format(new Date()));
			System.out.println("Optimization models will be built based on Prism-Formulation-10");
			
			// Get info: input_11_state_id
			LinkedHashMap<String, String> map_prescription_and_row_id_to_state_id = read.get_map_prescription_and_row_id_to_state_id();
			String merging_option = (map_prescription_and_row_id_to_state_id == null) ? "no_merge" : "merge";
			System.out.println("Iterations are connected by '" + merging_option + "' option");
			
			
			
			
			//--------------------------------------------------------------------------------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------  
		    //---------------------------------------------------SOLVING ITERATIONS-----------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------
		    //--------------------------------------------------------------------------------------------------------------------------
			int max_iteration = Integer.parseInt(data[row][1].toString());
			for (int iter = last_solved_iter + 1; iter <= max_iteration; iter++) {	// Loop all iterations
				data[row][3] = "solving iteration " + iter;
				model.fireTableDataChanged();
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println();
				System.out.println("ITERATION " + iter);
				
				// Read the hard-coding input if iteration >= 1 (for hard-coding 1st period period variables)
				if (iter >= 1) {
					time_start = System.currentTimeMillis();		// measure time before reading
				}
				
				// Create all outputs				
				problem_file = new File(runFolder.getAbsolutePath() + "/problem_" + iter + ".lp");
				solution_file = new File(runFolder.getAbsolutePath() + "/solution_" + iter + ".sol");
				output_general_outputs_file = new File(runFolder.getAbsolutePath() + "/output_01_general_outputs_" + iter + ".txt");	
				output_variables_file = new File(runFolder.getAbsolutePath() + "/output_02_variables_" + iter + ".txt");
				output_constraints_file = new File(runFolder.getAbsolutePath() + "/output_03_constraints_" + iter + ".txt");	
				output_management_overview_file = new File(runFolder.getAbsolutePath() + "/output_04_management_overview_" + iter + ".txt");
				output_management_details_file = new File(runFolder.getAbsolutePath() + "/output_05_management_details_" + iter + ".txt");	
				output_fly_constraints_file = new File(runFolder.getAbsolutePath() + "/output_05_fly_constraints_" + iter + ".txt");	
				output_basic_constraints_file = new File(runFolder.getAbsolutePath() + "/output_06_basic_constraints_" + iter + ".txt");
				output_flow_constraints_file = new File(runFolder.getAbsolutePath() + "/output_07_flow_constraints_" + iter + ".txt");
				
				
				// Set up problem-------------------------------------------------		
				// Some more data process definitions
				Information_Disturbance disturbance_info = (disturbance_condition_list != null) ? new Information_Disturbance(read_database, disturbance_condition_list, all_layers) : null;
				Information_Cost cost_info = (cost_condition_list != null) ? new Information_Cost(read_database, cost_condition_list, all_layers) : null;
				Information_Parameter parameter_info = new Information_Parameter(read_database);
				List<Information_Variable> var_info_list = new ArrayList<Information_Variable>();
				
				
				int total_age_classes = total_periods - 1 + iter;		//loop from age 1 to total_age_classes (for regenerated strata, set total_age_classes = total_periods - 1 + iter for rolling horizon update
				int total_NG_E_prescription_choices = 15;	// choices 0-14
				int total_PB_E_prescription_choices = 15;	// choices 0-14
				int total_GS_E_prescription_choices = 15;	// choices 0-14
				int total_EA_E_prescription_choices = 6;	// choices 0-5
				int total_MS_E_prescription_choices = 15;	// choices 0-14
				int total_BS_E_prescription_choices = 15;	// choices 0-14
				int total_NG_R_prescription_choices = 15;	// choices 0-14
				int total_PB_R_prescription_choices = 15;	// choices 0-14
				int total_GS_R_prescription_choices = 15;	// choices 0-14
				int total_EA_R_prescription_choices = 6;	// choices 0-5
				boolean allow_Non_Existing_Prescription = false;
				

				List<Double> objlist = new ArrayList<Double>();				//objective coefficient
				List<String> vnamelist = new ArrayList<String>();			//variable name
				List<Double> vlblist = new ArrayList<Double>();				//lower bound
				List<Double> vublist = new ArrayList<Double>();				//upper bound
				int nvars = 0;
				
				
				// Declare arrays to keep variables	
				int[] y = new int [total_softConstraints];	//y(j)
				int[] l = new int [total_softConstraints];	//l(j)
				int[] u = new int [total_softConstraints];	//u(j)
				int[] z = new int [total_hardConstraints];	//z(k)
				int[] v = new int [total_freeConstraints];	//v(n)
//				int[][][] xNGe = new int[total_model_strata][total_NG_E_prescription_choices][total_periods + 1 + iter];		//xNGe(s1,s2,s3,s4,s5,s6)(i)(t)	
//				int[][][] xPBe = new int[total_model_strata][total_PB_E_prescription_choices][total_periods + 1 + iter];		//xPBe(s1,s2,s3,s4,s5,s6)(i)(t)
//				int[][][] xGSe = new int[total_model_strata][total_GS_E_prescription_choices][total_periods + 1 + iter];		//xGSe(s1,s2,s3,s4,s5,s6)(i)(t)
//				int[][][][][] xEAe = new int[total_model_strata][total_periods + 1][total_layer5][total_EA_E_prescription_choices][total_periods + 1 + iter];	//xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R)(i)(t)
										// total_Periods + 1 + iter because tR starts from 1 to total_Periods + iter, ignore the 0
//				int[][][] xMS = new int[total_model_strata][total_MS_E_prescription_choices][total_periods + 1 + iter];		//xMS(s1,s2,s3,s4,s5,s6)(i)(t)
//				int[][][] xBS = new int[total_model_strata][total_BS_E_prescription_choices][total_periods + 1 + iter];		//xBS(s1,s2,s3,s4,s5,s6)(i)(t)			
//				int[][][][] xNGr = new int[total_model_strata_without_sizeclass][total_NG_R_prescription_choices][total_periods + 1 + iter][total_AgeClasses + 1 + iter];		//xNGr(s1,s2,s3,s4,s5)(i)(t)(a)
//										// total_Periods + 1 + iter because tR starts from 1 to total_Periods + iter, ignore the 0				
//				int[][][][] xPBr = new int[total_model_strata_without_sizeclass][total_PB_R_prescription_choices][total_periods + 1][total_AgeClasses + 1];		//xPBr(s1,s2,s3,s4,s5)(i)(t)(a)
//										// total_Periods + 1 + iter because tR starts from 1 to total_Periods + iter, ignore the 0
//				int[][][][] xGSr = new int[total_model_strata_without_sizeclass][total_GS_R_prescription_choices][total_periods + 1][total_AgeClasses + 1];		//xGSr(s1,s2,s3,s4,s5)(i)(t)(a)
//										// total_Periods + 1 + iter because tR starts from 1 to total_Periods + iter, ignore the 0
//				int[][][][][][] xEAr = new int[total_model_strata_without_sizeclass][total_periods + 1][total_AgeClasses + 1][total_layer5][total_EA_R_prescription_choices][total_periods + 1];		//xEAr(s1,s2,s3,s4,s5)(tR)(a)(s5R)(i)(t)
//										// total_Periods + 1 + iter because tR starts from 1 to total_Periods + iter, ignore the 0		
				
				// The below variables are optimized by using jagged-arrays
				int[][][] xNGe = null;
				int[][][] xPBe = null;
				int[][][] xGSe = null;
				int[][][] xMS = null;
				int[][][] xBS = null;
				int[][][][] xNGr = null;
				int[][][][] xPBr = null;	
				int[][][][] xGSr = null;
				int[][][][][] xEAe = null;
				int[][][][][][] xEAr = null;
				
						
				// Declare arrays to keep replacing-disturbance variables f(s1,s2,s3,s4,s5,t,s5R)
				int[][][] fire = new int[total_model_strata_without_sizeclass][total_periods + 1 + iter][total_layer5];						
							
				
				// Get the 2 parameter V(s1,s2,s3,s4,s5,s6) and A(s1,s2,s3,s4,s5,s6)
				String[][] model_strata_data = read.get_ms_data();	
				double[] strata_area = new double[total_model_strata];
				int[] strata_starting_age = new int[total_model_strata];			
					for (int id = 0; id < total_model_strata; id++) {
					strata_area[id] = Double.parseDouble(model_strata_data[id][7]);		// area (acres)
					strata_starting_age[id] = Integer.parseInt(model_strata_data[id][read.get_ms_total_columns() - 2]);	// age_class		
				}						

				
				
				
				// CREATE OBJECTIVE FUNCTION-------------------------------------------------
				// CREATE OBJECTIVE FUNCTION-------------------------------------------------
				// CREATE OBJECTIVE FUNCTION-------------------------------------------------
						
				// Create soft constraint decision variables y(j)			
				for (int j = 0; j < total_softConstraints; j++) {
					String var_name = "y_" + j;
					var_info_list.add(new Information_Variable(iter, var_name, -9999, yield_tables_names_list));

					objlist.add((double) 0);
					vnamelist.add(var_name);
					vlblist.add((double) 0);
					vublist.add(Double.MAX_VALUE);
					y[j] = nvars;
					nvars++;				
				}
				
				// Create soft constraint lower bound variables l(j)			
				for (int j = 0; j < total_softConstraints; j++) {
					String var_name = "l_" + j;
					var_info_list.add(new Information_Variable(iter, var_name, -9999, yield_tables_names_list));
					
					objlist.add(softConstraints_LB_Weight[j]);		//add LB weight W|[j]
					vnamelist.add(var_name);
					vlblist.add((double) 0);
					vublist.add(softConstraints_LB[j]);			//l[j] can be max = L[j]
					l[j] = nvars;
					nvars++;				
				}
				
				// Create soft constraint upper bound variables u(j)			
				for (int j = 0; j < total_softConstraints; j++) {
					String var_name = "u_" + j;
					var_info_list.add(new Information_Variable(iter, var_name, -9999, yield_tables_names_list));
					
					objlist.add(softConstraints_UB_Weight[j]);		//add UB weight W||[j]
					vnamelist.add(var_name);
					vlblist.add((double) 0);
					vublist.add(Double.MAX_VALUE);					//u[j] can be max = any positive number
					u[j] = nvars;
					nvars++;				
				}
				
				// Create hard constraint decision variables z(k)			
				for (int k = 0; k < total_hardConstraints; k++) {
					String var_name = "z_" + k;
					var_info_list.add(new Information_Variable(iter, var_name, -9999, yield_tables_names_list));
					
					objlist.add((double) 0);
					vnamelist.add(var_name);
					vlblist.add(hardConstraints_LB[k]);				// Constraints 4 is set here as LB
					vublist.add(hardConstraints_UB[k]);				// Constraints 5 is set here as UB
					z[k] = nvars;
					nvars++;				
				}
				
				// Create free constraint decision variables v(n)			
				for (int n = 0; n < total_freeConstraints; n++) {
					String var_name = "v_" + n;
					var_info_list.add(new Information_Variable(iter, var_name, -9999, yield_tables_names_list));
					
					objlist.add((double) 0);
					vnamelist.add(var_name);
					vlblist.add((double) 0);				// 0 if not allow negative multiplier
//					vlblist.add(-Double.MAX_VALUE);			// -MAX_VALUE if allow negative multiplier, need to redesign flow logic
					vublist.add(Double.MAX_VALUE);			// MAX_VALUE is UB
					v[n] = nvars;
					nvars++;				
				}
							
				// Create decision variables xNGe(s1,s2,s3,s4,s5,s6)(i)(t)	
				xNGe = new int[total_model_strata][][];
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					String strata = model_strata.get(strata_id);
					
					xNGe[strata_id] = new int[total_NG_E_prescription_choices][];
					for (int i = 0; i < total_NG_E_prescription_choices; i++) {
						if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata.get(strata_id), "NG_E" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)				
							
							xNGe[strata_id][i] = new int[total_periods + 1 + iter];
							for (int t = 1 + iter; t <= total_periods + iter; t++) {
								String var_name = "xNG_E_" + strata + "_" + i + "_" + t;	
								Information_Variable var_info = new Information_Variable(iter, var_name, strata_starting_age[strata_id], yield_tables_names_list);
								
								if (!allow_Non_Existing_Prescription) {		// Boost 2
									if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xNGe[strata_id][i][t] = nvars;
										nvars++;
									}
								} else {
									var_info_list.add(var_info);
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									xNGe[strata_id][i][t] = nvars;
									nvars++;
								}
							}
						}
					}
				}													
				
				// Create decision variables xPBe(s1,s2,s3,s4,s5,s6)(i)(t)	
				xPBe = new int[total_model_strata][][];
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					String strata = model_strata.get(strata_id);
					
					xPBe[strata_id] = new int[total_PB_E_prescription_choices][];
					for (int i = 0; i < total_PB_E_prescription_choices; i++) {
						if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata.get(strata_id), "PB_E" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)	
							
							xPBe[strata_id][i] = new int[total_periods + 1 + iter];
							for (int t = 1 + iter; t <= total_periods + iter; t++) {
								String var_name = "xPB_E_" + strata + "_" + i + "_" + t;										
								Information_Variable var_info = new Information_Variable(iter, var_name, strata_starting_age[strata_id], yield_tables_names_list);
								
								if (!allow_Non_Existing_Prescription) {		// Boost 2
									if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xPBe[strata_id][i][t] = nvars;
										nvars++;
									}
								} else {
									var_info_list.add(var_info);
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									xPBe[strata_id][i][t] = nvars;
									nvars++;
								}
							}
						}
					}
				}														
				
				// Create decision variables xGSe(s1,s2,s3,s4,s5,s6)(i)(t)		
				xGSe = new int[total_model_strata][][];
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					String strata = model_strata.get(strata_id);
					
					xGSe[strata_id] = new int[total_GS_E_prescription_choices][];
					for (int i = 0; i < total_GS_E_prescription_choices; i++) {
						if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata.get(strata_id), "GS_E" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)		
							
							xGSe[strata_id][i] = new int[total_periods + 1 + iter];
							for (int t = 1 + iter; t <= total_periods + iter; t++) {
								String var_name = "xGS_E_" + strata + "_" + i + "_" + t;										
								Information_Variable var_info = new Information_Variable(iter, var_name, strata_starting_age[strata_id], yield_tables_names_list);
								
								if (!allow_Non_Existing_Prescription) {		// Boost 2
									if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xGSe[strata_id][i][t] = nvars;
										nvars++;
									}
								} else {
									var_info_list.add(var_info);
									objlist.add((double) 0);
									vnamelist.add(var_name);
									vlblist.add((double) 0);
									vublist.add(Double.MAX_VALUE);
									xGSe[strata_id][i][t] = nvars;
									nvars++;
								}
							}
						}
					}
				}														
				
				// Create decision variables xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R)(i)(t)
				xEAe = new int[total_model_strata][][][][];
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					String strata = model_strata.get(strata_id);
					int s5 = Collections.binarySearch(layer5, strata.split("_")[4]);
					
					xEAe[strata_id] = new int[total_periods + 1 + iter][][][];
					for (int tR = 1 + iter; tR <= total_periods + iter; tR++) {
						xEAe[strata_id][tR] = new int[total_layer5][][];
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							int rotationAge = tR + strata_starting_age[strata_id] - 1;
							String this_covertype_conversion_and_rotation_age = layer5.get(s5) + " " + layer5.get(s5R) + " " + rotationAge;						
							if (is_ea_defined_with_some_rows && Collections.binarySearch(ea_conversion_and_rotation_for_strata.get(strata_id), this_covertype_conversion_and_rotation_age) >= 0) {
								xEAe[strata_id][tR][s5R] = new int[total_EA_E_prescription_choices][];
								for (int i = 0; i < total_EA_E_prescription_choices; i++) {
//									if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata.get(strata_id), "EA_E" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)	
										
										xEAe[strata_id][tR][s5R][i] = new int[total_periods + 1 + iter];
										for (int t = 1 + iter; t <= tR; t++) {
											String var_name = "xEA_E_" + strata + "_" + tR + "_" + layer5.get(s5R) + "_" + i + "_" + t;
											Information_Variable var_info = new Information_Variable(iter, var_name, strata_starting_age[strata_id], yield_tables_names_list);
											
											if (!allow_Non_Existing_Prescription) {		// Boost 2
												if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
													var_info_list.add(var_info);
													objlist.add((double) 0);
													vnamelist.add(var_name);
													vlblist.add((double) 0);
													vublist.add(Double.MAX_VALUE);
													xEAe[strata_id][tR][s5R][i][t] = nvars;
													nvars++;
												}
											} else {
												var_info_list.add(var_info);
												objlist.add((double) 0);
												vnamelist.add(var_name);
												vlblist.add((double) 0);
												vublist.add(Double.MAX_VALUE);
												xEAe[strata_id][tR][s5R][i][t] = nvars;
												nvars++;
											}
										}
//									}
								}
							}
						}
					}
				}
		
				// Create decision variables xMS(s1,s2,s3,s4,s5,s6)(i)(t)	
				xMS = new int[total_model_strata][][];
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					if (is_nonsr_defined_with_some_rows && percentage_MS_E[strata_id] != 0) {	// only define MS_E if input is created and the percentage is not zero
						String strata = model_strata.get(strata_id);
						
						xMS[strata_id] = new int[total_MS_E_prescription_choices][];
						for (int i = 0; i < total_MS_E_prescription_choices; i++) {
//							if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata.get(strata_id), "MS_E" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)	
								
								xMS[strata_id][i] = new int[total_periods + 1 + iter];
								for (int t = 1 + iter; t <= total_periods + iter; t++) {
									String var_name = "xMS_E_" + strata + "_" + i + "_" + t;										
									Information_Variable var_info = new Information_Variable(iter, var_name, strata_starting_age[strata_id], yield_tables_names_list);
									
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
											var_info_list.add(var_info);
											objlist.add((double) 0);
											vnamelist.add(var_name);
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											xMS[strata_id][i][t] = nvars;
											nvars++;	
										}
									} else {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xMS[strata_id][i][t] = nvars;
										nvars++;
									}
								}
//							}
						}
					}
				}														
				
				// Create decision variables xBS(s1,s2,s3,s4,s5,s6)(i)(t)
				xBS = new int[total_model_strata][][];
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					if (is_nonsr_defined_with_some_rows && percentage_BS_E[strata_id] != 0) {	// only define BS_E if input is created and the percentage is not zero
						String strata = model_strata.get(strata_id);
						
						xBS[strata_id] = new int[total_BS_E_prescription_choices][];
						for (int i = 0; i < total_BS_E_prescription_choices; i++) {
//							if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata.get(strata_id), "BS_E" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)	
								
								xBS[strata_id][i] = new int[total_periods + 1 + iter];
								for (int t = 1 + iter; t <= total_periods + iter; t++) {
									String var_name = "xBS_E_" + strata + "_" + i + "_" + t;										
									Information_Variable var_info = new Information_Variable(iter, var_name, strata_starting_age[strata_id], yield_tables_names_list);
									
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
											var_info_list.add(var_info);
											objlist.add((double) 0);
											vnamelist.add(var_name);
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											xBS[strata_id][i][t] = nvars;
											nvars++;	
										}
									} else {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xBS[strata_id][i][t] = nvars;
										nvars++;
									}									
								}
//							}
						}
					}
				}													
				
				// Create decision variables xNGr(s1,s2,s3,s4,s5)(i)(t)(a)
				xNGr = new int[total_model_strata_without_sizeclass][][][];
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata = model_strata_without_sizeclass.get(strata_5layers_id);
					
					xNGr[strata_5layers_id] = new int[total_NG_R_prescription_choices][][];
					for (int i = 0; i < total_NG_R_prescription_choices; i++) {
						if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata_without_sizeclass.get(strata_5layers_id), "NG_R" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)
							
							xNGr[strata_5layers_id][i] = new int[total_periods + 1 + iter][];
							int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
							for (int t = t_regen + iter; t <= total_periods + iter; t++) {
								xNGr[strata_5layers_id][i][t] = new int[total_age_classes + 1 + iter];
								for (int a = 1; a <= t - 1; a++) {
									String var_name = "xNG_R_" + strata + "_" + i + "_" + t + "_" + a;										
									Information_Variable var_info = new Information_Variable(iter, var_name, -9999, yield_tables_names_list);
									
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
											var_info_list.add(var_info);
											objlist.add((double) 0);
											vnamelist.add(var_name);							
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											xNGr[strata_5layers_id][i][t][a] = nvars;
											nvars++;
										}
									} else {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xNGr[strata_5layers_id][i][t][a] = nvars;
										nvars++;
									}
								}
							}
						}
					}
				}						
				
				// Create decision variables xPBr(s1,s2,s3,s4,s5)(i)(t)(a)
				xPBr = new int[total_model_strata_without_sizeclass][][][];
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata = model_strata_without_sizeclass.get(strata_5layers_id);
					
					xPBr[strata_5layers_id] = new int[total_PB_R_prescription_choices][][];
					for (int i = 0; i < total_PB_R_prescription_choices; i++) {
						if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata_without_sizeclass.get(strata_5layers_id), "PB_R" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)
							
							xPBr[strata_5layers_id][i] = new int[total_periods + 1 + iter][];
							int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
							for (int t = t_regen + iter; t <= total_periods + iter; t++) {
								xPBr[strata_5layers_id][i][t] = new int[total_age_classes + 1];
								for (int a = 1; a <= t - 1; a++) {
									String var_name = "xPB_R_" + strata + "_" + i + "_" + t + "_" + a;										
									Information_Variable var_info = new Information_Variable(iter, var_name, -9999, yield_tables_names_list);
									
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
											var_info_list.add(var_info);
											objlist.add((double) 0);
											vnamelist.add(var_name);							
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											xPBr[strata_5layers_id][i][t][a] = nvars;
											nvars++;
										}
									} else {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xPBr[strata_5layers_id][i][t][a] = nvars;
										nvars++;
									}
								}
							}
						}
					}
				}						
				
				// Create decision variables xGSr(s1,s2,s3,s4,s5)(i)(t)(a)
				xGSr = new int[total_model_strata_without_sizeclass][][][];
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata = model_strata_without_sizeclass.get(strata_5layers_id);
					
					xGSr[strata_5layers_id] = new int[total_GS_R_prescription_choices][][];
					for (int i = 0; i < total_GS_R_prescription_choices; i++) {
						if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata_without_sizeclass.get(strata_5layers_id), "GS_R" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)
							
							xGSr[strata_5layers_id][i] = new int[total_periods + 1 + iter][];
							int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
							for (int t = t_regen + iter; t <= total_periods + iter; t++) {
								xGSr[strata_5layers_id][i][t] = new int[total_age_classes + 1];
								for (int a = 1; a <= t - 1; a++) {
									String var_name = "xGS_R_" + strata + "_" + i + "_" + t + "_" + a;										
									Information_Variable var_info = new Information_Variable(iter, var_name, -9999, yield_tables_names_list);
									
									if (!allow_Non_Existing_Prescription) {		// Boost 2
										if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
											var_info_list.add(var_info);
											objlist.add((double) 0);
											vnamelist.add(var_name);							
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											xGSr[strata_5layers_id][i][t][a] = nvars;
											nvars++;
										}
									} else {
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xGSr[strata_5layers_id][i][t][a] = nvars;
										nvars++;
									}
								}
							}
						}
					}
				}
				
				// Create decision variables xEAr(s1,s2,s3,s4,s5)(tR)(aR)(s5R)(i)(t)
				xEAr = new int[total_model_strata_without_sizeclass][][][][][];
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata = model_strata_without_sizeclass.get(strata_5layers_id);
					int s5 = Collections.binarySearch(layer5, strata.split("_")[4]);
					
					xEAr[strata_5layers_id] = new int[total_periods + 1 + iter][][][][];
					int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
					for (int tR = t_regen + iter; tR <= total_periods + iter; tR++) {
						xEAr[strata_5layers_id][tR] = new int[total_age_classes + 1][][][];
						for (int aR = 1; aR <= tR - 1; aR++) {
							xEAr[strata_5layers_id][tR][aR] = new int[total_layer5][][];
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								String this_covertype_conversion_and_rotation_age = layer5.get(s5) + " " + layer5.get(s5R) + " " + aR;						
								if (is_ea_defined_with_some_rows && Collections.binarySearch(ea_conversion_and_rotation_for_strata_without_sizeclass.get(strata_5layers_id), this_covertype_conversion_and_rotation_age) >= 0) {
									xEAr[strata_5layers_id][tR][aR][s5R] = new int[total_EA_R_prescription_choices][];
									for (int i = 0; i < total_EA_R_prescription_choices; i++) {
//										if (is_nonea_defined_with_some_rows && Collections.binarySearch(nonea_method_choice_for_strata_without_sizeclass.get(strata_5layers_id), "EA_R" + " " + i) >= 0) {	// Boost 1 (a.k.a. Silviculture Method)
											
											xEAr[strata_5layers_id][tR][aR][s5R][i] = new int[total_periods + 1 + iter];
											for (int t = tR - aR + 1; t <= tR; t++) {
												if (t >= t_regen + iter) {
													String var_name = "xEA_R_" + strata + "_" + tR + "_" + aR + "_" + layer5.get(s5R) + "_" + i + "_" + t;										
													Information_Variable var_info = new Information_Variable(iter, var_name, -9999, yield_tables_names_list);
													
													if (!allow_Non_Existing_Prescription) {		// Boost 2
														if (var_info.get_prescription_id_and_row_id()[0] != -9999) {
															var_info_list.add(var_info);
															objlist.add((double) 0);
															vnamelist.add(var_name);	
															vlblist.add((double) 0);
															vublist.add(Double.MAX_VALUE);
															xEAr[strata_5layers_id][tR][aR][s5R][i][t] = nvars;
															nvars++;
														}
													} else {
														var_info_list.add(var_info);
														objlist.add((double) 0);
														vnamelist.add(var_name);	
														vlblist.add((double) 0);
														vublist.add(Double.MAX_VALUE);
														xEAr[strata_5layers_id][tR][aR][s5R][i][t] = nvars;
														nvars++;
													}
												}
											}
//										}
									}
								}
							}
						}
					}
				}
				
				
				//-----------------------replacing disturbance variables
				// Create decision variables f(s1,s2,s3,s4,s5,t,s5R)	
				for (String strata: model_strata_without_sizeclass) {
					String[] strata_layer = strata.split("_");
					
					int strata_5layers_id = Collections.binarySearch(model_strata_without_sizeclass, strata);
					int s1 = Collections.binarySearch(layer1, strata_layer[0]);
					int s2 = Collections.binarySearch(layer2, strata_layer[1]);
					int s3 = Collections.binarySearch(layer3, strata_layer[2]);
					int s4 = Collections.binarySearch(layer4, strata_layer[3]);
					int s5 = Collections.binarySearch(layer5, strata_layer[4]);
					for (int t = 1 + iter; t <= total_periods + iter; t++) {
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							String var_name = "f_" + layer1.get(s1) + "_" + layer2.get(s2) + "_" + layer3.get(s3) + "_" + layer4.get(s4) + "_" + layer5.get(s5) + "_" + t + "_" + layer5.get(s5R);
							var_info_list.add(new Information_Variable(iter, var_name, -9999, yield_tables_names_list));
							
							objlist.add((double) 0);			
							vnamelist.add(var_name);										
							vlblist.add((double) 0);
							vublist.add(Double.MAX_VALUE);
							fire[strata_5layers_id][t][s5R] = nvars;
							nvars++;	
						}
					}
				}					
				
							
				// Convert lists to 1-D arrays
				double[] objvals = Stream.of(objlist.toArray(new Double[objlist.size()])).mapToDouble(Double::doubleValue).toArray();
				objlist = null;			// Clear the lists to save memory
				String[] vname = vnamelist.toArray(new String[vnamelist.size()]);
				vnamelist = null;		// Clear the lists to save memory
				double[] vlb = Stream.of(vlblist.toArray(new Double[vlblist.size()])).mapToDouble(Double::doubleValue).toArray();
				vlblist = null;			// Clear the lists to save memory
				double[] vub = Stream.of(vublist.toArray(new Double[vublist.size()])).mapToDouble(Double::doubleValue).toArray();
				vublist = null;			// Clear the lists to save memory
				Information_Variable[] var_info_array = new Information_Variable[vname.length];		// This array stores variable information
				for (int i = 0; i < vname.length; i++) {
					var_info_array[i] = var_info_list.get(i);
				}	
				var_info_list = null;	// Clear the lists to save memory
				
				
				// This array stores cost information
				double[] var_cost_value = new double[vname.length];
				for (int i = 0; i < vname.length; i++) {
					var_cost_value[i] = -9999;		// start with -9999
				}
							
				// This array stores replacing disturbances information
				int[] var_rd_condition_id = new int[vname.length];
				for (int i = 0; i < vname.length; i++) {
					var_rd_condition_id[i] = -9999;		// start with -9999   This is the priority id. example we have 4 conditions --> id from 0 to 3
				}
				
				// This array is all zeroes of replacing disturbance info
				double[][] all_zeroes_2D_array = new double[total_replacing_disturbances][total_layer5];
				for (int i = 0; i < total_layer5; i++) {
					for (int k = 0; k < total_replacing_disturbances; k++) {
						all_zeroes_2D_array[k][i] = (double) 0;	// just an all zeroes array
					}
				}
				
				System.out.println("Connecting " + new DecimalFormat("###,###,###").format(nvars) + " variables to disturbance & cost logic...");
				for (int var_index = 0; var_index < vname.length; var_index++) {
					Information_Variable var_info = var_info_array[var_index];
					int[] prescription_and_row = var_info.get_prescription_id_and_row_id();
					int var_prescription_id = prescription_and_row[0];
					int var_row_id = prescription_and_row[1];
					
					
					if (var_prescription_id != -9999 && var_row_id < yield_tables_values[var_prescription_id].length && var_row_id != -9999) {	
						// The above if then is not necessary (because we already have it in the void call), it is here just to help not create unnecessary Cost object as below
						// And because of this, it would save processing time
						
						// Replacing Disturbances -----------------------------------
						// Replacing Disturbances -----------------------------------
						if (disturbance_info != null) {	// in case there is condition --> calculate this one right away since they will be definitely used
							var_rd_condition_id[var_index] = disturbance_info.get_rd_condition_id_for_this_var(var_info, var_prescription_id, var_row_id);	// always return -9999 or a number
						}
						
						// Cost ------------------------------------------------------
						// Cost ------------------------------------------------------
						if (cost_info != null) {	// in case there is condition --> calculate this one right away for future usage
							int s5 = Collections.binarySearch(layer5, var_info.get_layer5());
							int t = var_info.get_period();
							int tR = var_info.get_rotation_period();
							double discounted_value = 1 / Math.pow(1 + annualDiscountRate, 10 * (t - 1));
							
							List<String> conversion_after_disturbances_classification_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
							List<Double> conversion_after_disturbances_total_loss_rate_list = new ArrayList<Double>();	// i.e. 0.25				0.75
							
							if (var_rd_condition_id[var_index] != -9999 && t != tR) {		// Note: no replacing disturbance or EA in the period = rotation period--> no conversion cost after replacing disturbance
								double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
								double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
								for (int s5R = 0; s5R < total_layer5; s5R++) {
									double total_loss_rate_for_this_conversion = 0;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
									}
									if (total_loss_rate_for_this_conversion > 0) {
										conversion_after_disturbances_classification_list.add(layer5.get(s5) + " " + layer5.get(s5R) + " " + "disturbance");
										conversion_after_disturbances_total_loss_rate_list.add(total_loss_rate_for_this_conversion);
									}														
								}
							} else {
								var_rd_condition_id[var_index] = -9999;		// fix percentage bug, printing out wrong percentages in output_05 for areas of MS, BS, EA where t = tR
							}
							
							var_cost_value[var_index] = cost_info.get_cost_value(
											var_info, var_prescription_id, var_row_id,
											cost_condition_list, conversion_after_disturbances_classification_list, conversion_after_disturbances_total_loss_rate_list);		// always return 0 or a number
							var_cost_value[var_index] = var_cost_value[var_index] * discounted_value;	// Cost is discounted
						} else {
							var_cost_value[var_index] = 0;
						}
					} else {
						var_cost_value[var_index] = 0;
					}
					
					if (var_index + 1 > 1 && ((var_index + 1) % 30000 == 0 || var_index + 1 == vname.length)) {
						System.out.println("           - Established connections:           " + new DecimalFormat("###,###,###").format(var_index + 1) + "             " + dateFormat.format(new Date()));
					}
				}
				System.out.println();
				System.out.println("Total decision variables as in PRISM obj. function eq. (1):   " + nvars + "             " + dateFormat.format(new Date()));
						
				
				// CREATE CONSTRAINTS-------------------------------------------------
				// CREATE CONSTRAINTS-------------------------------------------------
				// CREATE CONSTRAINTS-------------------------------------------------
				
				
				// Constraints 2-------------------------------------------------
				List<List<Integer>> c2_indexlist = new ArrayList<List<Integer>>();	
				List<List<Double>> c2_valuelist = new ArrayList<List<Double>>();
				List<Double> c2_lblist = new ArrayList<Double>();	
				List<Double> c2_ublist = new ArrayList<Double>();
				int c2_num = 0;
				
				// 2a
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
				
				// 2b
				for (int j = 0; j < total_softConstraints; j++) {
					// Add constraint
					c2_indexlist.add(new ArrayList<Integer>());
					c2_valuelist.add(new ArrayList<Double>());

					// Add y(j)
					c2_indexlist.get(c2_num).add(y[j]);
					c2_valuelist.get(c2_num).add((double) 1);
					
					// Add -u(j)
					c2_indexlist.get(c2_num).add(u[j]);
					c2_valuelist.get(c2_num).add((double) -1);

					// add bounds
					c2_lblist.add((double) 0);			// Lower bound set to 0	because y[j] >= u[j]
					c2_ublist.add(softConstraints_UB[j]);		// Upper bound of the soft constraint
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
				
				// Clear lists to save memory
				c2_indexlist = null;	
				c2_valuelist = null;
				c2_lblist = null;	
				c2_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (2):   " + c2_num + "             " + dateFormat.format(new Date()));
				
		
				
				// Constraints 3 & 4-------------------------------------------------
				// Constraints 3ab  (hard) and 4ab (free) are set as the bounds of variables
				System.out.println("Total constraints as in PRISM model formulation eq. (3,4):   0             " + dateFormat.format(new Date()));
				
				
				
				// Constraints 5-------------------------------------------------
				// Map previous iteration output_02
				LinkedHashMap<String, Double> map_var_name_to_var_value = null;
				LinkedHashMap<String, Double> map_var_name_to_var_total_loss_rate_mean = null;
				LinkedHashMap<String, Integer> map_var_name_to_var_rd_condition_id = null;
				
				
				List<List<Integer>> c5_indexlist = new ArrayList<List<Integer>>();	
				List<List<Double>> c5_valuelist = new ArrayList<List<Double>>();
				List<Double> c5_lblist = new ArrayList<Double>();	
				List<Double> c5_ublist = new ArrayList<Double>();
				int c5_num = 0;
				
				if (iter == 0) {
					// 5a
					for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
						int total_variables_added_for_this_stratum = 0;
						
						// Add constraint
						c5_indexlist.add(new ArrayList<Integer>());
						c5_valuelist.add(new ArrayList<Double>());
						
						// Add sigma(i) xNGe(s1,s2,s3,s4,s5,s6)[i][1]
						for (int i = 0; i < total_NG_E_prescription_choices; i++) {
							if (xNGe[strata_id][i] != null
									&& xNGe[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
								c5_indexlist.get(c5_num).add(xNGe[strata_id][i][1]);
								c5_valuelist.get(c5_num).add((double) 1);
								total_variables_added_for_this_stratum++;
							}
						}
				
						// Add sigma(i) xPBe(s1,s2,s3,s4,s5,s6)[i][1]
						for (int i = 0; i < total_PB_E_prescription_choices; i++) {
							if (xPBe[strata_id][i] != null
									&& xPBe[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
								c5_indexlist.get(c5_num).add(xPBe[strata_id][i][1]);
								c5_valuelist.get(c5_num).add((double) 1);
								total_variables_added_for_this_stratum++;
							}
						}
						
						// Add xGSe(s1,s2,s3,s4,s5,s6)[i][1]
						for (int i = 0; i < total_GS_E_prescription_choices; i++) {
							if (xGSe[strata_id][i] != null
									&& xGSe[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
								c5_indexlist.get(c5_num).add(xGSe[strata_id][i][1]);
								c5_valuelist.get(c5_num).add((double) 1);
								total_variables_added_for_this_stratum++;
							}
						}
						
						// Add sigma(tR,s5R)(i) xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][1]	
						for (int tR = 1; tR <= total_periods; tR++) {
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								for (int i = 0; i < total_EA_E_prescription_choices; i++) {
									if (xEAe[strata_id][tR] != null 
											&& xEAe[strata_id][tR][s5R] != null
												&& xEAe[strata_id][tR][s5R][i] != null
													&& xEAe[strata_id][tR][s5R][i][1] > 0) {		// if variable is defined, this value would be > 0 
										c5_indexlist.get(c5_num).add(xEAe[strata_id][tR][s5R][i][1]);
										c5_valuelist.get(c5_num).add((double) 1);
										total_variables_added_for_this_stratum++;
									}
								}
							}	
						}
						
						// Add sigma(i) xMS(s1,s2,s3,s4,s5,s6)[i][1]
						if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
							for (int i = 0; i < total_MS_E_prescription_choices; i++) {
								if (xMS[strata_id][i] != null 
										&& xMS[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
									c5_indexlist.get(c5_num).add(xMS[strata_id][i][1]);
									c5_valuelist.get(c5_num).add((double) 1);
									total_variables_added_for_this_stratum++;
								}
							}
						}
						
						// Add sigma(i) xBS(s1,s2,s3,s4,s5,s6)[i][1]
						if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
							for (int i = 0; i < total_BS_E_prescription_choices; i++) {
								if (xBS[strata_id][i] != null 
										&& xBS[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
									c5_indexlist.get(c5_num).add(xBS[strata_id][i][1]);
									c5_valuelist.get(c5_num).add((double) 1);
									total_variables_added_for_this_stratum++;
								}
							}
						}
										
						// Add bounds
						c5_lblist.add(strata_area[strata_id]);
						c5_ublist.add(strata_area[strata_id]);
						c5_num++;
						
						// Remove this constraint if no variable added
						if (total_variables_added_for_this_stratum == 0) {
							c5_indexlist.remove(c5_num - 1);
							c5_valuelist.remove(c5_num - 1);
							c5_lblist.remove(c5_num - 1);
							c5_ublist.remove(c5_num - 1);
							c5_num--;
						}
					}														
								
					// 5b (NOTE: Formulation-09 does not reflex the bounds turn-off flexibility, we might need to revise the equation 5b)
					for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
						int total_variables_added_for_this_stratum = 0;
						
						// Add constraint
						c5_indexlist.add(new ArrayList<Integer>());
						c5_valuelist.add(new ArrayList<Double>());
						
						// Add sigma(i) xMS(s1,s2,s3,s4,s5,s6)[i][1]
						if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
							for (int i = 0; i < total_MS_E_prescription_choices; i++) {
								if (xMS[strata_id][i] != null 
										&& xMS[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
									c5_indexlist.get(c5_num).add(xMS[strata_id][i][1]);
									c5_valuelist.get(c5_num).add((double) 1);
									total_variables_added_for_this_stratum++;
								}
							}
						}
										
						// Add bounds
						if (percentage_MS_E[strata_id] != -9999) {	// Mixed Fire 		// -9999 indicate the bounds should be turned off
							c5_lblist.add((double) percentage_MS_E[strata_id] / 100 * strata_area[strata_id]);
							c5_ublist.add((double) percentage_MS_E[strata_id] / 100 * strata_area[strata_id]);
						} else {
							c5_lblist.add((double) 0);
							c5_ublist.add(strata_area[strata_id]);
						}
						c5_num++;
						
						// Remove this constraint if no variable added
						if (total_variables_added_for_this_stratum == 0) {
							c5_indexlist.remove(c5_num - 1);
							c5_valuelist.remove(c5_num - 1);
							c5_lblist.remove(c5_num - 1);
							c5_ublist.remove(c5_num - 1);
							c5_num--;
						}
					}
								
					// 5c (NOTE: Formulation-09 does not reflex the bounds turn-off flexibility, we might need to revise the equation 5c)
					for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
						int total_variables_added_for_this_stratum = 0;
						
						// Add constraint
						c5_indexlist.add(new ArrayList<Integer>());
						c5_valuelist.add(new ArrayList<Double>());
						
						// Add sigma(i) xBS(s1,s2,s3,s4,s5,s6)[i][1]
						if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
							for (int i = 0; i < total_BS_E_prescription_choices; i++) {
								if (xBS[strata_id][i] != null 
										&& xBS[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
									c5_indexlist.get(c5_num).add(xBS[strata_id][i][1]);
									c5_valuelist.get(c5_num).add((double) 1);
									total_variables_added_for_this_stratum++;
								}
							}
						}
										
						// Add bounds
						if (percentage_BS_E[strata_id] != -9999) {	// Bark Beetle 		// -9999 indicate the bounds should be turned off
							c5_lblist.add((double) percentage_BS_E[strata_id] / 100 * strata_area[strata_id]);
							c5_ublist.add((double) percentage_BS_E[strata_id] / 100 * strata_area[strata_id]);
						} else {
							c5_lblist.add((double) 0);
							c5_ublist.add(strata_area[strata_id]);
						}
						c5_num++;
						
						// Remove this constraint if no variable added
						if (total_variables_added_for_this_stratum == 0) {
							c5_indexlist.remove(c5_num - 1);
							c5_valuelist.remove(c5_num - 1);
							c5_lblist.remove(c5_num - 1);
							c5_ublist.remove(c5_num - 1);
							c5_num--;
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
				} else {	// iteration >= 1
					// Map previous iteration output_02
					map_var_name_to_var_value = new LinkedHashMap<String, Double>();
					map_var_name_to_var_total_loss_rate_mean = new LinkedHashMap<String, Double>();
					map_var_name_to_var_rd_condition_id = new LinkedHashMap<String, Integer>(); 
					int previous_iter = iter -1;
					File previous_output_variables_file = new File(runFolder.getAbsolutePath() + "/output_02_variables_" + previous_iter + ".txt");
					String delimited = "\t";		// tab delimited
					try {		
						// All lines to be in array
						List<String> list;
						list = Files.readAllLines(Paths.get(previous_output_variables_file.getAbsolutePath()), StandardCharsets.UTF_8);
						list.remove(0);	// Remove the first row (Column names)
						String[] a = list.toArray(new String[list.size()]);
						int file_total_rows = a.length;
						int file_total_columns = a[0].split(delimited).length;
					
						// read all values from all rows and columns
						for (int i = 0; i < file_total_rows; i++) {
							String[] row_value = a[i].split(delimited);
							for (int j = 0; j < file_total_columns; j++) {
								map_var_name_to_var_value.put(row_value[1], Double.valueOf(row_value[2]));						// var_name = key, id = var_value
								map_var_name_to_var_total_loss_rate_mean.put(row_value[1], Double.valueOf(row_value[4]));		// var_name = key, sum of all loss rate means = var_value
								map_var_name_to_var_rd_condition_id.put(row_value[1], Integer.valueOf(row_value[5]));			// var_name = key, rd_condition_id = var_value
							}
						}
					} catch (IOException e) {
						System.err.println(e.getClass().getName() + ": " + e.getMessage());
					}
					
					// Map the period 2 result of simulating stochastic disturbances on the period 1 management solution of the previous iteration obtained from output_02
					// Note: only store for the x variables except regenerated variables at age 1
					LinkedHashMap<Integer, Double> map_var_index_to_stochastic_var_value = new LinkedHashMap<Integer, Double>();
					// Existing variables
					for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
						// xNGe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
						for (int i = 0; i < total_NG_E_prescription_choices; i++) {
							if (xNGe[strata_id][i] != null
									&& xNGe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
								// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
								int var_index = xNGe[strata_id][i][1 + iter];	// this is the first period solution from previous iteration
								double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
										var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
										disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
								// Mapping
								map_var_index_to_stochastic_var_value.put(var_index, final_value);
							}
						}
				
						// xPBe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
						for (int i = 0; i < total_PB_E_prescription_choices; i++) {
							if (xPBe[strata_id][i] != null
									&& xPBe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
								// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
								int var_index = xPBe[strata_id][i][1 + iter];	// this is the first period solution from previous iteration
								double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
										var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
										disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
								// Mapping
								map_var_index_to_stochastic_var_value.put(var_index, final_value);
							}
						}
						
						// xGSe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
						for (int i = 0; i < total_GS_E_prescription_choices; i++) {
							if (xGSe[strata_id][i] != null
									&& xGSe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
								// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
								int var_index = xGSe[strata_id][i][1 + iter];	// this is the first period solution from previous iteration
								double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
										var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
										disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
								// Mapping
								map_var_index_to_stochastic_var_value.put(var_index, final_value);
							}
						}
						
						// xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][1 + iter]	
						for (int tR = 1 + iter; tR <= total_periods + iter; tR++) {
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								for (int i = 0; i < total_EA_E_prescription_choices; i++) {
									if (xEAe[strata_id][tR] != null 
											&& xEAe[strata_id][tR][s5R] != null
												&& xEAe[strata_id][tR][s5R][i] != null
													&& xEAe[strata_id][tR][s5R][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
										int var_index = xEAe[strata_id][tR][s5R][i][1 + iter];	// this is the first period solution from previous iteration
										double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
												var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
												disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
										// Mapping
										map_var_index_to_stochastic_var_value.put(var_index, final_value);
									}
								}
							}	
						}
						
						// xMS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
						if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
							for (int i = 0; i < total_MS_E_prescription_choices; i++) {
								if (xMS[strata_id][i] != null 
										&& xMS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
									// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
									int var_index = xMS[strata_id][i][1 + iter];	// this is the first period solution from previous iteration
									double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
											var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
											disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
									// Mapping
									map_var_index_to_stochastic_var_value.put(var_index, final_value);
								}
							}
						}
						
						// xBS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
						if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
							for (int i = 0; i < total_BS_E_prescription_choices; i++) {
								if (xBS[strata_id][i] != null 
										&& xBS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
									// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
									int var_index = xBS[strata_id][i][1 + iter];	// this is the first period solution from previous iteration
									double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
											var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
											disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
									// Mapping
									map_var_index_to_stochastic_var_value.put(var_index, final_value);
								}
							}
						}
					}														
						
					// Regenerated variables
					for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
						String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
						int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
				
						// xNGr
						for (int i = 0; i < total_NG_R_prescription_choices; i++) {
							int t = 1 + iter;
							for (int a = 2; a <= t - 1; a++) {
								if(xNGr[strata_5layers_id][i] != null
										&& xNGr[strata_5layers_id][i][1 + iter] != null
												&& xNGr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
									// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
									int var_index = xNGr[strata_5layers_id][i][1 + iter][a];	// this is the first period solution from previous iteration
									double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
											var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
											disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
									// Mapping
									map_var_index_to_stochastic_var_value.put(var_index, final_value);
								}
							}
						}
					
						// xPBr
						for (int i = 0; i < total_PB_R_prescription_choices; i++) {
							int t = 1 + iter;
							for (int a = 2; a <= t - 1; a++) {		// a =2 to exclude regenerated variables at age class 1
								if(xPBr[strata_5layers_id][i] != null
										&& xPBr[strata_5layers_id][i][1 + iter] != null
												&& xPBr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
									// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
									int var_index = xPBr[strata_5layers_id][i][1 + iter][a];	// this is the first period solution from previous iteration
									double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
											var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
											disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
									// Mapping
									map_var_index_to_stochastic_var_value.put(var_index, final_value);
								}
							}
						}
						
						// xGSr
						for (int i = 0; i < total_GS_R_prescription_choices; i++) {
							int t = 1 + iter;
							for (int a = 2; a <= t - 1; a++) {	// a =2 to exclude regenerated variables at age class 1
								if(xGSr[strata_5layers_id][i] != null
										&& xGSr[strata_5layers_id][i][1 + iter] != null
												&& xGSr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
									// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
									int var_index = xGSr[strata_5layers_id][i][1 + iter][a];	// this is the first period solution from previous iteration
									double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
											var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
											disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
									// Mapping
									map_var_index_to_stochastic_var_value.put(var_index, final_value);
								}
							}
						}
						
						// xEAr
						int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
						for (int tR = t_regen + iter; tR <= total_periods + iter; tR++) {
							for (int aR = 1; aR <= tR-1; aR++) {									
								for (int s5R = 0; s5R < total_layer5; s5R++) {
									for (int i = 0; i < total_EA_R_prescription_choices; i++) {
										if(xEAr[strata_5layers_id][tR] != null
												 && xEAr[strata_5layers_id][tR][aR] != null
														 && xEAr[strata_5layers_id][tR][aR][s5R] != null
																 && xEAr[strata_5layers_id][tR][aR][s5R][i] != null
																		 && xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
											if (var_info_array[xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter]].get_age() > 1) {	// to exclude regenerated variables at age class 1
												// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
												int var_index = xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter];	// this is the first period solution from previous iteration
												double final_value = get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
														var_index, map_var_name_to_var_value, map_var_name_to_var_total_loss_rate_mean, var_info_array, layer5, var_rd_condition_id, 
														disturbance_info, total_replacing_disturbances, all_zeroes_2D_array);
												// Mapping
												map_var_index_to_stochastic_var_value.put(var_index, final_value);
											}
										}
									}
								}
							}
						}
					}
					// ---------------------------------------No-Merge & Merge options-------------------------------------------------
					// ---------------------------------------No-Merge & Merge options-------------------------------------------------
					// ---------------------------------------No-Merge & Merge options-------------------------------------------------
					switch (merging_option) {
					case "no_merge":
						// 5a to 5j
						List<Integer> key = new ArrayList<>(map_var_index_to_stochastic_var_value.keySet());
						for (int var_index : key) {
							// Add constraint
							c5_indexlist.add(new ArrayList<Integer>());
							c5_valuelist.add(new ArrayList<Double>());
							c5_indexlist.get(c5_num).add(var_index);
							c5_valuelist.get(c5_num).add((double) 1);
							// Add bounds
							c5_lblist.add(map_var_index_to_stochastic_var_value.get(var_index));
							c5_ublist.add(map_var_index_to_stochastic_var_value.get(var_index));
							c5_num++;
						}
						break;
					case "merge":
						// 5a For existing variables
						for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
							// use LinkedHashMap to add all relevant variables in iteration 1+M
							LinkedHashMap<Integer, String> map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();
							
							// xNGe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							for (int i = 0; i < total_NG_E_prescription_choices; i++) {
								if (xNGe[strata_id][i] != null
										&& xNGe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
									int var_index = xNGe[strata_id][i][1 + iter];
									String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
									String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
									if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
									map_var_index_to_var_state_id.put(var_index, state_id);
								}
							}
					
							// xPBe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							for (int i = 0; i < total_PB_E_prescription_choices; i++) {
								if (xPBe[strata_id][i] != null
										&& xPBe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
									int var_index = xPBe[strata_id][i][1 + iter];
									String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
									String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
									if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
									map_var_index_to_var_state_id.put(var_index, state_id);
								}
							}
							
							// xGSe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							for (int i = 0; i < total_GS_E_prescription_choices; i++) {
								if (xGSe[strata_id][i] != null
										&& xGSe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
									int var_index = xGSe[strata_id][i][1 + iter];
									String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
									String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
									if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
									map_var_index_to_var_state_id.put(var_index, state_id);
								}
							}
							
							// xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][1 + iter]	
							for (int tR = 1 + iter; tR <= total_periods + iter; tR++) {
								for (int s5R = 0; s5R < total_layer5; s5R++) {
									for (int i = 0; i < total_EA_E_prescription_choices; i++) {
										if (xEAe[strata_id][tR] != null 
												&& xEAe[strata_id][tR][s5R] != null
													&& xEAe[strata_id][tR][s5R][i] != null
														&& xEAe[strata_id][tR][s5R][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
											int var_index = xEAe[strata_id][tR][s5R][i][1 + iter];
											String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
											String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
											if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
											map_var_index_to_var_state_id.put(var_index, state_id);
										}
									}
								}	
							}
							
							// xMS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
								for (int i = 0; i < total_MS_E_prescription_choices; i++) {
									if (xMS[strata_id][i] != null 
											&& xMS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										int var_index = xMS[strata_id][i][1 + iter];
										String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
										String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
										if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
										map_var_index_to_var_state_id.put(var_index, state_id);
									}
								}
							}
							
							// xBS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
								for (int i = 0; i < total_BS_E_prescription_choices; i++) {
									if (xBS[strata_id][i] != null 
											&& xBS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										int var_index = xBS[strata_id][i][1 + iter];
										String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
										String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
										if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
										map_var_index_to_var_state_id.put(var_index, state_id);
									}
								}
							}

							// sorted LinkedHashMap by values
							LinkedHashMap<Integer, String> sorted_map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();
							map_var_index_to_var_state_id.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
								sorted_map_var_index_to_var_state_id.put(entry.getKey(), entry.getValue());
							});
							map_var_index_to_var_state_id = null;	// delete the unsorted map
//							// test printing
//							String current_state_id = "state_id would never have a space";
//							for (int var_index : sorted_map_var_index_to_var_state_id.keySet()) {
//								String state_id = sorted_map_var_index_to_var_state_id.get(var_index);
//								if (!state_id.equals(current_state_id)) { // print each block of variables with the same state_id
//									System.out.println(); // a blank line when reaching a new block
//									current_state_id = state_id;
//								};
//								String var_name = var_info_array[var_index].get_var_name();
//								System.out.println(var_index + "                 " + var_name + "                 state_id =                 " + state_id);
//							};
//							System.out.println("----------------------------------------------------------------------");
//							// Example printing blocks with same state_id:
//									143493                 xMS_E_A_N_C_B_D_F_0_2                 state_id =                 1_0_13_61_45_27_2_0
//
//									845                 xNG_E_A_N_C_B_D_F_0_2                 state_id =                 1_1_18_68_39_27_2_3
//									65216                 xEA_E_A_N_C_B_D_F_12_D_1_2                 state_id =                 1_1_18_68_39_27_2_3
//									65239                 xEA_E_A_N_C_B_D_F_13_D_1_2                 state_id =                 1_1_18_68_39_27_2_3
//									65264                 xEA_E_A_N_C_B_D_F_14_D_1_2                 state_id =                 1_1_18_68_39_27_2_3
//									65291                 xEA_E_A_N_C_B_D_F_15_D_1_2                 state_id =                 1_1_18_68_39_27_2_3
//									65320                 xEA_E_A_N_C_B_D_F_16_D_1_2                 state_id =                 1_1_18_68_39_27_2_3
//									143513                 xMS_E_A_N_C_B_D_F_1_2                 state_id =                 1_1_18_68_39_27_2_3
//									143533                 xMS_E_A_N_C_B_D_F_2_2                 state_id =                 1_1_18_68_39_27_2_3
//									143553                 xMS_E_A_N_C_B_D_F_3_2                 state_id =                 1_1_18_68_39_27_2_3
//									143573                 xMS_E_A_N_C_B_D_F_4_2                 state_id =                 1_1_18_68_39_27_2_3
//
//									24925                 xPB_E_A_N_C_B_D_F_0_2                 state_id =                 1_1_18_68_40_27_2_3
//									24945                 xPB_E_A_N_C_B_D_F_1_2                 state_id =                 1_1_18_68_40_27_2_3
//									24965                 xPB_E_A_N_C_B_D_F_2_2                 state_id =                 1_1_18_68_40_27_2_3
//									65205                 xEA_E_A_N_C_B_D_F_12_D_0_2                 state_id =                 1_1_18_68_40_27_2_3
//									65227                 xEA_E_A_N_C_B_D_F_13_D_0_2                 state_id =                 1_1_18_68_40_27_2_3
//									65251                 xEA_E_A_N_C_B_D_F_14_D_0_2                 state_id =                 1_1_18_68_40_27_2_3
//									65277                 xEA_E_A_N_C_B_D_F_15_D_0_2                 state_id =                 1_1_18_68_40_27_2_3
//									65305                 xEA_E_A_N_C_B_D_F_16_D_0_2                 state_id =                 1_1_18_68_40_27_2_3
//
//									55445                 xGS_E_A_N_C_B_D_F_0_2                 state_id =                 1_1_18_87_40_27_2_3
//									----------------------------------------------------------------------
//
//									143593                 xMS_E_A_N_C_B_D_G_0_2                 state_id =                 1_0_13_61_45_27_2_0
//
//									55465                 xGS_E_A_N_C_B_D_G_0_2                 state_id =                 1_1_86_10_50_27_2_1
//
//									24985                 xPB_E_A_N_C_B_D_G_0_2                 state_id =                 1_1_95_42_60_27_2_1
//									25005                 xPB_E_A_N_C_B_D_G_1_2                 state_id =                 1_1_95_42_60_27_2_1
//									25025                 xPB_E_A_N_C_B_D_G_2_2                 state_id =                 1_1_95_42_60_27_2_1
//
//									65335                 xEA_E_A_N_C_B_D_G_12_D_0_2                 state_id =                 1_1_95_45_61_27_2_1
//									65357                 xEA_E_A_N_C_B_D_G_13_D_0_2                 state_id =                 1_1_95_45_61_27_2_1
//									65381                 xEA_E_A_N_C_B_D_G_14_D_0_2                 state_id =                 1_1_95_45_61_27_2_1
//									65407                 xEA_E_A_N_C_B_D_G_15_D_0_2                 state_id =                 1_1_95_45_61_27_2_1
//									65435                 xEA_E_A_N_C_B_D_G_16_D_0_2                 state_id =                 1_1_95_45_61_27_2_1
//
//									865                 xNG_E_A_N_C_B_D_G_0_2                 state_id =                 1_1_95_45_7_27_2_1
//									65346                 xEA_E_A_N_C_B_D_G_12_D_1_2                 state_id =                 1_1_95_45_7_27_2_1
//									65369                 xEA_E_A_N_C_B_D_G_13_D_1_2                 state_id =                 1_1_95_45_7_27_2_1
//									65394                 xEA_E_A_N_C_B_D_G_14_D_1_2                 state_id =                 1_1_95_45_7_27_2_1
//									65421                 xEA_E_A_N_C_B_D_G_15_D_1_2                 state_id =                 1_1_95_45_7_27_2_1
//									65450                 xEA_E_A_N_C_B_D_G_16_D_1_2                 state_id =                 1_1_95_45_7_27_2_1
//									143613                 xMS_E_A_N_C_B_D_G_1_2                 state_id =                 1_1_95_45_7_27_2_1
//									143633                 xMS_E_A_N_C_B_D_G_2_2                 state_id =                 1_1_95_45_7_27_2_1
//									143653                 xMS_E_A_N_C_B_D_G_3_2                 state_id =                 1_1_95_45_7_27_2_1
//									143673                 xMS_E_A_N_C_B_D_G_4_2                 state_id =                 1_1_95_45_7_27_2_1
							
							
							// Note that because
							// 1. var_index --> always found prescription + " " + row_id
							// 2. prescription + " " + row_id  --> might not found state_id (i.e. state_id = ""). This is because we still allow variables without row_id found to be defined
							// --> Any variable with null state_id need to be hard-coded by no-merge (x = X)
							
							List<List<Integer>> all_blocks = new ArrayList<List<Integer>>();	// each block contains var_index of the variables that have the same state_id
							List<Integer> this_block = null;
							
							
							String current_state_id = "state_id would never have a space";
							for (int var_index : sorted_map_var_index_to_var_state_id.keySet()) {
								String state_id = sorted_map_var_index_to_var_state_id.get(var_index);
								if (!state_id.equals(current_state_id)) { // new block of variables with the same state_id
									if (this_block != null)	all_blocks.add(this_block);
									this_block = new ArrayList<Integer>(); // a new list when reaching a new block
									current_state_id = state_id;
								};
								this_block.add(var_index);
							}
							all_blocks.add(this_block); // add the last block
													
//							// test printing: same result as above example
//							for (List<Integer> block : all_blocks) {
//								System.out.println(); // a blank line for a new block
//								for (int var_index : block) {
//									String var_name = var_info_array[var_index].get_var_name();
//									String state_id = sorted_map_var_index_to_var_state_id.get(var_index);
//									System.out.println(var_index + "                 " + var_name + "                 state_id =                 " + state_id);
//								}
//								System.out.println("----------------------------------------------------------------------");
//							}
							
							for (List<Integer> block : all_blocks) {
								if (sorted_map_var_index_to_var_state_id.get(block.get(0)).equals("")) { // this is the block that has all "" state_id	--> no merge
									for (int var_index : block) {
										// Add constraint for each var
										c5_indexlist.add(new ArrayList<Integer>());
										c5_valuelist.add(new ArrayList<Double>());
										// add variable
										c5_indexlist.get(c5_num).add(var_index);
										c5_valuelist.get(c5_num).add((double) 1);
										// calculate bounds
										double var_value = 0;
										if (map_var_index_to_stochastic_var_value.get(var_index) != null) {
											var_value = map_var_index_to_stochastic_var_value.get(var_index);
										}
										// Add bounds
										c5_lblist.add(var_value);
										c5_ublist.add(var_value);
										c5_num++;
									}
								} else {	// this is the regular block (every state_id is not "") --> merge
									// Add constraint for each block
									c5_indexlist.add(new ArrayList<Integer>());
									c5_valuelist.add(new ArrayList<Double>());
									
									double total_var_value = 0;
									for (int var_index : block) {
										// add all variables in this block of the same state_id
										c5_indexlist.get(c5_num).add(var_index);
										c5_valuelist.get(c5_num).add((double) 1);
										// calculate bounds
										double var_value = 0;
										if (map_var_index_to_stochastic_var_value.get(var_index) != null) {
											var_value = map_var_index_to_stochastic_var_value.get(var_index);
										}
										total_var_value = total_var_value + var_value;
									}
									// Add bounds
									c5_lblist.add(total_var_value);
									c5_ublist.add(total_var_value);
									c5_num++;
								}
							}
						}
						
						// 5b and 5c
						for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
							// xMS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
								for (int i = 0; i < total_MS_E_prescription_choices; i++) {
									if (xMS[strata_id][i] != null 
											&& xMS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										// Add constraint
										c5_indexlist.add(new ArrayList<Integer>());
										c5_valuelist.add(new ArrayList<Double>());
										c5_indexlist.get(c5_num).add(xMS[strata_id][i][1 + iter]);
										c5_valuelist.get(c5_num).add((double) 1);
										// Add bounds
										int var_index = xMS[strata_id][i][1 + iter];
										double var_value = 0;
										if (map_var_index_to_stochastic_var_value.get(var_index) != null) {
											var_value = map_var_index_to_stochastic_var_value.get(var_index);
										}
										c5_lblist.add(var_value);
										c5_ublist.add(var_value);
										c5_num++;
									}
								}
							}
							
							// xBS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
							if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
								for (int i = 0; i < total_BS_E_prescription_choices; i++) {
									if (xBS[strata_id][i] != null 
											&& xBS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										// Add constraint
										c5_indexlist.add(new ArrayList<Integer>());
										c5_valuelist.add(new ArrayList<Double>());
										c5_indexlist.get(c5_num).add(xBS[strata_id][i][1 + iter]);
										c5_valuelist.get(c5_num).add((double) 1);
										// Add bounds
										int var_index = xBS[strata_id][i][1 + iter];
										double var_value = 0;
										if (map_var_index_to_stochastic_var_value.get(var_index) != null) {
											var_value = map_var_index_to_stochastic_var_value.get(var_index);
										}
										c5_lblist.add(var_value);
										c5_ublist.add(var_value);
										c5_num++;
									}
								}
							}
						}														

						// 5d For regenerated variables
						for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
							// use LinkedHashMap to add all relevant variables in iteration 1+M
							LinkedHashMap<Integer, String> map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();
							
							String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
							int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
					
							// xNGr
							for (int i = 0; i < total_NG_R_prescription_choices; i++) {
								int t = 1 + iter;
								for (int a = 2; a <= t - 1; a++) {	// a =2 to exclude regenerated variables at age class 1
									if(xNGr[strata_5layers_id][i] != null
											&& xNGr[strata_5layers_id][i][1 + iter] != null
													&& xNGr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
										int var_index = xNGr[strata_5layers_id][i][1 + iter][a];
										String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
										String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
										if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
										map_var_index_to_var_state_id.put(var_index, state_id);
									}
								}
							}
						
							// xPBr
							for (int i = 0; i < total_PB_R_prescription_choices; i++) {
								int t = 1 + iter;
								for (int a = 2; a <= t - 1; a++) {	// a =2 to exclude regenerated variables at age class 1
									if(xPBr[strata_5layers_id][i] != null
											&& xPBr[strata_5layers_id][i][1 + iter] != null
													&& xPBr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
										int var_index = xPBr[strata_5layers_id][i][1 + iter][a];
										String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
										String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
										if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
										map_var_index_to_var_state_id.put(var_index, state_id);
									}
								}
							}
							
							// xGSr
							for (int i = 0; i < total_GS_R_prescription_choices; i++) {
								int t = 1 + iter;
								for (int a = 2; a <= t - 1; a++) {	// a =2 to exclude regenerated variables at age class 1
									if(xGSr[strata_5layers_id][i] != null
											&& xGSr[strata_5layers_id][i][1 + iter] != null
													&& xGSr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
										int var_index = xGSr[strata_5layers_id][i][1 + iter][a];
										String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
										String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
										if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
										map_var_index_to_var_state_id.put(var_index, state_id);
									}
								}
							}
							
							// xEAr
							int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
							for (int tR = t_regen + iter; tR <= total_periods + iter; tR++) {
								for (int aR = 1; aR <= tR-1; aR++) {									
									for (int s5R = 0; s5R < total_layer5; s5R++) {
										for (int i = 0; i < total_EA_R_prescription_choices; i++) {
											if(xEAr[strata_5layers_id][tR] != null
													 && xEAr[strata_5layers_id][tR][aR] != null
															 && xEAr[strata_5layers_id][tR][aR][s5R] != null
																	 && xEAr[strata_5layers_id][tR][aR][s5R][i] != null
																			 && xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
												if (var_info_array[xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter]].get_age() > 1) {	// to exclude regenerated variables at age class 1
													int var_index = xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter];
													String prescription_and_row_id = var_info_array[var_index].get_yield_table_name_to_find() + " " + var_info_array[var_index].get_yield_table_row_index_to_find();
													String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
													if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
													map_var_index_to_var_state_id.put(var_index, state_id);
												}
											}
										}
									}
								}
							}
							
							// The below code for 5d is exactly the same as for 5a above (only 1 different that is because we exclude regen var at age 1 --> all blocks in iteration M = 1 are null)
							// sorted LinkedHashMap by values
							LinkedHashMap<Integer, String> sorted_map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();
							map_var_index_to_var_state_id.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
								sorted_map_var_index_to_var_state_id.put(entry.getKey(), entry.getValue());
							}); 
							map_var_index_to_var_state_id = null;	// delete the unsorted map
							
							
							// Note that because
							// 1. var_index --> always found prescription + " " + row_id
							// 2. prescription + " " + row_id  --> might not found state_id (i.e. state_id = ""). This is because we still allow variables without row_id found to be defined
							// --> Any variable with null state_id need to be hard-coded by no-merge (x = X)
							List<List<Integer>> all_blocks = new ArrayList<List<Integer>>();	// each block contains var_index of the variables that have the same state_id
							List<Integer> this_block = null;
							
							
							String current_state_id = "state_id would never have a space";
							for (int var_index : sorted_map_var_index_to_var_state_id.keySet()) {
								String state_id = sorted_map_var_index_to_var_state_id.get(var_index);
								if (!state_id.equals(current_state_id)) { // new block of variables with the same state_id
									if (this_block != null)	all_blocks.add(this_block);
									this_block = new ArrayList<Integer>(); // a new list when reaching a new block
									current_state_id = state_id;
								};
								this_block.add(var_index);
							}
							all_blocks.add(this_block); // add the last block
													
//							// test printing
//							for (List<Integer> block : all_blocks) {
//								System.out.println(); // a blank line for a new block
//								if (block != null) 		// this is because we exclude regenerated variables at age class 1 from merging --> all blocks in iter M = 1 would be null
//								for (int var_index : block) {
//									String var_name = var_info_array[var_index].get_var_name();
//									String state_id = sorted_map_var_index_to_var_state_id.get(var_index);
//									System.out.println(var_index + "                 " + var_name + "                 state_id =                 " + state_id);
//								}
//								System.out.println("----------------------------------------------------------------------");
//							}
							
							for (List<Integer> block : all_blocks) {
								if (block != null) 		// this is added because we exclude regenerated variables at age class 1 from merging --> all blocks in iter M = 1 would be null
								if (sorted_map_var_index_to_var_state_id.get(block.get(0)).equals("")) { // this is the block that has all "" state_id	--> no merge
									for (int var_index : block) {
										// Add constraint for each var
										c5_indexlist.add(new ArrayList<Integer>());
										c5_valuelist.add(new ArrayList<Double>());
										// add variable
										c5_indexlist.get(c5_num).add(var_index);
										c5_valuelist.get(c5_num).add((double) 1);
										// calculate bounds
										double var_value = 0;
										if (map_var_index_to_stochastic_var_value.get(var_index) != null) {
											var_value = map_var_index_to_stochastic_var_value.get(var_index);
										}
										// Add bounds
										c5_lblist.add(var_value);
										c5_ublist.add(var_value);
										c5_num++;
									}
								} else {	// this is the regular block (every state_id is not "") --> merge
									// Add constraint for each block
									c5_indexlist.add(new ArrayList<Integer>());
									c5_valuelist.add(new ArrayList<Double>());
									
									double total_var_value = 0;
									for (int var_index : block) {
										// add all variables in this block of the same state_id
										c5_indexlist.get(c5_num).add(var_index);
										c5_valuelist.get(c5_num).add((double) 1);
										// calculate bounds
										double var_value = 0;
										if (map_var_index_to_stochastic_var_value.get(var_index) != null) {
											var_value = map_var_index_to_stochastic_var_value.get(var_index);
										}
										total_var_value = total_var_value + var_value;
									}
									// Add bounds
									c5_lblist.add(total_var_value);
									c5_ublist.add(total_var_value);
									c5_num++;
								}
							}
						}
						break;
					default:
						break;
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
				
				// Clear lists to save memory
				c5_indexlist = null;	
				c5_valuelist = null;
				c5_lblist = null;	
				c5_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (5):   " + c5_num + "             " + dateFormat.format(new Date()));
				

				
				// Constraints 6-------------------------------------------------
				List<List<Integer>> c6_indexlist = new ArrayList<List<Integer>>();	
				List<List<Double>> c6_valuelist = new ArrayList<List<Double>>();
				List<Double> c6_lblist = new ArrayList<Double>();	
				List<Double> c6_ublist = new ArrayList<Double>();
				int c6_num = 0;
				
				// 6a
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					int s5 = Collections.binarySearch(layer5, model_strata.get(strata_id).split("_")[4]);

					for (int i = 0; i < total_NG_E_prescription_choices; i++) {
						for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {
							if (xNGe[strata_id][i] != null
									&& xNGe[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
								// Add constraint
								c6_indexlist.add(new ArrayList<Integer>());
								c6_valuelist.add(new ArrayList<Double>());
								
								// Add xNGe(s1,s2,s3,s4,s5,s6)[i][t]
								int var_index = xNGe[strata_id][i][t];
								double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
								double total_loss_rate = 0;
								for (int k = 0; k < total_replacing_disturbances; k++) {
									total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
								}
								if (total_loss_rate != 1) {	// only add if parameter is non zero
									c6_indexlist.get(c6_num).add(xNGe[strata_id][i][t]);
									c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
								}
								
								// Add -xNGe(s1,s2,s3,s4,s5,s6)[i][t+1]
								c6_indexlist.get(c6_num).add(xNGe[strata_id][i][t + 1]);
								c6_valuelist.get(c6_num).add((double) -1);
								
								// Add bounds
								c6_lblist.add((double) 0);
								c6_ublist.add((double) 0);
								c6_num++;
							}
						}
					}
				}			
				
				// 6b
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					int s5 = Collections.binarySearch(layer5, model_strata.get(strata_id).split("_")[4]);

					for (int i = 0; i < total_PB_E_prescription_choices; i++) {
						for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {
							if (xPBe[strata_id][i] != null
									&& xPBe[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
								// Add constraint
								c6_indexlist.add(new ArrayList<Integer>());
								c6_valuelist.add(new ArrayList<Double>());
								
								// Add xPBe(s1,s2,s3,s4,s5,s6)[i][t]
								int var_index = xPBe[strata_id][i][t];
								double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
								double total_loss_rate = 0;
								for (int k = 0; k < total_replacing_disturbances; k++) {
									total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
								}
								if (total_loss_rate != 1) {	// only add if parameter is non zero
									c6_indexlist.get(c6_num).add(xPBe[strata_id][i][t]);
									c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
								}
								
								// Add -xPBe(s1,s2,s3,s4,s5,s6)[i][t+1]
								c6_indexlist.get(c6_num).add(xPBe[strata_id][i][t + 1]);
								c6_valuelist.get(c6_num).add((double) -1);
								
								// Add bounds
								c6_lblist.add((double) 0);
								c6_ublist.add((double) 0);
								c6_num++;
							}
						}
					}
				}
				
				// 6c
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					int s5 = Collections.binarySearch(layer5, model_strata.get(strata_id).split("_")[4]);

					for (int i = 0; i < total_GS_E_prescription_choices; i++) {
						for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {
							if (xGSe[strata_id][i] != null
									&& xGSe[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
								//Add constraint
								c6_indexlist.add(new ArrayList<Integer>());
								c6_valuelist.add(new ArrayList<Double>());
								
								//Add xGSe(s1,s2,s3,s4,s5,s6)[i][t]
								int var_index = xGSe[strata_id][i][t];
								double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
								double total_loss_rate = 0;
								for (int k = 0; k < total_replacing_disturbances; k++) {
									total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
								}
								if (total_loss_rate != 1) {	// only add if parameter is non zero
									c6_indexlist.get(c6_num).add(xGSe[strata_id][i][t]);
									c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
								}
								
								//Add -xGSe(s1,s2,s3,s4,s5,s6)[i][t+1]
								c6_indexlist.get(c6_num).add(xGSe[strata_id][i][t + 1]);
								c6_valuelist.get(c6_num).add((double) -1);												
								
								//add bounds
								c6_lblist.add((double) 0);
								c6_ublist.add((double) 0);
								c6_num++;
							}
						}
					}
				}	
				
				// 6d
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					String strata = model_strata.get(strata_id);
					int s5 = Collections.binarySearch(layer5, strata.split("_")[4]);
					
					for (int tR = 1 + iter; tR <= total_periods + iter; tR++) {
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							for (int i = 0; i < total_EA_E_prescription_choices; i++) {
								for (int t = 1 + iter; t <= tR - 1; t++) {
									if (xEAe[strata_id][tR] != null 
											&& xEAe[strata_id][tR][s5R] != null
												&& xEAe[strata_id][tR][s5R][i] != null
													&& xEAe[strata_id][tR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0
										// Add constraint
										c6_indexlist.add(new ArrayList<Integer>());
										c6_valuelist.add(new ArrayList<Double>());
										
										// Add xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][t]
										int var_index = xEAe[strata_id][tR][s5R][i][t];
										double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
										double total_loss_rate = 0;
										for (int k = 0; k < total_replacing_disturbances; k++) {
											total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
										}
										if (total_loss_rate != 1) {	// only add if parameter is non zero
											c6_indexlist.get(c6_num).add(xEAe[strata_id][tR][s5R][i][t]);
											c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
										}
										
										// Add - xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][t+1]		
										c6_indexlist.get(c6_num).add(xEAe[strata_id][tR][s5R][i][t + 1]);
										c6_valuelist.get(c6_num).add((double) -1);																					
										
										// Add bounds
										c6_lblist.add((double) 0);
										c6_ublist.add((double) 0);
										c6_num++;
									}
								}
							}
						}
					}
				}
				
				// 6e
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
						int s5 = Collections.binarySearch(layer5, model_strata.get(strata_id).split("_")[4]);

						for (int i = 0; i < total_MS_E_prescription_choices; i++) {
							for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {
								if (xMS[strata_id][i] != null
										&& xMS[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
									// Add constraint
									c6_indexlist.add(new ArrayList<Integer>());
									c6_valuelist.add(new ArrayList<Double>());
									
									// Add xMS(s1,s2,s3,s4,s5,s6)[i][t]
									int var_index = xMS[strata_id][i][t] ;
									double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
									double total_loss_rate = 0;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
									}
									if (total_loss_rate != 1) {	// only add if parameter is non zero
										c6_indexlist.get(c6_num).add(xMS[strata_id][i][t]);
										c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
									}
									
									// Add -xMS(s1,s2,s3,s4,s5,s6)[i][t+1]
									c6_indexlist.get(c6_num).add(xMS[strata_id][i][t + 1]);
									c6_valuelist.get(c6_num).add((double) -1);
									
									// Add bounds
									c6_lblist.add((double) 0);
									c6_ublist.add((double) 0);
									c6_num++;
								}
							}
						}
					}
				}
				
				// 6f
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
						int s5 = Collections.binarySearch(layer5, model_strata.get(strata_id).split("_")[4]);

						for (int i = 0; i < total_BS_E_prescription_choices; i++) {
							for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {
								if (xBS[strata_id][i] != null
										&& xBS[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
									// Add constraint
									c6_indexlist.add(new ArrayList<Integer>());
									c6_valuelist.add(new ArrayList<Double>());
									
									//Add xBS(s1,s2,s3,s4,s5,s6)[i][t]	
									int var_index = xBS[strata_id][i][t] ;
									double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
									double total_loss_rate = 0;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
									}
									if (total_loss_rate != 1) {	// only add if parameter is non zero
										c6_indexlist.get(c6_num).add(xBS[strata_id][i][t]);
										c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
									}
									
									//Add -xBS(s1,s2,s3,s4,s5,s6)[i][t+1]
									c6_indexlist.get(c6_num).add(xBS[strata_id][i][t + 1]);
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
				
				// Clear lists to save memory
				c6_indexlist = null;	
				c6_valuelist = null;
				c6_lblist = null;	
				c6_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (6):   " + c6_num + "             " + dateFormat.format(new Date()));
			
				
				
				// Constraints 12a-------------------------------------------------This is equation (7a) in Prism-Formulation-10
				// Note: only store for the F~ parameters associated the simulation of stochastic disturbances on the first period solution in previous iteration (iter = M)
				LinkedHashMap<String, Double> map_F_name_to_stochastic_F_value = new LinkedHashMap<String, Double>();
				if (iter >= 1) {
					// 12 --> Loop writing in this way will improve speed. This is also applied to eq. 15 to save running time. Other equations are fast so it is not needed to use this type of loop
					for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
						String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
						int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);

						for (int t = iter; t <= iter; t++) {		// t = M
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								double total_value_for_this_F = 0;			// Note note note I am testing using the deterministic mean
								
								// Add existing variables ----------------------------------------------
								for (int s6 = 0; s6 < total_layer6; s6++) {
									String strata_name = strata_5layers + "_" + layer6.get(s6);	
									int strata_id = Collections.binarySearch(model_strata, strata_name);
									if (strata_id >= 0) {		// == if model_strata.contains(strata_name)   --   strata_id = -1 means list does not contain the string
										
										// Add - sigma(s6)(i)	xNGe[s1][s2][s3][s4][s5][s6][i][t] 	--> : X~
										for (int i = 0; i < total_NG_E_prescription_choices; i++) {
											if (xNGe[strata_id][i] != null) {
												String var_name = "xNG_E_" + strata_name + "_" + i + "_" + t;
												if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
													int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
												}
											}
										}
										
										// Add - sigma(s6)(i)	xPBe[s1][s2][s3][s4][s5][s6][i][t] 	--> : X~
										for (int i = 0; i < total_PB_E_prescription_choices; i++) {
											if (xPBe[strata_id][i] != null) {
												String var_name = "xPB_E_" + strata_name + "_" + i + "_" + t;
												if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
													int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
												}
											}
										}
										
										// Add - sigma(s6)(i)	xGSe[s1][s2][s3][s4][s5][s6][i][t] 	--> : X~
										for (int i = 0; i < total_GS_E_prescription_choices; i++) {
											if (xGSe[strata_id][i] != null) {
												String var_name = "xGS_E_" + strata_name + "_" + i + "_" + t;
												if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
													int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
												}
											}
										}
										
										// Add - sigma(s6)(tR)(s5R')(i)   xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R')(i)(t) 	--> : X~
										for (int tR = t + 1; tR <= total_periods + iter; tR++) {		// tR
											if (xEAe[strata_id][tR] != null) 
												for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
													if (xEAe[strata_id][tR][s5RR] != null)
														for (int i = 0; i < total_EA_E_prescription_choices; i++) {	// i
															if (xEAe[strata_id][tR][s5RR][i] != null) {
																String var_name = "xEA_E_" + strata_name + "_" + tR + "_" + layer5.get(s5RR) + "_" + i + "_" + t;
																if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
																	int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
																	double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
																	double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
																	double total_loss_rate_for_this_conversion = 0;
																	for (int k = 0; k < total_replacing_disturbances; k++) {
																		total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
																	}
																	total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
																}
															}
														}
												}
										}
										
										// Add - sigma(s6)(i)	xMSe[s1][s2][s3][s4][s5][s6][i][t] 	--> : X~
										if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
											for (int i = 0; i < total_MS_E_prescription_choices; i++) {
												if (xMS[strata_id][i] != null) {
													String var_name = "xMS_E_" + strata_name + "_" + i + "_" + t;
													if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
														int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
														double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
														double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
														double total_loss_rate_for_this_conversion = 0;
														for (int k = 0; k < total_replacing_disturbances; k++) {
															total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
														}
														total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
													}
												}
											} 
										}
										
										// Add - sigma(s6)(i)	xBSe[s1][s2][s3][s4][s5][s6][i][t] 	--> : X~
										if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
											for (int i = 0; i < total_BS_E_prescription_choices; i++) {
												if (xBS[strata_id][i] != null) {
													String var_name = "xBS_E_" + strata_name + "_" + i + "_" + t;
													if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
														int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
														double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
														double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
														double total_loss_rate_for_this_conversion = 0;
														for (int k = 0; k < total_replacing_disturbances; k++) {
															total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
														}
														total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
													}
												}
											}
										}
										
									}
								}

								
								// Add regeneration variables----------------------------------------------
								if ((iter == 0 && t >= 2) || (iter >= 1)) {		 // if there is only iteration 0 then we add regeneration variables for period >= 2 only
									// Add - sigma(i,a)	xNGr[s1][s2][s3][s4][s5][i][t][a] 	--> : X~
									for (int i = 0; i < total_NG_R_prescription_choices; i++) {
										if (xNGr[strata_5layers_id][i] != null) 
											for (int a = 1; a <= t - 1; a++) {
												String var_name = "xNG_R_" + strata_5layers + "_" + i + "_" + t + "_" + a;
												if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
													int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
												}
											}
									}
								
									// Add - sigma(i,a)	xPBr[s1][s2][s3][s4][s5][i][t][a] 	--> : X~
									for (int i = 0; i < total_PB_R_prescription_choices; i++) {
										if (xPBr[strata_5layers_id][i] != null) 
											for (int a = 1; a <= t - 1; a++) {
												String var_name = "xPB_R_" + strata_5layers + "_" + i + "_" + t + "_" + a;
												if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
													int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
												}
											}
									}
								
									// Add - sigma(i,a)	xGSr[s1][s2][s3][s4][s5][i][t][a] 	--> : X~
									for (int i = 0; i < total_GS_R_prescription_choices; i++) {
										if (xGSr[strata_5layers_id][i] != null) 
											for (int a = 1; a <= t - 1; a++) {
												String var_name = "xGS_R_" + strata_5layers + "_" + i + "_" + t + "_" + a;
												if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
													int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
												}
											}
									}

									// Add - sigma(tR)(aR)(s5R')(i)	xEAr[s1][s2][s3][s4][s5][tR][aR][s5R'][i][t] 	--> : X~
									for (int tR = t + 1; tR <= total_periods + iter; tR++) { // tR
										if (xEAr[strata_5layers_id][tR] != null) 
											for (int aR = 1; aR <= tR - 1; aR++) {		// Note that we lack the condition that (aR - tR + t > 0) as in Formulation-10. But we do not need it here since the null check here and the definition (line 769, 779) guarantee this condition
												if (xEAr[strata_5layers_id][tR][aR] != null) 
													for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
														if (xEAr[strata_5layers_id][tR][aR][s5RR] != null)
															for (int i = 0; i < total_EA_R_prescription_choices; i++) {
																if (xEAr[strata_5layers_id][tR][aR][s5RR][i] != null) { 
																	
																	String var_name = "xEA_R_" + strata_5layers + "_" + tR + "_" + aR + "_" + layer5.get(s5RR) + "_" + i + "_" + t;
																	if(map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != -9999) {
																		int rd_id = map_var_name_to_var_rd_condition_id.get(var_name);
																		double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(rd_id);
																		double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_id);
																		double total_loss_rate_for_this_conversion = 0;
																		for (int k = 0; k < total_replacing_disturbances; k++) {
																			total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
																		}
																		total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
																	}
																}
															}
													}
											}
									} 
								}
								// Map	fire[s1][s2][s3][s4][s5][t][s5R]
								String var_name = "f_" + strata_5layers + "_" + t + "_" + layer5.get(s5R);
								map_F_name_to_stochastic_F_value.put(var_name, total_value_for_this_F);
							}
						}											
					}
				}
				
				
				
				// Constraints 12b-------------------------------------------------This is equation (7b) in Prism-Formulation-10
				List<List<Integer>> c12_indexlist = new ArrayList<List<Integer>>();	
				List<List<Double>> c12_valuelist = new ArrayList<List<Double>>();
				List<Double> c12_lblist = new ArrayList<Double>();	
				List<Double> c12_ublist = new ArrayList<Double>();
				int c12_num = 0; 
				
				// 12 --> Loop writing in this way will improve speed. This is also applied to eq. 15 to save running time. Other equations are fast so it is not needed to use this type of loop
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
					int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);

					for (int t = 1 + iter; t <= total_periods + iter; t++) {
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							// Add constraint
							c12_indexlist.add(new ArrayList<Integer>());
							c12_valuelist.add(new ArrayList<Double>());
							
							// Add	fire[s1][s2][s3][s4][s5][t][s5R]
							c12_indexlist.get(c12_num).add(fire[strata_5layers_id][t][s5R]);
							c12_valuelist.get(c12_num).add((double) 1);	
							
							
							// Add existing variables ----------------------------------------------
							for (int s6 = 0; s6 < total_layer6; s6++) {
								String strata_name = strata_5layers + "_" + layer6.get(s6);	
								int strata_id = Collections.binarySearch(model_strata, strata_name);
								if (strata_id >= 0) {		// == if model_strata.contains(strata_name)   --   strata_id = -1 means list does not contain the string
									
									// Add - sigma(s6)(i)	xNGe[s1][s2][s3][s4][s5][s6][i][t]
									for (int i = 0; i < total_NG_E_prescription_choices; i++) {
										if (xNGe[strata_id][i] != null) {
											int var_index = xNGe[strata_id][i][t];
											if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
												double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
												double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
												double total_loss_rate_for_this_conversion = 0;
												for (int k = 0; k < total_replacing_disturbances; k++) {
													total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
												}
												if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
													c12_indexlist.get(c12_num).add(var_index);
													c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
												}
											}
										}
									}
									
									// Add - sigma(s6)(i)	xPBe[s1][s2][s3][s4][s5][s6][i][t]
									for (int i = 0; i < total_PB_E_prescription_choices; i++) {
										if (xPBe[strata_id][i] != null) {
											int var_index = xPBe[strata_id][i][t];
											if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
												double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
												double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
												double total_loss_rate_for_this_conversion = 0;
												for (int k = 0; k < total_replacing_disturbances; k++) {
													total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
												}
												if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
													c12_indexlist.get(c12_num).add(var_index);
													c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
												}	
											}
										}
									}
									
									// Add - sigma(s6)(i)	xGSe[s1][s2][s3][s4][s5][s6][i][t]
									for (int i = 0; i < total_GS_E_prescription_choices; i++) {
										if (xGSe[strata_id][i] != null) {
											int var_index = xGSe[strata_id][i][t];
											if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
												double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
												double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
												double total_loss_rate_for_this_conversion = 0;
												for (int k = 0; k < total_replacing_disturbances; k++) {
													total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
												}
												if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
													c12_indexlist.get(c12_num).add(var_index);
													c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
												}
											}
										}
									}
									
									// Add - sigma(s6)(tR)(s5R')(i)   xEAe(s1,s2,s3,s4,s5,s6)(tR)(s5R')(i)(t)
									for (int tR = t + 1; tR <= total_periods + iter; tR++) {		// tR
										if (xEAe[strata_id][tR] != null) 
											for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
												if (xEAe[strata_id][tR][s5RR] != null)
													for (int i = 0; i < total_EA_E_prescription_choices; i++) {	// i
														if (xEAe[strata_id][tR][s5RR][i] != null) {
														
															int var_index = xEAe[strata_id][tR][s5RR][i][t];
															if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
																double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
																double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
																double total_loss_rate_for_this_conversion = 0;
																for (int k = 0; k < total_replacing_disturbances; k++) {
																	total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
																}
																if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
																	c12_indexlist.get(c12_num).add(var_index);
																	c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
																}
															}
														}
													}
											}
									}
									
									// Add - sigma(s6)(i)	xMSe[s1][s2][s3][s4][s5][s6][i][t]
									if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
										for (int i = 0; i < total_MS_E_prescription_choices; i++) {
											if (xMS[strata_id][i] != null) {
												int var_index = xMS[strata_id][i][t];
												if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
														c12_indexlist.get(c12_num).add(var_index);
														c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
													}
												}
											}
										}
									}
									
									// Add - sigma(s6)(i)	xBSe[s1][s2][s3][s4][s5][s6][i][t]
									if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
										for (int i = 0; i < total_BS_E_prescription_choices; i++) {
											if (xBS[strata_id][i] != null) {
												int var_index = xBS[strata_id][i][t];
												if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
														c12_indexlist.get(c12_num).add(var_index);
														c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
													}
												}
											}
										}
									}
									
								}
							}

							
							// Add regeneration variables----------------------------------------------
							if ((iter == 0 && t >= 2) || (iter >= 1)) {		 // if there is only iteration 0 then we add regeneration variables for period >= 2 only
								// Add - sigma(i,a)	xNGr[s1][s2][s3][s4][s5][i][t][a]
								for (int i = 0; i < total_NG_R_prescription_choices; i++) {
									if (xNGr[strata_5layers_id][i] != null) 
										if (xNGr[strata_5layers_id][i][t] != null) 
											for (int a = 1; a <= t - 1; a++) {
												if(xNGr[strata_5layers_id][i][t][a] > 0 && var_rd_condition_id[xNGr[strata_5layers_id][i][t][a]] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
													
													int var_index = xNGr[strata_5layers_id][i][t][a];
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
														c12_indexlist.get(c12_num).add(var_index);
														c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
													}	
												}
											}
								}
							
								// Add - sigma(i,a)	xPBr[s1][s2][s3][s4][s5][i][t][a]
								for (int i = 0; i < total_PB_R_prescription_choices; i++) {
									if (xPBr[strata_5layers_id][i] != null) 
										if (xPBr[strata_5layers_id][i][t] != null) 
											for (int a = 1; a <= t - 1; a++) {
												if(xPBr[strata_5layers_id][i][t][a] > 0 && var_rd_condition_id[xPBr[strata_5layers_id][i][t][a]] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
													
													int var_index = xPBr[strata_5layers_id][i][t][a];
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
														c12_indexlist.get(c12_num).add(var_index);
														c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
													}	
												}
											}
								}
							
								// Add - sigma(i,a)	xGSr[s1][s2][s3][s4][s5][i][t][a]
								for (int i = 0; i < total_GS_R_prescription_choices; i++) {
									if (xGSr[strata_5layers_id][i] != null) 
										if (xGSr[strata_5layers_id][i][t] != null) 
											for (int a = 1; a <= t - 1; a++) {
												if(xGSr[strata_5layers_id][i][t][a] > 0 && var_rd_condition_id[xGSr[strata_5layers_id][i][t][a]] != -9999) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
													
													int var_index = xGSr[strata_5layers_id][i][t][a];
													double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_replacing_disturbances; k++) {
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
													}
													if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
														c12_indexlist.get(c12_num).add(var_index);
														c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
													}
												}
											}
								}

								// Add - sigma(tR)(aR)(s5R')(i)	xEAr[s1][s2][s3][s4][s5][tR][aR][s5R'][i][t]
								for (int tR = t + 1; tR <= total_periods + iter; tR++) { // tR
									if (xEAr[strata_5layers_id][tR] != null) 
										for (int aR = 1; aR <= tR - 1; aR++) {		// Note that we lack the condition that (aR - tR + t > 0) as in Formulation-09. But we do not need it here since the null check here and the definition (line 769, 779) guarantee this condition
											if (xEAr[strata_5layers_id][tR][aR] != null) 
												for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
													if (xEAr[strata_5layers_id][tR][aR][s5RR] != null)
														for (int i = 0; i < total_EA_R_prescription_choices; i++) {
															if (xEAr[strata_5layers_id][tR][aR][s5RR][i] != null) { 
																
																int var_index = xEAr[strata_5layers_id][tR][aR][s5RR][i][t];
																if(var_index > 0 && var_rd_condition_id[var_index] != -9999) {		// if variable is defined, this value would be > 0 
																	double[][] loss_rate_mean = disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
																	double[][][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]);
																	double total_loss_rate_for_this_conversion = 0;
																	for (int k = 0; k < total_replacing_disturbances; k++) {
																		total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (loss_rate_mean[k][s5] / 100) * (conversion_rate_mean[k][s5][s5R] / 100);
																	}
																	if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
																		c12_indexlist.get(c12_num).add(var_index);
																		c12_valuelist.get(c12_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
																	}
																}
															}
														}
												}
										}
								}
							}

							// Add bounds
							c12_lblist.add((double) 0);
							c12_ublist.add((double) 0);
							c12_num++;
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

				// Clear lists to save memory
				c12_indexlist = null;	
				c12_valuelist = null;
				c12_lblist = null;	
				c12_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (7):   " + c12_num + "             " + dateFormat.format(new Date()));
				
				
				
				// Constraints 13-------------------------------------------------This is equation (8) in Prism-Formulation-10
				List<List<Integer>> c13_indexlist = new ArrayList<List<Integer>>();	
				List<List<Double>> c13_valuelist = new ArrayList<List<Double>>();
				List<Double> c13_lblist = new ArrayList<Double>();	
				List<Double> c13_ublist = new ArrayList<Double>();
				int c13_num = 0;
				
				// 8a
				if (iter >= 1) {
					for (String strata_4layers: model_strata_without_sizeclass_and_covertype) {
						for (int t = iter; t <= iter; t++) {	// t=M									
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								
								// Add constraint
								c13_indexlist.add(new ArrayList<Integer>());
								c13_valuelist.add(new ArrayList<Double>());
								
								
								// FIRE VARIABLES: F~
								// Add sigma(s5) fire(s1,s2,s3,s4,s5)[t][s5R] 	--> : F~
								double value_of_RHS = 0;
								for (int s5 = 0; s5 < total_layer5; s5++) {
									String strata_5layers = strata_4layers + "_" + layer5.get(s5);
									String var_name = "f_" + strata_5layers + "_" + t + "_" + layer5.get(s5R);
									value_of_RHS = value_of_RHS + map_F_name_to_stochastic_F_value.get(var_name);
								}
								

								// NON-FIRE VARIABLES: X~
								// Add sigma(s5)(s6)(i) xEAe(s1,s2,s3,s4,s5,s6)[t][s5R][i][t] 	--> : X~
								for (int s5 = 0; s5 < total_layer5; s5++) {
									for (int s6 = 0; s6 < total_layer6; s6++) {
										for (int i = 0; i < total_EA_E_prescription_choices; i++) {
											String strata_name = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
											int strata_id = Collections.binarySearch(model_strata, strata_name);
											
											if (strata_id >= 0) {
												strata_name = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
												String var_name = "xEA_E_" + strata_name + "_" + t + "_" + layer5.get(s5R) + "_" + i + "_" + t;
												if (map_var_name_to_var_value.get(var_name) != null) {
													value_of_RHS = value_of_RHS + map_var_name_to_var_value.get(var_name);
												}
											}
										}
									}
								}
								
								// Add sigma(s5)(a)(i) xEAr(s1,s2,s3,s4,s5)[t][a][s5R][i][t] 	--> : X~
								if ((iter == 0 && t >= 2) || (iter >= 1)) {		 // if there is only iteration 0 then we add regeneration variables for period >= 2 only
									for (int s5 = 0; s5 < total_layer5; s5++) {
										for (int a = 1; a <= t - 1; a++) {
											for (int i = 0; i < total_EA_R_prescription_choices; i++) {
												String strata_name = strata_4layers + "_" + layer5.get(s5);
												String var_name = "xEA_R_" + strata_name + "_" + t + "_" + a + "_" + layer5.get(s5R) + "_" + i + "_" + t;
												if (map_var_name_to_var_value.get(var_name) != null) {
													value_of_RHS = value_of_RHS + map_var_name_to_var_value.get(var_name);
												}
											}
										}
									}
								}
								
								
								String strata_name = strata_4layers + "_" + layer5.get(s5R);		// = s1,s2,s3,s4,s5R
								int strata_5layers_id = Collections.binarySearch(model_strata_without_sizeclass, strata_name);
						
								// Add - sigma(i) xNGr(s1,s2,s3,s4,s5R)[i][t+1][1]
								for (int i = 0; i < total_NG_R_prescription_choices; i++) {
									if(xNGr[strata_5layers_id][i] != null
											&& xNGr[strata_5layers_id][i][t + 1] != null
													&& xNGr[strata_5layers_id][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
										c13_indexlist.get(c13_num).add(xNGr[strata_5layers_id][i][t + 1][1]);
										c13_valuelist.get(c13_num).add((double) -1);
									}
								}
								
								// Add - sigma(i) xPBr(s1,s2,s3,s4,s5R)[i][t+1][1]
								for (int i = 0; i < total_PB_R_prescription_choices; i++) {
									if(xPBr[strata_5layers_id][i] != null
											&& xPBr[strata_5layers_id][i][t + 1] != null
													&& xPBr[strata_5layers_id][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
										c13_indexlist.get(c13_num).add(xPBr[strata_5layers_id][i][t + 1][1]);
										c13_valuelist.get(c13_num).add((double) -1);
									}
								}
								
								// Add - sigma(i) xGSr(s1,s2,s3,s4,s5R)[i][t+1][1]
								for (int i = 0; i < total_GS_R_prescription_choices; i++) {
									if(xGSr[strata_5layers_id][i] != null
											&& xGSr[strata_5layers_id][i][t + 1] != null
													&& xGSr[strata_5layers_id][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
										c13_indexlist.get(c13_num).add(xGSr[strata_5layers_id][i][t + 1][1]);
										c13_valuelist.get(c13_num).add((double) -1);
									}
								}
												
								// Add -sigma(tR)(s5R')(i) xEAr(s1,s2,s3,s4,s5R)[tR][aR=tR-t][s5R'][i][t+1]
								for (int tR = t + 1; tR <= total_periods + iter; tR++) {
									for (int s5RR = 0; s5RR < total_layer5; s5RR++) {
										for (int i = 0; i < total_EA_R_prescription_choices; i++) {
											if(xEAr[strata_5layers_id][tR] != null
													&& xEAr[strata_5layers_id][tR][tR - t] != null
															&& xEAr[strata_5layers_id][tR][tR - t][s5RR] != null
																	&& xEAr[strata_5layers_id][tR][tR - t][s5RR][i] != null
																		&& xEAr[strata_5layers_id][tR][tR - t][s5RR][i][t + 1] > 0) {		// if variable is defined, this value would be > 0 
												c13_indexlist.get(c13_num).add(xEAr[strata_5layers_id][tR][tR - t][s5RR][i][t + 1]);
												c13_valuelist.get(c13_num).add((double) -1);
											}
										}
									}
								}
								
								// Add bounds
								c13_lblist.add((double) - value_of_RHS);
								c13_ublist.add((double) - value_of_RHS);
								c13_num++;
							}
						}							
					}
				}
				
				// 8b
				for (String strata_4layers: model_strata_without_sizeclass_and_covertype) {
					for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {										
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							
							// Add constraint
							c13_indexlist.add(new ArrayList<Integer>());
							c13_valuelist.add(new ArrayList<Double>());
							
							
							// FIRE VARIABLES
							// Add sigma(s5) fire(s1,s2,s3,s4,s5)[t][s5R]
							for (int s5 = 0; s5 < total_layer5; s5++) {
								String strata_5layers = strata_4layers + "_" + layer5.get(s5);
								int strata_5layers_id = Collections.binarySearch(model_strata_without_sizeclass, strata_5layers);
								c13_indexlist.get(c13_num).add(fire[strata_5layers_id][t][s5R]);
								c13_valuelist.get(c13_num).add((double) 1);
							}
							

							// NON-FIRE VARIABLES
							// Add sigma(s5)(s6)(i) xEAe(s1,s2,s3,s4,s5,s6)[t][s5R][i][t]
							for (int s5 = 0; s5 < total_layer5; s5++) {
								for (int s6 = 0; s6 < total_layer6; s6++) {
									for (int i = 0; i < total_EA_E_prescription_choices; i++) {
										String strata_name = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
										int strata_id = Collections.binarySearch(model_strata, strata_name);
										
										if (strata_id >= 0) {
											if (xEAe[strata_id][t] != null 
													&& xEAe[strata_id][t][s5R] != null
														&& xEAe[strata_id][t][s5R][i] != null
															&& xEAe[strata_id][t][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0
												c13_indexlist.get(c13_num).add(xEAe[strata_id][t][s5R][i][t]);
												c13_valuelist.get(c13_num).add((double) 1);
											}
										}
									}
								}
							}
							
							// Add sigma(s5)(a)(i) xEAr(s1,s2,s3,s4,s5)[t][a][s5R][i][t]
							if ((iter == 0 && t >= 2) || (iter >= 1)) {		 // if there is only iteration 0 then we add regeneration variables for period >= 2 only
								for (int s5 = 0; s5 < total_layer5; s5++) {
									for (int a = 1; a <= t - 1; a++) {
										for (int i = 0; i < total_EA_R_prescription_choices; i++) {
											String strata_name = strata_4layers + "_" + layer5.get(s5);
											int strata_5layers_id = Collections.binarySearch(model_strata_without_sizeclass, strata_name);
											
											if(xEAr[strata_5layers_id][t] != null
													&& xEAr[strata_5layers_id][t][a] != null
															&& xEAr[strata_5layers_id][t][a][s5R] != null
																	&& xEAr[strata_5layers_id][t][a][s5R][i] != null
																		&& xEAr[strata_5layers_id][t][a][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
												c13_indexlist.get(c13_num).add(xEAr[strata_5layers_id][t][a][s5R][i][t]);
												c13_valuelist.get(c13_num).add((double) 1);
											}
										}
									}
								}
							}
							
							
							String strata_name = strata_4layers + "_" + layer5.get(s5R);		// = s1,s2,s3,s4,s5R
							int strata_5layers_id = Collections.binarySearch(model_strata_without_sizeclass, strata_name);
					
							// Add - sigma(i) xNGr(s1,s2,s3,s4,s5R)[i][t+1][1]
							for (int i = 0; i < total_NG_R_prescription_choices; i++) {
								if(xNGr[strata_5layers_id][i] != null
										&& xNGr[strata_5layers_id][i][t + 1] != null
												&& xNGr[strata_5layers_id][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
									c13_indexlist.get(c13_num).add(xNGr[strata_5layers_id][i][t + 1][1]);
									c13_valuelist.get(c13_num).add((double) -1);
								}
							}
							
							// Add - sigma(i) xPBr(s1,s2,s3,s4,s5R)[i][t+1][1]
							for (int i = 0; i < total_PB_R_prescription_choices; i++) {
								if(xPBr[strata_5layers_id][i] != null
										&& xPBr[strata_5layers_id][i][t + 1] != null
												&& xPBr[strata_5layers_id][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
									c13_indexlist.get(c13_num).add(xPBr[strata_5layers_id][i][t + 1][1]);
									c13_valuelist.get(c13_num).add((double) -1);
								}
							}
							
							// Add - sigma(i) xGSr(s1,s2,s3,s4,s5R)[i][t+1][1]
							for (int i = 0; i < total_GS_R_prescription_choices; i++) {
								if(xGSr[strata_5layers_id][i] != null
										&& xGSr[strata_5layers_id][i][t + 1] != null
												&& xGSr[strata_5layers_id][i][t + 1][1] > 0) {		// if variable is defined, this value would be > 0 
									c13_indexlist.get(c13_num).add(xGSr[strata_5layers_id][i][t + 1][1]);
									c13_valuelist.get(c13_num).add((double) -1);
								}
							}
											
							// Add -sigma(tR)(s5R')(i) xEAr(s1,s2,s3,s4,s5R)[tR][aR=tR-t][s5R'][i][t+1]
							for (int tR = t + 1; tR <= total_periods + iter; tR++) {
								for (int s5RR = 0; s5RR < total_layer5; s5RR++) {
									for (int i = 0; i < total_EA_R_prescription_choices; i++) {
										if(xEAr[strata_5layers_id][tR] != null
												&& xEAr[strata_5layers_id][tR][tR - t] != null
														&& xEAr[strata_5layers_id][tR][tR - t][s5RR] != null
																&& xEAr[strata_5layers_id][tR][tR - t][s5RR][i] != null
																	&& xEAr[strata_5layers_id][tR][tR - t][s5RR][i][t + 1] > 0) {		// if variable is defined, this value would be > 0 
											c13_indexlist.get(c13_num).add(xEAr[strata_5layers_id][tR][tR - t][s5RR][i][t + 1]);
											c13_valuelist.get(c13_num).add((double) -1);
										}
									}
								}
							}
							
							// Add bounds
							c13_lblist.add((double) 0);
							c13_ublist.add((double) 0);
							c13_num++;
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
				
				// Clear lists to save memory
				c13_indexlist = null;	
				c13_valuelist = null;
				c13_lblist = null;	
				c13_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (8):   " + c13_num + "             " + dateFormat.format(new Date()));

				
				
				// Constraints 14-------------------------------------------------This is equation (9) in Prism-Formulation-10
				List<List<Integer>> c14_indexlist = new ArrayList<List<Integer>>();	
				List<List<Double>> c14_valuelist = new ArrayList<List<Double>>();
				List<Double> c14_lblist = new ArrayList<Double>();	
				List<Double> c14_ublist = new ArrayList<Double>();
				int c14_num = 0;
				
				// 14a
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
					int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
			
					for (int i = 0; i < total_NG_R_prescription_choices; i++) {
						int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
						for (int t = t_regen + iter; t <= total_periods - 1 + iter; t++) {  
							for (int a = 1; a <= t - 1; a++) {
								if(xNGr[strata_5layers_id][i] != null
										&& xNGr[strata_5layers_id][i][t] != null
												&& xNGr[strata_5layers_id][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
									// Add constraint
									c14_indexlist.add(new ArrayList<Integer>());
									c14_valuelist.add(new ArrayList<Double>());
									
									// Add xNGr(s1,s2,s3,s4,s5)[i][t][a]
									int var_index = xNGr[strata_5layers_id][i][t][a];
									double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
									double total_loss_rate = 0;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
									}
									if (total_loss_rate != 1) {	// only add if parameter is non zero
										c14_indexlist.get(c14_num).add(xNGr[strata_5layers_id][i][t][a]);
										c14_valuelist.get(c14_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
									}
									
									// Add - xNGr(s1,s2,s3,s4,s5)[i][t+1][a+1]
									c14_indexlist.get(c14_num).add(xNGr[strata_5layers_id][i][t + 1][a + 1]);
									c14_valuelist.get(c14_num).add((double) -1);										
									
									// Add bounds
									c14_lblist.add((double) 0);
									c14_ublist.add((double) 0);
									c14_num++;
								}
							}
						}
					}
				}
				
				// 14b
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
					int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
			
					for (int i = 0; i < total_PB_R_prescription_choices; i++) {
						int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
						for (int t = t_regen + iter; t <= total_periods - 1 + iter; t++) {  
							for (int a = 1; a <= t-1; a++) {
								if(xPBr[strata_5layers_id][i] != null
										&& xPBr[strata_5layers_id][i][t] != null
												&& xPBr[strata_5layers_id][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
									// Add constraint
									c14_indexlist.add(new ArrayList<Integer>());
									c14_valuelist.add(new ArrayList<Double>());
									
									// Add xPBr(s1,s2,s3,s4,s5)[i][t][a]
									int var_index = xPBr[strata_5layers_id][i][t][a];
									double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
									double total_loss_rate = 0;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
									}
									if (total_loss_rate != 1) {	// only add if parameter is non zero
										c14_indexlist.get(c14_num).add(xPBr[strata_5layers_id][i][t][a]);
										c14_valuelist.get(c14_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)

									}
									
									// Add - xPBr(s1,s2,s3,s4,s5)[i][t+1][a+1]
									c14_indexlist.get(c14_num).add(xPBr[strata_5layers_id][i][t + 1][a + 1]);
									c14_valuelist.get(c14_num).add((double) -1);										
									
									// Add bounds
									c14_lblist.add((double) 0);
									c14_ublist.add((double) 0);
									c14_num++;
								}
							}
						}
					}
				}						
				
				// 14c
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
					int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
		
					for (int i = 0; i < total_GS_R_prescription_choices; i++) {
						int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
						for (int t = t_regen + iter; t <= total_periods - 1 + iter; t++) {  
							for (int a = 1; a <= t-1; a++) {
								if(xGSr[strata_5layers_id][i] != null
										&& xGSr[strata_5layers_id][i][t] != null
												&& xGSr[strata_5layers_id][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
									// Add constraint
									c14_indexlist.add(new ArrayList<Integer>());
									c14_valuelist.add(new ArrayList<Double>());
									
									// Add xGSr(s1,s2,s3,s4,s5)[i][t][a]
									int var_index = xGSr[strata_5layers_id][i][t][a];
									double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
									double total_loss_rate = 0;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
									}
									if (total_loss_rate != 1) {	// only add if parameter is non zero
										c14_indexlist.get(c14_num).add(xGSr[strata_5layers_id][i][t][a]);
										c14_valuelist.get(c14_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
									}
									
									// Add - xGSr(s1,s2,s3,s4,s5)[i][t+1][a+1]
									c14_indexlist.get(c14_num).add(xGSr[strata_5layers_id][i][t + 1][a + 1]);
									c14_valuelist.get(c14_num).add((double) -1);										
									
									// Add bounds
									c14_lblist.add((double) 0);
									c14_ublist.add((double) 0);
									c14_num++;
								}
							}
						}
					}
				}					
				
				// 14d
				for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
					String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
					int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
					
					int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
					for (int tR = t_regen + iter; tR <= total_periods + iter; tR++) {
						for (int aR = 1; aR <= tR - 1; aR++) {									
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								for (int i = 0; i < total_EA_R_prescription_choices; i++) {
									for (int t = tR - aR + 1; t <= tR - 1; t++) {
										if (t >= t_regen + iter) {
											if(xEAr[strata_5layers_id][tR] != null
													 && xEAr[strata_5layers_id][tR][aR] != null
															 && xEAr[strata_5layers_id][tR][aR][s5R] != null
																	 && xEAr[strata_5layers_id][tR][aR][s5R][i] != null
																			 && xEAr[strata_5layers_id][tR][aR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0 
												// Add constraint
												c14_indexlist.add(new ArrayList<Integer>());
												c14_valuelist.add(new ArrayList<Double>());
												
												// Add xEAr(s1,s2,s3,s4,s5)[tR][aR][s5R][i][t]
												int var_index = xEAr[strata_5layers_id][tR][aR][s5R][i][t];
												double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
												double total_loss_rate = 0;
												for (int k = 0; k < total_replacing_disturbances; k++) {
													total_loss_rate = total_loss_rate + loss_rate_mean[k][s5] / 100;
												}
												if (total_loss_rate != 1) {	// only add if parameter is non zero
													c14_indexlist.get(c14_num).add(xEAr[strata_5layers_id][tR][aR][s5R][i][t]);
													c14_valuelist.get(c14_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
												}
										
												// Add - xEAr(s1,s2,s3,s4,s5,s6)[tR][aR][s5R][i][t+1]		
												c14_indexlist.get(c14_num).add(xEAr[strata_5layers_id][tR][aR][s5R][i][t + 1]);
												c14_valuelist.get(c14_num).add((double) -1);																					
												
												// Add bounds
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
				
				// Clear lists to save memory
				c14_indexlist = null;	
				c14_valuelist = null;
				c14_lblist = null;	
				c14_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (9):   " + c14_num + "             " + dateFormat.format(new Date()));
				System.out.println("Processing basic constraints...");
				
				
				
				// Constraints 15------------------------------------------------- for y(j) and z(k) and v(n)		This is equation (10) in Prism-Formulation-10
				LinkedHashMap<String, Integer> map_strata_to_strata_id = new LinkedHashMap<String, Integer>();
				for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
					map_strata_to_strata_id.put(model_strata.get(strata_id), strata_id);		// strata = key, strata_id = value		
				}
				LinkedHashMap<String, Integer> map_strata_without_sizeclass_to_id = new LinkedHashMap<String, Integer>();
				for (int id = 0; id < total_model_strata_without_sizeclass; id++) {
					map_strata_without_sizeclass_to_id.put(model_strata_without_sizeclass.get(id), id);		// strata_without_sizeclass = key, id = value		
				}
				
				List<List<Integer>> c15_indexlist = new ArrayList<List<Integer>>();
				List<List<Double>> c15_valuelist = new ArrayList<List<Double>>();
				List<Double> c15_lblist = new ArrayList<Double>();
				List<Double> c15_ublist = new ArrayList<Double>();
				int c15_num = 0;

				int current_freeConstraint = 0;
				int current_softConstraint = 0;
				int current_hardConstraint = 0;	
					
				// Add -y(j) + user_defined_variables = 0		or 			-z(k) + user_defined_variables = 0		or 			-v(n) + user_defined_variables = 0
				for (int id = 1; id < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; id++) {	//Loop from 1 because the first row of the userConstraint file is just title
					long bc_time_start = System.currentTimeMillis();		// measure time before adding each basic constraint
					
					// Get the parameter indexes list
					List<String> parameters_indexes = read.get_parameters_indexes(id);
					// Get the dynamic identifiers indexes list
					List<String> dynamic_dentifiers_column_indexes = read.get_dynamic_identifiers_column_indexes_in_row(id);
					List<List<String>> dynamic_identifiers = read.get_dynamic_identifiers_in_row(id);
					// Sort String so binary search could be used in "Information_Parameter - are_all_dynamic_identifiers_matched"
					for (List<String> this_dynamic_identifier: dynamic_identifiers) {
						Collections.sort(this_dynamic_identifier);
					}
					
							
					// Add constraint
					c15_indexlist.add(new ArrayList<Integer>());
					c15_valuelist.add(new ArrayList<Double>());

					// Add -y(j) or -z(k) or -v(n)
					int constraint_type_col = constraint_column_names_list.indexOf("bc_type");				
					switch (bc_values[id][constraint_type_col]) {
					case "SOFT":
						c15_indexlist.get(c15_num).add(y[current_softConstraint]);
						c15_valuelist.get(c15_num).add((double) -1);
						current_softConstraint++;
						break;
					case "HARD":
						c15_indexlist.get(c15_num).add(z[current_hardConstraint]);
						c15_valuelist.get(c15_num).add((double) -1);
						current_hardConstraint++;
						break;
					case "FREE":
						c15_indexlist.get(c15_num).add(v[current_freeConstraint]);
						c15_valuelist.get(c15_num).add((double) -1);
						current_freeConstraint++;
						break;
					}
										
					
					// Add user_defined_variables and parameters------------------------------------
					List<String> static_methods = read.get_static_methods(id);
					List<Integer> static_periods = read.get_static_periods(id).stream().map(Integer::parseInt).collect(Collectors.toList());	// convert List<String> --> List<Integer>
					
					Set<String> static_strata = read.get_static_strata(id);
					Set<String> static_strata_without_sizeclass = read.get_static_strata_without_sizeclass(id);
					
					int multiplier_col = constraint_column_names_list.indexOf("bc_multiplier");
					double multiplier = (!bc_values[id][multiplier_col].equals("null")) ?  Double.parseDouble(bc_values[id][multiplier_col]) : 0;	// if multiplier = null --> 0
								
					// These 4 lines create the intersection sets, the original id could be retrieved from the 2 maps: map_strata_to_strata_id & map_strata_without_sizeclass_to_id
					List<String> common_strata = new ArrayList<String>(model_strata);
					List<String> common_strata_without_sizeclass = new ArrayList<String>(model_strata_without_sizeclass);
					common_strata.retainAll(static_strata);
					common_strata_without_sizeclass.retainAll(static_strata_without_sizeclass);
					
					
					
					// Add existing variables --------------------------------------------------------------
					for (String strata : common_strata) {
						int strata_id = map_strata_to_strata_id.get(strata);			// Note we need index from model_strata not common_trata
						
						for (String method : static_methods) {
							switch (method) {

							case "NG_E":
								// Add xNGe[s1][s2][s3][s4][s5][s6][i][t]
								for (int i = 0; i < xNGe[strata_id].length; i++) {
									if (xNGe[strata_id][i] != null)
									for (int period : static_periods) {		//Loop all periods of user-defineed
										int t = period + iter;		// this is a special case we need to adjust
										if(xNGe[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
											int var_index = xNGe[strata_id][i][t];
											double para_value = parameter_info.get_total_value(
													var_info_array[var_index].get_prescription_id_and_row_id()[0],
													var_info_array[var_index].get_prescription_id_and_row_id()[1],
													parameters_indexes,
													dynamic_dentifiers_column_indexes, 
													dynamic_identifiers,
													var_cost_value[var_index]);
											para_value = para_value * multiplier;
																																					
											if (para_value > 0) {	// only add if parameter is non zero
												c15_indexlist.get(c15_num).add(var_index);
												c15_valuelist.get(c15_num).add((double) para_value);
												}
											}
										}
								}
								break;
								
							case "PB_E":
								// Add xPBe[s1][s2][s3][s4][s5][s6][i][t]
								for (int i = 0; i < xPBe[strata_id].length; i++) {
									if (xPBe[strata_id][i] != null)
										for (int period : static_periods) {		//Loop all periods of user-defineed
											int t = period + iter;		// this is a special case we need to adjust
											if(xPBe[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
												int var_index = xPBe[strata_id][i][t];
												double para_value = parameter_info.get_total_value(
														var_info_array[var_index].get_prescription_id_and_row_id()[0],
														var_info_array[var_index].get_prescription_id_and_row_id()[1],
														parameters_indexes,
														dynamic_dentifiers_column_indexes, 
														dynamic_identifiers,
														var_cost_value[var_index]);
												para_value = para_value * multiplier;
																																						
												if (para_value > 0) {	// only add if parameter is non zero
													c15_indexlist.get(c15_num).add(var_index);
													c15_valuelist.get(c15_num).add((double) para_value);
												}
											}
										}
								}
								break;

							case "GS_E":
								// Add xGSe[s1][s2][s3][s4][s5][s6][i][t]
								for (int i = 0; i < xGSe[strata_id].length; i++) {
									if (xGSe[strata_id][i] != null)
										for (int period : static_periods) {		//Loop all periods of user-defineed
											int t = period + iter;		// this is a special case we need to adjust
											if(xGSe[strata_id][i][t] > 0) {		// if variable is defined, this value would be > 0 
												int var_index = xGSe[strata_id][i][t];
												double para_value = parameter_info.get_total_value(
														var_info_array[var_index].get_prescription_id_and_row_id()[0],
														var_info_array[var_index].get_prescription_id_and_row_id()[1],
														parameters_indexes,
														dynamic_dentifiers_column_indexes, 
														dynamic_identifiers,
														var_cost_value[var_index]);
												para_value = para_value * multiplier;
																																						
												if (para_value > 0) {	// only add if parameter is non zero
													c15_indexlist.get(c15_num).add(var_index);
													c15_valuelist.get(c15_num).add((double) para_value);
												}
											}
									}
								}
								break;
								
							case "EA_E":
								// Add xEAe[s1][s2][s3][s4][s5][s6](tR)(s5R)(i)(t)
								int total_no_1 = xEAe[strata_id].length;
								for (int tR = 1 + iter; tR < total_no_1; tR++) {
									if (xEAe[strata_id][tR] != null) {
										int total_no_2 = xEAe[strata_id][tR].length;
										for (int s5R = 0; s5R < total_no_2; s5R++) {
											if (xEAe[strata_id][tR][s5R] != null) {
												int total_no_3 = xEAe[strata_id][tR][s5R].length;
												for (int i = 0; i < total_no_3; i++) {
													if (xEAe[strata_id][tR][s5R][i] != null)
														for (int period : static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
															int t = period + iter;		// this is a special case we need to adjust
															if (xEAe[strata_id][tR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0   (this also removes the need of checking conversion and rotation) 
																int var_index = xEAe[strata_id][tR][s5R][i][t];
																double para_value = parameter_info.get_total_value(
																		var_info_array[var_index].get_prescription_id_and_row_id()[0],
																		var_info_array[var_index].get_prescription_id_and_row_id()[1],
																		parameters_indexes,
																		dynamic_dentifiers_column_indexes, 
																		dynamic_identifiers,
																		var_cost_value[var_index]);
																para_value = para_value * multiplier;
																																										
																if (para_value > 0) {	// only add if parameter is non zero
																	c15_indexlist.get(c15_num).add(var_index);
																	c15_valuelist.get(c15_num).add((double) para_value);
																}
															}
														}
												}
											}
										}
									}
								}
								break;
								
							case "MS_E":
								// Add xMS[s1][s2][s3][s4][s5][s6][i][t]
								if (xMS[strata_id] != null)		// only MS_E and BS_E might have null at this point and we need to check
									for (int i = 0; i < xMS[strata_id].length; i++) {
										if (xMS[strata_id][i] != null)
											for (int period : static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
												int t = period + iter; // this is a special case we need to adjust
												if (xMS[strata_id][i][t] > 0) { // if variable is defined, this value
													int var_index = xMS[strata_id][i][t];
													double para_value = parameter_info.get_total_value(
															var_info_array[var_index].get_prescription_id_and_row_id()[0],
															var_info_array[var_index].get_prescription_id_and_row_id()[1],
															parameters_indexes,
															dynamic_dentifiers_column_indexes, 
															dynamic_identifiers,
															var_cost_value[var_index]);
													para_value = para_value * multiplier;
																																					
													if (para_value > 0) { // only add if parameter is non zero
														c15_indexlist.get(c15_num).add(var_index);
														c15_valuelist.get(c15_num).add((double) para_value);
													}
												}
											}
									}
								break;
								
							case "BS_E":
								// Add xBS[s1][s2][s3][s4][s5][s6][i][t]
								if (xBS[strata_id] != null) // only MS_E and BS_E might have null at this point and we need to check
									for (int i = 0; i < xBS[strata_id].length; i++) {
										if (xBS[strata_id][i] != null)
											for (int period : static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
												int t = period + iter;		// this is a special case we need to adjust
												if (xBS[strata_id][i][t] > 0) { // if variable is defined, this value would be > 0
													int var_index = xBS[strata_id][i][t];
													double para_value = parameter_info.get_total_value(
															var_info_array[var_index].get_prescription_id_and_row_id()[0],
															var_info_array[var_index].get_prescription_id_and_row_id()[1],
															parameters_indexes,
															dynamic_dentifiers_column_indexes, 
															dynamic_identifiers,
															var_cost_value[var_index]);
															para_value = para_value * multiplier;

													if (para_value > 0) { // only add if parameter is non zero
														c15_indexlist.get(c15_num).add(var_index);
														c15_valuelist.get(c15_num).add((double) para_value);
													}
												}
											}
									}
								break;
							}
						}
					}	
					
							
					
					// Add regenerated variables --------------------------------------------------------------
					for (String strata_5layers : common_strata_without_sizeclass) {
						int strata_5layers_id = map_strata_without_sizeclass_to_id.get(strata_5layers);			// Note we need index from model_strata_without_sizeclass not common_trata_without_sizeclass

						for (String method : static_methods) {
							switch (method) {

							case "NG_R":
								// Add xNGr
								if (xNGr[strata_5layers_id] != null)
								for (int i = 0; i < xNGr[strata_5layers_id].length; i++) {
									if (xNGr[strata_5layers_id][i] != null)
									for (int period : static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
										int t = period + iter; // this is a special case we need to adjust
										if (xNGr[strata_5layers_id][i][t] != null)
										for (int a = 1; a < xNGr[strata_5layers_id][i][t].length; a++) {
											if (xNGr[strata_5layers_id][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
												int var_index = xNGr[strata_5layers_id][i][t][a];
												double para_value = parameter_info.get_total_value(
														var_info_array[var_index].get_prescription_id_and_row_id()[0],
														var_info_array[var_index].get_prescription_id_and_row_id()[1],
														parameters_indexes,
														dynamic_dentifiers_column_indexes, 
														dynamic_identifiers,
														var_cost_value[var_index]);
												para_value = para_value * multiplier;
																																						
												if (para_value > 0) {	// only add if parameter is non zero
													c15_indexlist.get(c15_num).add(var_index);
													c15_valuelist.get(c15_num).add((double) para_value);
												}
											}
										}	
									}
								}
								break;
								
							case "PB_R":
								// Add xPBr
								if (xPBr[strata_5layers_id] != null)
								for (int i = 0; i < xPBr[strata_5layers_id].length; i++) {
									if (xPBr[strata_5layers_id][i] != null)
									for (int period : static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
										int t = period + iter; // this is a special case we need to adjust
										if (xPBr[strata_5layers_id][i][t] != null)
										for (int a = 1; a < xPBr[strata_5layers_id][i][t].length; a++) {
											if (xPBr[strata_5layers_id][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
												int var_index = xPBr[strata_5layers_id][i][t][a];
												double para_value = parameter_info.get_total_value(
														var_info_array[var_index].get_prescription_id_and_row_id()[0],
														var_info_array[var_index].get_prescription_id_and_row_id()[1],
														parameters_indexes,
														dynamic_dentifiers_column_indexes, 
														dynamic_identifiers,
														var_cost_value[var_index]);
												para_value = para_value * multiplier;
																																						
												if (para_value > 0) {	// only add if parameter is non zero
													c15_indexlist.get(c15_num).add(var_index);
													c15_valuelist.get(c15_num).add((double) para_value);
												}
											}
										}	
									}
								}
								break;

							case "GS_R":
								// Add xGSr
								if (xGSr[strata_5layers_id] != null)
								for (int i = 0; i < xGSr[strata_5layers_id].length; i++) {
									if (xGSr[strata_5layers_id][i] != null)
									for (int period : static_periods) {		//Loop all periods, 	final cut at t but we need parameter at time tR
										int t = period + iter; // this is a special case we need to adjust
										if (xGSr[strata_5layers_id][i][t] != null)
										for (int a = 1; a < xGSr[strata_5layers_id][i][t].length; a++) {
											if (xGSr[strata_5layers_id][i][t][a] > 0) {		// if variable is defined, this value would be > 0 
												int var_index = xGSr[strata_5layers_id][i][t][a];
												double para_value = parameter_info.get_total_value(
														var_info_array[var_index].get_prescription_id_and_row_id()[0],
														var_info_array[var_index].get_prescription_id_and_row_id()[1],
														parameters_indexes,
														dynamic_dentifiers_column_indexes, 
														dynamic_identifiers,
														var_cost_value[var_index]);
												para_value = para_value * multiplier;
																																						
												if (para_value > 0) {	// only add if parameter is non zero
													c15_indexlist.get(c15_num).add(var_index);
													c15_valuelist.get(c15_num).add((double) para_value);
												}
											}
										}	
									}
								}
								break;
								
							case "EA_R":
								// Add xEAr[s1][s2][s3][s4][s5][tR][aR][s5R][i][t]		  note   tR >= 2   -->   tR >= t >= tR - aR + 1
								if (xEAr[strata_5layers_id] != null) {
									int total_no_1 = xEAr[strata_5layers_id].length;
									int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
									for (int tR = t_regen + iter; tR < total_no_1; tR++) {
										if (xEAr[strata_5layers_id][tR] != null) {
											int total_no_2 = xEAr[strata_5layers_id][tR].length;
											for (int aR = 1; aR < total_no_2; aR++) {
												if (xEAr[strata_5layers_id][tR][aR] != null) {
													int total_no_3 = xEAr[strata_5layers_id][tR][aR].length;
													for (int s5R = 0; s5R < total_no_3; s5R++) {
														if (xEAr[strata_5layers_id][tR][aR][s5R] != null) {
															int total_no_4 = xEAr[strata_5layers_id][tR][aR][s5R].length;
															for (int i = 0; i < total_no_4; i++) {
																if (xEAr[strata_5layers_id][tR][aR][s5R][i] != null) {
																	for (int period : static_periods) {		//Loop all periods, 	final cut at tR but we need parameter at time t
																		int t = period + iter; // this is a special case we need to adjust
																		if (xEAr[strata_5layers_id][tR][aR][s5R][i][t] > 0) {		// if variable is defined, this value would be > 0   (this also removes the need of checking conversion and rotation)
																			int var_index = xEAr[strata_5layers_id][tR][aR][s5R][i][t];
																			double para_value = parameter_info.get_total_value(
																					var_info_array[var_index].get_prescription_id_and_row_id()[0],
																					var_info_array[var_index].get_prescription_id_and_row_id()[1],
																					parameters_indexes,
																					dynamic_dentifiers_column_indexes, 
																					dynamic_identifiers,
																					var_cost_value[var_index]);
																			para_value = para_value * multiplier;
																																													
																			if (para_value > 0) { // only add if parameter is non zero
																				c15_indexlist.get(c15_num).add(var_index);
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
								break;
							}
							
						}
					}					
					
					
					// Add bounds
					c15_lblist.add((double) 0);
					c15_ublist.add((double) 0);
					c15_num++;
					
					long bc_time_end = System.currentTimeMillis();		// measure time after reading this basic constraint
					double bc_time_reading = (double) (bc_time_end - bc_time_start) / 1000;
					int id_col = constraint_column_names_list.indexOf("bc_id");
					int description_col = constraint_column_names_list.indexOf("bc_description");
					System.out.println("           - Time (seconds) for reading basic constraint " + bc_values[id][id_col] + " - " + bc_values[id][description_col] + "             " + bc_time_reading);
				}

				
				
				// Clear arrays not used any more before final step of constraint 15 & solving -------------------------------------------------------------
//				xNGe = null;
//				xPBe = null;
//				xGSe = null;
//				xEAe = null;
//				xMS = null;
//				xBS = null;
//				xNGr = null;
//				xPBr = null;
//				xGSr = null;
//				xEAr = null;		
				
//				var_cost_value = null;
//				var_rd_condition_id = null;
				
				cost_info = null;
				parameter_info = null;
//				disturbance_info = null;
//				System.gc();		Not call this to avoid poor performance. just let the collector auto starts when needed.	
				
										

				double[] c15_lb = Stream.of(c15_lblist.toArray(new Double[c15_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
				c15_lblist = null;	// Clear lists to save memory
				double[] c15_ub = Stream.of(c15_ublist.toArray(new Double[c15_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
				c15_ublist = null;	// Clear lists to save memory
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
				c15_indexlist = null;	// Clear lists to save memory
				c15_valuelist = null;	// Clear lists to save memory						
				System.out.println("Total constraints as in PRISM model formulation eq. (10):   " + c15_num + "             " + dateFormat.format(new Date()));
				
				
				
				// Constraints 16 (flow)------------------------------------------------This is equation (11) in Prism-Formulation-10
				List<List<Integer>> c16_indexlist = new ArrayList<List<Integer>>();
				List<List<Double>> c16_valuelist = new ArrayList<List<Double>>();
				List<Double> c16_lblist = new ArrayList<Double>();
				List<Double> c16_ublist = new ArrayList<Double>();
				int c16_num = 0;
						
				
				List<Integer> bookkeeping_ID_list = new ArrayList<Integer>();	// This list contains all GUI - IDs of the Basic Constraints
				List<Integer> bookkeeping_Var_list = new ArrayList<Integer>();			// This list contains all SOLVER Variables - IDs of the Basic Constraints
				if (flow_set_list.size() > 0) {		// Add flow constraints if there is at least a flow set
					current_freeConstraint = 0;
					current_softConstraint = 0;
					current_hardConstraint = 0;	
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
					/*	Example:
					  Sigma 1 contains bc_id = [1], [1], [2]				// Left Sigma 		 Left term
					  Sigma 2 contains bc_id = [1], [2], [3], [4]			// Right Sigma 		 Right term
					  
					  Assume Flow LB = 30%
					  Assume Flow UB = 60%
					  
					  Then we would need to build 2 constraints to capture the flow between Sigma 1 and Sigma 2
					  [1] + [2] + [3] + [4] - 30% * ([1] + [1] + [2]) >= 0			// if LB = null <--> LB = 0% --> no need to add this constraint
					  [1] + [2] + [3] + [4] - 60% * ([1] + [1] + [2]) <= 0			// if UB = null <--> UB = +infinity --> no need to add this constraint
					  
					  Those above need to be modified into the following before enter the matrix
					  40% * [1] + 70% * [2] + [3] + [4] >= 0
					  -20% *[1] + 40% * [2] + [3] + [4] <= 0
					  
					  The below codes are used to create the above 2 modified constraints
					*/
					
					for (int i = 0; i < flow_set_list.size(); i++) {		// loop each flow set (or each row of the flow_constraints_table)
						if (flow_type_list.get(i).equals("HARD")) {		// ONly add constraint if flow type is HARD
							int this_set_total_constraints = flow_set_list.get(i).size() - 1;
							for (int j = 0; j < this_set_total_constraints; j++) {	// j is the SigmaBox (j=0 --> Sigma1     j=1 --> Sigma2     etc)
								// process each pair of 2 SigmaBoxes
								// Left Sigma associated with j 
								List<Integer> first_sigma_id_list = new ArrayList<Integer>();
								List<Integer> first_sigma_parameter_list = new ArrayList<Integer>();
								for (int ID : flow_set_list.get(i).get(j)) {
									int gui_table_bc_id = bookkeeping_ID_list.indexOf(ID);	
									if (!first_sigma_id_list.contains(gui_table_bc_id)) {	// if gui_table_bc_id is not in the list --> add and count the duplicated 
										first_sigma_id_list.add(gui_table_bc_id);
										first_sigma_parameter_list.add(Collections.frequency(flow_set_list.get(i).get(j), ID));		// count the duplicated and add to final parameter for this gui_table_bc_id
									}
								}
								
								
								// Right Sigma associated with j + 1
								List<Integer> second_sigma_id_list = new ArrayList<Integer>();
								List<Integer> second_sigma_parameter_list = new ArrayList<Integer>();
								for (int ID : flow_set_list.get(i).get(j + 1)) {
									int gui_table_bc_id = bookkeeping_ID_list.indexOf(ID);	
									if (!second_sigma_id_list.contains(gui_table_bc_id)) {	// if gui_table_bc_id is not in the list --> add and count the duplicated 
										second_sigma_id_list.add(gui_table_bc_id);
										second_sigma_parameter_list.add(Collections.frequency(flow_set_list.get(i).get(j + 1), ID));		// count the duplicated and add to final parameter for this gui_table_bc_id
									}
								}
								
								
								// Each pair of Sigmas need 2 constraints: 
								// Constraint 1: Final term = Right term - lowerbound % * Left term >= 0 
								// Constraint 2: Final term = Right term - upperbound % * Left term <= 0
								// Note: We also need to merge the duplicated
								
								// Define Final term
								List<Integer> final_sigma_id_list = new ArrayList<Integer>();
								List<Double> final_sigma_LB_parameter_list = new ArrayList<Double>();
								List<Double> final_sigma_UB_parameter_list = new ArrayList<Double>();
								// Add all Right term
								for (int item2 = 0; item2 < second_sigma_id_list.size(); item2++) {	
									final_sigma_id_list.add(second_sigma_id_list.get(item2));
									final_sigma_LB_parameter_list.add((double) second_sigma_parameter_list.get(item2));
									final_sigma_UB_parameter_list.add((double) second_sigma_parameter_list.get(item2));
								}
								// Merge parameter if found duplication in the Left term
								for (int gui_table_bc_id : first_sigma_id_list) {
									int item = first_sigma_id_list.indexOf(gui_table_bc_id);
									
									if (final_sigma_id_list.contains(gui_table_bc_id)) {	// duplicated gui_table_bc_id found
										int item2 = final_sigma_id_list.indexOf(gui_table_bc_id) ;
										
										double flow_LB = (flow_lowerbound_percentage_list.get(i) == null) ? 0 : flow_lowerbound_percentage_list.get(i) / 100;
										double flow_UB = (flow_upperbound_percentage_list.get(i) == null) ? Double.MAX_VALUE : flow_upperbound_percentage_list.get(i) / 100;
											
										double final_LB_parameter = final_sigma_LB_parameter_list.get(item2) - first_sigma_parameter_list.get(item) * flow_LB;		// - left term parameter * lowerbound_percentage here
										double final_UB_parameter = final_sigma_LB_parameter_list.get(item2) - first_sigma_parameter_list.get(item) * flow_UB;		// - left term parameter * upperbound_percentage here
										final_sigma_LB_parameter_list.set(item2, final_LB_parameter);
										final_sigma_UB_parameter_list.set(item2, final_UB_parameter);
									}
								}
								// Add parameter if found non-duplication in the Left term
								for (int gui_table_bc_id : first_sigma_id_list) {
									int item = first_sigma_id_list.indexOf(gui_table_bc_id);
									
									if (!final_sigma_id_list.contains(gui_table_bc_id)) {	// non-duplicated gui_table_bc_id
										double flow_LB = (flow_lowerbound_percentage_list.get(i) == null) ? 0 : flow_lowerbound_percentage_list.get(i) / 100;
										double flow_UB = (flow_upperbound_percentage_list.get(i) == null) ? Double.MAX_VALUE : flow_upperbound_percentage_list.get(i) / 100;
										
										final_sigma_id_list.add(first_sigma_id_list.get(item));
										final_sigma_LB_parameter_list.add((double) - first_sigma_parameter_list.get(item) * flow_LB);		// - left term parameter * lowerbound_percentage here
										final_sigma_UB_parameter_list.add((double) - first_sigma_parameter_list.get(item) * flow_UB);		// - left term parameter * upperbound_percentage here
									}
								}
									
								// Now build the 2 constraints using the Final term	
								if (flow_lowerbound_percentage_list.get(i) != null) {	// add when lowerbound_percentage is not null
									// Add constraint				Final term = Right term - LB% * Left term >= 0
									c16_indexlist.add(new ArrayList<Integer>());
									c16_valuelist.add(new ArrayList<Double>());
									
									// Add Final term
									for (int item = 0; item < final_sigma_id_list.size(); item++) {
										int gui_table_bc_id = final_sigma_id_list.get(item);
										int var_id = bookkeeping_Var_list.get(gui_table_bc_id);
										double var_value = final_sigma_LB_parameter_list.get(item);
										c16_indexlist.get(c16_num).add(var_id);
										c16_valuelist.get(c16_num).add((double) var_value);
									}
									
									// Add bounds
									c16_lblist.add((double) 0);			// Lower bound set to 0	
									c16_ublist.add(Double.MAX_VALUE);	// Upper bound set to max
									c16_num++;
								}
								
								
								if (flow_upperbound_percentage_list.get(i) != null) {	// add when upperbound_percentage is not null
									// Add constraint				Final term = Right term - UB% * Left term <= 0
									c16_indexlist.add(new ArrayList<Integer>());
									c16_valuelist.add(new ArrayList<Double>());
									
									// Add Final term
									for (int item = 0; item < final_sigma_id_list.size(); item++) {
										int gui_table_bc_id = final_sigma_id_list.get(item);
										int var_id = bookkeeping_Var_list.get(gui_table_bc_id);
										double var_value = final_sigma_UB_parameter_list.get(item);
										c16_indexlist.get(c16_num).add(var_id);
										c16_valuelist.get(c16_num).add((double) var_value);
									}
									
									// Add bounds
									c16_lblist.add(-Double.MAX_VALUE);	// lower bound set to min	
									c16_ublist.add((double) 0);			// Upper bound set to 0
									c16_num++;
								}							
							}
						}
					}
				}
				
				
				double[] c16_lb = Stream.of(c16_lblist.toArray(new Double[c16_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
				double[] c16_ub = Stream.of(c16_ublist.toArray(new Double[c16_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
				int[][] c16_index = new int[c16_num][];
				double[][] c16_value = new double[c16_num][];
			
				for (int i = 0; i < c16_num; i++) {
					c16_index[i] = new int[c16_indexlist.get(i).size()];
					c16_value[i] = new double[c16_indexlist.get(i).size()];
					for (int j = 0; j < c16_indexlist.get(i).size(); j++) {
						c16_index[i][j] = c16_indexlist.get(i).get(j);
						c16_value[i][j] = c16_valuelist.get(i).get(j);			
					}
				}		
				
				// Clear lists to save memory
				c16_indexlist = null;	
				c16_valuelist = null;
				c16_lblist = null;	
				c16_ublist = null;
				System.out.println("Total constraints as in PRISM model formulation eq. (11):   " + c16_num + "             " + dateFormat.format(new Date()) + "\n");
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
						
				
				
				
				// Solve problem----------------------------------------------------------------------------------------------				
//				// Set constraints set name: Notice THIS WILL EXTREMELY SLOW THE SOLVING PROCESS (recommend for debugging only)
//				int indexOfC2 = c2_num;
//				int indexOfC5 = indexOfC2 + c5_num;
//				int indexOfC6 = indexOfC5 + c6_num;
//				int indexOfC7 = indexOfC6 + c7_num;
//				int indexOfC8 = indexOfC7 + c8_num;
//				int indexOfC9 = indexOfC8 + c9_num;
//				int indexOfC10 = indexOfC9 + c10_num;
//				int indexOfC11 = indexOfC10 + c11_num;
//				int indexOfC12 = indexOfC11 + c12_num;
//				int indexOfC13 = indexOfC12 + c13_num;
//				int indexOfC14 = indexOfC13 + c14_num;
//				int indexOfC15 = indexOfC14 + c15_num;		//Note: 	lp.getRanges().length = indexOfC15
//				
//				for (int i = 0; i<lp.getRanges().length; i++) {		
//					if (0<=i && i<indexOfC2) lp.getRanges() [i].setName("EQ(2)_");
//					if (indexOfC2<=i && i<indexOfC5) lp.getRanges() [i].setName("EQ(5)_");
//					if (indexOfC5<=i && i<indexOfC6) lp.getRanges() [i].setName("EQ(6)_");
//					if (indexOfC6<=i && i<indexOfC7) lp.getRanges() [i].setName("EQ(7)_");
//					if (indexOfC7<=i && i<indexOfC8) lp.getRanges() [i].setName("EQ(8)_");
//					if (indexOfC8<=i && i<indexOfC9) lp.getRanges() [i].setName("EQ(9)_");
//					if (indexOfC9<=i && i<indexOfC10) lp.getRanges() [i].setName("EQ(10)_");
//					if (indexOfC10<=i && i<indexOfC11) lp.getRanges() [i].setName("EQ(11)_");
//					if (indexOfC11<=i && i<indexOfC12) lp.getRanges() [i].setName("EQ(12)_");
//					if (indexOfC12<=i && i<indexOfC13) lp.getRanges() [i].setName("EQ(13)_");
//					if (indexOfC13<=i && i<indexOfC14) lp.getRanges() [i].setName("EQ(14)_");
//					if (indexOfC14<=i && i<indexOfC15) lp.getRanges() [i].setName("EQ(15)_");
//				}
				time_end = System.currentTimeMillis();		// measure time after reading
				time_reading = (double) (time_end - time_start) / 1000;
				
				
				
				
				
				if (solver_for_optimization.equals("CPLEX")) {
					// Load jar file dynamically at run time   (this is just for solving by CPLEX while running PRISM in Eclipse IDE)   
//					// This first try-catch works in Java SE-8 but not works in Java SE-9 --> ClassLoader.getSystemClassLoader() sequences will no longer execute:              https://blog.codefx.org/java/java-9-migration-guide/#Casting-To-URL-Class-Loader
//					try {
//						File file = new File("C:\\Users\\Dung Nguyen\\Desktop\\Temporary\\cplex.jar");
//						URL url = file.toURI().toURL();
//						URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
//						Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//						method.setAccessible(true);
//						method.invoke(classLoader, url);	
//						System.out.println("Successfully loaded cplex.jar from C:\\Users\\Dung Nguyen\\Desktop\\Temporary");	
//					} catch (Exception e) {
//						System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//						
//						// If not successful then:
//						// Load jar file dynamically at run time   (this is just for solving by CPLEX while running PRISM outside of Eclipse IDE - running the PrismAlphax.x.x.jar)
//						//  ........currently not working, need to find a way
//						try {
//							File file = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "cplex.jar");
//							URL url = file.toURI().toURL();
//							URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
//							Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//							method.setAccessible(true);
//							method.invoke(classLoader, url);	
//							System.out.println("Successfully loaded cplex.jar from " + FilesHandle.get_temporaryFolder().getAbsolutePath());
//						} catch (Exception e1) {
//							System.err.println("cplex error - " + e1.getClass().getName() + ": " + e1.getMessage());
//						}
//					}
					
					
					
					// Add the CPLEX native library path dynamically at run time   (this is just for solving by CPLEX while running PRISM in Eclipse IDE, we do not have some .dll in Temporary folder in this case, for i.e. cple1261.dll)
					try {
//						LibraryHandle.setLibraryPath("C:\\Users\\Dung Nguyen\\Desktop\\Temporary");
						LibraryHandle.addLibraryPath("C:\\Users\\Dung Nguyen\\Desktop\\Temporary");
						System.out.println("C:\\Users\\Dung Nguyen\\Desktop\\Temporary"  + " has been added to the java library paths");
					} catch (Exception e) {
						System.err.println("cplex error - Developer's computer is not found. " + e.getClass().getName() + ": " + e.getMessage());

					}
					
					// Add the CPLEX native library path dynamically at run time. When running PRISM outside of Eclipse IDE --> Temporary folder should have all .dll including cplex1261.dll
					try {
//						LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	// This will clear all other paths (many) and set path to the "temporary" folder
						LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	// This will NOT clear all other paths, and will add the "temporary" folder to the paths
						System.out.println(FilesHandle.get_temporaryFolder().getAbsolutePath().toString() + " has been added to the java library paths");	
						
						System.out.println("Below is all the java library paths Prism found:");
						String property = System.getProperty("java.library.path");
						StringTokenizer parser = new StringTokenizer(property, ";");
						while (parser.hasMoreTokens()) {
							System.out.println("           - " + parser.nextToken());
						}
					} catch (Exception e) {
						System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					
					
					
					
					prismcplex.Cplex_Wrapper cplex_wrapper = new prismcplex.Cplex_Wrapper(nvars, vlb, vub, vname, objvals, solvingTimeLimit);		vlb = null; vub = null; objvals = null;	// cannot clear vame because it is going to be used
					
					// add constraints & Clear arrays to save memory
					cplex_wrapper.addRows(c2_lb, c2_ub, c2_index, c2_value); 	c2_lb = null;  c2_ub = null;  c2_index = null;  c2_value = null;	// Constraints 2
					cplex_wrapper.addRows(c5_lb, c5_ub, c5_index, c5_value); 	c5_lb = null;  c5_ub = null;  c5_index = null;  c5_value = null;	// Constraints 5
					cplex_wrapper.addRows(c6_lb, c6_ub, c6_index, c6_value); 	c6_lb = null;  c6_ub = null;  c6_index = null;  c6_value = null;	// Constraints 6
					cplex_wrapper.addRows(c12_lb, c12_ub, c12_index, c12_value); 	c12_lb = null;  c12_ub = null;  c12_index = null;  c12_value = null;	// Constraints 12
					cplex_wrapper.addRows(c13_lb, c13_ub, c13_index, c13_value); 	c13_lb = null;  c13_ub = null;  c13_index = null;  c13_value = null;	// Constraints 13
					cplex_wrapper.addRows(c14_lb, c14_ub, c14_index, c14_value); 	c14_lb = null;  c14_ub = null;  c14_index = null;  c14_value = null;	// Constraints 14
					cplex_wrapper.addRows(c15_lb, c15_ub, c15_index, c15_value); 	c15_lb = null;  c15_ub = null;  c15_index = null;  c15_value = null;	// Constraints 15 - Basic Constraints
					cplex_wrapper.addRows(c16_lb, c16_ub, c16_index, c16_value); 	c16_lb = null;  c16_ub = null;  c16_index = null;  c16_value = null;	// Constraints 16 - Flow Constraints
					
					// set up cplex environments
					cplex_wrapper.setup();
					int cplex_total_variables = cplex_wrapper.getNcols();
					int cplex_total_constraints = cplex_wrapper.getNrows();
					
					// solve model
					time_start = System.currentTimeMillis();		// measure time before solving
					model.fireTableDataChanged();
					if (cplex_wrapper.solve()) {
						// get output info after solving & then stop cplex
						if (is_problem_exported) cplex_wrapper.exportModel(problem_file.getAbsolutePath());
						if (is_solution_exported) cplex_wrapper.writeSolution(solution_file.getAbsolutePath());
						double[] value = cplex_wrapper.getValues();
						double[] reduceCost = cplex_wrapper.getReducedCosts();
						double[] dual = cplex_wrapper.getDuals();
						double[] slack = cplex_wrapper.getSlacks();
						double objective_value = cplex_wrapper.getObjValue();
						String cplex_status = cplex_wrapper.getStatus();
						int cplex_algorithm = cplex_wrapper.getAlgorithm();
						long cplex_iteration = cplex_wrapper.getNiterations64();
						cplex_wrapper.end_the_run();
						cplex_wrapper = null;
						time_end = System.currentTimeMillis();		// measure time after solving
						time_solving = (double) (time_end - time_start) / 1000;
						
						
						
						
						// write Solution files
						time_start = System.currentTimeMillis();	// measure time before writing
						
						
						// output_02_variable
						output_variables_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_variables_file))) {
							String file_header = String.join("\t", "var_id", "var_name", "var_value", "var_reduced_cost", "var_total_lr_mean", "var_rd_condition_id");
							fileOut.write(file_header);
							
							for (int i = 0; i < value.length; i++) {
								if (value[i] != 0) {	// only write variable that is not zero
									fileOut.newLine();
									fileOut.write(i + "\t" + vname[i] 
											+ "\t" + Double.valueOf(value[i]) /*Double.valueOf(twoDForm.format(value[i]))*/ 
											+ "\t" + Double.valueOf(reduceCost[i])) /*Double.valueOf(twoDForm.format(reduceCost[i])))*/;
									
									if (vname[i].contains("xNG_") || vname[i].contains("xPB_") || vname[i].contains("xGS_") || vname[i].contains("xMS_") || vname[i].contains("xBS_") || vname[i].contains("xEA_")) {
										double total_loss_rate_mean = 0;
										int s5 = layer5.indexOf(var_info_array[i].get_layer5());
										double[][] loss_rate_mean = (var_rd_condition_id[i] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[i]) : all_zeroes_2D_array;
										for (int k = 0; k < total_replacing_disturbances; k++) {
											total_loss_rate_mean = total_loss_rate_mean + Double.valueOf(loss_rate_mean[k][s5]);
										}
										fileOut.write("\t" + total_loss_rate_mean); 
										fileOut.write("\t" + var_rd_condition_id[i]); 
									} else {
										fileOut.write("\t" + "-9999"); 
										fileOut.write("\t" + "-9999"); 
									}
								}
							}
							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_variables_file) error - "	+ e.getClass().getName() + ": " + e.getMessage());
						}
						output_variables_file.createNewFile();

						
						// output_03_constraints
						output_constraints_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_constraints_file))) {
							String file_header = String.join("\t", "cons_id", "cons_slack", "cons_dual");
							fileOut.write(file_header);
							
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
							System.err.println("Panel Solve Runs - FileWriter(output_constraints_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_constraints_file.createNewFile();

						
						// output_04_management_overview
						output_management_overview_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_management_overview_file))) {
							String file_header = String.join("\t", "iteration", "NG_E", "PB_E", "GS_E", "EA_E", "MS_E",	"BS_E", "NG_R", "PB_R", "GS_R", "EA_R");
							fileOut.write(file_header);
							
							double NG_E_total_area = 0;
							double PB_E_total_area = 0;
							double GS_E_total_area = 0;
							double EA_E_total_area = 0;
							double MS_E_total_area = 0;
							double BS_E_total_area = 0;
							double NG_R_total_area = 0;
							double PB_R_total_area = 0;
							double GS_R_total_area = 0;
							double EA_R_total_area = 0;
							for (int strata_id = 0; strata_id < total_model_strata; strata_id++) {
								// Add sigma(i) xNGe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
								for (int i = 0; i < total_NG_E_prescription_choices; i++) {
									if (xNGe[strata_id][i] != null
											&& xNGe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										int this_var_index = xNGe[strata_id][i][1 + iter];
										NG_E_total_area = NG_E_total_area + Double.valueOf(value[this_var_index]);
									}
								}
						
								// Add sigma(i) xPBe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
								for (int i = 0; i < total_PB_E_prescription_choices; i++) {
									if (xPBe[strata_id][i] != null
											&& xPBe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										int this_var_index = xPBe[strata_id][i][1 + iter];
										PB_E_total_area = PB_E_total_area + Double.valueOf(value[this_var_index]);
									}
								}
								
								// Add xGSe(s1,s2,s3,s4,s5,s6)[i][1 + iter]
								for (int i = 0; i < total_GS_E_prescription_choices; i++) {
									if (xGSe[strata_id][i] != null
											&& xGSe[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
										int this_var_index = xGSe[strata_id][i][1 + iter];
										GS_E_total_area = GS_E_total_area + Double.valueOf(value[this_var_index]);
									}
								}
								
								// Add sigma(tR,s5R)(i) xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][1 + iter]	
								for (int tR = 1 + iter; tR <= total_periods + iter; tR++) {
									for (int s5R = 0; s5R < total_layer5; s5R++) {
										for (int i = 0; i < total_EA_E_prescription_choices; i++) {
											if (xEAe[strata_id][tR] != null 
													&& xEAe[strata_id][tR][s5R] != null
														&& xEAe[strata_id][tR][s5R][i] != null
															&& xEAe[strata_id][tR][s5R][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
												int this_var_index = xEAe[strata_id][tR][s5R][i][1 + iter];
												EA_E_total_area = EA_E_total_area + Double.valueOf(value[this_var_index]);
											}
										}
									}	
								}
								
								// Add sigma(i) xMS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
								if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
									for (int i = 0; i < total_MS_E_prescription_choices; i++) {
										if (xMS[strata_id][i] != null 
												&& xMS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xMS[strata_id][i][1 + iter];
											MS_E_total_area = MS_E_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
								
								// Add sigma(i) xBS(s1,s2,s3,s4,s5,s6)[i][1 + iter]
								if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
									for (int i = 0; i < total_BS_E_prescription_choices; i++) {
										if (xBS[strata_id][i] != null 
												&& xBS[strata_id][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xBS[strata_id][i][1 + iter];
											BS_E_total_area = BS_E_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
							}														
								
							// Add Regenerated variables
							for (int strata_5layers_id = 0; strata_5layers_id < total_model_strata_without_sizeclass; strata_5layers_id++) {
								String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
								int s5 = Collections.binarySearch(layer5, strata_5layers.split("_")[4]);
						
								// xNGr
								for (int i = 0; i < total_NG_R_prescription_choices; i++) {
									int t = 1 + iter;
									for (int a = 1; a <= t - 1; a++) {
										if(xNGr[strata_5layers_id][i] != null
												&& xNGr[strata_5layers_id][i][1 + iter] != null
														&& xNGr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xNGr[strata_5layers_id][i][1 + iter][a];
											NG_R_total_area = NG_R_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
							
								// xPBr
								for (int i = 0; i < total_PB_R_prescription_choices; i++) {
									int t = 1 + iter;
									for (int a = 1; a <= t - 1; a++) {
										if(xPBr[strata_5layers_id][i] != null
												&& xPBr[strata_5layers_id][i][1 + iter] != null
														&& xPBr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xPBr[strata_5layers_id][i][1 + iter][a];
											PB_R_total_area = PB_R_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
								
								// xGSr
								for (int i = 0; i < total_GS_R_prescription_choices; i++) {
									int t = 1 + iter;
									for (int a = 1; a <= t - 1; a++) {
										if(xGSr[strata_5layers_id][i] != null
												&& xGSr[strata_5layers_id][i][1 + iter] != null
														&& xGSr[strata_5layers_id][i][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xGSr[strata_5layers_id][i][1 + iter][a];
											GS_R_total_area = GS_R_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
								
								// xEAr
								int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
								for (int tR = t_regen + iter; tR <= total_periods + iter; tR++) {
									for (int aR = 1; aR <= tR-1; aR++) {									
										for (int s5R = 0; s5R < total_layer5; s5R++) {
											for (int i = 0; i < total_EA_R_prescription_choices; i++) {
												if(xEAr[strata_5layers_id][tR] != null
														 && xEAr[strata_5layers_id][tR][aR] != null
																 && xEAr[strata_5layers_id][tR][aR][s5R] != null
																		 && xEAr[strata_5layers_id][tR][aR][s5R][i] != null
																				 && xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
													int this_var_index = xEAr[strata_5layers_id][tR][aR][s5R][i][1 + iter];
													EA_R_total_area = EA_R_total_area + Double.valueOf(value[this_var_index]);
												}
											}
										}
									}
								}
							}
							
							fileOut.write("\n" + iter);
							fileOut.write("\t" + NG_E_total_area /*Double.valueOf(twoDForm.format(NG_E_total_area))*/);
							fileOut.write("\t" + PB_E_total_area /*Double.valueOf(twoDForm.format(PB_E_total_area))*/);
							fileOut.write("\t" + GS_E_total_area /*Double.valueOf(twoDForm.format(GS_E_total_area))*/);
							fileOut.write("\t" + EA_E_total_area /*Double.valueOf(twoDForm.format(EA_E_total_area))*/);
							fileOut.write("\t" + MS_E_total_area /*Double.valueOf(twoDForm.format(MS_E_total_area))*/);
							fileOut.write("\t" + BS_E_total_area /*Double.valueOf(twoDForm.format(BS_E_total_area))*/);
							fileOut.write("\t" + NG_R_total_area /*Double.valueOf(twoDForm.format(NG_R_total_area))*/);
							fileOut.write("\t" + PB_R_total_area /*Double.valueOf(twoDForm.format(PB_R_total_area))*/);
							fileOut.write("\t" + GS_R_total_area /*Double.valueOf(twoDForm.format(GS_R_total_area))*/);
							fileOut.write("\t" + EA_R_total_area /*Double.valueOf(twoDForm.format(EA_R_total_area))*/);
								
							fileOut.close();
							xNGe = null;			// Clear arrays not used any more
							xPBe = null;			// Clear arrays not used any more
							xGSe = null;			// Clear arrays not used any more
							xEAe = null;			// Clear arrays not used any more
							xMS = null;				// Clear arrays not used any more
							xBS = null;				// Clear arrays not used any more
							xNGr = null;			// Clear arrays not used any more
							xPBr = null;			// Clear arrays not used any more
							xGSr = null;			// Clear arrays not used any more
							xEAr = null;			// Clear arrays not used any more
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_management_overview_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_management_overview_file.createNewFile();
						
						
						// output_05_management_details
						output_management_details_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_management_details_file))) {
							fileOut.write("var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost" + "\t");
							
							for (int k = 0; k < total_replacing_disturbances; k++) {
								int disturbance_index = k + 1;
					        	String disturbance_name = (disturbance_index < 10) ? ("percentage_SR_0" + disturbance_index) : "percentage_SR_" + disturbance_index;
					        	fileOut.write(disturbance_name + "\t");
					        }
							
							fileOut.write("var_unit_management_cost" + "\t");
							fileOut.write("var_method" + "\t" + "var_forest_status" + "\t" + "var_layer1" + "\t" + "var_layer2" + "\t" + "var_layer3" + "\t" + "var_layer4" + "\t" + "var_layer5" + "\t" + "var_layer6" + "\t" 
									+ "var_choice" + "\t" + "var_period" + "\t" + "var_age" + "\t" + "var_rotation_period" + "\t" + "var_rotation_age" + "\t" + "var_regen_covertype" + "\t"
									+ "data_connection" + "\t" + "prescription" + "\t" + "row_id");
//							for (int col = 2; col < yield_tables_column_names.length; col++) {		// do not write prescription & row_id column header
//								fileOut.write("\t" + yield_tables_column_names[col]);
//							}
							
							
							
							for (int i = 0; i < value.length; i++) {
								if (value[i] != 0 && (vname[i].contains("xNG_") || vname[i].contains("xPB_") || vname[i].contains("xGS_") || vname[i].contains("xMS_") || vname[i].contains("xBS_") || vname[i].contains("xEA_"))) {
									String prescription_name_to_find = var_info_array[i].get_yield_table_name_to_find();
									int[] prescription_and_row = var_info_array[i].get_prescription_id_and_row_id();
									int var_prescription_id = prescription_and_row[0];
									int var_row_id = prescription_and_row[1];
									

									String data_connection = "good";
									if (var_prescription_id == -9999) {
										data_connection = "missing yield table";
									} else {
										if (yield_tables_values[var_prescription_id].length <= var_row_id) {
											data_connection = "missing row id = " + var_row_id;
										}
									}

									fileOut.newLine();
									fileOut.write(i + "\t" + vname[i] 
											+ "\t" + Double.valueOf(value[i] /*Double.valueOf(twoDForm.format(value[i])*/)
											+ "\t" + Double.valueOf(reduceCost[i + 1])); /*Double.valueOf(twoDForm.format(reduceCost[i]))*/ 	// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
									
									int s5 = layer5.indexOf(var_info_array[i].get_layer5());
									double[][] loss_rate_mean = (var_rd_condition_id[i] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[i]) : all_zeroes_2D_array;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										fileOut.write("\t" + Double.valueOf(loss_rate_mean[k][s5]));
									}
									fileOut.write("\t" + Double.valueOf(var_cost_value[i]));
									
									fileOut.write("\t" + var_info_array[i].get_method() + "\t" + var_info_array[i].get_forest_status()
											+ "\t" + var_info_array[i].get_layer1() + "\t" + var_info_array[i].get_layer2()
											+ "\t" + var_info_array[i].get_layer3() + "\t" + var_info_array[i].get_layer4()
											+ "\t" + var_info_array[i].get_layer5() + "\t" + var_info_array[i].get_layer6()
											+ "\t" + var_info_array[i].get_timing_choice() + "\t" + var_info_array[i].get_period()
											+ "\t" + String.valueOf(var_info_array[i].get_age()).replace("-9999",  "") 
											+ "\t" + String.valueOf(var_info_array[i].get_rotation_period()).replace("-9999",  "") 
											+ "\t" + String.valueOf(var_info_array[i].get_rotation_age()).replace("-9999",  "") 
											+ "\t" + var_info_array[i].get_regenerated_covertype()
											+ "\t" + data_connection + "\t" + prescription_name_to_find + "\t" + var_row_id);
//									for (int col = 2; col < yield_tables_column_names.length; col++) {		// do not write prescription & row_id in the yield_tables
//										if (data_connection.equals("good")) {
//											fileOut.write("\t" + yield_tables_values[var_prescription_id][var_row_id][col]);
//										} else {
//											fileOut.write("\t" + "");
//										}
//									}
								}
							}
							fileOut.close();
							var_cost_value = null;			// Clear arrays not used any more
							var_rd_condition_id = null;		// Clear arrays not used any more
							disturbance_info = null;		// Clear arrays not used any more
							var_info_array = null;			// Clear arrays not used any more
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_management_details_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_management_details_file.createNewFile();
						
						// create a table inside the database.db
						SQLite.import_file_as_table_into_database(output_management_details_file, file_database);
						
						// fly_constraints --> don't need to create this file. Just clear query_value if this file exists
						clear_query_value_for_fly_constraints(output_fly_constraints_file);		
						
						
						// output_06_basic_constraints
						if (total_freeConstraints + total_softConstraints + total_hardConstraints > 0) {		// write basic constraints if there is at least a constraint set up
							output_basic_constraints_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_basic_constraints_file))) {
								String file_header = String.join("\t", "bc_id", "bc_description", "bc_type", "bc_multiplier",
										"lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty",
										"var_id", "var_name", "var_value", "var_reduced_cost", "total_penalty");
								fileOut.write(file_header);
		
								current_freeConstraint = 0;
								current_softConstraint = 0;
								current_hardConstraint = 0;	
								int constraint_type_col = constraint_column_names_list.indexOf("bc_type");	
								int lowerbound_col = constraint_column_names_list.indexOf("lowerbound");
								int lowerbound_perunit_penalty_col = constraint_column_names_list.indexOf("lowerbound_perunit_penalty");
								int upperbound_col = constraint_column_names_list.indexOf("upperbound");
								int upperbound_perunit_penalty_col = constraint_column_names_list.indexOf("upperbound_perunit_penalty");
								
								for (int i = 1; i < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; i++) {	// loop from 1 because the first row of the Basic Constraints file is just title												
									fileOut.newLine();
									for (int j = 0; j < 8; j++) { 	// just print the first 7 columns of basic constraints
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
										double upperbound = (!bc_values[i][upperbound_col].equals("null")) ? Double.parseDouble(bc_values[i][upperbound_col]) : Double.MAX_VALUE;
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
								System.err.println("Panel Solve Runs - FileWriter(output_basic_constraints_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_basic_constraints_file.createNewFile();					
						}							
						
						
						// output_07_flow_constraints 					
						if (flow_set_list.size() > 0) {		// write flow constraints if there is at least a flow set
							output_flow_constraints_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_flow_constraints_file))) {
								String file_header = String.join("\t", "iteration", "flow_id", "flow_description", "flow_arrangement", "flow_type", "lowerbound_percentage", "upperbound_percentage", "flow_output_original");
								fileOut.write(file_header);
								
								// add constraints for each flow set
								for (int i = 0; i < flow_set_list.size(); i++) {		// loop each flow set (or each row of the flow_constraints_table)								
									String temp = iter + "\t" + flow_id_list.get(i) + "\t" + flow_description_list.get(i) + "\t"
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
									temp = temp.substring(0, temp.length() - 1) + "\t";	// remove the last ; and add a tab									
									temp = temp.substring(0, temp.length() - 1);		// remove the last ;								
									
									// write the whole line
									fileOut.newLine();
									fileOut.write(temp);
								}
								fileOut.close();
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - FileWriter(output__flow_constraints_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_flow_constraints_file.createNewFile();
						}
						
						
						time_end = System.currentTimeMillis();		// measure time after writing
						time_writing = (double) (time_end - time_start) / 1000;
						
						
						// output_01_general input (write at the end since we need writing time)
						output_general_outputs_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_general_outputs_file))) {
							// Write variables info
							fileOut.write("iteration" + "\t" + "description" + "\t" + "value");

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Optimization solver" + "\t" + "CPLEX");

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Solution status" + "\t" + cplex_status);

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Solution algorithm" + "\t" + cplex_algorithm);

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Simplex iterations" + "\t" + cplex_iteration);
							
							fileOut.newLine();
							fileOut.write(iter + "\t" + "Prism version when problem solved" + "\t" + PrismMain.get_prism_version());
							
							fileOut.newLine();
							fileOut.write(iter + "\t" + "Date & time problem solved" + "\t" + dateFormat.format(new Date()));
							
							fileOut.newLine();
							if ((int) (time_reading / 60) == 0) {
								fileOut.write(iter + "\t" + "Time reading (minutes & seconds)" + "\t" + Double.valueOf(twoDForm.format(time_reading % 60)) + "s");
							} else {
								fileOut.write(iter + "\t" + "Time reading (minutes & seconds)" + "\t" + (int) (time_reading / 60) + "m" + Double.valueOf(twoDForm.format(time_reading % 60)) + "s");
							}
										
							fileOut.newLine();
							if ((int) (time_solving / 60) == 0) {
								fileOut.write(iter + "\t" + "Time solving (minutes & seconds)" + "\t" + Double.valueOf(twoDForm.format(time_solving % 60)) + "s");
							} else {
								fileOut.write(iter + "\t" + "Time solving (minutes & seconds)" + "\t" + (int) (time_solving / 60) + "m" + Double.valueOf(twoDForm.format(time_solving % 60)) + "s");
							}
							
							fileOut.newLine();
							if ((int) (time_writing / 60) == 0) {
								fileOut.write(iter + "\t" + "Time writing (minutes & seconds)" + "\t" + Double.valueOf(twoDForm.format(time_writing % 60)) + "s");
							} else {
								fileOut.write(iter + "\t" + "Time writing (minutes & seconds)" + "\t" + (int) (time_writing / 60) + "m" + Double.valueOf(twoDForm.format(time_writing % 60)) + "s");	
							}

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Total variables" + "\t" + cplex_total_variables);

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Total constraints" + "\t" + cplex_total_constraints);

							fileOut.newLine();
							fileOut.write(iter + "\t" + "Objective value" + "\t" + Double.valueOf(twoDForm.format(objective_value)));

							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_generalInfo_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_general_outputs_file.createNewFile();
						
						
						// show successful or fail in the GUI
						data[row][3] = "successful";
						model.fireTableDataChanged();
						value = null; reduceCost = null; dual = null; slack = null;		// clear arrays to save memory
						vlb = null; vub = null; vname = null; objvals = null;			// clear arrays to save memory
					} else {
						if (is_problem_exported) cplex_wrapper.exportModel(problem_file.getAbsolutePath());
						data[row][3] = "fail";
						model.fireTableDataChanged();
					}
				}
				
		
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if (solver_for_optimization.equals("LPSOLVE")) {				//Reference for all LPsolve classes here:		http://lpsolve.sourceforge.net/5.5/Java/docs/api/lpsolve/LpSolve.html
					// Add the LPsolve native library path dynamically at run time
					try {
//						LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	// This will clear all other paths (many) and set path to the "temporary" folder
						LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	// This will NOT clear all other paths, and will add the "temporary" folder to the paths
						System.out.println(FilesHandle.get_temporaryFolder().getAbsolutePath().toString() + " has been added to the java library paths");	
						
						System.out.println("Below is all the java library paths Prism found:");
						String property = System.getProperty("java.library.path");
						StringTokenizer parser = new StringTokenizer(property, ";");
						while (parser.hasMoreTokens()) {
							System.out.println("           - " + parser.nextToken());
						}

						try {	// add lpsolve55.dll, this is an important dependent associated with lpsolve55j.dll that will have to be loaded manually (only lpsolve55j.dll is auto loaded when set path to Temporary folder)
							System.loadLibrary("lpsolve55");
							System.out.println("lpsolve55.dll has been succesfully loaded");	
						} catch (UnsatisfiedLinkError e) {
							System.err.println("Native code library failed to load.\n" + e);
						}
					} catch (Exception e) {
						System.err.println("Panel Solve Runs - addLibraryPath error - " + e.getClass().getName() + ": " + e.getMessage());
					}
					
					// Create a problem with nvars variables and 0 constraints
					LpSolve solver = LpSolve.makeLp(0, nvars);
				    solver.setVerbose(LpSolve.NEUTRAL); //set verbose level
			        solver.setMinim(); //set the problem to minimization
			        
			        
			        
			        
//			        //---------------------------------------------------------------------------
//			        //---------------------------------------------------------------------------
//			        // From other people set up, I dont understand but I think it can speed up the solving process: no speed improved
//			        solver.setScalelimit(5); 
	//
//			        solver.setPivoting(LpSolve.PRICER_DEVEX + 
//			        LpSolve.PRICE_ADAPTIVE ); 
//			        solver.setMaxpivot(250); 
	//
//			        solver.setBbFloorfirst(LpSolve.BRANCH_AUTOMATIC); 
//			        solver.setBbRule(LpSolve.NODE_PSEUDONONINTSELECT + 
//			        LpSolve.NODE_GREEDYMODE + LpSolve.NODE_DYNAMICMODE + 
//			        LpSolve.NODE_RCOSTFIXING); 
//			        solver.setObjBound(1E30); 
//			        solver.setBbDepthlimit(-50); 
	//
//			        solver.setImprove(LpSolve.IMPROVE_DUALFEAS + 
//			        LpSolve.IMPROVE_THETAGAP); 
	//
//			        solver.setSimplextype(LpSolve.SIMPLEX_DUAL_PRIMAL); 
//			        //---------------------------------------------------------------------------
//			        //---------------------------------------------------------------------------
			        
			        

			        
			        // Set objective function coefficients
			        solver.setObjFn(pad1ZeroInfront(objvals));		      
					
//			        // Set scaling			//Note this make outputs change
//			        solver.setScaling(LpSolve.SCALE_NONE);				//reference here:	http://lpsolve.sourceforge.net/5.5/set_scaling.htm
			        
//			        // Set preSolve:		Eliminate linearly dependent rows = LpSolve.PRESOLVE_LINDEP  (value int = 4), option 1 can reduce iteration --> solve faster
//			        solver.setPresolve(LpSolve.PRESOLVE_LINDEP, solver.getPresolveloops());		//reference here:	http://lpsolve.sourceforge.net/5.5/set_presolve.htm
			        
			        
			        for (int i = 0; i < nvars; ++i) {
						solver.setColName(i + 1, vname[i]); // Set variable name		// plus one is required by the lib
						solver.setBounds(i + 1, vlb[i], vub[i]); 						// plus one is required by the lib
					}
			      	        
//			        // lower bounds for the variables	      
//					for (int i = 0; i < nvars; ++i) {
//						solver.setLowbo(i + 1, vlb[i]); // plus one is required by the lib
//					}
//			      		         
//			        // upper bounds for the variables
//					for (int i = 0; i < nvars; ++i) {
//						solver.setUpbo(i + 1, vub[i]); // plus one is required by the lib
//					}
			         
			        
			        // Allocate memory in advance to add constraints & variables faster		Reference:	http://lpsolve.sourceforge.net/5.5/resize_lp.htm
					int totalConstraints = c2_lb.length + c5_lb.length + c6_lb.length + c12_lb.length + c13_lb.length + c14_lb.length + c15_lb.length + c16_lb.length;
			        solver.resizeLp(totalConstraints, solver.getNcolumns());
			        
			        
					
			        // Add constraints:			//	The sign of the constraint: LE (1) for <=, EQ (3) for =, GE (2) for >=
												//	addConstraint(parameters, sign, RHS)
			        // Use addConstraintex because it is much more faster than addConstraint.		Reference:		http://lpsolve.sourceforge.net/5.5/add_constraint.htm
			        solver.setAddRowmode(true);
			        
			        // Constraints 2   
					for (int i = 0; i < c2_num; ++i) {
						if (c2_lb[i] != -Double.MAX_VALUE) {			
							solver.addConstraintex(c2_value[i].length, c2_value[i], plus1toIndex(c2_index[i]), LpSolve.GE, c2_lb[i]);
						}
					}	
					// Constraints 2   
					for (int i = 0; i < c2_num; ++i) {
						if (c2_ub[i] != Double.MAX_VALUE) {
							solver.addConstraintex(c2_value[i].length, c2_value[i], plus1toIndex(c2_index[i]), LpSolve.LE, c2_ub[i]);
						}			
					}	
					// Constraints 5  
					for (int i = 0; i < c5_num; ++i) {
						solver.addConstraintex(c5_value[i].length, c5_value[i], plus1toIndex(c5_index[i]), LpSolve.EQ, c5_lb[i]);
					}		
					// Constraints 6   
					for (int i = 0; i < c6_num; ++i) {
						solver.addConstraintex(c6_value[i].length, c6_value[i], plus1toIndex(c6_index[i]), LpSolve.EQ, c6_lb[i]);
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
					// Constraints 16
					for (int i = 0; i < c16_num; ++i) {
						solver.addConstraintex(c16_value[i].length, c16_value[i], plus1toIndex(c16_index[i]), LpSolve.EQ, c16_lb[i]);
					}	
					solver.setAddRowmode(false);	//perform much better addConstraintex-----------------------------------------------------------
					
			        
					
					
					// Add table info
					int lpsolve_total_variables = solver.getNcolumns();
					int lpsolve_total_constraints = solver.getNrows();
					
					// solve model
					time_start = System.currentTimeMillis();		// measure time before solving
					model.fireTableDataChanged();
					
			        
//			        // solve the problem
//			        int status = solver.solve();
//			        if(isSolutionValid(status)==false) {
//			            solver.setScaling(LpSolve.SCALE_NONE); //turn off automatic scaling
//			            status = solver.solve();
//			            if(isSolutionValid(status)==false) {
//			                throw new RuntimeException("LPSolver Error: "+solver.getStatustext(status));
//						}
//					}	        
			        
			        // solve the problem
//					solver.writeLp(problemFile[row].getAbsolutePath());
					if (isSolutionValid(solver.solve()) == true) {
						// get output info after solving & then stop cplex
						if (is_problem_exported) solver.writeLp(problem_file.getAbsolutePath());
						if (is_solution_exported) {
							solver.setOutputfile(solution_file.getAbsolutePath());
							solver.printObjective();
							solver.printSolution(nvars);
							solver.printDuals();
						}
						// Get output info to array
						double[] value = solver.getPtrVariables();
						double[] reduceCost = solver.getPtrDualSolution();
//						double[] dual = solver.getPtrDualSolution();
//						double[] slack = cplex.getSlacks(lp);
						
						double objective_value = solver.getObjective();
						int lpsolve_status = solver.getStatus();
						int lpsolve_algorithm = solver.getSimplextype();
						long lpsolve_iteration = solver.getTotalIter();
						time_end = System.currentTimeMillis();		// measure time after solving
						time_solving = (double) (time_end - time_start) / 1000;

						
						
						
						// write Solution files
						time_start = System.currentTimeMillis();	// measure time before writing

						
						// output_02_variable
						output_variables_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_variables_file))) {
							String file_header = String.join("\t", "var_id", "var_name", "var_value", "var_reduced_cost", "var_total_lr_mean", "var_rd_condition_id");
							fileOut.write(file_header);
							
							for (int i = 0; i < value.length; i++) {
								if (value[i] != 0) {	// only write variable that is not zero
									fileOut.newLine();
									fileOut.write(i + "\t" + vname[i] 
											+ "\t" + Double.valueOf(value[i]) /*Double.valueOf(twoDForm.format(value[i]))*/ 
											+ "\t" + Double.valueOf(reduceCost[i + 1])) /*Double.valueOf(twoDForm.format(reduceCost[i])))*/;			// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
									
									if (vname[i].contains("xNG_") || vname[i].contains("xPB_") || vname[i].contains("xGS_") || vname[i].contains("xMS_") || vname[i].contains("xBS_") || vname[i].contains("xEA_")) {
										double total_loss_rate_mean = 0;
										int s5 = layer5.indexOf(var_info_array[i].get_layer5());
										double[][] loss_rate_mean = (var_rd_condition_id[i] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[i]) : all_zeroes_2D_array;
										for (int k = 0; k < total_replacing_disturbances; k++) {
											total_loss_rate_mean = total_loss_rate_mean + Double.valueOf(loss_rate_mean[k][s5]);
										}
										fileOut.write("\t" + total_loss_rate_mean); 
										fileOut.write("\t" + var_rd_condition_id[i]); 
									} else {
										fileOut.write("\t" + "-9999"); 
										fileOut.write("\t" + "-9999"); 
									}
								}
							}
							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_variables_file) error - "	+ e.getClass().getName() + ": " + e.getMessage());
						}
						output_variables_file.createNewFile();

						
						// output_03_constraints
						output_constraints_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_constraints_file))) {
							String file_header = String.join("\t", "cons_id", "cons_slack", "cons_dual");
							fileOut.write(file_header);
							
//							for (int j = 0; j < dual.length; j++) {
//								if (slack[j] != 0 || dual[j] != 0) {
//									fileOut.newLine();
//									fileOut.write(j 
//											+ "\t" + Double.valueOf(slack[j]) /*Double.valueOf(twoDForm.format(slack[j]))*/ 
//											+ "\t" + Double.valueOf(dual[j])) /*Double.valueOf(twoDForm.format(dual[j])))*/;
//								}
//							}
							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_constraints_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_constraints_file.createNewFile();

						
						// output_04_management_overview
						output_management_overview_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_management_overview_file))) {
							String file_header = String.join("\t", "strata_id", "layer1", "layer2", "layer3", "layer4", "layer5", "layer6", "NG_E_acres", "PB_E_acres", "GS_E_acres", "EA_E_acres", "MS_E_acres", "BS_E_acres");
							fileOut.write(file_header);
							
							for (String strata: model_strata) {
								String[] strata_layer = strata.split("_");
								int strata_id = Collections.binarySearch(model_strata, strata);
								int s1 = Collections.binarySearch(layer1, strata_layer[0]);
								int s2 = Collections.binarySearch(layer2, strata_layer[1]);
								int s3 = Collections.binarySearch(layer3, strata_layer[2]);
								int s4 = Collections.binarySearch(layer4, strata_layer[3]);
								int s5 = Collections.binarySearch(layer5, strata_layer[4]);
								int s6 = Collections.binarySearch(layer6, strata_layer[5]);	
								// new line for each stratum
								fileOut.newLine();
								// write StrataID and 6 layers info
								fileOut.write(strata + "\t" + layer1.get(s1) + "\t" + layer2.get(s2) + "\t" + layer3.get(s3) + "\t" + layer4.get(s4) + "\t" + layer5.get(s5) + "\t" + layer6.get(s6));
								// write acres from each method

								// Add sigma(i) xNGe(s1,s2,s3,s4,s5,s6)[i][1]
								double NG_E_total_area = 0;
								for (int i = 0; i < total_NG_E_prescription_choices; i++) {
									if (xNGe[strata_id][i] != null
											&& xNGe[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
										int this_var_index = xNGe[strata_id][i][1];
										NG_E_total_area = NG_E_total_area + Double.valueOf(value[this_var_index]);
									}
								}
								fileOut.write("\t" + NG_E_total_area /*Double.valueOf(twoDForm.format(NG_E_total_area))*/);
								
								// Add sigma(i) xPBe(s1,s2,s3,s4,s5,s6)[i][1]
								double PB_E_total_area = 0;
								for (int i = 0; i < total_PB_E_prescription_choices; i++) {
									if (xPBe[strata_id][i] != null
											&& xPBe[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
										int this_var_index = xPBe[strata_id][i][1];
										PB_E_total_area = PB_E_total_area + Double.valueOf(value[this_var_index]);
									}
								}
								fileOut.write("\t" + PB_E_total_area /*Double.valueOf(twoDForm.format(PB_E_total_area))*/);
								
								// Add sigma(i) xGSe(s1,s2,s3,s4,s5,s6)[i][1]
								double GS_E_total_area = 0;
								for (int i = 0; i < total_GS_E_prescription_choices; i++) {
									if (xGSe[strata_id][i] != null
											&& xGSe[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
										int this_var_index = xGSe[strata_id][i][1];
										GS_E_total_area = GS_E_total_area + Double.valueOf(value[this_var_index]);
									}
								}
								fileOut.write("\t" + GS_E_total_area /*Double.valueOf(twoDForm.format(GS_E_total_area))*/);
								
								double EA_E_total_area = 0;
								// Add sigma(tR,s5R)(i) xEAe(s1,s2,s3,s4,s5,s6)[tR][s5R][i][1]	
								for (int tR = 1; tR <= total_periods; tR++) {
									for (int s5R = 0; s5R < total_layer5; s5R++) {
										for (int i = 0; i < total_EA_E_prescription_choices; i++) {
											if (xEAe[strata_id][tR] != null 
													&& xEAe[strata_id][tR][s5R] != null
														&& xEAe[strata_id][tR][s5R][i] != null
															&& xEAe[strata_id][tR][s5R][i][1] > 0) {		// if variable is defined, this value would be > 0 
												int this_var_index = xEAe[strata_id][tR][s5R][i][1];
												EA_E_total_area = EA_E_total_area + Double.valueOf(value[this_var_index]);
											}
										}
									}	
								}
								fileOut.write("\t" + EA_E_total_area /*Double.valueOf(twoDForm.format(EA_E_total_area))*/);
								
								// Add sigma(i) xMS(s1,s2,s3,s4,s5,s6)[i][1]
								double MS_E_total_area = 0;
								if (xMS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
									for (int i = 0; i < total_MS_E_prescription_choices; i++) {
										if (xMS[strata_id][i] != null 
												&& xMS[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xMS[strata_id][i][1];
											MS_E_total_area = MS_E_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
								fileOut.write("\t" + MS_E_total_area /*Double.valueOf(twoDForm.format(MS_E_total_area))*/);
								
								// Add sigma(i) xBS(s1,s2,s3,s4,s5,s6)[i][1]
								double BS_E_total_area = 0;
								if (xBS[strata_id] != null) {		// only MS_E and BS_E might have null at this point and we need to check
									for (int i = 0; i < total_BS_E_prescription_choices; i++) {
										if (xBS[strata_id][i] != null 
												&& xBS[strata_id][i][1] > 0) {		// if variable is defined, this value would be > 0 
											int this_var_index = xBS[strata_id][i][1];
											BS_E_total_area = BS_E_total_area + Double.valueOf(value[this_var_index]);
										}
									}
								}
								fileOut.write("\t" + BS_E_total_area /*Double.valueOf(twoDForm.format(BS_E_total_area))*/);
							}												
							fileOut.close();
							xNGe = null;			// Clear arrays not used any more
							xPBe = null;			// Clear arrays not used any more
							xGSe = null;			// Clear arrays not used any more
							xEAe = null;			// Clear arrays not used any more
							xMS = null;			// Clear arrays not used any more
							xBS = null;			// Clear arrays not used any more
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_management_overview_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_management_overview_file.createNewFile();

						
						// output_05_management_details
						output_management_details_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_management_details_file))) {
							fileOut.write("var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost" + "\t");
							
							for (int k = 0; k < total_replacing_disturbances; k++) {
								int disturbance_index = k + 1;
					        	String disturbance_name = (disturbance_index < 10) ? ("percentage_SR_0" + disturbance_index) : "percentage_SR_" + disturbance_index;
					        	fileOut.write(disturbance_name + "\t");
					        }
							
							fileOut.write("var_unit_management_cost" + "\t");
							fileOut.write("var_method" + "\t" + "var_forest_status" + "\t" + "var_layer1" + "\t" + "var_layer2" + "\t" + "var_layer3" + "\t" + "var_layer4" + "\t" + "var_layer5" + "\t" + "var_layer6" + "\t" 
									+ "var_choice" + "\t" + "var_period" + "\t" + "var_age" + "\t" + "var_rotation_period" + "\t" + "var_rotation_age" + "\t" + "var_regen_covertype" + "\t"
									+ "data_connection" + "\t" + "prescription" + "\t" + "row_id");
//							for (int col = 2; col < yield_tables_column_names.length; col++) {		// do not write prescription & row_id column header
//								fileOut.write("\t" + yield_tables_column_names[col]);
//							}
							
							
							
							for (int i = 0; i < value.length; i++) {
								if (value[i] != 0 && (vname[i].contains("xNG_") || vname[i].contains("xPB_") || vname[i].contains("xGS_") || vname[i].contains("xMS_") || vname[i].contains("xBS_") || vname[i].contains("xEA_"))) {
									String prescription_name_to_find = var_info_array[i].get_yield_table_name_to_find();
									int[] prescription_and_row = var_info_array[i].get_prescription_id_and_row_id();
									int var_prescription_id = prescription_and_row[0];
									int var_row_id = prescription_and_row[1];
									

									String data_connection = "good";
									if (var_prescription_id == -9999) {
										data_connection = "missing yield table";
									} else {
										if (yield_tables_values[var_prescription_id].length <= var_row_id) {
											data_connection = "missing row id = " + var_row_id;
										}
									}

									fileOut.newLine();
									fileOut.write(i + "\t" + vname[i] 
											+ "\t" + Double.valueOf(value[i] /*Double.valueOf(twoDForm.format(value[i])*/)
											+ "\t" + Double.valueOf(reduceCost[i + 1])); /*Double.valueOf(twoDForm.format(reduceCost[i]))*/ 	// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
									
									int s5 = layer5.indexOf(var_info_array[i].get_layer5());
									double[][] loss_rate_mean = (var_rd_condition_id[i] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[i]) : all_zeroes_2D_array;
									for (int k = 0; k < total_replacing_disturbances; k++) {
										fileOut.write("\t" + Double.valueOf(loss_rate_mean[k][s5]));
									}
									fileOut.write("\t" + Double.valueOf(var_cost_value[i]));
									
									fileOut.write("\t" + var_info_array[i].get_method() + "\t" + var_info_array[i].get_forest_status()
											+ "\t" + var_info_array[i].get_layer1() + "\t" + var_info_array[i].get_layer2()
											+ "\t" + var_info_array[i].get_layer3() + "\t" + var_info_array[i].get_layer4()
											+ "\t" + var_info_array[i].get_layer5() + "\t" + var_info_array[i].get_layer6()
											+ "\t" + var_info_array[i].get_timing_choice() + "\t" + var_info_array[i].get_period()
											+ "\t" + String.valueOf(var_info_array[i].get_age()).replace("-9999",  "") 
											+ "\t" + String.valueOf(var_info_array[i].get_rotation_period()).replace("-9999",  "") 
											+ "\t" + String.valueOf(var_info_array[i].get_rotation_age()).replace("-9999",  "") 
											+ "\t" + var_info_array[i].get_regenerated_covertype()
											+ "\t" + data_connection + "\t" + prescription_name_to_find + "\t" + var_row_id);
//									for (int col = 2; col < yield_tables_column_names.length; col++) {		// do not write prescription & row_id in the yield_tables
//										if (data_connection.equals("good")) {
//											fileOut.write("\t" + yield_tables_values[var_prescription_id][var_row_id][col]);
//										} else {
//											fileOut.write("\t" + "");
//										}
//									}
								}
							}
							fileOut.close();
							var_cost_value = null;			// Clear arrays not used any more
							var_rd_condition_id = null;		// Clear arrays not used any more
							disturbance_info = null;		// Clear arrays not used any more
							var_info_array = null;			// Clear arrays not used any more
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_management_details_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_management_details_file.createNewFile();
						
						// create a table inside the database.db
						SQLite.import_file_as_table_into_database(output_management_details_file, file_database);
						
						// fly_constraints --> don't need to create this file. Just clear query_value if this file exists
						clear_query_value_for_fly_constraints(output_fly_constraints_file);		
						
						
						// output_06_basic_constraints
						if (total_freeConstraints + total_softConstraints + total_hardConstraints > 0) {		// write basic constraints if there is at least a constraint set up
							output_basic_constraints_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_basic_constraints_file))) {
								String file_header = String.join("\t", "bc_id", "bc_description", "bc_type", "bc_multiplier",
										"lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty",
										"var_id", "var_name", "var_value", "var_reduced_cost", "total_penalty");
								fileOut.write(file_header);
		
								current_freeConstraint = 0;
								current_softConstraint = 0;
								current_hardConstraint = 0;	
								int constraint_type_col = constraint_column_names_list.indexOf("bc_type");	
								int lowerbound_col = constraint_column_names_list.indexOf("lowerbound");
								int lowerbound_perunit_penalty_col = constraint_column_names_list.indexOf("lowerbound_perunit_penalty");
								int upperbound_col = constraint_column_names_list.indexOf("upperbound");
								int upperbound_perunit_penalty_col = constraint_column_names_list.indexOf("upperbound_perunit_penalty");
								
								for (int i = 1; i < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; i++) {	// loop from 1 because the first row of the Basic Constraints file is just title												
									fileOut.newLine();
									for (int j = 0; j < 8; j++) { 	// just print the first 7 columns of basic constraints
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
										double upperbound = (!bc_values[i][upperbound_col].equals("null")) ? Double.parseDouble(bc_values[i][upperbound_col]) : Double.MAX_VALUE;
										double upperbound_perunit_penalty = (!bc_values[i][upperbound_perunit_penalty_col].equals("null")) ? Double.parseDouble(bc_values[i][upperbound_perunit_penalty_col]) : Double.MAX_VALUE;
										
										if (lowerbound_perunit_penalty != 0 && value[var_id] < lowerbound) {
											total_penalty = (lowerbound - value[var_id]) * lowerbound_perunit_penalty;
										}
										
										if (upperbound_perunit_penalty != 0 && value[var_id] > upperbound) {
											total_penalty = (value[var_id] - upperbound) * upperbound_perunit_penalty;
										}	
									}
		
									fileOut.write(var_id + "\t" + vname[var_id] + "\t" + value[var_id]  + "\t" + reduceCost[var_id + 1] + "\t" + total_penalty);		// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
								}
								fileOut.close();
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - FileWriter(output_basic_constraints_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_basic_constraints_file.createNewFile();					
						}							
						
						
						// output_07_flow_constraints 					
						if (flow_set_list.size() > 0) {		// write flow constraints if there is at least a flow set
							output_flow_constraints_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_flow_constraints_file))) {
								String file_header = String.join("\t", "flow_id", "flow_description", "flow_arrangement", "flow_type", "lowerbound_percentage", "upperbound_percentage", "flow_output_original");
								fileOut.write(file_header);
								
								// add constraints for each flow set
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
									temp = temp.substring(0, temp.length() - 1) + "\t";	// remove the last ; and add a tab									
									temp = temp.substring(0, temp.length() - 1);		// remove the last ;								
									
									// write the whole line
									fileOut.newLine();
									fileOut.write(temp);
								}
								fileOut.close();
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - FileWriter(output__flow_constraints_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_flow_constraints_file.createNewFile();
						}
						
						
						time_end = System.currentTimeMillis();		// measure time after writing
						time_writing = (double) (time_end - time_start) / 1000;
						
						
						// output_01_general input (write at the end since we need writing time)
						output_general_outputs_file.delete();
						try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_general_outputs_file))) {
							// Write variables info
							fileOut.write("description" + "\t" + "value");

							fileOut.newLine();
							fileOut.write("Optimization solver" + "\t" + "LPSOLVE");

							fileOut.newLine();
							fileOut.write("Solution status" + "\t" + lpsolve_status);

							fileOut.newLine();
							fileOut.write("Solution algorithm" + "\t" + lpsolve_algorithm);

							fileOut.newLine();
							fileOut.write("Simplex iterations" + "\t" + lpsolve_iteration);
							
							fileOut.newLine();
							fileOut.write("Prism version when problem solved" + "\t" + PrismMain.get_prism_version());
							
							fileOut.newLine();
							fileOut.write("Date & time problem solved" + "\t" + dateFormat.format(new Date()));
							
							fileOut.newLine();
							if ((int) (time_reading / 60) == 0) {
								fileOut.write("Time reading (minutes & seconds)" + "\t" + Double.valueOf(twoDForm.format(time_reading % 60)) + "s");
							} else {
								fileOut.write("Time reading (minutes & seconds)" + "\t" + (int) (time_reading / 60) + "m" + Double.valueOf(twoDForm.format(time_reading % 60)) + "s");
							}
										
							fileOut.newLine();
							if ((int) (time_solving / 60) == 0) {
								fileOut.write("Time solving (minutes & seconds)" + "\t" + Double.valueOf(twoDForm.format(time_solving % 60)) + "s");
							} else {
								fileOut.write("Time solving (minutes & seconds)" + "\t" + (int) (time_solving / 60) + "m" + Double.valueOf(twoDForm.format(time_solving % 60)) + "s");
							}
							
							fileOut.newLine();
							if ((int) (time_writing / 60) == 0) {
								fileOut.write("Time writing (minutes & seconds)" + "\t" + Double.valueOf(twoDForm.format(time_writing % 60)) + "s");
							} else {
								fileOut.write("Time writing (minutes & seconds)" + "\t" + (int) (time_writing / 60) + "m" + Double.valueOf(twoDForm.format(time_writing % 60)) + "s");	
							}

							fileOut.newLine();
							fileOut.write("Total variables" + "\t" + lpsolve_total_variables);

							fileOut.newLine();
							fileOut.write("Total constraints" + "\t" + lpsolve_total_constraints);

							fileOut.newLine();
							fileOut.write("Objective value" + "\t" + Double.valueOf(twoDForm.format(objective_value)));

							fileOut.close();
						} catch (IOException e) {
							System.err.println("Panel Solve Runs - FileWriter(output_generalInfo_file) error - " + e.getClass().getName() + ": " + e.getMessage());
						}
						output_general_outputs_file.createNewFile();
						
						
						// show successful or fail in the GUI
						data[row][3] = "successful";
						model.fireTableDataChanged();
						value = null; /*reduceCost = null; dual = null; slack = null;*/		// clear arrays to save memory
						vlb = null; vub = null; vname = null; objvals = null;			// clear arrays to save memory
					} else {
						if (is_problem_exported) solver.writeLp(problem_file.getAbsolutePath());
						data[row][3] = "fail";
						model.fireTableDataChanged();
					}						
					solver.deleteLp();
				}
				
				// Summarize outputs
				try {
					Summarize_Outputs sumamrize_output = new Summarize_Outputs(runFolder, iter);
					sumamrize_output = null;
				} catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Fail to summarize outputs for "+ runFolder);		
					e.printStackTrace();
				}
			}
			
			// These could be set to null only after all iterations are solved
			read = null;     												// Clear the lists to save memory       
			read_database = null;											// Clear the lists to save memory
			nonea_method_choice_for_strata = null;							// Clear the lists to save memory
			nonea_method_choice_for_strata_without_sizeclass = null;		// Clear the lists to save memory
			ea_conversion_and_rotation_for_strata = null;					// Clear the lists to save memory
			ea_conversion_and_rotation_for_strata_without_sizeclass = null;	// Clear the lists to save memory
		}
		catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Create output files exception for "+ runFolder);		
			e.printStackTrace();
			data[row][3] = "fail, cannot create outputs";
			model.fireTableDataChanged();
			
			problem_file.delete();
			solution_file.delete();
			output_general_outputs_file.delete();	
			output_variables_file.delete();
			output_constraints_file.delete();
			output_management_overview_file.delete();
			output_management_details_file.delete();	
			clear_query_value_for_fly_constraints(output_fly_constraints_file);	
			output_basic_constraints_file.delete();
			output_flow_constraints_file.delete();
		} catch (LpSolveException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   LPSOLVE exception for " + runFolder);
			e.printStackTrace();
			data[row][3] = "fail, lpsolve error";
			model.fireTableDataChanged();
			
			problem_file.delete();
			solution_file.delete();
			output_general_outputs_file.delete();	
			output_variables_file.delete();
			output_constraints_file.delete();
			output_management_overview_file.delete();
			output_management_details_file.delete();	
			clear_query_value_for_fly_constraints(output_fly_constraints_file);	
			output_basic_constraints_file.delete();
			output_flow_constraints_file.delete();
		}		
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Input files exception for " + runFolder);
			e.printStackTrace();
			data[row][3] = "fail, invalid inputs";
			model.fireTableDataChanged();
			
			problem_file.delete();
			solution_file.delete();
			output_general_outputs_file.delete();	
			output_variables_file.delete();
			output_constraints_file.delete();
			output_management_overview_file.delete();
			output_management_details_file.delete();	
			clear_query_value_for_fly_constraints(output_fly_constraints_file);	
			output_basic_constraints_file.delete();
			output_flow_constraints_file.delete();
		} finally {
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	
	
	// apply to eq (5)
	private double get_period_two_variable_value_after_applying_stochastic_loss_rate_for_period_one_variable(
			int var_index, LinkedHashMap<String, Double> map_var_name_to_var_value, LinkedHashMap<String, Double> map_var_name_to_var_total_loss_rate_mean,
			Information_Variable[] var_info_array, List<String> layer5, int[] var_rd_condition_id,
			Information_Disturbance disturbance_info, int total_replacing_disturbances,
			double[][] all_zeroes_2D_array) { 	
		
		// Get the period 1 solution from previous iteration
		String var_name = var_info_array[var_index].get_var_name();
		String[] name_split = var_name.split("_");
		String first_six_letters_of_var_name = var_name.substring(0, 6);
		switch (first_six_letters_of_var_name) {
		case "xNG_E_":
			int period = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(period);
			break;
		case "xPB_E_":
			period = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(period);
			break;
		case "xGS_E_":
			period = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(period);
			break;
		
		case "xMS_E_":
			period = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(period);
			break;
			
		case "xBS_E_":
			period = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(period);
			break;
			
		case "xEA_E_":
			period = Integer.parseInt(name_split[9 + 2]) - 1;
			name_split[9 + 2] = String.valueOf(period);
			break;
			
		case "xEA_R_":
			period = Integer.parseInt(name_split[9 + 2]) - 1;
			name_split[9 + 2] = String.valueOf(period);
			break;
			
		case "xNG_R_":
			period = Integer.parseInt(name_split[6 + 2]) - 1;
			name_split[6 + 2] = String.valueOf(period);
			int age = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(age);
			break;
			
		case "xPB_R_":
			period = Integer.parseInt(name_split[6 + 2]) - 1;
			name_split[6 + 2] = String.valueOf(period);
			age = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(age);
			break;
			
		case "xGS_R_":
			period = Integer.parseInt(name_split[6 + 2]) - 1;
			name_split[6 + 2] = String.valueOf(period);
			age = Integer.parseInt(name_split[7 + 2]) - 1;
			name_split[7 + 2] = String.valueOf(age);
			break;
		default:
			break;
		}
		String period_one_var_name = String.join("_", name_split);
		
		
		double period_one_var_value = 0;
		double total_loss_rate_mean = 0;
		if (map_var_name_to_var_value.get(period_one_var_name) != null) {
			period_one_var_value = map_var_name_to_var_value.get(period_one_var_name);
			total_loss_rate_mean = map_var_name_to_var_total_loss_rate_mean.get(period_one_var_name);
		}
		
		// Calculate the period 2 variable after the consequence of stochastic loss
		double total_stochastic_loss_rate_mean = total_loss_rate_mean;	
		double period_two_value = (1 - total_stochastic_loss_rate_mean / 100) * period_one_var_value;
		return period_two_value;
		
		
		
		
		
		
//		// we need P' here to calculate the second period variable which is the result from applying the random loss P'. Now we assume P' = P (P is the loss rate mean or average loss)
//		int s5 = Collections.binarySearch(layer5, var_info_array[var_index].get_layer5());
//		double[][] loss_rate_mean = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
//		double[][] loss_rate_std = (var_rd_condition_id[var_index] != -9999) ? disturbance_info.get_loss_rate_std_from_rd_condition_id(var_rd_condition_id[var_index]) : all_zeroes_2D_array;
//		
//		double final_loss_rate_mean = 0;		// the mean would add up from independent means
//		double final_loss_rate_variance = 0;	// (std)^2 = (std1)^2 + (std2)^2 + (std3)^2 + ...
//		for (int k = 0; k < total_replacing_disturbances; k++) {
//			final_loss_rate_mean = final_loss_rate_mean + loss_rate_mean[k][s5] / 100;
//			final_loss_rate_variance = final_loss_rate_variance + loss_rate_std[k][s5] / 100;
//		}
//		double final_loss_rate_std = Math.sqrt(final_loss_rate_variance);
//		// We need to draw the stochastic loss rate based on the final mean and final std. 
//		double stochastic_loss_rate = final_loss_rate_mean;		// stochastic loss Rate = P'(-->x). Now we assume P'= P (P is the final_loss_rate_mean)
//		// Calculate the period 2 variable after the consequence of stochastic loss
//		double period_two_value = (1 - stochastic_loss_rate) * period_one_var_value;
//		return period_two_value;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// For LPSOLVE only ----------------------------------------------------------------------------------------
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
	// End of For LPSOLVE only ----------------------------------------------------------------------------------------
	
	
	
	private void clear_query_value_for_fly_constraints(File file) {
		try {		
			if (file.exists()) {
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
									
				int totalRows = a.length;
				int totalColumns = a[0].split("\t").length;				
			
				try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
					fileOut.write(a[0]);	// the columns headers, 1st row
					// read and write all values from all rows and columns except the query_value (last column) in the row >=1
					for (int i = 1; i < totalRows; i++) {
						fileOut.newLine();
						
						String[] rowValue = a[i].split("\t");
						for (int j = 0; j < totalColumns; j++) {
							if (j == totalColumns - 1) {	// this is the query_value column (last column)
								rowValue[j] = null;
							}
							fileOut.write(rowValue[j] + "\t");
						}
					}
					fileOut.close();
				} catch (IOException e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				} 
			}
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}

