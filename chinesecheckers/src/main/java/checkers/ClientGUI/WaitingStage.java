package checkers.ClientGUI;

public class WaitingStage extends InfoStage {
    private final int playerNumber;

    public WaitingStage(int playerNumber) {
        this.playerNumber = playerNumber;
        this.setLabel("Hello Player " + this.playerNumber + ". Waiting for more players to join...");
    }
}
