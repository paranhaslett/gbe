package com.paranhaslett.gamebook.model;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.controller.Controller;

public interface Item extends Controller{

	void add(Item to);

	void update();

	//void setup(Editor ed);
	
	void setup();

	void changeMainLabel(String newLabel);

	boolean isDropOn(Item item);
	
	String toString();
	
}
