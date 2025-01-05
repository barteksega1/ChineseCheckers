package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import java.util.List;

public class BoardFiller {
    private List<Cell> cells;
    private StarShapeChecker starShapeChecker;

    public BoardFiller(List<Cell> cells) {
        this.cells = cells;
        this.starShapeChecker = new StarShapeChecker();
    }

    public void setInactiveCellsAsIllegal() {
        // TODO: Implement this method
    }

    public void checkStarShape(int gameSize) {
        // TODO: Implement this method
    }
}