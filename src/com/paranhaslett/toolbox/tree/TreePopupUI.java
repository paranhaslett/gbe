package com.paranhaslett.toolbox.tree;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.paranhaslett.toolbox.model.Artifact;

class TreePopupUI extends JPopupMenu {
	private static final long serialVersionUID = -3362291340967409194L;

	public TreePopupUI(final TreeUI tree) {
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                    .getLastSelectedPathComponent();
            if (node == null) {
                return;
            } // nothing is selected

            Object nodeInfo = node.getUserObject();
            if (nodeInfo instanceof Artifact) {
                Artifact item = (Artifact) nodeInfo;
                // TODO figure out how to specify items

            }

        });
		add(mntmAdd);

		JMenu mnAdd = new JMenu("Add");
		add(mnAdd);

		JMenuItem mntmPage = new JMenuItem("Page");
		mntmPage.addActionListener(e -> {
        });
		mnAdd.add(mntmPage);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		add(mntmDelete);

		addPopup(tree, this);
	}

	private static void addPopup(Component component, final TreePopupUI popup) {
		final TreeUI tree = (TreeUI) component;
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					Point loc = e.getPoint();
					TreePath path = tree.getPathForLocation(loc.x, loc.y);
					// System.out.printf("path = %s%n", path);
					if (path == null) {
						e.consume();
						return;
					}
					tree.setSelectionPath(path);
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				Point loc = e.getPoint();
				TreePath path = tree.getPathForLocation(loc.x, loc.y);
				System.out.printf("path = %s%n", path);
				if (path == null) {
					e.consume();
					return;
				}
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}
