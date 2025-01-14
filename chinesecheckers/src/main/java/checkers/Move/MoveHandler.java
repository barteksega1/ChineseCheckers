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

    public MoveHandler() {
        this.basicRules = new BasicRules();
        this.jumpRules = new JumpRules();
    }

    public String validateMove(Board board, int[] moveCoordinates, Player player) {
        int startRow = moveCoordinates[0];
        int startColumn = moveCoordinates[1];
        int endRow = moveCoordinates[2];
        int endColumn = moveCoordinates[3];


        // Sprawdzenie, czy ruch jest legalny zgodnie z podstawowymi zasadami
        if (!basicRules.isMoveLegal(board, startRow, startColumn, endRow, endColumn)) {
            return "Move is not legal according to basic rules";
        }

        Cell startCell = board.getCell(startRow, startColumn);
        Cell endCell = board.getCell(endRow, endColumn);

        // Exclude illegal cells
        if (startCell.getStatus() == CellStatus.ILLEGAL) {
            return "Start cell is illegal";
        }
        if (endCell.getStatus() == CellStatus.ILLEGAL) {
            return "End cell is illegal";
        }

        // Sprawdzenie, czy ruch jest ruchem skokowym
        boolean isJumpMove = jumpRules.isJumpMovePossible(board, startRow, startColumn, endRow, endColumn);
        
        // Sprawdzenie, czy jest to ruch skokowy
        if (basicRules.isNormalMovePossible(startRow, startColumn, endRow, endColumn) || isJumpMove) {
            return "valid";
        }

        // Ruch nie był możliwy
        return "Move is not possible";
    }

    public boolean makeMove(Board board, int[] moveCoordinates, Player player) {
        if (!validateMove(board, moveCoordinates, player).equals("valid")) {
            return false;
        }

        int startRow = moveCoordinates[0];
        int startColumn = moveCoordinates[1];
        int endRow = moveCoordinates[2];
        int endColumn = moveCoordinates[3];

        Cell startCell = board.getCell(startRow, startColumn);
        Cell endCell = board.getCell(endRow, endColumn);

        // Check if the move is a jump move
        boolean isJumpMove = jumpRules.isJumpMovePossible(board, startRow, startColumn, endRow, endColumn);

        // Aktualizacja komórek na planszy
        endCell.setStatus(startCell.getStatus());
        endCell.setColor(startCell.getColor());
        endCell.setPlayerNumber(startCell.getPlayerNumber());

        startCell.setStatus(CellStatus.FREE);
        startCell.setColor(null);
        startCell.setPlayerNumber(-1);

        board.setCell(startCell, startRow, startColumn);
        board.setCell(endCell, endRow, endColumn);

        PlayerCells playerCells = player.getPlayerCells();
        List<Cell> currentCells = playerCells.getCurrentCells();
        currentCells.remove(startCell);
        currentCells.add(endCell);
        playerCells.updateCurrentCells(currentCells);

        return isJumpMove;
    }

    public boolean hasAdditionalJumpMoves(Board board, int[] moveCoordinates) {
        int endRow = moveCoordinates[2];
        int endColumn = moveCoordinates[3];
        return jumpRules.checkForAdditionalJumpMoves(board, endRow, endColumn);
    }
}
