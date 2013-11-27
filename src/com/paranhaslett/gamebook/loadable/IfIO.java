package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.ChanceController;
import com.paranhaslett.gamebook.controller.ChoiceController;
import com.paranhaslett.gamebook.controller.TextController;
import com.paranhaslett.gamebook.controller.GotoController;
import com.paranhaslett.gamebook.controller.IfController;
import com.paranhaslett.gamebook.controller.SetController;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;

public class IfIO implements Loadable {
	private Editor gc = Editor.getEd();

	@Override
	public ModelItem loadFromXML(Element element) {	
		If ifob = new If();
		ifob.lhs = xmlLoader.getText(element, "var");
		ifob.rhs = xmlLoader.getText(element, "value");
		ifob.text = xmlLoader.getText(element, "text");
		List<Element> fragmentElements = xmlLoader.getAllElements(element,
				"fragments");
		TextController descC = (TextController)gc.getController("Text");
		SetController setC = (SetController)gc.getController("Set");
		ChoiceController choiceC = (ChoiceController)gc.getController("Choice");
		ChanceController chanceC = (ChanceController)gc.getController("Chance");
		IfController ifC = (IfController)gc.getController("If");
		GotoController gotoC = (GotoController)gc.getController("Goto");
		
		for (Element fragmentElement : fragmentElements) {
			Fragment frag = null;	
			if (fragmentElement.getNodeName().equals("text")) {
				frag = (Text) descC.loader.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("set")) {
				frag = (Set) setC.loader.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("choice")) {
				frag = (Choice) choiceC.loader.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("chance")) {
				frag = (Chance) chanceC.loader.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("if")) {
				frag = (If) ifC.loader.loadFromXML(fragmentElement);
			}
			if (fragmentElement.getNodeName().equals("goto")) {
				frag = (Goto)gotoC.loader.loadFromXML(fragmentElement);
			}
	
			if (frag != null) {
				ifob.fragments.add(frag);
			}
	
		}
		return ifob;
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
		for(String str:content){
			if(lineBlank || str != ""){
				sb.append(str);
				sb.append('\n');
				lineBlank = false;
			} else {
				lineBlank = true;
			}
		}
		desc.text=sb.toString();
		return null;

	}

	@Override
	public ArrayList<String> saveToEma(ModelItem modelItem) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
