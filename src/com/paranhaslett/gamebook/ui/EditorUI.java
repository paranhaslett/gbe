package com.paranhaslett.gamebook.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.tree.TreeUI;

public class EditorUI extends JFrame {
	private static final long serialVersionUID = 6198693420541435623L;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public EditorUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EditorUI.class.getResource("/icons/book.png")));
		setTitle("Game Book Editor");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(EditorUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editor.getEd().library.saveLibrary();
			}
		});
		mnFile.add(mntmSave);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnPreferences = new JMenu("Preferences");
		mnEdit.add(mnPreferences);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		contentPane.add(splitPane, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
	}

	public void updateTree(TreeUI tree) {
		scrollPane.setViewportView(tree);
	}

	public void updatePanel(PanelUI panel, Item item) {
		panel.populatePanel(item);
		splitPane.setRightComponent(panel);
	}

}
