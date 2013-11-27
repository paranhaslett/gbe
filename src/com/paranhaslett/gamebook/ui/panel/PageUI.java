package com.paranhaslett.gamebook.ui.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;

public class PageUI extends PanelUI {
  private static final long serialVersionUID = -6099292917735976714L;
  private static PanelUI panelUI;
  private Page model;
  private JTextField textNumber;
  private JLabel lblHeading;

  /**
   * Create the panel.
   */
  private PageUI() {

    JButton btnAddSection = new JButton("Add Section");
    btnAddSection.setIcon(new ImageIcon(PageUI.class.getResource("/icons/tree/section.png")));
    btnAddSection.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //Controller controller = Editor.getEd().getController("Page");
        Section section = new Section();
        section.getController().setup(section);
      }
    });
    
    lblHeading = new JLabel("Page");
    lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblHeading.setIcon(new ImageIcon(PageUI.class.getResource("/icons/tree/page.png")));
    
    textNumber = new JTextField();
    textNumber.setColumns(10);
    
    JLabel lblNumber = new JLabel("Number:");
    
    JButton btnUpdate = new JButton("Update");
    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(lblHeading)
            .addGroup(groupLayout.createSequentialGroup()
              .addComponent(lblNumber)
              .addPreferredGap(ComponentPlacement.RELATED)
              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                  .addComponent(btnUpdate)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(btnAddSection))
                .addComponent(textNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
          .addContainerGap(213, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
          .addContainerGap()
          .addComponent(lblHeading)
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblNumber)
            .addComponent(textNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnUpdate)
            .addComponent(btnAddSection))
          .addContainerGap(216, Short.MAX_VALUE))
    );
    setLayout(groupLayout);

  }

  public static PanelUI getPanelUI() {
    if (panelUI == null) {
      panelUI = new PageUI();
    }
    return panelUI;
  }

  @Override
  public void populatePanel(ModelItem modelItem) {
    model = (Page) modelItem;
    lblHeading.setText("Page " + model.id);
    textNumber.setText(model.id);
    
  }

  @Override
  public void populateModel() {
    model.id = textNumber.getText();
  }
}
