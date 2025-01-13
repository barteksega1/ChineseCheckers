package checkers.Commands;

import checkers.Board.Board;
import checkers.Move.MoveHandler;
import checkers.Player.Player;

public class MoveCommand extends Command {
    
    private final String[] moveInput;
    private final MoveHandler moveHandler;
    private final Board board;
    private final Player player;

    public MoveCommand(String[] moveInput, MoveHandler moveHandler, Board board, Player player) {
        this.moveInput = moveInput;
        this.moveHandler = moveHandler;
        this.board = board;
        this.player = player;
    }

    @Override
    public String command() {
        int[] moveCoordinates = parseMove(moveInput);
        String validationMessage = moveHandler.validateMove(board, moveCoordinates, player);
        if (validationMessage.equals("valid")) {
            boolean isJumpMove = moveHandler.makeMove(board, moveCoordinates, player);
            return isJumpMove ? "Move executed, additional jump move possible" : "Move executed";
        } else {
            return "Move failed: " + validationMessage;
        }
    }

    private int[] parseMove(String[] moveString) {
        int[] parMove = new int[moveString.length];
        for (int i = 0; i < moveString.length; i++) {
            try {
                parMove[i] = Integer.parseInt(moveString[i]);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid move format");
            }
        }
        return parMove;
    }
}
