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
    final List<Field> fields = new ArrayList<>();
    private final List<Tool> subTools = new ArrayList<>();
    private Icon icon;
    private String name;
    private String iconStr;
    
    public Tool(String name, String iconStr){
    	this.name = name;
    	this.iconStr = iconStr;
    }
    
    public String name(){
    	return name;
    }

    public boolean isDropOn(Tool tool){
        return subTools.contains(tool);
    }

    public Tool addTool(Tool tool) {
        subTools.add(tool);
        return this;
    }

    public Icon icon() {
    	if (icon == null){
    		 icon = Editor.getEd().tree.getTreeRenderer().createImageIcon("/icons/tree/" + iconStr + ".png");
    	}
        return icon;
    }

    public abstract Artifact load(Loader ff);

    public abstract void save(Loader ff, Artifact item);

    @SuppressWarnings("UnusedReturnValue")
    public Tool addField(Field field) {
        fields.add(field);
        return this;
    }

    public abstract void populateModel();

    public abstract void populatePanel(Artifact item);
}
