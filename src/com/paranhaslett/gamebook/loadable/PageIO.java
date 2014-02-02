package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;

public class PageIO implements Loadable {

	@Override
	public void load(Loader ff, Item item) {
		Page page = (Page) item;
		// Manditory page_id
		page.id = ff.getText("id");
		for (Loader sectionEl : ff.getChildren("section")) {
			Section section = new Section();
			Section.loadable.load(sectionEl, section);
		}
		
	}

	@Override
	public void save(Loader ff, Item item) {
		Page page = (Page) item;
		ff.setText("id", "" + page.id);
		for (Section section : page.sections) {
			
			Loader sectionElement = ff.create("section");
			Section.loadable.save(sectionElement, section);
		}
		
	}

}
