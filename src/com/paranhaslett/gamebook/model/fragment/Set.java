package com.paranhaslett.gamebook.model.fragment;

import javax.swing.Icon;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SetIO;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SetUI;

public class Set implements Fragment {
  public String var;
  public String value;
  public String text;

  public static Loadable loadable = new SetIO();
  private PanelUI panel = SetUI.getPanelUI();
  private Editor ed = Editor.getEd();

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

  static Icon icon;

  @Override
  public Icon icon() {
    if (icon == null) {
      icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/set.png");
    }
    return icon;
  }
}
