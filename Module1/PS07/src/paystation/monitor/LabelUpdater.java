package paystation.monitor;

import paystation.domain.*;
import paystation.gui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** A StatusListener that may update the status frame's lables.

    Author: Henrik Bærbak Christense 2007
*/

public class LabelUpdater extends UnicastRemoteObject implements StatusListener {
  private JLabel vLabel, eLabel;
  public LabelUpdater(JLabel vacantLabel, JLabel earnedLabel) throws RemoteException {
    super();
    vLabel = vacantLabel;
    eLabel = earnedLabel;
  }

  public void update(StatusEvent e) throws RemoteException {
    eLabel.setText("  Reported Earning "+e.earned);
    vLabel.setText("  Reported Vacant: "+e.vacant);
  }
}
