package paranhaslett.toolbox.tools;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import paranhaslett.toolbox.Editor;
import paranhaslett.toolbox.fields.Field;
import paranhaslett.toolbox.loader.Loader;
import paranhaslett.toolbox.model.Artifact;

public class FormTool extends Tool {
	public FormTool(String name) {
		this(name, name);
	}
	
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

        GroupLayout groupLayout = new GroupLayout(this);
        SequentialGroup seqHorizGroup = groupLayout.createSequentialGroup();
       
        for (Field field:fields){
          //seqHorizGroup.addComponent(field.getGui());
          //.addPreferredGap(ComponentPlacement.RELATED);
        }
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
    	init();
        //textField.setText(item.getData(0));
        
    }

}
