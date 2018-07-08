package paranhaslett.toolbox.tree;

import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import paranhaslett.toolbox.model.Artifact;

public class TreeUI extends JTree {
    private static final long serialVersionUID = -4252742793844024659L;
    private DefaultMutableTreeNode selection;
    private TreePath selectedPath;
    private Artifact root;

    public TreeUI() {
        setDropMode(DropMode.ON_OR_INSERT);
        setDragEnabled(true);
        setTransferHandler(new TreeTransferHandler());
        addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
            if (node == null) {
                return;
            } // nothing is selected

            Object nodeInfo = node.getUserObject();
            if (nodeInfo instanceof Artifact) {
                selectedPath = new TreePath(node.getPath());
                setSelectionPath(selectedPath);
                selection = node;
                ((Artifact) nodeInfo).update();

            }
        });
        setCellRenderer(new TreeRendererUI());
        setEditable(true);
        new TreePopupUI(this);
    }

    public void setup(Artifact art) {
    	this.root = art;
        DefaultMutableTreeNode lib = art.getTreeNode(); 
        DefaultTreeModel model = new DefaultTreeModel(lib);
        setModel(model);
        setSelectionPath(new TreePath(lib.getPath()));
        selection = lib;
        setVisible(true);
    }
    
    public Artifact root(){
    	return root;
    }
    
    public DefaultMutableTreeNode getSelection() {
      return selection;
     }

    public void update() {
        TreeNodeUI node = (TreeNodeUI) selectedPath.getLastPathComponent();
        ((DefaultTreeModel) this.getModel()).nodeChanged((TreeNode) node);
    }

    public void addToSel(DefaultMutableTreeNode child) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
        int lastIndex = parent.getChildCount();
        ((DefaultTreeModel) getModel()).insertNodeInto(child, parent, lastIndex);
        this.selection = child;
        selectedPath = new TreePath(child.getPath());
        ((DefaultTreeModel) this.getModel()).nodeStructureChanged(parent);

    }

    
    public TreePath getSelectLoc() {
        return selectedPath;
    }

    public void addToPath(TreePath path, DefaultMutableTreeNode child) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();
        int lastIndex = parent.getChildCount();
        ((DefaultTreeModel) getModel()).insertNodeInto(child, parent, lastIndex);
        this.selection = child;
        selectedPath = new TreePath(child.getPath());
        setSelectionPath(selectedPath);
        ((DefaultTreeModel) this.getModel()).nodeStructureChanged(parent);

    }

}
