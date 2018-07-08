package paranhaslett.toolbox.model.action;

import java.util.List;
import java.util.Queue;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

public abstract class ParseAction implements Action{

	Objective objective;
	
	public ParseAction (Objective objective){
		this.objective = objective;
	}
	
    public abstract Artifact act(Artifact to, List<String> lines);
	
	abstract Artifact act(String line, Queue<String> remains, List<Tool> tools);
}
