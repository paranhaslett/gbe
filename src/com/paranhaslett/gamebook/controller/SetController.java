package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SetIO;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SetUI;

public class SetController implements Controller{
	public Loadable loader = new SetIO();
	private PanelUI panel = SetUI.getPanelUI();
	private Editor gc=Editor.getEd();

	@Override
	public void add(ModelItem item, ModelItem to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDropOn(ModelItem mi) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void update(ModelItem item) {
		if (item instanceof Set) {
			Set set = (Set) item;
			panel.populatePanel(set);
			gc.editorUI.updatePanel(panel);
		}
	}

  @Override
  public void setup(ModelItem modelItem) {

  }

}
