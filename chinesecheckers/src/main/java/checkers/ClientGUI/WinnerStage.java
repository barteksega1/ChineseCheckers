package checkers.ClientGUI;

/**
 * Represents the stage that shows the winner of the game.
 */
public class WinnerStage extends InfoStage {
    private final int playerNumber;

    /**
     * Constructs a WinnerStage with the specified player number.
     *
     * @param playerNumber the number of the winning player
     */
    public WinnerStage(int playerNumber) {
        this.playerNumber = playerNumber;
        this.setLabel("Player " + this.playerNumber + " has won the game!");
    }
}
