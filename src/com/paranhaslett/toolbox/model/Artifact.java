package com.paranhaslett.toolbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.paranhaslett.toolbox.Editor;
import com.paranhaslett.toolbox.tools.Tool;

public class Artifact {
	private final Tool tool;
	private List <String> data;
	private final long id;
	private static long nextId = 1;
	private static final Editor ed = Editor.getEd();
	private final List<Artifact> contents = new ArrayList<>();
	
	public Tool tool(){
		 return tool;
	}
	
	public Artifact(Tool tool){
		this.tool = tool;
		id = nextId;
		nextId++;
		
	}
	public DefaultMutableTreeNode getTreeNode(){
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode();
	    treeNode.setUserObject(this);
	    
		if (contents.size()>0){
			treeNode.setAllowsChildren(true);
			for(Artifact art: contents){
				treeNode.add(art.getTreeNode());
			}
		}
		return treeNode;
	}
	
	public Artifact addData(String name){
		data.add(name);
		return this;
	}

	public Artifact add(Artifact item){
		if (tool.getSubTools().contains(item.tool())) {
			contents.add(item);
		}
		return this;
	}

    @SuppressWarnings("EmptyMethod")
    public void update(){}

	public void setup(){
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, getTreeNode());
	}

	public void changeMainLabel(String newLabel) {
		data.set(0, newLabel);
		ed.editorUI.updatePanel(tool, this);
		
	}
	

	public boolean isDropOn(Artifact item) {
		return tool != null && tool.getSubTools().contains(item.tool());
	}
	
	public String getData(int ind){
		return data.get(ind);
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		if (data == null || data.size() == 0){
			return tool.toString();
		}
		return data.get(0);
	}
	
	public String toString() {
		return "" + id;
	}

}
