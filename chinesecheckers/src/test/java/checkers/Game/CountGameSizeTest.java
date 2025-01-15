package checkers.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountGameSizeTest {

    private CountGameSize countGameSize;

    @BeforeEach
    public void setUp() {
        countGameSize = new CountGameSize();
    }

    @Test
    void testGetGameSize() {
        assertEquals(3, countGameSize.getGameSize(3));
        assertEquals(7, countGameSize.getGameSize(11));
    }
}
