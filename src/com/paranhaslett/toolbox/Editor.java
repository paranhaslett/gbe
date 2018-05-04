package com.paranhaslett.toolbox;

import com.paranhaslett.toolbox.loader.Loader;
import com.paranhaslett.toolbox.model.Artifact;
import com.paranhaslett.toolbox.tools.Tool;
import com.paranhaslett.toolbox.tree.TreeUI;
import com.paranhaslett.toolbox.ui.EditorUI;
import com.paranhaslett.toolbox.ui.FileChooserUI;
import com.paranhaslett.toolbox.ui.FileChooserUI.EmaFilter;
import com.paranhaslett.toolbox.ui.FileChooserUI.GameBookFilter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Editor {
    private static Editor editor = null;
    public final TreeUI tree;
    private final FileChooserUI fileChooser;
    public EditorUI editorUI;

    protected Editor() {
        tree = new TreeUI();
        fileChooser = new FileChooserUI();
        editor = this;
    }

    public static Editor getEd() {
        return editor;
    }

    @SuppressWarnings("SameParameterValue")
    protected void build(String name, Tool rootTool) {
        Artifact root = new Artifact(rootTool);
        editorUI = new EditorUI(name);
        editorUI.setVisible(true);

        tree.setup(root);
        editorUI.updateTree(tree);

    }

    private void load(Artifact root) {
        File file = new File("./library.xml");
        Loader loader = getLoader();
        if (loader != null) {
            loader.load(file, root.tool());
        }

    }

    private Loader getLoader() {
        FileFilter filter = fileChooser.getFileFilter();
        Loader loader = null;
        if (filter instanceof GameBookFilter) {
            loader = Tool.xmlLoader;
        }
        if (filter instanceof EmaFilter) {
            loader = Tool.emaLoader;
        }
        return loader;
    }

    public void update() {
        tree.update();
    }

}
