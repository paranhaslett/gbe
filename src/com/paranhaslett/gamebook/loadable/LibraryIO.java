package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class LibraryIO implements Loadable {

	public ModelItem load(Element element) {
		Library library = new Library();

		return library;
	}

	@Override
	public ModelItem loadFromXML(Element element) {
		Library library = new Library();
		for (Element bookElement : xmlLoader.getElements(element, "book")) {
			Book book = (Book) Book.loadable .loadFromXML(bookElement);
			library.items.add(book);
		}
		return library;
	}

	@Override
	public Element saveToXML(ModelItem modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelItem loadFromEma(ArrayList<String> content) {
		// Library will be normally be saved in xml
		//TODO implement this to load library from Ema
		return null;
	}

	@Override
	public ArrayList<String> saveToEma(ModelItem modelItem) {
		// Library will be normally be saved in xml
		//TODO implement export
		return null;
	}

}
