package paystation.util;

/**
   Four 7-segment digit panel.

   Written by : Henrik Bærbak Christensen (c) Imhotep 2006
   Based on code by Sriram Chitturi 
*/

import java.awt.*;
import javax.swing.*;

public class FourDigitDisplay extends JComponent {
  
  private static int NO_DIGIT = 4;

  private DigitalDisplay[] DigitDisplay = null; 

  private int[] intArray = new int[NO_DIGIT];
  
  private Dimension minSize = new Dimension(200,140);

  public FourDigitDisplay()
  {
    setBackground(Color.black);
    setPreferredSize(minSize);
    setSize(minSize);

    setDisplay(0, 0, 0, 0 );
  }
  
  public void setDisplay( int number ) {
    int thousand = number / 1000;
    int tmp = number % 1000;
    int hundred = tmp / 100;
    tmp = number % 100;
    int ten = tmp / 10;
    tmp = number % 10;
    int one = tmp;
    setDisplay(thousand,hundred,ten,one);
  }
  public void setDisplay( int thousand, int hundred, int ten, int one ) {
    intArray[0] = thousand;
    intArray[1] = hundred;
    intArray[2] = ten;
    intArray[3] = one;
    this.repaint();
  }
  
  protected void paintComponent(Graphics g)
  {
    PreparePanels();
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    DisplayTime(g);
  }

  private void DisplayTime(Graphics g)
  {
    DigitDisplay[0].Draw(intArray[0], g);
    DigitDisplay[1].Draw(intArray[1], g);
    DigitDisplay[2].Draw(intArray[2], g);
    DigitDisplay[3].Draw(intArray[3], g);
  }

  private void PreparePanels()
  {
    // from the above assumptions for height and width
    // the height should be 2.4 units and width 8.8 units :-)
    // check height and width whichever is dominant and adjust the other
    // and set up margins
    
    Dimension dim = this.getSize();
    Rectangle rect = new Rectangle(dim);

    // widths, spacings and margins
    // height of colon display is same as a digit
    int DigitWidth, DigitHeight, ColonWidth, Spacing;
    float HMargin=0, // left and right margin
      VMargin=0; // top and bottom margin
    
    // Calculate a digit width (which is our unit) from the above metrics
    // and settle for the least value
    int WidthUnit = (int)(rect.width/4.5F);
    int HeightUnit = (int)(rect.height/2.3F);
    DigitWidth = (WidthUnit < HeightUnit) ? WidthUnit : HeightUnit;
    
    DigitHeight = 2 * DigitWidth;  // height is twice of width
    ColonWidth = DigitWidth/2;  // colon width is half of a digit
    Spacing = DigitWidth/10;
    if (Spacing < 1) Spacing = 1; // atleast a spacing of 1 is required
    HMargin = (rect.width - (4.0F * DigitWidth))/2;
    VMargin = (rect.height - DigitHeight)/2;
    
    // This is the basic rectangle, offset it as required
    Rectangle basicRect = 
      new Rectangle(0, 0, (int)DigitWidth, (int)DigitHeight);
    int XOffset, YOffset;
    Rectangle calcRect;  // calculated rectangle for a panel
    // Y offset is same for all elements, expcept 1/10 second and AM/PM display
    YOffset = (int)(VMargin);
    
    // create digit panels.  NO_DIGIT digits
    if (DigitDisplay == null)
      DigitDisplay = new DigitalDisplay[NO_DIGIT];
    for (int i=0; i<NO_DIGIT; i++)
      {
        calcRect = basicRect;
        XOffset = (int)( /*HMargin/2*/ + (Spacing * (i+2+(i/2))) 
                         + (i * DigitWidth) /* +  ((i/2) * ColonWidth) */);
        calcRect.setLocation(XOffset, YOffset);
        if (DigitDisplay[i] == null)
          DigitDisplay[i] = new DigitalDisplay(calcRect);
        else
          DigitDisplay[i].CalculateAllParameters(calcRect);
      }
  }
}
