package hottargui.view;

import minidraw.standard.*;
import minidraw.framework.*;

/*
 * Basic demo of opening window with the background

 @author Henrik Bærbak Christensen

 */

public class ShowBoardComputed {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "Show Computed Board",
                               new TestStubFactory() );
    window.open();
  }
}


