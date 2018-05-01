package com.paranhaslett.gamebook.loader;

import java.io.File;
import java.util.List;

import com.paranhaslett.gamebook.model.Item;

public interface Loader {
	
	void load(File file, Item item);
	
	void save(File file, Item item);
	
	//-----------------------------
	
    String getText(String key);
	
	void setText(String key, String value);
	
	List<Loader> getChildren(String... childrenKeys);
	
	Loader getChild(String childkey);
	
	Loader create(String key);
	
	String getName();
}
