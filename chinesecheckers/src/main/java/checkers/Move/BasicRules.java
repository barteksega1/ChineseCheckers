package checkers.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class BasicRules {

    /**
     * Checks if the move is legal based on the start and end cells' status.
     *
     * @param board the game board
     * @param startRow the starting row index
     * @param startColumn the starting column index
     * @param endRow the ending row index
     * @param endColumn the ending column index
     * @return true if the move is legal, false otherwise
     */
    public boolean isMoveLegal(Board board, int startRow, int startColumn, int endRow, int endColumn) {
        Cell startCell = board.getCell(startRow, startColumn);
        Cell endCell = board.getCell(endRow, endColumn);

        return startCell.getStatus() == CellStatus.OCCUPIED && endCell.getStatus() == CellStatus.FREE;
    }

    /**
     * Checks if a normal move (non-jump) is possible based on the row and column differences.
     *
     * @param startRow the starting row index
     * @param startColumn the starting column index
     * @param endRow the ending row index
     * @param endColumn the ending column index
     * @return true if a normal move is possible, false otherwise
     */
    public boolean isNormalMovePossible(int startRow, int startColumn, int endRow, int endColumn) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startColumn - endColumn);
        return (rowDiff == 1 && colDiff == 1);
    }
}
