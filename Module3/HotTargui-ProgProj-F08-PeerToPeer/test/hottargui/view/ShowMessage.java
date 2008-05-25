package hottargui.view;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

/*
 * Basic demo of Message figures.

 @author Henrik Bærbak Christensen

 */

public class ShowMessage {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "Display messages + "+
                               "Mouse Coordinate Print tool",
                               new TestStubFactory() );
    window.open();
   
    window.setTool( new PrintCoordinateTool(window) );

    MessageFigure mf = new MessageFigure();
    mf.setText("Made by Henrik Bærbak" );
    mf.moveBy(40,20);
    window.drawing().add(mf);

    Messenger m = new Messenger(window.drawing());
    m.setText( "A Messenger text will appear and vanish in 5 secs..." );
  }
}

class PrintCoordinateTool extends AbstractTool {
  public PrintCoordinateTool(DrawingEditor editor) {
    super(editor);
  }
  public void mouseDown(MouseEvent evt, int x, int y) { 
    System.out.println( "Mouse down at ("+x+","+y+")" );
  }
}

