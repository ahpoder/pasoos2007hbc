package hottargui.framework;

/** TileType is a enumeration over the types of tiles available in
    Targui.

 */

public enum TileType {

    Settlement(4,4),
    Saltmine(5,5),
    Oasis(3,3),
    Erg(1,0),
    Reg(2,0),
    Feshfesh(0,2),
    Mountain(0,1),
    Saltlake(0,0);

    private final int economicValue;
    private final int strategicValue;

    private TileType(int economicValue, int strategicValue) {
        this.economicValue = economicValue;
        this.strategicValue = strategicValue;
    }
}
            