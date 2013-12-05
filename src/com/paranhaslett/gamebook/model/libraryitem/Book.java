package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.LibraryItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;

public class Book implements LibraryItem {
	public String title;
	public ArrayList<Page> pages = new ArrayList<Page>();
	public ArrayList<Section> sections = new ArrayList<Section>();
	public ArrayList<Section> freeSections = new ArrayList<Section>();

	public void setName(String name) {
		this.title = name;
	}

	public String toString() {
		return title;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.BOOK);
	}

}
