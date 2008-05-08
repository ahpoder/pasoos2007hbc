package hottargui.view;

import minidraw.standard.*;
import minidraw.framework.*;

import javax.swing.*;

/*
 * Basic demo of dual view.

 @author Henrik Bærbak Christensen

 */

public class MultiView {
  
  public static void main(String[] args) {
    TestStubFactory f = new TestStubFactory(); 
    DrawingEditor window = 
      new MiniDrawApplication( "MultiView - you may drag units.",
                               f );
    StubGame1 game = (StubGame1) f.game;

    window.setTool( new UnitMoveTool(window,game) );

    window.open();

    // create second view
    JFrame newWindow = new JFrame("Extra View");
    newWindow.setDefaultLookAndFeelDecorated(true);
    newWindow.setLocation( 620, 20 );
    newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DrawingView extraView = f.createDrawingView(window);
    JPanel panel = (JPanel) extraView;
    newWindow.getContentPane().add(panel);
    newWindow.pack();
    newWindow.setVisible(true);
 
  }
}


