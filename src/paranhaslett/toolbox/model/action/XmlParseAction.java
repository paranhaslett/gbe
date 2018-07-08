package paranhaslett.toolbox.model.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

public class XmlParseAction extends ParseAction {
	
	public XmlParseAction(Objective objective){
		super(objective);
	}
	
	
	@Override
	public Artifact act(String artStr, Queue<String> remains, List<Tool> tools) {
		Pattern pat = Pattern.compile("<art tool=\"(.*)\" id=\"(.*)\">");
		Matcher mat = pat.matcher(artStr);
		if (!mat.matches()) {
			return null;
		}
		Tool tool = null;
		for (Tool sel : tools) {
			if (sel.name().equals(mat.group(1))) {
				tool = sel;
			}
		}

		Artifact art = new Artifact(tool);
		List<String> data = new ArrayList<>();
		if (remains.size() > 0) {
			String line = remains.poll();

			while (remains.size() > 0 && !line.equals("</art>")) {

				if (line.startsWith("<art tool=\"")) {
					art.add(act(line, remains, art.tool().getSubTools()));
				} else if (line.equals("<data>")) {
					line = remains.poll();
					StringBuilder lines = null;
					while (remains.size() > 0 && !line.equals("</data>")) {
						if (lines != null) {
							lines.append("\n");
						} else {
							lines = new StringBuilder();
						}
						lines.append(line);
						line = remains.poll();	
					}
					data.add(lines.toString());
				}
				line = remains.poll();
			}
		}
		art.setData(data.toArray(new String[data.size()]));
		return art;
	}


	@Override
	public Artifact act(Artifact to, List<String> tokens) {
		List<Tool> tools = new ArrayList<>();
		if (to == null){
			tools.add(Config.getEd().getRootTool());
		} else {
			tools.add(to.tool());
		}
		   
		Queue<String> remains = new LinkedList<>(tokens);
		return act(remains.poll(), remains, tools);
	}

}
