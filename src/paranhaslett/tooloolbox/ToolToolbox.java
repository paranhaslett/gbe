package paranhaslett.tooloolbox;

import java.awt.*;

import paranhaslett.toolbox.Editor;
import paranhaslett.toolbox.fields.TextField;
import paranhaslett.toolbox.tools.FormTool;
import paranhaslett.toolbox.tools.Tool;

class ToolToolbox extends Editor {

    private ToolToolbox() {
        super();

        Tool fieldTool = new FormTool();
        fieldTool.addField(new TextField("Name"));
        fieldTool.addField(new TextField("Type"));

        Tool toolTool = new FormTool();
        toolTool.addIcon("book");
        toolTool.addField(new TextField("Name"));
        toolTool.addField(new TextField("Type"));
        toolTool.addField(new TextField("Icon"));
        toolTool.addTool(fieldTool);

        Tool toolBoxTool = new FormTool();
        toolBoxTool.addTool(toolTool);
        toolBoxTool.addField(new TextField("Root Name"));
        toolBoxTool.addField(new TextField("Icon"));

        build("Toolbox", toolBoxTool);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            try {
                new ToolToolbox();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
