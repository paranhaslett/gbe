package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.GoTo;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Template;

public class TemplateIO implements Loadable {

	@Override
	public void load(Loader ff, Item tempItem) {
		Template template = (Template) tempItem;
		template.title = ff.getText("title");
		for (Loader elem :ff.getChildren(
				"template",
				"book",
				"chance",
				"choice",
				"goto",
				"if",
				"page",
				"set",
				"template",
				"text",
				"section")){
			Item item = null;
			if (elem.getName().equals("book")){
				item = new Book();
				Book.loadable.load(elem, item);
			}
			if (elem.getName().equals("chance")){
				item = new Chance();
				Chance.loadable.load(elem, item);
			}
			if (elem.getName().equals("choice")){
				item = new Choice();
				Choice.loadable.load(elem, item);
			}
			if (elem.getName().equals("goto")){
				item = new GoTo();
				GoTo.loadable.load(elem, item);
			}
			if (elem.getName().equals("if")){
				item = new If();
				If.loadable.load(elem, item);
			}
			if (elem.getName().equals("page")){
				item = new Page();
				Page.loadable.load(elem, item);
			}
			if (elem.getName().equals("section")){
				item = new Section();
				Section.loadable.load(elem, item);
			}
			if (elem.getName().equals("set")){
				item = new Set();
				Set.loadable.load(elem, item);
			}
			if (elem.getName().equals("template")){
				item = new Template();
				Template.loadable.load(elem, item);
			}
			if (elem.getName().equals("text")){
				item = new Text();
				Text.loadable.load(elem, item);
			}
			template.items.add(item);
		}
		
	}

	@Override
	public void save(Loader ff, Item item) {
		// TODO Auto-generated method stub
		
	}

}
