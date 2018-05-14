package paranhaslett.toolbox.model;

import paranhaslett.toolbox.Editor;
import paranhaslett.toolbox.tools.Tool;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class Artifact {
    private static final Editor ed = Editor.getEd();
    private static long nextId = 1;
    private final Tool tool;
    private final long id;
    private final List<Artifact> contents = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public Artifact(Tool tool) {
        this.tool = tool;
        id = nextId;
        nextId++;

    }

    public Tool tool() {
        return tool;
    }

    public DefaultMutableTreeNode getTreeNode() {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode();
        treeNode.setUserObject(this);

        if (contents.size() > 0) {
            treeNode.setAllowsChildren(true);
            for (Artifact art : contents) {
                treeNode.add(art.getTreeNode());
            }
        }
        return treeNode;
    }

    public Artifact addData(String name) {
    	if (name == null){
    		throw new NullPointerException();
    	}
        data.add(name);
        return this;
    }

    public Artifact add(Artifact item) {
    	if (item == null){
    		throw new NullPointerException();
    	}
        if (tool.isDropOn(item.tool)) {
            contents.add(item);
        }
        return this;
    }

    public void update() {
    	if(data.size() == 0){
    		Artifact selected = (Artifact)ed.tree.getSelection().getUserObject();
    		// TODO addData(selected.tool);
    	}
    }

    public void setup() {
        TreePath path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, getTreeNode());
    }

    public void changeMainLabel(String newLabel) {
        data.set(0, newLabel);
        ed.editorUI.updatePanel(tool, this);

    }


    public boolean isDropOn(Artifact item) {
        return tool.isDropOn(item.tool);
    }

    public String getData(int ind) {
        return data.get(ind);
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return "" + id;
    }


}
