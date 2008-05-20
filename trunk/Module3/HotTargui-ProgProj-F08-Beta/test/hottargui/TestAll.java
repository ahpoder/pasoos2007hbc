package hottargui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
  @Suite.SuiteClasses(
  {   
	hottargui.config.TestAlphaTargui.class,
	hottargui.config.TestAlphaBoard.class,
	hottargui.config.TestPutUnitsStrategy.class
  })
  
  public class TestAll {
    // dummy
  }
