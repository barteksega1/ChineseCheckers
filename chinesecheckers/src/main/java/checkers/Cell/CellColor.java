package checkers.Cell;

public enum CellColor {

    RED,
    GREEN,
    BLUE,
    YELLOW,
    PURPLE,
    ORANGE,
    NONE;

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

