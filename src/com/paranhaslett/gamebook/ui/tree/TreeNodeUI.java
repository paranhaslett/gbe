package com.paranhaslett.gamebook.ui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import com.paranhaslett.gamebook.model.ModelItem;

public class TreeNodeUI extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 9020576980406733512L;
	private ModelItem previousValue;
	
	public TreeNodeUI(ModelItem modelItem) {
		super(modelItem, true);
		previousValue = modelItem;	
	}
		
	 public void setUserObject(Object userObject) {
			if (userObject instanceof ModelItem){
				this.userObject = userObject;
				this.previousValue=(ModelItem)userObject;
			} else if (userObject instanceof String){
				previousValue.getController().changeMainLabel(previousValue, (String) userObject);
				this.userObject = previousValue;
			}
	 }
	

}
