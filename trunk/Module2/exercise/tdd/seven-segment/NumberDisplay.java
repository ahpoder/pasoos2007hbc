/** The interface to a seven segment LED that displays numbers 0-9

    Responsibility: To display numbers 0-9 on a SevenSegment object.

    Author: Henrik Bærbak Christensen 2006
*/

public interface NumberDisplay {
  /** display a number on a seven segment.
      @param number the number to display. 
      PreCondition: number should
      be in the range 0 to 9. 
  */
  void display(int number); 
}
