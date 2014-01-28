package com.paranhaslett.gamebook.model.fragment.branch;

import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.IfIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.ModelContainer;
import com.paranhaslett.gamebook.ui.panel.IfUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class If implements ModelContainer, Fragment, Item {
	public String lhs;
	public String op;
	public String rhs;
	public String text;
	public ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	public static Loadable loadable = new IfIO();
	private PanelUI panel = IfUI.getPanelUI();
	private Editor gc = Editor.getEd();

	@Override
	public boolean isDropOn(Item mi) {
		// TODO Auto-generated method stub
		return false;
	}

	public void update(Item item) {
		if (item instanceof If) {
			If ifItem = (If) item;
			panel.populatePanel(ifItem);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
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
