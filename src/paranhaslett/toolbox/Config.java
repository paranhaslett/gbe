package paranhaslett.toolbox;


import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Menu;
import paranhaslett.toolbox.tree.TreeUI;
import paranhaslett.toolbox.ui.EditorUI;
import paranhaslett.toolbox.ui.FileChooserUI;

public class Config {
	private static Config editor = null;
	private TreeUI tree = null;
	private final FileChooserUI fileChooser;
	public EditorUI editorUI;
	protected Menu toolChest = new Menu();
	protected Menu artChest = new Menu();
	//private List<Tool> tools= new ArrayList<>(); 
	//private List<Artifact> arts = new ArrayList();
	

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

	public void build(Artifact root) {
		tree.setup(root);
		editorUI.updateTree(tree);
	}

	protected void build(String name, Artifact root) {
		editorUI = new EditorUI(name);
		editorUI.setVisible(true);

		tree.setup(root);
		editorUI.updateTree(tree);

	}

	public void update() {
		tree.update();
	}

	public Menu getTools(){
		return toolChest;
	}
	
	public Menu getArts(){
		return artChest;
	}

	
	
}
