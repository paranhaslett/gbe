package com.paranhaslett.gamebook.model.fragment.branch;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.ChoiceIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.ModelContainer;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.TextUI;

public class Choice implements ModelContainer, Fragment {
	String text;
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	
	public static Loadable loadable = new ChoiceIO();
	private PanelUI panel = TextUI.getPanelUI();
	private Editor gc = Editor.getEd();

	@Override
	public boolean isDropOn(Item mi) {
		// TODO Auto-generated method stub
		return false;
	}

	public void update(Item item) {
		if (item instanceof Choice) {
			Text set = (Text) item;
			panel.populatePanel(set);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void setup() {

	}


	public String toString() {
		return text;
	}

	public ArrayList<Fragment> getFragments() {
		return fragments;
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
