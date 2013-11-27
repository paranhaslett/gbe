package com.paranhaslett.gamebook.model;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;

public class Template implements LibraryItem {
	public String title;
	public ModelItem modelItem;
	
	public String toString() {
		return title;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController("Template");
	}
	
}
