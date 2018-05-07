package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class BookIO implements Loadable {
    
    @Override
    public void load(Loader loader, Item item) {
        Book gameBook = (Book) item;
        gameBook.title = loader.getText("title");// Mandatory name
        for (Loader pageFf : loader.getChildren("page")) {
            Page page = new Page();
            Page.loadable.load(pageFf, page);
            gameBook.pages.add(page);
        }
        for (Loader sectionElement : loader.getChildren("section")) {
            Section section = new Section();
            Section.loadable.load(sectionElement, section);
            gameBook.freeSections.add(section);
        }
    }

    @Override
    public void save(Loader loader, Item item) {
        Book gameBook = (Book) item;
        if (gameBook.title != null) {
            loader.setText("title", gameBook.title);
        }
        for (Page page : gameBook.pages) {
            Loader pageLoader = loader.create("page");
            Page.loadable.save(pageLoader, page);
        }
        for (Section section : gameBook.freeSections) {
            Loader sectionLoader = loader.create("section");
            Section.loadable.save(sectionLoader, section);
        }
    }
}
