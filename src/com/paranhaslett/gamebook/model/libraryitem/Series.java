package com.paranhaslett.gamebook.model.libraryitem;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SeriesIO;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SeriesUI;

public class Series implements Item {
	public String title;
	public ArrayList<Book> books = new ArrayList<Book>();
	public static Loadable loadable = new SeriesIO();
	private PanelUI panel = SeriesUI.getPanelUI();
	private Editor ed = Editor.getEd();
	static Icon seriesIcon;

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
		return (mi instanceof Book);
	}

	@Override
	public void setup() {
		title = "New";
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, this);
	}

	@Override
	public void add(Item added) {
		if (added instanceof Book) {
			books.add((Book) added);
			ed.tree.addToSel(added);
			((Book)added).update();

		}
		
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
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/series.png");
    }
    return icon;
  }


}
