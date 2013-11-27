package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.ChoiceIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.ui.panel.TextUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class ChoiceController implements Controller{
	public Loadable loader = new ChoiceIO();
	private PanelUI panel = TextUI.getPanelUI();
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
		if (item instanceof Choice) {
			Text set = (Text) item;
			panel.populatePanel(set);
			gc.editorUI.updatePanel(panel);
		}
	}

  @Override
  public void setup(ModelItem modelItem) {

  }

}
