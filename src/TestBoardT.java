import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.ColourT;
import src.PointT;
import src.LineT;
import src.PathT;
import src.BoardT;

import java.util.ArrayList;
import java.util.Arrays;

public class TestBoardT {

    private ArrayList<ColourT> r1;
    private ArrayList<ColourT> r2;
    private ArrayList<ColourT> r3;
    private ArrayList<ArrayList<ColourT>> cols;
    private BoardT b;
    private LineT l1;
    private LineT l2;
	private PathT p;

	@Before
	public void setUp() throws Exception {
		r1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.B, ColourT.B));
		r2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.B));
		r3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.R, ColourT.B));
		cols = new ArrayList<ArrayList<ColourT>>();
		cols.add(r3);
		cols.add(r2);
		cols.add(r1);
		b = new BoardT(cols);
		l1 = new LineT(new PointT(0, 2), new PointT(2, 2));
		l2 = new LineT(new PointT(2, 2), new PointT(2, 1));
        p = new PathT();
		p.add(l1);
		p.add(l2);
	}

	@After
	public void tearDown() throws Exception {
        r1 = null;
        r2 = null;
        r3 = null;
        cols = null;
        b = null;
        l1 = null;
        l2 = null;
        p = null;
        }
    
	@Test
	public void testBoardT() {
		assertTrue(!b.equals(null));
    }
    
	@Test (expected=IllegalArgumentException.class)
	public void testBoardTException1()
	{
        cols = new ArrayList<ArrayList<ColourT>>();
		b = new BoardT(cols);
	}
 
	@Test (expected=IllegalArgumentException.class)
	public void testBoardTException2()
	{
        cols = new ArrayList<ArrayList<ColourT>>();
        cols.add(new ArrayList<ColourT>());
		b = new BoardT(cols);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testBoardTException3()
	{
        r1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.B, ColourT.B));
        r2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G));
        r3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R));
        cols = new ArrayList<ArrayList<ColourT>>();
        cols.add(r3);
        cols.add(r2);
        cols.add(r1);
        b = new BoardT(cols);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testBoardTException4()
	{
        r1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.B, ColourT.B));
        r2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.B));
        r3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.W, ColourT.B));
        cols = new ArrayList<ArrayList<ColourT>>();
        cols.add(r3);
        cols.add(r2);
        cols.add(r1);
        b = new BoardT(cols);
    }

    @Test
	public void testBoard()
	{
        ArrayList<ColourT> d1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.B, ColourT.B));
        ArrayList<ColourT> d2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.B));
        ArrayList<ColourT> d3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.R, ColourT.B));
        ArrayList<ArrayList<ColourT>> dcols = new ArrayList<ArrayList<ColourT>>();
        dcols.add(d3);
        dcols.add(d2);
        dcols.add(d1);

		ArrayList<ArrayList<ColourT>> board = b.board();
        for(int i = 0; i < board.get(0).size(); i++)
            for(int j = 0; j < board.size(); j++)
				assertEquals(board.get(i).get(j), dcols.get(i).get(j));        
	}

    @Test
	public void testValidPath1()
	{
        assertTrue(b.validPath(p));
    }

    @Test
	public void testValidPath2()
	{
        l1 = new LineT(new PointT(0, 2), new PointT(2, 2));
        l2 = new LineT(new PointT(2, 2), new PointT(2, 5));
        p = new PathT();
		p.add(l1);
        p.add(l2);
        assertTrue(!b.validPath(p));
    }

    @Test
	public void testValidPath3()
	{
        l1 = new LineT(new PointT(0, 2), new PointT(2, 2));
        l2 = new LineT(new PointT(2, 2), new PointT(2, 0));
        p = new PathT();
        p.add(l1);
        p.add(l2);
        assertTrue(!b.validPath(p));
    }

	@Test (expected=IllegalArgumentException.class)
	public void testValidPathException()
	{
        l1 = new LineT(new PointT(0, 2), new PointT(2, 2));
        l2 = new LineT(new PointT(2, 0), new PointT(2, 1));
        p = new PathT();
        p.add(l1);
        p.add(l2);
        assertTrue(!b.validPath(p));
    }

    @Test
	public void testPathCol()
	{
        assertEquals(b.pathCol(p), ColourT.B);
    }

    @Test (expected=IllegalArgumentException.class)
	public void testPathColException()
	{
        l1 = new LineT(new PointT(0, 2), new PointT(2, 2));
        l2 = new LineT(new PointT(2, 2), new PointT(2, 5));
        p = new PathT();
        p.add(l1);
        p.add(l2);
        assertEquals(b.pathCol(p), ColourT.B);
    }

    @Test
	public void testRmPath1()
	{
        ArrayList<ColourT> d1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.W, ColourT.W));
        ArrayList<ColourT> d2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.W));
        ArrayList<ColourT> d3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.R, ColourT.W));
        ArrayList<ArrayList<ColourT>> dcols = new ArrayList<ArrayList<ColourT>>();
        dcols.add(d3);
        dcols.add(d2);
        dcols.add(d1);

        b.rmPath(p);
        for(int i = 0; i < dcols.get(0).size(); i++)
            for(int j = 0; j < dcols.size(); j++)
                if(dcols.get(i).get(j) == ColourT.W)
                    assertTrue(b.board().get(i).get(j) != ColourT.W);
                else
                    assertEquals(b.board().get(i).get(j), dcols.get(i).get(j));
	}
	
	@Test
	public void testRmPath2()
	{
        ArrayList<ColourT> d1 = new ArrayList<ColourT>(Arrays.asList(ColourT.W, ColourT.W, ColourT.B));
        ArrayList<ColourT> d2 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.B, ColourT.B));
        ArrayList<ColourT> d3 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.B));
        ArrayList<ArrayList<ColourT>> dcols = new ArrayList<ArrayList<ColourT>>();
        dcols.add(d3);
        dcols.add(d2);
        dcols.add(d1);

        p = new PathT();
        p.add(new LineT(new PointT(0, 0), new PointT(0, 1)));

        b.rmPath(p);
        for(int i = 0; i < dcols.get(0).size(); i++)
            for(int j = 0; j < dcols.size(); j++)
                if(dcols.get(i).get(j) == ColourT.W)
                    assertTrue(b.board().get(i).get(j) != ColourT.W);
                else
                    assertEquals(b.board().get(i).get(j), dcols.get(i).get(j));
	}
	
	@Test
	public void testRmPath3()
	{
		ArrayList<ColourT> r4, r5;
		LineT l3;

		r1 = new ArrayList<ColourT>(Arrays.asList(ColourT.O, ColourT.P, ColourT.B, ColourT.G, ColourT.R));
		r2 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.P, ColourT.G, ColourT.B, ColourT.R));
		r3 = new ArrayList<ColourT>(Arrays.asList(ColourT.O, ColourT.P, ColourT.R, ColourT.P, ColourT.O));
		r4 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.P, ColourT.B, ColourT.P, ColourT.P));
		r5 = new ArrayList<ColourT>(Arrays.asList(ColourT.B, ColourT.P, ColourT.P, ColourT.P, ColourT.O));
		cols = new ArrayList<ArrayList<ColourT>>();
		cols.add(r5);
		cols.add(r4);
		cols.add(r3);
		cols.add(r2);
		cols.add(r1);
		b = new BoardT(cols);
		l1 = new LineT(new PointT(2, 3), new PointT(0, 3));
		l2 = new LineT(new PointT(0, 3), new PointT(0, 1));
		l3 = new LineT(new PointT(0, 1), new PointT(4, 1));
        p = new PathT();
        p.add(l1);
		p.add(l2);
		p.add(l3);

        ArrayList<ColourT> d1 = new ArrayList<ColourT>(Arrays.asList(ColourT.O, ColourT.W, ColourT.W, ColourT.W, ColourT.R));
        ArrayList<ColourT> d2 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.W, ColourT.B, ColourT.W, ColourT.R));
        ArrayList<ColourT> d3 = new ArrayList<ColourT>(Arrays.asList(ColourT.O, ColourT.W, ColourT.G, ColourT.W, ColourT.O));
        ArrayList<ColourT> d4 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.W, ColourT.R, ColourT.G, ColourT.P));
        ArrayList<ColourT> d5 = new ArrayList<ColourT>(Arrays.asList(ColourT.B, ColourT.W, ColourT.B, ColourT.B, ColourT.O));
		ArrayList<ArrayList<ColourT>> dcols = new ArrayList<ArrayList<ColourT>>();
		dcols.add(d5);
        dcols.add(d4);
        dcols.add(d3);
        dcols.add(d2);
        dcols.add(d1);

        b.rmPath(p);
        for(int i = 0; i < dcols.get(0).size(); i++)
            for(int j = 0; j < dcols.size(); j++)
                if(dcols.get(i).get(j) == ColourT.W)
                    assertTrue(b.board().get(i).get(j) != ColourT.W);
                else
					assertEquals(b.board().get(i).get(j), dcols.get(i).get(j));
    }

    @Test (expected=IllegalArgumentException.class)
	public void testRmPathException()
	{
        l1 = new LineT(new PointT(2, 2), new PointT(0, 2));
        l2 = new LineT(new PointT(0, 2), new PointT(0, 5));
        p = new PathT();
        p.add(l1);
        p.add(l2);
        b.rmPath(p);
    }

}
