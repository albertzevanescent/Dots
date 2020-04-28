import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.ColourT;
import src.ObjectiveT;
import src.LevelT;
import java.util.ArrayList;

public class TestLevelT {

    private ObjectiveT obj1;
    private ObjectiveT obj2;
    private ArrayList<ObjectiveT> objs;
    private LevelT lv;


	@Before
	public void setUp() throws Exception {
        obj1 = new ObjectiveT(ColourT.R, 5);
        obj2 = new ObjectiveT(ColourT.B, 8);
        objs = new ArrayList<ObjectiveT>();
        objs.add(obj1);
        objs.add(obj2);
        lv = new LevelT(objs, 10);
	}

	@After
	public void tearDown() throws Exception {
        obj1 = null;
        obj2 = null;
        objs = null;
        lv = null;
	}

	@Test
	public void testLevelT() {
		assertTrue(lv != null);
    }

    @Test (expected=IllegalArgumentException.class)
	public void testLevelTException1()
	{
        lv = new LevelT(objs, -5);
	}
    
    @Test (expected=IllegalArgumentException.class)
	public void testLevelTException2()
	{
        lv = new LevelT(objs, 4);
	}

	@Test
	public void testObjectives() {
        ArrayList<ObjectiveT> objecs = lv.objectives();
        for(int i = 0; i < objecs.size(); i++){
            assertEquals(objs.get(i).col(), objecs.get(i).col());
            assertEquals(objs.get(i).goal(), objecs.get(i).goal());
        }
	}

	@Test
	public void testMoves() {
        assertEquals(lv.moves(), 10); 
    }

	@Test
	public void testComplete1() {
        assertTrue(!lv.complete(1)); 
    }

    @Test
	public void testComplete2() {
        assertTrue(!lv.complete(15));
    }
    
	@Test
	public void testAttempt1() {
        lv.attempt(ColourT.R, 5);
		assertTrue(!lv.complete(3));
    }
    
	@Test
	public void testAttempt2() {
        lv.attempt(ColourT.R, 5);
        lv.attempt(ColourT.B, 3);
		assertTrue(!lv.complete(5));
    }
    
	@Test
	public void testAttempt3() {
        lv.attempt(ColourT.R, 5);
        lv.attempt(ColourT.B, 3);
        lv.attempt(ColourT.B, 10);
		assertTrue(!lv.complete(15));
    }

    @Test
	public void testAttempt4() {
        lv.attempt(ColourT.R, 5);
        lv.attempt(ColourT.B, 3);
        lv.attempt(ColourT.B, 10);
		assertTrue(lv.complete(5));
    }

}
