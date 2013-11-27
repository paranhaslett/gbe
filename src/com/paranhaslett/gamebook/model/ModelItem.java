package com.paranhaslett.gamebook.model;

import com.paranhaslett.gamebook.controller.Controller;

public interface ModelItem {
	Controller getController();

	String toString();
}
