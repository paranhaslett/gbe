package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Text;

public class ChoiceIO implements Loadable {

	@Override
	public ModelItem loadFromXML(Element element) {
		Text desc = new Text();
		desc.text = element.getTextContent();
		return desc;
	}

	@Override
	public Element saveToXML(ModelItem modelItem) {
		Text desc = (Text) modelItem;
		Element descElement = xmlLoader.doc.createElement("text");
		descElement.appendChild(xmlLoader.doc.createTextNode(desc.text));
		return descElement;
	}

	@Override
	public ModelItem loadFromEma(ArrayList<String> content) {
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
	public ArrayList<String> saveToEma(ModelItem modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
