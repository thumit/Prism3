package spectrumConvenienceClasses;

import java.awt.Component;

import javax.swing.JComponent;

public class WindowAppearanceHandle {	
	// All child components will be transparent
	public static void setOpaqueForAll(JComponent aComponent, boolean isOpaque) {
		aComponent.setOpaque(isOpaque);
		Component[] comps = aComponent.getComponents();
		for (Component c : comps) {
			if (c instanceof JComponent) {
				setOpaqueForAll((JComponent) c, isOpaque);
			}
		}
	}
}
