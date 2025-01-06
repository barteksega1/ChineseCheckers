package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

public class BoardFiller {

    public void fillBoard(Board board, int gameSize) {

        if ((gameSize + 1)/2 % 2 == 0) {
            for (int row = 0; row < board.getRows(); row++) {
                for (int column = 0; column < board.getColumns(); column++) {
                    if ((row % 2 == 0 && column % 2 != 0) || (row % 2 != 0 && column % 2 == 0)) {
                        Cell cell = board.getCell(row, column);
                        cell.setStatus(CellStatus.ILLEGAL);
                        board.setCell(cell, row, column);
                    }
                }
            }
        } else {
            for (int row = 0; row < board.getRows(); row++) {
                for (int column = 0; column < board.getColumns(); column++) {
                    if ((row % 2 == 0 && column % 2 == 0) || (row % 2 != 0 && column % 2 != 0)) {
                        Cell cell = board.getCell(row, column);
                        cell.setStatus(CellStatus.ILLEGAL);
                        board.setCell(cell, row, column);
                    }
                }
            }
        }
    }
}
