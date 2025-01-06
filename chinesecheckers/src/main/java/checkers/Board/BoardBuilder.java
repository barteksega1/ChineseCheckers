package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class BoardBuilder {
    private final StarShapeChecker shapeChecker = new StarShapeChecker();

    public void setupBoard(Board board, int gameSize) {
        board.initializeSquare(gameSize);

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                if (shapeChecker.checkFirstTriangle(row, column, gameSize )) {
                    Cell cell = board.getCell(row, column);
                    cell.setStatus(CellStatus.FREE);
                    board.setCell(cell, row, column);
                } 
                else if (shapeChecker.checkSecondTriangle(row, column, gameSize)) {
                    Cell cell = board.getCell(row, column);
                    cell.setStatus(CellStatus.FREE);
                    board.setCell(cell, row, column);
                } 
            }
        }

        // Call printBoard to check if it works well
        board.printBoard();
    }

    public static void main(String[] args) {
        Board board = new Board();
        BoardBuilder builder = new BoardBuilder();
        builder.setupBoard(board, 7);
    }
}

