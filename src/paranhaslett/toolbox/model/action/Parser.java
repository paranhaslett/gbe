package paranhaslett.toolbox.model.action;

import java.util.List;
import java.util.Queue;

import paranhaslett.gamebook.model.Item;

interface Parser {
	Item parse(String artStr, Queue<String> remains, List<Integer> tools);
	String compile(Item art);

}
