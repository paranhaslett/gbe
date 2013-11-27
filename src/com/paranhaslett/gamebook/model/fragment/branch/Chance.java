package com.paranhaslett.gamebook.model.fragment.branch;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.ModelContainer;

public class Chance implements ModelContainer, Fragment {
	String text;
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	public String toString() {
		return text;
	}

	public ArrayList<Fragment> getFragments() {
		return fragments;
	}

	@Override
	public Controller getController() {
		return Editor.getEd().getController(Item.CHANCE);
	}

}
