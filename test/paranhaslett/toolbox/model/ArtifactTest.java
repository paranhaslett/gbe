package paranhaslett.toolbox.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import paranhaslett.toolbox.tools.FormTool;
import paranhaslett.toolbox.tools.Tool;

import javax.swing.tree.DefaultMutableTreeNode;

import static org.junit.Assert.*;

public class ArtifactTest {
    Tool subTool;
    Tool rootTool;

    @Before
    public void setUp() throws Exception {
        subTool = new FormTool();
        rootTool = new FormTool();
        rootTool.addTool(subTool);

    }

    @Test
    public void getTreeNode() {
        Artifact artifact = new Artifact(rootTool);
        DefaultMutableTreeNode dmtn = artifact.getTreeNode();
        Assert.assertNotNull(dmtn);
        Assert.assertEquals(artifact, dmtn.getUserObject());
    }

    @Test
    public void addData() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void setup() {
    }

    @Test
    public void changeMainLabel() {
    }

    @Test
    public void isDropOn() {
    }

    @Test
    public void getData() {
    }

    @Test
    public void getId() {
    }

    @Test
    public void getTitle() {
    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void contents() {
    }
}