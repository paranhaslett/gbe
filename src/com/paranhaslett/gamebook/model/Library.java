package com.paranhaslett.gamebook.model;

import java.io.File;
import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Library implements ModelItem, com.paranhaslett.gamebook.controller.Controller {
	private Loadable loadable = new LibraryIO();
	private PanelUI panel = GameBookUI.getPanelUI();
	private Editor ed = Editor.getEd();
	private ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();
	private Loader loader;

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

	public void loadLibrary(Editor editor) {
		File file = new File ("./library.xml");
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				editor.library = loader.loadLibrary(file);
			} else {
				editor.library = new Library();	
			}
			update(editor.library);
			editor.setupTree();
		}
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
