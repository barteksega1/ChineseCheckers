package checkers.Board;

public class StarShapeChecker {

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
