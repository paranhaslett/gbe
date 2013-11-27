package com.paranhaslett.gamebook.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.BookController;
import com.paranhaslett.gamebook.model.Book;

public class EmaLoader implements Loader {
	private Editor gc = Editor.getEd();

	@Override
	public Book load(File file) {
		
		Book gameBook = new Book();
		BookController gbController = (BookController)gc.getController("Book");
		BufferedReader reader;
		ArrayList<String> content = new ArrayList<String>();
		content.add(file.getParent());
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				content.add(line);
			}
			gameBook = (Book) gbController.loader.loadFromEma(content);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameBook;
	}

	@Override
	public void save(Book gameBook, File file) {
		// TODO Auto-generated method stub

	}

}
