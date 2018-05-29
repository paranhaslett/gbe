package paranhaslett.toolbox.model;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import paranhaslett.toolbox.Config;
import paranhaslett.toolbox.tools.FormTool;
import paranhaslett.toolbox.tools.Tool;

public class ArtifactTest {
	private Artifact artifact;
	private Tool subTool;
	private Tool rootTool;

	@Before
	public void setUp() throws Exception {
		Config ttb = new Config();
		subTool = new FormTool("sub-tool", "set");
		rootTool = new FormTool("root tool","book");
		rootTool.addTool(subTool);
		artifact = new Artifact(rootTool);

	}

	@Test
	public void hasTool() {
		Assert.assertNotNull(artifact.tool());
		Assert.assertNotNull(artifact.tool().icon());
	}

	@Test
	public void getTreeNodeTest() {
		DefaultMutableTreeNode treeNode = artifact.getTreeNode();
		Assert.assertNotNull(treeNode);
		Assert.assertEquals(artifact, treeNode.getUserObject());
	}

	@Test
	public void addDataTest() {

		artifact.addData("test data");
		Assert.assertSame("test data", artifact.getData(0));
		try {
			artifact.addData(null);
			Assert.fail("Needs to throw NPE if null");
		} catch (NullPointerException npe) {
			// success
		}

	}

	@Test
	public void add() {
		try {
			artifact.add(null);
			Assert.fail("Needs to throw NPE if null");
		} catch (NullPointerException npe) {
			// success
		}
		Artifact correctSubArtifact = new Artifact(subTool);
		artifact.add(correctSubArtifact);
		// Assert.assertSame(correctSubArtifact,artifact.get)

	}

	@Test
	public void update() {
	}

	@Test
	public void setup() {
	}

	@Test
	public void isDropOn() {
		Artifact dropOn = new Artifact(subTool);
		Assert.assertTrue(artifact.isDropOn(dropOn));
		try {
			artifact.isDropOn(null);
			Assert.fail("Needs to throw NPE if null");
		} catch (NullPointerException npe) {
			// success
		}
		dropOn = new Artifact(artifact.tool());
		Assert.assertFalse(artifact.isDropOn(dropOn));
	}

	@Test
	public void getData() {
		artifact.getData(10);
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
	public void contentsTest() {
	}
}