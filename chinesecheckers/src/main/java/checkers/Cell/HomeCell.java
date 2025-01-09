package checkers.Cell;

public class HomeCell extends Cell {    
    public HomeCell(int row, int column) {
        this.color = CellColor.NONE;
        this.row = row;
        this.column = column;
        this.status = CellStatus.FREE;
        this.isHomeCell = true;
        this.isPlayable = true;
    }


    public HomeCell(CellColor color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
        this.status = CellStatus.FREE;
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
