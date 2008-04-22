package hottargui.framework;

public class TileImpl implements Tile {
	private PlayerColor colour; 
	private Position position; 
	private TileType type;
	private int unitCount;
	
	public TileImpl(PlayerColor colour, Position position, TileType type, int unitCount){
		this.colour = colour;
		this.position = position;
		this.type = type;
		this.unitCount = unitCount;
	}
	public PlayerColor getOwnerColor() {
		return colour;
	}

	public Position getPosition() {
		return position;
	}

	public TileType getType() {
		return type;
	}

	public int getUnitCount() {
		return unitCount;
	}
}
