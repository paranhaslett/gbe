package com.paranhaslett.gamebook.controller;

import javax.swing.tree.TreePath;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loadable.SectionIO;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.ui.panel.PanelUI;
import com.paranhaslett.gamebook.ui.panel.SectionUI;

public class SectionController implements Controller {
	public Loadable loader = new SectionIO();
	private PanelUI panel = SectionUI.getPanelUI();
	private Editor gc=Editor.getEd();
	private long secnum =1;

	@Override
	public void add(ModelItem item, ModelItem added) {
		if (item instanceof Section) {
			Section section = (Section) item;
			if (added instanceof Section) {
				// merge into page
				// change to page controller
				// add newPage to parent of selection
			}
			if (added instanceof Fragment) {
				if (added instanceof Goto) {
					section.gotoid = (Goto) added;
				} else {
					section.fragments.add((Fragment) added);
				}
				gc.tree.addToSel(added);
				// change to fragment controller
			}
		}

	}

	public void update(ModelItem item) {
		if (item instanceof Section) {
			Section section = (Section) item;
			panel.populatePanel(section);
			gc.editorUI.updatePanel(panel);
		}
	}

	@Override
	public void changeMainLabel(ModelItem item, String newLabel) {
		if (item instanceof Section) {
			Section section = (Section) item;
			section.title = newLabel;
			panel.populatePanel(section);
			gc.editorUI.updatePanel(panel);
		}

	}

	public boolean isDropOn(ModelItem mi) {
		return (mi instanceof Fragment || mi instanceof Section);
	}

  @Override
  public void setup(ModelItem modelItem) {
   
    Section section =(Section) modelItem;
    section.id = "" + secnum;
    section.title = "< New >";
    
    Text desc = new Text();
    desc.getController().setup(desc);
    section.fragments.add(desc);
    Goto secgoto = new Goto();
    secgoto.getController().setup(secgoto);
    section.gotoid=secgoto;
    TreePath path = gc.tree.getSelectLoc();
    gc.tree.addToPath(path, section);
    path = gc.tree.getSelectLoc();
    gc.tree.addToPath(path,desc);
    gc.tree.addToPath(path, secgoto);
    secnum ++;
  }

}
