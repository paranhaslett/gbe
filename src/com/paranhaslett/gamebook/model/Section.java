package com.paranhaslett.gamebook.model;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.fragment.Goto;

public class Section implements ModelContainer {

	public String title;
	public String id;
	public Goto gotoid;
	public ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Fragment> getFragments() {
		return fragments;
	}

	public String toString() {
		if (title != null) {
			return title;
		}
		return "" + id;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.SECTION);
	}
}
