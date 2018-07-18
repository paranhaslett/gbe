package paranhaslett.toolbox.model.action;

import java.util.ArrayList;
import java.util.List;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

public class ArtSaveAction extends CompileAction {
	
	public ArtSaveAction(Objective objective){
		super(objective);
	}
	
	public ArtSaveAction(){
		super(null);
	}
	
	private void toolList(List<Artifact> arts, List<Tool> tools){
		
	}
	
	private void artList(Artifact art, List<Artifact> tools){
		if (!tools.contains(art)){
			tools.add(art);
			for (Artifact subArt : art.contents()){
				artList(subArt, tools);
			}
		}
	}
	
	private String save(Artifact art, List<Artifact> arts, List<Tool> tools){
		Tool tool = art.tool();
		
		StringBuilder ssb = new StringBuilder("<art tool=\"")
				.append(tool.name())
				.append(art.toString()).append("\">\n");
		for (String datum : art.getData()) {
			ssb.append("<data>\n").append(datum).append("\n</data>\n");
		}
		for (Artifact subArt : art.contents()){
			if (objective != null){
				ssb.append(objective.act(subArt));
			} else {
				ssb.append(act(subArt));
			}
		}
		ssb.append("</art>\n");

		return ssb.toString();
	}
	
	@Override
	public String act(Artifact art) {
		List<Artifact> arts =  new ArrayList<>();
		artList(art, arts);
		List<Tool> tools = new ArrayList<>();
		for (Artifact subArt : arts){
			if (!tools.contains(subArt.tool())){
				tools.add(subArt.tool());	
			}
		}
		
		return save(art,arts, tools);
	}
}
