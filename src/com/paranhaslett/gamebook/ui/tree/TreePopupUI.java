package com.paranhaslett.gamebook.ui.tree;

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Section;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TreePopupUI extends JPopupMenu {
    private static final long serialVersionUID = -3362291340967409194L;

    public TreePopupUI(final TreeUI tree) {
        JMenuItem mntmAdd = new JMenuItem("Add");
        mntmAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
                if (node == null) {
                    return;
                } // nothing is selected

                Object nodeInfo = node.getUserObject();
                if (nodeInfo instanceof Item) {
                    Item item = (Item) nodeInfo;
                    // TODO figure out how to specify items
                    item.add(new Section());
                }

            }
        });
        add(mntmAdd);

        JMenu mnAdd = new JMenu("Add");
        add(mnAdd);

        JMenuItem mntmPage = new JMenuItem("Page");
        mntmPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
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
