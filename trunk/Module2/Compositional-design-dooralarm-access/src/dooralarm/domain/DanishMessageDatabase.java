package dooralarm.domain;

/** DanishMessageDatabase: Messages in Danish.

    Author: Henrik B�rbak Christensen
*/

public class DanishMessageDatabase implements MessageDatabase {
  public String getFrameTitle() { return "D�ralarm"; }
  public String getWelcomeMessage() { return "Indtast kode:"; }
  public String getDeniedMessage() { return "Afvist"; }
  public String getGrantedMessage() { return "Godkendt"; }
}
