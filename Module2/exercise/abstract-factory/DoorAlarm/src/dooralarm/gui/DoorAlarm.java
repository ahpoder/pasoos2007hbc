package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/** DoorAlarm Graphical User Interface.

    Responsibilities: 
    1) Provide visual interface according to country code
    2) Collaborate with the domain door locking mechanism

    Henrik Bærbak Christensen
*/

public class DoorAlarm extends JFrame {

  MessageDatabase language;

  public static void main(String[] args) {
    System.out.println( "Country code: "+args[0] );
    
    new DoorAlarm( new DanishMessageDatabase(), new SimpleAccess() );
  }

  /** The entered code so far */
  private String codeSoFar;
  
  /** The info field displaying accept or reject of access */
  private InfoField info;

  /** The access system that determines whether a key is ok or not */
  private Access access;

  public DoorAlarm(MessageDatabase language, Access access) {
    super( language.getFrameTitle() );
    this.language = language;
    this.access = access;

    // initialize the global listener on the keyboard panel.
    keyCodeListener = new KeyCodeListener();

    JFrame.setDefaultLookAndFeelDecorated(true);
    setLocation( 100, 20 );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Box panel = Box.createVerticalBox();
 
    info = new InfoField( language );
    panel.add( info );

    panel.add( Box.createVerticalGlue() );

    JPanel keyboard = createKeyBoardPanel();
    panel.add( keyboard );
 
    
    TitledBorder title;
    title = 
      BorderFactory.createTitledBorder( BorderFactory.
                                        createEtchedBorder(EtchedBorder.RAISED),
                                        language.getWelcomeMessage());
    title.setTitleJustification(TitledBorder.LEFT);

    panel.setBorder( title );
    getContentPane().add(panel);

    // initialize the global listener on the keyboard panel.
    keyCodeListener = new KeyCodeListener();

    pack();
    setVisible(true);

    clearCode();
  }
   
  private JPanel createKeyBoardPanel() {
    JPanel numericalPanel = new JPanel();
    numericalPanel.setLayout( new GridLayout(4, 3, 5, 5) );
    // insert the buttons
	
    numericalPanel.add( makeButton( "1" ));
    numericalPanel.add( makeButton( "2" ));
    numericalPanel.add( makeButton( "3" ));
	
    numericalPanel.add( makeButton( "4" ));
    numericalPanel.add( makeButton( "5" ));
    numericalPanel.add( makeButton( "6" ));
	
    numericalPanel.add( makeButton( "7" ));
    numericalPanel.add( makeButton( "8" ));
    numericalPanel.add( makeButton( "9" ));
 
    numericalPanel.add( makeButton( "*" ));
    numericalPanel.add( makeButton( "0" ));
    numericalPanel.add( makeButton( "#" ));
    return numericalPanel;
  }
  
  private JButton makeButton( String label ) {
    JButton b = new JButton( label );
    b.addActionListener( keyCodeListener );
    b.setActionCommand( label );
    return b;
  }

  private class KeyCodeListener implements ActionListener {
    public void actionPerformed( ActionEvent e ) {
      String arg = e.getActionCommand();
      addToCode( arg.charAt(0) );
    }
  };
  private KeyCodeListener keyCodeListener;
    
  
  private void clearCode() {
    codeSoFar = new String();
    info.denied();
  }

  private void addToCode(char c) {
    codeSoFar = codeSoFar + c;
    if ( codeSoFar.length() > 4 ) {
      clearCode();
    } else {
      info.enterCode( codeSoFar.length() );
      if ( access.accept(codeSoFar) ) {
        info.granted();
      }
    }
  }
}
