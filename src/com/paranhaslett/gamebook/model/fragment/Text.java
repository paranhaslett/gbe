package com.paranhaslett.gamebook.model.fragment;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.Fragment;

public class Text implements Fragment {
	public String text;

	public String toString() {
		return "Text";
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.TEXT);
	}

}
