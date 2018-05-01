package com.paranhaslett.gamebook.model.fragment;

import javax.swing.Icon;

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

	@Override
	public void setup() {

	}

	@Override
	public void add(Item to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		ed.editorUI.updatePanel(panel, this);
	}

static Icon icon;
  
  @Override
  public Icon icon(){
    if (icon == null){
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/desc.png");
    }
    return icon;
  }
	
	@Override
	public void changeMainLabel(String newLabel) {
		// TODO Auto-generated method stub

	}

}
