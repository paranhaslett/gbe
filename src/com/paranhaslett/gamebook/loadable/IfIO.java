package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Fragment;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.GoTo;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;

import java.util.List;

public class IfIO implements Loadable {

    @Override
    public void load(Loader ff, Item item) {
        If ifObj = (If) item;
        ifObj.lhs = ff.getText("var");
        ifObj.rhs = ff.getText("value");
        ifObj.op = ff.getText("comp");
        ifObj.text = ff.getText("text");
        List<Loader> fragmentElements = ff.getChildren("set", "goto", "if");

        for (Loader fragmentElement : fragmentElements) {
            Fragment frag = null;
            if (fragmentElement.getName().equals("text")) {
                frag = new Text();
                Text.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("set")) {
                frag = new Set();
                Set.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("choice")) {
                frag = new Choice();
                Choice.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("chance")) {
                frag = new Chance();
                Chance.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("if")) {
                frag = new If();
                If.loadable.load(fragmentElement, frag);
            }
            if (fragmentElement.getName().equals("goto")) {
                frag = new GoTo();
                GoTo.loadable.load(fragmentElement, frag);
            }

            if (frag != null) {
                ifObj.trueBranch.add(frag);
            }

        }

    }

    @Override
    public void save(Loader ff, Item item) {
        // TODO Auto-generated method stub

    }

}
