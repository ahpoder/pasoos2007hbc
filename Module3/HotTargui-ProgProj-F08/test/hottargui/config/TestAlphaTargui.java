package hottargui.config;

import hottargui.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestAlphaTargui {

  Game game;

  @Before 
  public void setUp() {
    game = new AlphaGame();
    game.newGame();
  }

  @Test 
  public void testInitialPlayerInTurnIsRed() {
    Player p = game.getPlayerInTurn();
    assertEquals( PlayerColor.Red, p.getColor() );
  }

  
  
}
