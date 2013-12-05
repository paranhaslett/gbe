package com.paranhaslett.gamebook.loadable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.PageController;
import com.paranhaslett.gamebook.controller.SectionController;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class BookIO implements Loadable {
	private Editor gc = Editor.getEd();

	public ModelItem loadFromXML(Element element) {
		Book gameBook = new Book();
		gameBook.title = xmlLoader.load("title");
		gameBook.title = element.getAttribute("title");// Manditory name
		PageController pc = (PageController) gc.getController(Item.PAGE);
		for (Element pageElement : xmlLoader.getElements(element, "page")) {
			Page page = (Page) pc.loader.loadFromXML(pageElement);
			gameBook.pages.add(page);
		}
		SectionController sc = (SectionController) gc
				.getController(Item.SECTION);
		for (Element sectionElement : xmlLoader.getElements(element, "section")) {
			Section section = (Section) sc.loader.loadFromXML(sectionElement);
			gameBook.freeSections.add(section);
		}
		return gameBook;
	}

	@Override
	public Element saveToXML(ModelItem modelItem) {
		Book gameBook = (Book) modelItem;
		Element nodeElement = xmlLoader.doc.createElement("book");
		if (gameBook.title != null) {
			nodeElement.setAttribute("name", gameBook.title);
		}
		PageController pc = (PageController) gc.getController(Item.PAGE);
		for (Page page : gameBook.pages) {
			nodeElement.appendChild(pc.loader.saveToXML(page));
		}
		SectionController sc = (SectionController) gc
				.getController(Item.SECTION);
		for (Section section : gameBook.freeSections) {
			nodeElement.appendChild(sc.loader.saveToXML(section));
		}
		return nodeElement;
	}

	@Override
	public ModelItem loadFromEma(ArrayList<String> content) {
		Book gameBook = new Book();
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

		SectionController sc = (SectionController) gc
				.getController(Item.SECTION);
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

					Section section = (Section) sc.loader
							.loadFromEma(entryContent);
					gameBook.freeSections.add(section);

				}
			}
		}
		return gameBook;
	}

	@Override
	public ArrayList<String> saveToEma(ModelItem modelItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
