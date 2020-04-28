/**
 * Author: S. Smith
 * Revised: March 18, 2008
 * 
 * Description: Testing all of the modules
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestPointT.class,
    TestLineT.class,
    TestPathT.class,
    TestObjectiveT.class,
    TestLevelT.class,
    TestBoardT.class,
    TestModelT.class
})

public class AllTests
{
}
