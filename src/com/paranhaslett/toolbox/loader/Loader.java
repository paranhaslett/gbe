package com.paranhaslett.toolbox.loader;

import java.io.File;
import java.util.List;

import com.paranhaslett.toolbox.model.Artifact;
import com.paranhaslett.toolbox.tools.Tool;

public interface Loader {
	
	Artifact load(File file, Tool tool);
	
	void save(File file, Artifact item);
	
    String getText(String key);
	
	void setText(String key, String value);
	
	List<Loader> getChildren(String... childrenKeys);
	
	Loader getChild(String childkey);
	
	public Loader create(String key);
	
	public String getName();


}
