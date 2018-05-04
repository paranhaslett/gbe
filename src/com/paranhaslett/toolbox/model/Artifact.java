package com.paranhaslett.toolbox.model;

import com.paranhaslett.toolbox.Editor;
import com.paranhaslett.toolbox.tools.Tool;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class Artifact {
    private static final Editor ed = Editor.getEd();
    private static long nextId = 1;
    private final Tool tool;
    private final long id;
    private final List<Artifact> contents = new ArrayList<>();
    private List<String> data;

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
        data.add(name);
        return this;
    }

    @SuppressWarnings("EmptyMethod")
    public void add(Artifact item) {
        //TODO
        // merge into series
        // change to page controller
        // add newPage to parent of selection

    }

    @SuppressWarnings("EmptyMethod")
    public void update() {
    }

    public void setup() {
        TreePath path = ed.tree.getSelectLoc();
        ed.tree.addToPath(path, getTreeNode());
    }

    public void changeMainLabel(String newLabel) {
        data.set(0, newLabel);
        ed.editorUI.updatePanel(tool, this);

    }


    @SuppressWarnings("SameReturnValue")
    public boolean isDropOn(Artifact item) {
        return false;
    }

    public String getData(int ind) {
        return data.get(ind);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        if (data == null || data.size() == 0) {
            return tool.toString();
        }
        return data.get(0);
    }

    public String toString() {
        return "" + id;
    }

    public List<Artifact> contents() {
        return contents;
    }

}
