package checkers.Board;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StarShapeCheckerTest {

    private StarShapeChecker shapeChecker;

    @BeforeEach
    public void setUp() {
        shapeChecker = new StarShapeChecker();
    }

    @Test
    void testCheckFirstTriangle() {
        int gameSize = 5;

        // Test cells that should be in the first triangle
        assertTrue(shapeChecker.checkFirstTriangle(0, 9, gameSize));
        assertTrue(shapeChecker.checkFirstTriangle(4, 5, gameSize));

        // Test cells that should not be in the first triangle
        assertFalse(shapeChecker.checkFirstTriangle(0, 0, gameSize));
        assertFalse(shapeChecker.checkFirstTriangle(12, 9, gameSize));
    }

    @Test
    void testCheckSecondTriangle() {
        int gameSize = 5;

        // Test cells that should be in the second triangle
        assertTrue(shapeChecker.checkSecondTriangle(12, 9, gameSize));
        assertTrue(shapeChecker.checkSecondTriangle(8, 5, gameSize));

        // Test cells that should not be in the second triangle
        assertFalse(shapeChecker.checkSecondTriangle(0, 9, gameSize));
        assertFalse(shapeChecker.checkSecondTriangle(12, 0, gameSize));
    }
}
