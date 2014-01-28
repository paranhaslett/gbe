package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.loader.EmaLoader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.model.Item;

public interface Loadable {
	final XMLLoader xmlLoader = new XMLLoader();
	final EmaLoader emaLoader = new EmaLoader();

	Item loadFromXML(Element element);

	Element saveToXML(Item item);

	Item loadFromEma(ArrayList<String> content);

	ArrayList<String> saveToEma(Item item);

}
