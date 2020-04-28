import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.PointT;

public class TestPointT {

	private PointT p;

	@Before
	public void setUp() throws Exception {
		p = new PointT(0, 0);

	}

	@After
	public void tearDown() throws Exception {
		p = null;

	}

	@Test
	public void testPointT() {
		assertTrue(p != null);

	}

	@Test
	public void testRow() {
		assertEquals(p.row(), 0);
	}

	@Test
	public void testCol() {
		assertEquals(p.col(), 0);
	}

	@Test
	public void testEquals() {
		PointT q = new PointT(0, 0);
		assertTrue(p.equals(q));

	}

}
