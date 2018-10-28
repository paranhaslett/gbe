package paranhaslett.toolbox.ui.tools;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class FormToolUI extends JPanel {
	private ImageIcon icon;
	private String iconStr;
	
	public FormToolUI(String iconStr) {
		this.iconStr=iconStr;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public ImageIcon icon() {
		if (this.icon != null) {
			return this.icon;
		}

		java.net.URL imgURL = getClass().getResource("/icons/tree/" + this.iconStr + ".png");
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		return null;
	}

}
