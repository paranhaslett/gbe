package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Set;

public class SetIO implements Loadable {

	@Override
	public void load(Loader ff, Item item) {
		Set set = (Set) item;
		set.var = ff.getText("var");
		set.value = ff.getText("value");
		set.text = ff.getText("text");
	}

	@Override
	public void save(Loader ff, Item item) {
		Set set = (Set) item;
		Loader setff = ff.create("set");
		setff.setText("var", set.var);
		setff.setText("value", set.value);
		setff.setText("text", set.text);
	}

}
