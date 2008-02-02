package paystation.domain;

/** Instances of this class represents an exception raised
    when an illegal coin is encountered in pay station.

    author: (c) Henrik Bærbak Christensen 2006
*/
public class IllegalCoinException extends Exception {
  public IllegalCoinException( String e ) { super(e); }
}
