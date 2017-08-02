package spectrumYieldProject;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import spectrumConvenienceClasses.TableModelSpectrum;

public class ScrollPane_ConstraintsSplit  extends JScrollPane {

	private List<JCheckBox> selected_staticCheckboxes;
	private List<JCheckBox> selected_parametersCheckboxes;
	private List<JCheckBox> selected_dynamicCheckboxes;
	private Object[][] data;
	private JCheckBox autoDescription;
	
	
	public ScrollPane_ConstraintsSplit (List<JCheckBox> staticCheckboxes, List<JCheckBox> parametersCheckboxes, List<JCheckBox> dynamicCheckboxes) {
		// staticScrollPane	------------------------------------------------------------------------------	
		selected_staticCheckboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : staticCheckboxes) {
			selected_staticCheckboxes.add(new JCheckBox(i.getText()));
		}
		
		JPanel staticPanel = new JPanel();
		staticPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
		c1.weighty = 1;

		// Add all checkboxes
		for (int i = 0; i < selected_staticCheckboxes.size(); i++) {
			c1.gridx = 0;
			c1.gridy = i;
			staticPanel.add(selected_staticCheckboxes.get(i), c1);
		}
		
		JScrollPane staticScrollPane = new JScrollPane(staticPanel);
		TitledBorder border = new TitledBorder("Static Identifiers");
		border.setTitleJustification(TitledBorder.CENTER);
		staticScrollPane.setBorder(border);
		staticScrollPane.setPreferredSize(new Dimension(300, 250));

		
		
		
		// parametersScrollPane	------------------------------------------------------------------------------	
//		List<JCheckBox> selected_parametersCheckboxes = new ArrayList<JCheckBox>();
//		for (JCheckBox i : parametersCheckboxes) {
//			if (i.isSelected()) {
//				selected_parametersCheckboxes.add(new JCheckBox(i.getText()));
//			}		
//		}
		
		
		selected_parametersCheckboxes = new ArrayList<JCheckBox>();
		selected_parametersCheckboxes.add(new JCheckBox("Selected Parameters"));
		
		JPanel parametersPanel = new JPanel();
		parametersPanel.setLayout(new GridBagLayout());
		c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
		c1.weighty = 1;

		// Add all checkboxes
		for (int i = 0; i < selected_parametersCheckboxes.size(); i++) {
			c1.gridx = 0;
			c1.gridy = i;
			parametersPanel.add(selected_parametersCheckboxes.get(i), c1);
		}
		
		JScrollPane parametersScrollPane = new JScrollPane(parametersPanel);
		border = new TitledBorder("Parameters");
		border.setTitleJustification(TitledBorder.CENTER);
		parametersScrollPane.setBorder(border);
		parametersScrollPane.setPreferredSize(new Dimension(300, 250));		
		
		
		
		
		// dynamicScrollPane	------------------------------------------------------------------------------	
		selected_dynamicCheckboxes = new ArrayList<JCheckBox>();
		for (JCheckBox i : dynamicCheckboxes) {
			if (i.isSelected()) {
				selected_dynamicCheckboxes.add(new JCheckBox(i.getText()));
			}		
		}
		
		JPanel dynamicPanel = new JPanel();
		dynamicPanel.setLayout(new GridBagLayout());
		c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.weightx = 1;
		c1.weighty = 1;

		// Add all checkboxes
		for (int i = 0; i < selected_dynamicCheckboxes.size(); i++) {
			c1.gridx = 0;
			c1.gridy = i;
			dynamicPanel.add(selected_dynamicCheckboxes.get(i), c1);
		}
		
		JScrollPane dynamicScrollPane = new JScrollPane(dynamicPanel);
		border = new TitledBorder("Dynamic Identifiers");
		border.setTitleJustification(TitledBorder.CENTER);
		dynamicScrollPane.setBorder(border);
		dynamicScrollPane.setPreferredSize(new Dimension(300, 250));					
		
		
		
		
		// tableScrollPane	------------------------------------------------------------------------------	
		int rowCount = 1;
		int colCount = 8;
		data = new Object[rowCount][colCount];
		String[] columnNames = new String[] {"bc_id", "bc_description", "bc_type",  "bc_multiplier", "lowerbound", "lowerbound_perunit_penalty", "upperbound", "upperbound_perunit_penalty"};	         				
		data[0][3] = (double) 1;
		
		TableModelSpectrum model = new TableModelSpectrum(rowCount, colCount, data, columnNames) {
			@Override
			public Class getColumnClass(int c) {
				if (c == 0) return Integer.class;      //column 0 accepts only Integer
				else if (c >= 3 && c <= 7) return Double.class;      //column 3 to 7 accept only Double values   
				else return String.class;				//Just because delete all rows make JTable fail, otherwise we should use the below line
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 0) { //  The first column is un-editable
					return false;
				} else {
					return true;
				}
			}
		};
		
		JTable table = new JTable(model);
		
		class comboBox_constraint_type extends JComboBox {	
			public comboBox_constraint_type() {
				addItem("SOFT");
				addItem("HARD");
				addItem("FREE");
				setSelectedIndex(0);
			}
		}
		
		// Set up Type for each column 2
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new comboBox_constraint_type()));
		
		table.getColumnModel().getColumn(1).setPreferredWidth(200);	//Set width of Column bigger
		table.getColumnModel().getColumn(3).setPreferredWidth(100);	//Set width of Column bigger
		table.getColumnModel().getColumn(4).setPreferredWidth(100);	//Set width of Column bigger
		table.getColumnModel().getColumn(5).setPreferredWidth(200);	//Set width of Column bigger
		table.getColumnModel().getColumn(6).setPreferredWidth(100);	//Set width of Column bigger
		table.getColumnModel().getColumn(7).setPreferredWidth(200);	//Set width of Column bigger

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
		table.getTableHeader().setReorderingAllowed(false);		//Disable columns move

		JScrollPane tableScrollPane = new JScrollPane(table);
		border = new TitledBorder("Infomation below is applied for all new constraints");
		border.setTitleJustification(TitledBorder.CENTER);
		tableScrollPane.setBorder(border);
		tableScrollPane.setPreferredSize(new Dimension(600, 100));		// only the 150 matters, 650 does not matter
		//Hide the id column	
		table.removeColumn(table.getColumnModel().getColumn(0));		// The data is not changed anyway
		

		
		
		// Add all to a Panel------------------------------------------------------------------------------	
		JPanel popupPanel = new JPanel();	
		//	These codes make the popupPanel resizable --> the Big ScrollPane resizable --> JOptionPane resizable
		popupPanel.addHierarchyListener(new HierarchyListener() {
		    public void hierarchyChanged(HierarchyEvent e) {
		        Window window = SwingUtilities.getWindowAncestor(popupPanel);
		        if (window instanceof Dialog) {
		            Dialog dialog = (Dialog)window;
		            if (!dialog.isResizable()) {
		                dialog.setResizable(true);
		            }
		        }
		    }
		});
		
		
		popupPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// Add Static Splitters
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		popupPanel.add(staticScrollPane, c);

		// Add Parameters Splitters
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		popupPanel.add(parametersScrollPane, c);

		// Add Dynamic Splitters
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		popupPanel.add(dynamicScrollPane, c);
		
		// Add tableScrollPane
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 3;
		popupPanel.add(tableScrollPane, c);
		
		// Add autoDescription checkbox
		autoDescription = new JCheckBox("Add splitting infomation to constraints description (i.e. bc_description)");
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 3;
		popupPanel.add(autoDescription, c);

		
		
		//Add the Panel to this Big ScrollPane------------------------------------------------------------------------------
		border = new TitledBorder("Select items you want to split - currently supports splitting only Static Identifiers (future: Dynamic Identifiers & Parameters)");
		border.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
		setViewportView(popupPanel);			
	}
	
	
	public List<String> get_splitStatic_NameList() {
		List<String> splitStatic_NameList = new ArrayList<String>();
		for (JCheckBox i : selected_staticCheckboxes) {
			if (i.isSelected()) {
				splitStatic_NameList.add(i.getText());
			}		
		}	
		return splitStatic_NameList;
	}
	
	
	public Object[][] get_multiple_constraints_data() {
		return data;
	}
	
	
	public JCheckBox get_autoDescription() {
		return autoDescription;
	}
}
