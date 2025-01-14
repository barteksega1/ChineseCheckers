package checkers.Game;

import java.util.List;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Player.PlayerCells;

public abstract class GameWon {

    /**
     * Checks if the game is won by the player.
     *
     * @param playerCells the cells occupied by the player
     * @param playerColor the color of the player
     * @return true if the player has won the game, false otherwise
     */
    public static boolean isGameWon(PlayerCells playerCells, CellColor playerColor) {
        List<Cell> playerCurrentCells = playerCells.getCurrentCells();
        List<Cell> cellsOnEnemyHomeFields = playerCells.getCellsOnEnemyHomeFields(playerColor);

        return cellsOnEnemyHomeFields.size() == playerCurrentCells.size();
    }
}
