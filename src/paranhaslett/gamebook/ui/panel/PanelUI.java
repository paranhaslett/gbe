package paranhaslett.gamebook.ui.panel;

import javax.swing.*;

import paranhaslett.gamebook.model.Item;

public abstract class PanelUI extends JPanel {

    public abstract void populatePanel(Item modelItem);

    public abstract void populateModel();
}
