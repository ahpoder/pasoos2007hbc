import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
  @Suite.SuiteClasses({ TestAlphaTown.class,
                        TestBetaTown.class} )
  
/** Suite to run all test cases for PayStation.
    Author: (c) Henrik Bærbak Christensen 2007 */


public class TestAll {
  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestAll.class);
  } 

}
