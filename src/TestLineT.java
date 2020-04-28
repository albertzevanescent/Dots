import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.PointT;
import src.LineT;
import java.util.ArrayList;

public class TestLineT {

    private PointT p;
    private PointT q;
    private LineT l;

	@Before
	public void setUp() throws Exception {
		p = new PointT(2, 3);
        q = new PointT(2, 8);
        l = new LineT(p, q);

	}

	@After
	public void tearDown() throws Exception {
		p = null;
		q = null;
		l = null;

	}

	@Test
	public void testLineT() {
		assertTrue(!l.equals(null));

    }
    
    @Test (expected=IllegalArgumentException.class)
	public void testLineTException()
	{
        p = new PointT(0, 0);
        q = new PointT(0, 0);
		l = new LineT(p, q);
	}

	@Test
	public void testStart() {
        assertEquals(l.start().row(), 2);
        assertEquals(l.start().col(), 3);
	}

	@Test
	public void testEnd() {
        assertEquals(l.end().row(), 2);
        assertEquals(l.end().col(), 8);
    }
    
    @Test
	public void testMag() {
        assertEquals(l.mag(), 6);
    }
    
    @Test
	public void testPoints1() {
        ArrayList<PointT> pts = l.points();

        for(int i = 0; i < pts.size(); i++){
            assertEquals(pts.get(i).row(), l.start().row());
            assertEquals(pts.get(i).col(), l.start().col() + i);
        }
    }

    @Test
	public void testPoints2() {
        l = new LineT(q, p);
        ArrayList<PointT> pts = l.points();

        for(int i = 0; i < pts.size(); i++){
            assertEquals(pts.get(i).row(), l.start().row());
            assertEquals(pts.get(i).col(), l.start().col() - i);
        }
	}
    
    @Test
	public void testPoints3() {
        p = new PointT(-2, 5);
        q = new PointT(-11, 5);
        l = new LineT(p, q);
        ArrayList<PointT> pts = l.points();

        for(int i = 0; i < pts.size(); i++){
            assertEquals(pts.get(i).row(), l.start().row() - i);
            assertEquals(pts.get(i).col(), l.start().col());
        }
    }
    
    @Test
	public void testPoints4() {
        p = new PointT(-2, 5);
        q = new PointT(-11, 5);
        l = new LineT(q, p);
        ArrayList<PointT> pts = l.points();

        for(int i = 0; i < pts.size(); i++){
            assertEquals(pts.get(i).row(), l.start().row() + i);
            assertEquals(pts.get(i).col(), l.start().col());
        }
	}

}
