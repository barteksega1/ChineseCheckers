package checkers.Game;

import java.util.List;

import checkers.Cell.Cell;
import checkers.Player.PlayerCells;

public class GameWon {

    public boolean isGameWon(PlayerCells playerCells, PlayerCells enemyCells) {
        List<Cell> playerCurrentCells = playerCells.getCurrentCells();
        List<Cell> enemyHomeCells = enemyCells.getHomeCells();

        for (Cell cell : playerCurrentCells) {
            if (!enemyHomeCells.contains(cell)) {
                return false;
            }
        }
        return true;
    }
}
