package paranhaslett.toolbox.tools;

import paranhaslett.toolbox.Editor;
import paranhaslett.toolbox.fields.Field;
import paranhaslett.toolbox.loader.EmaLoader;
import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.loader.XMLLoader;
import paranhaslett.toolbox.model.Artifact;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tool extends JPanel {
    public static final XMLLoader xmlLoader = new XMLLoader();
    public static final EmaLoader emaLoader = new EmaLoader();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Field> fields = new ArrayList<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Tool> subTools = new ArrayList<>();
    private Icon icon;

    @SuppressWarnings("UnusedReturnValue")
    public Tool addIcon(String iconStr) {
        icon = Editor.getEd().tree.getTreeRenderer().createImageIcon("/icons/tree/" + iconStr + ".png");
        return this;
    }

    public boolean isDropOn(Tool tool){
        return subTools.contains(tool);
    }



    @SuppressWarnings("UnusedReturnValue")
    public Tool addTool(Tool tool) {
        subTools.add(tool);
        return this;
    }

    public Icon icon() {
        return icon;
    }

    @SuppressWarnings("SameReturnValue")
    public abstract Artifact load(Loader ff);

    @SuppressWarnings("EmptyMethod")
    public abstract void save(Loader ff, Artifact item);


    @SuppressWarnings("UnusedReturnValue")
    public Tool addField(Field field) {
        fields.add(field);
        return this;
    }

    public abstract void populateModel();

    public abstract void populatePanel(Artifact item);
}
