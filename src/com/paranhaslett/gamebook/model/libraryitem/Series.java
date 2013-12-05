package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.LibraryItem;

public class Series implements LibraryItem {
	public String title;
	public ArrayList<Book> pages = new ArrayList<Book>();

	public String toString() {
		return title;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.SERIES);
	}

}
