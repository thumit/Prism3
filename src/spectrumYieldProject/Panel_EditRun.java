package spectrumYieldProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JPanel radioPanel; 
	private ButtonGroup radioGroup; 
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
		radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		radioGroup = new ButtonGroup();
		JRadioButton[] radioButton  = new JRadioButton[listOfEditRuns.length];
		for (int i = 0; i < listOfEditRuns.length; i++) {
				radioButton[i] = new JRadioButton(listOfEditRuns[i].getName());
				radioGroup.add(radioButton[i]);
				radioPanel.add(radioButton[i]);
		}
		scrollPane_Left.setViewportView(radioPanel);
		
		
		
		
		// Right split panel-------------------------------------------------------------------------------
		JScrollPane scrollPane_Right = new JScrollPane();
		splitPanel.setRightComponent(scrollPane_Right);

		
		// Add all components to JInternalFrame------------------------------------------------------------
		super.add(splitPanel, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end Panel_EditRun()


}
