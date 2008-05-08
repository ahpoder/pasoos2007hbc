package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;
import minidraw.framework.*;

/*
 * Basic demo of rolling a die

 @author Henrik Bærbak Christensen

 */

public class RollDie {
  
  public static void main(String[] args) {
    TestStubFactory f = new TestStubFactory(); 
    DrawingEditor window = 
      new MiniDrawApplication( "Click to roll the die", f);

    Game game = f.game;

    window.setTool( new RollDieTool(window, game) );

    window.open();
  }
}
