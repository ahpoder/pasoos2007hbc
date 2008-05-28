package hottargui.view;

import minidraw.standard.*;

import java.awt.*;

/** DieFigure.

    Responsibility: A HotTargui figure representing the die.

    Author: Henrik Bærbak Christensen

*/

public class DieFigure extends ImageFigure {

  /** Create a die figure at the given position.
   * @param origin the position of the die figure.
   */
  public DieFigure(Point origin) {
    super();

    ImageManager im = ImageManager.getSingleton();
    Image fImage = im.getImage("die1");
    set( fImage, origin );
  }
  
  /** change the face of the die.
   * @param newValue the value to display on the die. Legal values are
   * 0, 1, 2, .., 6. Value 0 shows a "not used" die face.
   */
  public void changeToValue(int newValue) { 
    resetgraphics(newValue); 
  }

  private void resetgraphics(int newValue) {
    ImageManager im = ImageManager.getSingleton();
    Image fImage = im.getImage("die"+newValue);
    Point origin = this.displayBox().getLocation();
    invalidate();
    set( fImage, origin );
    changed();
  }
    
}
