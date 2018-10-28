package paranhaslett.toolbox.ui.tree;


import javax.swing.tree.DefaultMutableTreeNode;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Item;

public class TreeNodeUI extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 9020576980406733512L;
    private Item previousValue;


    public TreeNodeUI(Item item) {
    	super(item, true);
        previousValue = item;
	}

	public void setUserObject(Object userObject) {
        if (userObject instanceof Item) {
            this.userObject = userObject;
            this.previousValue = (Item) userObject;
        } else if (userObject instanceof String) {
        	previousValue.name(userObject.toString());
    		Config.getEd().editorUI.updateTree(previousValue);
            //
            this.userObject = previousValue;
        }
    }

}
