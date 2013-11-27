package com.paranhaslett.gamebook.model;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;

public class Page implements ModelItem {
	public String id;
	public ArrayList<Section> sections = new ArrayList<Section>();

	public String toString() {
		return id;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController("Page");
	}

}
