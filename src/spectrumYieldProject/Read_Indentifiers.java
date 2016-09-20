package spectrumYieldProject;

import java.util.ArrayList;
import java.util.List;

public class Read_Indentifiers {

	public List<String> get_layers_Title() {
		//Layers title
		List<String> layers_Title = new ArrayList<String>();
		layers_Title.add("Layer 1");
		layers_Title.add("Layer 2");
		layers_Title.add("Layer 3");
		layers_Title.add("Layer 4");
		layers_Title.add("Layer 5");
		layers_Title.add("Layer 6");
	
		return layers_Title;
	}

	
	public List<String> get_layers_Title_ToolTip() {
		//Layers title ToolTip
		List<String> layers_Title_ToolTip = new ArrayList<String>();
		layers_Title_ToolTip.add("Vegetation Desired Future Condition Areas");
		layers_Title_ToolTip.add("Roadless Status");
		layers_Title_ToolTip.add("Timber Suitability");
		layers_Title_ToolTip.add("Resource Condition Zones");
		layers_Title_ToolTip.add("Vegetation Cover Types");
		layers_Title_ToolTip.add("Size Class");
				
		return layers_Title_ToolTip;
	}
	
	
	public List<List<String>> get_allLayers() {
		//Layers title
		List<String> layers_Title = new ArrayList<String>();
		layers_Title.add("Layer 1");
		layers_Title.add("Layer 2");
		layers_Title.add("Layer 3");
		layers_Title.add("Layer 4");
		layers_Title.add("Layer 5");
		layers_Title.add("Layer 6");
	
		//Layers title ToolTip
		List<String> layers_Title_ToolTip = new ArrayList<String>();
		layers_Title_ToolTip.add("Vegetation Desired Future Condition Areas");
		layers_Title_ToolTip.add("Roadless Status");
		layers_Title_ToolTip.add("Timber Suitability");
		layers_Title_ToolTip.add("Resource Condition Zones");
		layers_Title_ToolTip.add("Vegetation Cover Types");
		layers_Title_ToolTip.add("Size Class");
		
			
		//Layers element name
		List<String> layer1 = new ArrayList<String>();
		layer1.add("B");
		layer1.add("U");
		layer1.add("S");
		layer1.add("K");
		layer1.add("R");
		layer1.add("C");
		
		List<String> layer2 = new ArrayList<String>();
		layer2.add("R");
		layer2.add("N");
	
		List<String> layer3 = new ArrayList<String>();
		layer3.add("N");
		layer3.add("O");
		layer3.add("P");
		layer3.add("S");

		List<String> layer4 = new ArrayList<String>();
		layer4.add("L");
		layer4.add("H");
		layer4.add("C");
		layer4.add("R");
		
		List<String> layer5 = new ArrayList<String>();
		layer5.add("P");
		layer5.add("D");
		layer5.add("W");
		layer5.add("C");
		layer5.add("I");
		layer5.add("A");
		layer5.add("L");
		layer5.add("N");
	
		List<String> layer6 = new ArrayList<String>();
		layer6.add("S");
		layer6.add("P");
		layer6.add("M");
		layer6.add("L");
		layer6.add("N");
			
		List<List<String>> allLayers = new ArrayList<List<String>>();
		allLayers.add(layer1);
		allLayers.add(layer2);
		allLayers.add(layer3);
		allLayers.add(layer4);
		allLayers.add(layer5);
		allLayers.add(layer6);
			
		return allLayers;
	}
	
	public List<List<String>> get_allLayers_ToolTips() {
		//Layers element ToolTip
		List<String> layer1_ToolTips = new ArrayList<String>();
		layer1_ToolTips.add("Bitterroot Mtns. (M333D) Breaklands");
		layer1_ToolTips.add("Bitterroot Mtns. (M333D) Uplands");
		layer1_ToolTips.add("Bitterroot Mtns. (M333D) Subalpine");
		layer1_ToolTips.add("Idaho Batholith (M332A) Breaklands");
		layer1_ToolTips.add("Idaho Batholith (M332A) Uplands");
		layer1_ToolTips.add("Idaho Batholith (M332A) Subalpine");
		
		List<String> layer2_ToolTips = new ArrayList<String>();
		layer2_ToolTips.add("Roadless and undeveloped");
		layer2_ToolTips.add("Roaded and developed");
		
		List<String> layer3_ToolTips = new ArrayList<String>();
		layer3_ToolTips.add("Not Available or Not Suited; No Timber Harvest Allowed");
		layer3_ToolTips.add("Generally Suitable for Timber Harvest for other resource objectives, no scheduled output");
		layer3_ToolTips.add("Generally Suitable for Timber Harvest for other resource objectives, scheduled output");
		layer3_ToolTips.add("Suited for Timber Production");
		
		List<String> layer4_ToolTips = new ArrayList<String>();
		layer4_ToolTips.add("Lynx habitat – conserve watershed");
		layer4_ToolTips.add("Lynx habitat – restore watershed");
		layer4_ToolTips.add("Non-Lynx habitat – conserve watershed");
		layer4_ToolTips.add("Non-Lynx habitat – restore watershed");			
		
		List<String> layer5_ToolTips = new ArrayList<String>();
		layer5_ToolTips.add("Ponderosa Pine");
		layer5_ToolTips.add("Dry Douglas-fir/Grand Fir");
		layer5_ToolTips.add("Mesic Douglas-fir mix");
		layer5_ToolTips.add("Grand Fir/Western Red Cedar");
		layer5_ToolTips.add("Cold Douglas-fir mix");
		layer5_ToolTips.add("Subalpine Fir mix");
		layer5_ToolTips.add("Lodgepole Pine");	
		layer5_ToolTips.add("No Species");
		
		List<String> layer6_ToolTips = new ArrayList<String>();
		layer6_ToolTips.add("Seedling and Sapling (0” – 5");
		layer6_ToolTips.add("Small (5” – 10”)");
		layer6_ToolTips.add("Medium (10” – 15”)");
		layer6_ToolTips.add("Large (15”+)");
		layer6_ToolTips.add("None");
		
		
		List<List<String>> allLayers_ToolTips = new ArrayList<List<String>>();
		allLayers_ToolTips.add(layer1_ToolTips);
		allLayers_ToolTips.add(layer2_ToolTips);
		allLayers_ToolTips.add(layer3_ToolTips);
		allLayers_ToolTips.add(layer4_ToolTips);
		allLayers_ToolTips.add(layer5_ToolTips);
		allLayers_ToolTips.add(layer6_ToolTips);
		
		return allLayers_ToolTips;
	}	

	
//	public List<String> get_Groups() {
//		//Group Title:	Each (element) represents a group of columns in the yield table
//		List<String> group = new ArrayList<String>();
//
////	group.add("Stand Age/10 years");
//		group.add("1. Live/Trees/All Species");
//		group.add("2. Live/Average DBH/All Species");
//		group.add("3. Live/Average Height/All Species");
//		group.add("4. Live/Basal Area/All Species");
//		group.add("5. Live/Cubic Feet/All Species");
//		group.add("6. Live/Board Feet/All Species");
//		group.add("7. Harvest/Trees/All Species");
//		group.add("8. Harvest/Average DBH/All Species");
//		group.add("9. Harvest/Average Height/All Species");
//		group.add("10. Harvest/Basal Area/All Species");
//		group.add("11. Harvest/Cubic Feet/All Species");
//		group.add("12. Harvest/Board Feet/All Species");
//		group.add("13. Canopy Cover/Acres");
//		group.add("14. Lynx/Acres");
//		group.add("15. Fire Hazard Rating/Acres");
//		group.add("16. Snags/Acres");
//		
//		
//		return group;
//	}	
	
//	public List<List<String>> get_GroupParameters() {
//
//		//Add Parameters to each group
////		List<String> group0 = new ArrayList<String>();
////		for (int i = 1; i <= 30; i++) {
////			group0.add(String.valueOf(i));
////		}
//		
//		List<String> group1 = new ArrayList<String>();
//		group1.add("LTr.AllSx");
//		group1.add("LTr.Gp1Xx");
//		group1.add("LTr.Gp2Mm");
//		group1.add("LTr.Gp3Yy");
//		group1.add("LTr.Gp4Ss");
//			
//		List<String> group2 = new ArrayList<String>();
//		group2.add("LAD.AllSx");
//		group2.add("LAD.Gp1Xx");
//		group2.add("LAD.Gp2Mm");
//		group2.add("LAD.Gp3Yy");
//		group2.add("LAD.Gp4Ss");		
//		
//		List<String> group3 = new ArrayList<String>();
//		group3.add("LAH.AllSx");
//		group3.add("LAH.Gp1Xx");
//		group3.add("LAH.Gp2Mm");
//		group3.add("LAH.Gp3Yy");
//		group3.add("LAH.Gp4Ss");	
//	
//		List<String> group4 = new ArrayList<String>();
//		group4.add("LBA.AllSx");
//		group4.add("LBA.Gp1Xx");
//		group4.add("LBA.Gp2Mm");
//		group4.add("LBA.Gp3Yy");
//		group4.add("LBA.Gp4Ss");	
//		
//		List<String> group5 = new ArrayList<String>();
//		group5.add("LCA.AllSx");
//		group5.add("LCA.Gp1Xx");
//		group5.add("LCA.Gp2Mm");
//		group5.add("LCA.Gp3Yy");
//		group5.add("LCA.Gp4Ss");
//		
//		List<String> group6 = new ArrayList<String>();
//		group6.add("LBd.AllSx");
//		group6.add("LBd.Gp1Xx");
//		group6.add("LBd.Gp2Mm");
//		group6.add("LBd.Gp3Yy");
//		group6.add("LBd.Gp4Ss");
//		
//		List<String> group7 = new ArrayList<String>();
//		group7.add("HTr.AllSx");
//		group7.add("HTr.Gp1Xx");
//		group7.add("HTr.Gp2Mm");
//		group7.add("HTr.Gp3Yy");
//		group7.add("HTr.Gp4Ss");
//		
//		List<String> group8 = new ArrayList<String>();
//		group8.add("HAD.AllSx");
//		group8.add("HAD.Gp1Xx");
//		group8.add("HAD.Gp2Mm");
//		group8.add("HAD.Gp3Yy");
//		group8.add("HAD.Gp4Ss");
//		
//		List<String> group9 = new ArrayList<String>();
//		group9.add("HAH.AllSx");
//		group9.add("HAH.Gp1Xx");
//		group9.add("HAH.Gp2Mm");
//		group9.add("HAH.Gp3Yy");
//		group9.add("HAH.Gp4Ss");	
//	
//		List<String> group10 = new ArrayList<String>();
//		group10.add("HBA.AllSx");
//		group10.add("HBA.Gp1Xx");
//		group10.add("HBA.Gp2Mm");
//		group10.add("HBA.Gp3Yy");
//		group10.add("HBA.Gp4Ss");	
//		
//		List<String> group11 = new ArrayList<String>();
//		group11.add("HCA.AllSx");
//		group11.add("HCA.Gp1Xx");
//		group11.add("HCA.Gp2Mm");
//		group11.add("HCA.Gp3Yy");
//		group11.add("HCA.Gp4Ss");
//		
//		List<String> group12 = new ArrayList<String>();
//		group12.add("HBd.AllSx");
//		group12.add("HBd.Gp1Xx");
//		group12.add("HBd.Gp2Mm");
//		group12.add("HBd.Gp3Yy");
//		group12.add("HBd.Gp4Ss");
//		
//		List<String> group13 = new ArrayList<String>();
//		group13.add("_CC00P");
//		group13.add("_CC10P");
//		group13.add("_CC15P");
//		group13.add("_CC20P");
//	
//		List<String> group14 = new ArrayList<String>();
//		group14.add("_LYNX");		
//
//		List<String> group15 = new ArrayList<String>();
//		group15.add("_FIRE");
//	
//		List<String> group16 = new ArrayList<String>();
//		group16.add("_SNAG10T");
//		group16.add("_SNAG20P");
//	
//			
//	
//		List<List<String>> allGroups = new ArrayList<List<String>>();
////		allGroups.add(group0);
//		allGroups.add(group1);
//		allGroups.add(group2);
//		allGroups.add(group3);
//		allGroups.add(group4);
//		allGroups.add(group5);
//		allGroups.add(group6);
//		allGroups.add(group7);
//		allGroups.add(group8);
//		allGroups.add(group9);
//		allGroups.add(group10);
//		allGroups.add(group11);
//		allGroups.add(group12);
//		allGroups.add(group13);
//		allGroups.add(group14);
//		allGroups.add(group15);
//		allGroups.add(group16);
//	
//		return allGroups;
//	}	

	
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
