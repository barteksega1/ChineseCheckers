package checkers.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Player.Player;
import checkers.Player.PlayerCells;

class FillPlayersTest {

    private Board board;
    private FillPlayers fillPlayers;
    private Player player;

    @BeforeEach
    public void setUp() {
        board = new Board();
        fillPlayers = new FillPlayers();
        player = new Player(1, CellColor.RED);
        board.initializeSquare(5);
    }

    @Test
    void testFillPlayerCells() {
        PlayerCells playerCells = fillPlayers.FillPlayerCells(board, player);

        // Check if home cells are correctly assigned
        for (Cell cell : playerCells.getHomeCells()) {
            assertEquals(CellStatus.OCCUPIED, cell.getStatus());
            assertEquals(player.getNumber(), cell.getPlayerNumber());
            assertEquals(player.getColor(), cell.getColor());
        }

        // Check if current cells are correctly assigned
        for (Cell cell : playerCells.getCurrentCells()) {
            assertTrue(cell.isOccupiedByPlayer(player.getNumber()));
        }
    }

    @Test
    void testFillPlayerCellsWithIllegalCells() {
        // Set some cells to ILLEGAL status
        Cell illegalCell = board.getCell(0, 0);
        illegalCell.setStatus(CellStatus.ILLEGAL);

        PlayerCells playerCells = fillPlayers.FillPlayerCells(board, player);

        // Ensure illegal cells are not included in player cells
        assertFalse(playerCells.getHomeCells().contains(illegalCell));
        assertFalse(playerCells.getCurrentCells().contains(illegalCell));
    }
}
