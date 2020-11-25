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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import prism_convenience.PrismTableModel;
import prism_project.data_process.Information_Cost;
import prism_project.data_process.Information_Disturbance;
import prism_project.data_process.Information_Parameter;
import prism_project.data_process.Information_Variable;
import prism_project.data_process.Read_Database;
import prism_project.data_process.Read_Input;
import prism_project.data_process.Statistics;
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
		int last_solved_iter = list_files.length - 1;
		int max_iteration = Integer.parseInt(data[row][3].toString());
		
		if (data[row][2].equals("restart")) {	// delete all except input files and fly_constraints file
			last_solved_iter = -1;
			// Delete all output files, problem file, and solution file, but keep the fly_constraints file
			File[] contents = runFolder.listFiles();
			if (contents != null) {
				for (File f : contents) {
					if ((f.getName().startsWith("output") || f.getName().startsWith("problem") || f.getName().startsWith("solution") || f.getName().startsWith("summarize")) && !f.getName().contains("fly_constraints")) {
						f.delete();
					}
				}
			}
		} else { // the case "continue"
			if (last_solved_iter < max_iteration) {
				System.out.println("Prism is trimming current outputs to build final solution up to the last found iteration......");
				Summarize_Outputs sumamrize_output = new Summarize_Outputs(runFolder, last_solved_iter);
				sumamrize_output = null;
				System.out.println("Trimming is completed!");
			}
			
			if (last_solved_iter == max_iteration) {
				System.out.println("No more solving needed for " + data[row][0].toString());
				System.out.println("Prism keeps current outputs as final solution.");
			}
			
			File[] contents = runFolder.listFiles();
			if (last_solved_iter > max_iteration && contents != null) {
				for (File f : contents) {
					if ((f.getName().startsWith("output") || f.getName().startsWith("problem") || f.getName().startsWith("solution")) && !f.getName().contains("fly_constraints")) {
						String iter_name = f.getName().substring(f.getName().lastIndexOf("_") + 1, f.getName().lastIndexOf("."));
						try {
							if (Integer.parseInt(iter_name) > max_iteration) f.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (f.getName().startsWith("summarize")) f.delete();	// Delete all summarize files
				}
				
				System.out.println("Prism is trimming current outputs to build final solution......");
				Summarize_Outputs sumamrize_output = new Summarize_Outputs(runFolder, max_iteration);
				sumamrize_output = null;
				System.out.println("Trimming is completed!");
			}

			data[row][4] = "trimming done";
			model.fireTableDataChanged();
		}
		
		
		
		
		
		
		
		if (last_solved_iter < max_iteration)
			try {
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
				int[] starting_age_class_for_prescription = read_database.get_starting_age_class_for_prescription();
				String[] yield_tables_column_names = read_database.get_yield_tables_column_names();
				String[] yield_tables_names = read_database.get_yield_tables_names();	
				int total_prescriptions = yield_tables_values.length;
				int[] total_rows_of_precription = read_database.get_total_rows_of_precription();
				int activity_col_id = read_database.get_activity_column_index();
				boolean[][] has_R_prescriptions = read_database.get_has_R_prescriptions();
				
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
				File input_03_file = new File(runFolder.getAbsolutePath() + "/input_03_prescription_category.txt");
				File input_04_file = new File(runFolder.getAbsolutePath() + "/input_04_prescription_assignment.txt");
				File input_06_file = new File(runFolder.getAbsolutePath() + "/input_06_natural_disturbances.txt");
				File input_07_file = new File(runFolder.getAbsolutePath() + "/input_07_management_cost.txt");
				File input_08_file = new File(runFolder.getAbsolutePath() + "/input_08_basic_constraints.txt");
				File input_09_file = new File(runFolder.getAbsolutePath() + "/input_09_flow_constraints.txt");
				File input_11_file = new File(runFolder.getAbsolutePath() + "/input_11_state_id.txt");
				Read_Input read = new Read_Input(read_database);
				read.read_general_inputs(input_01_file);
				read.read_model_strata(input_02_file);
				read.read_prescription_category(input_03_file);
				read.read_prescription_assignment(input_04_file);
				read.read_replacing_disturbances(input_06_file);
				read.read_management_cost(input_07_file);
				read.read_basic_constraints(input_08_file);
				read.read_flow_constraints(input_09_file);
				read.read_state_id(input_11_file);
				
				// Get info: input_01_general_inputs
				int total_periods = read.get_total_periods();
				int total_years_in_one_period = read.get_total_years_in_one_period();
				double annual_discount_rate = read.get_discount_rate() / 100;
				String solver_for_optimization = read.get_solver();
				int solving_time_limit = read.get_solving_time() * 60;	//convert to seconds
				boolean is_problem_exported = read.get_export_problem();
				boolean is_solution_exported = read.get_export_solution();
							
				// Get info: input_02_model_strata
				List<String> E_model_strata = read.get_E_model_strata();
				List<String> model_strata_4layers = read.get_E_model_strata_without_sizeclass_and_covertype(); 
				List<String> R_model_strata = new ArrayList<String>();
				for (String l1234: model_strata_4layers) {	// This is a special case, we need all s5_s6 combinations not just the s5_s6 combinations in E_model_strata
					for (String l5: layer5) {
						for (String l6: layer6) {
							int s5 = Collections.binarySearch(layer5, l5);
							int s6 = Collections.binarySearch(layer6, l6);
							if (has_R_prescriptions[s5][s6]) {
								R_model_strata.add(l1234 + "_" + l5 + "_" + l6);
							}
						}
					}
				}
				int	total_E_model_strata = E_model_strata.size();		
				int	total_R_model_strata = R_model_strata.size();
				int	total_model_strata_4layers = model_strata_4layers.size();
				LinkedHashMap<String, Integer> map_E_strata_to_strata_id = new LinkedHashMap<String, Integer>();
				for (int id = 0; id < total_E_model_strata; id++) {
					map_E_strata_to_strata_id.put(E_model_strata.get(id), id);		// strata = key, id = value		
				}
				LinkedHashMap<String, Integer> map_R_strata_to_strata_id = new LinkedHashMap<String, Integer>();
				for (int id = 0; id < total_R_model_strata; id++) {
					map_R_strata_to_strata_id.put(R_model_strata.get(id), id);		// strata_without_sizeclass = key, id = value		
				}
				LinkedHashMap<String, Integer> map_model_strata_4layers_to_strata_id = new LinkedHashMap<String, Integer>();
				for (int id = 0; id < total_model_strata_4layers; id++) {
					map_model_strata_4layers_to_strata_id.put(model_strata_4layers.get(id), id);		// strata_without_covertype_and_sizeclass = key, id = value		
				}
				
				// Get Info: input_04_prescription_assignment
				read.process_data_from_prescription_assignment(E_model_strata, R_model_strata);
				Set<Integer>[] set_of_prescription_ids_for_E_strata = read.get_set_of_prescription_ids_for_E_strata();
				Set<Integer>[] set_of_prescription_ids_for_R_strata = read.get_set_of_prescription_ids_for_R_strata();
				Set<Integer>[][][] set_of_prescription_ids_for_E_strata_with_s5R_s6R = read.get_set_of_prescription_ids_for_E_strata_with_s5R_s6R();
				Set<Integer>[][][] set_of_prescription_ids_for_R_strata_with_s5R_s6R = read.get_set_of_prescription_ids_for_R_strata_with_s5R_s6R();
				boolean is_prescription_assignment_defined_with_some_rows = (input_04_file.exists()) ? true : false;
				
				// Get Info: input_06_natural_disturbances
				List<String> disturbance_condition_list = read.get_disturbance_condition_list(); 
				
				// Get info: input_07_management_cost
				List<String> cost_condition_list = read.get_cost_condition_list(); 
				
				// Get info: input_08_basic_constraints
				List<String> constraint_column_names_list = read.get_constraint_column_names_list();
				int constraint_id_col = -9999, constraint_description_col = -9999, constraint_type_col = -9999, multiplier_col = -9999, lowerbound_col = -9999, lowerbound_perunit_penalty_col = -9999, upperbound_col = -9999, upperbound_perunit_penalty_col = -9999;
				if (constraint_column_names_list != null) {
					constraint_id_col = constraint_column_names_list.indexOf("bc_id");
					constraint_description_col = constraint_column_names_list.indexOf("bc_description");
					constraint_type_col = constraint_column_names_list.indexOf("bc_type");
					multiplier_col = constraint_column_names_list.indexOf("bc_multiplier");
					lowerbound_col = constraint_column_names_list.indexOf("lowerbound");
					lowerbound_perunit_penalty_col = constraint_column_names_list.indexOf("lowerbound_perunit_penalty");
					upperbound_col = constraint_column_names_list.indexOf("upperbound");
					upperbound_perunit_penalty_col = constraint_column_names_list.indexOf("upperbound_perunit_penalty");
				}
				String[][] bc_values = read.get_bc_data();		
				int total_softConstraints = read.get_total_soft_constraints();
				double[] softConstraints_LB = read.get_soft_constraints_LB();
				double[] softConstraints_UB = read.get_soft_constraints_UB();
				double[] softConstraints_LB_Weight = read.get_soft_constraints_LB_Weight();
				double[] softConstraints_UB_Weight = read.get_sof_constraints_UB_Weight();		
				int total_hardConstraints = read.get_total_hard_constraints();
				double[] hardConstraints_LB = read.get_hard_constraints_LB();
				double[] hardConstraints_UB = read.get_hard_constraints_UB();	
				int total_freeConstraints = read.get_total_free_constraints();
				
				// Get info: input_09_flow_constraints	
				List<List<List<Integer>>> flow_set_list = read.get_flow_set_list();
				List<Integer> flow_id_list = read.get_flow_id_list();
				List<String> flow_description_list = read.get_flow_description_list();
				List<String> flow_arrangement_list = read.get_flow_arrangement_list();
				List<String> flow_type_list = read.get_flow_type_list();
				List<Double> flow_lowerbound_percentage_list = read.get_flow_lowerbound_percentage_list();
				List<Double> flow_upperbound_percentage_list = read.get_flow_upperbound_percentage_list();			
				System.out.println("Reading process finished for all core inputs          " + dateFormat.format(new Date()));
				System.out.println("Optimization models will be built based on Prism-Formulation-15-v5");
				
				// Get info: input_11_state_id
				LinkedHashMap<String, String> map_prescription_and_row_id_to_state_id = read.get_map_prescription_and_row_id_to_state_id();
				String merging_option = (map_prescription_and_row_id_to_state_id == null) ? "no_merge" : "merge";
				System.out.println("Iterations are connected by '" + merging_option + "' option");
				
				
				
				
				//--------------------------------------------------------------------------------------------------------------------------
			    //--------------------------------------------------------------------------------------------------------------------------  
			    //---------------------------------------------------SOLVING ITERATIONS-----------------------------------------------------
			    //--------------------------------------------------------------------------------------------------------------------------
			    //--------------------------------------------------------------------------------------------------------------------------
				for (int iter = last_solved_iter + 1; iter <= max_iteration; iter++) {	// Loop all iterations
					data[row][4] = "solving iteration " + iter;
					model.fireTableDataChanged();
					System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println();
					System.out.println("ITERATION " + iter);
					if (iter >= 1) {
						time_start = System.currentTimeMillis();	// measure time before reading
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
					
					

					// Pre-calculations to speed up reading time----------------------------------------------------
					// Pre-calculations to speed up reading time----------------------------------------------------
					// Pre-calculations to speed up reading time----------------------------------------------------
					// Pre-identify s5 and s6 for each stratum and Pre-caterorize prescriptions for each stratum
					int[] s5_for_E_model_strata = new int[total_E_model_strata]; 
					int[] s6_for_E_model_strata = new int[total_E_model_strata];
					int[] s5_for_R_model_strata = new int[total_R_model_strata];
					int[] s6_for_R_model_strata = new int[total_R_model_strata];
					
					List<Integer>[] E_prescription_ids = new ArrayList[total_E_model_strata];
					List<Integer>[] E_0_prescription_ids = new ArrayList[total_E_model_strata];
					List<Integer>[] E_1_prescription_ids = new ArrayList[total_E_model_strata];
					List<Integer>[] R_prescription_ids = new ArrayList[total_R_model_strata];
					List<Integer>[] R_0_prescription_ids = new ArrayList[total_R_model_strata];
					List<Integer>[] R_1_prescription_ids = new ArrayList[total_R_model_strata];
					
					for (int strata_id = 0; strata_id < total_E_model_strata; strata_id++) {
						String strata = E_model_strata.get(strata_id);
						int s5 = Collections.binarySearch(layer5, strata.split("_")[4]);
						int s6 = Collections.binarySearch(layer6, strata.split("_")[5]);
						s5_for_E_model_strata[strata_id] = s5;
						s6_for_E_model_strata[strata_id] = s6;
						String s5_s6 = layer5.get(s5) + "_" + layer6.get(s6);
						
						E_prescription_ids[strata_id] = new ArrayList<Integer>();
						E_0_prescription_ids[strata_id] = new ArrayList<Integer>();
						E_1_prescription_ids[strata_id] = new ArrayList<Integer>();
						for (int i = 0; i < total_prescriptions; i++) {
							if (yield_tables_names[i].startsWith("E_0_" + s5_s6)) {
								E_0_prescription_ids[strata_id].add(i);
								E_prescription_ids[strata_id].add(i);
							} else if (yield_tables_names[i].startsWith("E_1_" + s5_s6)) {
								E_1_prescription_ids[strata_id].add(i);
								E_prescription_ids[strata_id].add(i);
							}
						}
					}
					
					for (int strata_id = 0; strata_id < total_R_model_strata; strata_id++) {
						String strata = R_model_strata.get(strata_id);
						int s5 = Collections.binarySearch(layer5, strata.split("_")[4]);
						int s6 = Collections.binarySearch(layer6, strata.split("_")[5]);
						s5_for_R_model_strata[strata_id] = s5;
						s6_for_R_model_strata[strata_id] = s6;
						String s5_s6 = layer5.get(s5) + "_" + layer6.get(s6);
						
						R_prescription_ids[strata_id] = new ArrayList<Integer>();
						R_0_prescription_ids[strata_id] = new ArrayList<Integer>();
						R_1_prescription_ids[strata_id] = new ArrayList<Integer>();
						for (int i = 0; i < total_prescriptions; i++) {
							if (yield_tables_names[i].startsWith("R_0_" + s5_s6)) {
								R_0_prescription_ids[strata_id].add(i);
								R_prescription_ids[strata_id].add(i);
							} else if (yield_tables_names[i].startsWith("R_1_" + s5_s6)) {
								R_1_prescription_ids[strata_id].add(i);
								R_prescription_ids[strata_id].add(i);
							}
						}
					}
					
					
					// Currently use for equation 7b only
					Set<Integer>[][] set_of_x_index_for_7b;
					set_of_x_index_for_7b = new HashSet[total_model_strata_4layers][];
					for (int strata_4layers_id = 0; strata_4layers_id < total_model_strata_4layers; strata_4layers_id++) {
						set_of_x_index_for_7b[strata_4layers_id] = new HashSet[total_periods + 1 + iter];
						for (int t = 1 + iter; t <= total_periods + iter; t++) {
							set_of_x_index_for_7b[strata_4layers_id][t] = new HashSet<Integer>();
						}
					}
					
					
					// DEFINITIONS --------------------------------------------------------------
					// DEFINITIONS --------------------------------------------------------------
					// DEFINITIONS --------------------------------------------------------------
					Information_Parameter parameter_info = new Information_Parameter(read_database);
					Information_Cost cost_info = (cost_condition_list != null) ? new Information_Cost(read_database, cost_condition_list) : null;
					Information_Disturbance disturbance_info = (disturbance_condition_list != null) ? new Information_Disturbance(read_database, disturbance_condition_list) : null;
					int total_disturbances = (disturbance_info != null) ? disturbance_info.get_total_disturbances() : 0; 	// get the total disturbance dynamically from the Natural Disturbances input
					
					List<Information_Variable> var_info_list = new ArrayList<Information_Variable>();
					List<Double> objlist = new ArrayList<Double>();		// objective coefficient
					List<String> vnamelist = new ArrayList<String>();	// variable name
					List<Double> vlblist = new ArrayList<Double>();		// lower bound
					List<Double> vublist = new ArrayList<Double>();		// upper bound
					int nvars = 0;
					
					// declare arrays to keep variables. some variables are optimized by using jagged-arrays (xE, xR, fire)
					int[] y = new int [total_softConstraints];	// y(j)
					int[] l = new int [total_softConstraints];	// l(j)
					int[] u = new int [total_softConstraints];	// u(j)
					int[] z = new int [total_hardConstraints];	// z(k)
					int[] v = new int [total_freeConstraints];	// v(n)
	//				int[][][][] xE = new int[total_E_model_strata][total_layer5][total_layer6][[total_prescriptions][total_periods + 1 + iter];	// xE[s1,s2,s3,s4,s5,s6][s5R][s6R][i][t]
	//				int[][][][][] xR = new int[total_R_model_strata][total_layer5][total_layer6][total_prescriptions][total_periods + 1 + iter][total_age_classes = t - 1];		// xR[s1,s2,s3,s4,s5,s6][s5R][s6R][i][t][a]
	//										// total_Periods + 1 + iter because tR starts from 1 to total_Periods + iter, ignore the 0		
	//										// total_age_classes can be max at a = t - 1	
					int[][][][][] xE = null;		// xE[s1,s2,s3,s4,s5,s6][s5R][s6R][i][t]				
					int[][][][][][] xR = null;		// xR[s1,s2,s3,s4,s5,s6][s5R][s6R][i][t][a]
					int[][] fire = new int[total_R_model_strata][total_periods + 1 + iter];		// f[s1,s2,s3,s4,s5R,s6R][t]		-->  replacing-disturbance variables			
					
					// get the area parameter for existing strata V[s1,s2,s3,s4,s5,s6]
					String[][] E_model_strata_data = read.get_ms_data();	
					double[] E_strata_area = new double[total_E_model_strata];
					for (int id = 0; id < total_E_model_strata; id++) {
						E_strata_area[id] = Double.parseDouble(E_model_strata_data[id][7]);		// area (acres)
					}						
	
					
					
					
					// CREATE OBJECTIVE FUNCTION-------------------------------------------------
					// CREATE OBJECTIVE FUNCTION-------------------------------------------------
					// CREATE OBJECTIVE FUNCTION-------------------------------------------------
					// A book-keeping variable is added to fix the bug: first existing stratum is not included into equation 5 (issue 128 in BitBucket)
					var_info_list.add(new Information_Variable(iter, "Book-keeping", read_database));
					objlist.add((double) 0);
					vnamelist.add("Book-keeping");
					vlblist.add((double) 0);
					vublist.add((double) 0);
					nvars++;
					
					// Create soft constraint decision variables y(j)			
					for (int j = 0; j < total_softConstraints; j++) {
						String var_name = "y_" + j;
						var_info_list.add(new Information_Variable(iter, var_name, read_database));
	
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
						var_info_list.add(new Information_Variable(iter, var_name, read_database));
						
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
						var_info_list.add(new Information_Variable(iter, var_name, read_database));
						
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
						var_info_list.add(new Information_Variable(iter, var_name, read_database));
						
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
						var_info_list.add(new Information_Variable(iter, var_name, read_database));
						
						objlist.add((double) 0);
						vnamelist.add(var_name);
						vlblist.add((double) 0);				// 0 if not allow negative multiplier
	//					vlblist.add(-Double.MAX_VALUE);			// -MAX_VALUE if allow negative multiplier, need to redesign flow logic
						vublist.add(Double.MAX_VALUE);			// MAX_VALUE is UB
						v[n] = nvars;
						nvars++;				
					}
					
					// Create decision variables x_E_(s1,s2,s3,s4,s5,s6)(i)(s5R)(s6R)(t)
					xE = new int[total_E_model_strata][][][][];
					for (int e_strata_id = 0; e_strata_id < total_E_model_strata; e_strata_id++) {
						String strata = E_model_strata.get(e_strata_id);
						xE[e_strata_id] = new int[total_prescriptions][][][];
						Set<Integer> filter_set;
						
						// non-clear-cut prescriptions (E_0)
						filter_set = new HashSet<Integer>(set_of_prescription_ids_for_E_strata[e_strata_id]);
						filter_set.retainAll(E_0_prescription_ids[e_strata_id]);	// filter out the E_0 prescriptions only
						for (int i : filter_set) {
							xE[e_strata_id][i] = new int[1][1][total_periods + 1 + iter];	// only 1 index for s5R and s6R. this doesn'n need to be correct for s5R and s6R for this variable
							int s5R = 0;
							int s6R = 0;
							for (int t = 1 + iter; t <= total_periods + iter; t++) {	// --> always loop to the ending period of the horizon (allow missing row ids)
								String var_name = "x_E_" + strata + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + i + "_" + t;	
								Information_Variable var_info = new Information_Variable(iter, var_name, read_database);
								
								String strata_4layers = var_info.get_layer1() + "_" + var_info.get_layer2() + "_" + var_info.get_layer3() + "_" + var_info.get_layer4();
								if (map_model_strata_4layers_to_strata_id.get(strata_4layers) != null) {
									int strata_4layers_id = map_model_strata_4layers_to_strata_id.get(strata_4layers);
									set_of_x_index_for_7b[strata_4layers_id][t].add(nvars);
								}
									
								var_info_list.add(var_info);
								objlist.add((double) 0);
								vnamelist.add(var_name);
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								xE[e_strata_id][i][s5R][s6R][t] = nvars;
								nvars++;
//								System.out.println("x_E_" + nvars);
							}						
						}
						
						// clear-cut prescriptions (E_1)
						filter_set = new HashSet<Integer>(set_of_prescription_ids_for_E_strata[e_strata_id]);
						filter_set.retainAll(E_1_prescription_ids[e_strata_id]);	// filter out the E_1 prescriptions only
						for (int i : filter_set) {
							xE[e_strata_id][i] = new int[total_layer5][][];
							int rotation_period = total_rows_of_precription[i]; // tR = total rows of this prescription
							int rotation_age = rotation_period + starting_age_class_for_prescription[i] - 1;
							int T_FINAL = Math.min(rotation_period, total_periods + iter);	// --> always loop to the rotation period (if tR within the horizon) or to the ending period of the horizon (if tR out of the planning horizon)
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								xE[e_strata_id][i][s5R] = new int[total_layer6][];
								for (int s6R = 0; s6R < total_layer6; s6R++) {
									if (has_R_prescriptions[s5R][s6R] && set_of_prescription_ids_for_E_strata_with_s5R_s6R[e_strata_id][s5R][s6R].contains(i)) {	// if this prescription leads to s5R s6R regeneration
										xE[e_strata_id][i][s5R][s6R] = new int[total_periods + 1 + iter];	// define broader otherwise Eq. 7 will fail 
										for (int t = 1 + iter; t <= T_FINAL; t++) {		// this loop guarantees that prescriptions with clear-cut beyond planning horizon are allowed
											String var_name = "x_E_" + strata + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + i + "_" + t;
											Information_Variable var_info = new Information_Variable(iter, var_name, read_database);
											var_info.set_rotation_period(rotation_period);
											var_info.set_rotation_age(rotation_age);
											
											String strata_4layers = var_info.get_layer1() + "_" + var_info.get_layer2() + "_" + var_info.get_layer3() + "_" + var_info.get_layer4();
											if (map_model_strata_4layers_to_strata_id.get(strata_4layers) != null) {
												int strata_4layers_id = map_model_strata_4layers_to_strata_id.get(strata_4layers);
												set_of_x_index_for_7b[strata_4layers_id][t].add(nvars);
											}
											
											var_info_list.add(var_info);
											objlist.add((double) 0);
											vnamelist.add(var_name);
											vlblist.add((double) 0);
											vublist.add(Double.MAX_VALUE);
											xE[e_strata_id][i][s5R][s6R][t] = nvars;
											nvars++;
//											System.out.println("x_E_" + nvars);
										}
									}
								}
							}
						}
					}
					
					// Create decision variables x_R_(s1,s2,s3,s4,s5,s6)(i)(s5R)(s6R)(t)(a)
					xR = new int[total_R_model_strata][][][][][];
					for (int r_strata_id = 0; r_strata_id < total_R_model_strata; r_strata_id++) {
						String strata = R_model_strata.get(r_strata_id);
						int s5 = s5_for_R_model_strata[r_strata_id];
						int s6 = s6_for_R_model_strata[r_strata_id];
						if (has_R_prescriptions[s5][s6]) {
							xR[r_strata_id] = new int[total_prescriptions][][][][];
							Set<Integer> filter_set;
							
							// non-clear-cut prescriptions (R_0)
							filter_set = new HashSet<Integer>(set_of_prescription_ids_for_R_strata[r_strata_id]);
							filter_set.retainAll(R_0_prescription_ids[r_strata_id]);	// filter out the R_0 prescriptions only
							for (int i : filter_set) {
								xR[r_strata_id][i] = new int[1][1][total_periods + 1 + iter][];		// only 1 index for s5R and s6R. this doesn'n need to be correct for s5R and s6R for this variable
								int s5R = 0;
								int s6R = 0;
								int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
								for (int t = t_regen + iter; t <= total_periods + iter; t++) {
									xR[r_strata_id][i][s5R][s6R][t] = new int[t];		// age class of regen forest could be at max = t - 1
									for (int a = 1; a <= t - 1; a++) {
										String var_name = "x_R_" + strata + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + i + "_" + t + "_" + a;									
										Information_Variable var_info = new Information_Variable(iter, var_name, read_database);
										
										String strata_4layers = var_info.get_layer1() + "_" + var_info.get_layer2() + "_" + var_info.get_layer3() + "_" + var_info.get_layer4();
										if (map_model_strata_4layers_to_strata_id.get(strata_4layers) != null) {
											int strata_4layers_id = map_model_strata_4layers_to_strata_id.get(strata_4layers);
											set_of_x_index_for_7b[strata_4layers_id][t].add(nvars);
										}
										
										var_info_list.add(var_info);
										objlist.add((double) 0);
										vnamelist.add(var_name);							
										vlblist.add((double) 0);
										vublist.add(Double.MAX_VALUE);
										xR[r_strata_id][i][s5R][s6R][t][a] = nvars;
										nvars++;
//										System.out.println("x_R_" + nvars);
									}
								}
							}
							
							// clear-cut prescriptions (R_1)
							filter_set = new HashSet<Integer>(set_of_prescription_ids_for_R_strata[r_strata_id]);
							filter_set.retainAll(R_1_prescription_ids[r_strata_id]);	// filter out the R_1 prescriptions only
							for (int i : filter_set) {
								int rotation_age = total_rows_of_precription[i]; 
								xR[r_strata_id][i] = new int[total_layer5][][][];
								for (int s5R = 0; s5R < total_layer5; s5R++) {
									xR[r_strata_id][i][s5R] = new int[total_layer6][][];
									for (int s6R = 0; s6R < total_layer6; s6R++) {
										if (has_R_prescriptions[s5R][s6R] && set_of_prescription_ids_for_R_strata_with_s5R_s6R[r_strata_id][s5R][s6R].contains(i)) {	// if this prescription leads to s5R s6R regeneration
											xR[r_strata_id][i][s5R][s6R] = new int[total_periods + 1 + iter][]; 
											int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
											for (int t = t_regen + iter; t <= total_periods + iter; t++) {
												xR[r_strata_id][i][s5R][s6R][t] = new int[t];	// in period t, age class of any regenerated forest is at max = t - 1
												int A_FINAL = Math.min(rotation_age, t - 1);	
												for (int a = 1; a <= A_FINAL; a++) {	// this loop guarantees that prescriptions with clear-cut beyond planning horizon are allowed a <= A_FINAL --> a <= rotation_age --> rotation_age + t - rotation_period <= rotation_age --> t <= rotation_period
													int rotation_period = rotation_age + t - a;
													String var_name = "x_R_" + strata + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + i + "_" + t + "_" + a;										
													Information_Variable var_info = new Information_Variable(iter, var_name, read_database);
													var_info.set_rotation_period(rotation_period);
													var_info.set_rotation_age(rotation_age);
													
													String strata_4layers = var_info.get_layer1() + "_" + var_info.get_layer2() + "_" + var_info.get_layer3() + "_" + var_info.get_layer4();
													if (map_model_strata_4layers_to_strata_id.get(strata_4layers) != null) {
														int strata_4layers_id = map_model_strata_4layers_to_strata_id.get(strata_4layers);
														set_of_x_index_for_7b[strata_4layers_id][t].add(nvars);
													}
													
													var_info_list.add(var_info);
													objlist.add((double) 0);
													vnamelist.add(var_name);	
													vlblist.add((double) 0);
													vublist.add(Double.MAX_VALUE);
													xR[r_strata_id][i][s5R][s6R][t][a] = nvars;
													nvars++;
//													System.out.println("x_R_" + nvars);
												}
											}
										}
									}
								}
							}
						}
					}
					
					//-----------------------replacing disturbance variables
					// Create decision variables f(s1,s2,s3,s4,s5R,s6R,t)	
					for (int r_strata_id = 0; r_strata_id < total_R_model_strata; r_strata_id++) {
						String strata = R_model_strata.get(r_strata_id);
						int s5R = s5_for_R_model_strata[r_strata_id];
						int s6R = s6_for_R_model_strata[r_strata_id];
						if (has_R_prescriptions[s5R][s6R]) {
							for (int t = 1 + iter; t <= total_periods + iter; t++) {
								String var_name = "f_" + strata + "_" + t;
								var_info_list.add(new Information_Variable(iter, var_name, read_database));
								objlist.add((double) 0);			
								vnamelist.add(var_name);										
								vlblist.add((double) 0);
								vublist.add(Double.MAX_VALUE);
								fire[r_strata_id][t] = nvars;
								nvars++;	
							}
						}
					}
					
								
					// Convert lists to 1-D arrays
					double[] objvals = Stream.of(objlist.toArray(new Double[objlist.size()])).mapToDouble(Double::doubleValue).toArray();
					objlist = null;			// Clear the lists to save memory
					String[] vname = vnamelist.toArray(new String[nvars]);
					vnamelist = null;		// Clear the lists to save memory
					double[] vlb = Stream.of(vlblist.toArray(new Double[vlblist.size()])).mapToDouble(Double::doubleValue).toArray();
					vlblist = null;			// Clear the lists to save memory
					double[] vub = Stream.of(vublist.toArray(new Double[vublist.size()])).mapToDouble(Double::doubleValue).toArray();
					vublist = null;			// Clear the lists to save memory
					Information_Variable[] var_info_array = new Information_Variable[nvars];		// This array stores variable information
					for (int i = 0; i < nvars; i++) {
						var_info_array[i] = var_info_list.get(i);
					}	
					var_info_list = null;	// Clear the lists to save memory
					
					
					// This array stores cost information
					double[] var_cost_value = new double[nvars];
								
					// This array stores replacing disturbances information
					String disturbance_option = data[row][1].toString();
					LinkedHashMap<Integer, double[]> map_var_index_to_user_loss_rates = new LinkedHashMap<Integer, double[]>();	// this map var_index to the user loss rates array[k]
					int[][] var_rd_condition_id = new int[nvars][];
					int[][] var_global_adjustment_rd_condition_id = new int[nvars][];
					for (int i = 0; i < nvars; i++) {
						var_rd_condition_id[i] = new int[total_disturbances];
						var_global_adjustment_rd_condition_id[i] = new int[total_disturbances];
					}

					
					System.out.println("Connecting " + new DecimalFormat("###,###,###").format(nvars) + " variables to disturbance & cost logic...");
					for (int var_index = 0; var_index < nvars; var_index++) {
						Information_Variable var_info = var_info_array[var_index];
						int prescription_id = var_info_array[var_index].get_prescription_id();
						int row_id = var_info.get_row_id();
						
						// Replacing Disturbances -----------------------------------
						// Replacing Disturbances -----------------------------------
						if (disturbance_info != null) {	// in case there is condition --> calculate this one right away since they will be definitely used
							for (int k = 0; k < total_disturbances; k++) {
								var_rd_condition_id[var_index][k] = disturbance_info.get_rd_condition_id_for(var_info, k);	// for each variable with each disturbance k, always return -9999 or a number
								var_global_adjustment_rd_condition_id[var_index][k] = disturbance_info.get_global_adjustment_rd_condition_id_for(var_info, k);	// for each variable with each disturbance k, always return -9999 or a number
							}
						}
						// map every var_index to user_loss_rates first, then use just the map later
						double[] this_var_index_user_loss_rates = get_user_loss_rates_for_this_var(var_rd_condition_id, var_global_adjustment_rd_condition_id, var_index, disturbance_info);
						map_var_index_to_user_loss_rates.put(var_index, this_var_index_user_loss_rates);
						
						
						// Cost ------------------------------------------------------
						// Cost ------------------------------------------------------
						var_cost_value[var_index] = 0;	// start with initial value of 0
						if (cost_info != null) {		// in case there is condition --> calculate this one right away for future usage
							if (row_id != -9999 && row_id < total_rows_of_precription[prescription_id]) {	
							// This second if then is not necessary because we already have it in the function call 
							// It is here to help not create unnecessary Cost objects and therefore would save processing time
								List<String> conversion_after_disturbances_classification_list = new ArrayList<String>();	// i.e. P P disturbance		P D disturbance
								List<Double> conversion_after_disturbances_total_loss_rate_list = new ArrayList<Double>();	// i.e. 0.25				0.75
								
								if (var_info.get_period() != var_info.get_rotation_period()) {		// Note: no replacing disturbance in the period = rotation period --> no conversion cost after replacing disturbance
									double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
									double[][][] conversion_rate_mean = new double[total_disturbances][][];
									
									for (int s5R = 0; s5R < total_layer5; s5R++) {
										for (int s6R = 0; s6R < total_layer6; s6R++) {
											if (has_R_prescriptions[s5R][s6R]) {
												double total_loss_rate_for_this_conversion = 0;
												for (int k = 0; k < total_disturbances; k++) {
													if (var_rd_condition_id[var_index][k] != -9999) {
														conversion_rate_mean[k] = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index][k]);
														total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (user_loss_rate[k] / 100) * (conversion_rate_mean[k][s5R][s6R] / 100);
													}
												}
												if (total_loss_rate_for_this_conversion > 0) {
													conversion_after_disturbances_classification_list.add(layer5.get(s5R) + " " + layer6.get(s6R) + " " + "disturbance");
													conversion_after_disturbances_total_loss_rate_list.add(total_loss_rate_for_this_conversion);
												}
											}
										}														
									}
								}
								
								double discounted_value = 1 / Math.pow(1 + annual_discount_rate, total_years_in_one_period * (var_info.get_period() - 1));		// this is adjusted period for rolling horizon model
								var_cost_value[var_index] = cost_info.get_cost_value(
										var_info, cost_condition_list,
										conversion_after_disturbances_classification_list,
										conversion_after_disturbances_total_loss_rate_list);	// always return 0 or a number
								var_cost_value[var_index] = var_cost_value[var_index] * discounted_value;	// Cost is discounted
							}
						}
						// Print
						if (var_index + 1 > 1 && ((var_index + 1) % 30000 == 0 || var_index + 1 == nvars)) {
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
					LinkedHashMap<String, Double> map_var_name_to_var_total_loss_rate = null;
					LinkedHashMap<String, int[]> map_var_name_to_var_rd_condition_id = null;
					LinkedHashMap<String, int[]> map_var_name_to_var_global_adjustment_rd_condition_id = null;
					LinkedHashMap<String, double[]> map_var_name_to_var_new_loss_rates = null;
					
					
					List<List<Integer>> c5_indexlist = new ArrayList<List<Integer>>();	
					List<List<Double>> c5_valuelist = new ArrayList<List<Double>>();
					List<Double> c5_lblist = new ArrayList<Double>();	
					List<Double> c5_ublist = new ArrayList<Double>();
					int c5_num = 0;
					
					if (iter == 0) {
						for (int strata_id = 0; strata_id < total_E_model_strata; strata_id++) {
							int total_variables_added_for_this_stratum = 0;
							
							// Add constraint
							c5_indexlist.add(new ArrayList<Integer>());
							c5_valuelist.add(new ArrayList<Double>());
							
							// Add sigma[s5R][s6R][i] xE[s1,s2,s3,s4,s5,s6][i][s5R][s6R][1] 
							for (int i : E_prescription_ids[strata_id]) {
								if (xE[strata_id][i] != null) {
									int LEN1 = xE[strata_id][i].length;
									for (int s5R = 0; s5R < LEN1; s5R++) {
										if (xE[strata_id][i][s5R] != null) {
											int LEN2 = xE[strata_id][i][s5R].length;
											for (int s6R = 0; s6R < LEN2; s6R++) {
												if (xE[strata_id][i][s5R][s6R] != null
														&& xE[strata_id][i][s5R][s6R][1] > 0) {		// if variable is defined, this value would be > 0  
													c5_indexlist.get(c5_num).add(xE[strata_id][i][s5R][s6R][1]);
													c5_valuelist.get(c5_num).add((double) 1);
													total_variables_added_for_this_stratum++;
												}
											}
										}
									}
								}
							}	
											
							// Add bounds
							c5_lblist.add(E_strata_area[strata_id]);
							c5_ublist.add(E_strata_area[strata_id]);
							c5_num++;
							
							// Remove this constraint if no variable added
							if (total_variables_added_for_this_stratum == 0) {
								System.out.println("existing strata = " + E_model_strata.get(strata_id) + " is removed from equation 5 because no prescription is implemented. Please review your model");
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
						map_var_name_to_var_total_loss_rate = new LinkedHashMap<String, Double>();
						map_var_name_to_var_rd_condition_id = new LinkedHashMap<String, int[]>(); 
						map_var_name_to_var_global_adjustment_rd_condition_id = new LinkedHashMap<String, int[]>(); 
						map_var_name_to_var_new_loss_rates = new LinkedHashMap<String, double[]>(); 
						int previous_iter = iter - 1;
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
								map_var_name_to_var_value.put(row_value[1], Double.valueOf(row_value[2]));					// var_name = key, id = var_value
								map_var_name_to_var_total_loss_rate.put(row_value[1], Double.valueOf(row_value[4]));		// var_name = key, sum of all loss rate means = var_value
								int[] rd_condition_id = Arrays.stream(row_value[5].split(" ")).mapToInt(Integer::parseInt).toArray(); 
								map_var_name_to_var_rd_condition_id.put(row_value[1], rd_condition_id);						// var_name = key, rd_condition_id[] = var_value
								int[] global_adjustment_rd_condition_id = Arrays.stream(row_value[6].split(" ")).mapToInt(Integer::parseInt).toArray(); 
								map_var_name_to_var_global_adjustment_rd_condition_id.put(row_value[1], global_adjustment_rd_condition_id);	// var_name = key, global_adjustment_rd_condition_id[] = var_value
							}
						} catch (IOException e) {
							System.err.println(e.getClass().getName() + ": " + e.getMessage());
						}
						
						// Map the period 2 result of simulating stochastic disturbances on the period 1 management solution of the previous iteration obtained from output_02
						// Note: only store for the x variables except regenerated variables at age 1
						LinkedHashMap<Integer, Double> map_var_index_to_stochastic_var_value = new LinkedHashMap<Integer, Double>();
						// Existing variables xE[s1,s2,s3,s4,s5,s6][i][s5R][s6R][1 + iter]
						for (int strata_id = 0; strata_id < total_E_model_strata; strata_id++) {
							for (int i : E_prescription_ids[strata_id]) {
								if (xE[strata_id][i] != null) {
									int LEN1 = xE[strata_id][i].length;
									for (int s5R = 0; s5R < LEN1; s5R++) {
										if (xE[strata_id][i][s5R] != null) {
											int LEN2 = xE[strata_id][i][s5R].length;
											for (int s6R = 0; s6R < LEN2; s6R++) {
												if (xE[strata_id][i][s5R][s6R] != null
														&& xE[strata_id][i][s5R][s6R][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
													// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
													int var_index = xE[strata_id][i][s5R][s6R][1 + iter];	// this is the first period variable in this iteration or the second period variable in previous iteration
													double final_value = get_period_two_variable_value_after_applying_new_loss_rates_for_period_one_variable(
															disturbance_option,
															var_index, var_info_array, map_var_name_to_var_value,
															map_var_name_to_var_rd_condition_id,
															map_var_name_to_var_global_adjustment_rd_condition_id,
															map_var_name_to_var_new_loss_rates, disturbance_info);
													// Mapping
													map_var_index_to_stochastic_var_value.put(var_index, final_value);
												}
											}
										}
									}	
								}
							}	
						}														
							
						// Regenerated variables xR
						for (int r_strata_id = 0; r_strata_id < total_R_model_strata; r_strata_id++) {
							for (int i : R_prescription_ids[r_strata_id]) {
								if(xR[r_strata_id][i] != null) {
									int LEN1 = xR[r_strata_id][i].length;
									for (int s5R = 0; s5R < LEN1; s5R++) {
										if(xR[r_strata_id][i][s5R] != null) {
											int LEN2 = xR[r_strata_id][i][s5R].length;
											for (int s6R = 0; s6R < LEN2; s6R++) {
												if(xR[r_strata_id][i][s5R][s6R] != null) {
													int t = 1 + iter;
													for (int a = 2; a <= t - 1; a++) {		// to exclude regenerated variables at age class 1
														if(xR[r_strata_id][i][s5R][s6R][1 + iter] != null
																&& xR[r_strata_id][i][s5R][s6R][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0
															// Simulate stochastic SRs to get final_value which is the period 2 solution after stochastic disturbances
															int var_index = xR[r_strata_id][i][s5R][s6R][1 + iter][a];	// this is the first period variable in this iteration or the second period variable in previous iteration
															double final_value = get_period_two_variable_value_after_applying_new_loss_rates_for_period_one_variable(
																	disturbance_option,
																	var_index, var_info_array, map_var_name_to_var_value,
																	map_var_name_to_var_rd_condition_id,
																	map_var_name_to_var_global_adjustment_rd_condition_id,
																	map_var_name_to_var_new_loss_rates, disturbance_info);
															// Mapping
															map_var_index_to_stochastic_var_value.put(var_index, final_value);
														}
													}
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
							// 5a to 5b
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
							// 5a For existing variables xE[s1,s2,s3,s4,s5,s6][s5R][s6R][i][1 + iter]
							for (int e_strata_id = 0; e_strata_id < total_E_model_strata; e_strata_id++) {
								LinkedHashMap<Integer, String> map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();	// map all relevant variables in iteration 1+M
								for (int i : E_prescription_ids[e_strata_id]) {
									if (xE[e_strata_id][i] != null) {
										int LEN1 = xE[e_strata_id][i].length;
										for (int s5R = 0; s5R < LEN1; s5R++) {
											if (xE[e_strata_id][i][s5R] != null) {
												int LEN2 = xE[e_strata_id][i][s5R].length;
												for (int s6R = 0; s6R < LEN2; s6R++) {
													if (xE[e_strata_id][i][s5R][s6R] != null
															&& xE[e_strata_id][i][s5R][s6R][1 + iter] > 0) {		// if variable is defined, this value would be > 0 
														int var_index = xE[e_strata_id][i][s5R][s6R][1 + iter];
														String prescription_and_row_id = var_info_array[var_index].get_prescription() + " " + var_info_array[var_index].get_row_id();
														String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
														if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
														map_var_index_to_var_state_id.put(var_index, state_id);
													}
												}
											}
										}
									}
								}
								
								// sorted LinkedHashMap by values
								LinkedHashMap<Integer, String> sorted_map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();
								map_var_index_to_var_state_id.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
									sorted_map_var_index_to_var_state_id.put(entry.getKey(), entry.getValue());
								});
								map_var_index_to_var_state_id = null;	// delete the unsorted map
	//							// test printing blocks with same state_id
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
														
	//							// test printing:
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
	
							// 5b For regenerated variables xR
							for (int r_strata_id = 0; r_strata_id < total_R_model_strata; r_strata_id++) {
								LinkedHashMap<Integer, String> map_var_index_to_var_state_id = new LinkedHashMap<Integer, String>();	// map all relevant variables in iteration 1+M
								for (int i : R_prescription_ids[r_strata_id]) {
									if(xR[r_strata_id][i] != null) {
										int LEN1 = xR[r_strata_id][i].length;
										for (int s5R = 0; s5R < LEN1; s5R++) {
											if(xR[r_strata_id][i][s5R] != null) {
												int LEN2 = xR[r_strata_id][i][s5R].length;
												for (int s6R = 0; s6R < LEN2; s6R++) {
													if(xR[r_strata_id][i][s5R][s6R] != null) {
														int t = 1 + iter;
														for (int a = 2; a <= t - 1; a++) {		// to exclude regenerated variables at age class 1
															if(xR[r_strata_id][i][s5R][s6R][1 + iter] != null		// t = 1+ iter
																	&& xR[r_strata_id][i][s5R][s6R][1 + iter][a] > 0) {		// if variable is defined, this value would be > 0
																int var_index = xR[r_strata_id][i][s5R][s6R][1 + iter][a];
																String prescription_and_row_id = var_info_array[var_index].get_prescription() + " " + var_info_array[var_index].get_row_id();
																String state_id = map_prescription_and_row_id_to_state_id.get(prescription_and_row_id);
																if (state_id == null) state_id = ""; // the case this var has prescription but missing row_id
																map_var_index_to_var_state_id.put(var_index, state_id);
															}
														}
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
					
					for (int e_strata_id = 0; e_strata_id < total_E_model_strata; e_strata_id++) {
						Set<Integer> filter_set;
						
						// non-clear-cut prescriptions (E_0)
						filter_set = new HashSet<Integer>(set_of_prescription_ids_for_E_strata[e_strata_id]);
						filter_set.retainAll(E_0_prescription_ids[e_strata_id]);	// filter out the E_0 prescriptions only
						for (int i : filter_set) {
							int s5R = 0;
							int s6R = 0;
							for (int t = 1 + iter; t <= total_periods + iter - 1; t++) {	// --> always loop to the ending period of the horizon (allow missing row ids)
								// Add constraint
								c6_indexlist.add(new ArrayList<Integer>());
								c6_valuelist.add(new ArrayList<Double>());
								
								// Add xE[s1,s2,s3,s4,s5,s6][s5R][i][t]
								int var_index = xE[e_strata_id][i][s5R][s6R][t];
								double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
								double total_loss_rate = 0;
								for (int k = 0; k < total_disturbances; k++) {
									total_loss_rate = total_loss_rate + user_loss_rate[k] / 100;
								}
								
								if (total_loss_rate != 1) {	// only add if parameter is non zero
									c6_indexlist.get(c6_num).add(xE[e_strata_id][i][s5R][s6R][t]);
									c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
								}
								
								// Add -xE[s1,s2,s3,s4,s5,s6][i][s5R][s6R][+1]
								c6_indexlist.get(c6_num).add(xE[e_strata_id][i][s5R][s6R][t + 1]);
								c6_valuelist.get(c6_num).add((double) -1);
								
								// Add bounds
								c6_lblist.add((double) 0);
								c6_ublist.add((double) 0);
								c6_num++;
							}						
						}
						
						// clear-cut prescriptions (E_1)
						filter_set = new HashSet<Integer>(set_of_prescription_ids_for_E_strata[e_strata_id]);
						filter_set.retainAll(E_1_prescription_ids[e_strata_id]);	// filter out the E_1 prescriptions only
						for (int i : filter_set) {
							int rotation_period = total_rows_of_precription[i]; // tR = total rows of this prescription
							int rotation_age = rotation_period + starting_age_class_for_prescription[i] - 1;
							int T_FINAL = Math.min(rotation_period, total_periods + iter);	// --> always loop to the rotation period (if tR within the horizon) or to the ending period of the horizon (if tR out of the planning horizon)
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								for (int s6R = 0; s6R < total_layer6; s6R++) {
									if (has_R_prescriptions[s5R][s6R] && set_of_prescription_ids_for_E_strata_with_s5R_s6R[e_strata_id][s5R][s6R].contains(i)) {	// if this prescription leads to s5R s6R regeneration
										for (int t = 1 + iter; t <= T_FINAL - 1; t++) {		// this loop guarantees that prescriptions with clear-cut beyond planning horizon are allowed
											// Add constraint
											c6_indexlist.add(new ArrayList<Integer>());
											c6_valuelist.add(new ArrayList<Double>());
											
											// Add xE[s1,s2,s3,s4,s5,s6][i][s5R][s6R][t]
											int var_index = xE[e_strata_id][i][s5R][s6R][t];
											double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
											double total_loss_rate = 0;
											for (int k = 0; k < total_disturbances; k++) {
												total_loss_rate = total_loss_rate + user_loss_rate[k] / 100;
											}
											
											if (total_loss_rate != 1) {	// only add if parameter is non zero
												c6_indexlist.get(c6_num).add(xE[e_strata_id][i][s5R][s6R][t]);
												c6_valuelist.get(c6_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
											}
											
											// Add - xE[s1,s2,s3,s4,s5,s6][i][s5R][s6R][t+1]	
											c6_indexlist.get(c6_num).add(xE[e_strata_id][i][s5R][s6R][t + 1]);
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
				

					
					// Constraints 7
					// 7a
					// Note: only store the F~ parameters associated the simulation of stochastic disturbances on the first period solution in previous iteration (iter = M)
					LinkedHashMap<String, Double> map_F_name_to_stochastic_F_value = new LinkedHashMap<String, Double>();
					if (iter >= 1) {
						// Loop writing in this way will improve speed. This is also applied to eq. 15 to save running time. Other equations are fast so it is not needed to use this type of loop
						for (String strata_4layers: model_strata_4layers) {
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								for (int s6R = 0; s6R < total_layer6; s6R++) {
									if (has_R_prescriptions[s5R][s6R]) {
										for (int t = iter; t <= iter; t++) {		// t = M
											double total_value_for_this_F = 0;		// Note note note I am testing using the deterministic mean
											
											// Add existing variables: Sigma(s5,s6,s5R',s6R',i) xE[s1,s2,s3,s4,s5,s6][s5R'][s6R'][i][t] 	--> : X~
											// Add regeneration variables: Sigma(s5,s6,s5R',s6R',i,a) xR[s1][s2][s3][s4][s5][s6][s5R'][s6R'][i][t][a] 	--> : X~
											for (int s5 = 0; s5 < total_layer5; s5++) {
												for (int s6 = 0; s6 < total_layer6; s6++) {
													String strata = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
													
													// X~E
													int e_strata_id = (map_E_strata_to_strata_id.get(strata) != null) ? map_E_strata_to_strata_id.get(strata) : -1;
													if (e_strata_id >= 0) {		// == if model_strata.contains(strata_name)   --   strata_id = -1 means list does not contain the string
														for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
															for (int s6RR = 0; s6RR < total_layer6; s6RR++) {	// s6R'
																for (int i : E_prescription_ids[e_strata_id]) {		// It is very important to not use null check for jagged arrays here to avoid the incorrect of mapping
																	String var_name = "x_E_" + strata + "_" + layer5.get(s5RR) + "_" + layer6.get(s6RR) + "_" + i + "_" + t;
																	if (map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != null) {
																		int[] rd_condition_id = map_var_name_to_var_rd_condition_id.get(var_name);
																		double total_loss_rate_for_this_conversion = 0;
																		for (int k = 0; k < total_disturbances; k++) {
																			if (rd_condition_id[k] != -9999) {
																				double[][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_condition_id[k]);
																				total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (map_var_name_to_var_new_loss_rates.get(var_name)[k] / 100) * (conversion_rate_mean[s5R][s6R] / 100);
																			}
																		}
																		total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
																	}
																}
															}
														}
													}
													
													//  X~R
													if ((iter == 0 && t >= 2) || (iter >= 1)) {		 // if there is only iteration 0 then we add regeneration variables for period >= 2 only		
														int r_strata_id = (map_R_strata_to_strata_id.get(strata) != null) ? map_R_strata_to_strata_id.get(strata) : -1;
														if (r_strata_id >= 0) {
															for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
																for (int s6RR = 0; s6RR < total_layer6; s6RR++) {	// s6R'
																	for (int i : R_prescription_ids[r_strata_id]) {
																		for (int a = 1; a <= t - 1; a++) {	
																			String var_name = "x_R_" + strata + "_" + layer5.get(s5RR) + "_" + layer6.get(s6RR) + "_" + i + "_" + t + "_" + a;
																			if (map_var_name_to_var_rd_condition_id.get(var_name) != null && map_var_name_to_var_rd_condition_id.get(var_name) != null) {
																				int[] rd_condition_id = map_var_name_to_var_rd_condition_id.get(var_name);
																				double total_loss_rate_for_this_conversion = 0;
																				for (int k = 0; k < total_disturbances; k++) {
																					if (rd_condition_id[k] != -9999) {
																						double[][] conversion_rate_mean = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(rd_condition_id[k]);
																						total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (map_var_name_to_var_new_loss_rates.get(var_name)[k] / 100) * (conversion_rate_mean[s5R][s6R] / 100);
																					}
																				}
																				total_value_for_this_F = total_value_for_this_F + total_loss_rate_for_this_conversion * map_var_name_to_var_value.get(var_name);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											
											// Map	fire[s1][s2][s3][s4][s5R][s6R][t]
											String f_var_name = "f_" + strata_4layers + "_" + layer5.get(s5R) + "_" + layer6.get(s6R)+ "_" + t;
											map_F_name_to_stochastic_F_value.put(f_var_name, total_value_for_this_F);	
										}
									}
								}
							}
						}
					}
					
					
					
					// 7b
					List<List<Integer>> c7_indexlist = new ArrayList<List<Integer>>();	
					List<List<Double>> c7_valuelist = new ArrayList<List<Double>>();
					List<Double> c7_lblist = new ArrayList<Double>();	
					List<Double> c7_ublist = new ArrayList<Double>();
					int c7_num = 0; 
					
					// Loop writing in this way will improve speed. This is also applied to eq. 15 to save running time. Other equations are fast so it is not needed to use this type of loop
					for (String strata_4layers: model_strata_4layers) {
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							for (int s6R = 0; s6R < total_layer6; s6R++) {
								if (has_R_prescriptions[s5R][s6R]) {
									for (int t = 1 + iter; t <= total_periods + iter; t++) {
										// Add constraint
										c7_indexlist.add(new ArrayList<Integer>());
										c7_valuelist.add(new ArrayList<Double>());
										
										// Add	fire[s1][s2][s3][s4][s5R][s6R][t]
										String f_strata = strata_4layers + "_" + layer5.get(s5R) + "_" + layer6.get(s6R);
										int f_strata_id = (map_R_strata_to_strata_id.get(f_strata) != null) ? map_R_strata_to_strata_id.get(f_strata) : -1;
										c7_indexlist.get(c7_num).add(fire[f_strata_id][t]);
										c7_valuelist.get(c7_num).add((double) 1);	
										
										// Testing this very fast method
										// Add existing variables: 		-sigma(s5,s6,s5R',s6R',i) xE(s1,s2,s3,s4,s5,s6)(i)(s5R')(s6R')(t)	----------------------------------------------
										// Add regeneration variables	-sigma(s5,s6,s5R',s6R',i,a)	xR[s1][s2][s3][s4][s5][s6][i][s5R'][s6R'][t][a]	----------------------------------------------
										if (map_model_strata_4layers_to_strata_id.get(strata_4layers) != null) {
											int strata_4layers_id = map_model_strata_4layers_to_strata_id.get(strata_4layers);
											for (int var_index : set_of_x_index_for_7b[strata_4layers_id][t]) {	// include x with period = rotation_period is ok because the user_loss_rate is set to 0 for those cases 
												if (var_rd_condition_id[var_index] != null) {		// if there is replacing disturbance associated with this variable
													double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
													double[][][] conversion_rate_mean = new double[total_disturbances][][];
													double total_loss_rate_for_this_conversion = 0;
													for (int k = 0; k < total_disturbances; k++) {
														if (var_rd_condition_id[var_index][k] != -9999) {
															conversion_rate_mean[k] = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index][k]);
															total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (user_loss_rate[k] / 100) * (conversion_rate_mean[k][s5R][s6R] / 100);
														}
													}
													if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
														c7_indexlist.get(c7_num).add(var_index);
														c7_valuelist.get(c7_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
													}
												}
											}
										}
										
										
										
										
										
//										for (int s5 = 0; s5 < total_layer5; s5++) {
//											for (int s6 = 0; s6 < total_layer6; s6++) {
//												String strata = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
//												
//												// -xE
//												int e_strata_id = (map_E_strata_to_strata_id.get(strata) != null) ? map_E_strata_to_strata_id.get(strata) : -1;
//												if (e_strata_id >= 0) {		// == if model_strata.contains(strata_name)   --   strata_id = -1 means list does not contain the string
//													if (xE[e_strata_id] != null) {
//														for (int s5RR = 0; s5RR < total_layer5; s5RR++) {		// s5R'
//															if (xE[e_strata_id][s5RR] != null) {
//																for (int s6RR = 0; s6RR < total_layer6; s6RR++) {	// s6R'
//																	if (xE[e_strata_id][s5RR][s6RR] != null) {
//																		for (int i : E_prescription_ids[e_strata_id]) {
//																			if (xE[e_strata_id][s5RR][s6RR][i] != null) { 
//																				int var_index = xE[e_strata_id][s5RR][s6RR][i][t];
//																				if (var_index > 0 && var_rd_condition_id[var_index] != null) {		// if variable is defined (this value would be > 0) and there is replacing disturbance associated with this variable
//																					double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
//																					double[][][] conversion_rate_mean = new double[total_disturbances][][];
//																					double total_loss_rate_for_this_conversion = 0;
//																					for (int k = 0; k < total_disturbances; k++) {
//																						if (var_rd_condition_id[var_index][k] != -9999) {
//																							conversion_rate_mean[k] = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index][k]);
//																							total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (user_loss_rate[k] / 100) * (conversion_rate_mean[k][s5R][s6R] / 100);
//																						}
//																					}
//																					if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
//																						c7_indexlist.get(c7_num).add(var_index);
//																						c7_valuelist.get(c7_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
//																					}
//																				}
//																			}
//																		}
//																	}
//																}
//															}
//														}
//													}												
//												}
//												
//												// -xR
//												if ((iter == 0 && t >= 2) || (iter >= 1)) {		 // if there is only iteration 0 then we add regeneration variables for period >= 2 only
//													int r_strata_id = (map_R_strata_to_strata_id.get(strata) != null) ? map_R_strata_to_strata_id.get(strata) : -1;
//													if (r_strata_id >= 0) {
//														for (int s5RR = 0; s5RR < total_layer5; s5RR++) {
//															for (int s6RR = 0; s6RR < total_layer6; s6RR++) {	// s6R'
//																for (int i : R_prescription_ids[r_strata_id]) {
//																	for (int a = 1; a <= t - 1; a++) {	
//																		if(xR[r_strata_id][s5RR] != null
//																				&& xR[r_strata_id][s5RR][s6RR] != null
//																					&& xR[r_strata_id][s5RR][s6RR][i] != null
//																						&& xR[r_strata_id][s5RR][s6RR][i][t] != null) {	// if variable is defined, this value would be > 0 
//																			int var_index = xR[r_strata_id][s5RR][s6RR][i][t][a];
//																			if (var_index > 0 && var_rd_condition_id[var_index] != null) {		// if variable is defined, this value would be > 0 
//																				double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
//																				double[][][] conversion_rate_mean = new double[total_disturbances][][];
//																				double total_loss_rate_for_this_conversion = 0;
//																				for (int k = 0; k < total_disturbances; k++) {
//																					 if (var_rd_condition_id[var_index][k] != -9999) {
//																						conversion_rate_mean[k] = disturbance_info.get_conversion_rate_mean_from_rd_condition_id(var_rd_condition_id[var_index][k]);
//																						total_loss_rate_for_this_conversion = total_loss_rate_for_this_conversion + (user_loss_rate[k] / 100) * (conversion_rate_mean[k][s5R][s6R] / 100);
//																					 }
//																				}
//																				if (total_loss_rate_for_this_conversion != 0) {	// only add if parameter is non zero
//																					c7_indexlist.get(c7_num).add(var_index);
//																					c7_valuelist.get(c7_num).add((double) - total_loss_rate_for_this_conversion);		// SR Fire loss Rate = P(s5, s5R --> x)
//																				}
//																			}
//																		}
//																	}
//																}
//															}
//														}	
//													}
//												}
//											}
//										}
			
										// Add bounds
										c7_lblist.add((double) 0);
										c7_ublist.add((double) 0);
										c7_num++;
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
	
					// Clear lists to save memory
					c7_indexlist = null;	
					c7_valuelist = null;
					c7_lblist = null;	
					c7_ublist = null;
					System.out.println("Total constraints as in PRISM model formulation eq. (7):   " + c7_num + "             " + dateFormat.format(new Date()));
					
					
					
					// Constraints 8
					List<List<Integer>> c8_indexlist = new ArrayList<List<Integer>>();	
					List<List<Double>> c8_valuelist = new ArrayList<List<Double>>();
					List<Double> c8_lblist = new ArrayList<Double>();	
					List<Double> c8_ublist = new ArrayList<Double>();
					int c8_num = 0;
					
					// 8a
					if (iter >= 1) {
						for (String strata_4layers: model_strata_4layers) {
							for (int s5R = 0; s5R < total_layer5; s5R++) {
								for (int s6R = 0; s6R < total_layer6; s6R++) {
									if (has_R_prescriptions[s5R][s6R]) {
										for (int t = iter; t <= iter; t++) {	// t=M									
											// Add constraint
											c8_indexlist.add(new ArrayList<Integer>());
											c8_valuelist.add(new ArrayList<Double>());
											
											
											// FIRE VARIABLES: F~
											// Add -fire[s1,s2,s3,s4,s5R,s6R][t] 	--> -F~
											String f_var_name = "f_" + strata_4layers + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + t;
											double value_of_RHS = map_F_name_to_stochastic_F_value.get(f_var_name);
											
			
											// NON-FIRE VARIABLES: X   (for only variables with clear-cuts)
											// Add sigma(s5)(s6)(i) xE(s1,s2,s3,s4,s5,s6)[s5R][s6R][i][t=tR] 	--> : X
											for (int s5 = 0; s5 < total_layer5; s5++) {
												for (int s6 = 0; s6 < total_layer6; s6++) {
													String e_strata = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
													int e_strata_id = (map_E_strata_to_strata_id.get(e_strata) != null) ? map_E_strata_to_strata_id.get(e_strata) : -1;
													if (e_strata_id >= 0) {
														for (int i : E_1_prescription_ids[e_strata_id]) {
															int rotation_period = total_rows_of_precription[i]; // tR = total rows of this prescription
															if (t == rotation_period) {		// It is very important to not use null check for jagged arrays here to avoid the incorrect of mapping
																String var_name = "x_E_" + e_strata + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + i + "_" + t;
																if (map_var_name_to_var_value.get(var_name) != null) {
																	value_of_RHS = value_of_RHS + map_var_name_to_var_value.get(var_name);
																}
															}
														}
													}
												}
											}
											
											// Add sigma(s5)(s6)(i)(a) xR[s1][s2][s3][s4][s5][s6][s5R][s6R][i][t][a=aR] 	--> : X
											for (int s5 = 0; s5 < total_layer5; s5++) {
												for (int s6 = 0; s6 < total_layer6; s6++) {
													String r_strata = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
													int r_strata_id = (map_R_strata_to_strata_id.get(r_strata) != null) ? map_R_strata_to_strata_id.get(r_strata) : -1;
													if (r_strata_id >= 0) {
														for (int i : R_1_prescription_ids[r_strata_id]) {
															int rotation_age = total_rows_of_precription[i];
															for (int a = 1; a <= t - 1; a++) {	
																if (a == rotation_age) {	// It is very important to not use null check for jagged arrays here to avoid the incorrect of mapping
																	String var_name = "x_R_" + r_strata + "_" + layer5.get(s5R) + "_" + layer6.get(s6R) + "_" + i + "_" + t + "_" + a;
																	if (map_var_name_to_var_value.get(var_name) != null) {
																		value_of_RHS = value_of_RHS + map_var_name_to_var_value.get(var_name);
																	}
																}
															}
														}
													}
												}
											}
											
											
											// Add -sigma(s5R')(s6R')(i) xR(s1,s2,s3,s4,s5,s6][i][s5R'][s6R'][t+1][1]
											String r_strata = strata_4layers + "_" + layer5.get(s5R) + "_" + layer6.get(s6R);		// = s1,s2,s3,s4,s5R,s6R
											int r_strata_id = (map_R_strata_to_strata_id.get(r_strata) != null) ? map_R_strata_to_strata_id.get(r_strata) : -1;
											if (r_strata_id >= 0) {
												for (int i : R_prescription_ids[r_strata_id]) {
													if(xR[r_strata_id][i] != null) {
														int LEN1 = xR[r_strata_id][i].length;
														for (int s5RR = 0; s5RR < LEN1; s5RR++) {
															if (xR[r_strata_id][i][s5RR] != null) {
																int LEN2 = xR[r_strata_id][i][s5RR].length;
																for (int s6RR = 0; s6RR < LEN2; s6RR++) {
																	if(xR[r_strata_id][i][s5RR][s6RR] != null
																			&& xR[r_strata_id][i][s5RR][s6RR][t + 1] != null
																				&& xR[r_strata_id][i][s5RR][s6RR][t + 1][1] > 0) {	// if variable is defined, this value would be > 0 
																		c8_indexlist.get(c8_num).add(xR[r_strata_id][i][s5RR][s6RR][t + 1][1]);
																		c8_valuelist.get(c8_num).add((double) -1);
																	}
																}
															}
														}
													}
												}
											}
											
											// Add bounds
											c8_lblist.add((double) - value_of_RHS);
											c8_ublist.add((double) - value_of_RHS);
											c8_num++;
										}
									}
								}
							}							
						}
					}
					
					// 8b
					for (String strata_4layers: model_strata_4layers) {
						for (int s5R = 0; s5R < total_layer5; s5R++) {
							for (int s6R = 0; s6R < total_layer6; s6R++) {
								if (has_R_prescriptions[s5R][s6R]) {
									for (int t = 1 + iter; t <= total_periods - 1 + iter; t++) {										
										// Add constraint
										c8_indexlist.add(new ArrayList<Integer>());
										c8_valuelist.add(new ArrayList<Double>());
										
										
										// FIRE VARIABLES
										// Add fire[s1,s2,s3,s4,s5R,s6R][t]
										String f_strata = strata_4layers + "_" + layer5.get(s5R) + "_" + layer6.get(s6R);
										int f_strata_id = (map_R_strata_to_strata_id.get(f_strata) != null) ? map_R_strata_to_strata_id.get(f_strata) : -1;
										c8_indexlist.get(c8_num).add(fire[f_strata_id][t]);
										c8_valuelist.get(c8_num).add((double) 1);
										
			
										// NON-FIRE VARIABLES:	(for only variables with clear-cuts)
										// Add sigma(s5)(s6)(i) xE[s1,s2,s3,s4,s5,s6][i][s5R][s6R][t=tR]
										for (int s5 = 0; s5 < total_layer5; s5++) {
											for (int s6 = 0; s6 < total_layer6; s6++) {
												String e_strata = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);
												int e_strata_id = (map_E_strata_to_strata_id.get(e_strata) != null) ? map_E_strata_to_strata_id.get(e_strata) : -1;
												if (e_strata_id >= 0) {
													for (int i : E_1_prescription_ids[e_strata_id]) {	
														int rotation_period = total_rows_of_precription[i]; // tR = total rows of this prescription
														if (t == rotation_period
																&& xE[e_strata_id][i] != null
																	&& xE[e_strata_id][i][s5R] != null
																		&& xE[e_strata_id][i][s5R][s6R] != null
																			&& xE[e_strata_id][i][s5R][s6R][t] > 0) {		// if variable is defined, this value would be > 0
															c8_indexlist.get(c8_num).add(xE[e_strata_id][i][s5R][s6R][t]);
															c8_valuelist.get(c8_num).add((double) 1);
														}
													}
												}
											}
										}
										
										// Add sigma(s5)(s6)(i)(a) xR[s1][s2][s3][s4][s5][s6][i][s5R][s6R][t][a=aR]
										for (int s5 = 0; s5 < total_layer5; s5++) {
											for (int s6 = 0; s6 < total_layer6; s6++) {
												String r_strata = strata_4layers + "_" + layer5.get(s5) + "_" + layer6.get(s6);		// = s1,s2,s3,s4,s5,s6
												int r_strata_id = (map_R_strata_to_strata_id.get(r_strata) != null) ? map_R_strata_to_strata_id.get(r_strata) : -1;
												if (r_strata_id >= 0) {
													for (int i : R_1_prescription_ids[r_strata_id]) {
														int rotation_age = total_rows_of_precription[i]; 
														for (int a = 1; a <= t - 1; a++) {	
															if (a == rotation_age 
																	&& xR[r_strata_id][i] != null
																			&& xR[r_strata_id][i][s5R] != null
																				&& xR[r_strata_id][i][s5R][s6R] != null
																					&& xR[r_strata_id][i][s5R][s6R][t] != null
																						&& xR[r_strata_id][i][s5R][s6R][t][a] > 0) {	// if variable is defined, this value would be > 0
																c8_indexlist.get(c8_num).add(xR[r_strata_id][i][s5R][s6R][t][a]);
																c8_valuelist.get(c8_num).add((double) 1);
															}
														}
													}
												}
											}
										}
										
										
										// Add -sigma(s5R')(s6R')(i) xR(s1,s2,s3,s4,s5,s6][i][s5R'][s6R'][t+1][1]
										String r_strata = strata_4layers + "_" + layer5.get(s5R) + "_" + layer6.get(s6R);		// = s1,s2,s3,s4,s5R,s6R = f_strata (same first 6 indexes)
										int r_strata_id = (map_R_strata_to_strata_id.get(r_strata) != null) ? map_R_strata_to_strata_id.get(r_strata) : -1;
										if (r_strata_id >= 0) {
											for (int i : R_prescription_ids[r_strata_id]) {
												if(xR[r_strata_id][i] != null) {
													int LEN1 = xR[r_strata_id][i].length;
													for (int s5RR = 0; s5RR < LEN1; s5RR++) {
														if (xR[r_strata_id][i][s5RR] != null) {
															int LEN2 = xR[r_strata_id][i][s5RR].length;
															for (int s6RR = 0; s6RR < LEN2; s6RR++) {
																if(xR[r_strata_id][i][s5RR][s6RR] != null
																		&& xR[r_strata_id][i][s5RR][s6RR][t + 1] != null
																			&& xR[r_strata_id][i][s5RR][s6RR][t + 1][1] > 0) {	// if variable is defined, this value would be > 0 
																	c8_indexlist.get(c8_num).add(xR[r_strata_id][i][s5RR][s6RR][t + 1][1]);
																	c8_valuelist.get(c8_num).add((double) -1);
																}
															}
														}
													}
												}
											}
										}
										
										// Add bounds
										c8_lblist.add((double) 0);
										c8_ublist.add((double) 0);
										c8_num++;
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
					
					// Clear lists to save memory
					c8_indexlist = null;	
					c8_valuelist = null;
					c8_lblist = null;	
					c8_ublist = null;
					System.out.println("Total constraints as in PRISM model formulation eq. (8):   " + c8_num + "             " + dateFormat.format(new Date()));
	
					
					
					// Constraints 9
					List<List<Integer>> c9_indexlist = new ArrayList<List<Integer>>();	
					List<List<Double>> c9_valuelist = new ArrayList<List<Double>>();
					List<Double> c9_lblist = new ArrayList<Double>();	
					List<Double> c9_ublist = new ArrayList<Double>();
					int c9_num = 0;
					
					for (int r_strata_id = 0; r_strata_id < total_R_model_strata; r_strata_id++) {
						String strata = R_model_strata.get(r_strata_id);
						int s5 = s5_for_R_model_strata[r_strata_id];
						int s6 = s6_for_R_model_strata[r_strata_id];
						if (has_R_prescriptions[s5][s6]) {
							Set<Integer> filter_set;
							
							// non-clear-cut prescriptions (R_0)
							filter_set = new HashSet<Integer>(set_of_prescription_ids_for_R_strata[r_strata_id]);
							filter_set.retainAll(R_0_prescription_ids[r_strata_id]);	// filter out the R_0 prescriptions only
							for (int i : filter_set) {
								int s5R = 0;
								int s6R = 0;
								int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
								for (int t = t_regen + iter; t <= total_periods - 1 + iter; t++) {
									for (int a = 1; a <= t - 1; a++) {
										// Add constraint
										c9_indexlist.add(new ArrayList<Integer>());
										c9_valuelist.add(new ArrayList<Double>());
										
										// Add xR[s1,s2,s3,s4,s5,s6][i][s5R][s6R][t][a]
										int var_index = xR[r_strata_id][i][s5R][s6R][t][a];
										double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
										double total_loss_rate = 0;
										for (int k = 0; k < total_disturbances; k++) {
											total_loss_rate = total_loss_rate + user_loss_rate[k] / 100;
										}
										if (total_loss_rate != 1) {	// only add if parameter is non zero
											c9_indexlist.get(c9_num).add(xR[r_strata_id][i][s5R][s6R][t][a]);
											c9_valuelist.get(c9_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
										}
								
										// Add - xR[s1,s2,s3,s4,s5,s6][s5R][s6R][i][t+1][a+1]		
										c9_indexlist.get(c9_num).add(xR[r_strata_id][i][s5R][s6R][t + 1][a + 1]);
										c9_valuelist.get(c9_num).add((double) -1);																					
										
										// Add bounds
										c9_lblist.add((double) 0);
										c9_ublist.add((double) 0);
										c9_num++;	
									}
								}
							}
							
							// clear-cut prescriptions (R_1)
							filter_set = new HashSet<Integer>(set_of_prescription_ids_for_R_strata[r_strata_id]);
							filter_set.retainAll(R_1_prescription_ids[r_strata_id]);	// filter out the R_1 prescriptions only
							for (int i : filter_set) {
								int rotation_age = total_rows_of_precription[i]; 
								for (int s5R = 0; s5R < total_layer5; s5R++) {
									for (int s6R = 0; s6R < total_layer6; s6R++) {
										if (has_R_prescriptions[s5R][s6R] && set_of_prescription_ids_for_R_strata_with_s5R_s6R[r_strata_id][s5R][s6R].contains(i)) {	// if this prescription leads to s5R s6R regeneration
											int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
											for (int t = t_regen + iter; t <= total_periods - 1 + iter; t++) {
												int A_FINAL = Math.min(rotation_age, t - 1);	
												for (int a = 1; a <= A_FINAL; a++) {	// this loop guarantees that prescriptions with clear-cut beyond planning horizon are allowed a <= A_FINAL --> a <= rotation_age --> rotation_age + t - rotation_period <= rotation_age --> t <= rotation_period
													if (a < rotation_age) {	// add this condition which is important. This would also make t < rotation_period
														// Add constraint
														c9_indexlist.add(new ArrayList<Integer>());
														c9_valuelist.add(new ArrayList<Double>());
														
														// Add xR(s1,s2,s3,s4,s5)[i]s5R][s6R][t][a]
														int var_index = xR[r_strata_id][i][s5R][s6R][t][a];
														double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
														double total_loss_rate = 0;
														for (int k = 0; k < total_disturbances; k++) {
															total_loss_rate = total_loss_rate + user_loss_rate[k] / 100;
														}
														if (total_loss_rate != 1) {	// only add if parameter is non zero
															c9_indexlist.get(c9_num).add(xR[r_strata_id][i][s5R][s6R][t][a]);
															c9_valuelist.get(c9_num).add((double) 1 - total_loss_rate);		// SR Fire loss Rate = P(-->x)
														}
												
														// Add - xR(s1,s2,s3,s4,s5,s6)[i][s5R][s6R][t+1][a+1]		
														c9_indexlist.get(c9_num).add(xR[r_strata_id][i][s5R][s6R][t + 1][a + 1]);
														c9_valuelist.get(c9_num).add((double) -1);																					
														
														// Add bounds
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
					
					// Clear lists to save memory
					c9_indexlist = null;	
					c9_valuelist = null;
					c9_lblist = null;	
					c9_ublist = null;
					System.out.println("Total constraints as in PRISM model formulation eq. (9):   " + c9_num + "             " + dateFormat.format(new Date()));
					System.out.println("Processing basic constraints...");
					
					
					
					// Constraints 10------------------------------------------------- for y(j) and z(k) and v(n)
					List<List<Integer>> c10_indexlist = new ArrayList<List<Integer>>();
					List<List<Double>> c10_valuelist = new ArrayList<List<Double>>();
					List<Double> c10_lblist = new ArrayList<Double>();
					List<Double> c10_ublist = new ArrayList<Double>();
					int c10_num = 0;
					int current_freeConstraint = 0;
					int current_softConstraint = 0;
					int current_hardConstraint = 0;	
					
					// Add -y(j) + user_defined_variables = 0		or 			-z(k) + user_defined_variables = 0		or 			-v(n) + user_defined_variables = 0
					for (int id = 1; id < total_freeConstraints + total_softConstraints + total_hardConstraints + 1; id++) {	//Loop from 1 because the first row of the userConstraint file is just title
						long bc_time_start = System.currentTimeMillis();		// measure time before adding each basic constraint
						
						// Add constraint
						c10_indexlist.add(new ArrayList<Integer>());
						c10_valuelist.add(new ArrayList<Double>());
	
						// Add -y(j) or -z(k) or -v(n)
						switch (bc_values[id][constraint_type_col]) {
						case "SOFT":
							c10_indexlist.get(c10_num).add(y[current_softConstraint]);
							c10_valuelist.get(c10_num).add((double) -1);
							current_softConstraint++;
							break;
						case "HARD":
							c10_indexlist.get(c10_num).add(z[current_hardConstraint]);
							c10_valuelist.get(c10_num).add((double) -1);
							current_hardConstraint++;
							break;
						case "FREE":
							c10_indexlist.get(c10_num).add(v[current_freeConstraint]);
							c10_valuelist.get(c10_num).add((double) -1);
							current_freeConstraint++;
							break;
						}
							
						
						int parameters_type = read.get_parameters_type_in_row(id);						// Get the parameter type: 0 = NoParameter, 1 = CostParameter, 2 = Others
						List<Integer> parameters_indexes = read.get_parameters_indexes_in_row(id);		// Get the parameter indexes list (not null only when parameters_type is not 0 or 1)
						List<List<String>> dynamic_identifiers = read.get_dynamic_identifiers_in_row(id);	
						List<Integer> dynamic_dentifiers_column_indexes = read.get_dynamic_identifiers_column_indexes_in_row(id);	// Get the dynamic identifiers indexes list (not null only when it is not NoIdentifier)
						
						// Add user_defined_variables and parameters------------------------------------
//						double multiplier = (!bc_values[id][multiplier_col].equals("null")) ?  Double.parseDouble(bc_values[id][multiplier_col]) : 0;	// if multiplier = null --> 0
//						LinkedHashMap<String, Integer> map_static_layer1 = read.get_map_static_layer_in_row(0, id);
//						LinkedHashMap<String, Integer> map_static_layer2 = read.get_map_static_layer_in_row(1, id);
//						LinkedHashMap<String, Integer> map_static_layer3 = read.get_map_static_layer_in_row(2, id);
//						LinkedHashMap<String, Integer> map_static_layer4 = read.get_map_static_layer_in_row(3, id);
//						LinkedHashMap<String, Integer> map_static_layer5 = read.get_map_static_layer_in_row(4, id);
//						LinkedHashMap<String, Integer> map_static_layer6 = read.get_map_static_layer_in_row(5, id);
//						LinkedHashMap<Integer, Integer> map_static_period = read.get_map_static_period_in_row(id);
//						
//						for (int var_index = 0; var_index < nvars; var_index++) {
//							Information_Variable this_var_info = var_info_array[var_index];
//							if (map_static_layer1.get(this_var_info.get_layer1()) != null
//									&& map_static_layer2.get(this_var_info.get_layer2()) != null
//									&& map_static_layer3.get(this_var_info.get_layer3()) != null
//									&& map_static_layer4.get(this_var_info.get_layer4()) != null
//									&& map_static_layer5.get(this_var_info.get_layer5()) != null
//									&& map_static_layer6.get(this_var_info.get_layer6()) != null
//									&& map_static_period.get(this_var_info.get_period()) != null) 					// period from the variable was already adjusted
//							{
//								double para_value = parameter_info.get_total_value(
//										this_var_info.get_prescription_id(),
//										this_var_info.get_row_id(),
//										parameters_type, parameters_indexes,
//										dynamic_dentifiers_column_indexes, 
//										dynamic_identifiers,
//										var_cost_value[var_index]);
//								para_value = para_value * multiplier;
//																																		
//								if (para_value > 0) {	// only add if parameter is non zero
//									c10_indexlist.get(c10_num).add(var_index);
//									c10_valuelist.get(c10_num).add((double) para_value);
//								}
//							}
//						}

						
						
						
						// This method is much more faster	
						LinkedHashMap<Integer, Integer> map_static_period = read.get_map_static_period_in_row(id);
						// List<Integer> static_periods = read.get_static_periods_in_row(id).stream().map(Integer::parseInt).collect(Collectors.toList());	// convert List<String> --> List<Integer>
						Set<String> static_strata = read.get_static_strata_in_row(id);
						double multiplier = (!bc_values[id][multiplier_col].equals("null")) ?  Double.parseDouble(bc_values[id][multiplier_col]) : 0;	// if multiplier = null --> 0
						// These 4 lines create the intersection sets, the original id could be retrieved from the 2 maps: map_E_strata_to_strata_id & map_R_strata_to_strata_id
						List<String> common_E_strata = new ArrayList<String>(E_model_strata);
						List<String> common_R_strata = new ArrayList<String>(R_model_strata);
						common_E_strata.retainAll(static_strata);
						common_R_strata.retainAll(static_strata);
						
						// Add existing variables xE[s1][s2][s3][s4][s5][s6](s5R)(s6R)(i)(t) --------------------------------------------------------------
						for (String e_strata : common_E_strata) {
							int e_strata_id = map_E_strata_to_strata_id.get(e_strata);			// Note we need index from model_strata not common_trata
							if (xE[e_strata_id] != null) {
								for (int i : E_prescription_ids[e_strata_id]) {
									if (xE[e_strata_id][i] != null) {
										int LEN1 = xE[e_strata_id][i].length;
										for (int s5R = 0; s5R < LEN1; s5R++) {		// must use length here for E_0
											if (xE[e_strata_id][i][s5R] != null) {
												int LEN2 = xE[e_strata_id][i][s5R].length;
												for (int s6R = 0; s6R < LEN2; s6R++) {	// must use length here for E_0
													if (xE[e_strata_id][i][s5R][s6R] != null) {		// if variable is defined, this value would be > 0 
														for (int t = 1 + iter; t <= total_periods + iter; t++) {	// --> always loop to the ending period of the horizon (allow missing row ids)
															int var_index = xE[e_strata_id][i][s5R][s6R][t];
															if (map_static_period.get(var_info_array[var_index].get_period()) != null) {	// check period
																double para_value = parameter_info.get_total_value(
																		var_info_array[var_index].get_prescription_id(),
																		var_info_array[var_index].get_row_id(),
																		parameters_type, parameters_indexes,
																		dynamic_dentifiers_column_indexes, 
																		dynamic_identifiers,
																		var_cost_value[var_index]);
																para_value = para_value * multiplier;
																																										
																if (para_value > 0) {	// only add if parameter is non zero
																	c10_indexlist.get(c10_num).add(var_index);
																	c10_valuelist.get(c10_num).add((double) para_value);
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
						
						// Add regenerated variables xR[s1][s2][s3][s4][s5][s6][s5R][s6R][i][t][a]--------------------------------------------------------------
						for (String r_strata : common_R_strata) {
							int r_strata_id = map_R_strata_to_strata_id.get(r_strata);			// Note we need index from model_R_strata not common_R_trata
							if (xR[r_strata_id] != null) {
								for (int i : R_prescription_ids[r_strata_id]) {
									if(xR[r_strata_id][i] != null) {
										int LEN1 = xR[r_strata_id][i].length;
										for (int s5R = 0; s5R < LEN1; s5R++) {		// must use length here for R_0
											if(xR[r_strata_id][i][s5R] != null) {
												int LEN2 = xR[r_strata_id][i][s5R].length;
												for (int s6R = 0; s6R < LEN2; s6R++) {	// must use length here for R_0
													if(xR[r_strata_id][i][s5R][s6R] != null) {
														int t_regen = (iter == 0) ? 2 : 1;	// this is because iteration 0 could not have regenerated forest in period 1, but iterations >= 1 do have regenerated forest strata
														for (int t = t_regen + iter; t <= total_periods + iter; t++) {
															if(xR[r_strata_id][i][s5R][s6R][t] != null) {
																for (int a = 1; a <= t - 1; a++) {
																	int var_index = xR[r_strata_id][i][s5R][s6R][t][a];
																	if (map_static_period.get(var_info_array[var_index].get_period()) != null) {	// check period
																		double para_value = parameter_info.get_total_value(
																				var_info_array[var_index].get_prescription_id(),
																				var_info_array[var_index].get_row_id(),
																				parameters_type, parameters_indexes,
																				dynamic_dentifiers_column_indexes, 
																				dynamic_identifiers,
																				var_cost_value[var_index]);
																		para_value = para_value * multiplier;
																																												
																		if (para_value > 0) { // only add if parameter is non zero
																			c10_indexlist.get(c10_num).add(var_index);
																			c10_valuelist.get(c10_num).add((double) para_value);
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
						
						// Add bounds
						c10_lblist.add((double) 0);
						c10_ublist.add((double) 0);
						c10_num++;
						
						long bc_time_end = System.currentTimeMillis();		// measure time after reading this basic constraint
						double bc_time_reading = (double) (bc_time_end - bc_time_start) / 1000;
						System.out.println("           - Time (seconds) for reading basic constraint " + bc_values[id][constraint_id_col] + " - " + bc_values[id][constraint_description_col] + "             " + bc_time_reading);
					}
					
					// Clear arrays not used any more before final step of constraint 10 & solving -------------------------------------------------------------
	//				xE = null;
	//				xR = null;		
	//				var_cost_value = null;
	//				var_rd_condition_id = null;
					
					cost_info = null;
					parameter_info = null;
	//				disturbance_info = null;
	//				System.gc();		Not call this to avoid poor performance. just let the collector auto starts when needed.	
					
											
	
					double[] c10_lb = Stream.of(c10_lblist.toArray(new Double[c10_lblist.size()])).mapToDouble(Double::doubleValue).toArray();
					c10_lblist = null;	// Clear lists to save memory
					double[] c10_ub = Stream.of(c10_ublist.toArray(new Double[c10_ublist.size()])).mapToDouble(Double::doubleValue).toArray();		
					c10_ublist = null;	// Clear lists to save memory
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
					c10_indexlist = null;	// Clear lists to save memory
					c10_valuelist = null;	// Clear lists to save memory						
					System.out.println("Total constraints as in PRISM model formulation eq. (10):   " + c10_num + "             " + dateFormat.format(new Date()));
					
					
					
					// Constraints 11 (flow)------------------------------------------------This is equation (11) in Prism-Formulation-10
					List<List<Integer>> c11_indexlist = new ArrayList<List<Integer>>();
					List<List<Double>> c11_valuelist = new ArrayList<List<Double>>();
					List<Double> c11_lblist = new ArrayList<Double>();
					List<Double> c11_ublist = new ArrayList<Double>();
					int c11_num = 0;
							
					
					List<Integer> bookkeeping_ID_list = new ArrayList<Integer>();	// This list contains all GUI - IDs of the Basic Constraints
					List<Integer> bookkeeping_Var_list = new ArrayList<Integer>();			// This list contains all SOLVER Variables - IDs of the Basic Constraints
					if (flow_set_list.size() > 0) {		// Add flow constraints if there is at least a flow set
						current_freeConstraint = 0;
						current_softConstraint = 0;
						current_hardConstraint = 0;	
						
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
										
						
						/*	
						  Add constraints for each flow set 
						  Example:
						  
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
										c11_indexlist.add(new ArrayList<Integer>());
										c11_valuelist.add(new ArrayList<Double>());
										
										// Add Final term
										for (int item = 0; item < final_sigma_id_list.size(); item++) {
											int gui_table_bc_id = final_sigma_id_list.get(item);
											int var_id = bookkeeping_Var_list.get(gui_table_bc_id);
											double var_value = final_sigma_LB_parameter_list.get(item);
											c11_indexlist.get(c11_num).add(var_id);
											c11_valuelist.get(c11_num).add((double) var_value);
										}
										
										// Add bounds
										c11_lblist.add((double) 0);			// Lower bound set to 0	
										c11_ublist.add(Double.MAX_VALUE);	// Upper bound set to max
										c11_num++;
									}
									
									
									if (flow_upperbound_percentage_list.get(i) != null) {	// add when upperbound_percentage is not null
										// Add constraint				Final term = Right term - UB% * Left term <= 0
										c11_indexlist.add(new ArrayList<Integer>());
										c11_valuelist.add(new ArrayList<Double>());
										
										// Add Final term
										for (int item = 0; item < final_sigma_id_list.size(); item++) {
											int gui_table_bc_id = final_sigma_id_list.get(item);
											int var_id = bookkeeping_Var_list.get(gui_table_bc_id);
											double var_value = final_sigma_UB_parameter_list.get(item);
											c11_indexlist.get(c11_num).add(var_id);
											c11_valuelist.get(c11_num).add((double) var_value);
										}
										
										// Add bounds
										c11_lblist.add(-Double.MAX_VALUE);	// lower bound set to min	
										c11_ublist.add((double) 0);			// Upper bound set to 0
										c11_num++;
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
					
					// Clear lists to save memory
					c11_indexlist = null;	
					c11_valuelist = null;
					c11_lblist = null;	
					c11_ublist = null;
					System.out.println("Total constraints as in PRISM model formulation eq. (11):   " + c11_num + "             " + dateFormat.format(new Date()) + "\n");
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
							
					
					
					
					// Solve problem----------------------------------------------------------------------------------------------				
	//				// Set constraints set name: Notice THIS WILL EXTREMELY SLOW THE SOLVING PROCESS (recommend for debugging only)
	//				int indexOfC2 = c2_num;
	//				int indexOfC5 = indexOfC2 + c5_num;
	//				int indexOfC6 = indexOfC5 + c6_num;
	//				int indexOfC7 = indexOfC6 + c7_num;
	//				int indexOfC8 = indexOfC7 + c8_num;
	//				int indexOfC9 = indexOfC8 + c9_num;
	//				int indexOfC10 = indexOfC9 + c10_num;
	//				int indexOfC11 = indexOfC10 + c11_num;	// Note: lp.getRanges().length = indexOfC11
	//				
	//				for (int i = 0; i < lp.getRanges().length; i++) {		
	//					if (0 <= i && i < indexOfC2) lp.getRanges() [i].setName("eq.2.");
	//					if (indexOfC2 <= i && i < indexOfC5) lp.getRanges() [i].setName("eq.5.");
	//					if (indexOfC5 <= i && i < indexOfC6) lp.getRanges() [i].setName("eq.6.");
	//					if (indexOfC6 <= i && i < indexOfC7) lp.getRanges() [i].setName("eq.7.");
	//					if (indexOfC7 <= i && i < indexOfC8) lp.getRanges() [i].setName("eq.8.");
	//					if (indexOfC8 <= i && i < indexOfC9) lp.getRanges() [i].setName("eq.9.");
	//					if (indexOfC9 <= i && i < indexOfC10) lp.getRanges() [i].setName("eq.10.");
	//					if (indexOfC10 <= i && i < indexOfC11) lp.getRanges() [i].setName("eq.11.");
	//				}
					time_end = System.currentTimeMillis();		// measure time after reading
					time_reading = (double) (time_end - time_start) / 1000;
					
					
					
	//				// Add the CPLEX native library path dynamically at run time   (this is just for solving by CPLEX while running PRISM in Eclipse IDE, we do not have some .dll in Temporary folder in this case, for i.e. cple1261.dll)
	//				try {
	////					LibraryHandle.setLibraryPath("C:\\Users\\Dung Nguyen\\Desktop\\Temporary");
	//					LibraryHandle.addLibraryPath("C:\\Users\\Dung Nguyen\\Desktop\\Temporary");		// (Failed in JDK 13) 
	//					System.out.println("C:\\Users\\Dung Nguyen\\Desktop\\Temporary"  + " has been added to the java library paths");
	//				} catch (Exception e) {
	//					System.err.println("cplex error - Developer's computer is not found. " + e.getClass().getName() + ": " + e.getMessage());
	//
	//				}
	//				
	//				// Add the CPLEX native library path dynamically at run time. When running PRISM outside of Eclipse IDE --> Temporary folder should have all .dll including cplex1280.dll
	//				try {
	////					LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	// This will clear all other paths (many) and set path to the "temporary" folder
	//					LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	// (Failed in JDK 13) This will NOT clear all other paths, and will add the "temporary" folder to the paths
	//					System.out.println(FilesHandle.get_temporaryFolder().getAbsolutePath().toString() + " has been added to the java library paths");	
	//					
	//					System.out.println("Prism found the below java library paths:");
	//					String property = System.getProperty("java.library.path");
	//					StringTokenizer parser = new StringTokenizer(property, ";");
	//					while (parser.hasMoreTokens()) {
	//						System.out.println("           - " + parser.nextToken());
	//					}
	//				} catch (Exception e) {
	//					System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
	//				}
					// Print the paths containing library files (e.g. cplex1280.dll, lpsolve55.dll, lpsolve55j.dll)
					System.out.println("Prism found the below java library paths:");
					String property = System.getProperty("java.library.path");
					StringTokenizer parser = new StringTokenizer(property, ";");
					while (parser.hasMoreTokens()) {
						System.out.println("           - " + parser.nextToken());
					}
	
					
					
					if (solver_for_optimization.equals("CPLEX")) {
						prismcplex.Cplex_Wrapper cplex_wrapper = new prismcplex.Cplex_Wrapper(nvars, vlb, vub, vname, objvals, solving_time_limit);		vlb = null; vub = null; objvals = null;	// cannot clear vame because it is going to be used
						// add constraints & Clear arrays to save memory
						cplex_wrapper.addRows(c2_lb, c2_ub, c2_index, c2_value); 	c2_lb = null;  c2_ub = null;  c2_index = null;  c2_value = null;	// Constraints 2
						cplex_wrapper.addRows(c5_lb, c5_ub, c5_index, c5_value); 	c5_lb = null;  c5_ub = null;  c5_index = null;  c5_value = null;	// Constraints 5
						cplex_wrapper.addRows(c6_lb, c6_ub, c6_index, c6_value); 	c6_lb = null;  c6_ub = null;  c6_index = null;  c6_value = null;	// Constraints 6
						cplex_wrapper.addRows(c7_lb, c7_ub, c7_index, c7_value); 	c7_lb = null;  c7_ub = null;  c7_index = null;  c7_value = null;	// Constraints 7
						cplex_wrapper.addRows(c8_lb, c8_ub, c8_index, c8_value); 	c8_lb = null;  c8_ub = null;  c8_index = null;  c8_value = null;	// Constraints 8
						cplex_wrapper.addRows(c9_lb, c9_ub, c9_index, c9_value); 	c9_lb = null;  c9_ub = null;  c9_index = null;  c9_value = null;	// Constraints 9
						cplex_wrapper.addRows(c10_lb, c10_ub, c10_index, c10_value); 	c10_lb = null;  c10_ub = null;  c10_index = null;  c10_value = null;	// Constraints 10 - Basic Constraints
						cplex_wrapper.addRows(c11_lb, c11_ub, c11_index, c11_value); 	c11_lb = null;  c11_ub = null;  c11_index = null;  c11_value = null;	// Constraints 11 - Flow Constraints
						
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
								String file_header = String.join("\t", "var_id", "var_name", "var_value", "var_reduced_cost", "var_loss_rate_total", "var_rd_condition_id", "var_global_adjustment_rd_condition_id");
								fileOut.write(file_header);
								
								for (int i = 0; i < value.length; i++) {
									if (value[i] != 0) {	// only write variable that is not zero
										fileOut.newLine();
										fileOut.write(i + "\t" + vname[i] 
												+ "\t" + Double.valueOf(value[i]) /*Double.valueOf(twoDForm.format(value[i]))*/ 
												+ "\t" + Double.valueOf(reduceCost[i])) /*Double.valueOf(twoDForm.format(reduceCost[i])))*/;
										
										if (vname[i].startsWith("x")) {
											double total_loss_rate = 0;
											int var_index = i;
											double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
											for (int k = 0; k < total_disturbances; k++) {
												total_loss_rate = total_loss_rate + Double.valueOf(user_loss_rate[k]);
											}
											fileOut.write("\t" + total_loss_rate); 
											
											if (total_disturbances == 0) {
												fileOut.write("\t" + "-9999" + "\t" + "-9999");
											} else {
												fileOut.write("\t"); 
												for (int k = 0; k < total_disturbances; k++) {
													fileOut.write(var_rd_condition_id[i][k] + " ");
												}
												fileOut.write("\t"); 
												for (int k = 0; k < total_disturbances; k++) {
													fileOut.write(var_global_adjustment_rd_condition_id[i][k] + " ");
												}
											}
										} else {
											fileOut.write("\t" + "-9999" + "\t" + "-9999" + "\t" + "-9999");
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
								List<String> activity = read_database.get_col_unique_values_list(activity_col_id);
								String file_header = String.join("\t", activity);
								file_header = "iteration" + "\t" + "period" + "\t" + "no-data"  + "\t" + file_header;
								fileOut.write(file_header);
								
								double[] no_data_area = new double[total_periods + 1];
								for (double a : no_data_area) {
									a = 0;
								}
								double[][] area = new double[total_periods + 1][activity.size()];	// area in each period for each activity
								for (double[] sub_area : area) {
									for (double a : sub_area) {
										a = 0;
									}
								}
								
								for (int i = 0; i < value.length; i++) {
									if (value[i] != 0) {	// only process variable that is not zero
										if (vname[i].startsWith("x")) {
											int var_index = i;
											int t = var_info_array[var_index].get_period();
											int prescription_id = var_info_array[var_index].get_prescription_id();
											int row_id = var_info_array[var_index].get_row_id();
											if (row_id != -9999	&& row_id < total_rows_of_precription[prescription_id]) {
												String action = yield_tables_values[prescription_id][row_id][activity_col_id];
												int activity_id = activity.indexOf(action);
												area[t][activity_id] = area[t][activity_id] + value[i];
											} else {
												no_data_area[t] = no_data_area[t] + value[i];
											}
										}
									}
								}
								
								for (int t = 1; t <= total_periods; t++) {
									fileOut.write("\n" + iter + "\t" + t + "\t" + no_data_area[t]);
									for (double a : area[t]) {
										fileOut.write("\t" + a /*Double.valueOf(twoDForm.format(area))*/);
									}
								}
								
								fileOut.close();
								activity = null;			// Clear arrays not used any more
								area = null;	// Clear arrays not used any more
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - FileWriter(output_management_overview_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_management_overview_file.createNewFile();
							
							
							// output_05_management_details
							output_management_details_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_management_details_file))) {
								fileOut.write("iteration" + "\t" + "var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost" + "\t" + "var_loss_rate_total" + "\t");
								
								for (int k = 0; k < total_disturbances; k++) {
									int disturbance_index = k + 1;
						        	String disturbance_name = (disturbance_index < 10) ? ("loss_rate_SR_0" + disturbance_index) : "loss_rate_SR_" + disturbance_index;
						        	fileOut.write(disturbance_name + "\t");
						        }
								
								fileOut.write("var_per_area_unit_cost" + "\t");
								fileOut.write("var_method" + "\t" + "var_forest_status" + "\t" + "var_layer1" + "\t" + "var_layer2" + "\t" + "var_layer3" + "\t" + "var_layer4" + "\t" + "var_layer5" + "\t" + "var_layer6"
								+ "\t" + "var_period" + "\t" + "var_age" + "\t" + "var_rotation_period" + "\t" + "var_rotation_age" + "\t" + "var_layer5_regen" + "\t"
										+ "data_connection" + "\t" + "prescription_id" + "\t" + "prescription"+ "\t" + "row_id");
	//							for (int col = 2; col < yield_tables_column_names.length; col++) {		// do not write prescription & row_id column header
	//								fileOut.write("\t" + yield_tables_column_names[col]);
	//							}
								
								
								
								for (int i = 0; i < value.length; i++) {
									if (value[i] != 0 && vname[i].startsWith("x")) {
										int var_prescription_id = var_info_array[i].get_prescription_id();
										int var_row_id = var_info_array[i].get_row_id();
	
										String data_connection = "good";
										if (total_rows_of_precription[var_prescription_id] <= var_row_id) {
											data_connection = "missing row id = " + var_row_id;
										}
	
										fileOut.newLine();
										fileOut.write(iter + "\t" + i + "\t" + vname[i] 
												+ "\t" + Double.valueOf(value[i] /*Double.valueOf(twoDForm.format(value[i])*/)
												+ "\t" + Double.valueOf(reduceCost[i + 1])); /*Double.valueOf(twoDForm.format(reduceCost[i]))*/ 	// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
										
										double total_loss_rate = 0;
										int var_index = i;
										double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
										for (int k = 0; k < total_disturbances; k++) {
											total_loss_rate = total_loss_rate + Double.valueOf(user_loss_rate[k]);
										}
										
										fileOut.write("\t" + Double.valueOf(total_loss_rate));
										for (int k = 0; k < total_disturbances; k++) {
											fileOut.write("\t" + Double.valueOf(user_loss_rate[k]));
										}
										fileOut.write("\t" + Double.valueOf(var_cost_value[i]));
										
										fileOut.write("\t" + var_info_array[i].get_method() + "\t" + var_info_array[i].get_forest_status()
												+ "\t" + var_info_array[i].get_layer1() + "\t" + var_info_array[i].get_layer2()
												+ "\t" + var_info_array[i].get_layer3() + "\t" + var_info_array[i].get_layer4()
												+ "\t" + var_info_array[i].get_layer5() + "\t" + var_info_array[i].get_layer6()
												+ "\t" + var_info_array[i].get_period()
												+ "\t" + String.valueOf(var_info_array[i].get_age()).replace("-9999",  "") 
												+ "\t" + String.valueOf(var_info_array[i].get_rotation_period()).replace("-9999",  "") 
												+ "\t" + String.valueOf(var_info_array[i].get_rotation_age()).replace("-9999",  "") 
												+ "\t" + var_info_array[i].get_layer5_regen()
												+ "\t" + data_connection + "\t" + var_info_array[i].get_prescription_id() + "\t" + var_info_array[i].get_prescription() + "\t" + var_row_id);
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
							
							
							// output_01_general_outputs (write at the end since we need writing time)
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
								fileOut.write(iter + "\t" + "Rolling horizon - iterating method" + "\t" + data[row][2].toString());
								
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Rolling horizon - disturbance option" + "\t" + data[row][1].toString());
								
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
							data[row][4] = "iteration " + iter + " done";
							model.fireTableDataChanged();
							value = null; reduceCost = null; dual = null; slack = null;		// clear arrays to save memory
							vlb = null; vub = null; vname = null; objvals = null;			// clear arrays to save memory
						} else {
							if (is_problem_exported) cplex_wrapper.exportModel(problem_file.getAbsolutePath());
							data[row][4] = "iteration " + iter + " fail";
							model.fireTableDataChanged();
						}
					}
					
			
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					if (solver_for_optimization.equals("LPSOLVE")) {				//Reference for all LPsolve classes here:		http://lpsolve.sourceforge.net/5.5/Java/docs/api/lpsolve/LpSolve.html
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
						int totalConstraints = c2_lb.length + c5_lb.length + c6_lb.length + c7_lb.length + c8_lb.length + c9_lb.length + c10_lb.length + c11_lb.length;
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
								String file_header = String.join("\t", "var_id", "var_name", "var_value", "var_reduced_cost", "var_loss_rate_total", "var_rd_condition_id", "var_global_adjustment_rd_condition_id");
								fileOut.write(file_header);
								
								for (int i = 0; i < value.length; i++) {
									if (value[i] != 0) {	// only write variable that is not zero
										fileOut.newLine();
										fileOut.write(i + "\t" + vname[i] 
												+ "\t" + Double.valueOf(value[i]) /*Double.valueOf(twoDForm.format(value[i]))*/ 
												+ "\t" + Double.valueOf(reduceCost[i + 1])) /*Double.valueOf(twoDForm.format(reduceCost[i])))*/;			// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
										
										if (vname[i].startsWith("x")) {
											double total_loss_rate = 0;
											int var_index = i;
											double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
											for (int k = 0; k < total_disturbances; k++) {
												total_loss_rate = total_loss_rate + Double.valueOf(user_loss_rate[k]);
											}
											fileOut.write("\t" + total_loss_rate); 
											
											if (total_disturbances == 0) {
												fileOut.write("\t" + "-9999" + "\t" + "-9999");
											} else {
												fileOut.write("\t"); 
												for (int k = 0; k < total_disturbances; k++) {
													fileOut.write(var_rd_condition_id[i][k] + " ");
												}
												fileOut.write("\t"); 
												for (int k = 0; k < total_disturbances; k++) {
													fileOut.write(var_global_adjustment_rd_condition_id[i][k] + " ");
												}
											}
										} else {
											fileOut.write("\t" + "-9999" + "\t" + "-9999" + "\t" + "-9999");
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
								List<String> activity = read_database.get_col_unique_values_list(activity_col_id);
								String file_header = String.join("\t", activity);
								file_header = "iteration" + "\t" + "period" + "\t" + "no-data"  + "\t" + file_header;
								fileOut.write(file_header);
								
								double[] no_data_area = new double[total_periods + 1];
								for (double a : no_data_area) {
									a = 0;
								}
								double[][] area = new double[total_periods + 1][activity.size()];	// area in each period for each activity
								for (double[] sub_area : area) {
									for (double a : sub_area) {
										a = 0;
									}
								}
								
								for (int i = 0; i < value.length; i++) {
									if (value[i] != 0) {	// only process variable that is not zero
										if (vname[i].startsWith("x")) {
											int var_index = i;
											int t = var_info_array[var_index].get_period();
											int prescription_id = var_info_array[var_index].get_prescription_id();
											int row_id = var_info_array[var_index].get_row_id();
											if (row_id != -9999	&& row_id < total_rows_of_precription[prescription_id]) {
												String action = yield_tables_values[prescription_id][row_id][activity_col_id];
												int activity_id = activity.indexOf(action);
												area[t][activity_id] = area[t][activity_id] + value[i];
											} else {
												no_data_area[t] = no_data_area[t] + value[i];
											}
										}
									}
								}
								
								for (int t = 1; t <= total_periods; t++) {
									fileOut.write("\n" + iter + "\t" + t + "\t" + no_data_area[t]);
									for (double a : area[t]) {
										fileOut.write("\t" + a /*Double.valueOf(twoDForm.format(area))*/);
									}
								}
								
								fileOut.close();
								activity = null;			// Clear arrays not used any more
								area = null;	// Clear arrays not used any more
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - FileWriter(output_management_overview_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_management_overview_file.createNewFile();
	
							
							// output_05_management_details
							output_management_details_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_management_details_file))) {
								fileOut.write("iteration" + "\t" + "var_id" + "\t" + "var_name" + "\t" + "var_value" + "\t" + "var_reduced_cost" + "\t" + "var_loss_rate_total" + "\t");
								
								for (int k = 0; k < total_disturbances; k++) {
									int disturbance_index = k + 1;
						        	String disturbance_name = (disturbance_index < 10) ? ("loss_rate_SR_0" + disturbance_index) : "loss_rate_SR_" + disturbance_index;
						        	fileOut.write(disturbance_name + "\t");
						        }
								
								fileOut.write("var_per_area_unit_cost" + "\t");
								fileOut.write("var_method" + "\t" + "var_forest_status" + "\t" + "var_layer1" + "\t" + "var_layer2" + "\t" + "var_layer3" + "\t" + "var_layer4" + "\t" + "var_layer5" + "\t" + "var_layer6"
								+ "\t" + "var_period" + "\t" + "var_age" + "\t" + "var_rotation_period" + "\t" + "var_rotation_age" + "\t" + "var_layer5_regen" + "\t"
										+ "data_connection" + "\t" + "prescription_id" + "\t" + "prescription"+ "\t" + "row_id");
	//							for (int col = 2; col < yield_tables_column_names.length; col++) {		// do not write prescription & row_id column header
	//								fileOut.write("\t" + yield_tables_column_names[col]);
	//							}
								
								
								
								for (int i = 0; i < value.length; i++) {
									if (value[i] != 0 && vname[i].startsWith("x")) {
										int var_prescription_id = var_info_array[i].get_prescription_id();
										int var_row_id = var_info_array[i].get_row_id();
	
										String data_connection = "good";
										if (total_rows_of_precription[var_prescription_id] <= var_row_id) {
											data_connection = "missing row id = " + var_row_id;
										}
	
										fileOut.newLine();
										fileOut.write(iter + "\t" + i + "\t" + vname[i] 
												+ "\t" + Double.valueOf(value[i] /*Double.valueOf(twoDForm.format(value[i])*/)
												+ "\t" + Double.valueOf(reduceCost[i + 1])); /*Double.valueOf(twoDForm.format(reduceCost[i]))*/ 	// because index starts from 1 not 0:    http://lpsolve.sourceforge.net/5.0/get_sensitivity_rhs.htm
										
										double total_loss_rate = 0;
										int var_index = i;
										double[] user_loss_rate = map_var_index_to_user_loss_rates.get(var_index);
										for (int k = 0; k < total_disturbances; k++) {
											total_loss_rate = total_loss_rate + Double.valueOf(user_loss_rate[k]);
										}
										
										fileOut.write("\t" + Double.valueOf(total_loss_rate));
										for (int k = 0; k < total_disturbances; k++) {
											fileOut.write("\t" + Double.valueOf(user_loss_rate[k]));
										}
										fileOut.write("\t" + Double.valueOf(var_cost_value[i]));
										
										fileOut.write("\t" + var_info_array[i].get_method() + "\t" + var_info_array[i].get_forest_status()
												+ "\t" + var_info_array[i].get_layer1() + "\t" + var_info_array[i].get_layer2()
												+ "\t" + var_info_array[i].get_layer3() + "\t" + var_info_array[i].get_layer4()
												+ "\t" + var_info_array[i].get_layer5() + "\t" + var_info_array[i].get_layer6()
												+ "\t" + var_info_array[i].get_period()
												+ "\t" + String.valueOf(var_info_array[i].get_age()).replace("-9999",  "") 
												+ "\t" + String.valueOf(var_info_array[i].get_rotation_period()).replace("-9999",  "") 
												+ "\t" + String.valueOf(var_info_array[i].get_rotation_age()).replace("-9999",  "") 
												+ "\t" + var_info_array[i].get_layer5_regen()
												+ "\t" + data_connection + "\t" + var_info_array[i].get_prescription_id() + "\t" + var_info_array[i].get_prescription() + "\t" + var_row_id);
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
							
							
							// output_01_general_outputs (write at the end since we need writing time)
							output_general_outputs_file.delete();
							try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(output_general_outputs_file))) {
								// Write variables info
								fileOut.write("iteration" + "\t" + "description" + "\t" + "value");
	
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Optimization solver" + "\t" + "LPSOLVE");
	
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Solution status" + "\t" + lpsolve_status);
	
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Solution algorithm" + "\t" + lpsolve_algorithm);
	
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Simplex iterations" + "\t" + lpsolve_iteration);
								
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Rolling horizon - iterative method" + "\t" + data[row][2].toString());
								
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Rolling horizon - disturbance option" + "\t" + data[row][1].toString());
								
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
								fileOut.write(iter + "\t" + "Total variables" + "\t" + lpsolve_total_variables);
	
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Total constraints" + "\t" + lpsolve_total_constraints);
	
								fileOut.newLine();
								fileOut.write(iter + "\t" + "Objective value" + "\t" + Double.valueOf(twoDForm.format(objective_value)));
	
								fileOut.close();
							} catch (IOException e) {
								System.err.println("Panel Solve Runs - FileWriter(output_generalInfo_file) error - " + e.getClass().getName() + ": " + e.getMessage());
							}
							output_general_outputs_file.createNewFile();
							
							
							// show successful or fail in the GUI
							data[row][4] = "iteration " + iter + " done";
							model.fireTableDataChanged();
							value = null; /*reduceCost = null; dual = null; slack = null;*/		// clear arrays to save memory
							vlb = null; vub = null; vname = null; objvals = null;			// clear arrays to save memory
						} else {
							if (is_problem_exported) solver.writeLp(problem_file.getAbsolutePath());
							data[row][4] = "iteration " + iter + " fail";
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
			}
			catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Create output files exception for "+ runFolder);		
				e.printStackTrace();
				data[row][4] = "fail, cannot create outputs";
				model.fireTableDataChanged();
				
				problem_file.delete();
				solution_file.delete();
				output_general_outputs_file.delete();	
				output_variables_file.delete();
				output_constraints_file.delete();
				output_management_overview_file.delete();
				output_management_details_file.delete();	
				output_basic_constraints_file.delete();
				output_flow_constraints_file.delete();
			} catch (LpSolveException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   LPSOLVE exception for " + runFolder);
				e.printStackTrace();
				data[row][4] = "fail, lpsolve error";
				model.fireTableDataChanged();
				
				problem_file.delete();
				solution_file.delete();
				output_general_outputs_file.delete();	
				output_variables_file.delete();
				output_constraints_file.delete();
				output_management_overview_file.delete();
				output_management_details_file.delete();	
				output_basic_constraints_file.delete();
				output_flow_constraints_file.delete();
			}		
			catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage() + "   -   Panel Solve Runs   -   Input files exception for " + runFolder);
				e.printStackTrace();
				data[row][4] = "fail, invalid inputs";
				model.fireTableDataChanged();
				
				problem_file.delete();
				solution_file.delete();
				output_general_outputs_file.delete();	
				output_variables_file.delete();
				output_constraints_file.delete();
				output_management_overview_file.delete();
				output_management_details_file.delete();	
				output_basic_constraints_file.delete();
				output_flow_constraints_file.delete();
			} finally {
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
			}
	}
	
	
	
	
	// apply to eq (6) (7b) (9) and printing some outputs
	private double[] get_user_loss_rates_for_this_var(int[][] var_rd_condition_id, int[][] var_global_adjustment_rd_condition_id, int var_index, Information_Disturbance disturbance_info) {
		if (disturbance_info == null) return null; 
		Statistics stat = new Statistics();
		int total_disturbances = disturbance_info.get_total_disturbances();
		String[] modelling_approach = new String[total_disturbances];
		String[] normalizing_function = new String[total_disturbances];
		double[] parameter_a = new double[total_disturbances];
		double[] parameter_b = new double[total_disturbances];
		double[] mean = new double[total_disturbances];
		double[] std = new double[total_disturbances];
		double[] back_transformed_global_adjustment = new double[total_disturbances];
		for (int k = 0; k < total_disturbances; k++) {
			modelling_approach[k] = disturbance_info.get_modelling_approach_from_rd_condition_id(var_rd_condition_id[var_index][k]);
			normalizing_function[k] = disturbance_info.get_normalizing_function_from_rd_condition_id(var_rd_condition_id[var_index][k]);
			parameter_a[k] = disturbance_info.get_parameter_a_from_rd_condition_id(var_rd_condition_id[var_index][k]);
			parameter_b[k] = disturbance_info.get_parameter_b_from_rd_condition_id(var_rd_condition_id[var_index][k]);	
			mean[k] = disturbance_info.get_mean_from_rd_condition_id(var_rd_condition_id[var_index][k]);
			std[k] = disturbance_info.get_std_from_rd_condition_id(var_rd_condition_id[var_index][k]);
			back_transformed_global_adjustment[k] = disturbance_info.get_back_transformed_global_adjustment_for_using_across_iteration(var_global_adjustment_rd_condition_id[var_index][k]);
		}
		return stat.get_user_loss_rates_from_transformed_data(total_disturbances, modelling_approach, normalizing_function, parameter_a, parameter_b, mean, std, back_transformed_global_adjustment);
	}
	
	
	// this period 1 calculation is used for the period 2 calculation only
	private double[] get_new_loss_rates_for_period_one_variable(
			String disturbance_option,
			String period_one_var_name, 
			LinkedHashMap<String, Double> map_var_name_to_var_value,
			LinkedHashMap<String, int[]> map_var_name_to_var_rd_condition_id,
			LinkedHashMap<String, int[]> map_var_name_to_var_global_adjustment_rd_condition_id,
			Information_Disturbance disturbance_info) {
		if (disturbance_info == null) return null;
		Statistics stat = new Statistics();
		int total_disturbances = disturbance_info.get_total_disturbances();
		String[] modelling_approach = new String[total_disturbances];
		String[] normalizing_function = new String[total_disturbances];
		double[] parameter_a = new double[total_disturbances];
		double[] parameter_b = new double[total_disturbances];
		double[] mean = new double[total_disturbances];
		double[] std = new double[total_disturbances];
		double[] back_transformed_global_adjustment = new double[total_disturbances];
		int[] period_one_rd_condition_id = map_var_name_to_var_rd_condition_id.get(period_one_var_name);
		int[] period_one_global_adjustment_rd_condition_id = map_var_name_to_var_global_adjustment_rd_condition_id.get(period_one_var_name);
		for (int k = 0; k < total_disturbances; k++) {
			modelling_approach[k] = disturbance_info.get_modelling_approach_from_rd_condition_id(period_one_rd_condition_id[k]);
			normalizing_function[k] = disturbance_info.get_normalizing_function_from_rd_condition_id(period_one_rd_condition_id[k]);
			parameter_a[k] = disturbance_info.get_parameter_a_from_rd_condition_id(period_one_rd_condition_id[k]);
			parameter_b[k] = disturbance_info.get_parameter_b_from_rd_condition_id(period_one_rd_condition_id[k]);	
			mean[k] = disturbance_info.get_mean_from_rd_condition_id(period_one_rd_condition_id[k]);
			std[k] = disturbance_info.get_std_from_rd_condition_id(period_one_rd_condition_id[k]);
			back_transformed_global_adjustment[k] = disturbance_info.get_back_transformed_global_adjustment_for_using_across_iteration(period_one_global_adjustment_rd_condition_id[k]);
		}
		
		/*disturbance option
		  user defined: Before solving an iteration, re-simulate disturbances for the period 1 variables of the previous iteration based on user-defined modelling approach in the Natural Disturbances screen
		  full stochastic: Before solving an iteration, re-simulate disturbances for the period 1 variables of the previous iteration based on stochastic modelling approach regardless of what defined by users in the Natural Disturbances screen
		*/
		if (disturbance_option.equals("full stochastic")) {
			return stat.get_stochastic_loss_rates_from_transformed_data(total_disturbances, normalizing_function, parameter_a, parameter_b, mean, std, back_transformed_global_adjustment);
		} else {
			return stat.get_user_loss_rates_from_transformed_data(total_disturbances, modelling_approach, normalizing_function, parameter_a, parameter_b, mean, std, back_transformed_global_adjustment);
		}
	}
	
	
	// apply to eq (5) (7a)
	// this is the second period variable in previous iteration OR the first period variable in this iteration 
	private double get_period_two_variable_value_after_applying_new_loss_rates_for_period_one_variable(
			String disturbance_option, 
			int var_index, Information_Variable[] var_info_array,
			LinkedHashMap<String, Double> map_var_name_to_var_value,
			LinkedHashMap<String, int[]> map_var_name_to_var_rd_condition_id,
			LinkedHashMap<String, int[]> map_var_name_to_var_global_adjustment_rd_condition_id,
			LinkedHashMap<String, double[]> map_var_name_to_var_new_loss_rates,
			Information_Disturbance disturbance_info) { 	
		
		// get the period 1 solution from previous iteration
		String var_name = var_info_array[var_index].get_var_name();
		String[] name_split = var_name.split("_");
		String first_four_letters_of_var_name = var_name.substring(0, 4);
		switch (first_four_letters_of_var_name) {
		
		case "x_E_":			// t-->t - 1
			int period = Integer.parseInt(name_split[9 + 2]) - 1;
			name_split[9 + 2] = String.valueOf(period);
			break;
			
		case "x_R_":			// t-->t - 1	and 	a-->a - 1
			period = Integer.parseInt(name_split[9 + 2]) - 1;
			name_split[9 + 2] = String.valueOf(period);
			int age = Integer.parseInt(name_split[10 + 2]) - 1;
			name_split[10 + 2] = String.valueOf(age);
			break;
			
		default:
			break;
		}
		String period_one_var_name = String.join("_", name_split);
		
		if (map_var_name_to_var_value.get(period_one_var_name) != null) {
			// if not doing random drawn yet then do it and map it
			if (map_var_name_to_var_new_loss_rates.get(period_one_var_name) == null) {	
				double[] new_loss_rates_for_period_one_variable = get_new_loss_rates_for_period_one_variable(
						disturbance_option, period_one_var_name, map_var_name_to_var_value, map_var_name_to_var_rd_condition_id, map_var_name_to_var_global_adjustment_rd_condition_id, disturbance_info);
				map_var_name_to_var_new_loss_rates.put(period_one_var_name, new_loss_rates_for_period_one_variable);
			}

			// apply the total new loss rate to period 2 variable and return the value
			double total_new_loss_rates = 0;
			if (map_var_name_to_var_new_loss_rates.get(period_one_var_name) != null) {
				for (double i : map_var_name_to_var_new_loss_rates.get(period_one_var_name)) {	// each i is the stochastic rate for one SR
					total_new_loss_rates = total_new_loss_rates + i;
				}
			}
			
			// Calculate the period 2 variable after the consequence of stochastic loss
			double period_one_var_value = map_var_name_to_var_value.get(period_one_var_name);
			double period_two_value = (1 - total_new_loss_rates / 100) * period_one_var_value;
			return period_two_value;
		} else {
			return 0; 	// in case period one variable is not found then period 2 variable should be 0
		}
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
		// all values to be 0
		for (int i = 0; i < total_var; i++) {
			array[i] = 0;
		} 
		// for other values, check index & value to set it
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
	
	
    // Checks whether the status corresponds to a valid solution
	// @param status    The status id returned by lpsolve
	// @return          Boolean which indicates if the solution is valid
	private static boolean isSolutionValid(int status) {
		return (status == 0) || (status == 1) || (status == 11) || (status == 12);
	}
	// End of For LPSOLVE only ----------------------------------------------------------------------------------------
	
	private List<String> getCommonElements(final List<String> listA, final List<String> listB) {
		final Map<Integer, List<String>> hashA = new HashMap<Integer, List<String>>(listA.size());
		final Iterator<String> a = listA.iterator();
		
		while (a.hasNext()) {
			final String item = a.next();
			List<String> subList = hashA.get(item.hashCode());
			if (subList == null) {
				subList = new ArrayList<String>(4);
				hashA.put(item.hashCode(), subList);
			}
			subList.add(item);
		}

		final List<String> results = new ArrayList<String>();
		final Iterator<String> i = listB.iterator();
		while (i.hasNext()) {
			final String item = i.next();
			final List<String> list = hashA.get(item.hashCode());
			if (list != null && list.contains(item))
				results.add(item);
		}

		return results;
	}
	
}

