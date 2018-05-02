package com.paranhaslett.gamebook.model;

import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;
import com.paranhaslett.gamebook.model.libraryitem.Template;
import com.paranhaslett.gamebook.ui.panel.LibraryUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Library implements Item {
	public static final Loadable loadable = new LibraryIO();
	private static final PanelUI panel = LibraryUI.getPanelUI();
	private Editor ed = Editor.getEd();
	public final ArrayList<Item> items = new ArrayList<>();
	public static Loader loader = new XMLLoader();

	@Override
	public void add (Item item){
	       if (isDropOn(item)){
			items.add(item);
	       }
	}


	public void saveLibrary() {
		File file = new File ("./library-save.xml");
		Loader loader = Editor.getEd().getLoader();
		if (loader != null) {
            loader.save(file, Editor.getEd().library);
        } else {
            throw new RuntimeException(
                    "Bad Programmer: File chooser selected wrong file type");
        }
	}

	public void setup(Editor editor) {
		ed = editor;
		setup();
	}
	
	public void setup() {
		File file = new File ("./library.xml");
		Loader loader = Editor.getEd().getLoader();
		if (loader != null) {
            ed.library = new Library();
            loader.load(file, ed.library);
        }
		ed.library.update();
		ed.setupLibraryTree();
	}

	@Override
	public String toString() {
		return "Game Book Library";
	}	
	
	@Override
	public void update(){
		
		ed.editorUI.updatePanel(panel, this);
		//ed.setupLibraryTree();
	}

	@Override
	public void changeMainLabel(String newLabel) {
		// The library label cannot be changed
		
	}

	@Override
	public boolean isDropOn(Item item) {
		return (item instanceof Book || item instanceof Series || item instanceof Template);
	}

	private static Icon icon;
	
	  @Override
	public Icon icon() {
		if (icon == null){
		      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/library.png");
		    }
		    return icon;
	}
	
  
}
