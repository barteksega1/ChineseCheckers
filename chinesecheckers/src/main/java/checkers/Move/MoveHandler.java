package checkers.Move;

import java.util.List;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Player.Player;
import checkers.Player.PlayerCells;

/**
 * Handles the validation and execution of moves in the game.
 */
public class MoveHandler {

    private final BasicRules basicRules;
    private final JumpRules jumpRules;

    public MoveHandler() {
        this.basicRules = new BasicRules();
        this.jumpRules = new JumpRules();
    }

    /**
     * Validates a move based on the game rules.
     *
     * @param board the game board
     * @param moveCoordinates the coordinates of the move
     * @param player the player making the move
     * @return a string indicating whether the move is valid or the reason it is not
     */
    public String validateMove(Board board, int[] moveCoordinates, Player player) {
        int startRow = moveCoordinates[0];
        int startColumn = moveCoordinates[1];
        int endRow = moveCoordinates[2];
        int endColumn = moveCoordinates[3];

        // Check if the move is legal according to basic rules
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

        // Check if the move is a jump move
        boolean isJumpMove = jumpRules.isJumpMovePossible(board, startRow, startColumn, endRow, endColumn);

        // Check if it is a normal move or a jump move
        if (basicRules.isNormalMovePossible(startRow, startColumn, endRow, endColumn) || isJumpMove) {
            return "valid";
        }

        // Move was not possible
        return "Move is not possible";
    }

    /**
     * Executes a move on the game board.
     *
     * @param board the game board
     * @param moveCoordinates the coordinates of the move
     * @param player the player making the move
     * @return true if the move was a jump move, false otherwise
     */
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

        if (!isJumpMove) {
            return false;
        }

        // Update the board cells
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

    /**
     * Checks if there are additional jump moves available for the player.
     *
     * @param board the game board
     * @param moveCoordinates the coordinates of the last move
     * @return true if additional jump moves are available, false otherwise
     */
    public boolean hasAdditionalJumpMoves(Board board, int[] moveCoordinates) {
        int endRow = moveCoordinates[2];
        int endColumn = moveCoordinates[3];
        return jumpRules.checkForAdditionalJumpMoves(board, endRow, endColumn);
    }
}
