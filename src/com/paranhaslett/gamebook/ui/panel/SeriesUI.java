package com.paranhaslett.gamebook.ui.panel;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;

public class SeriesUI extends PanelUI {
	private final JTextField textField;
	private static PanelUI panelUI;
	private Series model;

	/**
	 * Create the panel.
	 */
	private SeriesUI() {
		JLabel lblNewLabel = new JLabel("Title");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(e -> populateModel());

		JButton btnAddPage = new JButton("Add Book");
		btnAddPage.setIcon(new ImageIcon(SeriesUI.class.getResource("/icons/tree/book.png")));
		btnAddPage.addActionListener(e -> {
            Book book = new Book();
            book.setup();
            model.add(book);
        });

		JLabel lblGameBook = new JLabel("Series");
		lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGameBook.setIcon(new ImageIcon(SeriesUI.class.getResource("/icons/tree/series.png")));
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
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnUpdate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddPage)))))
					.addContainerGap(16, GroupLayout.PREFERRED_SIZE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate)
						.addComponent(btnAddPage))
					.addContainerGap(216, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	public void setup(String title) {
		textField.setText(title);
	}

	public static PanelUI getPanelUI() {
		if (panelUI == null) {
			panelUI = new SeriesUI();
		}
		return panelUI;
	}

	@Override
	public void populatePanel(Item modelItem) {
		textField.setText(((Series) modelItem).title);
		model = (Series) modelItem;

	}

	@Override
	public void populateModel() {
		model.title = textField.getText();
		Editor.getEd().update();
	}
}
