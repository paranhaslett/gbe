package paranhaslett.toolbox.model;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import paranhaslett.toolbox.tools.FormTool;
import paranhaslett.toolbox.tools.Tool;

public class ArtifactTest {
	private Artifact artifact;
	private Tool subTool;
	Tool rootTool;

	@Before
	public void setUp() throws Exception {
		subTool = new FormTool();
		rootTool = new FormTool();
		rootTool.addTool(subTool);
		artifact = new Artifact(rootTool);

	}

	@Test
	public void getTreeNodeTest() {
		DefaultMutableTreeNode dmtn = artifact.getTreeNode();
		Assert.assertNotNull(dmtn);
		Assert.assertEquals(artifact, dmtn.getUserObject());
	}

	@Test
	public void addDataTest() {
		
		artifact.addData("test data");
		Assert.assertSame("test data", artifact.getData(0));
		try {
			artifact.addData(null);
			Assert.fail("Needs to throw NPE if null");
		} catch (NullPointerException npe) {
			// sucess
		}

	}

	@Test
	public void add() {
		try {
			artifact.add(null);
			Assert.fail("Needs to throw NPE if null");
		} catch (NullPointerException npe) {
			// sucess
		}
		Artifact correctsubArtifact = new Artifact(subTool);
		artifact.add(correctsubArtifact);
	    //Assert.assertSame(correctsubArtifact,artifact.get)

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
		} catch (NullPointerException npe){
			//do nothing
		}
		dropOn = new Artifact(artifact.tool());
		Assert.assertFalse(artifact.isDropOn(dropOn));
	}

	@Test
	public void getData() {
		try {
			artifact.getData(10);
			Assert.fail("Needs to throw OutOfBounds when accesed beyond scope");
		} catch (IndexOutOfBoundsException x) {
			x.printStackTrace();

		}
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