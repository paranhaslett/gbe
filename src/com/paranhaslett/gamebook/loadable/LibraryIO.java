package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.BookController;
import com.paranhaslett.gamebook.controller.PageController;
import com.paranhaslett.gamebook.controller.SectionController;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class LibraryIO implements Loadable {
	private Editor ed = Editor.getEd();

	public ModelItem load(Element element) {
		Library library = new Library();

		return library;
	}

	@Override
	public ModelItem loadFromXML(Element element) {
		Library library = new Library();
		BookController bc = (BookController) ed.getController(Item.BOOK);
		for (Element bookElement : xmlLoader.getElements(element, "book")) {
			Book book = (Book) bc.loader.loadFromXML(bookElement);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> saveToEma(ModelItem modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
