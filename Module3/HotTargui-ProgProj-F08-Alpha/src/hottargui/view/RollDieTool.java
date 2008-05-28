package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

/** A tool to roll the die.

    Author: Henrik Bærbak Christensen

*/

public class RollDieTool extends AbstractTool {
  Game game;
  public RollDieTool(DrawingEditor editor, Game game) {
    super(editor);
    this.game = game;
  }
  public void mouseDown(MouseEvent evt, int x, int y) { 
    game.rollDie();
  }
}
