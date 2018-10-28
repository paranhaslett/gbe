package paranhaslett.toolbox.ui.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldUI extends Container {
    private final JTextField textField = new JTextField();
    private final JLabel label = new JLabel();

	public TextFieldUI(String name) {
		setLayout(new BorderLayout(0, 0));	
		add(textField, BorderLayout.CENTER);
		add(label, BorderLayout.WEST);
		this.label.setText(name);

	}

	public void fill(final String[] data, final int idx) {
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (data[idx]==null){
					data[idx]="<new>";
				}
				if (!data[idx].equals(TextFieldUI.this.textField.getText())) {
					data[idx] = TextFieldUI.this.textField.getText();
				}
			}
		});
		this.textField.setText(data[idx]);
	}

	public TextFieldUI populate(String value) {
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!value.equals(TextFieldUI.this.textField.getText())) {

					System.out.println("Changed from " + value + " to " + TextFieldUI.this.textField.getText());
				}
			}
		});
		this.textField.setText(value);
		return this;
	}

}
