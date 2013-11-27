package com.paranhaslett.gamebook.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.LibraryController;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.tree.TreeUI;
import java.awt.Toolkit;

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

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryController lc = (LibraryController)Editor.getEd().lc;
				lc.createBook(Editor.getEd());
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryController lc = (LibraryController)Editor.getEd().lc;
				lc.loadBook(Editor.getEd());
			}
		});
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmGamebook = new JMenuItem("Gamebook");
		mnNew.add(mntmGamebook);
		
		JMenuItem mntmSeries = new JMenuItem("Series");
		mnNew.add(mntmSeries);
		
		JMenuItem mntmTemplate = new JMenuItem("Template");
		mnNew.add(mntmTemplate);
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryController lc = (LibraryController)Editor.getEd().lc;
				lc.saveBook(Editor.getEd());
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

	public void updatePanel(PanelUI panel) {
		splitPane.setRightComponent(panel);
	}

}
