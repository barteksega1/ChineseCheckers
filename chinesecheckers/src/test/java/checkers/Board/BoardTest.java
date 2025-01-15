package checkers.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Cell.PlayableCell;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    void testInitializeSquare() {
        board.initializeSquare(5);
        assertEquals(5, board.getGameSize());
        assertEquals(19, board.getColumns());
        assertEquals(13, board.getRows());
    }

    @Test
    void testGetCell() {
        board.initializeSquare(5);
        Cell cell = board.getCell(0, 0);
        assertNotNull(cell);
        assertTrue(cell instanceof PlayableCell);
    }

    @Test
    void testSetCell() {
        board.initializeSquare(5);
        Cell newCell = new PlayableCell(0, 0);
        board.setCell(newCell, 0, 0);
        assertEquals(newCell, board.getCell(0, 0));
    }

    @Test
    void testValidateIllegalCells() {
        board.initializeSquare(5);
        Cell cell = board.getCell(0, 0);
        cell.setStatus(CellStatus.ILLEGAL);
        board.validateIllegalCells();
        assertEquals(CellStatus.ILLEGAL, cell.getStatus());
        assertEquals(CellColor.NONE, cell.getColor());
        assertEquals(-1, cell.getPlayerNumber());
        assertFalse(cell.isPlayable());
    }
}
