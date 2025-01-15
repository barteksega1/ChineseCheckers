package checkers.Cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HomeCellTest {

    private HomeCell homeCell;

    @BeforeEach
    public void setUp() {
        homeCell = new HomeCell(CellColor.RED, 0, 0);
    }

    @Test
    void testGetAndSetHomeColor() {
        assertEquals(CellColor.RED, homeCell.getHomeColor());
        homeCell.setHomeColor(CellColor.BLUE);
        assertEquals(CellColor.BLUE, homeCell.getHomeColor());
    }

    @Test
    void testToString() {
        String expected = "HomeCell{row=0, column=0, status=FREE, playerNumber=-1, color=NONE, homeColor=RED}";
        assertEquals(expected, homeCell.toString());
    }
}
