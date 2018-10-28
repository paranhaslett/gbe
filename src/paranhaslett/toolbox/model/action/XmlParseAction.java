package paranhaslett.toolbox.model.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Item;

public class XmlParseAction extends ParseAction {
	
	public XmlParseAction(Objective objective){
		super(objective);
	}
	
	
	@Override
	public Item actid(String artStr, Queue<String> remains, List<Integer> tools) {
		Pattern pat = Pattern.compile("<art tool=\"(.*)\" id=\"(.*)\">");
		Matcher mat = pat.matcher(artStr);
		if (!mat.matches()) {
			return null;
		}
		Item tool = null;
		for (Integer selid : tools) {
			Item sel = Config.getEd().getTools().get(selid);
			if (sel.name().equals(mat.group(1))) {
				tool = sel;
			}
		}
        if (tool == null){
        	return null;
        }
		
		Item art = Config.getEd().getTools().add(tool.toolId(), "");
		
		List<String> data = new ArrayList<>();
		if (remains.size() > 0) {
			String line = remains.poll();

			while (remains.size() > 0 && !line.equals("</art>")) {

				if (line.startsWith("<art tool=\"")) {
					List<Integer> subTools = Config.getEd().getTools().get(art.toolId()).getSubTools();
					art.add(actid(line, remains,subTools));
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
					if (lines != null){
						data.add(lines.toString());
					}
				}
				line = remains.poll();
			}
		}
		//art.setData(data.toArray(new String[data.size()]));
		return art;
	}


	@Override
	public Item act(Integer to, List<String> tokens) {
		List<Integer> tools = new ArrayList<>();
		if (to == null){
			tools.add(0);
		} else {
			tools.add(to);
		}
		   
		Queue<String> remains = new LinkedList<>(tokens);
		return actid(remains.poll(), remains, tools);
	}

}
