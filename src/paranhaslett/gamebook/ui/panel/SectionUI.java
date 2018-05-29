package paranhaslett.gamebook.ui.panel;

import paranhaslett.gamebook.Config;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Section;
import paranhaslett.gamebook.model.fragment.Set;
import paranhaslett.gamebook.model.fragment.Text;
import paranhaslett.gamebook.model.fragment.branch.Chance;
import paranhaslett.gamebook.model.fragment.branch.Choice;
import paranhaslett.gamebook.model.fragment.branch.If;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SectionUI extends PanelUI {

    private static PanelUI panelUI;
    private final JTextField textTitle;
    private final JLabel lblHeading;
    private Section model;

    /**
     * Create the panel.
     */
    @SuppressWarnings("Convert2Lambda")
    private SectionUI() {

        JLabel lblTitle = new JLabel("Title:");

        textTitle = new JTextField();
        lblTitle.setLabelFor(textTitle);
        textTitle.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                populateModel();
            }
        });

        lblHeading = new JLabel("Section");
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblHeading.setIcon(new ImageIcon(SectionUI.class
                .getResource("/icons/tree/section.png")));

        JButton btnDescription = new JButton("Add Description");
        btnDescription.setIcon(new ImageIcon(SectionUI.class
                .getResource("/icons/tree/desc.png")));
        btnDescription.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Text chance = new Text();
                chance.setup();
                model.add(chance);
            }
        });

        JButton btnSet = new JButton("Add Set");
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Set set = new Set();
                set.setup();
                model.add(set);
            }
        });
        btnSet.setIcon(new ImageIcon(SectionUI.class
                .getResource("/icons/tree/set.png")));

        JButton btnChance = new JButton("Add Chance");
        btnChance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chance chance = new Chance();
                chance.setup();
                model.add(chance);
            }
        });
        btnChance.setIcon(new ImageIcon(SectionUI.class
                .getResource("/icons/tree/sm_chance.png")));

        JButton btnChoice = new JButton("Add Choice");
        btnChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice choice = new Choice();
                choice.setup();
                model.add(choice);
            }
        });
        btnChoice.setIcon(new ImageIcon(SectionUI.class
                .getResource("/icons/tree/choice.png")));

        JButton btnIf = new JButton("Add If");
        btnIf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                If ef = new If();
                ef.setup();
                model.add(ef);
            }
        });
        btnIf.setIcon(new ImageIcon(SectionUI.class
                .getResource("/icons/tree/if.png")));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout
                .setHorizontalGroup(groupLayout
                        .createParallelGroup(Alignment.TRAILING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                lblHeading)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGap(14)
                                                                        .addComponent(
                                                                                lblTitle)
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                textTitle,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                323,
                                                                                                Short.MAX_VALUE)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                btnIf)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnChance)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnChoice))
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                btnUpdate)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnDescription)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnSet)))))
                                        .addGap(20)));
        groupLayout
                .setVerticalGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblHeading)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(lblTitle)
                                                        .addComponent(
                                                                textTitle,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(btnUpdate)
                                                        .addComponent(
                                                                btnDescription)
                                                        .addComponent(btnSet))
                                        .addPreferredGap(
                                                ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                Alignment.BASELINE)
                                                        .addComponent(btnIf)
                                                        .addComponent(btnChance)
                                                        .addComponent(btnChoice))
                                        .addContainerGap(181, Short.MAX_VALUE)));
        setLayout(groupLayout);

    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new SectionUI();
        }
        return panelUI;
    }

    @Override
    public void populatePanel(Item modelItem) {
        model = (Section) modelItem;
        lblHeading.setText("Section " + model.id);
        textTitle.setText(model.title);
    }

    @Override
    public void populateModel() {
        model.title = textTitle.getText();
        Config.getEd().update();
    }
}
