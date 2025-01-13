package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;
import checkers.Cell.PlayableCell;

public class Board {
    private Cell[][] cells;
    private int gameSize; // Added gameSize field

    public void initializeSquare(int gameSize) {
        if (gameSize < 5) {
            throw new IllegalArgumentException("Game size must be at least 5");
        }

        this.gameSize = gameSize; // Initialize gameSize
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;
        cells = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new PlayableCell(i, j);
            }
        }
    }
    
    public int getGameSize() {
        return this.gameSize;
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

        final String WHITE = "\u001B[97m";
        final String BLUE = "\u001B[34m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String PURPLE = "\u001B[35m";
        final String ORANGE = "\u001B[38;5;214m"; // Corrected ANSI escape sequence for orange
        final String BLACK = "\u001B[30m";


        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getStatus() == CellStatus.ILLEGAL) {
                    System.out.print(BLACK + "  "+ RESET); // Simulate transparency by printing spaces
                } else if (cell instanceof HomeCell) {
                    switch (((HomeCell) cell).getHomeColor()) {
                        case RED:
                            System.out.print(RED + "R " + RESET);
                            break;
                        case GREEN:
                            System.out.print(GREEN + "G " + RESET);
                            break;
                        case BLUE:
                            System.out.print(BLUE + "B " + RESET);
                            break;
                        case YELLOW:
                            System.out.print(YELLOW + "Y " + RESET);
                            break;
                        case PURPLE:
                            System.out.print(PURPLE + "P " + RESET);
                            break;
                        case ORANGE:
                            System.out.print(ORANGE + "O " + RESET);
                            break;
                        default:
                            System.out.print("H ");
                            break;
                    }

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

