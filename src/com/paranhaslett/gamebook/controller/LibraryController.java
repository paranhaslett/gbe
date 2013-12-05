package com.paranhaslett.gamebook.controller;

import java.io.File;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Book;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class LibraryController implements Controller {
	public Loadable loader = new LibraryIO();
	private PanelUI panel = GameBookUI.getPanelUI();
	private Editor ed = Editor.getEd();

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
		// may need to add a book however
		createBook(Editor.getEd());
	}

	public void loadBook(Editor editor) {
		File file = Editor.getEd().fileChooser.loadGameBook(editor.editorUI);
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				editor.book = loader.loadBook(file);
				Editor.getEd().getController(Item.BOOK).update(editor.book);
				editor.setupOldTree();
			} else {
				createBook(editor);
			}
		}
	}

	public void saveBook(Editor editor) {
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

	public void createBook(Editor editor) {
		editor.book = new Book();
		editor.book.title = "New";
		editor.getController(Item.BOOK).update(editor.book);
		editor.setupOldTree();
	}

}
