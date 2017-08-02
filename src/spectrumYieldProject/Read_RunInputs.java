package spectrumYieldProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Read_RunInputs {
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_01_general_inputs.txt
	private int GI_totalRows, GI_totalColumns;
	private String[][] GI_value;

	public void readGeneralInputs (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]);
								
			//Read the first row
			String[] columnName = a[0].split(delimited);	//Read the first row
			GI_totalRows = a.length - 1;	// - 1st row which is the column name
			GI_totalColumns = columnName.length;				
			GI_value = new String[GI_totalRows][GI_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 0; i < GI_totalRows; i++) {		//From 1st row			
				String[] rowValue = a[i+1].split(delimited);		
				for (int j = 0; j < GI_totalColumns; j++) {
					GI_value[i][j] = rowValue[j].replaceAll("\\s+","");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public int get_total_periods() {
		return Integer.parseInt(GI_value[0][1]);
	}

	public double get_discount_rate() {
		return Double.parseDouble(GI_value[1][1]);
	}

	public String get_solver() {
		return GI_value[2][1].toString();
	}

	public int get_solving_time() {
		return Integer.parseInt(GI_value[3][1]);
	}

	public boolean get_export_problem() {
		return Boolean.parseBoolean(GI_value[4][1]);
	}

	public boolean get_export_solution() {
		return Boolean.parseBoolean(GI_value[5][1]);
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_02_modeled_strata
	private int MO_totalRows, MO_totalColumns;
	private String[][] MO_value;
		
	public void readManagementOptions (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]);
						
			//Read the first row into array. This will be Column names
			String[] columnName = a[0].split(delimited);
			MO_totalRows = a.length;
			MO_totalColumns = columnName.length;				
			MO_value = new String[MO_totalRows][MO_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 1; i < MO_totalRows; i++) {		//From 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < MO_totalColumns; j++) {
					MO_value[i][j] = rowValue[j].replaceAll("\\s+","");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public String[][] get_MO_Values () {
		return MO_value;
	}

	public int get_MO_TotalRows () {
		return MO_totalRows;
	}
	
	public int get_MO_TotalColumns () {
		return MO_totalColumns;
	}
	
	public List<String> get_modeled_strata () {
		List<String> modeled_strata = new ArrayList<String>();
		for (int i = 1; i < MO_totalRows; i++) {		//From 2nd row			
//			modeled_strata.add(MO_value[i][0]);	//1st column contains the full name with 6 layers letters
			String combined_name = MO_value[i][1] + MO_value[i][2] + MO_value[i][3] + MO_value[i][4] + MO_value[i][5] + MO_value[i][6];
			modeled_strata.add(combined_name);
		}
		return modeled_strata;
	}
	
	public List<String> get_modeled_strata_withoutSizeClass () {
		List<String> modeled_strata_withoutSizeClass = new ArrayList<String>();
		for (int i = 1; i < MO_totalRows; i++) {		//From 2nd row			
//			modeled_strata_withoutSizeClass.add(MO_value[i][0].substring(0,MO_value[i][0].length()-1));	//remove the last character to get name with 5 layers only
			String combined_name = MO_value[i][1] + MO_value[i][2] + MO_value[i][3] + MO_value[i][4] + MO_value[i][5];
			modeled_strata_withoutSizeClass.add(combined_name);
		}
		return modeled_strata_withoutSizeClass;
	}	
	
	public List<String> get_modeled_strata_withoutSizeClassandCoverType () {
		List<String> get_modeled_strata_withoutSizeClassandCoverType = new ArrayList<String>();
		for (int i = 1; i < MO_totalRows; i++) {		//From 2nd row			
//			get_modeled_strata_withoutSizeClassandCoverType.add(MO_value[i][0].substring(0,MO_value[i][0].length()-2));	//remove the last 2 characters to get name with 4 layers only
			String combined_name = MO_value[i][1] + MO_value[i][2] + MO_value[i][3] + MO_value[i][4];
			get_modeled_strata_withoutSizeClassandCoverType.add(combined_name);
		}
		return get_modeled_strata_withoutSizeClassandCoverType;
	}
		
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_03_clearcut_covertype_conversion
	private List<String> covertype_conversions_and_existing_rotation_ages_list;	
	private List<String> covertype_conversions_and_regeneration_rotation_ages_list;	
	private List<String> covertype_conversions_list;	
	
	public void readRequirements (File file) {
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
	//For input_04_replacingdisturbances_covertype_conversion
	private List<Double> rdProportion_list;	
	
	public void readRDCovertypeProportion (File file) {
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
	//For input_05_non_replacing_disturbances
	private List<Double> msProportion_list, bsProportion_list;	
	
	public void readMSPercent(File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			msProportion_list = new ArrayList<Double>();
			bsProportion_list = new ArrayList<Double>();
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");				
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
	//For input_06_replacing_disturbances
	private double[][] SRDProportion;	
	
	public void readRDPercent (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
			int ageClass = 0;
			int s5 = 0;
		
			//Define the size of array
			String[] firstRowValue = list.get(0).split("\t");
			int totalRows = list.size();
			int totalColumns = firstRowValue.length;	
			SRDProportion = new double[totalColumns - 1][totalRows + 1];		//This is 	P(s5,a)				since age = row + 1 the size should be [totalRows + 1]			
																											// - 1st column which is the age class column	
			
			//Put the values to arrays
			for (int i = 0; i < totalRows; i++) {
				ageClass = i + 1;
				String[] values = list.get(i).split("\t");	
				for (int j = 1; j < totalColumns; j++) {
					s5 = j - 1;
					SRDProportion[s5][ageClass] = Double.parseDouble(values[j]);							
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public double[][] getSRDProportion () {		
		return SRDProportion;
	}			
				
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_08_management_cost
	private List<String> cost_condition_list;
//	private List<String> condition_column_names_list;
//	private int condition_total_rows, condition_total_columns;
//	private String[][] condition_value;
//	private int priority_col, condition_description_col, action_percentage_col, conversion_percentage_col, condition_static_identifiers_col, condition_dynamic_identifiers_col;	
	
	
	public void readManagementCost (File file) {
		try {
//			// All lines to be in array
//			List<String> list;
//			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
//			String[] a = list.toArray(new String[list.size()]);
//
//			// Read the first row into array. This will be Column names
//			String[] columnName = a[0].split("\t");
//
//			// List of constraint column names
//			condition_column_names_list = Arrays.asList(columnName);	
//			priority_col = condition_column_names_list.indexOf("priority");
//			condition_description_col = condition_column_names_list.indexOf("condition_description");
//			action_percentage_col = condition_column_names_list.indexOf("action_percentage");
//			conversion_percentage_col = condition_column_names_list.indexOf("conversion_percentage");
//			condition_static_identifiers_col = condition_column_names_list.indexOf("static_identifiers");
//			condition_dynamic_identifiers_col = condition_column_names_list.indexOf("dynamic_identifiers");
//	
//			
//			// Values in all rows and columns
//			condition_total_rows = a.length;
//			condition_total_columns = columnName.length;				
//			condition_value = new String[condition_total_rows][condition_total_columns];
//		
//			// Read all values from all rows and columns
//			for (int i = 1; i < condition_total_rows; i++) { // From 2nd row
//				String[] rowValue = a[i].split("\t");
//				for (int j = 0; j < condition_total_columns; j++) {
//					condition_value[i][j] = rowValue[j];
//				}
//			}		
			
			
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
	private int BC_totalRows, BC_totalColumns;
	private String[][] BC_value;
	private int constraint_id_col, constraint_description_col, constraint_type_col, constraint_multiplier_col, lowerbound_col, lowerbound_perunit_penalty_col,
			upperbound_col, upperbound_perunit_penalty_col, parameter_index_col, static_identifiers_col, dynamic_identifiers_col;

	public void read_basic_constraints (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
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
			BC_totalRows = a.length;
			BC_totalColumns = columnName.length;				
			BC_value = new String[BC_totalRows][BC_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < BC_totalColumns; j++) {
					BC_value[i][j] = rowValue[j];
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}	

	public List<String> get_constraint_column_names_list() {
		return constraint_column_names_list;
	}
	
	public String[][] get_BC_Values () {
		return BC_value;
	}

	public int get_UC_TotalRows () {
		return BC_totalRows;
	}
	
	public int get_UC_TotalColumns () {
		return BC_totalColumns;
	}	
		
	
	public int get_total_hardConstraints () {
		int total =0;	
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("HARD")) total++;
		}	
		return total;
	}	

	public double[] get_hardConstraints_LB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("HARD") && !BC_value[i][lowerbound_col].equals("null")) list.add(Double.parseDouble(BC_value[i][lowerbound_col]));
			if (BC_value[i][constraint_type_col].equals("HARD") && BC_value[i][lowerbound_col].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_hardConstraints_UB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("HARD")  && !BC_value[i][upperbound_col].equals("null")) list.add(Double.parseDouble(BC_value[i][upperbound_col]));
			if (BC_value[i][constraint_type_col].equals("HARD")  && BC_value[i][upperbound_col].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}
	
	public int get_total_softConstraints () {
		int total =0;	
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("SOFT")) total++;
		}	
		return total;
	}		
				
	
	public double[] get_softConstraints_LB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("SOFT") && !BC_value[i][lowerbound_col].equals("null")) list.add(Double.parseDouble(BC_value[i][lowerbound_col]));
			if (BC_value[i][constraint_type_col].equals("SOFT") && BC_value[i][lowerbound_col].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}				
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_softConstraints_UB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("SOFT")  && !BC_value[i][upperbound_col].equals("null")) list.add(Double.parseDouble(BC_value[i][upperbound_col]));
			if (BC_value[i][constraint_type_col].equals("SOFT")  && BC_value[i][upperbound_col].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	public double[] get_softConstraints_LB_Weight () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("SOFT") && !BC_value[i][lowerbound_perunit_penalty_col].equals("null")) list.add(Double.parseDouble(BC_value[i][lowerbound_perunit_penalty_col]));
			if (BC_value[i][constraint_type_col].equals("SOFT") && BC_value[i][lowerbound_perunit_penalty_col].equals("null")) list.add((double) 0);		//Change "null" value of LB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_softConstraints_UB_Weight () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row			
			if (BC_value[i][constraint_type_col].equals("SOFT") && !BC_value[i][upperbound_perunit_penalty_col].equals("null")) list.add(Double.parseDouble(BC_value[i][upperbound_perunit_penalty_col]));
			if (BC_value[i][constraint_type_col].equals("SOFT") && BC_value[i][upperbound_perunit_penalty_col].equals("null")) list.add((double) 0);		//Change "null" value of UB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}		

	
	public int get_total_freeConstraints () {
		int total =0;	
		for (int i = 1; i < BC_totalRows; i++) {		//From 2nd row		
			if (BC_value[i][constraint_type_col].equals("null")) BC_value[i][constraint_type_col] = "FREE";
			if (BC_value[i][constraint_type_col].equals("FREE")) total++;
		}	
		return total;
	}	
	
	
	public List<List<String>> get_all_static_identifiers_in_row (int row) {
		List<List<String>> all_staticIdentifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] staticLayer_Info = BC_value[row][static_identifiers_col].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_staticIdentifiers = staticLayer_Info.length;
		
		//Get all static Identifiers to be in the list
		for (int i = 0; i < total_staticIdentifiers; i++) {		//6 first identifiers is strata 6 layers (layer 0 to 5)		
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = staticLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			all_staticIdentifiers.add(thisIdentifier);
		}
			
		return all_staticIdentifiers;
	}	
	
	
	public List<String> get_static_strata (int row) {
		List<String> static_strata = new ArrayList<String>();
		List<List<String>> all_staticIdentifiers = get_all_static_identifiers_in_row(row);
		
		List<String> layer1 = all_staticIdentifiers.get(0);
		List<String> layer2 = all_staticIdentifiers.get(1);
		List<String> layer3 = all_staticIdentifiers.get(2);
		List<String> layer4 = all_staticIdentifiers.get(3);
		List<String> layer5 = all_staticIdentifiers.get(4);
		List<String> layer6 = all_staticIdentifiers.get(5);
		
		
		//first 6 layers
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

	
	public List<String> get_static_strata_withoutSizeClassandCoverType (int row) {	
		List<String> static_strata_withoutSizeClassandCoverType = new ArrayList<String>();
		List<List<String>> all_static_identifiers = get_all_static_identifiers_in_row(row);
		
		List<String> layer1 = all_static_identifiers.get(0);
		List<String> layer2 = all_static_identifiers.get(1);
		List<String> layer3 = all_static_identifiers.get(2);
		List<String> layer4 = all_static_identifiers.get(3);
				
		//first 4 layers
		for (int i1 = 0; i1 < layer1.size(); i1++) {
			for (int i2 = 0; i2 < layer2.size(); i2++) {
				for (int i3 = 0; i3 < layer3.size(); i3++) {
					for (int i4 = 0; i4 < layer4.size(); i4++) {
						String combined_name = layer1.get(i1) + layer2.get(i2) + layer3.get(i3) + layer4.get(i4);
						static_strata_withoutSizeClassandCoverType.add(combined_name);						
					}					
				}				
			}	
		}
		return static_strata_withoutSizeClassandCoverType;
	}	
	
	
	
	public List<String> get_static_methods (int row) {	
		List<List<String>> all_static_identifiers = get_all_static_identifiers_in_row(row);
		return all_static_identifiers.get(6);
	}
	
	public List<String> get_static_periods (int row) {	
		List<List<String>> all_static_identifiers = get_all_static_identifiers_in_row(row);	
		return all_static_identifiers.get(7);
	}	

	
	
	public List<List<String>> get_all_dynamic_identifiers_in_row (int row) {
		List<List<String>> all_dynamicIdentifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] dynamicLayer_Info = BC_value[row][dynamic_identifiers_col].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_dynamicIdentifiers = dynamicLayer_Info.length;
	
		
		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamicIdentifiers; i++) {	
			List<String> thisIdentifier = new ArrayList<String>();
			
			String[] identifierElements = dynamicLayer_Info[i].split("\\s+");				//space delimited
			for (int j = 1; j < identifierElements.length; j++) {		//Ignore the first element which is the identifier column index, so we loop from 1 not 0
				thisIdentifier.add(identifierElements[j].replaceAll("\\s+",""));		//Add element name, if name has spaces then remove all the spaces
			}
			
			all_dynamicIdentifiers.add(thisIdentifier);
		}
			
		return all_dynamicIdentifiers;
	}	
	
	
	public List<String> get_all_dynamicIdentifiers_columnsIndexes_in_row (int row) {	//Column 8 in the GUI table "Dynamic identifiers". The whole is contained by UC_value[i][8]
		List<String> all_dynamicIdentifiers_columnIndexes = new ArrayList<String>();
			
		//Read the whole cell into array
		String[] dynamicLayer_Info = BC_value[row][dynamic_identifiers_col].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
		int total_dynamicIdentifiers = dynamicLayer_Info.length;

		//Get all dynamic Identifiers to be in the list
		for (int i = 0; i < total_dynamicIdentifiers; i++) {	
			String[] identifierElements = dynamicLayer_Info[i].split("\\s+");				//space delimited
			//add the first element which is the identifier column index
			all_dynamicIdentifiers_columnIndexes.add(identifierElements[0].replaceAll("\\s+",""));
		}
			
		return all_dynamicIdentifiers_columnIndexes;
	}	
	
	
	public List<String> get_Parameters_indexes_list (int row) {	
		List<String> parameters_indexes_list = new ArrayList<String>();
		
		//Read the whole cell into array
		String[] parameter_Info = BC_value[row][parameter_index_col].split("\\s+");			
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
