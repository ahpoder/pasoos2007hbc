package dooralarm.domain;

/** SimpleAccess - A FakeIt implementation of the Access interface.

    KeyCode '1234' is the only legal code.

    Author: Henrik Bærbak Christensen
*/

import java.io.*;

public class AccessLoggingDecorator implements Access {
  public AccessLoggingDecorator(Access decoratee)
  {
    this.decoratee = decoratee;
  }
  public boolean accept(String keycode) {
	boolean result = decoratee.accept(keycode);
	if (result)
	{
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter("log.txt", true));
	        out.write("Access granted: " + keycode + "\r\n");
	        out.close();
	    } catch (IOException e) { }
	}
	return result;
  }
  private Access decoratee;
}
