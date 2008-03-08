package dooralarm.domain;

/** Access - the component that determines whether access is granted by the door.

    Author: Henrik Bærbak Christensen
*/

public interface Access {
  /** returns true if the keycode matches a valid one; false otherwise */
  public boolean accept(String keycode);
}
