package paranhaslett.toolbox.tree;


import javax.swing.tree.DefaultMutableTreeNode;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;

public class TreeNodeUI extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 9020576980406733512L;
    private Artifact previousValue;

    public TreeNodeUI(Artifact item) {
        super(item, true);
        previousValue = item;
    }

    public void setUserObject(Object userObject) {
        if (userObject instanceof Artifact) {
            this.userObject = userObject;
            this.previousValue = (Artifact) userObject;
        } else if (userObject instanceof String) {
        	previousValue.getData()[0] = userObject.toString();
    		Config.getEd().editorUI.updatePanel(previousValue);
            //
            this.userObject = previousValue;
        }
    }

}
