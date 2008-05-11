package hottargui.view;

import minidraw.standard.*;

import java.awt.*;

/** UnitFigure.

    Responsibility: A HotTargui figure for the two types of unit figures.

    Author: Henrik Bærbak Christensen

*/

public class UnitFigure extends ImageFigure {

  public enum UnitType { ONE, FIVE };
  private UnitType type;

  /** Create a unit figure at the given position */
  public UnitFigure(UnitType type, Point origin) {
    super();

    this.type = type;

    ImageManager im = ImageManager.getSingleton();
    if ( type == UnitType.FIVE ) {
      fImage = im.getImage("camel5");
    } else {
      fImage = im.getImage("camel1");
    }
    set( fImage, origin );
  }
  
  public UnitType getType() {
    return type;
  }
}
