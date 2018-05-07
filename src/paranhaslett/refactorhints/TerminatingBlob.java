package paranhaslett.refactorhints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminatingBlob implements Blob {
	String name;
	String hash;
	String content;
	boolean inline = true;
	
	// this is an in tellying blob
	
	public Map <Character, Integer> tally(){
		Map <Character, Integer> map = new HashMap<>();
		for(int i=0; i<content.length(); i++){
			char c = content.charAt(i);
			if (map.keySet().contains(c)){
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}
	
	public String commonStr(List <String> strings, boolean isHead){
		if (strings != null && strings.size()>0){
			if (isHead){
				
			} else {
				
			}
		}
		return null;
	}

	public void toggleInline() {
		//if an inline is resolved it is bubbled up
		inline = !inline;
		
	}
	
	public NonTerminalBlob splitBlob(String splitPoint, NonTerminalBlob parent){
		List<Blob> ntb = new ArrayList<>();
	    String[] split = content.split(splitPoint);
	    TerminatingBlob splitBlob = new TerminatingBlob();
		splitBlob.content = splitPoint;
		splitBlob.inline = false;
	    boolean first = true;
	    for(String si:split){
	    	if (first){
	    		first = false;
	    	} else {
	    		ntb.add(splitBlob);
	    	}
	    	TerminatingBlob newBlob = new TerminatingBlob();
			newBlob.content = si;
	    	ntb.add(newBlob);
	    	
	    }
	    if (inline){
	    	//TODO insert into parent
	    } else {
	    	NonTerminalBlob nb = new NonTerminalBlob();
	    	nb.blobstr = ntb;
	    	return nb;
	    }
		return null;
	}

}
