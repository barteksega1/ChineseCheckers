package checkers.Cell;

/**
 * Represents the color of a cell in the game.
 */
public enum CellColor {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    PURPLE,
    ORANGE,
    NONE;

    /**
     * Gets the CellColor corresponding to the specified number.
     *
     * @param number the number representing the color
     * @return the CellColor corresponding to the number
     */
    public static CellColor fromNumber(int number) {
        switch (number) {
            case 0:
                return CellColor.RED;
            case 1:
                return CellColor.GREEN;
            case 2:
                return CellColor.BLUE;
            case 3:
                return CellColor.YELLOW;
            case 4:
                return CellColor.PURPLE;
            case 5:
                return CellColor.ORANGE;
            default:
                return CellColor.NONE;
        }
    }

    /**
     * Gets the enemy color of the specified color.
     *
     * @param color the color to get the enemy of
     * @return the enemy color
     */
    public static CellColor getEnemy(CellColor color) {
        switch (color) {
            case RED:
                return GREEN;
            case GREEN:
                return RED;
            case BLUE:
                return ORANGE;
            case ORANGE:
                return BLUE;
            case PURPLE:
                return YELLOW;
            case YELLOW:
                return PURPLE;
            default:
                return NONE;
        }
    }
}

