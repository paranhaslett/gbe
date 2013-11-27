package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.BookIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Book;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.ui.panel.GameBookUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class BookController implements Controller {
	public Loadable loader = new BookIO();
	private PanelUI panel = GameBookUI.getPanelUI();
	private Editor gc=Editor.getEd();

	@Override
	public void add(ModelItem item, ModelItem added) {
		if (item instanceof Book) {
			Book gameBook = (Book) item;
			if (added instanceof Book) {
				// merge into series
				// change to page controller
				// add newPage to parent of selection
			}
			if (added instanceof Page) {
				gameBook.pages.add((Page)added);
				gc.tree.addToSel(added);
				Controller controller = gc.getController("Page");
				controller.update(added);
				
			}
			if (added instanceof Section) {
				gameBook.freeSections.add((Section)added);
				gc.tree.addToSel(added);
				Controller controller = gc.getController("Section");
				controller.update(added);
			}
			
		}

	}

	public void update(ModelItem item) {
		if (item instanceof Book) {
			Book GameBook = (Book) item;
			panel.populatePanel(GameBook);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		if (item instanceof Book) {
			Book gameBook = (Book) item;
			gameBook.title = newLabel;
			panel.populatePanel(gameBook);
			gc.editorUI.updatePanel(panel);
		}

	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof Page || mi instanceof Section);
	}

  @Override
  public void setup(ModelItem modelItem) {
    Book gameBook = (Book) modelItem;
    gameBook.title = "New";
  }

}
