package paranhaslett.toolbox.model.action;

import java.util.List;
import java.util.Queue;

import paranhaslett.toolbox.model.Item;

public abstract class ParseAction implements Action{

	Objective objective;
	
	public ParseAction (Objective objective){
		this.objective = objective;
	}
	
    public abstract Item act(Integer to, List<String> lines);
	
	abstract Item actid(String artStr, Queue<String> remains, List<Integer> tools);
}
