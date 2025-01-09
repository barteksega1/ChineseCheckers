package checkers.Cell.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class MoveHandler {

    public boolean move(Board board, int startRow, int startColumn, int endRow, int endColumn) {
        Cell startCell = board.getCell(startRow, startColumn);
        Cell endCell = board.getCell(endRow, endColumn);

        if (startCell.getStatus() == CellStatus.OCCUPIED && endCell.getStatus() == CellStatus.FREE) {
            endCell.setStatus(startCell.getStatus());
            endCell.setColor(startCell.getColor());
            endCell.setPlayer(startCell.getPlayer());

            startCell.setStatus(CellStatus.FREE);
            startCell.setColor(null);
            startCell.setPlayer(null);

            board.setCell(startCell, startRow, startColumn);
            board.setCell(endCell, endRow, endColumn);

            return true;
        }

        return false;
    }
}
