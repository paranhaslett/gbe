package paranhaslett.refactorhints;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RefactorHints {

	public RefactorHints(List<Blob> blobs) {
		Map<Character, Integer> hist = new HashMap<>();
		for (Blob blob : blobs) {
			hist = total(hist, blob.tally());
		}
		int minGreaterThanOne = 1;
		List<Character> minimal = new ArrayList<>();
		for (Entry<Character, Integer> entry : hist.entrySet()) {
			if (entry.getValue() != 1) {
				if (entry.getValue() < minGreaterThanOne) {
					minimal.clear();
					minGreaterThanOne = entry.getValue();
				}

				if (minGreaterThanOne == 1 || entry.getValue() == minGreaterThanOne) {
					minimal.add(entry.getKey());
					minGreaterThanOne = entry.getValue();
				}
			}
			// System.out.println(entry.getKey() + " " + entry.getValue());
		}

		System.out.println(minGreaterThanOne);

		for (Character c : minimal) {
			System.out.println(c);
		}
	}

	public Map<Character, Integer> total(Map<Character, Integer> t1, Map<Character, Integer> t2) {
		Map<Character, Integer> result = new HashMap<>(t1);
		for (Character key : t2.keySet()) {
			if (result.containsKey(key)) {
				result.put(key, t1.get(key) + t2.get(key));
			} else {
				result.put(key, t2.get(key));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			List<Blob> blobs = new ArrayList<>();
			File file = new File(args[0]);
			if (file.exists()) {

				if (file.isDirectory()) {
					File[] files = file.listFiles();
					for (File nfile : files) {

					}
				} else {
					try {
						BufferedReader bfr = new BufferedReader(new FileReader(file));
						StringBuilder content = new StringBuilder();
					    String line = bfr.readLine();
						while (line !=null) {
					    	content.append(line).append("/n");
					    }
						TerminatingBlob blob = new TerminatingBlob();
						blob.name = file.getAbsolutePath();
						blob.content = content.toString();
						blobs.add(blob);
					} catch (IOException ioex) {

					}
				}
			}
			new RefactorHints(blobs);
		}
	}
	

	public List<Blob> mostCommonBlob(List<Blob> strings, boolean isHead) {
		if (strings != null && strings.size() > 0) {
			if (isHead) {

			} else {

			}
		}
		return null;
	}

}
