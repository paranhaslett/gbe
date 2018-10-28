package paranhaslett.toolbox.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import paranhaslett.toolbox.Config;

public class Menu implements Iterable<Item> {
	List<Item> chest = new ArrayList<>();

	public int getId(Item art) {
		int id = chest.indexOf(art);
		if (id < 0) {
			chest.add(art);
			id = chest.size() - 1;
		}
		return id;
	}

	public Item add(int toolId, String name) {
		Item itm = new Item(chest.size(), toolId, name);
		chest.add(itm);
		return itm;
	}

	public Item get(int id) {
		return chest.get(id);
	}

	@Override
	public Iterator<Item> iterator() {
		return chest.iterator();
	}

	String save() {
		StringBuilder ssb = new StringBuilder();
		for (Item itm : Config.getEd().getArts()) {
			ssb.append(itm.name()).append(',').append(itm.toolId()).append(',');
			for (int contId : itm.contents()) {
				ssb.append(contId).append(',');
			}
			ssb.append('\n');
		}
		return ssb.toString();
	}
	
	void load(String[] strs) {
		int id = 0;
		for (String str:strs){
			String[] split = str.split(",");
			Item itm = add(Integer.parseInt(split[1]), split[0]);
			for (int i=1; i<split.length; i++ ){
				
			}
		}
		StringBuilder ssb = new StringBuilder();
		for (Item itm : Config.getEd().getArts()) {
			ssb.append(itm.name()).append(',').append(itm.toolId()).append(',');
			for (int contId : itm.contents()) {
				ssb.append(contId).append(',');
			}
			ssb.append('\n');
		}
		
	}
}
