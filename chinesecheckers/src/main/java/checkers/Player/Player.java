package checkers.Player;

public class Player {
    private final String id;
    private final char symbol;

    public Player(String id, char symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public char getSymbol() {
        return symbol;
    }
}

