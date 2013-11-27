package com.paranhaslett.gamebook.model.fragment.branch;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.ModelContainer;

public class If implements ModelContainer, Fragment{
	public String lhs;
	public String op;
	public String rhs;
	public String text;
	public ArrayList<Fragment> fragments = new ArrayList<Fragment>();


	public String toString() {
		return text;
	}
	
	public ArrayList<Fragment> getFragments() {
		return fragments;
	}

	
	@Override
	public Controller getController() {
		return Editor.getEd().getController("If");
	}
}
