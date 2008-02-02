package paystation.domain;

import junit.framework.*;

public class TestAll extends TestCase {

  public static Test suite() {
    TestSuite ts = new TestSuite();
    ts.addTest(TestPayStationAlpha.suite());
    ts.addTest(TestPayStationBeta.suite());
    return ts;    
  }
}
