import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.ColourT;
import src.ObjectiveT;

public class TestObjectiveT {

	private ObjectiveT obj;

	@Before
	public void setUp() throws Exception {
		obj = new ObjectiveT(ColourT.R, 5);

	}

	@After
	public void tearDown() throws Exception {
		obj = null;

	}

	@Test
	public void testObjectiveT() {
		assertTrue(obj != null);

    }

    @Test (expected=IllegalArgumentException.class)
	public void testObjectiveTException1()
	{
        obj = new ObjectiveT(ColourT.R, -5);
	}
    
    @Test (expected=IllegalArgumentException.class)
	public void testObjectiveTException2()
	{
        obj = new ObjectiveT(ColourT.R, 1);
	}

	@Test
	public void testCol() {
		assertEquals(obj.col(), ColourT.R);
	}

	@Test
	public void testGoal() {
		assertEquals(obj.goal(), 5);
	}

	@Test
	public void testComplete() {
		assertTrue(!obj.complete());
    }
    
	@Test
	public void testAttempt1() {
        obj.attempt(ColourT.R, 5);
		assertTrue(obj.complete());
    }
    
    @Test
	public void testAttempt2() {
        obj.attempt(ColourT.R, 3);
		assertTrue(!obj.complete());
    }
    
    @Test
	public void testAttempt3() {
        obj.attempt(ColourT.B, 5);
		assertTrue(!obj.complete());
    }
    
    @Test
	public void testAttempt4() {
        obj.attempt(ColourT.R, 5);
        obj.attempt(ColourT.R, 1);
		assertTrue(obj.complete());
	}

}
