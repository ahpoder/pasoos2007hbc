package hottargui.view;

import hottargui.config.*;
import hottargui.framework.*;
import hottargui.standard.*;

import minidraw.standard.*;
import minidraw.framework.*;

/*
 * Basic demo of gui changes are reflected in the domain.

 @author Henrik Bærbak Christensen

 */

public class Beta {
  
  public static void main(String[] args) {
    Game game = new StandardGame();
    GameFactory gameFactory = new BetaGameFactory(game);
    ((StandardGame)game).setGameFactory(gameFactory);
	game.newGame();
        ViewFactory f = new ViewFactory(game);
        DrawingEditor window = new MiniDrawApplication("TarGui", f);
        window.setTool(new hottargui.view.UnitMoveTool(window, game));
        window.open();
  }
}


