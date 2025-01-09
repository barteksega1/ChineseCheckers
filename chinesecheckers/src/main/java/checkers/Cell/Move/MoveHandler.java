package checkers.Cell.Move;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class MoveHandler {

    private final BasicRules basicRules;
    private final JumpRules jumpRules;
    private boolean isFirstMove;

    public MoveHandler() {
        this.basicRules = new BasicRules();
        this.jumpRules = new JumpRules();
        this.isFirstMove = true;
    }

    public boolean move(Board board, int startRow, int startColumn, int endRow, int endColumn) {
        // Sprawdzenie, czy ruch jest legalny zgodnie z podstawowymi zasadami
        if (!basicRules.isMoveLegal(board, startRow, startColumn, endRow, endColumn)) {
            throw new IllegalArgumentException("Move is not legal");
        }

        // Sprawdzenie, czy ruch jest ruchem skokowym
        boolean isJumpMove = jumpRules.isJumpMovePossible(board, startRow, startColumn, endRow, endColumn);
        
        // Sprawdzenie, czy jest to pierwszy ruch lub ruch skokowy
        if ((isFirstMove && basicRules.isNormalMovePossible(startRow, startColumn, endRow, endColumn)) || isJumpMove) {
            // Aktualizacja komórek na planszy
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

            // Jeśli jest to ruch skokowy, sprawdzenie dodatkowych możliwych ruchów skokowych
            if (isJumpMove) {
                isFirstMove = false;
                return jumpRules.checkForAdditionalJumpMoves(board, endRow, endColumn);
            }

            // Ustawienie flagi pierwszego ruchu na true
            isFirstMove = true;
            return true;
        }

        // Ruch nie był możliwy
        return false;
    }
}
