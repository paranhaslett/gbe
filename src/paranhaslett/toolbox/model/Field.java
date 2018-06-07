package paranhaslett.toolbox.model;

import java.awt.Container;

public interface Field {
	Container getGui();
	Field fill(String[] data, int idx);
}
