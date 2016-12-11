package spectrumROOT;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuItem_SetTransparency extends JMenuItem {
	private ImageIcon icon;
	private Image scaleImage;

	public MenuItem_SetTransparency(Spectrum_Main main) {
		setText("Change Window Transparency");

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// Create a slider
				int currentOpacity = (int) (main.getOpacity() * 100);
				final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, currentOpacity);
				slider.setSize(400, 20);
				slider.setMajorTickSpacing(20);
				slider.setMinorTickSpacing(4);
				slider.setPaintTicks(true);

				
				// Create a scrollBar & add listeners
				final JScrollBar scrollBar = new JScrollBar();
				
				scrollBar.addAdjustmentListener(new AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						float sliderValue = (float) slider.getValue() / 100;
						main.setOpacity((float) sliderValue);
					}
				});

				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						float sliderValue = (float) slider.getValue() / 100;
						main.setOpacity((float) sliderValue);
					}
				});

				
				// Add slider to sliderPanel
				JPanel sliderPanel = new JPanel(new BorderLayout());
				sliderPanel.setPreferredSize(new Dimension(300, 50));
				sliderPanel.add(slider);

				
				// Add sliderPanel to a Popup Panel
				icon = new ImageIcon(getClass().getResource("/icon_question.png"));
				scaleImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				String ExitOption[] = { "Ok" };
				int response = JOptionPane.showOptionDialog(Spectrum_Main.mainFrameReturn(), sliderPanel,
						"Drag the slider to change transparency", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						new ImageIcon(scaleImage), ExitOption, ExitOption[0]);

				if (response == 0) {

				}

				
			}
		});
	}
}
