package dooralarm.domain;

/** DanishMessageDatabase: Messages in Danish.

    Author: Henrik Bærbak Christensen
*/

public class UsMessageDatabase implements MessageDatabase {
  public String getFrameTitle() { return "Door alarm"; }
  public String getWelcomeMessage() { return "Enter code:"; }
  public String getDeniedMessage() { return "Rejected"; }
  public String getGrantedMessage() { return "Authorised"; }
}
