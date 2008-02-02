/** Two minute mean wind.
    A simple data object containing mean wind data.

    Author: Henrik Bærbak Christensen 2006 
*/

public class TwoMinuteMeanWind {
  /** true iff sensor readings are valid */
  public boolean valid;
  /** two minute mean wind direction; the value
      is between 0 and 359 degrees. */
  public int direction;
  /** two minute mean wind speed in knots; the
      value is between 0.0 and 99.9 */
  public double speed;
  /** low extreme wind direction in the
      two minute time span; range [0;359] degrees.
      low < high always. */
  public int low;
  /** high extreme wind direction in the
      two minute time span; range [0;359] degrees.
      low < high always. */
  public int high;
} 
