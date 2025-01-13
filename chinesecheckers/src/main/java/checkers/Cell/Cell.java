package checkers.Cell;

public abstract class Cell {
    protected int row;
    protected int column;
    protected CellStatus status;
    protected int playerNumber;
    protected CellColor color;
    protected boolean isHomeCell;
    protected boolean isPlayable;

    public Cell() {
        this.status = CellStatus.ILLEGAL;
        this.playerNumber = -1; // Use -1 to indicate no player
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

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public boolean isHomeCell() {
        return isHomeCell;
    }

    public boolean isPlayable() {
        return isPlayable;
    }

    public void setPlayable(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

    public boolean isOccupiedByPlayer(int playerNumber) {
        return this.playerNumber == playerNumber;
    }

}