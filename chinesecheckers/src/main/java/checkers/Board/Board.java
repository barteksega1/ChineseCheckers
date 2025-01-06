package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
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
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getStatus() == CellStatus.ILLEGAL) {
                    System.out.print(RED + "X " + RESET);
                } else {
                    System.out.print(GREEN + "O " + RESET);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.initializeSquare(5);
        board.printBoard();
    }
}

