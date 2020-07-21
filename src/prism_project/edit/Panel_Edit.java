/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/

package prism_project.edit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import prism_convenience.ColorUtil;
import prism_convenience.IconHandle;
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
	
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	
	public Panel_Edit(File[] listOfEditRuns, JButton btnSave) {
		super.setLayout(new BorderLayout(0, 0));
		this.listOfEditRuns = listOfEditRuns;
		this.btnSave = btnSave;
		
		splitPanel = new JSplitPane();
		// splitPanel.setResizeWeight(0.15);
		splitPanel.setOneTouchExpandable(true);
		splitPanel.setDividerLocation(250);
		// splitPanel.setDividerSize(5);
		// splitPanel.getComponent(2).setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		// Left split panel--------------------------------------------------------------------------------
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
		scrollPane_Left = new JScrollPane();
		scrollPane_Left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
		scrollPane_Left.setViewportView(radioPanel_Left);	
		splitPanel.setLeftComponent(scrollPane_Left);
		
		
		// Right split panel-------------------------------------------------------------------------------
		JLabel animate_label = new JLabel("   LOADING...");
		animate_label.setHorizontalTextPosition(JLabel.CENTER);
		animate_label.setVerticalTextPosition(JLabel.BOTTOM);
		animate_label.setFont(new Font(null, Font.BOLD, 15));
		animate_label.setIcon(IconHandle.get_scaledImageIcon_replicate(128, 128, "main_animation.gif"));
		JPanel animate_panel = new JPanel();
		animate_panel.setLayout(new GridBagLayout());
		animate_panel.add(animate_label);
		scrollPane_Right = new JScrollPane();
		File[] contents = listOfEditRuns[0].listFiles();
		if (contents != null) {
			for (File f : contents) {
				if (f.getName().contains("database")) {
					scrollPane_Right.setViewportView(animate_panel);	// only show animation when loading the first model which has database. Do not show animation if the run is empty because it is a blinking --> annoying
				}
			}
		}
		scrollPane_Right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 70)));
		splitPanel.setRightComponent(scrollPane_Right);
			
		
		// Add all Panel_EditRun_Details for all selected Runs, but only show the 1st selected Run details
		combinePanel = new Panel_Edit_Details[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {	
			int processing_run = i;					
			executor.submit(() -> {
				combinePanel[processing_run] = new Panel_Edit_Details(listOfEditRuns[processing_run]);
				System.out.println("thread " + processing_run + " is working");
				radioButton_Left[processing_run].setSelected(true);
				scrollPane_Right.setViewportView(combinePanel[processing_run]);
				radioButton_Left[processing_run].setEnabled(true);
				btnSave.setToolTipText("Save " + listOfEditRuns[processing_run].getName());
			});			
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
					int response = JOptionPane.showOptionDialog(PrismMain.get_Prism_DesktopPane(),"Outputs from " + listOfEditRuns[i].getName() + " will be deleted when click 'Save'.\nSave now?", "Save Confirmation",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, IconHandle.get_scaledImageIcon(50, 50, "icon_question.png"), ExitOption, ExitOption[0]);
					
					if (response == 0) {		
						combinePanel[i].save_inputs_and_delete_outputs_for_this_run();
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
	public void save_inputs() {
		int total_Runs = listOfEditRuns.length;
		for (int i = 0; i < total_Runs; i++) {
			combinePanel[i].save_inputs_and_delete_outputs_for_this_run();
		}
	}
    
 
	
	
}
