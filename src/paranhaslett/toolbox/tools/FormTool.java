package paranhaslett.toolbox.tools;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.fields.Field;
import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.model.Artifact;

public class FormTool extends Tool {
	public FormTool(String name) {
		this(name, name);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public FormTool(String name,String iconStr) {
		super(name, iconStr);
	}

    private JTextField textField;

    @Override
    public Artifact load(Loader ff) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Loader ff, Artifact item) {
        // TODO Auto-generated method stub

    }

    public void init() {
    	
    	
        JLabel lblNewLabel = new JLabel("Title");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> populateModel());

        setLayout(new GridLayout(fields.size(),1));
        for (Field field:fields){
          add(field.getGui());
          //.addPreferredGap(ComponentPlacement.RELATED);
        }
       

    }


    @Override
    public void populateModel() {
        //model.title = textField.getText();
        Config.getEd().update();
    }


    @Override
    public void populatePanel(Artifact item) {
    	init();
        //textField.setText(item.getData(0));
        
    }

}
