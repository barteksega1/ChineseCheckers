package checkers.Move;

import java.util.List;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Player.Player;
import checkers.Player.PlayerCells;

public class MoveHandler {

    private final BasicRules basicRules;
    private final JumpRules jumpRules;
    private boolean isFirstMove;

    public MoveHandler() {
        this.basicRules = new BasicRules();
        this.jumpRules = new JumpRules();
        this.isFirstMove = true;
    }

    public boolean move(Board board, int startRow, int startColumn, int endRow, int endColumn, Player player) {
        PlayerCells playerCells = player.getPlayerCells();

        // Sprawdzenie, czy ruch jest legalny zgodnie z podstawowymi zasadami
        if (!basicRules.isMoveLegal(board, startRow, startColumn, endRow, endColumn)) {
            throw new IllegalArgumentException("Move is not legal");
        }

        Cell startCell = board.getCell(startRow, startColumn);
        Cell endCell = board.getCell(endRow, endColumn);

        // Exclude illegal cells
        if (startCell.getStatus() == CellStatus.ILLEGAL || endCell.getStatus() == CellStatus.ILLEGAL) {
            throw new IllegalArgumentException("Move involves illegal cell");
        }

        // Sprawdzenie, czy ruch jest ruchem skokowym
        boolean isJumpMove = jumpRules.isJumpMovePossible(board, startRow, startColumn, endRow, endColumn);
        
        // Sprawdzenie, czy jest to pierwszy ruch lub ruch skokowy
        if ((isFirstMove && basicRules.isNormalMovePossible(startRow, startColumn, endRow, endColumn)) || isJumpMove) {
            // Aktualizacja komórek na planszy
            endCell.setStatus(startCell.getStatus());
            endCell.setColor(startCell.getColor());
            endCell.setPlayerNumber(startCell.getPlayerNumber());

            startCell.setStatus(CellStatus.FREE);
            startCell.setColor(null);
            startCell.setPlayerNumber(-1);

            board.setCell(startCell, startRow, startColumn);
            board.setCell(endCell, endRow, endColumn);

            // Aktualizacja listy currentCells
            List<Cell> currentCells = playerCells.getCurrentCells();
            currentCells.remove(startCell);
            currentCells.add(endCell);
            playerCells.updateCurrentCells(currentCells);

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
