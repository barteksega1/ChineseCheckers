package checkers.Board.Cell;

public class Cell {
    private boolean occupied;
    private String player;

    public Cell() {
        this.occupied = false;
        this.player = null;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
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