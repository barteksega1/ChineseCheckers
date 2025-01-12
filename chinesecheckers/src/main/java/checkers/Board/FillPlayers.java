package checkers.Board;

import java.util.ArrayList;
import java.util.List;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;
import checkers.Player.PlayerCells;

public class FillPlayers {
    public PlayerCells fillPlayer(Board board, char colour, String playerName) {
        List<Cell> homeCells = new ArrayList<>();
        List<Cell> currentCells = new ArrayList<>();

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Cell cell = board.getCell(row, column);
                if (cell instanceof HomeCell && cell.getColor().toString().charAt(0) == colour) {
                    cell.setStatus(CellStatus.OCCUPIED);
                    cell.setPlayer(playerName);
                    homeCells.add(cell);
                }
                if (cell.isOccupiedByPlayer(playerName)) {
                    currentCells.add(cell);
                }
            }
        }

        return new PlayerCells(homeCells, currentCells);
    }
}


