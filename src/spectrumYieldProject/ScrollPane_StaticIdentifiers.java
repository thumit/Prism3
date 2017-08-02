package spectrumYieldProject;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;



public class ScrollPane_StaticIdentifiers extends JScrollPane {
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	private List<JLabel> layers_Title_Label;

	public ScrollPane_StaticIdentifiers (Read_Database read_Database) {
	
		List<String> layers_Title = new ArrayList<>(read_Database.get_layers_Title());
		List<String> layers_Title_ToolTip = new ArrayList<>(read_Database.get_layers_Title_ToolTip());
		List<List<String>> allLayers = new ArrayList<>(read_Database.get_allLayers());
		List<List<String>> allLayers_ToolTips = new ArrayList<>(read_Database.get_allLayers_ToolTips());

		int total_layers = allLayers.size(); // Remove the last 1 layers = allLayers.size() - 1
		// int total_layers_ToolTips = allLayers_ToolTips.size() -1; //Remove the last 1 layers = allLayers.size() - 1

		// Add 3 more into static identifiers
		List<String> MethodsPeriodsAges_Title = read_Database.get_MethodsPeriodsAges_Title();
		List<List<String>> MethodsPeriodsAges = read_Database.get_MethodsPeriodsAges();

		layers_Title.addAll(MethodsPeriodsAges_Title);
		layers_Title_ToolTip.addAll(MethodsPeriodsAges_Title);
		allLayers.addAll(MethodsPeriodsAges);
		
		// Full name of silvicultural methods			// NOTE NOTE NOTE change later
		List<List<String>> allmethods_ToolTips = read_Database.get_MethodsPeriodsAges();
		for (int i = 0; i < allmethods_ToolTips.get(0).size(); i++) {	// 0 is method, 1 is period
			if (allmethods_ToolTips.get(0).get(i).equals("NG_E")) 	allmethods_ToolTips.get(0).set(i, "Natural Growth existing");
			if (allmethods_ToolTips.get(0).get(i).equals("PB_E")) 	allmethods_ToolTips.get(0).set(i, "Prescribed Burn existing");
			if (allmethods_ToolTips.get(0).get(i).equals("GS_E")) 	allmethods_ToolTips.get(0).set(i, "Group Selection existing");
			if (allmethods_ToolTips.get(0).get(i).equals("EA_E")) 	allmethods_ToolTips.get(0).set(i, "Even Age existing");
			if (allmethods_ToolTips.get(0).get(i).equals("MS_E")) 	allmethods_ToolTips.get(0).set(i, "Mixed Severity Wildfire");
			if (allmethods_ToolTips.get(0).get(i).equals("BS_E")) 	allmethods_ToolTips.get(0).set(i, "Severe Bark Beetle");
			if (allmethods_ToolTips.get(0).get(i).equals("NG_R")) 	allmethods_ToolTips.get(0).set(i, "Natural Growth regeneration");
			if (allmethods_ToolTips.get(0).get(i).equals("PB_R")) 	allmethods_ToolTips.get(0).set(i, "Prescribed Burn regeneration");
			if (allmethods_ToolTips.get(0).get(i).equals("GS_R")) 	allmethods_ToolTips.get(0).set(i, "Group Selection regeneration");
			if (allmethods_ToolTips.get(0).get(i).equals("EA_R")) 	allmethods_ToolTips.get(0).set(i, "Even Age regeneration");		
		}	
		allLayers_ToolTips.addAll(allmethods_ToolTips);

		int total_staticIdentifiers = total_layers + MethodsPeriodsAges.size();
		
		
		
		//Add all layers labels and CheckBoxes to identifiersPanel
		JPanel identifiersPanel = new JPanel();		
		identifiersPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
	    c1.weighty = 1;

    
		//Add all layers labels
	    layers_Title_Label = new ArrayList<JLabel>();
		for (int i = 0; i < total_staticIdentifiers; i++) {
			layers_Title_Label.add(new JLabel(layers_Title.get(i)));
			layers_Title_Label.get(i).setToolTipText(layers_Title_ToolTip.get(i));
			
			//add listeners to select all or deselect all
			int curent_index = i;
			layers_Title_Label.get(curent_index).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (layers_Title_Label.get(curent_index).isEnabled()) {	
						for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							if (checkboxStaticIdentifiers.get(curent_index).get(j).isVisible()) {		// to prevent select or de-select invisible periods which slow the Table Filter
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(false);
							}						
						}
						layers_Title_Label.get(curent_index).setEnabled(false);
					} else {
						for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							if (checkboxStaticIdentifiers.get(curent_index).get(j).isVisible()) {		// to prevent select or de-select invisible periods which slow the Table Filter
								checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(true);
							}							
						}
						layers_Title_Label.get(curent_index).setEnabled(true);
					}
				}
			});
	
			//Add to identifiersPanel
			c1.gridx = i;
			c1.gridy = 0;
			identifiersPanel.add(layers_Title_Label.get(i), c1);
		}
		

		
		//Add CheckBox for all layers
		checkboxStaticIdentifiers = new ArrayList<List<JCheckBox>>();
		for (int i = 0; i < total_staticIdentifiers; i++) {		//Loop all layers
			List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		//A temporary List
			checkboxStaticIdentifiers.add(temp_List);
			for (int j = 0; j < allLayers.get(i).size(); j++) {		//Loop all elements in each layer
				checkboxStaticIdentifiers.get(i).add(new JCheckBox(allLayers.get(i).get(j)));
				checkboxStaticIdentifiers.get(i).get(j).setToolTipText(allLayers_ToolTips.get(i).get(j));	
				checkboxStaticIdentifiers.get(i).get(j).setSelected(true);
				
				c1.gridx = i;
				c1.gridy = j + 1;
				identifiersPanel.add(checkboxStaticIdentifiers.get(i).get(j), c1);
				
				//Make label Enable after a checkbox is selected
				int current_i = i;
				int current_j = j;
				checkboxStaticIdentifiers.get(i).get(j).addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						if (checkboxStaticIdentifiers.get(current_i).get(current_j).isSelected()) {
							layers_Title_Label.get(current_i).setEnabled(true);
						}					
					}
				});
						
				// Set layer 5 - Cover Type & layer 6 - Size Class invisible and disable
				if (i == 4 || i == 5) {
//					checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
				}
			}
		}
		
		
	    
	    
		this.setViewportView(identifiersPanel);
		TitledBorder border1 = new TitledBorder("Static Identifiers  -  use strata attributes to filter variables");
		border1.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border1);
		this.setPreferredSize(new Dimension(550, 250));
	}
	
	
	public List<List<JCheckBox>> get_CheckboxStaticIdentifiers() {
		return checkboxStaticIdentifiers;
	}
	
	
	public List<JCheckBox> get_TitleAsCheckboxes() {
		List<JCheckBox> temp_List = new ArrayList<JCheckBox>();		//A temporary List
		for (int i = 0; i < layers_Title_Label.size(); i++) {
			temp_List.add(new JCheckBox(layers_Title_Label.get(i).getText()));
		}
		return temp_List;
	}
	
	
	public String get_static_info_from_GUI() {			
		String static_info = "";
		for (int ii = 0; ii < checkboxStaticIdentifiers.size(); ii++) {		//Loop all static identifiers
			static_info = static_info + ii + " ";
			for (int j = 0; j < checkboxStaticIdentifiers.get(ii).size(); j++) {		//Loop all elements in each layer
				String checkboxName = checkboxStaticIdentifiers.get(ii).get(j).getText();				
				//Add checkBox if it is (selected & visible) or disable
				if ((checkboxStaticIdentifiers.get(ii).get(j).isSelected() && (checkboxStaticIdentifiers.get(ii).get(j).isVisible())
						|| !checkboxStaticIdentifiers.get(ii).get(j).isEnabled()))	
					static_info = static_info + checkboxName + " ";	
			}
						
			if (!static_info.equals("")) {
				static_info = static_info.substring(0, static_info.length() - 1) + ";";		// remove the last space, and add ;
			}
		}	
		
		if (!static_info.equals("")) {
			static_info = static_info.substring(0, static_info.length() - 1);		// remove the last ;
		}
				
		return static_info;
	}
	
	
	public void reload_this_constraint_static_identifiers(String static_identifiers_info) {	
		// Note: static_identifiers_info: contains all the selected static identifiers	
		for (int i = 0; i < checkboxStaticIdentifiers.size(); i++) {			
			for (int j = 0; j < checkboxStaticIdentifiers.get(i).size(); j++) {					
				checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
			}
		}
				
		// Read the whole cell into array
		String[] info = static_identifiers_info.split(";");
		int total_static_identifiers = info.length;
		
		// Get all static Identifiers
		for (int i = 0; i < total_static_identifiers; i++) {	
			String[] identifier_elements = info[i].split("\\s+");	// Space delimited
			int current_identifier_id = Integer.valueOf(identifier_elements[0]);
						
			// Reload the selection			
			for (int j = 1; j < identifier_elements.length; j++) {		//Ignore the first element which is the identifier id
				String this_identifier_attribute = identifier_elements[j].replaceAll("\\s+","");		//Add element name, if name has spaces then remove all the spaces												
				for (JCheckBox k: checkboxStaticIdentifiers.get(current_identifier_id)) {
					if (k.getText().equalsIgnoreCase(this_identifier_attribute)) {
						k.setSelected(true);
					}
				}
			}
		}
	}
	
}
