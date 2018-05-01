package com.paranhaslett.gamebook.ui.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.paranhaslett.gamebook.model.Item;

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
		if (nodeInfo instanceof Item){
		  Item item = (Item) nodeInfo;
		  setIcon(item.icon());
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
