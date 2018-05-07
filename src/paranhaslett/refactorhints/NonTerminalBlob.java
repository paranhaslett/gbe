package paranhaslett.refactorhints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonTerminalBlob implements Blob {
	String name;
	String hash;
	List<Blob> blobstr;

	public List<TerminatingBlob> getAllTerminatingBlobs(){
		List<TerminatingBlob> result = new ArrayList<>();
		for (Blob blob:blobstr){
			if(blob instanceof NonTerminalBlob){
				result.addAll(getAllTerminatingBlobs());
			} else {
				result.add((TerminatingBlob)blob);
			}
		}
		return result;
	}
	@Override
	public Map<Character, Integer> tally() {
		Map<Character, Integer> result = new HashMap<>();
		for (Blob blob : blobstr) {
			Map<Character, Integer> blobTally = blob.tally();
			for (Character key : blobTally.keySet()) {
				if (result.containsKey(key)) {
					result.put(key, blobTally.get(key) + blobTally.get(key));
				} else {
					result.put(key, blobTally.get(key));
				}
			}
		}
		return result;
	}

	@Override
	public String commonStr(List<String> strings, boolean isHead) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
