package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.model.ModelItem;

public interface Controller {
	
	void add(ModelItem item, ModelItem to);
	void update(ModelItem item);
	void setup(ModelItem modelItem);
	void changeMainLabel(ModelItem item, String newLabel);
	boolean isDropOn(ModelItem item);

}
