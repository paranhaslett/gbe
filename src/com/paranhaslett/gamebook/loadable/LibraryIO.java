package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;
import com.paranhaslett.gamebook.model.libraryitem.Template;

public class LibraryIO implements Loadable {

	public Item load(Element element) {
		Library library = new Library();

		return library;
	}

	@Override
	public Item loadFromXML(Element element) {
		Library library = new Library();
		for (Element seriesElement : xmlLoader.getElements(element, "series")) {
			Series series = (Series) Series.loadable .loadFromXML(seriesElement);
			library.items.add(series);
		}
		for (Element bookElement : xmlLoader.getElements(element, "book")) {
			Book book = (Book) Book.loadable .loadFromXML(bookElement);
			library.items.add(book);
		}
		for (Element seriesElement : xmlLoader.getElements(element, "template")) {
			Template series = (Template) Template.loadable.loadFromXML(seriesElement);
			library.items.add(series);
		}
		
		
		return library;
		
	}

	@Override
	public Element saveToXML(Item modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item loadFromEma(ArrayList<String> content) {
		// Library will be normally be saved in xml
		//TODO implement this to load library from Ema
		return null;
	}

	@Override
	public ArrayList<String> saveToEma(Item modelItem) {
		// Library will be normally be saved in xml
		//TODO implement export
		return null;
	}

}
