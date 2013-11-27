package com.paranhaslett.gamebook.model.fragment;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.Fragment;

public class Set implements Fragment {
	public String var;
	public String value;
	public String text;

	public String toString() {
		return text;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.SET);
	}

}
