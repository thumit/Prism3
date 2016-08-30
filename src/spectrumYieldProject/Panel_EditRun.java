package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Panel_EditRun extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel;
	private JPanel radioPanel_Left; 
	private ButtonGroup radioGroup_Left;
	private JRadioButton[] radioButton_Left; 
	private File[] listOfEditRuns = null;
	private JScrollPane scrollPane_Left, scrollPane_Right;
	private Panel_EditRun_Details[] combinePanel;
	
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
		scrollPane_Left = new JScrollPane();
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
		scrollPane_Right = new JScrollPane();
		splitPanel.setRightComponent(scrollPane_Right);
	
		// Add all Panel_EditRun_Details for all selected Runs, but only show the 1st selected Run details
		combinePanel = new Panel_EditRun_Details[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			combinePanel[i] = new Panel_EditRun_Details();
		}			
		scrollPane_Right.setViewportView(combinePanel[0]);	
				
		
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
		
		
	} // end Panel_EditRun()

	// Listener for radio buttons----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
    	for (int i = 0; i < listOfEditRuns.length; i++) {
			if (radioButton_Left[i].isSelected()) {
				scrollPane_Right.setViewportView(combinePanel[i]);	
			}
		}	
    }

    
	//--------------------------------------------------------------------------------------------------------------------------------
	// Get values to pass to other classes
	public File[] getGeneralInputFile() {
		File[] generalInputFile = new File[Panel_YieldProject.getSelectedRuns().length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			File temp = new File(listOfEditRuns[i].getAbsolutePath() + "/GeneralInputs.txt");
			combinePanel[i].getGeneralInputFile().renameTo(temp);
			generalInputFile[i] = temp;
		}
		return generalInputFile;
	}	

	public File[] getManagementOptionsFile() {
		File[] managementOptionsFile = new File[Panel_YieldProject.getSelectedRuns().length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			File temp = new File(listOfEditRuns[i].getAbsolutePath() + "/ManagementOptions.txt");
			combinePanel[i].getManagementOptionsFile().renameTo(temp);
			managementOptionsFile[i] = temp;
		}
		return managementOptionsFile;
	}	
	
	public File[] getUserConstraintsFile() {
		File[] userConstraintsFile = new File[Panel_YieldProject.getSelectedRuns().length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			File temp = new File(listOfEditRuns[i].getAbsolutePath() + "/UserConstraints.txt");
			combinePanel[i].getUserConstraintsFile().renameTo(temp);
			userConstraintsFile[i] = temp;
		}
		return userConstraintsFile;
	}
}
