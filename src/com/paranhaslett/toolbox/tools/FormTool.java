package com.paranhaslett.toolbox.tools;

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

import com.paranhaslett.toolbox.Editor;
import com.paranhaslett.toolbox.loader.Loader;
import com.paranhaslett.toolbox.model.Artifact;

public class FormTool extends Tool{

	@Override
	public Artifact load(Loader ff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Loader ff, Artifact item) {
		// TODO Auto-generated method stub
		
	}
	
	
    private JTextField textField;
	
	
	public void init() {
		JLabel lblNewLabel = new JLabel("Title");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateModel();

			}
		});

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
