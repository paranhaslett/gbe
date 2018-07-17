package paranhaslett.tooltoolbox;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.FormTool;
import paranhaslett.toolbox.model.TextField;
import paranhaslett.toolbox.model.Tool;
import paranhaslett.toolbox.model.action.ParseAction;
import paranhaslett.toolbox.model.action.XmlParseAction;

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
		
		super.build(toolBoxTool);

		
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
