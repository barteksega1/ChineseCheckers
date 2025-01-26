package checkers.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.HomeCell;
import checkers.Move.MoveHandler;

public class BotPlayer extends Player {
    private static final Map<Integer, Player> players = new HashMap<>();

    @Override
    public void addPlayer(int number, CellColor color) {
        Player player = new BotPlayer(number, color);
        players.put(number, player);  
    }

    private final Random random;

    public BotPlayer(int number, CellColor color) {
        super(number, color);
        super.isBot = true;
        this.random = new Random();
    }

    /**
     * Chooses a random piece and moves it within the rules.
     *
     * @param board the game board
     * @param moveHandler the move handler to validate and execute moves
     */
    public int[] makeMove(Board board, MoveHandler moveHandler) {
        List<Cell> currentCells = getPlayerCells().getCurrentCells();
        boolean moveMade = false;

        while (!moveMade) {
            Cell startCell = currentCells.get(random.nextInt(currentCells.size()));
            int startRow = startCell.row;
            int startColumn = startCell.column;

            // Get the enemy's home cells
            CellColor enemyColor = CellColor.getEnemy(getColor());
            List<HomeCell> enemyHomeCells = getEnemyHomeCells(board, enemyColor);

            // Try to find a valid move that minimizes the distance to the enemy's home cells
            int[][] directions = {
                {-1, -1}, // Top-left
                {-1, 1},  // Top-right
                {1, -1},  // Bottom-left
                {1, 1},   // Bottom-right
                {-2, -2}, // Top-left jump
                {-2, 2},  // Top-right jump
                {2, -2},  // Bottom-left jump
                {2, 2}    // Bottom-right jump
            };

            int[] bestMoveCoordinates = null;
            int minDistance = Integer.MAX_VALUE;

            for (int[] direction : directions) {
                int endRow = startRow + direction[0];
                int endColumn = startColumn + direction[1];

                int rowDiff = Math.abs(startRow - endRow);
                int colDiff = Math.abs(startColumn - endColumn);

                if (rowDiff <= 2 && colDiff <= 2) {
                    int[] moveCoordinates = {startRow, startColumn, endRow, endColumn};

                    if (moveHandler.validateMove(board, moveCoordinates, this).equals("valid")) {
                        Cell endCell = board.getCell(endRow, endColumn);
                        int distance = calculateMinDistance(endCell, enemyHomeCells);

                        if (distance < minDistance) {
                            minDistance = distance;
                            bestMoveCoordinates = moveCoordinates;
                        }
                    }
                }
            }

            if (bestMoveCoordinates != null) {
                moveHandler.makeMove(board, bestMoveCoordinates, this);
                moveMade = true;
                return bestMoveCoordinates;
            }
        }
        return null;
    }

    /**
     * Gets the enemy's home cells.
     *
     * @param board the game board
     * @param enemyColor the color of the enemy
     * @return a list of the enemy's home cells
     */
    private List<HomeCell> getEnemyHomeCells(Board board, CellColor enemyColor) {
        List<HomeCell> enemyHomeCells = new ArrayList<>();
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Cell cell = board.getCell(row, column);
                if (cell instanceof HomeCell && ((HomeCell) cell).getHomeColor() == enemyColor) {
                    enemyHomeCells.add((HomeCell) cell);
                }
            }
        }
        return enemyHomeCells;
    }

    /**
     * Calculates the minimum distance from the current cell to the enemy's home cells.
     *
     * @param currentCell the current cell
     * @param enemyHomeCells the list of the enemy's home cells
     * @return the minimum distance to the enemy's home cells
     */
    private int calculateMinDistance(Cell currentCell, List<HomeCell> enemyHomeCells) {
        int minDistance = Integer.MAX_VALUE;
        for (HomeCell enemyHomeCell : enemyHomeCells) {
            int distance = Math.abs(currentCell.row - enemyHomeCell.row) +
                           Math.abs(currentCell.column - enemyHomeCell.column);
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
