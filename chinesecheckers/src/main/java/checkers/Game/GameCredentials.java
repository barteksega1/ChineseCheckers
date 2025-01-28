package checkers.Game;

public class GameCredentials {
    private int playerCount;
    private int pinCount;
    private int botCount;

    // Default constructor (required by Jackson)
    public GameCredentials() {
    }

    // Constructor
    public GameCredentials(int playerCount, int botCount, int pinCount) {
        this.playerCount = playerCount;
        this.pinCount = pinCount;
        this.botCount = botCount;
    }

    // Getters and setters
    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getPinCount() {
        return pinCount;
    }

    public void setPinCount(int pinCount) {
        this.pinCount = pinCount;
    }

    public int getBotCount() {
        return botCount;
    }

    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }
    
}
