package spectrumYieldProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Read_Indentifiers {
	private List<String> layers_Title;
	private List<String> layers_Title_ToolTip;
	
	private List<List<String>> allLayers;
	private List<List<String>> allLayers_ToolTips;
	
	public Read_Indentifiers() {
		
//		File file = FilesChooser_Units.chosenManagementunit();	
//		File file = new File("C:\\Users\\Dung Nguyen\\Desktop\\LayersDefinition(Matchyieldtable).csv");
		File file = new File("C:\\Users\\Dung Nguyen\\Desktop\\LayersDefinition.csv");
		
		String delimited;
			delimited = ","; // comma delimited
		// delimited = "\\s+"; // space delimited
		// delimited = "\t"; // tab delimited
					
			
		if (delimited != null) {
			try {
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
				int totalRows = a.length;
				int totalCols = 2;
				String value[][] = new String[totalRows][totalCols];

				// read all values from all rows and columns
				for (int i = 0; i < totalRows; i++) { // Read from 1st row
					String[] rowValue = a[i].split(delimited);
					for (int j = 0; j < totalCols && j < rowValue.length; j++) {
//						value[i][j] = rowValue[j].replaceAll("\\s+", "");		//Remove all the space in the String   
						value[i][j] = rowValue[j];		//to make toolTp text separated with space, may need the above line if there is spaces in layer and elements name in the file LayersDefinition.csv
					}
				}
	

				layers_Title = new ArrayList<String>();
				layers_Title_ToolTip = new ArrayList<String>();
				
				allLayers = new ArrayList<List<String>>();
				allLayers_ToolTips = new ArrayList<List<String>>();
				
				
				//Loop through all rows and add all layers information
				int i = 0;
		        while (i < totalRows) {
		        	//Add Layer title and toolTip    	
		        	layers_Title.add(value[i][0]);
		        	layers_Title_ToolTip.add(value[i][1]);

		        	//Get total elements in this layer
		        	i++;
		        	int total_elements = Integer.parseInt(value[i][0]);
		        	

		        	//Create a new Layer Name and toolTips:  2 temporary Lists
		        	List<String> newLayer = new ArrayList<String>();
		        	List<String> newLayer_ToolTip = new ArrayList<String>();

		        	//Loop through all elements of this layer name and toolTip
		        	for (int j = 0; j < total_elements; j++) {
		        		i++;
		        		newLayer.add(value[i][0]);
		        		newLayer_ToolTip.add(value[i][1]);
					}
		        	
		        	//Add temporary Lists to the allLayers & allLayers_ToolTips
		        	allLayers.add(newLayer);
		        	allLayers_ToolTips.add(newLayer_ToolTip);
		        	
		        	//Move to the next Layer
		        	i++;
		        }
				

			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
		
		
		
	}
	
	
	public List<String> get_layers_Title() {       
		return layers_Title;
	}

	
	public List<String> get_layers_Title_ToolTip() {		
		return layers_Title_ToolTip;
	}
	
	
	public List<List<String>> get_allLayers() {			
		return allLayers;
	}
	
	public List<List<String>> get_allLayers_ToolTips() {
		return allLayers_ToolTips;
	}	


	
	
	
	public List<String> get_MethodsPeriodsAges_Title() {
		//Layers title
		List<String> MethodsPeriodsAges_Title = new ArrayList<String>();
		MethodsPeriodsAges_Title.add("Silvicultural Methods");
		MethodsPeriodsAges_Title.add("Time Periods");
		MethodsPeriodsAges_Title.add("Age Classes");

		return MethodsPeriodsAges_Title;
	}

	
	public List<List<String>> get_MethodsPeriodsAges() {
		//Layers element name
		List<String> layer1 = new ArrayList<String>();			//Silvicultural methods
		layer1.add("Even Age");
		layer1.add("Group Selection");
		layer1.add("Prescribed Burn");
		layer1.add("Natural Growth");

		
		List<String> layer2 = new ArrayList<String>();		//Time Periods
		for (int i = 1; i <= 50; i++) {
			layer2.add(Integer.toString(i));
		}
		
		List<String> layer3 = new ArrayList<String>();		//Age Classes
		for (int i = 1; i <= 50; i++) {
			layer3.add(Integer.toString(i));
		}

			
		List<List<String>> MethodsPeriodsAges = new ArrayList<List<String>>();
		MethodsPeriodsAges.add(layer1);
		MethodsPeriodsAges.add(layer2);
//		MethodsPeriodsAges.add(layer3);
			
		return MethodsPeriodsAges;
	}


	
	public String get_ParameterToolTip(String YTcolumnNames) {
		String toolTip = null;
		
		
		if (YTcolumnNames.equals("Strata")) toolTip = "Stand Type/Rx/Timing Choice Label";
		if (YTcolumnNames.equals("Proj_Year")) toolTip = "Projection Cycle Year";
		if (YTcolumnNames.equals("St_Age/10")) toolTip = "Stand Age/10 years";
		if (YTcolumnNames.equals("Stand_Age")) toolTip = "Stand Age";
		if (YTcolumnNames.equals("StDnIndex")) toolTip = "Stand Density Index";
		if (YTcolumnNames.equals("CulmMAI-A")) toolTip = "Culmination Mean Annual Increment - Merchantable Cubic Feet, All Trees";
		if (YTcolumnNames.equals("Qd_Mn_Dia")) toolTip = "Quadratic Mean Diameter";
		if (YTcolumnNames.equals("Plt_Acres")) toolTip = "Plot Acres (Count)";
		if (YTcolumnNames.equals("Trt_Acres")) toolTip = "Treatment Acres (Count)";
		if (YTcolumnNames.equals("Strata")) toolTip = "Stand Type/Rx/Timing Choice Label";
		if (YTcolumnNames.equals("Strata")) toolTip = "Stand Type/Rx/Timing Choice Label";
		if (YTcolumnNames.equals("Strata")) toolTip = "Stand Type/Rx/Timing Choice Label";
		if (YTcolumnNames.equals("Strata")) toolTip = "Stand Type/Rx/Timing Choice Label";
		if (YTcolumnNames.equals("Strata")) toolTip = "Stand Type/Rx/Timing Choice Label";
		
		
		if (YTcolumnNames.equals("LTr.AllSx")) toolTip = "Live/Trees per Acre/All Species/All Size Classes";	
		if (YTcolumnNames.equals("LAD.AllSx")) toolTip = "Live/Average DBH/All Species/All Size Classes";
		if (YTcolumnNames.equals("LAH.AllSx")) toolTip = "Live/Average Height/All Species/All Size Classes";		
		if (YTcolumnNames.equals("LBA.AllSx")) toolTip = "Live/Basal Area per Acre/All Species/All Size Classes";		
		if (YTcolumnNames.equals("LCA.AllSx")) toolTip = "Live/Cubic Feet per Acre/All Species/All Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("LBd.AllSx")) toolTip = "Live/Board Feet per Acre/All Species/All Size Classes";		
		if (YTcolumnNames.equals("HTr.AllSx")) toolTip = "Harvest/Trees per Acre/All Species/All Size Classes";
		if (YTcolumnNames.equals("HAD.AllSx")) toolTip = "Harvest/Average DBH/All Species/All Size Classes";		
		if (YTcolumnNames.equals("HAH.AllSx")) toolTip = "Harvest/Average Height/All Species/All Size Classes";		
		if (YTcolumnNames.equals("HBA.AllSx")) toolTip = "Harvest/Basal Area per Acre/All Species/All Size Classes";		
		if (YTcolumnNames.equals("HCA.AllSx")) toolTip = "Harvest/Cubic Feet per Acre/All Species/All Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("HBd.AllSx")) toolTip = "Harvest/Board Feet per Acre/All Species/All Size Classes";		
		
		
		if (YTcolumnNames.equals("LTr.Gp1Xx")) toolTip = "Live/Trees per Acre/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("LAD.Gp1Xx")) toolTip = "Live/Average DBH/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("LAH.Gp1Xx")) toolTip = "Live/Average Height/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("LBA.Gp1Xx")) toolTip = "Live/Basal Area per Acre/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("LCA.Gp1Xx")) toolTip = "Live/Cubic Feet per Acre/All Species/Mature Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("LBd.Gp1Xx")) toolTip = "Live/Board Feet per Acre/All Species/Mature Size Classes";
		if (YTcolumnNames.equals("HTr.Gp1Xx")) toolTip = "Harvest/Trees per Acre/All Species/Mature Size Classes";	
		if (YTcolumnNames.equals("HAD.Gp1Xx")) toolTip = "Harvest/Average DBH/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("HAH.Gp1Xx")) toolTip = "Harvest/Average Height/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("HBA.Gp1Xx")) toolTip = "Harvest/Basal Area per Acre/All Species/Mature Size Classes";		
		if (YTcolumnNames.equals("HCA.Gp1Xx")) toolTip = "Harvest/Cubic Feet per Acre/All Species/Mature Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("HBd.Gp1Xx")) toolTip = "Harvest/Board Feet per Acre/All Species/Mature Size Classes";		
				
		
		if (YTcolumnNames.equals("LTr.Gp2Mm")) toolTip = "Live/Trees per Acre/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("LAD.Gp2Mm")) toolTip = "Live/Average DBH/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("LAH.Gp2Mm")) toolTip = "Live/Average Height/All Species/Mid-Age Size Classes";
		if (YTcolumnNames.equals("LBA.Gp2Mm")) toolTip = "Live/Basal Area per Acre/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("LCA.Gp2Mm")) toolTip = "Live/Cubic Feet per Acre/All Species/Mid-Age Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("LBd.Gp2Mm")) toolTip = "Live/Board Feet per Acre/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("HTr.Gp2Mm")) toolTip = "Harvest/Trees per Acre/All Species/Mid-Age Size Classes";
		if (YTcolumnNames.equals("HAD.Gp2Mm")) toolTip = "Harvest/Average DBH/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("HAH.Gp2Mm")) toolTip = "Harvest/Average Height/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("HBA.Gp2Mm")) toolTip = "Harvest/Basal Area per Acre/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("HCA.Gp2Mm")) toolTip = "Harvest/Cubic Feet per Acre/All Species/Mid-Age Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("HBd.Gp2Mm")) toolTip = "Harvest/Board Feet per Acre/All Species/Mid-Age Size Classes";		
		

		if (YTcolumnNames.equals("LTr.Gp3Yy")) toolTip = "Live/Trees per Acre/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("LAD.Gp3Yy")) toolTip = "Live/Average DBH/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("LAH.Gp3Yy")) toolTip = "Live/Average Height/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("LBA.Gp3Yy")) toolTip = "Live/Basal Area per Acre/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("LCA.Gp3Yy")) toolTip = "Live/Cubic Feet per Acre/All Species/Young Size Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("LBd.Gp3Yy")) toolTip = "Live/Board Feet per Acre/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("HTr.Gp3Yy")) toolTip = "Harvest/Trees per Acre/All Species/Young Size Size Classes";
		if (YTcolumnNames.equals("HAD.Gp3Yy")) toolTip = "Harvest/Average DBH/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("HAH.Gp3Yy")) toolTip = "Harvest/Average Height/All Species/Young Size Size Classes";
		if (YTcolumnNames.equals("HBA.Gp3Yy")) toolTip = "Harvest/Basal Area per Acre/All Species/Young Size Size Classes";		
		if (YTcolumnNames.equals("HCA.Gp3Yy")) toolTip = "Harvest/Cubic Feet per Acre/All Species/Young Size Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("HBd.Gp3Yy")) toolTip = "Harvest/Board Feet per Acre/All Species/Young Size Size Classes";		
		
		if (YTcolumnNames.equals("LTr.Gp4Ss")) toolTip = "Live/Trees per Acre/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("LAD.Gp4Ss")) toolTip = "Live/Average DBH/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("LAH.Gp4Ss")) toolTip = "Live/Average Height/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("LBA.Gp4Ss")) toolTip = "Live/Basal Area per Acre/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("LCA.Gp4Ss")) toolTip = "Live/Cubic Feet per Acre/All Species/Sapling Size Classes, All Trees - Cubic Top";
		if (YTcolumnNames.equals("LBd.Gp4Ss")) toolTip = "Live/Board Feet per Acre/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("HTr.Gp4Ss")) toolTip = "Harvest/Trees per Acre/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("HAD.Gp4Ss")) toolTip = "Harvest/Average DBH/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("HAH.Gp4Ss")) toolTip = "Harvest/Average Height/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("HBA.Gp4Ss")) toolTip = "Harvest/Basal Area per Acre/All Species/Sapling Size Classes";
		if (YTcolumnNames.equals("HCA.Gp4Ss")) toolTip = "Harvest/Cubic Feet per Acre/All Species/Sapling Size Classes, All Trees - Cubic Top";
		if (YTcolumnNames.equals("HBd.Gp4Ss")) toolTip = "Harvest/Board Feet per Acre/All Species/Sapling Size Classes";
	
		
		if (YTcolumnNames.equals("PCA.AllSx")) toolTip = "Proportion Cut/Cubic Feet per Acre/All Species/All Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("PBd.AllSx")) toolTip = "Proportion Cut/Board Feet per Acre/All Species/All Size Classes";
		if (YTcolumnNames.equals("PCA.Gp1Xx")) toolTip = "Proportion Cut/Cubic Feet per Acre/All Species/Mature Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("PBd.Gp1Xx")) toolTip = "Proportion Cut/Board Feet per Acre/All Species/Mature Size Classes";			
		if (YTcolumnNames.equals("PCA.Gp2Mm")) toolTip = "Proportion Cut/Cubic Feet per Acre/All Species/Mid-Age Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("PBd.Gp2Mm")) toolTip = "Proportion Cut/Board Feet per Acre/All Species/Mid-Age Size Classes";		
		if (YTcolumnNames.equals("PCA.Gp3Yy")) toolTip = "Proportion Cut/Cubic Feet per Acre/All Species/Young Size Size Classes, All Trees - Cubic Top";		
		if (YTcolumnNames.equals("PBd.Gp3Yy")) toolTip = "Proportion Cut/Board Feet per Acre/All Species/Young Size Size Classes";			
		if (YTcolumnNames.equals("PCA.Gp4Ss")) toolTip = "Proportion Cut/Cubic Feet per Acre/All Species/Sapling Size Classes, All Trees - Cubic Top";
		if (YTcolumnNames.equals("PBd.Gp4Ss")) toolTip = "Proportion Cut/Board Feet per Acre/All Species/Sapling Size Classes";
		
		
		//Structure Variables: Compute Post Processor
		if (YTcolumnNames.equals("_STNDAGE0")) toolTip = "Stand Age";
		if (YTcolumnNames.equals("_AGEINT0")) toolTip = "Stand Age Interval - 10 years";
		if (YTcolumnNames.equals("_CC00P")) toolTip = "Canopy Cover O' plus";
		if (YTcolumnNames.equals("_CC10P")) toolTip = "Canopy Cover 1O' plus";
		if (YTcolumnNames.equals("_CC15P")) toolTip = "Canopy Cover 15' plus";
		if (YTcolumnNames.equals("_CC20P")) toolTip = "Canopy Cover 2O' plus";		
		if (YTcolumnNames.equals("_LYNX")) toolTip = "Lynx Habitat";

		
		//Fire Variables: Compute Post Processor
		if (YTcolumnNames.equals("_CRBD")) toolTip = "Crown Bulk Density";
		if (YTcolumnNames.equals("_TRIDX")) toolTip = "Torching Index - Severe Fire";
		if (YTcolumnNames.equals("_CRIDX")) toolTip = "Crowning Index - Severe Fire";
		if (YTcolumnNames.equals("_FIRE")) toolTip = "Fire Hazard Rating - Torching x Crowning Index Matrix";
		if (YTcolumnNames.equals("_SNAG10T")) toolTip = "Snags 10'-20'";
		if (YTcolumnNames.equals("_SNAG20P")) toolTip = "Snags 20' plus";
	
		
		//Pest Variables: Compute Post Processor		
		if (YTcolumnNames.equals("_ESBTL")) toolTip = "Spruce Beetle";
		if (YTcolumnNames.equals("_DFBTL")) toolTip = "Douglas-fir Beetle";
		if (YTcolumnNames.equals("_PPBTL")) toolTip = "Ponderosa Pine (MPB/WPB)";
		if (YTcolumnNames.equals("_WPBTL")) toolTip = "Western White Pine (MPB)";
		if (YTcolumnNames.equals("_LPBTL")) toolTip = "Lodgepole Pine (MPB)";
		if (YTcolumnNames.equals("_HZBTL")) toolTip = "Composite Beetle Hazard";
		if (YTcolumnNames.equals("_BDWTSM")) toolTip = "W. Spruce Budworm/DF Tussock Moth";
		
		
		//R1 Vegetation Variables: R1 Stand Classifier Post Processor
		if (YTcolumnNames.equals("DOM_GRP")) toolTip = "Dominance Group (a.k.a. Cover Type)";
		if (YTcolumnNames.equals("SIZ_NTG")) toolTip = "Size Class (National Technical Guide standards)";
		if (YTcolumnNames.equals("_STNDAGE1")) toolTip = "Stand Age";
		if (YTcolumnNames.equals("_AGEINT1")) toolTip = "Stand Age Interval - 10 years";
		

		
		return toolTip;
	}	
	
		
}
