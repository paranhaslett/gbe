package mafiamaniac.net.mafiatools;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.DefaultTools;
import paranhaslett.toolbox.model.FormTool;
import paranhaslett.toolbox.model.Item;
import paranhaslett.toolbox.model.Tool;
import paranhaslett.toolbox.model.action.ParseAction;
import paranhaslett.toolbox.model.action.XmlParseAction;
import paranhaslett.toolbox.ui.tools.TextFieldUI;

public class MafiaTools extends Config{

	protected void init() {
		Item mafiaTB = toolChest.add(DefaultTools.TOOLBOX.ordinal(), "Mafia");
		Item roleTool = toolChest.add(DefaultTools.TOOL.ordinal(), "Role");
		Item stateTool = toolChest.add(DefaultTools.TOOL.ordinal(), "State");
		Item actionTool = toolChest.add(DefaultTools.TOOL.ordinal(), "Action");
		Item mafiaIcon = toolChest.add(DefaultTools.ICON.ordinal(), "mafia");
		Item roleIcon = toolChest.add(DefaultTools.ICON.ordinal(), "mafia");
		Item stateIcon = toolChest.add(DefaultTools.ICON.ordinal(), "mafia");
		
		mafiaTB.add(mafiaIcon);
		mafiaTB.add(roleTool);
		mafiaTB.add(stateTool);	
		
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
				MafiaTools ttb = new MafiaTools();
				ttb.init();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	

}
