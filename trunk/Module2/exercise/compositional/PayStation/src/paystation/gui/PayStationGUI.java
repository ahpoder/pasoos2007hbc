package paystation.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
  public PayStationGUI() {
    super("PayStation GUI" );

    payStation = new PayStationImpl( new AlphaTownFactory() ); 

    // all the gui setup
    JFrame.setDefaultLookAndFeelDecorated(true);
    setLocation( 100, 20 );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
    Container cpane = getContentPane(); 

    cpane.setLayout( new BorderLayout() );

    cpane.add( createCoinInputPanel(), BorderLayout.EAST );
    cpane.add( createButtonPanel(), BorderLayout.SOUTH );

    cpane.add( createDisplayPanel(), BorderLayout.CENTER );

    setJMenuBar( createAllMenus() );

    pack();
    setVisible(true);
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
          payStation.pushCancelButton();
          updateDisplay();
        } } );

    b = new JButton("Buy");
    b.setBackground( new Color(0,200,0) );
    p.add( b );
    b.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Receipt r = payStation.pushBuyButton();
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

  /** Create all menus used by the GUI */
  private JMenuBar createAllMenus() {
    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menuBar = new JMenuBar();
    
    menu = new JMenu("Variant Selection");
    menu.getAccessibleContext().
      setAccessibleDescription("Select pay station variants here.");
    menuBar.add(menu);
    
    //a group of JMenuItems
    menuItem = makeTownMenuItem("Alphatown", new AlphaTownFactory() );
    menu.add(menuItem);
    menuItem = makeTownMenuItem("Betatown", new BetaTownFactory() );
    menu.add(menuItem);
    menuItem = makeTownMenuItem("Gammatown", new GammaTownFactory() );
    menu.add(menuItem);

    menuItem = new JMenuItem("Add logging" );
    menuItem.getAccessibleContext().
      setAccessibleDescription("Add coin logging behaviour (output in shell).");
    menu.add(menuItem);
    menuItem.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          PayStation logger = new LoggingPayStation(payStation);
          payStation = logger;
        }
      } );
        
    return menuBar;
  }

  /** Create menu item for a single town; link it with the factory
      that defines that town's pay station */
  private JMenuItem makeTownMenuItem( final String name,
                                      final PayStationFactory factory ) {
    JMenuItem menuItem;
    menuItem = new JMenuItem( name );
    menuItem.getAccessibleContext().
      setAccessibleDescription("Reconfig to basic "+name+" behaviour.");
    menuItem.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          payStation = new PayStationImpl( factory );
          updateDisplay();
        }
      } );
    return menuItem;
  }

}
