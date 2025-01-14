package checkers.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class JumpRules {

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
