package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Panel_EditRun extends JLayeredPane {
	private JSplitPane splitPanel;
	private JPanel radioPanel_Left, radioPanel_Right; 
	private ButtonGroup radioGroup_Left, radioGroup_Right; 
	private File[] listOfEditRuns = null;
	

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
		
		JRadioButton[] radioButton_Left  = new JRadioButton[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			radioButton_Left[i] = new JRadioButton(listOfEditRuns[i].getName());
				radioGroup_Left.add(radioButton_Left[i]);
				radioPanel_Left.add(radioButton_Left[i]);
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
		
		JRadioButton[] radioButton_Right  = new JRadioButton[3];
		radioButton_Right[0]= new JRadioButton("General Inputs");
		radioButton_Right[1]= new JRadioButton("Rules");
		radioButton_Right[2]= new JRadioButton("Constraints");
		radioButton_Right[0].setSelected(true);
		for (int i = 0; i < 3; i++) {
				radioGroup_Right.add(radioButton_Right[i]);
				radioPanel_Right.add(radioButton_Right[i]);
		}	
		
		JSplitPane GUI_Text_split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		GUI_Text_split.setResizeWeight(0.5);
		GUI_Text_split.setDividerSize(4);
		GUI_Text_split.setLeftComponent(null);
		GUI_Text_split.setRightComponent(null);
				
		JPanel combinePanel = new JPanel();
		combinePanel.setLayout(new BorderLayout());
		combinePanel.add(radioPanel_Right, BorderLayout.NORTH);
		combinePanel.add(GUI_Text_split, BorderLayout.CENTER);
			
		scrollPane_Right.setViewportView(combinePanel);	

				
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_EditRun()


}
