package paranhaslett.toolbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreePath;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.tree.TreeNodeUI;

public class Artifact {
	private static final Config ed = Config.getEd();
	private static long nextId = 1;
	private final Tool tool;
	private final long id;
	private final List<Artifact> contents = new ArrayList<>();
	private String[] data;

	public Artifact(Tool tool) {
		this.tool = tool;
		id = nextId;
		nextId++;
		data = new String[tool.numOfFields()];
	}

	public Tool tool() {
		return tool;
	}

	public TreeNodeUI getTreeNode() {
		TreeNodeUI treeNode = new TreeNodeUI(this);
		treeNode.setUserObject(this);
		if (contents.size() > 0) {
			treeNode.setAllowsChildren(true);
			for (Artifact art : contents) {
				treeNode.add(art.getTreeNode());
			}
		}
		return treeNode;
	}

	public Artifact add(Artifact item) {
		if (item == null) {
			throw new NullPointerException();
		}
		if (tool.isDropOn(item.tool)) {
			contents.add(item);
		}
		return this;
	}

	
	public void update(){
		tool.fill(data);
	  Config.getEd().editorUI.updatePanel(this);
	}
	
	public void setup() {
		TreePath path = ed.tree().getSelectLoc();
		ed.tree().addToPath(path, getTreeNode());
	}

	public boolean isDropOn(Artifact item) {
		return tool.isDropOn(item.tool);
	}

	public String[] getData() {
		return data;
	}

	public Artifact setData(String[] data) {
		this.data = data;
		return this;
	}

	public long getId() {
	  return id;
	}
	
	public String save(){
		StringBuilder ssb = new StringBuilder("<artifact id=\"")
				.append(id).append("\" tool=\"").append(tool.getName())
				.append("\">\n");
		for (String datum:data){
			ssb.append("<data>")
					.append(datum).append("</data>\n");
		}
		for (Artifact subArt: contents){
			ssb.append(subArt.save());                                                                                                     
		}
		ssb.append("</artifact>\n");
		
		return ssb.toString();	
	}

	public String toString() {
		if (data[0] == null) {
			return tool.getName() + id;
		}
		return data[0];
	}

}
