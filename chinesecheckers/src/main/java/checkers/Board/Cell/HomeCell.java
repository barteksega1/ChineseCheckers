package checkers.Board.Cell;

public class HomeCell extends Cell {

    public HomeCell(CellColor color, int row, int column) {
        this.color = color;
        this.row = row;
        this.columnt = column;
        this.status = CellStatus.OCCUPIED;
    }
    
}
