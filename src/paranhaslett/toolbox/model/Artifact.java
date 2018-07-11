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
		this.id = nextId;
		nextId++;
		this.data = new String[tool.numOfFields()];
	}

	public Tool tool() {
		return this.tool;
	}

	public TreeNodeUI getTreeNode() {
		TreeNodeUI treeNode = new TreeNodeUI(this);
		treeNode.setUserObject(this);
		if (this.contents.size() > 0) {
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
		if (this.tool.isDropOn(item.tool)) {
			this.contents.add(item);
		}
		return this;
	}

	public void update() {
		tool.fill(this, this.data);
		Config.getEd().editorUI.updatePanel(this);
	}

	public void setup() {
		TreePath path = ed.tree().getSelectLoc();
		ed.tree().addToPath(path, getTreeNode());
	}

	public boolean isDropOn(Artifact item) {
		return this.tool.isDropOn(item.tool);
	}

	public String[] getData() {
		return this.data;
	}

	public Artifact setData(String[] data) {
		this.data = data;
		return this;
	}

	public List<Artifact> contents() {
		return this.contents;
	}

	@Override
	public String toString() {
		long id2 = this.id;
		return ((Long)id2).toString();
	}


}
