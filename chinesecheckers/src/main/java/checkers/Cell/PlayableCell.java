package checkers.Cell;

public class PlayableCell extends Cell {

    public PlayableCell(int row, int column) {
        this.row = row;
        this.column = column;
        this.status = CellStatus.ILLEGAL;
        this.playerNumber = -1; // Use -1 to indicate no player
        this.isHomeCell = false;
        this.isPlayable = true;
    }

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
