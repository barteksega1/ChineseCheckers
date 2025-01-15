package checkers.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.Cell.Cell;
import checkers.Cell.CellStatus;

class BoardBuilderTest {

    private Board board;
    private BoardBuilder boardBuilder;

    @BeforeEach
    public void setUp() {
        board = new Board();
        boardBuilder = new BoardBuilder();
    }



    @Test
    void testCellStatusIllegal() {
        int gameSize = 5;
        boardBuilder.setupBoard(board, gameSize);

        // Check if a specific cell is ILLEGAL
        Cell cell = board.getCell(0, 0);
        assertEquals(CellStatus.ILLEGAL, cell.getStatus());
    }
}
