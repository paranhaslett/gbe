package com.paranhaslett.toolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

import com.paranhaslett.toolbox.loader.Loader;
import com.paranhaslett.toolbox.model.Artifact;
import com.paranhaslett.toolbox.tools.Tool;
import com.paranhaslett.toolbox.tree.TreeUI;
import com.paranhaslett.toolbox.ui.EditorUI;
import com.paranhaslett.toolbox.ui.FileChooserUI;
import com.paranhaslett.toolbox.ui.FileChooserUI.EmaFilter;
import com.paranhaslett.toolbox.ui.FileChooserUI.GameBookFilter;

public class Editor {
	private static Editor editor = null;
	public final TreeUI tree;
	public EditorUI editorUI;
	private final FileChooserUI fileChooser;
	private final List<Tool> tools = new ArrayList<>();
	
	protected Editor(){
		tree = new TreeUI();
		fileChooser = new FileChooserUI();
		editor = this;
	}

	public static Editor getEd() {
		return editor;
	}
	
	
	public Editor addTool(Tool tool){
		tools.add(tool);
		return this;
	}
	

	protected void build(String name, Tool rootTool) {
		Artifact root = new Artifact(rootTool);
		editorUI = new EditorUI(name);
		editorUI.setVisible(true);
		
	
		tree.setup(root);
		editorUI.updateTree(tree);

	}
	
	private void load(Artifact root){
		File file = new File("./library.xml");
		if (file != null) {
			Loader loader = getLoader();
			if (loader != null) {
				root =  loader.load(file, root.tool());
			}
		} 
		
	}

	private Loader getLoader() {
		FileFilter filt = fileChooser.getFileFilter();
		Loader loader = null;
		if (filt instanceof GameBookFilter) {
			loader = Tool.xmlLoader;
		}
		if (filt instanceof EmaFilter) {
			loader = Tool.emaLoader;
		}
		return loader;
	}


	public void update() {
		tree.update();
	}

}
