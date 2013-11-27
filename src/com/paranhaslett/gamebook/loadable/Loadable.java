package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.loader.EmaLoader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.model.ModelItem;

public interface Loadable {
	final XMLLoader xmlLoader = new XMLLoader();
	final EmaLoader emaLoader = new EmaLoader();
	
	ModelItem loadFromXML(Element element);
	Element saveToXML(ModelItem modelItem);	
	
	ModelItem loadFromEma(ArrayList<String> content);
	ArrayList<String> saveToEma(ModelItem modelItem);

}
