package com.paranhaslett.gamebook.loadable;

import java.io.BufferedReader;
import java.io.File;
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
		// get the game book name from content
		String path = content.get(0);
		for (String line : content) {
			if (line.contains("#")) {
				int pos = line.lastIndexOf('#');
				gameBook.title = line.substring(pos + 1);
				break;
			}
		}
		File[] files = new File(path).listFiles();
		if (files != null) {

			for (File file : files) {
				String entryName = file.getName();
				if (entryName.endsWith(".txt") && entryName.startsWith(gameBook.title)) {
					int startPos = gameBook.title.length();
					int endPos = entryName.lastIndexOf('.');
					if (endPos - startPos > 0) {
						ArrayList<String> entryContent = new ArrayList<>();
						String secNum = entryName.substring(startPos, endPos);
						entryContent.add(secNum);

						BufferedReader reader;
						try {
							reader = new BufferedReader(new FileReader(file));
							String line = null;

							while ((line = reader.readLine()) != null) {
								entryContent.add(line);
							}

							reader.close();
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
	}


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
