package checkers.Player;

import java.util.HashMap;
import java.util.Map;

import checkers.Cell.CellColor;

/**
 * Represents a player in the checkers game.
 */
public abstract class Player {
    private final int number;
    private final CellColor color;
    private final CellColor enemyColor;
    private PlayerCells playerCells;
    private boolean isBot;

    private static final Map<Integer, Player> players = new HashMap<>();

    /**
     * Constructs a Player with the specified number and color.
     *
     * @param number the player's number
     * @param color the player's color
     */
    public Player(int number, CellColor color) {
        this.number = number;
        this.color = color;
        this.enemyColor = CellColor.getEnemy(color);
    }

    /**
     * Adds a player to the players map.
     *
     * @param number the player's number
     * @param color the player's color
     */
    public abstract void addPlayer(int number, CellColor color);

    /**
     * Gets the player's number.
     *
     * @return the player's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the player's color.
     *
     * @return the player's color
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * Gets the player's enemy color.
     *
     * @return the player's enemy color
     */
    public CellColor getEnemyColor() {
        return enemyColor;
    }

    /**
     * Gets the player's cells.
     *
     * @return the player's cells
     */
    public PlayerCells getPlayerCells() {
        return playerCells;
    }

    /**
     * Sets the player's cells.
     *
     * @param playerCells the player's cells
     */
    public void setPlayerCells(PlayerCells playerCells) {
        this.playerCells = playerCells;
    }

    /**
     * Gets a player by their number.
     *
     * @param number the player's number
     * @return the player with the specified number
     */
    public static Player getPlayerByNumber(int number) {
        return players.get(number);
    }
}

