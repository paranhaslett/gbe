package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.IfIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.branch.If;
import com.paranhaslett.gamebook.ui.panel.IfUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class IfController implements Controller {
	public Loadable loader = new IfIO();
	private PanelUI panel = IfUI.getPanelUI();
	private Editor gc = Editor.getEd();

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
		if (item instanceof If) {
			If ifItem = (If) item;
			panel.populatePanel(ifItem);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void setup(ModelItem modelItem) {
		// TODO Auto-generated method stub
	}

}
