package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SeriesIO;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SeriesUI;

public class Series implements LibraryItem {
	public String title;
	public ArrayList<Book> books = new ArrayList<Book>();
	public static Loadable loadable = new SeriesIO();
	private PanelUI panel = SeriesUI.getPanelUI();
	private Editor ed = Editor.getEd();

	public void setName(String name) {
		this.title = name;
	}

	public String toString() {
		return title;
	}

	
	public void update(){
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
	}

	public void update(Item item) {
		if (item instanceof Series) {
			Series series = (Series) item;
			panel.populatePanel(series);
			ed.editorUI.updatePanel(panel);
		}
	}

	public boolean isDropOn(Item mi) {
		return (mi instanceof Book);
	}

	@Override
	public void setup() {
		title = "New";
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, this);
	}

	@Override
	public void add(Item added) {
		if (added instanceof Book) {
			books.add((Book) added);
			ed.tree.addToSel(added);
			((Book)added).update();

		}
		
	}

	@Override
	public void changeMainLabel(String newLabel) {
		title = newLabel;
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
		
	}


}
