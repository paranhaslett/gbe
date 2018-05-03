package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Text;

public class TextIO implements Loadable {

	@Override
	public void load(Loader ff, Item item) {
		Text desc = (Text) item;
		desc.text = ff.getText(null);	
	}

	@Override
	public void save(Loader ff, Item item) {
		Text desc = (Text) item;
		ff.setText(null, desc.text);
	}

}
