package com.paranhaslett.gamebook.ui.tree;

import javax.swing.DropMode;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Book;
import com.paranhaslett.gamebook.model.ModelContainer;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;

public class TreeUI extends JTree {
	private static final long serialVersionUID = -4252742793844024659L;
	private ModelItem selection;
	private TreePath selectedPath;

	public TreeUI() {
		setDropMode(DropMode.ON_OR_INSERT);
		setDragEnabled(true);
		setTransferHandler(new TreeTransferHandler());
		setCellRenderer(new TreeRendererUI());
		addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreeNodeUI node = (TreeNodeUI) getLastSelectedPathComponent();
				if (node == null) {
					return;
				} // nothing is selected

				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof ModelItem) {
					selectedPath = new TreePath(node.getPath());
					setSelectionPath(selectedPath);
					selection = (ModelItem) nodeInfo;
					Controller controller = selection.getController();
					controller.update(selection);

				}
			}

		});
		setEditable(true);
		new TreePopupUI(this);
	}

	public void setup(Book gameBook) {
		TreeNodeUI root = new TreeNodeUI(gameBook);

		for (Page page : gameBook.pages) {
			TreeNodeUI pageNode = new TreeNodeUI(page);
			for (Section section : page.sections) {
				TreeNodeUI sectionNode = fragments(section);
				if (section.gotoid != null) {
					TreeNodeUI gotoNode = new TreeNodeUI(section.gotoid);
					sectionNode.add(gotoNode);
				}
				pageNode.add(sectionNode);
			}
			root.add(pageNode);
		}
		for (Section section : gameBook.freeSections) {
			TreeNodeUI sectionNode = fragments(section);
			if (section.gotoid != null) {
				TreeNodeUI gotoNode = new TreeNodeUI(section.gotoid);
				sectionNode.add(gotoNode);
			}
			root.add(sectionNode);
		}
		setModel(new DefaultTreeModel(root));
		selectedPath = new TreePath(root.getPath());
		setSelectionPath(selectedPath);
		selection = gameBook;
	}

	private TreeNodeUI fragments(ModelContainer section) {
		TreeNodeUI sectionNode = new TreeNodeUI(section);
		for (Fragment frag : section.getFragments()) {
			if (frag instanceof ModelContainer) {
				sectionNode.add(fragments((ModelContainer) frag));
			} else {
				TreeNodeUI fragNode = new TreeNodeUI(frag);
				sectionNode.add(fragNode);
			}
		}
		return sectionNode;
	}

	public ModelItem getSelection() {
		return selection;
	}

	public void update() {
		TreeNodeUI node = (TreeNodeUI) selectedPath.getLastPathComponent();
		((DefaultTreeModel) this.getModel()).nodeChanged((TreeNode) node);
	}

	public void addToSel(ModelItem childValue) {
		TreeNodeUI parent = (TreeNodeUI) selectedPath.getLastPathComponent();
		TreeNodeUI child = new TreeNodeUI(childValue);
		int lastindex = parent.getChildCount();
		((DefaultTreeModel) getModel())
				.insertNodeInto(child, parent, lastindex);
		this.selection = childValue;
		selectedPath = new TreePath(child.getPath());
		((DefaultTreeModel) this.getModel())
				.nodeStructureChanged((TreeNode) parent);

	}

	public TreePath getSelectLoc() {
		return selectedPath;
	}

	public void addToPath(TreePath path, ModelItem childValue) {
		TreeNodeUI parent = (TreeNodeUI) path.getLastPathComponent();
		TreeNodeUI child = new TreeNodeUI(childValue);
		int lastindex = parent.getChildCount();
		((DefaultTreeModel) getModel())
				.insertNodeInto(child, parent, lastindex);
		this.selection = childValue;
		selectedPath = new TreePath(child.getPath());
		setSelectionPath(selectedPath);
		((DefaultTreeModel) this.getModel())
				.nodeStructureChanged((TreeNode) parent);

	}

}
