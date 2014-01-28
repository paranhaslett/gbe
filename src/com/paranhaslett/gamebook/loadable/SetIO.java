package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Set;

public class SetIO implements Loadable {

	@Override
	public Item loadFromXML(Element element) {
		Set set = new Set();
		set.var = element.getAttribute("var");
		set.value = xmlLoader.getText(element, "value");
		set.text = xmlLoader.getText(element, "text");
		return set;
	}

	@Override
	public Element saveToXML(Item modelItem) {
		Set set = (Set) modelItem;
		Element setElement = xmlLoader.doc.createElement("set");
		setElement.setAttribute("var", set.var);
		Element valueElement = xmlLoader.doc.createElement("value");
		valueElement.appendChild(xmlLoader.doc.createTextNode(set.value));
		setElement.appendChild(valueElement);
		Element textElement = xmlLoader.doc.createElement("text");
		textElement.appendChild(xmlLoader.doc.createTextNode(set.text));
		setElement.appendChild(textElement);

		return setElement;
	}

	@Override
	public Item loadFromEma(ArrayList<String> content) {
		// Set desc = new Set();
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
		return null;

	}

	@Override
	public ArrayList<String> saveToEma(Item modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
