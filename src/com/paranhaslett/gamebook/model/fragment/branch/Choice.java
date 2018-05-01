package com.paranhaslett.gamebook.model.fragment.branch;

import java.util.ArrayList;

import javax.swing.Icon;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.ChoiceIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.ModelContainer;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.TextUI;

public class Choice implements ModelContainer, Fragment {
	private String text;
	private final ArrayList<Fragment> fragments = new ArrayList<>();
	
	public static final Loadable loadable = new ChoiceIO();
	private final PanelUI panel = TextUI.getPanelUI();
	private final Editor ed = Editor.getEd();

	@Override
	public boolean isDropOn(Item mi) {
		// TODO Auto-generated method stub
		return false;
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
		ed.editorUI.updatePanel(panel, this);
		
	}

	@Override
	public void changeMainLabel(String newLabel) {
		// TODO Auto-generated method stub
		
	}

private static Icon icon;
  
  @Override
  public Icon icon(){
    if (icon == null){
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/choice.png");
    }
    return icon;
  }
}
