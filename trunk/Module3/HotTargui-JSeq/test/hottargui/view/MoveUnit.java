package hottargui.view;

import minidraw.standard.*;
import minidraw.framework.*;

/*
 * Basic demo of gui changes are reflected in the domain.

 @author Henrik Bærbak Christensen

 */

public class MoveUnit {
  
  public static void main(String[] args) {
    TestStubFactory f = new TestStubFactory(); 
    DrawingEditor window = 
      new MiniDrawApplication( "Drag units to check movement. (4,4) is illegal.",
                               f );
    StubGame1 game = (StubGame1) f.game;

    window.setTool( new UnitMoveTool(window,game) );

    window.open();
  }
}


