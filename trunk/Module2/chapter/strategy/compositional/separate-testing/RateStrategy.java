/** This interface defines the responsibility of
    calculating parking time based upon the
    payment accepted by a pay station. It
    plays the Strategy role of the Strategy pattern.

    Instances implementing this interface defines
    a specific rate structure.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public interface RateStrategy {
  /** return the number of minutes parking time the
      provided amount of payment is valid for.
      @param amount payment in some currency.
      @return number of minutes parking time
  */
  public int calculateTime( int amount );
}

