package checkers.Cell;

public class PlayableCell extends Cell {

    public PlayableCell(int row, int column) {
        this.row = row;
        this.column = column;
        this.status = CellStatus.ILLEGAL;
        this.player = null;
        this.isHomeCell = false;
        this.isPlayable = true;
    }

    @Override
    public String toString() {
        return "PlayableCell{" +
                "row=" + row +
                ", column=" + column +
                ", status=" + status +
                ", player='" + player + '\'' +
                ", color=" + color +
                '}';
    }
}
