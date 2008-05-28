package hottargui.framework;

public interface Deck {

    public Tile getTile(Position position);
    public void shuffleDeck();
    public boolean isEmpty();
    
}
