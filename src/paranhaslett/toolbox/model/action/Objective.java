package paranhaslett.toolbox.model.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

public class Objective {

	private Map<Tool, Action> actions = new HashMap<>();
	private Action defaultAct;
	private String name;

	public Objective(String name) {
		this.name = name;
	}

	public void put(Action act) {
		defaultAct = act;
	}

	public void put(Tool tool, Action act) {
		actions.put(tool, act);
	}

	public String act(Artifact from){
		Action action = actions.get(from.tool());
		 return null;
		//parser.parse();
	}
	
	public Artifact act(Artifact to, List<String> tokens){
		return null;
	}

	

}
