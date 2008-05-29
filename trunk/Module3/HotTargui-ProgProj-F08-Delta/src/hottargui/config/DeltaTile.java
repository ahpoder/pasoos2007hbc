package hottargui.config;

import hottargui.framework.Tile;
import hottargui.framework.Position;
import hottargui.framework.PlayerColor;
import hottargui.framework.TileType;

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

    public int getEconomicValue() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
