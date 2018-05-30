package paranhaslett.toolbox.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import paranhaslett.toolbox.model.Artifact;

public class TreeRendererUI extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = -4221259711269183060L;

    public TreeRendererUI() {
        setTextSelectionColor(Color.white);
        setBackgroundSelectionColor(Color.blue);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object nodeInfo = node.getUserObject();
        if (nodeInfo instanceof Artifact) {
            Artifact item = (Artifact) nodeInfo;
            setIcon(item.tool().icon());
            setText(item.getData(0));
        }

        return this;
    }

}
