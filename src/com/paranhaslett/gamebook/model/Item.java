package com.paranhaslett.gamebook.model;

import com.paranhaslett.gamebook.Editor;

public interface Item {

	void add(ModelItem to);

	void update();

	void setup(Editor ed);

	void changeMainLabel(String newLabel);

	boolean isDropOn(ModelItem item);
	
	String toString();
	
}
