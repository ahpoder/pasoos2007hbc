package hottargui.framework;

/** Position on the board.
    
    Responsibilities:
    1) Know a specific location (row,column) on a Targui board.
*/



public class Position {

  /** create a position. 
   * PRECONDITION: row and column must be within the range 0..6 whenever
   * Position objects are used within the domain - that is, never
   * pass invalid object over the Game interface as no range checks are
   * made within the domain package. You can, however, use "invalid"
   * postions in the gui as part of range checks. (For an example,
   * see the UnitMoveTool).
   * @param r the row
   * @param c the column
  */
  public Position(int r, int c) { 
    this.r = r; this.c = c; 
  }

  protected int r;
  protected int c;

  /** get the row represented by this position.
   * @return the row.
   */
  public int getRow() { return r; }

  /** get the column represented by this position.
   * @return the column.
   */
  public int getColumn() { return c; }

  public boolean equals(Object o) {
    if (o.getClass() != Position.class) { return false; }
    Position other = (Position) o;
    return r==other.r && c==other.c;
  }

  public String toString() {
    return "{"+r+","+c+"}";
  }
}
