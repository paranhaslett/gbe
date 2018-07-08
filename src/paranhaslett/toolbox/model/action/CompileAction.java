package paranhaslett.toolbox.model.action;

import paranhaslett.toolbox.model.Artifact;

public abstract class CompileAction implements Action{

	Objective objective;
	
	public CompileAction (Objective objective){
		this.objective = objective;
	}
	abstract String act(Artifact art);
}
