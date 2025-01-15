package checkers.Board;

/**
 * Checks if a cell is within the star-shaped board.
 */
public class StarShapeChecker {

    /**
     * Checks if the specified cell is within the first triangle of the star.
     *
     * @param row the row of the cell
     * @param column the column of the cell
     * @param gameSize the size of the game - how many cells are in the longest row in arm of the star
     * @return true if the cell is within the first triangle, false otherwise
     */
    public boolean checkFirstTriangle(int row, int column, int gameSize) {
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;
        int midColumn = columns / 2;

        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return false;
        }

        int startColumn = Math.max(0, midColumn - row);
        int endColumn = Math.min(columns - 1, midColumn + row);

        if (row >= rows - (gameSize + 1)/2) {
            return false;
        }

        return column >= startColumn && column <= endColumn;
    }

    /**
     * Checks if the specified cell is within the second triangle of the star.
     *
     * @param row the row of the cell
     * @param column the column of the cell
     * @param gameSize the size of the game - how many cells are in the longest row in arm of the star
     * @return true if the cell is within the second triangle, false otherwise
     */
    public boolean checkSecondTriangle(int row, int column, int gameSize) {
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;
        int midColumn = columns / 2;

        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return false;
        }

        int startColumn = Math.max(0, midColumn - (rows - 1 - row));
        int endColumn = Math.min(columns - 1, midColumn + (rows - 1 - row));

        if (row < (rows - gameSize - 1) / 2) {
            return false;
        }

        return column >= startColumn && column <= endColumn;
    }
}
