package dooralarm.domain;

/** SimpleAccess - A FakeIt implementation of the Access interface.

    KeyCode '1234' is the only legal code.

    Author: Henrik B�rbak Christensen
*/

public class SimpleAccess implements Access {
  public boolean accept(String keycode) {
    if ( keycode.equals("1234") ) return true;
    return false;
  }
}
