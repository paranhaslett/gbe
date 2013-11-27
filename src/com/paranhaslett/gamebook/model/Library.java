package com.paranhaslett.gamebook.model;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;

public class Library implements ModelItem {
	public ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();
	
	public String toString() {
		return "Gamebook Library";
	}


	@Override
	public Controller getController() {
		return Editor.getEd().getController("Library");
	}
	
}
