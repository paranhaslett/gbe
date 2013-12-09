package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SeriesIO;
import com.paranhaslett.gamebook.loadable.TemplateIO;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SeriesUI;

public class Template implements LibraryItem,  Controller, Item {
	public String title;
	public ArrayList<ModelItem> items = new ArrayList<ModelItem>();
	public static Loadable loadable = new TemplateIO();
	private PanelUI panel = TemplateUI.getPanelUI();
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
		if (item instanceof Template) {
			Template gameBook = (Template) item;
			gameBook.add(added);
		}
	}
	
	public void update(){
		panel.populatePanel(this);
		ed.editorUI.updatePanel(panel);
	}

	public void update(ModelItem item) {
		if (item instanceof Template) {
			Template template = (Template) item;
			panel.populatePanel(template);
			ed.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		if (item instanceof Template) {
			Template template = (Template) item;
			template.changeMainLabel(newLabel);
		}

	}

	public boolean isDropOn(ModelItem mi) {
		return true;
	}

	@Override
	public void setup(ModelItem modelItem) {
		Template template = (Template) modelItem;
		template.setup(ed);
	}

	@Override
	public void add(ModelItem to) {
		if (to instanceof Book) {
			items.add((Book) to);
			ed.tree.addToSel(to);
			((Book)to).update();

		}
		
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
