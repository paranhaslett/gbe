package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loadable.BookIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Book implements LibraryItem,  Controller, Item {
	public String title;
	public ArrayList<Page> pages = new ArrayList<Page>();
	public ArrayList<Section> sections = new ArrayList<Section>();
	public ArrayList<Section> freeSections = new ArrayList<Section>();
	public static Loadable loadable = new BookIO();
	private PanelUI panel = GameBookUI.getPanelUI();
	private Editor ed = Editor.getEd();

	public void setName(String name) {
		this.title = name;
	}

	public String toString() {
		return title;
	}

	@Override
	@Deprecated
	public Controller getController() {
		return this;
	}

	@Override
	public void add(ModelItem item, ModelItem added) {
		if (item instanceof Book) {
			Book gameBook = (Book) item;
			gameBook.add(added);

		}

	}
	
	public void add(ModelItem added) {
			if (added instanceof Book) {
				// merge into series
				// change to page controller
				// add newPage to parent of selection
			}
			if (added instanceof Page) {
				pages.add((Page) added);
				ed.tree.addToSel(added);
				((Page)added).update();

			}
			if (added instanceof Section) {
				freeSections.add((Section) added);
				ed.tree.addToSel(added);
				
				((Section)added).update();
			}
	}
	
	public void update(){
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
	}

	public void update(ModelItem item) {
		if (item instanceof Book) {
			Book GameBook = (Book) item;
			panel.populatePanel(GameBook);
			ed.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		if (item instanceof Book) {
			Book gameBook = (Book) item;
			gameBook.changeMainLabel(newLabel);
		}

	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof Page || mi instanceof Section);
	}

	@Override
	public void setup(ModelItem modelItem) {
		Book gameBook = (Book) modelItem;
		gameBook.setup(ed);
	}

	@Override
	public void setup(Editor ed) {
		title = "New";
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, this);
	}

	@Override
	public void changeMainLabel(String newLabel) {
		title = newLabel;
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);	
	}


}
