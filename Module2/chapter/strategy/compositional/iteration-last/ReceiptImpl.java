/** Implementation of Receipt.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class ReceiptImpl implements Receipt {
  private int value;
  public ReceiptImpl(int value) { this.value = value; }
  public int value() { return value;}
  public void show() {}
}
