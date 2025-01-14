package checkers.Game;

import java.util.ArrayList;

import checkers.Board.Board;
import checkers.Board.BoardBuilder;
import checkers.Cell.CellColor;
import checkers.Player.Player;

/**
 * Represents a game of Chinese Checkers.
 */
public class Game {
    private int playerCount;
    private final int gameSize;
    private Board board;
    private final BoardBuilder boardBuilder;
    private final ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> winners = new ArrayList<>();

    /**
     * Constructs a Game with the specified player count and game size.
     *
     * @param playerCount the number of players in the game
     * @param gameSize the size of the game - how many cells are in the longest row in the arm of the star
     */
    public Game(int playerCount, int gameSize) {
        this.playerCount = playerCount;
        this.gameSize = gameSize;
        this.boardBuilder = new BoardBuilder();
        this.board = new Board();
        boardBuilder.setupBoard(board, gameSize); // Use BoardBuilder to initialize the board with game size
        this.buildPlayers(playerCount);
        for (Player player : players) {
            board.initializePlayerCells(player);
        }
        board.validateIllegalCells(); // Validate illegal cells after initializing the board
    }

    /**
     * Builds the players for the game.
     *
     * @param playerCount the number of players to build
     */
    private void buildPlayers(final int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            CellColor newPlayerColor = CellColor.fromNumber(i);
            Player newPlayer = new Player(i, newPlayerColor);
            players.add(newPlayer);
        }
    }

    /**
     * Returns the board of the game.
     *
     * @return the board of the game
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the number of players in the game.
     *
     * @return the number of players in the game
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Returns the size of the game.
     *
     * @return the size of the game
     */
    public int getGameSize() {
        return gameSize;
    }

    /**
     * Returns the list of players in the game.
     *
     * @return the list of players in the game
     */
    public ArrayList<Player> getPlayers() {
        for (Player player : players) {
            System.out.println(player.getNumber() + " " + player.getColor().toString());
        }
        return players;
    }

    /**
     * Sets the board of the game.
     *
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Sets the number of players in the game.
     *
     * @param playerCount the number of players to set
     */
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    /**
     * Returns the list of winners in the game.
     *
     * @return the list of winners in the game
     */
    public ArrayList<Player> getWinners() {
        return winners;
    }

    /**
     * Sets the list of winners in the game.
     *
     * @param winners the list of winners to set
     */
    public void setWinners(ArrayList<Player> winners) {
        this.winners = winners;
    }

    /**
     * Returns the player with the specified number.
     *
     * @param number the number of the player to return
     * @return the player with the specified number
     */
    public Player getPlayerByNumber(int number) {
        return players.get(number);
    }
}
