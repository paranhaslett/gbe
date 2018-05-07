package paranhaslett.toolbox.fields;

import javax.swing.*;

public class TextField implements Field {
    private final JTextField textField;

    public TextField(String name) {
        textField = new JTextField();
        JLabel label = new JLabel();
        label.setText(name);
    }

    public void populate(String value) {
        textField.setText(value);
    }

}
