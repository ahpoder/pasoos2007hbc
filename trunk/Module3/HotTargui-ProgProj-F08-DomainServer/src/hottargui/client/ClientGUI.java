package hottargui.client;

import hottargui.domain.*;
import hottargui.framework.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class ClientGUI extends JFrame {
  private StatusListener myListener;

  public ClientGUI() {
    super();
    try
	{
		myListener = new ClientUpdater();
	}
	catch (RemoteException re)
	{
		System.out.println(re);
	}
  }

  public StatusListener getStatusListener() {
    return myListener;
  }
}
