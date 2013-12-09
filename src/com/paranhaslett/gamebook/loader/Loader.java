package com.paranhaslett.gamebook.loader;

import java.io.File;

import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public interface Loader {

	Book loadBook(File file);
	
	Library loadLibrary(File file);

	void save(Book gameBook, File file);
	
	void save(Library library, File file);
}
