package paranhaslett.toolbox;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;
import paranhaslett.toolbox.tree.TreeUI;
import paranhaslett.toolbox.ui.EditorUI;
import paranhaslett.toolbox.ui.FileChooserUI;

public class Config {
	private static Config editor = null;
	private TreeUI tree = null;
	private final FileChooserUI fileChooser;
	public EditorUI editorUI;
	private Artifact rootArt;
	private Tool rootTool;

	public Config() {
		tree = new TreeUI();
		fileChooser = new FileChooserUI();
		editor = this;
	}

	public TreeUI tree() {
		if (tree == null) {
			tree = new TreeUI();
		}
		return tree;
	}

	public static Config getEd() {
		return editor;
	}

	protected void build(Tool tool) {
		rootTool = tool;
	}

	public void build(Artifact root) {
		rootArt = root;
		tree.setup(root);
		editorUI.updateTree(tree);
	}

	protected void build(String name, Artifact root) {
		rootArt = root;
		rootTool = root.tool();
		editorUI = new EditorUI(name);
		editorUI.setVisible(true);

		tree.setup(root);
		editorUI.updateTree(tree);

	}

	public void update() {
		tree.update();
	}

	public Tool getRootTool() {
		return rootTool;
	}

}
