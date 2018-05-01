package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.TemplateIO;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.TemplateUI;

public class Template implements Item {
	public String title;
	public ArrayList<Item> items = new ArrayList<Item>();
	public static Loadable loadable = new TemplateIO();
	private PanelUI panel = TemplateUI.getPanelUI();
	private Editor ed = Editor.getEd();

	public void setName(String name) {
		this.title = name;
	}

	public String toString() {
		return title;
	}
	
	public void update(){
		ed.editorUI.updatePanel(panel, this);
	}

	public boolean isDropOn(Item mi) {
		return true;
	}

	@Override
	public void add(Item to) {
		if (to instanceof Book) {
			items.add((Book) to);
			ed.tree.addToSel(to);
			((Book)to).update();

		}
		
	}

	@Override
	public void setup() {
		title = "New";
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, this);
		
	}

	@Override
	public void changeMainLabel(String newLabel) {
		title = newLabel;
		ed.editorUI.updatePanel(panel, this);
		
	}

static Icon icon;
  
  @Override
  public Icon icon(){
    if (icon == null){
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/template.png");
    }
    return icon;
  }
}
