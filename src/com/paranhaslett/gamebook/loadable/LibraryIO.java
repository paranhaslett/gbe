package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.ModelItem;

public class LibraryIO implements Loadable {

	public ModelItem load(Element element) {
		Library library = new Library();

		return library;
	}

	@Override
	public ModelItem loadFromXML(Element element) {
		// TODO Auto-generated method stub
		return null;
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
