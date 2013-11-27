package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.BookIO;
import com.paranhaslett.gamebook.loadable.LibraryIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class LibraryController implements Controller {
	private Library library;
	public Loadable loader = new LibraryIO();
	private PanelUI panel = GameBookUI.getPanelUI();
	private Editor ed=Editor.getEd();

	@Override
	public void add(ModelItem item, ModelItem added) {
		//cant add a library
	}

	public void update(ModelItem item) {
		if (item instanceof Library) {
			Library library = (Library) item;
			panel.populatePanel(library);
			ed.editorUI.updatePanel(panel);
			//update the entire model
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		//The library label cannot be changed
	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof LibraryItem);
	}

  @Override
  public void setup(ModelItem modelItem) {
    //library is already setup cant create a new one
  }

}
