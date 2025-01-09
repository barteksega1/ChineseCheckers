package checkers.Cell.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class BasicRules {

    public boolean isMoveLegal(Board board, int startRow, int startColumn, int endRow, int endColumn) {
        Cell startCell = board.getCell(startRow, startColumn);
        Cell endCell = board.getCell(endRow, endColumn);

        return startCell.getStatus() == CellStatus.OCCUPIED && endCell.getStatus() == CellStatus.FREE;
    }

    public boolean isMovePossible(int startRow, int startColumn, int endRow, int endColumn) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startColumn - endColumn);
        return (rowDiff == 1 && colDiff == 1);
    }
}
