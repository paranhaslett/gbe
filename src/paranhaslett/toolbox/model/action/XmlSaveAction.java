package paranhaslett.toolbox.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;

public class XmlSaveAction extends CompileAction {
	
	public XmlSaveAction(Objective objective){
		super(objective);
	}
	
	public XmlSaveAction(){
		super(null);
	}
	
	
	@Override
	public String act(Artifact art) {
		Tool tool = art.tool();
		StringBuilder ssb = new StringBuilder("<art tool=\"").append(tool.name() + "\" id=\"")
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
}
