package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.TemplateIO;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.TemplateUI;

public class Template implements LibraryItem {
	public String title;
	public ArrayList<Item> items = new ArrayList<Item>();
	public static Loadable loadable = new TemplateIO();
	private PanelUI panel = TemplateUI.getPanelUI();
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
		if (item instanceof Template) {
			Template template = (Template) item;
			panel.populatePanel(template);
			ed.editorUI.updatePanel(panel);
		}
	}

	public boolean isDropOn(Item mi) {
		return true;
	}

	@Override
	public void add(Item to) {
		if (to instanceof Book) {
			items.add((Book) to);
			ed.tree.addToSel(to);
			((Book)to).update();

		}
		
	}

	@Override
	public void setup() {
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
