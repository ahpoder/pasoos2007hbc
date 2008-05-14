package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

/** A tool to turn the upper card on the tribe card stack.

    Author: Henrik Bærbak Christensen

*/

public class TurnTribeCardTool extends AbstractTool {
  Game game;
  public TurnTribeCardTool(DrawingEditor editor, Game game) {
    super(editor);
    this.game = game;
  }
  public void mouseDown(MouseEvent evt, int x, int y) { 
    game.turnCard();
  }
}
