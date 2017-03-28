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
	
	public int get_total_Periods () {
		return Integer.parseInt(GI_value[0][1]);
	}
	
	public int get_SolvingTimeLimit () {
		return Integer.parseInt(GI_value[1][1]);
	}
	
	public double get_AnnualDiscountRate () {
		return Double.parseDouble(GI_value[2][1]);
	}
	
	public String get_Solver () {
		return GI_value[3][1].toString();
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
	private List<String> coverTypeConversions_and_RotationAges_list;	
	private List<String> coverTypeConversions_list;	
	
	public void readRequirements (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			coverTypeConversions_and_RotationAges_list = new ArrayList<String>();
			coverTypeConversions_list = new ArrayList<String>();
			
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");
				String listname = values[0] + " " + values[1];
				coverTypeConversions_list.add(listname);

				int RA_min = Integer.parseInt(values[2]);
				int RA_max = Integer.parseInt(values[3]);

				for (int age = RA_min; age <= RA_max; age++) {
					String listname2 = values[0] + " " + values[1] + " " + age;
					coverTypeConversions_and_RotationAges_list.add(listname2);
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public List<String> getcoverTypeConversions_and_RotationAges () {
		return coverTypeConversions_and_RotationAges_list;
	}		
	
	public List<String> getCoverTypeConversions () {
		return coverTypeConversions_list;
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------		
	//For input_04_replacingdisturbances_covertype_conversion
	private List<Double> SRDrequirementProportion_list;	
	
	public void readRDCovertypeProportion (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			SRDrequirementProportion_list = new ArrayList<Double>();		
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");				
				SRDrequirementProportion_list.add(Double.parseDouble(values[3]));
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public double[] getSRDrequirementProportion () {		
		double[] array = Stream.of(SRDrequirementProportion_list.toArray(new Double[SRDrequirementProportion_list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_05_mixed_severity_wildfire
	private List<Double> msFireProportion_list;	
	
	public void readMSPercent (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			msFireProportion_list = new ArrayList<Double>();		
			for (int i = 0; i < list.size(); i++) {
				String[] values = list.get(i).split("\t");				
				msFireProportion_list.add(Double.parseDouble(values[2]));
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
							
	public double[] getMSFireProportion () {		
		double[] array = Stream.of(msFireProportion_list.toArray(new Double[msFireProportion_list.size()])).mapToDouble(Double::doubleValue).toArray();
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
	//For input_07_base_cost
	private List<Double> baseCost_acres;
	private double[][] baseCost_yieldtables;	
	private List<String> action_type_list;
	
	public void readBaseCost (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			//Define the size of array
			String[] firstRowValue = list.get(0).split("\t");
			int totalRows = list.size();
			int totalColumns = firstRowValue.length;
			baseCost_yieldtables = new double[totalRows][totalColumns - 2];			// - 2 columns which are action_type & acres
			
			//Define lists
			baseCost_acres = new ArrayList<Double>();
			action_type_list = new ArrayList<String>();
						
			//Put the values to arrays and lists
			for (int i = 0; i < totalRows; i++) {
				String[] values = list.get(i).split("\t");	
				
				action_type_list.add(values[0]);						//1st column is action_type
				baseCost_acres.add(Double.parseDouble(values[1]));		//2nd column is base cost for acres
				
				for (int j = 2; j < totalColumns; j++) {
					baseCost_yieldtables[i][j - 2] = Double.parseDouble(values[j]);							
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
	
	public List<String> get_action_type_list() {
		return action_type_list;
	}
	
	public double[] getbaseCost_acres() {		
		double[] array = Stream.of(baseCost_acres.toArray(new Double[baseCost_acres.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	public double[][] getbaseCost_yieldtables() {		
		return baseCost_yieldtables;
	}			

	//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For input_08_cost_adjustment
	private List<String> cost_staticCondition_list;
	private double[] cost_adjusted_percentage;	
	
	public void readCostAdjustment (File file) {
		try {
			// All lines except the 1st line to be in a list;		
			List<String> list;	
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	//Remove the first row (Column names)
		
			//Define the size of array
			String[] firstRowValue = list.get(0).split("\t");
			int totalRows = list.size();
			int totalColumns = firstRowValue.length;
			cost_adjusted_percentage = new double[totalRows];
			
			//Define list
			cost_staticCondition_list = new ArrayList<String>();
						
			//Put the values to arrays and lists
			for (int i = 0; i < totalRows; i++) {
				String[] values = list.get(i).split("\t");				
				cost_staticCondition_list.add(values[0] + values[1] +values[2]);	// action_list + layer_id + attribute_id
				cost_adjusted_percentage[i] = Double.parseDouble(values[3]);		//4th column is the cost_adjusted_percentage  Double.parseDouble(values[1]));		
			}
			
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}		
	
	public List<String> get_cost_staticCondition_list() {
		return cost_staticCondition_list;
	}
	
	public double[] get_cost_adjusted_percentage() {		
		return cost_adjusted_percentage;
	}		
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	//For input_09_basic_constraints
	private List<String> constraint_column_names_list;
	private int BC_totalRows, BC_totalColumns;
	private String[][] BC_value;
	private int constraint_id_col, constraint_description_col, constraint_type_col, lowerbound_col, lowerbound_perunit_penalty_col,
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
			constraint_id_col = constraint_column_names_list.indexOf("id");
			constraint_description_col = constraint_column_names_list.indexOf("description");
			constraint_type_col = constraint_column_names_list.indexOf("type");
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
			if (BC_value[i][constraint_type_col].equals("FREE")) total++;
		}	
		return total;
	}	
	
	
	public List<List<String>> get_all_staticIdentifiers_in_row (int row) {	//Column 7 in the GUI table "Static identifiers". The whole is contained by UC_value[i][7]
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
		List<List<String>> all_staticIdentifiers = get_all_staticIdentifiers_in_row(row);
		
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
		List<List<String>> all_staticIdentifiers = get_all_staticIdentifiers_in_row(row);
		
		List<String> layer1 = all_staticIdentifiers.get(0);
		List<String> layer2 = all_staticIdentifiers.get(1);
		List<String> layer3 = all_staticIdentifiers.get(2);
		List<String> layer4 = all_staticIdentifiers.get(3);
				
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
	
	
	
	public List<String> get_static_SilvivulturalMethods (int row) {	
		List<List<String>> all_staticIdentifiers = get_all_staticIdentifiers_in_row(row);
		List<String> static_SilvivulturalMethods = all_staticIdentifiers.get(6);
		return static_SilvivulturalMethods;
	}
	
	public List<String> get_static_timePeriods (int row) {	
		List<List<String>> all_staticIdentifiers = get_all_staticIdentifiers_in_row(row);
		List<String> static_timePeriods = all_staticIdentifiers.get(7);	
		return static_timePeriods;
	}	

	
	
	public List<List<String>> get_all_dynamicIdentifiers_in_row (int row) {	//Column 8 in the GUI table "Dynamic identifiers". The whole is contained by UC_value[i][8]
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
	private int flow_id_col, flow_description_col, flow_arrangement_col, flow_type_col, relaxed_percentage_col;		
	private List<List<List<Integer>>> flow_set_list;
	private List<String> flow_type_list;
	private List<Double> flow_relaxed_percentage_list;

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
			flow_id_col = flow_column_names_list.indexOf("id");
			flow_description_col = flow_column_names_list.indexOf("description");
			flow_arrangement_col = flow_column_names_list.indexOf("flow_arrangement");
			flow_type_col = flow_column_names_list.indexOf("type");
			relaxed_percentage_col = flow_column_names_list.indexOf("relaxed_percentage");

			
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
			
			// add value to the following lists: 	type	relaxed_percentage
			flow_type_list = new ArrayList<String>();	
			flow_relaxed_percentage_list = new ArrayList<Double>();
			for (int i = 1; i < flow_totalRows; i++) {		// from 2nd row			
				flow_type_list.add(flow_value[i][flow_type_col]);
				flow_relaxed_percentage_list.add(Double.parseDouble(flow_value[i][relaxed_percentage_col]));
			}

			
			// add value to 3D flow_set_list
			flow_set_list = new ArrayList<List<List<Integer>>>();	
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
		
	public List<String> get_flow_type_list() {
		return flow_type_list;
	}
	
	public List<Double> get_flow_relaxed_percentage_list() {
		return flow_relaxed_percentage_list;
	}
	
	public List<List<List<Integer>>> get_flow_set_list () {	
		return flow_set_list;
	}	
}
