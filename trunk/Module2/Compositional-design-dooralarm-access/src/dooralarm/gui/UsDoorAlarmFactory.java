package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class UsDoorAlarmFactory extends DoorAlarmFactoryBase {
  public String[] createKeyboardPanelSequence()
  {
	return new String[] { "7", "8", "9", "4", "5", "6", "1", "2", "3", "*", "0", "#" };
  }

  public JPanel createKeyboardPanel(ActionListener keyCodeListener)
  {
    JPanel numericalPanel = new JPanel();
    numericalPanel.setLayout( new GridLayout(4, 3, 5, 5) );
    // insert the buttons
	
    numericalPanel.add( makeButton( "7", keyCodeListener ));
    numericalPanel.add( makeButton( "8", keyCodeListener ));
    numericalPanel.add( makeButton( "9", keyCodeListener ));
	
    numericalPanel.add( makeButton( "4", keyCodeListener ));
    numericalPanel.add( makeButton( "5", keyCodeListener ));
    numericalPanel.add( makeButton( "6", keyCodeListener ));
	
    numericalPanel.add( makeButton( "1", keyCodeListener ));
    numericalPanel.add( makeButton( "2", keyCodeListener ));
    numericalPanel.add( makeButton( "3", keyCodeListener ));
 
    numericalPanel.add( makeButton( "*", keyCodeListener ));
    numericalPanel.add( makeButton( "0", keyCodeListener ));
    numericalPanel.add( makeButton( "#", keyCodeListener ));
    return numericalPanel;
  }

  public MessageDatabase createMessageDatabase()
  {
    return new UsMessageDatabase();
  }
}
