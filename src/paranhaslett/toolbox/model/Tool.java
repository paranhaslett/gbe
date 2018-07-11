package paranhaslett.toolbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class Tool extends JPanel {
	final List<Field> fields = new ArrayList<>();
	protected final List<Tool> subTools = new ArrayList<>();
	protected Artifact parent;
	private ImageIcon icon;
	private String nameStr;
	private String iconStr;

	public Tool(String name, String iconStr) {
		this.nameStr = name;
		this.iconStr = iconStr;
	}

	public String name() {
		return this.nameStr;
	}

	public List<Tool> getSubTools() {
		return this.subTools;
	}

	public boolean isDropOn(Tool tool) {
		return this.subTools.contains(tool);
	}

	public Tool addTool(Tool tool) {
		this.subTools.add(tool);
		return this;
	}

	public ImageIcon icon() {
		if (this.icon != null) {
			return this.icon;
		}

		this.icon = createImageIcon("/icons/tree/" + this.iconStr + ".png"); //$NON-NLS-1$ //$NON-NLS-2$

		return this.icon;
	}

	public Tool addField(Field field) {
		this.fields.add(field);
		return this;
	}

	public abstract void fill(Artifact art, String[] data);

	public int numOfFields() {
		return this.fields.size();
	}

	public abstract void build();

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		System.err.println("Couldn't find file: " + path);
		return null;
	}
}
