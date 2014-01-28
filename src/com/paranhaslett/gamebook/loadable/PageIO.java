package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;

public class PageIO implements Loadable {

	@Override
	public Item loadFromXML(Element element) {
		Page page = new Page();
		// Manditory page_id
		page.id = element.getAttribute("id");
		for (Element sectionEl : xmlLoader.getElements(element, "section")) {
			Section section = (Section) Section.loadable.loadFromXML(sectionEl);
			page.sections.add(section);
		}
		return null;
	}

	@Override
	public Element saveToXML(Item modelItem) {
		Page page = (Page) modelItem;
		Element nodeElement = xmlLoader.doc.createElement("page");
		nodeElement.setAttribute("id", "" + page.id);
		for (Section section : page.sections) {
			nodeElement.appendChild(Section.loadable.saveToXML(section));
		}
		return nodeElement;
	}

	@Override
	public Item loadFromEma(ArrayList<String> content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> saveToEma(Item modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
