package paranhaslett.toolbox.model.action;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Item;

public class XmlSaveAction extends CompileAction {
	
	public XmlSaveAction(Objective objective){
		super(objective);
	}
	
	public XmlSaveAction(){
		super(null);
	}
	
	
	@Override
	public String act(Integer artId) {
		Item art = Config.getEd().getArts().get(artId);
		Item tool = Config.getEd().getTools().get(art.toolId());
		StringBuilder ssb = new StringBuilder("<art tool=\"").append(tool.name() + "\" id=\"")
				.append(art.toString()).append("\">\n");
		for (Integer subArt : art.contents()){
			if (objective != null){	
				ssb.append(objective.act(subArt));
			} else {
				ssb.append(act(subArt));
			}
		}
		ssb.append("</art>\n");

		return ssb.toString();
	}

}
