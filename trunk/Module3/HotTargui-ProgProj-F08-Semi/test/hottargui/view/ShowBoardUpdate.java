package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

/*
 * Basic demo of how board changes are propagated to GUI via 
   observer events.

 @author Henrik Bærbak Christensen

 */

public class ShowBoardUpdate {
  
  public static void main(String[] args) {
    TestStubFactory f = new TestStubFactory(); 
    DrawingEditor window = 
      new MiniDrawApplication( "Click to see board updates effected",
                               f );
    Game game = f.game;

    window.setTool( new BoardUpdateTool(window, game) );

    window.open();

    window.view().checkDamage();
  }
}

class BoardUpdateTool extends AbstractTool {
  StubGame1 game;
  public BoardUpdateTool(DrawingEditor editor, Game game ) {
    super(editor);
    this.game = (StubGame1) game;
  }
  int t = 0;
  public void mouseUp(MouseEvent evt, int x, int y) { 
    switch ( t ) {
    case 0:
      
      game.fakeMove( 0,0, PlayerColor.Red, 18,
                     1,1, PlayerColor.Red, 5 );
      break;
    case 1:
      game.fakeMove( 0,4, PlayerColor.Green, 3,
                     1,3, PlayerColor.Green, 8 );
      break;
    case 2:
      game.fakeMove( 1,1, PlayerColor.Red, 0,
                     2,2, PlayerColor.Red, 5 );
      break;
    case 3:
      game.fakeMove( 6,6, PlayerColor.Yellow, 6,
                     6,5, PlayerColor.Yellow, 4 );
      break;
    case 4:
      game.fakeMove( 2,2, PlayerColor.Yellow, 29,
                     2,3, PlayerColor.Yellow, 35 );
      break;

    case 5:
      System.out.println("Only 3 and 7 left on (2,2) and (2,3)" );
      game.fakeMove( 2,2, PlayerColor.Yellow, 3,
                     2,3, PlayerColor.Yellow, 7 );
      break;

    case 6:
      game.fakeMove( 0,0, PlayerColor.Red, 11,
                     1,0, PlayerColor.Red, 7 );
      break;

    case 7:
      game.fakeMove( 0,0, PlayerColor.Red, 18,
                     1,0, PlayerColor.Red, 0 );
      break;


    case 8:
      System.out.println( "*** Using real move method *** " );
      game.move(new Position(0,0), new Position(1,0),
                8 );
      break;
      
    default: 
    }
    t++;
  }
}
