package com.paranhaslett.toolbox.fields;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextField implements Field {
	private JTextField textField;
	private JLabel label;

	public TextField(String name) {
		textField = new JTextField();
		label = new JLabel();
		label.setText(name);
	}
	
	public void populate(String value){	
	  textField.setText(value);
	}

}
