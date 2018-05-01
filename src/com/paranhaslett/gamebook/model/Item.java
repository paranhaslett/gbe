package com.paranhaslett.gamebook.model;

import javax.swing.Icon;

public interface Item {

	void add(Item to);

	void update();

	void setup();

	void changeMainLabel(String newLabel);

	boolean isDropOn(Item item);

	Icon icon();

}
