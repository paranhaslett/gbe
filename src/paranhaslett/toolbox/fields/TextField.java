package paranhaslett.toolbox.fields;

import java.awt.Container;
import java.awt.FlowLayout;

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

    public void populate(String value) {
        textField.setText(value);
    }
    
    public Container getGui(){
    	Container container = new Container();
    	container.setLayout(new FlowLayout());
    	container.add(label);
    	container.add(textField);
    	return container;
    }

}
