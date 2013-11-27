package com.paranhaslett.gamebook.model.fragment;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.Fragment;

public class Goto implements Fragment {
	public String to;
	public String text;
	
	
	public String toString() {
		return to;
	}
	
	@Override
	public Controller getController() {
		return Editor.getEd().getController("Goto");
	}

}
