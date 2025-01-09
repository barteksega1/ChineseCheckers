package checkers.Cell;

public class HomeCell extends Cell {    
    public HomeCell(int row, int column) {
        this(CellColor.NONE, row, column);
    }


    public HomeCell(CellColor color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
        this.status = CellStatus.OCCUPIED;

        this.isHomeCell = true;
        this.isPlayable = true;

    }

    @Override
    public String toString() {
        return "HomeCell{" +
                "row=" + row +
                ", column=" + column +
                ", status=" + status +
                ", player='" + player + '\'' +
                ", color=" + color +
                '}';
    }

}
