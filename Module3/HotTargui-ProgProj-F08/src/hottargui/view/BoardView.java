
package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;
import minidraw.framework.*;
import java.awt.Dimension;

import java.awt.*;
import java.util.*;

/** BoardView: A MiniDraw DrawingView to display the board
as defined by an associated game instance. Note that
the BoardView draws Tiles with associated images that
are not part of the MiniDraw model and can thus not be
manipulated by any tools or the like.

Responsibilities:
1) Draw the board as specified by the game's layout of tiles.

It is NOT the responsibility of the BoardView to draw units
on the tiles.

Author: Henrik Bærbak Christensen 2006


*/
public class BoardView 
  extends StandardDrawingView {
  
  private Game game;
  
  Image overlay;

  public BoardView( DrawingEditor editor, Game game ) {
    super(editor, new Dimension( TarguiViewDefinition.TILE_IMAGE_SIZE * 7,
                                 TarguiViewDefinition.TILE_IMAGE_SIZE * 7 ) );
    ImageManager im = ImageManager.getSingleton();
    overlay = im.getImage( "overlay" );

    this.game = game;
  }

  public void drawBackground(Graphics g) {
    ImageManager im = ImageManager.getSingleton();

    g.setColor( TarguiViewDefinition.bgBlue );
    g.fillRect(0, 0, getBounds().width, getBounds().height);
    
    Image img;
    Iterator<? extends Tile> i = game.getBoardIterator();
    while ( i.hasNext() ) {
      Tile t = i.next();
      String imageName = 
          TarguiViewDefinition.mapTile2Name( t );
      img = im.getImage( imageName );
      Position p = t.getPosition();
      // Draw the tile image
      g.drawImage(img, 
                  TarguiViewDefinition.BOARD_OFFSET_X +
                  TarguiViewDefinition.TILE_IMAGE_SIZE * p.getColumn(),
                  TarguiViewDefinition.BOARD_OFFSET_Y +
                  TarguiViewDefinition.TILE_IMAGE_SIZE * p.getRow(),
                  null );
      // draw the owner marker
      PlayerColor c = t.getOwnerColor();
      Image marker = null;
      if ( c == PlayerColor.Red ) {
        marker = im.getImage( "red-marker" ); 
      } else if ( c == PlayerColor.Blue ) {
        marker = im.getImage( "blue-marker" ); 
      } else if ( c == PlayerColor.Green ) {
        marker = im.getImage( "green-marker" ); 
      } else if ( c == PlayerColor.Yellow ) {
        marker = im.getImage( "yellow-marker" ); 
      } 

      if ( marker != null ) {
        g.drawImage(marker,
                    TarguiViewDefinition.BOARD_OFFSET_X +
                    TarguiViewDefinition.TILE_IMAGE_SIZE * p.getColumn() +
                    TarguiViewDefinition.MARKER_DX,
                    TarguiViewDefinition.BOARD_OFFSET_Y +
                    TarguiViewDefinition.TILE_IMAGE_SIZE * p.getRow() +
                    TarguiViewDefinition.MARKER_DY,
                    null );
      }
    }
  }

  public void drawOverlay(Graphics g) {
    // Overlay the frame borders
    g.drawImage(overlay, 0, 0, null );
  }
  
  public Dimension getPreferredSize() {
    return new Dimension( overlay.getWidth(null),
                          overlay.getHeight(null) );
  }

  public Dimension getMinimumSize() {
    return getPreferredSize();
  }
  
}
