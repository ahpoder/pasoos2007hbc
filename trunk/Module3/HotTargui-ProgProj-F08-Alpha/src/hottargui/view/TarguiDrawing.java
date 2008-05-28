package hottargui.view;

import hottargui.framework.*;

import minidraw.framework.*;
import minidraw.standard.*;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/** Instances of this class serves the Drawing role of MiniDraw and
    as GameListener on the HotTargui Game instance.

    Responsibility:
    A) To ensure that the drawing's list of unit figures reflect that
    of the underlying Game.
    B) To react when Game state changes happens as informed by the
    GameListener protocol 
*/

public class TarguiDrawing 
  extends StandardDrawing 
  implements GameListener {

  /** The game instance this drawing is associated with */
  protected Game game;

  /** The die figure we simply reuse */
  protected DieFigure die;

  /** The card figure we reuse */
  protected CardFigure card;
  
  public TarguiDrawing( Game game ) {
    super();
    this.game = game;

    defineUnits();
    defineDie();
    defineCard();
  }

  /**
   * This methods should only be used to add "additional"
   * figures, like messages to the drawing area.
   * The unit figures etc. are added by the observer
   * mechanism.
   */
  //public Figure add(Figure figure);



  public  void updateTile( Tile t ) {
    // TODO: Change so each tile can be defined individually
    defineUnits();
    // note that unit figures only report figure invalidation,
    // but not changes, this means we can request redrawing
    // only once by asking the drawing to 'requestUpdate'
    requestUpdate();
  }

  public void updateDie( int value ) {
    die.changeToValue(value);
  }

  public void updatePlayer( PlayerColor color ) {
    card.changeToValue( color );
    // new player in turn also meas we must update the
    // units in the treasury
    defineUnits();
  }

  public void report(String text) {
    Messenger m = new Messenger(this);
    m.setText(text);
  }

  /** define all units on tiles as well as players treasury */
  protected void defineUnits() {
    // First, remove all existing unitfigures -
    // we cannot simply remove everthing as thre
    // may be other figures floating around.
    List<Figure> removeList = new ArrayList<Figure>();
    for (Figure f : fFigures ) { 
      if ( f instanceof UnitFigure ) {
        removeList.add(f);
      }
    }
    for (Figure f : removeList ) {
      fFigures.remove(f);
    }
    
    // Second define the unit figures on board
    Iterator<? extends Tile> i = game.getBoardIterator();
    while ( i.hasNext() ) {
      Tile t = i.next();
      int units = t.getUnitCount();
      
      Position p = t.getPosition();
      int x = 
        TarguiViewDefinition.BOARD_OFFSET_X +
        TarguiViewDefinition.TILE_IMAGE_SIZE * (p.getColumn()+1);
      int y = 
        TarguiViewDefinition.BOARD_OFFSET_Y +
        TarguiViewDefinition.TILE_IMAGE_SIZE * (p.getRow()+1);
      
      createUnitsInRectangle( units, x, y,
                              TarguiViewDefinition.TILE_IMAGE_SIZE ); 
    }
    // Define the units in the treasury
    Player p = game.getPlayerInTurn();
    if ( p != null ) {
      int treasury = p.getCoins();
      createUnitsInRectangle( treasury, 
                              TarguiViewDefinition.TREASURY_X,
                              TarguiViewDefinition.TREASURY_Y,
                              TarguiViewDefinition.TREASURY_BOX_HEIGHT );
    }
  }

  /** define the die */
  protected void defineDie() {
    die = new DieFigure( new Point(720,195) );
    addFigureToContents(die);
  }
  
  /** define the card */
  protected void defineCard() {
    card = 
      new CardFigure( new Point(TarguiViewDefinition.TRIBE_CARD_OFFSET_X,
                                TarguiViewDefinition.TRIBE_CARD_OFFSET_Y) );
    addFigureToContents(card);
  }

  /** create unit figures corresponding to 'units' units in a rectangle
      whose bottom right corner is defined by (x,y) and whose height
      is 'maxHeight' */
  protected void createUnitsInRectangle(int units, int x, int y, 
                                         int maxHeight) {
    int five, one;
    if ( units < 10 ) {
      five = 0; one = units;
    } else {
      five = units / 5 - 1; one = units % 5 + 5;
    }

    Figure f;

    int 
      nx = x - TarguiViewDefinition.MARKER_DIAMETER, 
      ny = y - TarguiViewDefinition.MARKER_DIAMETER;
    int dx, dy;
    
    int stackSize = maxHeight / TarguiViewDefinition.MARKER_DIAMETER;

    for ( int j = 0; j < five; j++ ){
      dx = (j / stackSize) * (- TarguiViewDefinition.MARKER_DIAMETER);
      dy = (j % stackSize) * (- TarguiViewDefinition.MARKER_DIAMETER);

      Point pt = new Point(nx+dx, ny+dy);
      f = new UnitFigure(UnitFigure.UnitType.FIVE, pt);
      addFigureToContents(f);
    }

    for ( int j = 0; j < one; j++ ){
      dx = ((j+five) / stackSize) * (- TarguiViewDefinition.MARKER_DIAMETER);
      dy = ((j+five) % stackSize) * (- TarguiViewDefinition.MARKER_DIAMETER);

      Point pt = new Point(nx+dx, ny+dy);
       f = new UnitFigure(UnitFigure.UnitType.ONE, pt );
      addFigureToContents(f);
    }
  }

  /** use this method to add figures to the drawing's fFigures,
      never add directly to the list! */
  protected void addFigureToContents(Figure f) {
    fFigures.add(f);
    f.addFigureChangeListener(this);
    f.invalidate();
  }
    
}
