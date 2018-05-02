package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.GoTo;

public class GoToIO implements Loadable {

	@Override
	public void load(Loader ff, Item item) {
		GoTo goToObj = (GoTo)item;
		goToObj.to = ff.getText("to");
		goToObj.text = ff.getText(null);
		
	}

	@Override
	public void save(Loader ff, Item item) {
		GoTo goToObj = (GoTo) item;
		ff.setText("to", goToObj.to);
		if (goToObj.text != null && !goToObj.text.equals("")) {
			ff.setText("text", goToObj.text);
		}
		
	}

}
