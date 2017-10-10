package prismConvenienceClass;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class PrismTitleScrollPane extends JScrollPane {
	// Scroll Panel with Title and the nested ScrollPane with Border
	
	public PrismTitleScrollPane(String title, String title_alignment, Component component) {
		JScrollPane nested_scrollpane = new JScrollPane(component);	
		nested_scrollpane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorUtil.makeTransparent(Color.BLACK, 75)));
		nested_scrollpane.setPreferredSize(new Dimension(100, 100));
		
		TitledBorder border = new TitledBorder(title);
		if (title_alignment.equals("LEFT")) {
			border.setTitleJustification(TitledBorder.LEFT);
		} else if (title_alignment.equals("RIGHT")) {
			border.setTitleJustification(TitledBorder.RIGHT);
		}
		else if (title_alignment.equals("CENTER")) {
			border.setTitleJustification(TitledBorder.CENTER);
		}		
		setBorder(border);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);	
		setViewportView(nested_scrollpane);
	}
}
