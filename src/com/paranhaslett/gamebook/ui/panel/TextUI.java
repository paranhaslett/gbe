package com.paranhaslett.gamebook.ui.panel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.model.Item;
import com.paranhaslett.gamebook.model.fragment.Text;

public class TextUI extends PanelUI {
	private final JTextArea txtDesc;
	private static PanelUI panelUI;
	private Text model;

	private static final long serialVersionUID = -1739742038591872687L;

	private TextUI() {

		JScrollPane scrollPane = new JScrollPane();

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(e -> populateModel());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 430,
												Short.MAX_VALUE)
										.addComponent(btnUpdate))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								237, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnUpdate)
						.addContainerGap(23, Short.MAX_VALUE)));

		txtDesc = new JTextArea();
		scrollPane.setViewportView(txtDesc);
		setLayout(groupLayout);
	}

	public static PanelUI getPanelUI() {
		if (panelUI == null) {
			panelUI = new TextUI();
		}
		return panelUI;
	}

	@Override
	public void populatePanel(Item modelItem) {
		model = (Text) modelItem;
		txtDesc.setText(((Text) modelItem).text);

	}

	@Override
	public void populateModel() {
		model.text = txtDesc.getText();
		Editor.getEd().update();

	}
}
