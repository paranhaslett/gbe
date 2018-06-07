package paranhaslett.tooltoolbox;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.FormTool;
import paranhaslett.toolbox.model.TextField;
import paranhaslett.toolbox.model.Tool;

import java.awt.*;

class ToolToolbox extends Config {

	private ToolToolbox() {
		super();
	}

	protected void init() {
		Tool fieldTool = new FormTool("Field", "page");
		fieldTool.addField(new TextField("Name"));
		fieldTool.addField(new TextField("Type"));
		fieldTool.build();

		Tool toolTool = new FormTool("Tool");
		toolTool.addField(new TextField("Name"));
		toolTool.addField(new TextField("Type"));
		toolTool.addField(new TextField("Icon"));
		toolTool.addTool(fieldTool);
		toolTool.build();

		Tool toolBoxTool = new FormTool("Toolbox");
		toolBoxTool.addTool(toolTool);
		toolBoxTool.addField(new TextField("Root Name"));
		toolBoxTool.addField(new TextField("Icon"));
		toolBoxTool.build();

		// setUp
		Artifact tool = new Artifact(toolTool);
		String[] data = { "Form Tool", "FormTool", "tool" };
		tool.setData(data);
		Artifact root = new Artifact(toolBoxTool);
		String[] rootdata = { "Toolbox", "toolbox" };
		root.setData(rootdata);
		root.add(tool);

		super.build("Toolbox", root);

		
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			try {
				ToolToolbox ttb = new ToolToolbox();
				ttb.init();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
