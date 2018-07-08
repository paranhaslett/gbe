package paranhaslett.toolbox.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.model.Artifact;
import paranhaslett.toolbox.model.Tool;
import paranhaslett.toolbox.model.action.ParseAction;
import paranhaslett.toolbox.model.action.XmlParseAction;
import paranhaslett.toolbox.model.action.XmlSaveAction;
import paranhaslett.toolbox.tree.TreeUI;

public class EditorUI extends JFrame {
    private static final long serialVersionUID = 6198693420541435623L;
    private final JSplitPane splitPane;
    private final JScrollPane scrollPane;
    private final XmlSaveAction saveAct = new XmlSaveAction(null);

    /**
     * Create the frame.
     */
    public EditorUI(String name) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                EditorUI.class.getResource("/icons/app.png")));
        setTitle(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 400);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mnItmSave = new JMenuItem("Save");
        //mnItmSave.setIcon(new ImageIcon(EditorUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
        mnItmSave.addActionListener(e -> {
            FileChooserUI fcu = new FileChooserUI();
            File file = fcu.save(this);
            FileWriter fw = null;
            try {
				fw = new FileWriter(file);
				fw.write("<tb>\n");
				Artifact root = Config.getEd().tree().root();
				
				fw.write(saveAct.act(root));
				fw.write("</tb>");
				fw.flush();
            } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				if (fw != null){
					try {
						fw.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
            
           
        });
        mnFile.add(mnItmSave);
        
        JMenuItem mntmLoad = new JMenuItem("Load");
        
        mntmLoad.addActionListener(e -> {
        		 FileChooserUI fcu = new FileChooserUI();
                 File file = fcu.load(this);
                 BufferedReader bfr = null;
                 List<String> tokens = new LinkedList<>();
                 try {
                	FileReader fr = new FileReader(file);
     				bfr = new BufferedReader(fr);
     				String line = bfr.readLine();
     				if (line.equals("<tb>")){
     					line = bfr.readLine();
     					while (!line.equals("</tb>")){
     						tokens.add(line);
        					line = bfr.readLine();
     					}
     					
     					ParseAction parse = new XmlParseAction(null);
     					
     					Config.getEd().build(parse.act(null, tokens));
     				}
                 } catch (Exception e1){
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     			} finally {
     				if (bfr != null){
     					try {
     						bfr.close();
     					} catch (Exception e1) {
     						// TODO Auto-generated catch block
     						e1.printStackTrace();
     					}
     				}
     			}
        });
        mnFile.add(mntmLoad);
        
        JMenuItem mntmLoadTools = new JMenuItem("Load Tools");
        mnFile.add(mntmLoadTools);

        JMenu mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        JMenu mnPreferences = new JMenu("Preferences");
        mnEdit.add(mnPreferences);

        JMenu mnTools = new JMenu("Tools");
        menuBar.add(mnTools);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mnItmAbout = new JMenuItem("About");
        mnItmAbout.addActionListener(arg0 -> {
            About dialog = new About();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
        mnHelp.add(mnItmAbout);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.5);
        contentPane.add(splitPane, BorderLayout.CENTER);

        scrollPane = new JScrollPane();
        splitPane.setLeftComponent(scrollPane);

    }

    public void setName(String name) {
        setTitle(name);
    }

    public void updateTree(TreeUI tree) {
        scrollPane.setViewportView(tree);
    }

    public void updatePanel(Artifact artifact) {
       //artifact.tool().initPanel();
        splitPane.setRightComponent(artifact.tool());
    }

}
