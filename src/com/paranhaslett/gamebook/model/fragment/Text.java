package com.paranhaslett.gamebook.model.fragment;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.TextIO;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.TextUI;

public class Text implements Fragment {
	public static Loadable loadable = new TextIO();
	private PanelUI panel = TextUI.getPanelUI();
	private Editor ed = Editor.getEd();
	
	public String text;

	public String toString() {
		return "Text";
	}


	@Override
	public boolean isDropOn(Item mi) {
		// TODO Auto-generated method stub
		return false;
	}

	public void update(Item item) {
		if (item instanceof Text) {
			Text set = (Text) item;
			panel.populatePanel(set);
			ed.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void add(Item to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMainLabel(String newLabel) {
		// TODO Auto-generated method stub
		
	}

}
