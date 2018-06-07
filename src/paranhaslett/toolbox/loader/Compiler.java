package paranhaslett.toolbox.loader;

import paranhaslett.toolbox.model.Artifact;

public interface Compiler {
	String compile(Artifact artifact);
	Artifact decompile(String str);

}
