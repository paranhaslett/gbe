package paranhaslett.toolbox.model.action;

import java.util.List;
import java.util.Queue;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

interface Parser {
	Artifact parse(String artStr, Queue<String> remains, List<Tool> tools);
	String compile(Artifact art);

}
