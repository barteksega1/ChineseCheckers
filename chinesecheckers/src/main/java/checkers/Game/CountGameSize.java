package checkers.Game;

public class CountGameSize {
    public int getGameSize(int numOfPins) {
        return -2 + (int) Math.sqrt(1 + 8 * numOfPins);
    }
}
