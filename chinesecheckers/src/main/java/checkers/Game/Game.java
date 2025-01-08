package checkers.Game;

import java.util.ArrayList;

import checkers.Board.Board;
import checkers.Cell.CellColor;
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
        this.buildPlayers(playerCount);
    }

    private void buildPlayers(final int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            CellColor newPlayerColor = null;
            Player newPlayer = null;
                    newPlayerColor = CellColor.fromNumber(i);
                    newPlayer = new Player(i, newPlayerColor);
                    players.add(newPlayer);
                
         }
    }

    // private void initializePlayer(PlayerType type, int playerNumber, int numberOfHumanPlayers, Player[] players) {
    //     FieldColor newPlayerColor = null;
    //     Player newPlayer = null;
    //     switch (type) {
    //         case HUMAN:
    //         	newPlayerColor = FieldColor.fromNumber(playerNumber);
    //             newPlayer = new HumanPlayer(newPlayerColor);
    //             players[playerNumber] = newPlayer;
    //             break;
    //         case BOT:
    //         	newPlayerColor = FieldColor.fromNumber(playerNumber + numberOfHumanPlayers);
    //             newPlayer = new BotPlayer(newPlayerColor);
    //             players[playerNumber + numberOfHumanPlayers] = newPlayer;
    //             break;
    //     }
    //     Piece[] newPlayerPieces = game.getPlayerPieces(newPlayerColor);
    //     newPlayer.setPieces(newPlayerPieces);
    // }

    public Board getBoard() {
        return board;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    // public void setPlayers(ArrayList<Player> players) {
    //     this.players = players;
    // }

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

    public Player getPlayerByNumber(int number) {
        return players.get(number);
    }



    //todo -- szukanie pól na boardzie po wspolrzednych
}
