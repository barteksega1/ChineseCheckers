package checkers.Player;

import java.util.HashMap;
import java.util.Map;

import checkers.Cell.CellColor;

public class Player {
    private final int number;
    private final CellColor color;
    private final CellColor enemyColor;
    private PlayerCells playerCells;

    private static final Map<Integer, Player> players = new HashMap<>();

    public Player(int number, CellColor color) {
        this.number = number;
        this.color = color;
        this.enemyColor = CellColor.getEnemy(color);
    }

    public static void addPlayer(int number, CellColor color) {
        Player player = new Player(number, color);
        players.put(number, player);
    }

    public int getNumber() {
        return number;
    }

    public CellColor getColor() {
        return color;
    }

    public CellColor getEnemyColor() {
        return enemyColor;
    }

    public PlayerCells getPlayerCells() {
        return playerCells;
    }

    public void setPlayerCells(PlayerCells playerCells) {
        this.playerCells = playerCells;
    }

    public static Player getPlayerByNumber(int number) {
        return players.get(number);
    }
}

