package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;

public class SectionIO implements Loadable {

	public Item loadFromXML(Element element) {
		Section section = new Section();
		// Manditory section_id
		section.id = element.getAttribute("id");
		section.title = element.getAttribute("title");

		List<Element> fragmentElements = xmlLoader.getChildren(element);
		
		boolean hasDesc = false;
		for (Element fragmentElement : fragmentElements) {
			Fragment frag = null;
			if (fragmentElement.getNodeName().equals("text")) {
				frag = (Text) Text.loadable.loadFromXML(fragmentElement);
				hasDesc = true;
			}
			if (fragmentElement.getNodeName().equals("set")) {
				frag = (Set) Set.loadable.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("choice")) {
				frag = (Choice) Choice.loadable.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("chance")) {
				frag = (Chance) Chance.loadable.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("if")) {
				frag = (If) If.loadable.loadFromXML(fragmentElement);
			}

			if (frag != null) {
				section.fragments.add(frag);
			}
		}

		if (!hasDesc) {
			Text desc = new Text();
			desc.setup();
			section.fragments.add(desc);
		}

		Element gotoElement = xmlLoader.getChild(element, "goto");
		if (gotoElement != null) {
			section.gotoid = (Goto) Goto.loadable.loadFromXML(gotoElement);
		} else {
			Goto secgoto = new Goto();
			secgoto.setup();
			section.gotoid = secgoto;
		}
		return section;
	}

	@Override
	public Element saveToXML(Item modelItem) {
		Section section = (Section) modelItem;
		Element nodeElement = xmlLoader.doc.createElement("section");
		nodeElement.setAttribute("id", "" + section.id);
		if (section.title != null) {
			xmlLoader.setTextElement(xmlLoader.doc, nodeElement, "title",
					section.title);
		}
		if (section.fragments.size() > 0) {
			Element fragElements = xmlLoader.doc.createElement("fragments");
			
			for (Fragment fragment : section.fragments) {
				Element fragElement = null;
				if (fragment instanceof Text) {
					fragElement = Text.loadable.saveToXML(fragment);
				}
				if (fragment instanceof Set) {
					fragElement = Set.loadable.saveToXML(fragment);
				}
				if (fragment instanceof Choice) {
					fragElement = Choice.loadable.saveToXML(fragment);
				}
				if (fragment instanceof Chance) {
					fragElement = Chance.loadable.saveToXML(fragment);
				}
				if (fragment instanceof If) {
					fragElement = If.loadable.saveToXML(fragment);
				}
				fragElements.appendChild(fragElement);
			}
			nodeElement.appendChild(fragElements);
		}

		if (section.gotoid != null) {
			nodeElement.appendChild(Goto.loadable.saveToXML(section.gotoid));
		}

		return nodeElement;
	}

	@Override
	public Item loadFromEma(ArrayList<String> content) {
		Section section = new Section();
		section.id = content.get(0);
		boolean gotTitle = false;
		for (String line : content) {
			if (!gotTitle && line.contains("#")) {
				int pos = line.lastIndexOf('#');
				section.title = line.substring(pos + 1);
				gotTitle = true;
			} else {

			}
		}
		return section;
	}

	@Override
	public ArrayList<String> saveToEma(Item modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
