package com.paranhaslett.toolbox.tree;

import javax.swing.DropMode;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.paranhaslett.toolbox.model.Artifact;

public class TreeUI extends JTree {
	private static final long serialVersionUID = -4252742793844024659L;
	protected DefaultMutableTreeNode selection;
	protected TreePath selectedPath;
	private TreeRendererUI treeRenderer = new TreeRendererUI();

	public TreeUI() {
		setDropMode(DropMode.ON_OR_INSERT);
		setDragEnabled(true);
		setTransferHandler(new TreeTransferHandler());
		setCellRenderer(treeRenderer);
		addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
				if (node == null) {
					return;
				} // nothing is selected

				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof Artifact) {
					selectedPath = new TreePath(node.getPath());
					setSelectionPath(selectedPath);
					selection = (DefaultMutableTreeNode) nodeInfo;
					((Artifact)selection.getUserObject()).update();

				}
			}

		});
		setEditable(true);
		new TreePopupUI(this);
	}

	public TreeRendererUI getTreeRenderer() {
		return treeRenderer;
	}

	
	public void setup(Artifact art) {
		DefaultMutableTreeNode lib = art.getTreeNode();
		DefaultTreeModel model = new DefaultTreeModel(lib);
		setModel(model);
		setSelectionPath(new TreePath(lib.getPath()));
		selection = lib;
		setVisible(true);
	}
	// public Item getSelection() {
	// return selection;
	// }

	public void update() {
		Artifact node = (Artifact) selectedPath.getLastPathComponent();
		((DefaultTreeModel) this.getModel()).nodeChanged((TreeNode) node);
	}

	public void addToSel(DefaultMutableTreeNode child) {
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
		int lastindex = parent.getChildCount();
		((DefaultTreeModel) getModel()).insertNodeInto(child, parent, lastindex);
		this.selection = child;
		selectedPath = new TreePath(child.getPath());
		((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) parent);

	}


	public TreePath getSelectLoc() {
		return selectedPath;
	}

	public void addToPath(TreePath path, DefaultMutableTreeNode child) {
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();
		int lastindex = parent.getChildCount();
		((DefaultTreeModel) getModel()).insertNodeInto(child, parent, lastindex);
		this.selection = child;
		selectedPath = new TreePath(child.getPath());
		setSelectionPath(selectedPath);
		((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) parent);

	}

}
