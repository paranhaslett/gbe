package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.PageIO;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.ui.panel.PageUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class PageController implements Controller {
	public Loadable loader = new PageIO();
	private PanelUI panel = PageUI.getPanelUI();
	private Editor gc=Editor.getEd();
	private int pgnum = 1;

	@Override
	public void add(ModelItem item, ModelItem added) {
		if (item instanceof Page) {
			Page page = (Page) item;
			if (added instanceof Page) {
				// merge into series
				// change to page controller
				// add newPage to parent of selection
			}
			
			if (added instanceof Section) {
				page.sections.add((Section)added);
				gc.tree.addToSel(added);
			}
		}

	}

	public void update(ModelItem item) {
		if (item instanceof Page) {
			Page page = (Page) item;
			panel.populatePanel(page);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		if (item instanceof Page) {
			Page page = (Page) item;
			page.id = newLabel;
			panel.populatePanel(page);
			gc.editorUI.updatePanel(panel);
		}

	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof Section);
	}

  @Override
  public void setup(ModelItem modelItem) {
    Page section =(Page) modelItem;
    section.id = "" + pgnum;
    
    pgnum ++;

  }
	
}
