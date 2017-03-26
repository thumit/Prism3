package spectrumConvenienceClasses;

import java.awt.Component;
import java.util.Enumeration;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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
	
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getLookAndFeelDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.getLookAndFeelDefaults().get(key);
			if (value instanceof FontUIResource) {
				UIManager.getLookAndFeelDefaults().put(key, f);
			}
		}
	}
}
