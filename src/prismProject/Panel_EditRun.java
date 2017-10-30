package prismProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import prismConvenienceClass.IconHandle;
import prismRoot.PrismMain;

public class Panel_EditRun extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel;
	private JPanel radioPanel_Left; 
	private ButtonGroup radioGroup_Left;
	private JRadioButton[] radioButton_Left; 
	private File[] listOfEditRuns;		// Return the selected Runs for editing
	private JScrollPane scrollPane_Left, scrollPane_Right;
	private Panel_EditRun_Details[] combinePanel;
	private JButton btnSave;
	
	public Panel_EditRun(File[] listOfEditRuns, JButton btnSave) {
		super.setLayout(new BorderLayout(0, 0));
		this.listOfEditRuns = listOfEditRuns;
		this.btnSave = btnSave;
		
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
			radioButton_Left[i].addActionListener(this);
			radioGroup_Left.add(radioButton_Left[i]);
			radioPanel_Left.add(radioButton_Left[i]);
			radioButton_Left[i].setEnabled(false);
		}
//		radioButton_Left[0].setSelected(true);
		scrollPane_Left.setViewportView(radioPanel_Left);					
		
		
		// Right split panel-------------------------------------------------------------------------------
		scrollPane_Right = new JScrollPane();
		splitPanel.setRightComponent(scrollPane_Right);
				
	
		// Add all Panel_EditRun_Details for all selected Runs, but only show the 1st selected Run details
		combinePanel = new Panel_EditRun_Details[listOfEditRuns.length];
		Thread[] thread_array = new Thread[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
			final int processingRun = i;
			thread_array[i] = new Thread() { // Make a thread so JFrame will not be frozen
				public void run() {
					combinePanel[processingRun] = new Panel_EditRun_Details(listOfEditRuns[processingRun]);
					if (combinePanel[processingRun] != null) {
						radioButton_Left[processingRun].setSelected(true);
						scrollPane_Right.setViewportView(combinePanel[processingRun]);
						radioButton_Left[processingRun].setEnabled(true);
						btnSave.setToolTipText("Save " + listOfEditRuns[processingRun].getName());
						interrupt();
					}
				}
			};
			thread_array[i].start();			
		}
			
		
		// Button Save------------------------------------------------------------------------------------
		if (btnSave.getActionListeners() != null) {
			for (int i = 0; i < btnSave.getActionListeners().length; i++) {
				btnSave.removeActionListener(btnSave.getActionListeners()[i]);
			}
		}
		btnSave.addActionListener(e -> {
			for (int i = 0; i < listOfEditRuns.length; i++) {
				if (radioButton_Left[i].isSelected()) {
					String[] ExitOption = { "Save", "Don't Save", "Cancel"};
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Outputs from " + listOfEditRuns[i].getName() + "  will be deleted when click 'Save'. Your option?", "Save Confirmation",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
					
					if (response == 0) {		
						// Delete all output files, problem file, and solution file of the edited Runs
						for (int j = 0; j < listOfEditRuns.length; j++) {
							File[] contents = listOfEditRuns[j].listFiles();
							if (contents != null) {
								for (File f : contents) {
									if (f.getName().contains("output") || f.getName().contains("problem") || f.getName().contains("solution")) {
										f.delete();
									}
								}
							}
						}	
						combinePanel[i].create_inputFiles_for_thisRun();
			        }

				}
			}
		});
		
		
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);	
	}

	// Listener for radio buttons----------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {
    	for (int i = 0; i < listOfEditRuns.length; i++) {
			if (radioButton_Left[i].isSelected()) {
				scrollPane_Right.setViewportView(combinePanel[i]);	
				btnSave.setToolTipText("Save " + listOfEditRuns[i].getName());
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
