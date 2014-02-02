package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Goto;

public class GotoIO implements Loadable {

	@Override
	public void load(Loader ff, Item item) {
		Goto gotoob = (Goto)item;
		gotoob.to = ff.getText("to");
		gotoob.text = ff.getText(null);
		
	}

	@Override
	public void save(Loader ff, Item item) {
		Goto gotoob = (Goto) item;
		ff.setText("to", gotoob.to);
		if (gotoob.text != null && !gotoob.text.equals("")) {
			ff.setText("text", gotoob.text);
		}
		
	}

}
