package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;

public class BoardBuilder {
    private final StarShapeChecker shapeChecker = new StarShapeChecker();
    private final BoardFiller boardFiller = new BoardFiller();

    public void setupBoard(Board board, int gameSize) {
        board.initializeSquare(gameSize);

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                boolean inFirstTriangle = shapeChecker.checkFirstTriangle(row, column, gameSize);
                boolean inSecondTriangle = shapeChecker.checkSecondTriangle(row, column, gameSize);

                if (inFirstTriangle && inSecondTriangle) {
                    Cell cell = board.getCell(row, column);
                    cell.setStatus(CellStatus.FREE);
                    board.setCell(cell, row, column);
                } else if (inFirstTriangle || inSecondTriangle) {

                    Cell cell = new HomeCell(row, column);

                    board.setCell(cell, row, column);
                } else {
                    Cell cell = board.getCell(row, column);
                    cell.setStatus(CellStatus.ILLEGAL);
                    board.setCell(cell, row, column);
                }
            }
        }

        // Fill the board with additional illegal cells
        boardFiller.fillBoard(board, gameSize);

        // Split home cells into groups and set their colors
        boardFiller.splitHomeCellsIntoGroups(board, gameSize);

        // Call printBoard to check if it works well
        board.printBoard();
    }

    public static void main(String[] args) {
        Board board = new Board();
        BoardBuilder builder = new BoardBuilder();
        builder.setupBoard(board, 7);
    }
}

