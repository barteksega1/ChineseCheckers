package checkers.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.Cell.CellColor;

class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(1, CellColor.RED);
    }

    @Test
    void testGetNumber() {
        assertEquals(1, player.getNumber());
    }

    @Test
    void testGetColor() {
        assertEquals(CellColor.RED, player.getColor());
    }

    @Test
    void testGetEnemyColor() {
        assertEquals(CellColor.GREEN, player.getEnemyColor());
    }

    @Test
    void testSetAndGetPlayerCells() {
        PlayerCells playerCells = new PlayerCells(null, null);
        player.setPlayerCells(playerCells);
        assertEquals(playerCells, player.getPlayerCells());
    }

    @Test
    void testAddAndGetPlayerByNumber() {
        Player.addPlayer(2, CellColor.BLUE);
        Player retrievedPlayer = Player.getPlayerByNumber(2);
        assertNotNull(retrievedPlayer);
        assertEquals(2, retrievedPlayer.getNumber());
        assertEquals(CellColor.BLUE, retrievedPlayer.getColor());
    }

    @Test
    void testGetPlayerByNumberNotFound() {
        Player retrievedPlayer = Player.getPlayerByNumber(3);
        assertNull(retrievedPlayer);
    }
}
