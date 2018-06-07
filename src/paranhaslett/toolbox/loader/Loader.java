package paranhaslett.toolbox.loader;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

import java.io.File;
import java.util.List;

public interface Loader {

    @SuppressWarnings("UnusedReturnValue")
    Artifact load(File file, Tool tool);

    void save(File file, Artifact item);

    String getText(String key);

    void setText(String key, String value);

    List<Loader> getChildren(String... childrenKeys);

    Loader getChild(String childKey);

    Loader create(String key);

    String getName();


}
