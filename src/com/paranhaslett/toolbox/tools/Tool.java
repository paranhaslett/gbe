package com.paranhaslett.toolbox.tools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JPanel;

import com.paranhaslett.toolbox.Editor;
import com.paranhaslett.toolbox.fields.Field;
import com.paranhaslett.toolbox.loader.EmaLoader;
import com.paranhaslett.toolbox.loader.Loader;
import com.paranhaslett.toolbox.loader.XMLLoader;
import com.paranhaslett.toolbox.model.Artifact;


public abstract class Tool extends JPanel{
	private Icon icon;
	private final List<Field> fields = new ArrayList<>();
	private final List<Tool> subTools = new ArrayList<>();
	
	@SuppressWarnings("UnusedReturnValue")
	public Tool addIcon(String iconStr){
		  icon = Editor.getEd().tree.getTreeRenderer().createImageIcon("/icons/tree/" + iconStr + ".png");
	      return this;
	}
	
	@SuppressWarnings("UnusedReturnValue")
	public Tool addTool(Tool tool){
		subTools.add(tool);
		return this;
	}
	
	public Icon icon(){
		return icon;
	}
	
	public static final XMLLoader xmlLoader = new XMLLoader();
	public static final EmaLoader emaLoader = new EmaLoader();
	
	@SuppressWarnings("SameReturnValue")
	public abstract Artifact load(Loader ff);
	@SuppressWarnings("EmptyMethod")
	public abstract void save(Loader ff, Artifact item);
	
	
	@SuppressWarnings("UnusedReturnValue")
	public Tool addField(Field field){
		fields.add(field);
		return this;
	}

	public abstract void populateModel();

	@SuppressWarnings("EmptyMethod")
	public abstract void populatePanel(Artifact item);
}
