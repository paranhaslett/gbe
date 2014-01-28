package com.paranhaslett.gamebook.ui.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.Page;
import com.paranhaslett.gamebook.model.Section;
import com.paranhaslett.gamebook.model.fragment.Goto;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;
import com.paranhaslett.gamebook.model.libraryitem.Book;
import com.paranhaslett.gamebook.model.libraryitem.Series;
import com.paranhaslett.gamebook.model.libraryitem.Template;

public class TemplateUI extends PanelUI {
	private static final long serialVersionUID = 5259672660377230929L;
	private JTextField textField;
	private static PanelUI panelUI;
	private Template model;

	/**
	 * Create the panel.
	 */
	private TemplateUI() {
		JLabel lblNewLabel = new JLabel("Title");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateModel();

			}
		});

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Template", "Series", "Book", "Page", "Section", "Text",
				"Goto", "Set", "Chance", "Choice", "If", "Var" }));

		JButton btnAddPage = new JButton("Add");
		btnAddPage.setIcon(null);
		btnAddPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				Item item = null;
				if (selected.equals("Book")){
					item = new Book();
				}
				if (selected.equals("Series")){
					item = new Series();
				}
				if (selected.equals("Template")){
					item = new Template();
				}
				if (selected.equals("Page")){
					item = new Page();
				}
				if (selected.equals("Section")){
					item = new Section();
				}
				if (selected.equals("Text")){
					item = new Text();
				}
				if (selected.equals("Goto")){
					item = new Goto();
				}
				if (selected.equals("Set")){
					item = new Set();
				}
				if (selected.equals("Chance")){
					item = new Chance();
				}
				if (selected.equals("Choice")){
					item = new Choice();
				}
				if (selected.equals("If")){
					item = new If();
				}
				//if (selected.equals("Var")){
				//	item = new Var();
				//}
				
				
				item.changeMainLabel("< New >");
				model.add(item);
			}
		});

		JLabel lblGameBook = new JLabel("Template");
		lblGameBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGameBook.setIcon(new ImageIcon(TemplateUI.class
				.getResource("/icons/tree/template.png")));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblGameBook)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
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
																						.addComponent(
																								btnUpdate)
																						.addComponent(
																								comboBox,
																								GroupLayout.PREFERRED_SIZE,
																								142,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnAddPage))))
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
										.addComponent(btnUpdate)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(comboBox,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnAddPage)
										.addContainerGap(161, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	public void setup(String title) {
		textField.setText(title);
	}

	public static PanelUI getPanelUI() {
		if (panelUI == null) {
			panelUI = new TemplateUI();
		}
		return panelUI;
	}

	@Override
	public void populatePanel(Item modelItem) {
		textField.setText(((Template) modelItem).title);
		model = (Template) modelItem;

	}

	@Override
	public void populateModel() {
		model.title = textField.getText();
		Editor.getEd().update();
	}
}
