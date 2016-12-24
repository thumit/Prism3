package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	private File[] listOfEditRuns;		// Return the selected Runs for editing
	private JScrollPane scrollPane_Left, scrollPane_Right;
	private Panel_EditRun_Details[] combinePanel;
	
	public Panel_EditRun(File[] runsList) {
		super.setLayout(new BorderLayout(0, 0));
		listOfEditRuns = runsList;
		
		splitPanel = new JSplitPane();
		// splitPanel.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(200);
		// splitPanel.setDividerSize(5);
		// splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		// Left split panel--------------------------------------------------------------------------------
		scrollPane_Left = new JScrollPane();
		splitPanel.setLeftComponent(scrollPane_Left);
		
		// Add all selected Runs to radioPanel and add that panel to scrollPane_Left
		radioPanel_Left = new JPanel();
		radioPanel_Left.setLayout(new BoxLayout(radioPanel_Left, BoxLayout.Y_AXIS));
		radioGroup_Left = new ButtonGroup();
		
		radioButton_Left = new JRadioButton[listOfEditRuns.length];
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
			combinePanel[i] = new Panel_EditRun_Details(listOfEditRuns[i]);
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
    
    //Get all input Files from all edited runs and return a 2D array which contains Input Files with corrected Paths
	public File[][] getInputFiles() {
		int total_Runs = listOfEditRuns.length;
		File[][] InputFiles = new File[total_Runs][];
			
		for (int i = 0; i < total_Runs; i++) {	
			List<File> inputFiles_list = combinePanel[i].get_List_Of_inputFiles();	
			int total_Inputs_perRun = inputFiles_list.size();
			
			InputFiles[i] = new File[total_Inputs_perRun];		//Redim the array
			
			for (int j = 0; j < total_Inputs_perRun; j++) {		
				File temp = new File(listOfEditRuns[i].getAbsolutePath() + "/" + inputFiles_list.get(j).getName());			
				inputFiles_list.get(j).renameTo(temp);	
				InputFiles[i][j] = temp;
			}
		}
		
		return InputFiles;
	}	
    
 
	
	
}
