package checkers.Board;
import java.util.ArrayList;
import java.util.List;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;


public class BoardFiller {

    public void fillBoard(Board board, int gameSize) {

        if ((gameSize + 1)/2 % 2 == 0) {
            for (int row = 0; row < board.getRows(); row++) {
                for (int column = 0; column < board.getColumns(); column++) {
                    if ((row % 2 == 0 && column % 2 != 0) || (row % 2 != 0 && column % 2 == 0)) {
                        Cell cell = board.getCell(row, column);
                        cell.setStatus(CellStatus.ILLEGAL);
                        board.setCell(cell, row, column);
                    }
                }
            }
        } else {
            for (int row = 0; row < board.getRows(); row++) {
                for (int column = 0; column < board.getColumns(); column++) {
                    if ((row % 2 == 0 && column % 2 == 0) || (row % 2 != 0 && column % 2 != 0)) {
                        Cell cell = board.getCell(row, column);
                        cell.setStatus(CellStatus.ILLEGAL);
                        board.setCell(cell, row, column);
                    }
                }
            }
        }
    }

    public List<List<HomeCell>> splitHomeCellsIntoGroups(Board board, int gameSize) {
        List<List<HomeCell>> groups = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            groups.add(new ArrayList<>());
        }
    
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;
        int midColumn = columns / 2;
    
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = board.getCell(row, column);
                if (cell instanceof HomeCell) {
                    if (row < gameSize && column >= midColumn - row && column <= midColumn + row) {
                        groups.get(0).add((HomeCell) cell); // Top arm
                    } else if (row >= rows - gameSize && column >= midColumn - (rows - 1 - row) && column <= midColumn + (rows - 1 - row)) {
                        groups.get(1).add((HomeCell) cell); // Bottom arm
                    } else if (column < gameSize && row >= midColumn - column && row <= midColumn + column) {
                        groups.get(2).add((HomeCell) cell); // Left arm
                    } else if (column >= columns - gameSize && row >= midColumn - (columns - 1 - column) && row <= midColumn + (columns - 1 - column)) {
                        groups.get(3).add((HomeCell) cell); // Right arm
                    }  else if (column < gameSize && row < gameSize * 2 ) {
                        groups.get(4).add((HomeCell) cell); // Left top arm
                    }  else if (column >= columns - gameSize && row < gameSize * 2 ) {
                        groups.get(5).add((HomeCell) cell); // Right top arm
                    }
                }
            }
        }
    
        CellColor[] colors = {CellColor.RED, CellColor.GREEN, CellColor.BLUE, CellColor.ORANGE, CellColor.PURPLE, CellColor.YELLOW};
    
        for (int i = 0; i < groups.size(); i++) {
            for (HomeCell cell : groups.get(i)) {
                cell.setColor(colors[i]);
            }
        }
    
        return groups;
    }
    
}    

