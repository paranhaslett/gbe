package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.ChanceIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.ui.panel.TextUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

public class ChanceController implements Controller {
	public Loadable loader = new ChanceIO();
	private PanelUI panel = TextUI.getPanelUI();
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
		if (item instanceof Chance) {
			Text set = (Text) item;
			panel.populatePanel(set);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void setup(ModelItem modelItem) {

	}

}
