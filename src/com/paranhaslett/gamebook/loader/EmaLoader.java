package com.paranhaslett.gamebook.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class EmaLoader implements Loader {

	@Override
	public Book loadBook(File file) {

		Book gameBook = null;
		BufferedReader reader;
		ArrayList<String> content = new ArrayList<String>();
		content.add(file.getParent());
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = reader.readLine()) != null) {
				content.add(line);
			}
			gameBook = (Book) Book.loadable.loadFromEma(content);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (gameBook == null){
			gameBook = new Book();
		}
		return gameBook;
	}

	@Override
	public void save(Book gameBook, File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public Library loadLibrary(File file) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save(Library library, File file) {
		// TODO Auto-generated method stub

	}

}
