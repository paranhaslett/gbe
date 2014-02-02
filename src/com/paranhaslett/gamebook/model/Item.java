package com.paranhaslett.gamebook.model;

public interface Item {

	void add(Item to);

	void update();
	
	void setup();

	void changeMainLabel(String newLabel);

	boolean isDropOn(Item item);
	
	String toString();
	
}
