package com.paranhaslett.gamebook.loadable;

import java.util.ArrayList;
import java.util.List;

import com.paranhaslett.gamebook.loader.Loader;
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

	@Deprecated
	public void load(ArrayList<String> content, Item item) {
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
	}


	@Override
	public void load(Loader ff, Item item) {
			Section section = (Section) item;
			// Manditory section_id
			section.id = ff.getText("id");
			section.title = ff.getText("title");

			List<Loader> fragmentElements = ff.getChildren(
					"text",
					"set",
					"choice", 
					"chance",
					"if");
			
			boolean hasDesc = false;
			for (Loader fragmentElement : fragmentElements) {
				Fragment frag = null;
				if (fragmentElement.getName().equals("text")) {
					frag = new Text();
					Text.loadable.load(fragmentElement, frag);
					hasDesc = true;
				}
				if (fragmentElement.getName().equals("set")) {	
					frag = new Set();
					Set.loadable.load(fragmentElement, frag);
				}
				if (fragmentElement.getName().equals("choice")) {
					frag = new Choice();
					Choice.loadable.load(fragmentElement, frag);
				}
				if (fragmentElement.getName().equals("chance")) {
					frag = new Chance();
					Chance.loadable.load(fragmentElement, frag);
				}
				if (fragmentElement.getName().equals("if")) {
					frag = new If();
					If.loadable.load(fragmentElement, frag);
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

			Loader gotoElement = ff.getChild("goto");
			Goto secgoto = new Goto();
			if (gotoElement != null) {
				Goto.loadable.load(gotoElement, secgoto);
			} else {
				secgoto.setup();
				section.gotoid = secgoto;
			}
		
	}

	@Override
	public void save(Loader ff, Item item) {
		Section section = (Section) item;
		Loader nodeElement =ff.create("section");
		nodeElement.setText("id", "" + section.id);
		if (section.title != null) {
			ff.setText("title", section.title);
		}
		if (section.fragments.size() > 0) {
			Loader fragElements = nodeElement.create("fragments");
			
			for (Fragment fragment : section.fragments) {
				Loader fragElement = null;
				if (fragment instanceof Text) {
					fragElement = fragElements.create("text");
					Text.loadable.save(fragElement, fragment);
				}
				if (fragment instanceof Set) {
					fragElement = fragElements.create("set");
					Set.loadable.save(fragElement, fragment);
				}
				if (fragment instanceof Choice) {
					fragElement = fragElements.create("choice");
					Choice.loadable.save(fragElement, fragment);
				}
				if (fragment instanceof Chance) {
					fragElement = fragElements.create("chance");
					Chance.loadable.save(fragElement, fragment);
				}
				if (fragment instanceof If) {
					fragElement = fragElements.create("choice");
					If.loadable.save(fragElement, fragment);
				}
			}
		}

		if (section.gotoid != null) {
			Loader gotoElement = nodeElement.create("goto");
			Goto.loadable.save( gotoElement, section.gotoid);
		}

		
	}

}
