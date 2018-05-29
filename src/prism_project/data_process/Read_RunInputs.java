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
package prism_project.data_process;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Read_RunInputs {
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_01_general_inputs.txt
	private int gi_totalRows, gi_totalColumns;
	private String[][] gi_value;

	public void read_general_inputs (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	// Remove the first row (Column names)
			String[] a = list.toArray(new String[list.size()]);
								
			gi_totalRows = a.length;
			gi_totalColumns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)					
			gi_value = new String[gi_totalRows][gi_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 0; i < gi_totalRows; i++) {
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < gi_totalColumns; j++) {
					gi_value[i][j] = rowValue[j].replaceAll("\\s+", "");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public int get_total_periods() {
		return Integer.parseInt(gi_value[0][1]);
	}
	
	public int get_total_replacing_disturbances() {
		return Integer.parseInt(gi_value[1][1]);
	}

	public double get_discount_rate() {
		return Double.parseDouble(gi_value[2][1]);
	}

	public String get_solver() {
		return gi_value[3][1].toString();
	}

	public int get_solving_time() {
		return Integer.parseInt(gi_value[4][1]);
	}

	public boolean get_export_problem() {
		return Boolean.parseBoolean(gi_value[5][1]);
	}

	public boolean get_export_solution() {
		return Boolean.parseBoolean(gi_value[6][1]);
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_02_silviculture_method
	private int sm_totalRows, sm_totalColumns;
	private String[][] sm_value;
	private List<String> sm_strata;	
	private List<String> sm_strata_without_sizeclass;
	private List<String> sm_strata_without_sizeclass_and_covertype;
	private List<List<String>> sm_method_choice_for_strata;
	private List<List<String>> sm_method_choice_for_strata_without_sizeclass;
	private List<List<String>> sm_method_choice_for_strata_without_sizeclass_and_covertype;

	public void read_silviculture_method(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	// Remove the first row (Column names)
			String[] a = list.toArray(new String[list.size()]);
								
			sm_totalRows = a.length;
			sm_totalColumns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)	
			sm_value = new String[sm_totalRows][sm_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 0; i < sm_totalRows; i++) {		
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < sm_totalColumns; j++) {
					sm_value[i][j] = rowValue[j];
				}
			}
			
			populate_sm_lists();
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public List<List<String>> get_sm_static_identifiers_in_row(int row) {
		List<List<String>> sm_static_identifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] staticLayer_Info = sm_value[row][2].split(";");
		int total_staticIdentifiers = staticLayer_Info.length;
		
		//Get all static Identifiers to be in the list
		for (int i = 0; i < total_staticIdentifiers; i++) {		//6 first identifiers is strata 6 layers (layer 0 to 5)		
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = staticLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			sm_static_identifiers.add(thisIdentifier);
		}
			
		return sm_static_identifiers;
	}	
	
	public List<List<String>> get_sm_method_choice_in_row(int row) {
		List<List<String>> sm_method_choice = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] method_choice_info = sm_value[row][3].split(";");
		int total_method_choice = method_choice_info.length;
		
		//Get all method and choice to be in the list
		for (int i = 0; i < total_method_choice; i++) {		// only 2: method and choice	
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = method_choice_info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			sm_method_choice.add(thisIdentifier);
		}
			
		return sm_method_choice;
	}
			
	private void populate_sm_lists() {	
		sm_strata = new ArrayList<String>();	
		sm_strata_without_sizeclass = new ArrayList<String>();
		sm_strata_without_sizeclass_and_covertype = new ArrayList<String>();
		sm_method_choice_for_strata = new ArrayList<List<String>>();
		sm_method_choice_for_strata_without_sizeclass = new ArrayList<List<String>>();
		sm_method_choice_for_strata_without_sizeclass_and_covertype = new ArrayList<List<String>>();
		
		for (int row = 0; row < sm_totalRows; row++) {		
			List<List<String>> sm_static_identifiers = get_sm_static_identifiers_in_row(row);			
			List<List<String>> sm_method_choice = get_sm_method_choice_in_row(row);							
			
			// existing 6 layers
			for (String layer1 : sm_static_identifiers.get(0)) {
				for (String layer2 : sm_static_identifiers.get(1)) {
					for (String layer3 : sm_static_identifiers.get(2)) {
						for (String layer4 : sm_static_identifiers.get(3)) {
							for (String layer5 : sm_static_identifiers.get(4)) {
								for (String layer6 : sm_static_identifiers.get(5)) {				
									
									String combined_name = layer1 + layer2 + layer3 + layer4 + layer5 + layer6;
									int strata_id = Collections.binarySearch(sm_strata, combined_name);
									
									if (strata_id < 0) {
										sm_strata.add(combined_name);
										sm_method_choice_for_strata.add(new ArrayList<String>());
										strata_id = sm_strata.size() - 1;
									}
									
									for (String method : sm_method_choice.get(0)) {
										for (String choice : sm_method_choice.get(1)) {
											if (!sm_method_choice_for_strata.get(strata_id).contains(method + " " + choice)) {
												sm_method_choice_for_strata.get(strata_id).add(method + " " + choice);
											}
										}
									}
									
								}							
							}						
						}					
					}				
				}	
			}	
			
			// regeneration 5 layers
			for (String layer1 : sm_static_identifiers.get(0)) {
				for (String layer2 : sm_static_identifiers.get(1)) {
					for (String layer3 : sm_static_identifiers.get(2)) {
						for (String layer4 : sm_static_identifiers.get(3)) {
							for (String layer5 : sm_static_identifiers.get(4)) {
									
								String combined_name = layer1 + layer2 + layer3 + layer4 + layer5;
								int strata_5layers_id = Collections.binarySearch(sm_strata_without_sizeclass, combined_name);
								
								if (strata_5layers_id < 0) {
									sm_strata_without_sizeclass.add(combined_name);
									sm_method_choice_for_strata_without_sizeclass.add(new ArrayList<String>());
									strata_5layers_id = sm_strata_without_sizeclass.size() - 1;
								}
								
								for (String method : sm_method_choice.get(0)) {
									for (String choice : sm_method_choice.get(1)) {
										if (!sm_method_choice_for_strata_without_sizeclass.get(strata_5layers_id).contains(method + " " + choice)) {
											sm_method_choice_for_strata_without_sizeclass.get(strata_5layers_id).add(method + " " + choice);
										}
									}
								}
									
							}						
						}					
					}				
				}	
			}
			
			// regeneration 4 layers
			for (String layer1 : sm_static_identifiers.get(0)) {
				for (String layer2 : sm_static_identifiers.get(1)) {
					for (String layer3 : sm_static_identifiers.get(2)) {
						for (String layer4 : sm_static_identifiers.get(3)) {		
							
							String combined_name = layer1 + layer2 + layer3 + layer4;
							int strata_4layers_id = Collections.binarySearch(sm_strata_without_sizeclass_and_covertype, combined_name);
							
							if (strata_4layers_id < 0) {
								sm_strata_without_sizeclass_and_covertype.add(combined_name);
								sm_method_choice_for_strata_without_sizeclass_and_covertype.add(new ArrayList<String>());
								strata_4layers_id = sm_strata_without_sizeclass_and_covertype.size() - 1;
							}	
							
							for (String method : sm_method_choice.get(0)) {
								for (String choice : sm_method_choice.get(1)) {
									if (!sm_method_choice_for_strata_without_sizeclass_and_covertype.get(strata_4layers_id).contains(method + " " + choice)) {
										sm_method_choice_for_strata_without_sizeclass_and_covertype.get(strata_4layers_id).add(method + " " + choice);
									}
								}
							}
							
						}					
					}				
				}	
			}	
		}	
	}	
	
	public List<String> get_sm_strata() {
		return sm_strata;
	}
	
	public List<String> get_sm_strata_without_sizeclass() {
		return sm_strata_without_sizeclass;
	}
	
	public List<String> get_sm_strata_without_sizeclass_and_covertype() {
		return sm_strata_without_sizeclass_and_covertype;
	}
	
	public List<List<String>> get_sm_method_choice_for_strata() {
		return sm_method_choice_for_strata;
	}
	
	public List<List<String>> get_sm_method_choice_for_strata_without_sizeclass() {
		return sm_method_choice_for_strata_without_sizeclass;
	}

	public List<List<String>> get_sm_method_choice_for_strata_without_sizeclass_and_covertype() {
		return sm_method_choice_for_strata_without_sizeclass_and_covertype;
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_03_modeled_strata
	private int MO_totalRows, MO_totalColumns;
	private String[][] MO_value;
		
	public void read_model_strata(File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	// Remove the first row (Column names)
			String[] a = list.toArray(new String[list.size()]);
						
			MO_totalRows = a.length;
			MO_totalColumns = a[0].split(delimited).length;		// a[0].split(delimited) = String[] of the first row (this is the row below the column headers row which was removed already)				
			MO_value = new String[MO_totalRows][MO_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 0; i < MO_totalRows; i++) {
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < MO_totalColumns; j++) {
					MO_value[i][j] = rowValue[j].replaceAll("\\s+", "");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public String[][] get_MO_Values() {
		return MO_value;
	}

	public int get_MO_TotalRows() {
		return MO_totalRows;
	}
	
	public int get_MO_TotalColumns() {
		return MO_totalColumns;
	}
	
	public List<String> get_model_strata() {
		List<String> modeled_strata = new ArrayList<String>();
		for (int i = 0; i < MO_totalRows; i++) {		
			String combined_name = MO_value[i][1] + MO_value[i][2] + MO_value[i][3] + MO_value[i][4] + MO_value[i][5] + MO_value[i][6];
			modeled_strata.add(combined_name);
		}
		return modeled_strata;
	}
	
	public List<String> get_model_strata_without_sizeclass() {
		List<String> model_strata_without_sizeclass = new ArrayList<String>();
		for (int i = 0; i < MO_totalRows; i++) {		
			String combined_name = MO_value[i][1] + MO_value[i][2] + MO_value[i][3] + MO_value[i][4] + MO_value[i][5];
			if (!model_strata_without_sizeclass.contains(combined_name)) {	// only add to list if list does not contain the value
				model_strata_without_sizeclass.add(combined_name);
			}
		}
		return model_strata_without_sizeclass;
	}	
	
	public List<String> get_model_strata_without_sizeclass_and_covertype() {
		List<String> model_strata_without_sizeclass_and_covertype = new ArrayList<String>();
		for (int i = 0; i < MO_totalRows; i++) {		
			String combined_name = MO_value[i][1] + MO_value[i][2] + MO_value[i][3] + MO_value[i][4];			
			if (!model_strata_without_sizeclass_and_covertype.contains(combined_name)) {	// only add to list if list does not contain the value
				model_strata_without_sizeclass_and_covertype.add(combined_name);
			}	
		}
		return model_strata_without_sizeclass_and_covertype;
	}
		
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_04_covertype_conversion_clearcut
	private List<String> covertype_conversions_and_existing_rotation_ages_list;	
	private List<String> covertype_conversions_and_regeneration_rotation_ages_list;	
	private List<String> covertype_conversions_list;	
	
	public void read_covertype_conversion_clearcut (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			covertype_conversions_and_existing_rotation_ages_list = new ArrayList<String>();
			covertype_conversions_and_regeneration_rotation_ages_list = new ArrayList<String>();
			covertype_conversions_list = new ArrayList<String>();
			
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");
				String listname = values[0] + " " + values[1];
				covertype_conversions_list.add(listname);

				int e_RA_min = Integer.parseInt(values[2]);
				int e_RA_max = Integer.parseInt(values[3]);
				int r_RA_min = Integer.parseInt(values[4]);
				int r_RA_max = Integer.parseInt(values[5]);

				for (int age = e_RA_min; age <= e_RA_max; age++) {
					String temp = values[0] + " " + values[1] + " " + age;
					covertype_conversions_and_existing_rotation_ages_list.add(temp);
				}
				
				for (int age = r_RA_min; age <= r_RA_max; age++) {
					String temp = values[0] + " " + values[1] + " " + age;
					covertype_conversions_and_regeneration_rotation_ages_list.add(temp);
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public List<String> get_covertype_conversions_and_existing_rotation_ages() {
		return covertype_conversions_and_existing_rotation_ages_list;
	}	
	
	public List<String> get_covertype_conversions_and_regeneration_rotation_ages() {
		return covertype_conversions_and_regeneration_rotation_ages_list;
	}
	
	public List<String> get_covertype_conversions() {
		return covertype_conversions_list;
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------		
	//For input_05_covertype_conversion_replacing
	private List<Double> rdProportion_list;	
	
	public void read_covertype_conversion_replacing (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			rdProportion_list = new ArrayList<Double>();		
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");				
				rdProportion_list.add(Double.parseDouble(values[3]));
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public double[] getRDProportion () {		
		double[] array = Stream.of(rdProportion_list.toArray(new Double[rdProportion_list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_06_natural_disturbances_non_replacing
	private List<Double> msProportion_list, bsProportion_list;	
	
	public void read_natural_disturbances_non_replacing(File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			msProportion_list = new ArrayList<Double>();
			bsProportion_list = new ArrayList<Double>();
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");		
				if (values[2].equals("null")) values[2] = "-9999";		// null --> -9999 indicate the bounds should be turned off
				if (values[3].equals("null")) values[3] = "-9999";		// null --> -9999 indicate the bounds should be turned off
				msProportion_list.add(Double.parseDouble(values[2]));
				bsProportion_list.add(Double.parseDouble(values[3]));
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public double[] getMSFireProportion () {		
		double[] array = Stream.of(msProportion_list.toArray(new Double[msProportion_list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	public double[] getBSFireProportion () {		
		double[] array = Stream.of(bsProportion_list.toArray(new Double[bsProportion_list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_07_natural_disturbances_replacing
	private List<String> disturbance_condition_list;
	
	public void read_natural_disturbances_replacing (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			disturbance_condition_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			disturbance_condition_list.remove(0);	// Remove the first row (Column names)

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public List<String> get_disturbance_condition_list() {
		return disturbance_condition_list;
	}			
				
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_08_management_cost
	private List<String> cost_condition_list;
	
	public void read_management_cost (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			cost_condition_list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			cost_condition_list.remove(0);	// Remove the first row (Column names)

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
	
	public List<String> get_cost_condition_list() {
		return cost_condition_list;
	}
			
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	//For input_09_basic_constraints
	private List<String> constraint_column_names_list;
	private int bc_total_rows, bc_total_columns;
	private String[][] bc_values;
	private int constraint_id_col, constraint_description_col, constraint_type_col, constraint_multiplier_col, lowerbound_col, lowerbound_perunit_penalty_col,
			upperbound_col, upperbound_perunit_penalty_col, parameter_index_col, static_identifiers_col, dynamic_identifiers_col;

	public void read_basic_constraints (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
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
			constraint_id_col = constraint_column_names_list.indexOf("bc_id");
			constraint_description_col = constraint_column_names_list.indexOf("bc_description");
			constraint_type_col = constraint_column_names_list.indexOf("bc_type");
			constraint_multiplier_col = constraint_column_names_list.indexOf("bc_multiplier");
			lowerbound_col = constraint_column_names_list.indexOf("lowerbound");
			lowerbound_perunit_penalty_col = constraint_column_names_list.indexOf("lowerbound_perunit_penalty");
			upperbound_col = constraint_column_names_list.indexOf("upperbound");
			upperbound_perunit_penalty_col = constraint_column_names_list.indexOf("upperbound_perunit_penalty");
			parameter_index_col = constraint_column_names_list.indexOf("parameter_index");
			static_identifiers_col = constraint_column_names_list.indexOf("static_identifiers");
			dynamic_identifiers_col = constraint_column_names_list.indexOf("dynamic_identifiers");	
			
			//Values in all rows and columns
			bc_total_rows = a.length;
			bc_total_columns = columnName.length;				
			bc_values = new String[bc_total_rows][bc_total_columns];
		
			// read all values from all rows and columns
			for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < bc_total_columns; j++) {
					bc_values[i][j] = rowValue[j];
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}	

	public List<String> get_constraint_column_names_list() {
		return constraint_column_names_list;
	}
	
	public String[][] get_bc_values () {
		return bc_values;
	}

	public int get_UC_TotalRows () {
		return bc_total_rows;
	}
	
	public int get_UC_TotalColumns () {
		return bc_total_columns;
	}	
		
	
	public int get_total_hardConstraints () {
		int total =0;	
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("HARD")) total++;
		}	
		return total;
	}	

	public double[] get_hardConstraints_LB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("HARD") && !bc_values[i][lowerbound_col].equals("null")) list.add(Double.parseDouble(bc_values[i][lowerbound_col]));
			if (bc_values[i][constraint_type_col].equals("HARD") && bc_values[i][lowerbound_col].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_hardConstraints_UB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("HARD")  && !bc_values[i][upperbound_col].equals("null")) list.add(Double.parseDouble(bc_values[i][upperbound_col]));
			if (bc_values[i][constraint_type_col].equals("HARD")  && bc_values[i][upperbound_col].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}
	
	public int get_total_softConstraints () {
		int total =0;	
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("SOFT")) total++;
		}	
		return total;
	}		
				
	
	public double[] get_softConstraints_LB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("SOFT") && !bc_values[i][lowerbound_col].equals("null")) list.add(Double.parseDouble(bc_values[i][lowerbound_col]));
			if (bc_values[i][constraint_type_col].equals("SOFT") && bc_values[i][lowerbound_col].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}				
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_softConstraints_UB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("SOFT")  && !bc_values[i][upperbound_col].equals("null")) list.add(Double.parseDouble(bc_values[i][upperbound_col]));
			if (bc_values[i][constraint_type_col].equals("SOFT")  && bc_values[i][upperbound_col].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	public double[] get_softConstraints_LB_Weight () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("SOFT") && !bc_values[i][lowerbound_perunit_penalty_col].equals("null")) list.add(Double.parseDouble(bc_values[i][lowerbound_perunit_penalty_col]));
			if (bc_values[i][constraint_type_col].equals("SOFT") && bc_values[i][lowerbound_perunit_penalty_col].equals("null")) list.add((double) 0);		//Change "null" value of LB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_softConstraints_UB_Weight () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row			
			if (bc_values[i][constraint_type_col].equals("SOFT") && !bc_values[i][upperbound_perunit_penalty_col].equals("null")) list.add(Double.parseDouble(bc_values[i][upperbound_perunit_penalty_col]));
			if (bc_values[i][constraint_type_col].equals("SOFT") && bc_values[i][upperbound_perunit_penalty_col].equals("null")) list.add((double) 0);		//Change "null" value of UB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}		

	
	public int get_total_freeConstraints () {
		int total =0;	
		for (int i = 1; i < bc_total_rows; i++) {		//From 2nd row		
			if (bc_values[i][constraint_type_col].equals("FREE")) total++;
		}	
		return total;
	}	
	
	
	public List<List<String>> get_static_identifiers_in_row (int row) {
		List<List<String>> static_identifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] staticLayer_Info = bc_values[row][static_identifiers_col].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_staticIdentifiers = staticLayer_Info.length;
		
		//Get all static Identifiers to be in the list
		for (int i = 0; i < total_staticIdentifiers; i++) {		//6 first identifiers is strata 6 layers (layer 0 to 5)		
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = staticLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			static_identifiers.add(thisIdentifier);
		}
			
		return static_identifiers;
	}	
	
	
	public List<String> get_static_strata (int row) {
		List<String> static_strata = new ArrayList<String>();
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		
		List<String> layer1 = static_identifiers.get(0);
		List<String> layer2 = static_identifiers.get(1);
		List<String> layer3 = static_identifiers.get(2);
		List<String> layer4 = static_identifiers.get(3);
		List<String> layer5 = static_identifiers.get(4);
		List<String> layer6 = static_identifiers.get(5);		
		
		// first 6 layers
		for (int i1 = 0; i1 < layer1.size(); i1++) {
			for (int i2 = 0; i2 < layer2.size(); i2++) {
				for (int i3 = 0; i3 < layer3.size(); i3++) {
					for (int i4 = 0; i4 < layer4.size(); i4++) {
						for (int i5 = 0; i5 < layer5.size(); i5++) {
							for (int i6 = 0; i6 < layer6.size(); i6++) {
								String combined_name = layer1.get(i1) + layer2.get(i2) + layer3.get(i3) + layer4.get(i4) + layer5.get(i5) + layer6.get(i6);
								static_strata.add(combined_name);
							}							
						}						
					}					
				}				
			}	
		}
		return static_strata;
	}	

	
	public List<String> get_static_strata_without_sizeclass(int row) {	
		List<String> static_strata_without_sizeclass = new ArrayList<String>();
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		
		List<String> layer1 = static_identifiers.get(0);
		List<String> layer2 = static_identifiers.get(1);
		List<String> layer3 = static_identifiers.get(2);
		List<String> layer4 = static_identifiers.get(3);
		List<String> layer5 = static_identifiers.get(4);
				
		// first 5 layers
		for (int i1 = 0; i1 < layer1.size(); i1++) {
			for (int i2 = 0; i2 < layer2.size(); i2++) {
				for (int i3 = 0; i3 < layer3.size(); i3++) {
					for (int i4 = 0; i4 < layer4.size(); i4++) {
						for (int i5 = 0; i5 < layer5.size(); i5++) {
							String combined_name = layer1.get(i1) + layer2.get(i2) + layer3.get(i3) + layer4.get(i4) + layer5.get(i5);
							if (!static_strata_without_sizeclass.contains(combined_name)) static_strata_without_sizeclass.add(combined_name);						
						}					
					}					
				}				
			}	
		}
		return static_strata_without_sizeclass;
	}
	
	
	public List<String> get_static_strata_without_sizeclass_and_covertype (int row) {	
		List<String> static_strata_without_sizeclass_and_covertype = new ArrayList<String>();
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		
		List<String> layer1 = static_identifiers.get(0);
		List<String> layer2 = static_identifiers.get(1);
		List<String> layer3 = static_identifiers.get(2);
		List<String> layer4 = static_identifiers.get(3);
				
		// first 4 layers
		for (int i1 = 0; i1 < layer1.size(); i1++) {
			for (int i2 = 0; i2 < layer2.size(); i2++) {
				for (int i3 = 0; i3 < layer3.size(); i3++) {
					for (int i4 = 0; i4 < layer4.size(); i4++) {
						String combined_name = layer1.get(i1) + layer2.get(i2) + layer3.get(i3) + layer4.get(i4);
						if (!static_strata_without_sizeclass_and_covertype.contains(combined_name)) static_strata_without_sizeclass_and_covertype.add(combined_name);						
					}					
				}				
			}	
		}
		return static_strata_without_sizeclass_and_covertype;
	}	
	
	
	
	public List<String> get_static_methods (int row) {	
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);
		return static_identifiers.get(6);
	}
	
	public List<String> get_static_periods (int row) {	
		List<List<String>> static_identifiers = get_static_identifiers_in_row(row);	
		return static_identifiers.get(7);
	}	

	
	
	public List<List<String>> get_dynamic_identifiers_in_row (int row) {
		List<List<String>> dynamic_identifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] dynamicLayer_Info = bc_values[row][dynamic_identifiers_col].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_dynamicIdentifiers = dynamicLayer_Info.length;
	
		
		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamicIdentifiers; i++) {	
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = dynamicLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier column index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			dynamic_identifiers.add(thisIdentifier);
		}
			
		return dynamic_identifiers;
	}	
	
	
	public List<String> get_dynamic_identifiers_column_indexes_in_row (int row) {	//Column 8 in the GUI table "Dynamic identifiers". The whole is contained by UC_value[i][8]
		List<String> dynamic_identifiers_column_indexes = new ArrayList<String>();
			
		//Read the whole cell into array
		String[] dynamicLayer_Info = bc_values[row][dynamic_identifiers_col].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_dynamicIdentifiers = dynamicLayer_Info.length;

		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamicIdentifiers; i++) {	
			String[] identifierElements = dynamicLayer_Info[i].split("\\s+");				//space delimited
			//add the first element which is the identifier column index
			dynamic_identifiers_column_indexes.add(identifierElements[0].replaceAll("\\s+",""));
		}
			
		return dynamic_identifiers_column_indexes;
	}	
	
	
	public List<String> get_parameters_indexes (int row) {	
		List<String> parameters_indexes_list = new ArrayList<String>();
		
		//Read the whole cell into array
		String[] parameter_Info = bc_values[row][parameter_index_col].split("\\s+");			
		for (int i = 0; i < parameter_Info.length; i++) {	
			parameters_indexes_list.add(parameter_Info[i].replaceAll("\\s+",""));
		}				
		return parameters_indexes_list;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	//For input_10_flow_constraints
	private List<String> flow_column_names_list;
	private int flow_totalRows, flow_totalColumns;
	private String[][] flow_value;
	private int flow_id_col, flow_description_col, flow_arrangement_col, flow_type_col, lowerbound_percentage_col, upperbound_percentage_col;		

	public void read_flow_constraints (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// all lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
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
			flow_totalRows = a.length;
			flow_totalColumns = columnName.length;				
			flow_value = new String[flow_totalRows][flow_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < flow_totalColumns; j++) {
					flow_value[i][j] = rowValue[j];
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}				
	
	public List<String> get_flow_column_names_list() {
		return flow_column_names_list;
	}
	
	public String[][] get_flow_values () {
		return flow_value;
	}

	public int get_flow_TotalRows () {
		return flow_totalRows;
	}
	
	public int get_flow_TotalColumns () {
		return flow_totalColumns;
	}
		
	public List<Integer> get_flow_id_list() {
		List<Integer> get_flow_id_list = new ArrayList<Integer>();
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
			get_flow_id_list.add(Integer.parseInt(flow_value[i][flow_id_col]));
		}
		return get_flow_id_list;
	}
	
	public List<String> get_flow_description_list() {
		List<String> flow_description_list = new ArrayList<String>();	
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
			flow_description_list.add(flow_value[i][flow_description_col]);
		}
		return flow_description_list;
	}
	
	public List<String> get_flow_arrangement_list() {
		List<String> flow_arrangement_list = new ArrayList<String>();	
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
			flow_arrangement_list.add(flow_value[i][flow_arrangement_col]);
		}
		return flow_arrangement_list;
	}
	
	public List<String> get_flow_type_list() {
		List<String> flow_type_list = new ArrayList<String>();	
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
			flow_type_list.add(flow_value[i][flow_type_col]);
		}
		return flow_type_list;
	}
	
	public List<Double> get_flow_lowerbound_percentage_list() {
		List<Double> flow_lowerbound_percentage_list = new ArrayList<Double>();
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
			if (!flow_value[i][lowerbound_percentage_col].equals("null")) {
				flow_lowerbound_percentage_list.add(Double.parseDouble(flow_value[i][lowerbound_percentage_col]));
			} else {
				flow_lowerbound_percentage_list.add(null);
			}
		}
		return flow_lowerbound_percentage_list;
	}
	
	public List<Double> get_flow_upperbound_percentage_list() {
		List<Double> flow_upperbound_percentage_list = new ArrayList<Double>();
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row						
			if (!flow_value[i][upperbound_percentage_col].equals("null")) {
				flow_upperbound_percentage_list.add(Double.parseDouble(flow_value[i][upperbound_percentage_col]));
			} else {
				flow_upperbound_percentage_list.add(null);
			}
		}
		return flow_upperbound_percentage_list;
	}	
	
	public List<List<List<Integer>>> get_flow_set_list () {	
		List<List<List<Integer>>> flow_set_list = new ArrayList<List<List<Integer>>>();	
		for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
			List<List<Integer>> this_set = new ArrayList<List<Integer>>();				
			String[] flow_arrangement_info = flow_value[i][flow_arrangement_col].split(";");	// Read the whole cell 'flow_arrangement'
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
}
