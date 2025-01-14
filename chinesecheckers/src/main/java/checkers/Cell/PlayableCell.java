package checkers.Cell;

public class PlayableCell extends Cell {

    /**
     * Constructs a PlayableCell with the specified row and column.
     *
     * @param row the row of the cell
     * @param column the column of the cell
     */
    public PlayableCell(int row, int column) {
        this.row = row;
        this.column = column;
        this.status = CellStatus.ILLEGAL;
        this.playerNumber = -1; // Use -1 to indicate no player
        this.isHomeCell = false;
        this.isPlayable = true;
    }

    /**
     * Returns a string representation of the PlayableCell.
     *
     * @return a string representation of the PlayableCell
     */
    @Override
    public String toString() {
        return "PlayableCell{" +
                "row=" + row +
                ", column=" + column +
                ", status=" + status +
                ", playerNumber=" + playerNumber +
                ", color=" + color +
                '}';
    }
}
