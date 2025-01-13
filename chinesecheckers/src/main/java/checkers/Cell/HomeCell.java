package checkers.Cell;

public class HomeCell extends Cell {    
    private CellColor homeColor;

    public HomeCell(int row, int column) {
        this.color = CellColor.NONE;
        this.row = row;
        this.column = column;
        this.status = CellStatus.FREE;
        this.isHomeCell = true;
        this.isPlayable = true;
        this.homeColor = CellColor.NONE;
    }

    public HomeCell(CellColor color, int row, int column) {
        this.color = CellColor.NONE;
        this.row = row;
        this.column = column;
        this.status = CellStatus.FREE;
        this.isHomeCell = true;
        this.isPlayable = true;
        this.homeColor = color;
    }

    public CellColor getHomeColor() {
        return homeColor;
    }

    public void setHomeColor(CellColor homeColor) {
        this.homeColor = homeColor;
    }

    @Override
    public String toString() {
        return "HomeCell{" +
                "row=" + row +
                ", column=" + column +
                ", status=" + status +
                ", player='" + player + '\'' +
                ", color=" + color +
                ", homeColor=" + homeColor +
                '}';
    }

}
