package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class UsCentralNoLoggingDoorAlarmFactory implements DoorAlarmFactory {
  public String[] createKeyboardPanelSequence()
  {
	return new String[] { "7", "8", "9", "4", "5", "6", "1", "2", "3", "*", "0", "#" };
  }
  public MessageDatabase createMessageDatabase()
  {
    return new UsMessageDatabase();
  }
  public Access createAccessControl()
  {
    return new CentralAccess();
  }
}
