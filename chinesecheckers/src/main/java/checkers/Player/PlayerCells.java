package checkers.Player;

import java.util.List;
import java.util.stream.Collectors;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.HomeCell;

public class PlayerCells {
    private final List<Cell> homeCells;
    private List<Cell> currentCells;

    public PlayerCells(List<Cell> homeCells, List<Cell> currentCells) {
        this.homeCells = homeCells;
        this.currentCells = currentCells;
    }

    public List<Cell> getHomeCells() {
        return homeCells;
    }

    public List<Cell> getCurrentCells() {
        return currentCells;
    }

    public void updateCurrentCells(List<Cell> newCurrentCells) {
        this.currentCells = newCurrentCells;
    }

    public List<Cell> getCellsOnEnemyHomeFields(CellColor playerColor) {
        CellColor enemyHomeColor = CellColor.getEnemy(playerColor);
        return currentCells.stream()
                .filter(cell -> cell instanceof HomeCell && ((HomeCell) cell).getHomeColor() == enemyHomeColor)
                .collect(Collectors.toList());
    }
}
