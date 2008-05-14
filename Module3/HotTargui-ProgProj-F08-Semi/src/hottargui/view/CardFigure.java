package hottargui.view;

import hottargui.framework.*;

import minidraw.standard.*;

import java.awt.*;

/** CardFigure.

    Responsibility: A HotTargui figure representing the tribe cards.

    Author: Henrik Bærbak Christensen

*/

public class CardFigure extends ImageFigure {

  /** Create a card figure at the given position.
   * @param origin the position of the die figure.
   */
  public CardFigure(Point origin) {
    super();
    ImageManager im = ImageManager.getSingleton();
    Image fImage = im.getImage("none-card");
    set( fImage, origin );
  }
  
  /** change the appearance of the card.
   * @param color the new color to show.
   */
  public void changeToValue(PlayerColor color) { 
    resetgraphics(color); 
  }

  private void resetgraphics(PlayerColor color) {
    ImageManager im = ImageManager.getSingleton();
    Image img;
    switch( color ) {
    case Red: img = im.getImage("red-card"); break;
    case Blue: img = im.getImage("blue-card"); break;
    case Green: img = im.getImage("green-card"); break;
    case Yellow: img = im.getImage("yellow-card"); break;
    default: img = im.getImage("none-card"); break;
    }
    Point origin = this.displayBox().getLocation();
    invalidate();
    set( img, origin );
    changed();
  }
}
