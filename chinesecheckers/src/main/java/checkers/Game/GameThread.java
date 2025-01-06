package checkers.Game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import checkers.Player.Player;

public class GameThread extends Thread {
    
    private boolean started = false;
    private Game game;
    private ArrayList<Player> players = new ArrayList<>();
    private int numberOfPlayers = 0;



    public GameThread(ArrayList<Player> joinedPlayers, int numberOfPlayers) {
    this.players = joinedPlayers;
    this.numberOfPlayers = numberOfPlayers;
    this.game = new Game(joinedPlayers);
    }

    @Override
    public void run() {
        // while(game.getPlayerCount() < numberOfPlayers) {
        //     System.out.println("Waiting for " + (numberOfPlayers - game.getPlayerCount())  + "more players");
        //     try {
        //         synchronized(this) {
        //             wait(5000);
        //         }
        //     }
        //     catch (InterruptedException ex) {};
        System.out.println("\n Game is running \n");
        while(true)
        {
            try {
                synchronized(this) {
                wait(5000);
                System.out.println("\n Game is still running \n");
                }
            } catch (InterruptedException ex) {};
        }
        
      


        }


    }

