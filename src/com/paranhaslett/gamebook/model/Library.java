package com.paranhaslett.gamebook.model;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.loader.Loader;

public class Library implements ModelItem {
	public ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();
	private Loader loader;

	public String toString() {
		return "Gamebook Library";
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.LIBRARY);
	}

}
