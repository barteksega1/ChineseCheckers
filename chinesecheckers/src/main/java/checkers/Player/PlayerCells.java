package checkers.Player;

import java.util.List;

import checkers.Cell.Cell;

public class PlayerCells {
    private final List<Cell> homeCells;
    private List<Cell> currentCells;

    public PlayerCells(List<Cell> homeCells, List<Cell> currentCells) {
        this.homeCells = homeCells;
        this.currentCells = currentCells;
    }

    public List<Cell> getHomeCells() {
        return homeCells;
    }

    public List<Cell> getCurrentCells() {
        return currentCells;
    }

    public void updateCurrentCells(List<Cell> newCurrentCells) {
        this.currentCells = newCurrentCells;
    }
}
