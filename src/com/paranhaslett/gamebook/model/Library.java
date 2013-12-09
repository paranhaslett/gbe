package com.paranhaslett.gamebook.model;

import java.io.File;
import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.ui.panel.LibraryUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Library implements ModelItem, Controller, Item {
	public static Loadable loadable = new LibraryIO();
	private PanelUI panel = LibraryUI.getPanelUI();
	private Editor ed = Editor.getEd();
	public ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();
	public static Loader loader = new XMLLoader();

	@Override
	public void add (ModelItem item){
		if (item instanceof LibraryItem){
			items.add((LibraryItem)item);
		}
	}

	@Override
	public void add(ModelItem item, ModelItem added) {
		if (item instanceof LibraryItem){
			items.add((LibraryItem)item);
		}
	}
	
	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		// The library label cannot be changed
	}
	
	@Override
	@Deprecated
	public Controller getController() {
		return this;
	}
	
    @Override
	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof LibraryItem);
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
		File file = new File ("./library.xml");
		if (file != null) {
			Loader loader = Editor.getEd().getLoader();
			if (loader != null) {
				editor.library = loader.loadLibrary(file);
			} 
		} 
		editor.library.update();
		editor.setupLibraryTree();
	}

	@Override
	public void setup(ModelItem modelItem) {
		// library is already setup cant create a new one
	}

	@Override
	public String toString() {
		return "Gamebook Library";
	}	

	public void update(ModelItem item) {
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
	
}
