package checkers.Board.Cell;

public abstract class Cell {
    private int row;
    private int column;
    private CellStatus status;
    private String player;
    private CellColor color;

    // public Cell() {
    //     this.occupied = false;
    //     this.player = null;
    // }

    public CellColor getColor() {
        return color;
    }

    public void setColor(CellColor color) {
        this.color = color;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return occupied ? player.substring(0, 1) : ".";
    }
}