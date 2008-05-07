package hottargui.view;

import hottargui.framework.*;

import minidraw.framework.*;
import minidraw.standard.*;
import minidraw.standard.handlers.*;

import java.awt.Point;
import java.awt.event.MouseEvent;

/** UnitMoveTool: A MiniDraw tool to move units on the playing area.

 * It is closely modelled over the SelectionTool. 

 * Author: Henrik Bærbak Christensen 2006
*/

public class UnitMoveTool extends AbstractTool {

  /** UnitMoveTool's internal state is like SelectionTool defined
      by a subtool */
  protected Tool subtool,nulltool;
  private Figure movedFigure;
  private Point startPoint;
  private Game game;

  // TODO: refactor to use an object server instead
  public UnitMoveTool(DrawingEditor editor, Game game) {
    super(editor);
    this.game = game;
    nulltool = new NullTool();
    subtool = nulltool;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e,x,y);
    Drawing model = editor().drawing();
    model.lock();
    startPoint = new Point(x,y);
    
    movedFigure = model.findFigure(e.getX(), e.getY());
    if ( movedFigure != null &&
         movedFigure instanceof UnitFigure ) {
      subtool = new DragTracker( editor(), movedFigure );
    } else {
      movedFigure = null;
      if ( ! e.isShiftDown() ) {
        model.clearSelection();
      }
      subtool = new SelectAreaTracker( editor(), 
                                       new TarguiSelectionStrategy() );
    }
    subtool.mouseDown(e, x, y);
  }

  public void mouseDrag(MouseEvent e, int x, int y) {
    subtool.mouseDrag(e, x, y);
  }

  public void mouseMove(MouseEvent e, int x, int y) {
    subtool.mouseMove(e, x, y);
  }

  public void mouseUp(MouseEvent e, int x, int y) {
    super.mouseUp(e,x,y);
    editor().drawing().unlock();
    subtool.mouseUp(e,x,y);
    subtool = nulltool;

    // allow normal movement of message figures;
    // however, unit must be treated with care.
    if ( movedFigure instanceof UnitFigure ) {
      Drawing d = editor().drawing(); 

      Position
        boardStartPosition = mouse2pos(startPoint),
        boardEndPosition = mouse2pos(new Point(x,y) );
      
      // flag that it is units from the treasury that
      // are going to be put onto the board.
      // TODO: Remove magic constant.
      boolean treasuryMove =
        boardStartPosition.getColumn() > 6;
      // flag if start position is a legal tile
      boolean startTileLegal = 
        TarguiViewDefinition.legalPosition(boardStartPosition);
      // flag if end position is legal tile
      boolean endTileLegal =
        TarguiViewDefinition.legalPosition(boardEndPosition);
      // calculate # of units in the selection
      int unitsToMove = calculateUnitsInSelection(d);
      
      // Did the Targui game state that the move was legal?
      boolean actionLegalInTargui = false;
      
      // OK: perform the actions
      if ( treasuryMove && endTileLegal ) {
        // buying camels
        actionLegalInTargui = game.buy(unitsToMove, boardEndPosition );
      } else if ( !treasuryMove &&
                  startTileLegal && endTileLegal ) {
        // moving - test that the move is not to the same
        // tile
        if ( boardStartPosition.equals(boardEndPosition) ) {
          unitsToMove = 0;
        }
        actionLegalInTargui = 
          game.move(boardStartPosition, boardEndPosition, unitsToMove );
      }
      
      if ( ! actionLegalInTargui ) {
        undoMove(x,y);
      }
      d.clearSelection();
    }
    movedFigure = null;
  }

  /** convert a mouse position to board position */
  private Position mouse2pos(Point p) {
    Position
      pos =
      TarguiViewDefinition.getTilePositionFromXY(p.x, p.y);
    return pos;
  }

  /** move back all selected figures to their starting position.
   * @param x the x position to move from
   * @param y the y position to move from
   */
  private void undoMove(int x, int y) {
    for ( Figure f : editor().drawing().selection() ) {
      f.moveBy( startPoint.x - x,
                startPoint.y - y );
    }
  }


  private int calculateUnitsInSelection(Drawing d) {
    int unitsToMove = 0;
    for ( Figure f: d.selection() ) {
      UnitFigure uf = (UnitFigure) f;
      if ( uf.getType() == UnitFigure.UnitType.FIVE ) {
        unitsToMove += 5;
      } else {
        unitsToMove++;
      }
    }
    return unitsToMove;
  }
}
