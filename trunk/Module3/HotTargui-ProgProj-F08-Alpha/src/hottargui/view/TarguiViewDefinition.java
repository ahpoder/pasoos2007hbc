package hottargui.view;

import hottargui.framework.*;

import java.awt.*;


/** TarguiViewDefinition.
    General definitions of targui specific view things.
*/

public class TarguiViewDefinition {

  public final static Color   bgBlue = new Color(0,123,198);
  public final static Color   shadow = new Color(70,88,101);

  public final static int TRIBE_CARD_OFFSET_X         = 681;
  public final static int TRIBE_CARD_OFFSET_Y         = 7;

  public final static int BOARD_OFFSET_X              = 6;
  public final static int BOARD_OFFSET_Y              = 6;
  public final static int TILE_IMAGE_SIZE             = 96;
  public final static int TILE_HALFSIZE               = TILE_IMAGE_SIZE / 2;
  public final static int MARKER_DX             = 4;
  public final static int MARKER_DY             = 69;
  public final static int MARKER_DIAMETER       = 20;
  
  public final static int TREASURY_X            = 792;
  public final static int TREASURY_Y            = 676; 
  public final static int TREASURY_BOX_HEIGHT   = 140;

  /** given a tile, t, return the name of the graphics file name that
      contains the graphical image to represent t's type */
  public static String mapTile2Name(Tile t) {
    TileType type = t.getType();
    String imageName = "error";
    if ( type == TileType.Settlement ) {
      if ( t.getOwnerColor() == PlayerColor.Red ) {
        imageName = "red-settlement";
      } else if ( t.getOwnerColor() == PlayerColor.Blue ) {
        imageName = "blue-settlement";
      } else if ( t.getOwnerColor() == PlayerColor.Yellow ) {
        imageName = "yellow-settlement";
      } else if ( t.getOwnerColor() == PlayerColor.Green ) {
        imageName = "green-settlement";
      } 
    } else {
      switch ( type ) {
      case Settlement: imageName = "red-settlement"; break;
      case Oasis: imageName = "oasis"; break;
      case Reg: imageName = "reg"; break;
      case Feshfesh: imageName = "feshfesh"; break;
      case Erg: imageName = "erg"; break;
      case Mountain: imageName = "mountain"; break;
      case Saltlake: imageName = "saltlake"; break;
      case Saltmine: imageName = "saltmine"; break;
      }
    }
    return imageName;
  }

  /** given (x,y) on the playing area return the tile (r,c) */
  public static Position getTilePositionFromXY( int x, int y ) {
    int r, c;
    c = (x - BOARD_OFFSET_X) / TILE_IMAGE_SIZE;
    r = (y - BOARD_OFFSET_Y) / TILE_IMAGE_SIZE;
    return new Position(r,c);
  }

  /** given a position p determine whether it is within
      board limits: (-1,0) is not; (2,3) is.
      @return true iff the position is legal false otherwise
  */
  public static boolean legalPosition(Position p) {
    // TODO: Remove magic constants :(
    if ( p.getRow() < 0 
         || 
         p.getRow() > 6 
         ||
         p.getColumn() < 0 
         || 
         p.getColumn() > 6 ) { 
      return false;
    }
    return true;
  }

}
