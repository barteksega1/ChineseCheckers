package checkers.ClientGUI;

public class WaitingStage extends InfoStage {

    public WaitingStage(int playerNumber) {
        super(playerNumber);
        this.setLabel("Hello Player " + playerNumber + ". Waiting for more players to join...");
    }
    
}
