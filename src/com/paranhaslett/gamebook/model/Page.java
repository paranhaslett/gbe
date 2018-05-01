package com.paranhaslett.gamebook.model;

import java.util.ArrayList;

import javax.swing.Icon;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.PageIO;
import com.paranhaslett.gamebook.ui.panel.PageUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class Page implements Item {
	public String id;
	public ArrayList<Section> sections = new ArrayList<Section>();

	public static Loadable loadable = new PageIO();
	private PanelUI panel = PageUI.getPanelUI();
	private Editor ed = Editor.getEd();
	private int pgnum = 1;
	

	public void update(Item item) {
		if (item instanceof Page) {
			ed.editorUI.updatePanel(panel, item);
		}
	}

	public boolean isDropOn(Item mi) {
		return (mi instanceof Section);
	}

	public String toString() {
		return id;
	}

	@Override
	public void add(Item to) {
		if (to instanceof Page) {
			// merge into series
			// change to page controller
			// add newPage to parent of selection
		}

		if (to instanceof Section) {
			sections.add((Section) to);
			ed.tree.addToSel(to);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeMainLabel(String newLabel) {
		id = newLabel;
		ed.editorUI.updatePanel(panel, this);

	}

	@Override
	public void setup() {
		id = "" + pgnum;
		pgnum++;
		
	}
	
	static Icon icon;
	
    @Override
	public Icon icon() {
		if (icon == null){
		      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/library.png");
		    }
		    return icon;
	}

  
}
