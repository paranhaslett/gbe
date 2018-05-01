package com.paranhaslett.gamebook.model.fragment.branch;

import java.util.ArrayList;

import javax.swing.Icon;

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
	public final ArrayList<Fragment> trueBranch = new ArrayList<>();

	public static final Loadable loadable = new IfIO();
	private final PanelUI panel = IfUI.getPanelUI();
	private final Editor ed = Editor.getEd();

	@Override
	public boolean isDropOn(Item mi) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return text;
	}

	public ArrayList<Fragment> getFragments() {
		return trueBranch;
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
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/if.png");
    }
    return icon;
  }
}
