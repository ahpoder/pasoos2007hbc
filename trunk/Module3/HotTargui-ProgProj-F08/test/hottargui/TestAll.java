package hottargui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
  @Suite.SuiteClasses(
  { hottargui.config.TestAlphaTargui.class,
    hottargui.config.TestBoard.class,
  })
  
  public class TestAll {
    // dummy
  }
