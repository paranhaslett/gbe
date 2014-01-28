package com.paranhaslett.gamebook;

import java.awt.EventQueue;

import javax.swing.filechooser.FileFilter;

import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.ui.EditorUI;
import com.paranhaslett.gamebook.ui.FileChooserUI;
import com.paranhaslett.gamebook.ui.FileChooserUI.EmaFilter;
import com.paranhaslett.gamebook.ui.FileChooserUI.GameBookFilter;
import com.paranhaslett.gamebook.ui.tree.TreeUI;

public class Editor {
	public Library library;
	private static Editor editor = null;
	public TreeUI tree;
	public EditorUI editorUI;
	public FileChooserUI fileChooser;

	private Editor() {
	}

	public static Editor getEd() {
		if (editor == null) {
			editor = new Editor();
			editor.init();

		}
		return editor;
	}

	private void init() {
		tree = new TreeUI();
		fileChooser = new FileChooserUI();
		editorUI = new EditorUI();
		editorUI.setVisible(true);
		library = new Library();
		library.setup(editor);
	}

	public Loader getLoader() {
		FileFilter filt = fileChooser.getFileFilter();
		Loader loader = null;
		if (filt instanceof GameBookFilter) {
			loader = Loadable.xmlLoader;
		}
		if (filt instanceof EmaFilter) {
			loader = Loadable.emaLoader;
		}
		return loader;
	}
	
	public void setupLibraryTree() {
		tree.setup(library);
		editorUI.updateTree(tree);
	}

	public void update() {
		tree.update();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getEd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
