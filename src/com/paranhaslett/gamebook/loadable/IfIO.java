package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;

public class IfIO implements Loadable {

	@Override
	public Item loadFromXML(Element element) {
		If ifob = new If();
		ifob.lhs = xmlLoader.getText(element, "var");
		ifob.rhs = xmlLoader.getText(element, "value");
		ifob.text = xmlLoader.getText(element, "text");
		List<Element> fragmentElements = xmlLoader.getAllElements(element,
				"fragments");
		
		for (Element fragmentElement : fragmentElements) {
			Fragment frag = null;
			if (fragmentElement.getNodeName().equals("text")) {
				frag = (Text) Text.loadable.loadFromXML(fragmentElement);
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
			if (fragmentElement.getNodeName().equals("goto")) {
				frag = (Goto) Goto.loadable.loadFromXML(fragmentElement);
			}

			if (frag != null) {
				ifob.fragments.add(frag);
			}

		}
		return ifob;
	}

	@Override
	public Element saveToXML(Item modelItem) {
		Text desc = (Text) modelItem;
		Element descElement = xmlLoader.doc.createElement("text");
		descElement.appendChild(xmlLoader.doc.createTextNode(desc.text));
		return descElement;
	}

	@Override
	public Item loadFromEma(ArrayList<String> content) {
		Text desc = new Text();
		StringBuilder sb = new StringBuilder();
		boolean lineBlank = false;
		for (String str : content) {
			if (lineBlank || str != "") {
				sb.append(str);
				sb.append('\n');
				lineBlank = false;
			} else {
				lineBlank = true;
			}
		}
		desc.text = sb.toString();
		return null;

	}

	@Override
	public ArrayList<String> saveToEma(Item modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
