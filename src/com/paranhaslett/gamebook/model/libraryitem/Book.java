package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.BookIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Book implements LibraryItem {
	public String title;
	public ArrayList<Page> pages = new ArrayList<Page>();
	public ArrayList<Section> sections = new ArrayList<Section>();
	public ArrayList<Section> freeSections = new ArrayList<Section>();
	public static Loadable loadable = new BookIO();
	private PanelUI panel = GameBookUI.getPanelUI();
	private Editor ed = Editor.getEd();

	@Override
	public void add(Item added) {
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

	@Override
	public void changeMainLabel(String newLabel) {
		title = newLabel;
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);	
	}

	
	@Override
	public boolean isDropOn(Item mi) {
		return (mi instanceof Page || mi instanceof Section);
	}
	
	public void setName(String name) {
		this.title = name;
	}
	
	@Override
	public void setup() {
		//TODO: Get setup from current Book template copy it across
		title = "New";
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, this);
	}


	public String toString() {
		return title;
	}

	@Override
	public void update(){
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
	}
}
