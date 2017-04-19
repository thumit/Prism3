package spectrumYieldProject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import spectrumConvenienceClasses.IconHandle;
import spectrumROOT.Spectrum_Main;

public class ScrollPane_Parameters extends JScrollPane {	
	private JCheckBox checkboxNoParameter, checkboxCostParameter;
	private List<JCheckBox> checkboxParameter;
	
	public ScrollPane_Parameters(String[] yieldTable_ColumnNames) {				
		JPanel parametersPanel = new JPanel();	
		parametersPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.weightx = 1;
	    c2.weighty = 1;
	    
		setViewportView(parametersPanel);
	    		
		if (yieldTable_ColumnNames != null && checkboxParameter == null) {				
			checkboxParameter = new ArrayList<JCheckBox>();
			
			Read_Indentifiers read_Identifiers = new Read_Indentifiers(null);
			for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
				String YTcolumnName = yieldTable_ColumnNames[i];

				checkboxParameter.add(new JCheckBox(YTcolumnName));		//add checkbox
				String tip = read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")";
				checkboxParameter.get(i).setToolTipText(tip);		//add toolTip
				if (!tip.contains("per Acre")) {	// Disable Parameter check box if unit is not per Acre
					checkboxParameter.get(i).setEnabled(false);
				}
				
				// add checkboxParameter to the Panel
			    c2.gridx = 0;
			    c2.gridy = 2 + i;
				c2.weightx = 1;
			    c2.weighty = 1;
				parametersPanel.add(checkboxParameter.get(i), c2);
			}
			
			
			//Add checkboxNoParameter for the option of not using any Column, use 1 instead as multiplier
			checkboxNoParameter = new JCheckBox();			
			checkboxNoParameter.setText("NoParameter");	
			checkboxNoParameter.setRolloverIcon(UIManager.getIcon("CheckBox.icon"));
			checkboxNoParameter.setSelectedIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_check.png"));
			checkboxNoParameter.setToolTipText("1 is used as multiplier (parameter), no column will be used as parameter");		//set toolTip
			// add the checkBox to the Panel
			c2.gridx = 0;
			c2.gridy = 0;
			c2.weightx = 1;
			c2.weighty = 1;
			parametersPanel.add(checkboxNoParameter, c2);
			
			
			//Add checkboxCostParameter for the option of using cost info
			checkboxCostParameter = new JCheckBox();			
			checkboxCostParameter.setText("CostParameter");	
			checkboxCostParameter.setRolloverIcon(UIManager.getIcon("CheckBox.icon"));
			checkboxCostParameter.setSelectedIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_check.png"));
			checkboxCostParameter.setToolTipText("CostParameter details are based on Management Cost window");		//set toolTip			
			// add the checkBox to the Panel
			c2.gridx = 0;
			c2.gridy = 1;
			c2.weightx = 1;
			c2.weighty = 1;
			parametersPanel.add(checkboxCostParameter, c2);			
			
			
			// Add listeners to de-select all other checkBoxes
			checkboxNoParameter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (checkboxNoParameter.isSelected()) {
						checkboxCostParameter.setSelected(false);
						for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
							checkboxParameter.get(i).setSelected(false);
						} 
					}
				}
			});		
			
			
			// Add listeners to de-select all other checkBoxes
			checkboxCostParameter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if (checkboxCostParameter.isSelected()) {
						checkboxNoParameter.setSelected(false);
						for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
							checkboxParameter.get(i).setSelected(false);
						} 
					}
				}
			});				
			
			
			// Add listeners to checkBox so if then name has AllSx then other checkbox would be deselected 
			for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
				String currentCheckBoxName = yieldTable_ColumnNames[i];
				int currentCheckBoxIndex = i;
				
				checkboxParameter.get(i).addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						//Deselect the checkboxNoParameter & checkboxCostParameter
						checkboxNoParameter.setSelected(false);
						checkboxCostParameter.setSelected(false);
						
//						if (currentCheckBoxName.contains("AllSx")) {
//							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
//								if (j!=currentCheckBoxIndex) 	checkboxParameter.get(j).setSelected(false);
//							}
//						} else {
//							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
//								if (checkboxParameter.get(j).getText().contains("AllSx")) 	checkboxParameter.get(j).setSelected(false);
//							}
//						}					
					}
				});
			}

			//Do a resize to same size for JInteral Frame of the project to help repaint the checkboxVariables added					
			Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().setSize(Spectrum_Main.get_spectrumDesktopPane().getSelectedFrame().getSize());	
		}

	}
	
	public JCheckBox get_checkboxNoParameter() {
		return checkboxNoParameter;
	}
	
	public JCheckBox get_checkboxCostParameter() {
		return checkboxCostParameter;
	}
	
	public List<JCheckBox> get_checkboxParameter() {
		return checkboxParameter;
	}
	
	public String get_parameters_info_from_GUI() {			
		String parameters_info = "";
		for (int j = 0; j < checkboxParameter.size(); j++) {
			if (checkboxParameter.get(j).isSelected()) {			//add the index of selected Columns to this String
				parameters_info = parameters_info + j + " ";
			}
		}
		
		if (parameters_info.equals("") || checkboxNoParameter.isSelected()) {
			parameters_info = "NoParameter";		//= parametersScrollPanel.checkboxNoParameter.getText();
		}		
		
		if (checkboxCostParameter.isSelected()) {
			parameters_info = "CostParameter";		//= parametersScrollPanel.checkboxCostParameter.getText();
		}	
		
		return parameters_info;
	}
}
