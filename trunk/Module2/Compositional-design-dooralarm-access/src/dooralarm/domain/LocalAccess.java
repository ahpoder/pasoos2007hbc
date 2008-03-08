package dooralarm.domain;

/** SimpleAccess - A FakeIt implementation of the Access interface.

    KeyCode '1234' is the only legal code.

    Author: Henrik Bærbak Christensen
*/

public class LocalAccess implements Access {
  public boolean accept(String keycode) {
    System.out.println("Using Local database");
	if ( keycode.equals("1234") ) return true;
    return false;
  }
}
