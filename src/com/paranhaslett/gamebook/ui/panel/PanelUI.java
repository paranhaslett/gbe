package com.paranhaslett.gamebook.ui.panel;

import javax.swing.JPanel;

import com.paranhaslett.gamebook.model.ModelItem;

public abstract class PanelUI extends JPanel {
	private static final long serialVersionUID = 6754120900779746776L;

	public abstract void populatePanel(ModelItem modelItem);

	public abstract void populateModel();
}
