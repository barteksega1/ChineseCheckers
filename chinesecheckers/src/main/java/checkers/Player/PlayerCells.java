package checkers.Player;

import java.util.List;
import java.util.stream.Collectors;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.HomeCell;

/**
 * Represents the cells occupied by a player, including home cells and current cells.
 */
public class PlayerCells {
    private final List<Cell> homeCells;
    private List<Cell> currentCells;

    /**
     * Constructs a PlayerCells object with the specified home cells and current cells.
     *
     * @param homeCells the list of home cells
     * @param currentCells the list of current cells
     */
    public PlayerCells(List<Cell> homeCells, List<Cell> currentCells) {
        this.homeCells = homeCells;
        this.currentCells = currentCells;
    }

    /**
     * Gets the list of home cells.
     *
     * @return the list of home cells
     */
    public List<Cell> getHomeCells() {
        return homeCells;
    }

    /**
     * Gets the list of current cells.
     *
     * @return the list of current cells
     */
    public List<Cell> getCurrentCells() {
        return currentCells;
    }

    /**
     * Updates the list of current cells.
     *
     * @param newCurrentCells the new list of current cells
     */
    public void updateCurrentCells(List<Cell> newCurrentCells) {
        this.currentCells = newCurrentCells;
    }

    /**
     * Gets the list of cells that are on enemy home fields.
     *
     * @param playerColor the color of the player
     * @return the list of cells on enemy home fields
     */
    public List<Cell> getCellsOnEnemyHomeFields(CellColor playerColor) {
        CellColor enemyHomeColor = CellColor.getEnemy(playerColor);
        return currentCells.stream()
                .filter(cell -> cell instanceof HomeCell && ((HomeCell) cell).getHomeColor() == enemyHomeColor)
                .collect(Collectors.toList());
    }
}
