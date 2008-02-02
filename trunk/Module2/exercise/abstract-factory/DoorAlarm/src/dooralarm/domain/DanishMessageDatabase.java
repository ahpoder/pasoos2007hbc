package dooralarm.domain;

/** DanishMessageDatabase: Messages in Danish.

    Author: Henrik Bærbak Christensen
*/

public class DanishMessageDatabase implements MessageDatabase {
  public String getFrameTitle() { return "Døralarm"; }
  public String getWelcomeMessage() { return "Indtast kode:"; }
  public String getDeniedMessage() { return "Afvist"; }
  public String getGrantedMessage() { return "Godkendt"; }
}
