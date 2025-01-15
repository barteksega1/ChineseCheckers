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

    /**
     * Gets the color of the cell.
     *
     * @return the color of the cell
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * Sets the color of the cell.
     *
     * @param color the color to set
     */
    public void setColor(CellColor color) {
        this.color = color;
    }

    /**
     * Gets the status of the cell.
     *
     * @return the status of the cell
     */
    public CellStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the cell.
     *
     * @param status the status to set
     */
    public void setStatus(CellStatus status) {
        this.status = status;
    }

    /**
     * Gets the player number occupying the cell.
     *
     * @return the player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Sets the player number occupying the cell.
     *
     * @param playerNumber the player number to set
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * Checks if the cell is a home cell.
     *
     * @return true if the cell is a home cell, false otherwise
     */
    public boolean isHomeCell() {
        return isHomeCell;
    }

    /**
     * Checks if the cell is playable.
     *
     * @return true if the cell is playable, false otherwise
     */
    public boolean isPlayable() {
        return isPlayable;
    }

    /**
     * Sets whether the cell is playable.
     *
     * @param isPlayable true if the cell is playable, false otherwise
     */
    public void setPlayable(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

    /**
     * Checks if the cell is occupied by the specified player.
     *
     * @param playerNumber the player number to check
     * @return true if the cell is occupied by the specified player, false otherwise
     */
    public boolean isOccupiedByPlayer(int playerNumber) {
        return this.playerNumber == playerNumber;
    }

}