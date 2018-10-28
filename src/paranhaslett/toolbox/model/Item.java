package paranhaslett.toolbox.model;

import java.util.ArrayList;
import java.util.List;

import paranhaslett.toolbox.Config;

public class Item {
	private static final Config ed = Config.getEd();
	private final int toolId;
	private final Item tool;
	private final int id;
	private final List<Integer> subs = new ArrayList<>();
	private String name;

	public Item(int id, int toolId, String name){
		this.toolId = toolId;
		this.tool = Config.getEd().getTools().get(toolId);
		this.name = name;
		this.id = id;
	}

	public int toolId() {
		return toolId;
	}

	
	public Item add(Item item){
		if (isDropOn(item)){
			subs.add(item.id);
		}
		return this;
	}
	public void update() {
		tool.fill(this);
		Config.getEd().editorUI.updatePanel(this);
		//return tool.isDropOn(item.toolId());
	}


	public boolean isDropOn(Item item) {
		Item tool = Config.getEd().getTools().get(item.toolId());
		return tool.subs.contains(id);
	}
	
	public boolean isDropOn(int id){
		Item art = Config.getEd().getArts().get(id);
		return isDropOn(art);
	}


	public List<Integer> contents() {
		return subs;
	}

	@Override
	public String toString() {
		return ((Integer)id).toString();
	}

	
	public String name() {
		return name;
	}
	
	public void name(String name){
		this.name =name;
	}

	public List<Integer> getSubTools() {
		return this.subs;
	}

	public Item add(int id) {
		subs.add(id);
		return this;
	}

	public void fill(Item art){
		
	}

	public void build(){
		
	}

}
