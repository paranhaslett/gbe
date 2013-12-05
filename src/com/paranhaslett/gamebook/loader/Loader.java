package com.paranhaslett.gamebook.loader;

import java.io.File;

import com.paranhaslett.gamebook.model.Book;
import com.paranhaslett.gamebook.model.Library;

public interface Loader {

	Book loadBook(File file);
	
	Library loadLibrary(File file);

	void save(Book gameBook, File file);
}
