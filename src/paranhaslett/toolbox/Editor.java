package paranhaslett.toolbox;

import javax.swing.filechooser.FileFilter;

import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.tools.Tool;
import paranhaslett.toolbox.tree.TreeUI;
import paranhaslett.toolbox.ui.EditorUI;
import paranhaslett.toolbox.ui.FileChooserUI;
import paranhaslett.toolbox.ui.FileChooserUI.EmaFilter;
import paranhaslett.toolbox.ui.FileChooserUI.GameBookFilter;

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
