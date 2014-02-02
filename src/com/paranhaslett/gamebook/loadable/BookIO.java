package com.paranhaslett.gamebook.loadable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class BookIO implements Loadable {

	@Deprecated
	public void load(ArrayList<String> content, Item item) {
		Book gameBook = (Book) item;
		// get the gamebook name from content
		String path = content.get(0);
		for (String line : content) {
			if (line.contains("#")) {
				int pos = line.lastIndexOf('#');
				gameBook.title = line.substring(pos + 1);
				break;
			}
		}
		File[] files = new File(path).listFiles();

		for (File file : files) {
			String entryname = file.getName();
			if (entryname.endsWith(".txt")
					&& entryname.startsWith(gameBook.title)) {
				int spos = gameBook.title.length();
				int epos = entryname.lastIndexOf('.');
				if (epos - spos > 0) {
					ArrayList<String> entryContent = new ArrayList<String>();
					String secnum = entryname.substring(spos, epos);
					entryContent.add(secnum);

					BufferedReader reader;
					try {
						reader = new BufferedReader(new FileReader(file));
						String line = null;

						while ((line = reader.readLine()) != null) {
							entryContent.add(line);
						}

						reader.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Section section = new Section();
					//Section.loadable.load(entryContent, section);
					gameBook.freeSections.add(section);

				}
			}
		}
	}


	@Override
	public void load(Loader ff, Item item) {
		Book gameBook = (Book) item;
		gameBook.title = ff.getText("title");// Manditory name
		for (Loader pageFf : ff.getChildren("page")) {
			Page page = new Page();
			Page.loadable.load(pageFf, page);
			gameBook.pages.add(page);
		}
		for (Loader sectionElement : ff.getChildren("section")) {
			Section section = new Section();
			Section.loadable.load(sectionElement, section);
			gameBook.freeSections.add(section);
		}
	}

	@Override
	public void save(Loader ff, Item item) {
		Book gameBook = (Book) item;
		if (gameBook.title != null) {
			ff.setText("name", gameBook.title);
		}
		for (Page page : gameBook.pages) {
			Loader pageff = ff.create("page"); 
			Page.loadable.save(pageff, page);
		}
		for (Section section : gameBook.freeSections) {
			Loader sectionff = ff.create("section"); 
			Section.loadable.save(sectionff, section);
		}
		
	}

}
