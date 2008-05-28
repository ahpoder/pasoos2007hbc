package hottargui.view;

import hottargui.framework.*;

import minidraw.framework.*;

import javax.swing.JTextField;

/** Testing factory that returns a drawing view with backgammon background 

 @author Henrik Bærbak Christensen
 
*/

class TestStubFactory implements Factory {
  
  Game game;
  public TestStubFactory() {
    System.out.println( minidraw.RevisionInfo.RevisionString );
     game = new StubGame1();
  }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    return new BoardView( editor, game );
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    TarguiDrawing d = new TarguiDrawing( game );
    game.addGameListener( d );
    return d;
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }

}
