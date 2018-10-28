package paranhaslett.toolbox.model.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import paranhaslett.gamebook.model.Item;
import paranhaslett.toolbox.model.Tool;

public class Objective {

	private Map<Integer, Action> actions = new HashMap<>();
	private Action defaultAct;
	private String name;

	public Objective(String name) {
		this.name = name;
	}

	public void put(Action act) {
		defaultAct = act;
	}

	public void put(Integer toolid, Action act) {
		actions.put(toolid, act);
	}

	public String act(Integer from){
		Action action = actions.get(from);
		 return null;
		//parser.parse();
	}
	
	public Item act(Integer to, List<String> tokens){
		return null;
	}

	

}
