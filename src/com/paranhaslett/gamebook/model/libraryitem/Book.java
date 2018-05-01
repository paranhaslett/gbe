package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.BookIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PageUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SectionUI;

public class Book implements Item {
  
	public String title;
	public final ArrayList<Page> pages = new ArrayList<>();
	public ArrayList<Section> sections = new ArrayList<>();
	public final ArrayList<Section> freeSections = new ArrayList<>();
	public static final Loadable loadable = new BookIO();
	private final PanelUI panel = GameBookUI.getPanelUI();
	private final Editor ed = Editor.getEd();

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
				ed.editorUI.updatePanel(PageUI.getPanelUI(), added);
			}
			if (added instanceof Section) {
				freeSections.add((Section) added);
				ed.tree.addToSel(added);
				ed.editorUI.updatePanel(SectionUI.getPanelUI(), added);
			}
	}

	@Override
	public void changeMainLabel(String newLabel) {
		title = newLabel;
		ed.editorUI.updatePanel(panel, this);	
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
		//TODO ensure this is unique (add anumber perhaps
		return title;
	}

	@Override
	public void update(){
		ed.editorUI.updatePanel(panel, this);
	}

	private static Icon icon;
	
	@Override
	public Icon icon(){
	  if (icon == null){
	    icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/gamebook.png");
	  }
	  return icon;
	}
}
