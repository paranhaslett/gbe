package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Template;

public class TemplateIO implements Loadable {

	@Override
	public Item loadFromXML(Element element) {
		Template template = new Template();
		template.title = element.getAttribute("title");
		for (Element elem :xmlLoader.getChildren(element)){
			Item item = null;
			if (elem.getNodeName().equals("book")){
				item = Book.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("chance")){
				item = Chance.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("choice")){
				item = Choice.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("goto")){
				item = Goto.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("if")){
				item = If.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("page")){
				item = Page.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("section")){
				item = Section.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("set")){
				item = Set.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("template")){
				item = Template.loadable.loadFromXML(elem);
			}
			if (elem.getNodeName().equals("set")){
				item = Text.loadable.loadFromXML(elem);
			}
			template.items.add(item);
		}
		
		return template;
	}

	@Override
	public Element saveToXML(Item modelItem) {
		// TODO Auto-generated method stub
		return null;
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
