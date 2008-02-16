package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import java.awt.event.*;

public interface DoorAlarmFactory {
  public String[] createKeyboardPanelSequence();
  public JPanel createKeyboardPanel(ActionListener keyCodeListener);
  public MessageDatabase createMessageDatabase();
}
