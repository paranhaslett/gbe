package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SeriesIO;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SeriesUI;

public class Series implements LibraryItem,  Controller {
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

	@Override
	@Deprecated
	public Controller getController() {
		return this;
	}

	@Override
	public void add(ModelItem item, ModelItem added) {
		if (item instanceof Series) {
			Series gameBook = (Series) item;
			if (added instanceof Book) {
				gameBook.books.add((Book) added);
				ed.tree.addToSel(added);
				((Book)added).update();

			}
		}
	}
	
	public void update(){
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
	}

	public void update(ModelItem item) {
		if (item instanceof Series) {
			Series series = (Series) item;
			panel.populatePanel(series);
			ed.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		if (item instanceof Series) {
			Series series = (Series) item;
			series.title = newLabel;
			panel.populatePanel(series);
			ed.editorUI.updatePanel(panel);
		}

	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof Book);
	}

	@Override
	public void setup(ModelItem modelItem) {
		Series series = (Series) modelItem;
		series.title = "New";
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, series);
	}


}
