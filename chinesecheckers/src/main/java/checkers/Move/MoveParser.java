package checkers.Move;

public abstract class MoveParser {
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
