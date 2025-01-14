package checkers.Move;

/**
 * Utility class for parsing move strings into integer arrays.
 */
public abstract class MoveParser {

    /**
     * Parses a move string array into an integer array.
     *
     * @param moveString the move string array to parse
     * @return an integer array representing the move coordinates
     */
    public static int[] parseMove(String[] moveString) {
        int[] parMove = new int[moveString.length];
        for (int i = 0; i < moveString.length; i++) {
            try {
                parMove[i] = Integer.parseInt(moveString[i]);
            } catch (NumberFormatException e) {
                // Handle exception
            }
        }
        return parMove;
    }
}
