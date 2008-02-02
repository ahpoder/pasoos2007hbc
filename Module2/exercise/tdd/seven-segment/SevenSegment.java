/** Java interface for seven segment LED display

    Responsibility: To enable turning on and off
    individual segments.

    Author: Henrik Bærbak Christensen 2006
*/

public interface SevenSegment {
  /** turn a LED on or off.
      @param led the number of the LED. Range is 0 to 6. The LEDs are
      numbered top to bottom, left to right. That is, the top,
      horizontal, LED is 0, the top left LED is 1, etc.
      @param on if true the LED is turned on otherwise it is turned
      off.
  */
  void setLED(int led, boolean on);
}
