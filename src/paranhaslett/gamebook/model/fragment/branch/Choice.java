package paranhaslett.gamebook.model.fragment.branch;

import paranhaslett.gamebook.Config;
import paranhaslett.gamebook.loadable.ChoiceIO;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.model.Fragment;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.ModelContainer;
import paranhaslett.gamebook.ui.panel.PanelUI;
import paranhaslett.gamebook.ui.panel.TextUI;

import javax.swing.*;
import java.util.ArrayList;

public class Choice implements ModelContainer, Fragment {
    public static final Loadable loadable = new ChoiceIO();
    private static Icon icon;
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private final PanelUI panel = TextUI.getPanelUI();
    private final Config ed = Config.getEd();

    @Override
    public boolean isDropOn(Item mi) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setup() {

    }

    @Override
	public ArrayList<Fragment> getFragments() {
        return this.fragments;
    }

    @Override
    public void add(Item to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        this.ed.editorUI.updatePanel(this.panel, this);

    }

    @Override
    public void changeMainLabel(String newLabel) {
        // TODO Auto-generated method stub

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = this.ed.tree.getTreeRenderer().createImageIcon("/icons/tree/choice.png");
        }
        return icon;
    }
}
