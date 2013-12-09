package com.paranhaslett.gamebook.model;

import com.paranhaslett.gamebook.controller.Controller;

public interface ModelItem {
	@Deprecated
	Controller getController();

	String toString();
}
