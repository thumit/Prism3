package spectrumYieldProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReadRunInputs {
	//For readGeneralInputs
	private int GI_totalRows, GI_totalColumns;
	private String GI_value[][];

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
	//For readManagementOptions
	private int MO_totalRows, MO_totalColumns;
	private String MO_value[][];
		
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
	//For readUserConstraints
	private int UC_totalRows, UC_totalColumns;
	private String UC_value[][];

	public void readUserConstraints (File file) {
		String delimited = "\t";		// tab delimited
				
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]);
						
			//Read the first row into array. This will be Column names
			String[] columnName = a[0].split(delimited);
			UC_totalRows = a.length;
			UC_totalColumns = columnName.length;				
			UC_value = new String[UC_totalRows][UC_totalColumns];
		
			// read all values from all rows and columns
			for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
				String[] rowValue = a[i].split(delimited);		
				for (int j = 0; j < UC_totalColumns; j++) {
					UC_value[i][j] = rowValue[j];
				}
			}

		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}	
	
	public String[][] get_UC_Values () {
		return UC_value;
	}

	public int get_UC_TotalRows () {
		return UC_totalRows;
	}
	
	public int get_UC_TotalColumns () {
		return UC_totalColumns;
	}	
		
	
	public int get_total_hardConstraints () {
		int total =0;		
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("HARD")) total++;
		}	
		return total;
	}	

	public double[] get_hardConstraints_LB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("HARD") && !UC_value[i][2].equals("null")) list.add(Double.parseDouble(UC_value[i][2]));
			if (UC_value[i][1].equals("HARD") && UC_value[i][2].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_hardConstraints_UB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("HARD")  && !UC_value[i][4].equals("null")) list.add(Double.parseDouble(UC_value[i][4]));
			if (UC_value[i][1].equals("HARD")  && UC_value[i][4].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}
	
	public int get_total_softConstraints () {
		int total =0;		
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("SOFT")) total++;
		}	
		return total;
	}		
				
	
	public double[] get_softConstraints_LB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("SOFT") && !UC_value[i][2].equals("null")) list.add(Double.parseDouble(UC_value[i][2]));
			if (UC_value[i][1].equals("SOFT") && UC_value[i][2].equals("null")) list.add((double) 0);		//Change "null" value of LB to 0
		}				
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_softConstraints_UB () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("SOFT")  && !UC_value[i][4].equals("null")) list.add(Double.parseDouble(UC_value[i][4]));
			if (UC_value[i][1].equals("SOFT")  && UC_value[i][4].equals("null")) list.add(Double.MAX_VALUE);	//Change "null" value of UB to Double.Max_Value
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}	
	
	public double[] get_softConstraints_LB_Weight () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("SOFT") && !UC_value[i][3].equals("null")) list.add(Double.parseDouble(UC_value[i][3]));
			if (UC_value[i][1].equals("SOFT") && UC_value[i][3].equals("null")) list.add((double) 0);		//Change "null" value of LB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}

	public double[] get_softConstraints_UB_Weight () {	
		List<Double> list = new ArrayList<Double>();
		for (int i = 1; i < UC_totalRows; i++) {		//From 2nd row			
			if (UC_value[i][1].equals("SOFT") && !UC_value[i][5].equals("null")) list.add(Double.parseDouble(UC_value[i][5]));
			if (UC_value[i][1].equals("SOFT") && UC_value[i][5].equals("null")) list.add((double) 0);		//Change "null" value of UB_Weight to 0
		}			
		double[] array = Stream.of(list.toArray(new Double[list.size()])).mapToDouble(Double::doubleValue).toArray();
		return array;
	}		

	
	
	
	public List<List<String>> get_all_staticIdentifiers_in_row (int row) {	//Column 7 in the GUI table "Static identifiers". The whole is contained by UC_value[i][7]
		List<List<String>> all_staticIdentifiers = new ArrayList<List<String>>();
		
		//Read the whole cell into array
		String[] staticLayer_Info = UC_value[row][7].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
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
		String[] dynamicLayer_Info = UC_value[row][8].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
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
		String[] dynamicLayer_Info = UC_value[row][8].split(";");		//Note: row 0 is the title only, row 1 is constraint 1,.....
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
		String[] parameter_Info = UC_value[row][6].split("\\s+");			
		for (int i = 0; i < parameter_Info.length; i++) {	
			parameters_indexes_list.add(parameter_Info[i].replaceAll("\\s+",""));
		}				
		return parameters_indexes_list;
	}	
//-------------------------------------------------------------------------------------------------------------------------------------------------	
	//For readRequirements
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
	//For readMSFire
	private List<Double> msFireProportion_list;	
	
	public void readMSFire (File file) {
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
	//For readSRDFile
	private double[][] SRDProportion;	
	
	public void readSRDFile (File file) {
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
	//For readSRDrequirementFile
	private List<Double> SRDrequirementProportion_list;	
	
	public void readSRDrequirementFile (File file) {
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
	
}
