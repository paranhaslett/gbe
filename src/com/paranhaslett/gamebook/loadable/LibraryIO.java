package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;
import com.paranhaslett.gamebook.model.libraryitem.Template;

public class LibraryIO implements Loadable {

	@Override
	public void load(Loader ff, Item item) {
		Library library = (Library) item;
		for (Loader seriesElement : ff.getChildren("series")) {
				Series series = new Series();
				Series.loadable.load(seriesElement, series);
				library.items.add(series);
		}
		
		for (Loader bookElement : ff.getChildren("book")) {
				Book book = new Book();
				Book.loadable.load(bookElement, book);
				library.items.add(book);
		}
		for (Loader templateElement :ff.getChildren("template")) {
				Template template = new Template();
				Template.loadable.load(templateElement, template);
				library.items.add(template);
		}
		
	}

	@Override
	public void save(Loader ff, Item item) {
		if (item instanceof Library) {
			Library library = (Library) item;
			for (Item libraryItem : library.items) {
				if (libraryItem instanceof Book) {
					Loader bookElement = ff.create("book");
					Book.loadable.save( bookElement, libraryItem);
				}
				if (libraryItem instanceof Template) {
					Loader templateElement = ff.create("template");
					Template.loadable.save(templateElement, libraryItem);
				}
				if (libraryItem instanceof Series) {
					Loader seriesElement = ff.create("template");
					Series.loadable.save(seriesElement, libraryItem);
				}
			}
		}
		
	}

}
