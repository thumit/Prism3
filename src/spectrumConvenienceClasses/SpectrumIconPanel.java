package spectrumConvenienceClasses;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import spectrumROOT.Spectrum_Main;

public class SpectrumIconPanel extends JPanel{

	public SpectrumIconPanel(Component nestedCOmponent) {
		setLayout(new GridBagLayout());
//		Border tempBorder = BorderFactory.createMatteBorder(3, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 255));
//		setBorder(tempBorder);
		setBorder(null);
		
	
	    	    

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		JButton btnIconify = new JButton();
		btnIconify.setText("OFF");
		btnIconify.setToolTipText("Strata Filter");
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon_refresh.png"));
		Image scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btnIconify.setIcon(new ImageIcon(scaleImage));
		btnIconify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
		
				if (btnIconify.getText().equals("OFF")) {
					btnIconify.setText("ON");
					scrollPane.setViewportView(nestedCOmponent);
					// Get everything show up nicely
					Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	//this can replace the below 2 lines
					Spectrum_Main.mainFrameReturn().getSelectedFrame().revalidate();
					Spectrum_Main.mainFrameReturn().getSelectedFrame().repaint();
				} else {
					btnIconify.setText("OFF");
					scrollPane.setViewportView(null);
					// Get everything show up nicely
					Spectrum_Main.mainFrameReturn().getSelectedFrame().setSize(Spectrum_Main.mainFrameReturn().getSelectedFrame().getSize());	//this can replace the below 2 lines
					Spectrum_Main.mainFrameReturn().getSelectedFrame().revalidate();
					Spectrum_Main.mainFrameReturn().getSelectedFrame().repaint();
				}
			}
		});
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
	    c.weighty = 1;
	    
	    // Add the 1st grid - importPanel to the main Grid
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
	    c.weighty = 0;
		add(btnIconify, c);
		
		// Add Empty Label to make button not resize
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		add(new JLabel(), c);
		
		// Add the 1st grid - importPanel to the main Grid
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
	    c.weighty = 1;
	    c.gridheight = 2;
		add(scrollPane, c);
		
		
		
		
		
		
		
//		//button 3
//		JButton btnIconify = new JButton();
//		btnIconify.setText("OFF");
//		btnIconify.setToolTipText("Strata Filter");
//		ImageIcon icon = new ImageIcon(getClass().getResource("/icon_refresh.png"));
//		Image scaleImage = icon.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
//		btnIconify.setIcon(new ImageIcon(scaleImage));
//		btnIconify.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionEvent) {
//		
//				if (btnIconify.getText().equals("OFF")) {
//					btnIconify.setText("ON");
//					checkPanel.setVisible(true);
//					// Get everything show up nicely
//					GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
//				} else {
//					btnIconify.setText("OFF");
//					checkPanel.setVisible(false);
//					// Get everything show up nicely
//					GUI_Text_splitPanel.setLeftComponent(panel_Model_Identifiniton_GUI);
//				}
//			}
//		});
//		checkPanel.setVisible(false);

	}
}
