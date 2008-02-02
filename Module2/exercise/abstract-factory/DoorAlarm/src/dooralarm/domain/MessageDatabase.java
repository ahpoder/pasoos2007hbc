package dooralarm.domain;

/** MessageDatabase is a database of messages in a particular language.

    Author: Henrik B�rbak Christensen
*/

public interface MessageDatabase {
  public String getFrameTitle();
  public String getWelcomeMessage();
  public String getDeniedMessage();
  public String getGrantedMessage();
}
