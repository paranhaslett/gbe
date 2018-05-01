package com.paranhaslett.gamebook.model;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SectionIO;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SectionUI;

public class Section implements ModelContainer {

	public String title;
	public String id;
	public Goto gotoid;
	public final ArrayList<Fragment> fragments = new ArrayList<>();

	public static final Loadable loadable = new SectionIO();
	private final PanelUI panel = SectionUI.getPanelUI();
	private final Editor ed = Editor.getEd();
	private static long secnum = 1;

	@Override
	public boolean isDropOn(Item mi) {
		return (mi instanceof Fragment || mi instanceof Section);
	}

	@Override
	public void setup() {
		id = "" + secnum;
		title = "< New >";

		Text desc = new Text();
		desc.setup();
		fragments.add(desc);
		Goto secgoto = new Goto();
		secgoto.setup();
		gotoid = secgoto;
		TreePath path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, this);
		path = ed.tree.getSelectLoc();
		ed.tree.addToPath(path, desc);
		ed.tree.addToPath(path, secgoto);
		secnum++;
	}

	public ArrayList<Fragment> getFragments() {
		return fragments;
	}

	public String toString() {
		if (title != null) {
			return title;
		}
		return "" + id;
	}

	@Override
	public void add(Item added) {
		if (added instanceof Section) {
			// merge into page
			// change to page controller
			// add newPage to parent of selection
		}
		if (added instanceof Fragment) {
			if (added instanceof Goto) {
				gotoid = (Goto) added;
			} else {
				fragments.add((Fragment) added);
			}
			ed.tree.addToSel(added);
			// change to fragment controller
		}
	}

	@Override
	public void update() {
		ed.editorUI.updatePanel(panel, this);

	}

	@Override
	public void changeMainLabel(String newLabel) {
		title = newLabel;
		ed.editorUI.updatePanel(panel, this);

	}

	private static Icon icon;

	@Override
	public Icon icon() {
		if (icon == null) {
			icon = ed.tree.getTreeRenderer().createImageIcon("/icons/tree/section.png");
		}
		return icon;
	}
}
