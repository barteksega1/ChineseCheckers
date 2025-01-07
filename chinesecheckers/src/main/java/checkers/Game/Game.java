package checkers.Game;

import java.util.ArrayList;

import checkers.Board.Board;
import checkers.Player.Player;

public class Game {
    
    private int playerCount;
    //private int botCount;
    private Board board;
    private ArrayList <Player> players = new ArrayList<>();
    private ArrayList <Player> winners = new ArrayList<>(); 

    public Game(int playerCount) {
        this.playerCount = playerCount;
        this.board = new Board();
        board.initializeSquare(6);
    }
    
    public Board getBoard() {
        return board;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
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


    //todo -- szukanie pól na boardzie po wspolrzednych
}
