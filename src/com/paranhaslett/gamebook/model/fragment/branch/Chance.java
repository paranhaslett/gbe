package com.paranhaslett.gamebook.model.fragment.branch;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.ChanceIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.ModelContainer;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.TextUI;

import javax.swing.*;
import java.util.ArrayList;

public class Chance implements ModelContainer, Fragment, Item {
    public static final Loadable loadable = new ChanceIO();
    private static Icon icon;
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private final PanelUI panel = TextUI.getPanelUI();
    private final Editor ed = Editor.getEd();

    @Override
    public boolean isDropOn(Item mi) {
        // TODO Auto-generated method stub
        return false;
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    @Override
    public void add(Item to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        ed.editorUI.updatePanel(panel, this);
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeMainLabel(String newLabel) {
        // TODO Auto-generated method stub

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/chance.png");
        }
        return icon;
    }
}
