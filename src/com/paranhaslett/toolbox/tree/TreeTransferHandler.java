package com.paranhaslett.toolbox.tree;

import com.paranhaslett.toolbox.model.Artifact;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.List;

class TreeTransferHandler extends TransferHandler {
    private static final long serialVersionUID = -9199992278240380840L;
    private final DataFlavor[] flavors = new DataFlavor[1];
    private DataFlavor nodesFlavor;
    private DefaultMutableTreeNode[] nodesToRemove;

    public TreeTransferHandler() {
        try {
            String mimeType = DataFlavor.javaJVMLocalObjectMimeType
                    + ";class=\""
                    + Artifact[].class
                    .getName() + "\"";
            nodesFlavor = new DataFlavor(mimeType);
            flavors[0] = nodesFlavor;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound: " + e.getMessage());
        }
    }

    public boolean canImport(TransferHandler.TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        support.setShowDropLocation(true);
        if (!support.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }
        // TODO get all the children of the selected node if not already
        // selected
        // as it is a bit of a bugger having to select them manually

        // Do not allow a drop on the drag source selections.
        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        JTree tree = (JTree) support.getComponent();
        int dropRow = tree.getRowForPath(dl.getPath());
        int[] selRows = tree.getSelectionRows();
        if (selRows == null) {
            return false;
        }
        for (int selRow : selRows) {
            if (selRow == dropRow) {
                return false;
            }
        }
        //TODO
        DefaultMutableTreeNode sourceRoot = (DefaultMutableTreeNode) tree.getPathForRow(selRows[0])
                .getLastPathComponent();
        DefaultMutableTreeNode targetRoot = (DefaultMutableTreeNode) dl.getPath()
                .getLastPathComponent();
        Artifact sourceComponent = (Artifact) sourceRoot.getUserObject();
        Artifact targetComponent = (Artifact) targetRoot.getUserObject();
        System.out.println(sourceComponent + " x " + targetComponent);
        if (!targetComponent.isDropOn(sourceComponent)) {
            return false;
        }
        // Do not allow MOVE-action drops if a non-leaf node is
        // selected unless all of its children are also selected.
        int action = support.getDropAction();

        //TODO	action = LINK;

        if (action == MOVE) {
            return haveCompleteNode(tree);
        }
        // Do not allow a non-leaf node to be copied to a level
        // which is less than its source level.
        TreePath dest = dl.getPath();
        DefaultMutableTreeNode target = (DefaultMutableTreeNode) dest.getLastPathComponent();
        TreePath path = tree.getPathForRow(selRows[0]);
        DefaultMutableTreeNode firstNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        return firstNode.getChildCount() <= 0 || target.getLevel() >= firstNode.getLevel();
    }

    private boolean haveCompleteNode(JTree tree) {
        int[] selRows = tree.getSelectionRows();
        if (selRows == null) {
            return false;
        }
        TreePath path = tree.getPathForRow(selRows[0]);
        DefaultMutableTreeNode first = (DefaultMutableTreeNode) path.getLastPathComponent();
        int childCount = first.getChildCount();
        // first has children and no children are selected.
        if (childCount > 0 && selRows.length == 1)
            return false;
        // first may have children.
        for (int i = 1; i < selRows.length; i++) {
            path = tree.getPathForRow(selRows[i]);
            DefaultMutableTreeNode next = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (first.isNodeChild(next)) {
                // Found a child of first.
                if (childCount > selRows.length - 1) {
                    // Not all children of first are selected.
                    return false;
                }
            }
        }
        return true;
    }

    protected Transferable createTransferable(JComponent c) {
        JTree tree = (JTree) c;
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            // Make up a node array of copies for transfer and
            // another for/of the nodes that will be removed in
            // exportDone after a successful drop.
            List<DefaultMutableTreeNode> copies = new ArrayList<>();
            List<DefaultMutableTreeNode> toRemove = new ArrayList<>();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) paths[0].getLastPathComponent();
            DefaultMutableTreeNode copy = copy(node);
            copies.add(copy);
            toRemove.add(node);
            for (int i = 1; i < paths.length; i++) {
                DefaultMutableTreeNode next = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                // Do not allow higher level nodes to be added to list.
                if (next.getLevel() < node.getLevel()) {
                    break;
                } else if (next.getLevel() > node.getLevel()) { // child node
                    copy.add(copy(next));
                    // node already contains child
                } else { // sibling
                    copies.add(copy(next));
                    toRemove.add(next);
                }
            }
            DefaultMutableTreeNode[] nodes = copies.toArray(new DefaultMutableTreeNode[0]);
            nodesToRemove = toRemove.toArray(new DefaultMutableTreeNode[0]);
            return new NodesTransferable(nodes);
        }
        return null;
    }

    /**
     * Defensive copy used in createTransferable.
     */
    private DefaultMutableTreeNode copy(DefaultMutableTreeNode node) {
        return (DefaultMutableTreeNode) (node.clone());
    }

    protected void exportDone(JComponent source, Transferable data, int action) {
        if ((action & MOVE) == MOVE) {
            JTree tree = (JTree) source;
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            // Remove nodes saved in nodesToRemove in createTransferable.
            for (DefaultMutableTreeNode nodeToRemove : nodesToRemove) {
                model.removeNodeFromParent(nodeToRemove);
            }
        }
    }

    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    public boolean importData(TransferHandler.TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }
        // Extract transfer data.
        DefaultMutableTreeNode[] nodes = null;
        try {
            Transferable t = support.getTransferable();
            nodes = (DefaultMutableTreeNode[]) t.getTransferData(nodesFlavor);
        } catch (UnsupportedFlavorException ufe) {
            System.out.println("UnsupportedFlavor: " + ufe.getMessage());
        } catch (java.io.IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        }
        // Get drop location info.
        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        int childIndex = dl.getChildIndex();
        TreePath dest = dl.getPath();
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) dest.getLastPathComponent();
        JTree tree = (JTree) support.getComponent();
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        // Configure for drop mode.
        int index = childIndex; // DropMode.INSERT
        if (childIndex == -1) { // DropMode.ON
            index = parent.getChildCount();
        }
        if (nodes == null) {
            return false;
        }
        // Add data to model.
        for (DefaultMutableTreeNode node : nodes) {
            model.insertNodeInto(node, parent, index++);
        }
        return true;
    }

    public String toString() {
        return getClass().getName();
    }

    class NodesTransferable implements Transferable {
        final DefaultMutableTreeNode[] nodes;

        NodesTransferable(DefaultMutableTreeNode[] nodes2) {
            this.nodes = nodes2;
        }

        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor))
                throw new UnsupportedFlavorException(flavor);
            return nodes;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return nodesFlavor.equals(flavor);
        }
    }
}
