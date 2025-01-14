package checkers.Game;

/**
 * Provides a method to calculate the game size based on the number of pins.
 */
public class CountGameSize {

    /**
     * Calculates the game size based on the number of pins.
     *
     * @param numOfPins the number of pins in the game
     * @return the size of the game, which is the number of cells in the longest row in the arm of the star
     */
    public int getGameSize(int numOfPins) {
        return -2 + (int) Math.sqrt(1 + 8 * numOfPins);
    }
}
