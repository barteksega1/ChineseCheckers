package checkers.Cell;

public abstract class Cell {
    protected int row;
    protected int column;
    protected CellStatus status;
    protected String player;
    protected CellColor color;
    protected boolean isHomeCell;
    protected boolean isPlayable;

    public Cell() {
        this.status = CellStatus.ILLEGAL;
        this.player = null;
    }

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

    public boolean isHomeCell() {
        return isHomeCell;
    }

    public boolean isPlayable() {
        return isPlayable;
    }

}