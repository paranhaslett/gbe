package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.GotoIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.ui.panel.GotoUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class GotoController implements Controller{
	public Loadable loader = new GotoIO();
	private PanelUI panel = GotoUI.getPanelUI();
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
		if (item instanceof Goto) {
			Goto gotoob = (Goto) item;
			panel.populatePanel(gotoob);
			gc.editorUI.updatePanel(panel);
		}
	}

  @Override
  public void setup(ModelItem modelItem) {
    
  }

}
