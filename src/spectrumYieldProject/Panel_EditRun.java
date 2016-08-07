package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

public class Panel_EditRun extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel, GUI_Text_splitPanel ;
	private JPanel radioPanel_Left, radioPanel_Right, combinePanel; 
	private ButtonGroup radioGroup_Left, radioGroup_Right; 
	private JRadioButton[] radioButton_Left, radioButton_Right; 
	private File[] listOfEditRuns = null;
	
	//6 panels for each selected Run
	PaneL_General_Inputs_GUI[] panelInput0_GUI;
	PaneL_General_Inputs_Text[] panelInput0_TEXT;
	PaneL_Rules_GUI[] panelInput1_GUI;
	PaneL_Rules_Text[] panelInput1_TEXT;
	PaneL_Constraints_GUI[] panelInput2_GUI;
	PaneL_Constraints_Text[] panelInput2_TEXT;		

	
	public Panel_EditRun() {
		super.setLayout(new BorderLayout(0, 0));
		// Return the selected Runs
		listOfEditRuns = Panel_YieldProject.getSelectedRuns();
		
		
		splitPanel = new JSplitPane();
		// splitPane.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(200);
		// splitPane.setDividerSize(5);
		// splitPane.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		// Left split panel--------------------------------------------------------------------------------
		JScrollPane scrollPane_Left = new JScrollPane();
		splitPanel.setLeftComponent(scrollPane_Left);
		
		// Add all selected Runs to radioPanel and add that panel to scrollPane_Left
		radioPanel_Left = new JPanel();
		radioPanel_Left.setLayout(new BoxLayout(radioPanel_Left, BoxLayout.Y_AXIS));
		radioGroup_Left = new ButtonGroup();
		
		radioButton_Left  = new JRadioButton[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			radioButton_Left[i] = new JRadioButton(listOfEditRuns[i].getName());
				radioGroup_Left.add(radioButton_Left[i]);
				radioPanel_Left.add(radioButton_Left[i]);
				radioButton_Left[i].addActionListener(this);
		}
		radioButton_Left[0].setSelected(true);
		
		scrollPane_Left.setViewportView(radioPanel_Left);					
		
		
		// Right split panel-------------------------------------------------------------------------------
		JScrollPane scrollPane_Right = new JScrollPane();
		splitPanel.setRightComponent(scrollPane_Right);
	
		// Add 3 input options to radioPanel and add that panel to scrollPane_Right at combinePanel NORTH
		radioPanel_Right = new JPanel();
		radioPanel_Right.setLayout(new FlowLayout());		
		radioGroup_Right = new ButtonGroup();
		
		radioButton_Right  = new JRadioButton[3];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[1]= new JRadioButton("Rules");
		radioButton_Right[2]= new JRadioButton("Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < 3; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
				radioButton_Right[i].addActionListener(this);
		}	
		
		GUI_Text_splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_splitPanel.setDividerSize(5);
		GUI_Text_splitPanel.setLeftComponent(null);
		GUI_Text_splitPanel.setRightComponent(null);
				
		combinePanel = new JPanel();
		combinePanel.setLayout(new BorderLayout());
		combinePanel.add(radioPanel_Right, BorderLayout.NORTH);
		combinePanel.add(GUI_Text_splitPanel, BorderLayout.CENTER);
			
		scrollPane_Right.setViewportView(combinePanel);	

				
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
		
	
		// Create all new 6 panels for each selected Run--------------------------------------------------
		panelInput0_GUI = new PaneL_General_Inputs_GUI[listOfEditRuns.length];
		panelInput0_TEXT = new PaneL_General_Inputs_Text[listOfEditRuns.length];
		panelInput1_GUI = new PaneL_Rules_GUI[listOfEditRuns.length];
		panelInput1_TEXT = new PaneL_Rules_Text[listOfEditRuns.length];
		panelInput2_GUI = new PaneL_Constraints_GUI[listOfEditRuns.length];
		panelInput2_TEXT = new PaneL_Constraints_Text[listOfEditRuns.length];
		
		for (int i = 0; i < listOfEditRuns.length; i++) {
			panelInput0_GUI[i] = new PaneL_General_Inputs_GUI();
			panelInput0_TEXT[i] = new PaneL_General_Inputs_Text();
			panelInput1_GUI[i] = new PaneL_Rules_GUI();
			panelInput1_TEXT[i] = new PaneL_Rules_Text();
			panelInput2_GUI[i] = new PaneL_Constraints_GUI();
			panelInput2_TEXT[i] = new PaneL_Constraints_Text();	
		}					
		
		// Show the 2 panelInput of the first selected Run
		GUI_Text_splitPanel.setLeftComponent(panelInput0_GUI[0]);
		GUI_Text_splitPanel.setRightComponent(panelInput0_TEXT[0]);		
	} // end Panel_EditRun()

	
	// Listener for radio buttons----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
    	for (int i = 0; i < listOfEditRuns.length; i++) {
			if (radioButton_Left[i].isSelected()) {
				for (int j = 0; j < 3; j++) {
					if (radioButton_Right[j].isSelected()) {			
						if (j == 0) {
							GUI_Text_splitPanel.setLeftComponent(panelInput0_GUI[i]);
							GUI_Text_splitPanel.setRightComponent(panelInput0_TEXT[i]);
						} else if (j == 1) {
							GUI_Text_splitPanel.setLeftComponent(panelInput1_GUI[i]);
							GUI_Text_splitPanel.setRightComponent(panelInput1_TEXT[i]);
						} else if (j == 2) {
							GUI_Text_splitPanel.setLeftComponent(panelInput2_GUI[i]);
							GUI_Text_splitPanel.setRightComponent(panelInput2_TEXT[i]);
						}				
					}
				}
			}
		}	
    }
 

	// Panel General Inputs-----------------------------------------------------------------------------	
	class PaneL_General_Inputs_GUI extends JLayeredPane {
		public PaneL_General_Inputs_GUI() {
			setLayout(new GridLayout(0,4,30,10));		//2 last numbers are the gaps 			
			
			JLabel label1 = new JLabel("Number of planning periods");
			JComboBox combo1 = new JComboBox();		
			for (int i = 1; i <= 50; i++) {
				combo1.addItem(i);
			}
			combo1.setSelectedItem((int) 5);
			super.add(label1);
			super.add(combo1);
			
			JLabel label2 = new JLabel("Budget limit (thousand dollars)");
			JSpinner spin2 = new JSpinner (new SpinnerNumberModel(1000, 0, null, 10));
			JFormattedTextField SpinnerText = ((DefaultEditor) spin2.getEditor()).getTextField();
			SpinnerText.setHorizontalAlignment(JTextField.LEFT);
			super.add(label2);
			super.add(spin2);
			
			JLabel label3 = new JLabel("Discount rate (%)");
			JComboBox combo3 = new JComboBox();		
			for (int i = 0; i <= 100; i++) {
				double value = (double) i/10;
				combo3.addItem(value);
			}
			combo3.setSelectedItem((double) 3.5);
			super.add(label3);
			super.add(combo3);
			
			JLabel label4 = new JLabel("Solver for optimization");
			JComboBox  combo4 = new JComboBox();
			combo4.addItem("CPLEX");
			combo4.addItem("LPSOLVE");
			combo4.addItem("CBC");
			combo4.addItem("CLP");
			combo4.addItem("GUROBI");
			combo4.addItem("GLPK");
			combo4.addItem("SPCIP");
			combo4.addItem("SOPLEX");
			combo4.addItem("XPRESS");	
			super.add(label4);
			super.add(combo4);
			
			
			Action apply = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					// Apply any change in the GUI to the TEXT area	
					String input0_info = label1.getText() + "	" + combo1.getSelectedItem().toString() + "\n\r"
							+ label2.getText() + "	" + (Integer)spin2.getValue() + "\n\r"
							+ label3.getText() + "	" + combo3.getSelectedItem().toString() + "\n\r"
							+ label4.getText() + "	" + combo4.getSelectedItem().toString();
							
					for (int i = 0; i < listOfEditRuns.length; i++) {
						if (radioButton_Left[i].isSelected()) {
							for (int j = 0; j < 3; j++) {
								panelInput0_TEXT[i].setText(input0_info);
							}
						}
					}
				}
			};
			
			
			combo1.addActionListener(apply);
			combo3.addActionListener(apply);
			combo4.addActionListener(apply);
			
			DefaultFormatter formatter = (DefaultFormatter) SpinnerText.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    spin2.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	spin2.setValue(spin2.getValue());
		        	// Apply any change in the GUI to the TEXT area	
		        	String input0_info = label1.getText() + "	" + combo1.getSelectedItem().toString() + "\n\r"
							+ label2.getText() + "	" + (Integer)spin2.getValue() + "\n\r"
							+ label3.getText() + "	" + combo3.getSelectedItem().toString() + "\n\r"
							+ label4.getText() + "	" + combo4.getSelectedItem().toString();
					
					for (int i = 0; i < listOfEditRuns.length; i++) {
						if (radioButton_Left[i].isSelected()) {
							for (int j = 0; j < 3; j++) {
								panelInput0_TEXT[i].setText(input0_info);
							}
						}
					}	
		        }
		    });
		}
	}
	
	class PaneL_General_Inputs_Text extends JTextArea {
		public PaneL_General_Inputs_Text() {		
			setRows(10);		// set text areas with 10 rows when starts	
		}
	}

	// Panel Rules-----------------------------------------------------------------------------------
	class PaneL_Rules_GUI extends JLayeredPane {
		public PaneL_Rules_GUI() {
			setLayout(new GridLayout(0,2,10,10));		//2 last numbers are the gaps 			
			
			JLabel label1 = new JLabel("Total number of planning periods");
			JSpinner spin1 = new JSpinner();
			super.add(label1);
			super.add(spin1);
			
			JLabel label2 = new JLabel("Total budget for the entire planning horizon ($)");
			JSpinner spin2 = new JSpinner();
			super.add(label2);
			super.add(spin2);
			
			JLabel label3 = new JLabel("Discount Rate (%)");
			JTextField tField3 = new JTextField();
			super.add(label3);
			super.add(tField3);
			
			JLabel label4 = new JLabel("Solver for optimization");
			JComboBox  combo4 = new JComboBox();
			combo4.addItem("CPLEX");
			combo4.addItem("LPSOLVE");
			combo4.addItem("CBC");
			combo4.addItem("CLP");
			combo4.addItem("GUROBI");
			combo4.addItem("GLPK");
			combo4.addItem("SPCIP");
			combo4.addItem("SOPLEX");
			combo4.addItem("XPRESS");		
			super.add(label4);
			super.add(combo4);
			
	
		}
	}
	
	class PaneL_Rules_Text extends JTextArea {
		public PaneL_Rules_Text() {
			setRows(10);		// set text areas with 10 rows when starts
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Panel Constraints-----------------------------------------------------------------------------------
	class PaneL_Constraints_GUI extends JLayeredPane {
		public PaneL_Constraints_GUI() {
			setLayout(new GridLayout(0,2,10,10));		//2 last numbers are the gaps 			
			
			JLabel label1 = new JLabel("Total number of planning periods");
			JSpinner spin1 = new JSpinner();
			super.add(label1);
			super.add(spin1);
			
			JLabel label2 = new JLabel("Total budget for the entire planning horizon ($)");
			JSpinner spin2 = new JSpinner();
			super.add(label2);
			super.add(spin2);
			
			JLabel label3 = new JLabel("Discount Rate (%)");
			JTextField tField3 = new JTextField();
			super.add(label3);
			super.add(tField3);
			
			JLabel label4 = new JLabel("Solver for optimization");
			JComboBox  combo4 = new JComboBox();
			combo4.addItem("CPLEX");
			combo4.addItem("LPSOLVE");
			combo4.addItem("CBC");
			combo4.addItem("CLP");
			combo4.addItem("GUROBI");
			combo4.addItem("GLPK");
			combo4.addItem("SPCIP");
			combo4.addItem("SOPLEX");
			combo4.addItem("XPRESS");		
			super.add(label4);
			super.add(combo4);
			
	
		}
	}
	
	class PaneL_Constraints_Text  extends JTextArea {
		public PaneL_Constraints_Text() {
			setRows(10);		// set text areas with 10 rows when starts		
		}
	}

}
