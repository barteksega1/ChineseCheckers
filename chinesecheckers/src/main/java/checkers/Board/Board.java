package checkers.Board;

import java.util.List;

import checkers.Cell.Cell;

public class Board {
    private List<Cell> cells;

    public void initializeSquare(int gameSize) {
        // TODO: Implement this method
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
