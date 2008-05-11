package hottargui.view;

import minidraw.framework.*;

/** Messenger to make a text appear for a short while.

    Responsibility: To make a message appear and disappear again.

    Author: Henrik Bærbak Christensen.
*/

public class Messenger {
  /** The Drawing this messenger is associated with. */
  Drawing model;
  /** The view this messenger is associated with */
  DrawingView view;

  /** the message figure used to display text */
  MessageFigure         mfig;
  /** delay before message is removed */
  StopNSecs             s5s;
  
  public Messenger(Drawing model) {
    this.model = model;
    mfig = new MessageFigure();
    mfig.setText("Messenger");
    mfig.moveBy(100,200);

    s5s = new StopNSecs();
    s5s.sleepInMilli = 5000;
  }

  public void setText(String text) {
    mfig.setText(text);
//    new Thread(s5s).start();
		
    model.lock();
    model.add(mfig);
    model.unlock();
    model.requestUpdate();
  }

  void removeText() {
    model.lock();
    model.remove(mfig);
    model.unlock();
    model.requestUpdate();
  }

  class StopNSecs implements Runnable {
    int sleepInMilli;
    
    public void run() {
      try {
        Thread.sleep(sleepInMilli);
      } catch( InterruptedException e) {} 
      removeText();
    }
  }
}

