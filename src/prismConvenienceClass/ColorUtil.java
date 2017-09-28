package prismConvenienceClass;

import java.awt.Color;

public class ColorUtil {
	
	public static Color makeTransparent(Color source, int alpha) {
		return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);
	}

}