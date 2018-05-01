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

import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Goto;

public class GotoUI extends PanelUI {
	private static PanelUI panelUI;
	private Goto model;

	private static final long serialVersionUID = -1739742038591872687L;
	private final JTextField textGoto;
	private final JLabel lblHeading;
	private final JTextField textField;

	private GotoUI() {
		lblHeading = new JLabel("Go To");
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHeading.setIcon(new ImageIcon(GotoUI.class
				.getResource("/icons/tree/goto.png")));

		textGoto = new JTextField();
		textGoto.setColumns(10);

		JLabel lblGoTo = new JLabel("Go To:");
		lblGoTo.setLabelFor(textGoto);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateModel();
			}
		});

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
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
																lblHeading)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(lblGoTo)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnUpdate)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												textGoto,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												lblDescription)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												textField,
																												GroupLayout.DEFAULT_SIZE,
																												243,
																												Short.MAX_VALUE)))))
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
														.addComponent(lblGoTo)
														.addComponent(
																textGoto,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblDescription)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnUpdate)
										.addContainerGap(218, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}

	public static PanelUI getPanelUI() {
		if (panelUI == null) {
			panelUI = new GotoUI();
		}
		return panelUI;
	}

	@Override
	public void populatePanel(Item modelItem) {
		model = (Goto) modelItem;
		textGoto.setText(model.to);
		textField.setText(model.text);
		lblHeading.setText("Go To " + model.to);

	}

	@Override
	public void populateModel() {
		if (textField.getText() == null || textField.getText().equals("")) {
			textField.setText("Go to section " + textGoto.getText());
		}
		model.text = textField.getText();
		model.to = textGoto.getText();
		lblHeading.setText("Go To " + model.to);
	}
}
