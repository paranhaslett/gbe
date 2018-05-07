package paranhaslett.refactorhints;

import java.util.List;
import java.util.Map;

public interface Blob {
	Map<Character, Integer> tally();
	String commonStr(List<String> strings, boolean isHead);
}
