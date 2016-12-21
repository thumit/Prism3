package spectrumYieldProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import spectrumConvenienceClasses.FilesHandle;

public class Read_Indentifiers {
	private List<String> layers_Title;
	private List<String> layers_Title_ToolTip;
	
	private List<List<String>> allLayers;
	private List<List<String>> allLayers_ToolTips;
	
	public Read_Indentifiers(File file_StrataDefinition) {
		
		String delimited = ","; // 		","		comma delimited			"\\s+"		space delimited		"\t"	tab delimited
	
		if (delimited != null) {
			try {
				// All lines to be in array
				List<String> list;
				list = Files.readAllLines(Paths.get(file_StrataDefinition.getAbsolutePath()), StandardCharsets.UTF_8);
				String[] a = list.toArray(new String[list.size()]);
				int totalRows = a.length;
				int totalCols = 2;
				String[][] value = new String[totalRows][totalCols];

				// read all values from all rows and columns
				for (int i = 0; i < totalRows; i++) { // Read from 1st row
					String[] rowValue = a[i].split(delimited);
					for (int j = 0; j < totalCols && j < rowValue.length; j++) {
//						value[i][j] = rowValue[j].replaceAll("\\s+", "");		//Remove all the space in the String   
						value[i][j] = rowValue[j];		//to make toolTp text separated with space, may need the above line if there is spaces in layer and elements name in the file StrataDefinition.csv
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


	
	public String get_ParameterToolTip(String yt_columnName) {
		String toolTip = null;

		
		//Read library from the system
		File file_SpectrumLiteLibrary = null;
		
		if (file_SpectrumLiteLibrary == null) {		//This is to make it read the file only once, after that no need to repeat reading this file any more
			try {
				file_SpectrumLiteLibrary = new File(FilesHandle.get_temporaryFolder().getAbsolutePath() + "/" + "SpectrumLiteLibrary.csv");
				file_SpectrumLiteLibrary.deleteOnExit();

				InputStream initialStream = getClass().getResourceAsStream("/SpectrumLiteLibrary.csv"); //Default definition
				byte[] buffer = new byte[initialStream.available()];
				initialStream.read(buffer);

				OutputStream outStream = new FileOutputStream(file_SpectrumLiteLibrary);
				outStream.write(buffer);

				initialStream.close();
				outStream.close();
			} catch (FileNotFoundException e1) {
				System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			} catch (IOException e2) {
				System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
			} 
		}
		
		
		String delimited = ","; // 		","		comma delimited			"\\s+"		space delimited		"\t"	tab delimited	
		try {
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file_SpectrumLiteLibrary.getAbsolutePath()), StandardCharsets.UTF_8);
			String[] a = list.toArray(new String[list.size()]);
			int totalRows = a.length;
			int totalCols = 2;
			String[][] value = new String[totalRows][totalCols];

			// read all values from all rows and columns
			for (int i = 0; i < totalRows; i++) { // Read from 1st row
				String[] rowValue = a[i].split(delimited);
				for (int j = 0; j < totalCols && j < rowValue.length; j++) {
//					value[i][j] = rowValue[j].replaceAll("\\s+", "");		//Remove all the space in the String   
					value[i][j] = rowValue[j];		//to make toolTp text separated with space, may need the above line if there is spaces in layer and elements name in the file StrataDefinition.csv
				
				}
				if (yt_columnName.equals(value[i][0])) toolTip = value[i][1];
			}	
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
			
		return toolTip;
	}	
	
		
}
