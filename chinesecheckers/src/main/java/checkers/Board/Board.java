package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;
import checkers.Cell.PlayableCell;

public class Board {
    private Cell[][] cells;

    public void initializeSquare(int gameSize) {
        if (gameSize < 5) {
            throw new IllegalArgumentException("Game size must be at least 5");
        }

        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;
        cells = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new PlayableCell(i, j);
            }
        }
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(Cell cell, int row, int column) {
        cells[row][column] = cell;
    }

    public int getColumns() {
        return cells[0].length;
    }

    public int getRows() {
        return cells.length;
    }

    public void printBoard() {
        final String RESET = "\u001B[0m";
        final String DARK_GREY = "\u001B[90m";
        final String WHITE = "\u001B[97m";
        final String BLUE = "\u001B[34m";
    

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getStatus() == CellStatus.ILLEGAL) {
                    System.out.print(DARK_GREY + "X " + RESET);
                } else if (cell instanceof HomeCell) {
                    System.out.print(BLUE + "H " + RESET);
                } else if (cell.getStatus() == CellStatus.FREE) {
                    System.out.print(WHITE + "O " + RESET);
                } else {
                    System.out.print(BLUE + "O " + RESET);
                }
            }
            System.out.println();
        }
    }
}

