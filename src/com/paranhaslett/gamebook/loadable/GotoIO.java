package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Text;

public class GotoIO implements Loadable {

	@Override
	public Item loadFromXML(Element element) {
		Goto gotoob = new Goto();
		gotoob.to = element.getAttribute("to");
		gotoob.text = element.getTextContent();
		return gotoob;
	}

	@Override
	public Element saveToXML(Item modelItem) {
		Goto gotoob = (Goto) modelItem;
		Element gotoElement = xmlLoader.doc.createElement("goto");
		gotoElement.setAttribute("to", gotoob.to);
		if (gotoob.text != null && !gotoob.text.equals("")) {
			xmlLoader.setTextElement(xmlLoader.doc, gotoElement, "text",
					gotoob.text);
		}
		return gotoElement;
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
