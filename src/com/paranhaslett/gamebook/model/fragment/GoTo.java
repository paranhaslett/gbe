package com.paranhaslett.gamebook.model.fragment;

import javax.swing.Icon;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.ast.Null;
import com.paranhaslett.gamebook.loadable.GoToIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.ui.panel.GoToUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class GoTo implements Fragment {
	public String to;
	public String text;
	public Null ast;
	
	public static final Loadable loadable = new GoToIO();
	private final PanelUI panel = GoToUI.getPanelUI();
	private final Editor ed = Editor.getEd();

	public void update(Item item) {
		if (item instanceof GoTo) {
			ed.editorUI.updatePanel(panel, item);
		}
	}

	@Override
	public void setup() {

	}

	public String toString() {
		return to;
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


	@Override
	public boolean isDropOn(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

private static Icon icon;
  
  @Override
  public Icon icon(){
    if (icon == null){
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/goto.png");
    }
    return icon;
  }
}