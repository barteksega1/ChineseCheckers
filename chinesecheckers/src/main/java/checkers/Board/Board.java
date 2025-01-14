package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;
import checkers.Cell.PlayableCell;
import checkers.Player.Player;
import checkers.Player.PlayerCells;

/**
 * Represents the game board for Chinese Checkers.
 * Manages cells, game size, and player interactions.
 */
public class Board {
    private Cell[][] cells;
    private int gameSize;

    /**
     * Initializes a square board with the specified size.
     *
     * @param gameSize the size of the game, minimum 5
     * @throws IllegalArgumentException if gameSize is less than 5
     */
    public void initializeSquare(int gameSize) {
        if (gameSize < 5) {
            throw new IllegalArgumentException("Game size must be at least 5");
        }

        this.gameSize = gameSize;
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;
        cells = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new PlayableCell(i, j);
            }
        }
    }
    
    /**
     * Gets the current game size.
     *
     * @return the game size
     */
    public int getGameSize() {
        return this.gameSize;
    }
    
    /**
     * Retrieves the cell at the specified position.
     *
     * @param row the row index
     * @param column the column index
     * @return the Cell at the specified position
     */
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * Sets the cell at the specified position.
     *
     * @param cell the Cell to place
     * @param row the row index
     * @param column the column index
     */
    public void setCell(Cell cell, int row, int column) {
        cells[row][column] = cell;
    }

    /**
     * Gets the number of columns on the board.
     *
     * @return the column count
     */
    public int getColumns() {
        return cells[0].length;
    }

    /**
     * Gets the number of rows on the board.
     *
     * @return the row count
     */
    public int getRows() {
        return cells.length;
    }

    /**
     * Initializes cells for the specified player.
     *
     * @param player the Player to initialize cells for
     */
    public void initializePlayerCells(Player player) {
        FillPlayers fillPlayers = new FillPlayers();
        PlayerCells playerCells = fillPlayers.FillPlayerCells(this, player);
        player.setPlayerCells(playerCells);
    }

    /**
     * Updates illegal cells to be non-playable and resets their color and player number.
     */
    public void validateIllegalCells() {
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                Cell cell = getCell(row, column);
                if (cell.getStatus() == CellStatus.ILLEGAL) {
                    cell.setColor(CellColor.NONE);
                    cell.setPlayerNumber(-1);
                    cell.setPlayable(false);
                }
            }
        }
    }

    /**
     * Displays the board state with color-coded cells.
     */
    public void printBoard() {
        final String RESET = "\u001B[0m";

        final String WHITE = "\u001B[97m";
        final String BLUE = "\u001B[34m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String PURPLE = "\u001B[35m";
        final String ORANGE = "\u001B[38;5;214m";
        final String BLACK = "\u001B[30m";


        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getStatus() == CellStatus.ILLEGAL) {
                    System.out.print(BLACK + "  " + RESET);
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
