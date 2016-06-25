package spectrumGUI;

import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class Panel_Project extends JLayeredPane {

	public Panel_Project() {
		super.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.15);

		JScrollPane scrollPane_Left = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_Left);

		JTree tree = new JTree();
		scrollPane_Left.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Runs") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Run1");
				node_1.add(new DefaultMutableTreeNode("Inputs"));
				node_1.add(new DefaultMutableTreeNode("Outputs"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Run2");
				node_1.add(new DefaultMutableTreeNode("Inputs"));
				node_1.add(new DefaultMutableTreeNode("Outputs"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Run3");
				node_1.add(new DefaultMutableTreeNode("Inputs"));
				node_1.add(new DefaultMutableTreeNode("Outputs"));
				add(node_1);
			}
		}));

		// JScrollPane scrollPane_Right = new JScrollPane();
		// splitPane.setRightComponent(scrollPane_Right);

		super.add(splitPane, BorderLayout.CENTER);
		super.setOpaque(false);
	} // end newProjectPanel()
}
