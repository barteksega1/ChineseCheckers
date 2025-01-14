package checkers.ClientGUI;

/**
 * Represents the stage that shows a waiting message for the player.
 */
public class WaitingStage extends InfoStage {
    private final int playerNumber;

    /**
     * Constructs a WaitingStage with the specified player number.
     *
     * @param playerNumber the number of the player who is waiting
     */
    public WaitingStage(int playerNumber) {
        this.playerNumber = playerNumber;
        this.setLabel("Hello Player " + this.playerNumber + ". Waiting for more players to join...");
    }
}
