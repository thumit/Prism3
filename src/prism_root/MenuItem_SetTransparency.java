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

package prism_root;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prism_convenience.IconHandle;

public class MenuItem_SetTransparency extends JMenuItem {

	public MenuItem_SetTransparency(Prism3Main main) {
		setText("Change Transparency");
		setIcon(IconHandle.get_scaledImageIcon(15, 15, "icon_glassy.png"));

		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK, true));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// Create a slider
				int currentOpacity = (int) (main.getOpacity() * 100);
				final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, currentOpacity);
				slider.setSize(400, 15);
				slider.setMajorTickSpacing(10);
				slider.setMinorTickSpacing(1);
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
				sliderPanel.setPreferredSize(new Dimension(400, 30));
				sliderPanel.add(slider);
				
				// Add sliderPanel to a Popup Panel
				String ExitOption[] = { "Ok" };
				int response = JOptionPane.showOptionDialog(Prism3Main.get_Prism_DesktopPane(), sliderPanel,
						"Transparency", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						IconHandle.get_scaledImageIcon(40, 40, "icon_glassy.png"), ExitOption, ExitOption[0]);
				if (response == 0) {

				}
			}
		});
	}
}
