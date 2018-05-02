package com.paranhaslett.toolbox.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.paranhaslett.toolbox.tools.Tool;
import com.paranhaslett.toolbox.tree.TreeUI;

public class EditorUI extends JFrame {
	private static final long serialVersionUID = 6198693420541435623L;
	private final JSplitPane splitPane;
	private final JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public EditorUI(String name) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EditorUI.class.getResource("/icons/book.png")));
		setTitle(name);
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
				//TODO Editor.getEd().library.saveLibrary();
			}
		});
		mnFile.add(mntmSave);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnPreferences = new JMenu("Preferences");
		mnEdit.add(mnPreferences);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO open dialog
			}
		});
		mnHelp.add(mntmAbout);
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
	
	public void setName(String name){
		setTitle(name);
	}

	public void updateTree(TreeUI tree) {
		scrollPane.setViewportView(tree);
	}

	public void updatePanel(Tool tool, List<String> data) {
		tool.populatePanel(data);
		splitPane.setRightComponent(tool);
	}

}