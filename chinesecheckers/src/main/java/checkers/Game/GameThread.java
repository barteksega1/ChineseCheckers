package checkers.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import checkers.Player.Player;
import checkers.Server.CommunicationDevice;

public class GameThread extends Thread {
    
    private boolean started = false;
    private Game game;
    //private ArrayList<Player> players = new ArrayList<>();
    private int numberOfPlayers = 0;
    private int numberOfJoinedPlayers
    private CommunicationDevice communicationDevice = new CommunicationDevice();



    public GameThread(Socket firstPlayer, BufferedReader firstBufferedReader, PrintWriter firstPrintWriter, int numberOfPlayers) {
    this.players = joinedPlayers;
    this.numberOfPlayers = numberOfPlayers;
    this.game = new Game(joinedPlayers);
    CommunicationDevice.setUp();
    addPlayer(firstPlayer, firstBufferedReader, firstPrintWriter);
    }

    @Override
    public void run() {
        while(game.getPlayerCount() < numberOfPlayers) {
            System.out.println("Waiting for " + (numberOfPlayers - game.getPlayerCount())  + "more players");
            try {
                synchronized(this) {
                    wait(5000);
                }
            }
            catch (InterruptedException ex) {};
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


        public void addPlayer(Socket player, BufferedReader br, PrintWriter pw) throws IOException {
            communicationData.addPlayer(player, br, pw);
            numberOfJoinedPlayers++;
        }

        public int getNumberOfJoinedPlayers() {
            return numberOfJoinedPlayers;
        }

        public int getNumberOfPlayers() {
            return numberOfPlayers;
        }


    }

