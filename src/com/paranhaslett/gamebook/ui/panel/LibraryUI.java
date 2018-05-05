package com.paranhaslett.gamebook.ui.panel;

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;
import com.paranhaslett.gamebook.model.libraryitem.Template;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryUI extends PanelUI {
    private static final long serialVersionUID = -6099292917735976714L;
    private static PanelUI panelUI;
    private Library model;

    /**
     * Create the panel.
     */
    private LibraryUI() {

        JButton btnAddSection = new JButton("Add Series");
        btnAddSection.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/series.png")));
        btnAddSection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Series series = new Series();
                series.setup();
                model.add(series);
            }
        });

        JButton btnAddPage = new JButton("Add Book");
        btnAddPage.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/book.png")));
        btnAddPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                book.setup();
                model.add(book);
            }
        });

        JLabel lblGameBook = new JLabel("Library");
        lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGameBook.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/library.png")));

        JButton btnAddTemplate = new JButton("Add Template");
        btnAddTemplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Template template = new Template();
                template.setup();
                model.add(template);
            }
        });
        btnAddTemplate.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/template.png")));

        JButton btnLoadBook = new JButton("Load Book");
        btnLoadBook.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/book.png")));

        JButton btnLoadSeries = new JButton("Load Series");
        btnLoadSeries.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/series.png")));
        btnLoadSeries.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnLoadTemplate = new JButton("Load Template");
        btnLoadTemplate.setIcon(new ImageIcon(LibraryUI.class.getResource("/icons/tree/template.png")));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblGameBook)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(btnAddPage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnLoadBook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(btnLoadSeries, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnAddSection, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(6)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(btnAddTemplate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnLoadTemplate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(97, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblGameBook)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAddPage)
                                        .addComponent(btnAddSection)
                                        .addComponent(btnAddTemplate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnLoadBook, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLoadSeries, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLoadTemplate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(211, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }

    public static PanelUI getPanelUI() {
        if (panelUI == null) {
            panelUI = new LibraryUI();
        }
        return panelUI;
    }

    @Override
    public void populatePanel(Item modelItem) {
        model = (Library) modelItem;

    }

    @Override
    public void populateModel() {
        // TODO Auto-generated method stub

    }
}
