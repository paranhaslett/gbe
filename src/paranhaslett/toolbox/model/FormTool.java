package paranhaslett.toolbox.model;

import java.awt.GridLayout;

import javax.swing.JTextField;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.loader.Loader;

public class FormTool extends Tool {
	public FormTool(String name) {
		this(name, name.toLowerCase());
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


    @Override
    public void fill(String[] data) {
    	int ind = 0;
    	for(Field f : fields){
    		f.fill(data, ind++);
    	}
        //model.title = textField.getText();
        Config.getEd().update();
    }


    @Override
    public void build() {
         setLayout(new GridLayout(fields.size(),1));
         for (Field field:fields){
           add(field.getGui());
           //.addPreferredGap(ComponentPlacement.RELATED);
         }
        
    }

	@Override
	public String compile(Compiler compiler) {
		// TODO Auto-generated method stub
		return null;
	}

}
