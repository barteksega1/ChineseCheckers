package checkers.Cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {

    private Cell cell;

    @BeforeEach
    public void setUp() {
        cell = new PlayableCell(0, 0);
    }

    @Test
    void testSetAndGetColor() {
        cell.setColor(CellColor.RED);
        assertEquals(CellColor.RED, cell.getColor());
    }

    @Test
    void testSetAndGetStatus() {
        cell.setStatus(CellStatus.OCCUPIED);
        assertEquals(CellStatus.OCCUPIED, cell.getStatus());
    }

    @Test
    void testSetAndGetPlayerNumber() {
        cell.setPlayerNumber(1);
        assertEquals(1, cell.getPlayerNumber());
    }

    @Test
    void testIsHomeCell() {
        assertFalse(cell.isHomeCell());
    }

    @Test
    void testIsPlayable() {
        assertTrue(cell.isPlayable());
    }

    @Test
    void testSetPlayable() {
        cell.setPlayable(false);
        assertFalse(cell.isPlayable());
    }

    @Test
    void testIsOccupiedByPlayer() {
        cell.setPlayerNumber(1);
        assertTrue(cell.isOccupiedByPlayer(1));
        assertFalse(cell.isOccupiedByPlayer(2));
    }
}
