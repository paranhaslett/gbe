package paranhaslett.gamebook.loadable;

import paranhaslett.gamebook.loader.Loader;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Page;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.fragment.GoTo;
import paranhaslett.gamebook.model.fragment.Set;
import paranhaslett.gamebook.model.fragment.Text;
import paranhaslett.gamebook.model.fragment.branch.Chance;
import paranhaslett.gamebook.model.fragment.branch.Choice;
import paranhaslett.gamebook.model.fragment.branch.If;
import paranhaslett.gamebook.model.libraryitem.Book;
import paranhaslett.gamebook.model.libraryitem.Template;

public class TemplateIO implements Loadable {

    @Override
    public void load(Loader ff, Item tempItem) {
        Template template = (Template) tempItem;
        template.title = ff.getText("title");
        for (Loader elem : ff.getChildren(
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
                "section")) {
            Item item = null;
            if (elem.getName().equals("book")) {
                item = new Book();
                Book.loadable.load(elem, item);
            }
            if (elem.getName().equals("chance")) {
                item = new Chance();
                Chance.loadable.load(elem, item);
            }
            if (elem.getName().equals("choice")) {
                item = new Choice();
                Choice.loadable.load(elem, item);
            }
            if (elem.getName().equals("goto")) {
                item = new GoTo();
                GoTo.loadable.load(elem, item);
            }
            if (elem.getName().equals("if")) {
                item = new If();
                If.loadable.load(elem, item);
            }
            if (elem.getName().equals("page")) {
                item = new Page();
                Page.loadable.load(elem, item);
            }
            if (elem.getName().equals("section")) {
                item = new Section();
                Section.loadable.load(elem, item);
            }
            if (elem.getName().equals("set")) {
                item = new Set();
                Set.loadable.load(elem, item);
            }
            if (elem.getName().equals("template")) {
                item = new Template();
                Template.loadable.load(elem, item);
            }
            if (elem.getName().equals("text")) {
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
