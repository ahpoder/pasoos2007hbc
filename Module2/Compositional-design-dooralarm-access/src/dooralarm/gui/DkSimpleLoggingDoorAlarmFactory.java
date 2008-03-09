package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class DkSimpleLoggingDoorAlarmFactory implements DoorAlarmFactory {
  public String[] createKeyboardPanelSequence()
  {
	return new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#" };
  }
  public MessageDatabase createMessageDatabase()
  {
    return new DanishMessageDatabase();
  }
  public Access createAccessControl()
  {
    return new AccessLoggingDecorator(new SimpleAccess(), new FileLogging());
  }
}
