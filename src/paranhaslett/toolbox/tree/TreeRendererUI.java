package paranhaslett.toolbox.tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import paranhaslett.toolbox.model.Artifact;

import java.awt.*;

public class TreeRendererUI extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = -4221259711269183060L;

    public TreeRendererUI() {
        setTextSelectionColor(Color.white);
        setBackgroundSelectionColor(Color.blue);
    }

    public Component getTreeCellRendererComponent(JTree tree, DefaultMutableTreeNode value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        Object nodeInfo = value.getUserObject();
        if (nodeInfo instanceof Artifact) {
            Artifact item = (Artifact) nodeInfo;
            setIcon(item.tool().icon());
            setText(item.getTitle());
        }

        return this;
    }

    public ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
