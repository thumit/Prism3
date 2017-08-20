package prismProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import prismRoot.PrismMain;

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
			final int processingRun = i;
			Thread thread = new Thread() {			// Make a thread so JFrame will not be frozen
				public void run() {
					combinePanel[processingRun] = new Panel_EditRun_Details(listOfEditRuns[processingRun]);
					radioButton_Left[processingRun].setSelected(true);
					scrollPane_Right.setViewportView(combinePanel[processingRun]);
					this.interrupt();
				}
			};
			thread.start();
		}			
				
		
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
    
    //Get all input Files from all edited runs
	public void createInputFiles() {
		int total_Runs = listOfEditRuns.length;
		for (int i = 0; i < total_Runs; i++) {
			combinePanel[i].create_inputFiles_for_thisRun();
		}
	}
    
 
	
	
}
