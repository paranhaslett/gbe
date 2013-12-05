package com.paranhaslett.gamebook;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.filechooser.FileFilter;

import com.paranhaslett.gamebook.controller.ChanceController;
import com.paranhaslett.gamebook.controller.ChoiceController;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.controller.LibraryController;
import com.paranhaslett.gamebook.controller.TextController;
import com.paranhaslett.gamebook.controller.BookController;
import com.paranhaslett.gamebook.controller.GotoController;
import com.paranhaslett.gamebook.controller.IfController;
import com.paranhaslett.gamebook.controller.PageController;
import com.paranhaslett.gamebook.controller.SectionController;
import com.paranhaslett.gamebook.controller.SetController;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.ui.EditorUI;
import com.paranhaslett.gamebook.ui.FileChooserUI;
import com.paranhaslett.gamebook.ui.FileChooserUI.EmaFilter;
import com.paranhaslett.gamebook.ui.FileChooserUI.GameBookFilter;
import com.paranhaslett.gamebook.ui.tree.TreeUI;

public class Editor {
	private HashMap<Item, Controller> controllers = new HashMap<Item, Controller>();
	public Book book;
	public Library library;
	public LibraryController lc;
	private static Editor editor = null;
	public TreeUI tree;
	public EditorUI editorUI;
	public FileChooserUI fileChooser;

	public static enum Item {
		LIBRARY, SERIES, TEMPLATE, BOOK, SECTION, PAGE, TEXT, CHANCE, SET, CHOICE, IF, GOTO, OP
	}

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
		controllers.put(Item.BOOK, new BookController());
		controllers.put(Item.SECTION, new SectionController());
		controllers.put(Item.PAGE, new PageController());
		controllers.put(Item.TEXT, new TextController());
		controllers.put(Item.CHANCE, new ChanceController());
		controllers.put(Item.CHOICE, new ChoiceController());
		controllers.put(Item.SET, new SetController());
		controllers.put(Item.IF, new IfController());
		controllers.put(Item.GOTO, new GotoController());
		tree = new TreeUI();
		lc  = new LibraryController();
		fileChooser = new FileChooserUI();
		editorUI = new EditorUI();
		editorUI.setVisible(true);
		library = new Library();
		library.loadLibrary(editor);
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

	@Deprecated
	public void setupOldTree() {
		tree.book(book);
		editorUI.updateTree(tree);
	}
	
	public void setupLibraryTree() {
		tree.setup(library);
		editorUI.updateTree(tree);
	}

	public void update() {
		tree.update();
	}

	public Controller getController(Item key) {
		return controllers.get(key);
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
