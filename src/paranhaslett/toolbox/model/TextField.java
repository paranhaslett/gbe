package paranhaslett.toolbox.model;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextField implements Field {
    private final JTextField textField;
    private final JLabel label;

	public TextField(String name) {
		textField = new JTextField();
		label = new JLabel();
		label.setText(name);

	}

	public Field fill(String[] data, int idx) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!data[idx].equals(textField.getText())) {
					data[idx] = textField.getText();
				}
			}
		});
		textField.setText(data[idx]);
		return this;
	}

	public TextField populate(String value) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!value.equals(textField.getText())) {

					System.out.println("Changed from " + value + " to " + textField.getText());
				}
			}
		});
		textField.setText(value);
		return this;
	}

	public Container getGui() {
		Container container = new Container();
		container.setLayout(new FlowLayout());
		container.add(label);
		container.add(textField);
		return container;
	}

}
