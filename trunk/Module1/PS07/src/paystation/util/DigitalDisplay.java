package paystation.util;

/**
   Seven-Segment "look-and-feel" display. Original code by Sriram Chitturi 
   A class to display digital digits and characters on a rectangular area

   Rewritten to Java by Henrik Bærbak Christensen 2006.
*/

import java.awt.*;

class DigitalDisplay
{
  // public enum ColonType { Circular, Rectangular };

  private float linewidth = 20.0F;
  private Point[] Points; // end point coordinates of lines in the digital display

  // for each digit the display bits are set into an int
  // 'A' and 'P' are included for AM and PM display in the clock
  private int[] displayNum = new int[] {63,12,118,94,77,91,123,14,127,95,
                                            111, // to display 'A'
                                            103}; // to display 'P'

  // Rectangles in which colons are displayed
  private Rectangle colonRect1, colonRect2;
	

  private void DrawLine(Graphics g, boolean dim, Point a, Point b) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke( new BasicStroke(linewidth, 
                                  BasicStroke.CAP_ROUND, 
                                  BasicStroke.JOIN_BEVEL));

    if ( dim ) {
      g2.setColor( new Color(60,0,0) );
    } else {
      g2.setColor( Color.RED);
    }

    g2.drawLine(a.x, a.y, b.x, b.y);
  }


  // This function is called by the paint method to display the numbers
  // A set of bits in the 'displayNum' variable define which of the
  // display legs to display
  // Based on this the ones with a '1' are in bright color and the rest
  // with '0's are in a dull color giving the effect of a digital clock
  public void Draw(int num,  // number to display
                   Graphics g) // graphics object for drawing
  {
    int check; // used to check if a leg of digit should be bright or dull

    for (int i=0; i<7; i++)
      {
        check = (int)Math.pow(2, i);
        if ((check & displayNum[num])==0)
          DrawLine(g, true, Points[i*2], Points[i*2+1]);
        else
          DrawLine(g, false, Points[i*2], Points[i*2+1]);
      }
  }

  // Draws the complete rectangle in dim shade to give the digital effect :-)
  private void Draw(Graphics g)
  {
    // althought pens are static, linewidths are specific to each instance
    for (int i=0; i<7; i++)
      DrawLine(g, true, Points[i*2], Points[i*2+1]);
  }

  // Constructor takes a rectangle and prepares the end points
  // of the lines to be drawn for the clock
  public DigitalDisplay(Rectangle rect)
  {
    Points = new Point[14]; // there are 7 lines in a display
    for (int i=0; i<14; i++)
      Points[i] = new Point(0,0);
    CalculateAllParameters(rect);
  }

  public void CalculateAllParameters(Rectangle rect)
  {
    linewidth = (int)(rect.width/5); 
    if (linewidth < 2) linewidth = 2;
    if (linewidth > 20) linewidth = 20;

    CalculateLineEnds(rect);
  }

  // Function calculates end points of lines to display
  // The draw function will draw lines using this data
  private void CalculateLineEnds(Rectangle rect)
  {
    // 0,1,2,9,10,11,12 points share the same left edge X coordinate
    Points[0].x = Points[1].x = Points[2].x = Points[9].x = 
      Points[10].x = Points[11].x = Points[12].x = rect.x;
 
    // points 3,4,5,6,7,8,13 the right edge X coordinate
    Points[3].x = Points[4].x = Points[5].x = Points[6].x =
      Points[7].x = Points[8].x = Points[13].x= rect.x+rect.width-(int)linewidth;

    // Points 1,2,3,4 are the top most points
    Points[1].y = Points[2].y = Points[3].y = Points[4].y = (int)(rect.y);

    // Points 0,11,12,13,5,6 are the middle points
    Points[0].y = Points[11].y = Points[12].y = Points[13].y =
      Points[5].y = Points[6].y = 
      rect.y + (int)((rect.height-linewidth)/2.0);
    // points 7,8,9,10 are on the bottom edge
    Points[7].y = Points[8].y = Points[9].y = Points[10].y 
      = rect.y + (int)(rect.height-linewidth);

    // now adjust the coordinates that were computed, to get the digital look
    AdjustCoordinates();
  }
	
  // This function is necessary to give the lines a digital clock look
  // Push the coordinates a little away so that they look apart
  private void AdjustCoordinates()
  {
    Point swap; // required in case points have to be swapped
    for (int i=0; i<7; i++)
      {
        // Always draw from left to right and top to bottom
        // Adjust the end points accordingly
        if (Points[i*2].x > Points[(i*2)+1].x || Points[i*2].y > Points[(i*2)+1].y)
          {
            swap = Points[i*2]; Points[i*2]= Points[(i*2)+1]; Points[(i*2)+1]=swap;
          }

        // for horizontal lines adjust the X coord
        if (Points[i*2].x != Points[(i*2)+1].x)
          {
            Points[i*2].x += (int)(linewidth/1.6);
            Points[(i*2)+1].x -= (int)(linewidth/1.6);
          }
        // for vertical lines adjust the y coord
        if (Points[i*2].y != Points[(i*2)+1].y)
          {
            Points[i*2].y += (int)(linewidth/1.6);
            Points[(i*2)+1].y -= (int)(linewidth/1.6);
          }
      }
  }

}
