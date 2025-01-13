package checkers.Game;

import java.util.List;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Player.PlayerCells;

public class GameWon {

    public boolean isGameWon(PlayerCells playerCells, CellColor playerColor) {
        List<Cell> playerCurrentCells = playerCells.getCurrentCells();
        List<Cell> cellsOnEnemyHomeFields = playerCells.getCellsOnEnemyHomeFields(playerColor);

        return cellsOnEnemyHomeFields.size() == playerCurrentCells.size();
    }
}
