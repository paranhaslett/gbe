package paranhaslett.tooltoolbox;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.fields.TextField;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.tools.FormTool;
import paranhaslett.toolbox.tools.Tool;

import java.awt.*;

class ToolToolbox extends Config {

    private ToolToolbox() {
        super();
    }
    
    protected void init(){
        Tool fieldTool = new FormTool("Field");
        fieldTool.addField(new TextField("Name"));
        fieldTool.addField(new TextField("Type"));

        Tool toolTool = new FormTool("Tool","book");
        toolTool.addField(new TextField("Name"));
        toolTool.addField(new TextField("Type"));
        toolTool.addField(new TextField("Icon"));
        toolTool.addTool(fieldTool);

        Tool toolBoxTool = new FormTool("Toolbox","set");
        toolBoxTool.addTool(toolTool);
        toolBoxTool.addField(new TextField("Root Name"));
        toolBoxTool.addField(new TextField("Icon"));
        
        //setUp
        Artifact tool = new Artifact(toolTool);   
        Artifact root = new Artifact(toolBoxTool);
        root.add(tool);

        super.build("Toolbox", root);
        
        editorUI.updatePanel(root);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            try {
                ToolToolbox ttb = new ToolToolbox();
                ttb.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
