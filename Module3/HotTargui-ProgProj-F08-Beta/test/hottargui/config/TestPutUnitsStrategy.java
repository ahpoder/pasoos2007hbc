package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.*;

import org.junit.*;

import static org.junit.Assert.*;


public class TestPutUnitsStrategy {
	Tile tile;
	PutUnitsStrategy pus; 
	Player p;

	@Before	
	public void setUp(){
		pus = new BetaPutUnitsStrategy();
		p = new StandardPlayer(PlayerColor.Red);
	}

	@Test 
	public void testPutOnOwnTile(){
		tile = new StandardTile(TileType.Erg, PlayerColor.Red, 1,1 );
		assertTrue(pus.isPutValid(p, tile));
	}

	@Test 
	public void testPutOnOtherTile(){
		tile = new StandardTile(TileType.Erg, PlayerColor.Green, 1,1 );
		assertFalse(pus.isPutValid(p, tile));
	}

	@Test 
	public void testPutOnEmptyTile(){
		tile = new StandardTile(TileType.Erg, PlayerColor.None, 1,1 );
		assertFalse(pus.isPutValid(p, tile));
	}


}
