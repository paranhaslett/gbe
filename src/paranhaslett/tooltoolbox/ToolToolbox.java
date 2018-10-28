package paranhaslett.tooltoolbox;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.FormTool;
import paranhaslett.toolbox.model.Tool;
import paranhaslett.toolbox.model.action.ParseAction;
import paranhaslett.toolbox.model.action.XmlParseAction;
import paranhaslett.toolbox.ui.tools.TextFieldUI;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

class ToolToolbox extends Config {

	private ToolToolbox() {
		super();
	}

	protected void init() {
		Tool fieldTool = toolChest.add(toolId, name)("Field", "page");
		fieldTool.addField(new TextFieldUI("Name"));
		fieldTool.addField(new TextFieldUI("Type"));
		fieldTool.build();

		Tool toolTool = new FormTool("Tool");
		toolTool.addField(new TextFieldUI("Name"));
		toolTool.addField(new TextFieldUI("Type"));
		toolTool.addField(new TextFieldUI("Icon"));
		toolTool.addTool(fieldTool);
		toolTool.build();

		Tool toolBoxTool = new FormTool("Toolbox");
		toolBoxTool.addTool(toolTool);
		toolBoxTool.addField(new TextFieldUI("Root Name"));
		toolBoxTool.addField(new TextFieldUI("Icon"));
		toolBoxTool.build();
		

		
		 File file = new File("/root/tools.xml");
         BufferedReader bfr = null;
         List<String> tokens = new LinkedList<>();
         try {
        	FileReader fr = new FileReader(file);
				bfr = new BufferedReader(fr);
				String line = bfr.readLine();
				if (line.equals("<tb>")){
					line = bfr.readLine();
					while (!line.equals("</tb>")){
						tokens.add(line);
					line = bfr.readLine();
					}
					
					ParseAction parse = new XmlParseAction(null);
					
					Artifact art = parse.act(null, tokens);
					build(art.getData()[0], art);
				}
         } catch (Exception e1){
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				if (bfr != null){
					try {
						bfr.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
         
		// setUp
         /*
		Artifact tool = new Artifact(toolTool);
		String[] data = { "Form Tool", "FormTool", "tool" };
		tool.setData(data);
		Artifact root = new Artifact(toolBoxTool);
		String[] rootdata = { "Toolbox", "toolbox" };
		root.setData(rootdata);
		root.add(tool); */

		//super.build("Toolbox", root);

		
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
