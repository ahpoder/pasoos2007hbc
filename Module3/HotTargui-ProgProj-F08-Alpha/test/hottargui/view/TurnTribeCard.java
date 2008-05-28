package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;
import minidraw.framework.*;

/*
 * Basic demo of turning tribe card

 @author Henrik Bærbak Christensen

 */

public class TurnTribeCard {
  
  public static void main(String[] args) {
    TestStubFactory f = new TestStubFactory(); 
    DrawingEditor window = 
      new MiniDrawApplication( "Click to flip tribe card", f);

    Game game = f.game;

    window.setTool( new TurnTribeCardTool(window, game) );

    window.open();
  }
}

