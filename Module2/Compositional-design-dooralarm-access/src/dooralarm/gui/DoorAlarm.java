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
    if (args.length < 3)
	{
		System.out.println("Invalid argument count");
		return;
	}
	System.out.println( "Country code: "+args[0] );
    
	if (args[0].equals("US")) {
		if (args[1].equals("SIM")) {
			if (args[2].equals("NOLOG")) {
				new DoorAlarm(new UsSimpleNoLoggingDoorAlarmFactory());
			}
			else {
				// Not implemented
			}
		}
		else if (args[1].equals("LOCAL")) {
			// Not implemented
		}
		else // CENTRAL
		{
			if (args[2].equals("NOLOG")) {
				// Not implemented
			}
			else {
				new DoorAlarm(new UsCentralLoggingDoorAlarmFactory());
			}
		}
	}
	else {
		if (args[1].equals("SIM")) {
			if (args[2].equals("NOLOG")) {
				new DoorAlarm(new DkSimpleNoLoggingDoorAlarmFactory());
			}
			else {
				// Not implemented
			}
		}
		else if (args[1].equals("LOCAL")) {
			if (args[2].equals("NOLOG")) {
				new DoorAlarm(new DkLocalNoLoggingDoorAlarmFactory());
			}
			else {
				// Not implemented
			}
		}
		else // CENTRAL
		{
			// Not implemented
		}
	}
  }

  /** The entered code so far */
  private String codeSoFar;
  
  /** The info field displaying accept or reject of access */
  private InfoField info;

  /** The access system that determines whether a key is ok or not */
  private Access access;

  public DoorAlarm(DoorAlarmFactory factory) {
//    super( language.getFrameTitle() );
    this.language = factory.createMessageDatabase();
    this.access = factory.createAccessControl();
	this.factory = factory;
	this.setTitle(language.getFrameTitle());

    // initialize the global listener on the keyboard panel.
    keyCodeListener = new KeyCodeListener();

    JFrame.setDefaultLookAndFeelDecorated(true);
    setLocation( 100, 20 );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Box panel = Box.createVerticalBox();
 
    info = new InfoField( language );
    panel.add( info );

    panel.add( Box.createVerticalGlue() );

    // initialize the global listener on the keyboard panel.
    keyCodeListener = new KeyCodeListener();

	JPanel keyboard = createKeyboardPanel();

    panel.add( keyboard );
 
    TitledBorder title;
    title = 
      BorderFactory.createTitledBorder( BorderFactory.
                                        createEtchedBorder(EtchedBorder.RAISED),
                                        language.getWelcomeMessage());
    title.setTitleJustification(TitledBorder.LEFT);

    panel.setBorder( title );
    getContentPane().add(panel);


    pack();
    setVisible(true);

    clearCode();
  }
  
  DoorAlarmFactory factory;

  public JPanel createKeyboardPanel()
  {
	JPanel numericalPanel = new JPanel();
    numericalPanel.setLayout( new GridLayout(4, 3, 5, 5) );
    // insert the buttons
	
    String[] strs = factory.createKeyboardPanelSequence();
	for (int i = 0; i < strs.length; ++i)
	{
		numericalPanel.add( makeButton( strs[i] ));
	}
    return numericalPanel;
  }

  private JButton makeButton( String label) {
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
