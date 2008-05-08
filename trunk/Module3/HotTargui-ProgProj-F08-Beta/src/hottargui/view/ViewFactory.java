package hottargui.view;

import hottargui.framework.*;

import minidraw.framework.*;

import javax.swing.JTextField;

/** View factory that returns a drawing view with backgammon background 

 @author Henrik Bærbak Christensen
 
*/

/**
 * Factory for creating the drawing and its view.
 */
public class ViewFactory implements Factory {
    private Game game;

    public ViewFactory(Game game) {
        this.game = game;
    }

    public DrawingView createDrawingView(DrawingEditor editor) {
        return new BoardView(editor, game);
    }

    public Drawing createDrawing(DrawingEditor editor) {
        TarguiDrawing d = new TarguiDrawing(game);
        game.addGameListener(d);
        return d;
    }

    public JTextField createStatusField( DrawingEditor editor ) {
      return null;
    }
}
