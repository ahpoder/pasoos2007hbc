package hottargui.config;

import hottargui.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestBoard {

  Board board;

  @Before 
  public void setUp() {
    board = new Board();
  }

  @Test 
  public void testPlayersCount() {
    assertEquals( 4, board.getPlayerCount() );
  }

}
