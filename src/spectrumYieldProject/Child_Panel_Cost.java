package spectrumYieldProject;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Child_Panel_Cost extends JPanel {	
//	private JCheckBox checkboxNoParameter;
//	private List<JCheckBox> checkboxParameter;
	
	public Child_Panel_Cost (Read_Indentifiers read_Identifiers, String[] yieldTable_ColumnNames) {	

	
		
		
//		JPanel parametersPanel = new JPanel();	
//		parametersPanel.setLayout(new GridBagLayout());
//		GridBagConstraints c2 = new GridBagConstraints();
//		c2.fill = GridBagConstraints.HORIZONTAL;
//		c2.weightx = 1;
//	    c2.weighty = 1;
//	    
//		setViewportView(parametersPanel);
//	    		
//		if (yieldTable_ColumnNames != null && checkboxParameter == null) {				
//			checkboxParameter = new ArrayList<JCheckBox>();
//			
//			for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
//				String YTcolumnName = yieldTable_ColumnNames[i];
//
//				checkboxParameter.add(new JCheckBox(YTcolumnName));		//add checkbox
//				checkboxParameter.get(i).setToolTipText(read_Identifiers.get_ParameterToolTip(YTcolumnName) + " (Column index: " + i + ")");		//add toolTip
//				
//				// add checkboxParameter to the Panel
//			    c2.gridx = 0;
//			    c2.gridy = 1 + i;
//				c2.weightx = 1;
//			    c2.weighty = 1;
//				parametersPanel.add(checkboxParameter.get(i), c2);
//			}
//			
//			
//			//Add an extra checkbox for the option of not using any Column, use 1 instead as multiplier
//			//This is also the checkbox for the option of not using any Column as dynamic identifier
//			checkboxNoParameter = new JCheckBox();		//add checkbox			
//			checkboxNoParameter.setText("NoParameter");		
//			checkboxNoParameter.setToolTipText("1 is used as multiplier (parameter), no column will be used as parameter");		//set toolTip
//			
//			// add the checkBox to the Panel
//			c2.gridx = 0;
//			c2.gridy = 0;
//			c2.weightx = 1;
//			c2.weighty = 1;
//			parametersPanel.add(checkboxNoParameter, c2);
//			
//			// Add listeners to de-select all other checkBoxes
//			checkboxNoParameter.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent actionEvent) {
//					if (checkboxNoParameter.isSelected()) {
//						for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
//							checkboxParameter.get(i).setSelected(false);
//						} 
//					}
//				}
//			});								
//			
//			
//			// Add listeners to checkBox so if then name has AllSx then other checkbox would be deselected 
//			for (int i = 0; i < yieldTable_ColumnNames.length; i++) {
//				String currentCheckBoxName = yieldTable_ColumnNames[i];
//				int currentCheckBoxIndex = i;
//				
//				checkboxParameter.get(i).addActionListener(new ActionListener() {	
//					@Override
//					public void actionPerformed(ActionEvent actionEvent) {
//						//Deselect the NoParameter checkBox
//						checkboxNoParameter.setSelected(false);
//						
//						if (currentCheckBoxName.contains("AllSx")) {
//							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
//								if (j!=currentCheckBoxIndex) 	checkboxParameter.get(j).setSelected(false);
//							}
//						} else {
//							for (int j = 0; j < yieldTable_ColumnNames.length; j++) {		
//								if (checkboxParameter.get(j).getText().contains("AllSx")) 	checkboxParameter.get(j).setSelected(false);
//							}
//						}					
//					}
//				});
//			}
//
//			//Do a resize to same size for JInteral Frame of the project to help repaint the checkboxVariables added					
//			Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	
//		}

	}
	
//	public JCheckBox get_checkboxNoParameter() {
//		return checkboxNoParameter;
//	}
//	
//	public List<JCheckBox> get_checkboxParameter() {
//		return checkboxParameter;
//	}
}