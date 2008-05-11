package hottargui.view;

import hottargui.framework.*;

import minidraw.framework.*;

import java.awt.Rectangle;
import java.util.Iterator;

/** Selecting figures in Targui differs from normal MiniDraw processing and
    this strategy implements the peculiarities.

    A) Only units from the same tile can be selected; alternatively only
    from the treasury.

    You must make the first mouse down click within the tile that you
    want units to move from!

    Author Henrik Bærbak Christensen

*/

public class TarguiSelectionStrategy implements RubberBandSelectionStrategy {

  public void selectGroup(Drawing model, Rectangle rubberBandRectangle,
                          boolean toggle) {
    // Define the tile position where the rubberband starts
    Position pstart = 
      TarguiViewDefinition.getTilePositionFromXY( rubberBandRectangle.x,
                                                  rubberBandRectangle.y );
    boolean isTreasury = false;

    // TODO: remove magic constant
    if ( pstart.getColumn() > 6 ) {
      isTreasury = true;
    }
    
    Iterator<Figure> i = model.iterator();
    while ( i.hasNext() ) {
      Figure figure = i.next();
      if ( ! (figure instanceof UnitFigure) ) { continue; } 
      // calculate this figure's position on the board
      Rectangle r2 = figure.displayBox();
      Position p2 =
        TarguiViewDefinition.getTilePositionFromXY( r2.x, r2.y );
      if ( ! isTreasury ) {
        if ( pstart.equals( p2 ) &&
             rubberBandRectangle.contains(r2) ) {
          model.addToSelection(figure);
        }
      } else {
        if ( p2.getColumn() > 6  && 
             rubberBandRectangle.contains(r2) ) {
          model.addToSelection(figure);
        }
      }
    }
  }

}
