package paystation.monitor;

import paystation.domain.*;
import paystation.gui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** A crude graphical monitor application.

    Author: Henrik Bærbak Christense 2007
*/

public class StatusFrame extends JFrame {
  private JLabel vLabel, eLabel;
  private StatusListener myListener;

  public StatusFrame(int x, int y) {
    super("Supervisor");
    setLocation(x,y);
    vLabel = new JLabel("  Reported Vacant xxx  ");
    eLabel = new JLabel("  Reported Earning xxx " );
    Container pane = getContentPane();
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
    pane.add(vLabel);
    pane.add(eLabel);

    try
	{
		myListener = new LabelUpdater(vLabel, eLabel);
	}
	catch (RemoteException re)
	{
		System.out.println(re);
	}

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    pack();
    setVisible(true);
  }

  public StatusListener getStatusListener() {
    return myListener;
  }
}
