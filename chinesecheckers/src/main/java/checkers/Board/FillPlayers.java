package checkers.Board;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;

public class FillPlayers {
    public void fillPlayer(Board board, char colour, String playerName) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Cell cell = board.getCell(row, column);
                if (cell instanceof HomeCell && cell.getColor().toString().charAt(0) == colour) {
                    cell.setStatus(CellStatus.OCCUPIED);
                    cell.setPlayer(playerName);
                }
            }
        }
    }
}
