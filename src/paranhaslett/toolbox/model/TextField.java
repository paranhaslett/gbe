package paranhaslett.toolbox.model;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextField implements Field {
    final JTextField textField;
    private final JLabel label;

	public TextField(String name) {
		this.textField = new JTextField();
		this.label = new JLabel();
		this.label.setText(name);

	}

	@Override
	public Field fill(final String[] data, final int idx) {
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (data[idx]==null){
					data[idx]="<new>";
				}
				if (!data[idx].equals(TextField.this.textField.getText())) {
					data[idx] = TextField.this.textField.getText();
				}
			}
		});
		this.textField.setText(data[idx]);
		return this;
	}

	public TextField populate(String value) {
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!value.equals(TextField.this.textField.getText())) {

					System.out.println("Changed from " + value + " to " + TextField.this.textField.getText());
				}
			}
		});
		this.textField.setText(value);
		return this;
	}

	@Override
	public Container getGui() {
		Container container = new Container();
		container.setLayout(new FlowLayout());
		container.add(this.label);
		container.add(this.textField);
		return container;
	}

}
