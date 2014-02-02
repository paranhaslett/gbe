package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Text;

public class TextIO implements Loadable {

	public void load(ArrayList<String> content, Item item) {
		Text desc = (Text) item;
		StringBuilder sb = new StringBuilder();
		boolean lineBlank = false;
		for (String str : content) {
			if (lineBlank || str != "") {
				sb.append(str);
				sb.append('\n');
				lineBlank = false;
			} else {
				lineBlank = true;
			}
		}
		desc.text = sb.toString();

	}

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
