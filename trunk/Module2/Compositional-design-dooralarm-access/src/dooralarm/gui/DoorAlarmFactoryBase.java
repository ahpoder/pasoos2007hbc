package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public abstract class DoorAlarmFactoryBase implements DoorAlarmFactory {
  protected JButton makeButton( String label, ActionListener keyCodeListener) {
    JButton b = new JButton( label );
    b.addActionListener( keyCodeListener );
    b.setActionCommand( label );
    return b;
  }
}
