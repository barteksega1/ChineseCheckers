package checkers.Game;

import java.util.ArrayList;

import checkers.Board.Board;
import checkers.Board.BoardBuilder;
import checkers.Cell.CellColor;
import checkers.Player.Player;

public class Game {
    
    private int playerCount;
    private Board board;
    private BoardBuilder boardBuilder;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> winners = new ArrayList<>(); 

    public Game(int playerCount) {
        this.playerCount = playerCount;
        this.boardBuilder = new BoardBuilder();
        this.board = new Board();
        boardBuilder.setupBoard(board, 7); // Use BoardBuilder to initialize the board
        this.buildPlayers(playerCount);
        for (Player player : players) {
            board.initializePlayerCells(player);
        }
        board.validateIllegalCells(); // Validate illegal cells after initializing the board
    }

    private void buildPlayers(final int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            CellColor newPlayerColor = CellColor.fromNumber(i);
            Player newPlayer = new Player(i, newPlayerColor);
            players.add(newPlayer);
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public ArrayList<Player> getPlayers() {
        for(Player player : players) {
            System.out.println(player.getNumber() + " " + player.getColor().toString());
        }
        return players;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public ArrayList<Player> getWinners() {
        return winners;
    }

    public void setWinners(ArrayList<Player> winners) {
        this.winners = winners;
    }

    public Player getPlayerByNumber(int number) {
        return players.get(number);
    }
}
