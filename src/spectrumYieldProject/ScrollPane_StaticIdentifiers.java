package spectrumYieldProject;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;



public class ScrollPane_StaticIdentifiers extends JScrollPane {
	private List<List<JCheckBox>> checkboxStaticIdentifiers;
	List<JLabel> layers_Title_Label;

	public ScrollPane_StaticIdentifiers (File file_StrataDefinition) {
	
		
		Read_Indentifiers read_Identifiers = new Read_Indentifiers(file_StrataDefinition);		// New instance so we can add some more layers, it does not change read_Identifiers in the 'Panel_EditRun_Details" 

		List<String> layers_Title = read_Identifiers.get_layers_Title();
		List<String> layers_Title_ToolTip = read_Identifiers.get_layers_Title_ToolTip();
		List<List<String>> allLayers = read_Identifiers.get_allLayers();
		List<List<String>> allLayers_ToolTips = read_Identifiers.get_allLayers_ToolTips();

		int total_layers = allLayers.size(); // Remove the last 1 layers = allLayers.size() - 1
		// int total_layers_ToolTips = allLayers_ToolTips.size() -1; //Remove the last 1 layers = allLayers.size() - 1

		// Add 3 more into static identifiers
		List<String> MethodsPeriodsAges_Title = read_Identifiers.get_MethodsPeriodsAges_Title();
		List<List<String>> MethodsPeriodsAges = read_Identifiers.get_MethodsPeriodsAges();

		layers_Title.addAll(MethodsPeriodsAges_Title);
		layers_Title_ToolTip.addAll(MethodsPeriodsAges_Title);
		allLayers.addAll(MethodsPeriodsAges);
		allLayers_ToolTips.addAll(MethodsPeriodsAges);

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
							checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(false);
						}
						layers_Title_Label.get(curent_index).setEnabled(false);
					} else {
						for (int j = 0; j < allLayers.get(curent_index).size(); j++) {		//Loop all elements in each layer
							checkboxStaticIdentifiers.get(curent_index).get(j).setSelected(true);
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
						
//				//Set layer 5 - Cover Type invisible
//				if (i==4) checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
				//Set layer 6 - Size Class invisible
				if (i==5) checkboxStaticIdentifiers.get(i).get(j).setEnabled(false);
//				//Deselect all time period check boxes (7)
//				if (i==7) checkboxStaticIdentifiers.get(i).get(j).setSelected(false);
			}
		}
		
		
	    
	    
		this.setViewportView(identifiersPanel);
		TitledBorder border1 = new TitledBorder("Static identifiers for VARIABLES");
		border1.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border1);
		this.setPreferredSize(new Dimension(500, 250));
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
				if (checkboxName.equals("Even Age")) {
					checkboxName = "EA";
				} else if (checkboxName.equals("Group Selection")) {
					checkboxName = "GS";
				} else if (checkboxName.equals("Prescribed Burn")) {
					checkboxName = "PB";
				} else if (checkboxName.equals("Natural Growth")) {
					checkboxName = "NG";
				} else if (checkboxName.equals("Mixed Severity Wildfire")) {
					checkboxName = "MS";
				}
				
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
}
