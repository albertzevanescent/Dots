import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.PointT;
import src.LineT;
import src.PathT;
import java.util.ArrayList;

public class TestPathT {

    private PointT p1;
    private PointT p2;
    private PointT p3;
    private LineT l1;
    private LineT l2;
    private PathT p;


	@Before
	public void setUp() throws Exception {
		p1 = new PointT(2, 3);
		p2 = new PointT(2, 8);
        p3 = new PointT(7, 8);
        l1 = new LineT(p1, p2);
        l2 = new LineT(p2, p3);
        p = new PathT();
        p.add(l1);

	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
		p1 = null;
        p1 = null;
        l1 = null;
        l2 = null;
        p = null;

	}

	@Test
	public void testPathT() {
		assertTrue(p != null);

    }

	@Test
	public void testAdd() {
        int x = p.mag();
        p.add(l2);
        assertTrue(x != p.mag());
	}

	@Test
	public void testValid1() {
        assertTrue(p.valid());
    }

    @Test
	public void testValid2() {
        p.add(l2);
        assertTrue(p.valid());
    }

    @Test
	public void testValid3() {
        p = new PathT();
        p.add(l2);
        p.add(l1);
        assertTrue(!p.valid());
    }
    
    @Test
	public void testMag1() {
        assertEquals(p.mag(), 6);
    }

    @Test
	public void testMag2() {
        p.add(l2);
        assertEquals(p.mag(), 11);
    }
    
    @Test
	public void testPoints1() {
        ArrayList<PointT> pts = p.points();

        for(int i = 0; i < pts.size(); i++){
            assertEquals(pts.get(i).row(), l1.start().row());
            assertEquals(pts.get(i).col(), l1.start().col() + i);
        }
    }

    @Test
	public void testPoints2() {
        p.add(l2);
        ArrayList<PointT> pts = p.points();

        int i = 0;
        for(i = 0; i < l1.mag(); i++){
            assertEquals(pts.get(i).row(), l1.start().row());
            assertEquals(pts.get(i).col(), l1.start().col() + i);
        }

        for(i = i; i < l2.mag(); i++){
            assertEquals(pts.get(i).row(), l2.start().row() + 1);
            assertEquals(pts.get(i).col(), l2.start().col());
        }
	}

}
