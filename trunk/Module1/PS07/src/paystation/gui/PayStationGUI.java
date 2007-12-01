package paystation.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

import paystation.domain.*;
import paystation.util.*;

/** A crude graphical user interface for testing the pay station.

    Author: Henrik Bærbak Christense 2006
*/
public class PayStationGUI extends JFrame {

  public static void main(String[] args) {
    new PayStationGUI();
  }

  /** The "digital display" where readings are shown */
  FourDigitDisplay display;
  /** The domain pay station that the gui interacts with */
  PayStation payStation;

  /** Create the GUI */
  public PayStationGUI(int x, int y) {
    super("PayStation GUI" );

	try
	{
		payStation = new PayStationImpl(); 
	}
	catch (RemoteException re)
	{
		System.out.println(re);
	}

    // all the gui setup
    JFrame.setDefaultLookAndFeelDecorated(true);
    setLocation( x, y );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
    Container cpane = getContentPane(); 

    cpane.setLayout( new BorderLayout() );

    cpane.add( createCoinInputPanel(), BorderLayout.EAST );
    cpane.add( createButtonPanel(), BorderLayout.SOUTH );

    cpane.add( createDisplayPanel(), BorderLayout.CENTER );

    pack();
    setVisible(true);
  }

  public PayStationGUI() {
    this(100,20);
  }

  public PayStation getPayStation() {
    return payStation;
  }

  /** Update the digital display with whatever the
      pay station domain shows */
  private void updateDisplay() {
    display.setDisplay( payStation.readDisplay() );
  }
  
  /** Create the coin input panel */
  private JComponent createCoinInputPanel() {
    Box p = new Box( BoxLayout.Y_AXIS );
    p.add( defineButton( " 5 c", "5" ));
    p.add( defineButton( "10 c", "10" ));
    p.add( defineButton( "25 c", "25" ));
    return p;
  }
  
  /** The button action listener that reacts on clicking the
      coin buttons */
  private ActionListener buttonActionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        int coin = Integer.parseInt(s);
        try {
          payStation.addPayment( coin );
        } catch (IllegalCoinException exc) {
          // illegal coins just do nothing.
        }
        updateDisplay();
      }
    };
  
  /** Define a button's text and action command */
  private JButton defineButton(String text, String actioncommand) {
    JButton b;
    b = new JButton( text );
    b.setActionCommand( actioncommand );
    b.addActionListener( buttonActionListener );
    return b;
  }

  /** Create the panel of buttons */
  private JComponent createButtonPanel() {
    Box p = new Box( BoxLayout.X_AXIS );
    JButton b;
    b = new JButton("Cancel");
    b.setBackground( new Color(200,0,0) );
    p.add( b );
    b.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          payStation.cancel();
          updateDisplay();
        } } );

    b = new JButton("Buy");
    b.setBackground( new Color(0,200,0) );
    p.add( b );
    b.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Receipt r = payStation.buy();
          updateDisplay();
          r.show();
        } } );

    return p;
  }

  /** Create the panel for the display */
  private JComponent createDisplayPanel() {
    display = new FourDigitDisplay();
    return display;
  }
}
