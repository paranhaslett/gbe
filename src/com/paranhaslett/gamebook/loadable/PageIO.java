package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.SectionController;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;

public class PageIO implements Loadable {
	private Editor gc = Editor.getEd();

	@Override
	public ModelItem loadFromXML(Element element) {
		Page page = new Page();
		// Manditory page_id
		page.id = element.getAttribute("id");
		SectionController sc = (SectionController)gc.getController("Section");
		for (Element sectionEl : xmlLoader.getElements(element, "section")) {
			Section section = (Section) sc.loader.loadFromXML(sectionEl);
			page.sections.add(section);
		}
		return null;
	}

	@Override
	public Element saveToXML(ModelItem modelItem) {
		Page page = (Page)modelItem;
		Element nodeElement = xmlLoader.doc.createElement("page");
		nodeElement.setAttribute("id", "" + page.id);
		SectionController sc = (SectionController)gc.getController("Section");
		for (Section section : page.sections) {
			nodeElement.appendChild(sc.loader.saveToXML(section));
		}
		return nodeElement;
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
