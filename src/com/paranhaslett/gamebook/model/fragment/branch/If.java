package com.paranhaslett.gamebook.model.fragment.branch;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.IfIO;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.ModelContainer;
import com.paranhaslett.gamebook.ui.panel.IfUI;
import com.paranhaslett.gamebook.ui.panel.PanelUI;

import javax.swing.*;
import java.util.ArrayList;

public class If implements ModelContainer, Fragment, Item {
    public static final Loadable loadable = new IfIO();
    private static Icon icon;
    public final ArrayList<Fragment> trueBranch = new ArrayList<>();
    private final PanelUI panel = IfUI.getPanelUI();
    private final Editor ed = Editor.getEd();
    public String lhs;
    public String op;
    public String rhs;
    public String text;

    @Override
    public boolean isDropOn(Item mi) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
    }

    public String toString() {
        return text;
    }

    public ArrayList<Fragment> getFragments() {
        return trueBranch;
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
    public void changeMainLabel(String newLabel) {
        // TODO Auto-generated method stub

    }

    @Override
    public Icon icon() {
        if (icon == null) {
            icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/if.png");
        }
        return icon;
    }
}
