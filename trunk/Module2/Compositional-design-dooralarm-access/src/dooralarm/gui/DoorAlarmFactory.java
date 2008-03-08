package dooralarm.gui;

import dooralarm.domain.*;

public interface DoorAlarmFactory {
  public String[] createKeyboardPanelSequence();
  public MessageDatabase createMessageDatabase();
  public Access createAccessControl();
}
