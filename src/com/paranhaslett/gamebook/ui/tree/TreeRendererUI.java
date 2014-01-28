package com.paranhaslett.gamebook.ui.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;
import com.paranhaslett.gamebook.model.libraryitem.Template;

public class TreeRendererUI extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = -4221259711269183060L;
	Icon libraryIcon;
	Icon seriesIcon;
	Icon templateIcon;
	Icon gameBookIcon;
	Icon pageIcon;
	Icon sectionIcon;
	Icon descIcon;
	Icon choiceIcon;
	Icon chanceIcon;
	Icon gotoIcon;
	Icon setIcon;
	Icon ifIcon;
	Icon varIcon;
	Icon useIcon;

	public TreeRendererUI() {
		setTextSelectionColor(Color.white);
		setBackgroundSelectionColor(Color.blue);
		libraryIcon = createImageIcon("/icons/tree/library.png");
		seriesIcon = createImageIcon("/icons/tree/series.png");
		templateIcon = createImageIcon("/icons/tree/template.png");
		gameBookIcon = createImageIcon("/icons/tree/gamebook.png");
		pageIcon = createImageIcon("/icons/tree/page.png");
		sectionIcon = createImageIcon("/icons/tree/section.png");
		descIcon = createImageIcon("/icons/tree/desc.png");
		choiceIcon = createImageIcon("/icons/tree/choice.png");
		chanceIcon = createImageIcon("/icons/tree/chance.png");
		gotoIcon = createImageIcon("/icons/tree/goto.png");
		setIcon = createImageIcon("/icons/tree/set.png");
		ifIcon = createImageIcon("/icons/tree/if.png");
		varIcon = createImageIcon("/icons/tree/var.png");
		varIcon = createImageIcon("/icons/tree/var.png");

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		Object nodeInfo = node.getUserObject();
		if (nodeInfo instanceof Library) {
			setIcon(libraryIcon);
		}
		if (nodeInfo instanceof Series) {
			setIcon(seriesIcon);
		}
		if (nodeInfo instanceof Template) {
			setIcon(templateIcon);
		}
		if (nodeInfo instanceof Book) {
			setIcon(gameBookIcon);
		}
		if (nodeInfo instanceof Page) {
			setIcon(pageIcon);
		}
		if (nodeInfo instanceof Section) {
			setIcon(sectionIcon);
		}
		if (nodeInfo instanceof Text) {
			setIcon(descIcon);
		}

		if (nodeInfo instanceof Choice) {
			setIcon(choiceIcon);
		}

		if (nodeInfo instanceof Chance) {
			setIcon(chanceIcon);
		}

		if (nodeInfo instanceof Goto) {
			setIcon(gotoIcon);
		}

		if (nodeInfo instanceof If) {
			setIcon(ifIcon);
		}

		if (nodeInfo instanceof Set) {
			setIcon(setIcon);
		}

		return this;
	}

	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
