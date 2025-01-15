package checkers.Cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayableCellTest {

    private PlayableCell playableCell;

    @BeforeEach
    public void setUp() {
        playableCell = new PlayableCell(0, 0);
    }

    @Test
    void testToString() {
        String expected = "PlayableCell{row=0, column=0, status=ILLEGAL, playerNumber=-1, color=NONE}";
        assertEquals(expected, playableCell.toString());
    }
}
