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

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.libraryitem.Book;

public class GameBookUI extends PanelUI {
	private static final long serialVersionUID = -6099292917735976714L;
	private JTextField textField;
	private static PanelUI panelUI;
	private Book model;

	/**
	 * Create the panel.
	 */
	private GameBookUI() {
		JLabel lblNewLabel = new JLabel("Title");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateModel();

			}
		});

		JButton btnAddSection = new JButton("Add Section");
		btnAddSection.setIcon(new ImageIcon(GameBookUI.class
				.getResource("/icons/tree/section.png")));
		btnAddSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Controller controller = model.getController();
				Section section = new Section();
				section.setup(section);
			}
		});

		JButton btnAddPage = new JButton("Add Page");
		btnAddPage.setIcon(new ImageIcon(GameBookUI.class
				.getResource("/icons/tree/page.png")));
		btnAddPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Page page = new Page();
				page.setup(page);
				model.add(page);
			}
		});

		JLabel lblGameBook = new JLabel("Game Book");
		lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGameBook.setIcon(new ImageIcon(GameBookUI.class
				.getResource("/icons/tree/gamebook.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblGameBook))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblNewLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								textField,
																								GroupLayout.DEFAULT_SIZE,
																								400,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnUpdate)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnAddPage)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnAddSection)))))
										.addContainerGap(16,
												GroupLayout.PREFERRED_SIZE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblGameBook)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																textField,
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
																btnAddPage)
														.addComponent(
																btnAddSection))
										.addContainerGap(216, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	public void setup(String title) {
		textField.setText(title);
	}

	public static PanelUI getPanelUI() {
		if (panelUI == null) {
			panelUI = new GameBookUI();
		}
		return panelUI;
	}

	@Override
	public void populatePanel(ModelItem modelItem) {
		textField.setText(((Book) modelItem).title);
		model = (Book) modelItem;

	}

	@Override
	public void populateModel() {
		model.title = textField.getText();
		Editor.getEd().update();
	}
}
