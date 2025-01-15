package checkers.Game;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.Board.Board;
import checkers.Cell.CellColor;
import checkers.Player.Player;

class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(4, 5);
    }

    @Test
    void testGetBoard() {
        assertNotNull(game.getBoard());
    }

    @Test
    void testGetPlayerCount() {
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    void testGetGameSize() {
        assertEquals(5, game.getGameSize());
    }

    @Test
    void testGetPlayers() {
        ArrayList<Player> players = game.getPlayers();
        assertEquals(4, players.size());
        for (int i = 0; i < players.size(); i++) {
            assertEquals(i, players.get(i).getNumber());
            assertEquals(CellColor.fromNumber(i), players.get(i).getColor());
        }
    }

    @Test
    void testSetAndGetBoard() {
        Board newBoard = new Board();
        game.setBoard(newBoard);
        assertEquals(newBoard, game.getBoard());
    }

    @Test
    void testSetAndGetPlayerCount() {
        game.setPlayerCount(6);
        assertEquals(6, game.getPlayerCount());
    }

    @Test
    void testSetAndGetWinners() {
        ArrayList<Player> winners = new ArrayList<>();
        winners.add(new Player(1, CellColor.RED));
        game.setWinners(winners);
        assertEquals(winners, game.getWinners());
    }

    @Test
    void testGetPlayerByNumber() {
        Player player = game.getPlayerByNumber(1);
        assertNotNull(player);
        assertEquals(1, player.getNumber());
        assertEquals(CellColor.GREEN, player.getColor());
    }
}
