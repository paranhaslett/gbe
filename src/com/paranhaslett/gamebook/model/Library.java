package com.paranhaslett.gamebook.model;

import java.io.File;
import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.ui.panel.LibraryUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Library implements Item {
	public static Loadable loadable = new LibraryIO();
	private static PanelUI panel = LibraryUI.getPanelUI();
	private Editor ed = Editor.getEd();
	public ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();
	public static Loader loader = new XMLLoader();

	@Override
	public void add (Item item){
		if (item instanceof LibraryItem){
			items.add((LibraryItem)item);
		}
	}


	public void saveLibrary(Editor editor) {
		File file = new File ("./library.xml");
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				loader.save(editor.library, file);
			} else {
				throw new RuntimeException(
						"Bad Programmer: File chooser selected wrong file type");
			}
		}
	}

	public void setup(Editor editor) {
		ed = editor;
		setup();
	}
	
	public void setup() {
		File file = new File ("./library.xml");
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				ed.library = loader.loadLibrary(file);
			} 
		} 
		ed.library.update();
		ed.setupLibraryTree();
	}

	@Override
	public String toString() {
		return "Gamebook Library";
	}	

	@Deprecated
	public void update(Item item) {
		if (item instanceof Library) {
			Library library = (Library) item;
			library.update();
		}
	}
	
	@Override
	public void update(){
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
		//ed.setupLibraryTree();
	}

	@Override
	public void changeMainLabel(String newLabel) {
		// The library label cannot be changed
		
	}

	@Override
	public boolean isDropOn(Item item) {
		return item instanceof LibraryItem;
	}
	
}
