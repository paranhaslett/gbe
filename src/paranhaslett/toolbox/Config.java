package paranhaslett.toolbox;

import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.tools.Tool;
import paranhaslett.toolbox.tree.TreeUI;
import paranhaslett.toolbox.ui.EditorUI;
import paranhaslett.toolbox.ui.FileChooserUI;
import paranhaslett.toolbox.ui.FileChooserUI.EmaFilter;
import paranhaslett.toolbox.ui.FileChooserUI.GameBookFilter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Config {
    private static Config editor = null;
    private TreeUI tree = null;
    private final FileChooserUI fileChooser;
    public EditorUI editorUI;

    public Config() {
        tree = new TreeUI();
        fileChooser = new FileChooserUI();
        editor = this;
    }

    public TreeUI tree(){
    	if (tree == null){
    		tree = new TreeUI();
    	}
    	return tree;
    }
    public static Config getEd() {
        return editor;
    }

    protected void build(String name, Artifact root) {
       
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
