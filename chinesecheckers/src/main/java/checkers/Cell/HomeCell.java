package checkers.Cell;

public class HomeCell extends Cell {    
    private CellColor homeColor;

    /**
     * Constructs a HomeCell with the specified row and column.
     *
     * @param row the row of the cell
     * @param column the column of the cell
     */
    public HomeCell(int row, int column) {
        this.color = CellColor.NONE;
        this.row = row;
        this.column = column;
        this.status = CellStatus.FREE;
        this.isHomeCell = true;
        this.isPlayable = true;
        this.homeColor = CellColor.NONE;
        this.playerNumber = -1; // Use -1 to indicate no player
    }

    /**
     * Constructs a HomeCell with the specified color, row, and column.
     *
     * @param color the home color of the cell
     * @param row the row of the cell
     * @param column the column of the cell
     */
    public HomeCell(CellColor color, int row, int column) {
        this.color = CellColor.NONE;
        this.row = row;
        this.column = column;
        this.status = CellStatus.FREE;
        this.isHomeCell = true;
        this.isPlayable = true;
        this.homeColor = color;
        this.playerNumber = -1; // Use -1 to indicate no player
    }

    /**
     * Gets the home color of the cell.
     *
     * @return the home color of the cell
     */
    public CellColor getHomeColor() {
        return homeColor;
    }

    /**
     * Sets the home color of the cell.
     *
     * @param homeColor the home color to set
     */
    public void setHomeColor(CellColor homeColor) {
        this.homeColor = homeColor;
    }

    /**
     * Returns a string representation of the HomeCell.
     *
     * @return a string representation of the HomeCell
     */
    @Override
    public String toString() {
        return "HomeCell{" +
                "row=" + row +
                ", column=" + column +
                ", status=" + status +
                ", playerNumber=" + playerNumber +
                ", color=" + color +
                ", homeColor=" + homeColor +
                '}';
    }

}
