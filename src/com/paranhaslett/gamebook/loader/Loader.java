package com.paranhaslett.gamebook.loader;

import java.io.File;

import com.paranhaslett.gamebook.model.Book;

public interface Loader {

	Book load(File file);

	void save(Book gameBook, File file);
}
