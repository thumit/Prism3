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
	
	
	
}
