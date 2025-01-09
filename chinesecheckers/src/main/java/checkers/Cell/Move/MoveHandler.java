package checkers.Cell.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class MoveHandler {

    private BasicRules basicRules;
    private JumpRules jumpRules;

    public MoveHandler() {
        this.basicRules = new BasicRules();
        this.jumpRules = new JumpRules();
    }

    public boolean move(Board board, int startRow, int startColumn, int endRow, int endColumn) {
        if (!basicRules.isMoveLegal(board, startRow, startColumn, endRow, endColumn)) {
            throw new IllegalArgumentException("Move is not legal");
        }

        boolean isJumpMove = jumpRules.isJumpMovePossible(board, startRow, startColumn, endRow, endColumn);
        if (basicRules.isNormalMovePossible(startRow, startColumn, endRow, endColumn) || isJumpMove) {
            Cell startCell = board.getCell(startRow, startColumn);
            Cell endCell = board.getCell(endRow, endColumn);

            endCell.setStatus(startCell.getStatus());
            endCell.setColor(startCell.getColor());
            endCell.setPlayer(startCell.getPlayer());

            startCell.setStatus(CellStatus.FREE);
            startCell.setColor(null);
            startCell.setPlayer(null);

            board.setCell(startCell, startRow, startColumn);
            board.setCell(endCell, endRow, endColumn);

            if (isJumpMove) {
                // Check for additional jump moves
                return jumpRules.checkForAdditionalJumpMoves(board, endRow, endColumn);
            }

            return true;
        }

        return false;
    }
}
