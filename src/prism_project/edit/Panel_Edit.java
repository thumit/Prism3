/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prism_project.edit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import prism_convenience_class.ColorUtil;
import prism_convenience_class.IconHandle;
import prism_root.PrismMain;

public class Panel_Edit extends JLayeredPane implements ActionListener {
	private JSplitPane splitPanel;
	private JPanel radioPanel_Left; 
	private ButtonGroup radioGroup_Left;
	private JRadioButton[] radioButton_Left; 
	private File[] listOfEditRuns;		// Return the selected Runs for editing
	private JScrollPane scrollPane_Left, scrollPane_Right;
	private Panel_Edit_Details[] combinePanel;
	private JButton btnSave;
	
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	
	public Panel_Edit(File[] listOfEditRuns, JButton btnSave) {
		super.setLayout(new BorderLayout(0, 0));
		this.listOfEditRuns = listOfEditRuns;
		this.btnSave = btnSave;
		
		splitPanel = new JSplitPane();
		// splitPanel.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(245);
		// splitPanel.setDividerSize(5);
		// splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		// Left split panel--------------------------------------------------------------------------------
		scrollPane_Left = new JScrollPane();
		scrollPane_Left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
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
		scrollPane_Right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
		splitPanel.setRightComponent(scrollPane_Right);
				
	
		// Add all Panel_EditRun_Details for all selected Runs, but only show the 1st selected Run details
		combinePanel = new Panel_Edit_Details[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {	
			int processing_run = i;					
			Runnable task = () -> {
				combinePanel[processing_run] = new Panel_Edit_Details(listOfEditRuns[processing_run]);
				System.out.println("thread " + processing_run + " is working");
				radioButton_Left[processing_run].setSelected(true);
				scrollPane_Right.setViewportView(combinePanel[processing_run]);
				radioButton_Left[processing_run].setEnabled(true);
				btnSave.setToolTipText("Save " + listOfEditRuns[processing_run].getName());
			};			
			executor.submit(new Thread(task));
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
					String[] ExitOption = { "Save", "Cancel"};
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
    	
    	// This is to reload the unsuccessful runs, triggered when any run is on click
    	for (int i = 0; i < listOfEditRuns.length; i++) {  			
			if (!radioButton_Left[i].isEnabled()) {
				int processing_run = i;
				executor.submit(() -> {
					combinePanel[processing_run] = new Panel_Edit_Details(listOfEditRuns[processing_run]);
					radioButton_Left[processing_run].setEnabled(true);
		    	});
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
