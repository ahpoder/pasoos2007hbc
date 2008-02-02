/** Test cases for pay station

Author: (c) Henrik Bærbak Christensen 2005
*/

using NUnit.Framework;

[TestFixture]
public class TestPayStation
{
  private PayStation ps;
  [SetUp]
  public void setup() {
    ps = new PayStationImpl();
  }

  [Test]
  public void testEnterNickel()
  {
    ps.addPayment(5);
    Assert.AreEqual(2, ps.readDisplay() );
  }
}


