import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.ColourT;
import src.PointT;
import src.LineT;
import src.PathT;
import src.ObjectiveT;
import src.LevelT;
import src.BoardT;
import src.ModelT;

import java.util.ArrayList;
import java.util.Arrays;

public class TestModelT {

    private ArrayList<ColourT> r1;
    private ArrayList<ColourT> r2;
    private ArrayList<ColourT> r3;
    private ArrayList<ArrayList<ColourT>> cols;
    private BoardT b;
    private LineT l1;
    private LineT l2;
    private PathT p;
    private LevelT lv1;
    private LevelT lv2;
    private ModelT m;

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
        lv1 = new LevelT(new ArrayList<ObjectiveT>(Arrays.asList(new ObjectiveT(ColourT.B, 3))), 5);
        lv2 = new LevelT(new ArrayList<ObjectiveT>(Arrays.asList(new ObjectiveT(ColourT.R, 2))), 10);
        m = new ModelT(b, new ArrayList<LevelT>(Arrays.asList(lv1, lv2)));

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
        lv1 = null;
        lv2 = null;
        m = null;
	}

	@Test
	public void testModelT1() {
		assertTrue(!m.equals(null));
    }

    @Test
	public void testModelT2() {
        m = new ModelT(6, 5);
    }
    
    @Test (expected=IllegalArgumentException.class)
	public void testModelTException1()
	{
        m = new ModelT(0, 1);
    }
    
    @Test (expected=IllegalArgumentException.class)
	public void testModelTException2()
	{
        m = new ModelT(3, 0);
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

		BoardT board = m.board();
        for(int i = 0; i < dcols.get(0).size(); i++)
            for(int j = 0; j < dcols.size(); j++)
				assertEquals(board.board().get(i).get(j), dcols.get(i).get(j));        
    }
    
    @Test
	public void testLevel()
	{
        LevelT lv = new LevelT(new ArrayList<ObjectiveT>(Arrays.asList(new ObjectiveT(ColourT.B, 3))), 5);
        assertEquals(m.level().objectives().get(0).col(), lv.objectives().get(0).col());   
		assertEquals(m.level().objectives().get(0).goal(), lv.objectives().get(0).goal());     
    }
        
    @Test
	public void testlv()
	{
		assertEquals(m.lv(), 0);             
    }
    
    @Test
	public void testMoves()
	{
		assertEquals(m.moves(), 5);        
    }
    
    @Test
	public void testHasMoves()
	{
		assertTrue(m.hasMoves());        
    }
    
    @Test
	public void testValidMove()
	{
        assertTrue(m.validMove(p));
    }

    @Test
	public void testMakeMove()
	{
        ArrayList<ColourT> d1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.W, ColourT.W));
        ArrayList<ColourT> d2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.W));
        ArrayList<ColourT> d3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.R, ColourT.W));
        ArrayList<ArrayList<ColourT>> dcols = new ArrayList<ArrayList<ColourT>>();
        dcols.add(d3);
        dcols.add(d2);
        dcols.add(d1);

        m.makeMove(p);
        for(int i = 0; i < dcols.get(0).size(); i++)
            for(int j = 0; j < dcols.size(); j++)
                if(dcols.get(i).get(j) == ColourT.W)
                    assertTrue(m.board().board().get(i).get(j) != ColourT.W);
                else
                    assertEquals(m.board().board().get(i).get(j), dcols.get(i).get(j));

        assertEquals(m.moves(), 4);        
    }
    
    @Test
	public void testCompleteLv()
	{
        m.makeMove(p);
        m.completeLv();
        assertEquals(m.lv(), 1);        
        assertEquals(m.moves(), 10);        
	}

}
