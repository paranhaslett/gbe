package paranhaslett.toolbox.tools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.fields.Field;
import paranhaslett.toolbox.loader.EmaLoader;
import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.loader.XMLLoader;
import paranhaslett.toolbox.model.Artifact;

public abstract class Tool extends JPanel {
    public static final XMLLoader xmlLoader = new XMLLoader();
    public static final EmaLoader emaLoader = new EmaLoader();
    final List<Field> fields = new ArrayList<>();
    private final List<Tool> subTools = new ArrayList<>();
    private DefaultTreeCellRenderer tcr;
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

    public TreeCellRenderer icon() {
    	if (tcr != null){
    		return tcr;
    	}
    	
    	ImageIcon icon = createImageIcon("/icons/tree/" + iconStr + ".png");
    	if (icon != null) {
    	    tcr = 
    	        new DefaultTreeCellRenderer();
    	    tcr.setIcon(icon);
    	    return tcr;
    	}
        return null;
    }

    public abstract Artifact load(Loader ff);

    public abstract void save(Loader ff, Artifact item);

    public Tool addField(Field field) {
        fields.add(field);
        return this;
    }

    public abstract void populateModel();

    public abstract void populatePanel(Artifact item);
    
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
