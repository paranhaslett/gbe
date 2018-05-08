package paranhaslett.toolbox.tools;

import paranhaslett.toolbox.Editor;
import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.model.Artifact;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class FormTool extends Tool {

    private JTextField textField;

    @Override
    public Artifact load(Loader ff) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Loader ff, Artifact item) {
        // TODO Auto-generated method stub

    }

    public void init() {
        JLabel lblNewLabel = new JLabel("Title");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        JLabel lblGameBook = new JLabel("Game Book");
        lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGameBook.setIcon(new ImageIcon(FormTool.class
                .getResource("/icons/tree/book.png")));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblGameBook)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblNewLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                                                        .addComponent(btnUpdate))))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblGameBook)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                                .addComponent(btnUpdate)
                                .addContainerGap())
        );
        setLayout(groupLayout);

    }


    @Override
    public void populateModel() {
        //model.title = textField.getText();
        Editor.getEd().update();
    }


    @Override
    public void populatePanel(Artifact item) {
        textField.setText(item.getData(0));
    }

}
