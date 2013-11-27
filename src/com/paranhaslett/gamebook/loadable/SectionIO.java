package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.ChanceController;
import com.paranhaslett.gamebook.controller.ChoiceController;
import com.paranhaslett.gamebook.controller.TextController;
import com.paranhaslett.gamebook.controller.GotoController;
import com.paranhaslett.gamebook.controller.IfController;
import com.paranhaslett.gamebook.controller.SetController;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;

public class SectionIO implements Loadable {
	private Editor gc = Editor.getEd();

	public ModelItem loadFromXML(Element element) {
		Section section = new Section();
		// Manditory section_id
		section.id = element.getAttribute("id");
		section.title = element.getAttribute("title");

		List<Element> fragmentElements = xmlLoader.getChildren(element);
		TextController descC = (TextController) gc.getController(Item.TEXT);
		SetController setC = (SetController) gc.getController(Item.SET);
		ChoiceController choiceC = (ChoiceController) gc
				.getController(Item.CHOICE);
		ChanceController chanceC = (ChanceController) gc
				.getController(Item.CHANCE);
		IfController ifC = (IfController) gc.getController(Item.IF);

		boolean hasDesc = false;
		for (Element fragmentElement : fragmentElements) {
			Fragment frag = null;
			if (fragmentElement.getNodeName().equals("text")) {
				frag = (Text) descC.loader.loadFromXML(fragmentElement);
				hasDesc = true;
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

			if (frag != null) {
				section.fragments.add(frag);
			}
		}

		if (!hasDesc) {
			Text desc = new Text();
			desc.getController().setup(desc);
			section.fragments.add(desc);
		}

		Element gotoElement = xmlLoader.getChild(element, "goto");
		if (gotoElement != null) {
			GotoController gotoC = (GotoController) gc.getController(Item.GOTO);
			section.gotoid = (Goto) gotoC.loader.loadFromXML(gotoElement);
		} else {
			Goto secgoto = new Goto();
			secgoto.getController().setup(secgoto);
			section.gotoid = secgoto;
		}
		return section;
	}

	@Override
	public Element saveToXML(ModelItem modelItem) {
		Section section = (Section) modelItem;
		Element nodeElement = xmlLoader.doc.createElement("section");
		nodeElement.setAttribute("id", "" + section.id);
		if (section.title != null) {
			xmlLoader.setTextElement(xmlLoader.doc, nodeElement, "title",
					section.title);
		}
		if (section.fragments.size() > 0) {
			Element fragElements = xmlLoader.doc.createElement("fragments");
			TextController descC = (TextController) gc.getController(Item.TEXT);
			SetController setC = (SetController) gc.getController(Item.SET);
			ChoiceController choiceC = (ChoiceController) gc
					.getController(Item.CHOICE);
			ChanceController chanceC = (ChanceController) gc
					.getController(Item.CHANCE);
			IfController ifC = (IfController) gc.getController(Item.IF);

			for (Fragment fragment : section.fragments) {
				Element fragElement = null;
				if (fragment instanceof Text) {
					fragElement = descC.loader.saveToXML(fragment);
				}
				if (fragment instanceof Set) {
					fragElement = setC.loader.saveToXML(fragment);
				}
				if (fragment instanceof Choice) {
					fragElement = choiceC.loader.saveToXML(fragment);
				}
				if (fragment instanceof Chance) {
					fragElement = chanceC.loader.saveToXML(fragment);
				}
				if (fragment instanceof If) {
					fragElement = ifC.loader.saveToXML(fragment);
				}
				fragElements.appendChild(fragElement);
			}
			nodeElement.appendChild(fragElements);
		}

		if (section.gotoid != null) {
			GotoController gotoC = (GotoController) gc.getController(Item.GOTO);
			nodeElement.appendChild(gotoC.loader.saveToXML(section.gotoid));
		}

		return nodeElement;
	}

	@Override
	public ModelItem loadFromEma(ArrayList<String> content) {
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
	public ArrayList<String> saveToEma(ModelItem modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
