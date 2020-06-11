/*
 * Copyright (C) 2016-2020 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM. If not, see <http://www.gnu.org/licenses/>.
 */

package prism_project.data_process;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Read_Input {
	private Read_Database read_database;
	private Identifiers_Processing identifiers_processing;
	private String[][][] yield_tables_values;
	
	public Read_Input(Read_Database read_database) {
		this.read_database = read_database;	
		identifiers_processing = new Identifiers_Processing(read_database);
		yield_tables_values = read_database.get_yield_tables_values();
	}
	
	private List<String> get_list_of_checked_conditions(List<String> list, String delimited) {
		String[] column_name = list.get(0).split(delimited);		// 1st line is column name
		List<String> column_names_list = Arrays.asList(column_name);
		int model_condition_col = column_names_list.indexOf("model_condition");
		list.remove(0);		// remove the first row (column headers row)
		List<String> remove_list = new ArrayList<String>();	// this list contains all lines which have model_condition = false
		for (String line : list) {
			if (line.split(delimited)[model_condition_col].equals("false")) {
				remove_list.add(line);
			}
		}
		list.removeAll(remove_list);	// remove lines where model_condition = false from the list
		return list;
	}
	
	private List<String> get_list_of_checked_strata(List<String> list, String delimited) {
		String[] column_name = list.get(0).split(delimited);		// 1st line is column name
		List<String> column_names_list = Arrays.asList(column_name);
		int model_strata_col = column_names_list.indexOf("model_strata");
		list.remove(0);		// remove the first row (column headers row)
		List<String> remove_list = new ArrayList<String>();	// this list contains all lines which have model_condition = false
		for (String line : list) {
			if (line.split(delimited)[model_strata_col].equals("false")) {
				remove_list.add(line);
			}
		}
		list.removeAll(remove_list);	// remove lines where model_strata = false from the list
		return list;
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_01_general_inputs.txt
	private int gi_total_rows, gi_total_columns;
	private String[][] gi_data;

	public void read_general_inputs(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	// Remove the first row (Column names)
			String[] a = list.toArray(new String[list.size()]);
								
			gi_total_rows = a.length;
			gi_total_columns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)					
			gi_data = new String[gi_total_rows][gi_total_columns];
		
			// read all values from all rows and columns
			for (int i = 0; i < gi_total_rows; i++) {
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < gi_total_columns; j++) {
					gi_data[i][j] = rowValue[j].replaceAll("\\s+", "");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public int get_total_periods() {
		return Integer.parseInt(gi_data[0][1]);
	}
	
	public int get_total_replacing_disturbances() {
		return Integer.parseInt(gi_data[1][1]);
	}

	public double get_discount_rate() {
		return Double.parseDouble(gi_data[2][1]);
	}

	public String get_solver() {
		return gi_data[3][1].toString();
	}

	public int get_solving_time() {
		return Integer.parseInt(gi_data[4][1]);
	}

	public boolean get_export_problem() {
		return Boolean.parseBoolean(gi_data[5][1]);
	}

	public boolean get_export_solution() {
		return Boolean.parseBoolean(gi_data[6][1]);
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_02_model_strata: must be read before silviculture_method
	private int ms_total_rows, ms_total_columns;
	private String[][] ms_data;
		
	public void read_model_strata(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list = get_list_of_checked_strata(list, delimited);
			String[] a = list.toArray(new String[list.size()]);
						
			ms_total_rows = a.length;
			ms_total_columns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)				
			ms_data = new String[ms_total_rows][ms_total_columns];
		
			// read all values from all rows and columns
			for (int i = 0; i < ms_total_rows; i++) {
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < ms_total_columns; j++) {
					ms_data[i][j] = rowValue[j].replaceAll("\\s+", "");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public String[][] get_ms_data() {
		return ms_data;
	}

	public int get_ms_total_rows() {
		return ms_total_rows;
	}
	
	public int get_ms_total_columns() {
		return ms_total_columns;
	}
	
	public List<String> get_model_strata() {
		List<String> modeled_strata = new ArrayList<String>();
		for (int i = 0; i < ms_total_rows; i++) {		
			String combined_name = String.join("_", ms_data[i][1], ms_data[i][2], ms_data[i][3], ms_data[i][4], ms_data[i][5], ms_data[i][6]);
			modeled_strata.add(combined_name);
		}
		return modeled_strata;
	}
	
	public List<String> get_model_strata_without_sizeclass() {	// Note this is replaced in Solve_Iterations because we need all the s5 --> use the below: get_model_strata_without_sizeclass_and_covertype + a loop add all s5
		List<String> model_strata_without_sizeclass = new ArrayList<String>();
		for (int i = 0; i < ms_total_rows; i++) {	
			String combined_name = String.join("_", ms_data[i][1], ms_data[i][2], ms_data[i][3], ms_data[i][4], ms_data[i][5]);
			if (!model_strata_without_sizeclass.contains(combined_name)) {	// only add to list if list does not contain the value
				model_strata_without_sizeclass.add(combined_name);
			}
		}
		return model_strata_without_sizeclass;
	}	
	
	public List<String> get_model_strata_without_sizeclass_and_covertype() {
		List<String> model_strata_without_sizeclass_and_covertype = new ArrayList<String>();
		for (int i = 0; i < ms_total_rows; i++) {		
			String combined_name = String.join("_", ms_data[i][1], ms_data[i][2], ms_data[i][3], ms_data[i][4]);		
			if (!model_strata_without_sizeclass_and_covertype.contains(combined_name)) {	// only add to list if list does not contain the value
				model_strata_without_sizeclass_and_covertype.add(combined_name);
			}	
		}
		return model_strata_without_sizeclass_and_covertype;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_03_prescription_category : must be read after model_strata
	private int category_total_rows, category_total_columns;
	private String[][] category_data;
	String[] prescription_group;

	public void read_prescription_category(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// all lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list = get_list_of_checked_conditions(list, delimited);
			String[] a = list.toArray(new String[list.size()]);

			category_total_rows = a.length;
			category_total_columns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)	
			category_data = new String[category_total_rows][category_total_columns];
		
			// read all values from all rows and columns
			for (int i = 0; i < category_total_rows; i++) {		
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < category_total_columns; j++) {
					category_data[i][j] = rowValue[j];
				}
			}
			
			// Logic: If the first row of a prescription meets the dynamic identifiers condition then the prescription will be considered as meeting the condition.
			// Logic: condition is PRIORITY CONDITION
			// Note: columnNames2 = new String[] {"condition_id", "condition_description", "prescription_group", "dynamic_identifiers", "original_dynamic_identifiers", "model_condition"};
			int category_prescription_group_col = 2;
			int category_dynamic_identifiers_col = 3;
			// This array stores dynamic identifiers in each row of the input table
			List<List<String>>[] all_priority_condition_dynamic_identifiers = new ArrayList[category_total_rows];
			List<String>[] all_priority_condition_dynamic_dentifiers_column_indexes = new ArrayList[category_total_rows];
			for (int priority = 0; priority < category_total_rows; priority++) {	// Looping from the highest priority condition to the lowest, each priority is a row in the table GUI
				String dynamic_identifiers_info = category_data[priority][category_dynamic_identifiers_col];	
				List<List<String>> category_dynamic_identifiers = identifiers_processing.get_dynamic_identifiers(dynamic_identifiers_info);
				List<String> category_dynamic_identifiers_column_indexes = identifiers_processing.get_dynamic_dentifiers_column_indexes(dynamic_identifiers_info);
				all_priority_condition_dynamic_identifiers[priority] = category_dynamic_identifiers;
				all_priority_condition_dynamic_dentifiers_column_indexes[priority] = category_dynamic_identifiers_column_indexes;
			}
			
			// Categorize prescriptions into groups
			int total_prescriptions = yield_tables_values.length;
			prescription_group = new String[total_prescriptions];
			for (int prescription_id = 0; prescription_id < total_prescriptions; prescription_id++) {
				for (int priority = 0; priority < category_total_rows; priority++) {	// Loop from the highest priority condition to the lowest
					// If this prescription is not categorized yet, and the first row of this prescription meets this priority condition dynamic identifiers then assign the group categorization to this prescription
					int row_id = 0;
					if (prescription_group[prescription_id] == null && identifiers_processing.are_all_dynamic_identifiers_matched(
							yield_tables_values, prescription_id, row_id,
							all_priority_condition_dynamic_dentifiers_column_indexes[priority],
							all_priority_condition_dynamic_identifiers[priority])) {
						prescription_group[prescription_id] = category_data[priority][category_prescription_group_col];
					}
				}
			}
			
//			// test results
//			for (int i = 0; i < prescription_group.length; i++) {
//				System.out.println(yield_tables_values[i][0][0] + " --> " + prescription_group[i]);
//			}
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public int get_category_total_rows() {
		return category_total_rows;
	}
	
	public String[] get_prescription_group() {
		return prescription_group;
	}
		
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_04_prescription_assignment
	private int assignment_total_rows, assignment_total_columns;
	private String[][] assignment_data;
	private List<List<String>>[] static_identifiers_for_row;
	private List<Integer>[] list_of_prescription_ids_for_row;
	private Set<Integer>[] set_of_s5R_for_strata, set_of_s5R_for_strata_without_sizeclass;
	private List<Integer>[] list_of_prescription_ids_for_strata, list_of_prescription_ids_for_strata_without_sizeclass;

	public void read_prescription_assignment(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list = get_list_of_checked_conditions(list, delimited);
			String[] a = list.toArray(new String[list.size()]);
								
			assignment_total_rows = a.length;
			assignment_total_columns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)	
			assignment_data = new String[assignment_total_rows][assignment_total_columns];
		
			// read all values from all rows and columns
			for (int i = 0; i < assignment_total_rows; i++) {		
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < assignment_total_columns; j++) {
					assignment_data[i][j] = rowValue[j];
				}
			}
			
			// Logic: If the first row of a prescription meets the dynamic identifiers condition then the prescription will be considered as meeting the condition.
			// Logic: condition is AGGREGATION CONDITION
			// Note: columnNames4 = new String[] {"condition_id", "condition_description", "layer5_regen", "static_identifiers", "dynamic_identifiers", "original_dynamic_identifiers", "model_condition"};
			int assignment_static_identifiers_col = 3;
			int assignment_dynamic_identifiers_col = 4;
			List<List<String>>[] all_condition_static_identifiers = new ArrayList[assignment_total_rows];	// This array stores static identifiers in each row of the input table
			List<List<String>>[] all_condition_dynamic_identifiers = new ArrayList[assignment_total_rows];	// This array stores dynamic identifiers in each row of the input table
			List<String>[] all_condition_dynamic_dentifiers_column_indexes = new ArrayList[assignment_total_rows];
			for (int aggregation_condition = 0; aggregation_condition < assignment_total_rows; aggregation_condition++) {		// Loop all aggregation conditions
				String static_identifiers_info = assignment_data[aggregation_condition][assignment_static_identifiers_col];	
				List<List<String>> assignment_static_identifiers = identifiers_processing.get_static_identifiers(static_identifiers_info);
				all_condition_static_identifiers[aggregation_condition] = assignment_static_identifiers;
				
				String dynamic_identifiers_info = assignment_data[aggregation_condition][assignment_dynamic_identifiers_col];	
				List<List<String>> assignment_dynamic_identifiers = identifiers_processing.get_dynamic_identifiers(dynamic_identifiers_info);
				List<String> assignment_dynamic_identifiers_column_indexes = identifiers_processing.get_dynamic_dentifiers_column_indexes(dynamic_identifiers_info);
				all_condition_dynamic_identifiers[aggregation_condition] = assignment_dynamic_identifiers;
				all_condition_dynamic_dentifiers_column_indexes[aggregation_condition] = assignment_dynamic_identifiers_column_indexes;
			}
			static_identifiers_for_row = all_condition_static_identifiers;
			
			// create the list
			int total_prescriptions = yield_tables_values.length;
			list_of_prescription_ids_for_row = new ArrayList[assignment_total_rows];
			for (int aggregation_condition = 0; aggregation_condition < assignment_total_rows; aggregation_condition++) {	// Loop all aggregation conditions
				list_of_prescription_ids_for_row[aggregation_condition] = new ArrayList<Integer>();
			}
			
			// add prescription ids to the list & add layer5_regen ids to the list
			for (int prescription_id = 0; prescription_id < total_prescriptions; prescription_id++) {
				for (int aggregation_condition = 0; aggregation_condition < assignment_total_rows; aggregation_condition++) {	// Loop all aggregation conditions
					// If the first row of this prescription meets this aggregation condition dynamic identifiers then add the prescription_id to the list_of_prescriptions_for_this_row 
					int row_id = 0;
					if (identifiers_processing.are_all_dynamic_identifiers_matched(
							yield_tables_values, prescription_id, row_id,
							all_condition_dynamic_dentifiers_column_indexes[aggregation_condition],
							all_condition_dynamic_identifiers[aggregation_condition])) {
						list_of_prescription_ids_for_row[aggregation_condition].add(prescription_id);
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public void populate_ea_lists(List<String> model_strata, List<String> model_strata_without_sizeclass, List<List<String>> all_layers) {	
		// existing 6 layers ------------------------------------ ------------------------------------ ------------------------------------
		list_of_prescription_ids_for_strata = new ArrayList[model_strata.size()];
		set_of_s5R_for_strata = new HashSet[model_strata.size()];
		for (int i = 0; i < model_strata.size(); i++) {
			list_of_prescription_ids_for_strata[i] = new ArrayList<Integer>();
			set_of_s5R_for_strata[i] = new HashSet<Integer>();
		}
		
		for (int strata_id = 0; strata_id < model_strata.size(); strata_id++) {
			String strata = model_strata.get(strata_id);
			String[] layer = strata.split("_");
		
			for (int row = 0; row < assignment_total_rows; row++) {	// load each row   (loop all aggregation conditions)
				List<List<String>> static_identifiers = static_identifiers_for_row[row];			
				List<Integer> list_of_prescription_ids = list_of_prescription_ids_for_row[row];
				
				// The below check also implements Speed Boost RRB9
				if (	(static_identifiers.get(0).size() == all_layers.get(0).size() || Collections.binarySearch(static_identifiers.get(0), layer[0]) >= 0) && 
						(static_identifiers.get(1).size() == all_layers.get(1).size() || Collections.binarySearch(static_identifiers.get(1), layer[1]) >= 0) && 
						(static_identifiers.get(2).size() == all_layers.get(2).size() || Collections.binarySearch(static_identifiers.get(2), layer[2]) >= 0) && 
						(static_identifiers.get(3).size() == all_layers.get(3).size() || Collections.binarySearch(static_identifiers.get(3), layer[3]) >= 0) && 
						(static_identifiers.get(4).size() == all_layers.get(4).size() || Collections.binarySearch(static_identifiers.get(4), layer[4]) >= 0) && 
						(static_identifiers.get(5).size() == all_layers.get(5).size() || Collections.binarySearch(static_identifiers.get(5), layer[5]) >= 0))	
				{
					list_of_prescription_ids_for_strata[strata_id].addAll(list_of_prescription_ids);	// union
					
					int assignment_layer5_regen_col = 2;
					List<String> layer5 = read_database.get_all_layers().get(4);
					String layer5_regen_info = assignment_data[row][assignment_layer5_regen_col];
					String[] layer5_regen_array = layer5_regen_info.split(" ");
					for (String layer5_regen : layer5_regen_array) {
						System.out.println("." + layer5_regen + ".");
						int s5R = layer5.indexOf(layer5_regen);
						if (s5R >= 0) set_of_s5R_for_strata[strata_id].add(s5R);
					}
				}
				
//				// Without RRB9 --> no need all_layers
//				if (	Collections.binarySearch(static_identifiers.get(0), layer1) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(1), layer2) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(2), layer3) >= 0 &&
//						Collections.binarySearch(static_identifiers.get(3), layer4) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(4), layer5) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(5), layer6) >= 0)	
//				{
//				list_of_prescription_ids_for_strata[strata_id].addAll(list_of_prescription_ids);	// union
//				}
			}
		}
		
		
		
		
		// regeneration 5 layers ------------------------------------ ------------------------------------ ------------------------------------
		list_of_prescription_ids_for_strata_without_sizeclass = new ArrayList[model_strata_without_sizeclass.size()];
		set_of_s5R_for_strata_without_sizeclass = new HashSet[model_strata_without_sizeclass.size()];
		for (int i = 0; i < model_strata_without_sizeclass.size(); i++) {
			list_of_prescription_ids_for_strata_without_sizeclass[i] = new ArrayList<Integer>();
			set_of_s5R_for_strata_without_sizeclass[i] = new HashSet<Integer>();
		}
		
		for (int strata_5layers_id = 0; strata_5layers_id < model_strata_without_sizeclass.size(); strata_5layers_id++) {
			String strata_5layers = model_strata_without_sizeclass.get(strata_5layers_id);
			String[] layer = strata_5layers.split("_");
		
			for (int row = 0; row < assignment_total_rows; row++) {	// load each row   (loop all aggregation conditions)
				List<List<String>> static_identifiers = static_identifiers_for_row[row];			
				List<Integer> list_of_prescription_ids = list_of_prescription_ids_for_row[row];	
				
				// The below check also implements Speed Boost RRB9
				if (	(static_identifiers.get(0).size() == all_layers.get(0).size() || Collections.binarySearch(static_identifiers.get(0), layer[0]) >= 0) && 
						(static_identifiers.get(1).size() == all_layers.get(1).size() || Collections.binarySearch(static_identifiers.get(1), layer[1]) >= 0) && 
						(static_identifiers.get(2).size() == all_layers.get(2).size() || Collections.binarySearch(static_identifiers.get(2), layer[2]) >= 0) && 
						(static_identifiers.get(3).size() == all_layers.get(3).size() || Collections.binarySearch(static_identifiers.get(3), layer[3]) >= 0) && 
						(static_identifiers.get(4).size() == all_layers.get(4).size() || Collections.binarySearch(static_identifiers.get(4), layer[4]) >= 0))	
				{
					list_of_prescription_ids_for_strata_without_sizeclass[strata_5layers_id].addAll(list_of_prescription_ids);	// union
					
					int assignment_layer5_regen_col = 2;
					List<String> layer5 = read_database.get_all_layers().get(4);
					String layer5_regen_info = assignment_data[row][assignment_layer5_regen_col];
					String[] layer5_regen_array = layer5_regen_info.split(" ");
					for (String layer5_regen : layer5_regen_array) {
						System.out.println("." + layer5_regen + ".");
						int s5R = layer5.indexOf(layer5_regen);
						if (s5R >= 0) set_of_s5R_for_strata_without_sizeclass[strata_5layers_id].add(s5R);
					}
				}
				
//				// Without RRB9 --> no need all_layers
//				if (	Collections.binarySearch(static_identifiers.get(0), layer1) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(1), layer2) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(2), layer3) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(3), layer4) >= 0 && 
//						Collections.binarySearch(static_identifiers.get(4), layer5) >= 0)	
//				{
//				list_of_prescription_ids_for_strata_without_sizeclass[strata_5layers_id].addAll(list_of_prescription_ids);	// union
//				}
			}
		}	
	}	
	
	public int get_assignment_total_rows() {
		return assignment_total_rows;
	}
	
	public List<Integer>[] get_list_of_prescription_ids_for_strata() {
		return list_of_prescription_ids_for_strata;		// each existing stratum includes a list of prescriptions
	}
	
	public List<Integer>[] get_list_of_prescription_ids_for_strata_without_sizeclass() {
		return list_of_prescription_ids_for_strata_without_sizeclass;		// each regenerated stratum includes a list of prescriptions
	}	
	
	public Set<Integer>[] get_set_of_s5R_for_strata() {
		return set_of_s5R_for_strata;
	}
	
	public Set<Integer>[] get_set_of_s5R_for_strata_without_sizeclass() {
		return set_of_s5R_for_strata_without_sizeclass;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_06_natural_disturbances
	private List<String> disturbance_condition_list;
	
	public void read_replacing_disturbances(File file) {
		String delimited = "\t";
		try {
			// All lines except the 1st line to be in a list;		
			disturbance_condition_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			disturbance_condition_list = get_list_of_checked_conditions(disturbance_condition_list, delimited);
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public List<String> get_disturbance_condition_list() {
		return disturbance_condition_list;
	}			
				
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_07_management_cost
	private List<String> cost_condition_list;
	
	public void read_management_cost(File file) {
		String delimited = "\t";
		try {
			// All lines except the 1st line to be in a list;		
			cost_condition_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			cost_condition_list = get_list_of_checked_conditions(cost_condition_list, delimited);
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
	
	public List<String> get_cost_condition_list() {
		return cost_condition_list;
	}
			
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	//For input_08_basic_constraints
	private List<String> constraint_column_names_list;
	private int bc_total_rows, bc_total_columns;
	private String[][] bc_data;
	private int bc_id_col, bc_description_col, bc_type_col, bc_multiplier_col, lowerbound_col, lowerbound_perunit_penalty_col,
			upperbound_col, upperbound_perunit_penalty_col, parameter_index_col, static_identifiers_col, dynamic_identifiers_col;

	public void read_basic_constraints(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			List<String> remove_list = new ArrayList<String>();	// this list contains all lines which have bc_type = IDLE
			for (String i : list) {
				if (i.split(delimited)[2].equals("IDLE")) {
					remove_list.add(i);
				}
			}
			list.removeAll(remove_list);	// remove bc_type = IDLE lines from the list
			String[] a = list.toArray(new String[list.size()]);
						
			//Read the first row into array. This will be Column names
			String[] columnName = a[0].split(delimited);
			
			//List of constraint column names
			constraint_column_names_list = Arrays.asList(columnName);	
			bc_id_col = constraint_column_names_list.indexOf("bc_id");
			bc_description_col = constraint_column_names_list.indexOf("bc_description");
			bc_type_col = constraint_column_names_list.indexOf("bc_type");
			bc_multiplier_col = constraint_column_names_list.indexOf("bc_multiplier");
			lowerbound_col = constraint_column_names_list.indexOf("lowerbound");
			lowerbound_perunit_penalty_col = constraint_column_names_list.indexOf("lowerbound_perunit_penalty");
			upperbound_col = constraint_column_names_list.indexOf("upperbound");
			upperbound_perunit_penalty_col = constraint_column_names_list.indexOf("upperbound_perunit_penalty");
			parameter_index_col = constraint_column_names_list.indexOf("parameter_index");
			static_identifiers_col = constraint_column_names_list.indexOf("static_identifiers");
			dynamic_identifiers_col = constraint_column_names_list.indexOf("dynamic_identifiers");	
			
			// data in all rows and columns
			bc_total_rows = a.length;
			bc_total_columns = columnName.length;				
			bc_data = new String[bc_total_rows][bc_total_columns];
		
			// read all data from all rows and columns
			for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < bc_total_columns; j++) {
					bc_data[i][j] = rowValue[j];
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}	

	public List<String> get_constraint_column_names_list() {
		return constraint_column_names_list;
	}

	public String[][] get_bc_data() {
		return bc_data;
	}

	public int get_bc_total_rows() {
		return bc_total_rows;
	}

	public int get_bc_total_columns() {
		return bc_total_columns;
	}
		
	public int get_total_hard_constraints() {
		int total = 0;
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("HARD")) total++;
		}	
		return total;
	}	

	public double[] get_hard_constraints_LB() {
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("HARD") && !bc_data[i][lowerbound_col].equals("null")) list.add(Double.parseDouble(bc_data[i][lowerbound_col]));
			if (bc_data[i][bc_type_col].equals("HARD") && bc_data[i][lowerbound_col].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_hard_constraints_UB() {
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("HARD")  && !bc_data[i][upperbound_col].equals("null")) list.add(Double.parseDouble(bc_data[i][upperbound_col]));
			if (bc_data[i][bc_type_col].equals("HARD")  && bc_data[i][upperbound_col].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}
	
	public int get_total_soft_constraints() {
		int total = 0;	
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("SOFT")) total++;
		}	
		return total;
	}		
				
	
	public double[] get_soft_constraints_LB() {
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("SOFT") && !bc_data[i][lowerbound_col].equals("null")) list.add(Double.parseDouble(bc_data[i][lowerbound_col]));
			if (bc_data[i][bc_type_col].equals("SOFT") && bc_data[i][lowerbound_col].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}				
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_soft_constraints_UB() {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("SOFT")  && !bc_data[i][upperbound_col].equals("null")) list.add(Double.parseDouble(bc_data[i][upperbound_col]));
			if (bc_data[i][bc_type_col].equals("SOFT")  && bc_data[i][upperbound_col].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	public double[] get_soft_constraints_LB_Weight() {
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("SOFT") && !bc_data[i][lowerbound_perunit_penalty_col].equals("null")) list.add(Double.parseDouble(bc_data[i][lowerbound_perunit_penalty_col]));
			if (bc_data[i][bc_type_col].equals("SOFT") && bc_data[i][lowerbound_perunit_penalty_col].equals("null")) list.add((double) 0);		//Change "null" value of LB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_sof_constraints_UB_Weight() {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_data[i][bc_type_col].equals("SOFT") && !bc_data[i][upperbound_perunit_penalty_col].equals("null")) list.add(Double.parseDouble(bc_data[i][upperbound_perunit_penalty_col]));
			if (bc_data[i][bc_type_col].equals("SOFT") && bc_data[i][upperbound_perunit_penalty_col].equals("null")) list.add((double) 0);		//Change "null" value of UB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}		

	
	public int get_total_free_constraints() {
		int total = 0;
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row		
			if (bc_data[i][bc_type_col].equals("FREE")) total++;
		}	
		return total;
	}	
	
	
	public List<List<String>> get_static_identifiers_in_row(int row) {
		String static_identifiers_info = bc_data[row][static_identifiers_col];		//Note: row 0 is the title only, row 1 is constraint 1,.....
		List<List<String>> static_identifiers = identifiers_processing.get_static_identifiers(static_identifiers_info);
		return static_identifiers;
	}	
	
	
	public Set<String> get_static_strata_in_row(int row) {
		Set<String> static_strata = new HashSet<String>();
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		// first 6 layers
		for (String layer1: static_identifiers.get(0)) {
			for (String layer2: static_identifiers.get(1)) {
				for (String layer3: static_identifiers.get(2)) {
					for (String layer4: static_identifiers.get(3)) {
						for (String layer5: static_identifiers.get(4)) {
							for (String layer6: static_identifiers.get(5)) {
								String combined_name = String.join("_", layer1, layer2, layer3, layer4, layer5, layer6);
								static_strata.add(combined_name);
							}							
						}						
					}					
				}				
			}	
		}
		return static_strata;
	}	

	
	public Set<String> get_static_strata_without_sizeclass_in_row(int row) {	
		Set<String> static_strata_without_sizeclass = new HashSet<String>();
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		// first 5 layers
		for (String layer1: static_identifiers.get(0)) {
			for (String layer2: static_identifiers.get(1)) {
				for (String layer3: static_identifiers.get(2)) {
					for (String layer4: static_identifiers.get(3)) {
						for (String layer5: static_identifiers.get(4)) {
							String combined_name = String.join("_", layer1, layer2, layer3, layer4, layer5);
							static_strata_without_sizeclass.add(combined_name);
						}					
					}					
				}				
			}	
		}
		return static_strata_without_sizeclass;
	}
	
	
	public Set<String> get_static_strata_without_sizeclass_and_covertype_in_row(int row) {	
		Set<String> static_strata_without_sizeclass_and_covertype = new HashSet<String>();
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		// first 4 layers
		for (String layer1: static_identifiers.get(0)) {
			for (String layer2: static_identifiers.get(1)) {
				for (String layer3: static_identifiers.get(2)) {
					for (String layer4: static_identifiers.get(3)) {
						String combined_name = String.join("_", layer1, layer2, layer3, layer4);
						static_strata_without_sizeclass_and_covertype.add(combined_name);
					}					
				}				
			}	
		}
		return static_strata_without_sizeclass_and_covertype;
	}	
	
	
	
	public List<String> get_static_periods_in_row(int row) {
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);	
		return static_identifiers.get(6);
	}	

	
	
	public List<List<String>> get_dynamic_identifiers_in_row(int row) {
		String dynamic_identifiers_info = bc_data[row][dynamic_identifiers_col];		//Note: row 0 is the title only, row 1 is constraint 1,.....
		List<List<String>> dynamic_identifiers = identifiers_processing.get_dynamic_identifiers(dynamic_identifiers_info);
		return dynamic_identifiers;
	}	
	
	
	public List<String> get_dynamic_identifiers_column_indexes_in_row(int row) {	//Column 8 in the GUI table "Dynamic identifiers". The whole is contained by UC_value[i][8]
		String dynamic_identifiers_info = bc_data[row][dynamic_identifiers_col];		//Note: row 0 is the title only, row 1 is constraint 1,.....
		List<String> dynamic_identifiers_column_indexes = identifiers_processing.get_dynamic_dentifiers_column_indexes(dynamic_identifiers_info);
		return dynamic_identifiers_column_indexes;
	}	
	
	
	public List<String> get_parameters_indexes_in_row(int row) {	
		String parameters_info = bc_data[row][parameter_index_col];			
		List<String> parameters_indexes_list = identifiers_processing.get_parameters_indexes(parameters_info);
		return parameters_indexes_list;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	//For input_09_flow_constraints
	private List<String> flow_column_names_list;
	private int flow_total_rows, flow_total_columns;
	private String[][] flow_data;
	private int flow_id_col, flow_description_col, flow_arrangement_col, flow_type_col, lowerbound_percentage_col, upperbound_percentage_col;		

	public void read_flow_constraints(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// all lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]);
						
			// read the first row into array. This will be column names
			String[] columnName = a[0].split(delimited);
			
			// list of constraint column names
			flow_column_names_list = Arrays.asList(columnName);	
			flow_id_col = flow_column_names_list.indexOf("flow_id");
			flow_description_col = flow_column_names_list.indexOf("flow_description");
			flow_arrangement_col = flow_column_names_list.indexOf("flow_arrangement");
			flow_type_col = flow_column_names_list.indexOf("flow_type");
			lowerbound_percentage_col = flow_column_names_list.indexOf("lowerbound_percentage");
			upperbound_percentage_col = flow_column_names_list.indexOf("upperbound_percentage");

			
			// values in all rows and columns
			flow_total_rows = a.length;
			flow_total_columns = columnName.length;				
			flow_data = new String[flow_total_rows][flow_total_columns];
		
			// read all values from all rows and columns
			for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < flow_total_columns; j++) {
					flow_data[i][j] = rowValue[j];
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}				
	
	public List<String> get_flow_column_names_list() {
		return flow_column_names_list;
	}
	
	public String[][] get_flow_data() {
		return flow_data;
	}

	public int get_flow_total_rows() {
		return flow_total_rows;
	}

	public int get_flow_total_columns() {
		return flow_total_columns;
	}
		
	public List<Integer> get_flow_id_list() {
		List<Integer> get_flow_id_list = new ArrayList<Integer>();
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
			get_flow_id_list.add(Integer.parseInt(flow_data[i][flow_id_col]));
		}
		return get_flow_id_list;
	}
	
	public List<String> get_flow_description_list() {
		List<String> flow_description_list = new ArrayList<String>();	
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
			flow_description_list.add(flow_data[i][flow_description_col]);
		}
		return flow_description_list;
	}
	
	public List<String> get_flow_arrangement_list() {
		List<String> flow_arrangement_list = new ArrayList<String>();	
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
			flow_arrangement_list.add(flow_data[i][flow_arrangement_col]);
		}
		return flow_arrangement_list;
	}
	
	public List<String> get_flow_type_list() {
		List<String> flow_type_list = new ArrayList<String>();	
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
			flow_type_list.add(flow_data[i][flow_type_col]);
		}
		return flow_type_list;
	}
	
	public List<Double> get_flow_lowerbound_percentage_list() {
		List<Double> flow_lowerbound_percentage_list = new ArrayList<Double>();
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
			if (!flow_data[i][lowerbound_percentage_col].equals("null")) {
				flow_lowerbound_percentage_list.add(Double.parseDouble(flow_data[i][lowerbound_percentage_col]));
			} else {
				flow_lowerbound_percentage_list.add(null);
			}
		}
		return flow_lowerbound_percentage_list;
	}
	
	public List<Double> get_flow_upperbound_percentage_list() {
		List<Double> flow_upperbound_percentage_list = new ArrayList<Double>();
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row						
			if (!flow_data[i][upperbound_percentage_col].equals("null")) {
				flow_upperbound_percentage_list.add(Double.parseDouble(flow_data[i][upperbound_percentage_col]));
			} else {
				flow_upperbound_percentage_list.add(null);
			}
		}
		return flow_upperbound_percentage_list;
	}	
	
	public List<List<List<Integer>>> get_flow_set_list() {
		List<List<List<Integer>>> flow_set_list = new ArrayList<List<List<Integer>>>();	
		for (int i = 1; i < flow_total_rows; i++) {		// from 2nd row			
			List<List<Integer>> this_set = new ArrayList<List<Integer>>();				
			String[] flow_arrangement_info = flow_data[i][flow_arrangement_col].split(";");	// Read the whole cell 'flow_arrangement'
			int this_set_size = flow_arrangement_info.length;
			
			for (int j = 0; j < this_set_size; j++) {
				List<Integer> this_term = new ArrayList<Integer>();
				String[] this_term_info = flow_arrangement_info[j].split("\\s+");		// Read each term in this arrangement
				int this_term_size = this_term_info.length;	
								
				for (int k = 0; k < this_term_size; k++) {
					this_term.add(Integer.parseInt(this_term_info[k]));										
				}
				this_set.add(this_term);
			}
			flow_set_list.add(this_set);
		}
		return flow_set_list;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_11_state_id.txt
	private LinkedHashMap<String, String> map_prescription_and_row_id_to_state_id = new LinkedHashMap<String, String>();
	public void read_state_id(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	// Remove the first row (Column names)
			String[] a = list.toArray(new String[list.size()]);
								
			int total_rows = a.length;
			int total_columns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)					
			String[][] data = new String[total_rows][total_columns];
		
			// read all values from all rows and columns
			for (int i = 0; i < total_rows; i++) {
				String[] row_value = a[i].split(delimited);
				for (int j = 0; j < total_columns; j++) {
					data[i][j] = row_value[j];
				}
			}

			// Map:	prescription + " " + row_id = key, state_id = var_value
			for (int i = 0; i < total_rows; i++) {
				String key = data[i][0] + " " + data[i][1];
				String value = data[i][2];
				map_prescription_and_row_id_to_state_id.put(key, value);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// this error occurs when user did not define the state_id --> it would save as blank cell value for all rows in the state_id column which consequently make the read_state_id failed 
			// BLANK is not NULL: the fail is at line 1269. Note that the blank cell in state_id column is not the null value. This is due to the way I create the state_id in AreaMerging class. 
			map_prescription_and_row_id_to_state_id = null;
		}
	}
	
	public LinkedHashMap<String, String> get_map_prescription_and_row_id_to_state_id() {
		return map_prescription_and_row_id_to_state_id;
	}	
}
