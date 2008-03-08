package dooralarm.domain;

/** SimpleAccess - A FakeIt implementation of the Access interface.

    KeyCode '1234' is the only legal code.

    Author: Henrik Bærbak Christensen
*/

import java.util.*;

public class CentralAccess implements Access {
  public CentralAccess()
  {
	hashtable.put("1234", new Object());
  }

  public boolean accept(String keycode) {
	  System.out.println("Opening SQL connection" );
	  System.out.println("Query the SQL database" );
	  Object entry = hashtable.get(keycode);
	  System.out.println("Closing connection" );
	  return entry != null;
  }
  
  private Hashtable hashtable = new Hashtable();
}
