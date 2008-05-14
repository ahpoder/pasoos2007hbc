package hottargui.config;

import hottargui.framework.*;

public class DeltaTile implements Tile {

    private Position position;
    private TileType tileType;
    private PlayerColor playerColor;
    private int unitCount;

    public DeltaTile(TileType tileType, PlayerColor playerColor) {
        this.tileType = tileType;
        this.playerColor = playerColor;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public PlayerColor getOwnerColor() {
        return playerColor;
    }

    public void changePlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void changeUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public TileType getType() {
        return tileType;
    }
}
