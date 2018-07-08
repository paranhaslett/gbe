package paranhaslett.toolbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import paranhaslett.gamebook.loader.EmaLoader;
import paranhaslett.gamebook.loader.XMLLoader;

public abstract class Tool extends JPanel {
	final List<Field> fields = new ArrayList<>();
	private final List<Tool> subTools = new ArrayList<>();
	private ImageIcon icon;;
	private String nameStr;
	private String iconStr;

	public Tool(String name, String iconStr) {
		this.nameStr = name;
		this.iconStr = iconStr;
	}

	public String name() {
		return nameStr;
	}

	public List<Tool> getSubTools() {
		return subTools;
	}

	public boolean isDropOn(Tool tool) {
		return subTools.contains(tool);
	}

	public Tool addTool(Tool tool) {
		subTools.add(tool);
		return this;
	}

	public ImageIcon icon() {
		if (icon != null) {
			return icon;
		}

		icon = createImageIcon("/icons/tree/" + iconStr + ".png");

		return icon;
	}

	public Tool addField(Field field) {
		fields.add(field);
		return this;
	}

	public abstract void fill(String[] data);

	public int numOfFields() {
		return fields.size();
	}

	public abstract void build();

	public abstract String compile(Compiler compiler);

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
