package checkers.Move;

public class SavedMove {
    private int playerNumber;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    // Default constructor (required by Jackson)
    public SavedMove() {
    }
    
    // Constructor
    public SavedMove(int playerNumber, int startX, int startY, int endX, int endY) {
        this.playerNumber = playerNumber;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    // Getters and setters
    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}
