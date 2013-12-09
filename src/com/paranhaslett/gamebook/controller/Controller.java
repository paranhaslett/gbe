package com.paranhaslett.gamebook.controller;

import com.paranhaslett.gamebook.model.ModelItem;

public interface Controller {

	@Deprecated
	void add(ModelItem item, ModelItem to);

	@Deprecated
	void update(ModelItem item);

	@Deprecated
	void setup(ModelItem modelItem);

	@Deprecated
	void changeMainLabel(ModelItem item, String newLabel);

	boolean isDropOn(ModelItem item);

}
