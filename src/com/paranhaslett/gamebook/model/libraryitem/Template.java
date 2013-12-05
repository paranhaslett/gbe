package com.paranhaslett.gamebook.model.libraryitem;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.ModelItem;

public class Template implements LibraryItem {
	public String title;
	public ModelItem modelItem;

	public String toString() {
		return title;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.TEMPLATE);
	}

}
