import junit.framework.*;

/** Test cases for NumberDisplay.

    Author: (c) Henrik Bærbak Christensen 2006
*/


public class TestNumberDisplay extends TestCase {
  public void setUp() {
  }
  public void testOne() {
    assertEquals( false, true );
  }
  public static Test suite() {
    return new TestSuite(TestNumberDisplay.class);
  }
}
