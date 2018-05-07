package com.paranhaslett.gamebook;

import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.ui.EditorUI;
import com.paranhaslett.gamebook.ui.FileChooserUI;
import com.paranhaslett.gamebook.ui.FileChooserUI.EmaFilter;
import com.paranhaslett.gamebook.ui.FileChooserUI.GameBookFilter;
import com.paranhaslett.gamebook.ui.tree.TreeUI;

import javax.swing.filechooser.FileFilter;
import java.awt.*;

public class Editor {
    private static Editor editor = null;
    public Library library;
    public TreeUI tree;
    public EditorUI editorUI;
    private FileChooserUI fileChooser;

    private Editor() {
    }

    public static Editor getEd() {
        if (editor == null) {
            editor = new Editor();
            editor.init();

        }
        return editor;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                getEd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void init() {
        tree = new TreeUI();
        fileChooser = new FileChooserUI();
        editorUI = new EditorUI();
        editorUI.setVisible(true);
        library = new Library();
        library.setup(editor);
    }

    public Loader getLoader() {
        FileFilter filter = fileChooser.getFileFilter();
        Loader loader = null;
        if (filter instanceof GameBookFilter) {
            loader = Loadable.xmlLoader;
        }
        if (filter instanceof EmaFilter) {
            loader = Loadable.emaLoader;
        }
        return loader;
    }

    public void setupLibraryTree() {
        tree.setup(library);
        editorUI.updateTree(tree);
    }

    public void update() {
        tree.update();
    }

}
