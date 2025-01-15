package checkers.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

/**
 * Handles the rules for jump moves in the game.
 */
public class JumpRules {

    /**
     * Checks if a jump move is possible based on the start and end coordinates.
     *
     * @param board the game board
     * @param startRow the starting row index
     * @param startColumn the starting column index
     * @param endRow the ending row index
     * @param endColumn the ending column index
     * @return true if a jump move is possible, false otherwise
     */
    public boolean isJumpMovePossible(Board board, int startRow, int startColumn, int endRow, int endColumn) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startColumn - endColumn);

        if (rowDiff == 2 && colDiff == 2) {
            int midRow = (startRow + endRow) / 2;
            int midColumn = (startColumn + endColumn) / 2;
            Cell midCell = board.getCell(midRow, midColumn);
            return midCell.getStatus() == CellStatus.OCCUPIED;
        }

        return false;
    }

    /**
     * Checks if there are additional jump moves available from the current position.
     *
     * @param board the game board
     * @param currentRow the current row index
     * @param currentColumn the current column index
     * @return true if additional jump moves are available, false otherwise
     */
    public boolean checkForAdditionalJumpMoves(Board board, int currentRow, int currentColumn) {
        int[][] possibleMoves = {
            {currentRow - 2, currentColumn - 2},
            {currentRow - 2, currentColumn + 2},
            {currentRow + 2, currentColumn - 2},
            {currentRow + 2, currentColumn + 2}
        };

        for (int[] move : possibleMoves) {
            int newRow = move[0];
            int newColumn = move[1];
            if (isJumpMovePossible(board, currentRow, currentColumn, newRow, newColumn)) {
                return true;
            }
        }

        return false;
    }
}
