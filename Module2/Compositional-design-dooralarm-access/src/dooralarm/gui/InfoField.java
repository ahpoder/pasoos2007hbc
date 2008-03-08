package dooralarm.gui;

import dooralarm.domain.*;

import javax.swing.*;
import java.awt.*;

/** InfoField tells the user whether door access is granted or denied.

    Henrik Bærbak Christensen
*/

public class InfoField extends JTextField {
  MessageDatabase language;
  public InfoField(MessageDatabase l) {
    super(18);
    language = l;
    setEditable(false);
    setFont( new Font( "Helvetica", Font.BOLD, 18 ) ); 
    denied();
  }

  /** set the info field in denied mode */
  public void denied() {
    setText( language.getDeniedMessage() );
    setBackground(Color.RED);
  }    

  /** set the info field in granted mode */
  public void granted() {
    setText( language.getGrantedMessage() );
    setBackground(Color.GREEN);
  }   
  
  public void enterCode( int noDigits ) {
    String v = "";
    for ( int i = 0; i < noDigits; i++ ) { v += "*"; }
    setText( v );
    setBackground(Color.YELLOW);
  }
}
