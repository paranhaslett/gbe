package com.paranhaslett.gamebook.model;

import java.io.File;
import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.ui.panel.LibraryUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Library implements ModelItem, com.paranhaslett.gamebook.controller.Controller {
	public Loadable loadable = new LibraryIO();
	private PanelUI panel = LibraryUI.getPanelUI();
	private Editor ed = Editor.getEd();
	public ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();
	public Loader loader = new XMLLoader();

	public String toString() {
		return "Gamebook Library";
	}

	@Override
	public Controller getController() {
		return this;
	}
	
	@Override
	public void add(ModelItem item, ModelItem added) {
		// cant add a library
	}

	public void update(ModelItem item) {
		if (item instanceof Library) {
			Library library = (Library) item;
			panel.populatePanel(library);
			ed.editorUI.updatePanel(panel);
			// update the entire model
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		// The library label cannot be changed
	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof LibraryItem);
	}

	@Override
	public void setup(ModelItem modelItem) {
		// library is already setup cant create a new one
	}
	
	public void createBook(Editor editor) {
		editor.book = new Book();
		editor.book.title = "New";
		editor.getController(Item.BOOK).update(editor.book);
		editor.setupOldTree();
	}

	public void loadLibrary(Editor editor) {
		File file = new File ("./library.xml");
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				editor.library = loader.loadLibrary(file);
			} 
		} 
		update(editor.library);
		editor.setupLibraryTree();
	}	

	public void saveLibrary(Editor editor) {
		File file = editor.fileChooser.loadGameBook(editor.editorUI);
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				loader.save(editor.book, file);
			} else {
				throw new RuntimeException(
						"Bad Programmer: File chooser selected wrong file type");
			}
		}
	}
}
