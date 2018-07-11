package paranhaslett.toolbox.model;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import paranhaslett.toolbox.Config;

public class FormTool extends Tool {
	
	public FormTool(String name) {
		this(name, name.toLowerCase());
	}	
	
	/**
	 * @wbp.parser.constructor
	 */
	public FormTool(String name, String iconStr) {
		super(name, iconStr);
	}

	@Override
	public void fill(Artifact art, String[] data) {
		this.parent = art;
		int ind = 0;
		for (Field f : this.fields) {
			f.fill(data, ind++);
		}
		// model.title = textField.getText();
		Config.getEd().update();
	}

	@Override
	public void build() {
		setLayout(new GridLayout(this.fields.size() + this.subTools.size(), 1));
		for (Field field : this.fields) {
			add(field.getGui());
			// .addPreferredGap(ComponentPlacement.RELATED);
		}
		for (Tool subTool : this.subTools) {
			JButton addButton = new JButton("Add " + subTool.name());
			addButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Artifact art = new Artifact(subTool);
					FormTool.this.parent.add(art);
					Config.getEd().tree().addToSel(art.getTreeNode());
				}

			});
			add(addButton);
		}

	}

}
