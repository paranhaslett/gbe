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
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.model.ModelItem;
import com.paranhaslett.gamebook.model.fragment.Text;
import com.paranhaslett.gamebook.model.fragment.Set;
import com.paranhaslett.gamebook.model.fragment.branch.Chance;
import com.paranhaslett.gamebook.model.fragment.branch.Choice;
import com.paranhaslett.gamebook.model.fragment.branch.If;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class IfUI extends PanelUI {
	private static final long serialVersionUID = 4882714495642743442L;
	private JTextField textLhs;
	private static PanelUI panelUI;
	private If model;
	private JLabel lblHeading;
	private JTextField textRhs;
	private JTextField textField;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	private IfUI() {

		JLabel lblVar = new JLabel("Variable:");

		textLhs = new JTextField();
		lblVar.setLabelFor(textLhs);
		textLhs.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateModel();
			}
		});

		lblHeading = new JLabel("If");
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHeading.setIcon(new ImageIcon(IfUI.class
				.getResource("/icons/tree/if.png")));

		JButton btnDescription = new JButton("Add Description");
		btnDescription.setIcon(new ImageIcon(IfUI.class
				.getResource("/icons/tree/desc.png")));
		btnDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = model.getController();
				Text chance = new Text();
				chance.getController().setup(chance);
				controller.add(model, chance);
			}
		});

		JButton btnSet = new JButton("Add Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = model.getController();
				Set set = new Set();
				set.getController().setup(set);
				controller.add(model, set);
			}
		});
		btnSet.setIcon(new ImageIcon(IfUI.class
				.getResource("/icons/tree/set.png")));

		JButton btnChance = new JButton("Add Chance");
		btnChance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = model.getController();
				Chance chance = new Chance();
				chance.getController().setup(chance);
				controller.add(model, chance);
			}
		});
		btnChance.setIcon(new ImageIcon(IfUI.class
				.getResource("/icons/tree/sm_chance.png")));

		JButton btnChoice = new JButton("Add Choice");
		btnChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = model.getController();
				Choice choice = new Choice();
				choice.getController().setup(choice);
				controller.add(model, choice);
			}
		});
		btnChoice.setIcon(new ImageIcon(IfUI.class
				.getResource("/icons/tree/choice.png")));

		JButton btnIf = new JButton("Add If");
		btnIf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = model.getController();
				If chance = new If();
				chance.getController().setup(chance);
				controller.add(model, chance);
			}
		});
		btnIf.setIcon(new ImageIcon(IfUI.class
				.getResource("/icons/tree/if.png")));

		textRhs = new JTextField();
		textRhs.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "=", "<", ">" }));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblText = new JLabel("Text:");
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
																		.addGap(10)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblVar)
																						.addComponent(
																								lblText))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												textLhs,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												comboBox,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												textRhs,
																												GroupLayout.DEFAULT_SIZE,
																												239,
																												Short.MAX_VALUE))
																						.addComponent(
																								textField,
																								GroupLayout.DEFAULT_SIZE,
																								297,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addGroup(
																																groupLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				btnIf)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				btnChance))
																														.addGroup(
																																groupLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				btnUpdate)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				btnDescription)))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																btnSet)
																														.addComponent(
																																btnChoice))))))
										.addContainerGap()));
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
														.addComponent(lblVar)
														.addComponent(
																textLhs,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																textRhs,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblText))
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
														.addComponent(btnChance)
														.addComponent(btnChoice)
														.addComponent(btnIf))
										.addContainerGap(154, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	public static PanelUI getPanelUI() {
		if (panelUI == null) {
			panelUI = new IfUI();
		}
		return panelUI;
	}

	@Override
	public void populatePanel(ModelItem modelItem) {
		model = (If) modelItem;
		textLhs.setText(model.lhs);
		textRhs.setText(model.rhs);
		comboBox.setSelectedItem(model.op);
	}

	@Override
	public void populateModel() {
		model.lhs = textLhs.getText();
		model.rhs = textRhs.getText();
		model.op = (String) comboBox.getSelectedItem();
		if (textField.getText() == null || textField.getText().equals("")) {
			textField.setText("If " + textLhs.getText()
					+ (String) comboBox.getSelectedItem() + textRhs.getText());
		}
		model.text = textField.getText();
		Editor.getEd().update();
	}
}