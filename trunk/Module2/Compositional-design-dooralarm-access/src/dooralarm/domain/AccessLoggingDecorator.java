package dooralarm.domain;

/** SimpleAccess - A FakeIt implementation of the Access interface.

    KeyCode '1234' is the only legal code.

    Author: Henrik Bærbak Christensen
*/

public class AccessLoggingDecorator implements Access {
  public AccessLoggingDecorator(Access decoratee, Logging log)
  {
    this.decoratee = decoratee;
	this.log = log;
  }
  public boolean accept(String keycode) {
	boolean result = decoratee.accept(keycode);
	if (result)
	{
	  log.log(keycode);
	}
	return result;
  }
  private Access decoratee;
  private Logging log;
}
