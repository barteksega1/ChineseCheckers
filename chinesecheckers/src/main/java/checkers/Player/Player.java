package checkers.Player;

import checkers.Cell.CellColor;

public class Player {
    private final int number;
    private final CellColor color;
    private final CellColor enemyColor;
    private PlayerCells playerCells;

    public Player(int number, CellColor color) {
        this.number = number;
        this.color = color;
        enemyColor = CellColor.getEnemy(color);
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
}

